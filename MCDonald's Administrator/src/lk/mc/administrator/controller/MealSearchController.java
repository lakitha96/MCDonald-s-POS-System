/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.mc.administrator.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.events.JFXDialogEvent;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import lk.mc.common.dto.ItemDTO;
import lk.mc.common.observer.Observer;
import lk.mc.common.service.ServiceFactory;
import lk.mc.common.service.custom.ItemService;
import lk.mc.administrator.proxy.ProxyHandler;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author lakitha
 */
public class MealSearchController implements Initializable, Observer {

    @FXML
    private StackPane stackPane;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Pane normalPane;
    @FXML
    private JFXTextField txtSearchBar;
    @FXML
    private ImageView imageView;
    @FXML
    private Label lblName;
    @FXML
    private Label lblPrice;
    @FXML
    private Label lblDeal;
    @FXML
    private Label lblDate;
    @FXML
    private Label lblQty;
    @FXML
    private JFXButton btnUpdate;
    @FXML
    private JFXTextField txtEstiQty;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadSearchBar();
        normalPane.setVisible(false);
        

        try {

            UnicastRemoteObject.exportObject(this, 0);
            ItemObserverController.getSubject().registerObserver(this);
        } catch (RemoteException ex) {
            Logger.getLogger(MealSearchController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(MealSearchController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void loadSearchBar() {

        try {
            //String  [] words={"akitha", "prabudh", "harshitha", "galle", "sri lanka"};
            List<String> names = new ArrayList<>();

            List<ItemDTO> allDetails = findAllMeals();
            for (ItemDTO i : allDetails) {
                names.add(i.getName());
            }
            TextFields.bindAutoCompletion(txtSearchBar, names);

        } catch (Exception ex) {
            Logger.getLogger(MealSearchController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static List<ItemDTO> findAllMeals() throws Exception {
        ItemService itemService = (ItemService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.ITEM);
        return itemService.findAll();
    }

    public static ItemDTO findMealByName(String name) throws Exception {
        ItemService itemService = (ItemService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.ITEM);
        return itemService.findByName(name);
    }
    
    private void setImage(String path){
        Image image = new Image("file:" + path,619,121,false,false);
                        imageView.setImage(image);
    }

    @FXML
    private void loadDetails(ActionEvent event) {
        try {

            normalPane.setVisible(true);
            FadeTransition ft = new FadeTransition(Duration.millis(500), normalPane);
            ft.setFromValue(0.0);
            ft.setToValue(1.0);
            ft.play();

            ItemDTO itemDTO = findMealByName(txtSearchBar.getText());
            System.err.println(itemDTO.getImagePath());
            
            setImage(itemDTO.getImagePath());
            

            Integer priceInPrimitive = itemDTO.getPrice().intValue();
            lblName.setText(itemDTO.getName());
            lblPrice.setText("Last net Price: Rs " + Integer.toString(priceInPrimitive) + ".00/=");
            lblDeal.setText("Special Offers: " + itemDTO.getDiscount() + "%");
            lblDate.setText("Deal Expiry Date: " + itemDTO.getValidDate());
            lblQty.setText(Integer.toString(itemDTO.getQtyAV()));

            if (itemDTO.getQtyAV() > 10) {
                lblQty.setStyle("-fx-text-fill: #4CAF50;");
            }

            btnUpdate.addEventHandler(MouseEvent.MOUSE_CLICKED, (eventEstimate) -> {
                try {
                    itemDTO.setCode(itemDTO.getCode());
                    itemDTO.setName(itemDTO.getName());
                    itemDTO.setDescription(itemDTO.getDescription());
                    itemDTO.setCategory(itemDTO.getCategory());
                    itemDTO.setDiscount(itemDTO.getDiscount());
                    itemDTO.setPrice(itemDTO.getPrice());
                    itemDTO.setImagePath(itemDTO.getImagePath());
                    itemDTO.setQtyAV(Integer.parseInt(txtEstiQty.getText()));

                    if (MealCollection.editController(itemDTO)) {
                        showSmartDialog("Sucessfully updated Estimate Quantity... ");
                    } else {
                        showSmartDialog("Something went wrong... Update Fail....");
                    }
                } catch (Exception ex) {
                    Logger.getLogger(MealSearchController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

            normalPane.setStyle("-fx-cursor: hand");
            normalPane.addEventHandler(MouseEvent.MOUSE_CLICKED, (loadEvent) -> {
                try {

                    StackPane sp = FXMLLoader.load(this.getClass().getResource("/lk/mc/mealcollection/view/Collection.fxml"));
                    anchorPane.getChildren().clear();
                    anchorPane.getChildren().add(sp);

                } catch (IOException ex) {
                    Logger.getLogger(MealSearchController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

        } catch (Exception ex) {
            Logger.getLogger(MealSearchController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void showSmartDialog(String message) {
        BoxBlur bb = new BoxBlur(3, 3, 3);
        JFXButton dialogButton = new JFXButton("OK");

        dialogButton.getStyleClass().add("button_dialog");
        JFXDialogLayout dialogLayout = new JFXDialogLayout();
        JFXDialog dialog = new JFXDialog(stackPane, dialogLayout, JFXDialog.DialogTransition.TOP);
        dialogButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent mouseEvent) -> {

            dialog.close();
        });

        dialogLayout.setHeading(new Label(message));
        dialogLayout.setActions(dialogButton);
        dialog.show();
        dialog.setOnDialogClosed((JFXDialogEvent event1) -> {

            anchorPane.setEffect(null);
        });

        anchorPane.setEffect(bb);
    }

    @FXML
    private void updateQuantity(MouseEvent event) {
        
    }

    @Override
    public void updateObserver() throws Exception {
        loadSearchBar();
    }

}

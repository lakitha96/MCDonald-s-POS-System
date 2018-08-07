/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.mc.mealcollection.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.events.JFXDialogEvent;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import lk.mc.common.dto.ItemDTO;
import lk.mc.common.service.ServiceFactory;
import lk.mc.common.service.custom.ItemService;
import lk.mc.mealcollection.proxy.ProxyHandler;

//import lk.ijse.pos.proxy.ProxyHandler;
//import lk.ijse.pos.service.ServiceFactory;
//import lk.ijse.pos.service.custom.CustomerService;
/**
 * FXML Controller class
 *
 * @author lakitha
 */
public class SaveNewMealController implements Initializable {

    @FXML
    private JFXTextField txtName;
    @FXML
    private JFXTextField txtPrice;

    private JFXTextField txtDiscount;
    private JFXTextField txtValidDate;
    @FXML
    private JFXButton btnChooseImage;
    @FXML
    private Label lblImageStatus;
    @FXML
    private JFXButton btnSave;
    @FXML
    private JFXButton btnCancel;

    private String imagePath;
    @FXML
    private JFXTextField txtqtyAV;
    @FXML
    private StackPane stackPane;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private ImageView imageView;
    @FXML
    private JFXTextField txtDes;
    @FXML
    private JFXTextField txtOffer;
    @FXML
    private JFXTextField txtCat;
    @FXML
    private JFXTextField txtExpieryDate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }


    @FXML
    private void chooseImage(MouseEvent event) {
        chooseImage();
        if(imagePath!=null){
            setImage(imagePath);
        }
    }

    @FXML
    private void saveMeal(MouseEvent event) {
        try {
            ItemDTO itemDTO = new ItemDTO();
            itemDTO.setName(txtName.getText());
            itemDTO.setDescription(txtDes.getText());
            itemDTO.setCategory(txtCat.getText());
            itemDTO.setDiscount(txtOffer.getText());
            itemDTO.setPrice(Double.parseDouble(txtPrice.getText()));
            itemDTO.setQtyAV(Integer.parseInt(txtqtyAV.getText()));
            itemDTO.setValidDate(txtExpieryDate.getText());

            if (imagePath != null) {
                itemDTO.setImagePath(imagePath);
                
            } else {
                //deafult image
            }

            if (mealController(itemDTO)) {

                BoxBlur bb = new BoxBlur(3, 3, 3);
                JFXButton dialogButton = new JFXButton("OK");

                dialogButton.getStyleClass().add("button_dialog");
                JFXDialogLayout dialogLayout = new JFXDialogLayout();
                JFXDialog dialog = new JFXDialog(stackPane, dialogLayout, JFXDialog.DialogTransition.TOP);
                dialogButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent mouseEvent) -> {

                    dialog.close();
                });

                dialogLayout.setHeading(new Label("Meal Saved.. Please checkout it on collection!"));
                dialogLayout.setActions(dialogButton);
                dialog.show();
                dialog.setOnDialogClosed((JFXDialogEvent event1) -> {

                    anchorPane.setEffect(null);
                });

                anchorPane.setEffect(bb);
            }
        } catch (Exception ex) {
            Logger.getLogger(SaveNewMealController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static boolean mealController(ItemDTO itemDTO) throws Exception {
        ItemService itemService = (ItemService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.ITEM);
        return itemService.saveItem(itemDTO);
    }

    private void saveButtonValidation() {
//        btnSave.disableProperty().bind(
//    Bindings.isEmpty(txtName.textProperty())
//    .and(Bindings.isEmpty(txtValidDate.textProperty())
//    .and(Bindings.isEmpty(txtDiscount.textProperty()))
//    .and(Bindings.isEmpty(txtqtyAV.textProperty()))
//    .and(Bindings.isEmpty(txtPrice.textProperty())))
//    
//        );
    }
    
    private void chooseImage() {
        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File("/home/lakitha/MEGAsync"));
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Images", "*.jpeg", "*.png", "*.jpg"));
        File selectedFile = fc.showOpenDialog(null);

        if (selectedFile != null) {
            imagePath = selectedFile.getAbsolutePath();
        }
    }
    
    private void setImage(String path){
        Image image = new Image("file:" + path,619,121,false,false);
                        imageView.setImage(image);
    }
    

}












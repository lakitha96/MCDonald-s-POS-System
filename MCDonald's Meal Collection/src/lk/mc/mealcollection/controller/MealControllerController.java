/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.mc.mealcollection.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.io.File;
import java.net.URL;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import javafx.util.Duration;
import lk.mc.common.dto.ItemDTO;
import lk.mc.common.observer.Observer;
import lk.mc.common.service.ServiceFactory;
import lk.mc.common.service.custom.ItemService;
import lk.mc.mealcollection.controller.ApplicationControlsController;
import lk.mc.mealcollection.controller.ApplicationControlsController;
import lk.mc.mealcollection.controller.ItemObserverController;
import lk.mc.mealcollection.controller.ItemObserverController;
import lk.mc.mealcollection.controller.MealSearchBarController;
import lk.mc.mealcollection.controller.MealSearchBarController;
import lk.mc.mealcollection.proxy.ProxyHandler;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author lakitha
 */
public class MealControllerController implements Initializable, Observer {

    /**
     * Initializes the controller class.
     */
    @FXML
    private StackPane stackPane;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private JFXTreeTableView<Item> mealTreeTable;

    @FXML
    private Label lblInstruction;

    @FXML
    private Pane normalPane;

    @FXML
    private ImageView imageView;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtPrice;

    @FXML
    private JFXTextField txtOffer;

    @FXML
    private JFXTextField txtOfferDate;

    @FXML
    private JFXTextField txtQTY;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnEdit;
    @FXML
    private JFXTextField txtCat;
    @FXML
    private JFXTextField txtDes;
    @FXML
    private JFXTextField txtSearch;

    private String imagePath;

    private int tempCode;

    //private String reservedItemId="";
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        startSetting();
        treeTableActivities();
        suggestCategory();
        
        try {
            UnicastRemoteObject.exportObject(this, 0);
            ItemObserverController.getSubject().registerObserver(this);
        } catch (Exception ex) {
            Logger.getLogger(CollectionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void startSetting() {
        lblInstruction.setVisible(true);
        normalPane.setVisible(false);
        txtSearch.addEventHandler(MouseEvent.MOUSE_CLICKED, (event) -> {
            txtSearch.selectAll();
        });
    }

    private void treeTableActivities(){
        try {

            /*Tree table start here*/
            JFXTreeTableColumn<Item, String> iName = new JFXTreeTableColumn<>("Fast Food");
            iName.setPrefWidth(249);
            iName.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Item, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Item, String> param) {
                    return param.getValue().getValue().itemName;
                }
            });

            /*Tree table data loading start here*/
            ObservableList<Item> items = FXCollections.observableArrayList();

            List<ItemDTO> allItems = MealSearchBarController.findAllMeals();
            for (ItemDTO itemDTO : allItems) {
                items.add(new Item(itemDTO.getName()));
            }

            final TreeItem<Item> root = new RecursiveTreeItem<Item>(items, RecursiveTreeObject::getChildren);

            mealTreeTable.getColumns().setAll(iName);
            mealTreeTable.setRoot(root);
            mealTreeTable.setShowRoot(false);

            /*Tree table  data filter start here*/
            txtSearch.textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    mealTreeTable.setPredicate(new Predicate<TreeItem<Item>>() {
                        @Override
                        public boolean test(TreeItem<Item> c) {
                            Boolean check = c.getValue().itemName.getValue().contains(newValue);
                            return check;
                        }
                    });
                }
            });

            mealTreeTable.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {

                StringProperty selectedItem = mealTreeTable.getSelectionModel().getSelectedItem().getValue().itemName;

                if (selectedItem.equals(null)) {

                    System.out.println(" No item selected");

                } else {
                    try {
                        lblInstruction.setVisible(false);
                        setAnimationFade(500, normalPane);
                        normalPane.setVisible(true);

                        ItemDTO itemDTO = MealSearchBarController.findMealByName(selectedItem.getValue());
                        txtName.setText(itemDTO.getName());
                        txtDes.setText(itemDTO.getDescription());
                        txtCat.setText(itemDTO.getCategory());
                        txtOffer.setText(itemDTO.getDiscount());
                        txtOfferDate.setText(itemDTO.getValidDate());
                        txtPrice.setText(Double.toString(itemDTO.getPrice()));
                        txtQTY.setText(Integer.toString(itemDTO.getQtyAV()));

                        tempCode = itemDTO.getCode();

                        setImage(itemDTO.getImagePath());

                        imageView.setStyle("-fx-cursor: hand");
                        imageView.addEventHandler(MouseEvent.MOUSE_CLICKED, (event) -> {
                            chooseImage();
                            setImage(imagePath);
                        });

                    } catch (Exception ex) {
                        Logger.getLogger(CollectionController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            });
        } catch (Exception ex) {
            Logger.getLogger(CollectionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void suggestCategory() {
        //List<ItemDTO> allDetails = findAllMeals();
        String[] allCat = {"Pizza", "Pasta", "Dessert", "Salad", "SoftDrinks"};
//            for (ItemDTO i : allDetails) {
//                names.add(i.getName());
//            }

        TextFields.bindAutoCompletion(txtCat, allCat);
    }

    private void chooseImage() {
        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File("/home/lakitha/MEGAsync"));
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Images", "*.jpeg", "*.png", "*.jpg"));
        File selectedFile = fc.showOpenDialog(null);

        if (selectedFile != null) {
            //System.err.println(selectedFile.getAbsolutePath());
            imagePath = selectedFile.getAbsolutePath();
        }
    }

    private void setImage(String path){
        Image image = new Image("file:" + path,619,121,false,false);
                        imageView.setImage(image);
    }

    @FXML
    private void delete(MouseEvent event) {

        //showSmartDialog("Sucessfully Deleted!");
        JFXButton okButton = new JFXButton("Delete");

        okButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (deleteEvent) -> {
            try {
                if (deleteController(tempCode)) {
                    ApplicationControlsController.showSmartDialog("Sucessfully Deleted!", stackPane, anchorPane);
                    treeTableActivities();
                    resetFields();
                } else {
                    ApplicationControlsController.showSmartDialog("Something went wrong. Delete Failed..", stackPane, anchorPane);                 
                }
            } catch (Exception ex) {
                Logger.getLogger(CollectionController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        JFXButton cancelButton = new JFXButton("Cancel");
        ApplicationControlsController.showSmartComfirmation(stackPane, anchorPane, "Are you sure about Delete? ",
                Arrays.asList(okButton, cancelButton));

    }

    public static boolean deleteController(int i) throws Exception {
        ItemService itemService = (ItemService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.ITEM);
        return itemService.delete(i);
    }

    @FXML
    private void editDetails(MouseEvent event) {
        try {
            ItemDTO itemDTO = new ItemDTO();
            itemDTO.setCode(tempCode);
            itemDTO.setName(txtName.getText());
            itemDTO.setDescription(txtDes.getText());
            itemDTO.setCategory(txtCat.getText());
            itemDTO.setDiscount(txtOffer.getText());
            itemDTO.setPrice(Double.parseDouble(txtPrice.getText()));
            itemDTO.setQtyAV(Integer.parseInt(txtQTY.getText()));
            itemDTO.setValidDate(txtOfferDate.getText());
            itemDTO.setImagePath(imagePath);

            if (editController(itemDTO)) {
                ApplicationControlsController.showSmartDialog("Sucessfully database updated!", stackPane, anchorPane);
                treeTableActivities();
                resetFields();
            } else {
                ApplicationControlsController.showSmartDialog("Something went wrong. update failed....", stackPane, anchorPane);
            }
        } catch (Exception ex) {
            Logger.getLogger(CollectionController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static boolean editController(ItemDTO i) throws Exception {
        ItemService itemService = (ItemService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.ITEM);
        return itemService.update(i);
    }

    

    private void resetFields() {
        txtName.setText(null);
        txtDes.setText(null);
        txtCat.setText(null);
        txtOffer.setText(null);
        txtOfferDate.setText(null);
        txtPrice.setText(null);
        txtQTY.setText(null);
        normalPane.setVisible(false);
        lblInstruction.setVisible(true);
    }

    private void setAnimationFade(int miliSec, Pane pane) {
        FadeTransition ft = new FadeTransition(Duration.millis(miliSec), pane);
        ft.setFromValue(0.0);
        ft.setToValue(1.0);
        ft.play();
    }

    @Override
    public void updateObserver() throws Exception {
        Platform.runLater(() -> {
            treeTableActivities();
        });
        
    }

    class Item extends RecursiveTreeObject<Item> {

        StringProperty itemName;

        public Item(String name) {
            this.itemName = new SimpleStringProperty(name);
        }

    }

}

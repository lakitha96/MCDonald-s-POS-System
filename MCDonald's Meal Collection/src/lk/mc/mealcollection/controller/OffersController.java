
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.mc.mealcollection.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import lk.mc.common.dto.ItemDTO;

/**
 * FXML Controller class
 *
 * @author lakitha
 */
public class OffersController implements Initializable {

    @FXML
    private ImageView imageView;
    @FXML
    private StackPane stackPane;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private JFXComboBox<String> cmbMealNames;
    @FXML
    private JFXTextField txtExDate;
    @FXML
    private JFXTextField txtOffer;
    @FXML
    private JFXTextField txtPrice;
    @FXML
    private Label lblPriceWithOffer;
    @FXML
    private JFXButton btnGiveOffer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadAllMeals();
    }    

    private void loadAllMeals() {
        try {
            List<ItemDTO> allMeals=MealSearchBarController.findAllMeals();
            
            cmbMealNames.setPromptText("Select Meal Item...");
            for (ItemDTO i : allMeals) {
                cmbMealNames.getItems().add(i.getName());
                
            }
            
            
            
            cmbMealNames.valueProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    try {
                        ItemDTO itemDTO=MealSearchBarController.findMealByName(newValue);
                        txtPrice.setText("Rs"+Double.toString(itemDTO.getPrice())+"0/=");
                        setImage(itemDTO.getImagePath());
                        txtOffer.addEventHandler(KeyEvent.KEY_RELEASED, (event) -> {
                            

                            if(txtOffer.getText().equals(null) || txtOffer.getText().equals("")){
                                lblPriceWithOffer.setText("Rs 0.00");
                            }else{
                                lblPriceWithOffer.setText("Rs"+Double.toString(itemDTO.getPrice()-itemDTO.getPrice()*Double.parseDouble(txtOffer.getText())/100)+"0/=");
                            }
                            
                        });
                    } catch (Exception ex) {
                        Logger.getLogger(OffersController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
                
            
            
                
        
            
            
        } catch (Exception ex) {
            Logger.getLogger(OffersController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
    private void setImage(String path){
        Image image = new Image("file:" + path,619,121,false,false);
                        imageView.setImage(image);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.mc.administrator.controller;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

/**
 *
 * @author lakitha
 */
public class ControllMealSection implements Initializable{

    @FXML
    private StackPane stackPane;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private JFXButton btnCollection;
    @FXML
    private JFXButton btnSaveNewMeal;
    @FXML
    private JFXButton btnOffers;
    @FXML
    private AnchorPane seconderyPane;
    @FXML
    private JFXButton btnSearch;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
    
    

    @FXML
    private void loadCollection(MouseEvent event) {
        loadPane("/lk/mc/administrator/view/MealCollection.fxml");
    }

    @FXML
    private void saveNewMeal(MouseEvent event) {
        cleanAndClear();
        loadPane("/lk/mc/administrator/view/SaveNewMeal.fxml");
    }

    @FXML
    private void loadOfffers(MouseEvent event) {
    }

    private void loadPane(String path) {
        try {
            StackPane sp = FXMLLoader.load(this.getClass().getResource(path));

            seconderyPane.getChildren().add(sp);
            ApplicationControlsController.setAnimationFade(400, sp);
            ApplicationControlsController.setAnimationFade(400, seconderyPane);
        } catch (IOException ex) {
            Logger.getLogger(DashBoardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cleanAndClear() {
        try {

            seconderyPane.getChildren().clear();
        } catch (Exception ex) {
            Logger.getLogger(DashBoardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public void paneEffect() {
        BoxBlur bb = new BoxBlur(3, 3, 3);
        seconderyPane.setEffect(bb);
    }

    private void paneEffectNull() {
        seconderyPane.setEffect(null);
    }

    @FXML
    private void loadSearch(MouseEvent event) {
        loadPane("/lk/mc/administrator/view/MealSearch.fxml");
    }
    
}

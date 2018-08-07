/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.mc.mealcollection.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author lakitha
 */
public class DashBoardController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private JFXDrawer drawer;

    @FXML
    private JFXHamburger hamBurger;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private VBox vSlideBar;
    @FXML
    private JFXButton btnSaveNewMeal;
    @FXML
    private JFXButton btnCollection;
    @FXML
    private StackPane seconderyPane;
    @FXML
    private StackPane stackPane;

    HamburgerSlideCloseTransition transition;
    @FXML
    private JFXButton btnOffers;
    @FXML
    private JFXButton btnHome;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initDrawer();
        loadStarter();
    }

    private void initDrawer() {

        transition = new HamburgerSlideCloseTransition(hamBurger);
        transition.setRate(-1);
        //VBox vBox=FXMLLoader.load(getClass().getResource("/lk/mc/mealcollection/view/SlideBar.fxml"));
        //VBox vBox=FXMLLoader.load(vSlideBar);
        drawer.setSidePane(vSlideBar);

        hamBurger.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            transition.setRate(transition.getRate() * -1);
            transition.play();
            if (drawer.isShown()) {
                drawer.close();
                paneEffectNull();
            } else {
                drawer.open();
                paneEffect();
            }
        });

    }

    private void loadPane(String path) {

        Platform.runLater(() -> {
            try {
                StackPane sp = FXMLLoader.load(this.getClass().getResource(path));

                seconderyPane.getChildren().add(sp);
                ApplicationControlsController.setAnimationFade(400, sp);
                ApplicationControlsController.setAnimationFade(400, seconderyPane);
            } catch (IOException ex) {
                Logger.getLogger(DashBoardController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

    }

    public void cleanAndClear() {
        try {

            seconderyPane.getChildren().clear();
        } catch (Exception ex) {
            Logger.getLogger(DashBoardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void resetHamburger() {
        drawer.close();
        paneEffectNull();
        transition.setRate(transition.getRate() * -1);
        transition.play();
    }

    public void paneEffect() {
        BoxBlur bb = new BoxBlur(3, 3, 3);
        seconderyPane.setEffect(bb);
    }

    private void paneEffectNull() {
        seconderyPane.setEffect(null);
    }

    @FXML
    private void saveNewMeal(MouseEvent event) {
        cleanAndClear();
        loadPane("/lk/mc/mealcollection/view/SaveNewMeal.fxml");
        resetHamburger();
    }

    private void loadStarter() {
        loadPane("/lk/mc/mealcollection/view/MealSearchBar.fxml");
    }

    @FXML
    private void loadCollection(MouseEvent event) {
        loadPane("/lk/mc/mealcollection/view/Collection.fxml");
        resetHamburger();
    }

    @FXML
    private void loadHomeScreen(MouseEvent event) {
        loadStarter();
    }

    @FXML
    private void loadOfffers(MouseEvent event) {
        cleanAndClear();
        loadPane("/lk/mc/mealcollection/view/Offers.fxml");
        resetHamburger();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.mc.administrator.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author lakitha
 */
public class DashBoardController implements Initializable {

    @FXML
    private StackPane stackPane;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private JFXHamburger hamburger;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private VBox vBox;
    @FXML
    private JFXButton btnCustomer;
    @FXML
    private JFXButton btnCashier;
    @FXML
    private JFXButton btnChefs;
    @FXML
    private JFXButton btnTeleOperators;
    @FXML
    private AnchorPane seconderyPane;

    private HamburgerSlideCloseTransition transition;
    @FXML
    private JFXButton btnMealController;

    private String onGoingButton;
    private String prevousOnGoingButton;
    @FXML
    private Label lblExit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> {
            loadSlideBar();
        });

        buttonStyle();
        specialBtnStyle(btnCustomer);

        loadPane("/lk/mc/administrator/view/Report.fxml");
        resetHamburger();

    }

    public void buttonStyle() {
        btnCustomer.setStyle("-fx-background-color:#37474F");
        btnCustomer.getStyleClass().add("toolBarReset");

        btnCashier.setStyle("-fx-background-color:#37474F");
        btnCashier.getStyleClass().add("toolBarReset");

        btnChefs.setStyle("-fx-background-color:#37474F");
        btnChefs.getStyleClass().add("toolBarReset");

        btnMealController.setStyle("-fx-background-color:#37474F");
        btnMealController.getStyleClass().add("toolBarReset");

        btnTeleOperators.setStyle("-fx-background-color:#37474F");
        btnTeleOperators.getStyleClass().add("toolBarReset");

    }

    public void specialBtnStyle(JFXButton node) {
        node.setStyle("-fx-background-color:#607D8B");
    }

    @FXML
    private void loadCustomer(MouseEvent event) {
        buttonStyle();
        specialBtnStyle(btnCustomer);

        loadPane("/lk/mc/administrator/view/CustomerCenter.fxml");
        resetHamburger();
    }

    @FXML
    private void loadCashier(MouseEvent event) {
        buttonStyle();
        specialBtnStyle(btnCashier);

        loadPane("/lk/mc/administrator/view/Cashier.fxml");
        resetHamburger();
    }

    @FXML
    private void loadChefs(MouseEvent event) {
        buttonStyle();
        specialBtnStyle(btnChefs);
        loadPane("/lk/mc/administrator/view/Chef.fxml");
        resetHamburger();
    }

    @FXML
    private void btnTeleOperators(MouseEvent event) {
        buttonStyle();
        specialBtnStyle(btnTeleOperators);
        loadPane("/lk/mc/administrator/view/TelephoneOperator.fxml");
        resetHamburger();
    }

    void loadPane(String path) {

        Platform.runLater(() -> {
            try {
                seconderyPane.getChildren().clear();
                StackPane sp = FXMLLoader.load(this.getClass().getResource(path));
                ApplicationControlsController.setAnimationFade(500, sp);
                seconderyPane.getChildren().add(sp);
            } catch (IOException ex) {
                Logger.getLogger(DashBoardController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

    }

    private void loadSlideBar() {
        BoxBlur bb = new BoxBlur(3, 3, 3);
        transition = new HamburgerSlideCloseTransition(hamburger);
        transition.setRate(-1);

        drawer.setSidePane(vBox);

        hamburger.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            //transition.setRate(transition.getRate() * -1);
            //transition.play();
            if (drawer.isShown()) {
                drawer.close();
                drawer.setDisable(true);
                paneEffectNull();
            } else {
                drawer.open();
                drawer.setDisable(false);
                paneEffect();
            }
        });
    }

    private void resetHamburger() {
        drawer.close();
        drawer.setDisable(true);
        paneEffectNull();
//        transition.setRate(transition.getRate() * -1);
//        transition.play();

    }

    public void paneEffect() {
        BoxBlur bb = new BoxBlur(3, 3, 3);
        seconderyPane.setEffect(bb);
    }

    private void paneEffectNull() {
        seconderyPane.setEffect(null);
    }

    @FXML
    private void mealController(MouseEvent event) {

        buttonStyle();
        specialBtnStyle(btnMealController);
        loadPane("/lk/mc/administrator/view/MealUnit.fxml");
        resetHamburger();

    }

    @FXML
    private void exitFromApplication(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    private void logout(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/lk/mc/administrator/view/Login.fxml"));
        Stage primaryStage = new Stage();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();
        stackPane.getScene().getWindow().hide();
    }

}

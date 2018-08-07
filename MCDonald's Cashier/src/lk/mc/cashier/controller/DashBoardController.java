/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.mc.cashier.controller;

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
    private JFXButton btnCustomer;
    @FXML
    private StackPane stackPane;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private AnchorPane seconderyPane;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private VBox vBox;
    @FXML
    private JFXHamburger hamburger;

    HamburgerSlideCloseTransition transition;
    @FXML
    private JFXButton btnOrder;
    @FXML
    private Label lblExit;
    @FXML
    private Label lblLogout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadSlideBar();
        lblExit.addEventHandler(MouseEvent.MOUSE_CLICKED, (event) -> {
            System.exit(0);
        });
        lblLogout.addEventHandler(MouseEvent.MOUSE_CLICKED, (ev1) -> {
            try {
                Parent root = FXMLLoader.load(this.getClass().getResource("/lk/mc/cashier/view/Login.fxml"));
                Stage primaryStage = new Stage();
                Scene scene = new Scene(root);
                primaryStage.setScene(scene);
                primaryStage.initStyle(StageStyle.UNDECORATED);
                primaryStage.show();
                stackPane.getScene().getWindow().hide();
            } catch (IOException ex) {
                Logger.getLogger(DashBoardController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        loadPane("/lk/mc/cashier/view/Report.fxml");
    }

    @FXML
    private void loadCustomer(MouseEvent event) {
        loadPane("/lk/mc/cashier/view/CustomerCenter.fxml");
        drawer.close();
        drawer.setDisable(true);
    }

    void loadPane(String path) {
        Platform.runLater(() -> {
            try {
                seconderyPane.getChildren().clear();
                StackPane sp = FXMLLoader.load(this.getClass().getResource(path));
                seconderyPane.getChildren().add(sp);
            } catch (IOException ex) {
                Logger.getLogger(DashBoardController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

    }

    private void loadSlideBar() {
        transition = new HamburgerSlideCloseTransition(hamburger);
        transition.setRate(-1);
        //VBox vBox=FXMLLoader.load(getClass().getResource("/lk/mc/mealcollection/view/SlideBar.fxml"));
        //VBox vBox=FXMLLoader.load(vSlideBar);
        drawer.setSidePane(vBox);

        hamburger.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
//            transition.setRate(transition.getRate() * -1);
//            transition.play();
            if (drawer.isShown()) {
                drawer.close();
                drawer.setDisable(true);
                //paneEffectNull();
            } else {
                drawer.setDisable(false);
                drawer.open();
                //paneEffect();
            }
        });
    }

    @FXML
    private void loadOrder(MouseEvent event) {
        loadPane("/lk/mc/cashier/view/Order.fxml");
        drawer.close();
        drawer.setDisable(true);
    }

}

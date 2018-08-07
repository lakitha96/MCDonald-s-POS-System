/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.mc.cashier.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lk.mc.common.dto.CashierLoginDTO;
import lk.mc.common.service.ServiceFactory;
import lk.mc.common.service.custom.CashierLoginService;
import org.jasypt.util.password.StrongPasswordEncryptor;

public class LoginController implements Initializable {

    @FXML
    private AnchorPane ap;
    @FXML
    private JFXTextField txtUserName;
    @FXML
    private JFXPasswordField password;
    @FXML
    private JFXButton btnLogin;
    @FXML
    private Label lblClose;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            btnLogin.disableProperty().bind(
                    Bindings.isEmpty(txtUserName.textProperty())
                            .and(Bindings.isEmpty(password.textProperty())));

//            StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor();
//
//            CashierLoginDTO cashierLoginDTO=new CashierLoginDTO();
//            cashierLoginDTO.setUserName("cash");
//            //cashierLoginDTO.setPassword(encryptor.encryptPassword(password));
//            cashierLoginDTO.setPassword(encryptor.encryptPassword("cash"));
//            saveLoginDetails(cashierLoginDTO);
        } catch (Exception ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void loginDetails(MouseEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/lk/mc/cashier/view/DashBoard.fxml"));
            Scene Scene = new Scene(parent);
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Dashboard");
            stage.setScene(Scene);

            StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor();

            if (encryptor.checkPassword(password.getText(), getUserDetails(txtUserName.getText()).getPassword())) {
                stage.show();
                ap.getScene().getWindow().hide();

            } else {
                txtUserName.getStyleClass().add("wrong-detail");
                password.getStyleClass().add("wrong-detail");
                password.setTooltip(new Tooltip("Either UserName or Password Wrong"));
            }
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private static boolean saveLoginDetails(CashierLoginDTO cashierLoginDTO) throws Exception {
        CashierLoginService cashierLoginService = (CashierLoginService) lk.mc.cashier.proxy.ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.CASHIERLOGIN);
        return cashierLoginService.save(cashierLoginDTO);
    }

    private static CashierLoginDTO getUserDetails(String username) throws Exception {
        CashierLoginService cashierLoginService = (CashierLoginService) lk.mc.cashier.proxy.ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.CASHIERLOGIN);
        return cashierLoginService.findByName(username);
    }

    @FXML
    private void closeLogin(MouseEvent event) {
        System.exit(0);
    }

}

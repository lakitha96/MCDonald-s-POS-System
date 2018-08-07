/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.mc.administrator.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXProgressBar;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lk.mc.administrator.proxy.ProxyHandler;
import lk.mc.common.dto.AdminLoginDTO;
import lk.mc.common.service.ServiceFactory;
import lk.mc.common.service.custom.AdminLoginService;
import org.jasypt.util.password.StrongPasswordEncryptor;

/**
 * FXML Controller class
 *
 * @author lakitha
 */
public class LoginController implements Initializable {

    @FXML
    private AnchorPane ap;
    @FXML
    private JFXTextField txtUserName;
    @FXML
    private JFXPasswordField password;
    @FXML
    private FontAwesomeIconView icnLock;
    @FXML
    private FontAwesomeIconView icnUser;
    @FXML
    private FontAwesomeIconView icnKey;
    @FXML
    private JFXButton btnLogin;
    @FXML
    private Label lblClose;
    @FXML
    private JFXProgressBar progress;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            progress.setVisible(false);

            btnLogin.disableProperty().bind(
                    Bindings.isEmpty(txtUserName.textProperty())
                            .and(Bindings.isEmpty(password.textProperty()))
            );

//            StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor();
//            
//            AdminLoginDTO adminLoginDTO=new AdminLoginDTO();
//            adminLoginDTO.setUserName("laki");
//            adminLoginDTO.setPassword(encryptor.encryptPassword("tharu"));
//            saveAdminLoginDetails(adminLoginDTO);
        } catch (Exception ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void loginDetails(MouseEvent event) throws IOException, Exception {
        
        Parent parent = FXMLLoader.load(getClass().getResource("/lk/mc/administrator/view/DashBoard.fxml"));
        Scene Scene = new Scene(parent);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("Dashboard");
        stage.setScene(Scene);

        Platform.runLater(() -> {
            try {
                StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor();
                
                if (encryptor.checkPassword(password.getText(), getLoginDetails(txtUserName.getText()).getPassword())) {
                    stage.show();
                    ap.getScene().getWindow().hide();
                    
                } else {
                    txtUserName.getStyleClass().add("wrong-detail");
                    password.getStyleClass().add("wrong-detail");
                    password.setTooltip(new Tooltip("Either UserName or Password Wrong"));
                }
            } catch (Exception ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

    }

    public static AdminLoginDTO getLoginDetails(String name) throws Exception {
        //CustomerService customerService = (CustomerService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.CUSTOMER);
        AdminLoginService adminLoginService = (AdminLoginService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.ADMINLOGIN);
        return adminLoginService.findByName(name);
    }

    public static boolean saveAdminLoginDetails(AdminLoginDTO adminLoginDTO) throws Exception {
        AdminLoginService adminLoginService = (AdminLoginService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.ADMINLOGIN);
        return adminLoginService.save(adminLoginDTO);
    }

    @FXML
    private void closeLogin(MouseEvent event) {
        System.exit(0);
    }
}

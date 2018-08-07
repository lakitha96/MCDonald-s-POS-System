/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.mc.administrator.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import lk.mc.administrator.proxy.ProxyHandler;
import lk.mc.common.dto.TelephoneOperatorDTO;
import lk.mc.common.service.ServiceFactory;
import lk.mc.common.service.custom.TelephoneOperatorService;

/**
 * FXML Controller class
 *
 * @author lakitha
 */
public class TelephoneOperatorController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private StackPane stackPane;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private JFXTreeTableView<?> treeTable;

    @FXML
    private JFXTextField txtTeleID;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnUpdate;

    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    @FXML
    void btnDelete(MouseEvent event) {

    }

    @FXML
    void btnUpdate(MouseEvent event) {

    }

    @FXML
    void saveOperator(MouseEvent event) {
        try {
            TelephoneOperatorDTO telephoneOperatorDTO=new TelephoneOperatorDTO(Integer.parseInt(txtTeleID.getText()), txtName.getText());
            if(saveOpeatorController(telephoneOperatorDTO)){
                ApplicationControlsController.showSmartDialog("Operator has been sucessfully saved..", stackPane, treeTable);
            }else{
                ApplicationControlsController.showSmartDialog("Failed....", stackPane, treeTable);
            }
        } catch (Exception ex) {
            Logger.getLogger(TelephoneOperatorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static boolean saveOpeatorController(TelephoneOperatorDTO operatorDTO) throws Exception{
        TelephoneOperatorService operatorService=(TelephoneOperatorService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.TELEOPERATOR);
        return operatorService.save(operatorDTO);
    }
    
}

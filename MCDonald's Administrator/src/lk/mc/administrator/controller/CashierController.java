/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.mc.administrator.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import lk.mc.administrator.proxy.ProxyHandler;
import lk.mc.common.dto.CashierDTO;
import lk.mc.common.service.ServiceFactory;
import lk.mc.common.service.custom.CashierService;

/**
 * FXML Controller class
 *
 * @author lakitha
 */
public class CashierController implements Initializable {

    
    @FXML
    private JFXTextField txtID;
    @FXML
    private JFXButton btnSave;
    @FXML
    private JFXButton brnUpdate;
    @FXML
    private JFXButton btnDelete;
    @FXML
    private ImageView imageView;
    @FXML
    private StackPane stackPane;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private JFXTextField txtCashierName;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    public static boolean saveCashierController(CashierDTO cashierDTO)throws Exception{
        CashierService cashierService=(CashierService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.CASHIER);
        return cashierService.save(cashierDTO);
    }
    
    public static boolean updateCashierController(CashierDTO cashierDTO)throws Exception{
        CashierService cashierService=(CashierService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.CASHIER);
        return cashierService.update(cashierDTO);
    }
    
    public static CashierDTO getCashierByname(String name) throws Exception{
        CashierService cashierService=(CashierService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.CASHIER);
        return cashierService.findByName(name);
    }
    
    public static List<CashierDTO> allCashiers() throws Exception{
        CashierService cashierService=(CashierService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.CASHIER);
        return cashierService.findAll();
    }
    
    public static boolean deletCashierController(String ID)throws Exception{
        CashierService cashierService=(CashierService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.CASHIER);
        return cashierService.delete(ID);
    }

    @FXML
    private void saveCashier(MouseEvent event) throws Exception {
        CashierDTO cdto=new CashierDTO(txtID.getText(), txtCashierName.getText());
        if(saveCashierController(cdto)){
            ApplicationControlsController.showSmartDialog("Cashier Saved...", stackPane, anchorPane);
        }else{
            ApplicationControlsController.showSmartDialog("Cashier Saving Failed...", stackPane, anchorPane);
        }
    }

    @FXML
    private void updateCashier(MouseEvent event) throws Exception {
        CashierDTO cashierDTO=new CashierDTO(txtID.getText(), txtCashierName.getText());
        if(updateCashierController(cashierDTO)){
            ApplicationControlsController.showSmartDialog("Cashier updated...", stackPane, anchorPane);
        }else{
            ApplicationControlsController.showSmartDialog("Cashier updating Failed...", stackPane, anchorPane);
        }
    }

    @FXML
    private void deleteCashier(MouseEvent event) throws Exception {
        if(deletCashierController(txtID.getText())){
            ApplicationControlsController.showSmartDialog("Cashier details deleted...", stackPane, anchorPane);
        }else{
            ApplicationControlsController.showSmartDialog("Cashier delete Failed...", stackPane, anchorPane);
        }
    }
}

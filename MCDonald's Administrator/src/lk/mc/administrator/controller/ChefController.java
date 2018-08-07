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
import lk.mc.common.dto.ChefDTO;
import lk.mc.common.service.ServiceFactory;
import lk.mc.common.service.custom.ChefService;

/**
 * FXML Controller class
 *
 * @author lakitha
 */
public class ChefController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private JFXTreeTableView<?> treeTable;

    @FXML
    private JFXTextField txtChefID;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnUpdate;
    @FXML
    private StackPane stackPane;
    @FXML
    private AnchorPane anchorPane;
    
    private int passedSecond;
    private int passedMinitues;
    private int passedHours;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    void deleteChef(MouseEvent event) {

    }

    @FXML
    void saveChef(MouseEvent event) {
        try {
            ChefDTO chefDTO = new ChefDTO(Integer.parseInt(txtChefID.getText()), txtName.getText());
            if (chefSaveController(chefDTO)) {
                ApplicationControlsController.showSmartDialog("Chef Successfully Saved....", stackPane, treeTable);
            } else {
                ApplicationControlsController.showSmartDialog("Error, Something went wrong....", stackPane, treeTable);
            }
        } catch (Exception ex) {
            Logger.getLogger(ChefController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static boolean chefSaveController(ChefDTO chefDTO) throws Exception {
        ChefService chefService = (ChefService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.CHEF);
        return chefService.save(chefDTO);
    }

    @FXML
    void updateChef(MouseEvent event) {
    }

}

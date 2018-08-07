/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autocomplete;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author lakitha
 */
public class AutoCompleteFxController implements Initializable {

    @FXML
    private AnchorPane root;
    
    @FXML
    private JFXTextField txtAutoComplete;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String  [] words={"lakitha", "prabudh", "harshitha", "galle", "sri lanka"};
        TextFields.bindAutoCompletion(txtAutoComplete, words);
    }    
    
}

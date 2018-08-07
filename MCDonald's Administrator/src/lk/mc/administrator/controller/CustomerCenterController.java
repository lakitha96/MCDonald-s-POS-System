/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.mc.administrator.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;
import lk.mc.administrator.proxy.ProxyHandler;
import lk.mc.common.dto.CustomerDTO;
import lk.mc.common.service.ServiceFactory;
import lk.mc.common.service.custom.CustomerService;



/**
 * FXML Controller class
 *
 * @author lakitha
 */
public class CustomerCenterController implements Initializable {

    @FXML
    private JFXTextField txtCustomerName;

    @FXML
    private JFXTextField txtMobileNumber;

    @FXML
    private JFXTextField txtAddress;


    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXTreeTableView<ColNames> treeTable;

    @FXML
    private JFXTextField txtSearch;

    @FXML
    private JFXComboBox<String> cmbSearchCat;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnUpdate;
    @FXML
    private StackPane stackPane;
    @FXML
    private AnchorPane anchorPane;
    

    private int tempCustmerID;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> {
            try {
                loadTreeTableView();
                loadComboBox();
            } catch (Exception ex) {
                Logger.getLogger(CustomerCenterController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
            
        
    }

    public static boolean saveCustomerController(CustomerDTO customerDTO) throws Exception {
        CustomerService customerService = (CustomerService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.CUSTOMER);
        return customerService.addCustomer(customerDTO);
    }

    @FXML
    private void saveCustomer(MouseEvent event) throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setName(txtCustomerName.getText());
        customerDTO.setMobileNumber(txtMobileNumber.getText());
        customerDTO.setAddress(txtAddress.getText());

        if (saveCustomerController(customerDTO)) {
            ApplicationControlsController.showSmartDialog("Customer Sucessfully Saved....", stackPane, anchorPane);
        } else {
            ApplicationControlsController.showSmartDialog("Something went wrong customer saving failed....", stackPane, anchorPane);
        }
    }

    private void loadTreeTableView() throws Exception {
        //colums
        JFXTreeTableColumn<ColNames, String> cusName = new JFXTreeTableColumn<>("Customer Name");
        cusName.setPrefWidth(245);

        cusName.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ColNames, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<ColNames, String> param) {
                return param.getValue().getValue().customerName;
            }
        });

        JFXTreeTableColumn<ColNames, String> address = new JFXTreeTableColumn<>("Address");
        address.setPrefWidth(249);
        address.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ColNames, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<ColNames, String> param) {
                return param.getValue().getValue().address;
            }
        });

        JFXTreeTableColumn<ColNames, String> mobileNumber = new JFXTreeTableColumn<>("Mobile Number");
        mobileNumber.setPrefWidth(200);
        mobileNumber.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ColNames, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<ColNames, String> param) {
                return param.getValue().getValue().mobileNumber;
            }
        });

        //setLoad all customers
        ObservableList<ColNames> colums = FXCollections.observableArrayList();

        List<CustomerDTO> allCustomers = getAllCustomers();
        for (CustomerDTO customerDTO : allCustomers) {
            colums.add(new ColNames(customerDTO.getName(), customerDTO.getAddress(), customerDTO.getMobileNumber()));
            System.out.println(customerDTO.getName());

        }

        final TreeItem<ColNames> root = new RecursiveTreeItem<ColNames>(colums, RecursiveTreeObject::getChildren);

        treeTable.getColumns().setAll(cusName, address, mobileNumber);
        treeTable.setRoot(root);
        treeTable.setShowRoot(false);

        //item selection actions
        treeTable.addEventHandler(MouseEvent.MOUSE_CLICKED, (event) -> {
            StringProperty selectedCusName = treeTable.getSelectionModel().getSelectedItem().getValue().customerName;
            StringProperty selectedAddress = treeTable.getSelectionModel().getSelectedItem().getValue().address;
            StringProperty selectedMobileNumber = treeTable.getSelectionModel().getSelectedItem().getValue().mobileNumber;

            txtAddress.setText(selectedAddress.getValueSafe());
            txtCustomerName.setText(selectedCusName.getValueSafe());
            txtMobileNumber.setText(selectedMobileNumber.getValueSafe());

            try {
                CustomerDTO itemDTO = getCustomerByName(selectedCusName.getValueSafe());
                tempCustmerID = itemDTO.getCID();
            } catch (Exception ex) {
                Logger.getLogger(CustomerCenterController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        

        cmbSearchCat.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.equals("Customer Name")) {
                    txtSearch.textProperty().addListener(new ChangeListener<String>() {
                        @Override
                        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                            treeTable.setPredicate(new Predicate<TreeItem<ColNames>>() {
                                @Override
                                public boolean test(TreeItem<ColNames> c) {
                                    Boolean check = c.getValue().customerName.getValue().contains(newValue);
                                    return check;
                                }
                            });
                        }
                    });
                } else if (newValue.equals("Address")) {
                    txtSearch.textProperty().addListener(new ChangeListener<String>() {
                        @Override
                        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                            treeTable.setPredicate(new Predicate<TreeItem<ColNames>>() {
                                @Override
                                public boolean test(TreeItem<ColNames> c) {
                                    Boolean check = c.getValue().address.getValue().contains(newValue);
                                    return check;
                                }
                            });
                        }
                    });

                } else if (newValue.equals("Mobile Number")) {
                    txtSearch.textProperty().addListener(new ChangeListener<String>() {
                        @Override
                        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                            treeTable.setPredicate(new Predicate<TreeItem<ColNames>>() {
                                @Override
                                public boolean test(TreeItem<ColNames> c) {
                                    Boolean check = c.getValue().mobileNumber.getValue().contains(newValue);
                                    return check;
                                }
                            });
                        }
                    });

                }
            }
        });
    }

    public static List<CustomerDTO> getAllCustomers() throws Exception {
        CustomerService customerService = (CustomerService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.CUSTOMER);
        return customerService.findAll();
    }

    public static CustomerDTO getCustomerByName(String name) throws Exception {
        CustomerService customerService = (CustomerService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.CUSTOMER);
        return customerService.findByName(name);
    }

    @FXML
    void searchCustomer(ActionEvent event) {

    }

    public static boolean updateCustomerController(CustomerDTO customerDTO) throws Exception {
        CustomerService customerService = (CustomerService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.CUSTOMER);
        return customerService.updateCustomer(customerDTO);
    }

    public static boolean deleteCustomerController(int customerID) throws Exception {
        CustomerService customerService = (CustomerService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.CUSTOMER);
        return customerService.deleteCustomer(customerID);
    }

    @FXML
    private void deleteCustomer(MouseEvent event) {
        String tempDeletedCusName = txtCustomerName.getText();
        if (!(txtCustomerName.getText().equals(null) && txtAddress.getText().equals(null) && txtMobileNumber.getText().equals(null))) {
            JFXButton deleteButton = new JFXButton("Delete");
            deleteButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (deleteEvent) -> {
                try {
                    if (deleteCustomerController(tempCustmerID)) {
                        ApplicationControlsController.showSmartDialog("Sucessfully customer " + tempDeletedCusName + " has been Deleted!", stackPane, treeTable);
                        loadTreeTableView();
                    } else {
                        ApplicationControlsController.showSmartDialog("Customer " + tempDeletedCusName + " delete failed....", stackPane, treeTable);
                    }
                } catch (Exception ex) {
                    Logger.getLogger(CustomerCenterController.class.getName()).log(Level.SEVERE, null, ex);
                }

            });

            JFXButton cancelButton = new JFXButton("Cancel");
            ApplicationControlsController.showSmartComfirmation(stackPane, anchorPane, "Are you sure about delete Customer " + txtCustomerName.getText() + "?",
                    Arrays.asList(deleteButton, cancelButton));

        }
    }

    @FXML
    private void updateCustomer(MouseEvent event) throws Exception {
        if (!(txtCustomerName.getText().equals(null) && txtAddress.getText().equals(null) && txtMobileNumber.getText().equals(null))) {

            CustomerDTO customerDTO = new CustomerDTO(tempCustmerID,
                    txtCustomerName.getText(),
                    txtAddress.getText(),
                    txtMobileNumber.getText());

            if (updateCustomerController(customerDTO)) {
                ApplicationControlsController.showSmartDialog("Database Sucessfully Updated!", stackPane, anchorPane);
                loadTreeTableView();
            } else {
                ApplicationControlsController.showSmartDialog("Something went wrong, Database not updaeted...", stackPane, anchorPane);
            }

        }
    }

    private void loadComboBox() {
        //filtering 
        cmbSearchCat.setPromptText("Search By");
        
        List<String> allFilteringCtg = new ArrayList<String>(Arrays.asList("Customer Name", "Address", "Mobile Number"));

        for (String ctg : allFilteringCtg) {
            cmbSearchCat.getItems().add(ctg);
        }
    }

    private static class ColNames extends RecursiveTreeObject<ColNames> {

        StringProperty customerName;
        StringProperty address;
        StringProperty mobileNumber;

        public ColNames(String name, String mobileNumber, String address) {
            this.customerName = new SimpleStringProperty(name);
            this.address = new SimpleStringProperty(address);
            this.mobileNumber = new SimpleStringProperty(mobileNumber);

        }

    }
}

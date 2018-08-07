/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.mc.cashier.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.awt.Dialog;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;

import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.swing.JFrame;
import lk.ijse.jasper.IJSEJasperViewer;
import javafx.util.Duration;
import lk.ijse.jasper.IJSEJasperViewer;
import lk.mc.cashier.proxy.ProxyHandler;
import lk.mc.common.dto.CashierDTO;
import lk.mc.common.dto.CustomerDTO;
import lk.mc.common.dto.ItemDTO;
import lk.mc.common.dto.OrderDetailsDTO;
import lk.mc.common.dto.OrdersDTO;
import lk.mc.common.dto.TelephoneOperatorDTO;
import lk.mc.common.observer.Observer;
import lk.mc.common.service.ServiceFactory;
import lk.mc.common.service.custom.CashierService;

import lk.mc.common.service.custom.PlaceOrderService;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author lakitha
 */
public class OrderController implements Initializable, Observer {

    /**
     * Initializes the controller class.
     */
    @FXML
    private JFXTextField txtCustomerSearch;

    @FXML
    private Label txtCustomerDetailsDisplay;

    @FXML
    private JFXComboBox<String> cmbCustomerType;

    @FXML
    private JFXComboBox<String> cmbFilteringCtg;

    @FXML
    private JFXButton btnMealPicker;

    @FXML
    private JFXTreeTableView<ColNames> treeTable;

    @FXML
    private Label lblTotal;

    @FXML
    private JFXButton btnGetPaid;
    @FXML
    private StackPane stackPane;
    @FXML
    private AnchorPane anchorPane;

    ObservableList<OrderController.ColNames> colums = FXCollections.observableArrayList();
    @FXML
    private JFXTextField txtSearchByItemName;
    @FXML
    private JFXTextField txtBalance;
    @FXML
    private JFXTextField txtPaidAmount;
    @FXML
    private JFXTextField txtQuantity;
    @FXML
    private JFXButton btnAddToTable;
    @FXML
    private Label lblTimeDate;
    @FXML
    private JFXTextField txtOID;
    @FXML
    private JFXComboBox<String> cmbCashierID;

    private int minute;
    private int hour;
    private int second;

    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
    Date date = new Date();
    @FXML
    private JFXButton btnRemove;
    @FXML
    private JFXButton btnUpdate;
    @FXML
    private Label txtCashier;
    @FXML
    private Label lblCart;

    private int customerID;
    
    private double total;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            loadCBCustomerType();
            loadCBSearchCtg();
            loadSearchByMealName();
            loadTimeDate();
            loadCBCashier();
            cartUpdate();
            MealPickerController.registerObserver(this);
            txtBalance.setDisable(true);
            txtOID.setDisable(true);
        } catch (Exception ex) {
            Logger.getLogger(OrderController.class.getName()).log(Level.SEVERE, null, ex);
        }

        btnGetPaid.setDisable(true);

        cmbCustomerType.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (cmbCustomerType.getSelectionModel().getSelectedItem().equalsIgnoreCase("cash customer")) {
                    txtCustomerDetailsDisplay.setText("Cash Customer");
                } else {
                    cmbFilteringCtg.requestFocus();
                }
            }
        });

    }

    private void loadPane(String path) {
        try {
            StackPane sp = FXMLLoader.load(this.getClass().getResource(path));

            anchorPane.getChildren().add(sp);
            ApplicationControlsController.setAnimationFade(400, sp);
            ApplicationControlsController.setAnimationFade(400, anchorPane);
        } catch (IOException ex) {
            Logger.getLogger(OrderController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void searchCustomer(ActionEvent event) throws Exception {
        if (cmbCustomerType.getSelectionModel().getSelectedItem().equals("Normal Customer")) {
            if (cmbFilteringCtg.getSelectionModel().getSelectedItem().equals("Customer Name")) {
                if (txtCustomerSearch.getText() != null) {
                    CustomerDTO customerDTO = CustomerCenterController.getCustomerByName(txtCustomerSearch.getText());
                    txtCustomerDetailsDisplay.setText(customerDTO.getName() + "\n" + customerDTO.getAddress() + "\n" + customerDTO.getMobileNumber());
                    customerID = customerDTO.getCID();
                }

            } else if (cmbFilteringCtg.getSelectionModel().getSelectedItem().equals("Address")) {
//            if (txtCustomerSearch.getText() != null) {
//                CustomerDTO customerDTO = CustomerCenterController.getCustomerByName(txtCustomerSearch.getText());
//                txtCustomerDetailsDisplay.appendText(customerDTO.getName() + "\n" + customerDTO.getAddress() + "\n" + customerDTO.getMobileNumber());
//            }
            } else if (cmbFilteringCtg.getSelectionModel().getSelectedItem().equals("Mobile Number")) {
//            if (txtCustomerSearch.getText() != null) {
//                CustomerDTO customerDTO = CustomerCenterController.getCustomerByName(txtCustomerSearch.getText());
//                txtCustomerDetailsDisplay.appendText(customerDTO.getName() + "\n" + customerDTO.getAddress() + "\n" + customerDTO.getMobileNumber());
//            }
            }
        } else {
            txtCustomerDetailsDisplay.setText("Cash Customer");
        }

    }

    private void loadCBCustomerType() {
        cmbCustomerType.setPromptText("Select Type");

        List<String> allTypes = new ArrayList<String>(Arrays.asList("Normal Customer", "Cash Customer"));

        for (String type : allTypes) {
            cmbCustomerType.getItems().add(type);
        }
    }

    private void loadCBSearchCtg() {
        cmbFilteringCtg.setPromptText("Search By");

        List<String> allFilteringCtg = new ArrayList<String>(Arrays.asList("Customer Name", "Address", "Mobile Number"));

        for (String ctg : allFilteringCtg) {
            cmbFilteringCtg.getItems().add(ctg);
        }
    }

    @FXML
    private void loadMealPicker(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/lk/mc/cashier/view/MealPicker.fxml"));
        Scene Scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("MealPicker");
        stage.setScene(Scene);
        stage.show();
    }

    void loadTreeTable() {

        //colums
        JFXTreeTableColumn<OrderController.ColNames, String> colCode = new JFXTreeTableColumn<>("Code");
        colCode.setPrefWidth(100);

        colCode.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<OrderController.ColNames, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<OrderController.ColNames, String> param) {
                return param.getValue().getValue().getCode();
            }
        });

        JFXTreeTableColumn<OrderController.ColNames, String> colName = new JFXTreeTableColumn<>("Name");
        colName.setPrefWidth(200);

        colName.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<OrderController.ColNames, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<OrderController.ColNames, String> param) {
                return param.getValue().getValue().getName();
            }
        });

        JFXTreeTableColumn<OrderController.ColNames, String> colPrice = new JFXTreeTableColumn<>("Unit Price");
        colPrice.setPrefWidth(100);

        colPrice.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<OrderController.ColNames, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<OrderController.ColNames, String> param) {
                return param.getValue().getValue().getPrice();
            }
        });

//        JFXTreeTableColumn<OrderController.ColNames, String> colCtg = new JFXTreeTableColumn<>("Catogery");
//        colCtg.setPrefWidth(200);
//
//        colCtg.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<OrderController.ColNames, String>, ObservableValue<String>>() {
//            @Override
//            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<OrderController.ColNames, String> param) {
//                return param.getValue().getValue().category;
//            }
//        });
//
//        JFXTreeTableColumn<OrderController.ColNames, String> colDes = new JFXTreeTableColumn<>("Description");
//        colDes.setPrefWidth(245);
//
//        colDes.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<OrderController.ColNames, String>, ObservableValue<String>>() {
//            @Override
//            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<OrderController.ColNames, String> param) {
//                return param.getValue().getValue().description;
//            }
//        });
        JFXTreeTableColumn<OrderController.ColNames, String> colqty = new JFXTreeTableColumn<>("Quantity");
        colqty.setPrefWidth(100);

        colqty.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<OrderController.ColNames, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<OrderController.ColNames, String> param) {
                return param.getValue().getValue().getQtyAV();
            }
        });

        JFXTreeTableColumn<OrderController.ColNames, String> colDiscount = new JFXTreeTableColumn<>("Offer");
        colDiscount.setPrefWidth(100);

        colDiscount.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<OrderController.ColNames, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<OrderController.ColNames, String> param) {
                return param.getValue().getValue().getDiscount();
            }
        });

        JFXTreeTableColumn<OrderController.ColNames, String> colExDate = new JFXTreeTableColumn<>("Offer Expire Date");
        colExDate.setPrefWidth(100);

        colExDate.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<OrderController.ColNames, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<OrderController.ColNames, String> param) {
                return param.getValue().getValue().getExpireDate();
            }
        });

        //treeTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        //treeTable.getSelectionModel().setCellSelectionEnabled(true);
        //loadData
        List<ItemDTO> allMeals = MealPickerController.orderList;
        double unitTotal = 0;

        ItemDTO tempDto = new ItemDTO();
        List<String> name = new ArrayList<>();
        int specificCell = 0;
        for (ItemDTO i : allMeals) {

            for (int j = 0; j < treeTable.getCurrentItemsCount(); j++) {
                //treeTable.getRowFactory().call(treeTable)
                specificCell = j;

                name.add((String) treeTable.getColumns().get(1).getCellData(j));
//                if (!i.getName().equals(name)) {
//                    name = "";
//                }
                System.err.println("count " + treeTable.getCurrentItemsCount());
                System.err.println("@forl " + j + " ==" + name);
            }
            tempDto = i;
        }

        int updatedData = 0;

        System.err.println(name + " ;;" + tempDto.getName());

        if (name.contains(tempDto.getName())) {
            try {
                final String newName = tempDto.getName();

                String data = (String) treeTable.getColumns().get(3).getCellData(specificCell);
                updatedData = tempDto.getQtyAV() + Integer.parseInt(data);

                System.out.println(tempDto.getQtyAV() + " :) " + data);

                ItemDTO itemDTO = MealPickerController.getItemByName(tempDto.getName());
                if (itemDTO.getQtyAV() > updatedData) {
                    colums.set(specificCell, new OrderController.ColNames(
                            Integer.toString(tempDto.getCode()),
                            tempDto.getName(),
                            Double.toString(tempDto.getPrice()),
                            tempDto.getCategory(),
                            tempDto.getDescription(),
                            Integer.toString(updatedData),
                            tempDto.getDiscount(),
                            tempDto.getValidDate()));
                } else {
                    ApplicationControlsController.showSmartDialog(tempDto.getName() + " quanity is not enough(" + itemDTO.getQtyAV() + "). Please contact administrator..", stackPane, anchorPane);
                }

                //colums.replaceAll(operator);
            } catch (Exception ex) {
                Logger.getLogger(OrderController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            try {
                ItemDTO itemDTO = MealPickerController.getItemByName(tempDto.getName());
                if (itemDTO.getQtyAV() > Integer.parseInt(txtQuantity.getText())) {
                    colums.add(new OrderController.ColNames(Integer.toString(tempDto.getCode()),
                            tempDto.getName(),
                            Double.toString(tempDto.getPrice()),
                            tempDto.getCategory(),
                            tempDto.getDescription(),
                            Integer.toString(tempDto.getQtyAV()),
                            tempDto.getDiscount(),
                            tempDto.getValidDate()));
                } else {
                    ApplicationControlsController.showSmartDialog(tempDto.getName() + " quanity is not enough(" + itemDTO.getQtyAV() + "). Please contact administrator..", stackPane, anchorPane);
                }
            } catch (Exception ex) {
                Logger.getLogger(OrderController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        final TreeItem<OrderController.ColNames> root = new RecursiveTreeItem<OrderController.ColNames>(colums, RecursiveTreeObject::getChildren);

        treeTable.getColumns().setAll(colCode, colName, colPrice, colqty, colDiscount, colExDate);
        treeTable.setRoot(root);
        treeTable.setShowRoot(false);

    }

    @Override
    public void updateObserver() throws Exception {
        loadTreeTable();
        getTotal();
        cartUpdate();
    }

    public void getTotal() {
        total = 0;
        double unitTotal = 0;
        for (int j = 0; j < treeTable.getCurrentItemsCount(); j++) {
            String Quantity = (String) treeTable.getColumns().get(3).getCellData(j);
            String Price = (String) treeTable.getColumns().get(2).getCellData(j);
            //offer must be calculate
            unitTotal += Double.parseDouble(Quantity) * Double.parseDouble(Price);

        }

        total += unitTotal;
        //System.err.println(b.toString());
        lblTotal.setText("Rs" + Double.toString(total) + "0/=");
        final double finalTot = total;
        txtPaidAmount.addEventHandler(ActionEvent.ACTION, (event) -> {

            if (Double.parseDouble(txtPaidAmount.getText()) - finalTot > 0) {
                txtBalance.setText(Double.toString(Double.parseDouble(txtPaidAmount.getText()) - finalTot));
            } else {
                txtBalance.setText("");
            }

            txtBalance.setEditable(false);
        });

        final double lastTotal = total;
        txtPaidAmount.addEventHandler(ActionEvent.ACTION, (event) -> {
            if (lastTotal <= Double.parseDouble(txtPaidAmount.getText())) {
                btnGetPaid.setDisable(false);
            } else {
                btnGetPaid.setDisable(true);
            }
        });

    }

    @FXML
    private void addItemOrderList(ActionEvent event) throws Exception {
//        if(!(txtQuantity.getText().equals(null) && txtSearchByItemName.getText().equals(null))){
        //  btnAddToTable.setDisable(false);
        ItemDTO itemDTO = MealPickerController.getItemByName(txtSearchByItemName.getText());
        itemDTO.setQtyAV(Integer.parseInt(txtQuantity.getText()));
        if (itemDTO != null) {
            MealPickerController.addMealToOrderList(itemDTO);

        }
        if (txtOID.getText().equals(null) || txtOID.getText().equals("")) {
            txtOID.setText(setUniqueOID());
        } else {
            btnGetPaid.setDisable(true);
        }

    }

    @FXML
    private void getBalance(KeyEvent event) {
    }

    private void loadSearchByMealName() throws Exception {
        //btnAddToTable.setDisable(true);
        List<String> names = new ArrayList<>();

        List<ItemDTO> allDetails = MealPickerController.getAllMeals();
        for (ItemDTO i : allDetails) {
            names.add(i.getName());
        }
        TextFields.bindAutoCompletion(txtSearchByItemName, names);

    }

    private void loadTimeDate() {

        //System.out.println(dateFormat.format(date));
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            Calendar cal = Calendar.getInstance();
            second = cal.get(Calendar.SECOND);
            minute = cal.get(Calendar.MINUTE);
            hour = cal.get(Calendar.HOUR_OF_DAY);
            //System.out.println(hour + ":" + (minute) + ":" + second);
            lblTimeDate.setText("Date " + dateFormat.format(date) + "\nTime " + hour + ":" + (minute) + ":" + second);
        }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
        //lblTimeDate.setText(dateFormat.format(date));
    }

    private void loadCBCashier() {
        try {
            cmbCashierID.setPromptText("Select Cashier");

            List<CashierDTO> allcashiers = allCashiers();

            for (CashierDTO cashierDTO : allcashiers) {
                System.err.println(cashierDTO.getName());
                String id = cashierDTO.getCashierID();
                cmbCashierID.getItems().add(id);
            }

            cmbCashierID.valueProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    try {
                        txtCashier.setText(getCashierByID(newValue).getName());
                    } catch (Exception ex) {
                        Logger.getLogger(OrderController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

        } catch (Exception ex) {
            Logger.getLogger(OrderController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static List<CashierDTO> allCashiers() throws Exception {
        CashierService cashierService = (CashierService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.CASHIER);
        return cashierService.findAll();
    }

    public static CashierDTO getCashierByID(String id) throws Exception {
        CashierService cashierService = (CashierService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.CASHIER);
        return cashierService.findByID(id);
    }

    @FXML
    private void getPaid(MouseEvent event) {
        try {
            CustomerDTO customerDTO = CustomerCenterController.getCustomerByName(txtCustomerSearch.getText());
            CashierDTO cashierDTO = getCashierByID(cmbCashierID.getSelectionModel().getSelectedItem());

            OrdersDTO ordersDTO = new OrdersDTO();

            ordersDTO.setOID(Integer.parseInt(txtOID.getText()));
            ordersDTO.setDate(dateFormat.format(date));
            ordersDTO.setOrderStatus("Pending");
            ordersDTO.setOrderType("Dine-IN");
            ordersDTO.setCustomerDTO(customerDTO);
            ordersDTO.setCashierDTO(cashierDTO);
            ordersDTO.setTelephoneOperatorDTO(new TelephoneOperatorDTO(0, "null"));

            List<OrderDetailsDTO> allOrdersDetails = new ArrayList<>();
            List<ItemDTO> allOrderedMeals = new ArrayList<>();
            OrderDetailsDTO orderDetailsDTO = new OrderDetailsDTO();

            for (int i = 0; i < treeTable.getCurrentItemsCount(); i++) {
                ItemDTO idto = new ItemDTO();
                idto.setCode(Integer.parseInt(treeTable.getColumns().get(0).getCellData(i).toString()));
                idto.setName(treeTable.getColumns().get(1).getCellData(i).toString());
                idto.setPrice(Double.parseDouble(treeTable.getColumns().get(2).getCellData(i).toString()));
                idto.setQtyAV(Integer.parseInt(treeTable.getColumns().get(3).getCellData(i).toString()));
                idto.setDiscount(treeTable.getColumns().get(4).getCellData(i).toString());
                idto.setValidDate(treeTable.getColumns().get(5).getCellData(i).toString());
                allOrderedMeals.add(idto);
            }

            for (ItemDTO i : allOrderedMeals) {
                orderDetailsDTO.setItemDTO(i);
                orderDetailsDTO.setItemName(i.getName());
                orderDetailsDTO.setOrder_QTY(i.getQtyAV());
                orderDetailsDTO.setPrice(i.getPrice());
                allOrdersDetails.add(orderDetailsDTO);
                System.out.println(i.getName());
            }

            //for chef temp(send to chef)
            if (placeOrder(ordersDTO, allOrdersDetails, allOrderedMeals)) {
                ApplicationControlsController.showSmartDialog("Order has been send to BakeHouse!", stackPane, anchorPane);
                System.err.println("");

                //PrintRepo
                JasperReport compiledReport = (JasperReport) JRLoader.loadObject(OrderController.class.getResourceAsStream("/lk/mc/cashier/report/jasper/Invoice.jasper"));

                HashMap<String, Object> reportParams = new HashMap<>();
                reportParams.put("CustomerName", customerDTO.getName());
                reportParams.put("OID", txtOID.getText());
                reportParams.put("mobileNumber", customerDTO.getMobileNumber());
                reportParams.put("CashierID", getCashierByID(cmbCashierID.getSelectionModel().getSelectedItem()).getCashierID());
                for (int i = 0; i < treeTable.getCurrentItemsCount(); i++) {
                    reportParams.put("itemName", treeTable.getColumns().get(1).getCellData(i).toString());
                    reportParams.put("price", treeTable.getColumns().get(2).getCellData(i).toString());
                    reportParams.put("Qty", treeTable.getColumns().get(3).getCellData(i).toString());
                    
                }
                reportParams.put("Total", Double.toString(total));
                

                refreshFields();

                //reportParams.put();
//                reportParams.put("ContactNumber", customerDTO.getContactNo());
//                reportParams.put("ProcessingTime", processingTime);
//                reportParams.put("TPONo", tPOService.findByID(ordersDTO.getTpoID()).getTpoNO());
//                reportParams.put("ChefID", ordersDTO.getChefID());
//                reportParams.put("Quantity", ordersDTO.getQty());
                JasperPrint filledReport = JasperFillManager.fillReport(compiledReport, reportParams, new JREmptyDataSource());

                IJSEJasperViewer frmJasperViewer = new IJSEJasperViewer(filledReport);
                frmJasperViewer.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frmJasperViewer.setTitle("Invoice");
                frmJasperViewer.setModalExclusionType(Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
                frmJasperViewer.setVisible(true);

            } else {
                ApplicationControlsController.showSmartDialog("Error Occured.....", stackPane, anchorPane);
            }

        } catch (Exception ex) {
            Logger.getLogger(OrderController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void refreshFields() {
        txtCustomerSearch.setText("");
        txtSearchByItemName.setText("");
        txtQuantity.setText("");
        //colums.removeAll();
        MealPickerController.orderList.clear();
        txtPaidAmount.setText("");
        txtBalance.setText("");
        txtOID.setText("");

        lblCart.setText("0");
        txtCustomerDetailsDisplay.setText("");

        colums.remove(0, treeTable.getCurrentItemsCount());

    }

    public static boolean placeOrder(OrdersDTO ordersDTO, List<OrderDetailsDTO> orderDetailsDTOs, List<ItemDTO> itemDTOs) throws Exception {
        PlaceOrderService placeOrderService = (PlaceOrderService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.PLACEORDER);
        return placeOrderService.saveOrder(ordersDTO, orderDetailsDTOs, itemDTOs);
    }

    private void cartUpdate() {
        lblCart.setText(Integer.toString(treeTable.getCurrentItemsCount()));
    }

    @FXML
    private void removeItem(MouseEvent event) {
        System.err.println("removeItem");

        colums.remove(treeTable.getSelectionModel().getSelectedIndex());

    }

    @FXML
    private void updateItemQuantity(MouseEvent event) {
        try {
            String data = (String) treeTable.getColumns().get(3).getCellData(treeTable.getSelectionModel().getSelectedIndex());
            TreeItem<ColNames> cn = treeTable.getSelectionModel().getSelectedItem();

            ItemDTO itemDTO = MealPickerController.getItemByName(cn.getValue().name.getValueSafe());
            if (itemDTO.getQtyAV() > Integer.parseInt(txtQuantity.getText())) {
                colums.set(treeTable.getSelectionModel().getSelectedIndex(), new ColNames(cn.getValue().code.getValueSafe(),
                        cn.getValue().name.getValueSafe(),
                        cn.getValue().price.getValueSafe(),
                        cn.getValue().category.getValueSafe(),
                        cn.getValue().description.getValueSafe(),
                        txtQuantity.getText(),
                        cn.getValue().discount.getValueSafe(),
                        cn.getValue().expireDate.getValueSafe()));
            } else {
                ApplicationControlsController.showSmartDialog(cn.getValue().getName().getValueSafe() + " quantity is not enough(" + itemDTO.getQtyAV() + "). Please contact administrator..", stackPane, anchorPane);
            }
        } catch (Exception ex) {
            Logger.getLogger(OrderController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private String setUniqueOID() throws Exception {
        String id = "";
        int lastID = getLastOrderID();
        int newID = 0;
        if (lastID > 0) {
            newID = lastID + 1;
            id = cmbCashierID.getSelectionModel().getSelectedItem() + customerID + "" + newID;
        }
        return id;
    }

    public static int getLastOrderID() throws Exception {
        PlaceOrderService placeOrderService = (PlaceOrderService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.PLACEORDER);
        return placeOrderService.getLastOrderID();
    }

    private static class ColNames extends RecursiveTreeObject<ColNames> {

        private StringProperty code;
        private StringProperty name;
        private StringProperty price;
        private StringProperty category;
        private StringProperty description;
        private StringProperty qtyAV;
        private StringProperty discount;
        private StringProperty expireDate;

        public ColNames(String code, String name, String price, String category, String desc, String qty, String discount, String expireDate) {
            this.code = new SimpleStringProperty(code);
            this.name = new SimpleStringProperty(name);
            this.price = new SimpleStringProperty(price);
            this.category = new SimpleStringProperty(category);
            this.description = new SimpleStringProperty(desc);
            this.qtyAV = new SimpleStringProperty(qty);
            this.discount = new SimpleStringProperty(discount);
            this.expireDate = new SimpleStringProperty(expireDate);
        }

        /**
         * @return the code
         */
        public StringProperty getCode() {
            return code;
        }

        /**
         * @param code the code to set
         */
        public void setCode(StringProperty code) {
            this.code = code;
        }

        /**
         * @return the name
         */
        public StringProperty getName() {
            return name;
        }

        /**
         * @param name the name to set
         */
        public void setName(StringProperty name) {
            this.name = name;
        }

        /**
         * @return the price
         */
        public StringProperty getPrice() {
            return price;
        }

        /**
         * @param price the price to set
         */
        public void setPrice(StringProperty price) {
            this.price = price;
        }

        /**
         * @return the category
         */
        public StringProperty getCategory() {
            return category;
        }

        /**
         * @param category the category to set
         */
        public void setCategory(StringProperty category) {
            this.category = category;
        }

        /**
         * @return the description
         */
        public StringProperty getDescription() {
            return description;
        }

        /**
         * @param description the description to set
         */
        public void setDescription(StringProperty description) {
            this.description = description;
        }

        /**
         * @return the qtyAV
         */
        public StringProperty getQtyAV() {
            return qtyAV;
        }

        /**
         * @param qtyAV the qtyAV to set
         */
        public void setQtyAV(StringProperty qtyAV) {
            this.qtyAV = qtyAV;
        }

        /**
         * @return the discount
         */
        public StringProperty getDiscount() {
            return discount;
        }

        /**
         * @param discount the discount to set
         */
        public void setDiscount(StringProperty discount) {
            this.discount = discount;
        }

        /**
         * @return the expireDate
         */
        public StringProperty getExpireDate() {
            return expireDate;
        }

        /**
         * @param expireDate the expireDate to set
         */
        public void setExpireDate(StringProperty expireDate) {
            this.expireDate = expireDate;
        }

    }

}

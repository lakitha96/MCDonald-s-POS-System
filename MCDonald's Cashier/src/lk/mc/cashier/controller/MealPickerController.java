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
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import static lk.mc.cashier.controller.CustomerCenterController.getAllCustomers;
import lk.mc.cashier.proxy.ProxyHandler;
import lk.mc.common.dto.CustomerDTO;
import lk.mc.common.dto.ItemDTO;
import lk.mc.common.observer.Observer;
import lk.mc.common.observer.Subject;
import lk.mc.common.service.ServiceFactory;
import lk.mc.common.service.custom.ItemService;

/**
 * FXML Controller class
 *
 * @author lakitha
 */
public class MealPickerController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private JFXTreeTableView<ColNames> treeTable;

    @FXML
    private JFXButton btnAddOrderList;

    @FXML
    private JFXComboBox<String> cmbSort;

    @FXML
    private JFXTextField txtSearch;

    public static List<ItemDTO> orderList = new ArrayList<>();
    @FXML
    private JFXTextField txtquantity;
    @FXML
    private ImageView imageView;
    @FXML
    private JFXButton btnDone;

    private static List<Observer> allObservers = new ArrayList<>();
    @FXML
    private Label lblNotiyfy;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            loadTreetable();
        } catch (Exception ex) {
            Logger.getLogger(MealPickerController.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (ItemDTO i : orderList) {
            System.err.println(i.getName());
        }
        System.err.println(orderList.size());

        loadComboBox();
    }

    @FXML
    void addToOrderList(MouseEvent event) {

    }

    private void loadTreetable() throws Exception {
        //colums
        JFXTreeTableColumn<MealPickerController.ColNames, String> colCode = new JFXTreeTableColumn<>("Code");
        colCode.setPrefWidth(150);

        colCode.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<MealPickerController.ColNames, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<MealPickerController.ColNames, String> param) {
                return param.getValue().getValue().code;
            }
        });

        JFXTreeTableColumn<MealPickerController.ColNames, String> colName = new JFXTreeTableColumn<>("Name");
        colName.setPrefWidth(245);

        colName.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<MealPickerController.ColNames, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<MealPickerController.ColNames, String> param) {
                return param.getValue().getValue().name;
            }
        });

        JFXTreeTableColumn<MealPickerController.ColNames, String> colPrice = new JFXTreeTableColumn<>("Price");
        colPrice.setPrefWidth(245);

        colPrice.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<MealPickerController.ColNames, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<MealPickerController.ColNames, String> param) {
                return param.getValue().getValue().price;
            }
        });

        JFXTreeTableColumn<MealPickerController.ColNames, String> colCtg = new JFXTreeTableColumn<>("Catogery");
        colCtg.setPrefWidth(200);

        colCtg.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<MealPickerController.ColNames, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<MealPickerController.ColNames, String> param) {
                return param.getValue().getValue().category;
            }
        });

        JFXTreeTableColumn<MealPickerController.ColNames, String> colDes = new JFXTreeTableColumn<>("Description");
        colDes.setPrefWidth(245);

        colDes.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<MealPickerController.ColNames, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<MealPickerController.ColNames, String> param) {
                return param.getValue().getValue().description;
            }
        });

        JFXTreeTableColumn<MealPickerController.ColNames, String> colqty = new JFXTreeTableColumn<>("Estimate Quantity");
        colqty.setPrefWidth(245);

        colqty.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<MealPickerController.ColNames, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<MealPickerController.ColNames, String> param) {
                return param.getValue().getValue().qtyAV;
            }
        });

        JFXTreeTableColumn<MealPickerController.ColNames, String> colDiscount = new JFXTreeTableColumn<>("Offer");
        colDiscount.setPrefWidth(245);

        colDiscount.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<MealPickerController.ColNames, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<MealPickerController.ColNames, String> param) {
                return param.getValue().getValue().discount;
            }
        });

        JFXTreeTableColumn<MealPickerController.ColNames, String> colExDate = new JFXTreeTableColumn<>("Offer Expire Date");
        colExDate.setPrefWidth(245);

        colExDate.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<MealPickerController.ColNames, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<MealPickerController.ColNames, String> param) {
                return param.getValue().getValue().expireDate;
            }
        });

        //treeTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        //treeTable.getSelectionModel().setCellSelectionEnabled(true);
        //loadData
        ObservableList<MealPickerController.ColNames> colums = FXCollections.observableArrayList();

        List<ItemDTO> allMeals = getAllMeals();
        for (ItemDTO i : allMeals) {
            colums.add(new MealPickerController.ColNames(Integer.toString(i.getCode()),
                    i.getName(),
                    Double.toString(i.getPrice()),
                    i.getCategory(),
                    i.getDescription(),
                    Integer.toString(i.getQtyAV()),
                    i.getDiscount(),
                    i.getValidDate()));
        }

        final TreeItem<MealPickerController.ColNames> root = new RecursiveTreeItem<MealPickerController.ColNames>(colums, RecursiveTreeObject::getChildren);

        treeTable.getColumns().setAll(colCode, colName, colPrice, colCtg, colDes, colqty, colDiscount, colExDate);
        treeTable.setRoot(root);
        treeTable.setShowRoot(false);

        //addToList
        treeTable.addEventHandler(MouseEvent.MOUSE_CLICKED, (selectedEvent) -> {
            try {
                //set Image
                if (!treeTable.getSelectionModel().isEmpty()) {
                    ItemDTO itemDTO = getItemByName(treeTable.getSelectionModel().getSelectedItem().getValue().name.getValueSafe());
                    setImage(itemDTO.getImagePath());
                } else {

                }

                btnAddOrderList.addEventHandler(MouseEvent.MOUSE_CLICKED, (event) -> {
                    if (!treeTable.getSelectionModel().isEmpty()) {
                        StringProperty code = treeTable.getSelectionModel().getSelectedItem().getValue().code;
                        StringProperty name = treeTable.getSelectionModel().getSelectedItem().getValue().name;
                        StringProperty price = treeTable.getSelectionModel().getSelectedItem().getValue().price;
                        StringProperty cat = treeTable.getSelectionModel().getSelectedItem().getValue().category;
                        StringProperty des = treeTable.getSelectionModel().getSelectedItem().getValue().description;
                        //
                        StringProperty discount = treeTable.getSelectionModel().getSelectedItem().getValue().discount;
                        StringProperty expireDate = treeTable.getSelectionModel().getSelectedItem().getValue().expireDate;

                        if (!(txtquantity.getText().equals("") || txtquantity.getText().equals(null))) {
                            ItemDTO item = new ItemDTO();
                            item.setCode(Integer.parseInt(code.getValueSafe()));
                            item.setName(name.getValueSafe());
                            item.setPrice(Double.parseDouble(price.getValueSafe()));
                            item.setCategory(cat.getValueSafe());

                            if (Integer.parseInt(treeTable.getSelectionModel().getSelectedItem().getValue().qtyAV.getValueSafe()) > 1) {
                                item.setQtyAV(Integer.parseInt(txtquantity.getText()));

                            } else {
                                //ApplicationControlsController.showSmartDialog(message, stackPane, treeTable);
                                System.err.println("Estimate quantity is low");
                            }

                            //
                            item.setImagePath("no need");
                            item.setDiscount(discount.getValueSafe());
                            item.setValidDate(expireDate.getValueSafe());

//                            if (orderList.contains(item.getName())) {
//                                System.err.println("Yes Availble");
//                            }
                            //To REMOVE DUPLICATES
                            ListIterator<ItemDTO> it = orderList.listIterator();
                            while (it.hasNext()) {
                                int i = it.next().getCode();
                                if (i == item.getCode()) {
//                                    //it.remove();
//                                    int prevIndex=it.previousIndex();
//                                    ItemDTO duplicateDTO=orderList.get(prevIndex);
//                                    
//                                    
//                                    
//                                    item.setQtyAV(duplicateDTO.getQtyAV()+item.getQtyAV());
//                                    //it.remove();
//                                    orderList.add(item);
//                                    //updatedDTO.setCode(duplicateDTO.getCode());
//                                    
                                    System.err.println("Yes Availble");
                                    it.remove();
                                    break;
                                }
                                it.remove();
                            }

                            if (orderList.add(item)) {
                                System.out.println("Added");
                                try {
                                    notifyAllObservers();
                                } catch (Exception ex) {
                                    Logger.getLogger(MealPickerController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                treeTable.getSelectionModel().clearSelection();

                            } else {

                            }

                        } else {
                            System.err.println("Please select add Quantity...");
                        }

                    } else if (treeTable.getSelectionModel().isEmpty()) {
                        //System.err.println("Please select item");
                    }

                });

            } catch (Exception ex) {
                Logger.getLogger(MealPickerController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        //combo Actions
        //cmbSearchCat.valueProperty().addListener(new ChangeListener<String>() {
//            @Override
//            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
//                if (newValue.equals("Customer Name")) {
//                    txtSearch.textProperty().addListener(new ChangeListener<String>() {
//                        @Override
//                        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
//                            treeTable.setPredicate(new Predicate<TreeItem<CustomerCenterController.ColNames>>() {
//                                @Override
//                                public boolean test(TreeItem<CustomerCenterController.ColNames> c) {
//                                    Boolean check = c.getValue().customerName.getValue().contains(newValue);
//                                    return check;
//                                }
//                            });
//                        }
//                    });
//                } else if (newValue.equals("Address")) {
//                    
//                }
        cmbSort.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.equals("Code")) {
                    txtSearch.textProperty().addListener(new ChangeListener<String>() {
                        @Override
                        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                            treeTable.setPredicate(new Predicate<TreeItem<ColNames>>() {
                                @Override
                                public boolean test(TreeItem<ColNames> t) {
                                    Boolean check = t.getValue().code.getValue().contains(newValue);
                                    return check;
                                }
                            });
                        }
                    });

                } else if (newValue.equals("name")) {
                    txtSearch.textProperty().addListener(new ChangeListener<String>() {
                        @Override
                        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                            treeTable.setPredicate(new Predicate<TreeItem<ColNames>>() {
                                @Override
                                public boolean test(TreeItem<ColNames> t) {
                                    Boolean check = t.getValue().name.getValue().contains(newValue);
                                    return check;
                                }
                            });
                        }
                    });
                } else if (newValue.equals("price")) {
                    txtSearch.textProperty().addListener(new ChangeListener<String>() {
                        @Override
                        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                            treeTable.setPredicate(new Predicate<TreeItem<ColNames>>() {
                                @Override
                                public boolean test(TreeItem<ColNames> t) {
                                    Boolean check = t.getValue().price.getValue().contains(newValue);
                                    return check;
                                }
                            });
                        }
                    });
                } else if (newValue.equals("category")) {
                    txtSearch.textProperty().addListener(new ChangeListener<String>() {
                        @Override
                        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                            treeTable.setPredicate(new Predicate<TreeItem<ColNames>>() {
                                @Override
                                public boolean test(TreeItem<ColNames> t) {
                                    Boolean check = t.getValue().category.getValue().contains(newValue);
                                    return check;
                                }
                            });
                        }
                    });
                } else if (newValue.equals("description")) {
                    txtSearch.textProperty().addListener(new ChangeListener<String>() {
                        @Override
                        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                            treeTable.setPredicate(new Predicate<TreeItem<ColNames>>() {
                                @Override
                                public boolean test(TreeItem<ColNames> t) {
                                    Boolean check = t.getValue().description.getValue().contains(newValue);
                                    return check;
                                }
                            });
                        }
                    });
                } else if (newValue.equals("Estimate quantity")) {
                    txtSearch.textProperty().addListener(new ChangeListener<String>() {
                        @Override
                        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                            treeTable.setPredicate(new Predicate<TreeItem<ColNames>>() {
                                @Override
                                public boolean test(TreeItem<ColNames> t) {
                                    Boolean check = t.getValue().qtyAV.getValue().contains(newValue);
                                    return check;
                                }
                            });
                        }
                    });
                } else if (newValue.equals("Offer")) {
                    txtSearch.textProperty().addListener(new ChangeListener<String>() {
                        @Override
                        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                            treeTable.setPredicate(new Predicate<TreeItem<ColNames>>() {
                                @Override
                                public boolean test(TreeItem<ColNames> t) {
                                    Boolean check = t.getValue().discount.getValue().contains(newValue);
                                    return check;
                                }
                            });
                        }
                    });
                } else if (newValue.equals("expireDate")) {
                    txtSearch.textProperty().addListener(new ChangeListener<String>() {
                        @Override
                        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                            treeTable.setPredicate(new Predicate<TreeItem<ColNames>>() {
                                @Override
                                public boolean test(TreeItem<ColNames> t) {
                                    Boolean check = t.getValue().expireDate.getValue().contains(newValue);
                                    return check;
                                }
                            });
                        }
                    });
                }
            }
        });
    }

    private void loadComboBox() {
        List<String> filterType = new ArrayList<String>(Arrays.asList("Code",
                "name",
                "category",
                "price",
                "description",
                "Estimate quantity",
                "Offer",
                "Offer ExpiryDate"));
        for (String ctg : filterType) {
            cmbSort.getItems().add(ctg);
        }

    }

    @FXML
    private void hidePane(MouseEvent event) {

    }

    public static void registerObserver(Observer observer) throws Exception {
        allObservers.add(observer);
    }

    public static void unRegisterObserver(Observer observer) throws Exception {
        allObservers.remove(observer);
    }

    public static void unRegisterAllObserver() throws Exception {
// 
    }

    public static void notifyAllObservers() throws Exception {
        for (Observer ob : allObservers) {
            ob.updateObserver();
        }
    }

    private static class ColNames extends RecursiveTreeObject<ColNames> {

        StringProperty code;
        StringProperty name;
        StringProperty price;
        StringProperty category;
        StringProperty description;
        StringProperty qtyAV;
        StringProperty discount;
        StringProperty expireDate;

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

    }

    private void setImage(String path) {
        Image image = new Image("file:" + path, 619, 121, false, false);
        imageView.setImage(image);
    }

    public static List<ItemDTO> getAllMeals() throws Exception {
        ItemService itemService = (ItemService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.ITEM);
        return itemService.findAll();
    }

    public static ItemDTO getItemByName(String name) throws Exception {
        ItemService itemService = (ItemService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.ITEM);
        return itemService.findByName(name);
    }

    public static boolean addMealToOrderList(ItemDTO item) throws Exception {

        ListIterator<ItemDTO> it = orderList.listIterator();
        while (it.hasNext()) {
            int i = it.next().getCode();
            if (i == item.getCode()) {
//                                    //it.remove();
//                                    int prevIndex=it.previousIndex();
//                                    ItemDTO duplicateDTO=orderList.get(prevIndex);
//                                    
//                                    
//                                    
//                                    item.setQtyAV(duplicateDTO.getQtyAV()+item.getQtyAV());
//                                    //it.remove();
//                                    orderList.add(item);
//                                    //updatedDTO.setCode(duplicateDTO.getCode());
//                                    
                System.err.println("Yes Availble");
                //it.remove();
                break;
            }
            it.remove();
        }
        boolean b=orderList.add(item);
        
        notifyAllObservers();
        
        System.err.println("@today---"+orderList.size());

        return b;
    }

}

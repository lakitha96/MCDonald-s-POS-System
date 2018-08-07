/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.mc.cashier.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import lk.mc.cashier.proxy.ProxyHandler;
import lk.mc.common.dto.ItemDTO;
import lk.mc.common.service.ServiceFactory;
import lk.mc.common.service.custom.ItemService;

/**
 * FXML Controller class
 *
 * @author lakitha
 */
public class ReportController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private PieChart pieChart;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadMealPieChart();
    }

    void loadMealPieChart() {

        try {
            List<ItemDTO> allItems = getAllItems();
            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
            for (ItemDTO itemDTO : allItems) {
                pieChartData.add(new PieChart.Data(itemDTO.getName() + " (" + itemDTO.getQtyAV() + ")", itemDTO.getQtyAV()));
            }
            pieChart.setData(pieChartData);
        } catch (Exception ex) {
            Logger.getLogger(ReportController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static List getAllItems() throws Exception {
        ItemService itemService = (ItemService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.ITEM);
        return itemService.findAll();

    }

}

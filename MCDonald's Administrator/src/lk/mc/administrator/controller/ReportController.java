/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.mc.administrator.controller;

import com.jfoenix.controls.JFXDatePicker;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import lk.mc.administrator.proxy.ProxyHandler;
import lk.mc.common.dto.BakeHouseDTO;
import lk.mc.common.dto.ItemDTO;
import lk.mc.common.service.ServiceFactory;
import lk.mc.common.service.custom.BakeHouseService;
import lk.mc.common.service.custom.ItemService;

/**
 * FXML Controller class
 *
 * @author lakitha
 */
public class ReportController implements Initializable {

    @FXML
    private PieChart mealChart;
    @FXML
    private LineChart<?, ?> lineChart;
    @FXML
    private NumberAxis y;
    @FXML
    private CategoryAxis x;

    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
    Date date = new Date();
    @FXML
    private JFXDatePicker datePicker;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            loadMealPieChart();
            loadLineChart();
        } catch (Exception ex) {
            Logger.getLogger(ReportController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    void loadMealPieChart() {
        try {
            //colums.add(new ColNames(customerDTO.getName(), customerDTO.getAddress(), customerDTO.getMobileNumber()));
            List<ItemDTO> allItems = getAllItems();
            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
            for (ItemDTO itemDTO : allItems) {
                pieChartData.add(new PieChart.Data(itemDTO.getName() + " (" + itemDTO.getQtyAV() + ")", itemDTO.getQtyAV()));
            }
            mealChart.setData(pieChartData);
        } catch (Exception ex) {
            Logger.getLogger(ReportController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static List<ItemDTO> getAllItems() throws Exception {
        ItemService itemService = (ItemService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.ITEM);
        return itemService.findAll();
    }

    public static List<BakeHouseDTO> getAllBakeDetails() throws Exception {
        BakeHouseService bakeHouseService = (BakeHouseService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.BAKEHOUSE);
        return bakeHouseService.findAll();
    }

    public static List<BakeHouseDTO> getAllBakeDetailsByDate(String date) throws Exception {
        BakeHouseService bakeHouseService = (BakeHouseService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.BAKEHOUSE);
        return bakeHouseService.findByDate(date);
    }

    private void loadLineChart() throws Exception {
        List<BakeHouseDTO> allBakeDetails = getAllBakeDetails();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        LocalDate b5 = LocalDate.now().minusDays(5);
        //allBakeDetails = getAllBakeDetailsByDate(b5.format(formatter));
        
        LocalDate b4 = LocalDate.now().minusDays(4);
        //allBakeDetails = getAllBakeDetailsByDate(b4.format(formatter));
        
        LocalDate b3 = LocalDate.now().minusDays(3);
        //allBakeDetails = getAllBakeDetailsByDate(b3.format(formatter));
        
        LocalDate b2 = LocalDate.now().minusDays(2);
        //allBakeDetails = getAllBakeDetailsByDate(b2.format(formatter));
        
        LocalDate b1 = LocalDate.now().minusDays(1);
        //allBakeDetails = getAllBakeDetailsByDate(b1.format(formatter));
        
        LocalDate t = LocalDate.now();
        //allBakeDetails = getAllBakeDetailsByDate(t.format(formatter));
        System.err.println("@W@@@"+b5.format(formatter));
        
        XYChart.Series incomeSeries = new XYChart.Series();
        double total = 0;
        double today = 0.0;
        double day5=0;
        double day4=0;
        double day3=0;
        double day2=0;
        double day1=0;
        for (BakeHouseDTO bake : allBakeDetails) {
            
            if (bake.getDate().equals(b5.format(formatter))) {
                day5 += bake.getPreparedQty() * bake.getItemDTO().getPrice();
                total += day5;
                incomeSeries.getData().add(new XYChart.Data(date, day5));
                //total=0; curentIncome=0;
                
                
            }else if(bake.getDate().equals(b4.format(formatter))){
                day4 += bake.getPreparedQty() * bake.getItemDTO().getPrice();
                total += day4;
                incomeSeries.getData().add(new XYChart.Data(bake.getDate(), day4));
                //total=0; curentIncome=0;
            }else if(bake.getDate().equals(b3.format(formatter))){
                day3 += bake.getPreparedQty() * bake.getItemDTO().getPrice();
                total += day3;
                incomeSeries.getData().add(new XYChart.Data(bake.getDate(), day3));
                //total=0; curentIncome=0;
            }else if(bake.getDate().equals(b2.format(formatter))){
                day2 += bake.getPreparedQty() * bake.getItemDTO().getPrice();
                total += day2;
                incomeSeries.getData().add(new XYChart.Data(bake.getDate(), day2));
                //total=0; curentIncome=0;
            }else if(bake.getDate().equals(b1.format(formatter))){
                day1 += bake.getPreparedQty() * bake.getItemDTO().getPrice();
                total += day1;
                incomeSeries.getData().add(new XYChart.Data(bake.getDate(), day1));
                //total=0; curentIncome=0;
            }else if(bake.getDate().equals(t.format(formatter))){
                today += bake.getPreparedQty() * bake.getItemDTO().getPrice();
                total += today;
                incomeSeries.getData().add(new XYChart.Data(bake.getDate(), today));
                //total=0; curentIncome=0;
            }
        }

//        System.err.println(allBakeDetails.size());
//        XYChart.Series incomeSeries = new XYChart.Series();
//        double total = 0;
//        double curentIncome = 0.0;
//        String date = "";
//        for (BakeHouseDTO bakeDTO : allBakeDetails) {
//
//            curentIncome += bakeDTO.getPreparedQty() * bakeDTO.getItemDTO().getPrice();
//            total += curentIncome;
//            System.err.println(bakeDTO.getPreparedQty() * bakeDTO.getItemDTO().getPrice() + "#######");
//            date = bakeDTO.getDate();
//            curentIncome = 0;
//
//        }
//        incomeSeries.getData().add(new XYChart.Data(date, total));
//
//        lineChart.getData().clear();
        lineChart.getData().add(incomeSeries);
//        datePicker.addEventHandler(ActionEvent.ANY, (event) -> {
//            try {
//                List<BakeHouseDTO> allBakeDetailsByDate = getAllBakeDetailsByDate(datePicker.getValue().toString().replace('-', '/'));
//
//                XYChart.Series incomeSeriesByDate = new XYChart.Series();
//                double total1 = 0;
//                double curentIncome1 = 0.0;
//                String date1 = "";
//                for (BakeHouseDTO bakeDTO : allBakeDetails) {
//
//                    curentIncome1 += bakeDTO.getPreparedQty() * bakeDTO.getItemDTO().getPrice();
//                    total1 += curentIncome1;
//                    System.err.println(bakeDTO.getPreparedQty() * bakeDTO.getItemDTO().getPrice() + "#######");
//                    date1 = bakeDTO.getDate();
//                    curentIncome1 = 0;
//
//                }
//
//                lineChart.getData().add(incomeSeriesByDate);
//            } catch (Exception ex) {
//                Logger.getLogger(ReportController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        });

    }
}

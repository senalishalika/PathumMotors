/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tml.pathummoto;

import com.tml.pathummoto.Dao.BillDao;
import com.tml.pathummoto.Dao.CustomDao;
import com.tml.pathummoto.model.Bill;
import com.tml.pathummoto.model.Customer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Tishan Madhawa
 */
public class CustomerController {

    @FXML
    AnchorPane AnchorPane;
    @FXML
    TextField customer;
    @FXML
    TableView table;
    @FXML
    TableView customerTable;
     
    static ObservableList<Bill> data
                = FXCollections.observableArrayList();
    @FXML
    public void search(ActionEvent event) {
//        Stage stage = (Stage) anchorPane.getScene().getWindow();
        String customerNumber=customer.getText();
        CustomDao customerDao=new CustomDao();
        Customer customer = customerDao.searchCustomer(customerNumber);
        BillDao billDao=new BillDao();
        ArrayList<Bill> bills=billDao.searchBill(customerNumber);
        for(int i=0;i<bills.size();i++){
            data.add(bills.get(i));
            
        }
        

        TableColumn partId = new TableColumn("Date");
        partId.setMinWidth(100);
        partId.setCellValueFactory(
                new PropertyValueFactory<Bill, String>("date")
        );

        TableColumn partName = new TableColumn("Km");
        partName.setMinWidth(100);
        partName.setCellValueFactory(
                new PropertyValueFactory<Bill, Integer>("km")
        );
        TableColumn parts = new TableColumn("Parts and Quentity");
        parts.setMinWidth(100);
        parts.setCellValueFactory(
                new PropertyValueFactory<Bill, String>("parts")
        );
        TableColumn quentitypart = new TableColumn("Service Cost");
        quentitypart.setMinWidth(100);
        quentitypart.setCellValueFactory(
                new PropertyValueFactory<Bill, Integer>("service")
        );
        TableColumn Price = new TableColumn("TotalCost");
        Price.setMinWidth(100);
        Price.setCellValueFactory(
                new PropertyValueFactory<Bill, Integer>("totalCost")
        );
        TableColumn payment = new TableColumn("Payment");
        payment.setMinWidth(100);
        payment.setCellValueFactory(
                new PropertyValueFactory<Bill, Integer>("payment")
        );

        table.getColumns().addAll(partId, partName,parts, quentitypart, Price, payment);
        table.setItems(data);
//        AnchorPane anchor = new AnchorPane();
//        anchor.getChildren().add(table);
//        Scene scene = new Scene(anchor);
//        stage.setScene(scene);
//        stage.show();
    }
    @FXML
    void searchCustomer(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/searchCustomer.fxml"));
        AnchorPane anchorPane = (AnchorPane) root;
        Scene scene = new Scene(anchorPane);
        
        stage.setMaximized(true);

        stage.setTitle("Pathum Motors");
        stage.setScene(scene);
        stage.show();
    }
}

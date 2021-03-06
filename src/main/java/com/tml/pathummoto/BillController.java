/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tml.pathummoto;

import com.mongodb.BasicDBObject;
import com.tml.pathummoto.Dao.BillDao;
import com.tml.pathummoto.Dao.CustomDao;
import com.tml.pathummoto.Dao.ModelDao;
import com.tml.pathummoto.Dao.PartDao;
import com.tml.pathummoto.model.Bill;
import com.tml.pathummoto.model.Customer;
import com.tml.pathummoto.model.MainPart;
import com.tml.pathummoto.model.Model;
import com.tml.pathummoto.model.Part;
import com.tml.pathummoto.model.User;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Callback;
import javax.swing.JFileChooser;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;

/**
 *
 * @author Tishan Madhawa
 */
public class BillController {

    @FXML
    TextField type;
    @FXML
    TextField modell;
    @FXML
    TextField mainPartt;
    @FXML
    AnchorPane AnchorPane;

    static ComboBox comboBox1 = new ComboBox();

    static ComboBox comboBox2 = new ComboBox();

    static ImageView image = new ImageView();
    static TableView<Part> partTable = new TableView<Part>();
    static TableView<Part> billPartTable = new TableView<Part>();

    @FXML
    public void home(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/home.fxml"));
        final PartDao partdao = new PartDao();
        ArrayList arr1 = new ArrayList();
        arr1 = partdao.getModelData();
        ObservableList<String> observableListOfObjects = FXCollections.observableList(arr1);
        comboBox1.setItems(observableListOfObjects);
        comboBox1.setLayoutX(170.0);
        comboBox1.setLayoutY(60.0);
        comboBox1.setMinSize(150, 25);
        comboBox1.setId("modelName");
        comboBox2.setLayoutX(170.0);
        comboBox2.setLayoutY(90.0);
        comboBox2.setMinSize(150, 25);
        comboBox2.setId("MainPartName");
        comboBox1.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String t, String t1) {
                comboBox1.getEditor().setText(t1);
                try {
                    searchMainPart();
                } catch (IOException ex) {
                    Logger.getLogger(BillController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        });

        comboBox2.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String t, String t1) {
                comboBox2.getEditor().setText(t1);
                try {
                    searchPart();
                } catch (IOException ex) {
                    Logger.getLogger(BillController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (BiffException ex) {
                    Logger.getLogger(BillController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (WriteException ex) {
                    Logger.getLogger(BillController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        });

        billPartTable.setLayoutX(575);
        billPartTable.setLayoutY(215);
        billPartTable.setPrefWidth(769);
        billPartTable.setPrefHeight(330);

        partTable.setLayoutX(0);
        partTable.setLayoutY(450);
        partTable.setPrefWidth(500);
        partTable.setPrefHeight(250);

        AnchorPane anchorPane = (AnchorPane) root;
        anchorPane.getChildren().addAll(comboBox1, comboBox2, image, partTable, billPartTable);

        Scene scene = new Scene(anchorPane);

        stage.setMaximized(true);

        stage.setTitle("Pathum Motors");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void home1() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/home.fxml"));
        final PartDao partdao = new PartDao();
        ArrayList arr1 = new ArrayList();
        arr1 = partdao.getModelData();
        ObservableList<String> observableListOfObjects = FXCollections.observableList(arr1);
        comboBox1.setItems(observableListOfObjects);
        comboBox1.setLayoutX(170.0);
        comboBox1.setLayoutY(60.0);
        comboBox1.setMinSize(150, 25);
        comboBox1.setId("modelName");
        comboBox2.setLayoutX(170.0);
        comboBox2.setLayoutY(90.0);
        comboBox2.setMinSize(150, 25);
        comboBox2.setId("MainPartName");
        comboBox1.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String t, String t1) {
                comboBox1.getEditor().setText(t1);

            }

        });

        comboBox2.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String t, String t1) {
                comboBox2.getEditor().setText(t1);

            }

        });

        billPartTable.setLayoutX(575);
        billPartTable.setLayoutY(215);
        billPartTable.setPrefWidth(769);
        billPartTable.setPrefHeight(330);

        partTable.setLayoutX(0);
        partTable.setLayoutY(450);
        partTable.setPrefWidth(500);
        partTable.setPrefHeight(250);

        AnchorPane anchorPane = (AnchorPane) root;
        anchorPane.getChildren().addAll(comboBox1, comboBox2, image, partTable, billPartTable);

        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) AnchorPane.getScene().getWindow();
        stage.setMaximized(true);

        stage.setTitle("Pathum Motors");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void searchMainPart() throws IOException {
        String model = comboBox1.getEditor().getText();

        System.out.println("selected model is " + model);
        final PartDao partdao = new PartDao();

        ArrayList arr = new ArrayList();
        arr = partdao.getMainPartData(model);
        ObservableList<String> mainPartList = FXCollections.observableList(arr);
        comboBox2.setItems(mainPartList);
        comboBox2.setLayoutX(170.0);
        comboBox2.setLayoutY(90.0);
        comboBox2.setMinSize(150, 25);
        comboBox2.setId("MainPartName");
        comboBox2.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String t, String t1) {
                comboBox2.getEditor().setText(t1);

            }

        });

    }

    @FXML
    public void searchPart() throws IOException, BiffException, WriteException {
        partTable.getColumns().clear();
        String model = comboBox1.getEditor().getText();
        String mainPart = comboBox2.getEditor().getText();
        final PartDao partdao = new PartDao();
        Image img = new Image("file:" + partdao.getImage(model, mainPart));
        //partTable.getColumns().clear();
        image.setImage(img);
        image.setLayoutX(0);
        image.setLayoutY(130.0);
        image.setFitHeight(300);
        image.setFitWidth(500);

        System.out.println("file:" + partdao.getImage(model, mainPart));
        ArrayList<Part> arr = new ArrayList<Part>();
        arr = partdao.searchParts(model, mainPart);

        final ObservableList<Part> data
                = FXCollections.observableArrayList();
        for (int i = 0; i < arr.size(); i++) {

            Part part = arr.get(i);
            Excel excel = new Excel();
            int price = excel.load(part.getPartNo());
            part.setPrice("" + price);

            data.add(part);

        }

        TableColumn Number = new TableColumn("Image Number");
        Number.setMinWidth(100);
        Number.setCellValueFactory(new PropertyValueFactory<Part, String>("ImageNo"));

        TableColumn partId = new TableColumn("Part No");
        partId.setMinWidth(120);
        partId.setCellValueFactory(
                new PropertyValueFactory<Part, String>("PartNo")
        );

        TableColumn partName = new TableColumn("Part Name");
        partName.setMinWidth(170);
        partName.setCellValueFactory(
                new PropertyValueFactory<Part, String>("PartName")
        );
        TableColumn Price = new TableColumn("Price");
        Price.setMinWidth(110);
        Price.setCellValueFactory(
                new PropertyValueFactory<Part, String>("price")
        );

        partTable.getColumns().addAll(Number, partId, partName, Price);
        partTable.setItems(data);

    }

    @FXML
    public void addcustomer() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/addCustomer.fxml"));
        AnchorPane anchorPane = (AnchorPane) root;
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) AnchorPane.getScene().getWindow();

        stage.setMaximized(true);

        stage.setTitle("Pathum Motors");
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    public void searchcustomer(ActionEvent event) throws IOException {
        CustomerController customer = new CustomerController();
        Stage stage = (Stage) AnchorPane.getScene().getWindow();
        customer.searchCustomer(stage);

    }

    @FXML
    public void addModel1() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/model.fxml"));
        AnchorPane anchorPane = (AnchorPane) root;
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) AnchorPane.getScene().getWindow();
        stage.setMaximized(true);

        stage.setTitle("Pathum Motors");
        stage.setScene(scene);
        stage.show();

    }
    @FXML
    final Label lable = new Label();

    @FXML
    Button button;
    @FXML
    private TextField part_type;

    @FXML
    private TextField path;

    static ComboBox<String> comboBox;

    @FXML
    public void addMainPart() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/MainPart.fxml"));
        PartDao partdao = new PartDao();
        ArrayList arr1 = new ArrayList();
        arr1 = partdao.getModelData();
        ObservableList<String> observableListOfObjects = FXCollections.observableList(arr1);
        comboBox = new ComboBox<String>(observableListOfObjects);
        comboBox.setLayoutX(250.0);
        comboBox.setLayoutY(20.0);

        comboBox.setId("modelName");

        comboBox.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String t, String t1) {

                comboBox.getEditor().setText(t1);

            }

        });
//        System.out.println("see" + com);
//
//        Button button1 = new Button();
//        button1.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                System.out.println("tisan" + com);
//
//            }
//
//        });

        AnchorPane anchorPane = (AnchorPane) root;
        anchorPane.getChildren().addAll(lable, comboBox);

        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) AnchorPane.getScene().getWindow();
        stage.setMaximized(true);

        stage.setTitle("Pathum Motors");
        stage.setScene(scene);
        stage.show();

    }
//add main parts
    @FXML
    private Label labelmodel2;
    @FXML
    private Label labelparttype2;
    @FXML
    private Label labelimage2;

    @FXML
    public void addMainParts(ActionEvent event) {
        labelmodel2.setText("");
        labelparttype2.setText("");
        labelimage2.setText("");

        String PartType = part_type.getText();
        String model1 = comboBox.getEditor().getText();
        String path1 = path.getText();
        if (PartType.length() == 0) {
            labelparttype2.setText("Fill this Field");
        } else if (model1.length() == 0) {
            labelmodel2.setText("Fill this Field");
        } else if (path1.length() == 0) {
            labelimage2.setText("Fill this Field");

        } else if (!PartType.matches("^[ a-zA-Z]*$")) {
            labelparttype2.setText("Part type is invalid");
        } else {
            MainPart mainpart = new MainPart();
            mainpart.setImageName(path1);
            mainpart.setModelName(model1);
            mainpart.setPartType(PartType);

            PartDao partdao = new PartDao();
            partdao.addMainPart(mainpart);
            System.out.println("senali=" + model1);
        }
    }

    public void browsePart(ActionEvent event) {
        final FileChooser fileChooser = new FileChooser();
        Stage stage = (Stage) AnchorPane.getScene().getWindow();
        File file = fileChooser.showOpenDialog(stage);
        if (file.getPath() != null) {
            path.setText(file.getPath());
        }

    }
    static ComboBox<String> comboBox3;
    static ComboBox<String> comboBox4;
    @FXML
    TextField part_no;
    @FXML
    TextField image_no;
    @FXML
    TextField part_nam;
    @FXML
    Label labelparttype3;
    @FXML
    Label labelmodel3;
    @FXML
    Label labelpartno;
    @FXML
    Label labelpartname;
    @FXML
    Label labelimageno;

    @FXML
    public void addPart1(ActionEvent event) throws IOException {
        String ModelName = comboBox3.getEditor().getText();

        String PartNo;
        PartNo = part_no.getText();
        String ImageNo;
        ImageNo = image_no.getText();
        String PartType;
        PartType = comboBox4.getEditor().getText();
        String PartNam = part_nam.getText();

        if (ModelName.length() == 0) {
            labelmodel3.setText("Fill this field");
        } else if (PartNo.length() == 0) {
            labelpartno.setText("Fill this field");
        } else if (ImageNo.length() == 0) {
            labelimageno.setText("Fill this field");
        } else if (PartType.length() == 0) {
            labelparttype3.setText("Fill this field");
        } else if (PartNam.length() == 0) {
            labelpartname.setText("Fill this field");
        } else if (!ImageNo.matches("[0-9]+")) {
            labelimageno.setText("Invalid");
        } else if (!PartType.matches("^[ a-zA-Z]*$")) {
            labelparttype3.setText("Invalid part type");
        } else if (!PartNam.matches("^[ a-zA-Z]*$")) {
            labelpartname.setText("Invalid part type");

        } else {
            Part part = new Part();
            part.setImageNo(ImageNo);
            part.setModelName(ModelName);
            part.setPartName(PartNam);
            part.setPartNo(PartNo);
            part.setPartType(PartType);
            PartDao partdao = new PartDao();
            partdao.addPart(part);
        }
    }
    static ObservableList<String> observableListOfObjects1;

    @FXML
    public void addPart() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/addPart.fxml"));
        final PartDao partdao = new PartDao();
        ArrayList arr1 = new ArrayList();

        arr1 = partdao.getModelData();
        ObservableList<String> observableListOfObjects = FXCollections.observableList(arr1);
        comboBox3 = new ComboBox<String>(observableListOfObjects);
        comboBox3.setLayoutX(245.0);
        comboBox3.setLayoutY(27.0);

        FileChooser chooser = new FileChooser();

        final AnchorPane anchorPane = (AnchorPane) root;
        anchorPane.getChildren().add(comboBox3);

        // comboBox.setId("modelName");
        comboBox3.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String t, String t1) {

                comboBox3.getEditor().setText(t1);
                ArrayList arr2 = new ArrayList();

                arr2 = partdao.getMainPartData(t1);
                System.out.println(t1);
                observableListOfObjects1 = FXCollections.observableList(arr2);
                System.out.println(observableListOfObjects1);
                comboBox4 = new ComboBox<String>(observableListOfObjects1);
                comboBox4.setLayoutX(245.0);
                comboBox4.setLayoutY(75.0);
                comboBox4.valueProperty().addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue ov, String t, String t1) {

                        comboBox4.getEditor().setText(t1);

                    }

                });
                anchorPane.getChildren().add(comboBox4);

            }

        });
        System.out.println(observableListOfObjects1);

        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) AnchorPane.getScene().getWindow();
        stage.setMaximized(true);

        stage.setTitle("Pathum Motors");
        stage.setScene(scene);
        stage.show();

    }
    //add model

    @FXML
    private TextField model_Name;
    @FXML
    private Label labelmodel;

    @FXML
    public void addModel(ActionEvent event) {
        String ModelName = model_Name.getText();

        labelmodel.setText("");
        if (ModelName.length() == 0) {

            labelmodel.setText("Fill this field");
        } else {
            Model model = new Model();
            model.setModelName(ModelName);
            ModelDao modeldao = new ModelDao();
            modeldao.addModel(model);

        }
    }

    static ObservableList<Part> data = FXCollections.observableArrayList();

    static int total;
    @FXML
    TextField partNo;
    @FXML
    TextField amount;
    @FXML
    TextField quentity;
    @FXML
    Label partLabel;

    @FXML

    public void singlePart(ActionEvent event) throws BiffException, WriteException, IOException {

        String que = quentity.getText();
        String quentityval = partNo.getText();
        if (quentityval.length() == 0) {
            partLabel.setText("Enter Part");
        } else if (!quentityval.matches("^[a-zA-Z0-9]*$")) {
            partLabel.setText("Enter Part");
        } else if (que.length() == 0) {
            partLabel.setText("Enter quentity");
        } else if (!que.matches("^[0-9]*$")) {
            partLabel.setText("Enter valid quentity");
        } else {
            billPartTable.getColumns().clear();

            int quentityOfPart = Integer.parseInt(quentity.getText());

            String partNumber = partNo.getText();
            PartDao partDao = new PartDao();
            Part part = partDao.singlePart(partNumber);
            part.setQuentity(quentityOfPart);
            if (part != null) {
                Excel excel = new Excel();
                int price = (excel.load(partNumber)) * quentityOfPart;
                part.setPrice("" + price);
                total = total + price;
                amount.setText("" + total);
            }

            data.add(part);

            TableColumn partId = new TableColumn("Part No");
            partId.setMinWidth(215);
            partId.setCellValueFactory(
                    new PropertyValueFactory<Part, String>("PartNo")
            );

            TableColumn partName = new TableColumn("Part Name");
            partName.setMinWidth(215);
            partName.setCellValueFactory(
                    new PropertyValueFactory<Part, String>("PartName")
            );
            TableColumn quentitypart = new TableColumn("Quentity");
            quentitypart.setMinWidth(50);
            quentitypart.setCellValueFactory(
                    new PropertyValueFactory<Part, Integer>("quentity")
            );
            TableColumn Price = new TableColumn("Price");
            Price.setMinWidth(295);
            Price.setCellValueFactory(
                    new PropertyValueFactory<Part, String>("price")
            );

            billPartTable.getColumns().addAll(partId, partName, quentitypart, Price);
            billPartTable.setItems(data);
        }
    }
    @FXML
    TextField rowNumber;
    @FXML
    Label rowLabel;

    @FXML

    public void deleteRow(ActionEvent event) {
        String pay = rowNumber.getText();
        if (pay.length() == 0) {
            rowLabel.setText("Enter row number");
        } else if (!pay.matches("^[0-9]*$")) {
            rowLabel.setText("Enter valid row number");
        } else {
            int number = Integer.parseInt(rowNumber.getText()) - 1;
            int price = Integer.parseInt(data.get(number).getPrice());
            total = total - price;
            data.remove(number);
            TableColumn partId = new TableColumn("Part No");
            partId.setMinWidth(240);
            partId.setCellValueFactory(
                    new PropertyValueFactory<Part, String>("PartNo")
            );

            TableColumn partName = new TableColumn("Part Name");
            partName.setMinWidth(239);
            partName.setCellValueFactory(
                    new PropertyValueFactory<Part, String>("PartName")
            );
            TableColumn Price = new TableColumn("Price");
            Price.setMinWidth(300);
            Price.setCellValueFactory(
                    new PropertyValueFactory<Part, String>("price")
            );

            amount.setText("" + total);
            rowLabel.setText("");
            billPartTable.getColumns().addAll(partId, partName, Price);
            billPartTable.setItems(data);
        }
    }
    /**
     * Initializes the controller class.
     */

    @FXML
    private TextField name;
    @FXML
    private TextField address;
    @FXML
    private TextField vehicleNo;
    @FXML
    private TextField vehicleType;
    @FXML
    private DatePicker dateOfDelivery;
    @FXML
    private TextField engineNo;
    @FXML
    private TextField phoneNo;
    @FXML
    private TextField chassisNo;
    @FXML
    private TextField freeServiceNo;
    @FXML
    private TextField serviceNo;
    @FXML
    private TextField payment;
    @FXML
    private TextField lastkm;
    static Customer customer = new Customer();

    ;
    
    
    @FXML
    public void addCustomer(ActionEvent event) {
        String customerName = name.getText();
        String customerAdress = address.getText();
        String customerVehicleN0 = vehicleNo.getText();
        String customerVehicleType = vehicleType.getText();
        LocalDate customerDateOfDelivery = dateOfDelivery.getValue();
        Date date = java.sql.Date.valueOf(customerDateOfDelivery);
        int freeService = Integer.parseInt(freeServiceNo.getText());
        int payments = Integer.parseInt(payment.getText());
        int ServiceNo = Integer.parseInt(serviceNo.getText());
        String customerEngineNo = engineNo.getText();
        String customerPhoneNo = phoneNo.getText();
        String customerChassisNo = chassisNo.getText();

        customer.setFreeServiceNo(freeService);
        customer.setLastKm(Integer.parseInt(lastkm.getText()));
        customer.setPayment(payments);
        customer.setServiceNo(ServiceNo);
        customer.setAddress(customerAdress);
        customer.setChassisNo(customerChassisNo);
        customer.setDateOfDelivery(date);
        customer.setEngineNo(customerEngineNo);
        customer.setName(customerName);
        customer.setPhoneNo(customerPhoneNo);
        customer.setVehicleNo(customerVehicleN0);
        customer.setVehicleType(customerVehicleType);

        CustomDao customerDao = new CustomDao();
        customerDao.addCustomer(customer);

    }
    @FXML
    private TextField cycleNo;

    @FXML
    private TextField km;
    @FXML
    private Label details;
    @FXML
    TextField servicePayment;
    @FXML
    private Label cycleNumberLabel;
    @FXML
    private Label kmLabel;
    @FXML
    private Label paymentLabel;
    @FXML
    private Label amountDetails;
    @FXML
    private Label serviceLabel;

    @FXML
    public void Bill(ActionEvent event) {
        String cycleNumber = cycleNo.getText();
        String Km1 = km.getText();
        String amounts = amount.getText();

        String totalCost1 = payment.getText();
        amountDetails.setText("");
        cycleNumberLabel.setText("");
        kmLabel.setText("");
        paymentLabel.setText("");
        if (cycleNumber.length() == 0 || Km1.length() == 0 || amounts.length() == 0 || totalCost1.length() == 0) {
            if (cycleNumber.length() == 0) {
                cycleNumberLabel.setText("Enter Vehicle Number");
            }
            if (Km1.length() == 0) {
                kmLabel.setText("Enter milometer value");
            }
            if (amounts.length() == 0) {
                amountDetails.setText("Enter Payment details");
            }
            if (totalCost1.length() == 0) {
                paymentLabel.setText("enter Customer payment value ");
            }

        } else if (!cycleNumber.matches("^[a-zA-Z0-9]*$")) {
            cycleNumberLabel.setText("Enter valid Vehicle Number");
        } else if (!Km1.matches("^[0-9]*$")) {
            kmLabel.setText("Enter the valid milometer value");
        } else if (!("" + totalCost1).matches("^[0-9]*$")) {
            paymentLabel.setText("Enter the valid payment value");
        } else {
            int totalCost = 0;
            if (totalCost1 != null) {
                totalCost = Integer.parseInt(totalCost1);
            }

            int Km = Integer.parseInt(Km1);
            CustomDao customerDao = new CustomDao();

            Bill bill = new Bill();
            bill.setVehicleNo(cycleNumber);
            bill.setKm(Km);
            bill.setPayment(totalCost);
            if (servicePayment.getText() != null) {
                int ServicePayment = Integer.parseInt(servicePayment.getText());
                bill.setService(ServicePayment);
            }

            bill.setTotalCost(total);

            customerDao.addBill(data, bill);
            int PaymentFee = total - totalCost;
            Part part = new Part();
            Customer customer1 = customerDao.searchCustomer(cycleNumber);
            if (customer1.getFreeServiceNo() < 1) {
                customer1.setFreeServiceNo(0);
            } else {
                customer1.setFreeServiceNo(customer1.getFreeServiceNo() - 1);
            }
            customer.setLastKm(Km);
            customer.setServiceNo(customer1.getServiceNo() + 1);
            customer.setFreeServiceNo(customer1.getFreeServiceNo());
            customer.setVehicleNo(cycleNumber);
            //update Customer
            customer.setPayment(PaymentFee);
            LocalDate now = LocalDate.now();
            Date date = java.sql.Date.valueOf(now);
            customer.setDateOfDelivery(date);
            customerDao.updateCustomer(customer);

            System.out.println(PaymentFee);
            PartDao partDao = new PartDao();
            partDao.addBill(part);
            cycleNo.setText("");
            km.setText("");
            servicePayment.setText("");
            amount.setText("");
            payment.setText("");
            data.clear();
        }

    }

    @FXML
    public void checkCustomer(ActionEvent event) {
        String No = cycleNo.getText();
        String Km1 = km.getText();
        if (No.length() == 0) {
            cycleNumberLabel.setText("Enter Vehicle Number");
        } else if (!No.matches("^[a-zA-Z0-9]*$")) {
            cycleNumberLabel.setText("Enter valid Vehicle Number");
        } else if (Km1.length() == 0) {
            kmLabel.setText("Enter milometer value");
        } else if (!Km1.matches("^[0-9]*$")) {
            kmLabel.setText("Enter the valid milometer value");
        } else {
            CustomDao customerDao = new CustomDao();
            Customer customer = customerDao.searchCustomer(No);
            if (customer != null) {
                LocalDate now = LocalDate.now();
                Date date = java.sql.Date.valueOf(now);
                int kmvalue = Integer.parseInt(Km1) - customer.getLastKm();
                total = total + customer.getPayment();
                amount.setText("" + total);
                System.out.println(No);
                System.out.println(customer.getDateOfDelivery().getTime());
                long datess = (date.getTime() - customer.getDateOfDelivery().getTime());
                int dates = (int) TimeUnit.DAYS.convert(datess, TimeUnit.MILLISECONDS);

                details.setText("Deferent of Km:" + kmvalue + "\n" + "payment:" + customer.getPayment() + "\n" + "this is your " + customer.getServiceNo() + " and you have " + customer.getFreeServiceNo() + " free services \n" + "dates after last Service:" + dates + " Days");
                cycleNumberLabel.setText("");
                kmLabel.setText("");

            }

        }
    }

    @FXML
    public void addServicePayment(ActionEvent event) {
        String pay = servicePayment.getText();
        if (pay.length() == 0) {
            serviceLabel.setText("Enter Service payment");
        } else if (!pay.matches("^[0-9]*$")) {
            serviceLabel.setText("Enter valid Service payment");
        } else {

            int service = Integer.parseInt(pay);
            total = total + service;
            amount.setText("" + total);
            serviceLabel.setText("");
        }
    }

    static ObservableList<String> observableListOfObjects4;
    static ComboBox<String> comboBox5;
    static ComboBox<String> comboBox6;
    static TableView<Part> partTable2;

    @FXML
    public void searchpart() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/searchPart.fxml"));
        final PartDao partdao = new PartDao();
        ArrayList arr1 = new ArrayList();
        partTable2 = new TableView<Part>();
        arr1 = partdao.getModelData();
        ObservableList<String> observableListOfObjects = FXCollections.observableList(arr1);
        comboBox5 = new ComboBox<String>(observableListOfObjects);
        comboBox5.setLayoutX(245.0);
        comboBox5.setLayoutY(75.0);
        partTable2.setLayoutX(40);
        partTable2.setLayoutY(200);
        partTable2.setPrefHeight(300);
        partTable2.setPrefWidth(800);

        FileChooser chooser = new FileChooser();

        final AnchorPane anchorPane = (AnchorPane) root;
        anchorPane.getChildren().add(comboBox5);
        anchorPane.getChildren().add(partTable2);
        // comboBox.setId("modelName");
        comboBox5.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String t, String t1) {

                comboBox5.getEditor().setText(t1);
                ArrayList arr2 = new ArrayList();

                arr2 = partdao.getMainPartData(t1);
                System.out.println(t1);
                observableListOfObjects4 = FXCollections.observableList(arr2);
                System.out.println(observableListOfObjects4);
                comboBox6 = new ComboBox<String>(observableListOfObjects4);
                comboBox6.setLayoutX(245.0);
                comboBox6.setLayoutY(100.0);
                comboBox6.valueProperty().addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue ov, String t, String t1) {

                        comboBox6.getEditor().setText(t1);

                    }

                });
                anchorPane.getChildren().add(comboBox6);

            }

        });
        System.out.println(observableListOfObjects4);

        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) AnchorPane.getScene().getWindow();
        stage.setMaximized(true);

        stage.setTitle("Pathum Motors");
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    public void searchPart2(ActionEvent event) throws IOException {
        String model = comboBox5.getEditor().getText();
        String mainPart = comboBox6.getEditor().getText();
        PartDao partdao = new PartDao();
        ArrayList<Part> arr3 = new ArrayList<Part>();
        arr3 = partdao.searchpart3(model, mainPart);
        final ObservableList<Part> data = FXCollections.observableArrayList();
        System.out.println(arr3.size());
        for (int i = 0; i < arr3.size(); i++) {
            data.add(arr3.get(i));
        }
        TableColumn second = new TableColumn("Part Type");
        second.setMinWidth(200);
        second.setCellValueFactory(
                new PropertyValueFactory<>("PartType"));

        TableColumn first = new TableColumn("Part Name");
        first.setMinWidth(200);
        first.setCellValueFactory(
                new PropertyValueFactory<>("PartName"));
        TableColumn third = new TableColumn("Part No");
        third.setMinWidth(200);
        third.setCellValueFactory(
                new PropertyValueFactory<>("PartNo"));
        TableColumn four = new TableColumn("Quentity");
        four.setMinWidth(200);
        four.setCellValueFactory(
                new PropertyValueFactory<>("quant"));

        partTable2.setEditable(true);

        partTable2.getColumns().addAll(first, second, third, four);
        partTable2.setItems(data);

    }
    //for stock add

    static ObservableList<String> observableListOfObjects5;
    static ComboBox<String> comboBox7;
    static ComboBox<String> comboBox8;
    static TableView<Part> partTable3;

    public void addStock() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/addstock.fxml"));
        final PartDao partdao = new PartDao();
        ArrayList arr1 = new ArrayList();
        arr1 = partdao.getModelData();
        partTable3 = new TableView<Part>();
        ObservableList<String> observableListOfObjects = FXCollections.observableList(arr1);
        comboBox7 = new ComboBox<String>(observableListOfObjects);
        comboBox7.setLayoutX(245.0);
        comboBox7.setLayoutY(40.0);

        partTable3.setLayoutX(150);
        partTable3.setLayoutY(200);
        partTable3.setPrefHeight(300);
        partTable3.setPrefWidth(450);

        final AnchorPane anchorPane = (AnchorPane) root;
        anchorPane.getChildren().add(comboBox7);
        anchorPane.getChildren().add(partTable3);

        comboBox7.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String t, String t1) {

                comboBox7.getEditor().setText(t1);
                ArrayList arr2 = new ArrayList();

                arr2 = partdao.getMainPartData(t1);
                System.out.println(t1);
                observableListOfObjects5 = FXCollections.observableList(arr2);
                System.out.println(observableListOfObjects5);
                comboBox8 = new ComboBox<String>(observableListOfObjects5);
                comboBox8.setLayoutX(245.0);
                comboBox8.setLayoutY(100.0);
                comboBox8.valueProperty().addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue ov, String t, String t1) {

                        comboBox8.getEditor().setText(t1);

                    }

                });
                anchorPane.getChildren().add(comboBox8);

            }

        });

        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) AnchorPane.getScene().getWindow();
        stage.setMaximized(true);

        stage.setTitle("Pathum Motors");
        stage.setScene(scene);
        stage.show();
    }
    //for search button
    ArrayList<Part> arr3;
    static ObservableList<Part> data2;
    @FXML
    private Label modelName;

    @FXML
    public void searchStock(ActionEvent event) throws IOException {

        String model = comboBox7.getEditor().getText();
        String mainPart = comboBox8.getEditor().getText();
        PartDao partdao = new PartDao();
        arr3 = new ArrayList<Part>();
        arr3 = partdao.searchpart4(model, mainPart);
        data2 = FXCollections.observableArrayList();
        System.out.println(arr3.size());
        for (int i = 0; i < arr3.size(); i++) {
            data2.add(arr3.get(i));
        }

        partTable3.setEditable(true);

        /*   Callback<TableColumn, TableCell> cellFactory =
                new Callback<TableColumn, TableCell>() {
                     
                    @Override
                    public TableCell call(TableColumn p) {
                        return new EditingCell();
                    }
                };*/
        TableColumn<Part, String> first = new TableColumn<Part, String>("Part No");
        TableColumn<Part, String> second = new TableColumn<Part, String>("Quentity");
        second.setCellValueFactory(new PropertyValueFactory("quantity"));
        second.setMinWidth(399 / 2);
        first.setCellValueFactory(new PropertyValueFactory("PartNo"));
        first.setMinWidth(399 / 2);

        //second.setCellFactory(TextFieldTableCell.<Part>forTableColumn());
        second.setCellFactory(TextFieldTableCell.<Part>forTableColumn());

        second.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Part, String>>() {

            @Override
            public void handle(TableColumn.CellEditEvent<Part, String> event) {
                ((Part) event.getTableView().getItems().get(event.getTablePosition().getRow())).setQuantity(event.getNewValue());
            }
        });

        partTable3.getColumns().addAll(first, second);

        partTable3.setItems(data2);

        String a = (String) partTable3.getColumns().get(1).getCellData(0);
        System.out.println(a);

    }

    //for add stock
    @FXML
    public void addStock1(ActionEvent event) throws IOException {
        PartDao partdao = new PartDao();

        for (int i = 0; i < data2.size(); i++) {

            String aa = (String) partTable3.getColumns().get(1).getCellData(i);
            System.out.println(aa);
            String bb = (String) partTable3.getColumns().get(0).getCellData(i);
            System.out.println(bb);
            int cc = data2.get(i).getQuant();
            System.out.println(cc);
            int kk = cc + Integer.parseInt(aa);
            System.out.println(kk);
            partdao.addstock(kk, bb);
            data2.get(i).setQuant(kk);

        }

    }
    @FXML
    TextField customer1;
    @FXML
    TableView table;
    @FXML
    TableView customerTable;

    static ObservableList<Bill> data3
            = FXCollections.observableArrayList();

    @FXML
    public void search(ActionEvent event) {
//        Stage stage = (Stage) anchorPane.getScene().getWindow();
        String customerNumber = customer1.getText();
        CustomDao customerDao = new CustomDao();
        Customer customer = customerDao.searchCustomer(customerNumber);
        BillDao billDao = new BillDao();
        ArrayList<Bill> bills = billDao.searchBill(customerNumber);
        for (int i = 0; i < bills.size(); i++) {
            data3.add(bills.get(i));

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

        table.getColumns().addAll(partId, partName, parts, quentitypart, Price, payment);
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

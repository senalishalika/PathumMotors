/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tml.pathummoto.Dao;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.WriteResult;
import com.tml.pathummoto.model.Bill;
import com.tml.pathummoto.model.Customer;
import com.tml.pathummoto.model.Part;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import javafx.collections.ObservableList;


public class CustomDao {
    
    public void addCustomer(Customer customer){
         MongoClient mongoClient = new MongoClient("localhost", 27017);

        // Now connect to your databases
        DB db = mongoClient.getDB("pathumdb");
        System.out.println("Connect to database successfully");
        
        
        DBCollection coll = db.getCollection("customer");
        System.out.println("Collection user selected successfully");
         BasicDBObject doc = new BasicDBObject("title", "customer").
                 append("name", customer.getName()).
                 append("address", customer.getAddress()).
                 append("_id", customer.getVehicleNo()).
                 append("vehicletype", customer.getVehicleType()).
                 append("dateOfDelivery", customer.getDateOfDelivery()).
                 append("engineNo", customer.getEngineNo()).
                 append("chassisNo", customer.getChassisNo()).
                 append("freeServiceNo", customer.getFreeServiceNo()).
                 append("payment", customer.getPayment()).
                 append("serviceNo", customer.getServiceNo()).
                 append("lastKm", customer.getLastKm()).
                 append("phoneNo", customer.getPhoneNo());

            coll.insert(doc);
                System.out.println("successfully");


    }
     public Customer searchCustomer(String no) {
        Customer customer = new Customer();
        // To connect to mongodb server
        MongoClient mongoClient = new MongoClient("localhost", 27017);

        // Now connect to your databases
        DB db = mongoClient.getDB("pathumdb");
        System.out.println("Connect to database successfully");
        
        

        DBCollection coll = db.getCollection("customer");
        System.out.println("Collection user selected successfully");

        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.put("_id", no);
        DBCursor cursor = coll.find(whereQuery);
        while (cursor.hasNext()) {
            System.out.println(cursor.next());
        }
        BasicDBObject doc=(BasicDBObject) cursor.curr();
        System.out.println("doc"+doc);
        if(doc!=null){
            customer.setVehicleNo(doc.getString("_id"));
            customer.setName(doc.getString("name"));
            customer.setPayment(doc.getInt("payment"));
            customer.setFreeServiceNo(doc.getInt("freeServiceNo"));
            customer.setServiceNo(doc.getInt("serviceNo"));
            customer.setDateOfDelivery(doc.getDate("dateOfDelivery"));
            customer.setLastKm(doc.getInt("lastKm"));
           
            
        }
        
        return customer;
    }

    public void updateCustomer(Customer customer) {
        MongoClient mongoClient = new MongoClient("localhost", 27017);

        // Now connect to your databases
        DB db = mongoClient.getDB("pathumdb");
        System.out.println("Connect to database successfully");
        
        

        DBCollection coll = db.getCollection("customer");
        System.out.println("Collection user selected successfully");
        
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.put("_id", customer.getVehicleNo());
        
        DBCursor cursor = coll.find(whereQuery);
        System.out.println(customer.getVehicleNo());
        DBObject query = new BasicDBObject("_id", customer.getVehicleNo());
		DBObject update = new BasicDBObject();
		update.put("$set", new BasicDBObject("payment",customer.getPayment()).append("freeServiceNo", customer.getFreeServiceNo()).append("serviceNo",customer.getServiceNo()).append("dateOfDelivery",customer.getDateOfDelivery()).append("lastKm",customer.getLastKm()));
		
		WriteResult result = coll.update(query, update);
                System.out.println(result);
            
                
            

    }

    public void addBill(ObservableList<Part> data, Bill bill) {
         MongoClient mongoClient = new MongoClient("localhost", 27017);

        // Now connect to your databases
        DB db = mongoClient.getDB("pathumdb");
        System.out.println("Connect to database successfully");
        
        

        DBCollection coll = db.getCollection("Bill");
        System.out.println("Collection user selected successfully");
        
        LocalDate now = LocalDate.now();
        Date date = java.sql.Date.valueOf(now);
        
        for(int i=0;i<data.size();i++){
            
        }
        
        BasicDBObject doc = new BasicDBObject("title", "Bill").
                 append("Date",date).
                append("vehicleNo", bill.getVehicleNo()).
                append("km", bill.getKm())
                .append("payment", bill.getPayment())
                .append("service", bill.getService())
                .append("total", bill.getTotalCost())
                .append("partsSize", data.size());
        
        for(int i=0;i<data.size();i++){
            String parts="part"+i;
            String quentity="quant"+i;
            doc.append(parts, data.get(i).getPartNo()).append(quentity, data.get(i).getQuentity());
        }
        coll.insert(doc);
        System.out.println(doc);
    }
}

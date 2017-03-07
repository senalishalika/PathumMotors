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
import com.mongodb.MongoClient;
import com.tml.pathummoto.model.Bill;
import com.tml.pathummoto.model.Customer;
import com.tml.pathummoto.model.Part;
import java.util.ArrayList;

/**
 *
 * @author Tishan Madhawa
 */
public class BillDao {
    
    public ArrayList<Bill> searchBill(String no) {
        
        MongoClient mongoClient = new MongoClient("localhost", 27017);

        // Now connect to your databases
        DB db = mongoClient.getDB("pathumdb");
        System.out.println("Connect to database successfully");
        
        DBCollection coll = db.getCollection("Bill");
        System.out.println("Collection user selected successfully");
        
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.put("vehicleNo", no);
        DBCursor cursor = coll.find(whereQuery);
        
        ArrayList<Bill> bills = new ArrayList<Bill>();
        
        while (cursor.hasNext()) {
            BasicDBObject doc = (BasicDBObject) cursor.next();
            
            if (doc != null) {
                Bill bill = new Bill();
                bill.setDate(doc.getDate("Date"));
                bill.setKm(doc.getInt("km"));
                bill.setPayment(doc.getInt("payment"));
                bill.setService(doc.getInt("service"));
                bill.setTotalCost(doc.getInt("total"));
                String array="";
                int partSize = doc.getInt("partsSize");
                for (int i = 0; i < partSize; i++) {
                    String parts = "part" + i;
                    String quentity = "quant" + i;
                    array=array+doc.getString(parts)+" : "+doc.getInt(quentity)+"\n";
                    
                }
                bill.setParts(array);
                bills.add(bill);
            }
        }
       return bills; 
    }
    
}

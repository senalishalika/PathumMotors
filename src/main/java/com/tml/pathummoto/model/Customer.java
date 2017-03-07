/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tml.pathummoto.model;

import java.time.LocalDate;
import java.util.Date;


public class Customer {
    private String name;
    private String address;
    private String vehicleNo;
    private String vehicleType;
    private Date dateOfDelivery;
    private String engineNo;
    private String phoneNo;
    private String chassisNo;
    private int lastKm;

    public int getLastKm() {
        return lastKm;
    }

    public void setLastKm(int lastKm) {
        this.lastKm = lastKm;
    }
    private int serviceNo;
    private int freeServiceNo;

    public int getFreeServiceNo() {
        return freeServiceNo;
    }

    public void setFreeServiceNo(int freeServiceNo) {
        this.freeServiceNo = freeServiceNo;
    }
    private int payment;
    public int getServiceNo() {
        return serviceNo;
    }

    public void setServiceNo(int ServiceNo) {
        this.serviceNo = ServiceNo;
    }

    public int getPayment() {
        return payment;
    }

    public void setPayment(int payment) {
        this.payment = payment;
    }
    
    
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Date getDateOfDelivery() {
        return dateOfDelivery;
    }

    public void setDateOfDelivery(Date dateOfDelivery) {
        this.dateOfDelivery = dateOfDelivery;
    }

    public String getEngineNo() {
        return engineNo;
    }

    public void setEngineNo(String engineNo) {
        this.engineNo = engineNo;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getChassisNo() {
        return chassisNo;
    }

    public void setChassisNo(String chassisNo) {
        this.chassisNo = chassisNo;
    }
   
    
    
    
}

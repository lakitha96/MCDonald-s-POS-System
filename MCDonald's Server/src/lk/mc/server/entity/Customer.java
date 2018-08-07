/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.mc.server.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author lakitha
 */
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int CID;
    private String name;
    private String Address;
    private String mobileNumber;

    public Customer() {
    }

    public Customer(int CID, String name, String Address, String mobileNumber) {
        this.CID = CID;
        this.name = name;
        this.Address = Address;
        this.mobileNumber = mobileNumber;
    }

    /**
     * @return the CID
     */
    public int getCID() {
        return CID;
    }

    /**
     * @param CID the CID to set
     */
    public void setCID(int CID) {
        this.CID = CID;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the Address
     */
    public String getAddress() {
        return Address;
    }

    /**
     * @param Address the Address to set
     */
    public void setAddress(String Address) {
        this.Address = Address;
    }

    /**
     * @return the mobileNumber
     */
    public String getMobileNumber() {
        return mobileNumber;
    }

    /**
     * @param mobileNumber the mobileNumber to set
     */
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    @Override
    public String toString() {
        return "Customer{" + "CID=" + CID + ", name=" + name + ", Address=" + Address + ", mobileNumber=" + mobileNumber + '}';
    }

    

}

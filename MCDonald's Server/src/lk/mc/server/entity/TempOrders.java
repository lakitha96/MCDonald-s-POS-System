/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.mc.server.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author lakitha
 */
@Entity
public class TempOrders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int OID;
    private String date;
    private String orderStatus;
    @ManyToOne(cascade = CascadeType.ALL)
    private Customer customer;
    @ManyToOne(cascade = CascadeType.ALL)
    private Cashier cashier;

    public TempOrders() {
    }

    public TempOrders(int OID, String date, String orderStatus, Customer customer, TelephoneOperator telephoneOperator) {
        this.date = date;
        this.orderStatus = orderStatus;
    }
    
    public TempOrders(int OID, String date, String orderStatus, Customer customer, Cashier cashier) {
        this.OID = OID;
        this.date = date;
        this.orderStatus = orderStatus;
        this.customer = customer;
        this.cashier = cashier;
    }

    /**
     * @return the OID
     */
    public int getOID() {
        return OID;
    }

    /**
     * @param OID the OID to set
     */
    public void setOID(int OID) {
        this.OID = OID;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return the orderStatus
     */
    public String getOrderStatus() {
        return orderStatus;
    }

    /**
     * @param orderStatus the orderStatus to set
     */
    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * @return the customer
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * @param customer the customer to set
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * @return the cashier
     */
    public Cashier getCashier() {
        return cashier;
    }

    /**
     * @param cashier the cashier to set
     */
    public void setCashier(Cashier cashier) {
        this.cashier = cashier;
    }

    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.mc.server.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author lakitha
 */
@Entity
public class Cashier {
    @Id
    private String cashierID;
    private String name;

    public Cashier() {
    }

    public Cashier(String cashierID, String name) {
        this.cashierID = cashierID;
        this.name = name;
    }

    /**
     * @return the cashierID
     */
    public String getCashierID() {
        return cashierID;
    }

    /**
     * @param cashierID the cashierID to set
     */
    public void setCashierID(String cashierID) {
        this.cashierID = cashierID;
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

    @Override
    public String toString() {
        return "Cashier{" + "cashierID=" + cashierID + ", name=" + name + '}';
    }

    
}

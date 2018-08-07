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
public class Chef {
    @Id
    private int chefID;
    private String chefName;

    public Chef() {
    }

    public Chef(int chefID, String chefName) {
        this.chefID = chefID;
        this.chefName = chefName;
    }

    /**
     * @return the chefID
     */
    public int getChefID() {
        return chefID;
    }

    /**
     * @param chefID the chefID to set
     */
    public void setChefID(int chefID) {
        this.chefID = chefID;
    }

    /**
     * @return the chefName
     */
    public String getChefName() {
        return chefName;
    }

    /**
     * @param chefName the chefName to set
     */
    public void setChefName(String chefName) {
        this.chefName = chefName;
    }

    @Override
    public String toString() {
        return "Chef{" + "chefID=" + chefID + ", chefName=" + chefName + '}';
    }
    
    
    
    
}

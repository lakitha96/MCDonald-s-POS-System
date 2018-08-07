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
public class BakeHouse {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int BID;
    private String processingTime;
    private int preparedQty;
    private String date;
    @ManyToOne(cascade = CascadeType.ALL)
    private Item item;
    @ManyToOne(cascade = CascadeType.ALL)
    private Chef chef;

    public BakeHouse() {
    }

    public BakeHouse(int BID, String processingTime, int preparedQty, String date, Item item, Chef chef) {
        this.BID = BID;
        this.processingTime = processingTime;
        this.preparedQty = preparedQty;
        this.date = date;
        this.item = item;
        this.chef = chef;
    }

    /**
     * @return the BID
     */
    public int getBID() {
        return BID;
    }

    /**
     * @param BID the BID to set
     */
    public void setBID(int BID) {
        this.BID = BID;
    }

    /**
     * @return the processingTime
     */
    public String getProcessingTime() {
        return processingTime;
    }

    /**
     * @param processingTime the processingTime to set
     */
    public void setProcessingTime(String processingTime) {
        this.processingTime = processingTime;
    }

    /**
     * @return the preparedQty
     */
    public int getPreparedQty() {
        return preparedQty;
    }

    /**
     * @param preparedQty the preparedQty to set
     */
    public void setPreparedQty(int preparedQty) {
        this.preparedQty = preparedQty;
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
     * @return the item
     */
    public Item getItem() {
        return item;
    }

    /**
     * @param item the item to set
     */
    public void setItem(Item item) {
        this.item = item;
    }

    /**
     * @return the chef
     */
    public Chef getChef() {
        return chef;
    }

    /**
     * @param chef the chef to set
     */
    public void setChef(Chef chef) {
        this.chef = chef;
    }

    
    
    

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.mc.common.dto;

/**
 *
 * @author lakitha
 */
public class BakeHouseDTO extends SuperDTO{
    private int BID;
    private String processingTime;
    private int preparedQty;
    private String date;
    private ItemDTO itemDTO;
    private ChefDTO chefDTO;
    

    public BakeHouseDTO() {
    }

    public BakeHouseDTO(int BID, String processingTime, int preparedQty, String date, ItemDTO itemDTO, ChefDTO chefDTO) {
        this.BID = BID;
        this.processingTime = processingTime;
        this.preparedQty = preparedQty;
        this.date = date;
        this.itemDTO = itemDTO;
        this.chefDTO = chefDTO;
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
     * @return the itemDTO
     */
    public ItemDTO getItemDTO() {
        return itemDTO;
    }

    /**
     * @param itemDTO the itemDTO to set
     */
    public void setItemDTO(ItemDTO itemDTO) {
        this.itemDTO = itemDTO;
    }

    /**
     * @return the chefDTO
     */
    public ChefDTO getChefDTO() {
        return chefDTO;
    }

    /**
     * @param chefDTO the chefDTO to set
     */
    public void setChefDTO(ChefDTO chefDTO) {
        this.chefDTO = chefDTO;
    }

    
}

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
public class TelephoneOperatorDTO extends SuperDTO{
    private int TID;
    private String teleOPName;

    public TelephoneOperatorDTO() {
    }

    public TelephoneOperatorDTO(int TID, String teleOPName) {
        this.TID = TID;
        this.teleOPName = teleOPName;
    }

    /**
     * @return the TID
     */
    public int getTID() {
        return TID;
    }

    /**
     * @param TID the TID to set
     */
    public void setTID(int TID) {
        this.TID = TID;
    }

    /**
     * @return the teleOPName
     */
    public String getTeleOPName() {
        return teleOPName;
    }

    /**
     * @param teleOPName the teleOPName to set
     */
    public void setTeleOPName(String teleOPName) {
        this.teleOPName = teleOPName;
    }
    
    
}

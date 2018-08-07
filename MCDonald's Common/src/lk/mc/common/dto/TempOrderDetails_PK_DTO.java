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
public class TempOrderDetails_PK_DTO {
    private int OID;
    private int code;
    private int chefID;

    public TempOrderDetails_PK_DTO() {
    }

    public TempOrderDetails_PK_DTO(int OID, int code, int chefID) {
        this.OID = OID;
        this.code = code;
        this.chefID = chefID;
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
     * @return the code
     */
    public int getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(int code) {
        this.code = code;
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

    
    
}

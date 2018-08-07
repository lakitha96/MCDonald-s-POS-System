/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.mc.server.entity;

import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 *
 * @author lakitha
 */
@Embeddable
public class OrderDetails_PK implements Serializable{
    private int OID;
    private int code;

    public OrderDetails_PK() {
    }

    public OrderDetails_PK(int OID, int code) {
        this.OID = OID;
        this.code = code;
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
}

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
public class CashierLogin {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int CLID;
    private String userName;
    private String password;

    public CashierLogin() {
    }

    public CashierLogin(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    /**
     * @return the CLID
     */
    public int getCLID() {
        return CLID;
    }

    /**
     * @param CLID the CLID to set
     */
    public void setCLID(int CLID) {
        this.CLID = CLID;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "CashierLogin{" + "CLID=" + CLID + ", userName=" + userName + ", password=" + password + '}';
    }
}

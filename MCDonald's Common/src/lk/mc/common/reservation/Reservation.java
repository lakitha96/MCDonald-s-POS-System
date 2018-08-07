/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.mc.common.reservation;

import java.rmi.Remote;

/**
 *
 * @author lakitha
 */
public interface Reservation extends Remote{
    
    public abstract boolean reserve(Object id)throws Exception;
    
    public abstract boolean release(Object id)throws Exception;
    
}

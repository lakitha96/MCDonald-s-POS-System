/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.mc.common.observer;

import java.rmi.Remote;

/**
 *
 * @author lakitha
 */
public interface Observer extends Remote {

    public void updateObserver() throws Exception;
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.mc.administrator.controller;

import lk.mc.administrator.proxy.ProxyHandler;
import lk.mc.common.observer.Subject;
import lk.mc.common.service.ServiceFactory;


/**
 *
 * @author lakitha
 */
public class ItemObserverController {
    public static Subject getSubject() throws Exception{
        return (Subject) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.ITEM);
    }
}

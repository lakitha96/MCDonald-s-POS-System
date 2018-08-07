/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.mc.mealcollection.controller;

import lk.mc.common.observer.Subject;
import lk.mc.common.service.ServiceFactory;
import lk.mc.mealcollection.proxy.ProxyHandler;

/**
 *
 * @author lakitha
 */
public class ItemObserverController {
    public static Subject getSubject() throws Exception{
        return (Subject) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.ITEM);
    }
}

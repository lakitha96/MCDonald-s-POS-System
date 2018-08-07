/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.mc.server.service.custom.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lk.mc.common.dto.ItemDTO;
import lk.mc.common.observer.Observer;
import lk.mc.common.observer.Subject;
import lk.mc.common.reservation.Reservation;
import lk.mc.common.service.custom.ItemService;
import lk.mc.server.business.BOFactory;
import lk.mc.server.business.custom.ItemBO;

/**
 *
 * @author lakitha
 */
public class ItemServiceImpl extends UnicastRemoteObject implements ItemService, Subject {

    private ItemBO itemBO;
    private static List<Observer> allObservers = new ArrayList<>();

    public ItemServiceImpl() throws RemoteException {
        itemBO = (ItemBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.ITEM);
    }

    @Override
    public boolean saveItem(ItemDTO itemDTO) throws Exception {
        boolean b = itemBO.saveItem(itemDTO);
        notifyAllObservers();
        return b;
    }

    @Override
    public boolean delete(int code) throws Exception {
        boolean b = itemBO.delete(code);
        notifyAllObservers();
        return b;
    }

    @Override
    public boolean update(ItemDTO i) throws Exception {
        boolean b = itemBO.update(i);
        notifyAllObservers();
        return b;
    }

    @Override
    public ItemDTO findByName(String name) throws Exception {
        ItemDTO itemDTO = itemBO.findByName(name);
        return itemDTO;

    }

    @Override
    public List<ItemDTO> findAll() throws Exception {
        List<ItemDTO> all = itemBO.findAll();
        return all;
    }

    @Override
    public void registerObserver(Observer observer) throws Exception {
        System.err.println("Observer Added");
        allObservers.add(observer);
    }

    @Override
    public void unRegisterObserver(Observer observer) throws Exception {
        allObservers.remove(observer);
    }

    @Override
    public void notifyAllObservers() throws Exception {
        System.out.println("notification 1"+allObservers.size());
        new Thread(() -> {
            System.out.println("notification 2"+allObservers.size());
            for (Observer observer : allObservers) {
                try {
                    System.out.println("Observer Updated...."+allObservers.size());
                    observer.updateObserver();
                } catch (Exception ex) {
                    Logger.getLogger(CustomerServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }).start();

    }

    @Override
    public void unRegisterAllObserver() throws Exception {
        allObservers.clear();
    }

}

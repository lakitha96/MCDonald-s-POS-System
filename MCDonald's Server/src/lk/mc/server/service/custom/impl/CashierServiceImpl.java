/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.mc.server.service.custom.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import lk.mc.common.dto.CashierDTO;
import lk.mc.common.service.custom.CashierService;
import lk.mc.server.business.BOFactory;
import lk.mc.server.business.custom.CashierBO;

/**
 *
 * @author lakitha
 */
public class CashierServiceImpl extends UnicastRemoteObject implements CashierService{
    private CashierBO cashierBO;

    public CashierServiceImpl() throws RemoteException{
        cashierBO=(CashierBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.CASHIER);
    }

    @Override
    public boolean save(CashierDTO cashier) throws Exception {
        return cashierBO.save(cashier);
    }

    @Override
    public boolean update(CashierDTO cashierDTO) throws Exception {
        return cashierBO.update(cashierDTO);
    }

    @Override
    public boolean delete(String cashierID) throws Exception {
        return cashierBO.delete(cashierID);
    }

    @Override
    public CashierDTO findByName(String name) throws Exception {
        return cashierBO.findByName(name);
    }

    @Override
    public CashierDTO findByID(String cashierID) throws Exception {
        return cashierBO.findByID(cashierID);
    }

    @Override
    public List<CashierDTO> findAll() throws Exception {
        return cashierBO.findAll();
    }
    
    
    
    
}

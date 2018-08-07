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
import lk.mc.common.dto.CashierLoginDTO;
import lk.mc.common.service.custom.CashierLoginService;
import lk.mc.common.service.custom.CashierService;
import lk.mc.server.business.BOFactory;
import lk.mc.server.business.custom.CashierLoginBO;

/**
 *
 * @author lakitha
 */
public class CashierLoginServiceImpl extends UnicastRemoteObject implements CashierLoginService {

    private CashierLoginBO cashierLoginBO;

    public CashierLoginServiceImpl() throws RemoteException {
        cashierLoginBO = (CashierLoginBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.CASHIERLOGIN);
    }

    @Override
    public boolean save(CashierLoginDTO cashierLogin) throws Exception {
        return cashierLoginBO.save(cashierLogin);
    }

    @Override
    public boolean update(CashierLoginDTO cashierLogin) throws Exception {
        return cashierLoginBO.update(cashierLogin);
    }

    @Override
    public boolean delete(int cashierID) throws Exception {
        return cashierLoginBO.delete(cashierID);
    }

    @Override
    public CashierLoginDTO findByName(String name) throws Exception {
        return cashierLoginBO.findByName(name);
    }

}

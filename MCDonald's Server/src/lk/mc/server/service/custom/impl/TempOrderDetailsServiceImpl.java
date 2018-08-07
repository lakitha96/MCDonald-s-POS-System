/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.mc.server.service.custom.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import lk.mc.common.dto.TempOrderDetailsDTO;
import lk.mc.common.service.custom.TempOrderDetailsService;
import lk.mc.server.business.BOFactory;
import lk.mc.server.business.custom.TempOrderDetailsBO;

/**
 *
 * @author lakitha
 */
public class TempOrderDetailsServiceImpl extends UnicastRemoteObject implements TempOrderDetailsService{

    private TempOrderDetailsBO orderDetailsBO;
    
    public TempOrderDetailsServiceImpl() throws RemoteException{
        orderDetailsBO=(TempOrderDetailsBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.TEMPORDERDETAILS);
    }
    
    

    @Override
    public boolean update(TempOrderDetailsDTO tempOrderDetailsDTO) throws Exception {
        return orderDetailsBO.update(tempOrderDetailsDTO);
    }

    @Override
    public boolean delete(int oid, int code) throws Exception {
        return orderDetailsBO.delete(oid, code);
    }

    @Override
    public List<TempOrderDetailsDTO> allTempDetails() throws Exception {
        return orderDetailsBO.allTempDetails();
    }
    
}

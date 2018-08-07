/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.mc.server.service.custom.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import lk.mc.common.dto.BakeHouseDTO;
import lk.mc.common.service.custom.BakeHouseService;
import lk.mc.server.business.BOFactory;
import lk.mc.server.business.custom.BakeHouseBO;

/**
 *
 * @author lakitha
 */
public class BakeHouseServiceImpl extends UnicastRemoteObject implements BakeHouseService{
    private BakeHouseBO bakeHouseBO;

    public BakeHouseServiceImpl() throws RemoteException{
        bakeHouseBO=(BakeHouseBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.BAKEHOUSE);
    }
    
    
    @Override
    public boolean save(BakeHouseDTO bakeHouseDTO) throws Exception {
        return bakeHouseBO.save(bakeHouseDTO);
    }

    @Override
    public boolean delete(String BID) throws Exception {
        return bakeHouseBO.delete(BID);
    }

    @Override
    public List findByDate(String date) throws Exception {
        return bakeHouseBO.findByDate(date);
    }

    @Override
    public BakeHouseDTO findByID(String BID) throws Exception {
        return bakeHouseBO.findByID(BID);
    }

    @Override
    public List<BakeHouseDTO> findAll() throws Exception {
        return bakeHouseBO.findAll();
    }
    
}

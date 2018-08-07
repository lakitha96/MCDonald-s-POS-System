/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.mc.server.service.custom.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import lk.mc.common.dto.ChefDTO;
import lk.mc.common.service.custom.ChefService;
import lk.mc.server.business.BOFactory;
import lk.mc.server.business.custom.ChefBO;

/**
 *
 * @author lakitha
 */
public class ChefServiceServiceImpl extends UnicastRemoteObject implements ChefService{
    
    private ChefBO chefBO;
    
    public ChefServiceServiceImpl() throws RemoteException{
        chefBO=(ChefBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.CHEF);
    }
    
    

    @Override
    public boolean save(ChefDTO chefDTO) throws Exception {
        return chefBO.save(chefDTO);
    }

    @Override
    public boolean update(ChefDTO chefDTO) throws Exception {
        return chefBO.update(chefDTO);
    }

    @Override
    public boolean delete(String chefID) throws Exception {
        return chefBO.delete(chefID);
    }

    @Override
    public ChefDTO findByName(String name) throws Exception {
        return chefBO.findByName(name);
    }

    @Override
    public ChefDTO findByID(int id) throws Exception {
        return chefBO.findByID(id);
    }

    @Override
    public List<ChefDTO> findAll() throws Exception {
        return chefBO.findAll();
    }
    
}

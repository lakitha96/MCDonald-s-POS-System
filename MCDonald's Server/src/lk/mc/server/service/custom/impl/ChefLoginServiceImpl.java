/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.mc.server.service.custom.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import lk.mc.common.dto.ChefLoginDTO;
import lk.mc.common.service.custom.ChefLoginService;
import lk.mc.server.business.BOFactory;
import lk.mc.server.business.custom.ChefBO;
import lk.mc.server.business.custom.ChefLoginBO;

/**
 *
 * @author lakitha
 */
public class ChefLoginServiceImpl extends UnicastRemoteObject implements ChefLoginService {

    private ChefLoginBO chefLoginBO;

    public ChefLoginServiceImpl() throws RemoteException {
        chefLoginBO = (ChefLoginBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.CHEFLOGIN);
    }

    @Override
    public boolean save(ChefLoginDTO chefLoginDTO) throws Exception {
        return chefLoginBO.save(chefLoginDTO);
    }

    @Override
    public boolean update(ChefLoginDTO chefLoginDTO) throws Exception {
        return chefLoginBO.update(chefLoginDTO);
    }

    @Override
    public boolean delete(int chefID) throws Exception {
        return chefLoginBO.delete(chefID);
    }

    @Override
    public ChefLoginDTO findByName(String name) throws Exception {
        return chefLoginBO.findByName(name);
    }

}

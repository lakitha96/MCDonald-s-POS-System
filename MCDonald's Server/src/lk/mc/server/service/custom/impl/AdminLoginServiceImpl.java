/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.mc.server.service.custom.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import lk.mc.common.dto.AdminLoginDTO;
import lk.mc.common.service.custom.AdminLoginService;
import lk.mc.server.business.BOFactory;
import lk.mc.server.business.custom.AdminLoginBO;

/**
 *
 * @author lakitha
 */
public class AdminLoginServiceImpl extends UnicastRemoteObject implements AdminLoginService{
    
    private AdminLoginBO adminLoginBO;
    
    public AdminLoginServiceImpl() throws RemoteException{
        adminLoginBO=(AdminLoginBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.ADMINLOGIN);
    }

    @Override
    public boolean save(AdminLoginDTO a) throws Exception {
        System.out.println("@ServiceImpl");
        return adminLoginBO.save(a);
    }

    @Override
    public boolean update(AdminLoginDTO a) throws Exception {
        return adminLoginBO.update(a);
    }

    @Override
    public boolean delete(int adminID) throws Exception {
        return adminLoginBO.delete(adminID);
    }

    @Override
    public AdminLoginDTO findByName(String name) throws Exception {
        return adminLoginBO.findByName(name);
    }
    
}

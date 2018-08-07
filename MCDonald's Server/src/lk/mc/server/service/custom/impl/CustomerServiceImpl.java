/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.mc.server.service.custom.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import lk.mc.common.dto.CustomerDTO;
import lk.mc.common.service.custom.CustomerService;
import lk.mc.server.business.BOFactory;
import lk.mc.server.business.custom.CustomerBO;
import lk.mc.server.business.custom.ItemBO;

/**
 *
 * @author lakitha
 */
public class CustomerServiceImpl extends UnicastRemoteObject implements CustomerService{
    private CustomerBO customerBO;
    public CustomerServiceImpl() throws RemoteException{
       customerBO=(CustomerBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.CUSTOMER);
    }

    @Override
    public boolean addCustomer(CustomerDTO customer) throws Exception {
        return customerBO.addCustomer(customer);
    }

    @Override
    public boolean updateCustomer(CustomerDTO customer) throws Exception {
        return customerBO.updateCustomer(customer);
    }

    @Override
    public boolean deleteCustomer(int customerID) throws Exception {
        return customerBO.deleteCustomer(customerID);
    }

    @Override
    public CustomerDTO findByName(String name) throws Exception {
        return customerBO.findByName(name);
    }

    @Override
    public CustomerDTO findByID(int code) throws Exception {
        return customerBO.findByID(code);
    }

    @Override
    public List<CustomerDTO> findAll() throws Exception {
        return customerBO.findAll();
    }
    
}


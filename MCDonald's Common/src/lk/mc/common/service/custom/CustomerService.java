/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.mc.common.service.custom;

import java.util.List;
import lk.mc.common.dto.CustomerDTO;
import lk.mc.common.service.SuperService;

/**
 *
 * @author lakitha
 */
public interface CustomerService extends SuperService {

    public boolean addCustomer(CustomerDTO customer) throws Exception;

    public boolean updateCustomer(CustomerDTO customer) throws Exception;

    public boolean deleteCustomer(int customerID) throws Exception;

    public CustomerDTO findByName(String name) throws Exception;

    public CustomerDTO findByID(int code) throws Exception;

    public List<CustomerDTO> findAll() throws Exception;
}

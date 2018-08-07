/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.mc.server.repository.custom;

import lk.mc.server.entity.Customer;
import lk.mc.server.repository.SuperRepository;

/**
 *
 * @author lakitha
 */
public interface CustomerRepository extends SuperRepository<Customer, Integer>{
    //public Customer findByName(String name) throws Exception;
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.mc.server.business.custom.impl;

import java.util.ArrayList;
import java.util.List;
import lk.mc.common.dto.CustomerDTO;
import lk.mc.common.dto.ItemDTO;
import lk.mc.server.business.custom.CustomerBO;
import lk.mc.server.entity.Customer;
import lk.mc.server.entity.Item;
import lk.mc.server.repository.RepositoryFactory;
import lk.mc.server.repository.custom.CustomerRepository;
import lk.mc.server.resource.HibernateUtil;
import org.hibernate.Session;

/**
 *
 * @author lakitha
 */
public class CustomerBOImpl implements CustomerBO{

    private CustomerRepository customerRepository;

    public CustomerBOImpl() {
        customerRepository=(CustomerRepository) RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryTypes.CUSTOMER);
    }
    
    
    @Override
    public boolean addCustomer(CustomerDTO c) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            customerRepository.setSession(session);

            session.beginTransaction();
            Customer customer =new Customer(c.getCID(), c.getName(), c.getAddress(),  c.getMobileNumber());

            boolean result = customerRepository.save(customer);
            session.getTransaction().commit();
            return result;
        }
    }

    @Override
    public boolean updateCustomer(CustomerDTO c) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            customerRepository.setSession(session);
            
            session.beginTransaction();
            
            
            
            Customer customer =new Customer(c.getCID(), c.getName(),  c.getAddress(), c.getMobileNumber());
            
            customerRepository.update(customer);
            session.getTransaction().commit();
            
            return true;
        }catch(Exception exp){
            exp.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteCustomer(int customerID) throws Exception {
        try(Session session=HibernateUtil.getSessionFactory().openSession()){
            customerRepository.setSession(session);
            
            session.beginTransaction();

            Customer c= customerRepository.findByID(customerID);
            boolean result = false;

            if (c != null) {

                customerRepository.delete(c);
                result=true;
            }
            
            session.getTransaction().commit();

            return result;
            
        }
    }

    @Override
    public CustomerDTO findByName(String name) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            customerRepository.setSession(session);
        
            session.beginTransaction();
            
            Customer c = customerRepository.findByName("from Customer where name='"+name+"'");

            session.getTransaction().commit();

            if (c != null) {
                CustomerDTO customerDTO =new CustomerDTO(c.getCID(), c.getName(), c.getMobileNumber(), c.getAddress());

                return customerDTO;

            } else {
                return null;
            }

        }
    }

    @Override
    public CustomerDTO findByID(int code) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            customerRepository.setSession(session);
        
            session.beginTransaction();
            
            Customer c = customerRepository.findByID(code);

            session.getTransaction().commit();

            if (c != null) {
                CustomerDTO customerDTO =new CustomerDTO(c.getCID(), c.getName(), c.getMobileNumber(), c.getAddress());

                return customerDTO;

            } else {
                return null;
            }

        }
    }

    @Override
    public List<CustomerDTO> findAll() throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            customerRepository.setSession(session);
            session.beginTransaction();
            List<Customer> customers = customerRepository.findAll();
            session.getTransaction().commit();

            if (customers != null) {

                List<CustomerDTO> allCustomers = new ArrayList<>();

                for (Customer c : customers) {
                    CustomerDTO customerDTO =new CustomerDTO(c.getCID(), c.getName(), c.getMobileNumber(), c.getAddress());
                    allCustomers.add(customerDTO);
                }

                return allCustomers;

            } else {

                return null;
            }

        }
    }
    
}

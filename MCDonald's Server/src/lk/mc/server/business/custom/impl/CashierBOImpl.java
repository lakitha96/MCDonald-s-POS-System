/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.mc.server.business.custom.impl;

import java.util.ArrayList;
import java.util.List;
import lk.mc.common.dto.CashierDTO;
import lk.mc.server.business.custom.CashierBO;
import lk.mc.server.entity.Cashier;
import lk.mc.server.repository.RepositoryFactory;
import lk.mc.server.repository.custom.CashierRepository;
import lk.mc.server.resource.HibernateUtil;
import org.hibernate.Session;

/**
 *
 * @author lakitha
 */
public class CashierBOImpl implements CashierBO{
    
    private CashierRepository cashierRepository;

    public CashierBOImpl() {
        cashierRepository=(CashierRepository) RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryTypes.CASHIER);
    }
    
    

    @Override
    public boolean save(CashierDTO c) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            cashierRepository.setSession(session);

            session.beginTransaction();
            Cashier cashier=new Cashier();
            cashier.setCashierID(c.getCashierID());
            cashier.setName(c.getName());
            

            boolean result = cashierRepository.save(cashier);
            session.getTransaction().commit();
            return result;
        }
    }

    @Override
    public boolean update(CashierDTO c) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            cashierRepository.setSession(session);
            
            session.beginTransaction();
            
            
            
            Cashier cashier=new Cashier();
            cashier.setCashierID(c.getCashierID());
            cashier.setCashierID(c.getName());
            
            cashierRepository.update(cashier);
            session.getTransaction().commit();
            
            return true;
        }catch(Exception exp){
            exp.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(String cashierID) throws Exception {
        try(Session session=HibernateUtil.getSessionFactory().openSession()){
            cashierRepository.setSession(session);
            
            session.beginTransaction();

            //Customer c= customerRepository.findByID(customerID);
            
            Cashier c=cashierRepository.findByIDS(cashierID);
            boolean result = false;

            if (c != null) {

                cashierRepository.delete(c);
                result=true;
            }
            
            session.getTransaction().commit();

            return result;
            
        }
    }

    @Override
    public CashierDTO findByName(String name) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            cashierRepository.setSession(session);
        
            session.beginTransaction();
            
            Cashier c = cashierRepository.findByName("from Cashier where name='"+name+"'");

            session.getTransaction().commit();

            if (c != null) {
                CashierDTO cashierDTO=new CashierDTO(c.getCashierID(), c.getName());

                return cashierDTO;

            } else {
                return null;
            }

        }
    }

    @Override
    public CashierDTO findByID(String code) throws Exception {
        try(Session session=HibernateUtil.getSessionFactory().openSession()){
            cashierRepository.setSession(session);
            session.beginTransaction();
            Cashier c=cashierRepository.findByIDS(code);
            
            CashierDTO cashierDTO=new CashierDTO(c.getCashierID(), c.getName());
            if(c!=null){
                return cashierDTO;
            }else{
                return null;
            }
        }
    }

    @Override
    public List<CashierDTO> findAll() throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            cashierRepository.setSession(session);
            session.beginTransaction();
            List<Cashier> cashiers = cashierRepository.findAll();
            session.getTransaction().commit();

            if (cashiers != null) {

                List<CashierDTO> allCashiers = new ArrayList<>();

                for (Cashier c : cashiers) {
                    CashierDTO cashierDTO =new CashierDTO(c.getCashierID(), c.getName());
                    allCashiers.add(cashierDTO);
                }

                return allCashiers;

            } else {

                return null;
            }

        }
    }
    
}

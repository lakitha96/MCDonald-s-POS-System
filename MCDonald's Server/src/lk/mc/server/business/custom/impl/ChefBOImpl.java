/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.mc.server.business.custom.impl;

import java.util.ArrayList;
import java.util.List;
import lk.mc.common.dto.ChefDTO;
import lk.mc.server.business.custom.ChefBO;
import lk.mc.server.entity.Chef;
import lk.mc.server.repository.RepositoryFactory;
import lk.mc.server.repository.custom.ChefRepository;
import lk.mc.server.resource.HibernateUtil;
import org.hibernate.Session;

/**
 *
 * @author lakitha
 */
public class ChefBOImpl implements ChefBO {

    private ChefRepository chefRepository;

    public ChefBOImpl() {
        chefRepository = (ChefRepository) RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryTypes.CHEF);
    }

    @Override
    public boolean save(ChefDTO chefDTO) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            chefRepository.setSession(session);

            session.beginTransaction();
            Chef chef = new Chef();
            chef.setChefID(chefDTO.getChefID());
            chef.setChefName(chefDTO.getChefName());

            boolean result = chefRepository.save(chef);
            session.getTransaction().commit();
            return result;
        }
    }

    @Override
    public boolean update(ChefDTO chefDTO) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            chefRepository.setSession(session);

            session.beginTransaction();

            Chef chef = new Chef();
            chef.setChefID(chefDTO.getChefID());
            chef.setChefName(chefDTO.getChefName());

            chefRepository.update(chef);
            session.getTransaction().commit();

            return true;
        } catch (Exception exp) {
            exp.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(String chefID) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            chefRepository.setSession(session);

            session.beginTransaction();

            //Customer c= customerRepository.findByID(customerID);
            Chef c = chefRepository.findByIDS(chefID);
            boolean result = false;

            if (c != null) {

                chefRepository.delete(c);
                result = true;
            }

            session.getTransaction().commit();

            return result;

        }
    }

    @Override
    public ChefDTO findByName(String name) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            chefRepository.setSession(session);

            session.beginTransaction();

            Chef c = chefRepository.findByName("from Chef where chefName='" + name + "'");

            session.getTransaction().commit();

            if (c != null) {
                ChefDTO chefDTO = new ChefDTO(c.getChefID(), c.getChefName());

                return chefDTO;

            } else {
                return null;
            }

        }
    }

    @Override
    public ChefDTO findByID(int id) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            chefRepository.setSession(session);
            session.beginTransaction();
            Chef c = chefRepository.findByID(id);

            ChefDTO chefDTO = new ChefDTO(c.getChefID(), c.getChefName());
            if (c != null) {
                return chefDTO;
            } else {
                return null;
            }
        }
    }

    @Override
    public List<ChefDTO> findAll() throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            chefRepository.setSession(session);
            session.beginTransaction();
            List<Chef> chefs = chefRepository.findAll();
            session.getTransaction().commit();

            if (chefs != null) {

                List<ChefDTO> allChefs = new ArrayList<>();

                for (Chef c : chefs) {
                    ChefDTO chefDTO = new ChefDTO(c.getChefID(), c.getChefName());
                    allChefs.add(chefDTO);
                }

                return allChefs;

            } else {

                return null;
            }

        }
    }

}

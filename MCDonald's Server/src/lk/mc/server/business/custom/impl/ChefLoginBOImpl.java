/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.mc.server.business.custom.impl;

import lk.mc.common.dto.ChefLoginDTO;
import lk.mc.server.business.custom.ChefLoginBO;
import lk.mc.server.entity.ChefLogin;
import lk.mc.server.repository.RepositoryFactory;
import lk.mc.server.repository.custom.ChefLoginRepository;
import lk.mc.server.repository.custom.ChefRepository;
import lk.mc.server.resource.HibernateUtil;
import org.hibernate.Session;

/**
 *
 * @author lakitha
 */
public class ChefLoginBOImpl implements ChefLoginBO {

    private ChefLoginRepository chefLoginRepository;

    public ChefLoginBOImpl() {
        chefLoginRepository = (ChefLoginRepository) RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryTypes.CHEFLOGIN);
    }

    @Override
    public boolean save(ChefLoginDTO chefLoginDTO) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            chefLoginRepository.setSession(session);

            session.beginTransaction();
            ChefLogin chefLogin = new ChefLogin(chefLoginDTO.getUserName(), chefLoginDTO.getPassword());

            boolean result = chefLoginRepository.save(chefLogin);
            session.getTransaction().commit();
            return result;
        }
    }

    @Override
    public boolean update(ChefLoginDTO chefLoginDTO) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            chefLoginRepository.setSession(session);

            session.beginTransaction();
            ChefLogin chefLogin = new ChefLogin(chefLoginDTO.getUserName(), chefLoginDTO.getPassword());

            chefLoginRepository.update(chefLogin);
            session.getTransaction().commit();
            return true;
        } catch (Exception exp) {
            exp.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(int chefID) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ChefLoginDTO findByName(String name) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            chefLoginRepository.setSession(session);

            session.beginTransaction();

            ChefLogin al = chefLoginRepository.findByName("from ChefLogin where userName='" + name + "'");

            session.getTransaction().commit();

            if (al != null) {
                ChefLoginDTO chefLoginDTO = new ChefLoginDTO(al.getUserName(), al.getPassword());

                return chefLoginDTO;

            } else {
                return null;
            }

        }
    }

}

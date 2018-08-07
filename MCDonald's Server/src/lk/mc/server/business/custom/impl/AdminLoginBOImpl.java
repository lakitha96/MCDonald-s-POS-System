/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.mc.server.business.custom.impl;

import lk.mc.common.dto.AdminLoginDTO;
import lk.mc.server.business.custom.AdminLoginBO;
import lk.mc.server.entity.AdminLogin;
import lk.mc.server.repository.RepositoryFactory;
import lk.mc.server.repository.custom.AdminLoginRepository;
import lk.mc.server.resource.HibernateUtil;
import org.hibernate.Session;

/**
 *
 * @author lakitha
 */
public class AdminLoginBOImpl implements AdminLoginBO {

    AdminLoginRepository adminLoginRepository;

    public AdminLoginBOImpl() {
        adminLoginRepository = (AdminLoginRepository) RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryTypes.ADMINLOGIN);
    }

    @Override
    public boolean save(AdminLoginDTO a) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            adminLoginRepository.setSession(session);

            session.beginTransaction();
            AdminLogin adminLogin = new AdminLogin();
            adminLogin.setUserName(a.getUserName());
            adminLogin.setPassword(a.getPassword());

            boolean result = adminLoginRepository.save(adminLogin);
            session.getTransaction().commit();
            return result;
        }
    }

    @Override
    public boolean update(AdminLoginDTO a) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            adminLoginRepository.setSession(session);

            session.beginTransaction();
            AdminLogin adminLogin = new AdminLogin();
            adminLogin.setUserName(a.getUserName());
            adminLogin.setPassword(a.getPassword());

            adminLoginRepository.update(adminLogin);
            session.getTransaction().commit();
            return true;
        } catch (Exception exp) {
            exp.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(int adminID) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AdminLoginDTO findByName(String name) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            adminLoginRepository.setSession(session);
        
            session.beginTransaction();
            
            AdminLogin al = adminLoginRepository.findByName("from AdminLogin where userName='"+name+"'");

            session.getTransaction().commit();

            if (al != null) {
                AdminLoginDTO adminLoginDTO=new AdminLoginDTO(al.getUserName(), al.getPassword());

                return adminLoginDTO;

            } else {
                return null;
            }

        }
    }

}

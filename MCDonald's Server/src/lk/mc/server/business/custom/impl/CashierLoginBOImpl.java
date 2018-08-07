/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.mc.server.business.custom.impl;

import lk.mc.common.dto.CashierLoginDTO;
import lk.mc.server.business.custom.CashierLoginBO;
import lk.mc.server.entity.CashierLogin;
import lk.mc.server.repository.RepositoryFactory;
import lk.mc.server.repository.custom.CashierLoginRepository;
import lk.mc.server.resource.HibernateUtil;
import org.hibernate.Session;

/**
 *
 * @author lakitha
 */
public class CashierLoginBOImpl implements CashierLoginBO {

    private CashierLoginRepository cashierLoginRepository;

    public CashierLoginBOImpl() {
        cashierLoginRepository = (CashierLoginRepository) RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryTypes.CASHIERLOGIN);

    }

    @Override
    public boolean save(CashierLoginDTO cashierLogin) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            cashierLoginRepository.setSession(session);
            session.beginTransaction();

            CashierLogin login = new CashierLogin(cashierLogin.getUserName(), cashierLogin.getPassword());
            boolean result = cashierLoginRepository.save(login);
            session.getTransaction().commit();
            return result;
        }
    }

    @Override
    public boolean update(CashierLoginDTO cashierLogin) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(int cashierID) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CashierLoginDTO findByName(String name) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            cashierLoginRepository.setSession(session);
            session.beginTransaction();

            CashierLogin cl = cashierLoginRepository.findByName("from CashierLogin where userName='" + name + "'");

            session.getTransaction().commit();

            if (cl != null) {
                CashierLoginDTO cashierLoginDTO = new CashierLoginDTO(cl.getUserName(), cl.getPassword());

                return cashierLoginDTO;

            } else {
                return null;
            }

        }
    }

}

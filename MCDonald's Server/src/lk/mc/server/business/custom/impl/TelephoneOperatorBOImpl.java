/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.mc.server.business.custom.impl;

import java.util.ArrayList;
import java.util.List;
import lk.mc.common.dto.TelephoneOperatorDTO;
import lk.mc.server.business.custom.TelephoneOperatorBO;
import lk.mc.server.entity.TelephoneOperator;
import lk.mc.server.repository.RepositoryFactory;
import lk.mc.server.repository.custom.TelephoneOperatorRepository;
import lk.mc.server.resource.HibernateUtil;
import org.hibernate.Session;

/**
 *
 * @author lakitha
 */
public class TelephoneOperatorBOImpl implements TelephoneOperatorBO {

    private TelephoneOperatorRepository operatorRepository;

    public TelephoneOperatorBOImpl() {
        operatorRepository = (TelephoneOperatorRepository) RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryTypes.TELEPHONEOPERATOR);
    }

    @Override
    public boolean save(TelephoneOperatorDTO operatorDTO) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            operatorRepository.setSession(session);

            session.beginTransaction();
            TelephoneOperator operator = new TelephoneOperator(operatorDTO.getTID(), operatorDTO.getTeleOPName());

            boolean result = operatorRepository.save(operator);
            session.getTransaction().commit();
            return result;
        }
    }

    @Override
    public boolean update(TelephoneOperatorDTO operatorDTO) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(String topID) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TelephoneOperatorDTO findByName(String name) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TelephoneOperatorDTO findByID(int id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TelephoneOperatorDTO> findAll() throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            operatorRepository.setSession(session);
            session.beginTransaction();
            List<TelephoneOperator> allOp = operatorRepository.findAll();
            session.getTransaction().commit();

            if (allOp != null) {
                List<TelephoneOperatorDTO> allOperators = new ArrayList<>();

                for (TelephoneOperator operator : allOp) {
                    TelephoneOperatorDTO operatorDTO = new TelephoneOperatorDTO(operator.getTID(), operator.getTeleOPName());
                    allOperators.add(operatorDTO);
                }
                return allOperators;
            } else {
                return null;
            }
        }
    }

}

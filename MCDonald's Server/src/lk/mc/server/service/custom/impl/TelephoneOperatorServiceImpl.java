/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.mc.server.service.custom.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import lk.mc.common.dto.TelephoneOperatorDTO;
import lk.mc.common.service.custom.TelephoneOperatorService;
import lk.mc.server.business.BOFactory;
import lk.mc.server.business.custom.TelephoneOperatorBO;

/**
 *
 * @author lakitha
 */
public class TelephoneOperatorServiceImpl extends UnicastRemoteObject implements TelephoneOperatorService{
    
    private TelephoneOperatorBO operatorBO;
    
    public TelephoneOperatorServiceImpl() throws RemoteException{
        operatorBO=(TelephoneOperatorBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.TELEOPERATOR);
    }
    
    

    @Override
    public boolean save(TelephoneOperatorDTO operatorDTO) throws Exception {
        return operatorBO.save(operatorDTO);
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
        return operatorBO.findAll();
    }
    
}

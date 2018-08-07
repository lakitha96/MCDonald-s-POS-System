/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.mc.common.service.custom;

import java.util.List;
import lk.mc.common.dto.TelephoneOperatorDTO;
import lk.mc.common.service.SuperService;

/**
 *
 * @author lakitha
 */
public interface TelephoneOperatorService extends SuperService {

    public boolean save(TelephoneOperatorDTO operatorDTO) throws Exception;

    public boolean update(TelephoneOperatorDTO operatorDTO) throws Exception;

    public boolean delete(String topID) throws Exception;

    public TelephoneOperatorDTO findByName(String name) throws Exception;

    public TelephoneOperatorDTO findByID(int id) throws Exception;

    public List<TelephoneOperatorDTO> findAll() throws Exception;

}

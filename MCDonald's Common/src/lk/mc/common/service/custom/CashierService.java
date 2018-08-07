/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.mc.common.service.custom;

import java.util.List;
import lk.mc.common.dto.CashierDTO;
import lk.mc.common.service.SuperService;

/**
 *
 * @author lakitha
 */
public interface CashierService extends SuperService {

    public boolean save(CashierDTO cashier) throws Exception;

    public boolean update(CashierDTO cashierDTO) throws Exception;

    public boolean delete(String cashierID) throws Exception;

    public CashierDTO findByName(String name) throws Exception;

    public CashierDTO findByID(String cashierID) throws Exception;

    public List<CashierDTO> findAll() throws Exception;
}

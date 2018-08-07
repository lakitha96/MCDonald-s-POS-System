/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.mc.server.business.custom;

import java.util.List;
import lk.mc.common.dto.CashierDTO;
import lk.mc.server.business.SuperBO;

/**
 *
 * @author lakitha
 */
public interface CashierBO extends SuperBO{
    public boolean save(CashierDTO cashier) throws Exception;

    public boolean update(CashierDTO cashierDTO) throws Exception;

    public boolean delete(String cashierID) throws Exception;

    public CashierDTO findByName(String name) throws Exception;

    public CashierDTO findByID(String cashierID) throws Exception;

    public List<CashierDTO> findAll() throws Exception;
}

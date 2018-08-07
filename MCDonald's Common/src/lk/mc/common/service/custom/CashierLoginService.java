/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.mc.common.service.custom;

import lk.mc.common.dto.CashierLoginDTO;
import lk.mc.common.service.SuperService;

/**
 *
 * @author lakitha
 */
public interface CashierLoginService extends SuperService {

    public boolean save(CashierLoginDTO cashierLogin) throws Exception;

    public boolean update(CashierLoginDTO cashierLogin) throws Exception;

    public boolean delete(int cashierID) throws Exception;

    public CashierLoginDTO findByName(String name) throws Exception;
}

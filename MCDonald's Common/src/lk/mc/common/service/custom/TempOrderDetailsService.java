/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.mc.common.service.custom;

import java.util.List;
import lk.mc.common.dto.TempOrderDetailsDTO;
import lk.mc.common.service.SuperService;

/**
 *
 * @author lakitha
 */
public interface TempOrderDetailsService extends SuperService {

    public boolean update(TempOrderDetailsDTO tempOrderDetailsDTO) throws Exception;

    public boolean delete(int oid, int code) throws Exception;

    public List<TempOrderDetailsDTO> allTempDetails() throws Exception;
}

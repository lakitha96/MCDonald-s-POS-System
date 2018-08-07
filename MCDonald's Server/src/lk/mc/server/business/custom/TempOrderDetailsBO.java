/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.mc.server.business.custom;

import java.util.List;
import lk.mc.common.dto.TempOrderDetailsDTO;
import lk.mc.server.business.SuperBO;

/**
 *
 * @author lakitha
 */
public interface TempOrderDetailsBO extends SuperBO {

    public boolean update(TempOrderDetailsDTO tempOrderDetailsDTO) throws Exception;

    public boolean delete(int oid, int code) throws Exception;

    public List<TempOrderDetailsDTO> allTempDetails() throws Exception;
}

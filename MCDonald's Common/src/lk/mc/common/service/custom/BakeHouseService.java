/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.mc.common.service.custom;

import java.util.List;
import lk.mc.common.dto.BakeHouseDTO;
import lk.mc.common.service.SuperService;

/**
 *
 * @author lakitha
 */
public interface BakeHouseService extends SuperService{
    public boolean save(BakeHouseDTO bakeHouseDTO) throws Exception;

    public boolean delete(String BID) throws Exception;

    public List findByDate(String date) throws Exception;

    public BakeHouseDTO findByID(String BID) throws Exception;

    public List<BakeHouseDTO> findAll() throws Exception;
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.mc.common.service.custom;

import java.util.List;
import lk.mc.common.dto.ChefDTO;
import lk.mc.common.service.SuperService;

/**
 *
 * @author lakitha
 */
public interface ChefService extends SuperService{
    public boolean save(ChefDTO chefDTO) throws Exception;

    public boolean update(ChefDTO chefDTO) throws Exception;

    public boolean delete(String chefID) throws Exception;

    public ChefDTO findByName(String name) throws Exception;

    public ChefDTO findByID(int id) throws Exception;

    public List<ChefDTO> findAll() throws Exception;
}

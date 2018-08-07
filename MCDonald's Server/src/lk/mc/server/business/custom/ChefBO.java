/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.mc.server.business.custom;

import java.util.List;
import lk.mc.common.dto.ChefDTO;
import lk.mc.server.business.SuperBO;

/**
 *
 * @author lakitha
 */
public interface ChefBO extends SuperBO{

    public boolean save(ChefDTO chefDTO) throws Exception;

    public boolean update(ChefDTO chefDTO) throws Exception;

    public boolean delete(String chefID) throws Exception;

    public ChefDTO findByName(String name) throws Exception;

    public ChefDTO findByID(int id) throws Exception;

    public List<ChefDTO> findAll() throws Exception;
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.mc.server.business.custom;

import lk.mc.common.dto.ChefDTO;
import lk.mc.common.dto.ChefLoginDTO;
import lk.mc.server.business.SuperBO;

/**
 *
 * @author lakitha
 */
public interface ChefLoginBO extends SuperBO {

    public boolean save(ChefLoginDTO chefLoginDTO) throws Exception;

    public boolean update(ChefLoginDTO chefLoginDTO) throws Exception;

    public boolean delete(int chefID) throws Exception;

    public ChefLoginDTO findByName(String name) throws Exception;
}

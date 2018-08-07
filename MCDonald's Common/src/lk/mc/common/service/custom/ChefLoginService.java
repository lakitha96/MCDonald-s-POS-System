/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.mc.common.service.custom;

import lk.mc.common.dto.ChefLoginDTO;
import lk.mc.common.service.SuperService;

/**
 *
 * @author lakitha
 */
public interface ChefLoginService extends SuperService {

    public boolean save(ChefLoginDTO chefLoginDTO) throws Exception;

    public boolean update(ChefLoginDTO chefLoginDTO) throws Exception;

    public boolean delete(int chefID) throws Exception;

    public ChefLoginDTO findByName(String name) throws Exception;

}

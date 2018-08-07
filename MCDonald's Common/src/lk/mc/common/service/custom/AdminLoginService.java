/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.mc.common.service.custom;

import lk.mc.common.dto.AdminLoginDTO;
import lk.mc.common.service.SuperService;

/**
 *
 * @author lakitha
 */
public interface AdminLoginService extends SuperService {

    public boolean save(AdminLoginDTO a) throws Exception;

    public boolean update(AdminLoginDTO a) throws Exception;

    public boolean delete(int adminID) throws Exception;

    public AdminLoginDTO findByName(String name) throws Exception;
}

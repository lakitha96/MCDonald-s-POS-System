/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.mc.server.business.custom;

import lk.mc.common.dto.AdminLoginDTO;
import lk.mc.server.business.SuperBO;

/**
 *
 * @author lakitha
 */
public interface AdminLoginBO extends SuperBO {

    public boolean save(AdminLoginDTO a) throws Exception;

    public boolean update(AdminLoginDTO a) throws Exception;

    public boolean delete(int adminID) throws Exception;

    public AdminLoginDTO findByName(String name) throws Exception;
}

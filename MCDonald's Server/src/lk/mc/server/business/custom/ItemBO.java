/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.mc.server.business.custom;

import java.util.List;
import lk.mc.common.dto.ItemDTO;
import lk.mc.server.business.SuperBO;

/**
 *
 * @author lakitha
 */
public interface ItemBO extends SuperBO{
    
    public boolean saveItem(ItemDTO itemDTO)throws Exception;
    
    public boolean delete(int code) throws Exception;

    public boolean update(ItemDTO i) throws Exception;
    
    public ItemDTO findByName(String name) throws Exception;
    
    public ItemDTO findByID(int code) throws Exception;
    
    public List<ItemDTO> findAll() throws Exception;

}

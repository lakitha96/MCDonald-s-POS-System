/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.mc.common.service.custom;

import java.util.List;
import lk.mc.common.dto.ItemDTO;
import lk.mc.common.service.SuperService;

/**
 *
 * @author lakitha
 */
public interface ItemService extends SuperService {

    public boolean saveItem(ItemDTO itemDTO) throws Exception;

    public boolean delete(int code) throws Exception;

    public boolean update(ItemDTO i) throws Exception;

    public ItemDTO findByName(String name) throws Exception;

    public List<ItemDTO> findAll() throws Exception;

}

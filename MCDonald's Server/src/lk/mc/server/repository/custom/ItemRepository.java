/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.mc.server.repository.custom;

import lk.mc.common.dto.ItemDTO;
import lk.mc.server.entity.Item;
import lk.mc.server.repository.SuperRepository;
import org.hibernate.Session;

/**
 *
 * @author lakitha
 */
public interface ItemRepository extends SuperRepository<Item, Integer>{
//    public Item findByName(String name) throws Exception;
}

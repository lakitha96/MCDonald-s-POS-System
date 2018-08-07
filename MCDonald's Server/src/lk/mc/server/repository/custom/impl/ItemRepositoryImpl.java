/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.mc.server.repository.custom.impl;

import java.io.Serializable;
import java.util.List;
import lk.mc.common.dto.ItemDTO;
import lk.mc.server.entity.Item;
import lk.mc.server.repository.SuperRepositoryImpl;
import lk.mc.server.repository.custom.ItemRepository;
import org.hibernate.Session;

/**
 *
 * @author lakitha
 */
public class ItemRepositoryImpl extends SuperRepositoryImpl<Item, Integer> implements ItemRepository {

    //private Session session;

    public ItemRepositoryImpl() {
    }


//    public Item findByName(String name) throws Exception {
//        return (Item) session.createQuery("FROM Item where nameItem where name='"+name+"'").list().get(0);
//    }
//
//    @Override
//    public void setSession(Session session) throws Exception {
//        this.session=session;
//    }

//    @Override
//    public Item findByName(String name) throws Exception {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

}

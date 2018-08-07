/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.mc.server.business.custom.impl;

import java.util.ArrayList;
import java.util.List;
import lk.mc.common.dto.ItemDTO;
import lk.mc.server.business.custom.ItemBO;
import lk.mc.server.entity.Item;
import lk.mc.server.repository.RepositoryFactory;
import lk.mc.server.repository.custom.ItemRepository;
import lk.mc.server.resource.HibernateUtil;
import org.hibernate.Session;

/**
 *
 * @author lakitha
 */
public class ItemBOImpl implements ItemBO {

    private ItemRepository itemRepository;

    public ItemBOImpl() {
        itemRepository = (ItemRepository) RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryTypes.ITEM);
    }

    @Override
    public boolean saveItem(ItemDTO i) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            itemRepository.setSession(session);

            session.beginTransaction();
            Item item = new Item(i.getCode(),
                    i.getName(),
                    i.getPrice(),
                    i.getDescription(),
                    i.getCategory(),
                    i.getQtyAV(),
                    i.getDiscount(),
                    i.getValidDate(),
                    i.getImagePath());

            boolean result = itemRepository.save(item);
            session.getTransaction().commit();
            return result;
        }
    }

    @Override
    public boolean delete(int code) throws Exception {
        try(Session session=HibernateUtil.getSessionFactory().openSession()){
            itemRepository.setSession(session);
            
            session.beginTransaction();

            Item item= itemRepository.findByID(code);
            boolean result = false;

            if (item != null) {

                itemRepository.delete(item);
                result=true;
            }
            
            session.getTransaction().commit();

            return result;
            
        }
    }

    @Override
    public boolean update(ItemDTO i) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            itemRepository.setSession(session);
            
            session.beginTransaction();
            
            
            
            Item item = new Item(i.getCode(),
                    i.getName(),
                    i.getPrice(),
                    i.getDescription(),
                    i.getCategory(),
                    i.getQtyAV(),
                    i.getDiscount(),
                    i.getValidDate(),
                    i.getImagePath());
            itemRepository.update(item);
            session.getTransaction().commit();
            
            return true;
        }catch(Exception exp){
            exp.printStackTrace();
            return false;
        }
    }

    @Override
    public ItemDTO findByName(String name) throws Exception {
        System.out.println("@findByName");
        
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            itemRepository.setSession(session);
        
            session.beginTransaction();
            
            Item item = itemRepository.findByName("from Item where name='"+name+"'");
            System.out.println("@findByName--"+item.getName());

            session.getTransaction().commit();

            if (item != null) {
                ItemDTO itemDTO = new ItemDTO(item.getCode(),
                        item.getName(),
                        item.getPrice(),
                        item.getDescription(),
                        item.getCategory(),
                        item.getQtyAV(),
                        item.getDiscount(),
                        item.getValidDate(),
                        item.getImagePath());
                System.err.println(item.getImagePath());

                return itemDTO;

            } else {
                return null;
            }

        }
    }

    @Override
    public List<ItemDTO> findAll() throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            //1
            itemRepository.setSession(session);
            //2
            session.beginTransaction();
            //3
            List<Item> items = itemRepository.findAll();
            //4
            session.getTransaction().commit();

            if (items != null) {

                List<ItemDTO> allItems = new ArrayList<>();

                for (Item item : items) {
                    ItemDTO itemDTO = new ItemDTO(item.getCode(),
                            item.getName(),
                            item.getPrice(),
                            item.getDescription(),
                            item.getCategory(),
                            item.getQtyAV(),
                            item.getDiscount(),
                            item.getValidDate(),
                            item.getImagePath());
                    allItems.add(itemDTO);
                }

                return allItems;

            } else {

                return null;
            }

        }
    }

    @Override
    public ItemDTO findByID(int code) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            itemRepository.setSession(session);
        
            session.beginTransaction();
            
            Item item = itemRepository.findByID(code);

            session.getTransaction().commit();

            if (item != null) {
                ItemDTO itemDTO = new ItemDTO(item.getCode(),
                        item.getName(),
                        item.getPrice(),
                        item.getDescription(),
                        item.getCategory(),
                        item.getQtyAV(),
                        item.getDiscount(),
                        item.getValidDate(),
                        item.getImagePath());
                System.err.println(item.getImagePath());

                return itemDTO;

            } else {
                return null;
            }

        }
    }

}

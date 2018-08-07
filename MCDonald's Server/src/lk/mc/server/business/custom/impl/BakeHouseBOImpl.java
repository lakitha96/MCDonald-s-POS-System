/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.mc.server.business.custom.impl;

import java.util.ArrayList;
import java.util.List;
import lk.mc.common.dto.BakeHouseDTO;
import lk.mc.common.dto.ChefDTO;
import lk.mc.common.dto.ItemDTO;
import lk.mc.server.business.custom.BakeHouseBO;
import lk.mc.server.entity.BakeHouse;
import lk.mc.server.entity.Chef;
import lk.mc.server.repository.RepositoryFactory;
import lk.mc.server.repository.custom.BakeHouseRepository;
import lk.mc.server.repository.custom.ChefRepository;
import lk.mc.server.repository.custom.ItemRepository;
import lk.mc.server.resource.HibernateUtil;
import org.hibernate.Session;

/**
 *
 * @author lakitha
 */
public class BakeHouseBOImpl implements BakeHouseBO {

    private BakeHouseRepository bakeHouseRepository;
    private ItemRepository itemRepository;
    private ChefRepository chefRepository;

    public BakeHouseBOImpl() {
        bakeHouseRepository = (BakeHouseRepository) RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryTypes.BAKEHOUSE);
        itemRepository = (ItemRepository) RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryTypes.ITEM);
        chefRepository = (ChefRepository) RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryTypes.CHEF);
    }

    @Override
    public boolean save(BakeHouseDTO b) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            bakeHouseRepository.setSession(session);

            session.beginTransaction();
            BakeHouse bakeHouse = new BakeHouse();
            bakeHouse.setDate(b.getDate());
            bakeHouse.setPreparedQty(b.getPreparedQty());
            bakeHouse.setProcessingTime(b.getProcessingTime());
            itemRepository.setSession(session);
            bakeHouse.setItem(itemRepository.findByName("from Item where name='" + b.getItemDTO().getName() + "'"));
            chefRepository.setSession(session);
            bakeHouse.setChef(chefRepository.findByID(b.getChefDTO().getChefID()));

            boolean result = bakeHouseRepository.save(bakeHouse);
            session.getTransaction().commit();
            return result;
      

        }
    }

    @Override
    public boolean delete(String BID) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List findByDate(String date) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            bakeHouseRepository.setSession(session);
            session.beginTransaction();
            List<BakeHouse> allDetails = bakeHouseRepository.findByDate("from BakeHouse where date='" + date + "'");
            session.getTransaction().commit();

            if (allDetails != null) {

                List<BakeHouseDTO> allDetailsDTO = new ArrayList<>();
                for (BakeHouse b : allDetails) {
                    BakeHouseDTO bakeHouseDTO = new BakeHouseDTO();
                    ChefDTO chefDTO = new ChefDTO(b.getChef().getChefID(), b.getChef().getChefName());
                    bakeHouseDTO.setChefDTO(chefDTO);

                    ItemDTO itemDTO = new ItemDTO(b.getItem().getCode(),
                            b.getItem().getName(),
                            b.getItem().getPrice(),
                            b.getItem().getDescription(),
                            b.getItem().getCategory(),
                            b.getItem().getQtyAV(),
                            b.getItem().getDiscount(),
                            b.getItem().getValidDate(),
                            b.getItem().getImagePath());
                    System.err.println("@BakeHouseBO ...."+bakeHouseDTO.getDate());
                    bakeHouseDTO.setItemDTO(itemDTO);
                    bakeHouseDTO.setDate(b.getDate());
                    bakeHouseDTO.setPreparedQty(b.getPreparedQty());
                    bakeHouseDTO.setProcessingTime(b.getProcessingTime());
                    allDetailsDTO.add(bakeHouseDTO);
                }

                return allDetailsDTO;

            } else {

                return null;
            }

        }
    }

    @Override
    public BakeHouseDTO findByID(String BID) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<BakeHouseDTO> findAll() throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            bakeHouseRepository.setSession(session);
            session.beginTransaction();
            List<BakeHouse> allDetails = bakeHouseRepository.findAll();
            session.getTransaction().commit();

            if (allDetails != null) {

                List<BakeHouseDTO> allDetailsDTO = new ArrayList<>();
                for (BakeHouse b : allDetails) {
                    BakeHouseDTO bakeHouseDTO = new BakeHouseDTO();
                    ChefDTO chefDTO = new ChefDTO(b.getChef().getChefID(), b.getChef().getChefName());
                    bakeHouseDTO.setChefDTO(chefDTO);

                    ItemDTO itemDTO = new ItemDTO(b.getItem().getCode(),
                            b.getItem().getName(),
                            b.getItem().getPrice(),
                            b.getItem().getDescription(),
                            b.getItem().getCategory(),
                            b.getItem().getQtyAV(),
                            b.getItem().getDiscount(),
                            b.getItem().getValidDate(),
                            b.getItem().getImagePath());
                    bakeHouseDTO.setItemDTO(itemDTO);
                    bakeHouseDTO.setDate(b.getDate());
                    bakeHouseDTO.setPreparedQty(b.getPreparedQty());
                    bakeHouseDTO.setProcessingTime(b.getProcessingTime());
                    allDetailsDTO.add(bakeHouseDTO);
                }

                return allDetailsDTO;

            } else {

                return null;
            }

        }
    }

}

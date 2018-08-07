/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.mc.server.business.custom.impl;

import java.util.ArrayList;
import java.util.List;
import lk.mc.common.dto.TempOrderDetailsDTO;
import lk.mc.server.business.custom.TempOrderDetailsBO;
import lk.mc.server.entity.Chef;
import lk.mc.server.entity.Item;
import lk.mc.server.entity.OrderDetails;
import lk.mc.server.entity.OrderDetails_PK;
import lk.mc.server.entity.Orders;
import lk.mc.server.entity.TempOrderDetails;
import lk.mc.server.repository.RepositoryFactory;
import lk.mc.server.repository.custom.ChefRepository;
import lk.mc.server.repository.custom.ItemRepository;
import lk.mc.server.repository.custom.OrderDetailsRepository;
import lk.mc.server.repository.custom.OrderRepository;
import lk.mc.server.repository.custom.TempOrderDetailsRepository;
import lk.mc.server.resource.HibernateUtil;
import org.hibernate.Session;

/**
 *
 * @author lakitha
 */
public class TempOrderDetailsBOImpl implements TempOrderDetailsBO {
    
    private TempOrderDetailsRepository tempDetails;
    private OrderDetailsRepository orderDetailsRepository;
    private OrderRepository orderRepository;
    private ItemRepository itemRepository;
    private ChefRepository chefRepository;
    
    public TempOrderDetailsBOImpl() {
        tempDetails = (TempOrderDetailsRepository) RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryTypes.TEMPORDERDETAILS);
        orderDetailsRepository = (OrderDetailsRepository) RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryTypes.ORDERDETAILS);
        orderRepository = (OrderRepository) RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryTypes.ORDER);
        itemRepository = (ItemRepository) RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryTypes.ITEM);
        chefRepository = (ChefRepository) RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryTypes.CHEF);
    }
    
    @Override
    public boolean update(TempOrderDetailsDTO tempOrderDetailsDTO) throws Exception {
        
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            
            session.beginTransaction();
            
            itemRepository.setSession(session);
            Item item = itemRepository.findByName("from Item where name='" + tempOrderDetailsDTO.getItemName() + "'");
            chefRepository.setSession(session);
            Chef chef = chefRepository.findByID(tempOrderDetailsDTO.getChefDTO().getChefID());
            orderRepository.setSession(session);
            Orders o = orderRepository.findByID(tempOrderDetailsDTO.getOid());
            
            Orders orders = new Orders();
            
            orders.setOID(o.getOID());
            orders.setDate(o.getDate());
            orders.setOrderType(o.getOrderType());
            orders.setOrderStatus(tempOrderDetailsDTO.getOrderStatus());
            orders.setCustomer(o.getCustomer());
            
            orders.setTelephoneOperator(o.getTelephoneOperator());
            
            orders.setCashier(o.getCashier());
            
            orderRepository.update(orders);
            
            orderDetailsRepository.setSession(session);
            OrderDetails detailsEn = new OrderDetails(tempOrderDetailsDTO.getOrder_QTY(),
                    tempOrderDetailsDTO.getItemName(),
                    item.getPrice(),
                    chef.getChefName(),
                    orders,
                    item,
                    chef,
                    new OrderDetails_PK(orders.getOID(), item.getCode()));
            
            orderDetailsRepository.update(detailsEn);
            
            tempDetails.setSession(session);
            TempOrderDetails tempOrderDetails=new TempOrderDetails();
            tempOrderDetails.setOrderDetails_PK(new OrderDetails_PK(tempOrderDetailsDTO.getOid(), tempOrderDetailsDTO.getCode()));
            tempDetails.delete(tempOrderDetails);
            //tempDetails.queryText("TempOrderDetails where oid='"+tempOrderDetailsDTO.getOid()+"' and code='"+tempOrderDetailsDTO.getCode()+"'");
            
            session.getTransaction().commit();
            
            return true;
            
        } catch (Exception exp) {
            exp.printStackTrace();
            return false;
        }
        
    }
    
    @Override
    public boolean delete(int oid, int code) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public List<TempOrderDetailsDTO> allTempDetails() throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tempDetails.setSession(session);
            session.beginTransaction();
            List<TempOrderDetails> tempDetails = this.tempDetails.findAll();
            session.getTransaction().commit();
            
            if (tempDetails != null) {
                
                List<TempOrderDetailsDTO> allTempDetailsDTO = new ArrayList<>();
                
                for (TempOrderDetails t : tempDetails) {
                    TempOrderDetailsDTO detailsDTO = new TempOrderDetailsDTO(t.getOrders().getOID(),
                            t.getItem().getCode(),
                            t.getOrder_QTY(),
                            t.getItemName(),
                            t.getItem().getDescription(),
                            t.getItem().getImagePath(),
                            t.getPrice());
                    allTempDetailsDTO.add(detailsDTO);
                }
                
                return allTempDetailsDTO;
                
            } else {
                
                return null;
            }
            
        }
        
    }
    
}

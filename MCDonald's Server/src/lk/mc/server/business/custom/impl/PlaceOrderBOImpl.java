/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.mc.server.business.custom.impl;

import java.util.ArrayList;
import java.util.List;
import lk.mc.common.dto.ItemDTO;
import lk.mc.common.dto.OrderDetailsDTO;
import lk.mc.common.dto.OrdersDTO;
import lk.mc.server.business.custom.PlaceOrderBO;
import lk.mc.server.entity.Cashier;
import lk.mc.server.entity.Chef;
import lk.mc.server.entity.Customer;
import lk.mc.server.entity.Item;
import lk.mc.server.entity.OrderDetails;
import lk.mc.server.entity.OrderDetails_PK;
import lk.mc.server.entity.Orders;
import lk.mc.server.entity.TelephoneOperator;
import lk.mc.server.entity.TempOrderDetails;
import lk.mc.server.repository.RepositoryFactory;
import lk.mc.server.repository.custom.ItemRepository;
import lk.mc.server.repository.custom.OrderDetailsRepository;
import lk.mc.server.resource.HibernateUtil;
import org.hibernate.Session;
import lk.mc.server.repository.custom.OrderRepository;
import lk.mc.server.repository.custom.TempOrderDetailsRepository;

/**
 *
 * @author lakitha
 */
public class PlaceOrderBOImpl implements PlaceOrderBO {

    private OrderRepository orderRepository;
    private ItemRepository itemRepository;
    private OrderDetailsRepository orderDetailsRepository;
    private TempOrderDetailsRepository tempDetailsRepository;
    private boolean isOrderAdded = false;
    private boolean isOrderDetailsAdded = false;

    public PlaceOrderBOImpl() {
        orderRepository = (OrderRepository) RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryTypes.ORDER);
        orderDetailsRepository = (OrderDetailsRepository) RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryTypes.ORDERDETAILS);
        itemRepository = (ItemRepository) RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryTypes.ITEM);
        tempDetailsRepository = (TempOrderDetailsRepository) RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryTypes.TEMPORDERDETAILS);
    }

    @Override
    public boolean saveOrder(OrdersDTO o, List<OrderDetailsDTO> orderDetailsDTOs, List<ItemDTO> allItems) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            orderRepository.setSession(session);
            session.beginTransaction();

            Customer customer = new Customer(o.getCustomerDTO().getCID(),
                    o.getCustomerDTO().getName(),
                    o.getCustomerDTO().getAddress(),
                    o.getCustomerDTO().getMobileNumber());

            Orders orders = new Orders();

            orders.setOID(o.getOID());
            orders.setDate(o.getDate());
            orders.setOrderType(o.getOrderType());
            orders.setOrderStatus(o.getOrderStatus());
            orders.setCustomer(customer);

            if (!o.getTelephoneOperatorDTO().getTeleOPName().equalsIgnoreCase("null")) {
                TelephoneOperator telephoneOperator = new TelephoneOperator(o.getTelephoneOperatorDTO().getTID(),
                        o.getTelephoneOperatorDTO().getTeleOPName());
                orders.setTelephoneOperator(telephoneOperator);
            } else if (!o.getCashierDTO().getName().equalsIgnoreCase("name")) {
                Cashier cashier = new Cashier(o.getCashierDTO().getCashierID(),
                        o.getCashierDTO().getName());
                orders.setCashier(cashier);
            }

            isOrderAdded = orderRepository.save(orders);

            ///////////////////////////////////
            orderDetailsRepository.setSession(session);
            tempDetailsRepository.setSession(session);
            itemRepository.setSession(session);

            OrderDetails details = new OrderDetails();
            TempOrderDetails tempDetails = new TempOrderDetails();

            details.setOrders(orders);
            tempDetails.setOrders(orders);
            Item oldItem = new Item();
            for (ItemDTO i : allItems) {

                oldItem = itemRepository.findByID(i.getCode());

                int updatedQTY = (oldItem.getQtyAV() - i.getQtyAV());

                Item item = new Item();
                item.setCode(i.getCode());
                item.setName(i.getName());
                item.setPrice(i.getPrice());
                item.setQtyAV(updatedQTY);
                item.setValidDate(i.getValidDate());
                item.setDiscount(i.getDiscount());
                item.setDescription(oldItem.getDescription());
                item.setCategory(oldItem.getCategory());
                item.setImagePath(oldItem.getImagePath());

                details.setItemName(i.getName());
                details.setItem(item);
                details.setOrder_QTY(i.getQtyAV());
                details.setPrice(i.getPrice());
                details.setOrderDetails_PK(new OrderDetails_PK(orders.getOID(), i.getCode()));
                System.err.println(i.getName());

                tempDetails.setItemName(i.getName());
                tempDetails.setItem(item);
                tempDetails.setOrder_QTY(i.getQtyAV());
                tempDetails.setPrice(i.getPrice());
                tempDetails.setOrderDetails_PK(new OrderDetails_PK(orders.getOID(), i.getCode()));
                
                isOrderDetailsAdded = orderDetailsRepository.save(details) && tempDetailsRepository.save(tempDetails);

            }

            boolean b = false;
            System.err.println(isOrderAdded + ":DDDD" + isOrderDetailsAdded);
            if (isOrderAdded && isOrderDetailsAdded) {
                System.err.println("Sucess");
                session.getTransaction().commit();

                b = true;
            } else {
                System.err.println("Failed");
                session.getTransaction().rollback();
            }

            
            return b;
        }
    }

    @Override
    public int getLastOrderID() throws Exception {
        try(Session session=HibernateUtil.getSessionFactory().openSession()){
            orderRepository.setSession(session);
            session.beginTransaction();
            
            int id=orderRepository.queryText("SELECT count(*) from orders");
            System.err.println(id+"@plBO");
            session.getTransaction().commit();
            return id;
            
        }
    }

}

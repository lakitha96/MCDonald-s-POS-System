/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.mc.server.service.custom.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import lk.mc.common.dto.ItemDTO;
import lk.mc.common.dto.OrderDetailsDTO;
import lk.mc.common.dto.OrdersDTO;
import lk.mc.common.service.custom.PlaceOrderService;
import lk.mc.server.business.BOFactory;
import lk.mc.server.business.custom.PlaceOrderBO;

/**
 *
 * @author lakitha
 */
public class PlaceOrderServiceImpl extends UnicastRemoteObject implements PlaceOrderService{
    
    private PlaceOrderBO placeOrderBO;
    
    public PlaceOrderServiceImpl() throws RemoteException{
        placeOrderBO=(PlaceOrderBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.PLACEORDER);
    }

    @Override
    public boolean saveOrder(OrdersDTO ordersDTO, List<OrderDetailsDTO> orderDetailsDTOs, List<ItemDTO> itemDTOs) throws Exception {
        return placeOrderBO.saveOrder(ordersDTO, orderDetailsDTOs, itemDTOs);
    }

    @Override
    public int getLastOrderID() throws Exception {
        return placeOrderBO.getLastOrderID();
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.mc.server.business.custom;

import java.util.List;
import lk.mc.common.dto.ItemDTO;
import lk.mc.common.dto.OrderDetailsDTO;
import lk.mc.common.dto.OrdersDTO;
import lk.mc.server.business.SuperBO;

/**
 *
 * @author lakitha
 */
public interface PlaceOrderBO extends SuperBO {

    public boolean saveOrder(OrdersDTO ordersDTO, List<OrderDetailsDTO> orderDetailsDTOs, List<ItemDTO> itemDTO) throws Exception;

    public int getLastOrderID() throws Exception;
}

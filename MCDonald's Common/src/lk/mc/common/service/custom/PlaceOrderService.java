/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.mc.common.service.custom;

import java.util.List;
import lk.mc.common.dto.ItemDTO;
import lk.mc.common.dto.OrderDetailsDTO;
import lk.mc.common.dto.OrdersDTO;
import lk.mc.common.service.SuperService;

/**
 *
 * @author lakitha
 */
public interface PlaceOrderService extends SuperService {

    public boolean saveOrder(OrdersDTO ordersDTO, List<OrderDetailsDTO> orderDetailsDTOs, List<ItemDTO> itemDTO) throws Exception;

    public int getLastOrderID() throws Exception;
}

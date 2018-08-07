/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.mc.server.repository.custom;

import java.util.List;
import lk.mc.common.dto.ItemDTO;
import lk.mc.common.dto.OrderDetailsDTO;
import lk.mc.common.dto.OrdersDTO;
import lk.mc.server.entity.Item;
import lk.mc.server.entity.OrderDetails;
import lk.mc.server.entity.Orders;
import lk.mc.server.repository.SuperRepository;

/**
 *
 * @author lakitha
 */
public interface OrderRepository extends SuperRepository<Orders, Integer> {
}

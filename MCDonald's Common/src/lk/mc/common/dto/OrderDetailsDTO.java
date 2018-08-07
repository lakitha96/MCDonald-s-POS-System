/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.mc.common.dto;

/**
 *
 * @author lakitha
 */
public class OrderDetailsDTO extends SuperDTO{
    private int order_QTY;
    private String itemName;
    private double price;
    private String chefName;
    
    private OrdersDTO ordersDTO;
    private ItemDTO itemDTO;
    private ChefDTO chefDTO;
    private OrderDetails_PK_DTO orderDetails_PK_DTO;

    public OrderDetailsDTO() {
    }

    public OrderDetailsDTO(int order_QTY, String itemName, double price, String chefName, OrdersDTO ordersDTO, ItemDTO itemDTO, ChefDTO chefDTO, OrderDetails_PK_DTO orderDetails_PK_DTO) {
        this.order_QTY = order_QTY;
        this.itemName = itemName;
        this.price = price;
        this.chefName = chefName;
        this.ordersDTO = ordersDTO;
        this.itemDTO = itemDTO;
        this.chefDTO = chefDTO;
        this.orderDetails_PK_DTO = orderDetails_PK_DTO;
    }

    /**
     * @return the order_QTY
     */
    public int getOrder_QTY() {
        return order_QTY;
    }

    /**
     * @param order_QTY the order_QTY to set
     */
    public void setOrder_QTY(int order_QTY) {
        this.order_QTY = order_QTY;
    }

    /**
     * @return the itemName
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * @param itemName the itemName to set
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return the chefName
     */
    public String getChefName() {
        return chefName;
    }

    /**
     * @param chefName the chefName to set
     */
    public void setChefName(String chefName) {
        this.chefName = chefName;
    }

    /**
     * @return the ordersDTO
     */
    public OrdersDTO getOrdersDTO() {
        return ordersDTO;
    }

    /**
     * @param ordersDTO the ordersDTO to set
     */
    public void setOrdersDTO(OrdersDTO ordersDTO) {
        this.ordersDTO = ordersDTO;
    }

    /**
     * @return the itemDTO
     */
    public ItemDTO getItemDTO() {
        return itemDTO;
    }

    /**
     * @param itemDTO the itemDTO to set
     */
    public void setItemDTO(ItemDTO itemDTO) {
        this.itemDTO = itemDTO;
    }

    /**
     * @return the chefDTO
     */
    public ChefDTO getChefDTO() {
        return chefDTO;
    }

    /**
     * @param chefDTO the chefDTO to set
     */
    public void setChefDTO(ChefDTO chefDTO) {
        this.chefDTO = chefDTO;
    }

    /**
     * @return the orderDetails_PK_DTO
     */
    public OrderDetails_PK_DTO getOrderDetails_PK_DTO() {
        return orderDetails_PK_DTO;
    }

    /**
     * @param orderDetails_PK_DTO the orderDetails_PK_DTO to set
     */
    public void setOrderDetails_PK_DTO(OrderDetails_PK_DTO orderDetails_PK_DTO) {
        this.orderDetails_PK_DTO = orderDetails_PK_DTO;
    }

    
}

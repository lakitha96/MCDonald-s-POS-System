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
public class TempOrderDetailsDTO extends SuperDTO{
    private int oid;
    private int code;
    private int order_QTY;
    private String itemName;
    private String itemDescription;
    private String ItemImagePath;
    private double price;
    private String chefName;
    private String orderStatus;
    
    private OrdersDTO ordersDTO;
    private ItemDTO itemDTO;
    private ChefDTO chefDTO;
    

    public TempOrderDetailsDTO() {
    }

    public TempOrderDetailsDTO(int oid, int code, int order_QTY, String itemName, String itemDescription, String ItemImagePath, double price) {
        this.oid = oid;
        this.code = code;
        this.order_QTY = order_QTY;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.ItemImagePath = ItemImagePath;
        this.price = price;
    }

    public TempOrderDetailsDTO(int oid, int code, int order_QTY, String itemName, String itemDescription, String ItemImagePath, double price, String chefName, String orderStatus, OrdersDTO ordersDTO, ItemDTO itemDTO, ChefDTO chefDTO) {
        this.oid = oid;
        this.code = code;
        this.order_QTY = order_QTY;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.ItemImagePath = ItemImagePath;
        this.price = price;
        this.chefName = chefName;
        this.orderStatus = orderStatus;
        this.ordersDTO = ordersDTO;
        this.itemDTO = itemDTO;
        this.chefDTO = chefDTO;
    }

    /**
     * @return the oid
     */
    public int getOid() {
        return oid;
    }

    /**
     * @param oid the oid to set
     */
    public void setOid(int oid) {
        this.oid = oid;
    }

    /**
     * @return the code
     */
    public int getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(int code) {
        this.code = code;
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
     * @return the itemDescription
     */
    public String getItemDescription() {
        return itemDescription;
    }

    /**
     * @param itemDescription the itemDescription to set
     */
    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    /**
     * @return the ItemImagePath
     */
    public String getItemImagePath() {
        return ItemImagePath;
    }

    /**
     * @param ItemImagePath the ItemImagePath to set
     */
    public void setItemImagePath(String ItemImagePath) {
        this.ItemImagePath = ItemImagePath;
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
     * @return the orderStatus
     */
    public String getOrderStatus() {
        return orderStatus;
    }

    /**
     * @param orderStatus the orderStatus to set
     */
    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
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

    
}

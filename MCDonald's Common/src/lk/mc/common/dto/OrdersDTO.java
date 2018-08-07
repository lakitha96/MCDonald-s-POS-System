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
public class OrdersDTO extends SuperDTO{
    private int OID;
    private String date;
    private String orderType;
    private String orderStatus;
    
    private CustomerDTO customerDTO;
    
    private CashierDTO cashierDTO;
    
    private TelephoneOperatorDTO telephoneOperatorDTO;
    

    public OrdersDTO() {
    }
    
    public OrdersDTO(int OID, String date, String orderType, String orderStatus, CustomerDTO customerDTO, CashierDTO cashierDTO) {
        this.OID = OID;
        this.date = date;
        this.orderType = orderType;
        this.orderStatus = orderStatus;
        this.customerDTO = customerDTO;
        this.cashierDTO = cashierDTO;
    }

    public OrdersDTO(int OID, String date, String orderType, String orderStatus, CustomerDTO customerDTO, CashierDTO cashierDTO, TelephoneOperatorDTO telephoneOperatorDTO) {
        this.OID = OID;
        this.date = date;
        this.orderType = orderType;
        this.orderStatus = orderStatus;
        this.customerDTO = customerDTO;
        this.cashierDTO = cashierDTO;
        this.telephoneOperatorDTO = telephoneOperatorDTO;
    }

    /**
     * @return the OID
     */
    public int getOID() {
        return OID;
    }

    /**
     * @param OID the OID to set
     */
    public void setOID(int OID) {
        this.OID = OID;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return the orderType
     */
    public String getOrderType() {
        return orderType;
    }

    /**
     * @param orderType the orderType to set
     */
    public void setOrderType(String orderType) {
        this.orderType = orderType;
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
     * @return the customerDTO
     */
    public CustomerDTO getCustomerDTO() {
        return customerDTO;
    }

    /**
     * @param customerDTO the customerDTO to set
     */
    public void setCustomerDTO(CustomerDTO customerDTO) {
        this.customerDTO = customerDTO;
    }

    /**
     * @return the cashierDTO
     */
    public CashierDTO getCashierDTO() {
        return cashierDTO;
    }

    /**
     * @param cashierDTO the cashierDTO to set
     */
    public void setCashierDTO(CashierDTO cashierDTO) {
        this.cashierDTO = cashierDTO;
    }

    /**
     * @return the telephoneOperatorDTO
     */
    public TelephoneOperatorDTO getTelephoneOperatorDTO() {
        return telephoneOperatorDTO;
    }

    /**
     * @param telephoneOperatorDTO the telephoneOperatorDTO to set
     */
    public void setTelephoneOperatorDTO(TelephoneOperatorDTO telephoneOperatorDTO) {
        this.telephoneOperatorDTO = telephoneOperatorDTO;
    }
    
    
}



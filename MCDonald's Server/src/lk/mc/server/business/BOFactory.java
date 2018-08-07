/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.mc.server.business;

import lk.mc.server.business.custom.impl.AdminLoginBOImpl;
import lk.mc.server.business.custom.impl.BakeHouseBOImpl;
import lk.mc.server.business.custom.impl.CashierBOImpl;
import lk.mc.server.business.custom.impl.CashierLoginBOImpl;
import lk.mc.server.business.custom.impl.ChefBOImpl;
import lk.mc.server.business.custom.impl.ChefLoginBOImpl;
import lk.mc.server.business.custom.impl.CustomerBOImpl;
import lk.mc.server.business.custom.impl.ItemBOImpl;
import lk.mc.server.business.custom.impl.PlaceOrderBOImpl;
import lk.mc.server.business.custom.impl.TelephoneOperatorBOImpl;
import lk.mc.server.business.custom.impl.TempOrderDetailsBOImpl;

/**
 *
 * @author lakitha
 */
public class BOFactory {
    
    public enum BOTypes{
        CUSTOMER, ITEM, CASHIER, ADMINLOGIN, PLACEORDER, TEMPORDERDETAILS, CHEF, TELEOPERATOR, CHEFLOGIN, BAKEHOUSE, CASHIERLOGIN
    }
    
    private static BOFactory boFactory;
    
    public static BOFactory getInstance(){
        if (boFactory == null){
            boFactory = new BOFactory();
        }
        return boFactory;
    }
    
    public SuperBO getBO(BOTypes type){
        switch(type){
            case CUSTOMER:
                return new CustomerBOImpl();
            case ITEM:
                return new ItemBOImpl();
            case CASHIER:
                return new CashierBOImpl();
            case ADMINLOGIN:
                return new AdminLoginBOImpl();
            case PLACEORDER:
                return new PlaceOrderBOImpl();
            case TEMPORDERDETAILS:
                return new TempOrderDetailsBOImpl();
            case CHEF:
                return new ChefBOImpl();    
            case TELEOPERATOR:
                return new TelephoneOperatorBOImpl();
            case CHEFLOGIN:
                return new ChefLoginBOImpl();
            case BAKEHOUSE:
                return new BakeHouseBOImpl();
            case CASHIERLOGIN:
                return new CashierLoginBOImpl();
            default:
                return null;
        }
    }
    
}


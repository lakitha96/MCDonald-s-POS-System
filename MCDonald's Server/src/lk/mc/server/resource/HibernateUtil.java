/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.mc.server.resource;

import java.io.File;
import lk.mc.server.entity.AdminLogin;
import lk.mc.server.entity.BakeHouse;
import lk.mc.server.entity.Cashier;
import lk.mc.server.entity.CashierLogin;
import lk.mc.server.entity.Chef;
import lk.mc.server.entity.ChefLogin;
import lk.mc.server.entity.Customer;
import lk.mc.server.entity.Item;
import lk.mc.server.entity.TelephoneOperator;
import lk.mc.server.entity.OrderDetails;
import lk.mc.server.entity.OrderDetails_PK;
import lk.mc.server.entity.Orders;
import lk.mc.server.entity.TempOrderDetails;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author lakitha
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory;

    private static StandardServiceRegistry registry;

    static {
        try {
            // Create the SessionFactory from standard (hibernate.cfg.xml) 
            // config file.
            File hibernateProperties = new File("settings/hibernate.properties");
            registry = new StandardServiceRegistryBuilder().loadProperties(hibernateProperties).build();

            // (2)
            sessionFactory = new MetadataSources(registry)
                    .addAnnotatedClass(Customer.class)
                    .addAnnotatedClass(Cashier.class)
                    .addAnnotatedClass(Item.class)
                    .addAnnotatedClass(Chef.class)
                    .addAnnotatedClass(TelephoneOperator.class)
                    .addAnnotatedClass(Orders.class)
                    .addAnnotatedClass(OrderDetails.class)
                    .addAnnotatedClass(OrderDetails_PK.class)
                    .addAnnotatedClass(AdminLogin.class)
                    .addAnnotatedClass(TempOrderDetails.class)
                    .addAnnotatedClass(ChefLogin.class)
                    .addAnnotatedClass(BakeHouse.class)
                    .addAnnotatedClass(CashierLogin.class)
                    .buildMetadata().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            StandardServiceRegistryBuilder.destroy(registry
            );
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.mc.server.resource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lakitha
 */
public class DBConnection {
    private static DBConnection dbConnection;
    
    private static final int MAXIMUM_POOL_CONNECTION=20;
    
    private ArrayList <Connection> idleConnection=new ArrayList<>();
    private ArrayList<Connection> usedConnection=new ArrayList<>();

    private DBConnection() {
        try {
            Class.forName("com.mysql.jbc.Driver");
            for (int i = 0; i < MAXIMUM_POOL_CONNECTION; i++) {
                Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/JDBC","root","lakitha");
                idleConnection.add(connection);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}

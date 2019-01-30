/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;

/**
 *
 * @author Aymen
 */
public class DataSource {
    private Connection connection;
    private String url;
    private String login;
    private String password;
    private Properties prop;
    private static DataSource instance;
    
    private DataSource() {
        prop=new Properties();
        try {
            prop.load(new FileInputStream("config.properties"));
            url=prop.getProperty("url");
            login=prop.getProperty("login");
            password=prop.getProperty("password");
            connection=DriverManager.getConnection(url,login,password);
        } catch (IOException ex) {
            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static DataSource getInstance(){
        if (instance==null)
        {
            instance=new DataSource();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
    
    
    
    
    
}

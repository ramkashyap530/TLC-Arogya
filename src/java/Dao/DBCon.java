/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tarun
 */
public class DBCon {
    
    public Connection getConnection() throws IOException{
      
    Connection conn = null;
    InputStream inputStream=null;
    try{
    Properties prop = new Properties();
    String propFileName = "database.properties";
    
    inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
    if (inputStream != null) {prop.load(inputStream);} 
    else {throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");}
   
    String Driver_name=prop.getProperty("Driver_name");  
    String URL=prop.getProperty("URL");  
    String Uname=prop.getProperty("Uname"); 
    String password=prop.getProperty("password"); 
   
    Class.forName(Driver_name);  
    conn=DriverManager.getConnection(URL,Uname,password); 
    
    } catch (IOException | ClassNotFoundException | SQLException e) {System.out.println("Exception: " + e);
    Logger.getLogger(DBCon.class.getName()).log(Level.SEVERE, null, e);} 
    finally {inputStream.close();
    }
    return conn;}
    
}

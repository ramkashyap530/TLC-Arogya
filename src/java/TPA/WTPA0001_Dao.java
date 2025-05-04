/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TPA;

import Dao.DBCon;
import Laboratory.WLAB0001_dao;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tarun
 */
public class WTPA0001_Dao {
    
        
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Statement stmt;
    
    
    boolean Save_TPA(String tpa_name,String tpa_code,String contact_person,String mobile_number,String address,String User,String hospital_code)throws SQLDataException{
         boolean sts=false;
        
        int i=0;
        int z=0;
        String Code="";
        try{
            
            con=new DBCon().getConnection();
            con.setAutoCommit(false);
          
           String Sql_code="select count(*)+1 as code from TPA_Details";
              ps=con.prepareStatement(Sql_code);
              rs=ps.executeQuery();
              if(rs.next()){
                  Code="TPA_"+rs.getString("code");
              }
            
            
           String Sql="insert into TPA_Details(tpa_hos_code,tpa_name,tpa_code,contact_person,"
                   + "mobile_number,address,hospital_code,created_by)Values(?,?,?,?,?,?,?,?)";
           ps=con.prepareStatement(Sql);
           ps.setString(1,Code);
           ps.setString(2,tpa_name);
           ps.setString(3,tpa_code);
           ps.setString(4,contact_person);
           ps.setString(5,mobile_number);
           ps.setString(6,address);
           ps.setString(7,hospital_code);
           ps.setString(8,User);
          i=ps.executeUpdate();
        
           if(i>=0){
               
               con.commit();
               sts=true;
           }
            
        }catch(Exception e){
            Logger.getLogger(WTPA0001_Dao.class.getName()).log(Level.SEVERE, null, e);
        }
         
        
       return sts;
    } 
    
    
    
    
    
    public  ArrayList<HashMap<String,String>> get_TPA()throws SQLException, IOException{
      ArrayList<HashMap<String,String>>al=new ArrayList<>();
      try{
      con=new DBCon().getConnection();
      
      String Sql="select isnull(tpa_name,'')as tpa_name,isnull(tpa_code,'')as tpa_code,\n" +
"isnull(contact_person,'')as contact_person,isnull(mobile_number,'')as mobile_number\n" +
"from tpa_details";
        
      ps=con.prepareStatement(Sql);
          System.out.println("====>stff"+Sql);
      rs=ps.executeQuery();
      while(rs.next()){
          HashMap<String,String> Map=new HashMap();
          
          Map.put("tpa_name",rs.getString("tpa_name"));
          Map.put("tpa_code",rs.getString("tpa_code"));
          Map.put("contact_person",rs.getString("contact_person"));
          Map.put("mobile_number",rs.getString("mobile_number"));
         
          al.add(Map);
      }

         
     } catch( Exception ex)
        {
             Logger.getLogger(WTPA0001_Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
     return al;
 
}
}

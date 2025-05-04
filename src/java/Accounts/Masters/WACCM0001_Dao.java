/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Accounts.Masters;

import Dao.DBCon;
import Master.WMAS0001_Dao;
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
public class WACCM0001_Dao {
        Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Statement stmt;
    
    
    
    
    
    public ArrayList<String> getdata(String cd,String usr)throws SQLDataException{
        ArrayList<String> al=new ArrayList<>();
        try{
           con=new DBCon().getConnection();
           String sql="";
           if(cd.equals("1")){
               
               sql="select isnull(group_id,'')as group_id,isnull(group_name,'')as group_name from Accounts_groups";
               
           }
           
           if(cd.equals("2")){
               
               sql="select isnull(type_code,'')as type_code,isnull(type_name,'')as type_name from ledger_type";
              
           }
           
           if(cd.equals("3")){
               
               sql="select isnull(method_code,'')as method_code,isnull(method_name,'')as method_name from ledger_rounding_methods";
              
           }
           
           if(cd.equals("4")){
               
               sql="select isnull(voucher_id,'')as voucher_id,isnull(voucher_name,'')as voucher_name from Account_vochur_type";
              
           }
       
           
           
           
           ps=con.prepareStatement(sql);
           rs=ps.executeQuery();
           while(rs.next()){
      
               
                al.add("<option value='"+rs.getString(1)+"'>"+rs.getString(2)+"</option>");
               
           }
               
            
        } catch( Exception ex)
        {
             Logger.getLogger(WACCM0001_Dao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return al;
        
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    ArrayList<HashMap<String,String>> get_group_name()throws SQLException, IOException{
      ArrayList<HashMap<String,String>>al=new ArrayList<>();
      try{
      con=new DBCon().getConnection();
      
      String Sql="select isnull(group_id,'')as group_id,isnull(group_name,'')as group_name,\n" +
"isnull(under,'')as under from Accounts_groups";
      ps=con.prepareStatement(Sql);
      rs=ps.executeQuery();
      while(rs.next()){
          HashMap<String,String> Map=new HashMap();
          Map.put("group_id",rs.getString("group_id"));
          Map.put("group_name",rs.getString("group_name"));
          al.add(Map);
      }

     }
         catch( Exception ex)
        {
             Logger.getLogger(WMAS0001_Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
            
     
     return al;
 
}
    
    
}

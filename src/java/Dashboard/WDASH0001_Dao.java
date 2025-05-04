/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dashboard;

import Dao.DBCon;
import Master.WMAS0001_Dao;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
public class WDASH0001_Dao {
     Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Statement stmt;
    
    
    
//    for geeting the total_doctors
    
    
   public String get_total_doc()throws SQLException, IOException{
    String total_doc="";
      try{
      con=new DBCon().getConnection();
      
      String Sql="select count(*) as totl_doc from mst_doctor";
      ps=con.prepareStatement(Sql);
      rs=ps.executeQuery();
      if(rs.next()){
       total_doc=rs.getString("totl_doc");
      }

     }
         catch( Exception ex)
        {
             Logger.getLogger(WDASH0001_Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
            
     
     return total_doc;
 
}
    
    
//    
//    for total staff

  public String get_total_staff()throws SQLException, IOException{
    String total_staff="";
      try{
      con=new DBCon().getConnection();
      
      String Sql="select count(*)as total_staff from hospital_staff";
      ps=con.prepareStatement(Sql);
      rs=ps.executeQuery();
      if(rs.next()){
       total_staff=rs.getString("total_staff");
      }

     }
         catch( Exception ex)
        {
             Logger.getLogger(WDASH0001_Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
            
     
     return total_staff;
 
}
    
    
//    
//    for geeting total ipd Patients
    
     public String get_total_ipd()throws SQLException, IOException{
     String total_ipd="";
      try{
      con=new DBCon().getConnection();
      
      String Sql="select count(*)as ipd_patient from mst_ipd_patient";
      ps=con.prepareStatement(Sql);
          System.out.println("---->"+Sql);
      rs=ps.executeQuery();
      if(rs.next()){
       total_ipd=rs.getString("ipd_patient");
      }

     }
         catch( Exception ex)
        {
             Logger.getLogger(WDASH0001_Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
            
     
     return total_ipd;
 
}
     
     
     
     
     //get total patients 
     
      
    
    
    
}

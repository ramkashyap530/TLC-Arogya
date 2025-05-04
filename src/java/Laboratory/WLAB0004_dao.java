/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Laboratory;

import Dao.DBCon;
import IPD.WIPD0002_Dao;
import PatitentPayment.PatitentPaymentDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lenovo
 */
public class WLAB0004_dao {
    
      Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Statement stmt;
    //for saving final bill 
    
  public Boolean Save_final_bill(String category_name[],String charge_name[],
          String qty[],String total_amount[],String Patient_id,String Bill_no,String cretaed_by,String charge_rate[]) {
        boolean sts =false;
        int i = 0;
        int y = 0;
        int z = 0;
        int a = 0;
        int r = 0;
 
        


  
        try {
            con = new DBCon().getConnection();
            con.setAutoCommit(false);

   
            String Sql = "insert into Patient_waise_bill_saving(Bill_no,patient_id,category_name,"
                    + "charge_name,rate,qty,total_amount,create_date,create_by)Values(?,?,?,?,?,?,?,getdate(),?)";
      ps = con.prepareStatement(Sql);
      int index=0;
      for (String cd : category_name) {

        ps.setString(1,Bill_no);
        ps.setString(2,Patient_id);
        ps.setString(3,cd);
        ps.setString(4,charge_name[index]);
        ps.setString(5,charge_rate[index]);
        ps.setString(6,qty[index]);
        ps.setString(7,total_amount[index]);
        ps.setString(8,cretaed_by);
   
        ps.addBatch();
        i++;
        index++;
      }
        sts = ps.executeBatch().length>0;
         if(sts){con.commit();sts = true;}
        
        } catch (Exception ex) {
            Logger.getLogger(WIPD0002_Dao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return sts;
    }
    
    
    
    
    
}

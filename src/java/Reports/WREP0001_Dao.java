/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reports;

import Dao.DBCon;
import IPD.WIPD0002_Dao;
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
public class WREP0001_Dao {
         Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Statement stmt;
    
    
    
    
    public  ArrayList<HashMap<String,String>> get_total_doc(String [] arr)throws SQLException, IOException{
      ArrayList<HashMap<String,String>>al=new ArrayList<>();
      try{
      con=new DBCon().getConnection();
     
      String Sql="select isnull(Doc_code,'')as doc_code,\n" +
"isnull(concat(doc_name_first,'',doc_name_last),'')as doc_name,isnull(specialist,'')as specialist,\n" +
"isnull(const_cahrge,0)as cont_charge,isnull(department_name,'')as department_name,isnull(Degree,'')as degre,"
              + "isnull(concat(mobile_number,',',Altranate_number),'')as mobile_number,\n" +
"isnull(email,'')as email,isnull(address,'')as address,\n" +
"isnull(sts,'')as sts\n" +
"\n" +
"from mst_doctor where 1=1 ";

        if(arr[1]!=null && arr[2]!=""){
                Sql+=" and admision_date between '"+arr[1]+"' and '"+arr[2]+"'";
                  
              }
              
            if(arr[4]!=null && arr[4]!=""){
                Sql+=" and type='"+arr[4]+"'";
                  
              }
              
                if(arr[3]!=null && arr[3]!=""){
                Sql+="and mip.patient_id='"+arr[3]+"'";
                  
              }

      
      ps=con.prepareStatement(Sql);
      rs=ps.executeQuery();
      while(rs.next()){
          HashMap<String,String> Map=new HashMap();
          
          Map.put("doc_code",rs.getString("doc_code"));
          Map.put("doc_name",rs.getString("doc_name"));
          Map.put("specialist",rs.getString("specialist"));
          Map.put("cont_charge",rs.getString("cont_charge"));
          Map.put("department_name",rs.getString("department_name"));
          Map.put("degre",rs.getString("degre"));
          Map.put("mobile_number",rs.getString("mobile_number"));
          Map.put("email",rs.getString("email"));
          Map.put("address",rs.getString("address"));
          Map.put("sts",rs.getString("sts"));
          al.add(Map);
      }

         
     } 
      
      catch( Exception ex)
        {
             Logger.getLogger(WREP0001_Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
     return al;
 
}
    
    
    
    public  ArrayList<HashMap<String,String>> get_total_staff(String [] arr)throws SQLException, IOException{
      ArrayList<HashMap<String,String>>al=new ArrayList<>();
      try{
      con=new DBCon().getConnection();
     
      String Sql=" select isnull(staff_id,'')as staff_id,isnull(name,'')as name,\n" +
" isnull(mobile_number,'')as mobile_number,isnull(department_name,'')as depaertment,\n" +
" isnull(postion,'')as position,isnull(salary,'')as salary,isnull(adhar_card,'')as adhar_card,\n" +
" isnull(format(date_of_joining,'dd/MM/yyyy'),'')as date_of_joining,isnull(sts,'')as sts,\n" +
" isnull(gender,'')as gender,isnull(age,'')as age\n" +
" from hospital_staff hf inner join hosiptal_department hd on hf.Depertament=hd.department_code";

        if(arr[1]!=null && arr[2]!=""){
                Sql+=" and admision_date between '"+arr[1]+"' and '"+arr[2]+"'";
                  
              }
              
            if(arr[4]!=null && arr[4]!=""){
                Sql+=" and type='"+arr[4]+"'";
                  
              }
              
                if(arr[3]!=null && arr[3]!=""){
                Sql+="and mip.patient_id='"+arr[3]+"'";
                  
              }

      
      ps=con.prepareStatement(Sql);
      rs=ps.executeQuery();
          System.out.println("====>sql"+Sql);
      while(rs.next()){
          HashMap<String,String> Map=new HashMap();
          
          Map.put("staff_id",rs.getString("staff_id"));
        
          
          Map.put("name",rs.getString("name"));
          Map.put("mobile_number",rs.getString("mobile_number"));
          Map.put("depaertment",rs.getString("depaertment"));
          Map.put("position",rs.getString("position"));
          Map.put("salary",rs.getString("salary"));
          Map.put("adhar_card",rs.getString("adhar_card"));
          Map.put("date_of_joining",rs.getString("date_of_joining"));
          Map.put("sts",rs.getString("sts"));
          Map.put("gender",rs.getString("gender"));
          Map.put("age",rs.getString("age"));
          al.add(Map);
      }

         
     } 
      
      catch( Exception ex)
        {
             Logger.getLogger(WREP0001_Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
     return al;
 
}
    
    
    
    public  ArrayList<HashMap<String,String>> bed_wise_details(String [] arr)throws SQLException, IOException{
      ArrayList<HashMap<String,String>>al=new ArrayList<>();
      try{
      con=new DBCon().getConnection();
     
      String Sql="select isnull(mip.patient_id,'')as patient_id,isnull(format(admision_date,'dd/MM/yyyy'),'')as adm_date,\n" +
" isnull(patient_name,'')as name,isnull(mip.room_bed_id,'')as room_bed_id,isnull(concat(Room_number_name,' - ',category_name),'')as room,isnull(ipd_no,'')as ipd_no\n" +
" from mst_ipd_patient mip inner join room_beds rb on mip.patient_id=rb.patient_id inner join mst_rooms mr on mr.Room_id=rb.room_id \n" +
" inner join charge_category cc on mr.room_category=cc.category_id where mip.pt_sts='Admitted'";

        if(arr[1]!=null && arr[2]!=""){
                Sql+=" and  mip.admision_date between '"+arr[1]+"' and '"+arr[2]+"'";
                  
              }
              
            if(arr[4]!=null && arr[4]!=""){
                Sql+=" and  gender='"+arr[4]+"'";
                  
              }
              
                if(arr[3]!=null && arr[3]!=""){
                Sql+="and mip.patient_id='"+arr[3]+"'";
                  
              }
                   if(arr[5]!=null && arr[5]!=""){
                Sql+="and mr.Room_number_name='"+arr[5]+"'";
                  
              }

      
      ps=con.prepareStatement(Sql);
      rs=ps.executeQuery();
          System.out.println("====>sql"+Sql);
      while(rs.next()){
          HashMap<String,String> Map=new HashMap();
          
      
        
          
          Map.put("patient_id",rs.getString("patient_id"));
          Map.put("adm_date",rs.getString("adm_date"));
          Map.put("name",rs.getString("name"));
          Map.put("room_bed_id",rs.getString("room_bed_id"));
          Map.put("ipd_no",rs.getString("ipd_no"));
          Map.put("room",rs.getString("room"));
        
          al.add(Map);
      }

         
     } 
      
      catch( Exception ex)
        {
             Logger.getLogger(WREP0001_Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
     return al;
 
}
    
    
    public  ArrayList<HashMap<String,String>> doctor_wise_opd(String [] arr)throws SQLException, IOException{
      ArrayList<HashMap<String,String>>al=new ArrayList<>();
      try{
      con=new DBCon().getConnection();
     
      String Sql="select isnull(opd_id,'')as opd_id,isnull(patient_name,'')as patient_name,isnull(CONCAT(doc_name_first,'',doc_name_last),'')as doc_name,\n" +
"isnull(const_cahrge,'')as cont_charge,isnull(format(create_date,'dd/MM/yyyy'),'')as dat,isnull(opd_sts,'')as opd_sts from mst_opd mo\n" +
"inner join mst_doctor md on mo.doc_code=md.Doc_code where 1=1";

        if(arr[1]!=null && arr[2]!=""){
                Sql+=" and  mo.create_date between '"+arr[1]+"' and '"+arr[2]+"'";
                  
              }
              
            if(arr[4]!=null && arr[4]!=""){
                Sql+=" and  gender='"+arr[4]+"'";
                  
              }
              
                if(arr[3]!=null && arr[3]!=""){
                Sql+="and md.Doc_code='"+arr[3]+"'";
                  
              }
                   if(arr[5]!=null && arr[5]!=""){
                Sql+="and  mo.opd_id='"+arr[5]+"'";
                  
              }

      
      ps=con.prepareStatement(Sql);
      rs=ps.executeQuery();
          System.out.println("====>sql"+Sql);
      while(rs.next()){
          HashMap<String,String> Map=new HashMap();
          
      
        
          
          Map.put("opd_id",rs.getString("opd_id"));
          Map.put("patient_name",rs.getString("patient_name"));
          Map.put("doc_name",rs.getString("doc_name"));
          Map.put("cont_charge",rs.getString("cont_charge"));
          Map.put("dat",rs.getString("dat"));
          Map.put("opd_sts",rs.getString("opd_sts"));
        
          al.add(Map);
      }

         
     } 
      
      catch( Exception ex)
        {
             Logger.getLogger(WREP0001_Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
     return al;
 
}
    
    
    
    //for daily collection report 
    
    public  ArrayList<HashMap<String,String>> Get_Daily_collection_report(String [] arr)throws SQLException, IOException{
      ArrayList<HashMap<String,String>>al=new ArrayList<>();
      try{
      con=new DBCon().getConnection();
       
      String Sql_opd="";
       
      
      if(arr[0].equals("OPD")){
           Sql_opd="select isnull(mo.opd_id,'')as patient_id,isnull(patient_name,'')as patient_name,\n" +
"isnull(md.const_cahrge ,'')as Amount,\n" +
"isnull(op.refund_amt,'')as refund,isnull(concat('Dr ',md.doc_name_first,' ',md.doc_name_last),'')as doc_name,\n" +
"format(create_date,'dd/MM/yy')as Create_date from mst_opd\n" +
"mo inner join mst_doctor md on mo.doc_code=md.Doc_code\n" +
"left join opd_refund op \n" +
"on mo.opd_id=op.opd_id";
      
         
      
      ps=con.prepareStatement(Sql_opd);
      rs=ps.executeQuery();
      while(rs.next()){
          HashMap<String,String> Map=new HashMap();
          
          Map.put("patient_id",rs.getString("patient_id"));
          Map.put("patient_name",rs.getString("patient_name"));
          Map.put("Amount",rs.getString("Amount"));
          Map.put("refund",rs.getString("refund"));
          Map.put("doc_name",rs.getString("doc_name"));
          Map.put("Create_date",rs.getString("Create_date"));
          al.add(Map);
      }
      
      }
      
      //for lab 
      if(arr[0].equals("LAB")){
         String  Sql_LAB="select isnull(ltb.patient_id,'')as patient_id,isnull(mip.patient_name,'')as patient_name,\n" +
"isnull(total_amount,'')as Amount,\n" +
"isnull(paid_amount,'') as refund,isnull(concat('Dr ',md.doc_name_first,' ',md.doc_name_last),'')as doc_name,\n" +
"isnull(format(done_date,'dd/MM/yy'),'')as Create_date\n" +
"\n" +
"from lab_test_payments ltb left join mst_ipd_patient mip on ltb.patient_id=mip.patient_id\n" +
"left join mst_doctor md on mip.doctor_id=md.Doc_code";
      
         
      
      ps=con.prepareStatement(Sql_LAB);
      rs=ps.executeQuery();
      while(rs.next()){
          HashMap<String,String> Map=new HashMap();
          
          Map.put("patient_id",rs.getString("patient_id"));
          Map.put("patient_name",rs.getString("patient_name"));
          Map.put("Amount",rs.getString("Amount"));
          Map.put("refund",rs.getString("refund"));
          Map.put("doc_name",rs.getString("doc_name"));
          Map.put("Create_date",rs.getString("Create_date"));
          al.add(Map);
      }
      
      }

         
     } 
      
      catch( Exception ex)
        {
             Logger.getLogger(WREP0001_Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
     return al;
 
}
    
    
    
    
}

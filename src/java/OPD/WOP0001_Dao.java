/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package OPD;

import Dao.DBCon;
import Master.WMAS0001_Dao;
import PatitentPayment.PatitentPaymentDao;
import Pharmacy.WPHR0001_Dao;
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
public class WOP0001_Dao {
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Statement stmt;
    
    
    public ArrayList<String> getdata(String cd,String Hospital_code)throws SQLDataException{
        ArrayList<String> al=new ArrayList<>();
        try{
           con=new DBCon().getConnection();
           String sql="";
           if(cd.equals("1")){
               
               sql="select isnull(Doc_code,'')as doc_code,isnull(concat(doc_name_first, '  ',doc_name_last,' / ',const_cahrge),'')as doctor_name"
                       + " from mst_doctor where hospital_code='"+Hospital_code+"'";
               
           }
           if(cd.equals("2")){
               sql="select isnull(procedure_id,'')as procedure_id,isnull(procedure_name,'')as procedure_name from opd_procedure_master";
           }
           if(cd.equals("3")){
               sql="select isnull(Room_id,'')as room_id,isnull(Room_number_name,'')as room_name from mst_rooms where room_category='"+Hospital_code+"'";
         
           }
           
           if(cd.equals("4")){
               sql="select isnull(room_bed_id,'')as romm_bed_id,isnull(room_bed_id,'')as romm_bed_id_no from room_beds where room_id='"+Hospital_code+"'  and isnull(patient_id,'')=''";
            
           }
           
           if(cd.equals("5")){
              sql="select bank_id,bank_name from Hos_bank_details";
           
           }
         
           
           ps=con.prepareStatement(sql);
           rs=ps.executeQuery();
           while(rs.next()){
      
               
                al.add("<option value='"+rs.getString(1)+"'>"+rs.getString(2)+"</option>");
               
           }
               
            
        } catch( Exception ex)
        {
             Logger.getLogger(WMAS0001_Dao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return al;
        
        
    }
    
    
    
    //save update opd
    
    
  public String  save_update_opd(String patient_id,String hospital_code,String Patient_name,String Patient_number,String patient_adhar,
            String Address,String DOC,String user,String Age,String Free,String Sex,String doc_fees,String gender_type,
           String Refer_Doc,String DOB,String doc_charge,String doc_discount,String doc_final,String Pay_type,String upi_name,String trans_id,String bank_name,String Card_no,String checque_no,String Guardian,
           String garduian_type){
        String sts="";
        
        
        try{
            
            con=new DBCon().getConnection();
            
           String sql="select * from mst_opd where opd_id='"+patient_id+"'";
           ps=con.prepareStatement(sql);
            System.out.println("------>"+patient_id);
           rs=ps.executeQuery();
           if(rs.next()){
               System.out.println("-=====>UPDATE ME AYA");
               sts=update_opd(patient_id,Patient_name,Patient_number,patient_adhar,Address,Age,Sex,gender_type,Refer_Doc,DOB,Guardian);
           }
           else{
        
          sts= save_opd(hospital_code,Patient_name,Patient_number,patient_adhar,Address,DOC,user,Age,Free,Sex,doc_fees,gender_type,Refer_Doc,DOB,doc_charge,doc_discount,
                   doc_final,Pay_type,upi_name,trans_id,bank_name,Card_no,checque_no,Guardian,garduian_type);
              
           }
            
            
        }catch(Exception e){
            System.out.println("--->"+e);
        }
        
        
        return sts;
    }
    
    
    

    
    public String  save_opd(String hospital_code,String Patient_name,String Patient_number,String patient_adhar,
            String Address,String DOC,String user,String Age,String Free,String Sex,String doc_fees,String gender_type,
           String Refer_Doc,String DOB,String doc_charge,String doc_discount,
                  String doc_final,String Pay_type,String upi_name,String trans_id,String bank_name,String Card_no,String checque_no,String Guardian,String garduian_type){
        String sts="";
        int i=0;
        int y=0;
        String code="";
        boolean patient_id_mod = false;
        
        try{
            con=new DBCon().getConnection();
            con.setAutoCommit(false);
      
              String sql="select count(*)+1as code from mst_opd ";
              String Sql_slip_number="select top 1 rwid+3 as Slip_number from mst_opd order by rwid desc";
            System.out.println("---->delete"+sql);
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            if(rs.next()){
                code="OPD/"+hospital_code+"/"+rs.getString("code");
            }
            
            
            
            
            //for creating slip number 
            String slip_number="";
         String sql_slip_number="select top 1 rwid+1 as Slip_number from mst_opd order by rwid desc";
            System.out.println("---->"+sql_slip_number);
            ps=con.prepareStatement(sql_slip_number);
            rs=ps.executeQuery();
            if(rs.next()){
                slip_number=rs.getString("Slip_number");
            }
            

            String Sql="insert into mst_opd(opd_id,patient_name,mobile_number,doc_code,adhar,address,hospital_code,created_by,Age,free_opd,Sex,gender_type,dob,gardian_name,slip_no,garudian_type)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            ps=con.prepareStatement(Sql);
            ps.setString(1,code);
            ps.setString(2,Patient_name);
            ps.setString(3,Patient_number);
            ps.setString(4,DOC);
            ps.setString(5,patient_adhar);
            ps.setString(6,Address);
            ps.setString(7,hospital_code);
            ps.setString(8,user);
            ps.setString(9,Age);
            ps.setString(10, Free);
            ps.setString(11, Sex);
            ps.setString(12, gender_type);
            ps.setString(13, DOB);
            ps.setString(14, Guardian);
            ps.setString(15, slip_number);
            ps.setString(16, garduian_type);
            
            i=ps.executeUpdate();
          
                
            
              
if(i>=0){
     String Sql_pay="insert into mst_opd_payment(opd_id,doctor_charge,discount,final_amount,return_amt,paytype,upi_name,trans_id,\n" +
"card_no,bank_name,cheque_no,created_by)values(?,?,?,?,?,?,?,?,?,?,?,?)";
            ps=con.prepareStatement(Sql_pay);
            ps.setString(1,code);
            ps.setString(2,doc_charge);
            ps.setString(3,doc_discount);
            ps.setString(4,doc_final);
            ps.setString(5,"0");
            ps.setString(6,Pay_type);
            ps.setString(7,upi_name);
            ps.setString(8,trans_id);
            ps.setString(9,Card_no);
            ps.setString(10,bank_name);
            ps.setString(11,checque_no);
            ps.setString(12,user);
            y=ps.executeUpdate();
            
}



            if(y>=0){
              
                 HashMap<String, Object> patitent_paymen_data = new HashMap<>();
                patitent_paymen_data.put("patitent_id", code);
                patitent_paymen_data.put("payment_id", "OPD");
                patitent_paymen_data.put("payment_type", "OPD FEES");
                patitent_paymen_data.put("mode", "OPD_FEES");
                patitent_paymen_data.put("create_by", user);
         
                patitent_paymen_data.put("pay_amt",doc_fees);
                patitent_paymen_data.put("paid_amt",doc_fees);
                patitent_paymen_data.put("pending_amt", 0);

            
                boolean pp_status = new PatitentPaymentDao().savePayment(patitent_paymen_data);
                    if(pp_status)
                    {
                     con.commit();
                     con.close();
                     sts =code;
                    }else{sts=""; con.close(); throw new Exception("Payment Table Error!!");}
            }
              
        }   catch( Exception ex)
        {
             Logger.getLogger(WMAS0001_Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("-------sts"+sts);
        return sts;
        
    }
    
    
    
  
    
    
    
    //for geetinng opd in update mode 
    public ArrayList<HashMap<String, String>>get_opd_patient(String patient_id) {
        ArrayList<HashMap<String, String>> prd_data = new ArrayList<>();
        try {
            try {
                this.con = new DBCon().getConnection();
                String sql = "select ISNULL(mo.opd_id,'')as opd_id,\n" +
"isnull(patient_name,'')as patient_name,\n" +
"isnull(mobile_number,'')as mobile_number,\n" +
"isnull(doc_code,'')as doc_code,\n" +
"isnull(adhar,'')as adhar,\n" +
"isnull(address,'')as address,\n" +
"isnull(gender_type,'')as gender_type,\n" +
"isnull(dob,'')as dob,\n" +
"isnull(gardian_name,'')as gardian_name,\n" +
"isnull(Age,0)as age\n" +
"from mst_opd mo where mo.opd_id='"+patient_id+"'";
              
                System.out.println("sql-> " + sql);
                this.ps = this.con.prepareStatement(sql);
                this.rs = this.ps.executeQuery();
                while (this.rs.next()) {
                    HashMap<String, String> prdcd = new HashMap<>();
                    prdcd.put("opd_id", this.rs.getString("opd_id"));
                    prdcd.put("patient_name", this.rs.getString("patient_name"));
                    prdcd.put("mobile_number", this.rs.getString("mobile_number"));
                    prdcd.put("doc_code", this.rs.getString("doc_code"));
                    prdcd.put("adhar", this.rs.getString("adhar"));
                    prdcd.put("address", this.rs.getString("address"));
                    prdcd.put("gender_type", this.rs.getString("gender_type"));
                    prdcd.put("dob", this.rs.getString("dob"));
                    prdcd.put("gardian_name", this.rs.getString("gardian_name"));
                    prdcd.put("age", this.rs.getString("age"));
                  
                 
                    prd_data.add(prdcd);
                }
                try {
                    this.con.close();
                    this.ps.close();
                    this.rs.close();
                    return prd_data;
                } catch (SQLException ex) {
                    Logger.getLogger(WOP0001_Dao.class.getName()).log(Level.SEVERE, (String) null, (Throwable) ex);
                    return prd_data;
                }
            } catch (Exception ex2) {
                Logger.getLogger(WPHR0001_Dao.class.getName()).log(Level.SEVERE, (String) null, (Throwable) ex2);
                try {
                    this.con.close();
                    this.ps.close();
                    this.rs.close();
                    return prd_data;
                } catch (SQLException ex3) {
                    Logger.getLogger(WOP0001_Dao.class.getName()).log(Level.SEVERE, (String) null, (Throwable) ex3);
                    return prd_data;
                }
            }
        } catch (Throwable th) {
            try {
                this.con.close();
                this.ps.close();
                this.rs.close();
                return prd_data;
            } catch (SQLException ex4) {
                Logger.getLogger(WOP0001_Dao.class.getName()).log(Level.SEVERE, (String) null, (Throwable) ex4);
                throw th;
            }
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public  ArrayList<HashMap<String,String>> get_total_opd(String [] arr)throws SQLException, IOException{
      ArrayList<HashMap<String,String>>al=new ArrayList<>();
      try{
      con=new DBCon().getConnection();
      
      String Sql="select isnull(free_opd,'')as free_opd,isnull(opd_id,'')as op_id,isnull(concat(gender_type,'  ',patient_name),'')as patient_name,\n" +
"isnull(mp.mobile_number,'')as mobile_number,isnull(adhar,'')as adhar,\n" +
"isnull(md.const_cahrge,0)as charge,\n" +
"isnull(format(mp.next_visit_date,'dd/MM/yyyy'),'')as next_visit,\n" +
"isnull(format(create_date,'dd/MM/yyyy'),'')as create_date,isnull(opd_sts,'')as opd_sts,isnull(CONCAT(doc_name_first,' ',doc_name_last),'')as docname\n" +
"from mst_opd mp inner join mst_doctor md  on mp.doc_code=md.Doc_code where mp.hospital_code='"+arr[0]+"'"
            ;
        
      
      
        if(arr[1]!=null && arr[1]!=""){
                Sql+=" and format(mp.create_date,'dd/MM/yyyy')=format(cast('"+arr[1]+"' as date),'dd/MM/yyyy')";
                  
              }
        
         Sql+=" and mp.opd_sts='Pending' and cancle_sts='0'  order by mp.opd_id desc";
      
          System.out.println("==>"+Sql);
      
      ps=con.prepareStatement(Sql);
          System.out.println("====>stff"+Sql);
          System.out.println(Sql);
      rs=ps.executeQuery();
      while(rs.next()){
          HashMap<String,String> Map=new HashMap();
          
          Map.put("patient_name",rs.getString("patient_name"));
          Map.put("mobile_number",rs.getString("mobile_number"));
          Map.put("adhar",rs.getString("adhar"));
          Map.put("next_visit",rs.getString("next_visit"));
          Map.put("opd_sts",rs.getString("opd_sts"));
          Map.put("docname",rs.getString("docname"));
          Map.put("free_opd",rs.getString("free_opd"));
       
          
          al.add(Map);
          
      }

         
     } catch( Exception ex)
        {
             Logger.getLogger(WMAS0001_Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
     return al;
 
}

    
    public  ArrayList<HashMap<String,String>> get_total_oprec(String [] arr)throws SQLException, IOException{
      ArrayList<HashMap<String,String>>al=new ArrayList<>();
      try{
      con=new DBCon().getConnection();
      
      String Sql="DECLARE @target_date DATE = '"+arr[1]+"';"
              + ""
              + "SELECT \n" +
"    CASE \n" +
"        WHEN ofp.opd_id IS NOT NULL THEN 'Follow_Up' \n" +
"        ELSE 'New' \n" +
"    END AS opd_type,\n" +
"    \n" +
"    ISNULL(CAST(ISNULL(ofp.cancel_sts, mp.cancle_sts) AS VARCHAR), '') AS cancle_sts,\n" +
"    ISNULL(mp.opd_id, ofp.opd_id) AS op_id,\n" +
"    \n" +
"    ISNULL(\n" +
"        CASE  \n" +
"             WHEN ofp.opd_id IS NOT NULL THEN concat(mp.gender_type,' ',mp.patient_name)\n" +
"            ELSE CONCAT(mp.gender_type, ' ', mp.patient_name)\n" +
"        END, ''\n" +
"    ) AS patient_name,\n" +
"    \n" +
"    ISNULL(mo.mobile_number, mp.mobile_number) AS mobile_number,\n" +
"    ISNULL(mo.adhar, mp.adhar) AS adhar,\n" +
"    \n" +
"    CASE \n" +
"        WHEN ofp.opd_id IS NOT NULL THEN 0 \n" +
"        ELSE ISNULL(md.const_cahrge, 0)\n" +
"    END AS charge,\n" +
"    \n" +
"    ISNULL(\n" +
"        FORMAT(\n" +
"            ISNULL(ofp.next_visit_date, mp.next_visit_date),\n" +
"        'dd/MM/yyyy'), ''\n" +
"    ) AS next_visit,\n" +
"    \n" +
"    ISNULL(mp.gardian_name,'') AS gardian_name,\n" +
"    \n" +
"    ISNULL(\n" +
"        FORMAT(\n" +
"            ISNULL(ofp.create_date, mp.create_date),\n" +
"        'dd/MM/yyyy'), ''\n" +
"    ) AS create_date,\n" +
"    \n" +
"    ISNULL(ofp.opd_status, mp.opd_sts) AS opd_sts,\n" +
"    \n" +
"    ISNULL(\n" +
"        CASE \n" +
"            WHEN ofp.opd_id IS NOT NULL THEN CONCAT('Dr ', md.doc_name_first, ' ', md.doc_name_last)\n" +
"            ELSE CONCAT('Dr',' ',md.doc_name_first, ' ', md.doc_name_last)\n" +
"        END, ''\n" +
"    ) AS docname,\n" +
"\n" +
"    ROW_NUMBER() OVER (\n" +
"        PARTITION BY \n" +
"            ISNULL(ofp.doc_id, mp.doc_code), \n" +
"            CAST(ISNULL(ofp.create_date, mp.create_date) AS DATE)\n" +
"        ORDER BY \n" +
"            ISNULL(ofp.create_date, mp.create_date), \n" +
"            ISNULL(mp.opd_id, ofp.opd_id)\n" +
"    ) AS token_number\n" +
"\n" +
"FROM mst_opd mp\n" +
"LEFT JOIN opd_follow_up ofp ON mp.opd_id = ofp.opd_id AND ISNULL(ofp.cancel_sts, 0) = 0\n" +
"LEFT JOIN mst_opd mo ON ofp.opd_id = mo.opd_id -- For follow-up patient details\n" +
"INNER JOIN mst_doctor md ON md.Doc_code = ISNULL(ofp.doc_id, mp.doc_code) where 1=1 ";
        
      
      
        if(arr[1]!=null && arr[1]!=""){
                Sql+="and  FORMAT(ISNULL(ofp.create_date, mp.create_date), 'dd/MM/yyyy') = FORMAT(@target_date, 'dd/MM/yyyy')";
                  
              }
        if(arr[2]!=null && arr[2]!=""){
            Sql+=" and md.Doc_code='"+arr[2]+"' ";
        }
        
         Sql+=" AND ISNULL(mp.cancle_sts, 0) = 0 order by mp.opd_id desc";
      
      
      ps=con.prepareStatement(Sql);
          System.out.println("====>"+Sql);
      rs=ps.executeQuery();
      while(rs.next()){
          HashMap<String,String> Map=new HashMap();
          
          Map.put("patient_name",rs.getString("patient_name"));
          Map.put("mobile_number",rs.getString("mobile_number"));
          Map.put("adhar",rs.getString("adhar"));
          Map.put("next_visit",rs.getString("next_visit"));
          Map.put("opd_sts",rs.getString("opd_sts"));
          Map.put("docname",rs.getString("docname"));
          Map.put("op_id",rs.getString("op_id"));
          Map.put("cancle_sts",rs.getString("cancle_sts"));
          Map.put("charge",rs.getString("charge"));
          Map.put("token_number",rs.getString("token_number"));
          Map.put("gardian_name",rs.getString("gardian_name"));
          Map.put("opd_type",rs.getString("opd_type"));
          
          al.add(Map);
      }

         
     } catch( Exception ex)
        {
             Logger.getLogger(WMAS0001_Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
     return al;
 
}
    
    
    
    
    public  ArrayList<HashMap<String,String>> get_total_opd_doctor(String [] arr)throws SQLException, IOException{
      ArrayList<HashMap<String,String>>al=new ArrayList<>();
      try{
      con=new DBCon().getConnection();
      
      String Sql="select isnull(opd_id,'')as op_id,isnull(patient_name,'')as patient_name,\n" +
"isnull(mp.mobile_number,'')as mobile_number,isnull(adhar,'')as adhar,isnull(age,'Not Asked')as age,\n" +
"isnull(md.const_cahrge,0)as charge,\n" +
"isnull(format(mp.next_visit_date,'dd/MM/yyyy'),'')as next_visit,\n" +
"isnull(format(create_date,'dd/MM/yyyy'),'')as create_date,isnull(opd_sts,'')as opd_sts,isnull(CONCAT(doc_name_first,' ',doc_name_last),'')as docname\n" +
"from mst_opd mp inner join mst_doctor md  on mp.doc_code=md.Doc_code where mp.hospital_code='"+arr[0]+"' ";
        
      
      
        if(arr[1]!=null && arr[1]!=""){
                Sql+=" and format(mp.create_date,'dd/MM/yyyy')=format(cast('"+arr[1]+"' as date),'dd/MM/yyyy')";
                  
              }
             Sql+=" and mp.opd_sts='Pending' and cancle_sts='0' order by mp.opd_id asc";
      
      ps=con.prepareStatement(Sql);
          System.out.println("====>stff"+Sql);
      rs=ps.executeQuery();
      if(rs.next()){
          HashMap<String,String> Map=new HashMap();
          
          Map.put("patient_name",rs.getString("patient_name"));
          Map.put("mobile_number",rs.getString("mobile_number"));
          Map.put("adhar",rs.getString("adhar"));
          Map.put("next_visit",rs.getString("next_visit"));
          Map.put("opd_sts",rs.getString("opd_sts"));
          Map.put("docname",rs.getString("docname"));
          Map.put("op_id",rs.getString("op_id"));
          Map.put("age",rs.getString("age"));
          al.add(Map);
          
          System.out.println(rs.getString("age"));
      }

         
     } catch( Exception ex)
        {
             Logger.getLogger(WMAS0001_Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
     return al;
 
}
    
    
    
    
//    for smart opd doctor
    
   public  ArrayList<HashMap<String,String>> get_next_opd(String [] arr)throws SQLException, IOException{
      ArrayList<HashMap<String,String>>al=new ArrayList<>();
      try{
      con=new DBCon().getConnection();
      
      String Sql="select isnull(opd_id,'')as op_id,isnull(patient_name,'')as patient_name,\n" +
"isnull(mp.mobile_number,'')as mobile_number,isnull(adhar,'')as adhar,\n" +
"isnull(md.const_cahrge,0)as charge,isnull(hospital_name,'')as hospital_name,isnull(mh.address,'')as location,\n" +
"isnull(format(mp.next_visit_date,'dd/MM/yyyy'),'')as next_visit,\n" +
"isnull(format(mp.create_date,'dd/MM/yyyy'),'')as create_date,isnull(opd_sts,'')as opd_sts,isnull(CONCAT(doc_name_first,' ',doc_name_last),'')as docname\n" +
"from mst_opd mp inner join mst_doctor md  on mp.doc_code=md.Doc_code \n" +
"inner join mst_hospital mh on mp.hospital_code=mh.hospital_code\n" +
"where mp.hospital_code='"+arr[0]+"'  \n" +
"and format(mp.create_date,'dd/MM/yyyy')=format(cast('2022-02-07' as date),'dd/MM/yyyy') ";
        
      
      
        if(arr[1]!=null && arr[1]!=""){
                Sql+=" and format(mp.create_date,'dd/MM/yyyy')=format(cast('"+arr[1]+"' as date),'dd/MM/yyyy')";
  
              }
        Sql+=" and mp.opd_sts='Pending' and cancle_sts='0' order by mp.opd_id desc";
      
      
      ps=con.prepareStatement(Sql);
          System.out.println("====>stff"+Sql);
      rs=ps.executeQuery();
      while(rs.next()){
          HashMap<String,String> Map=new HashMap();
          
          Map.put("patient_name",rs.getString("patient_name"));
          Map.put("mobile_number",rs.getString("mobile_number"));
          Map.put("adhar",rs.getString("adhar"));
          Map.put("next_visit",rs.getString("next_visit"));
          Map.put("opd_sts",rs.getString("opd_sts"));
          Map.put("docname",rs.getString("docname"));
          Map.put("hospital_name",rs.getString("hospital_name"));
          al.add(Map);
      
      }

         
     } 
      catch( Exception ex)
        {
             Logger.getLogger(WMAS0001_Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
     return al;
 
}
     
    
    
    
    
     public boolean  save_opd_medicine(String hospital_code,String []opd_medicine,String opd_diagonis,String opd_id,String []dosage,
                                          String []Medicine_Additional,String []test_name,String []Test_additional){
        boolean sts=false;
        int i=0;
        int y=0;
        int t=0;
        int z=0;
        int med_index=0;
        int test_ind_i=0;
       
        String code="";
        
        try{
            con=new DBCon().getConnection();
            con.setAutoCommit(false);
            
              String sql="select count(*)+1 as code from mst_medicine ";
            System.out.println("---->delete"+sql);
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            if(rs.next()){
                code="OPD_Diagonisis/"+hospital_code+"/"+rs.getString("code");
            }
             
            for(String cd : opd_medicine){
            String Sql="insert into mst_medicine(medicine_name,opd_id,hospital_code,dosage,Additional_comments)values(?,?,?,?,?)";
            ps=con.prepareStatement(Sql);
            ps.setString(1,cd);
            ps.setString(2,opd_id.trim());
            ps.setString(3,hospital_code);
            ps.setString(4,dosage[med_index]);
            ps.setString(5,Medicine_Additional[med_index] );
           
            i=ps.executeUpdate();
            
                med_index++;
            
            }
            
            if(i>=0){
                String Sql="insert into preciption_id(preciption_id,opd_id,diagonsis)Values(?,?,?)";
            ps=con.prepareStatement(Sql);
            ps.setString(1,code);
            ps.setString(2,opd_id.trim());
            ps.setString(3,opd_diagonis);
           
            y=ps.executeUpdate();
            }
            
            
            if(y>=0){
                String Sql="update mst_opd set opd_sts='Done' where opd_id='"+opd_id.trim()+"' ";
            ps=con.prepareStatement(Sql);
                System.out.println(Sql);
                 t=ps.executeUpdate();
       
            }
            
                        //for test saving 
            
                  if(t>=0){
                     for(String cd_test : test_name){
                     String Sql="insert into mst_test_prec(opd_id,test_name,hospital_code,comments)Values(?,?,?,?)";
                     ps=con.prepareStatement(Sql);
                     ps.setString(1,opd_id.trim());
                     ps.setString(2,cd_test);
                     ps.setString(3,hospital_code);
                     ps.setString(4,Test_additional[test_ind_i]);
                     System.out.println(Sql);
                     z=ps.executeUpdate();
                     test_ind_i++;
                      }
       
            }
            
            
            
            if(z>=0){
              
                con.commit();
                  sts=true;
            }
              
        }   catch( Exception ex)
        {
             Logger.getLogger(WMAS0001_Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return sts;
    }

     
    
     
     
     
     
//     for cancelling  OPD

public boolean  cancel_opd(String Opd_id,String opd_type){
        boolean sts=false;
        int i=0;
       
        try{
            con=new DBCon().getConnection();
            con.setAutoCommit(false);
       
            
            if(opd_type.equals("Follow_Up")){
                   String Sql="update opd_follow_up set cancel_sts='1' where opd_id='"+Opd_id.trim()+"' ";
            ps=con.prepareStatement(Sql);
                System.out.println(Sql);
                 i=ps.executeUpdate();  
            }
            else{
            
            
            String Sql="update mst_opd set cancle_sts='1' where opd_id='"+Opd_id.trim()+"' ";
            ps=con.prepareStatement(Sql);
                System.out.println(Sql);
                 i=ps.executeUpdate();
            }
            if(i>=0){
              
                con.commit();
                  sts=true;
            }
              
        }   catch( Exception ex)
        {
             Logger.getLogger(WMAS0001_Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return sts;
    }  



//for procedure 
     public ArrayList<HashMap<String, String>> get_pro_data(String procedure_id) {
        ArrayList<HashMap<String, String>> prd_data = new ArrayList<>();
        try {
            try {
                this.con = new DBCon().getConnection();
                String sql = "select isnull(procedure_id,'')as procedure_id,\nisnull(procedure_name,'')as procedure_name,\nisnull(procedure_rate,0)as procedure_rate  \nfrom opd_procedure_master where procedure_id='" + procedure_id + "'";
                System.out.println("====sql" + sql);
                System.out.println("sql-> " + sql);
                this.ps = this.con.prepareStatement(sql);
                this.rs = this.ps.executeQuery();
                while (this.rs.next()) {
                    HashMap<String, String> prdcd = new HashMap<>();
                    prdcd.put("procedure_id", this.rs.getString("procedure_id"));
                    prdcd.put("procedure_name", this.rs.getString("procedure_name"));
                    prdcd.put("procedure_rate", this.rs.getString("procedure_rate"));
                    prd_data.add(prdcd);
                }
                try {
                    this.con.close();
                    this.ps.close();
                    this.rs.close();
                    return prd_data;
                } catch (SQLException ex) {
                    Logger.getLogger(WOP0001_Dao.class.getName()).log(Level.SEVERE, (String) null, (Throwable) ex);
                    return prd_data;
                }
            } catch (Exception ex2) {
                Logger.getLogger(WPHR0001_Dao.class.getName()).log(Level.SEVERE, (String) null, (Throwable) ex2);
                try {
                    this.con.close();
                    this.ps.close();
                    this.rs.close();
                    return prd_data;
                } catch (SQLException ex3) {
                    Logger.getLogger(WOP0001_Dao.class.getName()).log(Level.SEVERE, (String) null, (Throwable) ex3);
                    return prd_data;
                }
            }
        } catch (Throwable th) {
            try {
                this.con.close();
                this.ps.close();
                this.rs.close();
                return prd_data;
            } catch (SQLException ex4) {
                Logger.getLogger(WOP0001_Dao.class.getName()).log(Level.SEVERE, (String) null, (Throwable) ex4);
                throw th;
            }
        }
    }


//for precriprtion details 

public ArrayList<HashMap<String, String>> get_prec_data(String OPD_ID) {
        ArrayList<HashMap<String, String>> prd_data = new ArrayList<>();
        try {
            try {
                this.con = new DBCon().getConnection();
                String sql = "select isnull(medicine_name,'')as medicine_name,\n" +
"isnull(dosage,'')as dosage,ISNULL(Additional_comments,'')as additional_comments,\n" +
"isnull(diagonsis,'')AS diagonsis\n" +
"from mst_medicine md\n" +
"inner join   preciption_id pd on md.opd_id=pd.opd_id\n" +
"where pd.opd_id='"+OPD_ID+"'";
                System.out.println("====sql" + sql);
                System.out.println("sql-> " + sql);
                this.ps = this.con.prepareStatement(sql);
                this.rs = this.ps.executeQuery();
                while (this.rs.next()) {
                    HashMap<String, String> prdcd = new HashMap<>();
                    prdcd.put("medicine_name", this.rs.getString("medicine_name"));
                    prdcd.put("dosage", this.rs.getString("dosage"));
                    prdcd.put("additional_comments", this.rs.getString("additional_comments"));
                    prdcd.put("diagonsis", this.rs.getString("diagonsis"));
                    
                    prd_data.add(prdcd);
                }
                try {
                    this.con.close();
                    this.ps.close();
                    this.rs.close();
                    return prd_data;
                } catch (SQLException ex) {
                    Logger.getLogger(WOP0001_Dao.class.getName()).log(Level.SEVERE, (String) null, (Throwable) ex);
                    return prd_data;
                }
            } catch (Exception ex2) {
                Logger.getLogger(WPHR0001_Dao.class.getName()).log(Level.SEVERE, (String) null, (Throwable) ex2);
                try {
                    this.con.close();
                    this.ps.close();
                    this.rs.close();
                    return prd_data;
                } catch (SQLException ex3) {
                    Logger.getLogger(WOP0001_Dao.class.getName()).log(Level.SEVERE, (String) null, (Throwable) ex3);
                    return prd_data;
                }
            }
        } catch (Throwable th) {
            try {
                this.con.close();
                this.ps.close();
                this.rs.close();
                return prd_data;
            } catch (SQLException ex4) {
                Logger.getLogger(WOP0001_Dao.class.getName()).log(Level.SEVERE, (String) null, (Throwable) ex4);
                throw th;
            }
        }
    }


//for procedure saving 


public boolean save_assigned_pro(String[] procedure_id, String[] procedure_rate, String patient_id, String total_amount, String discount, String final_amount, String collect_value, String return_value, String user, String adjust) {
        boolean sts = false;
        int i = 0;
        int index = 0;
        String code = "";
        String Pay_id = "";
        try {
            this.con = new DBCon().getConnection();
            this.con.setAutoCommit(false);
            System.out.println("---->deleteselect count(*)+1 as code from  opd_procedure_payment");
            this.ps = this.con.prepareStatement("select count(*)+1 as code from  opd_procedure_payment");
            this.rs = this.ps.executeQuery();
            if (this.rs.next()) {
                code = "Proced_NO/" + this.rs.getString("code");
                Pay_id = "PAY_NO/" + this.rs.getString("code");
            }
            this.ps = this.con.prepareStatement("insert into opd_procedure_assignmnet(patient_id,procedure_id,rate,created_date,created_by,pro_id)values(?,?,?,GETDATE(),?,?)");
            for (String pro_id : procedure_id) {
                this.ps.setString(1, patient_id);
                this.ps.setString(2, pro_id);
                this.ps.setString(3, procedure_rate[index]);
                this.ps.setString(4, user);
                this.ps.setString(5, code);
                this.ps.addBatch();
                i++;
                index++;
            }
            sts = this.ps.executeBatch().length > 0;
            if (sts) {
                this.ps = this.con.prepareStatement("insert into opd_procedure_payment(patient_id,payment_id,total_rate,created_by,pro_id,"
                        + "discount,final_rate,adjusted_from_opd,collect_amount,"
                        + "return_amount)values(?,?,?,?,?,?,?,?,?,?)");
                this.ps.setString(1, patient_id);
                this.ps.setString(2, Pay_id);
                this.ps.setString(3, total_amount);
                this.ps.setString(4, user);
                this.ps.setString(5, code);
                this.ps.setString(6, discount);
                this.ps.setString(7, final_amount);
                this.ps.setString(8, adjust);
                this.ps.setString(9, collect_value);
                this.ps.setString(10, return_value);
                i = this.ps.executeUpdate();
            }
            if (i >= 0) {
                this.con.commit();
                sts = true;
            }
        } catch (Exception ex) {
            Logger.getLogger(WMAS0001_Dao.class.getName()).log(Level.SEVERE, (String) null, (Throwable) ex);
        }
        return sts;
    }


//for follow up patients 

public ArrayList<HashMap<String, String>> get_follow_up_data(String doc) {
        ArrayList<HashMap<String, String>> prd_data = new ArrayList<>();
        try {
            try {
                this.con = new DBCon().getConnection();
                String sql = "select isnull(opd_id,'')as opd_id,isnull(patient_name,'')as patient_name,\n" +
"isnull(mo.mobile_number,'')as mobile_number,isnull(md.Doc_code,'')as doc_id,isnull(create_date,'')\n" +
"create_date,isnull(next_visit_date,'')as visit_date\n" +
"from mst_opd mo inner join mst_doctor md\n" +
"on mo.doc_code=md.Doc_code where mo.doc_code='"+doc+"'";
                System.out.println("====sql" + sql);
                System.out.println("sql-> " + sql);
                this.ps = this.con.prepareStatement(sql);
                this.rs = this.ps.executeQuery();
                while (this.rs.next()) {
                    HashMap<String, String> prdcd = new HashMap<>();
                    prdcd.put("opd_id", this.rs.getString("opd_id"));
                    prdcd.put("patient_name", this.rs.getString("patient_name"));
                    prdcd.put("mobile_number", this.rs.getString("mobile_number"));
                    prdcd.put("create_date", this.rs.getString("create_date"));
                    prdcd.put("visit_date", this.rs.getString("visit_date"));
                    prdcd.put("doc_id", this.rs.getString("doc_id"));
                    
                    
                    prd_data.add(prdcd);
                }
                try {
                    this.con.close();
                    this.ps.close();
                    this.rs.close();
                    return prd_data;
                } catch (SQLException ex) {
                    Logger.getLogger(WOP0001_Dao.class.getName()).log(Level.SEVERE, (String) null, (Throwable) ex);
                    return prd_data;
                }
            } catch (Exception ex2) {
                Logger.getLogger(WPHR0001_Dao.class.getName()).log(Level.SEVERE, (String) null, (Throwable) ex2);
                try {
                    this.con.close();
                    this.ps.close();
                    this.rs.close();
                    return prd_data;
                } catch (SQLException ex3) {
                    Logger.getLogger(WOP0001_Dao.class.getName()).log(Level.SEVERE, (String) null, (Throwable) ex3);
                    return prd_data;
                }
            }
        } catch (Throwable th) {
            try {
                this.con.close();
                this.ps.close();
                this.rs.close();
                return prd_data;
            } catch (SQLException ex4) {
                Logger.getLogger(WOP0001_Dao.class.getName()).log(Level.SEVERE, (String) null, (Throwable) ex4);
                throw th;
            }
        }
    }
        

     //for saving folloup 

public boolean  save_follow_up(String Opd_id,String visit_date,String doc_id){
    
        boolean sts=false;
        int i=0;
        int t=0;
        try{
            con=new DBCon().getConnection();
            con.setAutoCommit(false);
       
            String Sql="insert into opd_follow_up(opd_id,doc_id,Assigned_follow_up_date,visit_date,next_visit_date,create_date)"
                    + "values(?,?,?,getdate(),dateadd(day,(5),getdate()),getdate())";
            ps=con.prepareStatement(Sql);
                System.out.println(Sql);
                ps.setString(1,Opd_id.trim());
                ps.setString(2,doc_id.trim());
                ps.setString(3,visit_date);
                
                 i=ps.executeUpdate();
              
            if(i>=0){
                
                String Sql_up="update mst_opd set next_visit_date=dateadd(day,(5),getdate()) where opd_id='"+Opd_id.trim()+"'";
                System.out.println(Sql_up);
                    ps=con.prepareStatement(Sql_up);
                System.out.println(Sql_up);
                 t=ps.executeUpdate();
                
             
            }
            if(t>=0){
              
                con.commit();
                  sts=true;
                  
            }
            
              
        } catch( Exception ex)
        {
             Logger.getLogger(WMAS0001_Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return sts;
    }  



//for doctor Wise Opd 
public  ArrayList<HashMap<String,String>> get_total_opd_doc(String [] arr)throws SQLException, IOException{
      ArrayList<HashMap<String,String>>al=new ArrayList<>();
      try{
      con=new DBCon().getConnection();
      
      String Sql="select isnull(opd_id,'')as op_id,isnull(patient_name,'')as patient_name,\n" +
"isnull(mp.mobile_number,'')as mobile_number,isnull(adhar,'')as adhar,\n" +
"isnull(md.const_cahrge,0)as charge,\n" +
"isnull(format(mp.next_visit_date,'dd/MM/yyyy'),'')as next_visit,\n" +
"isnull(format(create_date,'dd/MM/yyyy'),'')as create_date,isnull(opd_sts,'')as opd_sts,isnull(CONCAT(doc_name_first,' ',doc_name_last),'')as docname\n" +
"from mst_opd mp inner join mst_doctor md  on mp.doc_code=md.Doc_code where mp.hospital_code='"+arr[0]+"'"
            ;
        
      
      
        if(arr[1]!=null && arr[1]!=""){
                Sql+=" and format(mp.create_date,'dd/MM/yyyy')=format(cast('"+arr[1]+"' as date),'dd/MM/yyyy')";
                  
              }
        
         Sql+=" and mp.opd_sts='Pending' and cancle_sts='0'   order by mp.opd_id desc";
      
          System.out.println("==>"+Sql);
      
      ps=con.prepareStatement(Sql);
          System.out.println("====>stff"+Sql);
          System.out.println(Sql);
      rs=ps.executeQuery();
      while(rs.next()){
          HashMap<String,String> Map=new HashMap();
          
          Map.put("patient_name",rs.getString("patient_name"));
          Map.put("mobile_number",rs.getString("mobile_number"));
          Map.put("adhar",rs.getString("adhar"));
          Map.put("next_visit",rs.getString("next_visit"));
          Map.put("opd_sts",rs.getString("opd_sts"));
          Map.put("docname",rs.getString("docname"));
       
          
          al.add(Map);
          
      }

         
     } catch( Exception ex)
        {
             Logger.getLogger(WMAS0001_Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
     return al;
 
}


//for doc code 

    String get_doc_charge(String doc_code) {
        String Code="";
        try{
           
      con=new DBCon().getConnection();
      
      
      String Sql="select isnull(const_cahrge,'')as con_charge from mst_doctor where Doc_code='"+doc_code+"'";
      ps=con.prepareStatement(Sql);
      rs=ps.executeQuery();
      if(rs.next()){
          Code=rs.getString("con_charge");
          System.out.println("---->"+Code);
      }
            
            
        }catch(Exception e){
            System.out.println(e);
        }
        
        return Code;
    }

    
    
    
  //for saving opd refund amount 
    
    public String Save_opd_refund(String Opd_id,String OPD_AMOUNT,String AMOUNT_REFUNDED,String FINAL_AMOUNT,String user,String remark){
    
        String sts="";
        int i=0;
        int t=0;
        try{
            con=new DBCon().getConnection();
            con.setAutoCommit(false);
       
            String Sql="insert into opd_refund(opd_id,opd_amount,refund_amt,final_amt,created_date,created_by,Remark)Values(?,?,?,?,getdate(),?,?)";
            ps=con.prepareStatement(Sql);
                System.out.println(Sql);
                ps.setString(1,Opd_id.trim());
                ps.setString(2,OPD_AMOUNT.trim());
                ps.setString(3,AMOUNT_REFUNDED);
                ps.setString(4,FINAL_AMOUNT);
                ps.setString(5,user);
                ps.setString(6,remark);
                
                 i=ps.executeUpdate(); 
              
           
                 
                 
                 
            if(i>=0){
                
            String Sql_update_payment="update mst_opd_payment set final_amount=final_amount-'"+AMOUNT_REFUNDED+"',return_amt=return_amt + "+AMOUNT_REFUNDED+" where opd_id='"+Opd_id.trim()+"'";
            ps=con.prepareStatement(Sql_update_payment);
            System.out.println(Sql);
              t=ps.executeUpdate();
    
            }
            if(t>=0){
             con.commit();
                  sts=Opd_id.trim();
                  
            }
              
        } catch( Exception ex)
        {
             Logger.getLogger(WMAS0001_Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return sts;
    }  
    
    
    //for category charge 
    
    public String get_cat_charge(String Cat_id){
        
       String charge_amount="";
       
       
        
       
       try{
           con=new DBCon().getConnection();
              
            String Sql="select charge_amount from mst_hos_charges where category_id='"+Cat_id+"'";
            System.out.println("------"+Sql);
             ps=con.prepareStatement(Sql);
             rs=ps.executeQuery();
             if(rs.next()){
                 
                 charge_amount=rs.getString("charge_amount");
             }
 
           
       }catch(Exception e){
           System.out.println("---->"+e);
       }
       
       return charge_amount;
    }

    
   //for procedure list of patient 
    
    
    public ArrayList<HashMap<String, String>> get_pro_patient_list(String patient_id) {
        ArrayList<HashMap<String, String>> prd_data = new ArrayList<>();
        try {
            try {
                this.con = new DBCon().getConnection();
                String sql = "SELECT \n" +
"    opr.patient_id as patient_id,\n" +
"    STRING_AGG(opm.procedure_name, ', ') WITHIN GROUP (ORDER BY opr.pro_id) AS procedures,\n" +
"    STRING_AGG(CAST(opr.rate AS NVARCHAR), ', ') WITHIN GROUP (ORDER BY opr.pro_id) AS rates,\n" +
"    opp.total_rate as total_rate,\n" +
"    opp.discount as discount,\n" +
"    opp.final_rate as final_rate,\n" +
"    isnull(opp.adjusted_from_opd,0) as ad_opd,\n" +
"    isnull(opp.collect_amount,0) as collect_amount,\n" +
"    isnull(opp.return_amount,0) as return_amt,\n" +
"	opp.pro_id as pro_id,"
                        +  "mo.patient_name as patient_name"
                        + "\n" +
                        
"FROM \n" +
"    opd_procedure_assignmnet opr\n" +
"INNER JOIN \n" +
"    opd_procedure_master opm ON opr.procedure_id = opm.procedure_id\n" +
"INNER JOIN \n" +
"    opd_procedure_payment opp ON opp.patient_id = opr.patient_id"
                        + "   inner join \n" +
"  mst_opd mo on mo.opd_id=opr.patient_id"
                        + ""
                        + "\n" +
                        
                        
"GROUP BY \n" +
"  opr.patient_id, opp.total_rate, opp.discount, opp.final_rate,\n" +
"	opp.adjusted_from_opd, opp.collect_amount, opp.return_amount,opp.pro_id,mo.patient_name;";
              
                System.out.println("sql-> " + sql);
                this.ps = this.con.prepareStatement(sql);
                this.rs = this.ps.executeQuery();
                while (this.rs.next()) {
                    HashMap<String, String> prdcd = new HashMap<>();
                    prdcd.put("patient_id", this.rs.getString("patient_id"));
                    prdcd.put("procedures", this.rs.getString("procedures"));
                    prdcd.put("rates", this.rs.getString("rates"));
                    prdcd.put("total_rate", this.rs.getString("total_rate"));
                    prdcd.put("discount", this.rs.getString("discount"));
                    prdcd.put("final_rate", this.rs.getString("final_rate"));
                    prdcd.put("ad_opd", this.rs.getString("ad_opd"));
                    prdcd.put("collect_amount", this.rs.getString("collect_amount"));
                    prdcd.put("return_amt", this.rs.getString("return_amt"));
                    prdcd.put("pro_id", this.rs.getString("pro_id"));
                    prdcd.put("patient_name", this.rs.getString("patient_name"));
                    
                    
                    prd_data.add(prdcd);
                }
                try {
                    this.con.close();
                    this.ps.close();
                    this.rs.close();
                    return prd_data;
                } catch (SQLException ex) {
                    Logger.getLogger(WOP0001_Dao.class.getName()).log(Level.SEVERE, (String) null, (Throwable) ex);
                    return prd_data;
                }
            } catch (Exception ex2) {
                Logger.getLogger(WPHR0001_Dao.class.getName()).log(Level.SEVERE, (String) null, (Throwable) ex2);
                try {
                    this.con.close();
                    this.ps.close();
                    this.rs.close();
                    return prd_data;
                } catch (SQLException ex3) {
                    Logger.getLogger(WOP0001_Dao.class.getName()).log(Level.SEVERE, (String) null, (Throwable) ex3);
                    return prd_data;
                }
            }
        } catch (Throwable th) {
            try {
                this.con.close();
                this.ps.close();
                this.rs.close();
                return prd_data;
            } catch (SQLException ex4) {
                Logger.getLogger(WOP0001_Dao.class.getName()).log(Level.SEVERE, (String) null, (Throwable) ex4);
                throw th;
            }
        }
    }
    
    
    
   //for opd done 
    
    public boolean  opd_done(String Opd_id,String opd_type){
        boolean sts=false;
        int i=0;
       
        try{
            con=new DBCon().getConnection();
            con.setAutoCommit(false);
            
            
            
            if(opd_type.equals("Follow_Up")){
        String Sql="update opd_follow_up set opd_status='Done' where opd_id='"+Opd_id.trim()+"'";
            ps=con.prepareStatement(Sql);
                System.out.println(Sql);
                 i=ps.executeUpdate();    
            }
            
            
            
            else{
            String Sql="update mst_opd set opd_sts='Done' where opd_id='"+Opd_id.trim()+"'";
            ps=con.prepareStatement(Sql);
                System.out.println("----->Yanha Tak aya ");
                System.out.println(Sql);
                 i=ps.executeUpdate();
            }
            
            
            if(i>=0){
              
                con.commit();
                  sts=true;
            }
              
        }   catch( Exception ex)
        {
             Logger.getLogger(WMAS0001_Dao.class.getName()).log(Level.SEVERE, null, ex);
             
        }
        
        return sts;
    }
    
    
    
    

     public String update_opd(String patient_id, String Patient_name, String Patient_number, String patient_adhar, String Address, String Age, String Sex, String gender_type, String Refer_Doc, String DOB,String Guardian) {
        String sts="";
        int i=0;
        try{
            con=new DBCon().getConnection();
            con.setAutoCommit(false);
            String Sql="update mst_opd set patient_name='"+Patient_name+"',mobile_number='"+Patient_number+"',adhar='"+patient_adhar+"',address='"+Address+"',Age='"+Age+"',sex='"+Sex+"',gender_type='"+gender_type+"',dob='"+DOB+"',gardian_name='"+Guardian+"' where opd_id='"+patient_id.trim()+"'";
             ps=con.prepareStatement(Sql);
            System.out.println(Sql);
                 i=ps.executeUpdate();
            
            if(i>=0){
              
                con.commit();
                
                  sts=patient_id;
            }
                 
                 
        }catch(Exception ex){
           Logger.getLogger(WMAS0001_Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return sts;
    }

    
    
 //for opd Payment Sheet 
    
    public ArrayList<HashMap<String, String>> get_opd_payment_sheet(String[] filter) {
        ArrayList<HashMap<String, String>> prd_data = new ArrayList<>();
        try {
            try {
                this.con = new DBCon().getConnection();
                String sql = "SELECT \n" +
"mo.patient_name,\n" +
"concat('Dr ',md.doc_name_first,' ',md.doc_name_last)as doctor_name,\n" +
"    mop.opd_id,\n" +
"    payment_id,\n" +
"    doctor_charge,\n" +
"    discount,\n" +
"    final_amount,\n" +
"   isnull(return_amt,0)as refund_amount,\n" +
"    paytype,\n" +
"    CASE \n" +
"        WHEN paytype = 'UPI' THEN concat('UPI Name : ',' ',upi_name,':-','Transcation Id:-',' ',trans_id)\n" +
"        WHEN paytype = 'Cash' THEN 'Cash'\n" +
"		WHEN paytype = 'Card' THEN concat('Bank Name :-',' ',bank_name,'Card No:-',card_no)\n" +
"		WHEN paytype = 'cheque_no' THEN concat('Bank Name :-',' ',bank_name,'Cheque No:-',cheque_no)\n" +
"        ELSE 'Other'\n" +
"    END AS transaction_info,\n" +
"    mop.create_date,\n" +
"    mop.created_by\n" +
"FROM mst_opd_payment mop\n" +
"inner join mst_opd mo on mop.opd_id=mo.opd_id\n" +
"inner join mst_doctor md on mo.doc_code=md.Doc_code\n" +
"  where 1=1 ";
              
                
        if(filter[0]!=null && filter[3]!=""){
                sql+="and mo.create_date BETWEEN  '"+filter[0]+"' and '"+filter[3]+"' ";
  
              }
        
      
        
         if(filter[1]!=null && filter[1]!=""){
                sql+="and paytype='"+filter[1]+"'";
  
              }
         
        if(filter[2]!=null && filter[2]!=""){
                sql+="and mo.doc_code='"+filter[2]+"'";
  
              }
        sql+=" order by mo.create_date desc";
                

                System.out.println("sql-> " + sql);
                this.ps = this.con.prepareStatement(sql);
                this.rs = this.ps.executeQuery();
                while (this.rs.next()) {
                    HashMap<String, String> prdcd = new HashMap<>();
                    prdcd.put("patient_name", this.rs.getString("patient_name"));
                    prdcd.put("doctor_name", this.rs.getString("doctor_name"));
                    prdcd.put("opd_id", this.rs.getString("opd_id"));
                    prdcd.put("payment_id", this.rs.getString("payment_id"));
                    prdcd.put("doctor_charge", this.rs.getString("doctor_charge"));
                    prdcd.put("final_amount", this.rs.getString("final_amount"));
                    prdcd.put("discount", this.rs.getString("discount"));
                    prdcd.put("transaction_info", this.rs.getString("transaction_info"));
                    prdcd.put("create_date", this.rs.getString("create_date"));
                    prdcd.put("paytype", this.rs.getString("paytype"));
                     prdcd.put("refund_amount", this.rs.getString("refund_amount"));
                 
                    prd_data.add(prdcd);
                }
                try {
                    this.con.close();
                    this.ps.close();
                    this.rs.close();
                    return prd_data;
                } catch (SQLException ex) {
                    Logger.getLogger(WOP0001_Dao.class.getName()).log(Level.SEVERE, (String) null, (Throwable) ex);
                    return prd_data;
                }
            } catch (Exception ex2) {
                Logger.getLogger(WPHR0001_Dao.class.getName()).log(Level.SEVERE, (String) null, (Throwable) ex2);
                try {
                    this.con.close();
                    this.ps.close();
                    this.rs.close();
                    return prd_data;
                } catch (SQLException ex3) {
                    Logger.getLogger(WOP0001_Dao.class.getName()).log(Level.SEVERE, (String) null, (Throwable) ex3);
                    return prd_data;
                }
            }
        } catch (Throwable th) {
            try {
                this.con.close();
                this.ps.close();
                this.rs.close();
                return prd_data;
            } catch (SQLException ex4) {
                Logger.getLogger(WOP0001_Dao.class.getName()).log(Level.SEVERE, (String) null, (Throwable) ex4);
                throw th;
            }
        }
    }
    
    
    
    
    
     
}

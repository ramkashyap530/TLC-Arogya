/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package IPD;

import Dao.DBCon;
import Master.WMAS0001_Dao;
import PatitentPayment.PatitentPaymentDao;
import java.io.IOException;
import java.security.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tarun
 */
public class WIPD0001_Dao {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Statement stmt;

    public ArrayList<String> getdata(String cd, String Hospital_code) throws SQLDataException {
        ArrayList<String> al = new ArrayList<>();
        try {
            con = new DBCon().getConnection();
            String sql = "";
            if (cd.equals("1")) {

                sql = "select isnull(rb.room_bed_id,'')as id,isnull(concat(Room_number_name,'  /  ',bed_number,' / ',isnull(patient_name,'Vaccant')),'')as name,patient_name from room_beds\n"
                        + "rb inner join mst_rooms mr on rb.room_id=mr.Room_id left join mst_ipd_patient mip on mip.patient_id=rb.patient_id";

            }
            if (cd.equals("2")) {
                sql = "select doc_code,concat(doc_name_first,' ' ,doc_name_last)as doc_name from mst_doctor where hospital_code='" + Hospital_code + "'";
                
            }
            if (cd.equals("3")) {
                sql = "select isnull(room_id,'')as room_id,isnull(Room_number_name,'')as room_name from mst_rooms";
                //System.out.println("---->onchange"+sql);
            }

            if (cd.equals("4")) {
                sql = "select isnull(category_id,'') as category_id,isnull(category_name,'')as category_name from charge_category where charge_type='Room'";
                //System.out.println("---->onchange"+sql);
            }

            if (cd.equals("5")) {
                sql = "select isnull(tpa_hos_code,'')as tpa_code,isnull(tpa_name,'')as tpa_name from TPA_Details";
                //System.out.println("---->onchange"+sql);
            }

            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {

                al.add("<option disable value='" + rs.getString(1) + "'>" + rs.getString(2) + "</option>");
            }

        } catch (Exception ex) {
            Logger.getLogger(WMAS0001_Dao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return al;

    }

    String Save_update(String patient_id, String date, String room_no, String doctor, String type, String fees, String Patient_name,
            String Age, String gender, String phone, String adhar, String Address, String user, String hospital_code, String Bed_number,
            String Refering_doctor, String Father_Name,
            String tpa, String Relative_name, String Relation, String Relative_mobile_number, String Realtive_address,
            String dob,String charge_amount,String gender_type,String relation_of,String policy_Number,String Patient_type) {
        String sts = "";

        try {
            con = new DBCon().getConnection();
            con.setAutoCommit(false);

            String sql = "select *   from mst_ipd_patient where patient_id='" + patient_id + "'";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                update_ipd_patient(patient_id,Patient_name,Age,gender,phone,adhar,
                        Address,Father_Name,Relative_name,Relation,Relative_mobile_number,Realtive_address,dob,user,Refering_doctor);
            } else {
                sts = save_ipd(date, room_no, doctor, type, fees, Patient_name,
                        Age, gender, phone, adhar, Address, user, hospital_code, Bed_number, Refering_doctor, Father_Name,
                        tpa, Relative_name, Relation, Relative_mobile_number, Realtive_address,dob,charge_amount,gender_type,relation_of,policy_Number,Patient_type);

            }
        } catch (Exception ex) {
            Logger.getLogger(WMAS0001_Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sts;
    }

    String save_ipd(String date, String room_no, String doctor, String type, String fees, String Patient_name,
            String Age, String gender, String phone, String adhar, String Address, String user, String hospital_code, String Bed_number, String Refering_doctor,
            String Father_Name, String tpa, String Relative_name, String Relation, String Relative_mobile_number, String Realtive_address,String dob,
            String charge_amount,String gender_type,String relation_of,String policy_Number,String Patient_type) {
        String sts = "";
        int i = 0;
        int y = 0;
        int z = 0;
        int a = 0;
        int r = 0;
        int c=0;
        

        String code = "";
        String VOC_code="";
        
        int UHID=0;
       LocalDateTime localDateTime = LocalDateTime.parse(date);

        // Format for SQL Server (YYYY-MM-DD HH:MM:SS)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String sqlDateTime = localDateTime.format(formatter);

        
       
         
        try {

            String sql = "select count(*)+1 as code  from mst_ipd_patient";

            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                code = "IPD/" + hospital_code + "/" + rs.getString("code");
               
            }
            
            String sql_uhid = "IF EXISTS (SELECT top 1 UHID FROM mst_ipd_patient )\n" +
"BEGIN\n" +
"select top 1 UHID+1 as new_UHID from mst_ipd_patient order by UHID desc;\n" +
"END\n" +
"ELSE\n" +
"BEGIN\n" +
"SELECT uhid_no+1 as new_uhid\n" +
"FROM Hos_UHID\n" +
"                 \n" +
"END";

             ps = con.prepareStatement(sql_uhid);
            rs = ps.executeQuery();
            if (rs.next()) {
                UHID=rs.getInt("new_UHID");
               
               
            }
            
  
            String Sql = "insert into mst_ipd_patient(patient_id,admision_date,room_bed_id,doctor_id,type,admission_fess,patient_name"
                    + ",age,gender,phone,adhar,address,hospital_code,created_by,father_name,refer_doctor,tpa_id,dob,charge_amount,UHID,gender_type,relation_of,tpa_policy_no,patient_type)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"+UHID+",?,?,?,?)";
            ps = con.prepareStatement(Sql);
            ps.setString(1, code);
            ps.setString(2, sqlDateTime);
            ps.setString(3, Bed_number);
            ps.setString(4, doctor);
            ps.setString(5, type);
            ps.setString(6, fees);
            ps.setString(7, Patient_name);
            ps.setString(8, Age);
            ps.setString(9, gender);
            ps.setString(10, phone);
            ps.setString(11, adhar);
            ps.setString(12, Address);
            ps.setString(13, hospital_code);
            ps.setString(14, user);
            ps.setString(15, Father_Name);
            ps.setString(16, Refering_doctor);
            ps.setString(17, tpa);
            ps.setString(18, dob);
            ps.setString(19, charge_amount);
            ps.setString(20, gender_type);
            ps.setString(21, relation_of);
            ps.setString(22, policy_Number);
            ps.setString(23, Patient_type);

            i = ps.executeUpdate();

            
             
            if (i >= 0) {

                String sql_update = "update room_beds set patient_id='" + code + "' where room_bed_id='" + Bed_number + "'";
                ps = con.prepareStatement(sql_update);
                z = ps.executeUpdate();

            }

            if (z > 0) {
                String sql_update = "insert into patient_relatives(patient_id,Relative_name,Relation,Relative_number,relative_address,create_date)"
                        + "Values('" + code + "','" + Relative_name + "','" + Relation + "','" + Relative_mobile_number + "','" + Realtive_address + "','')";
                ps = con.prepareStatement(sql_update);
                a = ps.executeUpdate();

            }

            if (a >= 0) {
                String sql_location = "INSERT INTO patient_location(" +
"           patient_id," +
"           previous_room_bed_id," +
"           changed_room_bed_id," +
"           change_date," +
"           change_by," +
"           start_date," +
"           end_date," +
"           bed_amount," +
"           room_category," +
"           room_number," +
"           status)" +

                         "Values(?,?,?,?,?,?,?,?,?,?,?)";
                ps = con.prepareStatement(sql_location);
            ps.setString(1,code );
            ps.setString(2, "");
            ps.setString(3, Bed_number);
            ps.setString(4, null);
            ps.setString(5, user);
            ps.setString(6, sqlDateTime);
            ps.setString(7, null);
            ps.setString(8, charge_amount);
            ps.setString(9, "");
            ps.setString(10, "");
            ps.setString(11, "Authorize");
                r = ps.executeUpdate();
                System.out.println("-----------------------"+sql_location);

            }
            
            
            if(r > 0){
                 
                String sql_voc = "select count(*)+1 as voc from ipd_patient_payment";
            ps = con.prepareStatement(sql_voc);
            rs = ps.executeQuery();
            if (rs.next()) {
                VOC_code = "VOC_" + rs.getString("voc");
            }

            String Sql_voc_insert = "insert into ipd_patient_payment(patient_id,amount,payment_date,taken_by,voucher_id,remark)values(?,?,getdate(),?,?,?)";
            ps = con.prepareStatement(Sql_voc_insert);
            ps.setString(1,code );
            ps.setString(2, fees);
            ps.setString(3, user);
            ps.setString(4, VOC_code);
            ps.setString(5, "Admission Fees Voucher");

            c = ps.executeUpdate();
           
              
            }
            
            

            if (c > 0) {
                HashMap<String, Object> patitent_paymen_data = new HashMap<>();
                patitent_paymen_data.put("patitent_id", code);
                patitent_paymen_data.put("payment_id", "ADMIT_FEE");
                patitent_paymen_data.put("payment_type", "Addmission Fees Vocher");
                patitent_paymen_data.put("mode", "Admit");
                patitent_paymen_data.put("create_by", user);

                patitent_paymen_data.put("pay_amt", "0");
                patitent_paymen_data.put("paid_amt", fees);
                patitent_paymen_data.put("pending_amt", 0);

                boolean pp_status = new PatitentPaymentDao().savePayment(patitent_paymen_data);
                    if(pp_status)
                    {con.commit();
                            con.close();
                sts = code;}else{sts="";con.close(); throw new Exception("Payment Table Error!!");}

            }

        } catch (Exception ex) {
            Logger.getLogger(WMAS0001_Dao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return sts;
    }


    //for updating patients 
    
    
    
    String update_ipd_patient(String patient_id,String Patient_name,String Age,String gender,String phone,String adhar,String Address,
            String Father_Name,String Relative_name,String Relation,String Relative_mobile_number,String Realtive_address,String dob,String user,String Refering_doctor) throws SQLException{
       
        String sts=Patient_name;
        
        
        
        int i=0;
        int y=0;
        
     
        
              String sql_update = "update mst_ipd_patient set patient_name='"+Patient_name+"',age='"+Age+"'"
                      + ",gender='"+gender+"',adhar='"+adhar+"',address='"+Address+"',father_name='"+Father_Name+"',"
                      + "refer_doctor='"+Refering_doctor+"', phone='"+phone+"' where patient_id='"+patient_id+"'";
             
                ps = con.prepareStatement(sql_update);
                i = ps.executeUpdate();
        
        if(i>0){
            String sql_update_relation = "update patient_relatives set Relative_name='"+Relative_name+"',Relation='"+Relation+"',Relative_number='"+Relative_mobile_number+"',relative_address='"+Realtive_address+"'\n" +
"where patient_id='"+patient_id+"'";
             
                ps = con.prepareStatement(sql_update_relation);
                y = ps.executeUpdate();
        }
        
        if(y>0){
            con.commit();
            con.close();
        }
        
        
        
        return sts;
    }
    
    
    
    
    //for geeting patient details in update mode
public HashMap<String, String> get_doc(String patient_id) {
        HashMap<String, String> al = new HashMap<>();
        try {
            con = new DBCon().getConnection();

            String Sql = "select \n" +
"isnull(msp.patient_id,'')as patient_id,\n" +
"isnull(patient_name,'')as patient_name,\n" +
"isnull(age,'')as age,\n" +
"isnull(gender,'')as gender,\n" +
"isnull(phone,'')as phone,\n" +
"isnull(adhar,'')as adhar,\n" +
"isnull(address,'')as address,\n" +
"isnull(father_name,'')as father_name,\n" +
"isnull(refer_doctor,'')as refer_doc,\n" +
"isnull(pr.Relation,'')as relation,\n" +
"isnull(pr.relative_address,'')as relative_address,\n" +
"isnull(pr.Relative_number,'')as relative_number,\n" +
"isnull(pr.Relative_name,'')as relative_name\n" +
"from mst_ipd_patient msp\n" +
"inner join patient_relatives pr on msp.patient_id=pr.patient_id\n" +
"WHERE msp.patient_id='"+patient_id+"' ";

            ps = con.prepareStatement(Sql);
            System.out.println("===>" + Sql);
            rs = ps.executeQuery();
            if (rs.next()) {

                al.put("patient_id", rs.getString("patient_id"));
                al.put("patient_name", rs.getString("patient_name"));
                al.put("age", rs.getString("age"));
                al.put("gender", rs.getString("gender"));
                al.put("phone", rs.getString("phone"));
                al.put("adhar", rs.getString("adhar"));
                al.put("address", rs.getString("address"));
                al.put("father_name", rs.getString("father_name"));
                al.put("relation", rs.getString("relation"));
                al.put("relative_address", rs.getString("relative_address"));
                al.put("relative_number", rs.getString("relative_number"));

                al.put("relative_name", rs.getString("relative_name"));
                
                  al.put("refer_doc", rs.getString("refer_doc"));
                
       
            } else if (patient_id == null) {
                al.put("patient_id", "");
                al.put("patient_name", "");
                al.put("age","");
                al.put("gender", "");
                al.put("phone", "");
                al.put("adhar", "");
                al.put("address", "");
                al.put("father_name", "");
                al.put("relation", "");
                al.put("relative_address", "");
                al.put("relative_number", "");
                al.put("relative_name", "");
                al.put("refer_doc", "");

            }

        } catch (Exception ex) {
            Logger.getLogger(WMAS0001_Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return al;

    }




    
    
    
    
    
    
    
    
}

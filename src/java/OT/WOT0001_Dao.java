/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package OT;

import Dao.DBCon;
import Laboratory.WLAB0001_dao;
import PatitentPayment.PatitentPaymentDao;
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
public class WOT0001_Dao {

   Connection con;
   PreparedStatement ps;
   ResultSet rs;
   Statement stmt;

   public ArrayList<String> getdata(String cd, String usr) throws SQLDataException {
      ArrayList<String> al = new ArrayList<>();
      try {
         con = new DBCon().getConnection();
         String sql = "";
         if (cd.equals("1")) {

            sql = "select patient_id,patient_name from mst_ipd_patient where pt_sts='Admitted'";

         }
         if (cd.equals("2")) {

            sql = "select ot_id,ot_name from Operation_master order by ot_name asc";

         }

         ps = con.prepareStatement(sql);
         rs = ps.executeQuery();
         while (rs.next()) {

            al.add("<option value='" + rs.getString(1) + "'>" + rs.getString(2) + "</option>");

         }

      } catch (Exception ex) {
         Logger.getLogger(WOT0001_Dao.class.getName()).log(Level.SEVERE, null, ex);
      }

      return al;

   }

   public ArrayList<HashMap<String, String>> get_OT_doc() throws SQLException, IOException {
      ArrayList<HashMap<String, String>> al = new ArrayList<>();
      try {
         con = new DBCon().getConnection();

         String Sql = "select  isnull(doc_code,'')as doc_code,isnull(concat(doc_name_first,' ', doc_name_last),'')as doc_name from mst_doctor";

         ps = con.prepareStatement(Sql);
         //System.out.println("====>stff"+Sql);
         rs = ps.executeQuery();
         while (rs.next()) {
            HashMap<String, String> Map = new HashMap();

            Map.put("doc_code", rs.getString("doc_code"));
            Map.put("doc_name", rs.getString("doc_name"));

            al.add(Map);
         }

      } catch (Exception ex) {
         Logger.getLogger(WLAB0001_dao.class.getName()).log(Level.SEVERE, null, ex);
      }
      return al;

   }

   public ArrayList<HashMap<String, String>> get_Ot_Staff() throws SQLException, IOException {
      ArrayList<HashMap<String, String>> al = new ArrayList<>();
      try {
         con = new DBCon().getConnection();

         String Sql = "select isnull(staff_id,'')as staff_id,isnull(name,'')as name from hospital_staff";

         ps = con.prepareStatement(Sql);
         //System.out.println("====>stff"+Sql);
         rs = ps.executeQuery();
         while (rs.next()) {
            HashMap<String, String> Map = new HashMap();

            Map.put("staff_id", rs.getString("staff_id"));
            Map.put("name", rs.getString("name"));

            al.add(Map);
         }

      } catch (Exception ex) {
         Logger.getLogger(WLAB0001_dao.class.getName()).log(Level.SEVERE, null, ex);
      }
      return al;

   }

//    for saving the ot 
   boolean OT_Save(String patient_id, String[] doc_id, String[] staff_id, String OT_Amount,
           String Amount_paid, String Amount_pending, String user, String operation_name, String ot_date, String Ot_time) throws SQLDataException {
      boolean sts = false;

      int i = 0;
      int z = 0;
      int x = 0;
      int a = 0;
      String Code = "";
      try {

         con = new DBCon().getConnection();
         con.setAutoCommit(false);

         //System.out.println("==yanha thak aya save mne ");
         String Sql_test = "select count(*)+1 as code from operation_th";
         ps = con.prepareStatement(Sql_test);
         rs = ps.executeQuery();
         if (rs.next()) {
            Code = "OT-" + rs.getString("code");
         }

         String Sql = "insert into operation_th(patient_id,ot_date,ot_time,created_by,operation_name,ot_Code,ot_id)Values(?,?,?,?,?,?,?)";
         ps = con.prepareStatement(Sql);
         ps.setString(1, patient_id);
         ps.setString(2, ot_date);
         ps.setString(3, Ot_time);
         ps.setString(4, user);
         ps.setString(5, operation_name);
         ps.setString(6, Code);
         ps.setString(7, operation_name);
         i = ps.executeUpdate();

         if (i > 0) {
            String Sql_doc = "insert into operation_th_doc(patient_id,doc_id,created_by,ot_id,ot_Code)Values(?,?,?,?,?)";
            ps = con.prepareStatement(Sql_doc);

            for (String doc : doc_id) {
               ps.setString(1, patient_id);
               ps.setString(2, doc);
               ps.setString(3, user);
               ps.setString(4, operation_name);
               ps.setString(5, Code);
               z = ps.executeUpdate();

            }
         }

         if (z > 0) {
            String Sql_staff = "insert into operation_th_staff(patient_id,staff_id,created_by,ot_id,ot_Code)Values(?,?,?,?,?)";
            ps = con.prepareStatement(Sql_staff);

            for (String staff : staff_id) {
               ps.setString(1, patient_id);
               ps.setString(2, staff);
               ps.setString(3, user);
               ps.setString(4, operation_name);
               ps.setString(5, Code);
               x = ps.executeUpdate();
            }

         }

         if (x > 0) {
            String Sql_staff = "insert into OT_rate(patient_id,Ot_total_amount,ot_paid_amount,ot_pending_amount,created_by,ot_id,ot_Code)Values(?,?,?,?,?,?,?)";
            ps = con.prepareStatement(Sql_staff);
            ps.setString(1, patient_id);
            ps.setString(2, OT_Amount);
            ps.setString(3, Amount_paid);
            ps.setString(4, Amount_pending);
            ps.setString(5, user);
            ps.setString(6, operation_name);
            ps.setString(7, Code);
            a = ps.executeUpdate();
         }

         if (a > 0) {

            HashMap<String, Object> patitent_paymen_data = new HashMap<>();
            patitent_paymen_data.put("patitent_id", patient_id);
            patitent_paymen_data.put("payment_id", Code);
            patitent_paymen_data.put("payment_type", "OT Fees");
            patitent_paymen_data.put("mode", "OT");
            patitent_paymen_data.put("create_by", user);

            patitent_paymen_data.put("pay_amt", OT_Amount);
            patitent_paymen_data.put("paid_amt", Amount_paid);
            patitent_paymen_data.put("pending_amt", Amount_pending);

            boolean pp_status = new PatitentPaymentDao().savePayment(patitent_paymen_data);
            if (pp_status) {
               con.commit();
               sts = true;
            } else {
               sts = false;
               throw new Exception("Payment Table Error!!");
            }

         } else {
            sts = false;
         }

      } catch (Exception e) {
         Logger.getLogger(WLAB0001_dao.class.getName()).log(Level.SEVERE, null, e);
      }

      return sts;
   }

   public ArrayList<HashMap<String, String>> get_OT_details() throws SQLException, IOException {
      ArrayList<HashMap<String, String>> al = new ArrayList<>();
      try {
         con = new DBCon().getConnection();

         String Sql = "select isnull(oth.patient_id,'')as patient_id,isnull(format(ot_date,'dd/MM/yyyy'),'')as ot_date,\n"
                 + "isnull(ot_time,'')as ot_time,isnull(ot_status,'')as ot_status,isnull(operation_name,'')as ot_name ,\n"
                 + "\n"
                 + "isnull(STUFF((SELECT ', ' + convert(varchar(10), concat(md.doc_name_first,' ',md.doc_name_last), 120)FROM  operation_th_doc otd\n"
                 + "inner join mst_doctor md  on otd.doc_id=md.doc_code where otd.patient_id=oth.patient_id  FOR XML PATH ('')), 1, 1, ''),'N/A')  AS doc,\n"
                 + "\n"
                 + "isnull(STUFF((SELECT ', ' + convert(varchar(10), hs.Name, 120)FROM  operation_th_staff ots\n"
                 + "inner join hospital_staff hs  on ots.staff_id=hs.staff_id where ots.patient_id=oth.patient_id  FOR XML PATH ('')), 1, 1, ''),'N/A')  AS staff_name,\n"
                 + "isnull(Ot_total_amount,0)as ot_total_amount,isnull(ot_paid_amount,0)as ot_paid_amount,isnull(ot_paid_amount,0)as ot_pending_amount,\n"
                 + "isnull(mip.patient_name,'')as patient_name\n"
                 + "\n"
                 + "\n"
                 + "from operation_th oth left join OT_rate otr on oth.patient_id=otr.patient_id left join mst_ipd_patient mip on oth.patient_id=mip.patient_id  ";

         ps = con.prepareStatement(Sql);
         //System.out.println("====>stff"+Sql);
         rs = ps.executeQuery();
         while (rs.next()) {
            HashMap<String, String> Map = new HashMap();

            Map.put("patient_id", rs.getString("patient_id"));
            Map.put("ot_date", rs.getString("ot_date"));
            Map.put("ot_time", rs.getString("ot_time"));
            Map.put("ot_status", rs.getString("ot_status"));
            Map.put("ot_name", rs.getString("ot_name"));
            Map.put("doc", rs.getString("doc"));
            Map.put("staff_name", rs.getString("staff_name"));
            Map.put("ot_total_amount", rs.getString("ot_total_amount"));
            Map.put("ot_paid_amount", rs.getString("ot_paid_amount"));
            Map.put("ot_pending_amount", rs.getString("ot_pending_amount"));
            Map.put("patient_name", rs.getString("patient_name"));

            al.add(Map);
         }

      } catch (Exception ex) {
         Logger.getLogger(WLAB0001_dao.class.getName()).log(Level.SEVERE, null, ex);
      }
      return al;

   }

   String getOtRate(String ot) {
      String OtRate="";
      try {
         con = new DBCon().getConnection();
         String Sql = "select isnull(ot_rate,0)as ot_rate from Operation_master where ot_id='"+ot+"'";
         ps = con.prepareStatement(Sql);
         //System.out.println("====>stff"+Sql);
         rs = ps.executeQuery();
         if(rs.next()) {
            OtRate=rs.getString("ot_rate");
         }
      
      } catch (Exception ex) {
         Logger.getLogger(WLAB0001_dao.class.getName()).log(Level.SEVERE, null, ex);
      }
      return OtRate;
   }

}

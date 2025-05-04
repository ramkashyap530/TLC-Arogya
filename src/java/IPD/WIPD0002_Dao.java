/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package IPD;

import Dao.DBCon;
import Laboratory.WLAB0001_dao;
import Master.WMAS0001_Dao;
import PatitentPayment.PatitentPaymentDao;
import Tally_Erp.TallyCreateVoucher;
import java.io.IOException;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bouncycastle.asn1.eac.EACTags;

/**
 *
 * @author Tarun
 */
public class WIPD0002_Dao {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Statement stmt;

    public ArrayList<HashMap<String, String>> get_total_oprec(String[] arr) throws SQLException, IOException {
        ArrayList<HashMap<String, String>> al = new ArrayList<>();
        try {
            con = new DBCon().getConnection();

            String Sql = " select distinct isnull(ipd_no,'')as ipd_no,isnull(mip.patient_id,'')as patient_id,isnull(concat(patient_name,' / ',relation_of,' ',father_name),'')as patient_name,\n" +
"isnull(format(admision_date,'dd/MM/yyyy'),'')as admision_date,\n" +
"isnull(Concat(doc_name_first,' ',doc_name_last),'')as doctor,isnull(phone,'')as phone,\n" +
"concat(isnull(category_name,''),'/' ,isnull(Room_number_name,''))as room_name,\n" +
"isnull(rb.room_bed_id,'')as bed_number,\n" +
"isnull(td.tpa_name,'')as tpa_name,\n" +
"isnull(mip.UHID,0)as UHID,\n" +
"isnull(mip.ipd_no,0)as ipd,\n" +
"isnull(pt_sts,'')as pt_sts,isnull(cc.category_name,'')as category_name\n" +
",isnull((select sum(ppd.pay_amt) from patitent_payment_details ppd where ppd.patitent_id=mip.patient_id),0) as tot_amt\n" +
",isnull((select sum(ppd.pay_amt) from patitent_payment_details ppd where ppd.patitent_id=mip.patient_id and payment_type='Test Lab Fees'),0) as lab_test_n\n" +
",isnull((select sum(ppd.pay_amt) from patitent_payment_details ppd where ppd.patitent_id=mip.patient_id and payment_type='OT Fees'),0) as operation_name,isnull((select sum(ppd.paid_amt)\n" +
"from patitent_payment_details ppd where ppd.patitent_id=mip.patient_id),0) as tot_paid_amt\n" +
",isnull((select sum(ppd.paid_amt) from patitent_payment_details ppd where ppd.patitent_id=mip.patient_id and payment_type='Test Lab Fees'),0) as lab_test_paid_amt\n" +
",isnull((select sum(ppd.paid_amt) from patitent_payment_details ppd where ppd.patitent_id=mip.patient_id and payment_type='OT Fees'),0) as operation_paid_amt\n" +
"from mst_ipd_patient mip left join room_beds rb on mip.room_bed_id=rb.room_bed_id left join mst_rooms ms on rb.room_id=ms.Room_id\n" +
"left join mst_doctor md on mip.doctor_id=md.Doc_code\n" +
"left join charge_category cc on cc.category_id=ms.room_category\n" +
"left join TPA_Details td on td.tpa_hos_code=mip.tpa_id \n" +
"where mip.hospital_code='"+ arr[0] +"'  and  pt_sts='" + arr[4] + "' ";

            if (arr[1] != null && arr[2] != "") {
                Sql += " and admision_date between '" + arr[1] + "' and '" + arr[2] + "'";

            }

            if (arr[4] != null && arr[4] != "") {
                Sql += " and pt_sts='" + arr[4] + "'";

            }

            if (arr[3] != null && arr[3] != "") {
                Sql += "and mip.patient_id='" + arr[3] + "'";

            }
         Sql+=" order by patient_id desc";  

            System.out.println("==== RAM  >" + Sql);

            ps = con.prepareStatement(Sql);
            //System.out.println("====>stff"+Sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                HashMap<String, String> Map = new HashMap();

                Map.put("patient_id", rs.getString("patient_id"));
                Map.put("patient_name", rs.getString("patient_name"));
                Map.put("admision_date", rs.getString("admision_date"));
                Map.put("doctor", rs.getString("doctor"));
                Map.put("phone", rs.getString("phone"));
                Map.put("room_name", rs.getString("room_name"));
                Map.put("bed_number", rs.getString("bed_number"));
                Map.put("pt_sts", rs.getString("pt_sts"));
                Map.put("lab_test_n", rs.getString("lab_test_n"));
                Map.put("operation_name", rs.getString("operation_name"));
                Map.put("tot_amt", rs.getString("tot_amt"));
                Map.put("tot_paid_amt", rs.getString("tot_paid_amt"));
                Map.put("lab_test_paid_amt", rs.getString("lab_test_paid_amt"));
                Map.put("operation_paid_amt", rs.getString("operation_paid_amt"));
                Map.put("ipd_no",rs.getString("ipd_no"));
                Map.put("UHID",rs.getString("UHID"));
                Map.put("tpa_name",rs.getString("tpa_name"));
                al.add(Map);
            }

        } catch (Exception ex) {
            Logger.getLogger(WIPD0002_Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return al;

    }

    public ArrayList<HashMap<String, String>> get_test_hi(String Patient_id) throws SQLException, IOException {
        ArrayList<HashMap<String, String>> al = new ArrayList<>();
        try {
            con = new DBCon().getConnection();

            String Sql = "select isnull(lab_test_name,'')as test_name ,isnull(format(done_date,'dd/MM/yyyy'),'')done_date,\n"
                    + "isnull(lta.test_rate,0)as rates,\n"
                    + "isnull(lta.test_paid_amt,0)as test_paid_amt,\n"
                    + "isnull(lta.test_pending_amt,0)as test_pending_amt,\n"
                    + "(case  when isnull(lta.test_pending_amt,0)=0 then 'Done' else 'Pending' end)as done_sts\n"
                    + "from lab_test_assigned lta\n"
                    + "inner join laboratory_test lt on lta.test_id=lt.lab_test_id\n"
                    + "where lta.patient_id='" + Patient_id + "' ";

            ps = con.prepareStatement(Sql);
            System.out.println("==== RAM >" + Sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                HashMap<String, String> Map = new HashMap();

                Map.put("test_name", rs.getString("test_name"));
                Map.put("done_date", rs.getString("done_date"));
                Map.put("rates", rs.getString("rates"));
                Map.put("done_sts", rs.getString("done_sts"));
                Map.put("test_paid_amt", rs.getString("test_paid_amt"));
                Map.put("test_pending_amt", rs.getString("test_pending_amt"));

                al.add(Map);
            }

        } catch (Exception ex) {
            Logger.getLogger(WIPD0002_Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return al;

    }

    public ArrayList<HashMap<String, String>> get_OT_hi(String Patient_id) throws SQLException, IOException {
        ArrayList<HashMap<String, String>> al = new ArrayList<>();
        try {
            con = new DBCon().getConnection();

            String Sql = "select isnull(operation_name,'')as operation_name,\n"
                    + "isnull(ot_total_amount,0)as ot_rate,isnull(ot_pending_amount,0)as pending_amount,\n"
                    + "isnull(format(otr.created_date,'dd/MM/yyyy'),'')as created_date\n"
                    + "from operation_th oth inner join OT_rate otr on oth.patient_id=otr.patient_id and oth.ot_id=otr.ot_id where oth.patient_id='" + Patient_id + "' ";

            ps = con.prepareStatement(Sql);
            System.out.println("====>stff"+Sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                HashMap<String, String> Map = new HashMap();

                Map.put("operation_name", rs.getString("operation_name"));
                Map.put("ot_rate", rs.getString("ot_rate"));
                Map.put("pending_amount", rs.getString("pending_amount"));
                Map.put("created_date", rs.getString("created_date"));

                al.add(Map);
            }

        } catch (Exception ex) {
            Logger.getLogger(WIPD0002_Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return al;

    }

//     for discharge 
    String discharge_patient(String patient_id_Modal, String hospital_code, String Diagnosis, String condition, String Vitals, String History, String user) {
        String sts = "";
        int i = 0;
        int y = 0;
        int z = 0;
        int a = 0;
        int r = 0;

        String code = "";

        try {
            con = new DBCon().getConnection();
            con.setAutoCommit(false);

            String Sql = "insert into patient_discharge(patient_id,date_of_discharge,time_of_discharge,condition_on_discharge,vital_on_discharge,history_of_present_illeness,discharged_by)values(?,getdate(),CURRENT_TIMESTAMP,?,?,?,?)";
            ps = con.prepareStatement(Sql);
            ps.setString(1, patient_id_Modal);
            ps.setString(2, condition);
            ps.setString(3, Vitals);
            ps.setString(4, History);
            ps.setString(5, user);

            i = ps.executeUpdate();

            if (i >= 0) {
                String sql_update = "update mst_IPd_patient set pt_sts='Discharged' where patient_id='" + patient_id_Modal + "'";
                ps = con.prepareStatement(sql_update);
                z = ps.executeUpdate();

            }

            if (z > 0) {
                con.commit();
                sts = patient_id_Modal;

            }

        } catch (Exception ex) {
            Logger.getLogger(WIPD0002_Dao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return sts;
    }

    
    public ArrayList<HashMap<String, String>> get_dischaege(String Patient_id) throws SQLException, IOException {
        ArrayList<HashMap<String, String>> al = new ArrayList<>();
        try {
            con = new DBCon().getConnection();

            String Sql = "select * from patient_discharge where patient_id='" + Patient_id + "' ";

            ps = con.prepareStatement(Sql);
            //System.out.println("====>stff"+Sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                HashMap<String, String> Map = new HashMap();

                Map.put("patient_id", rs.getString("patient_id"));

                al.add(Map);
            }

        } catch (Exception ex) {
            Logger.getLogger(WIPD0002_Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return al;

    }

    public ArrayList<HashMap<String, String>> get_room_charge_hi(String Patient_id) throws SQLException, IOException {
        ArrayList<HashMap<String, String>> al = new ArrayList<>();
        try {
            con = new DBCon().getConnection();

            String Sql = "DECLARE @discharge_date AS DATETIME = GETDATE();\n" +
"DECLARE @patient_id AS VARCHAR(50) = '"+Patient_id+"';\n" +
"\n" +
"-- Final Query\n" +
"SELECT *,\n" +
"       (per_day_charge * number_of_days) AS tot_room_amt\n" +
"FROM (\n" +
"    SELECT \n" +
"        mip.patient_id,\n" +
"        mip.patient_type,\n" +
"        mip.room_bed_id AS current_bed_id,\n" +
"        ISNULL(pl.changed_room_bed_id, pl.previous_room_bed_id) AS move_bed_id,\n" +
"        ISNULL(pl.change_date, mip.admision_date) AS move_bed_datetime,\n" +
"        ISNULL(rb.room_id, '') AS move_room,\n" +
"\n" +
"        ISNULL(pl.change_date, mip.admision_date) AS start_time,\n" +
"        ISNULL(pl.end_date, @discharge_date) AS end_time,\n" +
"\n" +
"        -- Per-day charge taken from patient_location.bed_amount\n" +
"        CASE \n" +
"            WHEN LOWER(mip.patient_type) = 'ayushmaan' THEN 0\n" +
"            WHEN LOWER(mip.patient_type) = 'tpa' THEN ISNULL(pl.bed_amount, 0)\n" +
"            ELSE ISNULL(pl.bed_amount, 0)\n" +
"        END AS per_day_charge,\n" +
"\n" +
"        FORMAT(ISNULL(pl.change_date, mip.admision_date), 'dd/MM/yyyy hh:mm tt') AS started_datetime,\n" +
"        FORMAT(ISNULL(pl.end_date, @discharge_date), 'dd/MM/yyyy hh:mm tt') AS ended_datetime,\n" +
"\n" +
"        -- Improved Number of Days Calculation\n" +
"        CASE \n" +
"            WHEN DATEDIFF(DAY, ISNULL(pl.change_date, mip.admision_date), ISNULL(pl.end_date, @discharge_date)) = 0 THEN 1\n" +
"            WHEN CAST(ISNULL(pl.end_date, @discharge_date) AS TIME) > CAST(ISNULL(pl.change_date, mip.admision_date) AS TIME) THEN\n" +
"                DATEDIFF(DAY, ISNULL(pl.change_date, mip.admision_date), ISNULL(pl.end_date, @discharge_date)) + 1\n" +
"            ELSE\n" +
"                DATEDIFF(DAY, ISNULL(pl.change_date, mip.admision_date), ISNULL(pl.end_date, @discharge_date))\n" +
"        END AS number_of_days\n" +
"\n" +
"    FROM mst_ipd_patient mip \n" +
"    INNER JOIN patient_location pl ON pl.patient_id = mip.patient_id\n" +
"    INNER JOIN room_beds rb ON rb.room_bed_id = ISNULL(pl.changed_room_bed_id, pl.previous_room_bed_id)\n" +
"    WHERE mip.patient_id = @patient_id\n" +
") AS tab1\n" +
"ORDER BY move_bed_datetime ASC;";

            ps = con.prepareStatement(Sql);
            System.out.println("===TARUN=>stff" + Sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                HashMap<String, String> Map = new HashMap();

                Map.put("move_bed_id", rs.getString("move_bed_id"));
                Map.put("start_time", rs.getString("start_time"));
                Map.put("end_time", rs.getString("end_time"));
                Map.put("number_of_days", rs.getString("number_of_days"));
                Map.put("tot_room_amt", rs.getString("tot_room_amt"));

                al.add(Map);
            }

        } catch (Exception ex) {
            Logger.getLogger(WIPD0002_Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return al;

    }

//for payments
    String ipd_payment_vouchers(String Toatl_amount_till_now, String Amount_paid, 
            String Amount_collecting, String patient_id_mod, String user, String Remark,String pay_type,String Checque_number,String Card_number,
             String UPI_type,String transcation_id,String recieved_from) {
        String sts = "";
        int i = 0;
        int y = 0;
        int z = 0;
        int a = 0;
        int r = 0;

        String VOC_code = "";
  
        try {
            con = new DBCon().getConnection();
            con.setAutoCommit(false);

            String sql_voc = "select count(*)+1 as voc from ipd_patient_payment";
            ps = con.prepareStatement(sql_voc);
            rs = ps.executeQuery();
            if (rs.next()) {
                VOC_code = "VOC_" + rs.getString("voc");
            }

            String Sql = "insert into ipd_patient_payment(patient_id,amount,payment_date,taken_by,voucher_id,remark"
                    + ",paytype,cheque_number,card_number,upi_type,transcation_id,recieved_from)values(?,?,getdate(),?,?,?,?,?,?,?,?,?)";
            ps = con.prepareStatement(Sql);
            ps.setString(1, patient_id_mod);
            ps.setString(2, Amount_collecting);
            ps.setString(3, user);
            ps.setString(4, VOC_code);
            ps.setString(5, Remark);
            ps.setString(6, pay_type);
            ps.setString(7, Checque_number);
            ps.setString(8, Card_number);
            ps.setString(9, UPI_type);
            ps.setString(10, transcation_id);
            ps.setString(11, recieved_from);

            i = ps.executeUpdate();
           
           
            //for tally entry 
            if(i>0){
             TallyCreateVoucher TallyCreateVoucher=new TallyCreateVoucher();
             TallyCreateVoucher.Create_Voucher_in_Tally(Amount_collecting,Remark);
            }
            
            
            
            
            if (i >= 0) {
                   System.out.println("----->payment me aya");
                
                HashMap<String, Object> patitent_paymen_data = new HashMap<>();
                patitent_paymen_data.put("patitent_id", patient_id_mod);
                patitent_paymen_data.put("payment_id", "VOC");
                patitent_paymen_data.put("payment_type", "PAY VOCHER");
                patitent_paymen_data.put("mode", "Payment_Voucher");
                patitent_paymen_data.put("create_by", user);

                patitent_paymen_data.put("pay_amt","0");
                patitent_paymen_data.put("paid_amt",Amount_collecting);
                patitent_paymen_data.put("pending_amt", 0);

            
                boolean pp_status = new PatitentPaymentDao().savePayment(patitent_paymen_data);
                    if(pp_status)
                    {con.commit();
                sts = patient_id_mod;}else{sts=""; throw new Exception("Payment Table Error!!");}

                
            }

        } catch (Exception ex) {
            Logger.getLogger(WIPD0002_Dao.class.getName()).log(Level.SEVERE, null, ex);
        }

       
                return VOC_code;
    }

//  for gettinng voucers 
    public ArrayList<HashMap<String, String>> get_total_ipd_payemt(String[] arr) throws SQLException, IOException {
        ArrayList<HashMap<String, String>> al = new ArrayList<>();
        try {
            con = new DBCon().getConnection();

            String Sql = "select isnull(patient_id,'')as patient_id,isnull(amount,'')as amount,\n"
                    + "isnull(format(payment_date,'dd/MM/yyyy'),'')as payment_date, \n"
                    + "isnull(voucher_id,'')as voucher\n"
                    + "from ipd_patient_payment where 1=1";

            ps = con.prepareStatement(Sql);
         
            rs = ps.executeQuery();
            while (rs.next()) {
                HashMap<String, String> Map = new HashMap();

                Map.put("patient_id", rs.getString("patient_id"));
                Map.put("amount", rs.getString("amount"));
                Map.put("payment_date", rs.getString("payment_date"));
                Map.put("voucher", rs.getString("voucher"));

                al.add(Map);
            }

        } catch (Exception ex) {
            Logger.getLogger(WIPD0002_Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return al;

    }

    ArrayList<HashMap<String, String>> get_OTher_hi(String get) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        ArrayList<HashMap<String, String>> al = new ArrayList<>();
        return al;
    }

    public String get_current_room(String Patient_id) throws IOException, SQLException {
        String room = "";

        con = new DBCon().getConnection();
        String Sql = "select isnull(concat(cc.category_name,' - ',Room_number_name,' - ',mip.room_bed_id),'')as current_bed from mst_ipd_patient mip inner join room_beds rb on mip.room_bed_id=rb.room_bed_id \n"
                + "inner join mst_rooms mr on mr.Room_id=rb.room_id inner join charge_category cc on mr.room_category=cc.category_id\n"
                + "where mip.patient_id='" + Patient_id + "'";

        ps = con.prepareStatement(Sql);
        rs = ps.executeQuery();
        if (rs.next()) {
            room = rs.getString("current_bed");
        } else {
            room = "Current Room NOt Available";
        }

        return room;
    }

    
    //for changing bed of patient
    String update_room(String current_bed,String changed_bed,String patient_id,String user,String change_date,String remark,String charge_amount,String cahrge_category) throws SQLException, IOException {
      String Patient="";
      int i=0;
      int y=0;
      int z=0;
      int a=0;
      int g=0;
      
      
      //for converting the date 
      
        LocalDateTime localDateTime = LocalDateTime.parse(change_date);

        // Format for SQL Server (YYYY-MM-DD HH:MM:SS)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String sqlDateTime = localDateTime.format(formatter);
      
      
      con = new DBCon().getConnection();
        con.setAutoCommit(false);
      String Sql_mip = "update mst_ipd_patient set room_bed_id='"+changed_bed+"' where patient_id='"+patient_id+"'";
      ps=con.prepareStatement(Sql_mip);
      
      i=ps.executeUpdate();
      
      if(i>=0){
          String Sql_curre = "update room_beds set patient_id='' where room_bed_id='"+current_bed+"'";
      ps=con.prepareStatement(Sql_curre);
      
      
      y=ps.executeUpdate();
      
          System.out.println("-------change-"+Sql_curre);
      }
      
      if(y>=0){
           String Sql_changed = "update room_beds set patient_id='"+patient_id+"' where room_bed_id='"+changed_bed+"'";
      ps=con.prepareStatement(Sql_changed);
      
      z=ps.executeUpdate();
      }
      
      //for updating the cureent bed end date 
            if(z>=0){
           String Sql_changed_end_date = "update patient_location set end_date='"+sqlDateTime+"' where patient_id='"+patient_id+"' and changed_room_bed_id='"+current_bed+"'";
      ps=con.prepareStatement(Sql_changed_end_date);
      
      a=ps.executeUpdate();
      }
      
      
      if(a>=0){
          String Sql_location = "insert into patient_location(patient_id,previous_room_bed_id,changed_room_bed_id,change_date,change_by,start_date,end_date,bed_amount,room_category,room_number,status)\n" +
           "Values(?,?,?,?,?,?,?,?,?,?,'Unauthorized')";
      ps=con.prepareStatement(Sql_location);
      ps.setString(1, patient_id);
      ps.setString(2, current_bed);
      ps.setString(3, changed_bed);
      ps.setString(4, sqlDateTime);
      ps.setString(5, user);
      ps.setString(6, sqlDateTime);
      ps.setString(7, null);
      ps.setString(8, charge_amount);
      ps.setString(9, cahrge_category);
      ps.setString(10, "");
     
    
      g=ps.executeUpdate();
      }
      
      if(a>=0){
          con.commit();
          Patient=patient_id;
      }
      
 
      
      return Patient;
    }
    
    
    
//    for geeting other chareged Assigned
    
    public ArrayList<HashMap<String, String>> get_other_charge_data() throws SQLException, IOException {
    ArrayList<HashMap<String, String>> al = new ArrayList<>();
    try {
      con = new DBCon().getConnection();

      String Sql = "select isnull(charge_id,'')as charge_id,\n" +
"isnull(charge_name,'')as charge_name,\n" +
"isnull(charge_rate,'')as charge_rate\n" +
"from mst_other_charge ";

      ps = con.prepareStatement(Sql);
  
      rs = ps.executeQuery();
      while (rs.next()) {
        HashMap<String, String> Map = new HashMap();

        Map.put("charge_id", rs.getString("charge_id"));
        Map.put("charge_name", rs.getString("charge_name"));
        Map.put("charge_rate", rs.getString("charge_rate"));
       

        al.add(Map);
      }

    } catch (Exception ex) {
      Logger.getLogger(WIPD0002_Dao.class.getName()).log(Level.SEVERE, null, ex);
    }
    return al;

  }
    
    
    
//    for saving the other charge data 
      boolean Save_other_charges(String[] other_charge_id, String Patient_id, String user, String Total_Amount, String Amount_paid, String Amount_pending
  ,String[] other_paid_amt, String[] other_pending_amt) throws SQLDataException {
    boolean sts = false;

    int i = 0;
    int z = 0;
    String Code = "";
    String Pay_his_code="";
    try {

      con = new DBCon().getConnection();
      con.setAutoCommit(false);

      String Sql_lab_code = "select count(*)+1 as code from total_other_charge_payment";
      ps = con.prepareStatement(Sql_lab_code);
      rs = ps.executeQuery();
      if (rs.next()) {
        Code = "OTHER_CHARGR_" + rs.getString("code");
        Pay_his_code=Code+"/"+rs.getString("code");
      }

      String Sql = "insert into other_chargewise_payment(patient_id,charge_id,paid_amount,"
              + "left_amount,created_by,payment_id,payment_history_id)Values(?,?,?,?,?,?,?)";
      ps = con.prepareStatement(Sql);
      int index=0;
      for (String cd : other_charge_id) {

        ps.setString(1, Patient_id);
        ps.setString(2, cd);
        ps.setString(3, other_paid_amt[index]);
        ps.setString(4, other_pending_amt[index]);
        ps.setString(5,user);
        ps.setString(6,Code);
        ps.setString(7,Pay_his_code);
       

        ps.addBatch();
        i++;
        index++;
      }
      sts = ps.executeBatch().length > 0;
      if (sts) {

        String Sql_other_pay = "insert into total_other_charge_payment(payment_id,patient_id,total_amt,total_paid,total_pending,created_by)"
                + "Values(?,?,?,?,?,?)";
        ps = con.prepareStatement(Sql_other_pay);
        ps.setString(1, Code);
        ps.setString(2, Patient_id);
        ps.setString(3, Total_Amount);
        ps.setString(4, Amount_paid);
        ps.setString(5, Amount_pending);
        ps.setString(6, user);

        z = ps.executeUpdate();
      }

      if (z >= 0) {

        HashMap<String, Object> patitent_paymen_data = new HashMap<>();
                patitent_paymen_data.put("patitent_id", Patient_id);
                patitent_paymen_data.put("payment_id", Code);
                patitent_paymen_data.put("payment_type", "Other Charge Fees");
                patitent_paymen_data.put("mode", "Other_charge");
                patitent_paymen_data.put("create_by", user);

                patitent_paymen_data.put("pay_amt", Total_Amount);
                patitent_paymen_data.put("paid_amt", Amount_paid);
                patitent_paymen_data.put("pending_amt",Amount_pending);

                boolean pp_status = new PatitentPaymentDao().savePayment(patitent_paymen_data);
                    if(pp_status){con.commit();sts = true;}
                    else{sts=false; throw new Exception("Payment Table Error!!");}
        
      }

    } catch (Exception e) {
      Logger.getLogger(WIPD0002_Dao.class.getName()).log(Level.SEVERE, null, e);
    }

    return sts;
  }
//for geeting pending charge 
      
      public ArrayList<HashMap<String, String>> get_other_charge_data_pending_amt(String Patient_id) throws SQLException, IOException {
    ArrayList<HashMap<String, String>> al = new ArrayList<>();
    try {
      con = new DBCon().getConnection();

      String Sql = "select isnull(patient_id,'')as patient_id,\n" +
"isnull(charge_name,'')as charge_name,\n" +
"isnull((select charge_rate from mst_other_charge moc where moc.charge_id=ocp.charge_id),0)as total_charge,\n" +
"sum(paid_amount)as paid_amount,\n" +
"sum(left_amount)as left_amount,\n" +
"isnull(format(ocp.create_date,'dd/MM/yyyy'),'')as charge_date,\n" +
"isnull(payment_id,'')as payment_id,isnull(payment_history_id,'')as pay_his_id\n" +
"from  other_chargewise_payment  ocp inner join mst_other_charge moc on ocp.charge_id=moc.charge_id where ocp.patient_id='"+Patient_id+"'\n" +
"group by ocp.patient_id,ocp.charge_id,moc.charge_name,ocp.payment_history_id,ocp.create_date,payment_id,payment_history_id";

      ps = con.prepareStatement(Sql);
  
      rs = ps.executeQuery();
      while (rs.next()) {
        HashMap<String, String> Map = new HashMap();

        Map.put("patient_id", rs.getString("patient_id"));
        Map.put("charge_name", rs.getString("charge_name"));
        Map.put("total_charge", rs.getString("total_charge"));
        Map.put("paid_amount", rs.getString("paid_amount"));
        Map.put("left_amount", rs.getString("left_amount"));
        Map.put("charge_date", rs.getString("charge_date"));
        Map.put("payment_id", rs.getString("payment_id"));
        Map.put("pay_his_id", rs.getString("pay_his_id"));
       

        al.add(Map);
      }

    } catch (Exception ex) {
      Logger.getLogger(WIPD0002_Dao.class.getName()).log(Level.SEVERE, null, ex);
    }
    return al;

  }
      
      
      //for other charge history 
      
    ArrayList<HashMap<String, String>> get_OC_hi(String Patient_id) {
        ArrayList<HashMap<String, String>> al = new ArrayList<>();
        try {
            this.con = new DBCon().getConnection();
            String Sql = "select add_oc_code,aocd.oc_code,moc.oc_name,aocd.oc_amt_pay,aocd.oc_qty,aocd.oc_final_amt,aocd.oc_amt_paid,aocd.oc_amt_left\nfrom Add_other_charge_details aocd\ninner "
                    + "join mst_other_charge moc on moc.oc_code=aocd.oc_code\nwhere aocd.patient_id='" + Patient_id + "'";
            this.ps = this.con.prepareStatement(Sql);
            this.rs = this.ps.executeQuery();
            while (this.rs.next()) {
                HashMap<String, String> Map = new HashMap<>();
                Map.put("oc_name", this.rs.getString("oc_name"));
                Map.put("oc_final_amt", this.rs.getString("oc_final_amt"));
                Map.put("oc_amt_left", this.rs.getString("oc_amt_left"));
                al.add(Map);
            }
        } catch (Exception ex) {
            Logger.getLogger(WIPD0002_Dao.class.getName()).log(Level.SEVERE, (String) null, (Throwable) ex);
        }
        return al;
    }
    
    
   //for dischrge 
    ArrayList<HashMap<String, String>> get_discharge_patients() {
        ArrayList<HashMap<String, String>> al = new ArrayList<>();
        try {
            this.con = new DBCon().getConnection();
            this.ps = this.con.prepareStatement("");
            this.rs = this.ps.executeQuery();
            while (this.rs.next()) {
                HashMap<String, String> Map = new HashMap<>();
                Map.put("oc_name", this.rs.getString("oc_name"));
                Map.put("oc_final_amt", this.rs.getString("oc_final_amt"));
                Map.put("oc_amt_left", this.rs.getString("oc_amt_left"));
                al.add(Map);
            }
        } catch (Exception ex) {
            Logger.getLogger(WIPD0002_Dao.class.getName()).log(Level.SEVERE, (String) null, (Throwable) ex);
        }
        return al;
    }
    
    
    
    
    //for geeting all bills
    
    ArrayList<HashMap<String, String>> get_all_bill(String Patient_id,String BIll_NO) {
        ArrayList<HashMap<String, String>> al = new ArrayList<>();
        try {
            this.con = new DBCon().getConnection();
            String Sql = "select isnull(Bill_no,'')as bill_no,isnull(Patient_Id,'')as patient_id,isnull(Bill_gross_amount,'')as Bill_gross_amount,\n" +
"isnull(bill_discount,'')as bill_discount,isnull(Bill_final_paid,'')as final_bill_paid,\n" +
"isnull(Bill_status,'')as Bill_status,isnull(Real_Name,'')as created_by,isnull(created_date,'')as create_date\n" +
"from ipd_final_bill ifb inner join login_info lf on ifb.created_by=lf.user_id where 1=1";
            this.ps = this.con.prepareStatement(Sql);
            this.rs = this.ps.executeQuery();
            while (this.rs.next()) {
                HashMap<String, String> Map = new HashMap<>();
                Map.put("bill_no", this.rs.getString("bill_no"));
                Map.put("patient_id", this.rs.getString("patient_id"));
                Map.put("Bill_gross_amount", this.rs.getString("Bill_gross_amount"));
                Map.put("bill_discount", this.rs.getString("bill_discount"));
                Map.put("final_bill_paid", this.rs.getString("final_bill_paid"));                
                Map.put("Bill_status", this.rs.getString("Bill_status"));
                Map.put("created_by", this.rs.getString("created_by"));
                Map.put("create_date", this.rs.getString("create_date"));
                
                
                al.add(Map);
            }
        } catch (Exception ex) {
            Logger.getLogger(WIPD0002_Dao.class.getName()).log(Level.SEVERE, (String) null, (Throwable) ex);
        }
        return al;
    }
    
    //for approving the BIlls 
    
    String Approve_BILL(String Patient_id,String Bill_no) throws SQLException, IOException {
      
      int i=0;
      int y=0;
      
      String sts="";
     
      
      
      con = new DBCon().getConnection();
        con.setAutoCommit(false);
      String Sql_mip = "update ipd_final_bill set Bill_status='Approved' where Patient_Id='"+Patient_id+"' and Bill_no='"+Bill_no+"'";
     
      
        System.out.println("=========>"+Sql_mip);
      ps=con.prepareStatement(Sql_mip);
      
      i=ps.executeUpdate();
        System.out.println("========UPPDATE=====");
      
         if( i>=0){
          con.commit();
       
      }

      return sts;
    }
    

    
    //for checking bed shift with in the room of the patieent
    
     ArrayList<HashMap<String, String>> Check_Same_room_bed_shift(String Patient_id,String room_category_id) {
        ArrayList<HashMap<String, String>> al = new ArrayList<>();
        try {
            this.con = new DBCon().getConnection();
            String Sql = "";
            this.ps = this.con.prepareStatement(Sql);
            this.rs = this.ps.executeQuery();
            while (this.rs.next()) {
                HashMap<String, String> Map = new HashMap<>();
                Map.put("bill_no", this.rs.getString("bill_no"));
                Map.put("patient_id", this.rs.getString("patient_id"));
                Map.put("Bill_gross_amount", this.rs.getString("Bill_gross_amount"));
                Map.put("bill_discount", this.rs.getString("bill_discount"));
                Map.put("final_bill_paid", this.rs.getString("final_bill_paid"));                
                Map.put("Bill_status", this.rs.getString("Bill_status"));
                Map.put("created_by", this.rs.getString("created_by"));
                Map.put("create_date", this.rs.getString("create_date"));
                
                
                al.add(Map);
            }
        } catch (Exception ex) {
            Logger.getLogger(WIPD0002_Dao.class.getName()).log(Level.SEVERE, (String) null, (Throwable) ex);
        }
        return al;
    }
    
    
    
    //for geeting all vochers 
     
     ArrayList<HashMap<String, String>> get_all_Vochers(String Patient_id,String BIll_NO) {
        ArrayList<HashMap<String, String>> al = new ArrayList<>();
        try {
            this.con = new DBCon().getConnection();
            String Sql = "select Isnull(voucher_id,'')as voucher_id,\n" +
"isnull(ipp.patient_id,'')as patient_id,\n" +
"isnull(patient_name,'')as patient_name,\n" +
"isnull(format(payment_date,'dd/MM/yy'),'')as pay_date,\n" +
"isnull(remark,'')as remark,\n" +
"isnull(amount,'')as amount,\n" +
"isnull(paytype,'')as type,\n" +
"isnull(recieved_from,'')as recieved_from,\n" +
"isnull(Real_Name,'')as real_name\n" +
"from ipd_patient_payment ipp inner join login_info lf \n" +
"on ipp.taken_by=lf.user_id\n" +
"inner join mst_ipd_patient mip \n" +
"on mip.patient_id=ipp.patient_id order by pay_date desc";
            this.ps = this.con.prepareStatement(Sql);
            
            System.out.println("----->"+Sql);
            this.rs = this.ps.executeQuery();
            while (this.rs.next()) {
                HashMap<String, String> Map = new HashMap<>();
                Map.put("voucher_id", this.rs.getString("voucher_id"));
                Map.put("patient_id", this.rs.getString("patient_id"));
                Map.put("patient_name", this.rs.getString("patient_name"));
                Map.put("pay_date", this.rs.getString("pay_date"));
                Map.put("remark", this.rs.getString("remark"));                
                Map.put("amount", this.rs.getString("amount"));
                Map.put("recieved_from", this.rs.getString("recieved_from"));
                Map.put("real_name", this.rs.getString("real_name"));
                Map.put("type", this.rs.getString("type"));
                
                
                al.add(Map);
            }
        } catch (Exception ex) {
            Logger.getLogger(WIPD0002_Dao.class.getName()).log(Level.SEVERE, (String) null, (Throwable) ex);
        }
        return al;
    }
     
     
     //for geeting all beds location 
     
     ArrayList<HashMap<String, String>> get_all_bed_location(String[] arr) {
        ArrayList<HashMap<String, String>> al = new ArrayList<>();
        try {
            this.con = new DBCon().getConnection();
            String Sql = "SELECT distinct\n"
                    + "isnull(pl.rwid,'')as rwid," +
"    ISNULL(pl.patient_id, '') AS patient_id,\n" +
"	isnull(mip.patient_name,'')as patient_name,\n" +
"    ISNULL(pl.previous_room_bed_id, '') AS previous_bed,\n" +
"    ISNULL(cc.category_name, '') AS category,\n" +
"    ISNULL(pl.changed_room_bed_id, '') AS changed_room_bed_id,\n" +
"     isnull(cc.category_name,'')as category_name,\n" +
"    ISNULL(pl.change_date, '') AS changed_date,\n" +
"    ISNULL(pl.end_date, '') AS end_date,\n" +
"    ISNULL(pl.status, '') AS status,\n" +
"	isnull(lf.Real_Name,'')as real_name\n" +
"FROM \n" +
"    patient_location pl\n" +
"INNER JOIN \n" +
"    room_beds rb \n" +
"   ON pl.previous_room_bed_id = rb.room_bed_id  \n" +
"\n" +
"INNER JOIN \n" +
"    mst_rooms mr \n" +
"    ON mr.Room_id = rb.room_id\n" +
"INNER JOIN \n" +
"    charge_category cc \n" +
"    ON cc.category_id = mr.room_category\n" +
"	inner join mst_ipd_patient mip \n" +
"	on pl.patient_id=mip.patient_id\n" +
"	left join login_info lf \n" +
"	on pl.change_by=lf.user_id\n" +
"\n" +
"\n" +
"WHERE \n" +
"    1=1 ";
            
             if (arr[0] != null && arr[0] != "") {
                Sql += " and  pl.patient_id = '"+arr[0]+"'";

            }
            
            this.ps = this.con.prepareStatement(Sql);
            
            System.out.println("----->"+Sql);
            this.rs = this.ps.executeQuery();
            while (this.rs.next()) {
                HashMap<String, String> Map = new HashMap<>();
                Map.put("patient_id", this.rs.getString("patient_id"));
                Map.put("patient_name", this.rs.getString("patient_name"));
                Map.put("rwid", this.rs.getString("rwid"));
                Map.put("previous_bed", this.rs.getString("previous_bed"));
                Map.put("changed_room_bed_id", this.rs.getString("changed_room_bed_id"));                
                Map.put("changed_date", this.rs.getString("changed_date"));
                Map.put("end_date", this.rs.getString("end_date"));
                Map.put("real_name", this.rs.getString("real_name"));
                Map.put("status", this.rs.getString("status"));
                
                
                al.add(Map);
            }
        } catch (Exception ex) {
            Logger.getLogger(WIPD0002_Dao.class.getName()).log(Level.SEVERE, (String) null, (Throwable) ex);
        }
        return al;
    }
    
     
     //for Authorie room change 
     
     String auth_room_change(String Patient_id,String Rwid) throws SQLException, IOException {
      
      int i=0;
      int y=0;
      
      String sts="";
     
      
      
      con = new DBCon().getConnection();
        con.setAutoCommit(false);
      String Sql_mip = "update patient_location set status='Authorized' where patient_id='"+Patient_id+"' and rwid='"+Rwid+"'";
     
      
        System.out.println("=========>"+Sql_mip);
      ps=con.prepareStatement(Sql_mip);
      
      i=ps.executeUpdate();
        System.out.println("========UPPDATE=====");
      
         if( i>=0){
          con.commit();
       
      }

      return sts;
    }
    
    
    
  //for canceling the Bill 
     
     String Cancel_Bill(String Patient_id,String Bill_no) throws SQLException, IOException {
      
      int i=0;
      int y=0;
      
      String sts="";
     
      
      
      con = new DBCon().getConnection();
        con.setAutoCommit(false);
      String Sql_mip = "update ipd_final_bill set Bill_status='Approved' where Patient_Id='"+Patient_id+"' and Bill_no='"+Bill_no+"'";
     
      
        System.out.println("=========>"+Sql_mip);
      ps=con.prepareStatement(Sql_mip);
      
      i=ps.executeUpdate();
        System.out.println("========UPPDATE=====");
      
         if( i>=0){
          con.commit();
       
      }

      return sts;
    }
     
     
     ///for geeting patient journey details
    
     
      ArrayList<HashMap<String, String>> get_patient_journet(String Patient_id) {
        ArrayList<HashMap<String, String>> al = new ArrayList<>();
        try {
            this.con = new DBCon().getConnection();
            String Sql = "DECLARE @patient_id VARCHAR(50) = '"+Patient_id+"';\n" +
"\n" +
"-- Step 1: User mapping (user_id → Real_Name)\n" +
"WITH UserMap AS (\n" +
"    SELECT user_id, Real_Name FROM login_info\n" +
"),\n" +
"\n" +
"-- Step 2: Admission Date\n" +
"AdmissionDate AS (\n" +
"    SELECT ISNULL(admision_date, GETDATE()) AS admission_date\n" +
"    FROM mst_ipd_patient\n" +
"    WHERE patient_id = @patient_id\n" +
"),\n" +
"\n" +
"-- Step 3: Filter valid room changes where changed_room_bed_id exists\n" +
"ValidRoomChanges AS (\n" +
"    SELECT *\n" +
"    FROM patient_location pl\n" +
"    WHERE pl.patient_id = @patient_id\n" +
"      AND pl.changed_room_bed_id IS NOT NULL\n" +
"      \n" +
"),\n" +
"\n" +
"-- Step 4: Combine all events\n" +
"CombinedEvents AS (\n" +
"    -- Admission\n" +
"    SELECT \n" +
"        ISNULL(admision_date, GETDATE()) AS raw_event_date,\n" +
"        FORMAT(ISNULL(admision_date, GETDATE()), 'hh:mm tt') AS event_time,\n" +
"        patient_id,\n" +
"        CONCAT(gender_type, ' ', patient_name) AS description,\n" +
"        NULL AS amount,\n" +
"        CONCAT('Admission at: ', FORMAT(ISNULL(admision_date, GETDATE()), 'dd-MM-yyyy hh:mm tt')) AS other_info,\n" +
"        'Patient Admitted' AS event_type,\n" +
"        1 AS priority\n" +
"    FROM mst_ipd_patient\n" +
"    WHERE patient_id = @patient_id\n" +
"\n" +
"    UNION ALL\n" +
"\n" +
"    -- Payment\n" +
"    SELECT \n" +
"        ISNULL(p.payment_date, GETDATE()) AS raw_event_date,\n" +
"        FORMAT(ISNULL(p.payment_date, GETDATE()), 'hh:mm tt') AS event_time,\n" +
"        p.patient_id,\n" +
"        CONCAT('Taken by: ', ISNULL(l.Real_Name, p.taken_by), ', Voucher: ', ISNULL(p.voucher_id, '')) AS description,\n" +
"        ISNULL(p.amount, 0) AS amount,\n" +
"        CONCAT('Payment at: ', FORMAT(ISNULL(p.payment_date, GETDATE()), 'dd-MM-yyyy hh:mm tt')) AS other_info,\n" +
"        'Payment' AS event_type,\n" +
"        2 AS priority\n" +
"    FROM ipd_patient_payment p\n" +
"    LEFT JOIN UserMap l ON l.user_id = p.taken_by\n" +
"    WHERE p.patient_id = @patient_id\n" +
"\n" +
"    UNION ALL\n" +
"\n" +
"    -- Other Charges\n" +
"    SELECT \n" +
"        ISNULL(c.createDT, GETDATE()) AS raw_event_date,\n" +
"        FORMAT(ISNULL(c.createDT, GETDATE()), 'hh:mm tt') AS event_time,\n" +
"        c.patient_id,\n" +
"        CONCAT('Charge Code: ', ISNULL(c.oc_code, ''), ', By: ', ISNULL(u.Real_Name, c.createBY)) AS description,\n" +
"        ISNULL(c.oc_amt_pay, 0) AS amount,\n" +
"        CONCAT('Charge added at: ', FORMAT(ISNULL(c.createDT, GETDATE()), 'dd-MM-yyyy hh:mm tt')) AS other_info,\n" +
"        'Other Charges' AS event_type,\n" +
"        3 AS priority\n" +
"    FROM Add_other_charge_details c\n" +
"    LEFT JOIN UserMap u ON u.user_id = c.createBY\n" +
"    WHERE c.patient_id = @patient_id\n" +
"\n" +
"    UNION ALL\n" +
"\n" +
"    -- Room Change (Filtered)\n" +
"    SELECT \n" +
"        ISNULL(vc.change_date, GETDATE()) AS raw_event_date,\n" +
"        FORMAT(ISNULL(vc.change_date, GETDATE()), 'hh:mm tt') AS event_time,\n" +
"        vc.patient_id,\n" +
"        CONCAT('Room changed from ', ISNULL(vc.previous_room_bed_id, ''), ' to ', ISNULL(vc.changed_room_bed_id, '')) AS description,\n" +
"        NULL AS amount,\n" +
"        CONCAT('Changed at: ', FORMAT(ISNULL(vc.change_date, GETDATE()), 'dd-MM-yyyy hh:mm tt')) AS other_info,\n" +
"        'Room Change' AS event_type,\n" +
"        4 AS priority\n" +
"    FROM ValidRoomChanges vc\n" +
"),\n" +
"\n" +
"-- Step 5: Add Admission Date for context\n" +
"FinalEventsWithDate AS (\n" +
"    SELECT \n" +
"        e.*, a.admission_date,\n" +
"        CAST(e.raw_event_date AS DATE) AS event_only_date\n" +
"    FROM CombinedEvents e\n" +
"    CROSS JOIN AdmissionDate a\n" +
"),\n" +
"\n" +
"-- Step 6: Add Row Number Partitioned by Date to flag first event of that date\n" +
"EventsWithDateStatus AS (\n" +
"    SELECT *,\n" +
"        ROW_NUMBER() OVER (PARTITION BY event_only_date ORDER BY raw_event_date, priority) AS rn\n" +
"    FROM FinalEventsWithDate\n" +
")\n" +
"\n" +
"-- Final Output\n" +
"SELECT \n" +
"    FORMAT(raw_event_date, 'dd-MM-yyyy') AS event_date,\n" +
"    event_time,\n" +
"    patient_id,\n" +
"    description,\n" +
"    amount,\n" +
"    other_info,\n" +
"    event_type,\n" +
"    CASE \n" +
"        WHEN rn = 1 THEN 'New Date'\n" +
"        ELSE 'Same'\n" +
"    END AS date_status\n" +
"FROM EventsWithDateStatus\n" +
"ORDER BY raw_event_date, priority, event_time;";
            
            this.ps = this.con.prepareStatement(Sql);
            
            System.out.println("----->"+Sql);
            this.rs = this.ps.executeQuery();
            while (this.rs.next()) {
                HashMap<String, String> Map = new HashMap<>();
                Map.put("event_date", this.rs.getString("event_date"));
                Map.put("event_time", this.rs.getString("event_time"));
                Map.put("patient_id", this.rs.getString("patient_id"));
                Map.put("description", this.rs.getString("description"));
                Map.put("amount", this.rs.getString("amount"));                
                Map.put("other_info", this.rs.getString("other_info"));
                Map.put("event_type", this.rs.getString("event_type"));
                Map.put("date_status", this.rs.getString("date_status"));
                al.add(Map);
            }
        } catch (Exception ex) {
            Logger.getLogger(WIPD0002_Dao.class.getName()).log(Level.SEVERE, (String) null, (Throwable) ex);
        }
        return al;
    }
    
    
     
    
}

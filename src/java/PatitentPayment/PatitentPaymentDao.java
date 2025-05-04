/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatitentPayment;

import Dao.DBCon;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

/**
 *
 * @author Ram
 */
public class PatitentPaymentDao {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Statement stmt;

    public boolean savePayment(HashMap<String, Object> patitent_paymen_data) throws IOException, SQLException {
        con = new DBCon().getConnection();
        con.setAutoCommit(false);

        String patitent_id = "", payment_id = "", payment_type = "", create_by = "";

        float pay_amt = 0, paid_amt = 0, pending_amt = 0;

        if (patitent_paymen_data.get("mode").toString().equals("Admit")) {
            patitent_id = patitent_paymen_data.get("patitent_id").toString();
            payment_id = patitent_paymen_data.get("payment_id").toString();
            payment_type = patitent_paymen_data.get("payment_type").toString();
            create_by = patitent_paymen_data.get("create_by").toString();

            pay_amt = Float.parseFloat(patitent_paymen_data.get("pay_amt").toString());
            paid_amt = Float.parseFloat(patitent_paymen_data.get("paid_amt").toString());
            pending_amt = Float.parseFloat(patitent_paymen_data.get("pending_amt").toString());
        }
        if (patitent_paymen_data.get("mode").toString().equals("testLab")) {
            patitent_id = patitent_paymen_data.get("patitent_id").toString();
            payment_id = patitent_paymen_data.get("payment_id").toString();
            payment_type = patitent_paymen_data.get("payment_type").toString();
            create_by = patitent_paymen_data.get("create_by").toString();

            pay_amt = Float.parseFloat(patitent_paymen_data.get("pay_amt").toString());
            paid_amt = Float.parseFloat(patitent_paymen_data.get("paid_amt").toString());
            pending_amt = Float.parseFloat(patitent_paymen_data.get("pending_amt").toString());
        }
        if (patitent_paymen_data.get("mode").toString().equals("OT")) {
            patitent_id = patitent_paymen_data.get("patitent_id").toString();
            payment_id = patitent_paymen_data.get("payment_id").toString();
            payment_type = patitent_paymen_data.get("payment_type").toString();
            create_by = patitent_paymen_data.get("create_by").toString();

            pay_amt = Float.parseFloat(patitent_paymen_data.get("pay_amt").toString());
            paid_amt = Float.parseFloat(patitent_paymen_data.get("paid_amt").toString());
            pending_amt = Float.parseFloat(patitent_paymen_data.get("pending_amt").toString());
        }
        
        
        if (patitent_paymen_data.get("mode").toString().equals("Other_charge")) {
            patitent_id = patitent_paymen_data.get("patitent_id").toString();
            payment_id = patitent_paymen_data.get("payment_id").toString();
            payment_type = patitent_paymen_data.get("payment_type").toString();
            create_by = patitent_paymen_data.get("create_by").toString();

            pay_amt = Float.parseFloat(patitent_paymen_data.get("pay_amt").toString());
            paid_amt = Float.parseFloat(patitent_paymen_data.get("paid_amt").toString());
            pending_amt = Float.parseFloat(patitent_paymen_data.get("pending_amt").toString());
        }
        
             
        if (patitent_paymen_data.get("mode").toString().equals("Payment_Voucher")) {
            patitent_id = patitent_paymen_data.get("patitent_id").toString();
            payment_id = patitent_paymen_data.get("payment_id").toString();
            payment_type = patitent_paymen_data.get("payment_type").toString();
            create_by = patitent_paymen_data.get("create_by").toString();

            pay_amt = Float.parseFloat(patitent_paymen_data.get("pay_amt").toString());
            paid_amt = Float.parseFloat(patitent_paymen_data.get("paid_amt").toString());
            pending_amt = Float.parseFloat(patitent_paymen_data.get("pending_amt").toString());
            
            System.out.println("______isme___-aya");
        }
        
          if (patitent_paymen_data.get("mode").toString().equals("OPD_FEES")) {
            patitent_id = patitent_paymen_data.get("patitent_id").toString();
            payment_id = patitent_paymen_data.get("payment_id").toString();
            payment_type = patitent_paymen_data.get("payment_type").toString();
            create_by = patitent_paymen_data.get("create_by").toString();
            pay_amt = Float.parseFloat(patitent_paymen_data.get("pay_amt").toString());
            paid_amt = Float.parseFloat(patitent_paymen_data.get("paid_amt").toString());
            pending_amt = Float.parseFloat(patitent_paymen_data.get("pending_amt").toString());
            
           
        }
        

        String Sql = "INSERT INTO patitent_payment_details \n"
                + "(patitent_id,payment_id,pay_amt,paid_amt,pending_amt,payment_type,create_by)\n"
                + "VALUES (?,?,?,?,?,?,?);";
        ps = con.prepareStatement(Sql);
        ps.setString(1, patitent_id);
        ps.setString(2, payment_id);
        ps.setFloat(3, pay_amt);
        ps.setFloat(4, paid_amt);
        ps.setFloat(5, pending_amt);
        ps.setString(6, payment_type);
        ps.setString(7, create_by);

        if (ps.executeUpdate() > 0) {
            con.commit();
            return true;
        } else {
            return false;
        }

    }
    
    
    public boolean updatePayment(HashMap<String, Object> patitent_paymen_data) throws IOException, SQLException {
        this.con = new DBCon().getConnection();
        this.con.setAutoCommit(false);
        String patitent_id = "";
        String payment_id = "";
        String create_by = "";
        float paid_amt = 0.0f;
        float pending_amt = 0.0f;
        if (patitent_paymen_data.get("mode").toString().equals("OT")) {
            patitent_id = patitent_paymen_data.get("patitent_id").toString();
            payment_id = patitent_paymen_data.get("payment_id").toString();
            patitent_paymen_data.get("payment_type").toString();
            create_by = patitent_paymen_data.get("create_by").toString();
            Float.parseFloat(patitent_paymen_data.get("pay_amt").toString());
            paid_amt = Float.parseFloat(patitent_paymen_data.get("paid_amt").toString());
            pending_amt = Float.parseFloat(patitent_paymen_data.get("pending_amt").toString());
        }
        if (patitent_paymen_data.get("mode").toString().equals("testLab")) {
            patitent_id = patitent_paymen_data.get("patitent_id").toString();
            payment_id = patitent_paymen_data.get("payment_id").toString();
            patitent_paymen_data.get("payment_type").toString();
            create_by = patitent_paymen_data.get("create_by").toString();
            Float.parseFloat(patitent_paymen_data.get("pay_amt").toString());
            paid_amt = Float.parseFloat(patitent_paymen_data.get("paid_amt").toString());
            pending_amt = Float.parseFloat(patitent_paymen_data.get("pending_amt").toString());
        }
        this.ps = this.con.prepareStatement("update patitent_payment_details \nset paid_amt=?,pending_amt=?,create_by=? where patitent_id=? and payment_id=? ");
        this.ps.setFloat(1, paid_amt);
        this.ps.setFloat(2, pending_amt);
        this.ps.setString(3, create_by);
        this.ps.setString(4, patitent_id);
        this.ps.setString(5, payment_id);
        if (this.ps.executeUpdate() > 0) {
            this.con.commit();
            return true;
        }
        return false;
    }

    public boolean updatePaymentOfTestLab(HashMap<String, Object> patitent_paymen_data) throws IOException, SQLException {
        this.con = new DBCon().getConnection();
        this.con.setAutoCommit(false);
        String patitent_id = "";
        String payment_id = "";
        String create_by = "";
        float paid_amt = 0.0f;
        float pending_amt = 0.0f;
        if (patitent_paymen_data.get("mode").toString().equals("testLab")) {
            patitent_id = patitent_paymen_data.get("patitent_id").toString();
            payment_id = patitent_paymen_data.get("payment_id").toString();
            patitent_paymen_data.get("payment_type").toString();
            create_by = patitent_paymen_data.get("create_by").toString();
            Float.parseFloat(patitent_paymen_data.get("pay_amt").toString());
            paid_amt = Float.parseFloat(patitent_paymen_data.get("paid_amt").toString());
            pending_amt = Float.parseFloat(patitent_paymen_data.get("pending_amt").toString());
        }
        this.ps = this.con.prepareStatement("update patitent_payment_details \nset paid_amt+=?,pending_amt-=?,create_by=? where patitent_id=? and payment_id=? ");
        this.ps.setFloat(1, paid_amt);
        this.ps.setFloat(2, pending_amt);
        this.ps.setString(3, create_by);
        this.ps.setString(4, patitent_id);
        this.ps.setString(5, payment_id);
        if (this.ps.executeUpdate() > 0) {
            this.con.commit();
            return true;
        }
        return false;
    }

    public boolean updatePaymentOfOtherCHarge(HashMap<String, Object> patitent_paymen_data) throws IOException, SQLException {
        this.con = new DBCon().getConnection();
        this.con.setAutoCommit(false);
        String patitent_id = "";
        String payment_id = "";
        String create_by = "";
        float paid_amt = 0.0f;
        float pending_amt = 0.0f;
        if (patitent_paymen_data.get("mode").toString().equals("Other_charge")) {
            patitent_id = patitent_paymen_data.get("patitent_id").toString();
            payment_id = patitent_paymen_data.get("payment_id").toString();
            patitent_paymen_data.get("payment_type").toString();
            create_by = patitent_paymen_data.get("create_by").toString();
            paid_amt = Float.parseFloat(patitent_paymen_data.get("paid_amt").toString());
            pending_amt = Float.parseFloat(patitent_paymen_data.get("pending_amt").toString());
        }
        this.ps = this.con.prepareStatement("update patitent_payment_details \nset paid_amt+=?,pending_amt-=?,create_by=? where patitent_id=? and payment_id=? ");
        this.ps.setFloat(1, paid_amt);
        this.ps.setFloat(2, pending_amt);
        this.ps.setString(3, create_by);
        this.ps.setString(4, patitent_id);
        this.ps.setString(5, payment_id);
        if (this.ps.executeUpdate() > 0) {
            this.con.commit();
            return true;
        }
        return false;
    }
    
    
    
   //for Voucher Payments
    public boolean Vocher_payments(String patient_id,String Net_amount,String user,String VOC_code,String type,String Pay_type,String Checque_number,String Card_number,String UPI_type,
    String transcation_id,String Received_from,String Debit_Ac){
        
        boolean sts=false;
        try{
         String sql_voc = "select count(*)+1 as voc from ipd_patient_payment";
            ps = con.prepareStatement(sql_voc);
            rs = ps.executeQuery();
            if (rs.next()) {
                VOC_code = "VOC_" + rs.getString("voc");
            }

            String Sql_voc_insert = "insert into ipd_patient_payment(patient_id,amount,payment_date,taken_by,voucher_id,remark,paytype,cheque_number,card_number,upi_type,transcation_id,recieved_from,Debit_AC)values(?,?,getdate(),?,?,?,?,?,?,?,?,?,?)";
            ps = con.prepareStatement(Sql_voc_insert);
            ps.setString(1, patient_id);
            ps.setString(2, Net_amount);
            ps.setString(3, user);
            ps.setString(4, VOC_code);
            ps.setString(5, type);
            ps.setString(6, Pay_type);
            ps.setString(7, Checque_number);
            ps.setString(8, Card_number);
            ps.setString(9, UPI_type);
            ps.setString(10, transcation_id);
            ps.setString(11, Received_from);
            ps.setString(12, Debit_Ac);

        if (ps.executeUpdate() > 0) {
            con.commit();
            return sts=true;
        } else {
            return sts=false;
        }
    }
      
        catch(Exception e){
            System.out.println("----Voucher Payment Error"+e);
    }
    
      return sts;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

}

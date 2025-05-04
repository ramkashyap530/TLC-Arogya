/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package OC;

import Dao.DBCon;
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
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Lenovo
 */
public class WOC0001_Dao {
     Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Statement stmt;
    String add_oc_code = "";

    public ArrayList<String> getdata(String cd, String usr) throws SQLDataException {
        ArrayList<String> al = new ArrayList<>();
        try {
            this.con = new DBCon().getConnection();
            String sql = "";
            if (cd.equals("1")) {
                sql = "SELECT \n" +
"  ISNULL(patient_id, '') AS patient_id,\n" +
"  ISNULL(CONCAT(patient_name, ' / ', father_name, ' / ', room_bed_id), '') AS patient_name  \n" +
"FROM mst_ipd_patient p\n" +
"WHERE pt_sts = 'Admitted'\n" +
"  AND NOT EXISTS (\n" +
"    SELECT 1 \n" +
"    FROM ipd_final_bill fb \n" +
"    WHERE fb.patient_id = p.patient_id\n" +
"  );\n" +
"select * from ipd_final_bill";
            }
            if (cd.equals("2")) {
                sql = "select isnull(opd_id,'')as opd_id,\nisnull(patient_name,'')as patient_name\nfrom mst_opd";
            }
            
             if (cd.equals("3")) {
                sql = "select isnull(category_id,'')as category_id,isnull(category_name,'')as category_name\n" +
"from charge_category where charge_type='Charge'";
            }
             
                          if (cd.equals("4")) {
                sql = "select isnull(category_id,'')as category_id,isnull(category_name,'')as category_name\n" +
"from charge_category where charge_type='TPA_CHARGE'";
            }
            this.ps = this.con.prepareStatement(sql);
            this.rs = this.ps.executeQuery();
            while (this.rs.next()) {
                al.add("<option value='" + this.rs.getString(1) + "'>" + this.rs.getString(2) + "</option>");
            }
        } catch (Exception ex) {
            Logger.getLogger(WOC0001_Dao.class.getName()).log(Level.SEVERE, (String) null, (Throwable) ex);
        }
        return al;
    }

    ArrayList<HashMap<String, String>> get_oc_data(String Category_id) {
        ArrayList<HashMap<String, String>> al = new ArrayList<>();
        try {
            this.con = new DBCon().getConnection();
            this.ps = this.con.prepareStatement("select isnull(charge_id,'')as charge_id,isnull(cc.category_name,'')as category_name,\n" +
"isnull(cc.category_id,'')as category_id,isnull(charge_name,'')as charge_name,isnull(charge_amount,'')as \n" +
"charge_amount from mst_hos_charges mhc inner join charge_category cc on mhc.category_id=cc.category_id\n" +
"where cc.category_id='"+Category_id+"'");
            this.rs = this.ps.executeQuery();
            while (this.rs.next()) {
                HashMap<String, String> Map = new HashMap<>();
                Map.put("charge_name", this.rs.getString("charge_name"));
                Map.put("charge_amount", this.rs.getString("charge_amount"));
                Map.put("category_name", this.rs.getString("category_name"));
                Map.put("category_id", this.rs.getString("category_id"));
                Map.put("charge_id", this.rs.getString("charge_id"));
                
                al.add(Map);
            }
        } catch (Exception ex) {
            Logger.getLogger(WOC0001_Dao.class.getName()).log(Level.SEVERE, (String) null, (Throwable) ex);
        }
        return al;
    }

    boolean Save_OC_payment(HttpServletRequest request) throws SQLException, Exception {
        boolean sts = false;
        try {
            this.con = new DBCon().getConnection();
            this.con.setAutoCommit(false);
        } catch (Exception ex) {
            try {
                this.con.rollback();
                this.con.close();
                Logger.getLogger(WOC0001_Dao.class.getName()).log(Level.SEVERE, (String) null, (Throwable) ex);
            } catch (SQLException ex1) {
                Logger.getLogger(WOC0001_Dao.class.getName()).log(Level.SEVERE, (String) null, (Throwable) ex1);
            }
        }
        if (saveOCPayment(request)) {
            sts = saveOCPayment_ADD_OC(request);
            if (sts) {
                this.con.commit();
                this.con.close();
                return sts;
            }
            throw new Exception("Some Error !!");
        }
        throw new Exception("Some Error !!");
    }

    private boolean saveOCPayment(HttpServletRequest request) throws SQLException {
        String[] parameterValues;
        String patient_id = request.getParameter("patient_id");
        String user = request.getParameter("user");
        getNewAdd_oc_code();
        this.ps = this.con.prepareStatement("insert into Add_other_charge_details(add_oc_code,oc_code,patient_id,oc_amt_pay,oc_amt_paid,oc_amt_left,createBY,oc_qty,oc_final_amt,tpa_amount) Values(?,?,?,?,?,?,?,?,?,?); "
                + "insert into Add_other_charge_history(add_oc_code,oc_code,patient_id,oc_amt_paid,createBY) Values(?,?,?,?,?)");
        for (String i : request.getParameterValues("oc_checkBox")) {
            String oc_code = request.getParameter("oc_code_" + i);
            float oc_amt = Float.parseFloat(request.getParameter("oc_amt_" + i));
            float qty = Float.parseFloat(request.getParameter("qty_" + i));
            float F_amt = qty * oc_amt;
            float submit_amt = Float.parseFloat(request.getParameter("submit_amt_" + i));
            float oc_amt_left = F_amt - submit_amt;
            float submit_amt_tpa=0;
             
      
            
            System.out.println("F_amtF_amt " + F_amt);
            this.ps.setString(1, this.add_oc_code);
            this.ps.setString(2, oc_code);
            this.ps.setString(3, patient_id);
            this.ps.setFloat(4, oc_amt);
            this.ps.setFloat(5, submit_amt);
            this.ps.setFloat(6, oc_amt_left);
            this.ps.setString(7, user);
            this.ps.setFloat(8, qty);
            this.ps.setFloat(9, F_amt);
            this.ps.setFloat(10, submit_amt);
            this.ps.setString(11, this.add_oc_code);
            this.ps.setString(12, oc_code);
            this.ps.setString(13, patient_id);
            this.ps.setFloat(14, submit_amt);
            this.ps.setString(15, user);
          
            System.out.println("----->"+submit_amt);
            
            this.ps.addBatch();
        }
        return this.ps.executeBatch().length > 0;
    }

    private void getNewAdd_oc_code() throws SQLException {
        this.ps = this.con.prepareStatement("select count(*)+1 as code  from Add_other_charge");
        this.rs = this.ps.executeQuery();
        if (this.rs.next()) {
            this.add_oc_code = "OCA-" + this.rs.getString("code");
        }
    }
    
    

    private boolean saveOCPayment_ADD_OC(HttpServletRequest request) throws SQLException, IOException {
        String[] parameterValues;
        String patient_id = request.getParameter("patient_id");
        String user = request.getParameter("user");
        getNewAdd_oc_code();
        this.ps = this.con.prepareStatement("insert into Add_other_charge(add_oc_code,patient_id,oc_amt_pay,oc_amt_paid,oc_amt_left,createBY) Values(?,?,?,?,?,?) ");
        float submit_amt = 0.0f;
        float F_amt = 0.0f;
        for (String i : request.getParameterValues("oc_checkBox")) {
            float oc_amt = Float.parseFloat(request.getParameter("oc_amt_" + i));
            float qty = Float.parseFloat(request.getParameter("qty_" + i));
            F_amt += qty * oc_amt;
            submit_amt += Float.parseFloat(request.getParameter("submit_amt_" + i));
        }
        float oc_amt_left = F_amt - submit_amt;
        this.ps.setString(1, this.add_oc_code);
        this.ps.setString(2, patient_id);
        this.ps.setFloat(3, F_amt);
        this.ps.setFloat(4, submit_amt);
        this.ps.setFloat(5, oc_amt_left);
        this.ps.setString(6, user);
        boolean sts = this.ps.executeUpdate() > 0;
        if (sts) {
            HashMap<String, Object> patitent_paymen_data = new HashMap<>();
            patitent_paymen_data.put("patitent_id", patient_id);
            patitent_paymen_data.put("payment_id", this.add_oc_code);
            patitent_paymen_data.put("payment_type", "Other Charge");
            patitent_paymen_data.put("mode", "Other_charge");
            patitent_paymen_data.put("create_by", user);
            patitent_paymen_data.put("pay_amt", Float.valueOf(F_amt));
            patitent_paymen_data.put("paid_amt", Float.valueOf(submit_amt));
            patitent_paymen_data.put("pending_amt", Float.valueOf(oc_amt_left));
            sts = new PatitentPaymentDao().savePayment(patitent_paymen_data);
        }
        return sts;
    }

    ArrayList<HashMap<String, String>> get_oc_data2(String patient_id) {
        ArrayList<HashMap<String, String>> al = new ArrayList<>();
        try {
            this.con = new DBCon().getConnection();
            String Sql = "select add_oc_code,aocd.oc_code,moc.charge_name,aocd.oc_amt_pay,\n" +
"aocd.oc_qty,aocd.oc_final_amt,aocd.oc_amt_paid,aocd.oc_amt_left,aocd.status\n" +
"from Add_other_charge_details aocd\n" +
"inner join mst_hos_charges moc on moc.charge_id=aocd.oc_code\n" +
"where aocd.patient_id='"+patient_id+"' and status='Active'";
            this.ps = this.con.prepareStatement(Sql);
            System.out.println(Sql);
            this.rs = this.ps.executeQuery();
            while (this.rs.next()) {
                HashMap<String, String> Map = new HashMap<>();
                Map.put("add_oc_code", this.rs.getString("add_oc_code"));
                Map.put("oc_code", this.rs.getString("oc_code"));
                Map.put("charge_name", this.rs.getString("charge_name"));
                Map.put("oc_amt_pay", this.rs.getString("oc_amt_pay"));
                Map.put("oc_qty", this.rs.getString("oc_qty"));
                Map.put("oc_final_amt", this.rs.getString("oc_final_amt"));
                Map.put("oc_amt_paid", this.rs.getString("oc_amt_paid"));
                Map.put("oc_amt_left", this.rs.getString("oc_amt_left"));
                Map.put("status", this.rs.getString("status"));
                al.add(Map);
            }
        } catch (Exception ex) {
            Logger.getLogger(WOC0001_Dao.class.getName()).log(Level.SEVERE, (String) null, (Throwable) ex);
        }
        return al;
    }

    boolean update_OC_payment(HttpServletRequest request) {
        boolean sts = false;
        try {
            this.con = new DBCon().getConnection();
            this.con.setAutoCommit(false);
            sts = updateOCPayment(request);
            if (sts) {
                sts = updateOCPayment_ADD_OC(request);
            }
            if (sts) {
                this.con.commit();
                this.con.close();
                return sts;
            }
            throw new Exception("Some Error !!");
        } catch (Exception ex) {
                Logger.getLogger(WOC0001_Dao.class.getName()).log(Level.SEVERE, (String) null, (Throwable) ex);
           
            } 
             return false;
            }
        
    
    
    
    
    
    //remove other charge 
    
    boolean Remove_other_charge(String patient_id,String userid,String charge_code) {
        boolean sts = false;
        int i=0;
        try {
            this.con = new DBCon().getConnection();
            this.con.setAutoCommit(false);
           
            String Sql ="update Add_other_charge_details set status='Cancel',cancel_date=getdate(),cancled_by='"+userid+"' where patient_id='"+patient_id+"' and oc_code='"+charge_code+"'";
            ps = con.prepareStatement(Sql);
              System.out.println("---->"+Sql);
               i = ps.executeUpdate();

            
            if (i>=0) {
                this.con.commit();
                this.con.close();
                sts=true;
                return sts;
            }
            throw new Exception("Some Error !!");
        } catch (Exception ex) {
                Logger.getLogger(WOC0001_Dao.class.getName()).log(Level.SEVERE, (String) null, (Throwable) ex);
           
            } 
             return false;
  }

    
    
    

    

    private boolean updateOCPayment(HttpServletRequest request) throws SQLException {
        String[] parameterValues;
        String patient_id = request.getParameter("patient_id");
        String user = request.getParameter("user");
        this.ps = this.con.prepareStatement("update Add_other_charge_details set oc_amt_paid+=? ,oc_amt_left-=? where add_oc_code=? and oc_code=? and patient_id=? ; insert into Add_other_charge_history(add_oc_code,oc_code,patient_id,oc_amt_paid,createBY) Values(?,?,?,?,?)");
        for (String i : request.getParameterValues("oc_checkBox")) {
            String add_oc_code_ = request.getParameter("add_oc_code_" + i);
            String oc_code = request.getParameter("oc_code_" + i + "_" + add_oc_code_);
            float submit_amt = Float.parseFloat(request.getParameter("submit_amt_" + i + "_" + add_oc_code_));
            this.ps.setFloat(1, submit_amt);
            this.ps.setFloat(2, submit_amt);
            this.ps.setString(3, add_oc_code_);
            this.ps.setString(4, oc_code);
            this.ps.setString(5, patient_id);
            this.ps.setString(6, add_oc_code_);
            this.ps.setString(7, oc_code);
            this.ps.setString(8, patient_id);
            this.ps.setFloat(9, submit_amt);
            this.ps.setString(10, user);
            this.ps.addBatch();
        }
        return this.ps.executeBatch().length > 0;
    }

    private boolean updateOCPayment_ADD_OC(HttpServletRequest request) throws SQLException, IOException, Exception {
        String[] parameterValues;
        boolean sts = false;
        String patient_id = request.getParameter("patient_id");
        Object user = request.getParameter("user");
        getNewAdd_oc_code();
        this.ps = this.con.prepareStatement("update Add_other_charge set oc_amt_paid+=? ,oc_amt_left-=? where add_oc_code=? and patient_id=? ");
        for (String i : request.getParameterValues("oc_checkBox")) {
            String add_oc_code_ = request.getParameter("add_oc_code_" + i);
            float submit_amt = Float.parseFloat(request.getParameter("submit_amt_" + i + "_" + add_oc_code_));
            this.ps.setFloat(1, submit_amt);
            this.ps.setFloat(2, submit_amt);
            this.ps.setString(3, add_oc_code_);
            this.ps.setString(4, patient_id);
            sts = this.ps.executeUpdate() > 0;
            if (!sts) {
                break;
            }
            HashMap<String, Object> patitent_paymen_data = new HashMap<>();
            patitent_paymen_data.put("patitent_id", patient_id);
            patitent_paymen_data.put("payment_id", add_oc_code_);
            patitent_paymen_data.put("payment_type", "Other Charge");
            patitent_paymen_data.put("mode", "Other_charge");
            patitent_paymen_data.put("create_by", user);
            patitent_paymen_data.put("pay_amt", "0");
            patitent_paymen_data.put("paid_amt", Float.valueOf(submit_amt));
            patitent_paymen_data.put("pending_amt", Float.valueOf(submit_amt));
            sts = new PatitentPaymentDao().updatePaymentOfOtherCHarge(patitent_paymen_data);
            if (!sts) {
                throw new Exception("Error in Update Payment Table !!");
            }
        }
        return sts;
    }

    
   //for ayushman charges
    
    ArrayList<HashMap<String, String>> get_all_ayushmaan_charges() {
        ArrayList<HashMap<String, String>> al = new ArrayList<>();
        try {
            this.con = new DBCon().getConnection();
            String Sql = "select isnull(Specialty_code,'')as specility_code,isnull(Specialty,'')as specility,\n" +
"isnull(Package_code,'')as package_code,isnull(Package_name,'')as package_name,isnull(Procedure_code,'')as Procedure_code,\n" +
"isnull(procedure_name,'')as procedure_name,isnull(procedure_price,0)as procedure_price,isnull(Total_Package_Price,0)as total_package_price,\n" +
"isnull(Procedure_Label,'')as procedure_label\n" +
"\n" +
"from Ayuman_GOV_package";
            this.ps = this.con.prepareStatement(Sql);
         
            System.out.println(Sql);
            this.rs = this.ps.executeQuery();
            while (this.rs.next()) {
                HashMap<String, String> Map = new HashMap<>();
                Map.put("specility_code", this.rs.getString("specility_code"));
                Map.put("specility", this.rs.getString("specility"));
                Map.put("package_code", this.rs.getString("package_code"));
                Map.put("package_name", this.rs.getString("package_name"));
                Map.put("Procedure_code", this.rs.getString("Procedure_code"));
                Map.put("procedure_name", this.rs.getString("procedure_name"));
                Map.put("procedure_price", this.rs.getString("procedure_price"));
                Map.put("total_package_price", this.rs.getString("total_package_price"));
                Map.put("procedure_label", this.rs.getString("procedure_label"));
                al.add(Map);
            }
        } catch (Exception ex) {
            Logger.getLogger(WOC0001_Dao.class.getName()).log(Level.SEVERE, (String) null, (Throwable) ex);
        }
        return al;
    }
    
    
    
    //for checking patient Type 
public String Patient_type(String Patient_id){
   String type="";

 try {
            this.con = new DBCon().getConnection();
            String Sql = "select patient_type from mst_ipd_patient where patient_id='"+Patient_id+"'";
            this.ps = this.con.prepareStatement(Sql);
         
            System.out.println(Sql);
            this.rs = this.ps.executeQuery();
             if(this.rs.next()) {
                type=rs.getString("patient_type");
         
            }
        } catch (Exception ex) {
            Logger.getLogger(WOC0001_Dao.class.getName()).log(Level.SEVERE, (String) null, (Throwable) ex);
        }

return type;
}
    


//for TPA patients 
ArrayList<HashMap<String, String>> get_TPA_charge_package(String category_id) {
        ArrayList<HashMap<String, String>> al = new ArrayList<>();
        try {
            this.con = new DBCon().getConnection();
            String Sql = "select isnull(charge_id,'')as charge_id,isnull(category_id,'')as category_id,isnull(charge_name,'')as charge_name,\n" +
"isnull(charge_amount,0)as charge_amount\n" +
"from mst_hos_charges where category_id='"+category_id+"'";
            this.ps = this.con.prepareStatement(Sql);
         
            System.out.println(Sql);
            this.rs = this.ps.executeQuery();
            while (this.rs.next()) {
                HashMap<String, String> Map = new HashMap<>();
                Map.put("charge_id", this.rs.getString("charge_id"));
                Map.put("category_id", this.rs.getString("category_id"));
                Map.put("charge_name", this.rs.getString("charge_name"));
                Map.put("charge_amount", this.rs.getString("charge_amount"));
                al.add(Map);
            }
        } catch (Exception ex) {
            Logger.getLogger(WOC0001_Dao.class.getName()).log(Level.SEVERE, (String) null, (Throwable) ex);
        }
        return al;
    }


//for adding bills and medicine 
boolean assign_Tpa_drugs_bill(String patient_id,String userid,String charge_code,String qty,String charge_Amount,String Hos) {
        boolean sts = false;
        int i=0;
        try {
            this.con = new DBCon().getConnection();
            this.con.setAutoCommit(false);
            getNewAdd_oc_code();
          float F_amt = Float.parseFloat(qty) * Float.parseFloat(charge_Amount);
            String Sql ="insert into Add_other_charge_details(add_oc_code,oc_code,patient_id,oc_amt_pay,oc_amt_paid,oc_amt_left,oc_qty,\n" +
                          "oc_final_amt,createBY,status,tpa_amount)Values(?,?,?,?,?,?,?,?,?,?,?)";
            ps = con.prepareStatement(Sql);
            this.ps.setString(1,this.add_oc_code);
            this.ps.setString(2,charge_code);
            this.ps.setString(3,patient_id);
            this.ps.setString(4,charge_Amount);
            this.ps.setFloat(5,0);
            this.ps.setString(6,charge_Amount);
            this.ps.setString(7,qty);
            this.ps.setFloat(8,F_amt);
            this.ps.setString(9,userid);
            this.ps.setString(10,"Active");
            this.ps.setString(11,charge_Amount);
            i = ps.executeUpdate();
            
            if (i>=0) {
                this.con.commit();
                this.con.close();
                sts=true;
                return sts;
            }
            throw new Exception("Some Error !!");
        } catch (Exception ex) {
                Logger.getLogger(WOC0001_Dao.class.getName()).log(Level.SEVERE, (String) null, (Throwable) ex);
           
            } 
             return false;
  }
   
    
    
}



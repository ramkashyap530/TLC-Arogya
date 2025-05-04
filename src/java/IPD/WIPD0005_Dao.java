/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package IPD;

import Dao.DBCon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
public class WIPD0005_Dao {
    
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Statement stmt;
    String room_payment_id = "";
    String room_payment_Details_id = "";

    public ArrayList<HashMap<String, String>> get_room_charge_hi(String Patient_id) {
        ArrayList<HashMap<String, String>> al = new ArrayList<>();
        try {
            this.con = new DBCon().getConnection();
            String Sql = "DECLARE @discharge_date AS date\nSET @discharge_date =getdate()\nselect *,\n(per_day_charge*number_of_days) as tot_room_amt,\n(per_day_charge*number_of_days)-(paid_amt) as panding_amt\nfrom "
                    + "(select distinct mip.patient_id,mip.room_bed_id as current_bed_id,isnull(pl.changed_room_bed_id,pl.previous_room_bed_id)as move_bed_id,"
                    + "\nisnull(pl.change_date,mip.admision_date)as move_bed_date,\nISNULL(rb.room_id,'')as move_room,\n(select sum(isnull(mhc.charge_amount,0))as "
                    + "per_day_charge_of_cat from\ncharge_category cc \ninner join mst_hos_charges mhc on mhc.category_id=cc.category_id \ninner join mst_rooms"
                    + " mr on mr.room_category=cc.category_id\nwhere  mr.Room_id=rb.room_id)as  per_day_charge, \nisnull(format(cast(pl.change_date as date),'dd/MM/yyyy'),"
                    + "format(mip.admision_date,'dd/MM/yyyy')) as started_date,\nisnull(format(cast(pl.end_date as date),'dd/MM/yyyy'),format(@discharge_date,'dd/MM/yyyy')) "
                    + "as ended_date,\nDATEDIFF(DAY,isnull(pl.change_date,mip.admision_date),isnull(pl.end_date,@discharge_date))+1 AS number_of_days,\nSUM(isnull(rpd.paid_amt,0))"
                    + " OVER(PARTITION BY rpd.patient_location_rwid,rpd.patient_id,rpd.room_bed_id )  as paid_amt,\nisnull('','') as "
                    + "room_payment_id,pl.rwid as patient_locationRwid\nfrom mst_ipd_patient mip \ninner join patient_location pl on pl.patient_id=mip.patient_id\ninner join"
                    + " room_beds rb on rb.room_bed_id=isnull(pl.changed_room_bed_id,pl.previous_room_bed_id)\nleft join room_payment_details rpd on rpd.patient_id=mip.patient_id"
                    + " and rpd.patient_location_rwid=pl.rwid where mip.patient_id='" + Patient_id + "'\n ) as tab1 order by move_bed_date asc ";
            this.ps = this.con.prepareStatement(Sql);
            
            System.out.println("___TARUN__"+Sql);
            this.rs = this.ps.executeQuery();
            System.out.println(Sql);
            while (this.rs.next()) {
                HashMap<String, String> map = new HashMap<>();
                map.put("move_bed_id", this.rs.getString("move_bed_id"));
                map.put("started_date", this.rs.getString("started_date"));
                map.put("ended_date", this.rs.getString("ended_date"));
                map.put("number_of_days", this.rs.getString("number_of_days"));
                map.put("tot_room_amt", this.rs.getString("tot_room_amt"));
                map.put("paid_amt", this.rs.getString("paid_amt"));
                map.put("panding_amt", "" + (this.rs.getFloat("tot_room_amt") - this.rs.getFloat("paid_amt")));
                map.put("room_payment_id", this.rs.getString("room_payment_id"));
                map.put("patient_locationRwid", this.rs.getString("patient_locationRwid"));
                al.add(map);
            }
        } catch (Exception ex) {
            try {
                this.con.close();
                this.ps.close();
                this.rs.close();
            } catch (Exception ex1) {
                Logger.getLogger(WIPD0005_Dao.class.getName()).log(Level.SEVERE, (String) null, (Throwable) ex1);
            }
            Logger.getLogger(WIPD0005_Dao.class.getName()).log(Level.SEVERE, (String) null, (Throwable) ex);
        }
        return al;
    }
    
    
    String collectRoomPayment(HttpServletRequest request) throws Exception {
        boolean sts = false;
        String room_payment_Details_id = "";
        try {
            this.con = new DBCon().getConnection();
            this.con.setAutoCommit(false);
            sts = save_room_payment(request);
            if (sts) {
                sts = save_room_payment_Details(request);
            }
        } catch (Exception  ex) {
            
            Logger.getLogger(WIPD0005_Dao.class.getName()).log(Level.SEVERE, (String) null, (Throwable) ex);
        } 
        if (sts) {
            this.con.commit();
            this.con.close();
            this.ps.close();
            this.rs.close();
            room_payment_Details_id = this.room_payment_Details_id;
            return room_payment_Details_id;
        }
        throw new Exception("Some ERROR IN Room Payment !!");
    }
    
    
    
    private boolean save_room_payment(HttpServletRequest request) throws java.sql.SQLException{
        boolean sts = false;
        String[] checkbox_room_payment = request.getParameterValues("checkbox_room_payment");
        String patient_id = request.getParameter("patient_id");
        String createBY = request.getParameter("user_id");
        for (String sNo : checkbox_room_payment) {
        
        String room_bed_id = request.getParameter("room_bed_id_" + sNo);
        String room_payment_id = "";
        String Sql = "select room_payment_id from room_payment where patient_id='" + patient_id + "' and room_bed_id='"+room_bed_id+"' ";
        this.ps = this.con.prepareStatement(Sql);
        this.rs = this.ps.executeQuery();
        if (this.rs.next()) {
            room_payment_id = this.rs.getString("room_payment_id");
        }
        float room_amt = 0.0f;
        float paid_amt = 0.0f;
        float submit_amt = 0.0f;
          
        
        float final_paid_amt = paid_amt + submit_amt;
        float panding_amt = room_amt - final_paid_amt;
        if (room_payment_id.equals("")) {
            room_payment_id = get_room_payment_id();
            this.ps = this.con.prepareStatement("insert into room_payment(room_payment_id,patient_id,room_amt,paid_amt,panding_amt,createBY)\nvalues(?,?,?,?,?,?);");
            this.ps.setString(1, room_payment_id);
            this.ps.setString(2, patient_id);
            this.ps.setFloat(3, room_amt);
            this.ps.setFloat(4, final_paid_amt);
            this.ps.setFloat(5, panding_amt);
            this.ps.setString(6, createBY);
            sts = this.ps.executeUpdate() > 0;
        } else {
            this.ps = this.con.prepareStatement("update room_payment set room_amt=? ,paid_amt=?,panding_amt=? where patient_id=? and room_payment_id =? ");
            this.ps.setFloat(1, room_amt);
            this.ps.setFloat(2, final_paid_amt);
            this.ps.setFloat(3, panding_amt);
            this.ps.setString(4, patient_id);
            this.ps.setString(5, room_payment_id);
            sts = this.ps.executeUpdate() > 0;
        }
        
        
        }
   
        this.room_payment_id = room_payment_id;
        return sts;
    }

    private String get_room_payment_id() throws java.sql.SQLException{
        String Code = "";
        this.ps = this.con.prepareStatement("select count(*)+1 as code from room_payment");
        this.rs = this.ps.executeQuery();
        if (this.rs.next()) {
            Code = "BP_" + this.rs.getString("code");
        }
        return Code;
    }

    private boolean save_room_payment_Details(HttpServletRequest request) throws java.sql.SQLException{
        String[] checkbox_room_payment = request.getParameterValues("checkbox_room_payment");
        String patient_id = request.getParameter("patient_id");
        String createBY = request.getParameter("user_id");
        this.room_payment_Details_id = get_room_payment_details_id();
        this.ps = this.con.prepareStatement("insert into room_payment_details"
                + "(room_payment_details_id,room_payment_id,patient_id,patient_location_rwid,room_bed_id,room_amt,paid_amt\n,panding_amt,createBY)"
                + "nvalues(?,?,?,?,?,?,?,?,?);");
        for (String sNo : checkbox_room_payment) {
            String room_bed_id = request.getParameter("room_bed_id_" + sNo);
            request.getParameter("paid_amt_" + sNo);
            String submit_amt = request.getParameter("submit_amt_" + sNo);
            String patient_location_rwid = request.getParameter("patient_locationRwid_" + sNo);
            this.ps.setString(1, this.room_payment_Details_id);
            this.ps.setString(2, this.room_payment_id);
            this.ps.setString(3, patient_id);
            this.ps.setString(4, patient_location_rwid);
            this.ps.setString(5, room_bed_id);
            this.ps.setString(6, "");
            this.ps.setString(7, submit_amt);
            this.ps.setString(8, "");
            this.ps.setString(9, createBY);
            this.ps.addBatch();
        }
        return this.ps.executeBatch().length > 0;
    }

    private String get_room_payment_details_id(){
         String Code = "";
        try {
           
            this.ps = this.con.prepareStatement("select count(*)+1 as code from room_payment_details");
            this.rs = this.ps.executeQuery();
            if (this.rs.next()) {
                Code = "BPD_" + this.rs.getString("code");
            }
       
        } catch (Exception ex) {
            System.out.println(ex);
        }
             return Code;
    }
       
   

    ArrayList<HashMap<String, String>> get_room_payment(String patient_id) {
        ArrayList<HashMap<String, String>> al = new ArrayList<>();
        try {
            this.con = new DBCon().getConnection();
            String Sql = "select rpd.patient_id,rpd.room_payment_details_id,rpd.paid_amt,\nformat(rpd.createDT,'dd/MM/yyyy')as createDT from room_payment_details rpd where rpd.patient_id='" + patient_id + "'";
            this.ps = this.con.prepareStatement(Sql);
            this.rs = this.ps.executeQuery();
            while (this.rs.next()) {
                HashMap<String, String> map = new HashMap<>();
                map.put("patient_id", this.rs.getString("patient_id"));
                map.put("room_payment_details_id", this.rs.getString("room_payment_details_id"));
                map.put("paid_amt", this.rs.getString("paid_amt"));
                map.put("createDT", this.rs.getString("createDT"));
                al.add(map);
            }
        } catch (Exception ex) {
         
                Logger.getLogger(WIPD0005_Dao.class.getName()).log(Level.SEVERE, (String) null, (Throwable) ex);
            }
               return al; 
        }
    
    
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Laboratory;

import Dao.DBCon;
import HR.WHR0001_SERV;
import Master.WMAS0001_Dao;
import PatitentPayment.PatitentPaymentDao;
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

/**
 *
 * @author Tarun
 */
public class WLAB0001_dao {

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

                sql = "select isnull(patient_id,'')as patient_id,isnull(concat(patient_name,' / ',father_name,' / ',room_bed_id),'')as patient_name  from mst_ipd_patient where pt_sts='Admitted'";

            }

            if (cd.equals("2")) {

                sql = "select isnull(patient_id,'')as patient_id,isnull(concat(patient_name,' / ',father_name,' / ',room_bed_id),'')as patient_name  from mst_ipd_patient";

            }

            if (cd.equals("3")) {

                sql = "select isnull(Bill_no,'')as Bill_no,isnull(Bill_no,'')as Bill_no from ipd_final_bill";

            }

            if (cd.equals("4")) {

                sql = "select isnull(Patient_Id,'')as Patient_Id,isnull(Patient_Id,'')as Patient_Id from ipd_final_bill";

            }

            if (cd.equals("5")) {

                sql = "select isnull(voucher_id,'')as voucher_id,isnull(voucher_id,'')as voucher_id from ipd_patient_payment";

            }

            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {

                al.add("<option value='" + rs.getString(1) + "'>" + rs.getString(2) + "</option>");

            }

        } catch (Exception ex) {
            Logger.getLogger(WLAB0001_dao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return al;

    }

    boolean Save_test(String Test_name) throws SQLDataException {
        boolean sts = false;

        int i = 0;
        int z = 0;
        String Code = "";
        try {

            con = new DBCon().getConnection();
            con.setAutoCommit(false);

            String Sql_test = "select count(*)+1 as code from laboratory_test";
            ps = con.prepareStatement(Sql_test);
            rs = ps.executeQuery();
            if (rs.next()) {
                Code = "Test_" + rs.getString("code");
            }

            String Sql = "insert into laboratory_test(lab_test_id,lab_test_name)Values(?,?)";
            ps = con.prepareStatement(Sql);
            ps.setString(1, Code);
            ps.setString(2, Test_name);
            i = ps.executeUpdate();

            if (i >= 0) {

                con.commit();
                sts = true;
            }

        } catch (Exception e) {
            Logger.getLogger(WLAB0001_dao.class.getName()).log(Level.SEVERE, null, e);
        }

        return sts;
    }

    public ArrayList<HashMap<String, String>> get_test_data() throws SQLException, IOException {
        ArrayList<HashMap<String, String>> al = new ArrayList<>();
        try {
            con = new DBCon().getConnection();

            String Sql = "select isnull(lt.lab_test_id,'')as lab_test_id,isnull(lab_test_name,'')as lab_test_name,isnull(rate,0)as rate\n"
                    + ",isnull(test_sts,'')as test_sts from laboratory_test lt inner join test_rates tr on tr.lab_test_id=lt.lab_test_id where isnull(test_sts,'')='Active' ";

            ps = con.prepareStatement(Sql);
            //System.out.println("====>stff"+Sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                HashMap<String, String> Map = new HashMap();

                Map.put("lab_test_id", rs.getString("lab_test_id"));
                Map.put("lab_test_name", rs.getString("lab_test_name"));
                Map.put("test_sts", rs.getString("test_sts"));
                Map.put("rate", rs.getString("rate"));

                al.add(Map);
            }

        } catch (Exception ex) {
            Logger.getLogger(WLAB0001_dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return al;

    }

    public ArrayList<HashMap<String, String>> get_test_rate() throws SQLException, IOException {
        ArrayList<HashMap<String, String>> al = new ArrayList<>();
        try {
            con = new DBCon().getConnection();

            String Sql = "select isnull(lt.lab_test_id,'')as lab_test_id,isnull(lab_test_name,'')as lab_test_name,isnull(rate,0)as rate\n"
                    + "from laboratory_test lt left join test_rates tr \n"
                    + "on lt.lab_test_id=tr.lab_test_id";

            ps = con.prepareStatement(Sql);
            //System.out.println("====>stff"+Sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                HashMap<String, String> Map = new HashMap();

                Map.put("lab_test_id", rs.getString("lab_test_id"));
                Map.put("lab_test_name", rs.getString("lab_test_name"));
                Map.put("rate", rs.getString("rate"));

                al.add(Map);
            }

        } catch (Exception ex) {
            Logger.getLogger(WLAB0001_dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return al;

    }

    boolean Save_test_rates(String[] Test_id, String[] Rates) throws SQLDataException {
        boolean sts = false;

        int i = 0;
        int z = 0;
        String Code = "";
        try {

            con = new DBCon().getConnection();
            con.setAutoCommit(false);

            String delete = "delete from test_rates";
            ps = con.prepareStatement(delete);
            z = ps.executeUpdate();
            if (z >= 0) {

                String Sql = "insert into test_rates(lab_test_id,rate)Values(?,?)";
                ps = con.prepareStatement(Sql);
                for (String cd : Test_id) {

                    ps.setString(1, cd);
                    ps.setString(2, Rates[i]);

                    ps.addBatch();
                    i++;

                }
                sts = ps.executeBatch().length > 0;
                if (sts) {

                    con.commit();
                    sts = true;
                }
            }

        } catch (Exception e) {
            Logger.getLogger(WLAB0001_dao.class.getName()).log(Level.SEVERE, null, e);
        }

        return sts;
    }

    boolean Save_test_assigned(String[] Test_id, String Patient_id, String user, String Total_Amount, String Amount_paid, String Amount_pending,
            String[] test_paid_amt, String[] test_pending_amt, String[] test_rate) throws SQLDataException {
        boolean sts = false;

        int i = 0;
        int z = 0;
        String Code = "";
        try {

            con = new DBCon().getConnection();
            con.setAutoCommit(false);

            String Sql_lab_code = "select count(*)+1 as code from lab_test_assigned";
            ps = con.prepareStatement(Sql_lab_code);
            rs = ps.executeQuery();
            if (rs.next()) {
                Code = "LAB_" + rs.getString("code");
            }

            String Sql = "insert into lab_test_assigned(patient_id,test_id,assigned_by,assign_test_id,test_paid_amt,test_pending_amt,test_rate)Values(?,?,?,?,?,?,?)";
            ps = con.prepareStatement(Sql);
            int index = 0;
            for (String cd : Test_id) {

                ps.setString(1, Patient_id);
                ps.setString(2, cd);
                ps.setString(3, user);
                ps.setString(4, Code);
                ps.setString(5, test_paid_amt[index]);
                ps.setString(6, test_pending_amt[index]);
                ps.setString(7, test_rate[index]);

                ps.addBatch();
                i++;
                index++;
            }
            sts = ps.executeBatch().length > 0;
            if (sts) {

                String Sql_lab_pay = "insert into lab_test_payments(assign_test_id,patient_id,total_amount,paid_amount,left_amount)Values(?,?,?,?,?)";
                ps = con.prepareStatement(Sql_lab_pay);
                ps.setString(1, Code);
                ps.setString(2, Patient_id);
                ps.setString(3, Total_Amount);
                ps.setString(4, Amount_paid);
                ps.setString(5, Amount_pending);

                z = ps.executeUpdate();
            }

            if (z >= 0) {

                HashMap<String, Object> patitent_paymen_data = new HashMap<>();
                patitent_paymen_data.put("patitent_id", Patient_id);
                patitent_paymen_data.put("payment_id", Code);
                patitent_paymen_data.put("payment_type", "Test Lab Fees");
                patitent_paymen_data.put("mode", "testLab");
                patitent_paymen_data.put("create_by", user);

                patitent_paymen_data.put("pay_amt", Total_Amount);
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

            }

        } catch (Exception e) {
            Logger.getLogger(WLAB0001_dao.class.getName()).log(Level.SEVERE, null, e);
        }

        return sts;
    }

    public ArrayList<HashMap<String, String>> get_test_data_assigned() throws SQLException, IOException {
        ArrayList<HashMap<String, String>> al = new ArrayList<>();
        try {
            con = new DBCon().getConnection();

            String Sql = "select isnull(ltb.patient_id,'')as patient_id,isnull(test_id,'')as test_id,isnull(ltb.test_sts,'')as test_sts,\n"
                    + "isnull(patient_name,'')as patient_name,isnull(age,'')as age,isnull(lab_test_name,'')as lab_test_name \n"
                    + "from lab_test_assigned ltb left join mst_ipd_patient mip on ltb.patient_id=mip.patient_id \n"
                    + "left join laboratory_test lt on ltb.test_id=lt.lab_test_id\n"
                    + "where ltb.test_sts='Pending'\n";

            ps = con.prepareStatement(Sql);
            //System.out.println("====>stff"+Sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                HashMap<String, String> Map = new HashMap();

                Map.put("patient_id", rs.getString("patient_id"));
                Map.put("test_id", rs.getString("test_id"));
                Map.put("test_sts", rs.getString("test_sts"));
                Map.put("patient_name", rs.getString("patient_name"));
                Map.put("age", rs.getString("age"));
                Map.put("lab_test_name", rs.getString("lab_test_name"));

                al.add(Map);
            }

        } catch (Exception ex) {
            Logger.getLogger(WLAB0001_dao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return al;

    }

//    for changing the status
    boolean change_test_sts(String test_id, String Patient_id, String user) throws SQLDataException {
        boolean sts = false;

        int i = 0;
        int z = 0;
        String Code = "";
        try {

            con = new DBCon().getConnection();
            con.setAutoCommit(false);

            String Sql = "update lab_test_assigned set test_sts='Done',done_by='" + user + "',done_date=GETDATE() where patient_id='" + Patient_id + "' and test_id='" + test_id + "' ";
            ps = con.prepareStatement(Sql);
            //System.out.println("===>sql"+Sql);
            i = ps.executeUpdate();

            if (i >= 0) {

                con.commit();
                sts = true;
            }

        } catch (Exception e) {
            Logger.getLogger(WLAB0001_dao.class.getName()).log(Level.SEVERE, null, e);
        }

        return sts;
    }

    public ArrayList<HashMap<String, String>> get_test_data_assigned_all() throws SQLException, IOException {
        ArrayList<HashMap<String, String>> al = new ArrayList<>();
        try {
            con = new DBCon().getConnection();

            String Sql = "select isnull(ltb.patient_id,'')as patient_id,isnull(test_id,'')as test_id,isnull(ltb.test_sts,'')as test_sts,\n"
                    + "isnull(patient_name,'')as patient_name,isnull(age,'')as age,isnull(lab_test_name,'')as lab_test_name \n"
                    + "from lab_test_assigned ltb left join mst_ipd_patient mip on ltb.patient_id=mip.patient_id \n"
                    + "left join laboratory_test lt on ltb.test_id=lt.lab_test_id\n"
                    + "where ltb.test_sts='done'\n";

            ps = con.prepareStatement(Sql);
            //System.out.println("====>stff"+Sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                HashMap<String, String> Map = new HashMap();

                Map.put("patient_id", rs.getString("patient_id"));
                Map.put("test_id", rs.getString("test_id"));
                Map.put("test_sts", rs.getString("test_sts"));
                Map.put("patient_name", rs.getString("patient_name"));
                Map.put("age", rs.getString("age"));
                Map.put("lab_test_name", rs.getString("lab_test_name"));

                al.add(Map);
            }

        } catch (Exception ex) {
            Logger.getLogger(WLAB0001_dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return al;

    }

// for geeting the dues 
    public ArrayList<HashMap<String, String>> get_test_data_assigned_dues() throws SQLException, IOException {
        ArrayList<HashMap<String, String>> al = new ArrayList<>();
        try {
            con = new DBCon().getConnection();

            String Sql = "";

            ps = con.prepareStatement(Sql);
            //System.out.println("====>stff"+Sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                HashMap<String, String> Map = new HashMap();

                Map.put("patient_id", rs.getString("patient_id"));
                Map.put("test_id", rs.getString("test_id"));
                Map.put("test_sts", rs.getString("test_sts"));
                Map.put("patient_name", rs.getString("patient_name"));
                Map.put("age", rs.getString("age"));
                Map.put("lab_test_name", rs.getString("lab_test_name"));

                al.add(Map);
            }

        } catch (Exception ex) {
            Logger.getLogger(WLAB0001_dao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return al;

    }

    public ArrayList<HashMap<String, String>> get_test_data2(String patient_id) throws SQLException, IOException {
        ArrayList<HashMap<String, String>> al = new ArrayList<>();
        try {
            con = new DBCon().getConnection();

            String Sql = "select  lt.lab_test_id ,lt.lab_test_name,lt.test_sts,tr.rate as test_amt\n"
                    + ",isnull(0,0)as test_paid_amt ,isnull((0),0)as test_left_amt ,'' as 'Chackbox'\n"
                    + "from laboratory_test lt\n"
                    + "inner join test_rates tr on lt.lab_test_id=tr.lab_test_id\n"
                    + "where lt.lab_test_id not in\n"
                    + "(select  lta.test_id from lab_test_assigned lta where lta.patient_id='" + patient_id + "')\n"
                    + "union\n"
                    + "select  lt.lab_test_id ,lt.lab_test_name,lt.test_sts,tr.rate as test_amt\n"
                    + ",isnull(lta.test_paid_amt,0)as test_paid_amt,isnull((tr.rate-isnull(lta.test_paid_amt,0)),0)as test_left_amt ,'Selected' as 'Chackbox'\n"
                    + "from laboratory_test lt\n"
                    + "inner join test_rates tr on lt.lab_test_id=tr.lab_test_id\n"
                    + "left join lab_test_assigned lta on lta.test_id=lt.lab_test_id\n"
                    + "where lta.patient_id='" + patient_id + "'";

            ps = con.prepareStatement(Sql);
            //System.out.println("====>stff"+Sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                HashMap<String, String> Map = new HashMap();

                Map.put("lab_test_id", rs.getString("lab_test_id"));
                Map.put("lab_test_name", rs.getString("lab_test_name"));
                Map.put("test_sts", rs.getString("test_sts"));
                Map.put("rate", rs.getString("test_amt"));
                Map.put("test_paid_amt", rs.getString("test_paid_amt"));
                Map.put("test_left_amt", rs.getString("test_left_amt"));
                Map.put("Chackbox", rs.getString("Chackbox"));

                al.add(Map);
            }

        } catch (Exception ex) {
            Logger.getLogger(WLAB0001_dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return al;

    }

    //for patient wise payment data 
    public ArrayList<HashMap<String, String>> Get_patient_wise_payment_data(String Patient_id) throws SQLException, IOException {
        ArrayList<HashMap<String, String>> al = new ArrayList<>();
        try {
            con = new DBCon().getConnection();
            con = new DBCon().getConnection();

            String Sql = "DECLARE @patient_id VARCHAR(50) = '"+Patient_id+"';\n" +
"DECLARE @is_ayushmaan BIT = 0;\n" +
"DECLARE @is_tpa_patient BIT = 0;\n" +
"\n" +
"-- Determine if TPA is Ayushman and get patient_type\n" +
"SELECT \n" +
"    @is_ayushmaan = CASE WHEN td.tpa_name = 'AyushMan_Bharat' THEN 1 ELSE 0 END,\n" +
"    @is_tpa_patient = CASE WHEN mip.patient_type = 'TPA' THEN 1 ELSE 0 END\n" +
"FROM mst_ipd_patient mip\n" +
"LEFT JOIN TPA_Details td ON mip.tpa_id = td.tpa_hos_code\n" +
"WHERE mip.patient_id = @patient_id;\n" +
"\n" +
"SELECT * FROM (\n" +
"    -- ROOM CHARGES\n" +
"    SELECT \n" +
"        ISNULL(pl.patient_id, '') AS patient_id,\n" +
"        'Accomadation' AS category_name,\n" +
"        CONCAT(cc.category_name, ' / ', moh.charge_name) AS charge_name,\n" +
"        'Room' AS hidden_charge,\n" +
"        ISNULL(pl.changed_room_bed_id, '') AS charge_id,\n" +
"        ISNULL(pl.start_date, '') AS bed_use_date,\n" +
"        ISNULL(pl.end_date, NULL) AS end_date,\n" +
"        CASE WHEN @is_ayushmaan = 1 THEN 0 ELSE ISNULL(pl.bed_amount, 0) END AS charge_amount,\n" +
"\n" +
"        -- Total amount\n" +
"        (\n" +
"            SELECT COUNT(*) \n" +
"            FROM (\n" +
"                SELECT TOP (\n" +
"                    DATEDIFF(DAY, pl.start_date, ISNULL(pl.end_date, GETDATE())) + 1\n" +
"                )\n" +
"                ROW_NUMBER() OVER (ORDER BY (SELECT NULL)) - 1 AS day_offset\n" +
"                FROM master.dbo.spt_values\n" +
"            ) AS Days\n" +
"            WHERE \n" +
"                day_offset = 0\n" +
"                OR DATEADD(MINUTE, \n" +
"                    DATEDIFF(MINUTE, 0, CAST(pl.start_date AS TIME)), \n" +
"                    CAST(DATEADD(DAY, day_offset, CAST(pl.start_date AS DATE)) AS DATETIME)\n" +
"                ) <= ISNULL(pl.end_date, GETDATE())\n" +
"        ) * CASE WHEN @is_ayushmaan = 1 THEN 0 ELSE ISNULL(pl.bed_amount, 0) END AS total_amount,\n" +
"\n" +
"        0 AS oc_amt_paid,\n" +
"\n" +
"        (\n" +
"            SELECT COUNT(*) \n" +
"            FROM (\n" +
"                SELECT TOP (\n" +
"                    DATEDIFF(DAY, pl.start_date, ISNULL(pl.end_date, GETDATE())) + 1\n" +
"                )\n" +
"                ROW_NUMBER() OVER (ORDER BY (SELECT NULL)) - 1 AS day_offset\n" +
"                FROM master.dbo.spt_values\n" +
"            ) AS Days\n" +
"            WHERE \n" +
"                day_offset = 0\n" +
"                OR DATEADD(MINUTE, \n" +
"                    DATEDIFF(MINUTE, 0, CAST(pl.start_date AS TIME)), \n" +
"                    CAST(DATEADD(DAY, day_offset, CAST(pl.start_date AS DATE)) AS DATETIME)\n" +
"                ) <= ISNULL(pl.end_date, GETDATE())\n" +
"        ) * CASE WHEN @is_ayushmaan = 1 THEN 0 ELSE ISNULL(pl.bed_amount, 0) END AS oc_amt_left,\n" +
"\n" +
"        CAST(( \n" +
"            SELECT COUNT(*) \n" +
"            FROM (\n" +
"                SELECT TOP (\n" +
"                    DATEDIFF(DAY, pl.start_date, ISNULL(pl.end_date, GETDATE())) + 1\n" +
"                )\n" +
"                ROW_NUMBER() OVER (ORDER BY (SELECT NULL)) - 1 AS day_offset\n" +
"                FROM master.dbo.spt_values\n" +
"            ) AS Days\n" +
"            WHERE \n" +
"                day_offset = 0\n" +
"                OR DATEADD(MINUTE, \n" +
"                    DATEDIFF(MINUTE, 0, CAST(pl.start_date AS TIME)), \n" +
"                    CAST(DATEADD(DAY, day_offset, CAST(pl.start_date AS DATE)) AS DATETIME)\n" +
"                ) <= ISNULL(pl.end_date, GETDATE())\n" +
"        ) AS FLOAT) AS qty,\n" +
"\n" +
"        pl.rwid AS patient_location_rwid,\n" +
"        ISNULL(pl.status, '') AS status\n" +
"    FROM patient_location AS pl \n" +
"    INNER JOIN room_beds rb ON rb.room_bed_id = pl.changed_room_bed_id\n" +
"    INNER JOIN mst_rooms mr ON rb.room_id = mr.Room_id\n" +
"    INNER JOIN charge_category cc ON cc.category_id = mr.room_category\n" +
"    INNER JOIN mst_hos_charges moh ON moh.category_id = cc.category_id\n" +
"    WHERE pl.patient_id = @patient_id\n" +
"\n" +
"    UNION ALL\n" +
"\n" +
"    -- OTHER CHARGES\n" +
"    SELECT \n" +
"        aocd.patient_id,\n" +
"        ISNULL(agp.Package_name, cc.category_name) AS category_name,\n" +
"        ISNULL(agp.Procedure_Label, mhc.charge_name) AS charge_name,\n" +
"        '' AS hidden_charge,\n" +
"        ISNULL(agp.Procedure_code, mhc.charge_id) AS charge_id,\n" +
"        MIN(aocd.createDT) AS bed_use_date,\n" +
"        MAX(aocd.createDT) AS end_date,\n" +
"\n" +
"        CASE \n" +
"            WHEN @is_ayushmaan = 1 THEN ISNULL(agp.procedure_price, 0)\n" +
"            WHEN @is_tpa_patient = 1 THEN ISNULL(aocd.TPA_amount, 0)\n" +
"            ELSE ISNULL(aocd.oc_amt_pay, 0)\n" +
"        END AS charge_amount,\n" +
"\n" +
"        SUM(\n" +
"            CASE \n" +
"                WHEN @is_ayushmaan = 1 THEN ISNULL(agp.procedure_price, 0)\n" +
"                WHEN @is_tpa_patient = 1 THEN ISNULL(aocd.TPA_amount, 0)\n" +
"                ELSE ISNULL(aocd.oc_amt_pay, 0)\n" +
"            END * aocd.oc_qty\n" +
"        ) AS total_amount,\n" +
"\n" +
"        SUM(aocd.oc_amt_paid) AS oc_amt_paid,\n" +
"        SUM(aocd.oc_amt_left) AS oc_amt_left,\n" +
"        SUM(aocd.oc_qty) AS qty,\n" +
"        0 AS patient_location_rwid,\n" +
"        'Authorize' AS status\n" +
"    FROM Add_other_charge_details aocd\n" +
"    LEFT JOIN Ayuman_GOV_package agp ON agp.Procedure_code = aocd.oc_code AND @is_ayushmaan = 1\n" +
"    LEFT JOIN mst_hos_charges mhc ON aocd.oc_code = mhc.charge_id AND @is_ayushmaan = 0\n" +
"    LEFT JOIN charge_category cc ON mhc.category_id = cc.category_id\n" +
"    WHERE aocd.patient_id = @patient_id AND aocd.status = 'Active'\n" +
"    GROUP BY \n" +
"        aocd.patient_id,\n" +
"        agp.Package_name,\n" +
"        agp.Procedure_Label,\n" +
"        agp.Procedure_code,\n" +
"        agp.procedure_price,\n" +
"        mhc.charge_name,\n" +
"        mhc.charge_id,\n" +
"        mhc.charge_amount,\n" +
"        aocd.TPA_amount,\n" +
"        aocd.oc_amt_pay,\n" +
"        cc.category_name\n" +
") AS final_result\n" +
"ORDER BY category_name, charge_name;";

            ps = con.prepareStatement(Sql);

            System.out.println("====>stff" + Sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                HashMap<String, String> Map = new HashMap();

                Map.put("patient_id", rs.getString("patient_id"));
                Map.put("patient_id", rs.getString("patient_id"));
                Map.put("category_name", rs.getString("category_name"));
                Map.put("Charge_name", rs.getString("Charge_name"));
                Map.put("charge_amount", rs.getString("charge_amount"));
                Map.put("oc_amt_paid", rs.getString("oc_amt_paid"));
                Map.put("oc_amt_left", rs.getString("oc_amt_left"));
                Map.put("qty", rs.getString("qty"));
                Map.put("total_amount", rs.getString("total_amount"));
                Map.put("charge_id", rs.getString("charge_id"));
                Map.put("patient_location_rwid", rs.getString("patient_location_rwid"));
                Map.put("status", rs.getString("status"));
                Map.put("bed_use_date", rs.getString("bed_use_date"));
                Map.put("end_date", rs.getString("end_date"));
                Map.put("hidden_charge", rs.getString("hidden_charge"));

                al.add(Map);
            }
            Get_patient_wise_Voucher_data(Patient_id);

        } catch (Exception ex) {
            Logger.getLogger(WLAB0001_dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return al;

    }

    //for geetinng vovcher details of the patient 
    float Get_patient_wise_Voucher_data(String Patient_id) throws SQLException, IOException {

        float total_voc_amount = 0;

        try {
            con = new DBCon().getConnection();

            String Sql = "select isnull(cast(sum(amount)as float),0) as total_paid from ipd_patient_payment where patient_id='" + Patient_id + "'";

            ps = con.prepareStatement(Sql);
            //System.out.println("====>stff"+Sql);
            rs = ps.executeQuery();
            System.out.println("---<" + Sql);
            while (rs.next()) {

                total_voc_amount = rs.getFloat("total_paid");

            }

            System.out.println(total_voc_amount);

        } catch (Exception ex) {
            Logger.getLogger(WLAB0001_dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return total_voc_amount;

    }

    //for saving final bill 
    public String Save_final_bill(String patient_id, String user, String Gross_amount,
            String total_voc_amount, String Discount, String Net_amount, String[] Room_amount,
            String[] Room_Room_id, String[] room_charge_rwid,
            String category_name[], String charge_name[], String qty[], String total_amount[], String charge_rate[], String final_bill_romm_date,
            String Received_from, String Pay_type, String Debit_Ac, String Checque_number, String Card_number, String UPI_type, String transcation_id, String tpa_name, String Approval_amount, String Credit_remark) {
        String sts = null;
        int i = 0;
        int y = 0;
        int z = 0;
        int a = 0;
        int r = 0;
        int h = 0;
        int t = 0;
        String code = "";
        String VOC_code = "";
        String Room_code = "";

        try {
            con = new DBCon().getConnection();
            String sql = "select count(*)+1 as code  from ipd_final_bill";

            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                code = "IPD/BILL_NO-" + rs.getString("code");
            }
            //for converting the date 
            LocalDateTime localDateTime = LocalDateTime.parse(final_bill_romm_date);

            // Format for SQL Server (YYYY-MM-DD HH:MM:SS)
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String sqlDateTime = localDateTime.format(formatter);

            //for saving the complete bill 
            boolean Save_final_bill = new WLAB0004_dao().Save_final_bill(category_name, charge_name, qty, total_amount, patient_id, code, user, charge_rate);

            String Sql = "insert into ipd_final_bill(Bill_no,patient_id,Bill_gross_amount,bill_discount,Bill_final_paid,Bill_status,created_by,created_date,bill_type,TPA_approved_amount)"
                    + "values(?,?,?,?,?,?,?,GETDATE(),?,?)";
            ps = con.prepareStatement(Sql);
            ps.setString(1, code);
            ps.setString(2, patient_id);
            ps.setString(3, Gross_amount);
            ps.setString(4, Discount);
            ps.setString(5, Net_amount);
            ps.setString(6, "Provisional_Bill");
            ps.setString(7, user);
            
            System.out.println("========>"+tpa_name);
            if(tpa_name.trim().equals("TPA")){
            ps.setString(8,"TPA BILL");  
            ps.setString(9,Approval_amount);  
            }
            if(tpa_name.trim().equals("Ayushmaan")){
              ps.setString(8,"AyushMaan BILL"); 
              ps.setString(9,"0");
            }if(tpa_name.trim().equals("Cash")){
              ps.setString(8,"Cash BILL"); 
              ps.setString(9,"0");
            }
            
            

            i = ps.executeUpdate();

            //for ending the room payemnt calculation 
            String sql_room_date = "update patient_location set end_date='" + sqlDateTime + "',bill_clearence_sts='Yes' where patient_id='" + patient_id + "' and end_date IS NULL";
            ps = con.prepareStatement(sql_room_date);
            t = ps.executeUpdate();

            String sql_update = "update Add_other_charge_details set oc_amt_paid=oc_final_amt , oc_amt_left=0 where patient_id='" + patient_id + "'";
            ps = con.prepareStatement(sql_update);
            z = ps.executeUpdate();

            String sql_update_lab = "update   lab_test_assigned    set  test_paid_amt=test_rate ,test_pending_amt=0 ,test_sts='Done' where patient_id='" + patient_id + "'";
            ps = con.prepareStatement(sql_update_lab);
            a = ps.executeUpdate();

            String sql_voc = "select count(*)+1 as voc from ipd_patient_payment";
            ps = con.prepareStatement(sql_voc);
            rs = ps.executeQuery();
            if (rs.next()) {
                VOC_code = "VOC_" + rs.getString("voc");

            }

            String Sql_voc_insert = "insert into ipd_patient_payment(patient_id,amount,payment_date,taken_by,voucher_id,remark,paytype,cheque_number,card_number,upi_type,transcation_id,recieved_from,Debit_AC,credit_remark,credit_status)values(?,?,getdate(),?,?,?,?,?,?,?,?,?,?,?,?)";
            ps = con.prepareStatement(Sql_voc_insert);
            ps.setString(1, patient_id);
            ps.setString(2, Net_amount);
            ps.setString(3, user);
            ps.setString(4, VOC_code);
            ps.setString(5, "Final Bill Clerance");
            ps.setString(6, Pay_type);
            ps.setString(7, Checque_number);
            ps.setString(8, Card_number);
            ps.setString(9, UPI_type);
            ps.setString(10, transcation_id);
            ps.setString(11, Received_from);
            ps.setString(12, Debit_Ac);
            ps.setString(13, Credit_remark);
            if (tpa_name.trim().equals("TPA")) {
                ps.setString(14, "Pending With TPA");
                           } 
        if(tpa_name.trim().equals("Ayushmaan")){
                 ps.setString(14, "Pending With Ayushmaan Bharat");
            } 
            else{
                ps.setString(14, "");
            }

            r = ps.executeUpdate();

            System.out.println("====>" + patient_id);

            //for ayushman credit saving
            // Save_credit_bill_ayushman(patient_id,code,Net_amount,user);
            //room payment 
            int index = 0;
            for (String room : Room_Room_id) {

                System.out.println("---Ram-->" + room);

                String sql_check_room = "select * from room_payment where patient_id='" + patient_id + "' and room_bed_id='" + room + "'";
                ps = con.prepareStatement(sql_check_room);
                rs = ps.executeQuery();

                if (rs.next()) {
                    int rw = 0;
                    for (String G : Room_amount) {
                        String sql_update_room = "update room_payment set  paid_amt=" + G + " ,panding_amt=0  where patient_id='" + patient_id + "' and room_bed_id='" + room + "' ";
                        ps = con.prepareStatement(sql_update_room);
                        a = ps.executeUpdate();

                        String room_payment_id = fetch_room_payment_details_id(room, patient_id);

                        String room_payment_Details_id = get_room_payment_details_id();

                        String sql_update_room_detail = "insert into room_payment_details(room_payment_details_id,room_payment_id,"
                                + "patient_id,patient_location_rwid,room_bed_id,"
                                + "room_amt,paid_amt\n,panding_amt,createBY)\nvalues(?,?,?,?,?,?,?,0,?) "
                                + "";
                        ps = con.prepareStatement(sql_update_room_detail);

                        ps.setString(1, room_payment_Details_id);
                        ps.setString(2, room_payment_id);
                        ps.setString(3, patient_id);
                        ps.setString(4, room_charge_rwid[rw]);
                        ps.setString(5, room);
                        ps.setString(6, G);
                        ps.setString(7, G);
                        ps.setString(8, user);

                        a = ps.executeUpdate();
                        rw++;

                    }
                } else {

                    String Sql_room_code = "select count(*)+1 as code from room_payment";
                    ps = con.prepareStatement(Sql_room_code);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        Room_code = "BP_" + rs.getString("code");
                    }

                    String sql_insert_room = "insert into room_payment(room_payment_id,patient_id,room_amt,paid_amt,panding_amt,createDT,createBy,room_bed_id)"
                            + "Values(?,?,?,?,0,getdate(),?,?)";

                    System.out.println("======insert" + Room_code);

                    ps = con.prepareStatement(sql_insert_room);
                    //for (String Room_amt : Room_amount) {
                    ps.setString(1, Room_code);
                    ps.setString(2, patient_id);
                    ps.setString(3, Room_amount[index]);
                    ps.setString(4, Room_amount[index]);
                    ps.setString(5, user);
                    ps.setString(6, room);

                    h = ps.executeUpdate();

                    String room_payment_id = fetch_room_payment_details_id(room, patient_id);

                    String room_payment_Details_id = get_room_payment_details_id();

                    String sql_update_room_detail = "insert into room_payment_details(room_payment_details_id,room_payment_id,"
                            + "patient_id,patient_location_rwid,room_bed_id,"
                            + "room_amt,paid_amt\n,panding_amt,createBY)\nvalues(?,?,?,?,?,?,?,0,?) "
                            + "";
                    ps = con.prepareStatement(sql_update_room_detail);

                    ps.setString(1, room_payment_Details_id);
                    ps.setString(2, room_payment_id);
                    ps.setString(3, patient_id);
                    ps.setString(4, room_charge_rwid[index]);
                    ps.setString(5, room);
                    ps.setString(6, Room_amount[index]);
                    ps.setString(7, Room_amount[index]);
                    ps.setString(8, user);

                    a = ps.executeUpdate();
                    index++;

                    //}
                }
            }

            if (h > 0) {
                HashMap<String, Object> patitent_paymen_data = new HashMap<>();
                patitent_paymen_data.put("patitent_id", patient_id);
                patitent_paymen_data.put("payment_id", "VOC");
                patitent_paymen_data.put("payment_type", "Final Bill Clearance");
                patitent_paymen_data.put("mode", "Payment_Voucher");
                patitent_paymen_data.put("create_by", user);

                patitent_paymen_data.put("pay_amt", "0");
                patitent_paymen_data.put("paid_amt", Net_amount);
                patitent_paymen_data.put("pending_amt", 0);

                boolean pp_status = new PatitentPaymentDao().savePayment(patitent_paymen_data);
                if (pp_status) {
                    con.commit();
                    sts = code;
                } else {
                    sts = "";
                    throw new Exception("Payment Table Error!!");
                }

            }

        } catch (Exception ex) {
            Logger.getLogger(WMAS0001_Dao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return sts;
    }

    //for cedit bill entery 
    public String Save_credit_bill_ayushman(String patient_id, String bill_id, String credit_amount, String created_by) {
        String Credt_bill_id = "";

        int a = 0;

        try {
            con = new DBCon().getConnection();

            String sql_update_room_detail = "insert into mst_credit_bill(patient_id,bill_id,credit_amount,credit_amount_paid,credit_remark,credit_amt_status,created_by)\n"
                    + "Values(?,?,?,?,?,?,?,?)";
            ps = con.prepareStatement(sql_update_room_detail);

            ps.setString(1, patient_id);
            ps.setString(2, bill_id);
            ps.setString(3, credit_amount);
            ps.setString(4, "0");
            ps.setString(5, "Ayuhmaan Patient Bill");
            ps.setString(6, "Pending");
            ps.setString(7, created_by);
            a = ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("----->Credit Error" + e);
        }
        return Credt_bill_id;
    }

    private String fetch_room_payment_details_id(String Room_bed_id, String partient_id) {
        String Code = "";
        try {

            this.ps = this.con.prepareStatement("select room_payment_id  from room_payment where patient_id='" + partient_id + "' and room_bed_id='" + Room_bed_id + "'");
            this.rs = this.ps.executeQuery();
            System.out.println("--->" + this.ps);
            if (this.rs.next()) {
                return this.rs.getString("room_payment_id");
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
        return Code;
    }
    //

    private String get_room_payment_details_id() {
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

    //for voc data 
    public ArrayList<HashMap<String, String>> get_total_voc_data(String Patient_id) throws SQLException, IOException {
        ArrayList<HashMap<String, String>> al = new ArrayList<>();
        try {
            con = new DBCon().getConnection();

            String Sql = "  select isnull(patient_id,'')as patient_id,\n"
                    + "  isnull(amount,'')as amount,\n"
                    + "  isnull(format(payment_date,'dd/MM/yy'),'')as pay_date,isnull(remark,'')as reamrk\n"
                    + "  from ipd_patient_payment where patient_id='" + Patient_id + "'";

            ps = con.prepareStatement(Sql);
            System.out.println("====>stff" + Sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                HashMap<String, String> Map = new HashMap();

                Map.put("patient_id", rs.getString("patient_id"));
                Map.put("amount", rs.getString("amount"));
                Map.put("pay_date", rs.getString("pay_date"));
                Map.put("reamrk", rs.getString("reamrk"));

                al.add(Map);
            }

        } catch (Exception ex) {
            Logger.getLogger(WLAB0001_dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return al;

    }

    //for geeting discount of the bill 
    int Get_discount_of_BIll(String Patient_id) {
        int discount = 0;

        try {

            con = new DBCon().getConnection();

            String Sql = "select isnull(bill_discount,0)as bill_discount from ipd_final_bill where Patient_Id='" + Patient_id + "'";

            ps = con.prepareStatement(Sql);
            System.out.println("====>stff" + Sql);
            rs = ps.executeQuery();
            while (rs.next()) {

                discount = rs.getInt("bill_discount");

            }

        } catch (Exception e) {
            System.out.println("==>" + e);
        }
        return discount;
    }

    //for checking Ayushmaan 
    public String Check_ayushMaan(String Patient_id) throws SQLException, IOException {

        String Check_TPA = "";
        try {
            con = new DBCon().getConnection();

            String Sql = "select patient_type from mst_ipd_patient where patient_id='" + Patient_id + "'";

            ps = con.prepareStatement(Sql);
            System.out.println("====>stff" + Sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Check_TPA = rs.getString("patient_type");

            }

        } catch (Exception ex) {
            Logger.getLogger(WLAB0001_dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Check_TPA;

    }

    //for checing payemnet recieve
    public String Check_BILL_payemnet(String Patient_id) throws SQLException, IOException {

        String Bill_payment = "";
        try {
            con = new DBCon().getConnection();

            String Sql = "select isnull(Bill_no,'')as Bill_no from ipd_final_bill where Patient_Id='" + Patient_id + "'";

            ps = con.prepareStatement(Sql);
            System.out.println("====>stff" + Sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Bill_payment = rs.getString("Bill_no");

            }

        } catch (Exception ex) {
            Logger.getLogger(WLAB0001_dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Bill_payment;

    }

    //check Bill Approval 
    public String Check_BILL_Arroval(String Patient_id) throws SQLException, IOException {

        String Bill_Arroval = "";
        try {
            con = new DBCon().getConnection();

            String Sql = "select isnull(Bill_status,'')as Bill_status from ipd_final_bill where Patient_Id='" + Patient_id + "'";

            ps = con.prepareStatement(Sql);
            System.out.println("====>stff" + Sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Bill_Arroval = rs.getString("Bill_status");

            }

        } catch (Exception ex) {
            Logger.getLogger(WLAB0001_dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Bill_Arroval;

    }

    //for admission date 
    public String get_admission_date(String Patient_id) throws SQLException, IOException {

        String admision_date = "";
        try {
            con = new DBCon().getConnection();

            String Sql = "select Isnull(admision_date,'')as admision_date from mst_ipd_patient\n"
                    + "where patient_id='" + Patient_id + "'";

            ps = con.prepareStatement(Sql);
            System.out.println("====>stff" + Sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                admision_date = rs.getString("admision_date");

            }

        } catch (Exception ex) {
            Logger.getLogger(WLAB0001_dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return admision_date;

    }

    //for checking the final bill status paid 
    public String check_bill_status(String Patient_id) throws SQLException, IOException {

        String Bill_paid_satus = "";
        try {
            con = new DBCon().getConnection();

            String Sql = "select * from ipd_final_bill where Patient_Id='" + Patient_id + "'";

            ps = con.prepareStatement(Sql);

            rs = ps.executeQuery();
            if (rs.next()) {
                Bill_paid_satus = "Bill_Paid";

            } else {
                Bill_paid_satus = "Bill_Not_Paid";
            }

        } catch (Exception ex) {
            Logger.getLogger(WLAB0001_dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Bill_paid_satus;

    }

    //for cancling the Bill 
    public String cancel_final_bill(String Patient_id) {
        String sts = "";
        int i = 0;
        int y = 0;
        int z = 0;
        int a = 0;
        int d = 0;

        try {
            con = new DBCon().getConnection();
            String Sql = "delete from Patient_waise_bill_saving where patient_id='" + Patient_id + "'";

            ps = con.prepareStatement(Sql);
            i = ps.executeUpdate();

            if (i >= 0) {

                String Sql_final_bill = "delete from ipd_final_bill where patient_id='" + Patient_id + "'";
                ps = con.prepareStatement(Sql_final_bill);
                y = ps.executeUpdate();
            }
            if (y >= 0) {
                String Sql_ipd_patient_payment = "delete from ipd_patient_payment where  patient_id='" + Patient_id + "' and remark='Final Bill Clerance'";
                ps = con.prepareStatement(Sql_ipd_patient_payment);
                z = ps.executeUpdate();
            }
            if (z >= 0) {

                String Sql_final_bill = "update Add_other_charge_details set oc_amt_paid=0 , oc_amt_left=oc_amt_paid where patient_id='" + Patient_id + "'";
                ps = con.prepareStatement(Sql_final_bill);
                a = ps.executeUpdate();
            }
            if (a >= 0) {
                String Sql_Bed_update = "update patient_location set end_date=null  where patient_id='" + Patient_id + "' and bill_clearence_sts='Yes' ";
                ps = con.prepareStatement(Sql_Bed_update);
                d = ps.executeUpdate();

            }

            if (d > 0) {
                con.commit();
                con.close();
                sts = "Bill_Cancled_Successfully";
            }

        } catch (Exception ex) {
            Logger.getLogger(WMAS0001_Dao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return sts;
    }
    
    
    
 //function for geeting patient
    
    public ArrayList<HashMap<String, String>> get_patient_data(String patient_id) throws SQLException, IOException {
        ArrayList<HashMap<String, String>> al = new ArrayList<>();
        try {
            con = new DBCon().getConnection();

            String Sql = "SELECT \n" +
"  ISNULL(CONCAT(gender_type, ' ', patient_name, ' / ', relation_of, ' ', father_name), '') AS name,\n" +
"  ISNULL(room_bed_id, '') AS bed,\n" +
"  ISNULL(admision_date, '') AS admit_date,\n" +
"  CASE \n" +
"    WHEN discharge_date IS NULL THEN 'Not Discharged'\n" +
"    ELSE CONVERT(VARCHAR, discharge_date, 103)\n" +
"  END AS Dis_date,\n" +
"  ISNULL(concat(fb.bill_status,' ','Prepared'), 'Bill Not Prepared') AS bill_status\n" +
"FROM mst_ipd_patient p\n" +
"LEFT JOIN ipd_final_bill fb ON fb.patient_id = p.patient_id  where p.patient_id='"+patient_id+"';";

            ps = con.prepareStatement(Sql);
            //System.out.println("====>stff"+Sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                HashMap<String, String> Map = new HashMap();

                Map.put("name", rs.getString("name"));
                Map.put("bed", rs.getString("bed"));
                Map.put("admit_date", rs.getString("admit_date"));
                Map.put("Dis_date", rs.getString("Dis_date"));
                Map.put("bill_status", rs.getString("bill_status"));
             
                al.add(Map);
            }

        } catch (Exception ex) {
            Logger.getLogger(WLAB0001_dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return al;

    }
    
    

}

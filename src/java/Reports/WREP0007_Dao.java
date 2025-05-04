/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reports;

import Dao.DBCon;
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
 * @author Lenovo
 */
public class WREP0007_Dao {
    Connection con;
    PreparedStatement ps;

    ResultSet rs;
    Statement stmt;

    public ArrayList<HashMap<String, String>> get_mini_day_bbok(String[] arr) throws SQLException, IOException {
        ArrayList<HashMap<String, String>> al = new ArrayList<>();
        try {
            this.con = new DBCon().getConnection();
            String Sql = "select * from (\nselect 'Credit' as payment_type,'IPD' as payment_from,\n mip.patient_name as patient_name,ppd.paid_amt as amount_in, 0 as amount_out, format(ppd.create_dt,'dd-MM-yyyy')as submit_date,\n ppd.create_by,ppd.payment_type as pay_description\nfrom patitent_payment_details ppd\ninner join mst_ipd_patient mip on ppd.patitent_id=mip.patient_id\nwhere 1=1 and ppd.paid_amt>0\nunion \nselect 'Credit' as payment_type,'OPD' as payment_from,\n mo.patient_name as patient_name,ppd.paid_amt as amount_in, 0 as amount_out, format(ppd.create_dt,'dd-MM-yyyy')as submit_date,\n ppd.create_by,ppd.payment_type as pay_description\nfrom patitent_payment_details ppd\ninner join mst_opd mo on ppd.patitent_id=mo.opd_id\nwhere 1=1 and ppd.paid_amt>0\n) as tab where 1=1  ";
            if (arr[1] != null && arr[2] != "") {
                Sql = Sql + " and submit_date between '" + arr[1] + "' and '" + arr[2] + "'";
            }
            if (arr[4] != null && arr[4] != "") {
                Sql = Sql + " and type='" + arr[4] + "'";
            }
            if (arr[3] != null && arr[3] != "") {
                Sql = Sql + "and mip.patient_id='" + arr[3] + "'";
            }
            String Sql2 = Sql + " order by submit_date";
            this.ps = this.con.prepareStatement(Sql2);
            System.out.println("====>stff" + Sql2);
            this.rs = this.ps.executeQuery();
            while (this.rs.next()) {
                HashMap<String, String> Map = new HashMap<>();
                Map.put("payment_type", this.rs.getString("payment_type"));
                Map.put("payment_from", this.rs.getString("payment_from"));
                Map.put("patient_name", this.rs.getString("patient_name"));
                Map.put("amount_in", this.rs.getString("amount_in"));
                Map.put("amount_out", this.rs.getString("amount_out"));
                Map.put("submit_date", this.rs.getString("submit_date"));
                Map.put("create_by", this.rs.getString("create_by"));
                Map.put("pay_description", this.rs.getString("pay_description"));
                al.add(Map);
            }
        } catch (Exception ex) {
            Logger.getLogger(WREP0007_Dao.class.getName()).log(Level.SEVERE, (String) null, (Throwable) ex);
        }
        return al;
    }

    public ArrayList<HashMap<String, String>> get_mini_day_bbok_opd(String[] arr) throws SQLException, IOException {
        ArrayList<HashMap<String, String>> al = new ArrayList<>();
        try {
            this.con = new DBCon().getConnection();
            String Sql = "select 'Credit' as payment_type,'OPD' as payment_from,\n mo.patient_name as patient_name,ppd.paid_amt as amount_in, 0 as amount_out, format(ppd.create_dt,'dd-MM-yyyy')as submit_date,\n ppd.create_by,ppd.payment_type as pay_description,isnull(free_opd,'NO') as free\nfrom patitent_payment_details ppd\ninner join mst_opd mo on ppd.patitent_id=mo.opd_id\nwhere 1=1 and ppd.paid_amt>0  ";
            if (arr[1] != null && arr[2] != "") {
                Sql = Sql + " and submit_date between '" + arr[1] + "' and '" + arr[2] + "'";
            }
            if (arr[4] != null && arr[4] != "") {
                Sql = Sql + " and type='" + arr[4] + "'";
            }
            if (arr[3] != null && arr[3] != "") {
                Sql = Sql + "and mip.patient_id='" + arr[3] + "'";
            }
            String Sql2 = Sql + " order by submit_date";
            this.ps = this.con.prepareStatement(Sql2);
            System.out.println("====>stff" + Sql2);
            this.rs = this.ps.executeQuery();
            while (this.rs.next()) {
                HashMap<String, String> Map = new HashMap<>();
                Map.put("payment_type", this.rs.getString("payment_type"));
                Map.put("payment_from", this.rs.getString("payment_from"));
                Map.put("patient_name", this.rs.getString("patient_name"));
                if (this.rs.getString("free").equals("NO")) {
                    Map.put("amount_in", this.rs.getString("amount_in"));
                } else {
                    Map.put("amount_in", "0");
                }
                Map.put("amount_out", this.rs.getString("amount_out"));
                Map.put("submit_date", this.rs.getString("submit_date"));
                Map.put("create_by", this.rs.getString("create_by"));
                Map.put("pay_description", this.rs.getString("pay_description"));
                Map.put("free", this.rs.getString("free"));
                al.add(Map);
            }
        } catch (Exception ex) {
            Logger.getLogger(WREP0007_Dao.class.getName()).log(Level.SEVERE, (String) null, (Throwable) ex);
        }
        return al;
    }
}

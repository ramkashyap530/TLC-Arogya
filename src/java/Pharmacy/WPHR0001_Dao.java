/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pharmacy;

import Dao.DBCon;
import Master.WMAS0001_Dao;
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
public class WPHR0001_Dao {

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

                sql = "select isnull(item_code,'')as item_code,isnull(item_name,'')as item_name from phr_items";

            }
            if (cd.equals("2")) {
                sql = "select isnull(patient_id,'')as patient_id,\n"
                        + "isnull(concat(ipd_no,'  /  ',patient_name,' / ',room_bed_id),'')as patient_name\n"
                        + "from mst_ipd_patient";
            }
            if (cd.equals("3")) {
                sql = "";
                System.out.println("---->onchange" + sql);
            }

            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {

                al.add("<option value='" + rs.getString(1) + "'>" + rs.getString(2) + "</option>");

            }

        } catch (Exception ex) {
            Logger.getLogger(WMAS0001_Dao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return al;

    }

//  for pharmacy billing 
    public ArrayList<HashMap<String, String>> get_sale_data(String prd_cd) {

        ArrayList<HashMap<String, String>> prd_data = new ArrayList<>(); // Create an ArrayList object
        HashMap<String, String> prdcd = null;
        try {

            con = new DBCon().getConnection();
            String sql = "select ISNULL(pi.item_code,'')as item_code,isnull(item_name,'')as item_name,\n"
                    + "isnull(phr.item_sale_rate,'')as sale_rate,isnull(item_type,'')as item_type,\n"
                    + "isnull(min_stock,'')as min_stock,ISNULL(qty_in,0)as qty,"
                    + "isnull(bathc_no,'')as bathc_no,isnull(hsn_code,'')as hsn_code,\n"
                    + "isnull(format(manu_date,'dd/MM/yyyy'),'')as manu_date,\n"
                    + "isnull(format(EXP_date,'dd/MM/yyyyy'),'')as EXP_date from phr_items pi\n"
                    + "inner join phr_item_stock_in pis on pis.item_code=pi.item_code\n"
                    + "inner join phr_item_rates phr on phr.item_code=pi.item_code where pi.item_code='" + prd_cd + "'";

            System.out.println("====sql" + sql);

            System.out.println("sql-> " + sql);
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                prdcd = new HashMap<>();
                prdcd.put("item_code", rs.getString("item_code"));
                prdcd.put("item_name", rs.getString("item_name"));

                prdcd.put("sale_rate", rs.getString("sale_rate"));
                prdcd.put("min_stock", rs.getString("min_stock"));
                prdcd.put("qty", rs.getString("qty"));
                prdcd.put("item_type", rs.getString("item_type"));
                prdcd.put("bathc_no", rs.getString("bathc_no"));
                prdcd.put("hsn_code", rs.getString("hsn_code"));
                prdcd.put("manu_date", rs.getString("manu_date"));
                prdcd.put("EXP_date", rs.getString("EXP_date"));

                prd_data.add(prdcd);
            }

        } catch (Exception ex) {
            Logger.getLogger(WPHR0001_Dao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
                ps.close();
                rs.close();
                return prd_data;
            } catch (SQLException ex) {
                Logger.getLogger(WPHR0001_Dao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return prd_data;
    }

    boolean saveitem(String Item_name, String sale_price, String purchase_price, String Min_stock,
            String created_by, String opeaning_stock, String user, String item_type, String vendor,
            String hsn_code, String batch_no, String Manfu_date, String Exp_date) throws SQLDataException {
        boolean sts = false;
        int i = 0;
        int z = 0;
        int y = 0;
        int a = 0;
        String item_code = "";
        try {

            con = new DBCon().getConnection();
            con.setAutoCommit(false);

            String sql_1 = "select count(*)+1 as item_code from phr_items";
            ps = con.prepareStatement(sql_1);
            rs = ps.executeQuery();
            if (rs.next()) {
                item_code = "PHR_ITEM/" + rs.getString("item_code");
            }

            String Sql = "insert into phr_items(item_code,item_name,created_on,created_by,vendor,item_type,sts,min_stock,"
                    + "bathc_no,hsn_code,manu_date,Exp_date)Values(?,?,GETDATE(),?,?,?,?,?,?,?,?,?)";
            ps = con.prepareStatement(Sql);
            ps.setString(1, item_code);
            ps.setString(2, Item_name);
            ps.setString(3, user);
            ps.setString(4, vendor);
            ps.setString(5, item_type);
            ps.setString(6, "Active");
            ps.setString(7, Min_stock);
            ps.setString(8, batch_no);
            ps.setString(9, hsn_code);
            ps.setString(10, Manfu_date);
            ps.setString(11, Exp_date);
            i = ps.executeUpdate();
            if (i > 0) {
                String Sql_in = "insert into phr_item_stock_in(item_code,qty_in,qty_sts,created_by,created_on)Values(?,?,?,?,GETDATE())";
                ps = con.prepareStatement(Sql_in);
                ps.setString(1, item_code);
                ps.setString(2, opeaning_stock);
                ps.setString(3, "IN");
                ps.setString(4, created_by);
                z = ps.executeUpdate();
            }

            if (z >= 0) {
                String Sql_out = "insert into phr_item_stock_out(item_code,qty_in,qty_out,qty_sts,created_by)Values(?,?,?,?,?)";
                ps = con.prepareStatement(Sql_out);
                ps.setString(1, item_code);
                ps.setString(2, opeaning_stock);
                ps.setString(3, "0");
                ps.setString(4, "IN");
                ps.setString(5, created_by);
                y = ps.executeUpdate();

            }

            if (z >= 0) {
                String Sql_out = "insert into phr_item_rates(item_code,item_pur_rate,item_sale_rate)Values(?,?,?)";
                ps = con.prepareStatement(Sql_out);
                ps.setString(1, item_code);
                ps.setString(2, purchase_price);
                ps.setString(3, sale_price);

                a = ps.executeUpdate();

            }

            if (a >= 0) {
                con.commit();
                sts = true;

            }

        } catch (Exception e) {
            Logger.getLogger(WPHR0001_Dao.class.getName()).log(Level.SEVERE, null, e);
        }

        return sts;
    }

//    for pharmacy stock 
    ArrayList<HashMap<String, String>> getstockData(String[] arr) throws SQLDataException {
        ArrayList<HashMap<String, String>> al = new ArrayList<>();

        try {
            con = new DBCon().getConnection();

            String Sql = "select item_code,item_name,qty_in,qty_out,\n"
                    + "isnull((isnull(qty_in,0)-isnull(qty_out,0)),0)as current_qty,\n"
                    + "qty_sts,item_pur_unit,item_sal_unit from\n"
                    + " (select isnull(i.item_code,'')as item_code,isnull(i.qty_in,0)as qty_in,sum(cast(isnull(o.qty_out,0)as int))as qty_out,isnull(o.qty_sts,'')as qty_sts,\n"
                    + " isnull(item_name,'')as item_name,isnull(item_pur_unit,'')as item_pur_unit,ISNULL(item_sal_unit,'')as item_sal_unit\n"
                    + " from phr_item_stock_in i\n"
                    + " left join phr_item_stock_out o on o.item_code=i.item_code and o.qty_sts='out' left join phr_item pi on pi.item_code=i.item_code\n"
                    + " group by i.item_code,i.qty_in,o.qty_sts,pi.item_name,pi.item_pur_unit,pi.item_sal_unit) as tab where 1=1  ";

            if (arr[0] != null && arr[0] != "") {
                Sql += " and item_name='" + arr[0] + "'";

            }

            System.out.println("---->" + Sql);

            ps = con.prepareStatement(Sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                HashMap<String, String> Map = new HashMap();

                Map.put("item_code", rs.getString("item_code"));
                Map.put("item_name", rs.getString("item_name"));
                Map.put("qty_in", rs.getString("qty_in"));
                Map.put("qty_out", rs.getString("qty_out"));
                Map.put("current_qty", rs.getString("current_qty"));
                Map.put("item_pur_unit", rs.getString("item_pur_unit"));
                Map.put("item_sal_unit", rs.getString("item_sal_unit"));

                al.add(Map);
            }

        } catch (Exception e) {
            Logger.getLogger(WPHR0001_Dao.class.getName()).log(Level.SEVERE, null, e);
        }

        return al;

    }

//    for pharmacy Rates
    ArrayList<HashMap<String, String>> get_item_rate(String[] arr) throws SQLDataException {
        ArrayList<HashMap<String, String>> al = new ArrayList<>();

        try {
            con = new DBCon().getConnection();

            String Sql = "select isnull(pih.item_code,'')as item_code,isnull(item_sale_rate,0)as sale_rate,isnull(item_name,'')as item_name from phr_item_rates pih \n"
                    + "left join phr_items ph on ph.item_code=pih.item_code";

            if (arr[0] != null && arr[0] != "") {
                Sql += " and item_name='" + arr[0] + "'";

            }

            System.out.println("---->" + Sql);

            ps = con.prepareStatement(Sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                HashMap<String, String> Map = new HashMap();

                Map.put("item_code", rs.getString("item_code"));
                Map.put("item_name", rs.getString("item_name"));
                Map.put("sale_rate", rs.getString("sale_rate"));

                al.add(Map);
            }

        } catch (Exception e) {
            Logger.getLogger(WPHR0001_Dao.class.getName()).log(Level.SEVERE, null, e);
        }

        return al;

    }

//    for pharmacy_billing 
    public boolean save_daily_bill(String[] prd_cd, String[] qty,
            String[] total_rate, String bill_amt, String extra_ch, String final_bill_amt,
            String mobile_no, String bill_name, String bill_address, String[] item_type) throws Exception {
        boolean sts = false;
        try {
            con = new DBCon().getConnection();
            con.setAutoCommit(false);
            String code = "";

            String sql_1 = "select Count(rwid)+1 as t from final_bill_total";
            ps = con.prepareStatement(sql_1);
            rs = ps.executeQuery();
            if (rs.next()) {
                code = rs.getString("t");
            }

            String Bill_code = "BILL/" + code + "";

            String sql = "insert into pharmacy_bills(item_code,qty,unit,bill_id)\n"
                    + "values(?,?,?,?)";
            ps = con.prepareStatement(sql);
            //System.out.println("prd_cdprd_cdprd_cdprd_cdprd_cd "+prd_cd.length);

            int y = 0;
            for (String cd : prd_cd) {

                ps.setString(1, cd);

                ps.setString(2, qty[y]);
                ps.setString(3, item_type[y]);
                ps.setString(4, Bill_code);

                ps.addBatch();
                y++;
//                boolean sts2=updateBYBillingStockMaster(cd,""+qty[y],"Biiling_Stock");

//                if(!sts2){
//                    sts=false;
//                    throw new Exception("Stack Master ERROR IN Billing Site!!!");
//                }
            }
            boolean sts3 = final_bill(Bill_code, bill_amt, extra_ch, final_bill_amt, mobile_no, bill_name, bill_address);
            if (!sts3) {
                sts = false;
                throw new Exception("ERROR IN FINAL AMOUNT SAVING!!!");
            }

            int length = ps.executeBatch().length;

            if (length >= 0) {

                sts = true;
                // stsString=cd;
                con.commit();
            }

        } catch (IOException | SQLException e) {
            Logger.getLogger(WPHR0001_Dao.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("Error-> " + e.getMessage());
            return sts;
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(WPHR0001_Dao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("stsstsstssts return --> " + sts);
        return sts;
    }

//    private boolean updateBYBillingStockMaster(String prdcd, String prdQT, String Billing_Stock) throws SQLException {
//        
//                String sql="update stock_master set avl_stock=avl_stock-"+prdQT+",last_action='"+Billing_Stock+"'"
//                        + " where prd_cd='"+prdcd+"' and cat_cd='"+cat_cd+"'";
//                PreparedStatement  ps1=con.prepareStatement(sql);
//          
//                System.out.println("---->"+sql);
//           
//            return ps1.executeUpdate()>0;
//       
//    }
//        
    private boolean final_bill(String Bill_code, String bill_amt, String extra_ch, String final_bill_amt, String mobile_no, String bill_name, String bill_address) throws SQLException {

        String sql = "insert into final_bill_total(bill_code,tot_bill_amt,extra,final_amt,mobile_no,bill_to,address)"
                + "Values('" + Bill_code + "','" + bill_amt + "','" + extra_ch + "','" + final_bill_amt + "','" + mobile_no + "','" + bill_name + "','" + bill_address + "')";
        PreparedStatement ps1 = con.prepareStatement(sql);

        System.out.println("---->" + sql);

        return ps1.executeUpdate() > 0;

    }

    ArrayList<HashMap<String, String>> get_bill(String[] arr) throws SQLDataException {
        ArrayList<HashMap<String, String>> al = new ArrayList<>();

        try {
            con = new DBCon().getConnection();

            String Sql = "select isnull(bill_to,'')as bill_to,\n"
                    + "isnull(bill_code,'')as bill_code,isnull(final_amt,'')as final_amt,isnull(format(created_date,'dd/MM/yyyyy'),'')as created_date from final_bill_total";

            if (arr[0] != null && arr[0] != "") {
                Sql += " and item_name='" + arr[0] + "'";

            }

            System.out.println("---->" + Sql);

            ps = con.prepareStatement(Sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                HashMap<String, String> Map = new HashMap();

                Map.put("bill_to", rs.getString("bill_to"));
                Map.put("bill_code", rs.getString("bill_code"));
                Map.put("final_amt", rs.getString("final_amt"));
                Map.put("created_date", rs.getString("created_date"));

                al.add(Map);
            }

        } catch (Exception e) {
            Logger.getLogger(WPHR0001_Dao.class.getName()).log(Level.SEVERE, null, e);
        }

        return al;

    }

}

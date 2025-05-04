/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Master;

import Dao.DBCon;
import Reports.WREP0001_Dao;
import java.io.IOException;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
//import jdk.internal.foreign.Utils;

/**
 *
 * @author Tarun
 */
public class WMAS0001_Dao {

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

                sql = "select user_id,Real_name from login_info";

            }
            if (cd.equals("2")) {
                sql = "select mdid,modulenm from modulenfo";
            }
            if (cd.equals("3")) {
                sql = "select a.mdid,b.modulenm from assign_module a inner join modulenfo b on a.mdid=b.mdid where userid='" + usr + "'";
                System.out.println("---->onchange" + sql);
            }

            if (cd.equals("4")) {
                sql = "select isnull(category_id,'')as category_id,isnull(category_name,'')as category_name from charge_category";
                System.out.println("---->onchange" + sql);
            }

            if (cd.equals("5")) {
                sql = "select isnull(department_code,'') as dep_code,isnull(department_name,'')as dep_name from hosiptal_department";
                System.out.println("---->onchange" + sql);
            }

            if (cd.equals("6")) {
                sql = "select staff_id,name from hospital_staff";
                System.out.println("---->onchange" + sql);
            }

            if (cd.equals("7")) {
                sql = "SELECT isnull(category_id,'')as category_id,isnull(category_name,'')as category_name FROM charge_category c WHERE c.charge_type='Room' and NOT EXISTS (\n" +
"    SELECT 1\n" +
"    FROM mst_rooms m\n" +
"    WHERE m.room_category = c.category_id \n" +
");";
                System.out.println("---->onchange" + sql);
            }

            if (cd.equals("8")) {
                sql = "select isnull(role_id,'')as role_id,isnull(Role_name,'')as role_name from role_master";
                System.out.println("---->onchange" + sql);
            }
            
              if (cd.equals("9")) {
                sql = "select isnull(category_code,'')as category_code,isnull(category_name,'')\n" +
"as category_name from other_charge_category";
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

    //     for geeting module
    ArrayList<HashMap<String, String>> getmodule() throws SQLException, IOException {
        ArrayList<HashMap<String, String>> al = new ArrayList<>();
        try {
            con = new DBCon().getConnection();

            String Sql = "select mdid,modulenm from modulenfo";
            ps = con.prepareStatement(Sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                HashMap<String, String> Map = new HashMap();
                Map.put("mdid", rs.getString("mdid"));
                Map.put("modulenm", rs.getString("modulenm"));
                al.add(Map);
            }

        } catch (Exception ex) {
            Logger.getLogger(WMAS0001_Dao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return al;

    }

//    for saving the module
    public boolean save(String mem, String[] module) {
        boolean sts = false;
        int i = 0;

        try {
            con = new DBCon().getConnection();
            con.setAutoCommit(false);
            String sql = "delete from assign_module where userid='" + mem + "'";
            System.out.println("---->delete" + sql);
            ps = con.prepareStatement(sql);
            int y = ps.executeUpdate();
            if (y >= 0) {
                for (String mod : module) {
                    String Sql = "insert into assign_module(userid,mdid)values(?,?)";
                    ps = con.prepareStatement(Sql);
                    ps.setString(1, mem);
                    ps.setString(2, mod);
                    i = ps.executeUpdate();
                }
            }
            if (i >= 0) {
                sts = true;
                con.commit();
            }
        } catch (Exception ex) {
            Logger.getLogger(WMAS0001_Dao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return sts;
    }

    public boolean save_module(String modulenm, String Icon) {
        boolean sts = false;
        int i = 0;
        String mdid = "";

        try {
            con = new DBCon().getConnection();
            con.setAutoCommit(false);

            String sql_1 = "select count(*)+1 as mdid from modulenfo";
            ps = con.prepareStatement(sql_1);
            rs = ps.executeQuery();
            if (rs.next()) {
                mdid = rs.getString("mdid");
            }

            String sql = "insert into modulenfo(mdid,modulenm,icon)Values(?,?,?)";

            ps = con.prepareStatement(sql);
            ps.setString(1, mdid);
            ps.setString(2, modulenm);
            ps.setString(3, Icon);
            int y = ps.executeUpdate();

            if (i >= 0) {
                sts = true;
                con.commit();
            }
        } catch (Exception ex) {
            Logger.getLogger(WMAS0001_Dao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return sts;
    }

    public boolean save_sbmodule(String submdid, String Mdid, String sub_module_name, String url) {
        boolean sts = false;
        int i = 0;
        String mdid = "";

        try {
            con = new DBCon().getConnection();
            con.setAutoCommit(false);

            String sql_1 = "select count(*)+1 as submdid from submodulenfo";
            ps = con.prepareStatement(sql_1);
            rs = ps.executeQuery();
            if (rs.next()) {
                mdid = rs.getString("submdid");
            }

            String sql = "insert into submodulenfo(submid,mdid,submodnm,suburl)Values(?,?,?,?)";

            ps = con.prepareStatement(sql);
            ps.setString(1, mdid);
            ps.setString(2, Mdid);
            ps.setString(3, sub_module_name);
            ps.setString(4, url);
            int y = ps.executeUpdate();

            if (i >= 0) {
                sts = true;
                con.commit();
            }
        } catch (Exception ex) {
            Logger.getLogger(WMAS0001_Dao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return sts;
    }

//    for all menu 
    public ArrayList<HashMap<String, String>> getAllMainMenu(String usr_1) throws SQLException, IOException {
        ArrayList<HashMap<String, String>> al = new ArrayList<>();
        try {
            con = new DBCon().getConnection();

            String Sql = "select am.mdid as mdid,m.modulenm  as modulenm,icon \n"
                    + "from assign_module am\n"
                    + "inner join modulenfo m on m.mdid=am.mdid\n"
                    + "where am.userid='" + usr_1 + "'";

            ps = con.prepareStatement(Sql);
//          System.out.println("----->"+Sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                HashMap<String, String> Map = new HashMap();
                Map.put("mdid", rs.getString("mdid"));
                Map.put("modulnm", rs.getString("modulenm"));
                Map.put("icon", rs.getString("icon"));

                al.add(Map);
            }

        } catch (Exception ex) {
            Logger.getLogger(WMAS0001_Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return al;

    }

    // for assignined sub module
    public ArrayList<HashMap<String, String>> getAllSubMenuFOrMdID(String usr_1, String mdid) throws SQLException, IOException {
        ArrayList<HashMap<String, String>> al = new ArrayList<>();
        try {
            con = new DBCon().getConnection();

            String Sql = "select sm.submid,sm.submodnm as submodnm,sm.suburl as suburl from assign_sub_module asm\n"
                    + "inner join submodulenfo sm on sm.submid=asm.submid and sm.mdid=asm.mdid\n"
                    + "where asm.userid='" + usr_1 + "' and asm.mdid='" + mdid + "'";

            ps = con.prepareStatement(Sql);
//          System.out.println("----->"+Sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                HashMap<String, String> Map = new HashMap();
                Map.put("suburl", rs.getString("suburl").trim());
                Map.put("submodnm", rs.getString("submodnm").trim());

                al.add(Map);
            }

        } catch (Exception ex) {
            Logger.getLogger(WMAS0001_Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return al;

    }

    ArrayList<HashMap<String, String>> getsubmodule(String mdid, String usr_1) throws SQLException, IOException {
        ArrayList<HashMap<String, String>> al = new ArrayList<>();
        try {
            con = new DBCon().getConnection();

            String Sql = "select submid,mdid,submodnm,\n"
                    + "isnull((case when (select asm.submid from \n"
                    + "			assign_sub_module asm where asm.submid=sm.submid and asm.userid='" + usr_1 + "'\n"
                    + "            )=sm.submid\n"
                    + "				then 'checked' else '' end ),'')as checked_or_not\n"
                    + " from submodulenfo sm \n"
                    + "where mdid='" + mdid + "'";

            ps = con.prepareStatement(Sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                HashMap<String, String> Map = new HashMap();
                Map.put("submid", rs.getString("submid"));
                Map.put("mdid", rs.getString("mdid"));
                Map.put("submodnm", rs.getString("submodnm"));
                Map.put("checked_or_not", rs.getString("checked_or_not"));
                al.add(Map);
            }

        } catch (Exception ex) {
            Logger.getLogger(WMAS0001_Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return al;

    }

    //    for saving submodule
    public boolean savesubmod(String mem, String[] module, String[] submodule) throws SQLException {
        boolean sts = false;
        int i = 0;
        int z = 0;
        try {
            con = new DBCon().getConnection();
            con.setAutoCommit(false);

            String sql = "delete from assign_sub_module where userid='" + mem + "' and mdid='" + module[i] + "'";
            System.out.println("---->delete" + sql);
            ps = con.prepareStatement(sql);
            int y = ps.executeUpdate();
            if (y >= 0) {
                for (String mod : module) {

                    String Sql = "insert into assign_sub_module(userid,mdid,submid)values(?,?,?)";
                    ps = con.prepareStatement(Sql);
                    ps.setString(1, mem);
                    ps.setString(2, mod);
                    ps.setString(3, submodule[z]);
                    i = ps.executeUpdate();
                    z++;
                    System.out.println("--->+" + z);
                }
                if (i >= 0) {

                    sts = true;
                    con.commit();
                }

            }
        } catch (Exception ex) {
            Logger.getLogger(WMAS0001_Dao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return sts;
    }

    public boolean save_Room(String user, String hospital_code, String Room_name, String Number_of_bed, String Floor, String room_category) throws SQLException {
        boolean sts = false;
        int i = 0;
        int z = 0;
        String code = "";
        try {
            con = new DBCon().getConnection();
            con.setAutoCommit(false);

            String sql = "select Count(*)+1 as code from mst_rooms";

            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                code = rs.getString("code");
            }
            
            String sql_room_number_check = "select * from mst_rooms where Room_number_name='"+Room_name+"'";
            
            System.out.println("------>room"+sql_room_number_check);
            ps = con.prepareStatement(sql_room_number_check);
            rs = ps.executeQuery();
            if (rs.next()) {
               sts=false;
            }
            
            else{
      
            String Sql = "insert into mst_rooms(Room_id,Room_number_name,No_of_beds,floor_no,hospital_code,created_by,room_category)"
                    + "Values(?,?,?,?,?,?,?)";
            ps = con.prepareStatement(Sql);
            ps.setString(1, code);
            ps.setString(2, Room_name);
            ps.setString(3, Number_of_bed);
            ps.setString(4, Floor);
            ps.setString(5, hospital_code);
            ps.setString(6, user);
            ps.setString(7, room_category);
            i = ps.executeUpdate();
            z++;
            System.out.println("--->+" + z);

            if (i >= 0) {

                saveBeds(code, Number_of_bed);

                con.commit();
                sts = true;
            } else {
                sts = false;

            }
            
            }

        } catch (Exception ex) {
            Logger.getLogger(WMAS0001_Dao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return sts;
    }

    private void saveBeds(String code, String Number_of_bed) throws SQLException {
        int z = 0;
        int No_of_beds = Integer.parseInt(Number_of_bed);

        for (int i = 1; i <= No_of_beds; i++) {

            String bed_code = code + "-B" + i;

            String Sql = "insert into room_beds(room_id,bed_number,room_bed_id)"
                    + "Values(?,?,?)";
            ps = con.prepareStatement(Sql);
            ps.setString(1, code);
            ps.setInt(2, i);
            ps.setString(3, bed_code);

            z = ps.executeUpdate();

            System.out.println("====>yanha tak sab theek h ");

        }

    }

    public ArrayList<HashMap<String, String>> getroom(String Hospital_code) throws SQLException, IOException {
        ArrayList<HashMap<String, String>> al = new ArrayList<>();
        try {
            con = new DBCon().getConnection();

            String Sql = "select isnull(Room_id,'Not Set')as room_id,isnull(Room_number_name,'Not Set')as name,isnull(No_of_beds,'')as no_of_beds,\n"
                    + "isnull(floor_no,'Not Set')as floor_no from mst_rooms where hospital_code='" + Hospital_code + "'";

            ps = con.prepareStatement(Sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                HashMap<String, String> Map = new HashMap();
                Map.put("room_id", rs.getString("room_id"));
                Map.put("name", rs.getString("name"));
                Map.put("no_of_beds", rs.getString("no_of_beds"));
                Map.put("floor_no", rs.getString("floor_no"));
                al.add(Map);
            }

        } catch (Exception ex) {
            Logger.getLogger(WMAS0001_Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return al;

    }

    String save_update_doc(String user, String hospital_code, String Specialist, String f_name, String l_name,
            String charge, String depertment, String degree, String mobile_number, String alt_number, String emial, String city, String address, String doc_id) {
        String sts = "";
        try {
            con = new DBCon().getConnection();
            String Sql = "select * from mst_doctor where doc_code='" + doc_id + "' and hospital_code='" + hospital_code + "'";

            ps = con.prepareStatement(Sql);

            rs = ps.executeQuery();

            if (rs.next()) {
                sts = update_doc(user, hospital_code, Specialist, f_name, l_name,
                        charge, depertment, degree, mobile_number, alt_number, emial, city, address, doc_id);
            } else {
                sts = save_doctor(user, hospital_code, Specialist, f_name, l_name,
                        charge, depertment, degree, mobile_number, alt_number, emial, city, address, doc_id);
                System.out.println("+++" + sts);
            }

        } catch (Exception ex) {
            Logger.getLogger(WMAS0001_Dao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return sts;
    }

    String save_update_staff(String user, String hospital_code, String dep, String number, String position,
            String salary, String Adhar, String date_of_joining, String staff_id, String name, String gender, String age) {
        String sts = "";
        try {
            con = new DBCon().getConnection();
            String Sql = "select * from hospital_staff where staff_id='" + staff_id + "' and hospital_code='" + hospital_code + "'";

            ps = con.prepareStatement(Sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                sts = update_staff(user, hospital_code, dep, number, position,
                        salary, Adhar, date_of_joining, staff_id, name, gender, age);
            } else {
                sts = save_staff(user, hospital_code, dep, number, position, salary, Adhar, date_of_joining, staff_id, name, gender, age);
                System.out.println("+++" + sts);
            }

        } catch (Exception ex) {
            Logger.getLogger(WMAS0001_Dao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return sts;
    }

    public String save_doctor(String user, String hospital_code, String Specialist, String f_name, String l_name,
            String charge, String depertment, String degree, String mobile_number, String alt_number, String emial, String city, String address, String doc_id) throws SQLException {
        String sts = "";
        int i = 0;
        int z = 0;
        String code = "";
        try {
            con = new DBCon().getConnection();
            con.setAutoCommit(false);

            String sql = "select Count(*)+1 as code from mst_doctor";

            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                code = "DOC/" + hospital_code + "/" + rs.getString("code");
            }

            String Sql = "insert into mst_doctor(Doc_code,doc_name_first,doc_name_last,specialist,const_cahrge,department_name,Degree,mobile_number,"
                    + "Altranate_number,"
                    + "email,city,address,hospital_code,created_by)"
                    + "Values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            ps = con.prepareStatement(Sql);
            ps.setString(1, code);
            ps.setString(2, f_name);
            ps.setString(3, l_name);
            ps.setString(4, Specialist);
            ps.setString(5, charge);
            ps.setString(6, depertment);
            ps.setString(7, degree);
            ps.setString(8, mobile_number);
            ps.setString(9, alt_number);
            ps.setString(10, emial);
            ps.setString(11, city);
            ps.setString(12, address);
            ps.setString(13, hospital_code);
            ps.setString(14, user);

            i = ps.executeUpdate();
            z++;
            System.out.println("--->+" + z);

            if (i >= 0) {

                con.commit();
                sts = code;
            }

        } catch (Exception ex) {
            Logger.getLogger(WMAS0001_Dao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return sts;
    }

    public String update_doc(String user, String hospital_code, String Specialist, String f_name, String l_name,
            String charge, String depertment, String degree, String mobile_number, String alt_number, String emial, String city, String address, String doc_id) throws SQLException, IOException {

        String doc_code = "";

        con = new DBCon().getConnection();
        con.setAutoCommit(false);

        String Sql = "update mst_doctor set doc_name_first='" + f_name + "',doc_name_last='" + l_name + "',specialist='" + Specialist + "',"
                + "const_cahrge='" + charge + "',department_name='" + depertment + "',Degree='" + degree + "',"
                + "mobile_number='" + mobile_number + "',Altranate_number='" + alt_number + "',email='" + emial + "',city='" + city + "',"
                + "address='" + address + "' where Doc_code='" + doc_id + "'";

        ps = con.prepareStatement(Sql);

        System.out.println("+++++++++++checkkkkkkk" + Sql);

        System.out.println("=====>update me Aya ");
        int i = ps.executeUpdate();

        if (i >= 0) {
            con.commit();
            doc_code = doc_id;
        }

        return doc_code;
    }

//    for saving staff
    public String save_staff(String user, String hospital_code, String dep, String number, String position,
            String salary, String Adhar, String date_of_joining, String staff_id, String name, String gender, String Age) throws SQLException {
        String sts = "";
        int i = 0;
        int z = 0;
        String code = "";
        try {
            con = new DBCon().getConnection();
            con.setAutoCommit(false);

            String sql = "select Count(*)+1 as code from hospital_staff";

            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                code = "Staff/" + hospital_code + "/" + rs.getString("code");
            }

            String Sql = "insert into hospital_staff(Staff_id,Name,mobile_number,Depertament,postion,salary,adhar_card,gender,age,"
                    + "date_of_joining,hospital_code)"
                    + "Values(?,?,?,?,?,?,?,?,?,?,?)";
            ps = con.prepareStatement(Sql);
            ps.setString(1, code);
            ps.setString(2, name);
            ps.setString(3, number);
            ps.setString(4, dep);
            ps.setString(5, position);
            ps.setString(6, salary);
            ps.setString(7, Adhar);
            ps.setString(8, gender);
            ps.setString(9, Age);
            ps.setString(10, date_of_joining);
            ps.setString(11, hospital_code);
            

            i = ps.executeUpdate();
            z++;
            System.out.println("--->+" + z);

            if (i >= 0) {

                con.commit();
                sts = code;
            }

        } catch (Exception ex) {
            Logger.getLogger(WMAS0001_Dao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return sts;
    }

//   for updateting staff
    public String update_staff(String user, String hospital_code, String dep, String number, String position,
            String salary, String Adhar, String date_of_joining, String staff_id, String name, String gender, String age) throws SQLException, IOException {

        String doc_code = "";

        con = new DBCon().getConnection();
        con.setAutoCommit(false);

        String Sql = "update hospital_staff set name='" + name + "',mobile_number='" + number + "',Depertament='" + dep + "',"
                + "postion='" + position + "',salary='" + salary + "',adhar_card='" + Adhar + "',"
                + "date_of_joining='" + date_of_joining + "',gender='" + gender + "',age='" + age + "' where staff_id='" + staff_id + "'";

        ps = con.prepareStatement(Sql);

        System.out.println("+++++++++++checkkkkkkk" + Sql);

        System.out.println("=====>update me Aya ");
        int i = ps.executeUpdate();

        if (i >= 0) {
            con.commit();
            doc_code = staff_id;
        }

        return doc_code;
    }

//    for saving category
    public boolean save_charge_category(String category_name, String hospital_code, String user,String charge_type) {
        boolean sts = false;
        int i = 0;
        String code = "";

        try {
            con = new DBCon().getConnection();
            con.setAutoCommit(false);

            String sql_1 = "select count(*)+1 as code from charge_category";
            ps = con.prepareStatement(sql_1);
            rs = ps.executeQuery();
            if (rs.next()) {
                code = "Charge/" + rs.getString("code");
            }

            String sql = "insert into charge_category(category_id,category_name,created_by,hospital_code,charge_type)Values(?,?,?,?,?)";

            ps = con.prepareStatement(sql);
            ps.setString(1, code);
            ps.setString(2, category_name);
            ps.setString(3, user);
            ps.setString(4, hospital_code);
            ps.setString(5, charge_type);
            int y = ps.executeUpdate();

            if (i >= 0) {
                sts = true;
                con.commit();
            }
        } catch (Exception ex) {
            Logger.getLogger(WMAS0001_Dao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return sts;
    }

//    FOR SAVING CATEGORY
    public boolean save_charge_AMOUNT(String[] charge_name, String[] Amount, String hospital_code, String user, String category_id,String []MIn_Amount) {
        boolean sts = false;
        int i = 0;
        int z = 0;
        Array code[] = null;

        
        String code_1="";
        int new_cahrge=0;
        try {
            con = new DBCon().getConnection();
            con.setAutoCommit(false);
    
 
      int charge_id=0;
            
            String sql_1="select count(*)+1 as code from mst_hos_charges";
               ps=con.prepareStatement(sql_1);
            rs=ps.executeQuery();
            if(rs.next()){    
                
                
               charge_id=rs.getInt("code");
                
            }
            
            System.out.println("--------------------"+charge_id);
            
            String sql = "insert into mst_hos_charges(charge_id,category_id,charge_name,charge_amount,hospital_code,Minmum_charge)Values(?,?,?,?,?,?)";

            ps = con.prepareStatement(sql);
            for (String cd : charge_name) {
                
      
            
                    
                ps.setString(1,"HOS_CHAR_"+charge_id); 
                ps.setString(2, category_id);
                ps.setString(3, cd);
                ps.setString(4, Amount[z]);
                ps.setString(5, hospital_code);
                ps.setString(6, MIn_Amount[z]);
                z++;
                  charge_id++;
                
                int y = ps.executeUpdate();
                
                  
            
            }

            if (i >= 0) {
                sts = true;
                con.commit();
            }
        } catch (Exception ex) {
            Logger.getLogger(WMAS0001_Dao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return sts;
    }

    ArrayList<HashMap<String, String>> get_all_charges(String category) throws SQLException, IOException {
        ArrayList<HashMap<String, String>> al = new ArrayList<>();
        try {
            con = new DBCon().getConnection();

            String Sql = "select\n"
                    + "isnull(charge_name,'')as charge_name,isnull(charge_amount,'')as charge_amount,"
                    + "isnull(Minmum_charge,0)as min_charge,(select sum(charge_amount)"
                    + " from mst_hos_charges where category_id='" + category + "' )as total\n"
                    + "from mst_hos_charges where category_id='" + category + "' ";

            ps = con.prepareStatement(Sql);
            System.out.println("-----?"+Sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                HashMap<String, String> Map = new HashMap();
                Map.put("charge_name", rs.getString("charge_name"));
                Map.put("charge_amount", rs.getString("charge_amount"));
                Map.put("total", rs.getString("total"));
                Map.put("min_charge", rs.getString("min_charge"));

                al.add(Map);
            }
         

        } catch (Exception ex) {
            Logger.getLogger(WMAS0001_Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return al;

    }

//    for updation hoispital
    public String save_update_hos(String Hospital_name, String Hospital_reg,
            String Hospital_gst, String Hospital_address, String number_1, String number_2, String Hospital_code) {
        String sts = "";

        try {

            if (Hospital_code.equals("")) {
                sts = save_update_hospital(Hospital_name, Hospital_reg, Hospital_gst, Hospital_address, number_1, number_2);
            } else {
                sts = update_hosp(Hospital_name, Hospital_reg, Hospital_gst, Hospital_address, number_1, number_2, Hospital_code);
            }

        } catch (Exception ex) {
            Logger.getLogger(WMAS0001_Dao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return sts;
    }

    public String save_update_hospital(String Hospital_name, String Hospital_reg, String Hospital_gst, String Hospital_address, String number_1, String number_2) {
        String sts = "";
        int i = 0;
        int z = 0;
        String code = "";

        try {
            con = new DBCon().getConnection();
            con.setAutoCommit(false);

            String Sql_code = "select Count(*)+1 code from mst_hospital";
            ps = con.prepareStatement(Sql_code);
            rs = ps.executeQuery();
            if (rs.next()) {
                code = "HOS/" + rs.getString("code");
            }

            String sql = "update mst_hospital set hospital_name='" + Hospital_name + "',hospital_registration='" + Hospital_reg + "',"
                    + "hospital_gst='" + Hospital_gst + "',address='" + Hospital_address + "',number_1='" + number_1 + "',number_2='" + number_2 + "'"
                    + "where hospital_code='HOS/1'";

            ps = con.prepareStatement(sql);

            int y = ps.executeUpdate();

            if (i >= 0) {
                sts = code;
                con.commit();
            }
        } catch (Exception ex) {
            Logger.getLogger(WMAS0001_Dao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return sts;
    }

//     for creating user 
    String save_updateUser(String user, String pass, String realname, String Role, String User_id) throws SQLException, Exception {
        String sts = "";

        System.out.println("usetshsag" + User_id);

        if (User_id.equals("")) {
            
            sts = save_user(user, pass, realname, Role);

        } else {
             sts = update_user(user, pass, realname, Role, User_id);
        }

        return sts;
    }

    String save_user(String user, String pass, String realname, String Role) throws IOException, SQLException, Exception {
        String sts = "";

        System.out.println("========LOGIN me AYA =====");
        
        
        con = new DBCon().getConnection();
        con.setAutoCommit(false);

        int i = 0;
        int z = 0;
        String user_count = "";

        String Sql_2 = "Select count(*)+1 as c from login_info";
        ps = con.prepareStatement(Sql_2);
        rs = ps.executeQuery();
        if (rs.next()) {
            user_count = rs.getString("c");
        }

        System.out.println("=====>jkjhvh" + user_count);

        String sql = "insert into login_info(user_id,username,password,Real_Name,role_id,hospital_code,sts)"
                + "Values('User_" + user_count + "','" + user + "','" + pass + "','" + realname + "','" + Role + "','HOS/1','1')";
        ps = con.prepareStatement(sql);

        i = ps.executeUpdate();

        if (i > 0) {

            con.commit();

            sts = "USER_" + user_count;
        }

        return sts;
    }

    String update_user(String user, String pass, String realname, String Role, String User_id) throws IOException, SQLException, Exception {
        String sts = "";

        
          System.out.println("========UPDATE me AYA =====");
        con = new DBCon().getConnection();
        con.setAutoCommit(false);

        int i = 0;
        int z = 0;

        String sql = "Update login_info set username='" + user + "',password='" + pass + "',Real_name='" + realname + "' where user_id='" + User_id + "'";
        ps = con.prepareStatement(sql);

        i = ps.executeUpdate();

        if (i > 0) {

            con.commit();

            sts = "Update Done";

        }

        return sts;
    }

    public ArrayList<HashMap<String, String>> get_all_users() throws SQLException, IOException {
        ArrayList<HashMap<String, String>> al = new ArrayList<>();
        try {
            con = new DBCon().getConnection();

            String Sql = "select isnull(user_id,'')as user_id,isnull(username,'')as username,\n"
                    + "isnull(password,'')as password,\n"
                    + "isnull(Real_name,'')as real_name,isnull(sts,'')as sts from login_info \n"
                    + "";

            ps = con.prepareStatement(Sql);
            System.out.println("====>stff" + Sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                HashMap<String, String> Map = new HashMap();

                Map.put("user_id", rs.getString("user_id"));
                Map.put("username", rs.getString("username"));
                Map.put("password", rs.getString("password"));
                Map.put("real_name", rs.getString("real_name"));
                Map.put("sts", rs.getString("sts"));

                al.add(Map);
            }

        } catch (Exception ex) {
            Logger.getLogger(WMAS0001_Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return al;

    }

// for geeting the update modee in profie
    public HashMap<String, String> get_all_users(String Id) throws SQLException, IOException {
        HashMap<String, String> al = new HashMap<>();
        try {
            con = new DBCon().getConnection();

            String Sql = "select isnull(user_id,'')as user_id,isnull(username,'')as username,\n"
                    + "isnull(password,'')as password,\n"
                    + "isnull(Real_name,'')as real_name,isnull(sts,'')as sts from login_info \n"
                    + "Where user_id='" + Id + "'";

            ps = con.prepareStatement(Sql);
            System.out.println("====>stff" + Sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                HashMap<String, String> Map = new HashMap();

                al.put("user_id", rs.getString("user_id"));
                al.put("username", rs.getString("username"));
                al.put("password", rs.getString("password"));
                al.put("real_name", rs.getString("real_name"));
                al.put("sts", rs.getString("sts"));

            }
            
            else if (Id == null) {
                al.put("user_id", "");
                al.put("username", "");
                al.put("password", "");
                al.put("real_name", "");
                al.put("sts", "");
            }

            

        } catch (Exception ex) {
            Logger.getLogger(WMAS0001_Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return al;

    }

//     for getting the update mode of hospital
    public ArrayList<HashMap<String, String>> get_all_hospitals() throws SQLException, IOException {
        ArrayList<HashMap<String, String>> al = new ArrayList<>();
        try {
            con = new DBCon().getConnection();

            String Sql = "select isnull(hospital_name,'')as hospital_name,\n"
                    + "isnull(hospital_registration,'')as reg,\n"
                    + "isnull(hospital_gst,'')as gst,\n"
                    + "isnull(address,'')as address,\n"
                    + "isnull(number_1,'')as number_1,\n"
                    + "isnull(number_2,'')as number_2,\n"
                    + "isnull(hospital_code,'')as hospital_code\n"
                    + "from mst_hospital";

            ps = con.prepareStatement(Sql);
            System.out.println("====>stff" + Sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                HashMap<String, String> Map = new HashMap();

                Map.put("hospital_name", rs.getString("hospital_name"));
                Map.put("reg", rs.getString("reg"));
                Map.put("gst", rs.getString("gst"));
                Map.put("address", rs.getString("address"));
                Map.put("number_1", rs.getString("number_1"));
                Map.put("hospital_code", rs.getString("hospital_code"));
                Map.put("number_2", rs.getString("number_2"));

                al.add(Map);
            }

        } catch (Exception ex) {
            Logger.getLogger(WMAS0001_Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return al;

    }

//hospital in update mode
    public HashMap<String, String> get_hospital_update(String Id) throws SQLException, IOException {
        HashMap<String, String> al = new HashMap<>();
        try {
            con = new DBCon().getConnection();

            String Sql = "select isnull(hospital_name,'')as hospital_name,\n"
                    + "isnull(hospital_registration,'')as reg,\n"
                    + "isnull(hospital_gst,'')as gst,\n"
                    + "isnull(address,'')as address,\n"
                    + "isnull(number_1,'')as number_1,\n"
                    + "isnull(number_2,'')as number_2,\n"
                    + "isnull(hospital_code,'')as hospital_code\n"
                    + "from mst_hospital where hospital_code='" + Id + "'";

            ps = con.prepareStatement(Sql);
            System.out.println("====>stff" + Sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                HashMap<String, String> Map = new HashMap();

                al.put("hospital_name", rs.getString("hospital_name"));
                al.put("reg", rs.getString("reg"));
                al.put("gst", rs.getString("gst"));
                al.put("address", rs.getString("address"));
                al.put("number_1", rs.getString("number_1"));
                al.put("number_2", rs.getString("number_2"));
                al.put("hospital_code", rs.getString("hospital_code"));

            }

        } catch (Exception ex) {
            Logger.getLogger(WMAS0001_Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return al;

    }

//     for updating hos
    public String update_hosp(String Hospital_name, String Hospital_reg,
            String Hospital_gst, String Hospital_address, String number_1, String number_2, String Hospital_code) throws IOException, SQLException, Exception {
        String sts = "";

        con = new DBCon().getConnection();
        con.setAutoCommit(false);

        int i = 0;
        int z = 0;

        String sql = "update mst_hospital set hospital_name='" + Hospital_name + "',hospital_registration='" + Hospital_reg + "',hospital_gst='" + Hospital_gst + "',"
                + "address='" + Hospital_address + "',number_1='" + number_1 + "',number_2='" + number_2 + "' where hospital_code='" + Hospital_code + "'";
        ps = con.prepareStatement(sql);

        i = ps.executeUpdate();

        if (i > 0) {

            con.commit();

            sts = Hospital_code;

        }

        return sts;
    }

// for gettiong doc update 
    public HashMap<String, String> get_doc(String doc_code) throws SQLException, IOException {
        HashMap<String, String> al = new HashMap<>();
        try {
            con = new DBCon().getConnection();

            String Sql = "select "
                    + "isnull(specialist,'')as specialist,isnull(Degree,'')as Degree,"
                    + "isnull(Doc_code,'')as doc_code,isnull(doc_name_first,'')as doc_name_first,isnull(doc_name_last,'')as doc_name_last,\n"
                    + "isnull(concat(doc_name_first,'',doc_name_last),'')as doc_name,isnull(specialist,'')as specialist,\n"
                    + "isnull(const_cahrge,0)as cont_charge,isnull(department_name,'')as department_name,isnull(Degree,'')as degre,"
                    + "isnull(concat(mobile_number,',',Altranate_number),'')as mobile_number,isnull(mobile_number,'')as mobile_num,isnull(Altranate_number,'')as alt_num,\n"
                    + "isnull(email,'')as email,isnull(address,'')as address,isnull(city,'')as city,\n"
                    + "isnull(sts,'')as sts\n"
                    + "\n"
                    + "from mst_doctor where Doc_code='" + doc_code + "' ";

            ps = con.prepareStatement(Sql);
            System.out.println("===>" + Sql);
            rs = ps.executeQuery();
            if (rs.next()) {

                al.put("doc_code", rs.getString("doc_code"));
                al.put("doc_name", rs.getString("doc_name"));
                al.put("specialist", rs.getString("specialist"));
                al.put("cont_charge", rs.getString("cont_charge"));
                al.put("department_name", rs.getString("department_name"));
                al.put("degre", rs.getString("degre"));
                al.put("mobile_number", rs.getString("mobile_number"));
                al.put("email", rs.getString("email"));
                al.put("address", rs.getString("address"));
                al.put("sts", rs.getString("sts"));
                al.put("doc_name_first", rs.getString("doc_name_first"));

                al.put("doc_name_last", rs.getString("doc_name_last"));
                al.put("mobile_num", rs.getString("mobile_num"));
                al.put("alt_num", rs.getString("alt_num"));
                al.put("city", rs.getString("city"));
                al.put("specialist", rs.getString("specialist"));
                al.put("Degree", rs.getString("Degree"));

            } else if (doc_code == null) {
                al.put("doc_code", "");
                al.put("doc_name", "");
                al.put("specialist", "");
                al.put("cont_charge", "");
                al.put("department_name", "");
                al.put("degre", "");
                al.put("mobile_number", "");
                al.put("email", "");
                al.put("address", "");
                al.put("sts", "");
                al.put("doc_name_first", "");
                al.put("doc_name_last", "");
                al.put("mobile_num", "");
                al.put("alt_num", "");
                al.put("city", "");
                al.put("specialist", "");
                al.put("Degree", "");

            }

        } catch (Exception ex) {
            Logger.getLogger(WMAS0001_Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return al;

    }

// for getting stafff in update
// for gettiong doc update 
    public HashMap<String, String> get_staff_update(String staff_code) throws SQLException, IOException {
        HashMap<String, String> al = new HashMap<>();
        try {
            con = new DBCon().getConnection();

            String Sql = " select isnull(staff_id,'')as staff_id,isnull(name,'')as name,\n"
                    + " isnull(mobile_number,'')as mobile_number,isnull(department_name,'')as depaertment,\n"
                    + " isnull(postion,'')as position,isnull(salary,'')as salary,isnull(adhar_card,'')as adhar_card,\n"
                    + " isnull(date_of_joining,'')as date_of_joining,isnull(sts,'')as sts,\n"
                    + " isnull(gender,'')as gender,isnull(age,'')as age\n"
                    + " from hospital_staff hf inner join hosiptal_department hd on hf.Depertament=hd.department_code\n"
                    + " where staff_id='" + staff_code + "'";

            System.out.println("======yanta" + staff_code);

            ps = con.prepareStatement(Sql);
            System.out.println("===>" + Sql);
            rs = ps.executeQuery();
            if (rs.next()) {

                al.put("staff_id", rs.getString("staff_id"));
                al.put("name", rs.getString("name"));
                al.put("mobile_number", rs.getString("mobile_number"));
                al.put("depaertment", rs.getString("depaertment"));
                al.put("salary", rs.getString("salary"));
                al.put("adhar_card", rs.getString("adhar_card"));
                al.put("date_of_joining", rs.getString("date_of_joining"));
                al.put("gender", rs.getString("gender"));

                al.put("age", rs.getString("age"));
                al.put("position", rs.getString("position"));

            } else if (staff_code == null) {
                al.put("staff_id", "");
                al.put("name", "");
                al.put("mobile_number", "");
                al.put("depaertment", "");
                al.put("salary", "");
                al.put("adhar_card", "");
                al.put("date_of_joining", "");
                al.put("gender", "");
                al.put("sts", "");
                al.put("age", "");
                al.put("position", "");

            }

        } catch (Exception ex) {
            Logger.getLogger(WMAS0001_Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return al;

    }

//    for saving pharmacy Details
    public String save_update_pharmacy(String pharmacy_name, String pharmacy_reg, String pharmacy_gst, String pharmacy_address, String number, String pharmacy_code) {
        String sts = "";

        try {

            if (pharmacy_code.equals("")) {
                sts = save_pharmacy(pharmacy_name, pharmacy_reg, pharmacy_gst, pharmacy_address, number, pharmacy_code);
            } else {
                sts = update_pharmacy(pharmacy_name, pharmacy_reg, pharmacy_gst, pharmacy_address, number, pharmacy_code);
            }

        } catch (Exception ex) {
            Logger.getLogger(WMAS0001_Dao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return sts;
    }

    public String update_pharmacy(String pharmacy_name, String pharmacy_reg, String pharmacy_gst, String pharmacy_address, String number, String pharmacy_code) {
        String sts = "";
        int i = 0;
        int z = 0;
        String code = "";

        try {
            con = new DBCon().getConnection();
            con.setAutoCommit(false);

            String Sql_code = "select Count(*)+1 code from pharmacy_registration";
            ps = con.prepareStatement(Sql_code);
            rs = ps.executeQuery();
            if (rs.next()) {
                code = "PHR/" + rs.getString("code");
            }

            String sql = "update pharmacy_registration set pharmacy_name='" + pharmacy_name + "',phr_registration='" + pharmacy_reg + "',"
                    + "phr_gst='" + pharmacy_gst + "',phr_address='" + pharmacy_address + "',phr_number='" + number + "'"
                    + "where pharmacy_code='" + pharmacy_code + "'";

            ps = con.prepareStatement(sql);

            int y = ps.executeUpdate();

            if (y >= 0) {
                sts = code;
                con.commit();
            }
        } catch (Exception ex) {
            Logger.getLogger(WMAS0001_Dao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return sts;
    }

    public String save_pharmacy(String pharmacy_name, String pharmacy_reg, String pharmacy_gst, String pharmacy_address, String number, String pharmacy_code) {
        String sts = "";
        int i = 0;
        int z = 0;
        String code = "";

        try {
            con = new DBCon().getConnection();
            con.setAutoCommit(false);

            String Sql_code = "select Count(*)+1 code from pharmacy_registration";
            ps = con.prepareStatement(Sql_code);
            rs = ps.executeQuery();
            if (rs.next()) {
                code = "PHR/" + rs.getString("code");
            }

            String sql = "insert into pharmacy_registration(pharmacy_code,pharmacy_name,phr_address,phr_number,phr_registration,phr_gst)"
                    + "Values(?,?,?,?,?,?)";

            ps = con.prepareStatement(sql);
            ps.setString(1, code);
            ps.setString(2, pharmacy_name);
            ps.setString(3, pharmacy_address);
            ps.setString(4, number);
            ps.setString(5, pharmacy_reg);
            ps.setString(6, pharmacy_gst);

            int y = ps.executeUpdate();

            if (y >= 0) {
                sts = code;
                con.commit();
            }
        } catch (Exception ex) {
            Logger.getLogger(WMAS0001_Dao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return sts;
    }

    public ArrayList<HashMap<String, String>> get_pharmacy_update() throws SQLException, IOException {
        ArrayList<HashMap<String, String>> al = new ArrayList<>();
        try {
            con = new DBCon().getConnection();

            String Sql = "select isnull(pharmacy_name,'')as pharmacy_name,isnull(pharmacy_code,'')as pharmacy_code,\n"
                    + "isnull(phr_address,'')as phr_addrees,isnull(phr_number,'')as phr_number,isnull(phr_registration,'')as phr_reg,\n"
                    + "isnull(phr_gst,'')as phr_gst\n"
                    + "from pharmacy_registration ";

            ps = con.prepareStatement(Sql);
            System.out.println("===>" + Sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                HashMap<String, String> Map = new HashMap();
                Map.put("pharmacy_name", rs.getString("pharmacy_name"));
                Map.put("pharmacy_code", rs.getString("pharmacy_code"));
                Map.put("phr_addrees", rs.getString("phr_addrees"));
                Map.put("phr_number", rs.getString("phr_number"));
                Map.put("phr_reg", rs.getString("phr_reg"));
                Map.put("phr_gst", rs.getString("phr_gst"));

                al.add(Map);

            }

        } catch (Exception ex) {
            Logger.getLogger(WMAS0001_Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return al;

    }

    public HashMap<String, String> get_update_phr(String phr_code) throws SQLException, IOException {
        HashMap<String, String> al = new HashMap<>();
        try {
            con = new DBCon().getConnection();

            String Sql = "select isnull(pharmacy_name,'')as pharmacy_name,isnull(pharmacy_code,'')as pharmacy_code,\n"
                    + "isnull(phr_address,'')as phr_addrees,isnull(phr_number,'')as phr_number,isnull(phr_registration,'')as phr_reg,\n"
                    + "isnull(phr_gst,'')as phr_gst\n"
                    + "from pharmacy_registration where pharmacy_code='" + phr_code + "'";

            ps = con.prepareStatement(Sql);
            System.out.println("===>" + Sql);
            rs = ps.executeQuery();
            if (rs.next()) {

                al.put("pharmacy_name", rs.getString("pharmacy_name"));
                al.put("phr_addrees", rs.getString("phr_addrees"));
                al.put("phr_number", rs.getString("phr_number"));
                al.put("phr_reg", rs.getString("phr_reg"));
                al.put("phr_gst", rs.getString("phr_gst"));

            } else if (phr_code == null) {
                al.put("pharmacy_name", "");
                al.put("phr_addrees", "");
                al.put("phr_number", "");
                al.put("phr_reg", "");
                al.put("phr_gst", "");

            }

        } catch (Exception ex) {
            Logger.getLogger(WMAS0001_Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return al;

    }

//////////////////////////////////
//    for opd procedure master 
    public String save_update_opd_procedure(String procedure_name, String procedure_rate, String Procedure_code) {
        String sts = "";

        try {

            System.out.println("+++> Yahana tak aayega ");

            if (Procedure_code.equals("")) {
                sts = save_procedure(procedure_name, procedure_rate);
            } else {
                sts = update_procedure(procedure_name, procedure_rate, Procedure_code);
            }

        } catch (Exception ex) {
            Logger.getLogger(WMAS0001_Dao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return sts;
    }

//    for saving procedure 
    public String save_procedure(String procedure_name, String procedure_rate) {
        String sts = "";
        int i = 0;
        int z = 0;
        String code = "";

        try {
            con = new DBCon().getConnection();
            con.setAutoCommit(false);

            String Sql_code = "select Count(*)+1 code from opd_procedure_master";
            ps = con.prepareStatement(Sql_code);
            rs = ps.executeQuery();
            if (rs.next()) {
                code = "OPD_PRO/" + rs.getString("code");
            }

            String sql = "insert into opd_procedure_master(procedure_id,procedure_name,procedure_rate)"
                    + "Values(?,?,?)";

            ps = con.prepareStatement(sql);
            ps.setString(1, code);
            ps.setString(2, procedure_name);
            ps.setString(3, procedure_rate);

            int y = ps.executeUpdate();

            if (y >= 0) {
                sts = code;
                con.commit();
            }
        } catch (Exception ex) {
            Logger.getLogger(WMAS0001_Dao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return sts;
    }

//    for updating 
    public String update_procedure(String procedure_name, String procedure_rate, String Procedure_code) {
        String sts = "";
        int i = 0;
        int z = 0;
        String code = "";

        try {
            con = new DBCon().getConnection();
            con.setAutoCommit(false);

            String sql = "update opd_procedure_master set procedure_name='" + procedure_name + "',procedure_rate='" + procedure_rate + "' where procedure_id='" + Procedure_code + "'";

            ps = con.prepareStatement(sql);

            int y = ps.executeUpdate();

            if (y >= 0) {
                sts = code;
                con.commit();
            }
        } catch (Exception ex) {
            Logger.getLogger(WMAS0001_Dao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return sts;
    }

    public String save_update_other_charges(String charge_name, String charge_rate, String charge_id) {
        String sts = "";

        try {
            

            System.out.println("+++> Yahana tak aayega "+charge_id);

            if (charge_id.equals("")) {
                System.out.println("====<add  me aya ");
                sts = Add_other_charge(charge_name, charge_rate);
            } else {
                sts = update_other_charge(charge_name, charge_rate, charge_id);
            }

        } catch (Exception ex) {
            Logger.getLogger(WMAS0001_Dao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return sts;
    }

    String Add_other_charge(String charge_name, String charge_rate) {
        String chage_id = "";

        int i = 0;
        int z = 0;
        String code = "";

        try {
            con = new DBCon().getConnection();
            con.setAutoCommit(false);

            String Sql_code = "select Count(*)+1 code from mst_other_charge";
            ps = con.prepareStatement(Sql_code);
            rs = ps.executeQuery();
            if (rs.next()) {
                code = "OTHR_CHAR/" + rs.getString("code");
            }
            System.out.println(code);

            String sql = "insert into mst_other_charge(charge_id,charge_name,charge_rate)"
                    + "Values(?,?,?)";

            ps = con.prepareStatement(sql);
            ps.setString(1, code);
            ps.setString(2, charge_name);
            ps.setString(3, charge_rate);

            int y = ps.executeUpdate();

            if (y >= 0) {
                chage_id = code;
                con.commit();
            }
        } catch (Exception ex) {
            Logger.getLogger(WMAS0001_Dao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return chage_id;

    }

//    for updation of charge rate 
    String update_other_charge(String charge_name, String charge_rate, String Charge_id) {
        String chage_id = "";

        int i = 0;
        int z = 0;
        String code = "";

        try {
            con = new DBCon().getConnection();
            con.setAutoCommit(false);

            String sql = "Update mst_other_charge set charge_name='" + charge_name + "',charge_rate='" + charge_rate + "' where charge_id='" + Charge_id + "'";
            System.out.println("====>"+sql);
            ps = con.prepareStatement(sql);

            int y = ps.executeUpdate();

            if (y >= 0) {
                chage_id = code;
                con.commit();
            }
        } catch (Exception ex) {
            Logger.getLogger(WMAS0001_Dao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return chage_id;
    }

//    for geeting detialed filled in update mode
    public ArrayList<HashMap<String, String>> get_other_charges() throws SQLException, IOException {
        ArrayList<HashMap<String, String>> al = new ArrayList<>();
        try {
            con = new DBCon().getConnection();

            String Sql = "select isnull(charge_id,'')as charge_id,\n"
                    + "isnull(charge_name,'')as charge_name,\n"
                    + "isnull(charge_rate,'')as charge_rate\n"
                    + "from mst_other_charge where 1=1";

            ps = con.prepareStatement(Sql);
            System.out.println("===>" + Sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                HashMap<String, String> Map = new HashMap();
                Map.put("charge_id", rs.getString("charge_id"));
                Map.put("charge_name", rs.getString("charge_name"));
                Map.put("charge_rate", rs.getString("charge_rate"));
                al.add(Map);
            }
        }
            catch (Exception ex) {
            Logger.getLogger(WMAS0001_Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
            return al;

        }
    
    public HashMap<String, String> get_update_mode(String charge_id) throws SQLException, IOException {
        HashMap<String, String> al = new HashMap<>();
        try {
            con = new DBCon().getConnection();

            String Sql_update = "select isnull(charge_id,'')as charge_id,\n"
                        + "isnull(charge_name,'')as charge_name,\n"
                        + "isnull(charge_rate,'')as charge_rate\n"
                        + "from mst_other_charge where charge_id='" + charge_id + "' ";

                ps = con.prepareStatement(Sql_update);

                rs = ps.executeQuery();
                if (rs.next()) {

                    al.put("charge_id", rs.getString("charge_id"));
                    al.put("charge_name", rs.getString("charge_name"));
                    al.put("charge_rate", rs.getString("charge_rate"));
                   
                } else if (charge_id == null) {
                   
                    al.put("charge_name", "");
                    al.put("charge_rate", "");
                }


        } catch (Exception ex) {
            Logger.getLogger(WMAS0001_Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return al;

    }
    
    
    
    //for procedure
    
    public ArrayList<HashMap<String, String>> get_all_opd_procedure() throws SQLException, IOException {
        ArrayList<HashMap<String, String>> al = new ArrayList<>();
        try {
            this.con = new DBCon().getConnection();
            this.ps = this.con.prepareStatement("select isnull(procedure_id,'')as procedure_id,\nisnull(procedure_name,'')as procedure_name,\nisnull(procedure_rate,'')as procedure_rate\nfrom opd_procedure_master");
            System.out.println("===>select isnull(procedure_id,'')as procedure_id,\nisnull(procedure_name,'')as procedure_name,\nisnull(procedure_rate,'')as procedure_rate\nfrom opd_procedure_master");
            this.rs = this.ps.executeQuery();
            while (this.rs.next()) {
                HashMap<String, String> Map = new HashMap<>();
                Map.put("procedure_id", this.rs.getString("procedure_id"));
                Map.put("procedure_name", this.rs.getString("procedure_name"));
                Map.put("procedure_rate", this.rs.getString("procedure_rate"));
                al.add(Map);
            }
        } catch (Exception ex) {
            Logger.getLogger(WMAS0001_Dao.class.getName()).log(Level.SEVERE, (String) null, (Throwable) ex);
        }
        return al;
    }
    
    
    //for room beds
    
   public ArrayList<HashMap<String, String>> room_beds(String room_id){
           ArrayList<HashMap<String, String>> al = new ArrayList<>();
         int no_of_beds=0;
         
         
         System.out.println("===>"+room_id);
         
         try{
             
             con = new DBCon().getConnection();
             
             String Sql="select rb.room_id,bed_number,room_bed_id,mr.No_of_beds,mr.Room_number_name,cc.category_name from room_beds rb\n" +
"inner join mst_rooms mr on mr.Room_id=rb.room_id \n" +
"inner join charge_category cc on cc.category_id=mr.room_category  where mr.Room_id='"+room_id+"'";
             
             
             System.out.println(Sql);
             ps = con.prepareStatement(Sql);
             rs = ps.executeQuery();
              while (this.rs.next()) {
                HashMap<String, String> Map = new HashMap<>();
                Map.put("room_id", this.rs.getString("room_id"));
                Map.put("bed_number", this.rs.getString("bed_number"));
                Map.put("room_bed_id", this.rs.getString("room_bed_id"));
                Map.put("No_of_beds", this.rs.getString("No_of_beds"));
                Map.put("Room_number_name", this.rs.getString("Room_number_name"));
                Map.put("category_name", this.rs.getString("category_name"));
                al.add(Map);
                 
                
                  System.out.println("--------->beddd"+al);
            }
             
             
         }catch(Exception ex){
              Logger.getLogger(WMAS0001_Dao.class.getName()).log(Level.SEVERE, (String) null, (Throwable) ex);
         }
        return al;
        
    }

    
  //for careting and updating other charge 
    public String save_update_other_charge_category(String Category_name, String User,String Category_id) {
        String sts = "";

        try {
            

            System.out.println("+++> Yahana tak aayega "+Category_id);

            if (Category_id.equals("null")) {
                System.out.println("====<add  me aya ");
                sts = Add_other_charg_category(Category_name, User);
            } else {
                sts = update_other_charge_category(Category_name, Category_id);
            }

        } catch (Exception ex) {
            Logger.getLogger(WMAS0001_Dao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return sts;
    }
    
    //for adding charge Category 
    String Add_other_charg_category (String Category_name, String user) {
        String chage_id = "";

        int i = 0;
        int z = 0;
        String code = "";

        try {
            con = new DBCon().getConnection();
            con.setAutoCommit(false);

            String Sql_code = "select Count(*)+1 code from other_charge_category";
            ps = con.prepareStatement(Sql_code);
            rs = ps.executeQuery();
            if (rs.next()) {
                code = "OTHR_CHAR_CATG/" + rs.getString("code");
            }
            System.out.println(code);

            String sql = "insert into other_charge_category(category_code,category_name,createDT,created_by)"
                    + "Values(?,?,getdate(),?)";

            ps = con.prepareStatement(sql);
            ps.setString(1, code);
            ps.setString(2, Category_name);
            ps.setString(3, user);

            int y = ps.executeUpdate();

            if (y >= 0) {
                chage_id = code;
                con.commit();
            }
        } catch (Exception ex) {
            Logger.getLogger(WMAS0001_Dao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return chage_id;

    }

    
    //for updating other Charge Category 
    String update_other_charge_category(String category_name, String Charge_id) {
        String chage_id = "";

        int i = 0;
        int z = 0;
        String code = "";

        try {
            con = new DBCon().getConnection();
            con.setAutoCommit(false);

            String sql = "Update other_charge_category set category_name='" + category_name + "' where charge_id='" + Charge_id + "'";
            System.out.println("====>"+sql);
            ps = con.prepareStatement(sql);

            int y = ps.executeUpdate();

            if (y >= 0) {
                chage_id = code;
                con.commit();
            }
        } catch (Exception ex) {
            Logger.getLogger(WMAS0001_Dao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return chage_id;
    }

    
    //in update mode
    
     public HashMap<String, String> get_update_mode_category(String Category_id) throws SQLException, IOException {
        HashMap<String, String> al = new HashMap<>();
        try {
            con = new DBCon().getConnection();

            String Sql_update = "select isnull(category_code,'')as category_code,\n" +
"isnull(category_name,'')as category_name\n" +
"from other_charge_category where category_code='"+Category_id+"';";

                ps = con.prepareStatement(Sql_update);
            System.out.println(Sql_update);
                rs = ps.executeQuery();
                if (rs.next()) {

                    al.put("category_code", rs.getString("category_code"));
                    al.put("category_name", rs.getString("category_name"));
                    
                   
                } else if (Category_id == null) {
                   
                    al.put("category_code", "");
                    al.put("category_name", "");
                }


        } catch (Exception ex) {
            Logger.getLogger(WMAS0001_Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return al;

    }
    
    
    
     //for uniure charge id
     
    String Unqiue_cahrge_id(){
      
        String charge_id="";
        try{
                    con = new DBCon().getConnection();
            con.setAutoCommit(false);
    
             String sql_1="select count(*)+1 as code from mst_hos_charges";
            ps=con.prepareStatement(sql_1);
            rs=ps.executeQuery();
                
           if(rs.next()){
               charge_id ="HOS_Charge_/'"+rs.getString("code");
            }
           
           
    }catch(Exception e){
            System.out.println(e); 
    }
     return charge_id;
     
    }
    
  //for adding new bed in a room 
    
    String add_new_bed(String room_id) {
        String chage_id = "";

        int i = 0;
        int z = 0;
        int a= 0;
        String code = "";

        try {
            con = new DBCon().getConnection();
            con.setAutoCommit(false);

            String sql = "update mst_rooms set No_of_beds=No_of_beds+1 where Room_id='"+room_id+"'";
            System.out.println("====>"+sql);
            ps = con.prepareStatement(sql);
            int y = ps.executeUpdate();

            if (y >= 0) {
                String last_bed="";
                String sql_last_bed="select top 1 bed_number+1 as bed_number from room_beds where room_id='1'   order by bed_number desc ";
                ps=con.prepareStatement(sql_last_bed);
                rs=ps.executeQuery();
                if(rs.next()){
                    last_bed=rs.getString("bed_number");
                }
            
                String Sql_insert="insert into room_beds(room_id,bed_number,room_bed_id)values("+room_id+","+last_bed+",'"+room_id+"-B"+last_bed+"')";
                  ps = con.prepareStatement(Sql_insert);
                      a = ps.executeUpdate();
                
            }
            if(a>=0){
                con.commit();
                con.close();
            }
            
            
            
            
        } catch (Exception ex) {
            Logger.getLogger(WMAS0001_Dao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return chage_id;
    }
    
    
    //for adding Bank Details 
    
      String Add_Bank_details(String Bank_name,String Branch_name,String IFSC_code,String Account_number,String user,String hos) {
        String bank_id = "";

        int i = 0;
        int z = 0;
        String code = "";

        try {
            con = new DBCon().getConnection();
            con.setAutoCommit(false);

            String Sql_code = "select Count(*)+1 code from Hos_bank_details";
            ps = con.prepareStatement(Sql_code);
            rs = ps.executeQuery();
            if (rs.next()) {
                code = "Bank_" + rs.getString("code");
            }
            System.out.println(code);

            String sql = "insert into Hos_bank_details(bank_id,hos_code,bank_name,branch_name,ifsc_code,account_number)"
                    + "Values(?,?,?,?,?,?)";

            ps = con.prepareStatement(sql);
            ps.setString(1, code);
            ps.setString(2, hos);
            ps.setString(3, Bank_name);
            ps.setString(4, Branch_name);
            ps.setString(5, IFSC_code);
            ps.setString(6, Account_number);
       
            int y = ps.executeUpdate();

            if (y >= 0) {
                bank_id = code;
                con.commit();
            }
        } catch (Exception ex) {
            Logger.getLogger(WMAS0001_Dao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return bank_id;

    }

    
    //for fetching bank Details 
      public ArrayList<HashMap<String, String>> Bank_details(){
           ArrayList<HashMap<String, String>> al = new ArrayList<>();
       
         
       
         
         try{
             
             con = new DBCon().getConnection();
             
             String Sql="select isnull(bank_id,'')as bank_id,\n" +
"isnull(bank_name,'')as bank_name,\n" +
"isnull(branch_name,'')as branch_name,\n" +
"isnull(ifsc_code,'')as ifsc_code,\n" +
"isnull(account_number,'')as account_number,\n" +
"isnull(create_date,'')as create_date\n" +
"from Hos_bank_details";
             
             
             System.out.println(Sql);
             ps = con.prepareStatement(Sql);
             rs = ps.executeQuery();
              while (this.rs.next()) {
                HashMap<String, String> Map = new HashMap<>();
                Map.put("bank_id", this.rs.getString("bank_id"));
                Map.put("bank_name", this.rs.getString("bank_name"));
                Map.put("branch_name", this.rs.getString("branch_name"));
                Map.put("ifsc_code", this.rs.getString("ifsc_code"));
                Map.put("account_number", this.rs.getString("account_number"));
                Map.put("create_date", this.rs.getString("create_date"));
                al.add(Map);
                 
                
                  System.out.println("--------->bank"+al);
            }
             
             
         }catch(Exception ex){
              Logger.getLogger(WMAS0001_Dao.class.getName()).log(Level.SEVERE, (String) null, (Throwable) ex);
         }
        return al; 
    }
    
  
      
      //for adding the last UHId 
   
      String Add_last_UHID(String old_uhid) {
        String UHID = "";

        int i = 0;
        int z = 0;
        String code = "";

        try {
            con = new DBCon().getConnection();
            con.setAutoCommit(false);

            String Sql_code = "select * from Hos_UHID";
            ps = con.prepareStatement(Sql_code);
            rs = ps.executeQuery();
            if (rs.next()) {
                UHID="NOT_ADDED";
                   con.commit();
            }
            else{
               String sql = "insert into Hos_UHID(uhid_no)values(?)";

            ps = con.prepareStatement(sql);
            ps.setString(1,old_uhid);
      
            int y = ps.executeUpdate(); 
            
            if (y >= 0) {
                UHID = "Added";
                con.commit();
            }
            }
           

            

        } catch (Exception ex) {
            Logger.getLogger(WMAS0001_Dao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return UHID;

    }
      
   //for adding doctor from storcut 
public String save_doctor_shortcut(String f_name,String l_name,String charge,String depertment,String hospital_code) throws SQLException {
        String sts = "";
        int i = 0;
        int z = 0;
        String code = "";
        try {
            con = new DBCon().getConnection();
            con.setAutoCommit(false);

            String sql = "select Count(*)+1 as code from mst_doctor";

            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                code = "DOC/" + hospital_code + "/" + rs.getString("code");
            }

            String Sql = "insert into mst_doctor(Doc_code,doc_name_first,doc_name_last,const_cahrge,department_name,hospital_code)"
                    + "Values(?,?,?,?,?,?)";
            ps = con.prepareStatement(Sql);
            ps.setString(1, code);
            ps.setString(2, f_name);
            ps.setString(3, l_name);
            ps.setString(4, charge);
            ps.setString(5, depertment);
            ps.setString(6, hospital_code);
  

            i = ps.executeUpdate();
            z++;
            System.out.println("--->+" + z);

            if (i >= 0) {

                con.commit();
                sts = code;
            }

        } catch (Exception ex) {
            Logger.getLogger(WMAS0001_Dao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return sts;
    }

public ArrayList<HashMap<String, String>> get_doc_shortcut() {
      ArrayList<HashMap<String, String>> al = new ArrayList<>();
        try {
            con = new DBCon().getConnection();

            String Sql = "select doc_code,concat(doc_name_first,' ' ,doc_name_last)as doc_name from mst_doctor where hospital_code='HOS/1'";

            ps = con.prepareStatement(Sql);
            System.out.println("===>" + Sql);
            rs = ps.executeQuery();
        
              while (rs.next()) {
               HashMap<String, String> Map = new HashMap<>();
                Map.put("doc_code", rs.getString("doc_code"));
                Map.put("doc_name", rs.getString("doc_name"));
                al.add(Map);
         
            } 
            

        } catch (Exception ex) {
            Logger.getLogger(WMAS0001_Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return al;

    }
      

    }




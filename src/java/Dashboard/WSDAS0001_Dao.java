/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dashboard;

import Dao.DBCon;
import Master.WMAS0001_Dao;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lenovo
 */
public class WSDAS0001_Dao {
    
     Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Statement stmt;
     
    
    //for staff details on dashboard 
    public HashMap<String, String> get_staff_details(String Staff_id) throws SQLException, IOException {
        HashMap<String, String> al = new HashMap<>();
        try {
            con = new DBCon().getConnection();

            String Sql = "select isnull(staff_id,'')as staff_id,\n" +
"isnull(Name,'')as Name,\n" +
"isnull(mobile_number,'')as mobile_number,\n" +
"isnull(department_name,'')as depertment,\n" +
"isnull(postion,'')as position,\n" +
"isnull(salary,'')as salary,\n" +
"isnull(adhar_card,'')as adhar_card,\n" +
"isnull(format(date_of_joining,'dd/MM/yy'),'')as date_of_joining,\n" +
"isnull(adhar_card,'')as adhar_card,\n" +
"isnull(sts,'')as sts,\n" +
"isnull(gender,'')as gender,\n" +
"isnull(age,'')as age\n" +
"\n" +
"from hospital_staff hs\n" +
"inner join hosiptal_department hd \n" +
"on hs.Depertament=hd.department_code\n" +
"where staff_id='"+Staff_id+"'";

            ps = con.prepareStatement(Sql);
            System.out.println("===>" + Sql);
            rs = ps.executeQuery();
            if (rs.next()) {

                al.put("staff_id", rs.getString("staff_id"));
                al.put("Name", rs.getString("Name"));
                al.put("mobile_number", rs.getString("mobile_number"));
                al.put("depertment", rs.getString("depertment"));
                al.put("position", rs.getString("position"));
                al.put("salary", rs.getString("salary"));
                al.put("adhar_card", rs.getString("adhar_card"));
                al.put("date_of_joining", rs.getString("date_of_joining"));
                al.put("sts", rs.getString("sts"));
                al.put("gender", rs.getString("gender"));
                al.put("age", rs.getString("age"));


            } else if (Staff_id == null) {
                al.put("staff_id","");
                al.put("Name", "");
                al.put("mobile_number", "");
                al.put("depertment", "");
                al.put("position","");
                al.put("salary","");
                al.put("adhar_card","");
                al.put("date_of_joining","");
                al.put("sts", "");
                al.put("gender", "");
                al.put("age","");

            }

        } catch (Exception ex) {
            Logger.getLogger(WMAS0001_Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return al;

    }
    
    
    
    
    
}

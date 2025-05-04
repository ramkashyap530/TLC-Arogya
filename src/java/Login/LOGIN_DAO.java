/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Login;

import Dao.DBCon;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tarun
 */
public class LOGIN_DAO {
    
    
  Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    
     public HashMap<String, String> Login(WLOGAUTH_MOD mod) throws IOException {
        HashMap<String, String> loginInfo=new HashMap<>();
        loginInfo.put("msg","fail");
        loginInfo.put("role_id","");
        try{
           if(!mod.getUsername().equals("") && mod.getUsername()!=null && !mod.getPassword().equals("") && mod.getPassword()!=null)
           {
               con=new DBCon().getConnection();
                String sql = "select user_id,username,password,Real_Name,lf.role_id,lf.hospital_code,location,user_image,sts,role_name,mh.hospital_name "
                        + "as hospital_name,mh.address as address,lf.staff_id as staff_id from login_info lf \n" +
"inner join role_master rm on lf.role_id=rm.role_id inner join mst_hospital mh on lf.hospital_code=mh.hospital_code  where username=?  ";
              
                ps = con.prepareStatement(sql);
                ps.setString(1,mod.getUsername());
                
                rs = ps.executeQuery();
                if (rs.next()) 
                {
                    
                    if(mod.getPassword().equals(rs.getString("password"))){
                      loginInfo.put("msg","success");
                      loginInfo.put("user_id",rs.getString("user_id"));
                      loginInfo.put("username",rs.getString("username"));
                      loginInfo.put("password",rs.getString("password"));
                      loginInfo.put("Real_Name", rs.getString("Real_Name"));
                      loginInfo.put("role_id", rs.getString("role_id"));
                      loginInfo.put("hospital_code", rs.getString("hospital_code"));
                      loginInfo.put("user_image",rs.getString("user_image"));
                      loginInfo.put("sts",rs.getString("sts"));
                      loginInfo.put("role_name",rs.getString("role_name"));
                      loginInfo.put("hospital_name",rs.getString("hospital_name"));
                      loginInfo.put("address",rs.getString("address"));
                      loginInfo.put("staff_id",rs.getString("staff_id"));
                     
                        System.out.println("--->+"+loginInfo);
                        
                    }
                    
                
                   
                }
                
            
            
           }
        }
        catch(SQLException e)
        {
               // new ErrorLog().getCause(module_name,item_name, javaclass_name, error_desc.substring(0,500), "delete");
                Logger.getLogger(WLOGIN_SERV.class.getName()).log(Level.SEVERE, null, e);
                System.out.println("Error -> "+e.getMessage());
        }
        return loginInfo;
    }  
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Inventory;

import Dao.DBCon;
import Master.WMAS0001_Dao;
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
 * @author Tarun
 */
public class WINV0001_dao {
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Statement stmt;
     String inv_mode="";
    
    
    public ArrayList<String> getdata(String cd,String Hospital_code)throws SQLDataException{
        ArrayList<String> al=new ArrayList<>();
        try{
           con=new DBCon().getConnection();
           String sql="";
           if(cd.equals("1")){
               
               sql="select isnull(cat_cd,'')as cat_cd,isnull(cat_name,'')as cat_name from inventory_cat";
               
           }
           if(cd.equals("2")){
               sql="select isnull(ivn_in_code,'')as item_code,isnull(item_name,'')as item_name from inventory_in";
           }
           if(cd.equals("3")){
               sql="";
               System.out.println("---->onchange"+sql);
           }
         
           
           ps=con.prepareStatement(sql);
           rs=ps.executeQuery();
           while(rs.next()){
      
               
                al.add("<option value='"+rs.getString(1)+"'>"+rs.getString(2)+"</option>");
               
           }
               
            
        } catch( Exception ex)
        {
             Logger.getLogger(WINV0001_dao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return al;
        
        
    }
    
    
    
    public boolean  save_cat(String category_name){
        boolean sts=false;
        int i=0;
String code="";
        
        try{
            con=new DBCon().getConnection();
            con.setAutoCommit(false);
            
            String sql_code="select count(*)+1 as cat_code from inventory_cat";
            ps=con.prepareStatement(sql_code);
            rs=ps.executeQuery();
            if(rs.next()){
              code="CAT/"+rs.getString("cat_code");
            }
           
      
              String Sql="insert into inventory_cat(cat_cd,cat_name)Values(?,?)";
            ps=con.prepareStatement(Sql);
            ps.setString(1,code);
            ps.setString(2,category_name);
            i=ps.executeUpdate();
                
            
            if(i>=0){
                sts=true;
                con.commit();
            }
        }   catch( Exception ex)
        {
             Logger.getLogger(WINV0001_dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return sts;
    }
    
    
    
//    for cat
    
    public ArrayList<HashMap<String, String>> get_cat_data() {
        
        
        ArrayList<HashMap<String, String>> prd_data= new ArrayList<>(); // Create an ArrayList object
               HashMap<String, String> prdcd=null;
        try {
            
         
            
            con=new DBCon().getConnection();
            String sql = "select isnull(cat_cd,'')as cat_cd,isnull(cat_name,'')as cat_name,isnull(cat_sts,'')as cat_sts from inventory_cat";
            
               
            System.out.println("====sql"+sql);


           System.out.println("sql-> "+sql);
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
                while (rs.next()) 
                {
                   prdcd=new HashMap<>();
                   prdcd.put("cat_cd", rs.getString("cat_cd"));
                   prdcd.put("cat_name",rs.getString("cat_name"));
                   prdcd.put("cat_sts", rs.getString("cat_sts"));
                  
                   
                   
                 
                   
                   
                    prd_data.add(prdcd);
                }
            
            
        } catch (Exception ex) {
            Logger.getLogger(WINV0001_dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                con.close();
                ps.close();
                rs.close();
                return prd_data;
            } catch (SQLException ex) {
                Logger.getLogger(WINV0001_dao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return  prd_data;
    }
      
    
    
    
//    for invetory GB
    
    public String saveInventory(String inv_cat,String item_name,String item_qty,String item_from,String receive_by,String remark,String hospital_code,String user) {
        boolean sts=false;
        String stsString="";
        try{
        con=new DBCon().getConnection();
        con.setAutoCommit(false);
            inv_mode="IN";
        String cd= getNewInventoryId();
        
        String sql = "INSERT INTO inventory_IN(ivn_in_code,ivn_cat_id,item_name,item_qty,item_from,receive_by,remark,page_type,inv_mode,hospital_code,created_by)" +
                   "VALUES (?,?,?,?,?,?,?,?,?,?,?)";
       ps=con.prepareStatement(sql);
                    ps.setString(1,cd);
                    ps.setString(2,inv_cat);
                    ps.setString(3,item_name);
                    ps.setString(4,item_qty);
                    ps.setString(5,item_from);
                    ps.setString(6,receive_by);
                    ps.setString(7,remark);
                    ps.setString(8,"GB");
                    ps.setString(9,inv_mode);
                    ps.setString(10,hospital_code);
                    ps.setString(11,user);
                    int length = ps.executeUpdate();
                     if(length>=0)
                    {
                        sts=true;
                        stsString=cd;
                        con.commit();
                    }
        
        }
        catch(Exception e)
        {   try {
            Logger.getLogger(WMAS0001_Dao.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("Error-> "+e.getMessage());
            con.rollback();
            
            } catch (SQLException ex) {
                Logger.getLogger(WINV0001_dao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        finally{try {try {
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(WMAS0001_Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        con.close();} catch (SQLException ex) {
                Logger.getLogger(WINV0001_dao.class.getName()).log(Level.SEVERE, null, ex);
            }
}
      return stsString;
    }
    
    
    
    private String getNewInventoryId() {
      String newCd="";
        
        try{
        con=new DBCon().getConnection();
        con.setAutoCommit(false);
        
        String sql = " select count(*)+1 as cd from inventory_in";
        ps = con.prepareStatement(sql);
        
        rs = ps.executeQuery();
                if (rs.next()) 
                {
                   newCd+="INV"+inv_mode+"-"+(rs.getInt("cd"));
                }
        
        }
        catch(Exception e)
        {Logger.getLogger(WINV0001_dao.class.getName()).log(Level.SEVERE, null, e);
                System.out.println("Error-> "+e.getMessage());
        return newCd;
        }
        return newCd;
    }
    
    
    
    
//   for geeting inventory details 
    
    public ArrayList<HashMap<String, String>> getInventoryData(String inv_cat, String item_name)  {
        ArrayList<HashMap<String, String>> map_al = new ArrayList<>(); // Create an ArrayList object
               HashMap<String, String> map=null;
        try {
            
            
            
            con=new DBCon().getConnection();
            String sql = "select distinct ivn_cat_id,tab.cat_name,item_code,item_name,\n" +
                        "((sum(item_qty)OVER (PARTITION BY item_code ))\n" +
                        "-(isnull((select sum(isnull(ivo.item_qty,0)) from inventory_out ivo where \n" +
                        "ivo.ivn_cat_id=tab.ivn_cat_id and ivo.item_name=tab.item_name),0)))\n" +
                        "as item_qty\n" +
                        "from (\n" +
                        "select ivd.ivn_cat_id,ic.cat_name,\n" +
                        "isnull(ivd.item_name,'')as item_code,isnull(ivd.item_name,'')as item_name,\n" +
                        "isnull(ivd.item_qty,0) as item_qty \n" +
                        "from inventory_in ivd\n" +
                        "inner join inventory_cat ic on ic.cat_cd=ivd.ivn_cat_id\n" +
                        "where 1=1";
            if(!inv_cat.equals("")){ sql+=" and ivd.ivn_cat_id='"+inv_cat+"'";}
            
            if(!item_name.equals("")){ sql+=" and ivd.item_name='"+item_name+"'";}
            
           
           sql+=" ) as tab order by tab.item_name asc";


           System.out.println("sql-> "+sql);
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
                while (rs.next()) 
                {
                   map=new HashMap<>();
                  
                   map.put("ivn_cat_id",rs.getString("ivn_cat_id"));
                   map.put("cat_name",rs.getString("cat_name"));
                   map.put("item_code",rs.getString("item_code"));
                   map.put("item_name",rs.getString("item_name"));
                   map.put("item_qty",rs.getString("item_qty"));
                   
                   
                    map_al.add(map);
                }
            
            
        } catch (Exception ex) {
            System.out.println("ERROR "+ex.getMessage());
            ex.printStackTrace();
            Logger.getLogger(WINV0001_dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                con.close();
                ps.close();
                rs.close();
                return map_al;
            } catch (SQLException ex) {
                Logger.getLogger(WINV0001_dao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return  map_al;
    }
    
    
    
//    for saving inventory out
    
    public boolean saveInventoryOut(HttpServletRequest request) {
 
        boolean sts=false;
       
        try{
        con=new DBCon().getConnection();
        con.setAutoCommit(false);
            inv_mode="OUT";
             String sql = "INSERT INTO inventory_out(ivn_in_code,ivn_cat_id,item_name,item_qty,item_from,receive_by,remark,page_type,inv_mode)" +
                   "VALUES (?,?,?,?,?,?,?,?,?)";
            
            if(request.getParameterValues("chbox")!=null)
            {
                
               
                for(String chBox:request.getParameterValues("chbox"))
                {
                    
                    String ivn_cat_id=request.getParameter("ivn_cat_id"+chBox);
                    String item_code=request.getParameter("item_code"+chBox);
                    String receiver_name=request.getParameter("receiver_name"+chBox);
                    String item_qty=request.getParameter("item_qty"+chBox);
                    String sender_name=request.getParameter("sender_name"+chBox);
                    String remark=request.getParameter("remark"+chBox);
                    
                    
                   String cd= getNewInventoryId();
        
       
                   ps=con.prepareStatement(sql);
                    ps.setString(1,cd);
                    ps.setString(2,ivn_cat_id);
                    ps.setString(3,item_code);
                    ps.setString(4,item_qty);
                    ps.setString(5,"College BY "+sender_name);
                    ps.setString(6,receiver_name);
                    ps.setString(7,remark);
                    ps.setString(8,"Inventory Out");
                    ps.setString(9,inv_mode);
                    
                   int length = ps.executeUpdate();
                     if(length>=0)
                    {sts=true;
                    }else{sts=false; break;}
               }
                
               
            } 
            
            
            
            if(sts){con.commit();}else{throw new Exception("Some ERROR");}
              return  sts;
        }
        catch(Exception e)
        {   try {
            Logger.getLogger(WINV0001_dao.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("Error-> "+e.getMessage());
            con.rollback();
            
            } catch (SQLException ex) {
                Logger.getLogger(WINV0001_dao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        finally{try {try {
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(WINV0001_dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        con.close();} catch (SQLException ex) {
                Logger.getLogger(WINV0001_dao.class.getName()).log(Level.SEVERE, null, ex);
            }
}
      return sts;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}




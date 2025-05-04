/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HR;

import Dao.DBCon;
import Master.WMAS0001_Dao;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tarun
 */
public class WHR0001_Dao {
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Statement stmt;

    
    
    
    public ArrayList<String> getdata(String cd,String usr)throws SQLDataException{
        ArrayList<String> al=new ArrayList<>();
        try{
           con=new DBCon().getConnection();
           String sql="";
           if(cd.equals("1")){
               
               sql="select isnull(department_code,'') as dep_code,isnull(department_name,'')as dep_name from hosiptal_department";
               
           }
           if(cd.equals("2")){
               sql="select mdid,modulenm from modulenfo";
           }
           if(cd.equals("3")){
               sql="select a.mdid,b.modulenm from assign_module a inner join modulenfo b on a.mdid=b.mdid where userid='"+usr+"'";
               System.out.println("---->onchange"+sql);
           }
           
           if(cd.equals("4")){
               sql="select isnull(category_id,'')as category_id,isnull(category_name,'')as category_name from charge_category";
               System.out.println("---->onchange"+sql);
           }
           
           if(cd.equals("5")){
               sql="select isnull(room_id,'')as room_code,isnull(room_number_name,'')as room_name from mst_rooms";
               System.out.println("---->onchange"+sql);
           }
         
           
           ps=con.prepareStatement(sql);
           rs=ps.executeQuery();
           while(rs.next()){
      
               
                al.add("<option value='"+rs.getString(1)+"'>"+rs.getString(2)+"</option>");
               
           }
               
            
        } catch( Exception ex)
        {
             Logger.getLogger(WMAS0001_Dao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return al;
        
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public  ArrayList<HashMap<String,String>> get_satff_data(String Hospital_code)throws SQLException, IOException{
      ArrayList<HashMap<String,String>>al=new ArrayList<>();
      try{
      con=new DBCon().getConnection();
      
      String Sql="select isnull(hf.staff_id,'Not Set')as staff_id,isnull(name,'')as name,isnull(depertament,'Not Set')as depertement,\n" +
"isnull(postion,'Not Set')as position,isnull((select shift_name from mst_staff_shift mss where mss.staff_id=hf.staff_id and  mss.shift_date=format(GETDATE(),'yyyy-MM-dd')),'Not Assigned Yet')\n" +
"as current_shift from hospital_staff hf\n" +
"where hf.hospital_code='"+Hospital_code+"'";
        
      ps=con.prepareStatement(Sql);
          System.out.println("====>stff"+Sql);
      rs=ps.executeQuery();
      while(rs.next()){
          HashMap<String,String> Map=new HashMap();
          
          Map.put("staff_id",rs.getString("staff_id"));
          Map.put("depertement",rs.getString("depertement"));
          Map.put("position",rs.getString("position"));
          Map.put("current_shift",rs.getString("current_shift"));
          Map.put("name",rs.getString("name"));
          al.add(Map);
      }

         
     } catch( Exception ex)
        {
             Logger.getLogger(WMAS0001_Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
     return al;
 
}
    
    
    
    
    
//    public boolean  assign_shift(String []staff_id,String []shift){
//        boolean sts=false;
//        int i=0;
//        int y=0;
//        int z=0;
//        
//        try{
//            con=new DBCon().getConnection();
//            con.setAutoCommit(false);
//            
//              String sql="delete from mst_staff_shift where rwid>=0 ";
//            System.out.println("---->delete"+sql);
//            ps=con.prepareStatement(sql);
//             y=ps.executeUpdate();
//            
//            if(y>=0){
//            for(String mod:staff_id){
//                 
//            String Sql="insert into mst_staff_shift(satff_id,current_shift)values(?,?)";
//            ps=con.prepareStatement(Sql);
//            ps.setString(1,mod);
//            ps.setString(2,shift[z]);
//             i=ps.executeUpdate();
//         
//             z++;
//             
//            }
//            if(i>=0){
//              
//                con.commit();
//                  sts=true;
//            }
//            
//            }
//        }   catch( Exception ex)
//        {
//             Logger.getLogger(WMAS0001_Dao.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        return sts;
//    }
//     
    
    
    
    
    
    public boolean  assign_shift(String staff_id,String []dates,String []shift)throws SQLException {
        boolean sts=false;
        int i=0;
        int z=0;
        try{
            con=new DBCon().getConnection();
            con.setAutoCommit(false);
            
            
            for(String mod:dates){
               
                deleteSiftData(staff_id,mod);
                
           String Sql="insert into mst_staff_shift(staff_id,shift_date,shift_name)values(?,?,?)";
            ps=con.prepareStatement(Sql);
            ps.setString(1,staff_id);
            ps.setString(2,ddmmyyyToDateString2(mod));
            ps.setString(3,shift[z++]);
           
              
           
            i=ps.executeUpdate();
           
            
            if(i>=0){
                
                
                con.commit();
                sts=true;
            }else{sts=false;break;}
            
        }
        }catch( Exception ex)
        {
             Logger.getLogger(WMAS0001_Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return sts;
    }
    
    public String ddmmyyyToDateString(String ddmmyyyy)
    {
        String result="";
        try {
           String sDate1=ddmmyyyy;
    Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);  
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
       result= sdf.format(date1);
    
        } catch(Exception e)
       { Logger.getLogger(WMAS0001_Dao.class.getName()).log(Level.SEVERE, null, e);
                    System.out.println("Error-> "+e.getMessage());}
        return result;
    }
    
      private void deleteSiftData(String staff_id, String mod) throws SQLException {
       
            String newDt=ddmmyyyToDateString2(mod);
          
            String sql="delete from mst_staff_shift where staff_id='"+staff_id+"' and shift_date='"+newDt+"'";
            System.out.println("---->delete"+sql);
            PreparedStatement ps1=con.prepareStatement(sql);
            ps1.executeUpdate();
    }
    
      
      public String ddmmyyyToDateString2(String ddmmyyyy)
    {
        String result="";
        try {
           String sDate1=ddmmyyyy;
    Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);  
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
       result= sdf.format(date1);
    
        } catch(Exception e)
       { Logger.getLogger(WMAS0001_Dao.class.getName()).log(Level.SEVERE, null, e);
                    System.out.println("Error-> "+e.getMessage());}
        return result;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
// for geeting attendence data 
    
    public  ArrayList<HashMap<String,String>> get_satff_att_data(String Hospital_code)throws SQLException, IOException{
      ArrayList<HashMap<String,String>>al=new ArrayList<>();
      try{
      con=new DBCon().getConnection();
      
      String Sql="select isnull(name,'')as name,isnull(staff_id,'')as emp_id,isnull(postion,'')as postion,isnull(department_name,'')as Depertament,\n" +
"isnull((select att_sts from staff_attendance where staff_id=hf.staff_id and  att_date=format(GETDATE(),'yyyy-MM-dd')),'Not Marked') as att_sts,\n" +
"isnull((Select shift_name from mst_staff_shift mss where mss.staff_id=hf.staff_id and convert(varchar,shift_date, 103)=convert(varchar,getdate(), 103)),'Not Assigned/Please Assign Shift')as shit\n" +
"from hospital_staff hf inner join hosiptal_department hd on hd.department_code=hf.Depertament";
        
      ps=con.prepareStatement(Sql);
          System.out.println("====>stff"+Sql);
      rs=ps.executeQuery();
      while(rs.next()){
          HashMap<String,String> Map=new HashMap();
          
          Map.put("staff_id",rs.getString("emp_id"));
          Map.put("Depertament",rs.getString("Depertament"));
          Map.put("postion",rs.getString("postion"));
          Map.put("name",rs.getString("name"));
          Map.put("att_sts",rs.getString("att_sts"));
          Map.put("shit",rs.getString("shit"));
          System.out.println("===>"+rs.getString("att_sts"));
       
          al.add(Map);
      }

         
     } catch( Exception ex)
        {
             Logger.getLogger(WMAS0001_Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
     return al;
 
}
    
    
    
    
//    for saving attendances
    
    boolean SaveAtt(String[] emp_id,String[] p_sts,String user,String company_code,String att_dat,String[] remark)throws SQLDataException{
         boolean sts=false;
        
        int i=0;
        int z=0;
        try{
            
            con=new DBCon().getConnection();
            con.setAutoCommit(false);
            
            
           
           String Sql="insert into staff_attendance(staff_id,att_date,att_sts,remark,Hospital_code,marked_by)Values(?,?,?,?,?,?)";
           ps=con.prepareStatement(Sql);
           
           for(String e:emp_id){
         
           ps.setString(1,e);
           ps.setString(2,att_dat);
           ps.setString(3,p_sts[z]);
           ps.setString(4,remark[z]);
           ps.setString(5,company_code);
           ps.setString(6,user);
           
         

           ps.addBatch();
               z++;
           
           }
              sts=ps.executeBatch().length>0;
           if(sts){
               
               con.commit();
           }
            
        }catch(Exception e){
            System.out.println("----->"+e);
        }
         
        
       return sts;
    } 
    
    
    
    
//    for getting the salary details
    
    ArrayList<HashMap<String,String>> getMonthData(String code)throws SQLDataException{
      ArrayList<HashMap<String,String>> al=new ArrayList<>();
      
      try{
          con=new DBCon().getConnection();
          String Sql="";
         
          
          Sql="declare @start DATE = (select format(date_of_joining,'yyyy-MM-dd') from hospital_staff where staff_id='"+code+"')\n" +
"declare @end DATE = getdate()\n" +
"declare @empid varchar(max) = '"+code+"'\n" +
"\n" +
";with months (date)\n" +
"AS\n" +
"(\n" +
"    SELECT @start\n" +
"    UNION ALL\n" +
"    SELECT DATEADD(month, 1, date)\n" +
"    from months\n" +
"    where DATEADD(month, 1, date) < @end\n" +
")\n" +
"\n" +
"select *,(tab3.tot_sal_month-tab3.salary_paid-tab3.advance)as pending_salary from (\n" +
"select *,(tab2.perDaySaly*tab2.tot_this_Month_Attd + tab2.tot_night_allowance)as tot_sal_month,"
                  + "isnull((tab2.perDaySaly*tab2.tot_this_Month_Attd),0)as tot_sal,\n" +
"(isnull((select sum(salary_paid) from staff_salary_payment msp where msp.month=tab2.saveCode and msp.emp_id=@empid),0))as salary_paid\n" +
"from (\n" +
"select --*,\n" +
"concat(tab.MonthNumber,'-',tab.[MonthYear]) as saveCode,\n" +
"\n" +
"tab.[MonthName],\n" +
"tab.MonthNumber,tab.[LastDayOfMonth],tab.[MonthYear],tab.empid,\n" +
"(ms.salary) as salary,\n" +
"cast((cast(ms.salary as float)/cast(tab.LastDayOfMonth as float))as decimal(10,2)) as perDaySaly,\n" +
"(select isnull(sum((isnull(cast(att_sts as float),0))),0)from staff_attendance mta where mta.staff_id=tab.empid\n" +
"and MONTH(att_date)=tab.MonthNumber and year(att_date)=tab.MonthYear)as tot_this_Month_Attd,\n" +
"\n" +
"isnull(\n" +
"(select isnull(sum((isnull(cast(att_sts as float),0))),0)from staff_attendance mta \n" +
"inner join mst_staff_shift mss on mss.staff_id=mta.staff_id and mss.shift_name='Day' and mss.shift_date=mta.att_date\n" +
"where mta.staff_id=tab.empid\n" +
"and MONTH(att_date)=tab.MonthNumber and year(att_date)=tab.MonthYear)\n" +
"\n" +
",0)as tot_day_p,\n" +
"isnull(\n" +
"(select isnull(sum((isnull(cast(att_sts as float),0))),0)from staff_attendance mta \n" +
"inner join mst_staff_shift mss on mss.staff_id=mta.staff_id and mss.shift_name='Night' and mss.shift_date=mta.att_date\n" +
"where mta.staff_id=tab.empid\n" +
"and MONTH(att_date)=tab.MonthNumber and year(att_date)=tab.MonthYear)\n" +
",0)as tot_night_p,\n" +
"isnull((select sum(amount) from salary_advance sa where sa.staff_id=@empid and sa.month_code=concat(tab.MonthNumber,'-',tab.[MonthYear])),0)as advance,\n" +
"isnull((select sum(amount) from night_shift_allowance),0)as night_allowance,\n" +
"\n" +
"isnull((isnull((select isnull(sum((isnull(cast(att_sts as float),0))),0)from staff_attendance mta \n" +
"inner join mst_staff_shift mss on mss.staff_id=mta.staff_id and mss.shift_name='Night' and mss.shift_date=mta.att_date\n" +
"where mta.staff_id=tab.empid\n" +
"and MONTH(att_date)=tab.MonthNumber and year(att_date)=tab.MonthYear),0) * isnull((select sum(amount) from night_shift_allowance),0)),0)as tot_night_allowance,\n" +
"\n" +
"\n" +
"(select department_name from hospital_staff hf inner join hosiptal_department hd on\n" +
"hf.Depertament=hd.department_code where hf.staff_id=@empid)as department,\n" +
"isnull((select count(isnull(shift_name,0)) from mst_staff_shift where staff_id=@empid and shift_name='Day' and MONTH(shift_date)=tab.MonthNumber and year(shift_date)=tab.MonthYear),0)as day_shift,\n" +
"isnull((select count(isnull(shift_name,0)) from mst_staff_shift where staff_id=@empid and shift_name='Night' and MONTH(shift_date)=tab.MonthNumber and year(shift_date)=tab.MonthYear),0)as night_shift\n" +
"\n" +
"--(select isnull(isnull(sum(credit_balance),0)-isnull(sum(debit_balance),0),0) from mst_balance)as wallet_balance,\n" +
"  from (\n" +
"select     @empid as empid,\n" +
"[MonthName]    = DATENAME(mm, date),\n" +
"           [MonthNumber]  = DATEPART(mm, date),  \n" +
"           [LastDayOfMonth]  = DATEPART(dd, EOMONTH(date)),\n" +
"           [MonthYear]    = DATEPART(yy, date)\n" +
"from months ) as tab\n" +
"inner join hospital_staff ms on ms.staff_id=tab.empid\n" +
") as tab2 ) as tab3";
          
          
    // private static DecimalFormat df = new DecimalFormat("0.00");
           DecimalFormat df = new DecimalFormat("0.00");
          ps=con.prepareStatement(Sql);
          System.out.println("====>sql"+Sql);
          rs=ps.executeQuery();
          while(rs.next()){
              
             
               // df.setMaximumFractionDigits(2);
//System.out.println(df.format(decimalNumber));
              
              HashMap<String,String> Map=new HashMap();
              
              Map.put("pending_salary",df.format(rs.getDouble("pending_salary")));
              Map.put("salary",rs.getString("salary"));
              Map.put("tot_this_Month_Attd",rs.getString("tot_this_Month_Attd"));
              Map.put("tot_sal_month",df.format(rs.getDouble("tot_sal_month")));
              Map.put("salary_paid",rs.getString("salary_paid"));
              Map.put("MonthName",rs.getString("MonthName"));
              Map.put("empid",rs.getString("empid"));
              Map.put("saveCode",rs.getString("saveCode"));
              Map.put("department",rs.getString("department"));
              Map.put("day_shift",rs.getString("day_shift"));
              Map.put("night_shift",rs.getString("night_shift"));
              Map.put("tot_day_p",rs.getString("tot_day_p"));
              Map.put("tot_night_p",rs.getString("tot_night_p"));
              Map.put("advance",rs.getString("advance"));
              Map.put("tot_night_allowance",rs.getString("tot_night_allowance"));
              Map.put("night_allowance",rs.getString("night_allowance"));
              Map.put("tot_sal",rs.getString("tot_sal"));
              Map.put("MonthYear",rs.getString("MonthYear"));
             
             // System.out.println("df.format(rs.getDouble(\"wallet_balance\"))df.format(rs.getDouble(\"wallet_balance\"))df.format(rs.getDouble(\"wallet_balance\")) "+df.format(rs.getDouble("wallet_balance")));
              //System.out.println("-------------------->>>>>>> "+rs.getDouble("totBalance"));
//              Map.put("wallet_balance",df.format(rs.getDouble("wallet_balance"))); 
           

              al.add(Map);
          }
          System.out.println("=====test"+al);
          
          
          
          
          
          
      }catch (Exception e){
          System.out.println("---->"+e);
      }
        
        return al;
        
    }
     
//for full attendence    
    
  ArrayList<HashMap<String,String>> getMonthData_full(String code)throws SQLDataException{
      ArrayList<HashMap<String,String>> al=new ArrayList<>();
      
      try{
          con=new DBCon().getConnection();
          String Sql="";
         
          
          Sql="DECLARE @StartDate Date= (select date_of_joining from hospital_staff where staff_id='Staff/HOS/1/1')\n" +
"DECLARE @EndDate date=getdate()\n" +
"declare @empid varchar(max) = 'Staff/HOS/1/1'\n" +
";\n" +
"\n" +
"WITH ListDates(AllDates) AS\n" +
"(    SELECT @StartDate AS DATE\n" +
"    UNION ALL\n" +
"    SELECT DATEADD(DAY,1,AllDates)\n" +
"    FROM ListDates \n" +
"    WHERE AllDates < @EndDate)\n" +
"\n" +
"SELECT format(AllDates,'dd/MM/yyyy')as all_date,datename(DW,AllDates)as date_name,\n" +
"\n" +
"isnull((select att_sts from hospital_staff hf inner join staff_attendance sa on sa.staff_id=hf.staff_id   where hf.staff_id=@empid\n" +
" and AllDates=att_date),'Not Marked')as sta\n" +
"FROM ListDates";
          
          
    // private static DecimalFormat df = new DecimalFormat("0.00");
           DecimalFormat df = new DecimalFormat("0.00");
          ps=con.prepareStatement(Sql);
          System.out.println("====>sql"+Sql);
          rs=ps.executeQuery();
          while(rs.next()){
              
             
               // df.setMaximumFractionDigits(2);
//System.out.println(df.format(decimalNumber));
              
              HashMap<String,String> Map=new HashMap();
              
              Map.put("all_date",df.format(rs.getDouble("all_date")));
              Map.put("date_name",rs.getString("date_name"));
              Map.put("sta",rs.getString("sta"));


              al.add(Map);
          }
          System.out.println("=====test"+al);
          
          
          
          
          
          
      }catch (Exception e){
          System.out.println("---->"+e);
      }
        
        return al;
        
    }   
    
    
//    for attandance check 
  
  
  ArrayList<HashMap<String,String>>Particualr_emp(String cd)throws SQLDataException{
      ArrayList<HashMap<String,String>> al=new ArrayList<>();
      
      try{
          con=new DBCon().getConnection();
          String Sql="";
          
          Sql="DECLARE @StartDate Date= (select date_of_joining from hospital_staff where staff_id='"+cd+"')\n" +
"DECLARE @EndDate date=getdate()\n" +
"declare @empid varchar(max) = '"+cd+"'\n" +
";\n" +
"\n" +
"WITH ListDates(AllDates) AS\n" +
"(    SELECT @StartDate AS DATE\n" +
"    UNION ALL\n" +
"    SELECT DATEADD(DAY,1,AllDates)\n" +
"    FROM ListDates \n" +
"    WHERE AllDates < @EndDate)\n" +
"\n" +
"SELECT datename(month,AllDates)as month,format(AllDates,'dd/MM/yyyy')as all_date,datename(DW,AllDates)as date_name,\n" +
"\n" +
"isnull((select att_sts from hospital_staff hf inner join staff_attendance sa on sa.staff_id=hf.staff_id   where hf.staff_id=@empid\n" +
" and AllDates=att_date),'Not Marked')as staff_sts,\n" +
" isnull((select marked_by from hospital_staff hf inner join staff_attendance sa on sa.staff_id=hf.staff_id   where hf.staff_id=@empid\n" +
" and AllDates=att_date),'None')as marked_by\n" +
"\n" +
"FROM ListDates  ";

          
          System.out.println("---->"+Sql);
          
          ps=con.prepareStatement(Sql);
          rs=ps.executeQuery();
          while(rs.next()){
              HashMap<String,String> Map=new HashMap(); 
              Map.put("month",rs.getString("month"));
              Map.put("all_date",rs.getString("all_date"));
              Map.put("date_name",rs.getString("date_name"));
              Map.put("staff_sts",rs.getString("staff_sts"));
             
              Map.put("marked_by",rs.getString("marked_by"));
           
    
              al.add(Map);
          }
          
          System.out.println("===>emp"+Sql);
          
          
          
          
      }catch (Exception e){
          System.out.println("---->"+e);
      }
        
        return al;
        
    }  
    
  
  
  
  
//  for salry details
  
  ArrayList<HashMap<String,String>>Particualr_emp_sal_det(String cd)throws SQLDataException{
      ArrayList<HashMap<String,String>> al=new ArrayList<>();
      
      try{
          con=new DBCon().getConnection();
          String Sql="";
          
          Sql="select hf.staff_id,salary,((cast(pf as float)/100)*salary)as pf,\n" +
"((cast(MRA as float)/100)*salary)as mra,\n" +
"((cast(tds as float)/100)*salary)as tds,\n" +
"((cast(ECIS as float)/100)*salary)as ecis,\n" +
"((cast(taxes as float)/100)*salary)as taxes,\n" +
"((cast(other as float)/100)*salary)as other,\n" +
"isnull(amount,0)as advance\n" +
"\n" +
"from hospital_staff hf left join salary_deductions sd on hf.hospital_code=sd.hospital_code \n" +
"left join salary_advance sa on sa.staff_id=hf.staff_id\n" +
"\n" +
"where hf.staff_id='"+cd+"'";

          
          System.out.println("---->"+Sql);
          
          ps=con.prepareStatement(Sql);
          rs=ps.executeQuery();
          while(rs.next()){
              HashMap<String,String> Map=new HashMap(); 
              Map.put("pf",rs.getString("pf"));
              Map.put("mra",rs.getString("mra"));
              Map.put("tds",rs.getString("tds"));
              Map.put("ecis",rs.getString("ecis"));
              Map.put("taxes",rs.getString("taxes"));
              Map.put("other",rs.getString("other"));
              Map.put("salary",rs.getString("salary"));
              Map.put("advance",rs.getString("advance"));
           
    
              al.add(Map);
          }
          
          System.out.println("===>emp"+Sql);
          
          
          
          
      }catch (Exception e){
          System.out.println("---->"+e);
      }
        
        return al;
        
    }  
    
  
  
//  for hr leaves 
  
  public  ArrayList<HashMap<String,String>> get_leave_date()throws SQLException, IOException{
      ArrayList<HashMap<String,String>>al=new ArrayList<>();
      try{
      con=new DBCon().getConnection();
      
      String Sql="select isnull(leave_id,'')as leave_id,isnull(Hr_leave.staff_id,'')as staff_id,isnull(hs.name,'')as staff_name\n" +
",isnull(format(leave_from,'dd/MM/yyyy'),'') as leave_from ,\n" +
"isnull(format(leave_to,'dd/MM/yyyy'),'')as leave_to,isnull(reason,'')as reason,isnull(status,'')as status,\n" +
"DATEDIFF(DY,leave_from,leave_to)as total_leave_days,isnull(Approved_by,'')as approved_by,\n" +
"isnull(format(approved_on,'dd/MM/yyyy'),'')as approved_on\n" +
"from Hr_leave left join hospital_staff hs on hs.staff_id=Hr_leave.staff_id";
        
      ps=con.prepareStatement(Sql);
          System.out.println("====>stff"+Sql);
      rs=ps.executeQuery();
      while(rs.next()){
          HashMap<String,String> Map=new HashMap();
          
          Map.put("leave_id",rs.getString("leave_id"));
          Map.put("staff_id",rs.getString("staff_id"));
          Map.put("staff_name",rs.getString("staff_name"));
          Map.put("leave_from",rs.getString("leave_from"));
          Map.put("leave_to",rs.getString("leave_to"));
          Map.put("reason",rs.getString("reason"));
          Map.put("status",rs.getString("status"));
          Map.put("total_leave_days",rs.getString("total_leave_days"));
         
          Map.put("approved_by",rs.getString("approved_by"));
          Map.put("approved_on",rs.getString("approved_on"));
          al.add(Map);
      }

         
     } catch( Exception ex)
        {
             Logger.getLogger(WMAS0001_Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
     return al;
 
}
    
//   for leave apporaval
  
   public boolean  leave_approval(String user,String leave_code)throws SQLException {
        boolean sts=false;
        int i=0;
        int z=0;
        try{
            con=new DBCon().getConnection();
            con.setAutoCommit(false);

            String Sql="update hr_leave set status='Approved', approved_by='"+user+"' , approved_on=GETDATE(),reason_of_cancel='' where leave_id='"+leave_code+"'";
            ps=con.prepareStatement(Sql);
            System.out.println("====>"+Sql);
            i=ps.executeUpdate();
            if(i>=0){
                con.commit();
                sts=true;
            }
            
        
        }catch( Exception ex)
        {
             Logger.getLogger(WMAS0001_Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return sts;
    }
    
   
   
   public boolean  leave_cancel(String user,String leave_code,String Reason_of_cancel)throws SQLException {
        boolean sts=false;
        int i=0;
        int z=0;
        try{
            con=new DBCon().getConnection();
            con.setAutoCommit(false);

            String Sql="update hr_leave set status='Canceled' , reason_of_cancel='"+Reason_of_cancel+"',"
                    + "approved_on=GETDATE(),Approved_by='"+user+"' where leave_id='"+leave_code+"'";
            ps=con.prepareStatement(Sql);
            System.out.println("====>"+Sql);
            i=ps.executeUpdate();
            if(i>=0){
                con.commit();
                sts=true;
            }
            
        
        }catch( Exception ex)
        {
             Logger.getLogger(WMAS0001_Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return sts;
    }
    
   
   
//   get dates b/w the dates 
   
   public  ArrayList<HashMap<String,String>> get_dates_bw(String Date_from,String Date_to,String Staff_id)throws SQLException, IOException{
      ArrayList<HashMap<String,String>>al=new ArrayList<>();
      try{
      con=new DBCon().getConnection();
      
      String Sql="DECLARE @StartDate DATE, @EndDate DATE\n" +
"SELECT @StartDate = '"+Date_from+"', @EndDate = '"+Date_to+"'; \n" +
"WITH ListDates(AllDates) AS\n" +
"(    SELECT @StartDate AS DATE\n" +
"    UNION ALL\n" +
"    SELECT DATEADD(DAY,1,AllDates)\n" +
"    FROM ListDates \n" +
"    WHERE AllDates < @EndDate)\n" +
"SELECT format(AllDates,'dd/MM/yyyy')as dates,datename(DW,AllDates)as datename,"
              + "isnull((select shift_name from mst_staff_shift where staff_id='"+Staff_id+"' and convert(varchar,shift_date, 103)=format(AllDates,'dd/MM/yyyy')),'Not Assigned')as current_shift\n" +
"FROM ListDates\n" +
"";
        
      ps=con.prepareStatement(Sql);
          System.out.println("====>stff"+Sql);
      rs=ps.executeQuery();
      while(rs.next()){
          HashMap<String,String> Map=new HashMap();
          
          Map.put("dates",rs.getString("dates"));
          Map.put("datename",rs.getString("datename"));
          Map.put("current_shift",rs.getString("current_shift"));
          
          al.add(Map);
      }

         
     } catch( Exception ex)
        {
             Logger.getLogger(WMAS0001_Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
     return al;
 
}
   
   
//   for backdate attanence
   
   String Save_back_Att(String emp_id,String p_sts,String Date, String user)throws SQLDataException{
         String  sts="";
        
        int i=0;
        int z=0;
        try{
            
            con=new DBCon().getConnection();
            con.setAutoCommit(false);
            
            String sql_1="select * from staff_attendance where att_date='"+Date+"'";
            ps=con.prepareStatement(sql_1);
            rs=ps.executeQuery();
            if(rs.next()){
                sts="Sorry !! This Date Attendence Already Marked";
            }else{
            
            
           String Sql="insert into staff_attendance(staff_id,att_date,att_sts,marked_by)Values(?,?,?,?)";
           ps=con.prepareStatement(Sql);
  
           ps.setString(1,emp_id);
           ps.setString(2,Date);
           ps.setString(3,p_sts);
           ps.setString(4,user);
   
           i=ps.executeUpdate();
             
           
           
             
           if(i>0){
               con.commit();
               sts="Back Date Attendece Marked Of Date "+Date+"";
           }
        }
        }
            
        catch(Exception e){
            System.out.println("----->"+e);
        }
         
                 
        
       return sts;
    }   
   
   
   
   
   String Save_advance_pay(String emp_id,String month_code,String amount,String user)throws SQLDataException{
         String  sts="";
        
        int i=0;
        int z=0;
        try{
            
            con=new DBCon().getConnection();
            con.setAutoCommit(false);
            
     
         
            
            
           String Sql="insert into salary_advance(staff_id,paid_by,amount,month_code)Values(?,?,?,?)";
           ps=con.prepareStatement(Sql);
  
           ps.setString(1,emp_id);
           ps.setString(2,user);
           ps.setString(3,amount);
           ps.setString(4,month_code);
   
           i=ps.executeUpdate();
             
           
           
             
           if(i>0){
               con.commit();
               sts="Advance Paid";
           }
        }
        
            
        catch(Exception e){
            System.out.println("----->"+e);
        }
         
                 
        
       return sts;
    }   
          

   
//   for saving the salary 
   
   boolean SaveSalary(String[] emp_id,String [] base_salary,String [] paid_amount,String user,String [] month,String mode,String Pyem_mode,String Transction,String Check,String company_code,
           String month_name,String month_year)throws SQLDataException{
         boolean sts=false;
         String num="";
        int i=0;
        int z=0;
        try{
            float tot=0;
            con=new DBCon().getConnection();
            con.setAutoCommit(false);
            
           
           String Sql="insert into staff_salary_payment(emp_id,base_salary,salary_paid,month,paid_by,paid_date,payment_mode,trans_id,App_name,check_number,month_name,year_number)"
                   + "Values(?,?,?,?,?,GETDATE(),?,?,?,?,?,?)";
           
           ps=con.prepareStatement(Sql);
            System.out.println("====>yanha tak aya");
          for(String c:base_salary){
              System.out.println("====>loop me aya");
           ps.setString(1,emp_id[i]);
              System.out.println("====>loop me emp_id");
           ps.setString(2,c);
            System.out.println("====>loop me c");
           ps.setString(3,paid_amount[i]);
            System.out.println("====>loop me amount");
           ps.setString(4,month[i] );
            System.out.println("====>loop me month");
           ps.setString(5,user);
           ps.setString(6,mode);
           ps.setString(7,Transction);
           ps.setString(8,Pyem_mode);
           ps.setString(9,Check);
           ps.setString(10,month_name);
           ps.setString(11,month_year);
          
            
           ps.addBatch();
           
           tot=tot+Float.parseFloat(paid_amount[i]);
                    i++;
             
          }
           sts=ps.executeBatch().length>0;
            System.out.println("===>"+sts);
           if(sts){
               
//               boolean newSts=insertBalanceByEMpPayment(emp_id[0],tot,company_code,user);
               if(sts){con.commit();
               sts=true;}else{sts=false;}
               
            
               
           }
            
        }catch(Exception e){
            System.out.println("----->"+e);
        }
         
        
       return sts;
    }  
      
//  for slip 
   
   
   public  ArrayList<HashMap<String,String>> Slip_data(String Emp_id)throws SQLException, IOException{
      ArrayList<HashMap<String,String>>al=new ArrayList<>();
      try{
      con=new DBCon().getConnection();
      
      String Sql="select isnull(emp_id,'')as emp_id,\n" +
"isnull(month,'')as month_name,isnull(department_name,'')as dep_name,"
              + "isnull(slip_no,'')as slip_no from  staff_salary_payment ssp left join \n" +
"hospital_staff hs on hs.staff_id=ssp.emp_id left join hosiptal_department hd on hd.department_code=hs.depertament where ssp.emp_id='"+Emp_id+"'";
        
      ps=con.prepareStatement(Sql);
          System.out.println("====>stff"+Sql);
      rs=ps.executeQuery();
      while(rs.next()){
          HashMap<String,String> Map=new HashMap();
          
          Map.put("emp_id",rs.getString("emp_id"));
          Map.put("month_name",rs.getString("month_name"));
          Map.put("dep_name",rs.getString("dep_name"));
          Map.put("slip_no",rs.getString("slip_no"));
          
          al.add(Map);
      }

         
     } catch( Exception ex)
        {
             Logger.getLogger(WMAS0001_Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
     return al;
 
}
   
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package HR;

import Master.WMAS0001_SERV;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Tarun
 */
@WebServlet(name = "WHR0001_SERV", urlPatterns = {"/WHR0001_SERV"})
public class WHR0001_SERV extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
         PrintWriter out = response.getWriter();
          String val=request.getParameter("value");
           HttpSession session=request.getSession();
          WHR0001_Dao st=new WHR0001_Dao();
         try{
             
             
           if(val.equals("1")){
               
               String Hospital_code=request.getParameter("hospital_code");
                    ArrayList<HashMap<String,String>> data=st.get_satff_data(Hospital_code);
                    int i=0;
                    for(HashMap<String,String> cd:data){
                        i++;    
                        out.println(" <tr>");
                         out.println("<td><input type='checkbox' checked id='chk_"+i+"' onclick='enable_id("+i+")'></td>");
                        out.println("<td contenteditable=\"true\">"+cd.get("name")+"<input type=hidden name='staff_id'  value='"+cd.get("staff_id")+"' id='id_"+i+"'></td>");
                        out.println("<td contenteditable=\"true\">"+cd.get("position")+"<input type='hidden' value='"+cd.get("depertement")+"' id='dep_"+i+"'></td>");
                        out.println("<td contenteditable=\"true\"><span class='badge bg-success'>"+cd.get("current_shift")+"</span></td>");
                        out.println("<td contenteditable=\"true\"><select name='shift'  id='shift_"+i+"'><option value='Day'>Day</option><option value='Night'>Night</option></select></td>");
                        out.println("<td contenteditable=\"true\"><select name='Duty'><option value='ICU'>ICU</option> <option value='General'>General</option></select></td>");
                        out.println("<td ><button class='btn btn-sm btn-primary' type='button' data-toggle=\"modal\" data-target=\"#myModal\" onclick='getdates("+i+")' >Assign</button></td>");
                        out.println("</tr>");

                    }
                }  
             
             
             
             if(val.equals("2")){
                    boolean sts=false;
                    
                  
                   
                    String[] staff_id=request.getParameterValues("staff_id");
                   // System.out.println("====stafff"+staff_id);
                    String[] shift=request.getParameterValues("shift");
                     //System.out.println("====stafff"+shift);
                    
//                    sts=st.assign_shift(staff_id,shift);
                   
                     HashMap<String ,String> altMap=new HashMap<>();
                      altMap.put("Titel", "Shift Assigned");
                      altMap.put("Alt_Msg", "Some Error !!");
                      altMap.put("Alt_Type", "danger");
                   
                    if(sts){
                        {altMap.put("Alt_Msg", "Shift Assigned Successfully");
                      altMap.put("Alt_Type", "success");
                            }
                session.setAttribute("Alt_Data",altMap);
                response.sendRedirect("Main/HR/WHR0001.jsp");
                }
                
                }
             
             
//             for attandence data

if(val.equals("3")){
               
                     String Hospital_code=request.getParameter("hospital_code");
                    ArrayList<HashMap<String,String>> data=st.get_satff_att_data(Hospital_code);
                    int i=0;
                    for(HashMap<String,String> cd:data){
                        i++;    
                        out.println(" <tr>");
                        if(cd.get("att_sts").equals("Not Marked") && !cd.get("shit").equals("Not Assigned/Please Assign Shift")){
                      out.println("<td><input type='checkbox' id='chk_"+i+"' name='chk' onclick='enable("+i+")' ></td>");
                 }
                        
                        
                        
                        else{
                      out.println("<td><input type='checkbox' id='chk_"+i+"' name='' onclick='enable("+i+")' disabled ></td>");
                 }
                        out.println("<td contenteditable=\"true\">"+cd.get("Depertament")+"</td>");
                      
                        out.println("<td contenteditable=\"true\">"+cd.get("name")+"<input type=hidden name='emp_id'  disabled value='"+cd.get("staff_id")+"' id='emp_id_"+i+"'></td>");
                        
                        if(cd.get("att_sts").equals("1")){
                        out.println("<td contenteditable=\"true\"><span class='badge bg-success'>Present</span></td>");
                        }if(cd.get("att_sts").equals("0")){
                            out.println("<td contenteditable=\"true\"><span class='badge bg-danger'>Absent</span></td>");
                        }
                        if(cd.get("att_sts").equals("0.5")){
                            out.println("<td contenteditable=\"true\"><span class='badge bg-warning'>Leave</span></td>");
                        }else{
                            out.println("<td contenteditable=\"true\"><span class='badge bg-danger'>Not Marked</span></td>");
                        }
                         
                        out.println("<td contenteditable=\"true\">"+cd.get("postion")+"</td>");
                   
                          out.println("<td contenteditable=\"true\"><span class='badge bg-warning'>"+cd.get("shit")+"</span></td>");
                        out.println("<td contenteditable=\"true\"><select name='att_sts'  id='sts_"+i+"'  disabled><option value='1'>Present</option><option value='0'>Absent</option><option value='0.5'>Half</option>"
                                + "<option>Leave</option><option>None</option></select></td>");
                        
                       
                        
                        out.println("<td contenteditable=\"true\"><input type='text' name='remark'  class='form-control'></td>");
                        
                         out.println("<td><button class='btn btn-sm btn-primary' type='button' data-toggle=\"modal\" data-target=\"#myModal\" onclick='getatt_emp_part("+i+")')' >Track</button><input type='hidden' value='"+cd.get("staff_id")+"' id='emp_"+i+"'></td>");
                        out.println("</tr>");
                       
                       
                     

                    }
                } 
             

      
//      for saving the atteendence 

 if(val.equals("4")){
   
      
               
               String [] emp_id=request.getParameterValues("emp_id");
               System.out.println("wmpi_id"+emp_id[0]);
             
               String[] p_sts=request.getParameterValues("att_sts");
           
               String user=request.getParameter("user");
               String company_code=request.getParameter("company_code");
               String att_dat=request.getParameter("date");
               String[] remark=request.getParameterValues("remark");
               
             
               
              boolean sts=false;
               
               sts=st.SaveAtt(emp_id,p_sts,user,company_code,att_dat,remark);

                             HashMap<String ,String> altMap=new HashMap<>();
                      altMap.put("Titel", "Attendence Marked ");
                      altMap.put("Alt_Msg", "Some Error !!");
                      altMap.put("Alt_Type", "danger");
                   
                    if(sts){
                        {altMap.put("Alt_Msg","Attendence Marked Successfully");
                      altMap.put("Alt_Type", "success");
                            }
                session.setAttribute("Alt_Data",altMap);
                response.sendRedirect("Main/HR/WHR0002.jsp");
                }
               
           }
 
 
// for salray
 
 if(val.equals("5")){
               
    try{
               String  code=request.getParameter("reg_id");
                
                
                ArrayList<HashMap<String,String>> data=st.getMonthData(code);
                 int i=0;
                         
                 for(HashMap<String,String> al: data){
                     i++;
                   out.println("<tr>");
                   if(al.get("pending_salary").equals("0.00")){
                       out.println("<td><input type='checkbox' id='chk_"+i+"'  class='myck'  name='chk' onclick='varpay("+i+")' value=\""+i+"\" disabled></td>");
                   }else{
                       out.println("<td><input type='checkbox' id='chk_"+i+"'  class='myck'  name='chk' onclick='varpay("+i+")' value=\""+i+"\" ></td>");
                   }
                  
                   out.println("<td><img src=\"../../Staff/images/"+al.get("empid").replace('/', '-')+".jpg\" class=\"img-thumbnail\" onerror=\"this.onerror=null; this.src='../Design/avatr.jpg'\" alt=\"\"  style='height:50px;width:50px' ></td>");
                   out.println("<td><span class='badge bg-success'>"+al.get("department")+"</span></td>");
                   out.println("<td>"+al.get("MonthName")+"<input type='hidden' value='"+al.get("saveCode")+"'  name='month' id='month_"+i+"' disabled><input type='hidden' value='"+al.get("empid")+"'  name='empid' id='emp_id_"+i+"' disabled></td>");
                   out.println("<td>"+al.get("salary")+"<input type='hidden' value='"+al.get("salary")+"'  name='base_salary' id='base_salary_"+i+"' disabled></td>");
                   out.println("<td>"+al.get("tot_this_Month_Attd")+"<input type='hidden' value='"+al.get("MonthName")+"'  name='month_name' id='month_name_"+i+"' disabled>"
                           + "<input type='hidden' value='"+al.get("MonthYear")+"'  name='year_name' id='year_name_"+i+"' disabled></td>");
                   out.println("<td><span class='badge bg-warning'>"+al.get("day_shift")+"</span> / <span class='badge bg-success'>"+al.get("tot_day_p")+"</span></td>");
                   out.println("<td><span class='badge bg-warning'>"+al.get("night_shift")+"</span> / <span class='badge bg-success'>"+al.get("tot_night_p")+"</span></td>");
                   out.println("<td><span style='font-weight:700'>&#8377;</span> <span style='font-weight:700'>"+al.get("tot_night_allowance")+"  </span></td>");
                   out.println("<td><span style='font-weight:700' white-space: nowrap>&#8377;</span> <span style='font-weight:700'><span class='badge bg-success'>"+al.get("tot_sal")+" </span> </span></td>");
                   out.println("<td><span style='font-weight:700'>&#8377;</span> <span style='font-weight:700'>"+al.get("tot_sal_month")+"</span></td>");
                   out.println("<td><span style='font-weight:700'>&#8377;</span> <span class='badge bg-danger'> "+al.get("advance")+"</span></td>");
                   out.println("<td><span style='font-weight:700'>&#8377;</span> <span class='badge bg-danger'><span style='font-weight:700'>"+al.get("salary_paid")+"</span></span></td>");
                   out.println("<td><span style='font-weight:700'>&#8377;</span> <span style='font-weight:700'>"+al.get("pending_salary")+"</span><input type='hidden' value='"+al.get("pending_salary")+"'  name='final_salary' id='final_sal"+i+"' disabled></td>");
                   out.println("<td><div class=''><div class=''><input type='text' class='form-control input-sm'  value='"+al.get("pending_salary")+"' name='var_pay' id='var_pay_"+i+"' onkeyup='check("+i+")' disabled></div></div></td>");
                  
                   out.println("<td><button type='button' class='btn btn-sm btn-primary' data-toggle=\"modal\" data-target=\"#exampleModalCenter\" onclick='advance_pay("+i+")'>Advance</button><button type='button' class='btn btn-sm btn-success' onclick='show_emp_sal_det("+i+")'>Details</button><input type='hidden' value='"+al.get("empid")+"' id='emp_id_det_"+i+"'></td>");
                   
                   out.println("</tr>");
                   
                    
                }
                 
                 
//                 if(data.size()>0)
//                 {//System.out.println("data.get(0).get(\"totBalance\")data.get(0).get(\"totBalance\") "+data.get(0).get("totBalance"));
//                 out.println("<h6>Your Blalance : <span class='badge bg-info'>"+data.get(0).get("wallet_balance")+"<input type='hidden' id='totBalance' name='totBalance' value='"+data.get(0).get("wallet_balance")+"'></span><h6>");
//                
//                 }
           }
           
            
           
        catch(Exception e){
               System.out.println("=======>"+e);
        }
}
             
             
             
     
      
      
      
      
      
//      for attendance view 


if(val.equals("6")){
               try{
               
                 String id=request.getParameter("id");
                
                ArrayList<HashMap<String,String>> data=st.Particualr_emp(id);
                 int i=0;

               
                 for(HashMap<String,String> al: data){
                     i++;
                out.println("<tr>");  
                out.println("<td>"+i+"</td>");
               
                out.println("<td>"+al.get("month")+"</td>");
                out.println("<td>"+al.get("date_name")+"</td>");
                out.println("<td>"+al.get("all_date")+"</td>");
                if(al.get("staff_sts").equals("Present")){
                    out.println("<td><span class=\"badge badge-success\" >Present</span></td>");
                }
                else if(al.get("staff_sts").equals("Abesnt")){
                    out.println("<td><span class=\"badge badge-danger\">Abesnt</span></td>");
                }
                else if(al.get("staff_sts").equals("Half")){
                    out.println("<td><span class=\"badge badge-warning\">Half</span></td>");
                }
                else{
                    out.println("<td><span class=\"badge badge-info\">Leave</span></td>");
                }
                
                out.println("<td>"+al.get("marked_by")+"</td>");
                
                
                out.println("</tr>");
                
                
                
                           
                   
                }
                  
 
           }

        catch(Exception e){
               System.out.println("=======>"+e);
        }
           
           
    }
      
      
      
      
//  for salary details 

if(val.equals("7")){
               try{
               
                 String id=request.getParameter("id");
                
                ArrayList<HashMap<String,String>> data=st.Particualr_emp_sal_det(id);
                 int i=0;

               
                 for(HashMap<String,String> al: data){
                     i++;
                     
                    out.println("<div class=\"row\">"); 
                    out.println(" <div class=\"col-md-4\">\n" +
"                                            <label>Total Salary</label>\n" +
"                                             <input type=\"text\" class=\"form-control\" id=\"fname\" value='"+al.get("salary")+"' disabled style='color:black' name=\"f_name\">\n" +
"                                            </div>"); 
                   
                    out.println("<div class=\"col-md-4\">\n" +
"                                            <label>MRA</label>\n" +
"                                             <input type=\"text\" class=\"form-control\" id=\"fname\" value='"+al.get("mra")+"' disabled style='color:black'  name=\"f_name\">\n" +
"                                            </div>"); 
                    out.println("<div class=\"col-md-4\">\n" +
"                                            <label>Incentive</label>\n" +
"                                             <input type=\"text\" class=\"form-control\" id=\"fname\" value='' disabled style='color:black' name=\"f_name\">\n" +
"                                            </div>"); 
                    out.println("</div>"); 
                    
                    
                    out.println("<div class=\"row\">"); 
                    out.println("  <div class=\"col-md-4\">\n" +
"                                            <label>PF</label>\n" +
"                                             <input type=\"text\" class=\"form-control\" id=\"fname\" value='"+al.get("pf")+"' disabled style='color:black'  name=\"f_name\">\n" +
"                                            </div>"); 
                    out.println("<div class=\"col-md-4\">\n" +
"                                            <label>TDS</label>\n" +
"                                             <input type=\"text\" class=\"form-control\" id=\"fname\" value='"+al.get("tds")+"' disabled style='color:black' name=\"f_name\">\n" +
"                                            </div>"); 
                    out.println("<div class=\"col-md-4\">\n" +
"                                            <label>ECIS</label>\n" +
"                                             <input type=\"text\" class=\"form-control\" id=\"fname\" value='"+al.get("ecis")+"' disabled style='color:black' name=\"f_name\">\n" +
"                                            </div>"); 
                    out.println("</div>"); 
                    
                    
                    out.println("<div class=\"row\">"); 
                    out.println("<div class=\"col-md-4\">\n" +
"                                            <label>Advance</label>\n" +
"                                             <input type=\"text\" class=\"form-control\" id=\"fname\" value='"+al.get("advance")+"'  disabled style='color:black' name=\"f_name\">\n" +
"                                            </div>"); 
                    out.println("<div class=\"col-md-4\">\n" +
"                                            <label>Taxes</label>\n" +
"                                             <input type=\"text\" class=\"form-control\" id=\"fname\" value='"+al.get("taxes")+"' disabled style='color:black' name=\"f_name\">\n" +
"                                            </div>"); 
                    out.println("<div class=\"col-md-4\">\n" +
"                                            <label>Other</label>\n" +
"                                             <input type=\"text\" class=\"form-control\" id=\"fname\" value='"+al.get("other")+"' disabled style='color:black' name=\"f_name\">\n" +
"                                            </div>"); 
                    out.println("</div>"); 
                    
                }
                  
 
           }

        catch(Exception e){
               System.out.println("=======>"+e);
        }
           
           
    }


//for hr leaves 

if(val.equals("8")){
               
            
                    ArrayList<HashMap<String,String>> data=st.get_leave_date();
                    int i=0;
                    for(HashMap<String,String> cd:data){
                        i++;    
                        out.println(" <tr>");
                       
                        out.println("<td >"+cd.get("staff_id")+"<input type=hidden name='staff_id'  value='"+cd.get("leave_id")+"' id='id_"+i+"'></td>");
                        out.println("<td >"+cd.get("staff_name")+"</td>");
                        out.println("<td >"+cd.get("leave_from")+"</td>");
                        out.println("<td> "+cd.get("leave_to")+"</td>");
                        out.println("<td>"+cd.get("total_leave_days")+"</td>");
                        out.println("<td >"+cd.get("reason")+"</td>");
                        out.println("<td ></td>");
                        if(cd.get("status").equals("Pending")){
                             out.println("<td ><span class='badge bg-warning'>"+cd.get("status")+"</span></td>");
                        }
                        if(cd.get("status").equals("Canceled")){
                            out.println("<td ><span class='badge bg-danger'>"+cd.get("status")+"</span></td>");
                        }
                        
                        if(cd.get("status").equals("Approved")){
                              out.println("<td ><span class='badge bg-success'>"+cd.get("status")+"</span></td>");
                        }
                       
                        out.println("<td ><div class='col-md-12'><div class='row'><div class='col-md-6'><button class='btn btn-sm btn-success' onclick='leave_approve("+i+")' >Approve</button></div>"
                                + "<div class='col-md-6'><button class='btn btn-sm btn-danger' data-toggle=\"modal\" data-target=\"#exampleModalCenter\">Cancel</button></div></div></div></td>");
                        out.println("</tr>");
                       
                       
                     

                    }
                    out.println("<div class=\"modal fade\" id=\"exampleModalCenter\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"exampleModalCenterTitle\" aria-hidden=\"true\">\n" +
"                              <div class=\"modal-dialog modal-dialog-centered\" role=\"document\">\n" +
"                                 <div class=\"modal-content\">\n" +
"                                    <div class=\"modal-header\">\n" +
"                                       <h5 class=\"modal-title\" id=\"exampleModalCenterTitle\">Name:</h5>\n" +
"                                       <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-label=\"Close\">\n" +
"                                       <span aria-hidden=\"true\">&times;</span>\n" +
"                                       </button>\n" +
"                                    </div>\n" +
"                                    <div class=\"modal-body\">\n" +
"                                    \n" +
"                                     <div class=\"row\">\n" +
"                                         <div class=\"col-md-8\">\n" +
"                                            <label>Reason Of Cancelation</label>\n" +
"                                            <textarea id='Reason_of_cancel'></textarea>\n" +
"                                            </div>\n" +
"                                         </div>\n" +
"                                      \n" +
"                                    </div>\n" +
"                                    <div class=\"modal-footer\">\n" +
"                                        <button type=\"button\" class=\"btn btn-danger\" data-dismiss=\"modal\" onclick=\"leave_cancel("+i+")\">Cancel</button>\n" +
"                                     \n" +
"                                    </div>\n" +
"                                 </div>\n" +
"                              </div>\n" +
"                           </div>");
                }


//for leave approraval

 if(val.equals("9")){

               String user=request.getParameter("user_id");
               String leave_code=request.getParameter("leave_id");
           
             
               
              boolean sts=false;
               
               sts=st.leave_approval(user,leave_code);

                             HashMap<String ,String> altMap=new HashMap<>();
                      altMap.put("Titel", "Leave Approved ");
                      altMap.put("Alt_Msg", "Some Error !!");
                      altMap.put("Alt_Type", "danger");
                   
                    if(sts){
                        {altMap.put("Alt_Msg","Leave Approved Successfully");
                      altMap.put("Alt_Type", "success");
                            }
                session.setAttribute("Alt_Data",altMap);
                response.sendRedirect("Main/HR/WHR0005.jsp");
                }
               
           }
             
 
 
 
 
 if(val.equals("10")){

               String user=request.getParameter("user_id");
               String leave_code=request.getParameter("leave_id");
               String Reason_of_cancel=request.getParameter("Reason_of_cancel");
           
             
               
              boolean sts=false;
               
               sts=st.leave_cancel(user,leave_code,Reason_of_cancel);

                             HashMap<String ,String> altMap=new HashMap<>();
                      altMap.put("Titel", "Leave Approved ");
                      altMap.put("Alt_Msg", "Some Error !!");
                      altMap.put("Alt_Type", "danger");
                   
                    if(sts){
                        {altMap.put("Alt_Msg","Leave Approved Successfully");
                      altMap.put("Alt_Type", "success");
                            }
                session.setAttribute("Alt_Data",altMap);
                response.sendRedirect("Main/HR/WHR0005.jsp");
                }
               
           }
 
 
 
// for geeting dates b/w the dates
if(val.equals("11")){
               
               String date_from=request.getParameter("date_from");
               String date_to=request.getParameter("Date_too");
               String satff_id=request.getParameter("satff_id");
               
                    ArrayList<HashMap<String,String>> data=st.get_dates_bw(date_from,date_to,satff_id);
                    
                 
                    int i=0;
                    for(HashMap<String,String> cd:data){
                        i++;    
                        out.println(" <tr>");
                          out.println("<td>"+i+"</td>");
                        out.println("<td>"+cd.get("dates")+"<input type='hidden' value='"+cd.get("dates")+"' name='date_name'></td>");
                        out.println("<td>"+cd.get("datename")+"</td>");
                         
                        
                        if(cd.get("current_shift").trim().equals("Day")){
                             out.println("<td ><select class='form-control' name='shift_name'><option ></option ><option selected>Day</option><option>Night</option></select></td>");
                        }if(cd.get("current_shift").trim().equals("Night")){
                              out.println("<td ><select class='form-control' name='shift_name'><option ></option ><option>Day</option><option selected>Night</option></select></td>");
                        }if(cd.get("current_shift").trim().equals("Not Assigned")){
                        out.println("<td ><select class='form-control' name='shift_name'><option ></option><option>Day</option><option>Night</option></select></td>");
                        }
                        
//                        out.println("<td><select class='form-control' name='room_no'><option></option>"+st.getdata("5","")+"</select></td>");
                        out.println("</tr>");

                    }   
                }  




//for saving shift 


if(val.equals("12")){
                    boolean sts=false;
                    
                  
                   
                    String staff_id=request.getParameter("emp_id");
                  
                    String[] dates=request.getParameterValues("date_name");
                    String [] shift=request.getParameterValues("shift_name");
//                    String [] room_no=request.getParameterValues("room_no");
               
                    
                    sts=st.assign_shift(staff_id,dates,shift);
                   
                     HashMap<String ,String> altMap=new HashMap<>();
                      altMap.put("Titel", "Shift Assigned");
                      altMap.put("Alt_Msg", "Some Error !!");
                      altMap.put("Alt_Type", "danger");
                   
                    if(sts){
                        {altMap.put("Alt_Msg", "Shift Assigned Successfully");
                      altMap.put("Alt_Type", "success");
                            }
                session.setAttribute("Alt_Data",altMap);
                response.sendRedirect("Main/HR/WHR0001.jsp");
                }
                
                }



//for back date attandance

 if(val.equals("13")){
   
      
             
               String emp_id=request.getParameter("emp_id");
               String p_sts=request.getParameter("status");
                String date=request.getParameter("date");
               String user=request.getParameter("created_by");
               
             
               
              String sts="";
               
               sts=st.Save_back_Att(emp_id,p_sts,date,user);
               
               
            
               
                             HashMap<String ,String> altMap=new HashMap<>();
                      altMap.put("Titel", sts);
                      altMap.put("Alt_Msg", "Some Error !!");
                      altMap.put("Alt_Type", "danger");
                   
                    if(sts!=""){
                        {altMap.put("Alt_Msg",sts);
                      altMap.put("Alt_Type", "success");
                            }
                session.setAttribute("Alt_Data",altMap);
                response.sendRedirect("Main/HR/WHR0002.jsp");
                }
               
           }
 
 
 
// for salary advance 

if(val.equals("14")){
   
      
             
               String emp_id=request.getParameter("employyee_id");
               String month_code=request.getParameter("month_name_ad");
                String amount=request.getParameter("advance_amount");
               String user=request.getParameter("user");
               
             
               
              String sts="";
               
               sts=st.Save_advance_pay(emp_id,month_code,amount,user);
               
               
            
               
                             HashMap<String ,String> altMap=new HashMap<>();
                      altMap.put("Titel", sts);
                      altMap.put("Alt_Msg", "Some Error !!");
                      altMap.put("Alt_Type", "danger");
                   
                    if(sts!=""){
                        {altMap.put("Alt_Msg",sts);
                      altMap.put("Alt_Type", "success");
                            }
                session.setAttribute("Alt_Data",altMap);
                response.sendRedirect("Main/HR/WHR0003.jsp");
                }
               
           }


//for saving the salary 

if(val.equals("15")){
    
               
            
               String []  base_salary=request.getParameterValues("base_salary");
               String [] paid_amount=request.getParameterValues("var_pay");
               String [] month=request.getParameterValues("month");
               String [] emp_id=request.getParameterValues("empid");
               String user=request.getParameter("user");
               String mode=request.getParameter("mode");
               String Pyem_mode=request.getParameter("Pyem_mode");
               String Transction=request.getParameter("Transction");
               String Check=request.getParameter("Check");
               String company_code=request.getParameter("company_code");
               String month_name=request.getParameter("month_name");
               String month_year=request.getParameter("year_name");
               
               boolean sts=false;
               
               sts=st.SaveSalary(emp_id,base_salary,paid_amount,user,month,mode,Pyem_mode,Transction,Check,company_code,month_name,month_year);
               
                                    HashMap<String ,String> altMap=new HashMap<>();
                      altMap.put("Titel", "Employee Salary Paid");
                      altMap.put("Alt_Msg", "Some Error !!");
                      altMap.put("Alt_Type", "danger");
                   
                    if(sts){
                        {altMap.put("Alt_Msg","Paid");
                      altMap.put("Alt_Type", "success");
                            }
                session.setAttribute("Alt_Data",altMap);
                response.sendRedirect("Main/HR/WHR0003.jsp");
                }
               
           }


if(val.equals("16")){
               
              
               String satff_id=request.getParameter("staff");
               
                    ArrayList<HashMap<String,String>> data=st.Slip_data(satff_id);
                    
                 
                    int i=0;
                    for(HashMap<String,String> cd:data){
                        i++;    
                        out.println(" <tr>");
                          out.println("<td>"+i+"</td>");
                          out.println("<td><img src=\"../../Staff/images/"+cd.get("emp_id").replace('/', '-')+".jpg\" class=\"img-thumbnail\" onerror=\"this.onerror=null; this.src='../../Design/images/avatr.jpg'\" alt=\"\"  style='height:50px;width:50px' ></td>");
                        out.println("<td>"+cd.get("dep_name")+"<input type='hidden' value='"+cd.get("emp_id")+"' name='date_name'></td>");
                        out.println("<td>"+cd.get("month_name")+"<input type='hidden' value='"+cd.get("month_name")+"' name='date_name'></td>");
                        out.println("<td><span class='badge bg-success'>Generated</span></td>");
                        out.println("<td><button type='button' class='btn btn-warning' onclick='window.open(\"../../WSAL_PR?slip_no="+cd.get("slip_no").toString()+"\");'>Print</button></td>");
                       
                        out.println("</tr>");

                    }   
                } 
   

             
 
      
      
      
      
      
      
      
             
         }
         
         catch(Exception Ex){
             Logger.getLogger(WHR0001_SERV.class.getName()).log(Level.SEVERE, null, Ex);
        }
           
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package OPD;

import HR.WHR0001_Dao;
import HR.WHR0001_SERV;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
import notification.NotificationModel;
import notification.NotificationService;

/**
 *
 * @author Tarun
 */
@WebServlet(name = "WOP0001_SERV", urlPatterns = {"/WOP0001_SERV"})
public class WOP0001_SERV extends HttpServlet {

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
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
            /* TODO output your page here. You may use following sample code. */
       String val=request.getParameter("value");
       HttpSession session=request.getSession();
       WOP0001_Dao st=new WOP0001_Dao();
            
          
          try{
          
         if(val.equals("1")){
                    String sts="";
                   
                    
                  
                    //for checking token for double entery 
                    
                    String sessionToken = (String) session.getAttribute("formToken");
                    String requestToken = request.getParameter("formToken");

                    
                    
                   
                    String patient_code=request.getParameter("patient_code");
                    
                    String hospital_code=request.getParameter("hospital_code");
                    String Patient_name=request.getParameter("Patient_name");
                    String Patient_number=request.getParameter("Patient_number");
                    String patient_adhar=request.getParameter("patient_adhar");
                    String Address=request.getParameter("Address");
                    String DOC=request.getParameter("DOC");
                    String user=request.getParameter("user");
                    String Age=request.getParameter("Age");
                    String free = request.getParameter("free");
                    String Sex=request.getParameter("Sex");
                    String doc_fees=request.getParameter("doc_fees");
                    String gender_type=request.getParameter("gender_type");
                    String Refer_Doc=request.getParameter("Refer_Doc");
                    String DOB=request.getParameter("DOB");
                    String doc_charge=request.getParameter("doc_charge");
                    String doc_discount=request.getParameter("doc_discount");
                    String doc_final=request.getParameter("doc_final");
                    
                    String Pay_type=request.getParameter("Pay_type");
                    String upi_name=request.getParameter("upi_name");
                    String trans_id=request.getParameter("trans_id");
                    String bank_name=request.getParameter("bank_name");
                    String Card_no=request.getParameter("Card_no");
                    String checque_no=request.getParameter("checque_no");
                    String Guardian=request.getParameter("Guardian");
                    String garduian_type=request.getParameter("garduian_type");
                    
                    System.out.println("--------"+garduian_type);
                    
                    
                    
                    
                    if (sessionToken != null && sessionToken.equals(requestToken)) {
    session.removeAttribute("formToken"); // use-once token
   sts=st.save_update_opd(patient_code,hospital_code,Patient_name,Patient_number,patient_adhar,Address,DOC,user,Age,free,Sex,doc_fees,gender_type,
                            Refer_Doc,DOB,doc_charge,doc_discount,doc_final,Pay_type,upi_name,trans_id,bank_name,Card_no,checque_no,Guardian,garduian_type);
} 
                     
                    
                   
                   
                     HashMap<String ,String> altMap=new HashMap<>();
                      altMap.put("Titel", "Registerd");
                      altMap.put("Alt_Msg", "Some Error !!");
                      altMap.put("Alt_Type", "danger");
                   
                    if(sts!=""){
                        
                        {altMap.put("Alt_Msg", "Consultation Pending");
                      altMap.put("Alt_Type", "success");
                      
                      
                      //for Notification  
                     String message= "New OPD Created";
                     NotificationModel notificationModel =new NotificationModel();
                     notificationModel.setCode(sts);
                   //  notificationModel.setIsRead(sts);
                     notificationModel.setNotificationType("Action");
                     notificationModel.setNotificationFrom("OPD Created");
                     notificationModel.setNotificationCode("Admit"+sts);
                     notificationModel.setRedirectUrl("../OPD/WOP0001.jsp?patient_id="+sts);
                     notificationModel.setNotificationMessage(message);
                     NotificationService notificationService=new NotificationService();
                     notificationService.sendNotification(user, notificationModel);
                            }
                   
                session.setAttribute("Alt_Data",altMap);
                response.sendRedirect("Main/OPD/WOP0001.jsp");
                }else{
                           {altMap.put("Alt_Msg", "Some Error Please Check");
                      altMap.put("Alt_Type", "error");
                            }
                   
                session.setAttribute("Alt_Data",altMap);
                response.sendRedirect("Main/OPD/WOP0001.jsp");
                    }
                    
                
                }
         
         
         
         
         if(val.equals("2")){
               
             
             String arr[]={request.getParameter("hospital_code"),request.getParameter("opd_date")};
               
                    ArrayList<HashMap<String,String>> data=st.get_total_opd(arr);
                    int i=0;
                    for(HashMap<String,String> cd:data){
                        i++;    
                        out.println(" <tr>");
                         out.println("<td>"+i+"</td>");
                        out.println("<td>"+cd.get("patient_name")+"<input type=hidden name='staff_id'  value='"+cd.get("staff_id")+"' id='id_"+i+"'></td>");
                        out.println("<td>"+cd.get("mobile_number")+"</td>");
                        out.println("<td>"+cd.get("adhar")+"</td>");
                        if(cd.get("opd_sts").equals("Pending")){
                        out.println("<td><span class='badge bg-danger'>"+cd.get("opd_sts")+"</span></td>");
                        }else{
                            out.println("<td><span class='badge bg-success'>Done</span></td>");
                        }
                        out.println("<td>"+cd.get("docname")+"</td>");
                        out.println("<td>"+cd.get("next_visit")+"</td>");
                        out.println("</tr>");
                       
                       
                     

                    }
                }
         
         
         
         
         
         if(val.equals("3")){
               
             
             String arr[]={request.getParameter("hospital_code"),request.getParameter("opd_date")};
               
                    ArrayList<HashMap<String,String>> data=st.get_total_opd(arr);
                    int i=0;
                    for(HashMap<String,String> cd:data){
                        i++;    
                        out.println(" <tr>");
                         out.println("<td>"+i+"</td>");
                        out.println("<td>"+cd.get("patient_name")+"<input type=hidden name='staff_id'  value='"+cd.get("staff_id")+"' id='id_"+i+"'></td>");
                        if(cd.get("opd_sts").equals("Pending")){
                        out.println("<td><span class='badge bg-danger'>"+cd.get("opd_sts")+"</span></td>");
                        }else{
                            out.println("<td><span class='badge bg-success'>Done</span></td>");
                        }
                        
                        out.println("<td>"+cd.get("next_visit")+"</td>");
                        out.println("</tr>");
                       
                       
                     

                    }
                }
         
         
         if(val.equals("4")){
                
                String Diagonisis=request.getParameter("Diagonisis");
                String medicine_pre=request.getParameter("medicine_pre");
                String Test=request.getParameter("Test");
                
                
                int i=0;
                     
                    out.println("<tr>");
                
                    out.println("<td>"+medicine_pre+"</td>");
              
                    out.println("</tr> ");
                   i=i++;
                
                    
                
            }
            
         
         
         
         
         
         
          if(val.equals("5")){
               
             
             String arr[]={request.getParameter("hospital_code"),request.getParameter("opd_date")};
               
                    ArrayList<HashMap<String,String>> data=st.get_total_opd_doctor(arr);
                    int i=0;
                    for(HashMap<String,String> cd:data){
                        i++;    
                        
                        out.println("<span>"+cd.get("patient_name")+"</span>");
           
                    }
                }
         
         
         
         
         if(val.equals("6")){
               
             
             String arr[]={request.getParameter("hospital_code"),request.getParameter("opd_date")};
               
                    ArrayList<HashMap<String,String>> data=st.get_total_opd_doctor(arr);
                    int i=0;
                    for(HashMap<String,String> cd:data){
                        i++;    
                        
                        out.println(cd.get("op_id"));
           
                    }
                }
         
         
         

         
         
         if(val.equals("7")){
                    boolean sts=false;
                    
                  
                   
                    String hospital_code=request.getParameter("hospital_code");
                    String [] opd_medicine=request.getParameterValues("opd_medicine");
                    String  opd_diagonis=request.getParameter("opd_Diagonisis");
                    String  opd_code=request.getParameter("opd_code");
                    String []Dosage =request.getParameterValues("Dosage");
                    String []Medicine_Additional=request.getParameterValues("Medicine_Additional");
                    
                    
                    //for test saving 
                    String [] test_name=request.getParameterValues("Test_name");
                    String []Test_additional=request.getParameterValues("Test_additional");
                    
                   
                    
                    sts=st.save_opd_medicine(hospital_code,opd_medicine,opd_diagonis,opd_code,Dosage,Medicine_Additional,test_name,Test_additional);
                   
                     HashMap<String ,String> altMap=new HashMap<>();
                      altMap.put("Titel", "Docter Consaltation Done ");
                      altMap.put("Alt_Msg", "Some Error !!");
                      altMap.put("Alt_Type", "danger");
                   
                    if(sts){
                        {altMap.put("Alt_Msg", "Consultation Pending");
                      altMap.put("Alt_Type", "success");
                            }
                session.setAttribute("Alt_Data",altMap);
                response.sendRedirect("Main/OPD/WOP0002.jsp");
                }
                
                }
         
         
         if(val.equals("8")){
               
             
             String arr[]={request.getParameter("hospital_code"),request.getParameter("opd_date")};
               
                    ArrayList<HashMap<String,String>> data=st.get_total_opd_doctor(arr);
                    int i=0;
                    for(HashMap<String,String> cd:data){
                        i++;    
                        
                        out.println(cd.get("age"));
           
                    }
                }
         
         
         
         if(val.equals("9")){
               
             
             String arr[]={request.getParameter("hospital_code"),request.getParameter("opd_date"),request.getParameter("doc_code")};
               
                    ArrayList<HashMap<String,String>> data=st.get_total_oprec(arr);
                    int i=0;
                    for(HashMap<String,String> cd:data){
                        i++;    
                        out.println(" <tr>");
                         out.println("<td>"+i+"</td>");
                         
                         if(cd.get("opd_sts").equals("Pending")){
      
                            out.println("<td style='white-space: nowrap'><button class='btn ' title='Print OPD Slip' type='button' onclick='window.open(\"../../OPD_SLIP?patient_id="+cd.get("op_id").toString()+"\");'><i class='fa fa-print' ></i></button>"
                                + "<button type='button' class='btn btn' title='Procedure' data-toggle=\"modal\" data-target=\"#myModal\" onclick='get_procedure_id("+i+");' ><i class='fa fa-stethoscope' style='color: green;  font-size: 20px; /* Icon size */ text-shadow: 3px 3px 5px rgba(0, 0, 0, 0.4);'></i></button>"
                                    + "<button type='button' class='btn ' title='Cancel OPD' onclick=cancel_opd('"+cd.get("op_id").toString()+"');><i class='fa fa-times' style='color: red;  font-size: 20px; /* Icon size */ text-shadow: 3px 3px 5px rgba(0, 0, 0, 0.4);'></i></button>"
                               
                                            +"<button type='button' class='btn ' title='Refund' data-toggle=\"modal\" data-target=\"#myModal_refund\" onclick=Refund_opd('"+i+"');><i class='fa fa-credit-card' style='color: orange;  font-size: 20px; /* Icon size */ text-shadow: 3px 3px 5px rgba(0, 0, 0, 0.4);'></i></button>"
                                            + "<button type='button' class='btn ' title='OPD Done'  onclick=opd_done('"+cd.get("op_id").toString()+"');><i class='fa fa-check' style='color: green;  font-size: 20px; /* Icon size */ text-shadow: 3px 3px 5px rgba(0, 0, 0, 0.4);'></i></button>"
                                                    + "<button type='button' class='btn ' title='OPD Done' data-toggle=\"modal\" data-target=\"#myModal_opd\" onclick=edit_opd('"+cd.get("op_id").toString()+"');><i class='fa fa-edit' style='color: yellow;  font-size: 20px; /* Icon size */ text-shadow: 3px 3px 5px rgba(0, 0, 0, 0.4);'></i></button></td>");
                        }else{
                        out.println("<td><button class='btn ' type='button'  title='Print Slip' onclick='window.open(\"../../OPD_SLIP?patient_id="+cd.get("op_id").toString()+"\");'><i class='fa fa-print' style='color: #D68910;  font-size: 20px; /* Icon size */ text-shadow: 3px 3px 5px rgba(0, 0, 0, 0.4);'></i></button>"
                                + "<button type='button' class='btn '  title='Print Pricripetion' data-toggle=\"modal\" data-target=\"#myModal_Procedure\" onclick='get_precription_id("+i+");' ><i class='fa fa-file' style='color: green;  font-size: 20px; /* Icon size */ text-shadow: 3px 3px 5px rgba(0, 0, 0, 0.4);'></i></button></td>");
                        }
                         
                        out.println("<td>"+cd.get("token_number")+"</td>");
                        out.println("<td>"+cd.get("opd_type")+"<input type='hidden' value='"+cd.get("opd_type")+"' id='opd_type_"+cd.get("op_id")+"'></td>");
                        out.println("<td style='white-space:nowrap'>"+cd.get("patient_name")+"<input type=hidden name='staff_id'  value='"+cd.get("staff_id")+"' id='id_"+i+"'>"
                                + "<input type=hidden name='staff_id'  value='"+cd.get("op_id") + "'  id='id_o_"+i+"'>"
                                        + "<input type='hidden' value='" + cd.get("charge") +"' id='charge_"+i+ "'></td>");
                       
                          if(cd.get("opd_sts").equals("Pending")){
                        out.println("<td><span class='badge bg-danger'>"+cd.get("opd_sts")+"</span>");
                        }
                        else 
                        {
                            out.println("<td><span class='badge bg-success'>OPD Done</span></td>");
                        }
                           out.println("<td>"+cd.get("docname")+"</td>");
                        out.println("<td>"+cd.get("gardian_name")+"</td>");
                        out.println("<td>"+cd.get("mobile_number")+"</td>");
                       
                      
                        
                     
                        out.println("<td>"+cd.get("next_visit")+"</td>");
                        
                        out.println("</tr>");
                       
                       
                     

                    }
                }
         
         
         
         
//         for getting rooms

if(val.equals("10")){
               
             
             String category_id=request.getParameter("category_id");
           
               System.out.println("====>yanha tak aya ge ");
                    ArrayList<String> data=st.getdata("3",category_id);
                    int i=0;
                    for(String cd:data){
                           
                      out.println("<option></option>");
                       out.println(cd);
                       i++;
                     

                    }
                }

if(val.equals("11")){
             
             
             String room_id=request.getParameter("room_id");
               System.out.println("====>yanha tak aya ge ");
                    ArrayList<String> data=st.getdata("4",room_id);
                    int i=0;
                    for(String cd:data){
                           
                    
                       out.println(cd);
                       i++;
                     

                    }
                }


//New added 
if(val.equals("12")){
    
    boolean sts=false;
    
    String  opd_type=request.getParameter("opd_type");
    String  OPD_ID=request.getParameter("opd_id");
     
                sts=st.cancel_opd(OPD_ID,opd_type);
                   
                     
                }
    



   // for procedure 
   
             if (val.equals("13")) {
                String procedure_id = request.getParameter("procedure_id");
                ArrayList<HashMap<String, String>> sale_data = new WOP0001_Dao().get_pro_data(procedure_id);
         //       ArrayList<HashMap<String, String>> it9 = sale_data.get(ArrayList);
         int i=0;
                for(HashMap<String,String> cd:sale_data) {
                    //HashMap<String, String> cd4 = cd.next();
                    out.println("<tr>");
                    out.println("<td>" + cd.get("procedure_name") + "<input type='hidden'  value=" + cd.get("procedure_id") + " name='procedure_id'></td>");
                    out.println("<td>" + cd.get("procedure_rate") + "<input type='hidden' class='total_amt' value='" + cd.get("procedure_rate") + "' name='procedure_rate'></td>");
                    out.println("<td><button class=\"btn btn-danger  btn-sm\"  onClick=\"$(this).closest('tr').remove();final_bill_amount();\"><i class=\"fa fa-minus\"></i></button></td>");
                    out.println("</tr>");
                    i++;
                }
            }
             
             
             
             
             
              //for Precriptoin Details 
              
              if(val.equals("14")){
                  String OPD_ID = request.getParameter("opd_id");
                ArrayList<HashMap<String, String>> sale_data = new WOP0001_Dao().get_prec_data(OPD_ID);
         //       ArrayList<HashMap<String, String>> it9 = sale_data.get(ArrayList);
         int i=0;
         
         
                for(HashMap<String,String> cd:sale_data) {
                    //HashMap<String, String> cd4 = cd.next();
                    out.println("<tr>");
                    out.println("<td> "+cd.get("medicine_name") + "</td>");
                    out.println("<td><span class='badge bg-success'>" + cd.get("dosage") +" </span></td>");
                    out.println("<td>"+cd.get("additional_comments")+"</td>");
                    out.println("</tr>");
                    i++;
                    
                    
                }
                       String dig = sale_data.get(2).get("diagonsis");
                       out.println("<span class='badge bg-success text-align='center' font-size='14'>Diagonisis By Doctor:-"+dig+"</span>");
                      
                  
              }
             
             
              //for opd_procedure_saving
              
               if (val.equals("15")) {
                String[] procedure_id2 = request.getParameterValues("procedure_id");
                String[] procedure_rate = request.getParameterValues("procedure_rate");
                String patient_id = request.getParameter("patient_id");
                String total_amount = request.getParameter("total_amount");
                String discount = request.getParameter("discount");
                String final_amount = request.getParameter("final_amount");
                String collect_value = request.getParameter("collect");
                String return_value = request.getParameter("return");
                String adjust = request.getParameter("adjust");
                String user2 = request.getParameter("user");
                boolean sts3 = st.save_assigned_pro(procedure_id2, procedure_rate, patient_id, total_amount, discount, final_amount, collect_value, return_value, user2, adjust);
                HashMap<String, String> altMap3 = new HashMap<>();
                altMap3.put("Titel", "Procedure Done");
                altMap3.put("Alt_Msg", "Some Error !!");
                altMap3.put("Alt_Type", "danger");
                if (sts3) {
                    altMap3.put("Alt_Msg", "Amount Collected");
                    altMap3.put("Alt_Type", "success");
                    session.setAttribute("Alt_Data", altMap3);
                    response.sendRedirect("Main/OPD/WOP0001.jsp");
                }
            }
             
             
             //for follow up patients 
             
             if(val.equals("16")){
                  String doc = request.getParameter("doc");
                ArrayList<HashMap<String, String>> sale_data = new WOP0001_Dao().get_follow_up_data(doc);
         //       ArrayList<HashMap<String, String>> it9 = sale_data.get(ArrayList);
         int i=0;
         
         
                for(HashMap<String,String> cd:sale_data) {
                    //HashMap<String, String> cd4 = cd.next();
                    out.println("<tr>");
                    out.println("<td> "+i+ "</td>");
                    out.println("<td> "+cd.get("opd_id") + "</td> <input type='hidden' value='"+cd.get("opd_id")+"' id='opd_id_f_"+i+"'>");
                    out.println("<td><span class='badge bg-success'>" + cd.get("patient_name") + "</span><input type='hidden' value='"+cd.get("doc_id")+"' id='doc_id_"+i+"'></td>");
                    out.println("<td>"+cd.get("mobile_number")+"</td>");
                    out.println("<td>"+cd.get("create_date")+"<input type='hidden' value='"+cd.get("create_date")+"'  id='follow_up_date_"+i+"'></td>");
                    out.println("<td>"+cd.get("visit_date")+" <input type='hidden' value='"+cd.get("visit_date")+"' id='visit_"+i+"'></td>");
                    out.println("<td><button type='button' class='btn'  title='Create Follow Up'  onclick=create_follow_up("+i+")><i class='fa fa-edit' style='color: #5499c7;  font-size: 20px; /* Icon size */ text-shadow: 3px 3px 5px rgba(0, 0, 0, 0.4);'></i></td>");
                     
                    out.println("</tr>");
                    i++;
                    
                }
                       
                  
              }
             
             
      //for saving followup 
      
         if(val.equals("17")){
                    boolean sts=false;
                    
                  
                   
                    String Opd_id=request.getParameter("opd_id");
                    String visit_date=request.getParameter("last_visit");
                    String doc_id=request.getParameter("doc_id");
                  
                    
                    
              
                    
                    sts=st.save_follow_up(Opd_id,visit_date,doc_id);
                   
                     HashMap<String ,String> altMap=new HashMap<>();
                      altMap.put("Titel", "Follow OPD Created ");
                      altMap.put("Alt_Msg", "Some Error !!");
                      altMap.put("Alt_Type", "danger");
                   
                    if(sts){
                        {altMap.put("Alt_Msg", "Consultation Pending");
                      altMap.put("Alt_Type", "success");
                            }
                session.setAttribute("Alt_Data",altMap);
                response.sendRedirect("Main/OPD/WOP0002.jsp");
                }
                
                }
         
         
         //for doctor Wise Opd 
         
         if(val.equals("18")){
               
             
             String arr[]={request.getParameter("hospital_code"),request.getParameter("doc_code")};
               
                    ArrayList<HashMap<String,String>> data=st.get_total_opd_doc(arr);
                    int i=0;
                    for(HashMap<String,String> cd:data){
                        i++;    
                        out.println(" <tr>");
                         out.println("<td>"+i+"</td>");
                        out.println("<td>"+cd.get("patient_name")+"<input type=hidden name='staff_id'  value='"+cd.get("staff_id")+"' id='id_"+i+"'></td>");
                        if(cd.get("opd_sts").equals("Pending")){
                        out.println("<td><span class='badge bg-danger'>"+cd.get("opd_sts")+"</span></td>");
                        }else{
                            out.println("<td><span class='badge bg-success'>Done</span></td>");
                        }
                        
                        out.println("<td>"+cd.get("next_visit")+"</td>");
                        out.println("</tr>");
                       
                       
                     

                    }
                }
         
         //for doc Charge 
         
         if(val.equals("19")){
               
             
             String doc_code=request.getParameter("doc_id_code");
               
                    String data=st.get_doc_charge(doc_code);
                        
                       out.println(data);
                  
                    }
         
         
         
         //for opd Refund 
         
          if(val.equals("20")){
                    String sts="";
                    
                 
                    String Opd_id=request.getParameter("OPD_REFUND_Patient");
                    String OPD_AMOUNT=request.getParameter("OPD_AMOUNT");
                    String AMOUNT_REFUNDED=request.getParameter("AMOUNT_REFUNDED");
                    String FINAL_AMOUNT=request.getParameter("FINAL_AMOUNT");
                    String user=request.getParameter("user");
                    String remark=request.getParameter("Remark");
             
                    sts=st.Save_opd_refund(Opd_id,OPD_AMOUNT,AMOUNT_REFUNDED,FINAL_AMOUNT,user,remark);
                   
                     HashMap<String ,String> altMap=new HashMap<>();
                      altMap.put("Titel", "OPD Refunded Successfully");
                      altMap.put("Alt_Msg", sts);
                      altMap.put("Alt_Type", "success");
                   
                    if(sts!=""){
                        {altMap.put("Alt_Msg",sts);
                      altMap.put("Alt_Type", "success");
                            }
                session.setAttribute("Alt_Data",altMap);
                response.sendRedirect("Main/OPD/WOP0001.jsp");
                }
                
                }
          
          //for category charge 
          
          if(val.equals("21")){
               
             
             String category_id=request.getParameter("category_id");
           
               System.out.println("====>yanha tak aya ge ");
                   String data=st.get_cat_charge(category_id);
                 
                       out.println(data);
            
                }
         
          
          
          //for procedure list for patient 
          
          
             if (val.equals("22")) {
                String patient_id = request.getParameter("Patient_id");
                ArrayList<HashMap<String, String>> sale_data = new WOP0001_Dao().get_pro_patient_list(patient_id);
         //       ArrayList<HashMap<String, String>> it9 = sale_data.get(ArrayList);
         int i=0;
                for(HashMap<String,String> cd:sale_data) {
                    //HashMap<String, String> cd4 = cd.next();
                    out.println("<tr>");
                    out.println("<td  style='white-space:nowrap'>" + cd.get("patient_name") + "<input type='hidden'  value=" + cd.get("procedure_id") + " name='procedure_id'></td>");
                    out.println("<td>"+cd.get("patient_name")+"</td>");
                    out.println("<td>"+cd.get("patient_name")+"</td>");
                    out.println("<td style='white-space:nowrap'>"+cd.get("procedures")+"</td>");
                    out.println("<td style='white-space:nowrap'>"+cd.get("rates")+"</td>");
                    out.println("<td>"+cd.get("total_rate")+"</td>");
                    out.println("<td>"+cd.get("discount")+"</td>");
                    out.println("<td>"+cd.get("final_rate")+"</td>");
                    out.println("<td>"+cd.get("ad_opd")+"</td>");
                    out.println("<td>"+cd.get("collect_amount")+"</td>");
                    out.println("<td>"+cd.get("final_rate")+"</td>");
                    out.println("<td><input type='hidden' class='total_amt' value='" + cd.get("procedure_rate") + "' name='procedure_rate'>"
                            + "<button class='btn ' title='Face Sheet' onclick='window.open(\"../../WFACE_SHEET_PRINT?patient_id=" + cd.get("patient_id").toString() + "\");'><i class='fa fa-print' style='color: #5499c7;  font-size: 20px; /* Icon size */ text-shadow: 3px 3px 5px rgba(0, 0, 0, 0.4);'></i></button></td>");
                    out.println("</tr>");
                    i++;
                }
            }
             
             
             //for opd done 
             
             if(val.equals("23")){
    
    boolean sts=false;
    
     String  OPD_ID=request.getParameter("opd_id");
      String  opd_type=request.getParameter("opd_type");
     
                sts=st.opd_done(OPD_ID,opd_type);
                   
                   
                    
             }
             
             
    //for opd_update_mode
    
    if(val.equals("24")){
            String  OPD_ID=request.getParameter("opd_id");
            ArrayList<HashMap<String, String>> sale_data = new WOP0001_Dao().get_opd_patient(OPD_ID);
           for(HashMap<String,String> cd:sale_data) {
               out.println("<div class=\"row\">\n" +
"      \n" +
"                                      <div class=\"col-md-2\">\n" +
"                                     <div class=\"form-group\">\n" +
"                                            <label>Doctor:</label>\n" +
"                                            <select class=\"Select2 form-control\" id=\"doc_id_code\" name=\"DOC\" onchange=\"get_doc_fees()\" required=\"\"\">\n" +
"                                              <option>..</option>\n" +
"                                                <%\n" +
"                                                    WOP0001_Dao doc = new WOP0001_Dao();\n" +
"                                                    ArrayList<String> data = doc.getdata(\"1\", Hospital_code);\n" +
"                                                    for (String cd : data) {\n" +
"                                                        out.println(cd);\n" +
"                                                    }\n" +
"                                                %>\n" +
"                                               \n" +
"                                            </select>\n" +
"                                        </div>\n" +
"                                              </div>\n" +
"                                                \n" +
"                                                <div class=\"form-group col-md-2\">\n" +
"                                                <label for=\"fname\">Ref Doc:</label>\n" +
"                                                <input type=\"text\" class=\"form-control\" id=\"Refer_Doc\" placeholder=\"Ref Doc\" name=\"Refer_Doc\" required=\"\" value=\"Self\">\n" +
"                                            </div>\n" +
"      \n" +
"                  \n" +
"                         \n" +
"<div calss=\"col-md-2\">\n" +
"<label for=\"comboInput\">Patient Name</label>\n" +
"  <div class=\"input-group\" name=\"\" onchange=\"gender_change_opd()\">\n" +
"    <select id=\"type_gender\"  name=\"gender_type\" onchange=\"\">\n" +
"      <option></option>\n" +
"                                            <option value=\"Mr\">Mr</option>\n" +
"                                            <option>Mrs</option>\n" +
"                                            <option>Miss</option>\n" +
"                                            <option>Baby</option>\n" +
"                                            <option>Mst</option>\n" +
"                                            <option>Ms</option>\n" +
"    </select>\n" +
"    <input type=\"text\" class=\"form-control\" id=\"fname\" placeholder=\"Name\" name=\"Patient_name\" value='"+cd.get("patient_name")+"' required=\"\">\n" +
"  </div>\n" +
"          </div>                                      \n" +
"                                                \n" +
" \n" +
"                                           <div class=\"form-group col-md-3\" >\n" +
"                                                <label for=\"add1\">DOB:</label>\n" +
"                                                <input type=\"date\" class=\"form-control\" id=\"DOB\" placeholder=\"DOB\" name=\"DOB\" value='"+cd.get("dob")+"' onchange=\"calculateAge()\">\n" +
"                                            </div>\n" +
"                         \n" +
"                                            <div class=\"form-group col-md-2\" >\n" +
"                                                <label for=\"add1\">Age:</label>\n" +
"                                                <input type=\"number\" class=\"form-control\" id=\"age\" placeholder=\"Age\" name=\"Age\"  value='"+cd.get("age")+"'   required=\"\" min=\"0\">\n" +
"                                            </div>\n" +
"                                        \n" +
"                                                <div class=\"form-group col-md-3\">\n" +
"                                                <label for=\"mobno\">Guardian Name</label>\n" +
"                                                <div class=\"input-group\" name=\"\" >\n" +
"    <select  name=\"garduian_type\" onchange=\"\">\n" +
"      <option></option>\n" +
"                                          <option>W/O</option>\n" +
"                                            <option>S/O</option>\n" +
"                                            <option>D/O</option>\n" +
"                                            <option>M/O</option>\n" +
"                                            <option>B/O</option>\n" +
"                                            <option>H/O</option>\n" +
"    </select>\n" +
"                                                <input type=\"text\" class=\"form-control\" id=\"Guardian\"  value='"+cd.get("gardian_name")+"'     placeholder=\"Guardian\" name=\"Guardian\" required=\"\">\n" +
"                                            </div>\n" +
"                                                </div>\n" +
"                                   \n" +
"                                            <div class=\"form-group col-md-3\">\n" +
"                                                <label for=\"add1\">Number:</label>\n" +
"                                                <input type=\"text\" class=\"form-control\" id=\"Patient_number\" value='"+cd.get("mobile_number")+"'      placeholder=\"Number\" name=\"Patient_number\" required=\"\">\n" +
"                                        \n" +
"                                            </div>\n" +
"\n" +
"                                         \n" +
"\n" +
"                                            <div class=\"form-group col-md-3\">\n" +
"                                                <label for=\"mobno\">Address</label>\n" +
"                                                <input type=\"text\" class=\"form-control\" id=\"mobno\"   value='"+cd.get("address")+"'   placeholder=\"Address\" name=\"Address\" required=\"\">\n" +
"                                            </div>\n" +
"                                   \n" +
"                                        </div>");
               
               out.println("</div>");
           }
               
    }
    
    
    
    //for opd Payment 
    
      if (val.equals("25")) {
                
         String filter[]={request.getParameter("date"),request.getParameter("pay_type"),request.getParameter("doc"),request.getParameter("date_to")};
          
                ArrayList<HashMap<String, String>> sale_data = new WOP0001_Dao().get_opd_payment_sheet(filter);
         //       ArrayList<HashMap<String, String>> it9 = sale_data.get(ArrayList);
         int i=0;
              float opd_total=0;
              float total_discount=0;
              float final_amt=0;
                for(HashMap<String,String> cd:sale_data) {
                    //HashMap<String, String> cd4 = cd.next();
                    
                    opd_total+=Float.parseFloat(cd.get("doctor_charge"));
                    total_discount+=Float.parseFloat(cd.get("discount"));
                    final_amt+=Float.parseFloat(cd.get("final_amount"));
                    out.println("<tr>");
                    out.println("<td>"+i+"</td>");
                    out.println("<td  style='white-space:nowrap'>" + cd.get("doctor_name") + "</td>");
                    out.println("<td>"+cd.get("opd_id")+"</td>");
                    out.println("<td>"+cd.get("patient_name")+"</td>");
                    out.println("<td style='white-space:nowrap'>"+cd.get("doctor_charge")+"</td>");
                    out.println("<td style='white-space:nowrap'>"+cd.get("discount")+"</td>");
                    out.println("<td>"+cd.get("final_amount")+"</td>");
                    out.println("<td>"+cd.get("refund_amount")+"<button class='btn ' type='button'  title='Print Slip' onclick='window.open(\"../../OPD_Refund?patient_id="+cd.get("opd_id").toString()+"\");'><i class='fa fa-print' style='color: #D68910;  font-size: 20px; /* Icon size */ text-shadow: 3px 3px 5px rgba(0, 0, 0, 0.4);'></i></button></td>");
                    out.println("<td>"+cd.get("paytype")+"</td>");
                    out.println("<td style='white-space:nowrap'>"+cd.get("transaction_info")+"</td>");
                     out.println("<td style='white-space:nowrap'> "+cd.get("create_date")+"</td>");
                   
                    ++i;
                }
              out.println("<tr>");
                    out.println("<td>"+i+"</td>");
                    out.println("<td  style='white-space:nowrap'></td>");
                    out.println("<td></td>");
                    out.println("<td></td>");
                    out.println("<td style='white-space:nowrap'>"+opd_total+"</td>");
                    out.println("<td style='white-space:nowrap'>"+total_discount+"</td>");
                    out.println("<td>"+final_amt+"</td>");
                    out.println("<td></td>");
                    out.println("<td></td>");
                    out.println("<td></td>");
                     out.println("<td></td>");
            }
             
           
         
         
         
            
             
             
             
             
         
            }catch(Exception Ex){
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(WOP0001_SERV.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(WOP0001_SERV.class.getName()).log(Level.SEVERE, null, ex);
        }
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

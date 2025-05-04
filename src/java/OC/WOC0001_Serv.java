/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package OC;

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
 * @author Lenovo
 */
@WebServlet(name = "WOC0001_Serv", urlPatterns = {"/WOC0001_Serv"})
public class WOC0001_Serv extends HttpServlet {

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
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
       String status;
        response.setContentType("text/html;charset=UTF-8");
        String val = request.getParameter("value");
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        Throwable th = null;
        try {
            if (val.equals("1")) {
                String patient_id = request.getParameter("patient_id");
                String category_id=request.getParameter("category_id");
                ArrayList<HashMap<String, String>> data = new WOC0001_Dao().get_oc_data(category_id);
                int i = 0;
                    
                String type=new WOC0001_Dao().Patient_type(patient_id);
                
                System.out.println("------Patient Type "+type);
               
                
                for (HashMap<String, String> cd : data)  {
                    i++;
                    out.println(" <tr>");
                    out.println("<td>" + i + "<input type=\"checkbox\" name='oc_checkBox' id='oc_checkBox_" + i + "'  value='" + i + "'  onchange=\"checkboxFN('" + i + "');\" ><input type=\"hidden\" name='oc_code_" + i + "' id='oc_code_" + i + "'  value='" + cd.get("charge_id") + "' ></td>");
                    out.println("<td ><b>" + cd.get("charge_name") + "</b></td>");
                  
                    if(cd.get("charge_amount").trim().equals("0.0") && type.trim().equalsIgnoreCase("Cash")){
                          out.println("<td >" + cd.get("charge_amount") + 
                   "<input type='number' name='oc_amt_" + i + "' id='oc_amt_" + i + "'  value='" + cd.get("charge_amount") + "'></td>");
                    }else{
                        out.println("<td >" + cd.get("charge_amount") + 
                   "<input type=\"hidden\" name='oc_amt_" + i + "' id='oc_amt_" + i + "'  value='" + cd.get("charge_amount") + "'></td>");
                   
                    }
             
                    out.println("<td ><input type='number' name='qty_" + i + "' id='qty_" + i + "' value='0' min='0' disabled required ></td>"); 
                    
                    if(type.trim().equalsIgnoreCase("Cash")){
                        out.println("<td style='display:none'><input type='number' name='submit_amt_" + i + "' id='submit_amt_" + i + "' value='0' min='0' disabled required ></td>");
                    }
                    
                    else{
                        out.println("<td><input type='number' name='submit_amt_" + i + "' id='submit_amt_" + i + "' value='0' min='0' disabled required ></td>");
                    }
                    
                   
                    
                    
                    
                    
                    out.println("</tr>");
                }
            }
            if (val.equals("2")) {
                boolean sts = new WOC0001_Dao().Save_OC_payment(request);
                HashMap<String, String> altMap = new HashMap<>();
                altMap.put("Titel", "Other Charge Payment ");
                altMap.put("Alt_Msg", "Some Error !!");
                altMap.put("Alt_Type", "danger");
                if (sts) {
                    altMap.put("Alt_Msg", "Payment  Successfully");
                    altMap.put("Alt_Type", "success");
                    session.setAttribute("Alt_Data", altMap);
                    response.sendRedirect("Main/OC/WOC0001.jsp");
                }
            }
            if (val.equals("3")) {
                String patient_id2 = request.getParameter("patient_id");
                ArrayList<HashMap<String, String>> data2 = new WOC0001_Dao().get_oc_data2(patient_id2);
                int i2 = 0;
                
                for (HashMap<String, String> cd2 : data2) {
                
                    i2++;
                    if (Float.parseFloat(cd2.get("oc_amt_left")) <= 0.0f) {
                        status = "disabled";
                    } else {
                        status = "";
                    }
                    out.println(" <tr>");
                    out.println("<td>" + i2 + "<input type=\"checkbox\" name='oc_checkBox' id='oc_checkBox_" + i2 + "'  value='" + i2 + "' " + status + " onchange=\"checkboxFN2('" + i2 + "');\" ><input type=\"hidden\" name='add_oc_code_" + i2 + "' id='add_oc_code_" + i2 + "'  value='" + cd2.get("add_oc_code") + "' ><input type=\"hidden\" name='oc_code_" + i2 + "_" + cd2.get("add_oc_code") + "' id='oc_code_" + i2 + "_" + cd2.get("add_oc_code") + "'  value='" + cd2.get("oc_code") + "' ></td>");
                    out.println("<td >" + cd2.get("charge_name") + "(" + cd2.get("oc_code") + ")<input type='hidden' value='"+cd2.get("oc_code")+"' id='Charge_id_"+i2+"'></td>");
                    out.println("<td >" + cd2.get("oc_amt_pay") + "<input type=\"hidden\" name='oc_amt_pay_" + i2 + "_" + cd2.get("add_oc_code") + "' id='oc_amt_pay_" + i2 + "_" + cd2.get("add_oc_code") + "'  value='" + cd2.get("oc_amt_pay") + "'></td>");
                    out.println("<td >" + cd2.get("oc_qty") + "<input type='hidden' name='oc_qty_" + i2 + "_" + cd2.get("add_oc_code") + "' id='oc_qty_" + i2 + "_" + cd2.get("add_oc_code") + "' value='" + cd2.get("oc_qty") + "' readOnly></td>");
                    out.println("<td >" + cd2.get("oc_final_amt") + "<input type='hidden' name='oc_final_amt_" + i2 + "_" + cd2.get("add_oc_code") + "' id='oc_final_amt_" + i2 + "_" + cd2.get("add_oc_code") + "' value='" + cd2.get("oc_final_amt") + "' readOnly></td>");
                    out.println("<td >" + cd2.get("oc_amt_paid") + "<input type='hidden' name='oc_amt_paid_" + i2 + "_" + cd2.get("add_oc_code") + "' id='oc_amt_paid_" + i2 + "_" + cd2.get("add_oc_code") + "' value='" + cd2.get("oc_amt_paid_") + "' readOnly></td>");
                    out.println("<td >" + cd2.get("oc_amt_left") + "<input type='hidden' name='oc_amt_left_" + i2 + "_" + cd2.get("add_oc_code") + "' id='oc_amt_left_" + i2 + "_" + cd2.get("add_oc_code") + "' value='" + cd2.get("oc_amt_left") + "' readOnly></td>");
                    out.println("<td ><input type='number' name='submit_amt_" + i2 + "_" + cd2.get("add_oc_code") + "' id='submit_amt_" + i2 + "_" + cd2.get("add_oc_code") + "' value='0' min='1' disabled required ></td>");
                    out.println("<td><button type='button'  class='btn' title='Remove Charge'  onclick='remove_charge("+i2+")'><i class='fa fa-times'></i></button></td>");
                    out.println("</tr>"); 
                }
            }
            if (val.equals("4")) {
                boolean sts2 = new WOC0001_Dao().update_OC_payment(request);
                HashMap<String, String> altMap2 = new HashMap<>();
                altMap2.put("Titel", "OTher Charge Payment ");
                altMap2.put("Alt_Msg", "Some Error !!");
                altMap2.put("Alt_Type", "danger");
                if (sts2) {
                    altMap2.put("Alt_Msg", "Payment  Successfully");
                    altMap2.put("Alt_Type", "success");
                    session.setAttribute("Alt_Data", altMap2);
                    response.sendRedirect("Main/OC/WOC0002.jsp");
                }
            }
            
          //for removing the other charge
          
           if (val.equals("5")) {
               
               String patient_id=request.getParameter("patient_id");
               String userid=request.getParameter("User_id");
               String charge_code=request.getParameter("charge_id");
               
               
                boolean sts2 = new WOC0001_Dao().Remove_other_charge(patient_id,userid,charge_code);
                HashMap<String, String> altMap2 = new HashMap<>();
                altMap2.put("Titel", "OTher Charge Removed SuccessFully ");
                altMap2.put("Alt_Msg", "Some Error !!");
                altMap2.put("Alt_Type", "danger");
                if (sts2) {
                    altMap2.put("Alt_Msg", "Payment  Successfully");
                    altMap2.put("Alt_Type", "success");
                    session.setAttribute("Alt_Data", altMap2);
                    response.sendRedirect("Main/OC/WOC0002.jsp");
                }
            }
            
            
            
            
      //for ayushmaan charges 

 if (val.equals("6")) {
                
                ArrayList<HashMap<String, String>> data2 = new WOC0001_Dao().get_all_ayushmaan_charges();
                int i = 0;
                
                for (HashMap<String, String> cd : data2) {
                
                    i++;
                      if (Float.parseFloat(cd.get("procedure_price")) <= 0.0f) {
                        status = "disabled";
                    } else {
                        status = "";
                    }
                    out.println(" <tr>");
                    
                    out.println("<td><input type=\"checkbox\" name='oc_checkBox' id='oc_checkBox_" + i + "'  value='" + i + "'  onchange=\"checkboxFN('" + i + "');\" ><input type=\"hidden\" name='oc_code_" + i + "' id='oc_code_" + i + "'  value='" + cd.get("Procedure_code") + "' ></td>");
                    out.println("<td ><input type='number' name='qty_" + i + "' id='qty_" + i + "' value='0' min='0' disabled required ></td>");
                    out.println("<td >"+cd.get("procedure_price")+"<input type=\"hidden\" name='oc_amt_" + i + "' id='oc_amt_" + i + "'  value='" + cd.get("procedure_price") + "'>"
                            + "</td>");
                    out.println("<td >"+cd.get("total_package_price")+"<input type='number' name='submit_amt_" + i + "' id='submit_amt_" + i + "' value='0' min='0' disabled required ></td>");
                    out.println("<td >" + cd.get("Procedure_code") + "</td>");
                    out.println("<td Style='white-space:no-wrap'><div class='tooltip'>Package Details <span class=\"tooltiptext\">" + cd.get("procedure_name") +"</span></div></td>");
                  
                    out.println("<td >" + cd.get("specility_code") +"</td>");
                    out.println("<td >" + cd.get("specility") + "</td>");
                    out.println("<td >" + cd.get("package_code") + "</td>");
                    out.println("<td >" + cd.get("package_name") + "</td>");
                   
                   
                  
                   
                    out.println("<td >"+cd.get("procedure_label")+"</td>");
                    out.println("</tr>"); 
                }
            }      
            
 
 //for TPA charges and packages 
 
 if (val.equals("7")) {
                
                 String category_id=request.getParameter("Charge_category");
                ArrayList<HashMap<String, String>> data2 = new WOC0001_Dao().get_TPA_charge_package(category_id);
                int i = 0;
                
                for (HashMap<String, String> cd : data2) {
          
                      out.println("<div class='row'>");
                    out.println("<div class=\"form-group col-md-3\">\n" +
"                                 <label>Name</label>"
                            + "<input type='hidden' value='"+cd.get("charge_id")+"' name='charge_id'>\n" +
"                                 <input type=\"text\" class=\"form-control\"  value='"+cd.get("charge_name")+"' name=\"charge_name\" required=\"\" placeholder=\"Name\">\n" +
"                              </div>\n" +
"                                       \n" +
"                             <div class=\"form-group col-md-2\">\n" +
"                                 <label>Qty</label>\n" +
"                                 <input type=\"text\" class=\"form-control \" name=\"Qty\"  required=\"\" placeholder=\"Qty\" value=\"1\" min=\"1\">\n" +
"                              </div>\n" +
"                                       \n" +
"                                       \n" +
"                                       \n" +
"                               <div class=\"form-group col-md-2\">\n" +
"                                 <label>Amount</label>\n" +
"                                 <input type=\"text\" class=\"form-control\" name=\"charge_Amount\" value='"+cd.get("charge_amount")+"' required=\"\" placeholder=\"Amount\" min=\"0\">\n" +
"                               </div>"
   +
"                                       \n" +
"                                       <div class=\"col-md-2 mt-4\">\n" +
"                                           <button  class=\"btn btn-success\" title=\"Add\"><i class=\"fa fa-plus\"></i></button>\n" +
"                                           </div>\n" +
"                        ");
                    
                    out.println("</div>");
               
                }
            }
 
 
 //for adding drugs and bills 
 
 if (val.equals("8")) {
               
               String patient_id=request.getParameter("patient_id");
               System.out.println("---------------->"+patient_id);
               String userid=request.getParameter("user");
               String charge_code=request.getParameter("charge_id");
               String Qty=request.getParameter("Qty");
               String charge_Amount=request.getParameter("charge_Amount");
               String Hos=request.getParameter("company_code");
               
               
                boolean sts2 = new WOC0001_Dao().assign_Tpa_drugs_bill(patient_id,userid,charge_code,Qty,charge_Amount,Hos);
                HashMap<String, String> altMap2 = new HashMap<>();
                altMap2.put("Titel", "Charge Added SuccessFully ");
                altMap2.put("Alt_Msg", "Some Error !!");
                altMap2.put("Alt_Type", "danger");
                if (sts2) {
                    altMap2.put("Alt_Msg", "Bills Added Successfully");
                    altMap2.put("Alt_Type", "success");
                    session.setAttribute("Alt_Data", altMap2);
                    response.sendRedirect("Main/OC/WOC0002.jsp");
                }
                
            }
 
 
        }catch(Exception e){
            System.out.println(e);
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
        } catch (Exception ex) {
            Logger.getLogger(WOC0001_Serv.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (Exception ex) {
            Logger.getLogger(WOC0001_Serv.class.getName()).log(Level.SEVERE, null, ex);
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

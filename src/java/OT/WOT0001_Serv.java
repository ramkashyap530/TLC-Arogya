/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package OT;

import Laboratory.WLAB0001_Serv;
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
@WebServlet(name = "WOT0001_Serv", urlPatterns = {"/WOT0001_Serv"})
public class WOT0001_Serv extends HttpServlet {

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
      String val = request.getParameter("value");
      HttpSession session = request.getSession();
      
      WOT0001_Dao st = new WOT0001_Dao();
      
      try {
         
         if (val.equals("1")) {
            
            String Hospital_code = request.getParameter("hospital_code");
            ArrayList<HashMap<String, String>> data = st.get_OT_doc();
            int i = 0;
            for (HashMap<String, String> cd : data) {
               i++;               
               out.println(" <tr>");
               out.println("<td><input type='checkbox' onclick='assign_OT_doc(" + i + ")' id='chk_" + i + "'></td>");
               out.println("<td>" + cd.get("doc_name") + "<input type='hidden' name='doc_id' value='" + cd.get("doc_code") + "' id='doc_id_" + i + "' disabled>"
                       + "<input type='hidden' value='" + cd.get("doc_name") + "' id='doc_name_" + i + "' disabled></td>");
               
               out.println("</tr>");
               
            }
         }         
         
         if (val.equals("2")) {
            
            String Hospital_code = request.getParameter("hospital_code");
            ArrayList<HashMap<String, String>> data = st.get_Ot_Staff();
            int i = 0;
            for (HashMap<String, String> cd : data) {
               i++;               
               out.println(" <tr>");
               out.println("<td><input type='checkbox' onclick='assign_OT_staff(" + i + ")' id='chk_staff_" + i + "'></td>");
               out.println("<td>" + cd.get("name") + "<input type='hidden' name='staff_id' value='" + cd.get("staff_id") + "' id='staff_id_" + i + "' disabled>"
                       + "<input type='hidden' value='" + cd.get("name") + "' id='staff_name_" + i + "' disabled></td>");
               
               out.println("</tr>");
               
            }
         }

//            for saving 
         if (val.equals("3")) {
            
            String patient_id = request.getParameter("patient_id");
            String[] doc_id = request.getParameterValues("doc_id");
            String[] staff_id = request.getParameterValues("staff_id");
            String ot_date = request.getParameter("Ot_date");
            String Ot_time = request.getParameter("Ot_time");
            String OT_Amount = request.getParameter("OT_Amount");
            String Amount_paid = request.getParameter("Amount_paid");
            String Amount_pending = request.getParameter("Amount_pending");
            String user = request.getParameter("user");
            String operation_name = request.getParameter("operation_name");
            
            System.out.println("===Pa" + patient_id);
            
            System.out.println("=====>Yanha tak ayaaa ");
            
            boolean sts = false;
            
            sts = st.OT_Save(patient_id, doc_id, staff_id, OT_Amount, Amount_paid, Amount_pending, user, operation_name, ot_date, Ot_time);
            
            HashMap<String, String> altMap = new HashMap<>();
            altMap.put("Titel", "OT Assigned");
            altMap.put("Alt_Msg", "Some Error !!");
            altMap.put("Alt_Type", "danger");
            
            if (sts) {
               {
                  altMap.put("Alt_Msg", "OT  Assigned Successfully");
                  altMap.put("Alt_Type", "success");
               }
               session.setAttribute("Alt_Data", altMap);
               response.sendRedirect("Main/OT/WOT0001.jsp");
            }
            
         }
         
         if (val.equals("4")) {
            
            String Hospital_code = request.getParameter("hospital_code");
            ArrayList<HashMap<String, String>> data = st.get_OT_details();
            int i = 0;
            for (HashMap<String, String> cd : data) {
               i++;               
               out.println(" <tr>");
               out.println("<td></td>");
               out.println("<td></td>");
               
               out.println("<td>" + cd.get("patient_id") + "</td>");
               out.println("<td>" + cd.get("patient_name") + "</td>");
               out.println("<td>" + cd.get("ot_date") + "</td>");
               out.println("<td>" + cd.get("ot_time") + "</td>");
               out.println("<td>" + cd.get("doc") + "</td>");
               out.println("<td>" + cd.get("staff_name") + "</td>");
               out.println("<td>" + cd.get("ot_total_amount") + "</td>");
               out.println("<td>" + cd.get("ot_paid_amount") + "</td>");
               out.println("<td>" + cd.get("ot_pending_amount") + "</td>");
               out.println("<td><span class='badge bg-success'>" + cd.get("ot_status") + "</span></td>");
               
               out.println("</tr>");
               
            }
         }
         
         if (val.equals("5")) {
            
            String ot = request.getParameter("ot");
            String otrate = st.getOtRate(ot);
            if (!otrate.equals("")) {
               out.print(otrate);
            } else {
               out.print("0");
            }
         }         
         
      } catch (Exception Ex) {
         Logger.getLogger(WOT0001_Serv.class.getName()).log(Level.SEVERE, null, Ex);
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

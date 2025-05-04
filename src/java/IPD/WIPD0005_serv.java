/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package IPD;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
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
@WebServlet(name = "WIPD0005_serv", urlPatterns = {"/WIPD0005_serv"})
public class WIPD0005_serv extends HttpServlet {

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
        HttpSession session = request.getSession();
        String val = request.getParameter("value");
        PrintWriter out = response.getWriter();
        Throwable th = null;
        
        try {
            if (val.equals("1")) {
                String patient_id = request.getParameter("patient_id");
                int sNo = 0;
                ArrayList<HashMap<String, String>> get_room_data = new WIPD0005_Dao().get_room_charge_hi(patient_id);
             
                for (HashMap<String, String> dataNow : get_room_data) {
                  
                    sNo++;
                    String checkBoxDBL = "";
                    float panding_amt = Float.parseFloat(dataNow.get("panding_amt"));
                    if (panding_amt <= 0.0f) {
                        checkBoxDBL = "disabled";
                    }
                    out.println(" <tr>");
                    out.println("<td style=\"white-space: nowrap\"><input type='checkbox' " + checkBoxDBL + " onclick=\"disabled_enabled('" + sNo + "');\" name='checkbox_room_payment' id='checkbox_room_payment_" + sNo + "' value='" + sNo + "'>" + sNo + "</td>");
                    out.println("<td style=\"white-space: nowrap\"><input type='hidden' disabled name='patient_locationRwid_" + sNo + "' id='patient_locationRwid_" + sNo + "' value='" + dataNow.get("patient_locationRwid") + "'>" + patient_id + "</td>");
                    out.println("<td style=\"white-space: nowrap\"><input type='hidden' disabled name='room_bed_id_" + sNo + "' id='room_bed_id_" + sNo + "' value='" + dataNow.get("move_bed_id") + "'>" + dataNow.get("move_bed_id") + "</td>");
                    out.println("<td style=\"white-space: nowrap\">" + dataNow.get("started_date") + " to " + dataNow.get("ended_date") + "</td>");
                    out.println("<td style=\"white-space: nowrap\">" + dataNow.get("number_of_days") + "</td>");
                    out.println("<td style=\"white-space: nowrap\"><input type='hidden' disabled name='room_amt_" + sNo + "' id='room_amt_" + sNo + "' value='" + dataNow.get("tot_room_amt") + "'>" + dataNow.get("tot_room_amt") + "</td>");
                    out.println("<td style=\"white-space: nowrap\"><input type='hidden' disabled name='paid_amt_" + sNo + "' id='paid_amt_" + sNo + "' value='" + dataNow.get("paid_amt") + "'>" + dataNow.get("paid_amt") + "</td>");
                    out.println("<td style=\"white-space: nowrap\"><input type='hidden' disabled name='panding_amt_" + sNo + "' id='panding_amt_" + sNo + "' value='" + dataNow.get("panding_amt") + "'>" + dataNow.get("panding_amt") + "</td>");
                    out.println("<td style=\"white-space: nowrap\"><input type='test' disabled name='submit_amt_" + sNo + "' id='submit_amt_" + sNo + "' value='0'></td>");
                    out.println("<td hidden style=\"white-space: nowrap\">" + dataNow.get("room_payment_id") + "</td>");
                    out.println(" </tr>");
                }
            }
            
            
            if (val.equals("2")) {
                String room_payment_id = new WIPD0005_Dao().collectRoomPayment(request);
                HashMap<String, String> altMap = new HashMap<>();
                altMap.put("Titel", "Room Payment");
                altMap.put("Alt_Msg", "Some Error !!");
                altMap.put("Alt_Type", "error");
                if (!room_payment_id.equals("")) {
                    altMap.put("Alt_Msg", "Payment Done with Payment ID: " + room_payment_id);
                    altMap.put("Alt_Type", "success");
                }
                session.setAttribute("Alt_Data", altMap);
                response.sendRedirect("Main/IPD/WIPD0005.jsp");
            }
            
            if (val.equals("3")) {
                String patient_id2 = request.getParameter("patient_id");
                int sNo2 = 0;
                ArrayList<HashMap<String, String>> get_room_data2 = new WIPD0005_Dao().get_room_payment(patient_id2);
                
                  for (HashMap<String, String> dataNow2 : get_room_data2) {
                    sNo2++;
                    out.println(" <tr>");
                    out.println("<td style=\"white-space: nowrap\">" + sNo2 + "</td>");
                    out.println("<td style=\"white-space: nowrap\">" + patient_id2 + "</td>");
                    out.println("<td style=\"white-space: nowrap\">" + dataNow2.get("room_payment_details_id") + "</td>");
                    out.println("<td style=\"white-space: nowrap\">" + dataNow2.get("paid_amt") + "</td>");
                    out.println("<td style=\"white-space: nowrap\">" + dataNow2.get("createDT") + "</td>");
                    out.println(" </tr>");
                }
            }
            
            
     
    }
    
        catch(Exception e){
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

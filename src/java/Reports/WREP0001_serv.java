/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Reports;

import IPD.WIPD0001_SERV;
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
@WebServlet(name = "WREP0001_serv", urlPatterns = {"/WREP0001_serv"})
public class WREP0001_serv extends HttpServlet {

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
        WREP0001_Dao st = new WREP0001_Dao();

        try {

            if (val.equals("1")) {

                String arr[] = {request.getParameter("hospital_code"), request.getParameter("date_from"),
                    request.getParameter("date_to"), request.getParameter("patient_name"), request.getParameter("type")};

                ArrayList<HashMap<String, String>> data = st.get_total_doc(arr);
                int i = 0;
                for (HashMap<String, String> cd : data) {
                    i++;
                    out.println(" <tr>");
                    out.println("<td>" + i + "</td>");
                    out.println("<td style=\"white-space: nowrap\"><img src=\"../../Staff/images/DOC_" + cd.get("doc_code").replace('/', '-') + ".jpg\" class=\"img-thumbnail\" onerror=\"this.onerror=null; this.src='../Design/avatr.jpg'\" alt=\"\"  style='height:50px;width:50px' ></td></td>");
                    out.println("<td style=\"white-space: nowrap\"><img src=\"../../Staff/images/doc_adhar_" + cd.get("doc_code").replace('/', '-') + ".jpg\" class=\"img-thumbnail\" onerror=\"this.onerror=null; this.src='../Design/avatr.jpg'\" alt=\"\"  style='height:50px;width:50px' ></td></td>");

                    out.println("<td style=\"white-space: nowrap\">" + cd.get("doc_name") + "</td>");
                    out.println("<td style=\"white-space: nowrap\">" + cd.get("cont_charge") + "</td>");
                    out.println("<td style=\"white-space: nowrap\">" + cd.get("department_name") + "</td>");
                    out.println("<td style=\"white-space: nowrap\">" + cd.get("degre") + "</td>");
                    out.println("<td style=\"white-space: nowrap\">" + cd.get("mobile_number") + "</td>");
                    out.println("<td style=\"white-space: nowrap\">" + cd.get("email") + "</td>");
                    out.println("<td style=\"white-space: nowrap\">" + cd.get("address") + "</td>");

                    out.println("<td style=\"white-space: nowrap\"><button class='btn btn-sm btn-primary' onclick='window.open(\"../../Main/Master/WMAS0004.jsp?doc_code=" + cd.get("doc_code").toString() + "\");'><i class='fa fa-edit'></i></button></td>");

                    out.println("</tr>");

                }
            }

            if (val.equals("2")) {

                String arr[] = {request.getParameter("hospital_code"), request.getParameter("date_from"),
                    request.getParameter("date_to"), request.getParameter("patient_name"), request.getParameter("type")};

                ArrayList<HashMap<String, String>> data = st.get_total_staff(arr);
                int i = 0;
                for (HashMap<String, String> cd : data) {
                    i++;
                    out.println(" <tr>");
                    out.println("<td>" + i + "</td>");
                     out.println("<td><button type='button' class='btn btn-sm btn-primary'>User</button></td>");
                    out.println("<td style=\"white-space: nowrap\"><img src=\"../../Staff/images/Staff_" + cd.get("staff_id").replace('/', '-') + ".jpg\" class=\"img-thumbnail\" onerror=\"this.onerror=null; this.src='../Design/avatr.jpg'\" alt=\"\"  style='height:50px;width:50px' ></td></td>");
                    out.println("<td style=\"white-space: nowrap\"><img src=\"../../Staff/images/Staff_Adhar_" + cd.get("staff_id").replace('/', '-') + ".jpg\" class=\"img-thumbnail\" onerror=\"this.onerror=null; this.src='../Design/avatr.jpg'\" alt=\"\"  style='height:50px;width:50px' ></td></td>");

                    out.println("<td style=\"white-space: nowrap\">" + cd.get("name") + "</td>");
                    out.println("<td style=\"white-space: nowrap\">" + cd.get("gender") + "</td>");
                    out.println("<td style=\"white-space: nowrap\">" + cd.get("age") + "</td>");
                    out.println("<td style=\"white-space: nowrap\">" + cd.get("depaertment") + "</td>");
                    out.println("<td style=\"white-space: nowrap\">" + cd.get("position") + "</td>");
                    out.println("<td style=\"white-space: nowrap\">" + cd.get("salary") + "</td>");
                    out.println("<td style=\"white-space: nowrap\">" + cd.get("adhar_card") + "</td>");
                    out.println("<td style=\"white-space: nowrap\">" + cd.get("date_of_joining") + "</td>");

                    out.println("<td style=\"white-space: nowrap\"><button class='btn btn-sm btn-primary' onclick='window.open(\"../../Main/Master/WMAS0005.jsp?staff_code=" + cd.get("staff_id").toString() + "\");'><i class='fa fa-edit'></i></button>"
                            + ""
                            + "</td>");

                    
                   
                    out.println("</tr>");

                }
            }

            if (val.equals("3")) {

                String arr[] = {request.getParameter("hospital_code"), request.getParameter("date_from"),
                    request.getParameter("date_to"), request.getParameter("patient_name"), request.getParameter("type"), request.getParameter("")};

                ArrayList<HashMap<String, String>> data = st.bed_wise_details(arr);
                int i = 0;
                for (HashMap<String, String> cd : data) {
                    i++;
                    out.println(" <tr>");
                    out.println("<td>" + i + "</td>");
                    out.println("<td style=\"white-space: nowrap\"><img src=\"../../Staff/images/patient-" + cd.get("patient_id").replace('/', '-') + ".jpg\" class=\"img-thumbnail\" onerror=\"this.onerror=null; this.src='../Design/avatr.jpg'\" alt=\"\"  style='height:50px;width:50px' ></td></td>");

                    out.println("<td style=\"white-space: nowrap\">" + cd.get("name") + "</td>");
                    out.println("<td style=\"white-space: nowrap\">" + cd.get("room") + "</td>");
                    out.println("<td style=\"white-space: nowrap\">" + cd.get("room_bed_id") + "</td>");
                    out.println("<td style=\"white-space: nowrap\">" + cd.get("ipd_no") + "</td>");
                    out.println("<td style=\"white-space: nowrap\"><span class='badge bg-primary'>" + cd.get("adm_date") + "</span></td>");
                    out.println("</tr>");

                }
            }
            
            
            
             if (val.equals("4")) {

                String arr[] = {request.getParameter("hospital_code"), request.getParameter("date_from"),
                    request.getParameter("date_to"), request.getParameter("patient_name"), request.getParameter("type"), request.getParameter("")};

                ArrayList<HashMap<String, String>> data = st.doctor_wise_opd(arr);
                int i = 0;
                for (HashMap<String, String> cd : data) {
                    i++;
                    out.println(" <tr>");
                    out.println("<td>" + i + "</td>");
                    

                    out.println("<td style=\"white-space: nowrap\">" + cd.get("opd_id") + "</td>");
                    out.println("<td style=\"white-space: nowrap\">" + cd.get("patient_name") + "</td>");
                    out.println("<td style=\"white-space: nowrap\">" + cd.get("doc_name") + "</td>");
                    out.println("<td style=\"white-space: nowrap\">" + cd.get("cont_charge") + "</td>");
                    out.println("<td style=\"white-space: nowrap\"><span class='badge bg-primary'>" + cd.get("dat") + "</span></td>");
                    out.println("<td style=\"white-space: nowrap\"><span class='badge bg-warning'>" + cd.get("opd_sts") + "</span></td>");
                    out.println("</tr>");

                }
            }
             
             
             
         //for daily collection Report 
         
         if(val.equals("5")){
             

                String arr[] = {request.getParameter("type")};

                     float total_amt=0;
                     float total_refund=0;
                     float Final_amt=0;
                 
                ArrayList<HashMap<String, String>> data = st.Get_Daily_collection_report(arr);
                int i = 0;
                for (HashMap<String, String> cd : data) {
                    i++;
                    total_amt +=Float.parseFloat(cd.get("Amount"));
                    total_refund +=Float.parseFloat(cd.get("refund"));
                    Final_amt=total_amt - total_refund;
                    
                    out.println(" <tr>");
                    out.println("<td>" + i + "</td>");
                   
                    out.println("<td style=\"white-space: nowrap\">" + cd.get("patient_id") + "</td>");
                    out.println("<td style=\"white-space: nowrap\">" + cd.get("patient_name") + " / "+cd.get("doc_name")+"</td>");
                    out.println("<td style=\"white-space: nowrap\">" + cd.get("Amount") + " / " + cd.get("refund") + "</td>");
                   
                    out.println("<td style=\"white-space: nowrap\">" + cd.get("Create_date") + "</td>");
                 
                    out.println("</tr>");

                }
                if(arr[0].equals("LAB")){
                    
                   out.println("<tr>");
                
                out.println("<td></td>");
                out.println("<td></td>");
                out.println("<td>Total</td>");
                out.println("<td style='background-color:yellow'>Total <Br>"+total_amt+" / "+total_refund+"</td>");
                out.println("<td></td>");

                
                out.println("</tr>"); 
                }else{
                out.println("<tr>");
                
                out.println("<td></td>");
                out.println("<td></td>");
                out.println("<td>Total</td>");
                out.println("<td>"+total_amt+" / "+total_refund+" <br>"+Final_amt+"</td>");
                out.println("<td></td>");

                
                out.println("</tr>");
                }
             
         }
             
             
             
             
             
             
             
             

        } catch (Exception ex) {
            Logger.getLogger(WIPD0001_SERV.class.getName()).log(Level.SEVERE, null, ex);

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

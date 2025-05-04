/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Reports;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Lenovo
 */
@WebServlet(name = "WREP0007_Serv", urlPatterns = {"/WREP0007_Serv"})
public class WREP0007_Serv extends HttpServlet {

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
        request.getSession();
        WREP0007_Dao st = new WREP0007_Dao();
        try {
            if (val.equals("1")) {
                String[] arr = {request.getParameter("hospital_code"), request.getParameter("date_from"), request.getParameter("date_to"), request.getParameter("patient_name"), request.getParameter("type")};
                int i = 0;
                ArrayList<HashMap<String, String>> data = st.get_mini_day_bbok(arr);
                float amount_in = 0.0f;
                float day_end_amt_in = 0.0f;
                Iterator<HashMap<String, String>> it = data.iterator();
                while (it.hasNext()) {
                    HashMap<String, String> cd = it.next();
                    amount_in += Float.parseFloat(cd.get("amount_in"));
                    if (i > 0 && !cd.get("submit_date").equals(data.get(i - 1).get("submit_date"))) {
                        out.println("<tr bgcolor=\"#D3D3D3\">");
                        out.println("<td></td>");
                        out.println("<td></td>");
                        out.println("<td style='font-weight:900'> DAY END</td>");
                        out.println("<td style='font-weight:900'><span>&#8377</span> " + day_end_amt_in + "</td>");
                        out.println("<td></td>");
                        out.println("<td></td>");
                        out.println("<td></td>");
                        out.println("<td></td>");
                        out.println("</tr>");
                        day_end_amt_in = 0.0f;
                    }
                    i++;
                    out.println("<tr>");
                    out.println("<td><span class='badge bg-success'>" + cd.get("payment_type") + "</span></td>");
                    out.println("<td>" + cd.get("payment_from") + "</td>");
                    out.println("<td>" + cd.get("patient_name") + "</td>");
                    out.println("<td><span class='badge bg-primary'>" + cd.get("amount_in") + "</span></td>");
                    out.println("<td><span class='badge bg-danger'>" + cd.get("amount_out") + "</td>");
                    out.println("<td><span class='badge bg-warning'>" + data.get(i - 1).get("submit_date") + "</span></td>");
                    out.println("<td style='font-weight:500'>" + cd.get("pay_description") + "</td>");
                    out.println("<td>" + cd.get("create_by") + "</td>");
                    out.println("</tr>");
                    day_end_amt_in += Float.parseFloat(cd.get("amount_in"));
                }
                out.println("<tr>");
                out.println("<td></td>");
                out.println("<td></td>");
                out.println("<td></td>");
                out.println("<td>" + amount_in + "</td>");
                out.println("<td></td>");
                out.println("<td></td>");
                out.println("<td></td>");
                out.println("<td></td>");
                out.println("</tr>");
            }
            if (val.equals("2")) {
                String[] arr2 = {request.getParameter("hospital_code"), request.getParameter("date_from"), request.getParameter("date_to"), request.getParameter("patient_name"), request.getParameter("type")};
                int i2 = 0;
                ArrayList<HashMap<String, String>> data2 = st.get_mini_day_bbok_opd(arr2);
                float amount_in2 = 0.0f;
                float day_end_amt_in2 = 0.0f;
                Iterator<HashMap<String, String>> it2 = data2.iterator();
                while (it2.hasNext()) {
                    HashMap<String, String> cd2 = it2.next();
                    amount_in2 += Float.parseFloat(cd2.get("amount_in"));
                    if (i2 > 0 && !cd2.get("submit_date").equals(data2.get(i2 - 1).get("submit_date"))) {
                        out.println("<tr bgcolor=\"#D3D3D3\">");
                        out.println("<td></td>");
                        out.println("<td></td>");
                        out.println("<td style='font-weight:900'> DAY END</td>");
                        out.println("<td style='font-weight:900'><span>&#8377</span> " + day_end_amt_in2 + "</td>");
                        out.println("<td></td>");
                        out.println("<td></td>");
                        out.println("<td></td>");
                        out.println("<td></td>");
                        out.println("<td></td>");
                        out.println("</tr>");
                        day_end_amt_in2 = 0.0f;
                    }
                    i2++;
                    out.println("<tr>");
                    out.println("<td><span class='badge bg-success'>" + cd2.get("payment_type") + "</span></td>");
                    out.println("<td>" + cd2.get("payment_from") + "</td>");
                    out.println("<td>" + cd2.get("patient_name") + "</td>");
                    out.println("<td><span class='badge bg-primary'>" + cd2.get("amount_in") + "</span></td>");
                    out.println("<td><span class='badge bg-danger'>" + cd2.get("amount_out") + "</td>");
                    out.println("<td><span class='badge bg-warning'>" + data2.get(i2 - 1).get("submit_date") + "</span></td>");
                    out.println("<td style='font-weight:500'>" + cd2.get("pay_description") + "</td>");
                    out.println("<td>" + cd2.get("free") + "</td>");
                    out.println("<td>" + cd2.get("create_by") + "</td>");
                    out.println("</tr>");
                    day_end_amt_in2 += Float.parseFloat(cd2.get("amount_in"));
                }
                out.println("<tr>");
                out.println("<td></td>");
                out.println("<td></td>");
                out.println("<td></td>");
                out.println("<td>" + amount_in2 + "</td>");
                out.println("<td></td>");
                out.println("<td></td>");
                out.println("<td></td>");
                out.println("<td></td>");
                out.println("<td></td>");
                out.println("</tr>");
            }
        } catch (Exception ex) {
            Logger.getLogger(WREP0007_Serv.class.getName()).log(Level.SEVERE, (String) null, (Throwable) ex);
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

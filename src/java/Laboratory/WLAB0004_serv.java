/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Laboratory;

import IPD.WIPD0002_Dao;
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
@WebServlet(name = "WLAB0004_serv", urlPatterns = {"/WLAB0004_serv"})
public class WLAB0004_serv extends HttpServlet {

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
        String val = request.getParameter("value");
        HttpSession session = request.getSession();
        WLAB0004_dao st=new WLAB0004_dao();
        
        try{
            
                 if (val.equals("1")) {

                System.out.println("===>yanha tak aya");
                boolean sts =false;

                String category_name[] = request.getParameterValues("Charge_category");
                String charge_name[] = request.getParameterValues("charge_name");
                String qty[]= request.getParameterValues("qty");
                String total_amount[]=request.getParameterValues("total_amount");
                String charge_rate[]=request.getParameterValues("charge_rate");
                String Patient_id=request.getParameter("patient_id");
                String cretaed_by=request.getParameter("user");
                String Bill_no="";

                sts = st.Save_final_bill(category_name,charge_name,qty,total_amount,Patient_id,cretaed_by,Bill_no,charge_rate);

                HashMap<String, String> altMap = new HashMap<>();
                altMap.put("Titel", "Amount Recivied");
                altMap.put("Alt_Msg", "Some Error !!");
                altMap.put("Alt_Type", "danger");

                if (sts) {

                    {
                        altMap.put("Alt_Msg", "Amount Recivied Successfully");
                        altMap.put("Alt_Type", "success");
                    }
                    session.setAttribute("Alt_Data", altMap);
                    response.sendRedirect("Main/IPD/WIPD0002.jsp");
                }

            }
            
            
            
            
            
            
        }catch(Exception e){
            System.out.println("->"+e);
                    
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

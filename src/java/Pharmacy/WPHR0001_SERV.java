/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Pharmacy;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
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
@WebServlet(name = "WPHR0001_SERV", urlPatterns = {"/WPHR0001_SERV"})
public class WPHR0001_SERV extends HttpServlet {

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
        /* TODO output your page here. You may use following sample code. */
        HttpSession session = request.getSession();
        WPHR0001_Dao st = new WPHR0001_Dao();
        String val = request.getParameter("value");

        try {

            if (val.equals("1")) {

                System.out.println("==>1" + val);
                String Item_name = request.getParameter("Item_name");

                String vendor = request.getParameter("vendor_name");
                String sale_price = request.getParameter("sale_price");
                String opeaning_stock = request.getParameter("qty_in");
                String Min_stock = request.getParameter("Min_stock");
                String created_by = request.getParameter("user");
                String purchase_price = request.getParameter("purchase_price");
                String Item_type = request.getParameter("Item_type");
                String hsn_code = request.getParameter("hsn_code");
                String batch_no = request.getParameter("batch_no");
                String Manfu_date = request.getParameter("Manfu_date");
                String Exp_date = request.getParameter("Exp_date");

                System.out.println("====>min" + Min_stock);
                boolean sts = false;

                sts = st.saveitem(Item_name, sale_price, purchase_price, Min_stock, created_by, opeaning_stock, created_by, Item_type, vendor, hsn_code, batch_no, Manfu_date, Exp_date);

                HashMap<String, String> altMap = new HashMap<>();
                altMap.put("Titel", "Item  Added");
                altMap.put("Alt_Msg", "Some Error !!");
                altMap.put("Alt_Type", "danger");

                if (sts) {
                    {
                        altMap.put("Alt_Msg", "Item Added successfully");
                        altMap.put("Alt_Type", "success");
                    }
                    session.setAttribute("Alt_Data", altMap);
                    response.sendRedirect("Main/Pharmacy/WPHR0001.jsp");
                }

            }

            if (val.equals("2")) {

                String prd_cd = request.getParameter("prd_cd");
                String qty = request.getParameter("qty");
                String name = request.getParameter("name");

                ArrayList<HashMap<String, String>> sale_data = new WPHR0001_Dao().get_sale_data(prd_cd);
                int i = 0;
                for (HashMap<String, String> cd : sale_data) {

                    float tot = Float.parseFloat(qty) * Float.parseFloat(cd.get("sale_rate"));

                    out.println("<tr>");
                    out.println("<td></td>");
                    out.println("<td>" + cd.get("item_name") + "<input type='hidden' name='item_code' value=" + cd.get("item_code") + "></td>");
                    out.println("<td>" + cd.get("bathc_no") + "<input type='hidden' name='bathc_no' value=" + cd.get("bathc_no") + "></td>");
                    out.println("<td>" + cd.get("hsn_code") + "<input type='hidden' name='hsn_code' value=" + cd.get("hsn_code") + "></td>");
                    out.println("<td>" + qty + "  <input type='hidden' name='qty' value=" + qty + "></td>");
                    out.println("<td>" + cd.get("item_type") + "<input type='hidden' value='" + cd.get("item_type") + "' name='item_type' id='qty" + prd_cd + "'></td>");
                    out.println("<td>" + cd.get("sale_rate") + "<input type='hidden' class='form-control' name='rate_per_pcs' value=" + cd.get("sale_rate") + " id='" + prd_cd + "' onkeyup=\"multi('" + prd_cd + "',this);\"></td>");
                    out.println("<td><input type='text' class='form-control form-control-sm  total_amt' name='total_amt' value='" + tot + "' readonly id='total_amt_" + prd_cd + "'></td>");
                    out.println("<td><button class=\"btn btn-danger  btn-sm\"  onClick=\"$(this).closest('tr').remove();final_bill_amount();\"><i class=\"fa fa-minus\"></i></button></td>");
                    out.println("</tr>");

                }

            }

//            for pharmacy stock
            if (val.equals("3")) {

                String[] arr = {request.getParameter("item_name"), request.getParameter("item_code")};

                System.out.println("====>yanha tak aya h ");
                ArrayList<HashMap<String, String>> data = st.getstockData(arr);
                int i = 0;
                for (HashMap<String, String> al : data) {
                    i++;
                    out.println("<tr>");

                    out.println("<td><input type='checkbox' name='chk' id='chk_" + i + "' onclick='enable(" + i + ")'></td>");
                    out.println("<td>" + al.get("item_code") + "</td>");
                    out.println("<td>" + al.get("item_name") + "</td>");
                    out.println("<td>" + al.get("qty_in") + "<input type='hidden' name='qty_in' id='qty_in" + i + "' value='" + al.get("qty_in") + "' disabled></td>");
                    out.println("<td>" + al.get("qty_out") + "<input type='hidden' name='qty_out' id='qty_out_" + i + "' value='" + al.get("qty_out") + "' disabled></td>");
                    out.println("<td>" + al.get("current_qty") + "</td>");
                    out.println("<td><input type='text' class='form-control'></td>");
                    out.println("</tr>");

                }

            }

//   for pharmacy Rate
            if (val.equals("4")) {

                String[] arr = {request.getParameter("item_name"), request.getParameter("item_code")};

                System.out.println("====>yanha tak aya h ");
                ArrayList<HashMap<String, String>> data = st.get_item_rate(arr);
                int i = 0;
                for (HashMap<String, String> al : data) {
                    i++;
                    out.println("<tr>");

                    out.println("<td><input type='checkbox' name='chk' id='chk_" + i + "' onclick='enable(" + i + ")'></td>");
                    out.println("<td>" + al.get("item_code") + "</td>");
                    out.println("<td>" + al.get("item_name") + "</td>");
                    out.println("<td><input type='text' class='form-control' value=" + al.get("sale_rate") + "></td>");

                    out.println("</tr>");

                }

            }

//for pharmacy_billing 
            if (val.equals("5")) {

                String[] prd_cd = request.getParameterValues("item_code");

                String[] qty = request.getParameterValues("qty");
                String[] item_type = request.getParameterValues("item_type");
                String[] total_rate = request.getParameterValues("total_rate");
                String bill_amt = request.getParameter("bill_amt");
                String extra_ch = request.getParameter("extra_charge");
                String final_bill_amt = request.getParameter("final_bill_amt");

                String mobile_no = request.getParameter("bill_mobile");
                String bill_name = request.getParameter("bill_name");
                String bill_address = request.getParameter("bill_address");

                LinkedList<HashMap<String, String>> catAL = new LinkedList<>();

                boolean sts = false;

                sts = st.save_daily_bill(prd_cd, qty, total_rate, bill_amt, extra_ch, final_bill_amt, mobile_no, bill_name, bill_address, item_type);
                System.out.println("stssts " + sts);
                HashMap<String, String> altMap = new HashMap<>();
                altMap.put("Titel", "Bill Generated");
                altMap.put("Alt_Msg", "Some Error !!");
                altMap.put("Alt_Type", "danger");

                if (sts) {
                    {
                        altMap.put("Alt_Msg", "Bill Generated successfully");
                        altMap.put("Alt_Type", "success");
                    }
                    session.setAttribute("Alt_Data", altMap);
                    response.sendRedirect("Main/Pharmacy/WPHR0002.jsp");
                }

            }

            if (val.equals("6")) {

                String[] arr = {request.getParameter("item_name"), request.getParameter("item_code")};

                System.out.println("====>yanha tak aya h ");
                ArrayList<HashMap<String, String>> data = st.get_bill(arr);
                int i = 0;
                for (HashMap<String, String> al : data) {
                    i++;
                    out.println("<tr>");

                    out.println("<td></td>");
                    out.println("<td>" + al.get("bill_to") + "</td>");
                    out.println("<td>" + al.get("final_amt") + "</td>");
                    out.println("<td>" + al.get("created_date") + "</td>");
                    out.println("<td><button class='btn btn-primary' onclick='window.open(\"../../BIll_print?bill_id=" + al.get("bill_code").toString() + "\");'>Print</button></td>");

                    out.println("</tr>");

                }

            }

        } catch (Exception ex) {
            Logger.getLogger(WPHR0001_SERV.class.getName()).log(Level.SEVERE, null, ex);
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

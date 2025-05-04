/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Laboratory;

import HR.WHR0001_Dao;
import HR.WHR0001_SERV;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
@WebServlet(name = "WLAB0001_Serv", urlPatterns = {"/WLAB0001_Serv"})
public class WLAB0001_Serv extends HttpServlet {

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
        WLAB0001_dao st = new WLAB0001_dao();
        /* TODO output your page here. You may use following sample code. */
        try {
            if (val.equals("1")) {

                String test_name = request.getParameter("test_name");

                boolean sts = false;

                sts = st.Save_test(test_name);

                HashMap<String, String> altMap = new HashMap<>();
                altMap.put("Titel", "Test Added ");
                altMap.put("Alt_Msg", "Some Error !!");
                altMap.put("Alt_Type", "danger");

                if (sts) {
                    {
                        altMap.put("Alt_Msg", "Test Added Successfully");
                        altMap.put("Alt_Type", "success");
                    }
                    session.setAttribute("Alt_Data", altMap);
                    response.sendRedirect("Main/Laboratory/WLAB0001.jsp");
                }

            }

            if (val.equals("2")) {

                String Hospital_code = request.getParameter("hospital_code");
                ArrayList<HashMap<String, String>> data = st.get_test_data();
                int i = 0;
                for (HashMap<String, String> cd : data) {
                    i++;
                    out.println(" <tr>");
                    out.println("<td>" + i + "</td>");

                    out.println("<td contenteditable=\"true\">" + cd.get("lab_test_id") + "<input type=hidden name='staff_id'  value='" + cd.get("staff_id") + "' id='id_" + i + "'></td>");
                    out.println("<td contenteditable=\"true\">" + cd.get("lab_test_name") + " </td>");
                    out.println("<td contenteditable=\"true\"><span class='badge bg-success'>" + cd.get("test_sts") + "</td>");

                    out.println("</tr>");

                }
            }

            if (val.equals("3")) {

                String Hospital_code = request.getParameter("hospital_code");
                ArrayList<HashMap<String, String>> data = st.get_test_rate();
                int i = 0;
                for (HashMap<String, String> cd : data) {
                    i++;
                    out.println(" <tr>");
                    out.println("<td>" + i + "</td>");

                    out.println("<td contenteditable=\"true\">" + cd.get("lab_test_name") + "<input type=hidden name='test_id'  value='" + cd.get("lab_test_id") + "' id='id_" + i + "'></td>");
                    out.println("<td><input type='text' class='form-control' value='" + cd.get("rate") + "' name='test_name_rate'></td>");

                    out.println("</tr>");

                }
            }

            if (val.equals("4")) {

                String[] test_id = request.getParameterValues("test_id");
                String[] rates = request.getParameterValues("test_name_rate");

                boolean sts = false;

                sts = st.Save_test_rates(test_id, rates);

                HashMap<String, String> altMap = new HashMap<>();
                altMap.put("Titel", "Rates Added ");
                altMap.put("Alt_Msg", "Some Error !!");
                altMap.put("Alt_Type", "danger");

                if (sts) {
                    {
                        altMap.put("Alt_Msg", "Rates Added Successfully");
                        altMap.put("Alt_Type", "success");
                    }
                    session.setAttribute("Alt_Data", altMap);
                    response.sendRedirect("Main/Laboratory/WLAB0001.jsp");
                }

            }

            if (val.equals("5")) {

                String patient_id = request.getParameter("patient_id");
                ArrayList<HashMap<String, String>> data = st.get_test_data();
                int i = 0;
                for (HashMap<String, String> cd : data) {
                    i++;
                    out.println(" <tr>");
                    out.println("<td><input type='checkbox' onclick='assign_test(" + i + ")' name='test_checkbox' id='chk_" + i + "' value='" + i + "'></td>");
                    out.println("<td>" + cd.get("lab_test_name") + "<input type='hidden'  name='test_id' value='" + cd.get("lab_test_id") + "' id='test_id_" + i + "' disabled><input type='hidden' value='" + cd.get("lab_test_name") + "' id='test_name_" + i + "' disabled>"
                            + "<input type='hidden' value='" + cd.get("rate") + "' id='test_rate_" + i + "' ></td>");
                    out.println("<td><span class='badge bg-success'>" + cd.get("rate") + ""
                            + "<input type='hidden' name='rate' id='rate_" + i + "' value='" + cd.get("rate") + "' disabled> </td>");
                    out.println("<td><input type='text' name='test_paid_amt' id='test_paid_amt_" + i + "' min='1' max='" + cd.get("rate") + "' onkeyup=\"get_test_paidData('" + i + "');\" value='' disabled ></td>");
                    out.println("<td><input type='text' name='test_left_amt' id='test_left_amt_" + i + "' value='' disabled readonly ></td>");
//          out.println("<td><input type='text' name='submit_amt' id='submit_amt_"+i+"' value='"+cd.get("test_left_amt")+"'></td>");

                    out.println("</tr>");

                }
            }

            if (val.equals("6")) {

                String[] test_id = request.getParameterValues("test_id");
                String[] test_paid_amt = request.getParameterValues("test_paid_amt");
                String[] test_pending_amt = request.getParameterValues("test_left_amt");
                String[] test_rate = request.getParameterValues("rate");

                String patient_id = request.getParameter("patient_name_id");
                String user = request.getParameter("user");
                String Total_Amount = request.getParameter("Total_Amount");
                String Amount_paid = request.getParameter("Amount_paid");
                String Amount_pending = request.getParameter("Amount_pending");

                boolean sts = false;

                sts = st.Save_test_assigned(test_id, patient_id, user, Total_Amount, Amount_paid, Amount_pending, test_paid_amt, test_pending_amt, test_rate);

                HashMap<String, String> altMap = new HashMap<>();
                altMap.put("Titel", "Test Assigned");
                altMap.put("Alt_Msg", "Some Error !!");
                altMap.put("Alt_Type", "danger");

                if (sts) {
                    {
                        altMap.put("Alt_Msg", "Test Assigned Success");
                        altMap.put("Alt_Type", "success");
                    }
                    session.setAttribute("Alt_Data", altMap);
                    response.sendRedirect("Main/Laboratory/WLAB0002.jsp");
                }

            }

            if (val.equals("7")) {

                String Hospital_code = request.getParameter("hospital_code");
                ArrayList<HashMap<String, String>> data = st.get_test_data_assigned();
                int i = 0;
                for (HashMap<String, String> cd : data) {
                    i++;
                    out.println(" <tr>");
                    out.println("<td><input type='checkbox' onclick='assign_test(" + i + ")' id='chk_" + i + "'></td>");
                    out.println("<td>" + cd.get("patient_name") + "<input type='hidden' name='patient_id' value='" + cd.get("patient_id") + "' id='patient_id_" + i + "' ><input type='hidden' value='" + cd.get("test_id") + "' id='test_id_" + i + "' ></td>");
                    out.println("<td><span class='badge bg-primary'>" + cd.get("age") + "</td>");
                    out.println("<td><span class='badge bg-warning'>" + cd.get("lab_test_name") + "</td>");
                    if (cd.get("test_sts").equals("Pending")) {
                        out.println("<td><span class='badge bg-danger'>" + cd.get("test_sts") + "</td>");
                    } else {
                        out.println("<td><span class='badge bg-success'>" + cd.get("test_sts") + "</td>");
                    }
                    if (cd.get("test_sts").equals("Pending")) {
                        out.println("<td><button class='btn btn-success' type='button' onclick='change_test_sts(" + i + ")'>Done</button></td>");
                    }
                    out.println("</tr>");

                }
            }

            if (val.equals("8")) {

                String test_id = request.getParameter("test_code");
                String patient_id = request.getParameter("patient_code");
                String user = request.getParameter("user");

                System.out.println("===Pa" + patient_id);

                System.out.println("=====>Yanha tak ayaaa ");

                boolean sts = false;

                sts = st.change_test_sts(test_id, patient_id, user);

                HashMap<String, String> altMap = new HashMap<>();
                altMap.put("Titel", "Test Assigned");
                altMap.put("Alt_Msg", "Some Error !!");
                altMap.put("Alt_Type", "danger");

                if (sts) {
                    {
                        altMap.put("Alt_Msg", "Test Done");
                        altMap.put("Alt_Type", "success");
                    }
                    session.setAttribute("Alt_Data", altMap);
                    response.sendRedirect("Main/Laboratory/WLAB0003.jsp");
                }

            }

            if (val.equals("9")) {

                String Hospital_code = request.getParameter("hospital_code");
                ArrayList<HashMap<String, String>> data = st.get_test_data_assigned_all();
                int i = 0;
                for (HashMap<String, String> cd : data) {
                    i++;
                    out.println(" <tr>");
                    out.println("<td><input type='checkbox' onclick='assign_test(" + i + ")' id='chk_" + i + "'></td>");
                    out.println("<td>" + cd.get("patient_name") + "<input type='hidden' name='patient_id' value='" + cd.get("patient_id") + "' id='patient_id_" + i + "' ><input type='hidden' value='" + cd.get("test_id") + "' id='test_id_" + i + "' ></td>");
                    out.println("<td><span class='badge bg-primary'>" + cd.get("age") + "</td>");
                    out.println("<td><span class='badge bg-warning'>" + cd.get("lab_test_name") + "</td>");
                    if (cd.get("test_sts").equals("Pending")) {
                        out.println("<td><span class='badge bg-danger'>" + cd.get("test_sts") + "</td>");
                    } else {
                        out.println("<td><span class='badge bg-success'>" + cd.get("test_sts") + "</td>");
                    }
                    if (cd.get("test_sts").equals("Pending")) {
                        out.println("<td><button class='btn btn-success' type='button' onclick='change_test_sts(" + i + ")'>Done</button></td>");
                    } else {
                        out.println("<td><button class='btn btn-success' type='button' data-toggle=\"modal\" data-target=\"#exampleModalCenter\">Generate Report</button></td>");
                    }
                    out.println("</tr>");

                }
            }

//        for lab test dues 
            if (val.equals("10")) {

                String Hospital_code = request.getParameter("hospital_code");
                ArrayList<HashMap<String, String>> data = st.get_test_data_assigned_dues();
                int i = 0;
                for (HashMap<String, String> cd : data) {
                    i++;
                    out.println(" <tr>");
                    out.println(" <td></td>");
                    out.println("</tr>");

                }
            }

            //for patient wise Payemnet Data 
            if (val.equals("11")) {

                String Patient_id = request.getParameter("Patient_id");

                int discount = st.Get_discount_of_BIll(Patient_id);
                System.out.println("____>" + Patient_id);
                ArrayList<HashMap<String, String>> data = st.Get_patient_wise_payment_data(Patient_id);

                float otherVoucherPaidAmt = st.Get_patient_wise_Voucher_data(Patient_id);
                String BILL_PAY_STS = st.Check_BILL_payemnet(Patient_id);
                String BILL_approval = st.Check_BILL_Arroval(Patient_id);

                //for geeting the datetime in java 
                LocalDateTime now = LocalDateTime.now();

                // Format the date and time to match the required HTML format (yyyy-MM-dd'T'HH:mm)
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
                String formattedDateTime = now.format(formatter);

                int i = 0;

                float chargeRate = 0;
                float chargeQty = 0;
                float chargePaid = 0;
                float chargeleft = 0;
                float chargeTotal = 0;
                float grossTotal = 0;
                float grossPaidTotal = 0;
                float discountTotal = 0;
                float totalAmountLeft = 0;

                String Cat = "";

                String old_category_name = "";

                //
                for (HashMap<String, String> cd : data) {
                    grossTotal = grossTotal + Float.parseFloat(cd.get("total_amount"));
                    grossPaidTotal = grossPaidTotal + Float.parseFloat(cd.get("oc_amt_paid"));

                    //for decimal format 
                    Cat = cd.get("category_name");
                    if (Cat.equals(old_category_name)) {

                        old_category_name = Cat;
                        out.println(" <tr style='background-color:white;border-bottom:none'>");
                        out.println("<td hidden>" + i + "</td>");

                        out.println("<td>" + cd.get("Charge_name") + ""
                                + "<input type='hidden' name='charge_name' value='" + cd.get("Charge_name") + "'>"
                                + "</td>");

                        if (cd.get("hidden_charge").equalsIgnoreCase("Room")) {

                            if (cd.get("end_date") != null) {
                                out.println("<td hidden>" + cd.get("bed_use_date") + "/ " + cd.get("end_date") + "</td>");

                            } else {
                                out.println("<td hidden><div class='col-md-12 row'><div class='col-md-5'>"
                                        + "<input type='text' readonly class=\"form-control\" value='" + cd.get("bed_use_date") + "' id='start_date_" + cd.get("charge_id") + "' name='start_date_" + cd.get("charge_id") + "'></input>"
                                        + "<input type='hidden' name='' value='" + cd.get("Charge_name") + "'></div>"
                                        + "<div class='col-md-1'>/</div><div class='col-md-5'>"
                                        + "<input type=\"datetime-local\" class=\"form-control\" value='" + formattedDateTime + "' id='end_date_" + cd.get("charge_id") + "' name='end_date' onchange=\"calculateDateDifference('" + cd.get("charge_id") + "');\"></input>"
                                        + "</div></div></td>");
                            }
                        } else {
                            out.println("<td hidden>" + cd.get("bed_use_date") + "</td>");
                        }

                        out.println("<td >" + cd.get("charge_amount") + "<input type='hidden' value='" + cd.get("charge_amount") + "' name='charge_rate_" + cd.get("charge_id") + "' id='charge_rate_" + cd.get("charge_id") + "'>"
                                + "<input type='hidden' value='" + cd.get("charge_amount") + "' name='charge_rate'>"
                                + "<input type='hidden' name='patient_id' value='" + cd.get("patient_id") + "'>"
                                + "<input type='hidden' name='Charge_category' value='" + cd.get("category_name") + "'></td>");
                        out.println("<td><span id='span_qty_" + cd.get("charge_id") + "'>" + cd.get("qty") + "</span>"
                                + "<input type='hidden' value='" + cd.get("qty") + "' name='qty' id='qty_changed_" + cd.get("charge_id") + "'>"
                                + "<input type='hidden' value='" + cd.get("qty") + "' name='qty_" + cd.get("charge_id") + "' id='qty_" + cd.get("charge_id") + "'>"
                                + "</td>");
                        out.println("<td  hidden>" + cd.get("oc_amt_paid") + ""
                                + "<input type='hidden' value='" + cd.get("oc_amt_paid") + "' name='amt_paid_" + cd.get("charge_id") + "' id='amt_paid_" + cd.get("charge_id") + "'>"
                                + "<input type='hidden' value='" + cd.get("oc_amt_left") + "' name='amt_left_" + cd.get("charge_id") + "' id='amt_left_" + cd.get("charge_id") + "'>"
                                + "</td>");

                        out.println("<td><span id='span_total_amount_" + cd.get("charge_id") + "'>" + cd.get("total_amount") + "</span>"
                                + "<input type='hidden' value='" + cd.get("total_amount") + "' name='total_amount_" + cd.get("charge_id") + "' id='total_amount_" + cd.get("charge_id") + "'>"
                                + "<input type='hidden' name='total_amount' value='" + cd.get("total_amount") + "'   id='total_amount_changed_" + cd.get("charge_id") + "' >"
                                + "<input type='hidden' value=" + cd.get("total_amount") + " name='" + cd.get("hidden_charge") + "_charge_pay'>"
                                + "                      <input type='hidden' value='" + cd.get("patient_location_rwid") + "' name='" + cd.get("hidden_charge") + "_charge_rwid'>"
                                + "                   <input type='hidden' value='" + cd.get("charge_id") + "'  name='" + cd.get("Charge_name") + "_charge_id'>"
                                + "</td>");

                        out.println("</tr>");
                        i++;

                    } else {

                        old_category_name = Cat;

                        out.println(" <tr style='background-color:white;border-bottom:none'>");
                        out.println("<td hidden>" + i + "</td>");
                        out.println("<td style=\"style=\"max-height: 1px;\" ><b>" + Cat + "</b></td>");
                        out.println("<td></td>");
                        out.println("<td style=\"style=\"max-height: 1px;\"></td>");
                        out.println("<td style=\"style=\"max-height: 1px;\"></td>");
                        out.println("<td style=\"style=\"max-height: 1px;\" hidden></td>");
                        out.println("</tr>");

                        //
                        out.println(" <tr style='background-color:white'>");
                        out.println("<td hidden>" + i + "</td>");

                        out.println("<td>" + cd.get("Charge_name") + ""
                                + "<input type='hidden' name='charge_name' value='" + cd.get("Charge_name") + "'></td>");

                        if (cd.get("hidden_charge").equalsIgnoreCase("Room")) {

                            if (cd.get("end_date") != null) {
                                out.println("<td hidden>" + cd.get("bed_use_date") + "/ " + cd.get("end_date") + "</td>");
                            } else {
                                out.println("<td hidden><div class='col-md-12 row'><div class='col-md-5'>"
                                        + "<input type='text' readonly class=\"form-control\" value='" + cd.get("bed_use_date") + "' id='start_date_" + cd.get("charge_id") + "' name='start_date_" + cd.get("charge_id") + "'></input></div>"
                                        + "<div class='col-md-1'>/</div><div class='col-md-5'>"
                                        + "<input type=\"datetime-local\" class=\"form-control\" value=" + formattedDateTime + " id='end_date_" + cd.get("charge_id") + "' name='end_date' onchange=\"calculateDateDifference('" + cd.get("charge_id") + "');\"></input>"
                                        + "</div></div></td>");
                            }
                        } else {
                            out.println("<td hidden>" + cd.get("bed_use_date") + "</td>");
                        }

                        out.println("<td >" + cd.get("charge_amount") + "<input type='hidden' value='" + cd.get("charge_amount") + "' name='charge_rate_" + cd.get("charge_id") + "' id='charge_rate_" + cd.get("charge_id") + "'>"
                                + "<input type='hidden' value='" + cd.get("charge_amount") + "' name='charge_rate'>"
                                + "<input type='hidden' name='patient_id' value='" + cd.get("patient_id") + "'>"
                                + "<input type='hidden' name='Charge_category' value='" + cd.get("category_name") + "'></td>");
                        out.println("<td><span id='span_qty_" + cd.get("charge_id") + "'>" + cd.get("qty") + "</span>"
                                + "<input type='hidden' value='" + cd.get("qty") + "' name='qty'  id='qty_changed_" + cd.get("charge_id") + "'>"
                                + "<input type='hidden' value='" + cd.get("qty") + "' name='qty_" + cd.get("charge_id") + "' id='qty_" + cd.get("charge_id") + "'>"
                                + "</td>");
                        out.println("<td hidden>" + cd.get("oc_amt_paid") + ""
                                + "<input type='hidden' value='" + cd.get("oc_amt_paid") + "' name='amt_paid_" + cd.get("charge_id") + "' id='amt_paid_" + cd.get("charge_id") + "'>"
                                + "<input type='hidden' value='" + cd.get("oc_amt_left") + "' name='amt_left_" + cd.get("charge_id") + "' id='amt_left_" + cd.get("charge_id") + "'>"
                                + "</td>");
                        out.println("<td><span id='span_total_amount_" + cd.get("charge_id") + "'>" + cd.get("total_amount") + "</span>"
                                + "<input type='hidden' name='total_amount' value='" + cd.get("total_amount") + "'    id='total_amount_changed_" + cd.get("charge_id") + "'>"
                                + "<input type='hidden' value='" + cd.get("total_amount") + "' name='total_amount_" + cd.get("charge_id") + "' id='total_amount_" + cd.get("charge_id") + "'>"
                                + "<input type='hidden' value=" + cd.get("total_amount") + " name='" + cd.get("Charge_name") + "_charge_pay'>"
                                + "                      <input type='hidden' value='" + cd.get("patient_location_rwid") + "' name='" + cd.get("Charge_name") + "_charge_rwid'>"
                                + "                   <input type='hidden' value='" + cd.get("charge_id") + "'  name='" + cd.get("hidden_charge") + "_charge_id'>"
                                + "</td>");

                        out.println("</tr>");
                        i++;
                    }

                }

                out.println(" <tr>");

                if (BILL_PAY_STS.equals("")) {
                    out.println("<td></td>");
                } else {
                    out.println("<td><div class=\"col-md-3 mt-0\">\n"
                            + "                                            <img src=\"../Design/Recieved.png\" width=\"100px\" height=\"100px\"    alt=\"alt\"/>\n"
                            + "                                            </div></td>");

                }

                if (BILL_approval.equals("Provisional_Bill")) {

                    out.println("<td> "
                            + "<div class=\"col-md-3 mt-0\">\n"
                            + "                                            <img src=\"../Design/pending_Approval.png\" width=\"200px\" height=\"100px\"    alt=\"alt\"/>\n"
                            + "                                            </div>"
                            + "</td>");
                } else {
                    out.println("<td></td>");
                }

                if (BILL_approval.equals("Approved")) {
                    out.println("<td> <div class=\"col-md-3 mt-0\">\n"
                            + "                                            <img src=\"../Design/Approved.png\" width=\"200px\" height=\"100px\"    alt=\"alt\"/>\n"
                            + "                                            </div></td></td>");
                } else {
                    out.println("<td hidden></td>");

                }

                float finalLeftAmt = (grossTotal) - ((otherVoucherPaidAmt) + discount);

                out.println("<td hidden></td>");
                out.println("<td hidden></td>");
                out.println("<td><b>Gross Amount</b><br> Total Paid Amt  <br> Discount<br><b>Total Pending </b><br>"
                        + "</td>");
                out.println("<td><input type='hidden' id='bill_gross_amount' name='bill_gross_amount' value='" + grossTotal + "'><span id='span_grossTotal'>" + grossTotal + "</span><br>"
                        + "<span id='Total_bill_paid_show' >" + (otherVoucherPaidAmt) + "<input type='hidden' id='Bill_Total_bill_paid' name='Bill_Total_bill_paid' value='" + (grossPaidTotal + otherVoucherPaidAmt) + "'><br>"
                        + "" + discount + "<br>"
                        + "<span id='span_finalLeftAmt'>" + finalLeftAmt + "</span><input type='hidden' value='" + finalLeftAmt + "' id='Bill_total_amount_left'></td>");
                out.println("</tr>");

            }

            //for final bill seettelment 
            if (val.equals("13")) {

                String sessionToken = (String) session.getAttribute("formToken");
                String requestToken = request.getParameter("formToken");

                //for final bill settle Tab 
                String patient_id = request.getParameter("patient_id");
                String user = request.getParameter("user");
                String Gross_amount = request.getParameter("Gross_amount");
                String total_voc_amount = request.getParameter("total_voc_amount");
                String Discount = request.getParameter("Discount");
                String Net_amount = request.getParameter("Net_amount");
                String final_bill_romm_date = request.getParameter("end_date");

                //for room 
                String[] Room_amount = request.getParameterValues("Room_charge_pay");
                String[] Room_Room_id = request.getParameterValues("Room_charge_id");
                String[] room_charge_rwid = request.getParameterValues("Room_charge_rwid");

                //for saving the patient total bill 
                String category_name[] = request.getParameterValues("Charge_category");
                String charge_name[] = request.getParameterValues("charge_name");

                String qty[] = request.getParameterValues("qty");
                String total_amount[] = request.getParameterValues("total_amount");
                String charge_rate[] = request.getParameterValues("charge_rate");

                //for saving payemnt details 
                String Received_from = request.getParameter("Received_from");
                String Pay_type = request.getParameter("Pay_type");
                String Debit_Ac = request.getParameter("Debit_Ac");
                String Checque_number = request.getParameter("Checque_number");
                String Card_number = request.getParameter("Card_number");
                String UPI_type = request.getParameter("UPI_type");
                String transcation_id = request.getParameter("transcation_id");

                //for ayushmaan Patient
                String tpa_name = request.getParameter("TPA_name");

                String Approval_amount = request.getParameter("Approval_amount");
                String Credit_remark = request.getParameter("Credit_remark");

                System.out.println("------TPANAM" + tpa_name);
                String sts = "";
                if (sessionToken != null && sessionToken.equals(requestToken)) {
                    sts = st.Save_final_bill(patient_id, user, Gross_amount, total_voc_amount, Discount, Net_amount, Room_amount, Room_Room_id, room_charge_rwid,
                            category_name, charge_name, qty, total_amount, charge_rate, final_bill_romm_date, Received_from, Pay_type, Debit_Ac, Checque_number, Card_number, UPI_type, transcation_id, tpa_name, Approval_amount, Credit_remark);
                }
                HashMap<String, String> altMap = new HashMap<>();
                altMap.put("Titel", "Final Bill Cleared");
                altMap.put("Alt_Msg", "Patient ok To Discharge");
                altMap.put("Alt_Type", "Success");

                System.out.println("STTTTTTTTTTTTTTTSS" + sts);

                if (sts != null) {
                    {
                        altMap.put("Alt_Msg", "Final Bill Cleared");
                        altMap.put("Alt_Type", "success");
                    }
                    session.setAttribute("Alt_Data", altMap);
                    response.sendRedirect("Main/Laboratory/WLAB0004.jsp");
                } else {
                    {
                        altMap.put("Alt_Msg", "Final Bill Not Cleared");
                        altMap.put("Alt_Type", "error");
                    }
                    session.setAttribute("Alt_Data", altMap);
                    response.sendRedirect("Main/Laboratory/WLAB0004.jsp");

                }

            }

            //for total voc 
            if (val.equals("14")) {

                String Patient_id = request.getParameter("Patient_id");
                ArrayList<HashMap<String, String>> data = st.get_total_voc_data(Patient_id);
                int i = 0;
                for (HashMap<String, String> cd : data) {
                    i++;
                    out.println(" <tr>");

                    out.println("<td contenteditable=\"true\">" + cd.get("pay_date") + "</td>");
                    out.println("<td contenteditable=\"true\">" + cd.get("amount") + " </td>");
                    out.println("<td contenteditable=\"true\">" + cd.get("reamrk") + "</td>");

                    out.println("</tr>");

                }
            }

            if (val.equals("15")) {

                String Patient_id = request.getParameter("Patient_id");
                String data = st.Check_ayushMaan(Patient_id);
                int i = 0;
                out.println(data);
            }

            //for admission date 
            if (val.equals("16")) {

                String Patient_id = request.getParameter("Patient_id");
                String data = st.get_admission_date(Patient_id);
                int i = 0;
                out.println(data.trim());
            }

            //for checiking the final bill paid status 
            if (val.equals("17")) {

                String Patient_id = request.getParameter("Patient_id");
                String data = st.check_bill_status(Patient_id);
                int i = 0;
                out.println(data);
            }

            //for cancling Bill 
            if (val.equals("18")) {
                String Patient_id = request.getParameter("Patient_id");
                String data = st.cancel_final_bill(Patient_id);
                int i = 0;
                out.println(data);
            }

            //for patient details 
            if (val.equals("19")) {

                String Patient_id = request.getParameter("Patient_id");
                ArrayList<HashMap<String, String>> data = st.get_patient_data(Patient_id);
                int i = 0;
                for (HashMap<String, String> cd : data) {
                    i++;
                    out.println(" <tr>");

                    out.println("<Center><img src=\"../../Staff/images/patient-" + Patient_id.replace('/', '-') + ".jpg\" class=\"img-thumbnail\" onerror=\"this.onerror=null; this.src='../Design/avatr.jpg'\" alt=\"\"  style='height:150px;width:150px' ></center>\n"
                            + "                                                   <hr>\n"
                            + "                                                 \n"
                            + "                                                <div class=\"row\">\n"
                            + "                                                  <div class=\"col-md-12\">\n"
                            + "                                                <center> <b>" + cd.get("name") + "</b></center>\n"
                            + "                                                 </div>\n"
                            + "                                                    <div class=\"col-md-12\">\n"
                            + "                                                <center><b>" + cd.get("bed") + "</b></center>\n"
                            + "                                                 </div>\n"
                            + "                                                    <div class=\"col-md-12\">\n"
                            + "                                                <center> <b>" + cd.get("admit_date") + "</b></center>\n"
                            + "                                                 </div>\n"
                            + "                                                     <div class=\"col-md-12\">\n"
                            + "                                                <center> <b>" + cd.get("Dis_date") + "</b></center>\n"
                            + "                                                 </div>"
                            + "<div class=\"col-md-12\">\n"
                            + "                                                <center> <b>" + cd.get("bill_status") + "</b></center>\n"
                            + "                                                 </div>"
                            + "\n"
                            + "                                                </div>");

                    out.println("</tr>");

                }
            }

        } catch (Exception Ex) {
            Logger.getLogger(WLAB0001_Serv.class.getName()).log(Level.SEVERE, null, Ex);
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

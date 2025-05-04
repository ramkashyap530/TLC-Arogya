/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package IPD;

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
import javax.servlet.http.Part;

/**
 *
 * @author Tarun
 */
@WebServlet(name = "WIPD0002_serv", urlPatterns = {"/WIPD0002_serv"})
public class WIPD0002_serv extends HttpServlet {

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
        String val = request.getParameter("value");
        HttpSession session = request.getSession();
        WIPD0002_Dao st = new WIPD0002_Dao();

        try {

            if (val.equals("1")) {

                String arr[] = {request.getParameter("hospital_code"), request.getParameter("date_from"),
                    request.getParameter("date_to"), request.getParameter("patient_name"), request.getParameter("type")};

                //System.out.println("===" + arr);
                float tot_amt = 0, left_amt = 0, paid_amt = 0, TotRoomCharge = 0, TotLabCharge = 0, TotOtCharge = 0, TotOtherCharge = 0;

                ArrayList<HashMap<String, String>> data = st.get_total_oprec(arr);

                int i = 0;
                for (HashMap<String, String> cd : data) {
                    tot_amt = Float.parseFloat(cd.get("tot_amt"));
                    TotRoomCharge = 0;
                    paid_amt = Float.parseFloat(cd.get("tot_paid_amt"));

                    ArrayList<HashMap<String, String>> get_room_data = st.get_room_charge_hi(cd.get("patient_id"));
                    for (HashMap<String, String> dataNow : get_room_data) {
                        TotRoomCharge = TotRoomCharge + Float.parseFloat(dataNow.get("tot_room_amt"));
                    }
                    
                    tot_amt += TotRoomCharge;
                    left_amt = tot_amt - paid_amt;
                    i++;
                    out.println(" <tr>");
                    out.println("<td style=\"white-space: nowrap\">" +cd.get("patient_id").toString()+" <br> <b>"+cd.get("tpa_name")+"</b></td>");
                    
                    //for action buttons 
                    
                    if (cd.get("pt_sts").equals("Discharged")) {
                        out.println("<td><button class='btn btn' onclick='window.open(\"../../WDISPAT?patient_id=" + cd.get("patient_id").toString() + "\");'><i class='fa fa-print'></i></button>"
                                + "<button class='b"
                                + ""
                                + ""
                                + "tn btn' onclick='window.open(\"../../WFACE_SHEET_PRINT?patient_id=" + cd.get("patient_id").toString() + "\");'><i class='fa fa-user'></i></button>"
                                + ""
                                + "<button class='btn btn'><i class='fa fa-folder'></i></button></td>");

                    } else {
                        out.println("<td style=\"white-space: nowrap\">"
                                + "<button class='btn ' type='button' title='Edit Patient' onclick='window.open(\"../../Main/IPD/WIPD0001.jsp?patient_id=" + cd.get("patient_id").toString() + "\");' style=''><i class='fa fa-edit' style='color: #138d75;  font-size: 20px; /* Icon size */ text-shadow: 3px 3px 5px rgba(0, 0, 0, 0.4);'></i></button>"
                                + "<button class='btn ' type='button' data-toggle=\"modal\" title='Payment Receiet'  data-target=\"#myModal_Test\" onclick='patient_payment(" + i + ")'><i class='fa fa-paypal' style='color: #28a745;  font-size: 20px; /* Icon size */ text-shadow: 3px 3px 5px rgba(0, 0, 0, 0.4);'></i></button>"
                                        
                                        + "<button class='btn '><i class='fa fa-folder' style='color: #D68910;  font-size: 20px; /* Icon size */ text-shadow: 3px 3px 5px rgba(0, 0, 0, 0.4);'></i></button>"
                                       
                                  
                                + "<button class='btn ' title='Face Sheet' onclick='window.open(\"../../WFACE_SHEET_PRINT?patient_id=" + cd.get("patient_id").toString() + "\");'><i class='fa fa-user' style='color: #5499c7;  font-size: 20px; /* Icon size */ text-shadow: 3px 3px 5px rgba(0, 0, 0, 0.4);'></i></button>"
                                + "<button class='btn '  title='Shift Bed'    data-toggle=\"modal\" data-target=\"#myModal_BED\" onclick='get_current_bed(" + i + ")'><i class='fa fa-bed'  style='color: #D35400;  font-size: 20px; /* Icon size */ text-shadow: 3px 3px 5px rgba(0, 0, 0, 0.4);'></i></button>"
                                + "<button class='btn ' type='button' title='Discharge Patient'   data-toggle=\"modal\" data-target=\"#myModal\" onclick='get_test_his(" + i + ");getToTFinal(" + i + ")'><i class='fa fa-wheelchair' style=' color: #3498db; /* Blue color */\n" +
"            font-size: 30px; /* Icon size */\n" +
"            text-shadow: 3px 3px 5px rgba(0, 0, 0, 0.4);'></i></button>"
                                        + ""
                                        + ""
                                        + "<button class='btn '  title='Patient Journey'    data-toggle=\"modal\" data-target=\"#myModal_Journey\" onclick='patient_history("+i+")'><i class=\"fa fa-road\"  style='color: #D35400;  font-size: 20px; /* Icon size */ text-shadow: 3px 3px 5px rgba(0, 0, 0, 0.4);'></i>"
                                        + "</td>");
                    }
                    
                    

                    out.println("<td style=\"white-space: nowrap\"><b>" + cd.get("patient_name") + "</b><input type=hidden name='staff_id'  value='" + cd.get("patient_id") + "' id='id_" + i + "'></td>");
                     out.println("<td style=\"white-space: nowrap\">"+cd.get("UHID")+"</td>");
                      out.println("<td style=\"white-space: nowrap\">"+cd.get("ipd_no")+"</td>");
                    out.println("<td style=\"white-space: nowrap\"><img src=\"../../Staff/images/patient-" + cd.get("patient_id").replace('/', '-') + ".jpg\" class=\"img-thumbnail\" onerror=\"this.onerror=null; this.src='../Design/avatr.jpg'\" alt=\"\"  style='height:50px;width:50px' ></td></td>");
                    out.println("<td style=\"white-space: nowrap\"><img src=\"../../Staff/images/adhar-" + cd.get("patient_id").replace('/', '-') + ".jpg\" class=\"img-thumbnail\" onerror=\"this.onerror=null; this.src='../Design/adhar.png'\" alt=\"\"  style='height:50px;width:50px' ></td></td>");
                    
                    out.println("<td style=\"white-space: nowrap\"><span class='badge bg-warning'><b>" + cd.get("room_name") + "/"+cd.get("bed_number")+"</b></span></td>");
                    out.println("<td style=\"white-space: nowrap\"><span class='badge bg-primary'>" + cd.get("bed_number") + "</span> <input type='hidden' value='" + cd.get("bed_number") + "' id='bed_" + i + "'></td>");
                   
                
                    out.println("<td style=\"white-space: nowrap\"><span class='badge bg-primary'>" + tot_amt + "<input type='hidden' name='tot_amt' id='tot_amt_" + i + "' value='" + tot_amt + "' ></span></td>");
                    out.println("<td style=\"white-space: nowrap\"><span class='badge bg-primary'>" + paid_amt + "<input type='hidden' name='paid_amt' id='paid_amt_" + i + "' value='" + paid_amt + "' ></span></td>");
                    out.println("<td style=\"white-space: nowrap\"><span class='badge bg-primary'>" + left_amt + "<input type='hidden' name='left_amt' id='left_amt_" + i + "' value='" + left_amt + "' ></span></td>");
                    out.println("<td style=\"white-space: nowrap\">" + cd.get("doctor") + "</td>");

                    if (cd.get("pt_sts").equals("Admitted")) {
                        out.println("<td style=\"white-space: nowrap\"><span class='badge bg-success'>In Progress</span></td>");
                    }
                    if (cd.get("pt_sts").equals("Discharged")) {
                        out.println("<td style=\"white-space: nowrap\"><span class='badge bg-success'>Discharged</span></td>");
                    }

                    if (cd.get("lab_test_n").equals("N/A")) {
                        out.println("<td style=\"white-space: nowrap\"><span class='badge bg-danger'>" + cd.get("lab_test_n") + "</span></td>");
                    } else {
                        out.println("<td style=\"white-space: nowrap\"><span class='badge bg-success' onclick='window.open(\"../../Main/Laboratory/WLAB0004.jsp?patient_id=" + cd.get("patient_id").toString() + "\");'>" + cd.get("lab_test_n") + "</span></td>");
                    }

                    if (cd.get("operation_name").equals("N/A")) {
                        out.println("<td style=\"white-space: nowrap\"><span class='badge bg-danger'>" + cd.get("operation_name") + "</span></td>");
                    } else {
                        out.println("<td style=\"white-space: nowrap\"><span class='badge bg-success'>" + cd.get("operation_name") + "</span></td>");
                    }

                    out.println("<td style=\"white-space: nowrap\"><span class='badge bg-success'>" + cd.get("admision_date") + "</span></td>");

                    
                    out.println("</tr>");

                }

                out.println("<tr>"
                        + "<td></td>"
                        + "<td></td>"
                    
                        + "<td><span class='badge bg-primary'>Patient/Fathers</span></td>"
                            + "<td><span class='badge bg-primary'>UHID</span></td>"
                        + "<td><span class='badge bg-primary'>IPD</span></td>"
                        + "<td><span class='badge bg-primary'>Patient Photo</span></td>"
                        + "<td><span class='badge bg-primary'>Patient Adhar</span></td>"
                        + "<td><span class='badge bg-primary'>Room</span></td>"
                         + "<td><span class='badge bg-primary'>Bed No</span></td>"
                      
                        
                        + "<td><span class='badge bg-primary'>Total</span></td>"
                        + "<td><span class='badge bg-primary'>Total</span></td>"
                        + "<td><span class='badge bg-primary'>Total</span></td>"
                        + "<td></td>"
                        + "<td></td>"
                        + "<td></td>"
                        + "<td></td>"
                        + "<td></td>"
                       
                        + "</tr>");
            }

            if (val.equals("2")) {

                String Patient_id = request.getParameter("patient_id");

                ArrayList<HashMap<String, String>> data = st.get_test_hi(Patient_id);
                int i = 0;
                for (HashMap<String, String> cd : data) {
                    i++;
                    out.println(" <tr>");
                    out.println("<td style=\"white-space: nowrap\">" + cd.get("test_name") + "</td>");
                    out.println("<td style=\"white-space: nowrap\">" + cd.get("done_date") + "</td>");
                    out.println("<td style=\"white-space: nowrap\">" + cd.get("rates") + "<input type='hidden' class='total_amt' name='test_rates_amt' value='" + cd.get("rates") + "'></td>");
                    out.println("<td style=\"white-space: nowrap\">" + cd.get("test_paid_amt") + "<input type='hidden' class='' name='test_paid_amt' value='" + cd.get("test_paid_amt") + "'></td>");
                    out.println("<td style=\"white-space: nowrap\">" + cd.get("test_pending_amt") + "<input type='hidden' class='' name='test_pending_amt' value='" + cd.get("test_pending_amt") + "'></td>");
                    out.println("<td style=\"white-space: nowrap\">" + cd.get("done_sts") + "</td>");

                    out.println("</tr>");

                }
            }

            if (val.equals("3")) {
                String Patient_id = request.getParameter("patient_id");

                ArrayList<HashMap<String, String>> data = st.get_OT_hi(Patient_id);
                int i = 0;
                for (HashMap<String, String> cd : data) {
                    i++;
                    out.println(" <tr>");
                    out.println("<td style=\"white-space: nowrap\">" + cd.get("operation_name") + "</td>");
                    out.println("<td style=\"white-space: nowrap\">" + cd.get("created_date") + "</td>");
                    out.println("<td style=\"white-space: nowrap\">" + cd.get("ot_rate") + "<input type='hidden' class='total_amt' name='OT_rates_amt' value='" + cd.get("ot_rate") + "'></td>");
                    out.println("<td style=\"white-space: nowrap\">" + cd.get("pending_amount") + "</td>");

                    out.println("</tr>");

                }
            }

            if (val.equals("4")) {

                System.out.println("===>yanha tak aya");
                String sts = "";

                String hospital_code = request.getParameter("hospital_code");
                String Diagnosis = request.getParameter("Diagnosis");
                String condition = request.getParameter("condition");
                String Vitals = request.getParameter("Vitals");
                String History = request.getParameter("History");
                String patient_id_Modal = request.getParameter("patient_id_Modal");
                String user = request.getParameter("user");

                sts = st.discharge_patient(patient_id_Modal, hospital_code, Diagnosis, condition, Vitals, History, user);

                HashMap<String, String> altMap = new HashMap<>();
                altMap.put("Titel", "Patent Discharged ");
                altMap.put("Alt_Msg", "Some Error !!");
                altMap.put("Alt_Type", "danger");

                if (sts != null) {

                    {
                        altMap.put("Alt_Msg", "All Dues Cleared");
                        altMap.put("Alt_Type", "success");
                    }
                    session.setAttribute("Alt_Data", altMap);
                    response.sendRedirect("Main/IPD/WIPD0002.jsp");
                }

            }

            if (val.equals("5")) {
                String Patient_id = request.getParameter("patient_id");

                ArrayList<HashMap<String, String>> data = st.get_room_charge_hi(Patient_id);
                int i = 0;
                for (HashMap<String, String> cd : data) {
                    i++;
                    out.println(" <tr>");
                    out.println("<td style=\"white-space: nowrap\">" + cd.get("move_bed_id") + "</td>");
                    out.println("<td style=\"white-space: nowrap\">" + cd.get("start_time") + "</td>");
                    out.println("<td style=\"white-space: nowrap\">" + cd.get("end_time") + "</td>");
                    out.println("<td style=\"white-space: nowrap\">" + cd.get("tot_room_amt") + " <input type='hidden' class='total_amt' name='Room_rates_amt' value='" + cd.get("tot_room_amt") + "'></td>");

                    out.println("</tr>");

                }
            }

//            for patient amount voucher 
            if (val.equals("6")) {

                System.out.println("===>yanha tak aya");
                String sts = "";

                String Toatl_amount_till_now = request.getParameter("Toatl_amount_till_now");
                String Amount_paid = request.getParameter("");
                String Amount_collecting = request.getParameter("Amount_collecting");

                String patient_id_mod = request.getParameter("patient_id_mod");
                String user = request.getParameter("user");
                String Remark = request.getParameter("payment_Remark");
                String recieved = request.getParameter("Received_from");
                
                
                String pay_type=request.getParameter("Pay_type");
                String Checque_number=request.getParameter("Checque_number");
                String Card_number=request.getParameter("Card_number");
                String UPI_type=request.getParameter("UPI_type");
                String transcation_id=request.getParameter("transcation_id");
                
                

                sts = st.ipd_payment_vouchers(Toatl_amount_till_now, Amount_paid, Amount_collecting, patient_id_mod, user, Remark,pay_type,Checque_number,Card_number,
              UPI_type,transcation_id,recieved);

                HashMap<String, String> altMap = new HashMap<>();
                altMap.put("Titel", "Amount Recivied");
                altMap.put("Alt_Msg", "Some Error !!");
                altMap.put("Alt_Type", "danger");

                if (sts != null) {

                    {
                        altMap.put("Alt_Msg",sts);
                        altMap.put("Alt_patient",patient_id_mod);
                        altMap.put("Alt_Type", "success");
                    }
                    session.setAttribute("Alt_Data", altMap);
                    response.sendRedirect("Main/IPD/WIPD0002.jsp");
                }

            }

//for voucher details
            if (val.equals("9")) {

                String arr[] = {request.getParameter("hospital_code"), request.getParameter("date_from"),
                    request.getParameter("date_to"), request.getParameter("patient_name"), request.getParameter("type")};

                System.out.println("===" + arr);

                ArrayList<HashMap<String, String>> data = st.get_total_oprec(arr);
                int i = 0;
                for (HashMap<String, String> cd : data) {
                    i++;
                    out.println(" <tr>");
                    out.println("<td style=\"white-space: nowrap\">" + i + "</td>");
                    out.println("<td style=\"white-space: nowrap\"><img src=\"../../Staff/images/patient-" + cd.get("patient_id").replace('/', '-') + ".jpg\" class=\"img-thumbnail\" onerror=\"this.onerror=null; this.src='../Design/avatr.jpg'\" alt=\"\"  style='height:50px;width:50px' ></td></td>");
                    out.println("<td style=\"white-space: nowrap\"><img src=\"../../Staff/images/adhar-" + cd.get("patient_id").replace('/', '-') + ".jpg\" class=\"img-thumbnail\" onerror=\"this.onerror=null; this.src='../Design/adhar.png'\" alt=\"\"  style='height:50px;width:50px' ></td></td>");
                    out.println("<td style=\"white-space: nowrap\">" + cd.get("patient_name") + "<input type=hidden name='staff_id'  value='" + cd.get("patient_id") + "' id='id_" + i + "'></td>");
                    out.println("<td style=\"white-space: nowrap\"><span class='badge bg-warning'>" + cd.get("room_name") + "</span></td>");
                    out.println("<td style=\"white-space: nowrap\"><span class='badge bg-primary'>" + cd.get("bed_number") + "</span></td>");
                    out.println("<td style=\"white-space: nowrap\"><span class='badge bg-primary'></span></td>");
                    out.println("<td style=\"white-space: nowrap\"><span class='badge bg-primary'></span></td>");
                    out.println("<td style=\"white-space: nowrap\"><span class='badge bg-primary'></span></td>");
                    out.println("<td style=\"white-space: nowrap\">" + cd.get("doctor") + "</td>");

                    out.println("</tr>");

                }

            }

            if (val.equals("10")) {
                String Patient_id = request.getParameter("patient_id");

                String arr[] = {};
                ArrayList<HashMap<String, String>> data = st.get_total_ipd_payemt(arr);
                int i = 0;
                for (HashMap<String, String> cd : data) {
                    i++;

                    out.println(" <tr>");
                    out.println("<td style=\"white-space: nowrap\">" + cd.get("patient_id") + "</td>");
                    out.println("<td style=\"white-space: nowrap\">" + cd.get("amount") + "</td>");
                    out.println("<td style=\"white-space: nowrap\">" + cd.get("payment_date") + "</td>");
                    out.println("<td style=\"white-space: nowrap\">" + cd.get("voucher") + "</td>");

                    out.println("</tr>");

                }
            }

            if (val.equals("11")) {
                String Patient_id = request.getParameter("patient_id");

                String room = st.get_current_room(Patient_id);

                out.println(room);

            }

            if (val.equals("12")) {

                System.out.println("===>yanha tak aya");
                String sts = "";

                String current_bed = request.getParameter("current_bed");
                String changed_bed = request.getParameter("Bed_number");
                String patient_id = request.getParameter("shift_patient_id");
                String user = request.getParameter("user");
                String change_date = request.getParameter("change_date");
                String remark = request.getParameter("remark");
                String charge_amount = request.getParameter("charge_amount");
                String cahrge_category = request.getParameter("room_no");

                sts = st.update_room(current_bed, changed_bed, patient_id, user, change_date, remark,charge_amount,cahrge_category);

                HashMap<String, String> altMap = new HashMap<>();
                altMap.put("Titel", "Patient Shifted Successfully");
                altMap.put("Alt_Msg", "Some Error !!");
                altMap.put("Alt_Type", "danger");

                if (sts != null) {

                    {
                        altMap.put("Alt_Msg", "Shifting Done");
                        altMap.put("Alt_Type", "success");
                    }
                    session.setAttribute("Alt_Data", altMap);
                    response.sendRedirect("Main/IPD/WIPD0002.jsp");
                }

            }

//             for other charges
            if (val.equals("13")) {

                String patient_id = request.getParameter("patient_id");
                ArrayList<HashMap<String, String>> data = st.get_other_charge_data();
                int i = 0;
                for (HashMap<String, String> cd : data) {
                    i++;
                    out.println(" <tr>");
                    out.println("<td><input type='checkbox' onclick='assign_test(" + i + ")' name='test_checkbox' id='chk_" + i + "' value='" + i + "'></td>");
                    out.println("<td>" + cd.get("charge_name") + "<input type='hidden'  name='charge_id' value='" + cd.get("charge_id") + "' id='test_id_" + i + "' disabled><input type='hidden' value='" + cd.get("charge_name") + "' id='test_name_" + i + "' disabled>"
                            + "<input type='hidden' value='" + cd.get("charge_rate") + "' id='test_rate_" + i + "' ></td>");
                    out.println("<td><span class='badge bg-success'>" + cd.get("charge_rate") + ""
                            + "<input type='hidden' name='rate' id='rate_" + i + "' value='" + cd.get("charge_rate") + "' disabled> </td>");
                    out.println("<td><input type='text' name='other_paid_amt' id='test_paid_amt_" + i + "' min='1' max='" + cd.get("charge_rate") + "' onkeyup=\"get_test_paidData('" + i + "');\" value='' disabled ></td>");
                    out.println("<td><input type='text' name='other_left_amt' id='test_left_amt_" + i + "' value='' disabled readonly ></td>");
//          out.println("<td><input type='text' name='submit_amt' id='submit_amt_"+i+"' value='"+cd.get("test_left_amt")+"'></td>");

                    out.println("</tr>");

                }
            }

//for saving the other charges 

if (val.equals("14")) {

        String[] other_charge_id = request.getParameterValues("charge_id");
        String[] other_paid_amt = request.getParameterValues("other_paid_amt");
        String[] other_pending_amt = request.getParameterValues("other_left_amt");
        

        String patient_id = request.getParameter("patient_name_id");
        String user = request.getParameter("user");
        String Total_Amount = request.getParameter("Total_Amount");
        String Amount_paid = request.getParameter("Amount_paid");
        String Amount_pending = request.getParameter("Amount_pending");

        boolean sts = false;

        sts = st.Save_other_charges(other_charge_id, patient_id, user, Total_Amount, Amount_paid, Amount_pending,other_paid_amt,other_pending_amt);

        HashMap<String, String> altMap = new HashMap<>();
        altMap.put("Titel", "Other Charged Assigned");
        altMap.put("Alt_Msg", "Some Error !!");
        altMap.put("Alt_Type", "danger");

        if (sts) {
          {
            altMap.put("Alt_Msg", "Assigned Success");
            altMap.put("Alt_Type", "success");
          }
          session.setAttribute("Alt_Data", altMap);
          response.sendRedirect("Main/IPD/WIPD0005.jsp");
        }

      }
//for geeting 
if (val.equals("14")) {

                String patient_id = request.getParameter("patient_id");
                ArrayList<HashMap<String, String>> data = st.get_other_charge_data_pending_amt(patient_id);
                int i = 0;
                for (HashMap<String, String> cd : data) {
                    i++;
                    out.println(" <tr>");
                    out.println("<td><input type='checkbox' onclick='collect_payment(" + i + ")' name='test_checkbox' id='chk_" + i + "' value='" + i + "'></td>");
                    out.println("<td>" + cd.get("charge_name") + ""
                            + "</td>");
                    out.println("<td><span class='badge bg-success'>" + cd.get("total_charge") + "</span>"
                            + "</td>");
                    out.println("<td><span class='badge bg-success'>"+cd.get("paid_amount")+"</span></td>");
                    out.println("<td><input type='number' name='other_paid_amt' id='paid_amt_" + i + "' min='1' max='" + cd.get("left_amount") + "' value='"+cd.get("left_amount")+"' disabled ></td>");
                    out.println("<td>"+cd.get("charge_date")+"</td>");
                    out.println("<td><button class='btn btn-primary' type='button'>Pay</button><input type='hidden' id='pay_id_"+i+"' value='"+cd.get("payment_id")+"'></td>");
//          out.println("<td><input type='text' name='submit_amt' id='submit_amt_"+i+"' value='"+cd.get("test_left_amt")+"'></td>");

                    out.println("</tr>");

                }
            }

//for geeting other charge at dischage page 
if (val.equals("15")) {
                String Patient_id5 = request.getParameter("patient_id");
                ArrayList<HashMap<String, String>> data7 = st.get_OC_hi(Patient_id5);
                int i7 = 0;
                
                for (HashMap<String, String> cd7 : data7) {
              
                    i7++;
                    out.println(" <tr>");
                    out.println("<td style=\"white-space: nowrap\">" + cd7.get("oc_name") + "</td>");
                    out.println("<td style=\"white-space: nowrap\">" + cd7.get("oc_final_amt") + "</td>");
                    out.println("<td style=\"white-space: nowrap\">" + cd7.get("oc_amt_left") + "</td>");
                    out.println("</tr>");
                }
            }


//for geteing all bills 

if (val.equals("16")) {
                String Patient_id = request.getParameter("Patient_id");
                String Bill_no = request.getParameter("BILL_NO");
                ArrayList<HashMap<String, String>> data7 = st.get_all_bill(Patient_id,Bill_no);
                int i = 0;
                
                for (HashMap<String, String> cd7 : data7) {
              
                   
                    out.println(" <tr>");
                    out.println("<td>"+i+"</td>");
                    out.println("<td style=\"white-space: nowrap\">" + cd7.get("bill_no") + " <input type='hidden' value='"+cd7.get("bill_no")+"' id='bill_no_"+i+"'></td>");
                    out.println("<td style=\"white-space: nowrap\">" + cd7.get("patient_id") + "<input type='hidden' value='"+cd7.get("patient_id")+"' id='patient_id_"+i+"'></td>");
                    out.println("<td style=\"white-space: nowrap\">" + cd7.get("Bill_gross_amount") + "</td>");
                    out.println("<td style=\"white-space: nowrap\">" + cd7.get("bill_discount") + "</td>");
                    out.println("<td style=\"white-space: nowrap\">" + cd7.get("final_bill_paid") + "</td>");
                    out.println("<td style=\"white-space: nowrap\">" + cd7.get("Bill_status") + "</td>");
                    out.println("<td style=\"white-space: nowrap\">" + cd7.get("created_by") + "</td>");
                    out.println("<td style=\"white-space: nowrap\">" + cd7.get("create_date") + "</td>");
                    
                   
                    if(cd7.get("Bill_status").equals("Approved")){
                            out.println("<td style=\"white-space: nowrap\"> "
                                    + ""
                                    + "<button class='btn btn-success' title='Print Bill' onclick='window.open(\"../../IPD_BILL_PRINT?patient_id=" + cd7.get("patient_id").toString() + "\");'> <i class='fa fa-print' area-hidden='true'></i></button></td> ");
                    }else{
                        out.println("<td style=\"white-space: nowrap\"><button type='button' class='btn btn-success' title='Approve Bill' onclick='Approve_bills("+i+")'><i class='fa fa-check' area-hidden='true'></i></button>"
                            
                            + "<button type='button' class='btn btn-danger' onclick='cancel_bill("+i+")'><i class='fa fa-times' title='Cancel Bill' area-hidden='true'></i></button> "
                                    + ""
                                    + "<button class='btn btn-success' title='Print Bill' onclick='window.open(\"../../IPD_BILL_PRINT?patient_id=" + cd7.get("patient_id").toString() + "\");'> <i class='fa fa-print' area-hidden='true'></i></button></td> ");
                    }
                    
                    out.println("</tr>");
                     i++;
                }
            }



//for Arroving all Bills 

if (val.equals("17")) {
                String Patient_id = request.getParameter("Patient_id");
                String Bill_no = request.getParameter("Bill_no");
                String Approve = st.Approve_BILL(Patient_id,Bill_no);
             
                int i7 = 0;
                
                  out.println("Approve");
                }




            

//for checking the room bed of patient 
if (val.equals("19")) {
                String Patient_id = request.getParameter("Patient_id");
                String room_category_id = request.getParameter("room_category_id");
                ArrayList<HashMap<String, String>> data7 = st.get_all_bill(Patient_id,room_category_id);
                int i = 0;
                
                for (HashMap<String, String> cd7 : data7) {
              
                   
                    out.println(" <tr>");
                    
                    out.println("</tr>");
                     i++;
                }
            }





//for gettting all vochers



 


//for fething bed location 

if (val.equals("20")) {
                String arr[] ={request.getParameter("Patient_id")};
                ArrayList<HashMap<String, String>> data7 = st.get_all_bed_location(arr);
                int i = 0;
                
                for (HashMap<String, String> cd7 : data7) {
              
                   
                    out.println(" <tr>");
                   
                    out.println("<td style=\"white-space: nowrap\">" + cd7.get("rwid") + " <input type='hidden' value='"+cd7.get("rwid")+"' id='rwid_"+i+"'></td>");
                    out.println("<td style=\"white-space: nowrap\">" + cd7.get("patient_id") + "<input type='hidden' value='"+cd7.get("patient_id")+"' id='patient_id_"+i+"'></td>");
                    out.println("<td style=\"white-space: nowrap\">" + cd7.get("patient_name") + "</td>");
                    out.println("<td style=\"white-space: nowrap\">" + cd7.get("previous_bed") + "</td>");
                    out.println("<td style=\"white-space: nowrap\">" + cd7.get("changed_room_bed_id") + "</td>");
                    out.println("<td style=\"white-space: nowrap\">" + cd7.get("changed_date") + "</td>");
                    out.println("<td style=\"white-space: nowrap\">" + cd7.get("end_date") + "</td>");
                    out.println("<td style=\"white-space: nowrap\">" + cd7.get("real_name") + "</td>");
                     out.println("<td style=\"white-space: nowrap\">" + cd7.get("status") + "</td>");
                     if(cd7.get("status").equals("Unauthorized")){
                         out.println("<td style=\"white-space: nowrap\">"
                            + "<button class='btn btn-danger' title='Authorize' onclick=Auth_bed_change("+i+")"
                            + "> <i class='fa fa-times' area-hidden='true'></i></button></td> ");
                    out.println("</tr>");
                     }else{
                         out.println("<td style=\"white-space: nowrap\">"
                            + "<button class='btn btn-success' title='Authorize' "
                            + "> <i class='fa fa-check' area-hidden='true'></i></button></td> ");
                    out.println("</tr>");
                     }
                    
                     i++;
                }
            }


//for Authorie room beds 

if (val.equals("21")) {
                String Patient_id = request.getParameter("Patient_id");
                String Rwid = request.getParameter("Rwid");
                String Approve = st.auth_room_change(Patient_id,Rwid);
             
                int i7 = 0;
                
                  out.println("Approve");
                }





//for patient Journey 

if (val.equals("22")) {
                String Patient_id = request.getParameter("patient_id");
               ArrayList<HashMap<String, String>> data = st.get_patient_journet(Patient_id);
                int i = 0;
                
                
                
                for (HashMap<String, String> cd : data) {
                    
                    String date_status=cd.get("date_status");
                        
                    //for printing the date and time 
                    out.println("<div class=\"uia-timeline__annual-sections\">");
                    
                    
                    if(cd.get("date_status").equalsIgnoreCase("New Date")){
                           out.println("<span class=\"uia-timeline__year\" aria-hidden=\"true\">"+cd.get("event_date")+"</span>");
                    
               
                    }
                         out.println("<div class=\"uia-timeline__groups\">");
                    //for printing inner section 
                           out.println("<section class=\"uia-timeline__group\" aria-labelledby=\"timeline-demo-1-heading-1\">\n" +
"						<div class=\"uia-timeline__point uia-card\" data-uia-card-skin=\"1\" data-uia-card-mod=\"1\">\n" +
"							<div class=\"uia-card__container\">\n" +
"								<div class=\"uia-card__intro\">\n" +
"									\n" +
"									<span class=\"uia-card__time\">\n" +
"										<time datetime=\"2008-02-02\">\n" +
"											<span class=\"uia-card__day\">"+cd.get("other_info")+"</span>\n" +
"											\n" +
"										</time>\n" +
"									</span>\n" +
"								</div>\n" +
"								<div class=\"uia-card__body\">\n" +
"									<div class=\"uia-card__description\">\n" +
"										<p>"+cd.get("description")+"  /   Event Time :-"+cd.get("event_time")+"</p>\n" +
"									</div>\n" +
"								</div>\n" +
"							</div>\n" +
"						</div>\n" +
"					</section>");

                    
                    out.println("</div>");
             
             
                    
                                 }
                
                
                
}




//for gettting all vochers 
    
   
if (val.equals("24")) {
                String Patient_id = request.getParameter("Patient_id");
                String Bill_no = request.getParameter("BILL_NO");
                ArrayList<HashMap<String, String>> data7 = st.get_all_Vochers(Patient_id,Bill_no);
                int i = 0;
                
                for (HashMap<String, String> cd7 : data7) {
              
                   
                    out.println(" <tr>");
                    out.println("<td>"+i+"</td>");
                    out.println("<td style=\"white-space: nowrap\">" + cd7.get("voucher_id") + " <input type='hidden' value='"+cd7.get("voucher_id")+"' id='Voc_id_"+i+"'></td>");
                    out.println("<td style=\"white-space: nowrap\">" + cd7.get("patient_id") + "<input type='hidden' value='"+cd7.get("patient_id")+"' id='patient_id_"+i+"'></td>");
                    out.println("<td style=\"white-space: nowrap\">" + cd7.get("patient_name") + "</td>");
                    out.println("<td style=\"white-space: nowrap\">" + cd7.get("amount") + "</td>");
                    out.println("<td style=\"white-space: nowrap\">" + cd7.get("type") + "</td>");
                    out.println("<td style=\"white-space: nowrap\">" + cd7.get("pay_date") + "</td>");
                    out.println("<td style=\"white-space: nowrap\">" + cd7.get("remark") + "</td>");
                    out.println("<td style=\"white-space: nowrap\">" + cd7.get("recieved_from") + "</td>");
                     out.println("<td style=\"white-space: nowrap\">" + cd7.get("real_name") + "</td>");
                    out.println("<td style=\"white-space: nowrap\">"
                            + "<button class='btn btn-success' title='Print Slip' "
                            + "onclick='window.open(\"../../IPD_SLIP?Voc_id="+cd7.get("voucher_id")+"\");'> <i class='fa fa-print' area-hidden='true'></i></button></td> ");
                    out.println("</tr>");
                     i++;
                }
            }

            
 

        } catch (Exception Ex) {
            Logger.getLogger(WIPD0001_SERV.class.getName()).log(Level.SEVERE, (String) null, (Throwable) Ex);
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
            Logger.getLogger(WIPD0002_serv.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(WIPD0002_serv.class.getName()).log(Level.SEVERE, null, ex);
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

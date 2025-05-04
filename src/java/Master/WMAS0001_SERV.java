/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Master;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLDataException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
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
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 50)
@WebServlet(name = "WMAS0001_SERV", urlPatterns = {"/WMAS0001_SERV"})
public class WMAS0001_SERV extends HttpServlet {

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
            throws ServletException, IOException, SQLDataException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        WMAS0001_Dao st = new WMAS0001_Dao();
        String val = request.getParameter("value");

        try {

            if (val.equals("1")) {
                ArrayList<HashMap<String, String>> data = st.getmodule();
                int i = 0;
                for (HashMap<String, String> cd : data) {
                    i++;
                    out.println("<div class=\"card\" style=\"border:1px solid gray\">");
                    out.println("<div class=\"card-block\">");
                    out.println("<div class=\"row\">");
                    out.println("<div class=\"col-md-1\">");
                    out.println("<center> <input type=\"checkbox\" checked=\"\"  id='chk_" + i + "'  onclick=check(" + i + ")></center>");
                    out.println("</div>");
                    out.println("<div class=\"col-md-10\">");
                    out.println("<center><span>" + cd.get("modulenm") + "</span><input type='hidden'  id='mdid_" + i + "' name='module' value='" + cd.get("mdid") + "'><input type='hidden'   id='smdid_" + i + "'   value=" + cd.get("modulenm") + "></center>");
                    out.println("</div>");
                    out.println("</div>");
                    out.println("</div>");
                    out.println("</div>");

                }
            }

//            for saving the module assigned
            if (val.equals("2")) {
                boolean sts = false;

                String mem = request.getParameter("mem");
                System.out.println("---->Yanha tak aya ");
                String[] module = request.getParameterValues("module");

                sts = st.save(mem, module);

                HashMap<String, String> altMap = new HashMap<>();
                altMap.put("Titel", "Module Assigned");
                altMap.put("Alt_Msg", "Some Error !!");
                altMap.put("Alt_Type", "danger");

                if (sts) {
                    {
                        altMap.put("Alt_Msg", " Module Assigned Successfully");
                        altMap.put("Alt_Type", "success");
                    }
                    session.setAttribute("Alt_Data", altMap);
                    response.sendRedirect("Main/Master/WMAS0001.jsp");
                }

            }

            if (val.equals("3")) {

                String usr = request.getParameter("usr");
                ArrayList<String> data = st.getdata("3", usr);
                out.println("<option></option>");
                for (String cd : data) {

                    out.println(cd);
                }
            }

            if (val.equals("4")) {
                boolean sts = false;

                String modulenm = request.getParameter("modulename");
                String Icon = request.getParameter("icon");

                System.out.println("---->Yanha tak aya ");

                sts = st.save_module(modulenm, Icon);

                HashMap<String, String> altMap = new HashMap<>();
                altMap.put("Titel", "Success");
                altMap.put("Alt_Msg", "Some Error !!");
                altMap.put("Alt_Type", "danger");

                if (sts) {
                    {
                        altMap.put("Alt_Msg", "Saved Successfully");
                        altMap.put("Alt_Type", "success");
                    }
                    session.setAttribute("Alt_Data", altMap);
                    response.sendRedirect("Main/Master/WMAS0002.jsp");
                }

            }

            if (val.equals("5")) {
                boolean sts = false;

                String submdid = request.getParameter("submdid");
                String Mdid = request.getParameter("Mdid");
                String sub_module_name = request.getParameter("sub_module_name");
                String url = request.getParameter("url");

                System.out.println("---->Yanha tak aya ");

                sts = st.save_sbmodule(submdid, Mdid, sub_module_name, url);

                HashMap<String, String> altMap = new HashMap<>();
                altMap.put("Titel", "Success");
                altMap.put("Alt_Msg", "Some Error !!");
                altMap.put("Alt_Type", "danger");

                if (sts) {
                    {
                        altMap.put("Alt_Msg", "Saved Successfully");
                        altMap.put("Alt_Type", "success");
                    }
                    session.setAttribute("Alt_Data", altMap);
                    response.sendRedirect("Main/Master/WMAS0002.jsp");
                }

            }

            if (val.equals("6")) {
                int i = 0;
                String usr_1 = request.getParameter("usr_1");
                String usr = request.getParameter("usr");
                ArrayList<HashMap<String, String>> data_1 = st.getsubmodule(usr, usr_1);
                for (HashMap<String, String> submod : data_1) {
                    i++;
                    out.println(" <div class=\"row\">");
                    out.println("   <div class=\"col-md-3\" style=\"border:1px Solid Gray;padding: 1%;\">");
                    out.println(" <div class=\"row\">");
                    out.println(" <div class=\"col-md-2\">");
                    out.println(" <input type=\"checkbox\"  " + submod.get("checked_or_not") + " id='chkk_" + i + "'  onclick='enable(" + i + ")'>");
                    out.println("  </div>");
                    if (submod.get("checked_or_not").equals("checked")) {
                        out.println("  <div class=\"col-md-8\">"
                                + "<span style='color:green;'>"
                                + "<b>" + submod.get("submodnm") + "</b>"
                                + "</span>"
                                + "<input type='hidden'  name='mdid' id='getmdid_" + i + "' value='" + submod.get("mdid") + "'>"
                                + "<input type='hidden'  name='submid' id='getsubmid_" + i + "' value='" + submod.get("submid") + "'>");

                    } else {
                        out.println("  <div class=\"col-md-8\"><span>" + submod.get("submodnm") + "</span>"
                                + "<input type='hidden' disabled name='mdid' id='getmdid_" + i + "' value='" + submod.get("mdid") + "'>"
                                + "<input type='hidden' disabled name='submid' id='getsubmid_" + i + "' value='" + submod.get("submid") + "'>");
                    }
                    out.println(" </div>");

                    out.println("</div>");
                    out.println(" </div>");
                    out.println("</div>");

                }
            }

            if (val.equals("7")) {
                boolean sts1 = false;
                System.out.println("----> sub moule me aya ");
                String mem = request.getParameter("sub");
                System.out.println("===>sub" + mem);
                String[] module = request.getParameterValues("mdid");
                System.out.println("===>sub" + module);
                String[] submodule = request.getParameterValues("submid");

                sts1 = st.savesubmod(mem, module, submodule);

                HashMap<String, String> altMap = new HashMap<>();
                altMap.put("Titel", "Sub Module Assigned");
                altMap.put("Alt_Msg", "Some Error !!");
                altMap.put("Alt_Type", "danger");

                if (sts1) {
                    {
                        altMap.put("Alt_Msg", "Sub Module Assigned Successfully");
                        altMap.put("Alt_Type", "success");
                    }
                    session.setAttribute("Alt_Data", altMap);
                    response.sendRedirect("Main/Master/WMAS0001.jsp");
                }
            }

            if (val.equals("8")) {
                boolean sts1 = false;
              
                String user = request.getParameter("user");
                String hospital_code = request.getParameter("hospital_code");
                String Room_name = request.getParameter("Room_name");
                String Number_of_bed = request.getParameter("Number_of_bed");
                String Floor = request.getParameter("Floor");
                String room_category = request.getParameter("room_category");

                sts1 = st.save_Room(user, hospital_code, Room_name, Number_of_bed, Floor, room_category);

                HashMap<String, String> altMap = new HashMap<>();
                altMap.put("Titel", "Saved");
                altMap.put("Alt_Msg", "Some Error !!");
                altMap.put("Alt_Type", "danger");

                if (sts1) {
                    {
                        altMap.put("Alt_Msg", "Room Saved");
                        altMap.put("Alt_Type", "success");
                    }
                
                    session.setAttribute("Alt_Data", altMap);
                    response.sendRedirect("Main/Master/WMAS0003.jsp");
                }
                else{
                    {
                        altMap.put("Alt_Msg", "Room Number Alredy Defined");
                        altMap.put("Alt_Type", "error");
                    }
                
                    session.setAttribute("Alt_Data", altMap);
                    response.sendRedirect("Main/Master/WMAS0003.jsp");
                }
            }

            if (val.equals("9")) {
                String sts1 = "";
                System.out.println("----> yanha tak Aya  ");
                String user = request.getParameter("user");
                String hospital_code = request.getParameter("hospital_code");
                String Specialist = request.getParameter("Specialist");
                String f_name = request.getParameter("f_name");
                String l_name = request.getParameter("l_name");
                String charge = request.getParameter("charge");
                String depertment = request.getParameter("depertment");
                String degree = request.getParameter("degree");
                String mobile_number = request.getParameter("mobile_number");
                String alt_number = request.getParameter("alt_number");
                String emial = request.getParameter("emial");
                String city = request.getParameter("city");
                String address = request.getParameter("address");
                String doc_id = request.getParameter("doc_code");
                

                Part prd_photo = request.getPart("doc_photo");
                String mainFolder = "Staff", subFolder = "images";

                String sts = "";
                boolean img_sts = false;
                sts1 = st.save_update_doc(user, hospital_code, Specialist, f_name, l_name, charge, depertment, degree, mobile_number, alt_number, emial, city, address, doc_id);

                if (prd_photo.getSize() > 0) {
                    String file_name = sts1.replace('/', '-');
                    img_sts = saveImage(request, "DOC_" + file_name, prd_photo, mainFolder, subFolder);

                }

                HashMap<String, String> altMap = new HashMap<>();
                altMap.put("Titel", "Saved");
                altMap.put("Alt_Msg", "Some Error !!");
                altMap.put("Alt_Type", "danger");

                if (sts1 != "") {
                    {
                        altMap.put("Alt_Msg", "Room Saved");
                        altMap.put("Alt_Type", "success");
                    }
                    session.setAttribute("Alt_Data", altMap);
                    response.sendRedirect("Main/Reports/WREP0001.jsp");
                }
            }

            if (val.equals("10")) {
                String sts1 = "";
                System.out.println("----> yanha tak Aya  ");
                String user = request.getParameter("user");
                String name = request.getParameter("f_name");
                String hospital_code = request.getParameter("hospital_code");
                String dep = request.getParameter("dep");
                String number = request.getParameter("number");
                String position = request.getParameter("position");
                String salary = request.getParameter("salary");
                String Adhar = request.getParameter("Adhar");
                String date_of_joining = request.getParameter("date_of_joining");
                String staff_id = request.getParameter("staff_id");
                String gender = request.getParameter("gender");
                String age = request.getParameter("Age");

                Part prd_photo = request.getPart("prd_staff_photo");
                Part prd_adhar = request.getPart("prd_staff_adhar");
                Part prd_pan = request.getPart("prd_staff_pan");

                String mainFolder = "Staff", subFolder = "images";

                String sts = "";
                boolean img_sts = false;
                sts1 = st.save_update_staff(user, hospital_code, dep, number, position, salary, Adhar, date_of_joining, staff_id, name, gender, age);

//                    for saving photo 
                if (prd_photo.getSize() > 0) {
                    String file_name = sts1.replace('/', '-');
                    img_sts = saveImage(request, "Staff_" + file_name, prd_photo, mainFolder, subFolder);

                }
//                             for saving adhar

                if (prd_adhar.getSize() > 0) {
                    String file_name = sts1.replace('/', '-');
                    img_sts = saveImage(request, "Staff_Adhar_" + file_name, prd_photo, mainFolder, subFolder);

                }

//                                        for saving pan
                if (prd_pan.getSize() > 0) {
                    String file_name = sts1.replace('/', '-');
                    img_sts = saveImage(request, "Staff_Pan_" + file_name, prd_photo, mainFolder, subFolder);

                }

                HashMap<String, String> altMap = new HashMap<>();
                altMap.put("Titel", "Saved");
                altMap.put("Alt_Msg", "Some Error !!");
                altMap.put("Alt_Type", "danger");

                if (sts1 != "") {
                    {
                        altMap.put("Alt_Msg", "Satff Saved");
                        altMap.put("Alt_Type", "success");
                    }
                    session.setAttribute("Alt_Data", altMap);
                    response.sendRedirect("Main/Master/WMAS0005.jsp");
                }
            }

            if (val.equals("11")) {
                boolean sts = false;

                String category_name = request.getParameter("cat_name");
                String hospital_code = request.getParameter("hospital_code");
                String charge_type = request.getParameter("charge_type");
                String user = request.getParameter("user");

              

                sts = st.save_charge_category(category_name, hospital_code, user,charge_type);

                HashMap<String, String> altMap = new HashMap<>();
                altMap.put("Titel", "Success");
                altMap.put("Alt_Msg", "Some Error !!");
                altMap.put("Alt_Type", "danger");

                if (sts) {
                    {
                        altMap.put("Alt_Msg", "Saved Successfully");
                        altMap.put("Alt_Type", "success");
                    }
                    session.setAttribute("Alt_Data", altMap);
                    response.sendRedirect("Main/Master/WMAS0006.jsp");
                }

            }

            if (val.equals("12")) {
                boolean sts = false;

                String[] charge_name = request.getParameterValues("ca_Name");
                String[] Amount = request.getParameterValues("Amount");
                String[] MIn_Amount = request.getParameterValues("Mini_Mum");
                
                String hospital_code = request.getParameter("hospital_code");
                String user = request.getParameter("user");
                String category_id = request.getParameter("category_name");

                System.out.println("---->Yanha tak aya ");

                sts = st.save_charge_AMOUNT(charge_name, Amount, hospital_code, user, category_id,MIn_Amount);

                HashMap<String, String> altMap = new HashMap<>();
                altMap.put("Titel", "Success");
                altMap.put("Alt_Msg", "Some Error !!");
                altMap.put("Alt_Type", "danger");

                if (sts) {
                    {
                        altMap.put("Alt_Msg", "Saved Successfully");
                        altMap.put("Alt_Type", "success");
                    }
                    session.setAttribute("Alt_Data", altMap);
                    response.sendRedirect("Main/Master/WMAS0006.jsp");
                }

            }

            if (val.equals("13")) {
                int i = 0;
                String category = request.getParameter("category");

                ArrayList<HashMap<String, String>> data_1 = st.get_all_charges(category);
                for (HashMap<String, String> submod : data_1) {
                    i++;
                    
                     out.println("<tr>");
                     out.println("<td>"+i+"</td>");
                     out.println("<td>"+ submod.get("charge_name") +"</td>");
                     out.println("<td>"+submod.get("charge_amount")+"</td>");
                     if(submod.get("min_charge").trim().equals("0.0")){
                       out.println("<td><input type='text' value='0' name='Minmum_charge' id='MIN_"+i+"'></td>");
                     }else{
                         out.println("<td>"+submod.get("min_charge")+"</td>");  
                          
                     }
                    
                     out.println("<td><button type='btn'><i class='fa fa-edit'></btn></td>");
                     out.println("</tr>");

                }

            }

            if (val.equals("14")) {
                String sts = "";

                String Hospital_name = request.getParameter("Hospital_name");
                String Hospital_reg = request.getParameter("Hospital_reg");
                String Hospital_gst = request.getParameter("Hospital_gst");
                String Hospital_address = request.getParameter("Hospital_address");
                String number_1 = request.getParameter("number_1");
                String number_2 = request.getParameter("number_2");
                String Hospital_code = request.getParameter("hospital_code");

                Part prd_photo_logo = request.getPart("prd_photo_logo");

                String mainFolder = "Staff", subFolder = "images";

                sts = st.save_update_hos(Hospital_name, Hospital_reg, Hospital_gst, Hospital_address, number_1, number_2, Hospital_code);

                HashMap<String, String> altMap = new HashMap<>();
                altMap.put("Titel", "Hospital Updated");
                altMap.put("Alt_Msg", "Some Error !!");
                altMap.put("Alt_Type", "danger");

                if (sts != null) {

                    boolean img_sts = false;

                    if (prd_photo_logo.getSize() > 0) {
                        String file_name = sts.replace('/', '-');
                        img_sts = saveImage(request, "Hospital-" + file_name, prd_photo_logo, mainFolder, subFolder);
                        //stuPhoto_name=stuCode+".jpg";;
                        // stuPhoto_path=mainFolder+File.separator+subFolder+File.separator+stuPhoto_name;

                    }

                    {
                        altMap.put("Alt_Msg", " Hospital Updated Successfully");
                        altMap.put("Alt_Type", "success");
                    }
                    session.setAttribute("Alt_Data", altMap);
                    response.sendRedirect("Main/Master/WMAS0009.jsp");
                }

            }

//for creating users
            if (val.equals("15")) {

                String user = request.getParameter("User_name");
                String pass = request.getParameter("Password");

                String realname = request.getParameter("Real_name");
                String Role = request.getParameter("Role");
                String user_id = request.getParameter("user_id");

                System.out.println("--->yanha pe " + user_id);

                Part prd_photo_user = request.getPart("prd_photo_user");

                String mainFolder = "Staff", subFolder = "images";

                String sts = "";

                sts = st.save_updateUser(user, pass, realname, Role, user_id);

                HashMap<String, String> altMap = new HashMap<>();
                altMap.put("Titel", "User Created");
                altMap.put("Alt_Msg", "Some Error !!");
                altMap.put("Alt_Type", "danger");

                if (sts != null) {

                    boolean img_sts = false;

                    if (prd_photo_user.getSize() > 0) {
                        String file_name = sts.replace('/', '-');
                        img_sts = saveImage(request, "system_user-" + file_name, prd_photo_user, mainFolder, subFolder);
                        //stuPhoto_name=stuCode+".jpg";;
                        // stuPhoto_path=mainFolder+File.separator+subFolder+File.separator+stuPhoto_name;

                    }

                    altMap.put("Alt_Msg", "Permission Not Given");
                    altMap.put("Alt_Type", "success");
                    session.setAttribute("Alt_Data", altMap);
                    response.sendRedirect("Main/Master/WMAS00010.jsp");
                } else {
                    session.setAttribute("msg", "Some Error ");
                    response.sendRedirect("Main/Master/WMAS00010.jsp");
                }

            }

//for getting all users 
            if (val.equals("16")) {

                ArrayList<HashMap<String, String>> data = st.get_all_users();
                int i = 0;
                for (HashMap<String, String> cd : data) {
                    i++;
                    out.println(" <tr>");
                    out.println("<td>" + i + "</td>");
                     out.println("<td style=\"white-space: nowrap\"><button class='btn btn-success'>Permission</td>");
                    out.println("<td style=\"white-space: nowrap\"><img src=\"../../Staff/images/system_user-" + cd.get("user_id").replace('/', '-') + ".jpg\" class=\"img-thumbnail\" onerror=\"this.onerror=null; this.src='../Design/avatr.jpg'\" alt=\"\"  style='height:50px;width:50px' ></td></td>");
                    out.println("<td style=\"white-space: nowrap\">" + cd.get("username") + "</td>");
                    out.println("<td style=\"white-space: nowrap\"><span class='badge bg-success' >" + cd.get("password") + "</span></td>");
                    out.println("<td style=\"white-space: nowrap\">" + cd.get("real_name") + "</td>");
                    if (cd.get("sts").equals("1")) {
                        out.println("<td style=\"white-space: nowrap\"><span class='badge bg-success' onclick='window.open(\"../../Main/Profiles/WPROF0001.jsp?id=" + cd.get("user_id").toString() + "\");'>Active</td>");
                    } else {
                        out.println("<td style=\"white-space: nowrap\"><span class='badge bg-danger'>Deactivate</td>");
                    }
                    
                   
                    out.println("</tr>");

                }
            }

            if (val.equals("17")) {

                ArrayList<HashMap<String, String>> data = st.get_all_hospitals();
                int i = 0;
                for (HashMap<String, String> cd : data) {
                    i++;
                    out.println(" <tr>");
                    out.println("<td>" + i + "</td>");
                    out.println("<td style=\"white-space: nowrap\"><img src=\"../../Staff/images/system_user-" + cd.get("hospital_code").replace('/', '-') + ".jpg\" class=\"img-thumbnail\" onerror=\"this.onerror=null; this.src='../Design/avatr.jpg'\" alt=\"\"  style='height:50px;width:50px' ></td></td>");
                    out.println("<td style=\"white-space: nowrap\">" + cd.get("hospital_name") + "</td>");
                    out.println("<td style=\"white-space: nowrap\"><span class='badge bg-success' >" + cd.get("reg") + "</span></td>");
                    out.println("<td style=\"white-space: nowrap\">" + cd.get("gst") + "</td>");
                    out.println("<td style=\"white-space: nowrap\">" + cd.get("address") + "</td>");
                    out.println("<td style=\"white-space: nowrap\">" + cd.get("number_1") + "</td>");
                    out.println("<td style=\"white-space: nowrap\">" + cd.get("number_2") + "</td>");

                    out.println("<td style=\"white-space: nowrap\"><span class='badge bg-success' onclick='window.open(\"../../Main/Profiles/WPROH0001.jsp?id=" + cd.get("hospital_code").toString() + "\");'>Update</td>");

                    out.println("</tr>");

                }
            }

//          for pharmacy Registration 
            if (val.equals("18")) {
                String sts = "";

                String pharmacy_name = request.getParameter("pharmacy_name");
                String pharmacy_reg = request.getParameter("pharmacy_reg");
                String pharmacy_gst = request.getParameter("pharmacy_gst");
                String pharmacy_address = request.getParameter("pharmacy_address");
                String number = request.getParameter("number");

                String pharmacy_code = request.getParameter("pharmacy_code");

                System.out.println("===phtttttttttttttt" + pharmacy_code);

                Part prd_photo_logo = request.getPart("prd_photo_logo_pharmacy");

                String mainFolder = "Staff", subFolder = "images";

                sts = st.save_update_pharmacy(pharmacy_name, pharmacy_reg, pharmacy_gst, pharmacy_address, number, pharmacy_code);

                HashMap<String, String> altMap = new HashMap<>();
                altMap.put("Titel", "Pharmacy Updated");
                altMap.put("Alt_Msg", "Some Error !!");
                altMap.put("Alt_Type", "danger");

                if (sts != null) {

                    boolean img_sts = false;

                    if (prd_photo_logo.getSize() > 0) {
                        String file_name = sts.replace('/', '-');
                        img_sts = saveImage(request, "Pharmacy_" + file_name, prd_photo_logo, mainFolder, subFolder);
                        //stuPhoto_name=stuCode+".jpg";;
                        // stuPhoto_path=mainFolder+File.separator+subFolder+File.separator+stuPhoto_name;

                    }

                    {
                        altMap.put("Alt_Msg", " Pharmacy Saved Successfully");
                        altMap.put("Alt_Type", "success");
                    }
                    session.setAttribute("Alt_Data", altMap);
                    response.sendRedirect("Main/Master/WMAS00011.jsp");
                }

            }

            if (val.equals("19")) {

                ArrayList<HashMap<String, String>> data = st.get_pharmacy_update();
                int i = 0;
                for (HashMap<String, String> cd : data) {
                    i++;
                    out.println(" <tr>");
                    out.println("<td>" + i + "</td>");
                    out.println("<td style=\"white-space: nowrap\"><img src=\"../../Staff/images/Pharmacy_" + cd.get("pharmacy_code").replace('/', '-') + ".jpg\" class=\"img-thumbnail\" onerror=\"this.onerror=null; this.src='../Design/avatr.jpg'\" alt=\"\"  style='height:50px;width:50px' ></td></td>");
                    out.println("<td style=\"white-space: nowrap\">" + cd.get("pharmacy_name") + "</td>");
                    out.println("<td style=\"white-space: nowrap\"><span class='badge bg-success' >" + cd.get("phr_reg") + "</span></td>");
                    out.println("<td style=\"white-space: nowrap\">" + cd.get("phr_gst") + "</td>");
                    out.println("<td style=\"white-space: nowrap\">" + cd.get("phr_addrees") + "</td>");
                    out.println("<td style=\"white-space: nowrap\">" + cd.get("phr_number") + "</td>");

                    out.println("<td style=\"white-space: nowrap\"><span class='badge bg-success' onclick='window.open(\"../../Main/Master/WMAS00011.jsp?phr_id=" + cd.get("pharmacy_code").toString() + "\");'>Update</td>");

                    out.println("</tr>");

                }
            }

//for opd Procedure 
            if (val.equals("20")) {
                String sts = "";

                String procedure_name = request.getParameter("procedure_name");
                String procedure_rate = request.getParameter("procedure_rate");
                String Procedure_code = request.getParameter("Procedure_code");

                sts = st.save_update_opd_procedure(procedure_name, procedure_rate, Procedure_code);

                HashMap<String, String> altMap = new HashMap<>();
                altMap.put("Titel", "OPD Procedure Savd");
                altMap.put("Alt_Msg", "Some Error !!");
                altMap.put("Alt_Type", "danger");

                if (sts != null) {

                    {
                        altMap.put("Alt_Msg", " Procedure  Saved Successfully");
                        altMap.put("Alt_Type", "success");
                    }
                    session.setAttribute("Alt_Data", altMap);
                    response.sendRedirect("Main/Master/WMAS00012.jsp");
                }

            }

//for other charges addition 
            if (val.equals("21")) {
                String sts = "";

                String charge_name = request.getParameter("charge_name");
                String charge_rate = request.getParameter("charge_rate");
                String charge_id = request.getParameter("charge_id");

                sts = st.save_update_other_charges(charge_name, charge_rate, charge_id);

                HashMap<String, String> altMap = new HashMap<>();
                altMap.put("Titel", "Other Charge Added");
                altMap.put("Alt_Msg", "Some Error !!");
                altMap.put("Alt_Type", "danger");

                if (sts != null) {

                    {
                        altMap.put("Alt_Msg", " Charge  Saved Successfully");
                        altMap.put("Alt_Type", "success");
                    }
                    session.setAttribute("Alt_Data", altMap);
                    response.sendRedirect("Main/Master/WMAS00013.jsp");
                }

            }

            if (val.equals("22")) {

                String Charge_id = request.getParameter("charge_id");
                ArrayList<HashMap<String, String>> data = st.get_other_charges();
                int i = 0;
                for (HashMap<String, String> cd : data) {
                    i++;
                    out.println(" <tr>");
                    out.println("<td>" + i + "</td>");

                    out.println("<td style=\"white-space: nowrap\">" + cd.get("charge_name") + "</td>");
                    out.println("<td style=\"white-space: nowrap\">" + cd.get("charge_rate") + "</td>");

                    out.println("<td style=\"white-space: nowrap\"><span class='badge bg-success' onclick='window.open(\"../../Main/Master/WMAS00013.jsp?charge_id=" + cd.get("charge_id").toString() + "\");'>Update</td>");

                    out.println("</tr>");

                }
            }
            
            
            
            //for procedure 
            
          if (val.equals("23")) {
                ArrayList<HashMap<String, String>> data6 = st.get_all_opd_procedure();
                int i7 = 0;
                
                for (HashMap<String, String> cd : data6) {
                    i7++;
                    out.println(" <tr>");
                    out.println("<td>" + i7 + "</td>");
                    out.println("<td style=\"white-space: nowrap\">" + cd.get("procedure_name") + "</td>");
                    out.println("<td style=\"white-space: nowrap\"><span class='badge bg-success' >" + cd.get("procedure_rate") + "</span></td>");
                    out.println("<td style=\"white-space: nowrap\"><span class='badge bg-success' onclick='window.open(\"../../Main/Master/WMAS00012.jsp?procedure_id=" + cd.get("procedure_id").toString() + "\");'>Update</td>");
                    out.println("</tr>");
                }
            }
          
          
          //for rooms 
          
               if (val.equals("24")) {

                String room_id = request.getParameter("room_id");
                 ArrayList<HashMap<String, String>> room_data = st.room_beds(room_id);
                
                  int i = 0;
                 
                   out.println("<div class='row'>");
                    for (HashMap<String, String> cd : room_data) {
                        
                    out.println("<div class='col-md-4'>");
                    out.println("<div class='card' style='width: 10rem;'>\n" +
"       <center> <i class='fa fa-bed'  style='color: #D35400;  font-size: 30px; /* Icon size */ text-shadow: 3px 3px 5px rgba(0, 0, 0, 0.4);'></i><center>\n" +
"        <div class='card-body'>\n" +
"            <h5 class='card-title'><b>"+cd.get("room_bed_id")+"</b></h5>\n" +
"            <p class=\'card-text\'><b>"+cd.get("category_name")+"</b></p>\n" +
"            " +
"        </div>\n" +
"    </div>");
                    
                    out.println("</div>");
                 
                    
                    i++;   
                    
                }
                    
                    out.println("<button class=\"square-button\" onclick=Add_new_bed("+room_id+")>+</button>");
                  
                    out.println("</div>");
            }
               
               
       //for adding other charge Category 
       if (val.equals("25")) {
                String sts = "";

                String Category_name = request.getParameter("Category_name");
                String user = request.getParameter("user");
                String Category_id=request.getParameter("Category_id");
                
                System.out.println("--<"+Category_id);
               

                sts = st.save_update_other_charge_category(Category_name, user,Category_id);

                HashMap<String, String> altMap = new HashMap<>();
                altMap.put("Titel", "Other Charge Added");
                altMap.put("Alt_Msg", "Some Error !!");
                altMap.put("Alt_Type", "danger");

                if (sts != null) {

                    {
                        altMap.put("Alt_Msg", " Category   Saved Successfully");
                        altMap.put("Alt_Type", "success");
                    }
                    session.setAttribute("Alt_Data", altMap);
                    response.sendRedirect("Main/Master/WMAS00013.jsp");
                }

            }
               
             //for adding new bed 
             
             if (val.equals("26")) {
               

                String room_id = request.getParameter("room_id");
             
               String sts = st.add_new_bed(room_id);
            }
             
             
             
             
             //for adding Bank Detials 
             
       if (val.equals("27")) {
        
           String bank_name=request.getParameter("bank_name");
           String Branch_name=request.getParameter("Branch_name");
           String IFSC_code=request.getParameter("IFSC_code");
           String Account_number=request.getParameter("Account_number");
           String user=request.getParameter("user");
             String hos=request.getParameter("hospital_code");
              
            String sts="";
            sts = st.Add_Bank_details(bank_name,Branch_name,IFSC_code,Account_number,user,hos);
         
            
              HashMap<String, String> altMap = new HashMap<>();
                altMap.put("Titel", "Bank Added Successfully");
                altMap.put("Alt_Msg", "Some Error !!");
                altMap.put("Alt_Type", "danger");
            
                       if (sts != null) {

                    {
                        altMap.put("Alt_Msg", " Bank Added Successfully");
                        altMap.put("Alt_Type", "success");
                    }
                    session.setAttribute("Alt_Data", altMap);
                    response.sendRedirect("Main/Master/WMAS00014.jsp");
                }
              
              
              
       }   
         
       
       
       if (val.equals("28")) {
       
                 ArrayList<HashMap<String, String>> bank_data = st.Bank_details();
                
                  int i = 0;
                 
                   out.println("<div class='row'>");
                    for (HashMap<String, String> cd : bank_data) {
                        
                    out.println("<tr>");
                    out.println("<td>"+cd.get("bank_id")+"</td>");
                    out.println("<td>"+cd.get("bank_name")+"</td>");
                    out.println("<td>"+cd.get("branch_name")+"</td>");
                    out.println("<td>"+cd.get("ifsc_code")+"</td>");
                    out.println("<td>"+cd.get("account_number")+"</td>");
                    out.println("<td>"+cd.get("create_date")+"</td>");
                    out.println("<td><button type='btn' title='Edit detials'><i class='fa fa-edit'></i></button></td>");
                    out.println("</tr>");
           
       }   
                    
       }
       
       
       
       //for adding last UHID 
       
       if (val.equals("29")) {
        
           String last_uhid=request.getParameter("UHID");
     
            String sts="";
            sts = st.Add_last_UHID(last_uhid);
         
            
              HashMap<String, String> altMap = new HashMap<>();
                altMap.put("Titel", "UHID Added Successfully");
                altMap.put("Alt_Msg", "Some Error !!");
                altMap.put("Alt_Type", "danger");
            
                       if (sts != null) {

                    {
                        altMap.put("Alt_Msg", "Pervios Hospital UHID Added ");
                        altMap.put("Alt_Type", "success");
                    }
                    session.setAttribute("Alt_Data", altMap);
                    response.sendRedirect("Main/Master/WMAS00015.jsp");
                }
       
              
       }
       
       
       if (val.equals("30")) {
                String sts = "";

                String fname = request.getParameter("fname");
                String lname = request.getParameter("lname");
                String charge = request.getParameter("charge");
                String depertment = request.getParameter("depertment");
                String Hospital_code="HOS/1";

              

                sts = st.save_doctor_shortcut(fname,lname,charge,depertment,Hospital_code);

                HashMap<String, String> altMap = new HashMap<>();
                altMap.put("Titel", "Success");
                altMap.put("Alt_Msg", "Some Error !!");
                altMap.put("Alt_Type", "danger");

                if (sts!="") {
                    {
                        altMap.put("Alt_Msg", "Saved Successfully");
                        altMap.put("Alt_Type", "success");
                    }
                    session.setAttribute("Alt_Data", altMap);
                    response.sendRedirect("Main/Master/WMAS0006.jsp");
                }

            }
       
       
       if (val.equals("31")) {
       
                ArrayList<HashMap<String, String>>get_doc_shortcut = st.get_doc_shortcut();
                
                  int i = 0;
                    out.println("<option>Select</option>");
                    for (HashMap<String, String> cd : get_doc_shortcut) {
                       out.println("<option value='"+cd.get("doc_code")+"'>"+cd.get("doc_name")+"</option>");
              
           
       }
                    
       }
       
       
       
       
       


        } catch (Exception ex) {
            Logger.getLogger(WMAS0001_SERV.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (SQLDataException ex) {
            Logger.getLogger(WMAS0001_SERV.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (SQLDataException ex) {
            Logger.getLogger(WMAS0001_SERV.class.getName()).log(Level.SEVERE, null, ex);
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

    private boolean saveImage(HttpServletRequest request, String imgCode, Part imgPhoto, String mainFolder, String subFolder) {

        String Photo_name = "";
        String Photo_path = "";

        boolean sts = false;
        FileOutputStream fos = null;
        try {

            if (imgPhoto.getSize() > 0) {

                Photo_name = imgCode + ".jpg";//STF-001.jpg

                System.out.println("Photo_name--> " + Photo_name);
                InputStream is = imgPhoto.getInputStream();
                byte[] data = new byte[is.available()];
                is.read(data);

                String path = request.getRealPath("/") + mainFolder;
                File fileSaveDir = new File(path);
                if (!fileSaveDir.exists()) {
                    fileSaveDir.mkdir();
                }
                path += File.separator + subFolder;
                File fileSaveDir2 = new File(path);
                if (!fileSaveDir2.exists()) {
                    fileSaveDir2.mkdir();
                }
                path += File.separator + Photo_name;

                fos = new FileOutputStream(path);
                fos.write(data);

                Photo_path = mainFolder + File.separator + subFolder + File.separator + Photo_name;

                sts = true;
            }
        } catch (Exception e) {
            Logger.getLogger(WMAS0001_SERV.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("Error-> " + e.getMessage());
        } finally {
            try {
                fos.close();
                return sts;
            } catch (IOException ex) {
                Logger.getLogger(WMAS0001_SERV.class.getName()).log(Level.SEVERE, null, ex);
            }
            return sts;
        }

    }
}

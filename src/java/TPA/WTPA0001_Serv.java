/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package TPA;

import Laboratory.WLAB0001_Serv;
import Laboratory.WLAB0001_dao;
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
@WebServlet(name = "WTPA0001_Serv", urlPatterns = {"/WTPA0001_Serv"})
public class WTPA0001_Serv extends HttpServlet {

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
       String val=request.getParameter("value");
           HttpSession session=request.getSession();
          WTPA0001_Dao st=new WTPA0001_Dao(); 
          
          try{
              
              if(val.equals("1")){
   
      
       
               String tpa_name=request.getParameter("tpa_name");
               String tpa_code=request.getParameter("tpa_code");
               String contact_person=request.getParameter("contact_person");
               String mobile_number=request.getParameter("mobile_number");
               String address=request.getParameter("address");
               String User=request.getParameter("user");
               String hospital_code=request.getParameter("hospital_code");
              
              boolean sts=false;
               
               sts=st.Save_TPA(tpa_name,tpa_code,contact_person,mobile_number,address,User,hospital_code);

                             HashMap<String ,String> altMap=new HashMap<>();
                      altMap.put("Titel", "TPA Added ");
                      altMap.put("Alt_Msg", "Some Error !!");
                      altMap.put("Alt_Type", "danger");
                   
                    if(sts){
                        {altMap.put("Alt_Msg","TPA Added Successfully");
                      altMap.put("Alt_Type", "success");
                            }
                session.setAttribute("Alt_Data",altMap);
                response.sendRedirect("Main/TPA/WTPA0001.jsp");
                }
               
           }
              
              
              
              if(val.equals("2")){
               
              
                    ArrayList<HashMap<String,String>> data=st.get_TPA();
                    int i=0;
                    for(HashMap<String,String> cd:data){
                        i++;    
                        out.println(" <tr>");
                        out.println("<td>"+i+"</td>");
                        
                        out.println("<td>"+cd.get("tpa_name")+"<input type=hidden name='staff_id'  value='"+cd.get("staff_id")+"' id='id_"+i+"'></td>");
                        out.println("<td>"+cd.get("tpa_code")+"</td>");
                        out.println("<td>"+cd.get("contact_person")+"</td>");
                        out.println("<td><span class='badge bg-primary'>"+cd.get("mobile_number")+"</td>");
                       
                        out.println("</tr>");
                       
                       
                     

                    }
                }  
             
        
              
              
              
          }catch(Exception ex){
              Logger.getLogger(WTPA0001_Serv.class.getName()).log(Level.SEVERE, null, ex);
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

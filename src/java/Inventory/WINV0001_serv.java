/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Inventory;

import Master.WMAS0001_Dao;
import Master.WMAS0001_SERV;
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
@WebServlet(name = "WINV0001_serv", urlPatterns = {"/WINV0001_serv"})
public class WINV0001_serv extends HttpServlet {

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
        HttpSession session=request.getSession();
        WINV0001_dao st=new WINV0001_dao();
        String val=request.getParameter("value");
        
         try{
             
             if(val.equals("1")){
                    boolean sts=false;
                    
                  
                    String cat_name=request.getParameter("category_name");
                    
                    sts=st.save_cat(cat_name);
                   
                     HashMap<String ,String> altMap=new HashMap<>();
                      altMap.put("Titel", "Category Saved");
                      altMap.put("Alt_Msg", "Some Error !!");
                      altMap.put("Alt_Type", "danger");
                   
                    if(sts){
                        {altMap.put("Alt_Msg", "Category Saved Successfully");
                      altMap.put("Alt_Type", "success");
                            }
                session.setAttribute("Alt_Data",altMap);
                response.sendRedirect("Main/Inventory/WINV0001.jsp");
                }
                
                }
             
             
             
             
             
             if(val.equals("2")){
                
                
                ArrayList<HashMap<String,String>> sale_data=st.get_cat_data();
                int i=0;
                for(HashMap<String,String>cd:sale_data){
                    
      
                    out.println("<tr>");
                    
                    out.println("<td>"+cd.get("cat_cd")+"<input type='hidden' name='cat_cd' value="+cd.get("cat_id")+"></td>");
                    out.println("<td>"+cd.get("cat_name")+"");
                    out.println("<td>"+cd.get("cat_sts")+"");
     
                    out.println("</tr>");
                    
                    
               }
             }
                
                
                 if(val.equals("3"))
            { 
                String inv_cat=request.getParameter("category");
                String item_name=request.getParameter("item_name");
                String item_qty=request.getParameter("item_qty");
                String item_from=request.getParameter("item_from");
                String receive_by=request.getParameter("rec_by");
                String remark=request.getParameter("remark");
                String hospital_code=request.getParameter("hospital_code");
                String user=request.getParameter("user");
                
           
                
                HashMap<String, String> altMap=new HashMap<String, String>();
                  altMap.put("Titel", "Ground Balance");
                      altMap.put("Alt_Msg", "Some Error !!");
                      altMap.put("Alt_Type", "danger");
                String  code =st.saveInventory(inv_cat,item_name,item_qty,item_from,receive_by,remark,hospital_code,user);
                if(!code.equals(""))
                           {
                        {altMap.put("Alt_Msg", "Ground Balance Saved Successfully");
                      altMap.put("Alt_Type", "success");
                            }
                session.setAttribute("Alt_Data",altMap);
                response.sendRedirect("Main/Inventory/WINV0002.jsp");
                }
              
               
            }
                
                
                
                
               
//                 for getting inventory details

 if(val.equals("4"))
            {
                 ArrayList<HashMap<String, String>> stuArrayList = new ArrayList<>(); // Create an ArrayList object
                String inv_cat=request.getParameter("inv_cat");
                String item_name=request.getParameter("item_name");
                stuArrayList=st.getInventoryData(inv_cat,item_name);
                
                int i=1;
                for(HashMap<String, String> reg:stuArrayList)
               { 
                   //String IMG=!reg.get("actFile").toString().equals("")?reg.get("actFile").toString():"Designs/Images/Schhol_info/NOImg.jpg";
                   out.println("<tr>");
                   
                   out.println("<td>"+i+" <input type='checkbox' name='chbox' id='chbox"+i+"' value='"+i+"' onclick=\"opemValidation(this,'"+i+"')\" ></td>");
                   out.println("<td>"+reg.get("cat_name")+"<input hidden type='text' name='ivn_cat_id"+i+"' id='ivn_cat_id"+i+"' value='"+reg.get("ivn_cat_id")+"' disabled ></td>");
                   out.println("<td>"+reg.get("item_name")+"<input hidden type='text' name='item_code"+i+"' id='item_code"+i+"' value='"+reg.get("item_code")+"' disabled ></td>");
                   out.println("<td>"+reg.get("item_qty")+"</td>");
                   out.println("<td><input type='text' name='receiver_name"+i+"' id='receiver_name"+i+"' disabled required></td>");
                   out.println("<td><input type='number' min='1' max='"+reg.get("item_qty")+"' name='item_qty"+i+"' id='item_qty"+i+"' disabled required></td>");
                   out.println("<td><input type='text'  name='sender_name"+i+"' id='sender_name"+i+"' disabled ></td>");
                   out.println("<td><input type='text'  name='remark"+i+"' id='remark"+i+"' disabled ></td>");
                   out.println("</tr>");
                   
                   i++;
               }
            }
                
                
                
                
        if(val.equals("5"))
            {
                HashMap<String, String> regInfo=new HashMap<String, String>();
                regInfo.put("sts", "fail");
                regInfo.put("msg", "Some Error Ourced !");
                regInfo.put("alertIcon", "error");
                
                boolean sts=st.saveInventoryOut(request);
                if(sts)
                           {
                               regInfo.put("sts", "done");
                               regInfo.put("msg", "Inventory Out Saved");
                               regInfo.put("alertIcon", "success");
                               
                           }
               session.setAttribute("regInfo", regInfo);
               response.sendRedirect("Main/Inventory/WINV0003.jsp"); 
                
            }        
               
        
      
                
                
                
                
                
                
                
            
             
             
             
             
             
             
             
             
             
             
             
             
             
             
             
             
             
             
            
            
             
             
             
             
             
             
             }catch( Exception ex)
        {
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

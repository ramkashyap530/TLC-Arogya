/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

 
package Login;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Tarun
 */
@WebServlet(name = "WLOGIN_SERV", urlPatterns = {"/WLOGIN_SERV"})
public class WLOGIN_SERV extends HttpServlet {
    
    
     

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
        try{
            
             System.out.println("-------->Tarun");
              String val=request.getParameter("val");
              if(val.equals("1")){
           String username=request.getParameter("username");
           String password=request.getParameter("password");
           String remember=request.getParameter("remember"); 
           
              HttpSession session=request.getSession();
           
           if(!username.equals("") && username!=null && !password.equals("") && password!=null){
               
               WLOGAUTH_MOD log=new WLOGAUTH_MOD();
              log.setPassword(password);
              log.setUsername(username);
               
              
              
              
              HashMap<String, String> loginInfo=new LOGIN_DAO().Login(log);
                String msg=loginInfo.get("msg");
                String Role_id=loginInfo.get("role_id");
                out.println(msg);
               
                 if(msg.equals("success")){
//              for cookies 
                     
                     
                     if(remember!=null && remember.equals("yes"))
                   {
           //  Cookies Start 

					Cookie cUserName = new Cookie("cookuser", username.trim().replace(" ","$%^&~~&^%$"));
					Cookie cPassword = new Cookie("cookpass", password.trim().replace(" ","$%^&~~&^%$"));;
					Cookie cRemember = new Cookie("cookrem", remember.trim());
					cUserName.setMaxAge(60 * 60 * 24 * 30);// 30 days
					cPassword.setMaxAge(60 * 60 * 24 * 30);
					cRemember.setMaxAge(60 * 60 * 24 * 30);
					response.addCookie(cUserName);
					response.addCookie(cPassword);
					response.addCookie(cRemember);

                       //////////////////////////////////
                  }
                  
                   else
                   {
                                        Cookie cUserName = new Cookie("cookuser",null);
                                        Cookie cPassword = new Cookie("cookpass", null);
					Cookie cRemember = new Cookie("cookrem", null);
					cUserName.setMaxAge(0);// 15 days
					cPassword.setMaxAge(0);
					cRemember.setMaxAge(0);
					response.addCookie(cUserName);
					response.addCookie(cPassword);
					response.addCookie(cRemember);

//                   }

                   }
                  
                   session.setAttribute("loginInfo", loginInfo);
                   session.setAttribute("userRole", loginInfo.get("role_name"));
                   session.setAttribute("user_id", loginInfo.get("user_id"));
                   session.setMaxInactiveInterval(60*60*60);
                   System.out.println("TIME OUT : "+session.getMaxInactiveInterval());
                   
               }
                  else{
                   session.setAttribute("loginInfo", loginInfo);
                      response.sendRedirect("Main/login/Login.jsp");
                  
               }
                
           }
           
           
           
           
              }
              
              
              
              if(val.equals("2"))
            {
                HttpSession session=request.getSession();
                session.invalidate();
               response.sendRedirect("Main/login/Login.jsp"); 
            }
               
               
               
               
           }catch (Exception e){
               System.out.println("-------->"+e);
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

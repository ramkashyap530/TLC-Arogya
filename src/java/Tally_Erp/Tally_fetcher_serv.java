///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
// */
//package Tally_Erp;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.HashMap;
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///**
// *
// * @author Lenovo
// */
//@WebServlet(name = "Tally_fetcher_serv", urlPatterns = {"/Tally_fetcher_serv"})
//public class Tally_fetcher_serv extends HttpServlet {
//
//    /**
//     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
//     * methods.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        PrintWriter out = response.getWriter();
//        //Tally_fetcher tally_fetcher=new Tally_fetcher();
//            /* TODO output your page here. You may use following sample code. */
//               String Val="1";
//            
//            //for fetching all ledgers from tally
//            
//          //  try{
//            if(Val.equals("1")){
////                test new_test=new test();
////                String xmlData="";
////                java.util.ArrayList<HashMap<String,String>>data=new_test.parseVoucherData(xmlData);
////                
////                for(HashMap<String,String> cd:data){
////                       out.println(cd.get("VOUCHERTYPENAME"));  
//                }
//          
//                 
//                
//              
// 
//            }
//            
//            
//            
//            
//            }catch(Exception e){
//                System.out.println("---"+e);
//            }
//            
//            
//            
//            
//        
//                }
//
//    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
//    /**
//     * Handles the HTTP <code>GET</code> method.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        processRequest(request, response);
//    }
//
//    /**
//     * Handles the HTTP <code>POST</code> method.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        processRequest(request, response);
//    }
//
//    /**
//     * Returns a short description of the servlet.
//     *
//     * @return a String containing servlet description
//     */
//    @Override
//    public String getServletInfo() {
//        return "Short description";
//    }// </editor-fold>
//
//}

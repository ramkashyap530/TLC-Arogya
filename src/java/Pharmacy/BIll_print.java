/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Pharmacy;

import Dao.DBCon;
import IPD.WDISPAT;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;

/**
 *
 * @author Tarun
 */
@WebServlet(name = "BIll_print", urlPatterns = {"/BIll_print"})
public class BIll_print extends HttpServlet {
Connection con;
    PreparedStatement ps=null;
    ResultSet rs=null;
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
        String bill_id =request.getParameter("bill_id");
        String docnm ="hospital_bill";
            try{
                
                con=new DBCon().getConnection();
                      String filename =  docnm+".jasper";
                      String reporttype =  "pdf";//request.getParameter("reportformat");
                     // String reporttype ="xlsx";//request.getParameter("reportformat");
                      // jasperParameter is a Hashmap contains the  parameters
                      // passed from application to the jrxml layout
                      Map  jasperParameter = new  HashMap();        
                      jasperParameter.put("bill_id",bill_id);
                      


                      String path = getServletContext().getRealPath("/"); 
                      
                      String stuImgPath=path+File.separator;
                      System.out.println("stuImgPath "+stuImgPath);
                      jasperParameter.put("stuImgPath",stuImgPath);
                      
                      JasperPrint jasperPrint =  JasperFillManager.fillReport(path+ "/Main/Reports/"+ filename,jasperParameter,con);
                      
                      System.out.println("Report Created... in  "+reporttype+ " Format");
                      OutputStream ouputStream =  response.getOutputStream();
                      JRPdfExporter exporter = null;
                      JRXlsxExporter exporterXls = null;

                      //Report  generated in - PDF/
                      if("pdf".equalsIgnoreCase(reporttype))
                      {
                        response.setContentType("application/pdf");
                        response.setHeader("Content-Disposition",  "inline; filename=\"SalarySlip_"+bill_id+".pdf\"");

                        exporter = new  JRPdfExporter();
                        
                        exporter.setParameter(JRExporterParameter.JASPER_PRINT,  jasperPrint);
                        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,  ouputStream);
                      }
                      else if("xlsx".equalsIgnoreCase(reporttype)  )
                      {
                        response.setContentType("application/vnd.ms-excel");
                        response.setHeader("Content-Disposition",  "inline; filename=\"face_sheet"+bill_id+".xlsx\"");

                        exporterXls = new  JRXlsxExporter();
                        exporterXls.setParameter(JRExporterParameter.JASPER_PRINT,  jasperPrint);
                        exporterXls.setParameter(JRExporterParameter.OUTPUT_STREAM,  ouputStream);
                      }
                      try
                      {
                        if("pdf".equalsIgnoreCase(reporttype))
                        {
                        exporter.exportReport();
                        }
                           else  if("xlsx".equalsIgnoreCase(reporttype)  ){
                               exporterXls.exportReport();
                           }
                      
                      }
                      catch  (Exception e)
                      {
                      throw new  ServletException(e);
                      }
                      finally
                      {

                      if (ouputStream !=  null)
                      {
                      try
                      {
                      ouputStream.close();
                      }
                      catch (IOException  ex){}
                      }
                      }
            }catch(Exception e){
             Logger.getLogger(BIll_print.class.getName()).log(Level.SEVERE, null, e);
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

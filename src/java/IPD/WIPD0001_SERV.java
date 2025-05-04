/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package IPD;

import HR.WHR0001_SERV;
import OPD.WOP0001_Dao;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
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
import notification.NotificationModel;
import notification.NotificationService;
import notification.NotificationWebSocket;

/**
 *
 * @author Tarun
 * 
 * 
 */
@MultipartConfig(fileSizeThreshold=1024*1024*2, 
maxFileSize=1024*1024*10, 
maxRequestSize=1024*1024*50)
@WebServlet(name = "WIPD0001_SERV", urlPatterns = {"/WIPD0001_SERV"})
public class WIPD0001_SERV extends HttpServlet {

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
       WIPD0001_Dao st=new WIPD0001_Dao();
            
       
        System.out.println("===>"+val);
          
            try{
                
                if(val.equals("1")){
                    
                    System.out.println("===>yanha tak aya");
                    String sts="";
                    
                            //for checking token for double entery 
  
                    String sessionToken = (String) session.getAttribute("formToken");
                    String requestToken = request.getParameter("formToken");
                    
                    
                    String hospital_code=request.getParameter("hospital_code");
                    String date=request.getParameter("date");
                    String room_no=request.getParameter("room_no");
                    String doctor=request.getParameter("doctor");
                    String type=request.getParameter("type");
                    String fees=request.getParameter("fees");
                    String Patient_name=request.getParameter("Patient_name");
                    String Age=request.getParameter("Age");
                    String gender=request.getParameter("gender");
                    String phone=request.getParameter("phone");
                    String adhar=request.getParameter("adhar");
                    String Address=request.getParameter("Address");
                    String user=request.getParameter("user");
                    String patient_id=request.getParameter("patient_id");
                    String Bed_number=request.getParameter("Bed_number");
                    String Refering_doctor=request.getParameter("Refering_doctor");
                    String Father_Name=request.getParameter("Father_Name");
                    String Tpa=request.getParameter("tpa");
                    String dob=request.getParameter("DOB");
                    String charge_amount=request.getParameter("Room_charge");
                    String gender_type=request.getParameter("gender_type");
                    String relation_of=request.getParameter("relation_of");
                    String policy_Number=request.getParameter("Policy_Number");
                    String Patient_type=request.getParameter("Patient_type");
                    
                    
                    
                   
                   
//                   Relatives
  String Relative_name=request.getParameter("Realative_name");
  String Relation=request.getParameter("Relation");
  String Relative_mobile_number=request.getParameter("Relative_mobile_number");
  String Realtive_address=request.getParameter("Realtive_address");
 
  
//  for documents


                   Part prd_photo_adhar = request.getPart("prd_photo_adhar");
                   Part prd_photo_patient = request.getPart("prd_photo_patient");
                     String mainFolder="Staff",subFolder="images";
                    
                     
                      if (sessionToken != null && sessionToken.equals(requestToken)) {
                    sts=st.Save_update(patient_id,date,room_no,doctor,type,fees,Patient_name,Age,gender,phone,adhar,Address,user,hospital_code,Bed_number,Refering_doctor,
                            Father_Name,Tpa,Relative_name,Relation,Relative_mobile_number,Realtive_address,dob,charge_amount,gender_type,relation_of,policy_Number,Patient_type);
                }
                     HashMap<String ,String> altMap=new HashMap<>();
                      altMap.put("Titel", "Patent Admited");
                      altMap.put("Alt_Msg", "Some Error !!");
                      altMap.put("Alt_Type", "danger");
                   
                    if(sts!=null)
                            {
                                
                          boolean img_sts=false;
              
              
               if(prd_photo_adhar.getSize()>0)
                     {
                         String file_name=sts.replace('/','-');
                         img_sts=saveImage(request,"adhar-"+file_name,prd_photo_adhar,mainFolder,subFolder);
                         //stuPhoto_name=stuCode+".jpg";;
                        // stuPhoto_path=mainFolder+File.separator+subFolder+File.separator+stuPhoto_name;
                         
                     }  
               
               
                if(prd_photo_patient.getSize()>0)
                     {
                         String file_name=sts.replace('/','-');
                         img_sts=saveImage(request,"patient-"+file_name,prd_photo_patient,mainFolder,subFolder);
                         //stuPhoto_name=stuCode+".jpg";;
                        // stuPhoto_path=mainFolder+File.separator+subFolder+File.separator+stuPhoto_name;
                         
                     }  
                    
                                
                        {altMap.put("Alt_Msg", "Treatment Started");
                      altMap.put("Alt_Type", "success");
                      String message= "New Patient  Added";
                     //
                     /*
                      notificationId: 0, // or null if you want to omit for new notifications
                                          code: "TEST_CODE",
                                          notificationType: "Non-Action", // or "Action" as needed
                                          notificationMessage: "This is a dummy notification for testing.",
                                          notificationFrom: "Test Sender",
                                          notificationCode: "DUMMY123",
                                          redirectUrl: "/some/path",
                                          isRead: false,
                                          role: "Admin",
                     */
                      
                     NotificationModel notificationModel =new NotificationModel();
                     notificationModel.setCode(sts);
                     notificationModel.setIsRead(img_sts);
                     notificationModel.setNotificationType("Action");
                     notificationModel.setNotificationFrom("Admit");
                     notificationModel.setNotificationCode("Admit"+sts);
                     notificationModel.setRedirectUrl("../IPD/WIPD0002.jsp?patient_id="+sts);
                     notificationModel.setNotificationMessage(message);
                     NotificationService notificationService=new NotificationService();
                     notificationService.sendNotification(user, notificationModel);
                     //
                      
                            }
                session.setAttribute("Alt_Data",altMap);
                response.sendRedirect("Main/IPD/WIPD0001.jsp");
                }
                    else{
                        {altMap.put("Alt_Msg", "Some Error Occured");
                      altMap.put("Alt_Type", "error");
                      
                     
                      
                            }
                session.setAttribute("Alt_Data",altMap);
                response.sendRedirect("Main/IPD/WIPD0001.jsp");
                    }
                
                }
         
                
                
                
                
            }catch(Exception Ex){
             Logger.getLogger(WIPD0001_SERV.class.getName()).log(Level.SEVERE, null, Ex);
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

    
    
    
       private boolean saveImage(HttpServletRequest request,String imgCode, Part imgPhoto,String mainFolder,String subFolder) {
        
        String Photo_name="";
        String Photo_path="";
    
        boolean sts=false;
        FileOutputStream fos=null;
        try{
            
        if(imgPhoto.getSize()>0)
                     {
                        
                         Photo_name=imgCode+".jpg";//STF-001.jpg
                         
                         System.out.println("Photo_name--> "+Photo_name);
                         InputStream is=imgPhoto.getInputStream();
                            byte [] data=new byte[is.available()];
                            is.read(data);
                            
                            String path=request.getRealPath("/")+mainFolder;
                            File fileSaveDir=new File(path);
                            if(!fileSaveDir.exists()){
                                fileSaveDir.mkdir();
                            } 
                             path+=File.separator+subFolder;
                            File fileSaveDir2=new File(path);
                            if(!fileSaveDir2.exists()){
                                fileSaveDir2.mkdir();
                            }
                            path+=File.separator+Photo_name;
                            
                            fos=new FileOutputStream(path);
                            fos.write(data);
                            
                            
                            Photo_path=mainFolder+File.separator+subFolder+File.separator+Photo_name;
                           
                            
                            sts=true;
                     }
        }
        catch(Exception e)
        {Logger.getLogger(WIPD0001_SERV.class.getName()).log(Level.SEVERE, null, e);
                System.out.println("Error-> "+e.getMessage());}
        finally{
            try {
                fos.close();
                return sts;
            } catch (IOException ex) {
                Logger.getLogger(WIPD0001_SERV.class.getName()).log(Level.SEVERE, null, ex);
            }
             return sts;
        }
       
    }
}

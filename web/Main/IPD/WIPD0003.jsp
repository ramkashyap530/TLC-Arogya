<%-- 
    Document   : WMAS0004
    Created on : 3 Feb, 2022, 3:00:14 PM
    Author     : Tarun
--%>

<%@page import="IPD.WIPD0001_Dao"%>
<%@page import="java.lang.String"%>
<%@page import="OPD.WOP0001_Dao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <link rel="stylesheet" type="text/css" href="../Design/All/css/select2.min.css">
       <%@include file="../Design/All/All_css.jsp" %>
          <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css">
    

<style>
    /* Chrome, Safari, Edge, Opera */
input::-webkit-outer-spin-button,
input::-webkit-inner-spin-button {
  -webkit-appearance: none;
  margin: 0;
}

/* Firefox */
input[type=number] {
  -moz-appearance: textfield;
}
    </style>
    
    <%
        String code=request.getParameter("");
        %>

          <script src="../Js/WIPD0001JS.js"></script>
    </head>
    <body onload="get_ipd_rec()">
     <div class="wrapper">
        <%@include  file="../Master/Header_Menu.jsp" %>
        <div id="content-page" class="content-page">
            
             <input type="hidden" value="<%=user_id%>" name="user">
                        <input type="hidden" value="<%=Hospital_code%>" name="hospital_code" id="hospital_code">            
        
                  <div class="iq-card-body">
                   <div class="iq-card">
                        <div class="iq-card-header d-flex justify-content-between">
                           <div class="iq-header-title">
                              <h4 class="card-title">Patient Details </h4>
                           </div>
                        </div>
                        <div class="iq-card-body">
                           <div class="row">
                              <div class="col-md-3">
                                  <div class="row">
                                      <div class="col-md-6">
                                 <b> Patient Name:</b>
                                 </div>
                                      <div class="col-md-6">
                                          tarun
                                          </div>
                                 </div>
                                  </div>
                               
                               
                               
                               <div class="col-md-3">
                                  <div class="row">
                                      <div class="col-md-6">
                                 <b>  Doctor Name:</b>
                                 </div>
                                      <div class="col-md-6">
                                          tarun
                                          </div>
                                 </div>
                                  </div>
                               
                               
                                       <div class="col-md-2">
                                  <div class="row">
                                      <div class="col-md-6">
                                 <b>Room No</b>
                                 </div>
                                      <div class="col-md-6">
                                          101
                                          </div>
                                 </div>
                                  </div>
                               
                               <div class="col-md-1">
                                  <div class="row">
                                      <div class="col-md-6">
                                 <b>Bed:</b>
                                 </div>
                                      <div class="col-md-6">
                                          101
                                          </div>
                                 </div>
                                  </div>
                               
                               
                       
                       
                               
                               
                               </div>
                               
                               </div>
                            
                            
                          
                           <br>
                           
                           
                           
                           <div class="iq-card-body">
                           <ul class="nav nav-pills mb-3 nav-fill" id="pills-tab-1" role="tablist">
                              <li class="nav-item">
                                 <a class="nav-link active" id="pills-home-tab-fill" data-toggle="pill" href="#pills-home-fill" role="tab" aria-controls="pills-home" aria-selected="true">Tremaent Sheet </a>
                              </li>
                              <li class="nav-item">
                                 <a class="nav-link" id="pills-profile-tab-fill" data-toggle="pill" href="#pills-profile-fill" role="tab" aria-controls="pills-profile" aria-selected="false">Diagonisis</a>
                              </li>
                              <li class="nav-item">
                                 <a class="nav-link" id="pills-contact-tab-fill" data-toggle="pill" href="#pills-Chagred-fill" role="tab" aria-controls="pills-contact" aria-selected="false">Charges</a>
                              </li>
                              <li class="nav-item">
                                 <a class="nav-link" id="pills-contact-tab-fill" data-toggle="pill" href="#pills-Payment-fill" role="tab" aria-controls="pills-contact" aria-selected="false">Payment</a>
                              </li>
                              
                              <li class="nav-item">
                                 <a class="nav-link" id="pills-contact-tab-fill" data-toggle="pill" href="#pills-Discharge-fill" role="tab" aria-controls="pills-contact" aria-selected="false">Discharge</a>
                              </li>
                           </ul>
                           <div class="tab-content" id="pills-tabContent-1">
                              <div class="tab-pane fade show active" id="pills-home-fill" role="tabpanel" aria-labelledby="pills-home-tab-fill">
                                 <!--for teamtmentsgeet-->
                                <div class="row">
                                    <div class="col-md-4" style="border: 1px solid">
                                       <div class="col-md-12"> <center><b>CLINICAL INFORMATION</b></center></div>
                                       <div class="col-md-12"> <center><b style="font-size: 10px">SHORT PRESENT AND PAST HISTORY OF PATIENTS ILNESS</b><center></div>
                                       <hr>
                                       <p><u>GENERAL EXAMINATION</u></p>  
                                       
                                       <div class="row">
                                             <div class="col-md-2">
                                                 <B>Pulse</b>
                                                 </div>
                                             <div class="col-md-2">
                                                 255
                                                 </div>
                                           <div class="col-md-2">
                                                 <B>Pallor</b>
                                                 </div>
                                             <div class="col-md-2">
                                                 255
                                                 </div>
                                           <div class="col-md-2">
                                                 <B>Clubbing</b>
                                                 </div>
                                             <div class="col-md-2">
                                                 255
                                                 </div>
                                             </div>
                                       
                                       
                                       <div class="row">
                                             <div class="col-md-2">
                                                 <B>B.P</b>
                                                 </div>
                                             <div class="col-md-2">
                                                 255
                                                 </div>
                                           <div class="col-md-2">
                                                 <B>Cyanosis</b>
                                                 </div>
                                             <div class="col-md-2">
                                                 255
                                                 </div>
                                           <div class="col-md-2">
                                                 <B>LYMPH Nodes</b>
                                                 </div>
                                             <div class="col-md-2">
                                                 255
                                                 </div>
                                             </div>
                                       
                                       
                                       <div class="row">
                                             <div class="col-md-2">
                                                 <B>Temp</b>
                                                 </div>
                                             <div class="col-md-2">
                                                 255
                                                 </div>
                                           <div class="col-md-2">
                                                 <B>Odema</b>
                                                 </div>
                                             <div class="col-md-2">
                                                 255
                                                 </div>
                                           <div class="col-md-2">
                                                 <B>Consciousness</b>
                                                 </div>
                                             <div class="col-md-2">
                                                 255
                                                 </div>
                                             </div>
                                       
                                       
                                             </div>
                                         </div>
                                 
                                 
                                     </div>
                                 
                                 
                                 
                                 
                                 <!--ends here-->
                                  
                                  
                                  
                                  
                                  
                             
                              <div class="tab-pane fade" id="pills-profile-fill" role="tabpanel" aria-labelledby="pills-profile-tab-fill">
                                 <p>Diagonis</p>
                              </div>
                              <div class="tab-pane fade" id="pills-contact-fill" role="tabpanel" aria-labelledby="pills-contact-tab-fill">
                                 <p>Chagred</p>
                              </div>
                               
                               <div class="tab-pane fade" id="pills-Payment-fill" role="tabpanel" aria-labelledby="pills-contact-tab-fill">
                                 <p>Payment</p>
                              </div>
                               
                               <div class="tab-pane fade" id="pills-Discharge-fill" role="tabpanel" aria-labelledby="pills-contact-tab-fill">
                                 <p>Discharge</p>
                              </div>
                           </div>
                        </div>
                     </div>
                  
                   
                   
                    
               </div>
            
            
            
            
            </div>
        
        
        </div>
                        
                        
        
        
              <%@include file='../Design/All/All_js.jsp'%>
         <script src="../Design/All/js/select2.full.min.js"></script>
    
    <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.js"></script>
    
    
    
    
    <Script>
       
        
        </script>
    
     <%
          
           if(session.getAttribute("Alt_Data")!=null)
           {
               HashMap<String,String> altMap=(HashMap<String ,String>)session.getAttribute("Alt_Data");
              
            
               %>
               
<script>swal("<%=altMap.get("Titel")%>", "<%=altMap.get("Alt_Msg")%>","<%=altMap.get("Alt_Type")%>");</script>
<%
                    session.removeAttribute("Alt_Data");
                   
           }
  

          
          %>
    </body>
</html>

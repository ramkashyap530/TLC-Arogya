<%-- 
    Document   : WMAS0001.jsp
    Created on : 2 Feb, 2022, 3:12:06 PM
    Author     : Tarun
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="Master.WMAS0001_Dao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hospital Master</title>
         <%@include file="../Design/All/All_css.jsp" %>
          <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css">
          <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>

          <script src="../Js/WMAS0001JS.js"></script>
          
             <%
   String pharmacy_code="";
   pharmacy_code=request.getParameter("phr_id");
    WMAS0001_Dao doc_data=new WMAS0001_Dao();
     HashMap<String,String> map_phr=doc_data.get_update_phr(pharmacy_code);
   
   %>       
          
    </head>
    <body onload="get_phr_details()">
        <div class="wrapper">
        <%@include  file="../Master/Header_Menu.jsp" %>
        
        <div id="content-page" class="content-page">
            <div class="container-fluid">
               <div class="row">
                  <div class="col-sm-12 col-lg-6">
                     <div class="iq-card">
                        <div class="iq-card-header d-flex justify-content-between">
                           <div class="iq-header-title">
                              <h4 class="card-title">Pharmacy Master</h4>
                           </div>
                        </div>
                        <div class="iq-card-body">
                           <p>Pharmacy Detail</p>
                           <form action="../../WMAS0001_SERV" method="post" enctype="multipart/form-data">
                        <input type="hidden" value="18" name="value">
                        <input type="hidden" value="<%=user_id%>" name="user">
                        <input type="hidden" value="<%=Hospital_code%>" name="hospital_code">
                        <input type="hidden" name="pharmacy_code" value="<%=pharmacy_code%>">
                        
                              <div class="form-row">
                                 
                                <div class="col-md-4 mb-3">
                                                                       
                                    <label for="validationDefault01">Pharmacy Name</label>
                                    <input type="text" class="form-control" id="validationDefault01"  name="pharmacy_name" required value="<%=map_phr.get("pharmacy_name")%>">
                             
                                 </div> 
                                  <div class="col-md-4 mb-3">
                                                                       
                                    <label for="validationDefault01">Pharmacy Registration</label>
                                    <input type="text" class="form-control" id="validationDefault01"  name="pharmacy_reg" required value="<%=map_phr.get("phr_reg")%>">
                             
                                 </div> 
                                  
                              
                                  
                                  
                                  <div class="col-md-4 mb-3">
                                                                       
                                    <label for="validationDefault01">Pharmacy Gst</label>
                                    <input type="text" class="form-control" id="validationDefault01"  name="pharmacy_gst" required value="<%=map_phr.get("phr_gst")%>">
                             
                                 </div> 
                                  
                                  
                                  <div class="col-md-8 mb-3">
                                                                       
                                    <label for="validationDefault01">Pharmacy Address</label>
                                    <input type="text" class="form-control" id="validationDefault01"  name="pharmacy_address" required value="<%=map_phr.get("phr_addrees")%>">
                             
                                 </div> 
                                  
                                  
                                    <div class="col-md-4 mb-3">
                                                                       
                                    <label for="validationDefault01">Number</label>
                                    <input type="text" class="form-control" id="validationDefault01"  name="number" required value="<%=map_phr.get("phr_number")%>">
                             
                                 </div> 
                                  
                                    
                                  
                                  
                                  
                                  <div class="col-md-6">
                        <label for="validationDefault01">Logo</label>
                                                   <div class="form-group mt-4">
                                                       
                                                       <input type="file" id="file_adhar"  name="prd_photo_logo_pharmacy" class="form-control mt-1">
                                                       
                                                       </div>
                        </div>
                     
                                               
                                               
                                               <div class="ml-2 col-sm-2">
                                                   <img src="../Design/avatr.jpg"   id="preview" class="img-thumbnail">
                                               </div>
      
                                 
                              </div>
                        
                        
                        
                        <!--for logo--> 
                        
                       
                        
                        <!--ends here--> 
                              
                              <div class="form-group">
                                <center> <button class="btn btn-primary" type="submit">Update</button></center>
                              </div>
                           </form>
                        </div>
                     </div>
                     
            
        </div>
                                    
                                    
                                    
                                    <!--for submodule-->
                                   <div class="col-sm-12 col-lg-6">
                     <div class="iq-card">
                        <div class="iq-card-header d-flex justify-content-between">
                           <div class="iq-header-title">
                              <h4 class="card-title">Active Pharmacy</h4>
                           </div>
                        </div>
                        <div class="iq-card-body">
                            <div class='table-responsive'>
                        <table class="table table-bordered table-responsive-md table-striped text-center" id="M_tab">
                                 <thead>
                                    <tr>
                                        <th style="white-space: nowrap">S.No</th>
                                        <th style="white-space: nowrap">Logo</th>
                                        <th style="white-space: nowrap">Hospital Name</th>
                                        <th style="white-space: nowrap">Hospital Registration</th>
                                       <th style="white-space: nowrap">Hospital Gst</th>
                                       <th style="white-space: nowrap">Address</th>
                                       <th style="white-space: nowrap">Number </th>
                                    
                                       <th style="white-space: nowrap">Action</th>
                                    
                                       
                                       
                                    </tr>
                                 </thead>
                                 <tbody id="tbody">
                                    
                                    
                                    
                                    
                                 </tbody>
                              </table>
                     
                                </div>
                        
                              
                              
                              
                          
                        </div>
                     </div>
                     
            
        </div> 
                                    <!--ends here--> 
        
        
        
        </div>
            </div><!-- <> -->
            </div>
        </div><!-- comment -->

        <%@include file='../Design/All/All_js.jsp'%>
            <script src="../../Design/app-assets/js/scripts/forms/select/form-select2.min.js"></script>
    
    <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.js"></script>
        
        
        
        
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

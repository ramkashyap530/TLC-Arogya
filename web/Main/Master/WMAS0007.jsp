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
        <title>Assign Module</title>
         <%@include file="../Design/All/All_css.jsp" %>
          <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css">
          <script src="../Js/WMAS0001JS.js"></script>
    </head>
    <body onload="module()">
        <div class="wrapper">
        <%@include  file="../Master/Header_Menu.jsp" %>
        
        <div id="content-page" class="content-page">
            <div class="container-fluid">
               <div class="row">
                  <div class="col-sm-12 col-lg-12">
                     <div class="iq-card">
                        <div class="iq-card-header d-flex justify-content-between">
                           <div class="iq-header-title">
                              <h4 class="card-title">Hospital Master</h4>
                           </div>
                        </div>
                        <div class="iq-card-body">
                          
                           <form method="post" action="../../WMAS0006_serv" enctype="multipart/form-data">
                                      <input type='hidden' value='16' name='value'>
                                  <div class="row" id="update">

                                      <!--for The branch office--> 
                             
                                      <!--ends here--> 
                                      
                                      <div class='col-md-2' id="company_name">
                                          <label>Hospital Name</label>
                                          <div class="form-group">
                                              <input type="text" class='form-control input-sm'  name='name' >
                                          </div>
                                      </div>
                                      
                                      
                                      <div class='col-md-2' id="gst">
                                          <label>Registration No</label>
                                          <div class="form-group">
                                              <input type="text" class='form-control input-sm'  name='Registration' >
                                          </div>
                                      </div>
                                      
                                       <div class='col-md-2' id="gst">
                                          <label>Gst No</label>
                                          <div class="form-group">
                                              <input type="text" class='form-control input-sm'  name='gst' >
                                          </div>
                                      </div>
                                      
                                      
                                      
                                      
                                      
                                           
                                      
                                       
                                      
                                          <div class='col-md-2'>
                                          <label>Number_1 </label>
                                          <div class="form-group">
                                              <input type="number" class='form-control input-sm'  name='number_1'>
                                          </div>
                                      </div>
                                      
                                      
                                      
                                       <div class='col-md-2'>
                                          <label>Number_2 </label>
                                          <div class="form-group">
                                              <input type="number" class='form-control input-sm'  name='number_2'>
                                          </div>
                                      </div>
                                      
                                      
                                        <div class='col-md-2'>
                                          <label>Number_3 </label>
                                          <div class="form-group">
                                              <input type="number" class='form-control input-sm'  name='number_3'>
                                          </div>
                                      </div>
                                      
                                      
                                      
                                      
                                      
                                      <div class="col-md-2">
                                                   <label>Upload Image </label>
                                                   <div class="form-group">
                                                       <input type="file" id="myFile"  name="prd_photo">
                                                       
                                                       </div>
                                                   </div>
                                               
                                               <div class="col-md-2">
                                                   <img src="../../Design/images/avatr.jpg"  width="50px;height:50px" id="preview" class="img-thumbnail">
                                                   
</div>
                                      
                                     <div class='col-md-1 mt-2'>
                                          <button class="btn btn-sm btn-success">Save</button>
                                      </div>
                                        
                                      
                                        
                                      
                                          
                                      
                                      
                                      
                                  </div>
                                      </form>
                        </div>
                     </div>
                     
            
        </div>
                                    
                                    
                                    
                                    <!--for submodule-->
                                   
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

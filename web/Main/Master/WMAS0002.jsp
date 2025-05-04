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
                  <div class="col-sm-12 col-lg-6">
                     <div class="iq-card">
                        <div class="iq-card-header d-flex justify-content-between">
                           <div class="iq-header-title">
                              <h4 class="card-title">Modules</h4>
                           </div>
                        </div>
                        <div class="iq-card-body">
                          
                           <form action="../../WMAS0001_SERV" method="post">
                        <input type="hidden" value="4" name="value">
                        
                              <div class="form-row">
                                 
                                 
                                 
                                 
                                 <div class="col-md-6 mb-3">
                                                                       
                                    <label for="validationDefault01">Module Name</label>
                                    <input type="text" class="form-control" id="validationDefault01"  name="modulename" required>
                             
                                 </div>
                                  
                                  <div class="col-md-6 mb-3">
                                   
                                    
                                    <label for="validationDefault01">Icon</label>
                                    <input type="text" class="form-control" id="validationDefault01"   name="icon" required>
                                
                                 </div>
                                    
                                    
                                    
                                     
                                 
                              </div>
                              
                              <div class="form-group">
                              <center>   <button class="btn btn-primary" type="submit">Save</button></center>
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
                              <h4 class="card-title">Sub Modules</h4>
                           </div>
                        </div>
                        <div class="iq-card-body">
                           <p>Assign Required Sub Modules To Users</p>
                           <form action="../../WMAS0001_SERV" method="post">
                        <input type="hidden" value="5" name="value">
                        
                              <div class="form-row">
<!--                                 <div class="col-md-3 mb-3">
                                                                       
                                    <label for="validationDefault01">Sub Mdid</label>
                                    <input type="text" class="form-control" id="validationDefault01"  name="submdid" required>
                             
                                 </div>-->
                                    
                                    
                                   <div class="col-md-3 mb-3">
                                                                       
                                    <label for="validationDefault01">Mdid</label>
                                    <input type="text" class="form-control" id="validationDefault01"  name="Mdid" required>
                             
                                 </div>
                                  
                                   <div class="col-md-3 mb-3">
                                                                       
                                    <label for="validationDefault01">Sub Module Name</label>
                                    <input type="text" class="form-control" id="validationDefault01"  name="sub_module_name" required>
                             
                                 </div>
                                   <div class="col-md-3 mb-3">
                                                                       
                                    <label for="validationDefault01">URL</label>
                                    <input type="text" class="form-control" id="validationDefault01"  name="url" required>
                             
                                 </div>

                                     
                                 
                              </div>
                              
                              <div class="form-group">
                                  <center> <button class="btn btn-primary" type="submit">Save</button></center>
                              </div>
                           </form>
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

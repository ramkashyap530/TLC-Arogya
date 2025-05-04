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
          <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>

          <script src="../Js/WMAS0001JS.js"></script>
                       <%
   String Procedure_code="";
   Procedure_code=request.getParameter("Procedure_code");
//    WMAS0001_Dao doc_data=new WMAS0001_Dao();
//     HashMap<String,String> map_phr=doc_data.get_update_phr(Procedure_code);
   
   %>       
          
          
    </head>
    <body onload="get_all_procedure()">
        <div class="wrapper">
        <%@include  file="../Master/Header_Menu.jsp" %>
        
        <div id="content-page" class="content-page">
            <div class="container-fluid">
               <div class="row">
                  <div class="col-sm-12 col-lg-6">
                     <div class="iq-card">
                        <div class="iq-card-header d-flex justify-content-between">
                           <div class="iq-header-title">
                              <h4 class="card-title">Add OPD Procedure</h4>
                           </div>
                        </div>
                        <div class="iq-card-body">
                           <p>OPD Procedure Master</p>
                           <form action="../../WMAS0001_SERV" method="post" enctype="multipart/form-data">
                        <input type="hidden" value="20" name="value">
                        <input type="hidden" value="<%=user_id%>" name="user">
                        <input type="hidden" value="<%=Hospital_code%>" name="hospital_code">
                        <input type="hidden" value="<%=Procedure_code%>" name="Procedure_code">
                        
                              <div class="form-row">
                                 
                                  
                                  
                                  
                                  
                                <div class="col-md-4 mb-3">
                                                                       
                                    <label for="validationDefault01">Procedure Name</label>
                                    <input type="text" class="form-control" id="validationDefault01"  name="procedure_name" required>
                             
                                 </div> 
                                  <div class="col-md-4 mb-3">
                                                                       
                                    <label for="validationDefault01">Rate</label>
                                    <input type="number" class="form-control" id="validationDefault01"  name="procedure_rate" required>
                             
                                 </div> 
                                  

                                 
                              </div>
                              
                              <div class="form-group">
                                <center> <button class="btn btn-primary" type="submit">ADD</button></center>
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
                              <h4 class="card-title">Procedure List</h4>
                           </div>
                        </div>
                        <div class="iq-card-body">
                        <div class="table-responsive">
                    <table class="table table-bordered table-responsive-md table-striped text-center" id="M_tab">
                                 <thead>
                                    <tr>
                                        <th style="white-space: nowrap">S.No</th>
                                         <th style="white-space: nowrap">Procedure Name</th>
                                        <th style="white-space: nowrap">Rate</th>
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

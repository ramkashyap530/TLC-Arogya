<%-- 
    Document   : WMAS0004
    Created on : 3 Feb, 2022, 3:00:14 PM
    Author     : Tarun
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
       <%@include file="../Design/All/All_css.jsp" %>
          <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css">
          <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>

          <script src="../Js/WACCM0001JS.js"></script>
    </head>
    <body onload="get_groups();">
     <div class="wrapper">
        <%@include  file="../Master/Header_Menu.jsp" %>
        <div id="content-page" class="content-page">
            <form action="../../WMAS0001_SERV" method="post" enctype="multipart/form-data">
                        <input type="hidden" value="10" name="value">
                        <input type="hidden" value="<%=user_id%>" name="user">
                        <input type="hidden" value="<%=Hospital_code%>" name="hospital_code">
                        <input type="hidden" value="" name="staff_id">
        <div class="container-fluid">
               <div class="row">
                  
                  <div class="col-lg-9">
                     <div class="iq-card">
                        <div class="iq-card-header d-flex justify-content-between">
                           <div class="iq-header-title">
                              <h4 class="card-title">Group Creation (Secondry)</h4>
                           </div>
                        </div>
                        <div class="iq-card-body">
                           <div class="new-user-info">
                              
                                 <div class="row">
                                    <div class="form-group col-md-6" >
                                       <label for="fname">Group Name</label>
                                       <input type="text" class="form-control" id="fname" placeholder="Group Name" name="g_name">
                                    </div>
                                   
                                    <div class="form-group col-md-6" id="search-autocomplete">
                                       <label for="add1">Under</label>
                                       <select class="form-control" name="under">
                                           <option>
                                               </option>
                                       </select>
                                    </div>
                                   

                                    
                                    <div class="form-group col-md-6">
                                       <label for="mobno">Group Behave Like Sub Ledger</label>
                                       <select class="form-control" name="Group_behave">
                                           <option>No</option>
                                           <option>Yes</option>
                                       </select>
                                       </div>
                                     
                                     <div class="form-group col-md-6">
                                       <label for="mobno">Net Debit/Credit Balance Reporting</label>
                                       <select class="form-control" name="Net_debit">
                                           <option>No</option>
                                           <option>Yes</option>
                                       </select>
                                       </div>
                                     
                                     
                                         <div class="form-group col-md-6">
                                       <label for="mobno">Used For Calculation</label>
                                       <select class="form-control" name="Used_calculation">
                                           <option>No</option>
                                           <option>Yes</option>
                                       </select>
                                       </div>

                                     
                                     <div class="form-group col-md-6">
                                       <label for="mobno">Method To Allocate When Used In Purchase</label>
                                       <select class="form-control" name="Used_calculation">
                                           <option>Not Applicable</option>
                                           <option>Applicable</option>
                                       </select>
                                       </div>
                                     
                                    
                               
                               
                               
                               
                                    
                                    
                                    
                                     
                                 </div>
                               <center>  <button type="submit" class="btn btn-primary">Add</button></center>
                            
                           </div>
                        </div>
                     </div>
                  </div>
                   
                  
                   
                   <div class="col-lg-3">
                     <div class="iq-card">
                        <div class="iq-card-header d-flex justify-content-between">
                           <div class="iq-header-title">
                              <h4 class="card-title">All Group List</h4>
                           </div>
                        </div><!-- comment -->
                        <div class="iq-card-body"><!-- comment -->
                                <div class="col-md-6" id="groups">
                                    
                                    </div>
                                </div>
                            
                        </div><!-- comment -->
                        </div>
                       </div>
            
                   
                   
                   
               </div>
            
            </div>
          </form>
        
        </div>
        
        
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

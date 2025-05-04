<%-- 
    Document   : WMAS0004
    Created on : 3 Feb, 2022, 3:00:14 PM
    Author     : Tarun
--%>

<%@page import="Laboratory.WLAB0001_dao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
       <%@include file="../Design/All/All_css.jsp" %>
          <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css">
          <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>

          <script src="../Js/WLAB0001Js.js"></script>
    </head>
    <body onload="get_test_assigned_to_pat()">
     <div class="wrapper">
        <%@include  file="../Master/Header_Menu.jsp" %>
        <div id="content-page" class="content-page">
            
                        
        <div class="container-fluid">
             <form action="../../WLAB0001_Serv" method="post" id="form1">  
                 
                 <input type="hidden" name="value" value="6">
                                                     <input type="hidden" name="user" id="user" value="<%=Real_Name%>">
                                                     <input type="hidden" name="company_code" value="<%=Hospital_code%>">
               <div class="row">
<!--                  <div class="col-lg-3">
                     <div class="iq-card">
                        <div class="iq-card-header d-flex justify-content-between">
                           <div class="iq-header-title">
                              <h4 class="card-title">Assign Lab Test</h4>
                           </div>
                            
                        </div>
                        <div class="iq-card-body">
                         
                              
                              <div class="form-group">
                                 <label>Patient Name</label>
                                 <select class="form-control" id="selectuserrole" name="patient_id">
                                    <option>Select</option>
                                     <%
                                        WLAB0001_dao st_1=new WLAB0001_dao();
                                        ArrayList<String> data=st_1.getdata("1","");
                                        for(String cd:data){
                                            out.println(cd);
                                        }
                                        %>
                                 </select>
                                    
                              </div>
                            
                           <center> <button class='btn btn-sm btn-success'>Add</button></center>
                 
                          
                        </div>
                     </div>
                  </div>-->
                       </form>
                   
                
                                 
                                 
                                 
                                 
                  <div class="col-lg-6">
                     <div class="iq-card">
                        <div class="iq-card-header d-flex justify-content-between">
                           <div class="iq-header-title">
                              <h4 class="card-title">Reffered Test List Pending</h4>
                           </div>
                        </div>
                        <div class="iq-card-body">
                           <div class="new-user-info">
                              
                              
                                   
                        <div class="table-responsive">
                               <table class="table table-bordered table-responsive-md table-striped text-center">
                                 <thead>
                                    <tr>
                                      
                                      
                                       <th><input type="checkbox"></th>
                                       <th style="white-space: nowrap">Patient Name</th>
                                       <th>Age</th>
                                        <th>Test Name</th>
                                        <th style="white-space: nowrap">Test Status</th>
                                        <th>Action</th>
                                    </tr>
                                 </thead>
                                 <tbody id="tbody">
                                    
                                    
                                    
                                    
                                 </tbody>
                              </table>
                            </div>
                                 
                       
                                 
                            
                           </div>
                        
                        </div>
                        
                     </div>
                        
                  </div>
                                 <div class="col-lg-6">
                     <div class="iq-card">
                        <div class="iq-card-header d-flex justify-content-between">
                           <div class="iq-header-title">
                              <h4 class="card-title">Reffered Test List Done</h4>
                           </div>
                        </div>
                        <div class="iq-card-body">
                           <div class="new-user-info">
                              
                              
                                   
                        <div class="table-responsive">
                               <table class="table table-bordered table-responsive-md table-striped text-center">
                                 <thead>
                                    <tr>
                                      
                                      
                                       <th><input type="checkbox"></th>
                                       <th>Patient Name</th>
                                       <th>Age</th>
                                        <th>Test Name</th>
                                        <th>Test Status</th>
                                        <th>Action</th>
                                    </tr>
                                 </thead>
                                 <tbody id="tbody_done">
                                    
                                    
                                    
                                    
                                 </tbody>
                              </table>
                            </div>
                                 
                       
                                 
                            
                           </div>
                        
                        </div>
                        
                     </div>
                        
                  </div>
                        
                      
                        
              
                  
                   
                   
               
               </div>
             </form> 
                        
                        
                        
                     </div>  
                        
                        
                        
                  </div>
                        
                        
        <!--for salary advance--> 
        
        <div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                              <div class="modal-dialog modal-dialog-centered" role="document">
                                 <div class="modal-content">
                                    <div class="modal-header">
                                       <h5 class="modal-title" id="exampleModalCenterTitle">All Test Available</h5>
                                       <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                       <span aria-hidden="true">&times;</span>
                                       </button>
                                    </div>
                                    <div class="modal-body">
                                    
                                     <div class="row">
                                         <div class="col-md-2">
                                           
                                            </div>
                                         
                                         
                                         
                                         
                                         
                                         
                                         
                                         
                                         </div>
                                      
                                    </div>
                                    <div class="modal-footer">
                                       <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                     
                                    </div>
                                 </div>
                              </div>
                           </div>
        
        <!--ends here--> 
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
            </div>
        
        
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
          
          
           <script>
             $(function(){
    var dtToday = new Date();
    
    var month = dtToday.getMonth() + 1;
    var day = dtToday.getDate();
    var year = dtToday.getFullYear();
    if(month < 10)
        month = '0' + month.toString();
    if(day < 10)
        day = '0' + day.toString();
    
    var maxDate = year + '-' + month + '-' + day;
    
    $('#txtDate').attr('max', maxDate);
});




              </script>
              
              
             
    </body>
</html>

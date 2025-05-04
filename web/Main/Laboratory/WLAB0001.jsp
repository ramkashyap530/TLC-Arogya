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

          <script src="../Js/WLAB0001Js.js"></script>
    </head>
    <body onload="get_test_name()">
     <div class="wrapper">
        <%@include  file="../Master/Header_Menu.jsp" %>
        <div id="content-page" class="content-page">
            
                        
        <div class="container-fluid">
           
                                                    
               <div class="row">
                  <div class="col-lg-3">
                     <div class="iq-card">
                        <div class="iq-card-header d-flex justify-content-between">
                           <div class="iq-header-title">
                              <h4 class="card-title">Add Lab Test</h4>
                           </div>
                        </div>
                            
                        <div class="iq-card-body">
                      <form action="../../WLAB0001_Serv" method="post" id="form1">  
                 
                    <input type="hidden" name="value" value="1">
                                                     <input type="hidden" name="user" value="<%=Real_Name%>">
                                                     <input type="hidden" name="company_code" value="<%=Hospital_code%>">
                              
                              <div class="form-group">
                                 <label>Test Name</label>
                                 <input type='text' class='form-control' name='test_name'>
                                    
                              </div>
                            
                           <center> <button class='btn btn-sm btn-success'>Add</button></center>
                 
                                </form>
                        </div>
                               
                     </div>
                            
                  </div>
                   
                   
                
                                                     
                                                     
                                                     
                                                     
                                                     
                                                     
                                                     
                                                     
                                                     
                                                     
                                                     
                                                     
                                                     
                       
                   
                 
                  <div class="col-lg-4">
                     <div class="iq-card">
                        <div class="iq-card-header d-flex justify-content-between">
                           <div class="iq-header-title">
                              <h4 class="card-title">All Test</h4>
                           </div>
                        </div>
                        <div class="iq-card-body">
                           <div class="new-user-info">
                              
                              
                                   <input type="hidden" value="2" name="value">
                        <input type="hidden" value="<%=user_id%>" name="user">
                        <input type="hidden" value="<%=Hospital_code%>" name="hospital_code" id="hospital_code">
                        <input type="hidden" value="" name="staff_id">
                        <div class="table-responsive">
                               <table class="table table-bordered table-responsive-md table-striped text-center">
                                 <thead>
                                    <tr>
                                        <th><input  type="checkbox"></th>
                                      
                                       <th>Test Code</th>
                                        <th>Test Name</th>
                                        <th>Test Status</th>
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
                        
                      
                        
                        <form action="../../WLAB0001_Serv" method="post" id="form2">  
                 
                 <input type="hidden" name="value" value="4">
                        <div class="col-lg-12">
                     <div class="iq-card">
                        <div class="iq-card-header d-flex justify-content-between">
                           <div class="iq-header-title">
                              <h4 class="card-title">Test Rates</h4>
                           </div>
                            <button class='btn btn-sm btn-success'>Set</button>
                        </div>
                        <div class="iq-card-body">
                           <div class="new-user-info">
                              
                              
                                   
                        <div class="table-responsive">
                               <table class="table table-bordered table-responsive-md table-striped text-center">
                                 <thead>
                                    <tr>
                                        <th>Sno</th>
                                      
                                      
                                        <th style="white-space: nowrap">Test Name</th>
                                        <th>Test Rate</th>
                                    </tr>
                                 </thead>
                                 <tbody id="tbody_rate">
                                    
                                    
                                    
                                    
                                 </tbody>
                              </table>
                            </div>
                                 
                       
                                 
                            
                           </div>
                        
                        </div>
                        
                     </div>
                         
                  </div>
                       
                   </form>
                  
                   
                   
               
               </div>
             </form> 
                        
                        
                        
                     </div>  
                        
                        
                        
                  </div>
                        
                        
        <!--for salary advance--> 
        
        <div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                              <div class="modal-dialog modal-dialog-centered" role="document">
                                 <div class="modal-content">
                                    <div class="modal-header">
                                       <h5 class="modal-title" id="exampleModalCenterTitle">Month Name :</h5>
                                       <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                       <span aria-hidden="true">&times;</span>
                                       </button>
                                    </div>
                                    <div class="modal-body">
                                    
                                     <div class="row">
                                         <div class="col-md-4">
                                            <label>Salary This Month</label>
                                             <input type="text" class="form-control" id="fname" placeholder="" name="f_name">
                                            </div>
                                         
                                         
                                         
                                         <div class="col-md-4">
                                            <label>Advance</label>
                                             <input type="text" class="form-control" id="fname" placeholder="Amount" name="f_name">
                                            </div>
                                         
                                         
                                         <div class="col-md-4">
                                            <label></label>
                                             <button class="btn btn-sm btn-success mt-4">Pay</button>
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

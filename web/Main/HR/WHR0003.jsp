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

          <script src="../Js/WHR0001.js"></script>
    </head>
    <body onload="get_att_data()">
     <div class="wrapper">
        <%@include  file="../Master/Header_Menu.jsp" %>
        <div id="content-page" class="content-page">
            
                        <form action='../../WHR0001_SERV' method='post' id='form1'>
        <div class="container-fluid">
            
                 
                 <input type="hidden" name="value" value="15">
                                                     <input type="hidden" name="user" value="<%=Real_Name%>">
                                                     <input type="hidden" name="company_code" value="<%=Hospital_code%>">
               <div class="row">
                  <div class="col-lg-3">
                     <div class="iq-card">
                        <div class="iq-card-header d-flex justify-content-between">
                           <div class="iq-header-title">
                              <h4 class="card-title">Select Employee</h4>
                           </div>
                        </div>
                        <div class="iq-card-body">
                         
                              
                              <div class="form-group">
                                 <label>Employee Name</label>
                                 <select class="form-control" name="dep"  id="staff"  onchange="getMonStaff()">
                                    <option>Select</option>
                                     <%
                                        WMAS0001_Dao st_1=new WMAS0001_Dao();
                                        ArrayList<String> data=st_1.getdata("6","");
                                        for(String cd:data){
                                            out.println(cd);
                                        }
                                        %>
                                 </select>
                              </div>
                            
                            
                          
                            
                           
                              
                              
                              
                              
                          
                        </div>
                     </div>
                  </div>
                                     
                  <div class="col-lg-9">
                     <div class="iq-card">
                        <div class="iq-card-header d-flex justify-content-between">
                           <div class="iq-header-title">
                              <h4 class="card-title">Details</h4>
                           </div>
                        </div>
                        <div class="iq-card-body">
                           <div class="new-user-info">
                           
                                   <input type="hidden" value="15" name="value">
                        <input type="hidden" value="<%=user_id%>" name="user">
                        <input type="hidden" value="<%=Hospital_code%>" name="hospital_code" id="hospital_code">
                        <input type="hidden" value="" name="staff_id">
                        <div class="table-responsive">
                               <table class="table table-bordered table-responsive-md table-striped text-center">
                                 <thead>
                                    <tr>
                                        <th><input  type="checkbox"></th>
                                      
                                       <th>Image</th>
                                        <th>Department</th>
                                       <th>Month</th>
                                       <th style="white-space: nowrap">Base Salary</th>
                                       
                                       <th style="white-space: nowrap">Total Present</th>
                                        <th style="white-space: nowrap">Day Shift</th>
                                        <th style="white-space: nowrap">Night Shift</th>
                                         <th style="white-space: nowrap">Night Allowance</th>
                                         
                                         <th style="white-space: nowrap">Salary <span style="font-size: 10px;white-space: nowrap">(Per Day * Total Present)</span></th>
                                        
                                          <th style="white-space: nowrap">Total Salary <span style="font-size: 10px;white-space: nowrap">(Salary + Allowance)</span></th>
                                        
                                          <th style="white-space: nowrap">Advance</th>
                                       <th style="white-space: nowrap">Paid Amount</th>
                                       <th>Final Amount <span style="font-size: 10px;white-space: nowrap">(Total Salary-Advance-Paid Amount)</span></th>
                                       <th style="white-space: nowrap">Variable Pay</th>
                                       <th style="white-space: nowrap"><span style="visibility: hidden">---------</span>Action<span style="visibility: hidden">---------</span></th>
                                       
                                       
                                    </tr>
                                 </thead>
                                 <tbody id="payment">
                                    
                                    
                                    
                                    
                                 </tbody>
                              </table>
                            </div>
                        
                        
                        <div class="col-md-12">
                       <div class="row">
                           
                           <div class="col-md-3">
                               <label>Payemnt Mode</label>
                                 <div class="form-group">
                                     <select class="select2 form-control input-sm" onchange="payment_mode()" id="mode" name="mode" required=""name="mode">
                                   <option></option>
                                   <option value="Cash">Cash</option>
                                   <option value="Check">Check</option>
                                   <option value="Online">Online</option>
                                   
                               </select>
                               </div>
                           </div>
                            <div class="col-md-3" id="Pyem_mode" hidden="" >
                               <label>Onile Mode</label>
                                 <div class="form-group">
                                     <select class="form-control input-sm" name="Pyem_mode">
                                   <option></option>
                                   <option></option>
                                   <option value="Phone_Pay">Phone Pay</option>
                                   <option value="Goggle_Pay">Goggle Pay</option>
                                   <option value="Pay_Tm">Pay TM</option>
                                    <option value="Other_Apss">Other Apps</option>
                                   <option value="Online">Online</option>
                                   
                               </select>
                               </div>
                           </div>
                           
                         
                           
                           
                           
                           <div class="col-md-3" id="trans_id" hidden>
                               <label>Transction Id</label>
                               <div class="form-group">
                               <input type="text" class="form-control input-sm" name="Transction" >
                               </div>
                               </div>
                           
                            <div class="col-md-3" id="check" hidden>
                               <label>Check Number</label>
                               <div class="form-group">
                               <input type="text" class="form-control input-sm" name="Check" >
                               </div>
                               </div>
                           
                           <center>  <button type="button" class="btn btn-primary mt-4" onclick="valthisform()">Pay Salary</button></center>
                           </div>
                        
                    </div>
                    
                                 
                        
                                 
                            
                           </div>
                        
                        </div>
                        
                     </div>
                        
                  </div>
                        
                   
                  
                   
                   
               
               </div>
             </form> 
                        
                        <div class="col-lg-12" id="compete_details" hidden="">
                     <div class="iq-card">
                        <div class="iq-card-header d-flex justify-content-between">
                           <div class="iq-header-title">
                              <h4 class="card-title">Complete Details</h4>
                           </div>
                        </div>
                        <div class="iq-card-body">
                            <div class="row">
                          <div class="col-md-3">
                              </div>
                                
                                <div class="col-md-5" style="background: " id="sal_det">
                                    <div class="row">
                                        <div class="col-md-4">
                                            <label>Total Salary</label>
                                             <input type="text" class="form-control" id="fname" placeholder="Total Salary" name="f_name">
                                            </div>
                                        
                                        <div class="col-md-4">
                                            <label>MRA</label>
                                             <input type="text" class="form-control" id="fname" placeholder="MRA" name="f_name">
                                            </div>
                                        
                                        <div class="col-md-4">
                                            <label>Incentive</label>
                                             <input type="text" class="form-control" id="fname" placeholder="Incentive" name="f_name">
                                            </div>
                                        </div>
                                    
                                    <div class="row">
                                    <div class="col-md-4">
                                            <label>PF</label>
                                             <input type="text" class="form-control" id="fname" placeholder="PF" name="f_name">
                                            </div>
                                        
                                        <div class="col-md-4">
                                            <label>TDS</label>
                                             <input type="text" class="form-control" id="fname" placeholder="TDS" name="f_name">
                                            </div>
                                        
                                        <div class="col-md-4">
                                            <label>ECIS</label>
                                             <input type="text" class="form-control" id="fname" placeholder="ECIS" name="f_name">
                                            </div>
                                        </div>
                                    
                                    
                                    
                                    
                                    <div class="row">
                                    <div class="col-md-4">
                                            <label>Advance</label>
                                             <input type="text" class="form-control" id="fname" placeholder="Advance" name="f_name">
                                            </div>
                                        
                                        <div class="col-md-4">
                                            <label>Taxes</label>
                                             <input type="text" class="form-control" id="fname" placeholder="Taxes" name="f_name">
                                            </div>
                                        
                                        <div class="col-md-4">
                                            <label>Other</label>
                                             <input type="text" class="form-control" id="fname" placeholder="Other" name="f_name">
                                            </div>
                                        </div>
                                        </div>
                                
                                
                                
                                <div class="col-md-4">
                                    <div class="table-responsive">
                                        <table class="table table-bordered table-responsive-md table-striped text-center">
                                 <thead>
                                    <tr>
                                      <th>Year</th>
                                       <th>Month</th>
                                       <th>Date</th>
                                       <th>Day</th>      
                                       <th>status</th>
                                       <th style="white-space:nowrap">Day salary</th>
                                      
                                       
                                    </tr>
                                 </thead>
                                 <tbody id="">
                                    
                                    
                                    
                                    
                                 </tbody>
                              </table>
                                        </div>
                                    
                                    
                                    
                                    
                                    
                                    </div>
                                
                                
                                
                                        </div>
                                    </div>
                        </div>
                        </div>
                        
                     </div>  
                        
                        
                        
                  </div>
                        
                        
        <!--for salary advance--> 
        <form action="../../WHR0001_SERV" method="post">
        <input type="hidden" value="14" name="value">
                        <input type="hidden" value="<%=user_id%>" name="user">
                        <input type="hidden" value="<%=Hospital_code%>" name="hospital_code" id="hospital_code">
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
                                            <input type="text" class="form-control" id="advance_month" placeholder="" name="advance_month" readonly="">
                                            </div>
                                           <input type="hidden" class="form-control" id="month_name_ad" placeholder="" name="month_name_ad">
                                           <input type="hidden" class="form-control" id="employyee_id" placeholder="" name="employyee_id">
                                         
                                         
                                         <div class="col-md-4">
                                            <label>Advance</label>
                                             <input type="text" class="form-control" id="fname" placeholder="Amount" name="advance_amount">
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
        
        </form>
        
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

<%-- 
    Document   : WMAS0004
    Created on : 3 Feb, 2022, 3:00:14 PM
    Author     : Tarun
--%>

<%@page import="Laboratory.WLAB0001_dao"%>
<%@page import="IPD.WIPD0002_Dao"%>
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
    
<link rel="stylesheet" type="text/css" href="../Design/datatable/datatables.min.css">
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
        String code=request.getParameter("doc_code");
        
 


  
        %>

          <script src="../Js/WREP0001JS.js"></script>
    </head>
    <body onload="get_total_doc()">
     <div class="wrapper">
        <%@include  file="../Master/Header_Menu.jsp" %>
        <div id="content-page" class="content-page">
            
             <input type="hidden" value="<%=user_id%>" name="user">
                        <input type="hidden" value="<%=Hospital_code%>" name="hospital_code" id="hospital_code">            
        <div class="container-fluid">
                
                                    
                                    <div class="row">
                   
                               
                      
                  <div class="col-lg-12">
                     <div class="iq-card">
                        <div class="iq-card-header d-flex justify-content-between">
                           <div class="iq-header-title">
                              <h4 class="card-title">Filters</h4>
                           </div>
                            
                        </div>
                         
                         
                         
                         
                        <div class="iq-card-body row">
                            
                         <div class="form-group col-md-2">
                                       <label for="mobno">Date From</label>
                                       <input type="date" class="form-control" id="date_from" placeholder="OPD Date" name="" onchange="get_ipd_rec()">
                                    </div> 
                            
                            <div class="form-group col-md-2">
                                       <label for="mobno">Date To</label>
                                       <input type="date" class="form-control" id="date_to" placeholder="OPD Date" name="" onchange="get_ipd_rec()">
                                    </div> 
                            
                            <div class="form-group col-md-2">
                                       <label for="mobno">Type</label>
                                       <select class="form-control" id="type" name="" onchange="get_ipd_rec()">
                                          <option></option>
                                           <option>Admited</option>
                                           <option>Discharged</option>
                                           
                                           
                                           
                                       </select>
                                    </div> 
                            
                            <div class="form-group col-md-2">
                                       <label for="mobno">Name</label>
                                      <select class="form-control input-sm select2" name="na" id="patient_name_id" onchange="get_ipd_rec()">
                                           <option></option>
                                            <%
                                        WLAB0001_dao st_1_new=new WLAB0001_dao();
                                        ArrayList<String> data_patient=st_1_new.getdata("1","");
                                        for(String cd:data_patient){
                                            out.println(cd);
                                        }
                                        %>
                                           
                                           
                                       </select>
                                    </div> 
                                        
                                        <div class="form-group col-md-2">
                                       <label for="mobno">Room</label>
                                      <select class="form-control input-sm select2" name="na" id="patient_name_id" onchange="get_ipd_rec()">
                                           <option></option>
                                            
                                           
                                           
                                       </select>
                                    </div> 
                            
                              
                              
                              
                              
                          
                        </div>
                     </div>
                  </div>
                  <div class="col-lg-12">
                     <div class="iq-card">
                        <div class="iq-card-header d-flex justify-content-between">
                           <div class="iq-header-title">
                              <h4 class="card-title">All Doctor Details</h4>
                           </div>
                        </div>
                        <div class="iq-card-body">
                           <div class="new-user-info">
                              
                              <div class="table-responsive">
                    <table class="table table-bordered table-responsive-md table-striped text-center" id="M_tab">
                                 <thead>
                                    <tr>
                                        <th style="white-space: nowrap">S.No</th>
                                        <th style="white-space: nowrap">Photo</th>
                                        <th style="white-space: nowrap">Adhar</th>
                                        <th style="white-space: nowrap">Name</th>
                                        <th style="white-space: nowrap">Consultation Charge</th>
                                       <th style="white-space: nowrap">Department</th>
                                       <th style="white-space: nowrap">Degree</th>
                                       <th style="white-space: nowrap">Mobile Numbers</th>
                                       <th style="white-space: nowrap">Email</th>
                                       <th style="white-space: nowrap">Address</th>
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
                  </div>
                   
                  
                   
                   
                      </form>
               </div>
            
            
           <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog modal-lg" >
    
      <!-- Modal content-->
      <form action="../../WIPD0002_serv" method="post" onsubmit="return verify()"> 
          <input type="hidden" value="4" name="value">
        <input type="hidden" value="<%=user_id%>" name="user">

      <div class="modal-content">
        <div class="modal-header">
         
          <h4 class="modal-title">Discharge Patient</h4>
           <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        <div class="modal-body">
      
<div class="row">
               <input type="hidden" name="emp_id" id="emp_id">
               <input type="hidden" name="dep_id" id="dep_id">
               
               
               
               <div class="col-md-6">
                  <center> <span class="badge bg-success">Basic Charges</span></center>
                   <table class="table table-bordered table-responsive-md table-striped text-center">
                       
                       <thead>
                       <th style="white-space: nowrap">Room No</th>
                           <th style="white-space: nowrap">Date From</th>
                           <th style="white-space: nowrap">Date To</th>
                           <th style="white-space: nowrap">Charge</th>
                           
                       </thead>
                        <tbody id="patietient_room_hi"></tbody>
                       
                   </table>
               
               
               </div>
               
               <div class="col-md-6">
                     <center> <span class="badge bg-success">Tests</span></center>
                   <table class="table table-bordered table-responsive-md table-striped text-center">
                       
                       <thead>
                           <th style="white-space: nowrap">Test Name</th>
                           <th style="white-space: nowrap">Date</th>
                           <th style="white-space: nowrap">Charge</th>
                           <th style="white-space: nowrap">Paid Status</th>
                           
                       </thead>
                       
                       <tbody id="test_his"></tbody>
                   </table>
               
               
               </div>
               
               
               <div class="col-md-7">
                     <center> <span class="badge bg-success">Operation</span></center>
                   <table class="table table-bordered table-responsive-md table-striped text-center">
                       
                       <thead>
                           <th style="white-space: nowrap">Oeration Name</th>
                           <th style="white-space: nowrap">Date</th>
                           <th style="white-space: nowrap">Charge</th>
                           <th style="white-space: nowrap">Pending</th>
                           
                       </thead>
                       
                       <tbody id="OT_HIs"></tbody>
                   </table>
               
               
               </div>
               
               <div class="col-md-5">
                     <center> <span class="badge bg-success">Other Charge</span></center>
                   <table class="table table-bordered table-responsive-md table-striped text-center">
                       
                       <thead>
                           <th style="white-space: nowrap">Other Charge</th>
                           <th style="white-space: nowrap">Date</th>
                           <th style="white-space: nowrap">Charge</th>
                      
                           
                       </thead>
                   </table>
               
               
               </div>
               
               <div class="col-md-12" >
                   <div class="row">
                   <div class="col-md-4">
                       <label for="mobno">Total Amount</label>
                       <input type="text" class="form-control"  id="total_amount" placeholder=""  readonly="" name="total_amount" required="">
                       </div>
                       
                       <div class="col-md-4">
                       <label for="mobno">Paid Amount</label>
                       <input type="text" class="form-control"  id="total_paid" placeholder="" readonly="" name="paid_amount" required="">
                       </div>
                       
                       <div class="col-md-4">
                       <label for="mobno">Collect Amount</label>
                       <input type="text" class="form-control"  id="total_left" placeholder=""  name="collect_amount" required="">
                       </div>
                         
                       
                       
                       
                       </div>
                   </div>
               
               
            
               
                <div class="col-md-12">
                    
                    <input type="hidden" value="" name="patient_id_Modal" id="patient_id_Modal">
                    <center> <span class="badge bg-warning" style="color: black" ><span style="color: red">*</span>Details On Discharge</span></center>
                    <div class="row" style="border: 1px solid;background: gainsboro" >
                        
                        <div class="form-group col-md-4">
                                       <label for="mobno">Diagnosis</label>
                                       <input type="text" class="form-control" style="background:white" id="Diagnosis" placeholder="" name="Diagnosis" required="">
                                    </div>
                        
                        
                        <div class="form-group col-md-4">
                                       <label for="mobno">Condition On Discharge</label>
                                       <input type="text" class="form-control" style="background:white" id="Diagnosis" placeholder="" name="condition" required="">
                                    </div>
                        
                        <div class="form-group col-md-4">
                                       <label for="mobno">Vitals On Discharge</label>
                                       <input type="text" class="form-control" style="background:white" id="Diagnosis" placeholder="" name="Vitals" required="">
                                    </div>
                        
                        <div class="form-group col-md-12">
                                       <label for="mobno">History of Illness</label>
                                       <input type="text" class="form-control" style="background:white" id="Diagnosis" placeholder="" name="History" required="">
                                    </div>
                        </div>
                    </div>
               
               
               
               
               
               
               
               </div>
                            

       
            
          
           <center>  <button class="btn btn-sm btn-success">Discharge Now</button></center>
        </div>
        <div class="modal-footer">
       
        </div>
      </div>
        
        
        
        
       
        
          </form>
    
      
    </div>
         
  </div>
        
        
        <div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
            <form action="../../WIPD0002_serv" method="post" onsubmit="return verify()"> 
            <input type="hidden" value="6" name="value">
            
              <input type="hidden" value="<%=user_id%>" name="user">
             
             
                              <div class="modal-dialog modal-dialog-centered" role="document">
                                 <div class="modal-content">
                                    <div class="modal-header">
                                       <h5 class="modal-title" id="exampleModalCenterTitle">Recipeit Payment</h5>
                                       <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                       <span aria-hidden="true">&times;</span>
                                       </button>
                                    </div>
                                      <input type="text" name="patient_id_mod" id="patient_id_Modal_payment">
                                      <input type="text" name="patient_id_mod" id="total_amount">
                                    <div class="modal-body">
                                 
                                     <div class="row">
                                         <div class="col-md-4">
                                            <label>Total  Amount</label>
                                             <input type="text" class="form-control" id="fname" placeholder="" name="Toatl_amount_till_now" readonly>
                                            </div>
                                         
                                         
                                         
                                         <div class="col-md-4">
                                            <label>Paid Amount</label>
                                             <input type="text" class="form-control" id="fname" placeholder="" name="Amount_paid">
                                            </div>
                                         
                                         <div class="col-md-4">
                                            <label>Collect Amount</label>
                                             <input type="text" class="form-control" id="fname" placeholder="" name="Amount_collecting">
                                            </div>
                                         
                                         
                                         <div class="col-md-12">
                                            <label>Remark</label>
                                             <input type="text" class="form-control" id="fname" placeholder="" name="payment_Remark">
                                            </div>
                                         
                                         
                                         <div class="col-md-12">
                                            <label></label>
                                             <center><button class="btn btn-sm btn-success mt-4">Collect</button></center>
                                            </div>
                                         
                                         </div>
                                      
                                    </div>
                                    <div class="modal-footer">
                                       <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                     
                                    </div>
                                 </div>
                              </div>
             </form>
                           </div>   
            
            </div>
        
        
        </div>
        
        
              <%@include file='../Design/All/All_js.jsp'%>
              
                  <script src="../Design/All/js/tables/datatable/datatables.min.js"></script>
      <script src="../Design/All/js/scripts/tables/datatables/datatable-basic.min.js"></script>
     
     
      <script src="../Design/All/js/tables/datatable/datatables.min.js"></script>
    <script src="../Design/All/js/tables/datatable/dataTables.buttons.min.js"></script>
    <script src="../Design/All/js/tables/buttons.flash.min.js"></script>
    <script src="../Design/All/js/tables/jszip.min.js"></script>
    <script src="../Design/All/js/tables/pdfmake.min.js"></script>
    <script src="../Design/All/js/tables/vfs_fonts.js"></script>
    <script src="../Design/All/js/tables/buttons.html5.min.js"></script>
    <script src="../Design/All/js/tables/buttons.print.min.js"></script>
    
    <script src="../Design/All/js/select2.full.min.js"></script>
         <script src="../Design/All/js/select2.full.min.js"></script>
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

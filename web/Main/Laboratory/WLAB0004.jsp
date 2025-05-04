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
    
    <%
      String code=request.getParameter("patient_id");
    %>
    <body>
     <div class="wrapper">
        <%@include  file="../Master/Header_Menu.jsp" %>
        <div id="content-page" class="content-page">
            
                        
        <div class="container-fluid">
            <form action="../../WLAB0001_Serv" method="post" id="form1">  
                 
                 <input type="hidden" name="value" value="13">
                                      <input type="hidden" name="user" id="user" value="<%=user_id%>">
                                      <input type="hidden" name="company_code" value="<%=Hospital_code%>">
           
                                 
                  <div class="col-lg-12">
                     <div class="iq-card">
                        <div class="iq-card-header d-flex justify-content-between">
                           <div class="iq-header-title">
                              <h4 class="card-title">Patient Wise  Payment Sheet</h4>
                           </div>
                        </div>
                        <div class="iq-card-body">
                           <div class="new-user-info">
                          <div class="row">
                                            
                              
                              
                              
                              
                              
                              
                              <div class="col-md-2">
                              <div class="form-group" id="ipd_patient">
                                 <label>Patient Name</label>
                                 <select class="form-control js-example-basic-single"  id="patient_id" name="patient_id" required  onchange="Get_patient_wise_payment_details();check_Ayushmaan();">
                               
                                    <option>Select</option>
                                  
                                     <%
                                        WLAB0001_dao st_1_new=new WLAB0001_dao();
                                        ArrayList<String> data_patient=st_1_new.getdata("2","");
                                        for(String cd:data_patient){
                                         out.println(cd);
                                         }
                                        %>
                                         
                                           
                                           
                                      
                                        
                                        
                                        
                                 </select>
                                 </div>
                                    
                              </div>
                                 
                          
                                 
                                 
                                 <div class="col-md-3 mt-4">
                                     <button type="button" class="btn btn-primary"  data-toggle="modal" data-target="#exampleModalCenter" 
                                             onclick="Get_total_voc_pay()">Total Payment Done</button>
                                     </div>
                                        
                                        <div class="col-md-3 mt-4">
                                     <button type="button" class="btn btn-primary">Print Final Bill</button>
                                     </div>
                                 
                                        
                                        
                                        <!--for printing the image--->
                                        <div class="col-md-3 mt-0" id='AYUSH_MAAN' style="display:none;">
                                           <img src='../Design/Ayush_man.png' width='200px' height='100px'    alt='alt'/>
                                            </div>
                                        
                                        
                                 </div>
                                        
                                        
                                   
                        <div class="table-responsive" id="Bill_print_area">
                               <table class="table table-bordered table-responsive-md table-striped text-center">
                                 <thead>
                                    <tr>
                                      
                                      
                                       <th><input type="checkbox"></th>
                                      
                                       <th>Description</th>
                                      
                                       
                                       <th>Rate</th>
                                       <th>Qty</th>
                                       <th>Total Amount </th>
                                    
                                  
                                    </tr>
                                 </thead>
                                 <tbody id="tbody">
                                    
                                    
                                    
                                    
                                 </tbody>
                              </table>
                       
                                      </div>
                            
                           </div>
                                 <div class="row"><!-- comment -->  
                                     <div class="col-md-2">
                                            <label>Gross Amount </label>
                                            <input type="number" class="form-control" id="Gross_amount" placeholder="" name="Gross_amount" readonly="">
                                            </div>
                                     
                                     
                                      <div class="col-md-2">
                                            <label>Total Paid</label>
                                            <input type="number" class="form-control" id="Vocher_payment" placeholder="" name="Vocher_payment" readonly="">
                                            </div>
                                     
                                       <div class="col-md-2">
                                            <label>Total Amt To Pay</label>
                                             <input type="number" class="form-control" id="total_voc_amount" placeholder="" name="total_voc_amount"                                              <input type="number" class="form-control" id="total_voc_amount" placeholder="" name="total_voc_amount" readonly="">
                                            </div>
                                     
                                     <div class="col-md-2">
                                            <label>Discount</label>
                                            <input type="number" class="form-control" id="Discount" value="" name="Discount" onkeyup="final_bill_settle();Bill_discount();">
                                            </div>
                                     
                                     
                                     <div class="col-md-2">
                                            <label>Net Amount</label>
                                             <input type="number" class="form-control" id="Net_amount" placeholder="" name="Net_amount" readonly="">
                                            </div>
                                     
                                     <div class="col-md-2">
                                            <label></label>
                                            <button type="" class="btn btn-success" id="settle_bil">Settle Bill</button>
                                            </div>
                                     
                                     
                                     
                                     
                                     </div>
                               
                                 
                        
                        </div>
                                 
                                   
                                 
                        
                     </div>
                        
                  </div>
  </form>
               </div>
                                   
             <!--for total voucher payents-->
       

                        <div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                       
                                   <div class="modal-lg modal-dialog" >
                            
                            <input type="hidden" value="6" name="value">

                            <input type="hidden" value="<%=user_id%>" name="user">


                            <div class="modal-dialog modal-dialog-centered" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="exampleModalCenterTitle">Total Recpit Payment</h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                         
                                    <div class="modal-body">

                                        <div class="col-md-12">
                                                <center> <span class="badge bg-success">Payment Details</span></center>
                                                <table class="table table-bordered table-responsive-md table-striped text-center" id="M_table">                                            

                                                    <thead>
                                                    <th style="white-space: nowrap">Date</th>
                                                    <th style="white-space: nowrap">Amount</th>
                                                    <th style="white-space: nowrap">Remark</th>
                                                    </thead>
                                                    <tbody id="voc_tbody">
                                                        
                                                    </tbody>
                                                </table>


                                            </div>

                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>

                                    </div>
                                </div>
                            </div>
                        </form>
                 
                        
                        
                     </div> 
                            
        </div><!-- comment -->
             </div> 
                        
                        
                        
                  </div>
                         
                        
        <!--for salary advance--> 
        
        
        
        <!--ends here--> 
   
            </div>
        
        
        </div>
         
        
              <%@include file='../Design/All/All_js.jsp'%>
                          <script src="../Design/All/js/tables/datatable/datatables.min.js"></script>
            <script src="../Design/All/js/scripts/tables/datatables/datatable-basic.min.js"></script>


            <script src="../Design/All/js/tables/datatable/datatables.min.js"></script>
            <script src="../Design/All/js/tables/datatable/dataTables.buttons.min.js"></script>
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
              
              
             <script>
    // In your Javascript (external .js resource or <script> tag)
$(document).ready(function() {
    $('.js-example-basic-single').select2();
});
    </script>
    </body>
</html>

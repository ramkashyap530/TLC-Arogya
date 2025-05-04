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
<link rel="stylesheet" type="text/css" href="../Design/datatable/datatables.min.css">
          <script src="../Js/WHR0001.js"></script>
    </head>
    <body onload="get_att_data()">
     <div class="wrapper">
        <%@include  file="../Master/Header_Menu.jsp" %>
        <div id="content-page" class="content-page">
            
                        
        <div class="container-fluid">
             <form action="../../WHR0001_SERV" method="post" id="form1">  
                 
                 <input type="hidden" name="value" value="4">
                                                     <input type="hidden" name="user" value="<%=Real_Name%>">
                                                     <input type="hidden" name="company_code" value="<%=Hospital_code%>">
               <div class="row">
                  <div class="col-lg-3">
                     <div class="iq-card">
                        <div class="iq-card-header d-flex justify-content-between">
                           <div class="iq-header-title">
                              <h4 class="card-title">Attendance Master</h4>
                           </div>
                        </div>
                        <div class="iq-card-body">
                         
                              
                              <div class="form-group">
                                 <label>Department:</label>
                                 <select class="form-control" id="selectuserrole" name="dep">
                                    <option>Select</option>
                                     <%
                                        WMAS0001_Dao st_1=new WMAS0001_Dao();
                                        ArrayList<String> data=st_1.getdata("5","");
                                        for(String cd:data){
                                            out.println(cd);
                                        }
                                        %>
                                 </select>
                              </div>
                            
                            
                          
                            
                            <div class="form-group">
                                 <label>Date</label>
                                 <input type="date" class="form-control" name="date" id="txtDate"  >
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
                              
                              
                                   <input type="hidden" value="2" name="value">
                        <input type="hidden" value="<%=user_id%>" name="user">
                        <input type="hidden" value="<%=Hospital_code%>" name="hospital_code" id="hospital_code">
                        <input type="hidden" value="" name="staff_id">
                        <div class="table-responsive">
                               <table class="table table-bordered table-responsive-md table-striped text-center" id="M_tab">
                                 <thead>
                                    <tr>
                                        <th><input  type="checkbox"></th>
                                       <th>Department</th>
                                       <th>Name</th>
                                       <th>Status</th>
                                       
                                       <th>Position</th>
                                       <th>Shift</th>
                                       <th>Action</th>
                                       <th>Remark</th>
                                       <th>Track</th>
                                       
                                    </tr>
                                 </thead>
                                 <tbody id="tbody">
                                    
                                    
                                    
                                    
                                 </tbody>
                              </table>
                            </div>
                                 
                        <center>  <button type="submit" class="btn btn-primary" onclick="valthisform()">Mark</button></center>
                                 
                            
                           </div>
                        
                        </div>
                        
                     </div>
                        
                  </div>
                   
                  
                   
                   
               
               </div>
             </form>   
            </div>
        
                        
    <!--attandce recoed--> 
                          
                               <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog modal-lg" >
    
      <!-- Modal content-->
      <form action="../../WHR0001_SERV" method="post" onsubmit="return verify()"> 
          <input type="hidden" value="13" name="value">
          <input type="hidden" value="<%=user_id%>" name='created_by'>

      <div class="modal-content">
        <div class="modal-header">
         
          <h4 class="modal-title">Attendence Record Of </h4>
           <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        <div class="modal-body">
      
<div class="row">
               <input type="hidden" name="emp_id" id="emp_id">
               <div class="col-md-3">
                   <lable>Date</lable>
                   <input type="date" class="form-control input-sm " name="date" id="txtDate" required="" onkeyup="verify()">
                   </div>
               
               
               
               <div class="col-md-2">
                   <lable>Status</lable>
                   <select class="form-control input-sm" name="status">
                       <option value="1">P</option>
                       <option value="0">A</option>
                       <option value="0.5">Half</option>
                       <option value="L">Leave</option>
                   </select>
                   </div>
               
               <div class="col-md-3 mt-4">
                   <button type="submit" class="btn btn-sm btn-success"  onclick="verify()" onsubmit="return verify();" >Back Date Mark</button>
                   </div>
               
               
               <div class="col-md-3 mt-4" style="left: 10%;" >
                   <button type="button" class="btn  btn-sm btn-danger"  onclick="del_today()">Modify Today Att</button>
                   </div>
               </div>
                            

       
           
            
            
            
            
          <div class="col-md-12 mt-1">
              <div class="table-responsive " >
              <table class="table table-striped table-bordered" id="M_tab_M">
                  <thead>
                      <tr>
                          <th style="white-space: nowrap">SR No</th>
                          <th>Month</th>
                          <th>Day</th>
                          <th>Date</th>
                          <th>Status</th>
                          <th>Marked By</th>

                      </tr>
                      <tbody id="m_tbody">
                          </tbody>
                  </thead>
                  </table>
              </div>
              </div>
        </div>
        <div class="modal-footer">
        
        </div>
      </div>
          </form>
    
      
    </div>
         
  </div>                    
        
        
      
    
    
    <!--ends here--> 
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
        
        </div>
         
        
              <%@include file='../Design/All/All_js.jsp'%>
            <script src="../../Design/app-assets/js/scripts/forms/select/form-select2.min.js"></script>
    
    <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.js"></script>
    
    
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

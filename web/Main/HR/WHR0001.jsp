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
    <body onload="get_staff();">
     <div class="wrapper">
        <%@include  file="../Master/Header_Menu.jsp" %>
        <div id="content-page" class="content-page">
            
                        
        <div class="container-fluid">
               <div class="row">
                  <div class="col-lg-3">
                     <div class="iq-card">
                        <div class="iq-card-header d-flex justify-content-between">
                           <div class="iq-header-title">
                              <h4 class="card-title">Add New Staff</h4>
                           </div>
                        </div>
                        <div class="iq-card-body">
                         
                              <div class="form-group">
                                 <div class="add-img-user profile-img-edit">
                                    <img class="profile-pic img-fluid" src="../Design/All/images/user/11.png" alt="profile-pic">
                                    <div class="p-image">
                                       <div class="upload-btn-wrapper">
  <button class="btn">Upload a file</button>
  <input type="file" name="myfile">
</div>
                                    </div>
                                 </div>
                                 <div class="img-extension mt-3">
                                    <div class="d-inline-block align-items-center">
                                       <span>Only</span>
                                       <a href="javascript:void();">.jpg</a>
                                       <a href="javascript:void();">.png</a>
                                       <a href="javascript:void();">.jpeg</a>
                                       <span>allowed</span>
                                    </div>
                                 </div>
                              </div>
                              <div class="form-group">
                                 <label>Department:</label>
                                 <select class="form-control" id="selectuserrole" name="dep">
                                    <option>Select</option>
                                    <option>Nursing </option>
                                    <option>Cleaning</option>
                                    <option>Gate</option>
                                    <option>Helper</option>
                                    <option>Helper</option>
                                 </select>
                              </div>
                              
                              
                              
                              
                          
                        </div>
                     </div>
                  </div>
                  <div class="col-lg-9">
                     <div class="iq-card">
                        <div class="iq-card-header d-flex justify-content-between">
                           <div class="iq-header-title">
                              <h4 class="card-title">Multi Shift Management</h4>
                           </div>
                            
                            <button class="btn btn-sm btn-success" data-toggle="modal" data-target="#myModal_2">Multi</button>
                        </div>
                        <div class="iq-card-body">
                           <div class="new-user-info">
                              
                               <form action="../../WHR0001_SERV" method="post" >
                                   <input type="hidden" value="2" name="value">
                        <input type="hidden" value="<%=user_id%>" name="user">
                        <input type="hidden" value="<%=Hospital_code%>" name="hospital_code" id="hospital_code">
                        <input type="hidden" value="" name="staff_id">
                               <table class="table table-bordered table-responsive-md table-striped text-center">
                                 <thead>
                                    <tr>
                                        <th>S.No</th>
                                       <th>Name</th>
                                       <th>Department</th>
                                       <th>Position</th>
                                       <th>Shift</th>
                                       <th>Current Shift</th>
                                    
                                       <th>Asign</th>
                                       
                                    </tr>
                                 </thead>
                                 <tbody id="tbody">

                                    
                                 </tbody>
                              </table>
                                 
                               <center>  <button type="submit" class="btn btn-primary">ASSIGN</button></center>
                                 </form>
                            
                           </div>
                        </div>
                     </div>
                  </div>
                   
                        
                   
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                  
                <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog modal-lg" >
    
      <!-- Modal content-->
      <form action="../../WHR0001_SERV" method="post" onsubmit="return verify()"> 
          <input type="hidden" value="12" name="value">
          <input type="hidden" value="" name='created_by'>

      <div class="modal-content">
        <div class="modal-header">
         
          <h4 class="modal-title">Assign Shift</h4>
           <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        <div class="modal-body">
      
<div class="row">
               <input type="hidden" name="emp_id" id="emp_id">
               <input type="hidden" name="dep_id" id="dep_id">
               <div class="col-md-3">
                   <lable>Date From</lable>
                   <input type="date" class="form-control input-sm " name="date" id="txtDate_from" required="" required>
                   </div>
               
               <div class="col-md-3">
                   <lable>Date To</lable>
                   <input type="date" class="form-control input-sm " name="date" id="txtDate_to" required="" onkeyup="verify()" required>
                   </div>
               
               
               
               
               
               <div class="col-md-3 mt-4">
                   <button type="button" class="btn btn-sm btn-success"  onclick="live_date()"  >Get Data</button>
                   </div>
               
               
               
               </div>
                            

       
            
          <div class="col-md-12 mt-1">
              <div class="table-responsive " >
              <table class="table table-striped table-bordered" id="M_tab">
                  <thead>
                      <tr>
                          <th>S No</th>
                          <th>Date From</th>
                          <th>Day</th>
                          <th>Shift</th>
<!--                          <th>Room No</th>-->
                          

                      </tr>
                      <tbody id="m_tbody">
                          </tbody>
                  </thead>
                  </table>
              </div>
              </div>
             <button class="btn btn-sm btn-success">Assign Shift</button>
        </div>
        <div class="modal-footer">
       
        </div>
      </div>
          </form>
    
      
    </div>
         
  </div>       
                   
                   
               </div>
            
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
    </body>
</html>

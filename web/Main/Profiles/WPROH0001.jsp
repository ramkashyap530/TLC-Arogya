<%-- 
    Document   : WPROF0001
    Created on : 28 Mar, 2022, 9:48:10 PM
    Author     : Tarun
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>


<%
String hosp=request.getParameter("id");

WMAS0001_Dao data=new WMAS0001_Dao();
HashMap<String,String> user_date=data.get_hospital_update(hosp);
String hospital_name_updatw=user_date.get("hospital_name");
String reg=user_date.get("reg");
String gst=user_date.get("gst");
String HOs_address=user_date.get("address");
String number_1=user_date.get("number_1");
String number_2=user_date.get("number_2");
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hospital Profile</title>
        <%@include file="../../Main/Design/All/All_css.jsp"%>
    </head>
    <body>
    <div class="wrapper">
        <%@include  file="../Master/Header_Menu.jsp" %>
        <div id="content-page" class="content-page">
        <div class="container-fluid">
               <div class="row">
                  <div class="col-lg-4">
                     <div class="iq-card">
                        <div class="iq-card-body pl-0 pr-0 pt-0">
                           <div class="doctor-details-block">
                              <div class="doc-profile-bg bg-primary" style="height:150px;">
                              </div>
                              <div class="doctor-profile text-center">
                                  <img src="../../Staff/images/system_user_<%=hosp%>.jpg" class="img-thumbnail" onerror="this.onerror=null; this.src='../Design/avatr.jpg' alt=""  style='height:50px;width:50px' class="avatar-130 img-fluid">
                              </div>
                              <div class="text-center mt-3 pl-3 pr-3">
                                 <h4><b><%=hospital_name_updatw%></b></h4>
                                 <p>Hospital Name</p>
                                 <p class="mb-0">You Can update you Hospital Details from here only.</p>
                              </div>
                              <hr>
                              <ul class="doctoe-sedual d-flex align-items-center justify-content-between p-0 m-0">
                                 <li class="text-center">
                                    <h3 class="counter">4500</h3>
                                    <span>Doctors</span>
                                  </li>
                                  <li class="text-center">
                                    <h3 class="counter">100</h3>
                                    <span>Staff</span>
                                  </li>
                                  <li class="text-center">
                                    <h3 class="counter">10000</h3>
                                    <span>Patients</span>
                                  </li>
                              </ul>
                           </div>
                        </div>
                     </div>
                                                   </div>
 <div class="col-lg-4">
                     <div class="iq-card">
                        <div class="iq-card-header d-flex justify-content-between">
                           <div class="iq-header-title">
                              <h4 class="card-title">Hospital Information</h4>
                           </div>
                        </div>
                        <div class="iq-card-body">
                           <div class="about-info m-0 p-0">
                              <div class="row">
                                  <div class="col-6">Hospital Name</div><div class='col-md-6'><%=hospital_name_updatw%></div>
                                 <div class="col-6">Hospital Regist:</div><div class='col-md-6'><%=reg%></div>
                                 <div class="col-6">Hospital Gst</div><div class='col-md-6'><%=gst%></div>
                                 <div class="col-6">Address</div><div class='col-md-6'><%=HOs_address%></div>
                                 <div class="col-6">Number 1</div><div class='col-md-6'><%=number_1%></div>
                                 <div class="col-6">Number 2</div><div class='col-md-6'><%=number_2%></div>
                               
                              </div>
                           </div>
                        </div>
                     </div>
 </div>
                                  <div class="col-lg-4">
                     <div class="iq-card">
                        <div class="iq-card-header d-flex justify-content-between">
                           <div class="iq-header-title">
                              <h4 class="card-title">Update</h4>
                           </div>
                            <form action="../../WMAS0001_SERV" method="post" enctype="multipart/form-data">
                        <input type="hidden" value="14" name="value">
                         <input type="hidden" value='<%=hosp%>' name='hospital_code'>
                         <input type='file' hidden="" name="prd_photo_user">
                        <button class="btn btn-success" type="submit">Change</button>
                            </div>
                             
                         
                            <div class="iq-card-body">
                                <div class='col-md-12'>
                                    <div class='form-group'>
                                        <label>Hospital Name</label>
                                        <input type='text' class='form-control' value='<%=hospital_name_updatw%>' name="Hospital_name" >
                                    </div>
                                    </div>
                                    <div class='col-md-12'>
                                    <div class='form-group'>
                                        <label>Registration</label>
                                        <input type='text' class='form-control' value='<%=reg%>' name="Hospital_reg" >
                                    </div>
                                    </div><!-- comment -->
                                    <div class='col-md-12'>
                                    <div class='form-group'>
                                        <label>Gst</label>
                                        <input type='text' class='form-control' value='<%=gst%>' name="Hospital_gst" >
                                    </div>
                                    </div>
                                    
                                    <div class='col-md-12'>
                                    <div class='form-group'>
                                        <label>Address</label>
                                        <input type='text' class='form-control' value='<%=HOs_address%>' name="Hospital_address" >
                                    </div>
                                    </div>
                                    
                                    <div class='col-md-12'>
                                    <div class='form-group'>
                                        <label>Number 1</label>
                                        <input type='text' class='form-control' value='<%=number_1%>' name="number_1" >
                                    </div>
                                    </div>
                                    
                                    <div class='col-md-12'>
                                    <div class='form-group'>
                                        <label>Number 2</label>
                                        <input type='text' class='form-control' value='<%=number_2%>' name="number_2" >
                                    </div>
                                    </div>
                                
                            </div>
                             </form>
                        </div>
                        
                     </div>
                  
               </div>
                                 </div>
            </div>
        
        
        
           <%@include file="../../Main/Design/All/All_js.jsp"%>
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

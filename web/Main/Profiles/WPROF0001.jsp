<%-- 
    Document   : WPROF0001
    Created on : 28 Mar, 2022, 9:48:10 PM
    Author     : Tarun
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>


<%
String USer_id=request.getParameter("id");

WMAS0001_Dao data=new WMAS0001_Dao();
HashMap<String,String> user_date=data.get_all_users(USer_id);
String user_id_user=user_date.get("user_id");
String user_id_user_name=user_date.get("username");
String user_id_password=user_date.get("password");
String user_id_Real_name=user_date.get("real_name");
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Profile</title>
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
                                  <img src="../../Staff/images/system_user_<%=user_id_user%>.jpg" class="img-thumbnail" onerror="this.onerror=null; this.src='../Design/avatr.jpg' alt=""  style='height:50px;width:50px' class="avatar-130 img-fluid">
                              </div>
                              <div class="text-center mt-3 pl-3 pr-3">
                                 <h4><b><%=user_id_Real_name%></b></h4>
                                 <p>Doctor</p>
                                 <p class="mb-0">You Can update you profile from here only.</p>
                              </div>
                              <hr>
                              <ul class="doctoe-sedual d-flex align-items-center justify-content-between p-0 m-0">
                                 <li class="text-center">
                                    <h3 class="counter">4500</h3>
                                    <span>Operations</span>
                                  </li>
                                  <li class="text-center">
                                    <h3 class="counter">100</h3>
                                    <span>Hospital</span>
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
                              <h4 class="card-title">Personal Information</h4>
                           </div>
                        </div>
                        <div class="iq-card-body">
                           <div class="about-info m-0 p-0">
                              <div class="row">
                                  <div class="col-6">Real Name:</div><div class='col-md-6'><%=user_id_Real_name%></div>
                                 <div class="col-6">User Name:</div><div class='col-md-6'><%=user_id_user_name%></div>
                                 <div class="col-6">Pass Word:</div><div class='col-md-6'><%=user_id_password%></div>
                               
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
                        <input type="hidden" value="15" name="value">
                         <input type="hidden" value='<%=user_id_user%>' name='user_id'>
                         <input type='file' hidden="" name="prd_photo_user">
                        <button class="btn btn-success" type="submit">Change</button>
                            </div>
                             
                         
                            <div class="iq-card-body">
                                <div class='col-md-12'>
                                    <div class='form-group'>
                                        <label>User Name</label>
                                        <input type='text' class='form-control' value='<%=user_id_user_name%>' name="User_name" >
                                    </div>
                                    </div>
                                    <div class='col-md-12'>
                                    <div class='form-group'>
                                        <label>Pass Word</label>
                                        <input type='text' class='form-control' value='<%=user_id_password%>' name="Password" >
                                    </div>
                                    </div><!-- comment -->
                                    <div class='col-md-12'>
                                    <div class='form-group'>
                                        <label>Real Name</label>
                                        <input type='text' class='form-control' value='<%=user_id_Real_name%>' name="Real_name" >
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

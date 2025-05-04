<%-- 
    Document   : WMAS0001.jsp
    Created on : 2 Feb, 2022, 3:12:06 PM
    Author     : Tarun
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="Master.WMAS0001_Dao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    
              
    <%
        String User_id="";
     User_id=request.getParameter("user_id");
    WMAS0001_Dao User_data=new WMAS0001_Dao();
     HashMap<String,String> map_user=User_data.get_all_users(User_id);
   
    
  
    %>     
    
    
    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Assign Module</title>
         <%@include file="../Design/All/All_css.jsp" %>
          <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css">
          <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>

          <script src="../Js/WMAS0001JS.js"></script>
    </head>
    <body onload="get_all_system_user()">
        <div class="wrapper">
        <%@include  file="../Master/Header_Menu.jsp" %>
        
        <div id="content-page" class="content-page">
            <div class="container-fluid">
               <div class="row">
                  <div class="col-sm-12 col-lg-6">
                     <div class="iq-card">
                        <div class="iq-card-header d-flex justify-content-between">
                           <div class="iq-header-title">
                              <h4 class="card-title">Add Software User</h4>
                           </div>
                        </div>
                        <div class="iq-card-body">
                           <p>Add All Rooms </p>
                           <form action="../../WMAS0001_SERV" method="post" enctype="multipart/form-data">
                               
                               
                        <input type="hidden" value="15" name="value">
                        <input type="hidden" value="<%=user_id%>" name="user">
                        <input type="hidden" value="<%=Hospital_code%>" name="hospital_code">
                         <input type="hidden" value="<%=map_user.get("user_id")%>" name="user_id">
                        
                        
                              <div class="form-row">
                                 
                                  <div class="col-md-4 mb-3">
                                                                       
                                    <label for="validationDefault01">Role</label>
                                    <select class="form-control" name="Role">
                                            <%
                                       WMAS0001_Dao  st_1=new WMAS0001_Dao();
                                        ArrayList<String> data=st_1.getdata("8","");
                                        for(String cd:data){
                                            out.println(cd);
                                        }
                                        %>
                                        </select>
                             
                                 </div> 
                                  
                                  
                                  
                                <div class="col-md-4 mb-3">
                                                                       
                                    <label for="validationDefault01">User Name</label>
                                    <input type="text" class="form-control" id="validationDefault01"  name="User_name" required>
                             
                                 </div> 
                                  <div class="col-md-4 mb-3">
                                                                       
                                    <label for="validationDefault01">Password</label>
                                    <input type="Password" class="form-control" id="validationDefault01"  name="Password" required>
                             
                                 </div> 
                                  
                                  
                                  <div class="col-md-4 mb-3">
                                                                       
                                    <label for="validationDefault01">Real Name</label>
                                    <input type="text" class="form-control" id="validationDefault01"  name="Real_name" required>
                             
                                 </div> 
                                        
                                       
             
               
               
                                                          <label for="validationDefault01">Photo</label>
                                                   <div class="form-group mt-4">
                                                       
                                                       <input type="file" id="file_adhar"  name="prd_photo_user" class="form-control mt-1">
                                                       
                                                       </div>
                                               
                                               
                                               <div class="ml-2 col-sm-2">
                                                   <img src="../Design/avatr.jpg"   id="preview" class="img-thumbnail">
</div>
                                 
                                  
                              

                                    
                                 
                              </div>
                              
                              <div class="form-group">
                                <center> <button class="btn btn-primary" type="submit">Create User</button></center>
                              </div>
                           </form>
                        </div>
                     </div>
                     
            
        </div>
                                    
                                    
                                    
                                    <!--for submodule-->
                                   <div class="col-sm-12 col-lg-6">
                     <div class="iq-card">
                        <div class="iq-card-header d-flex justify-content-between">
                           <div class="iq-header-title">
                              <h4 class="card-title">Actived User</h4>
                           </div>
                        </div>
                        <div class="iq-card-body">
                        <div class="table-responsive">
                    <table class="table table-bordered table-responsive-md table-striped text-center" id="M_tab">
                                 <thead>
                                    <tr>
                                        <th style="white-space: nowrap">S.No</th>
                                          <th style="white-space: nowrap">Action</th>
                                         <th style="white-space: nowrap">Photo</th>
                                        <th style="white-space: nowrap">User Name</th>
                                        <th style="white-space: nowrap">PassWord</th>
                                       <th style="white-space: nowrap">Real Name</th>
                                       <th style="white-space: nowrap">Status</th>
                                      
                                       
                                       
                                    </tr>
                                 </thead>
                                 <tbody id="tbody">
                                    
                                    
                                    
                                    
                                 </tbody>
                              </table>
                        </div>
                          
                        </div>
                     </div>
                     
            
        </div> 
                                    <!--ends here--> 
        
        
        
        </div>
            </div><!-- <> -->
            </div>
        </div><!-- comment -->

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

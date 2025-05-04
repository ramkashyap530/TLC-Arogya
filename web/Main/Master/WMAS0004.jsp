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

          <script src="../Js/WMAS0001JS.js"></script>
          
    <%
        String Doc_code="";
     Doc_code=request.getParameter("doc_code");
    WMAS0001_Dao doc_data=new WMAS0001_Dao();
     HashMap<String,String> map_doc=doc_data.get_doc(Doc_code);
   
    
  
    %>      
          
          
          
    </head>
     <div class="wrapper">
        <%@include  file="../Master/Header_Menu.jsp" %>
        <div id="content-page" class="content-page">
            <form action="../../WMAS0001_SERV" method="post" enctype="multipart/form-data">
                        <input type="hidden" value="9" name="value">
                        <input type="hidden" value="<%=user_id%>" name="user">
                        <input type="hidden" value="<%=Hospital_code%>" name="hospital_code">
                        <input type="hidden"  name="doc_code" value="<%=Doc_code%>">
        <div class="container-fluid">
               <div class="row">
                  <div class="col-lg-3">
                     <div class="iq-card">
                        <div class="iq-card-header d-flex justify-content-between">
                           <div class="iq-header-title">
                              <h4 class="card-title">Add New Doctor</h4>
                           </div>
                        </div>
                        <div class="iq-card-body">
                         
                              <div class="form-group">
                                 <div class="add-img-user profile-img-edit">
                                    <img class="profile-pic img-fluid" src="../Design/All/images/user/11.png" alt="profile-pic" id="preview">
                                    <div class="p-image">
                                       <div class="upload-btn-wrapper">
  <label for="validationDefault01">Photo</label>
                                                   <div class="form-group mt-4">
                                                       
                                                       <input type="file" id="file_adhar"  name="doc_photo" class="form-control mt-1">
                                                       
                                                       </div>
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
                                  
                                 <label>Specialist In:</label>
                                 
                                 <input type="text"  class="form-control" id="selectuserrole" name="Specialist"  value="<%=map_doc.get("specialist")%>">
                                 
                              </div>
                              
                              
                              
                              
                          
                        </div>
                     </div>
                  </div>
                  <div class="col-lg-9">
                     <div class="iq-card">
                        <div class="iq-card-header d-flex justify-content-between">
                           <div class="iq-header-title">
                              <h4 class="card-title">Doctor Information</h4>
                           </div>
                        </div>
                        <div class="iq-card-body">
                           <div class="new-user-info">
                              
                                 <div class="row">
                                    <div class="form-group col-md-6">
                                       <label for="fname">First Name:</label>
                                       <input type="text" class="form-control" id="fname" placeholder="First Name"  value="<%=map_doc.get("doc_name_first")%>"   name="f_name" required="">
                                    </div>
                                    <div class="form-group col-md-6">
                                       <label for="lname">Last Name:</label>
                                       <input type="text" class="form-control" id="lname" placeholder="Last Name"  value="<%=map_doc.get("doc_name_last")%>"  name="l_name"   required="">
                                    </div>
                                    <div class="form-group col-md-6">
                                       <label for="add1">Consultation Charge:</label>
                                       <input type="text" class="form-control" id="add1" placeholder="Charge" name="charge" value="<%=map_doc.get("cont_charge")%>"  required="">
                                    </div>
                                   
                                    <div class="form-group col-md-6">
                                       <label for="cname">Depertament</label>
                                       <input type="text" class="form-control" id="cname" placeholder="Department Name"  value="<%=map_doc.get("department_name")%>" name="depertment" required="">
                                    </div>
                                    <div class="form-group col-sm-6">
                                       <label>Degree</label>
                                        <input type="text" class="form-control" id="cname" placeholder="Department Name"  value="<%=map_doc.get("Degree")%>" name="degree" required="">
                                       
                                       
                                    </div>
                                    <div class="form-group col-md-6">
                                       <label for="mobno">Mobile Number:</label>
                                       <input type="text" class="form-control" id="mobno" placeholder="Mobile Number" name="mobile_number" value="<%=map_doc.get("mobile_num")%>" required="">
                                    </div>
                                    <div class="form-group col-md-6">
                                       <label for="altconno">Alternate Contact:</label>
                                       <input type="text" class="form-control" id="altconno" placeholder="Alternate Contact" name="alt_number" value="<%=map_doc.get("alt_num")%>" required="">
                                    </div>
                                    <div class="form-group col-md-6">
                                       <label for="email">Email:</label>
                                       <input type="email" class="form-control" id="email" placeholder="Email" name="emial" value="<%=map_doc.get("email")%>" required="">
                                    </div>
                                    
                                    <div class="form-group col-md-6">
                                       <label for="city">Town/City:</label>
                                       <input type="text" class="form-control" id="city" placeholder="Town/City" name="city" value="<%=map_doc.get("city")%>" required="">
                                    </div>
                                     <div class="form-group col-md-6">
                                       <label for="pno">Address:</label>
                                       <input type="text" class="form-control" id="pno" placeholder="Address:" name="address" value="<%=map_doc.get("address")%>" required="">
                                    </div>
                                 </div>
                               <center>  <button type="submit" class="btn btn-primary">Add</button></center>
                            
                           </div>
                        </div>
                     </div>
                  </div>
                   
                   
                   
                   
                   
                   
                   
               </div>
            
            </div>
          </form>
        
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
              $(document).on("click", ".browse", function() {
  var file = $(this).parents().find(".file");
  file.trigger("click");
});
$('input[type="file"]').change(function(e) {
  var fileName = e.target.files[0].name;
  $("#file").val(fileName);

  var reader = new FileReader();
  reader.onload = function(e) {
    // get loaded data and render thumbnail.
    document.getElementById("preview").src = e.target.result;
  };
  
  
  // read the image file as a data URL.
  reader.readAsDataURL(this.files[0]);
});



              </script>
    </body>
</html>

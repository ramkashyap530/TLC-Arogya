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
   String staff_code="";
   staff_code=request.getParameter("staff_code");
    WMAS0001_Dao doc_data=new WMAS0001_Dao();
     HashMap<String,String> map_staff=doc_data.get_staff_update(staff_code);
   
   %>       
          
          
          
          
          
          
          
          
    </head>
     <div class="wrapper">
        <%@include  file="../Master/Header_Menu.jsp" %>
        <div id="content-page" class="content-page">
            <form action="../../WMAS0001_SERV" method="post" enctype="multipart/form-data">
                        <input type="hidden" value="10" name="value">
                        <input type="hidden" value="<%=user_id%>" name="user">
                        <input type="hidden" value="<%=Hospital_code%>" name="hospital_code">
                        <input type="hidden" value="<%=map_staff.get("staff_id")%>" name="staff_id" >
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
  <label>Upload a Photo</label>
   <div class="form-group">
                                                       <input type="file" id="file_adhar"  name="prd_staff_photo">
                                                       
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
                              
                              
                              
                              
                          
                        </div>
                     </div>
                  </div>
                  <div class="col-lg-9">
                     <div class="iq-card">
                        <div class="iq-card-header d-flex justify-content-between">
                           <div class="iq-header-title">
                              <h4 class="card-title">Information</h4>
                           </div>
                               <button class="btn btn-success" type="button" data-toggle="modal" data-target="#myModal_doc">Documents</button>
                        </div>
                        <div class="iq-card-body">
                           <div class="new-user-info">
                              
                                 <div class="row">
                                    <div class="form-group col-md-6">
                                       <label for="fname">First Name:</label>
                                       <input type="text" class="form-control" id="fname" placeholder="First Name" name="f_name" value="<%=map_staff.get("name")%>" required="">
                                    </div>
                                    
                                    
                                   
                                    <div class="form-group col-md-6">
                                       <label for="add1">Gender</label>
                                       <select class="form-control" name="gender" required="">
                                           <option>Male</option>
                                           <option>Female</option>
                              
                                       </select>
                                    </div>
                                   
                                    
                                    <div class="form-group col-md-6">
                                       <label for="add1">Age</label>
                                       <input type="text" class="form-control" id="add1" placeholder="Age" name="Age" value="<%=map_staff.get("age")%>" required="">
                                    </div>
                                    
                                    
                                    
                                    
                                    <div class="form-group col-md-6">
                                       <label for="add1">Mobile Number:</label>
                                       <input type="text" class="form-control" id="add1" placeholder="Charge" name="number" value="<%=map_staff.get("mobile_number")%>" required="">
                                    </div>
                                   
                                    <div class="form-group col-md-6">
                                       <label for="cname">Position</label>
                                       <input type="text" class="form-control" id="cname" placeholder="Department Name" name="position" value="<%=map_staff.get("position")%>" required="">
                                    </div>
                                    
                                    <div class="form-group col-md-6">
                                       <label for="mobno">Salary</label>
                                       <input type="text" class="form-control" id="mobno" placeholder="Mobile Number" name="salary" value="<%=map_staff.get("salary")%>" required="">
                                    </div>
                                    <div class="form-group col-md-6">
                                       <label for="altconno">Adhar Card</label>
                                       <input type="text" class="form-control" id="altconno" placeholder="Adhar" name="Adhar" value="<%=map_staff.get("adhar_card")%>" required="">
                                    </div>
                                    <div class="form-group col-md-6">
                                       <label for="email">Date Of Joining</label>
                                     
                                       <input type="date" class="form-control newdate" id="email" placeholder="Date" name="date_of_joining" value="<%=map_staff.get("date_of_joining")%>" required="">
                                      
                                    </div>
                                    
                                    
                                     
                                 </div>
                                          
                               <center>  <button type="submit" class="btn btn-primary">Add</button></center>
                            
                           </div>
                        </div>
                     </div>
                  </div>
                   
                  
                   <div class="modal fade" id="myModal_doc" role="dialog">
    <div class="modal-dialog modal-lg" >
    
      <!-- Modal content-->
     

      <div class="modal-content">
        <div class="modal-header">
         
          <h4 class="modal-title">Upload Documents</h4>
           <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        <div class="modal-body">
      
<div class="row">
             
               
               <div class="col-md-2">
                <label>Adhar Card</label>
                </div>
    <div class="col-md-4">
                                                   <div class="form-group">
                                                       <input type="file" id="file_adhar"  name="prd_staff_adhar">
                                                       
                                                       </div>
                                                   </div>
                                               
                                               <div class="col-sm-2">
                                                   <img src="../Design/adhar.png"   id="preview" class="img-thumbnail">
</div>
    </div>
            
            
                                               
                                   <div class="row">
             
               
               <div class="col-md-2">
                <label>Pan</label>
                </div>
    <div class="col-md-4">
                                                   <div class="form-group">
                                                       <input type="file" id="file_adhar"  name="prd_staff_pan">
                                                       
                                                       </div>
                                                   </div>
                                               
                                               <div class="col-sm-2">
                                                   <img src="../Design/adhar.png"   id="preview" class="img-thumbnail">
</div>
    </div>
               
              
               
               
               
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

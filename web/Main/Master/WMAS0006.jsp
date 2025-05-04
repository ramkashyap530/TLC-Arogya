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
    </head>
     <div class="wrapper">
        <%@include  file="../Master/Header_Menu.jsp" %>
        <div id="content-page" class="content-page">
            
        <div class="container-fluid">
               <div class="row">
                  
                  <div class="col-lg-4">
                      <form action="../../WMAS0001_SERV" method="post" enctype="multipart/form-data">
                        <input type="hidden" value="11" name="value">
                        <input type="hidden" value="<%=user_id%>" name="user">
                        <input type="hidden" value="<%=Hospital_code%>" name="hospital_code">
                        <input type="hidden" value="" name="staff_id">
                     <div class="iq-card">
                        <div class="iq-card-header d-flex justify-content-between">
                           <div class="iq-header-title">
                              <h4 class="card-title">Add Charge Category</h4>
                           </div>
                           
                        </div>
                        <div class="iq-card-body">
                           <div class="new-user-info">
                              
                                 <div class="row">
                                    <div class="form-group col-md-6">
                                       <label for="fname">Category Name</label>
                                       <input type="text" class="form-control" id="fname" placeholder="Category Name" name="cat_name" required="">
                                    </div>
                                     <div class="form-group col-md-1 mt-5">
                                       <center>  <button type="submit" class="btn btn-primary">Save</button></center>
                                         </div>
                                   
                                 </div>
                               
                               
                            
                           </div>
                        </div>
                     </div>
                          </form>
                  </div>
                   
                 
                   
                  
                   
                  <div class="col-lg-8">
                      <form action="../../WMAS0001_SERV" method="post" enctype="multipart/form-data">
                        <input type="hidden" value="12" name="value">
                        <input type="hidden" value="<%=user_id%>" name="user">
                        <input type="hidden" value="<%=Hospital_code%>" name="hospital_code">
                        <input type="hidden" value="" name="staff_id">
                     <div class="iq-card">
                        <div class="iq-card-header d-flex justify-content-between">
                           <div class="iq-header-title">
                              <h4 class="card-title">Add Charge Category</h4>
                           </div>
                            <center>  <button type="submit" class="btn btn-primary">Save</button></center>
                           
                        </div>
                        <div class="iq-card-body">
                           <div class="new-user-info">
                              
                                 <div class="row">
                                    <div class="form-group col-md-4">
                                       <label for="fname">Category Name</label>
                                       <select class="form-control" name="category_name" required="">
                                           <option>...</option>
                                           
                                       <%
                                        WMAS0001_Dao st_1=new WMAS0001_Dao();
                                        ArrayList<String> data=st_1.getdata("4","");
                                        for(String cd:data){
                                            out.println(cd);
                                        }
                                        %>
                                           
                                           
                                       </select>
                                    </div>
                                     
                                     <div class="form-group col-md-4">
                                       <label for="fname">Charge Name</label>
                                       <input type="text" class="form-control" id="fname" placeholder="Name" name="ca_Name" required="">
                                    </div>
                                     
                                     <div class="form-group col-md-2">
                                       <label for="fname">Amount</label>
                                       <input type="number" class="form-control" id="fname" placeholder="Amount" name="Amount" required="">
                                    </div>
                                     <div class="form-group col-md-1 mt-4">
                                         <button class="btn btn-warning add_form_field">Add</button>
                                         </div>
                                     
                                     
                                   
                                 </div>
                               
                               
                               <div class="container1">

                                  </div>
                               
                            
                           </div>
                        </div>
                     </div>
                        </form>
                  </div> 
                   
               </div>
            
            </div>
          </form>
          
          <div class="row">
        <div class="col-sm-12 col-lg-4">
                     <div class="iq-card">
                        <div class="iq-card-header d-flex justify-content-between">
                           <div class="iq-header-title">
                              <h4 class="card-title">All Charges</h4>
                           </div>
                        </div>
                        <div class="iq-card-body">
                           <p>Category Wise Charge</p>
                          
                        <input type="hidden" value="7" name="value">
                        
                              <div class="form-row">
                                 <div class="col-md-6 mb-3">
                                    <label for="validationDefault04">Category</label>
                                    <select class="form-control"   id="category" name="sub"  required onchange="all_hospital_charges()">
                                      
                                       <option>...</option>
                                <%
                                        WMAS0001_Dao st_2=new WMAS0001_Dao();
                                        ArrayList<String> data_1=st_2.getdata("4","");
                                        for(String cd:data_1){
                                            out.println(cd);
                                        }
                                        %>
                                    </select>
                                 </div>
                                  
                              </div>
                              
                              
                         
                        </div>
                     </div>
                     
            
        </div> 
                                    
                                    <div class="col-sm-12 col-lg-8">
                     <div class="iq-card">
                        <div class="iq-card-header d-flex justify-content-between">
                           <div class="iq-header-title">
                              <h4 class="card-title">All Charges</h4>
                           </div>
                        </div>
                       
                          
                          
                         <div class="iq-card-body">
                                  
                                    <div class="col-md-12 mb-3" id="submodnm">
 
                                   </div>
                                     
                                 </div>
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
          
          
          
          <script>
              $(document).ready(function() {
    var max_fields = 10;
    var wrapper = $(".container1");
    var add_button = $(".add_form_field");

    var x = 1;
    $(add_button).click(function(e) {
        e.preventDefault();
        if (x < max_fields) {
            x++;
            $(wrapper).append('<div class="row"><div class="form-group col-md-4"></div><div class="form-group col-md-4">\n\
<input type="text" class="form-control" id="fname" placeholder="Name" name="ca_Name"> </div><div class="form-group col-md-2"><input type="text" class="form-control" id="fname" placeholder="Amount" name="Amount"> </div>\n\
<a href="#" class="delete btn btn-danger btn-sm" style="height: 32px;">Delete</a></div>'); //add input box
        } else {
            alert('You Reached the limits')
        }
    });

    $(wrapper).on("click", ".delete", function(e) {
        e.preventDefault();
        $(this).parent('div').remove();
        x--;
    })
});
     
              
          </script>
    </body>
</html>

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
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Assign Module</title>
         <%@include file="../Design/All/All_css.jsp" %>
          <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css">
          <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>

          <script src="../Js/WMAS0001JS.js"></script>
    </head>
    <body >
        <div class="wrapper">
        <%@include  file="../Master/Header_Menu.jsp" %>
        
        <div id="content-page" class="content-page">
            <div class="container-fluid">
               <div class="row">
                  <div class="col-sm-12 col-lg-6">
                     <div class="iq-card">
                        <div class="iq-card-header d-flex justify-content-between">
                           <div class="iq-header-title">
                              <h4 class="card-title">Room Master</h4>
                           </div>
                        </div>
                        <div class="iq-card-body">
                           <p>Add All Rooms </p>
                           <form action="../../WMAS0001_SERV" method="post">
                        <input type="hidden" value="8" name="value">
                        <input type="hidden" value="<%=user_id%>" name="user">
                        <input type="hidden" value="<%=Hospital_code%>" name="hospital_code">
                        
                              <div class="form-row">
                                 
                                <div class="col-md-4 mb-3">
                                                                       
                                    <label for="validationDefault01">Room Number/Name</label>
                                    <input type="text" class="form-control" id="validationDefault01"  name="Room_name" required>
                             
                                 </div> 
                                  
                                  
                                  
                                   <div class="col-md-3 mb-3">
                                                                       
                                    <label for="validationDefault01">Room Category</label>
                                    <select  class="form-control" name="room_category">
                                        <option></option>
                                        <%
                                        WMAS0001_Dao st_1=new WMAS0001_Dao();
                                        ArrayList<String> data_caat=st_1.getdata("7","");
                                        for(String cd:data_caat){
                                            out.println(cd);
                                        }
                                        %>
                                    </select>
                             
                                 </div>
                                  
                                  <div class="col-md-3 mb-3">
                                                                       
                                    <label for="validationDefault01">Number Of Bed</label>
                                    <input type="text" class="form-control" id="validationDefault01"  name="Number_of_bed" required>
                             
                                 </div>
                                 
                                  <div class="col-md-3 mb-3">
                                                                       
                                    <label for="validationDefault01">Floor Number</label>
                                    <input type="text" class="form-control" id="validationDefault01"  name="Floor" required>
                             
                                 </div>

                                 
                              </div>
                              
                              <div class="form-group">
                                <center> <button class="btn btn-primary" type="submit">Add Room</button></center>
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
                              <h4 class="card-title">Active Rooms</h4>
                           </div>
                        </div>
                        <div class="iq-card-body">
                           <p></p>
                     
                        
                              <div class="form-row">
                                
                               <%
                                   WMAS0001_Dao getr=new WMAS0001_Dao();
                             ArrayList<HashMap<String,String>> data=getr.getroom(Hospital_code);
                             for(HashMap<String,String> cd: data){
                                 
                           
                               %>
                                  
                                  
                                  
                                  
                                  
                                  <div class="col-md-3">
                                    <div class="row">
                                        <div class="col-md-9">
                                           <center> <i class="fas fa-person-booth fa-4x " style="color:green"></i></center>
                                    </div>
                                        <div class="col-md-12">
                                            <input type='hidden' value="<%=cd.get("room_id")%>" id='mst_room_id'>
                                            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModalCenter_<%=cd.get("name")%>" onclick="get_total_number_rooms()">
                        Room No:  <%=cd.get("name")%>
                           </button>
                                            </div>
                                             <div class="col-md-9">
                                   <center> <b> <span>Floor No : <%=cd.get("floor_no")%></span></b></center>
                                            </div>
                                    </div>
                                     </div>
                                               <div id="No_of_beds">
                                            
                                            
                                        </div>
                                            
                                  
                                  <div class="modal fade" id="exampleModalCenter_<%=cd.get("name")%>" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                              <div class="modal-dialog modal-dialog-centered" role="document">
                                 <div class="modal-content">
                                    <div class="modal-header">
                                       <h5 class="modal-title" id="exampleModalCenterTitle">Room No : <%=cd.get("name")%></h5>
                                       <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                       <span aria-hidden="true">&times;</span>
                                       </button>
                                    </div>
                                    <div class="modal-body" >
                                      
                                     
                                      
                                    </div>
                                    <div class="modal-footer">
                                       <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                     
                                    </div>
                                 </div>
                              </div>
                           </div>
                                   <%
                                       }%>
                                  
                                   
                                  
                                   
                                  
                                   
                                  
                                  
                                  
                                 
                                 
                                    
                                    
                                    
                                    
                                    
                                    
                                     
                                 
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

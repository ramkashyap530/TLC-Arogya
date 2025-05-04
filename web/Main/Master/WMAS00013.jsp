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
        <title>Hospital Master</title>
         <%@include file="../Design/All/All_css.jsp" %>
          <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css">
          <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>

          <script src="../Js/WMAS0001JS.js"></script>
<!--          
             <%
   String charge_id="";
   charge_id=request.getParameter("charge_id");
    WMAS0001_Dao doc_data=new WMAS0001_Dao();
     HashMap<String,String> map_charge=doc_data.get_update_mode(charge_id);
   
   %>      -->
   
   
              <%
   String Category_id="";
   Category_id=request.getParameter("Category_id");
    WMAS0001_Dao doc_data_1=new WMAS0001_Dao();
     HashMap<String,String> map_cate=doc_data_1.get_update_mode_category(Category_id);
     System.out.println("---check"+map_cate);
   
   %>      
          
          
          
          
    </head>
    <body onload="get_other_charge_details()">
        <div class="wrapper">
        <%@include  file="../Master/Header_Menu.jsp" %>
        
        <div id="content-page" class="content-page">
            <div class="container-fluid">
               <div class="row">
                   
                   <!--Adding other charge category -->
                    <div class="col-sm-12 col-lg-6">
                     <div class="iq-card">
                        <div class="iq-card-header d-flex justify-content-between">
                           <div class="iq-header-title">
                              <h4 class="card-title">Other Charge Master</h4>
                           </div>
                        </div>
                        <div class="iq-card-body">
                           <p>Add Category Here</p>
                           <form action="../../WMAS0001_SERV" method="post">
                        <input type="hidden" value="25" name="value">
                        <input type="hidden" value="<%=user_id%>" name="user">
                        <input type="hidden" value="<%=Hospital_code%>" name="hospital_code">
                        <input type="hidden" value="<%=Category_id%>" name="Category_id" >
                        
                        
                              <div class="form-row">
                                 
                                <div class="col-md-4 mb-3">
                                                                       
                                    <label for="validationDefault01">Category  Name</label>
                                    <input type="text" class="form-control" id="validationDefault01"  name="Category_name" required value="<%=map_cate.get("category_name")%>">
                             
                                 </div> 
                                  
                              </div>
                        
                        
                        
                        <!--for logo--> 
                        
                       
                        
                        <!--ends here--> 
                              
                              <div class="form-group">
                                <center> <button class="btn btn-primary" type="submit">Add Category</button></center>
                              </div>
                           </form>
                        </div>
                     </div>
                     
            
        </div>
                   
                   
                   <!--ends -->
                   
                   
                   
                  <div class="col-sm-12 col-lg-6">
                     <div class="iq-card">
                        <div class="iq-card-header d-flex justify-content-between">
                           <div class="iq-header-title">
                              <h4 class="card-title">Other Charge Master</h4>
                           </div>
                        </div>
                        <div class="iq-card-body">
                           <p>Add Other Charges Here</p>
                           <form action="../../WMAS0001_SERV" method="post">
                        <input type="hidden" value="21" name="value">
                        <input type="hidden" value="<%=user_id%>" name="user">
                        <input type="hidden" value="<%=Hospital_code%>" name="hospital_code">
                        <input type="hidden" value="<%=charge_id%>" name="charge_id" >
                        
                              <div class="form-row">
                                  
                                  
                                  
                                  <div class="form-group col-md-4">
                                       <label for="fname">Charge Category</label>
                                       <select class="form-control" name="room_no" id="category_id" onchange="get_room()">
                                           
                                           <option></option>
                                              <%
                                          WMAS0001_Dao st_1=new WMAS0001_Dao();
                                           String  Category_code[]=new String [9];
                                           Category_code[0]=Hospital_code;
                                          
                                        ArrayList<String> data=st_1.getdata("9",Hospital_code);
                                        for(String cd:data){
                                            out.println(cd);
                                        }
                                        %>
                                    %>  
                                           
                                           
                                           
                                       </select>
                                    </div>
                                   
                                   
                                  
                                  
                                  
                                 
                                <div class="col-md-4 mb-3">
                                                                       
                                    <label for="validationDefault01">Charge Name</label>
                                    <input type="text" class="form-control" id="validationDefault01"  name="charge_name" required value="<%=map_charge.get("charge_name")%>">
                             
                                 </div> 
                                  <div class="col-md-4 mb-3">
                                                                       
                                    <label for="validationDefault01">Charge Rate</label>
                                    <input type="text" class="form-control" id="validationDefault01"  name="charge_rate" required  value="<%=map_charge.get("charge_rate")%>">
                             
                                 </div> 
                              
                              </div>
                        
                        
                        
                        <!--for logo--> 
                        
                       
                        
                        <!--ends here--> 
                              
                              <div class="form-group">
                                <center> <button class="btn btn-primary" type="submit">Add</button></center>
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
                              <h4 class="card-title">Active Hospitals</h4>
                           </div>
                        </div>
                        <div class="iq-card-body">
                            <div class='table-responsive'>
                        <table class="table table-bordered table-responsive-md table-striped text-center" id="M_tab">
                                 <thead>
                                    <tr>
                                        <th style="white-space: nowrap">S.No</th>
                                        <th style="white-space: nowrap">Charge Name</th>
                                        <th style="white-space: nowrap">Rate</th>
                                        <th style="white-space: nowrap">Update Rate</th>
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

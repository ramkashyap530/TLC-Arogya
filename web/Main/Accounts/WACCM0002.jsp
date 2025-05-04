<%-- 
    Document   : WMAS0004
    Created on : 3 Feb, 2022, 3:00:14 PM
    Author     : Tarun
--%>

<%@page import="Accounts.Masters.WACCM0001_Dao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
       <%@include file="../Design/All/All_css.jsp" %>
          <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css">
          <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>

          <script src="../Js/WACCM0001JS.js"></script>
    </head>
    <body onload="get_groups();">
     <div class="wrapper">
        <%@include  file="../Master/Header_Menu.jsp" %>
        <div id="content-page" class="content-page">
            <form action="../../WMAS0001_SERV" method="post" enctype="multipart/form-data">
                        <input type="hidden" value="10" name="value">
                        <input type="hidden" value="<%=user_id%>" name="user">
                        <input type="hidden" value="<%=Hospital_code%>" name="hospital_code">
                        <input type="hidden" value="" name="staff_id">
        <div class="container-fluid">
               <div class="row">
                  
                  <div class="col-lg-7">
                     <div class="iq-card">
                        <div class="iq-card-header d-flex justify-content-between">
                           <div class="iq-header-title">
                              <h4 class="card-title">Ledger Creation</h4>
                           </div>
                            <div class="col-md-12 row">
                                
                                <div class="col-md-3">
                                    <span class="badge bg-success"><u>Total Opening Balance</u></span>
                                    </div>
                                </div>
                        </div>
                        <div class="iq-card-body">
                           <div class="new-user-info">
                              
                                 <div class="row">
                                    <div class="form-group col-md-5" >
                                       <label for="fname">Name</label>
                                       <input type="text" class="form-control" id="fname" placeholder="Ledger Name" name="ledger_name">
                                    </div>
                                   
                                    <div class="form-group col-md-3" id="search-autocomplete">
                                       <label for="add1">Alias</label>
                                      <input type="text" class="form-control" id="fname" placeholder="Alias" name="Alias_name">
                                    </div>
                                   

                                    
                                    <div class="form-group col-md-4">
                                       <label for="mobno">Under</label>
                                       <select class="form-control" name="Group_behave">
                                           <option></option>
                                           <%
                                       WACCM0001_Dao  st_1=new WACCM0001_Dao();
                                        ArrayList<String> data=st_1.getdata("1","");
                                        for(String cd:data){
                                            out.println(cd);
                                        }
                                        %>
                                       </select>
                                       </div>
                                     
                                     <div class="form-group col-md-4">
                                       <label for="mobno">Type Of Ledger</label>
                                       <select class="form-control" name="Group_behave">
                                           <option>No</option>
                                           <%
                                       WACCM0001_Dao  type=new WACCM0001_Dao();
                                        ArrayList<String> type_data=type.getdata("2","");
                                        for(String cd:type_data){
                                            out.println(cd);
                                        }
                                        %>
                                       </select>
                                       </div>
                                     
                                     
                                         <div class="form-group col-md-6">
                                       <label for="mobno">Used For Calculation</label>
                                       <select class="form-control" name="Used_calculation">
                                          
                                           <%
                                       WACCM0001_Dao  rounding=new WACCM0001_Dao();
                                        ArrayList<String> type_rounding=type.getdata("3","");
                                        for(String cd:type_rounding){
                                            out.println(cd);
                                        }
                                        %>
                                       </select>
                                       </div>

                                     
                                     
                                     
                                    
                               <div class="form-group col-md-12 row" >
                                   <div class="col-md-6">
                                       <label for="fname"><span class="badge bg-success">Opeaning Balance</span></label>
                                       </div>
                                    <div class="col-md-6">
                                       <input type="number" class="form-control" id="fname" placeholder="Amount" name="ledger_name">
                                    </div>
                                   
                                   </div>
                               
                               
                               
                                    
                                    
                                    
                                     
                                 </div>
                               <center>  <button type="submit" class="btn btn-primary">Add</button></center>
                            
                           </div>
                        </div>
                     </div>
                  </div>
                   
                  
                   
                   <div class="col-lg-5">
                     <div class="iq-card">
                        <div class="iq-card-header d-flex justify-content-between">
                           <div class="iq-header-title">
                              <h4 class="card-title">Other Details</h4>
                           </div>
                        </div><!-- comment -->
                        <div class="iq-card-body"><!-- comment -->
                                <div class="col-md-12">
                                  <center>  <span class="badge bg-success">Mailing Details</span></center>
                                    <div class="row">
                                    <div class="form-group col-md-6" >
                                       <label for="fname">Name</label>
                                       <input type="text" class="form-control" id="fname" placeholder="Ledger Name" name="ledger_name">
                                    </div>
                                        
                                        <div class="form-group col-md-6" >
                                       <label for="fname">Address</label>
                                       <input type="text" class="form-control" id="fname" placeholder="Ledger Name" name="ledger_name">
                                    </div>
                                        
                                        <div class="form-group col-md-4" >
                                       <label for="fname">country</label>
                                       <select class="form-control">
                                           
                                       </select>
                                    </div>
                                        
                                        <div class="form-group col-md-4" >
                                       <label for="fname">State</label>
                                       <select class="form-control">
                                           
                                       </select>
                                    </div>
                                        
                                        <div class="form-group col-md-4" >
                                       <label for="fname">Pin code</label>
                                       <input type="text" class="form-control" id="fname" placeholder="Ledger Name" name="ledger_name">
                                    </div>
                                        
                                        </div>
                                    
                                    </div>
                            
                            
                            <div class="col-md-12">
                                  <center>  <span class="badge bg-success">Baking Details</span></center>
                                    <div class="row"> 
                                        <div class="form-group col-md-4" >
                                       <label for="fname">Banking Details</label>
                                       <select class="form-control">
                                           <option>No</option>
                                           <option>Yes</option>
                                       </select>
                                    </div>
                                        
                                        </div>
                                    
                                    </div>
                            
                                </div>
                            
                        </div><!-- comment -->
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
    </body>
</html>

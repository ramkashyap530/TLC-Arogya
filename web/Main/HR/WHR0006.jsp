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
    <body onload="get_leaves()">
     <div class="wrapper">
        <%@include  file="../Master/Header_Menu.jsp" %>
        <div id="content-page" class="content-page">
            <div class='col-md-12'>
 <div class='row'>
                        <div class="col-lg-2" id="compete_details" >
                     <div class="iq-card">
                        <div class="iq-card-header d-flex justify-content-between">
                           <div class="iq-header-title">
                            <center>  <h4 class="card-title">Joining </h4></center>
                           </div>
                        </div>
                        <div class="iq-card-body">
                            <center><button class='btn btn-sm btn-warning'>Generate</button></center>
                                    </div>
                        </div>
                        </div>
            
            
            <div class="col-lg-2" id="compete_details" >
                     <div class="iq-card">
                        <div class="iq-card-header d-flex justify-content-between">
                           <div class="iq-header-title">
                            <center>  <h4 class="card-title">Resignanition</h4></center>
                           </div>
                        </div>
                        <div class="iq-card-body">
                            <center><button class='btn btn-sm btn-warning'>Generate</button></center>
                                    </div>
                        </div>
                        </div>
     
     <div class="col-lg-2" id="compete_details" >
                     <div class="iq-card">
                        <div class="iq-card-header d-flex justify-content-between">
                           <div class="iq-header-title">
                            <center>  <h4 class="card-title">Agreement</h4></center>
                           </div>
                        </div>
                        <div class="iq-card-body">
                            <center><button class='btn btn-sm btn-warning'>Generate</button></center>
                                    </div>
                        </div>
                        </div>
     
     <div class="col-lg-2" id="compete_details" >
                     <div class="iq-card">
                        <div class="iq-card-header d-flex justify-content-between">
                           <div class="iq-header-title">
                            <center>  <h4 class="card-title">Terminanition</h4></center>
                           </div>
                        </div>
                        <div class="iq-card-body">
                            <center><button class='btn btn-sm btn-warning'>Generate</button></center>
                                    </div>
                        </div>
                        </div>
     
     
     <div class="col-lg-2" id="compete_details" >
                     <div class="iq-card">
                        <div class="iq-card-header d-flex justify-content-between">
                           <div class="iq-header-title">
                            <center>  <h4 class="card-title">Warning</h4></center>
                           </div>
                        </div>
                        <div class="iq-card-body">
                            <center><button class='btn btn-sm btn-warning'>Generate</button></center>
                                    </div>
                        </div>
                        </div>
     
     </div>
                </div>
                        
                     </div>  
                        
                        
                        
                  </div>
                        
                        
        <!--for salary advance--> 
        
        
        
        <!--ends here--> 
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
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
             $(function(){
    var dtToday = new Date();
    
    var month = dtToday.getMonth() + 1;
    var day = dtToday.getDate();
    var year = dtToday.getFullYear();
    if(month < 10)
        month = '0' + month.toString();
    if(day < 10)
        day = '0' + day.toString();
    
    var maxDate = year + '-' + month + '-' + day;
    
    $('#txtDate').attr('max', maxDate);
});




              </script>
              
              
             
    </body>
</html>

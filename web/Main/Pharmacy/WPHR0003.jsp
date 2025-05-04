<%-- 
    Document   : WMAS0004
    Created on : 3 Feb, 2022, 3:00:14 PM
    Author     : Tarun
--%>

<%@page import="Pharmacy.WPHR0001_Dao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
       <%@include file="../Design/All/All_css.jsp" %>
          <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css">
          <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
 <link rel="stylesheet" type="text/css" href="../Design/datatable/datatables.min.css">
    <link rel="stylesheet" type="text/css" href="../Design/All/css/select2.min.css">
          <script src="../Js/WPHR0001.js"></script>
          
    </head>
    <body onload="stock()">
    <div class="wrapper" >
        <%@include  file="../Master/Header_Menu.jsp" %>
        <div id="content-page" class="content-page">
            
        <div class="container-fluid ">
             <form action="../../WPHR0001_SERV" method="post" >
            
            <div class="col-m-12">
            
               <div class="row">
                  
                        <div class="col-lg-2">  
                     <div class="iq-card">
                         <div class="iq-card-header d-flex justify-content-between">
                           <div class="iq-header-title">
                              <h4 class="card-title">Search</h4>
                           </div>
                          
                           
                        </div>
                         
                         
                         
                         <div class="iq-card-body">
                             <div class="form-group col-md-12">
                                       <label for="fname">Item Name</label>
                                   
                  <select class='select2 form-control input-sm' id="item_name" name='item_name'>
                      <option class='' ></option>
                      <%
                                    WPHR0001_Dao doc=new WPHR0001_Dao();
                                        ArrayList<String> data=doc.getdata("1",Hospital_code);
                                        for(String cd:data){
                                            out.println(cd);
                                        }
                                        %>
                                    %>
                      
                  </select>
                                    </div>
                             <div class="form-group col-md-12">
                                       <label for="fname">Item Code</label>
                                       <input type="text" class="form-control" id="item_code" placeholder="code" name="">
                                    </div>
                             
                            
                             
                         </div>
                         
                     </div>
                     </div>  
                   
                  <div class="col-lg-10">
                     
                        <input type="hidden" value="1" name="value">
                        <input type="hidden" value="<%=user_id%>" name="user">
                        <input type="hidden" value="<%=Hospital_code%>" name="hospital_code">
                        <input type="hidden" value="" name="staff_id">
                     <div class="iq-card">
                        <div class="iq-card-header d-flex justify-content-between">
                           <div class="iq-header-title">
                              <h4 class="card-title">Item Stock List</h4>
                           </div>
                          
                           
                        </div>
                        <div class="iq-card-body">
                           <table class="table table-bordered table-responsive-md table-striped text-center" id='tab'>
                                 <thead>
                                    <tr>
                                        <th>S.No</th>
                                        <th>Item Code</th>
                                       <th>Item Name</th>
                                       <th>Qty In</th>
                                       <th>Qty Out</th>
                                       <th>Current Qty</th>
                                       <th>Update Qty</th>
                                      
                                     
                                       
                                    </tr>
                                 </thead>
                                 <tbody id="tbody">
                                    
                                    
                                    
                                    
                                 </tbody>
                              </table>
                        </div>
                     </div>
                      
                  </div>
                        
                    
                        
                        
                     
                       
                            
                        
                        
                        
                        
                 
                        
                        
                       
                        
                        
                        
                        
                        </div>
                       </div> 
                        
                        
                        
                        
                        
                        
                        
                        
                        
                   
               </div>
              </form>
            </div>
          </form>
          
          
            
        </div> 
        </div>
        
        
              <%@include file='../Design/All/All_js.jsp'%>
               <script src="../Design/All/js/tables/datatable/datatables.min.js"></script>
      <script src="../Design/All/js/scripts/tables/datatables/datatable-basic.min.js"></script>
     
     
      <script src="../Design/All/js/tables/datatable/datatables.min.js"></script>
    <script src="../Design/All/js/tables/datatable/dataTables.buttons.min.js"></script>
    <script src="../Design/All/js/tables/buttons.flash.min.js"></script>
    <script src="../Design/All/js/tables/jszip.min.js"></script>
    <script src="../Design/All/js/tables/pdfmake.min.js"></script>
    <script src="../Design/All/js/tables/vfs_fonts.js"></script>
    <script src="../Design/All/js/tables/buttons.html5.min.js"></script>
    <script src="../Design/All/js/tables/buttons.print.min.js"></script>
    
    <script src="../Design/All/js/select2.full.min.js"></script>
     
<!--    <script src="../Design/All/js/tables/datatables/datatable-advanced.min.js"></script>-->

    
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

<%-- 
    Document   : WMAS0004
    Created on : 3 Feb, 2022, 3:00:14 PM
    Author     : Tarun
--%>


<%@page import="Inventory.WINV0001_dao"%>
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
          <script src="../Js/WINV0001JS.js"></script>
          
    </head>
    <body onload="get_category()">
    <div class="wrapper" >
        <%@include  file="../Master/Header_Menu.jsp" %>
        <div id="content-page" class="content-page">
            
        <div class="container-fluid ">
              <form action="../../WINV0001_serv" method="post" >
                 <input type="hidden" value="3" name="value">
                        <input type="hidden" value="<%=user_id%>" name="user">
                        <input type="hidden" value="<%=Hospital_code%>" name="hospital_code">
            
            <div class="col-m-12">
            
               <div class="row">
                  
                        <div class="col-lg-12">  
                     <div class="iq-card">
                         <div class="iq-card-header d-flex justify-content-between">
                           <div class="iq-header-title">
                              <h4 class="card-title">Ground Balance
                              </h4>
                                </div>
                  
                        </div>
                         
                         
                         
                         <div class="iq-card-body">
                             <div class='row'>
                             <div class="form-group col-md-2">
                                       <label for="fname">Category</label>
                                       <select class="form-control" name='category'>
                                           <option></option>
                                           <%
                                    WINV0001_dao doc=new WINV0001_dao();
                                        ArrayList<String> data=doc.getdata("1",Hospital_code);
                                        for(String cd:data){
                                            out.println(cd);
                                        }
                                        %>
                                    %>
                                       </select>
                                       
                                    
                                    </div>
                                    
                                    
                                    <div class="form-group col-md-2">
                                       <label for="fname">Item Name</label>
                                       <input type='text' class='form-control' name='item_name'>
                                       
                                       
                                    
                                    </div>
                                    
                                    <div class="form-group col-md-2">
                                       <label for="fname">Item Qty</label>
                                       <input type='text' class='form-control' name='item_qty'>
                                   
                                    </div>

                                    
                                    <div class="form-group col-md-2">
                                       <label for="fname">Item From</label>
                                       <input type='text' class='form-control' name='item_from'>
                                   
                                    </div>
                                    
                                     <div class="form-group col-md-2">
                                       <label for="fname">Recieved By</label>
                                       <input type='text' class='form-control' name='rec_by'>
                                   
                                    </div>
                                    
                                    <div class="form-group col-md-2">
                                       <label for="fname">Remark</label>
                                       <input type='text' class='form-control' name='remark'>
                                   
                                    </div>
                                    <hr style="color: black">
                                    <div class='col-md-12'>
                                        <center><button class='btn btn-sm btn-success'>Save</button></center>
                                        </div>
                                        
                                   
                                 
                                 </div>
                             
                         </div>
                                  
                         
                         
                         
                         
                         
                     </div>
                     </div>  
                   
                                          
                    
                        
                        
                     
                       
                            
                        
                        
                        
                        
                 
                        
                        
                       
                        
                        
                        
                        
                        </div>
                       </div> 
                        
                        
                        
                        
                        
                        
                        
                        
                      </form>    
                   
               </div>
            
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

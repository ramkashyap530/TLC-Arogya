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

          <script src="../Js/WPHR0001.js"></script>
    </head>
     <div class="wrapper">
        <%@include  file="../Master/Header_Menu.jsp" %>
        <div id="content-page" class="content-page">
            
        <div class="container-fluid ">
             <form action="../../WPHR0001_SERV" method="post" >
            
            <div class="col-m-12">
            
               <div class="row">
                   
                   
                  <div class="col-lg-6">
                     
                        <input type="hidden" value="1" name="value">
                        <input type="hidden" value="<%=user_id%>" name="user">
                        <input type="hidden" value="<%=Hospital_code%>" name="hospital_code">
                        <input type="hidden" value="" name="staff_id">
                     <div class="iq-card">
                        <div class="iq-card-header d-flex justify-content-between">
                           <div class="iq-header-title">
                              <h4 class="card-title">Add Medicines/Items</h4>
                           </div>
                            <center>  <button type="submit" class="btn btn-primary">Save</button></center>
                           
                        </div>
                        <div class="iq-card-body">
                           <div class="new-user-info">
                              
                                 <div class="row">
                                    
                                     <div class="form-group col-md-4">
                                       <label for="fname">Item Name</label>
                                       <input type="text" class="form-control" id="fname" placeholder="Item Name" name="Item_name">
                                    </div>
                                     
                                     <div class="form-group col-md-4">
                                       <label for="fname">Type</label>
                                       <select class="form-control" onchange="conversion()" id="purchase_unit" name="Item_type">
                                           <option></option>
                                           <option value='cap'>Capsule (cp)</option>
                                           <option value='Tab'>Tablet (Tab)</option>
                                           <option Value='Bot'>Bottle</option>
                                           <option>Box</option>
                                           <option>Kgs</option>
                                           <option>Liters</option>
                                          
                                           <option>Pieces</option>
                                            <option>Other</option>
                                           
                                           </select>
                                       
                                       
                                     
                                    </div>
                                     
                                     
                                     <div class="form-group col-md-4">
                                       <label for="fname">Hsn Code</label>
                                       <input type="text" class="form-control" name="hsn_code">
                                       
                                       
                                     
                                    </div>
                                     
                                      <div class="form-group col-md-3">
                                       <label for="fname">Batch No</label>
                                       <input type="text" class="form-control" name="batch_no">
                                       
                                       
                                     
                                    </div>
                                     
                                     
                                       <div class="form-group col-md-3">
                                       <label for="fname">Manfu.Date</label>
                                       <input type="date" class="form-control" name="Manfu_date">
                                    </div>
                                     
                                     <div class="form-group col-md-3">
                                       <label for="fname">Exp.Date</label>
                                       <input type="date" class="form-control" name="Exp_date">
                                    </div>
                                     
                                     
                                     
                                      
                                     
                                     <div class="form-group col-md-3">
                                       <label for="fname">Vendor</label>
                                       <select class="form-control" onchange="conversion()" id="purchase_unit" name="vendor_name">
                                           <option value='VEN0001'>Tarun Pharma</option>
                                           
                                           
                                           </select>
                                       
                                       
                                     
                                    </div>
                                      
                                     
                                     
                                     
                                     
                                     
                                     
                                     
                                     
                                     
                                     
<!--                                     <div class="form-group col-md-1 mt-4">
                                         <button class="btn btn-warning add_form_field">Add</button>
                                         </div>-->
                                     
                                     
                                   
                                 </div>

                               
                               
                               <div class="container1">

                                  </div>
                               
                            
                           </div>
                        </div>
                     </div>
                      
                  </div>
                        
                    
                        
                        
                     
                       
                            
                        
                        
                        
                        
                 <div class="col-lg-3">  
                     <div class="iq-card">
                         <div class="iq-card-header d-flex justify-content-between">
                           <div class="iq-header-title">
                              <h4 class="card-title">Pricing</h4>
                           </div>
                         
                           
                        </div>
                         
                         
                         
                         <div class="iq-card-body">
                            <div class="form-group col-md-12">
                                       <label for="fname">Purchase Price Per Unit</label>
                                       <input type="text" class="form-control" id="purchase_price" placeholder="price" name="purchase_price" onkeyup="">
                                    </div>
                             
                             
                             
                             <div class="form-group col-md-12">
                                       <label for="fname">Tax %</label>
                                       <input type="text" class="form-control" id="Tax" placeholder="%" name="tax" onkeyup="">
                                    </div>
                             
                                  <div class="form-group col-md-12">
                                       <label for="fname">Sale Price Per Unit</label>
                                       <input type="text" class="form-control" id="sale" placeholder="price" name="sale_price">
                                    </div>
                                 
                                
                             
                                 
                         </div>
                         
                     </div>
                     </div>
                        
                        
                        
              <div class="col-lg-3">  
                     <div class="iq-card">
                         <div class="iq-card-header d-flex justify-content-between">
                           <div class="iq-header-title">
                              <h4 class="card-title">Stock</h4>
                           </div>
                          
                           
                        </div>
                         
                         
                         
                         <div class="iq-card-body">
                             <div class="form-group col-md-12">
                                       <label for="fname">Opening Qty Per Unit</label>
                                       <input type="text" class="form-control" id="fname" placeholder="Qty" name="qty_in">
                                    </div>
                             <div class="form-group col-md-12">
                                       <label for="fname">At Price</label>
                                       <input type="text" class="form-control" id="fname" placeholder="Qty" name="">
                                    </div>
                             
                             <div class="form-group col-md-12">
                                       <label for="fname">Min Stock Per Unit </label>
                                       <input type="text" class="form-control" id="fname" placeholder="Qty" name="Min_stock">
                                    </div>
                             
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

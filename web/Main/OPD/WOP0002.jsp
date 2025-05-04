<%-- 
    Document   : WMAS0004
    Created on : 3 Feb, 2022, 3:00:14 PM
    Author     : Tarun
--%>

<%@page import="java.lang.String"%>
<%@page import="OPD.WOP0001_Dao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

         
       <%@include file="../Design/All/All_css.jsp" %>
          <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css">
          <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>

          <script src="../Js/WOP0001JS.js"></script>

          
        
    </head>
    <body onload="get_opd_doctor();get_opd_patient_next_name();get_opd_patient_next_opd_id();get_opd_patient_next_age();">
     <div class="wrapper">
        <%@include  file="../Master/Header_Menu.jsp" %>
        <div id="content-page" class="content-page">
            
                        
        <div class="container-fluid">
               <div class="row">
                   
                               
                      
                  <div class="col-lg-4">
                     <div class="iq-card">
                        <div class="iq-card-header d-flex justify-content-between">
                           <div class="iq-header-title">
                              <h4 class="card-title">OPD Patients</h4>
                           </div>
                        </div>
                         
                         <div class="iq-card-body">
                             <div class="form-group">
                                            <label>Doctor:</label>
                                            <select class="Select2 form-control" id="doc_code" name="DOC">

                                                <%
                                                    WOP0001_Dao doc = new WOP0001_Dao();
                                                    ArrayList<String> data = doc.getdata("1", Hospital_code);
                                                    for (String cd : data) {
                                                        out.println(cd);
                                                    }
                                                %>
                                                %>
                                            </select>
                                        </div>
                                                
                                                
                                                
                                                <table class="table table-bordered table-responsive-md table-striped text-center">
                                 <thead>
                                    <tr>
                                       <th>Sno</th>
                                        <th>Name</th>
                                       <th>Status</th>
                                       <th>Type</th>
                                      
                                       
                                    </tr>
                                 </thead>
                                 <tbody  id="tbody_doc_wise_opd">
                                    
                                    
                                    
                                    
                                 </tbody>
                              </table> 
                            
                             
                             
                         
                             
                        
                         </div>
                         
                         
                         
                         
                         
                         
                        <div class="iq-card-body">


                                   
                              
                 
                        </div>
                     </div>
                  </div>
                  <div class="col-lg-8">
                     <div class="iq-card">
                        <div class="iq-card-header d-flex justify-content-between">
                           <div class="iq-header-title">
                              <h4 class="card-title">OPD Details</h4>
                              
                           </div>
                            <h4 class="card-title" style="float: right">Patient Name: <span class="badge bg-success" id="card_name"></span></h4>
                        </div>
                        <div class="iq-card-body">
                            
                           <div class="new-user-info">
                              
 
                        <div class="row">
                            
                           
                            <div class="form-group col-md-12">
                                       <label for="fname">Diagonisis</label>
                                       <input type="text" class="form-control" id="Diagonisis" placeholder="Diagonisis" name="Diagonisis">
                                    </div>
                            
                            <!-- for prception-->
                            
                                    <div class="form-group col-md-4">
                                       <label for="fname">Medicine Prec:</label>
                                       <input type="text" class="form-control" id="medicine_pre" placeholder="Medicine" name="Patient_name">
                                    </div>
                                                       <div class="form-group col-md-2">
                                       <label for="fname">Dosage:</label>
                                       <input type="text" class="form-control" id="medicine_dos" placeholder="Dosage" name="">
                                    </div>
                            
                             <div class="form-group col-md-4">
                                       <label for="fname">Additional Comments</label>
                                       <input type="text" class="form-control" id="medicine_Add" placeholder="Comments" name="">
                                    </div>
                            
                            
                              <div class="form-group col-md-1">
                                  <label for="fname"></label>
                                  <button  type="button" class="btn btn-primary mb-3"  onclick="add_to_prec()"><i class="fa fa-arrow-right"></i></button>
                            </div>
                                   
                            
                            <!--For Test Advice-->
                                    <div class="form-group col-md-4">
                                       <label for="add1">Test Advised</label>
                                       <input type="text" class="form-control" id="Test" placeholder="Test" name="Patient_number">
                                    </div>
                            
                              <div class="form-group col-md-4">
                                       <label for="fname">Additional Comments</label>
                                       <input type="text" class="form-control" id="test_additional" placeholder="Comments" name="">
                                    </div>
                            
                            
                            <div class="form-group col-md-1">
                                  <label for="fname"></label>
                                  <button  type="button" class="btn btn-primary mb-3"  onclick="add_to_test()"><i class="fa fa-arrow-right"></i></button>
                            </div>
                            
                          
                            
                            
                            <!--ends-->
                                   
                                    
                                    
                                     
                                 </div>
                        
                        
                              
                            
                           </div>
                        </div>
                     </div>
                  </div>
                   
                  
                   
                   
                     
               </div>
                                    
                                    
                                    
                                    
      <div class="row">
                   
                               
                      
                  <div class="col-lg-4">
                     <div class="iq-card">
                        <div class="iq-card-header d-flex justify-content-between">
                           <div class="iq-header-title">
                              <h4 class="card-title">OPD Patients</h4>
                           </div>
                        </div>
                         
                         
                         
                         
                        <div class="iq-card-body">
                            
                            
                            
                            
                            <div class="form-group col-md-12">
                                       <label for="mobno">Date</label>
                                       <input type="date" value='05-03-2025' class="form-control" id="opd_date" placeholder="OPD Date" name="" onchange="get_opd()">
                                    </div> 
                            
                         
                           <div class="col-md-12" style="border: 1px solid" >
                            
                             <center>  <h4><%=hospital_name%></h4></center>
                             <center>  <h6><%=address%></h6></center>
                             
                             <div class="row mb-3">
                                 <div class="col-md-6">
<!--                                     <h6>Doctor Name</h6>-->
                                     </div>
                                 
                                  <div class="col-md-6">
<!--                                     <h6>Phone</h6>-->
                                     </div>
                                 
                                 
                             </div>
                             
                              <hr>
                              <div class="col-md-12" >
                                 <div class="row">
                                     <div class="col-md-2">
                                          Name :  
                                         </div>
                                     <div class="col-md-4" id="page_name"></div>
                                     
                                    
                                 <div col-md-2>Age : </div>
                                 
                                 <div class="col-md-2" id="opd_age">
                                 
                                     </div>
                                 
                                  </div>
                                  <hr>
                                 
                                 </div>
                              <div class="col-md-12">
                              <h1>R<span style="font-size: 20px">x</span></h1>
                              </div>
                              
                              <div class="row">
                              <div class="col-md-3">
                                  
                                
                                  </div>
                                  
                              <div class="col-md-9" >
                             
                                  <table >
                                      <thead>
                                          <tr>
                                              <th style="visibility: "></th>
                                              </tr>
                                                 </thead>
                                      <tbody id="preci">
                                          
                                      </tbody>
                                       
                                          
                                      </table>
                                  
                             
                             </div>
                              </div>
                              
                              
                              
                              
                              
                              
                              
                              
                              
                              
                              
                              
                         </div>
                            



                                  
                              
                              
                              
                              
                          
                        </div>
                     </div>
                  </div>
                  <div class="col-lg-8">
                     <div class="iq-card">
                        <div class="iq-card-header d-flex justify-content-between">
                           <div class="iq-header-title">
                              <h4 class="card-title">Precription Details</h4>
                              
                           </div>
                            
                        </div>
                        <div class="iq-card-body">
                            
                           <div class="new-user-info">
                              <form action="../../WOP0001_SERV" method="post" >
                                   <input type="hidden" value="7" name="value">
                        <input type="hidden" value="<%=user_id%>" name="user">
                        <input type="hidden" value="<%=Hospital_code%>" name="hospital_code" id="hospital_code">
                        <input type="hidden" value="" name="opd_code" id="opd_code">
                              
                               <div class="row">
                               <div class="col-md-12">
                              <table class="table table-bordered table-responsive-md table-striped text-center">
                                 <thead>
                                    <tr>
                                       
                                        <th>Medicine Name</th>
                                       <th>Dosage</th>
                                       <th>Comments</th>
                                       <th>Remove</th>
                                    </tr>
                                 </thead>
                                 <tbody id="perctable">  
                                 </tbody>
                              </table>
                               </div>
                  
                                   <div class="col-md-12">
                                       <table class="table table-bordered table-responsive-md table-striped text-center">
                                 <thead>
                                    <tr>
                                        
                                        <th>Test Name</th>
                                        <th>Comments</th>
                                       <th>Action</th>
                                    </tr>
                                 </thead>
                                 <tbody id="Test_Table_1">
                                    
                                    
                                    
                                    
                                 </tbody>
                              </table>
                                       </div>
                        
                               
                               
                               
                               
                               
                               </div>
                        
                                
                               <center>  <button type="submit" class="btn btn-primary">Proceed</center>
                               </form>
                            
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
    
   
    
    
    
    
    <Script>
        document.getElementById('opd_date').valueAsDate = new Date();
        
        </script>
    
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

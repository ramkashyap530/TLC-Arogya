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
        <link rel="stylesheet" type="text/css" href="../Design/All/css/select2.min.css">
        <%@include file="../Design/All/All_css.jsp" %>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css">

        <link rel="stylesheet" type="text/css" href="../Design/datatable/datatables.min.css">


        <script src="../Js/WOP0001JS.js"></script>
    </head>
    <body onload="get_opd_rec()">
        <div class="wrapper">
            <%@include  file="../Master/Header_Menu.jsp" %>
            <div id="content-page" class="content-page">


                <div class="container-fluid">
                    <div class="row">



                        <div class="col-lg-3">
                            <div class="iq-card">
                                <div class="iq-card-header d-flex justify-content-between">
                                    <div class="iq-header-title">
                                        <h4 class="card-title">New OPD</h4>
                                    </div>
                                    
                                </div>




                                      <div class="iq-card-body">
                                        <form action="../../WOP0001_SERV" method="post" >
                                        <input type="hidden" value="1" name="value">
                                        <input type="hidden" value="<%=user_id%>" name="user">
                                        <input type="hidden" value="<%=Hospital_code%>" name="hospital_code" id="hospital_code">
                                        <input type="hidden" value="" name="doc_fees" id="doc_fees">
                                        
                                        <div class="form-group">
                                            <label>Doctor:</label>
                                            <select class="Select2 form-control" id="doc_id_code" name="DOC" onchange="get_doc_fees()">

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


                                </div>
                            </div>
                        </div>
                        <div class="col-lg-9">
                            <div class="iq-card">
                                <div class="iq-card-header d-flex justify-content-between">
                                    <div class="iq-header-title">
                                        <h4 class="card-title" style="white-space: nowrap">OPD Details</h4>
                                    </div>
                                    <div class='col-md-12 row'>
                                        <div class='col-md-7'>
                                            </div>
                                    <div class='col-md-2'>
                                    <label>FREE OPD</label>
                                    </div>
                                    <div class='col-md-1'>
                                        <input type='checkbox' value='FREE' name='' id='chk' onclick="enbale()">
                                        <input type='hidden' value='Yes' name='free' id='free' disabled>
                                            </div> 
                                        </div>
                                    </div>
                                <div class="iq-card-body">
                                    <div class="new-user-info">





                                        <div class="row">
                                            <div class="form-group col-md-2">
                                                <label for="fname">Name:</label>
                                                <input type="text" class="form-control" id="fname" placeholder="Name" name="Patient_name" required="">
                                            </div>




                                            <div class="form-group col-md-2">
                                                <label for="add1">Age:</label>
                                                <input type="number" class="form-control" id="age" placeholder="Age" name="Age" required="" min="0">
                                            </div>
                                            
                                     
                                    <div class="form-group col-md-2">
                                        <label for="mobno">Gender:</label>
                                        <select class="form-control" id="type" name="Sex" onchange="">
                                            <option></option>
                                            <option>Male</option>
                                            <option>Female</option>



                                        </select>
                                    </div> 
                                            
                                            
                                            
                                            

                                            <div class="form-group col-md-2">
                                                <label for="add1">Number:</label>
                                                <input type="number" class="form-control" id="add1" placeholder="Number" name="Patient_number" required="" maxlength="10" min="0">
                                            </div>

                                            <div class="form-group col-md-2">
                                                <label for="cname">Adhar</label>
                                                <input type="text" class="form-control" id="cname" placeholder="Adhar" name="patient_adhar" required="">
                                            </div>

                                            <div class="form-group col-md-2">
                                                <label for="mobno">Address</label>
                                                <input type="text" class="form-control" id="mobno" placeholder="Address" name="Address" required="">
                                            </div>

                                        </div>


                                        <center>  <button type="submit" class="btn btn-primary">Proceed</center>


                                    </div>
                                </div>
                            </div>
                        </div>




                        </form>
                    </div>





                    <div class="row">

                        <div class="col-lg-3">
                            <div class="iq-card">
                                <div class="iq-card-header d-flex justify-content-between">
                                    <div class="iq-header-title">
                                        <h4 class="card-title">Filters</h4>
                                    </div>

                                </div>




                                <div class="iq-card-body">
                                    <div class="form-group col-md-12">
                                        <label for="mobno">Date</label>
                                        <input type="date" class="form-control" id="opd_date" placeholder="OPD Date" name="" onchange="get_opd_rec()">
                                    </div> 

                                    <div class="form-group col-md-12" hidden="">
                                        <label for="mobno">Type</label>
                                        <select class="form-control" id="selectuserrole" name="DOC">
                                            <option></option>
                                            <option>New</option>
                                            <option>Old</option>


                                        </select>
                                    </div> 
                                      
                                    
                                     <div class="form-group col-md-12">
                                      <div class="form-group">
                                            <label>Doctor:</label>
                                            <select class="Select2 form-control" id="doc_code" name="DOC" onchange="get_opd_rec()">
                                                <option></option>

                                                <%
                                                    WOP0001_Dao doc1 = new WOP0001_Dao();
                                                    ArrayList<String> data_doc = doc.getdata("1", Hospital_code);
                                                    for (String cd : data) {
                                                        out.println(cd);
                                                    }
                                                %>
                                                %>
                                            </select>
                                        </div>
                                                </div>






                                </div>
                            </div>
                        </div>
                        <div class="col-lg-9">
                            <div class="iq-card">
                                <div class="iq-card-header d-flex justify-content-between">
                                    <div class="iq-header-title">
                                        <h4 class="card-title">Today's Patients</h4>
                                    </div>
                                    
                                    <div class='col-md-2'>
                                        <button type="button" class='btn btn-outline-info btn-success' data-toggle='modal' data-target='#myModal_follow_up'>Follow Up</button>
                                        </div>
                                </div>
                                <div class="iq-card-body">
                                    <div class="new-user-info">


                                        <table class="table table-bordered table-responsive-md table-striped text-center" id="tab">
                                            <thead>
                                                <tr>
                                                    <th>S.No</th>
                                                    <th>Name</th>
                                                    <th>Mobile Number</th>
                                                    <th>Adhar</th>
                                                    <th>OPD Status</th>
                                                    <th>Doctor Name</th>
                                                    <th>Next Vist</th>
                                                    <th>Action</th>

                                                </tr>
                                            </thead>
                                            <tbody id="tbody">




                                            </tbody>
                                        </table>

                                    </div>
                                </div>
                            </div>
                        </div>




                        </form>

                        <div class="modal fade" id="myModal" role="dialog">
                            <div class="modal-dialog modal-lg" >
                                <form action="../../WOP0001_SERV" method="post" >
                                    <input type="hidden" value="15" name="value">
                                    <input type="hidden" value="<%=user_id%>" name="user">
                                    <input type="hidden" value="<%=Hospital_code%>" name="hospital_code" id="hospital_code">
                                    <!-- Modal content-->




                                    <div class="modal-content">
                                        <div class="modal-header">

                                            <h4 class="modal-title">Assign Procedure</h4>
                                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                                        </div>
                                        <div class="modal-body">

                                            <div class="row">

                                                <div class="col-md-3">
                                                    <lable>Select Procedure</lable>
                                                    <select class="form-control" name="procedure" id="procedure_id">
                                                        <option></option>
                                                        <%
                                                            WOP0001_Dao pro = new WOP0001_Dao();
                                                            ArrayList<String> data_pro = doc.getdata("2", Hospital_code);
                                                            for (String cd : data_pro) {
                                                                out.println(cd);
                                                            }
                                                        %>
                                                        %>
                                                    </select>
                                                </div>

                                                <div class="col-md-3">
                                                    <lable>Add To List</lable>
                                                    <button type="button" class="btn btn-success" onclick="add_to_list();">Add To List</button>
                                                </div>
                                                <input type="text" id="modal_opd" name="patient_id">
                                                <input type="text" id="modal_charge">
                                                <div class="col-md-12">
                                                    <br>
                                                    <div class="table-responsive">
                                                        <table class="table table-bordered table-responsive-md table-striped text-center" id="M_tab">
                                                            <thead>
                                                                <tr>
                                                                    <th style="white-space: nowrap">Procedure Name</th>
                                                                    <th style="white-space: nowrap">Rate</th>
                                                                    <th style="white-space: nowrap">Remove</th>


                                                                </tr>
                                                            </thead>
                                                            <tbody id="prd_sale">




                                                            </tbody>
                                                        </table>
                                                    </div>

                                                </div>

                                                <div class="col-md-12 row">
                                                    <div class="col-md-3">
                                                        <lable>Total</lable>
                                                        <input type="text" class="form-control" readonly="" id="final_bil_amt" name="total_amount">
                                                    </div>

                                                    <div class="col-md-3">
                                                        <lable>Discount</lable>
                                                        <input type="text" class="form-control" id="extra_chr_amt"  onkeyup="final_bill_amount();" name="discount">
                                                    </div>


                                                    <div class="col-md-3">
                                                        <lable>Final Amount</lable>
                                                        <input type="text" class="form-control" id="finalPay_amt" readonly name="final_amount">
                                                    </div>
                                                    <div class="col-md-3">
                                                        <lable>Adjust From Opd</lable>
                                                        <input type="Checkbox" class="form-control" id="adjust" onclick="adjust_from_opd()" name="adjust" value="1">
                                                   
                                                    </div>

                                                </div>
                                                <div class="col-md-12">
                                                    <div id="after_ad"></div>
                                                    <center><button class="btn btn-success">Save</button></center>

                                                </div>






                                            </div>






                                        </div>

                                    </div>

                                </form>

                            </div>


                        </div>     
                                                        
                                                        <!-- for Prescription -->
                                                        
                                                       <div class="modal fade" id="myModal_Procedure" role="dialog">
                            <div class="modal-dialog modal-lg" >
                          
                                    <input type="hidden" value="13" name="value">
                                    <input type="hidden" value="<%=user_id%>" name="user">
                                    <input type="hidden" value="<%=Hospital_code%>" name="hospital_code" id="hospital_code">
                                    <!-- Modal content-->




                                    <div class="modal-content">
                                        <div class="modal-header">

                                            <h4 class="modal-title">Precription Details </h4>
                                                
                                                
                                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                        </div>
                                        <div class="modal-body">

                                           

                                            

                                     <div class='row'>
                                                <div class="col-md-6">
                                                    <br>
                                                    <div class="table-responsive">
                                                        <table class="table table-bordered table-responsive-md table-striped text-center" id="M_tab">
                                                            <thead>
                                                                <tr>
                                                                    <th style="white-space: nowrap">Medicine Name</th>
                                                                    <th style="white-space: nowrap">Dosage</th>
                                                                    <th style="white-space: nowrap">Comments</th>


                                                                </tr>
                                                            </thead>
                                                            <tbody id="Medicine_Name">




                                                            </tbody>
                                                        </table>
                                                    </div>

                                                </div>
                                            
                                            <div class="col-md-6">
                                                    <br>
                                                    <div class="table-responsive">
                                                        <table class="table table-bordered table-responsive-md table-striped text-center" id="M_tab">
                                                            <thead>
                                                                <tr>
                                                                    <th style="white-space: nowrap">Test Name</th>
                                                                    
                                                                    <th style="white-space: nowrap">Comments</th>


                                                                </tr>
                                                            </thead>
                                                            <tbody id="">




                                                            </tbody>
                                                        </table>
                                                    </div>

                                                </div>
</div>
                                                
                                                <div class="col-md-12">
                                                    <div id="after_ad"></div>
                                                    <center><button class="btn btn-success">Print</button></center>

                                                </div>






                                            </div>






                                        </div>

                                    </div>
                                    </div>
                                   
                                    
                                    <!---for follow up patients -->
                                    
                                    <div class="modal fade" id="myModal_follow_up" role="dialog">
                                    <div class="modal-dialog modal-lg" >
                          
                                    <input type="hidden" value="13" name="value">
                                    <input type="hidden" value="<%=user_id%>" name="user">
                                    <input type="hidden" value="<%=Hospital_code%>" name="hospital_code" id="hospital_code">
                                    <!-- Modal content-->




                                    <div class="modal-content">
                                        <div class="modal-header">

                                            <h4 class="modal-title">Follow Up Patients</h4>
                                                
                                                
                                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                        </div>
                                        <div class="modal-body">
                                             
                                            
                                            <div class='col-md-4'>
                                           <div class="form-group">
                                            <label>Doctor:</label>
                                            <select class="Select2 form-control" id="selectuserrole_follow" name="DOC" onchange="follow_up_detail()">
                                                    <option></option>
                                                <%
                                                    WOP0001_Dao doc_follow = new WOP0001_Dao();
                                                    ArrayList<String> data_follow = doc.getdata("1", Hospital_code);
                                                    for (String cd : data) {
                                                        out.println(cd);
                                                    }
                                                %>
                                                %>
                                            </select>
                                        </div>
                                                
                                                </div>
                                                
                                                <div class='colo-md-12'>
                                                    <div class="iq-card-body">
                                    <div class="new-user-info">


                                        <table class="table table-bordered table-responsive-md table-striped text-center" id="tab_1">
                                            <thead>
                                                <tr>
                                                    <th>S.No</th>
                                                    <th>OPD ID</th>
                                                    <th>Name</th>
                                                    <th>Mobile Number</th>
                                                    <th>Last Visit Date </th>
                                                    <th>Next Visit Date</th>
                                                    <th>Action</th>

                                                </tr>
                                            </thead>
                                            <tbody id="tbody_follow_up">


                                            </tbody>
                                        </table>

                                    </div>
                                </div>
                                                    
                                                    </div>

                                            </div>
                                        </div>

                                    </div>
                                
                                
                                
                                
                                
                                
                                
                                
                                
                                
                                
                                
                                
                                
                                

                            </div>
                                                
                                                
                                                
                                                <!--for opd Refund-->
                                                
                                                
                                     <div class="modal fade" id="myModal_refund" role="dialog">
                                    <div class="modal-dialog modal-lg" >
                                   <form action="../../WOP0001_SERV" method="post" >
                                    <input type="hidden" value="20" name="value">
            
                                    <input type="hidden" value="<%=user_id%>" name="user">
                                    <input type="hidden" value="<%=Hospital_code%>" name="hospital_code" id="hospital_code">
                                     
                                     <input type="hidden" name="OPD_REFUND_Patient" value="" id="OPD_REFUND_Patient">
                                    <!-- Modal content-->




                                    <div class="modal-content">
                                        <div class="modal-header">

                                            <h4 class="modal-title">OPD Refund</h4>
                                            <span id="Patient_name_on_opd"></span>
                                            
                                           
                                                
                                                
                                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                        </div>
                                        <div class="modal-body">
                                            
                                             <div class="row">
                                                 <div class="card">
                                                     <div class="col-md-12 row">
                                                         
                                                    <div class="col-md-3">
                                                        <lable>OPD Amount</lable>
                                                        <input type="text" class="form-control" readonly="" id="OPD_AMOUNT" name="OPD_AMOUNT" value="">
                                                    </div>
                                                         
                                                         
                                                         <div class="col-md-4">
                                                        <lable>OPD Amount Refunded</lable>
                                                        <input type="text" class="form-control"  id="AMOUNT_REFUNDED" name="AMOUNT_REFUNDED" onkeyup="Refund_cal()">
                                                    </div>
                                                         
                                                         <div class="col-md-3">
                                                        <lable>Final</lable>
                                                        <input type="text" class="form-control" readonly="" id="FINAL_AMOUNT" name="FINAL_AMOUNT">
                                                    </div>
                                                         
                                                         
                                                          
                                                         <div class="col-md-2">
                                                        <lable>ACTION</lable>
                                                        <button type="submit" class="btn btn-success" type="submit">Refund</button>
                                                    </div>

                                                     </div>
                                                 </div>
                                           
                                            
                                            
                                                    </div>

                                            </div>
                                        </div>

                                    </div>
                                
                
                            </div>


                        </div>     
                                                        
                                                        
                                                        
                                                        
                    </div>

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
            <script src="../Design/All/js/select2.full.min.js"></script>

            <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.js"></script>




            <Script>
                                                            document.getElementById('opd_date').valueAsDate = new Date();

            </script>

            <%
                if (session.getAttribute("Alt_Data") != null) {
                    HashMap<String, String> altMap = (HashMap<String, String>) session.getAttribute("Alt_Data");


            %>

            <script>swal("<%=altMap.get("Titel")%>", "<%=altMap.get("Alt_Msg")%>", "<%=altMap.get("Alt_Type")%>");</script>
            <%
                    session.removeAttribute("Alt_Data");

                }


            %>
            
            <script>
                function enable(){
                }
                </script>
    </body>
</html>

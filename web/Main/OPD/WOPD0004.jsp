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
                                        <label for="mobno">Date</label>ō
                                        <input type="date" class="form-control" id="opd_date" placeholder="OPD Date" name="" onchange="get_opd_rec()">
                                    </div> 

                                    <div class="form-group col-md-12">
                                        <label for="mobno">Type</label>
                                        <select class="form-control" id="selectuserrole" name="DOC">
                                            <option></option>
                                            <option>New</option>
                                            <option>Old</option>


                                        </select>
                                    </div> 

                                    <div class="form-group col-md-12">
                                        <label for="mobno">Name</label>
                                        <select class="form-control input-sm select2" name="na" id="na" >
                                            <option></option>
                                            <option>New</option>
                                            <option>Old</option>


                                        </select>
                                    </div> 
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-9">
                            <div class="iq-card">
                                <div class="iq-card-header d-flex justify-content-between">
                                    <div class="iq-header-title">
                                        <h4 class="card-title">OPD Procedure List</h4>
                                    </div>
                                </div>
                                <div class="iq-card-body">
                                    <div class="new-user-info">


                                        <table class="table table-bordered table-responsive-md table-striped text-center" id="tab">
                                            <thead>
                                                <tr>
                                                    <th>S.No</th>
                                                    <th>Name</th>
                                                    <th style="white-space: nowrap">OPD No</th>
                                                    <th style="white-space: nowrap">Total Rate</th>
                                                    <th>Discount</th>
                                                    <th style="white-space: nowrap">Final Paid Amount</th>
                                                     <th style="white-space: nowrap">Return Amount</th>
                                                     <th style="white-space: nowrap">Collected Amount</th>
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
    </body>
</html>

<%-- 
    Document   : WMAS0004
    Created on : 3 Feb, 2022, 3:00:14 PM
    Author     : Tarun
--%>

<%@page import="Laboratory.WLAB0001_dao"%>
<%@page import="IPD.WIPD0002_Dao"%>
<%@page import="IPD.WIPD0001_Dao"%>
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
        <style>
            /* Chrome, Safari, Edge, Opera */
            input::-webkit-outer-spin-button,
            input::-webkit-inner-spin-button {
                -webkit-appearance: none;
                margin: 0;
            }

            /* Firefox */
            input[type=number] {
                -moz-appearance: textfield;
            }
        </style>

        <%
            String code = request.getParameter("doc_code");


        %>

        <script src="../Js/WREP0001JS.js"></script>
    </head>
    <body onload="()">
        <div class="wrapper">
            <%@include  file="../Master/Header_Menu.jsp" %>
            <div id="content-page" class="content-page">

                <input type="hidden" value="<%=user_id%>" name="user">
                <input type="hidden" value="<%=Hospital_code%>" name="hospital_code" id="hospital_code">            
                <div class="container-fluid">


                    <div class="row">



                        <div class="col-lg-12">
                            <div class="iq-card">
                                <div class="iq-card-header d-flex justify-content-between">
                                    <div class="iq-header-title">
                                        <h4 class="card-title">Filters</h4>
                                    </div>

                                </div>




                                <div class="iq-card-body row">

                                    <div class="form-group col-md-2">
                                        <label for="mobno">Date From</label>
                                        <input type="date" class="form-control" id="date_from" placeholder="OPD Date" name="" onchange="doctor_wise_opd()">
                                    </div> 

                                    <div class="form-group col-md-2">
                                        <label for="mobno">Date To</label>
                                        <input type="date" class="form-control" id="date_to" placeholder="OPD Date" name="" onchange="doctor_wise_opd()">
                                    </div> 

                                   

                                            <div class="form-group col-md-2" >
                                        <label for="mobno">Collection From</label>
                                        <select class="form-control input-sm select2" name="" id="collection_type" onchange="daily_collection_report()">
                                            <option></option>
                                            <option value="OPD">OPD</option>
                                            <option value="IPD">IPD</option>
                                            <option value="LAB">Lab</option>
                                            
                                        </select>
                                    </div> 






                                </div>
                            </div>
                        </div>
                        <div class="col-lg-12">
                            <div class="iq-card">
                                <div class="iq-card-header d-flex justify-content-between">
                                    <div class="iq-header-title">
                                        <h4 class="card-title">Daily Collection Report </h4>
                                    </div>
                                </div>
                                <div class="iq-card-body">
                                    <div class="new-user-info">

                                        <div class="table-responsive">
                                            <table class="table table-bordered table-responsive-md table-striped text-center" id="M_tab">
                                                <thead>
                                                    <tr>
                                                        <th style="white-space: nowrap">S.No</th>
                                                        <th style="white-space: nowrap">Reference No</th>
                                                     
                                                        <th style="white-space: nowrap">Name</th>
                                                        <th style="white-space: nowrap">Amount</th>
                                                        <th style="white-space: nowrap">Date</th>
                                                        



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




                        </form>
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
            <script src="../Design/All/js/select2.full.min.js"></script>


            <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.js"></script>




            <Script>


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

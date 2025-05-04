<%-- 
    Document   : WMAS0004
    Created on : 3 Feb, 2022, 3:00:14 PM
    Author     : Tarun
--%>

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
            String code = request.getParameter("");
        %>

        <script src="../Js/WOP0001JS.js"></script>
    </head>
    <body onload="">
        <div class="wrapper">
            <%@include  file="../Master/Header_Menu.jsp" %>
            <div id="content-page" class="content-page">

                <form action="../../NotificationTestServ" method="post" enctype="multipart/form-data">   
                    <button type="submit" >SUBMIT</button>
                </form>
                
                <form action="../../WIPD0001_SERV" method="post" enctype="multipart/form-data">   
                    <input type="hidden" value="1" name="value">
                    <div class="container-fluid">

                        <div class="row">  


                            <input type="hidden" value="<%=user_id%>" name="user">
                            <input type="hidden" value="<%=Hospital_code%>" name="hospital_code" id="hospital_code">
                            <input type="hidden" value="<%=code%>" name="patient_id" id="hospital_code">

                            <div class="col-md-12 row">
                                <div class="col-lg-6">
                                    <div class="iq-card">
                                        <div class="iq-card-header d-flex justify-content-between">
                                            <div class="iq-header-title">
                                                <h4 class="card-title">Admit Patient</h4>
                                            </div>
                                        </div>
                                        <div class="iq-card-body">
                                            <div class="new-user-info">



                                                <div class=" row">

                                                    <div class="form-group col-md-4">
                                                        <label for="mobno">Addmission Date</label>
                                                        <input type="date" class="form-control" id="adm_date" placeholder="Date" name="date" required="">
                                                    </div>


                                                    <div class="form-group col-md-4">
                                                        <label for="fname">Room Category</label>
                                                        <select class="form-control" name="room_no" id="category_id" onchange="get_room()">

                                                            <option></option>
                                                            <%
                                                                WIPD0001_Dao st_1 = new WIPD0001_Dao();
                                                                String hospital_code[] = new String[5];
                                                                hospital_code[0] = Hospital_code;

                                                                ArrayList<String> data = st_1.getdata("4", Hospital_code);
                                                                for (String cd : data) {
                                                                    out.println(cd);
                                                                }
                                                            %>
                                                            %>  



                                                        </select>
                                                    </div>




                                                    <div class="form-group col-md-4">
                                                        <label for="add1">Room No</label>
                                                        <select class="form-control" name="" id="room_no_id" onchange="get_beds()">
                                                            <option></option>

                                                        </select>
                                                    </div>



                                                    <div class="form-group col-md-4">
                                                        <label for="add1">BED No</label>
                                                        <select class="form-control" name="Bed_number" id="room_bed_id">
                                                            <option></option>

                                                        </select>
                                                    </div>


                                                    <div class="form-group col-md-4">
                                                        <label for="mobno">Refer Doctor</label>
                                                        <input type="text" class="form-control" id="mobno" placeholder="Name" name="Refering_doctor" required="">
                                                    </div>

                                                    <div class="form-group col-md-4">
                                                        <label for="add1">Consult Doctor</label>
                                                        <select class="form-control" name="doctor">
                                                            <option></option>
                                                            <%
                                                                hospital_code[0] = Hospital_code;

                                                                ArrayList<String> data_doc = st_1.getdata("2", hospital_code[0]);
                                                                for (String cd : data_doc) {

                                                                    out.println(cd);

                                                                }


                                                            %>
                                                        </select>
                                                    </div>




                                                    <div class="form-group col-md-4">
                                                        <label for="cname">Type</label>
                                                        <select class="form-control" name="type">
                                                            <option></option><!-- comment -->
                                                            <option>Causility</option>
                                                            <option>Emergency</option>
                                                            <option>Accident</option>
                                                            <option>Other</option>
                                                        </select>
                                                    </div>

                                                    <div class="form-group col-md-4">
                                                        <label for="mobno">Addmission Fees</label>
                                                        <input type="text" class="form-control" id="Admission_Fess" value="0" placeholder="Fees" name="fees">
                                                    </div>

                                                    <div class="form-group col-md-4">
                                                        <label for="mobno">TPA</label>
                                                        <select class="form-control" name="tpa" id="TPA_DETAILS" onchange="get_TPA_Details()">
                                                            <option></option>
                                                            <%                                               hospital_code[0] = Hospital_code;
                                                                ArrayList<String> data_tpa = st_1.getdata("5", Hospital_code);
                                                                for (String cd : data_tpa) {
                                                                    out.println(cd);
                                                                }
                                                            %>
                                                        </select>
                                                    </div>





                                                </div>













                                            </div>
                                        </div>
                                    </div>

                                </div>

                                <div class="col-lg-6">
                                    <div class="iq-card">
                                        <div class="iq-card-header d-flex justify-content-between">
                                            <div class="iq-header-title">
                                                <h4 class="card-title">Patient Details</h4>
                                            </div>

                                            <button class="btn btn-success" type="button" data-toggle="modal" data-target="#myModal">Relative</button>
                                            <button class="btn btn-success" type="button" data-toggle="modal" data-target="#myModal_doc">Documents</button>
                                        </div>
                                        <div class="iq-card-body">
                                            <div class="new-user-info">




                                                <div class="row">
                                                    <div class="form-group col-md-4">
                                                        <label for="fname">Patient Name:</label>
                                                        <input type="text" class="form-control" id="fname" placeholder="Name" name="Patient_name" required="">
                                                    </div>


                                                    <div class="form-group col-md-4">
                                                        <label for="fname">Father Name</label>
                                                        <input type="text" class="form-control" id="fname" placeholder="Father Name" name="Father_Name" required="">
                                                    </div>

                                                    <div class="form-group col-md-4">
                                                        <label for="add1">Age:</label>
                                                        <input type="number" class="form-control" id="age" placeholder="Age" name="Age" required="" min="0">
                                                    </div>

                                                    <div class="form-group col-md-4 ">
                                                        <label for="add1">Gender</label>
                                                        <select class="form-control input-sm" name="gender">
                                                            <option></option>
                                                            <option>Male</option>
                                                            <option>Female</option>
                                                            <option>Trans</option>
                                                        </select>
                                                    </div>

                                                    <div class="form-group col-md-4">
                                                        <label for="cname">Phone</label>
                                                        <input type="Number" class="form-control" id="cname" placeholder="Number" name="phone" required="">
                                                    </div>

                                                    <div class="form-group col-md-4">
                                                        <label for="mobno">Adhar</label>
                                                        <input type="text" class="form-control" id="mobno" placeholder="Address" name="adhar" required="">
                                                    </div>

                                                    <div class="form-group col-md-4">
                                                        <label for="mobno">Address</label>
                                                        <input type="text" class="form-control" id="mobno" placeholder="Address" name="Address" required="">
                                                    </div>


                                                </div>











                                            </div>
                                        </div>
                                    </div>

                                </div>    




                            </div>






                            <div class="modal fade" id="myModal" role="dialog">
                                <div class="modal-dialog modal-lg" >

                                    <!-- Modal content-->


                                    <div class="modal-content">
                                        <div class="modal-header">

                                            <h4 class="modal-title">Relatives Details</h4>
                                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                                        </div>
                                        <div class="modal-body">

                                            <div class="row">

                                                <div class="col-md-3">
                                                    <lable>Relative Name </lable>
                                                    <input type="text" class="form-control input-sm " name="Realative_name" id="" required="" required>
                                                </div>

                                                <div class="col-md-3">
                                                    <lable>Relation </lable>
                                                    <input type="Text" class="form-control input-sm " name="Relation" id="" required="" onkeyup="" required>
                                                </div>


                                                <div class="col-md-3">
                                                    <lable>Mobile Number</lable>
                                                    <input type="Text" class="form-control input-sm " name="Relative_mobile_number" id="" required="" onkeyup="" required>
                                                </div>

                                                <div class="col-md-3">
                                                    <lable>Address</lable>
                                                    <input type="text" class="form-control input-sm " name="Realtive_address" id="" required="" onkeyup="" required>
                                                </div>










                                            </div>






                                        </div>

                                    </div>



                                </div>

                            </div>                       










                            <div class="modal fade" id="myModal_doc" role="dialog">
                                <div class="modal-dialog modal-lg" >

                                    <!-- Modal content-->


                                    <div class="modal-content">
                                        <div class="modal-header">

                                            <h4 class="modal-title">Upload Documents</h4>
                                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                                        </div>
                                        <div class="modal-body">

                                            <div class="row">



                                                <label>Adhar</label>
                                                <div class="form-group">
                                                    <input type="file" id="file_adhar"  name="prd_photo_adhar">

                                                </div>
                                            </div>

                                            <div class="ml-2 col-sm-2">
                                                <img src="../Design/adhar.png"   id="preview" class="img-thumbnail">
                                            </div>

                                            <div class="row">



                                                <label>Patient Photo</label>
                                                <div class="form-group">
                                                    <input type="file" id="file_adhar"  name="prd_photo_patient">

                                                </div>
                                            </div>

                                            <div class="ml-2 col-sm-2">
                                                <img src="../Design/avatr.jpg"   id="preview" class="img-thumbnail">
                                            </div>






                                        </div>






                                    </div>

                                </div>



                            </div>

                        </div>                            








                        <center>  <button type="submit" class="btn btn-primary">Admit</center>

                    </div>



                </form>                   


            </div>


        </div>


        <%@include file='../Design/All/All_js.jsp'%>
        <script src="../Design/All/js/select2.full.min.js"></script>

        <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.js"></script>




        <Script>
                                               document.getElementById('adm_date').valueAsDate = new Date();

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
            $(document).on("click", ".browse", function () {
                var file = $(this).parents().find(".file");
                file.trigger("click");
            });
            $('input[type="file"]').change(function (e) {
                var fileName = e.target.files[0].name;
                $("#file").val(fileName);

                var reader = new FileReader();
                reader.onload = function (e) {
                    // get loaded data and render thumbnail.
                    document.getElementById("preview").src = e.target.result;
                };


                // read the image file as a data URL.
                reader.readAsDataURL(this.files[0]);
            });



        </script>
    </body>
</html>

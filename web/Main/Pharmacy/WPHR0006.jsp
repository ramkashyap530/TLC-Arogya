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

    
        
            <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"/>



        <style>
            @import url('https://fonts.googleapis.com/css2?family=Poppins:wght@200;300;400;500;600;700&display=swap');


            ::selection{
                color: #fff;
                background: #664AFF;
            }

/*            .wrapper_search{
                max-width: 450px;
                margin: 150px auto;
            }*/

            .wrapper_search .search-input{
                background: #fff;
                width: 100%;
                border-radius: 5px;
                position: relative;
                box-shadow: 0px 1px 5px 3px rgba(0,0,0,0.12);
            }

            .search-input input{
                height: 55px;
                width: 100%;
                outline: none;
                border: none;
                border-radius: 5px;
                padding: 0 60px 0 20px;
                font-size: 18px;
                box-shadow: 0px 1px 5px rgba(0,0,0,0.1);
            }

            .search-input.active input{
                border-radius: 5px 5px 0 0;
            }

            .search-input .autocom-box{
                padding: 0;
                opacity: 0;
                pointer-events: none;
                max-height: 280px;
                overflow-y: auto;
            }

            .search-input.active .autocom-box{
                padding: 10px 8px;
                opacity: 1;
                pointer-events: auto;
            }

            .autocom-box li{
                list-style: none;
                padding: 8px 12px;
                display: none;
                width: 100%;
                
                border-radius: 3px;
            }

            .search-input.active .autocom-box li{
                display: block;
            }
            .autocom-box li:hover{
                background: #efefef;
            }

            .search-input .icon{
                position: absolute;
                right: 0px;
                top: 0px;
                height: 55px;
                width: 55px;
                text-align: center;
                line-height: 55px;
                font-size: 20px;
                color: #644bff;
               
            }
        </style>

    </head>
    <div class="wrapper">
        <%@include  file="../Master/Header_Menu.jsp" %>
        <div id="content-page" class="content-page">

            <div class="container-fluid ">
                <form action="../../WPHR0001_SERV" method="post" >
                    <input type="hidden" value="5" name="value">
                    <input type="hidden" value="<%=user_id%>" name="user">
                    <input type="hidden" value="<%=Hospital_code%>" name="hospital_code" id="hospital_code">


                    <div class="col-m-12">

                        <div class="row">
                            <div class="col-lg-12">  
                                <div class="iq-card">
                                    <div class="iq-card-header d-flex justify-content-between">
                                        <div class="iq-header-title">
                                            <h4 class="card-title">Return Master</h4>
                                        </div>


                                    </div>



                                    <div class="iq-card-body">

<div class="row">
             

                                             <div class="form-group col-md-6">
                                        
                                        <div class="wrapper_search">
                                            <div class="search-input">
                                                <a href="" target="_blank" hidden></a>
                                                <input type="text" placeholder="Type to search..">
                                                <div class="autocom-box">
                                                    <!-- here list are inserted from javascript -->
                                                </div>
                                                <div class="icon"><i class="fas fa-search"></i></div>
                                            </div>
                                        </div>
                                                 
                                                 </div>
                                                    
                                                    <div class="form-group col-md-2">
                                                        <input type="text" class="form-control" id="qty" placeholder="Qty" name=""    style=" box-shadow: 0px 1px 5px rgba(0,0,0,0.1);">
                                    </div>

                                    <div class="form-group col-md-2">
                                        <center> <button class="btn btn-sm btn-success" type="button" onclick="add_to_bill();">Add To List</button></center>
                                    </div>

                                 
                                        
                                        
<!--                                        <label for="fname">Item Name</label>-->

<!--                                        <select class='form-control' id="prd_cd" name='prd_cd'>
                                            <option class='form-control' >Select</option>
                                            <%
                                                WPHR0001_Dao doc = new WPHR0001_Dao();
                                                ArrayList<String> data = doc.getdata("1", Hospital_code);
                                                for (String cd : data) {
                                                    out.println(cd);
                                                }
                                            %>


                                        </select>-->
                                    </div>   
                                                
                                                
                                                
                                    </div>

                                                
                                                
                                </div>
                            </div>
                            

                        <div class="col-lg-12">




                            <div class="iq-card">
                                <div class="iq-card-header d-flex justify-content-between">
                                    <div class="iq-header-title">
                                        <h4 class="card-title">Add Medicines/Items</h4>
                                    </div>


                                </div>
                                <div class="iq-card-body">
                                    <table class="table table-bordered table-responsive-md table-striped text-center">
                                        <thead>
                                            <tr>
                                                <th>S.No</th>
                                                <th style="white-space: nowrap">Item Name</th>
                                                <th style="white-space: nowrap">Batch No</th>
                                                <th>HSN</th>
                                                <th>Qty</th>
                                                <th>Unit</th>
                                                <th>Rate(/Pc)</th>
                                                <th>Total</th>
                                                <th>Action</th>


                                            </tr>
                                        </thead>
                                        <tbody id="prd_sale">




                                        </tbody>
                                    </table>
                                </div>


                                <div class="row" hidden="">
                                    <div class="col-md-6">
                                    </div>
                                    <div class="col-md-6">
                                        <div class="row">
                                            <div class="col-md-2">

                                            </div>
                                            <div class="col-md-5">
                                                <b>Total Bill Amount</b>
                                            </div>
                                            <div class="col-md-1">
                                                <b>:</b>
                                            </div>
                                            <div class="col-md-3">
                                                <input type="text" class="form-control form-control-sm" id="final_bil_amt" name='bill_amt' readonly="" value="0">
                                            </div>

                                        </div>
                                        <br>

                                        <div class="row">
                                            <div class="col-md-2">

                                            </div>
                                            <div class="col-md-5">
                                                <b>Extra Charges</b>
                                            </div>
                                            <div class="col-md-1">
                                                <b>:</b>
                                            </div>
                                            <div class="col-md-3">
                                                <input type="text"  id="extra_chr_amt"  onkeyup="final_bill_amount();"   value="0"  name='extra_charge'  class="form-control form-control-sm">
                                            </div>

                                        </div>
                                        <br>



                                        <div class="row">
                                            <div class="col-md-2">

                                            </div>
                                            <div class="col-md-5">
                                                <b>Final Amount </b>
                                            </div>
                                            <div class="col-md-1">
                                                <b>:</b>
                                            </div>
                                            <div class="col-md-3">
                                                <input type="text" id="finalPay_amt" value="0"  name='final_bill_amt'  class="form-control form-control-sm" readonly="" >
                                            </div>

                                        </div>
                                        <br>



                                    </div>
                                </div>
                                <div class="col-md-12">
                                    <center><button class="btn btn-sm btn-primary">Return</button></center>
                                </div>
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

<script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.js"></script>

<%
    if (session.getAttribute("Alt_Data") != null) {
        HashMap<String, String> altMap = (HashMap<String, String>) session.getAttribute("Alt_Data");


%>

<script>swal("<%=altMap.get("Titel")%>", "<%=altMap.get("Alt_Msg")%>", "<%=altMap.get("Alt_Type")%>");</script>
<%
        session.removeAttribute("Alt_Data");

    }


%>


    <script src="../Js/WPHR0001.js"></script>

</body>
</html>

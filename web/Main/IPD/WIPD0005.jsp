<%-- 
    Document   : WIPD0005
    Created on : 03-Apr-2022, 12:32:10 am
    Author     : Ram
--%>

<%@page import="Laboratory.WLAB0001_dao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <%@include file="../Design/All/All_css.jsp" %>
      <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css">
      <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>

      <script src="../Js/WIPD0005JS.js"></script>
      <style>.error {
  position: relative;
  animation: shake .1s linear;
  animation-iteration-count: 3;
  border: 1px solid red;
}

@keyframes shake {
  0% {
    left: -5px;
  }
  100% {
    right: -5px;
  }
}</style>
   </head>
   <body>
      <div class="wrapper">
         <%@include  file="../Master/Header_Menu.jsp" %>
         <div id="content-page" class="content-page">


            <div class="container-fluid">
               <form action="../../WIPD0005_serv" method="post" id="form1">  

                  <input type="hidden" name="value" value="2">
                  <input type="hidden" name="user_id" value="<%=user_id%>">
                  <input type="hidden" name="company_code" value="<%=Hospital_code%>">
                  <div class="row">
                     <div class="col-lg-2">
                        <div class="iq-card">
                           <div class="iq-card-header d-flex justify-content-between">
                              <div class="iq-header-title">
                                 <h4 class="card-title">Room Payment</h4>
                              </div>

                           </div>
                           <div class="iq-card-body">

                              <div class="form-group" id="opd_patient" hidden="">
                                 <label>Name</label>
                                 <input type="text" class="form-control"  id="Name_of_test_patient"  name="Name_of_test_patient">

                              </div>

                              <div class="form-group" id="ipd_patient">
                                 <label>Patient Name</label>
                                 <select class="form-control" id="patient_id" name="patient_id" required onchange="get_RoomData();get_RoomPaymentData();">
                                    <option>Select</option>
                                    <%
                                       WLAB0001_dao st_1 = new WLAB0001_dao();
                                       ArrayList<String> data = st_1.getdata("1", "");
                                       for (String cd : data) {
                                          out.println(cd);
                                       }
                                    %>
                                 </select>

                              </div>

                                 <center> <button type="submit" class='btn btn-sm btn-success'>Submit Payment</button></center>


                           </div>
                        </div>
                     </div>
                     <div class="col-lg-10">
                        <div class="iq-card">
                           <div class="iq-card-header d-flex justify-content-between">
                              <div class="iq-header-title">
                                 <h4 class="card-title">Auto Pay<input  type="test" name="auto_paid" id="auto_paid" placeholder="Auto Paid" value="0" onkeyup="autoPaid(this.value);"></h4>
                              </div>
                           </div>
                           <div class="iq-card-body">
                              <div class="new-user-info">



                                 <div class="table-responsive">
                                    <table class="table table-bordered table-responsive-md table-striped text-center">
                                       <thead>
                                          <tr>


                                             <th><input type="checkbox"></th>
                                             <th>Patient Name</th>
                                             <th>Room Name</th>
                                             <th>Start Date to End Date</th>
                                             <th>No Of Days</th>
                                             <th>Room Amt</th>
                                             <th>Paid Amt</th>
                                             <th>Pending Amt</th>
                                             <th>Pay Amt.</th>
                                             <th hidden >Payment ID.</th>

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
                                 <div class="col-lg-2"></div>
                     <div class="col-lg-10">
                        <div class="iq-card">
                           <div class="iq-card-header d-flex justify-content-between">
                              <div class="iq-header-title">
                                 <h4 class="card-title">Paid Payment List</h4>
                              </div>
                           </div>
                           <div class="iq-card-body">
                              <div class="new-user-info">



                                 <div class="table-responsive">
                                    <table class="table table-bordered table-responsive-md table-striped text-center">
                                       <thead>
                                          <tr>


                                             <th>Sno.</th>
                                             <th>Patient id</th>
                                             <th>Payment ID</th>
                                             <th>Payment Amt</th>
                                             <th>Payment Date</th>
                                          </tr>
                                       </thead>
                                       <tbody id="tbody_payment">




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



         </div>

      </div>


   </div>


   <%@include file='../Design/All/All_js.jsp'%>
   <script src="../../Design/app-assets/js/scripts/forms/select/form-select2.min.js"></script>

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


   <script>
      $(function () {
         var dtToday = new Date();

         var month = dtToday.getMonth() + 1;
         var day = dtToday.getDate();
         var year = dtToday.getFullYear();
         if (month < 10)
            month = '0' + month.toString();
         if (day < 10)
            day = '0' + day.toString();

         var maxDate = year + '-' + month + '-' + day;

         $('#txtDate').attr('max', maxDate);
      });




   </script>



</body>
</html>

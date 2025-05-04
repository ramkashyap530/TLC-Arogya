<%-- 
    Document   : WOC0002
    Created on : 09-Apr-2022, 6:15:39 pm
    Author     : Ram
--%>

<%@page import="OC.WOC0001_Dao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <%@include file="../Design/All/All_css.jsp" %>
      <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css">
      <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>

      <script src="../Js/WOC0001JS.js"></script>
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
               <form action="../../WOC0001_Serv" method="post" id="form1">  

                  <input type="hidden" name="value" value="2">
                  <input type="hidden" name="user" value="<%=user_id%>">
                  <input type="hidden" name="company_code" value="<%=Hospital_code%>">
                  <div class="row">
                     <div class="col-lg-3">
                        <div class="iq-card">
                           <div class="iq-card-header d-flex justify-content-between">
                              <div class="iq-header-title">
                                 <h4 class="card-title">Assign Other Charge</h4>
                              </div>

                           </div>
                           <div class="iq-card-body">


                              <div class="form-group">
                                 <label>Type</label>
                                 <select class="form-control" id="type" name="type" 
                                         onchange="type_change();">
                                    <option>Select</option>
                                    <option>OPD</option>
                                    <option>IPD</option>

                                 </select>

                              </div>


                              <div class="form-group" id="ipd_patient">
                                 <label>Patient Name</label>
                                 <select class="form-control" id="patient_id" name="patient_id" required onchange="get_oc_assign();">
                                    <option>Select</option>
                                    <%
                                       WOC0001_Dao st_1 = new WOC0001_Dao();
                                       ArrayList<String> data = st_1.getdata("1", "");
                                       for (String cd : data) {
                                          out.println(cd);
                                       }
                                    %>
                                 </select>
                                
                              </div>
                                 
                                 
                                 <!--for other charge Category -->
                                 
                                 <div class="form-group" id="ipd_patient">
                                 <label>Charge Category  Name</label>
                                 <select class="form-control" id="charge_category_id" name="charge_category_id" required onchange="get_oc_assign();">
                                    <option>Select</option>
                                    <%
                                       WOC0001_Dao st_2 = new WOC0001_Dao();
                                       ArrayList<String> data_2 = st_2.getdata("3", "");
                                       for (String cd : data_2) {
                                          out.println(cd);
                                       }
                                    %>
                                 </select>
                                
                              </div>
                                 
                                 
                                 
                                 <div class="form-group" id="opd_patient" >
                                 <label>Patient Name</label>
                                 <select class="form-control" id="patient_id_opd" name="patient_id" required onchange="get_oc_assign();">
                                    <option>Select</option>
                                    <%
                                       WOC0001_Dao st_3 = new WOC0001_Dao();
                                       ArrayList<String> data_opd = st_3.getdata("2", "");
                                       for (String cd : data_opd) {
                                          out.println(cd);
                                       }
                                    %>
                                 </select>

                              </div>

                              <center> <button class='btn btn-sm btn-success'>Add</button></center>


                           </div>
                        </div>
                     </div>
                     <div class="col-lg-8">
                        <div class="iq-card">
                           <div class="iq-card-header d-flex justify-content-between">
                              <div class="iq-header-title">
                                 <h4 class="card-title">Auto Pay<input type="test" name="auto_paid" id="auto_paid" placeholder="Auto Paid" value="0" onkeyup="autoPaid(this.value);"></h4>
                              </div>
                           </div>
                           <div class="iq-card-body">
                              <div class="new-user-info">



                                 <div class="table-responsive">
                                    <table class="table table-bordered table-responsive-md table-striped text-center">
                                       <thead>
                                          <tr>


                                             <th><input type="checkbox"></th>
                                             <th>Charge Name</th>
                                             <th>Charge Rate</th>
                                             <th>Qty</th>
                                             <th>Submit Amt</th>

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

                                 <div class="col-lg-4" hidden="">
                        <div class="iq-card">
                           <div class="iq-card-header d-flex justify-content-between">
                              <div class="iq-header-title">
                                 <h4 class="card-title">Selected Test <span id="tot_test_amt">0</span> Rs.</h4>
                              </div>
                              <button class="btn btn-success "  type="button" data-toggle="modal" data-target="#exampleModalCenter" onclick="get_lab_patient_id(); return;"  >Send For Testing</button>
                           </div>
                           <div class="iq-card-body" id="test_t">


                           </div>

                        </div>

                     </div>                   



                     <div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">



                        <div class="modal-dialog modal-dialog-centered" role="document">
                           <div class="modal-content">
                              <div class="modal-header">
                                 <h5 class="modal-title" id="exampleModalCenterTitle">Test Total Amount</h5>
                                 <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                 </button>
                              </div>
                              <div class="modal-body">
                                 <input type="text" readonly name="patient_id_mod" id="patient_id_mod">
                                 <div class="row">
                                    <div class="col-md-4">
                                       <label>Total  Amount </label>
                                       <input type="text" class="form-control"  id="Test_Tot_amt" placeholder="0" value='0' name="Total_Amount" readonly>
                                    </div>



                                    <div class="col-md-4">
                                       <label>Amount Paying</label>
                                       <input type="text" class="form-control" id="Test_paid_amt"  placeholder="0" value='0' name="Amount_paid" readonly>
                                    </div>

                                    <div class="col-md-4">
                                       <label>Pending</label>
                                       <input type="text" class="form-control" id="Test_pending_amt"  placeholder="0" value='0' name="Amount_pending" readonly>
                                    </div>


                                    <div class="col-md-12">
                                       <label></label>
                                       <center><button type="button" data-dismiss="modal" class="btn btn-sm btn-success mt-4">Pay</button></center>
                                    </div>

                                 </div>

                              </div>
                              <div class="modal-footer">
                                 <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>

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


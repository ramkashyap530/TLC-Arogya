<%-- 
    Document   : 404_Error
    Created on : 28 Mar, 2022, 9:02:03 PM
    Author     : Tarun
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<head>
      <!-- Required meta tags -->
      <meta charset="utf-8">
      <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
      <title>Page Not Found !!</title>
      <!-- Favicon -->
      <%@include file="../Design/All/All_css.jsp"%>
   </head>
   <body>
      <!-- loader Start -->
      <div id="loading">
         <div id="loading-center">
         </div>
      </div>
      <!-- loader END -->
        <!-- Wrapper Start -->
            <div class="container-fluid p-0">
                <div class="row no-gutters">
                    <div class="col-sm-12 text-center">
                        <div class="iq-error">
                          <h1 class="text-primary">404</h1>
                            <!-- <img src="images/error/01.png" class="img-fluid iq-error-img" alt=""> -->
                            <h2 class="mb-0">Oops! This Page is Not Found.</h2>
                            <p>The requested page dose not exist.</p>
                            <a class="btn btn-primary mt-3" href="../Dashboard/WDASH0001.jsp"><i class="ri-home-4-line"></i>Back to Home</a>                            
                        </div>
                    </div>
                </div>
            </div>
            <%@include file="../Design/All/All_js.jsp"%>
   </body>


</html>
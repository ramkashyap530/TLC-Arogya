<%-- 
    Document   : Login
    Created on : 2 Feb, 2022, 1:49:09 PM
    Author     : Tarun
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Cookie[] cookies = request.getCookies();
    String userName = "", password = "", rememberVal = "";
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("cookuser")) {
                userName = cookie.getValue().trim().replace("$%^&~~&^%$"," ");
            }
            if (cookie.getName().equals("cookpass")) {
                password = cookie.getValue().trim().replace("$%^&~~&^%$"," ");
            }
            if (cookie.getName().equals("cookrem")) {
                rememberVal = cookie.getValue();
            }
        }
    }
%>

<!doctype html>
<html lang="en">
   
<!-- Mirrored from templates.iqonic.design/xray/html/sign-in.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 02 Feb 2022 07:13:39 GMT -->
<head>
      <!-- Required meta tags -->
      <meta charset="utf-8">
      <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
      <title>Welcome To Arogya(Hospital Ka Jevansaathi)</title>
      <!-- Favicon -->
      <link rel="shortcut icon" href="../Design/All/images/Arogya.png" />
      <!-- Bootstrap CSS -->
      <link rel="stylesheet" href="../Design/All/css/bootstrap.min.css">
      <!-- Typography CSS -->
      <link rel="stylesheet" href="../Design/All/css/typography.css">
      <!-- Style CSS -->O
      <link rel="stylesheet" href="../Design/All/css/style.css">
      <!-- Responsive CSS -->
      <link rel="stylesheet" href="../Design/All/css/responsive.css">
      
      <style>
          /* From 0 to 992px Device Width */
@media (max-width: 992px)  {

   #for-mobile {
       display: none;
   
   }

}
          </style>
      
   </head>
   <body>
      <!-- loader Start -->
      <div id="loading">
         <div id="loading-center">
         </div>
      </div>
      <!-- loader END -->
        <!-- Sign in Start -->
        <section class="sign-in-page">
            <div class="container sign-in-page-bg mt-5 p-0">
                <div class="row no-gutters">
                    <div class="col-md-6 text-center " id="for-mobile">
                        <div class="sign-in-detail text-white">
<!--                            <a class="sign-in-logo mb-5" href="#"><img src="../Design/All/images/Arogya.png" class="img-fluid" alt="logo"></a>-->
                            <div class="owl-carousel" data-autoplay="true" data-loop="true" data-nav="false" data-dots="true" data-items="1" data-items-laptop="1" data-items-tab="1" data-items-mobile="1" data-items-mobile-sm="1" data-margin="0">
                                <div class="item">
                                    <img src="../Design/All/images/login/1.png" class="img-fluid mb-4" alt="logo">
                                    <h4 class="mb-1 text-white">Manage your orders</h4>
                                    <p>It is a long established fact that a reader will be distracted by the readable content.</p>
                                </div>
                                <div class="item">
                                    <img src="../Design/All/images/login/2.png" class="img-fluid mb-4" alt="logo">
                                    <h4 class="mb-1 text-white">Manage your orders</h4>
                                    <p>It is a long established fact that a reader will be distracted by the readable content.</p>
                                </div>
                                <div class="item">
                                    <img src="../Design/All/images/login/3.png" class="img-fluid mb-4" alt="logo">
                                    <h4 class="mb-1 text-white">Manage your orders</h4>
                                    <p>It is a long established fact that a reader will be distracted by the readable content.</p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6 position-relative">
                        <div class="sign-in-from">
                            <a class="sign-in-logo" href="#"><img src="../Design/All/images/Arogya.png" class="img-fluid" alt="logo">
                            <p>Enter your UserName and Password To Access Arogya</p>
                            <form  action="../../WLOGIN_SERV" method="post">
                                 <input type="hidden" value="1" name="val" id="var"> 
                                <div class="form-group">
                                    <label for="exampleInputEmail1">UserName</label>
                                    <input type="test" class="form-control mb-0" id="exampleInputEmail1"  value="<%=userName%>"  placeholder="Enter Username" name="username">
                                </div>
                                <div class="form-group">
                                    <label for="exampleInputPassword1">Password</label>
                                  
                                    <input type="password" class="form-control mb-0" id="exampleInputPassword1" placeholder="Password"  value="<%=password%>"  name="password">
                                </div>
                                <div class="d-inline-block w-100">
                                    <div class="custom-control custom-checkbox d-inline-block mt-2 pt-1">
                                       <input type="checkbox"   id="remember" name="remember" value="yes" <%= "yes".equals(rememberVal.trim()) ? "checked=\"checked\"" : ""%> >
                                       
                                    </div>
                                    <button type="button" class="btn btn-primary float-right" onclick="login()">Sign in</button>
                                </div>
                                <div class="sign-info">
<!--                                    <span class="dark-color d-inline-block line-height-2">Don't have an account? <a href="#">Sign up</a></span>-->
<!--                                    <ul class="iq-social-media">
                                        <li><a href="#"><i class="ri-facebook-box-line"></i></a></li>
                                        <li><a href="#"><i class="ri-twitter-line"></i></a></li>
                                        <li><a href="#"><i class="ri-instagram-line"></i></a></li>
                                    </ul>-->
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- Sign in END -->
      <!-- Optional JavaScript -->
      <!-- jQuery first, then Popper.js, then Bootstrap JS -->
      <script src="../Design/All/js/jquery.min.js"></script>
      <script src="../Design/All/js/popper.min.js"></script>
      <script src="../Design/All/js/bootstrap.min.js"></script>
      <!-- Appear JavaScript -->
      <script src="../Design/All/js/jquery.appear.js"></script>
      <!-- Countdown JavaScript -->
      <script src="../Design/All/js/countdown.min.js"></script>
      <!-- Counterup JavaScript -->
      <script src="../Design/All/js/waypoints.min.js"></script>
      <script src="../Design/All/js/jquery.counterup.min.js"></script>
      <!-- Wow JavaScript -->
      <script src="../Design/All/js/wow.min.js"></script>
      <!-- Apexcharts JavaScript -->
      <script src="../Design/All/js/apexcharts.js"></script>
      <!-- Slick JavaScript -->
      <script src="../Design/All/js/slick.min.js"></script>
      <!-- Select2 JavaScript -->
      <script src="../Design/All/js/select2.min.js"></script>
      <!-- Owl Carousel JavaScript -->
      <script src="../Design/All/js/owl.carousel.min.js"></script>
      <!-- Magnific Popup JavaScript -->
      <script src="../Design/All/js/jquery.magnific-popup.min.js"></script>
      <!-- Smooth Scrollbar JavaScript -->
      <script src="../Design/All/js/smooth-scrollbar.js"></script>
      <!-- Chart Custom JavaScript -->
      <script src="../Design/All/js/chart-custom.js"></script>
      <!-- Custom JavaScript -->
      <script src="../Design/All/js/custom.js"></script>
      
      
      <script>
        
        
 function login()
 
  {
 
 
      var username=document.getElementById("exampleInputEmail1").value;
      var password=document.getElementById("exampleInputPassword1").value;
      var remember=document.getElementById("remember").value;
      
      var http = new XMLHttpRequest();
        var url = '../../WLOGIN_SERV';
       
        var params = 'val=1&username='+username+'&password='+password+'&remember='+remember;
        http.open('POST', url, true);
         

        //Send the proper header information along with the request
        http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');

        http.onreadystatechange = function() {//Call a function when the state changes.
            if(http.readyState === 4 && http.status === 200) {
  
                          
                  
                if(http.responseText.trim()==="success"){
                    
                   
                    window.location="../Dashboard/WDASH0001.jsp";
                }else{
                    alert("Wrong User Name PassWord");
                }
                
                
//            document.getElementById("tbody").innerHTML=;
               
            
        }
        };
        http.send(params);
  
  }


        
        </script>
      
      
      
   </body>

<!-- Mirrored from templates.iqonic.design/xray/html/sign-in.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 02 Feb 2022 07:13:40 GMT -->
</html>
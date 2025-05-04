


<%@page import="notification.NotificationWebSocket"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Master.WMAS0001_Dao"%>
<%@page import="java.util.HashMap"%>
<%
    
        String Real_Name="";
        String Role="";
        String Hospital_code="";
        String user_image="";
        String sts="";
        String role_name="";
        String user_id="";
        String hospital_name="";
        String address="";
   
          if(session.getAttribute("loginInfo")!=null)
    {
      HashMap<String, String> loginInfo_1=(HashMap<String, String>)session.getAttribute("loginInfo");
        
      if(loginInfo_1.get("msg").equals("success"))
        {
          
        Real_Name=loginInfo_1.get("Real_Name");
        Role=loginInfo_1.get("role_id");
        Hospital_code=loginInfo_1.get("hospital_code");
        user_image=loginInfo_1.get("user_image");
        sts=loginInfo_1.get("sts");
        role_name=loginInfo_1.get("role_name");
        user_id=loginInfo_1.get("user_id");
        hospital_name=loginInfo_1.get("hospital_name");
        address=loginInfo_1.get("address");
       
        
    }
       else{
       RequestDispatcher rd = request.getRequestDispatcher("../Main/login/Login.jsp");
       rd.forward(request, response);
         }

    }else{
       RequestDispatcher rd = request.getRequestDispatcher("../Main/login/Login.jsp");
       rd.forward(request, response);
         }
    
    %>    
    
<style>
    .btn-group{
        top:36px;
    }
    </style>

<div id="loading">
         <div id="loading-center">
         </div>
      </div>
<div class="iq-sidebar">
            <div class="iq-sidebar-logo d-flex justify-content-between">
               <a href="index.html">
                <img src="../Design/All/images/newlogo.png" class="img-fluid" alt="" >
               <span>AAROGYA</span>
               </a>
               <div class="iq-menu-bt-sidebar">
                     <div class="iq-menu-bt align-self-center">
                        <div class="wrapper-menu">
                           <div class="main-circle"><i class="ri-more-fill"></i></div>
                           <div class="hover-circle"><i class="ri-more-2-fill"></i></div>
                        </div>
                     </div>
                  </div>
            </div>
            <div id="sidebar-scrollbar">
               <nav class="iq-sidebar-menu">
                  <ul id="iq-sidebar-toggle" class="iq-menu">
                     
                                          <li class="iq-menu-title"><i class="ri-subtract-line"></i><span>All Apps</span></li>
                     
                     
                     <%
                String id = "";
                WMAS0001_Dao st = new WMAS0001_Dao();
             

                ArrayList<HashMap<String, String>> al = new ArrayList<HashMap<String, String>>();
                al = new WMAS0001_Dao().getAllMainMenu(user_id);
                for (HashMap<String, String> map : al) {
            %>
                     
                   
                     <li>
                         
                         <a href="#mailbox_<%=map.get("modulnm")%>" class="iq-waves-effect collapsed" data-toggle="collapse" aria-expanded="false"><i class="ri-mail-open-fill"></i><span><%=map.get("modulnm")%></span><i class="ri-arrow-right-s-line iq-arrow-right"></i></a>
                                             

                       
                        <ul id="mailbox_<%=map.get("modulnm")%>" class="iq-submenu collapse" data-parent="#iq-sidebar-toggle">
                            <%
                        ArrayList<HashMap<String, String>> al2 = new ArrayList<HashMap<String, String>>();
                        al2 = new WMAS0001_Dao().getAllSubMenuFOrMdID(user_id, map.get("mdid"));
                        for (HashMap<String, String> map2 : al2) {
                    %>
                            <li><a href="<%=map2.get("suburl")%>"><i class="ri-inbox-fill"></i><%=map2.get("submodnm")%></a></li>
                           <%}%>
                        </ul>
                  
                      <%}%>
                    
                       </li>
                     
                    
                     
                    
                  </ul>
               </nav>
               <div class="p-3"></div>
            </div>
         </div>




<!--for the top nave--> 
<div class="iq-top-navbar">
            <div class="iq-navbar-custom">
               <div class="iq-sidebar-logo">
                  <div class="top-logo">
                     <a href="index.html" class="logo">
                   
                     <span></span>
                     </a>
                  </div>
               </div>
               <nav class="navbar navbar-expand-lg navbar-light p-0">
                  <div class="iq-search-bar">
                      <img src="../Design/All/images/newlogo.png" class="img-fluid" alt="" style="width: 73px;">
                    
                  </div>
                  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                  <i class="ri-menu-3-line"></i>
                  </button>
                  <div class="iq-menu-bt align-self-center">
                     <div class="wrapper-menu">
                        <div class="main-circle"><i class="ri-more-fill"></i></div>
                           <div class="hover-circle"><i class="ri-more-2-fill"></i></div>
                     </div>
                  </div>
                   
                   <div class="collapse navbar-collapse" id="navbarSupportedContent">
                     <ul class="navbar-nav ml-auto navbar-list">
                        
                        <li class="">
                            <button type="click" onclick="sendNotification();" class="button" > NOTIFICATION </button>
                        </li>
                        
                        
                     </ul>
                  </div>
                  <div class="collapse navbar-collapse" id="navbarSupportedContent">
                     <ul class="navbar-nav ml-auto navbar-list">
                        
                        <li class="nav-item iq-full-screen">
                           <a href="#" class="iq-waves-effect" id="btnFullscreen"><i class="ri-fullscreen-line"></i></a>
                        </li>
                        <li class="nav-item">
                           <a href="#" class="search-toggle iq-waves-effect">
                                 <i class="ri-notification-3-fill"></i>
                                 <span class="bg-danger dots"></span>
                              </a>
                           <div class="iq-sub-dropdown">
                              <div class="iq-card shadow-none m-0">
                                 <div class="iq-card-body p-0 ">
                                    <div class="bg-primary p-3">
                                       <h5 class="mb-0 text-white" id="notification" style="display: none;">All Notifications<small class="badge  badge-light float-right pt-1">4</small></h5>
                                    
                                    </div>


                                    
                                    
                                 </div>
                              </div>
                           </div>
                        </li>
                        
                     </ul>
                  </div>
                  <ul class="navbar-list">
                     <li>
                        <a href="#" class="search-toggle iq-waves-effect d-flex align-items-center">
<!--                           <img src="images/user/1.jpg" class="img-fluid rounded mr-3" alt="user">-->
                           <div class="caption">
<!--                              <h6 class="mb-0 line-height"><%=Real_Name%></h6>-->
                               
                              <%
                                  if(Role.equals("1")){
                               
                              %>
                             <span class="font-size-12"><%=role_name%></span>
                                  <%
                                      }
else{
                              %>
                              <span class="font-size-12"><%=Real_Name%></span>
                              <%
                                  }
                              %>
                           </div>
                        </a>
                        <div class="iq-sub-dropdown iq-user-dropdown">
                           <div class="iq-card shadow-none m-0">
                              <div class="iq-card-body p-0 ">
                                 <div class="bg-primary p-3">
                                    <h5 class="mb-0 text-white line-height">Hello <%=Real_Name%></h5>
                                    <span class="text-white font-size-12"><%=role_name%></span>
                                 </div>
                                 <a href="profile.html" class="iq-sub-card iq-bg-primary-hover">
                                    <div class="media align-items-center">
                                       <div class="rounded iq-card-icon iq-bg-primary">
                                          <i class="ri-file-user-line"></i>
                                       </div>
                                       <div class="media-body ml-3">
                                          <h6 class="mb-0 ">My Profile</h6>
                                          <p class="mb-0 font-size-12">View personal profile details.</p>
                                       </div>
                                    </div>
                                 </a>
                                 <a href="../../Main/Master/WMAS0001.jsp" class="iq-sub-card iq-bg-primary-hover">
                                    <div class="media align-items-center">
                                       <div class="rounded iq-card-icon iq-bg-primary">
                                          <i class="ri-profile-line"></i>
                                       </div>
                                       <div class="media-body ml-3">
                                          <h6 class="mb-0 " >Module setings</h6>
                                          <p class="mb-0 font-size-12">Assign Module and Sub Module.</p>
                                       </div>
                                    </div>
                                 </a>
                                 <a href="" class="iq-sub-card iq-bg-primary-hover">
                                    <div class="media align-items-center">
                                       <div class="rounded iq-card-icon iq-bg-primary">
                                          <i class="ri-account-box-line"></i>
                                       </div>
                                       <div class="media-body ml-3">
                                          <h6 class="mb-0 ">Account settings</h6>
                                          <p class="mb-0 font-size-12">Manage your Software Users .</p>
                                       </div>
                                    </div>
                                 </a>
                                 <a href="privacy-setting.html" class="iq-sub-card iq-bg-primary-hover">
                                    <div class="media align-items-center">
                                       <div class="rounded iq-card-icon iq-bg-primary">
                                          <i class="ri-lock-line"></i>
                                       </div>
                                       <div class="media-body ml-3">
                                          <h6 class="mb-0 ">Privacy Settings</h6>
                                          <p class="mb-0 font-size-12">Control your privacy parameters.</p>
                                       </div>
                                    </div>
                                 </a>
                                 <div class="d-inline-block w-100 text-center p-3">
                                    <a class="bg-primary iq-sign-btn" href="../../WLOGIN_SERV?val=2" role="button">Sign out<i class="ri-login-box-line ml-2"></i></a>
                                 </div>
                              </div>
                           </div>
                        </div>
                     </li>
                  </ul>
               </nav>

            </div>
         </div>
                                 
                                  <script>

                                  function sendNotification() {
                                      alert();
                                      // Dummy notification data
                                      var notification = {
                                          notificationId: 0, // or null if you want to omit for new notifications
                                          code: "TEST_CODE",
                                          notificationType: "Non-Action", // or "Action" as needed
                                          notificationMessage: "This is a dummy notification for testing.",
                                          notificationFrom: "Test Sender",
                                          notificationCode: "DUMMY123",
                                          redirectUrl: "/some/path",
                                          isRead: false,
                                          role: "Admin",
                                          createdAt: new Date().toISOString() // or null if backend sets this
                                      };

                                      // Send POST request to your backend endpoint
                                         fetch('<%= request.getContextPath() %>/NotificationTestServlate', {
                                             method: 'POST',
                                             headers: {
                                                 'Content-Type': 'application/json'
                                             },
                                             body: JSON.stringify(notification)
                                         })
                                      .then(response => {
                                          if (response.ok) {
                                              alert("Dummy notification sent!");
                                          } else {
                                              alert("Failed to send notification."+response);
                                          }
                                      })
                                      .catch(error => {
                                          alert("Error: " + error);
                                      });
                                  }

    var userRole = "<%= session.getAttribute("userRole") %>"; // Admin role check karein
        var userId = "<%= session.getAttribute("user_id") %>";     // Get userId from session


        if (userRole === "Admin" && userId) {
            // Dynamically construct the WebSocket URL
            var protocol = window.location.protocol === "https:" ? "wss://" : "ws://";
            var host = window.location.hostname;
            var port = window.location.port ? (":" + window.location.port) : "";
            var contextPath = "<%= request.getContextPath() %>";
            // WebSocket endpoint with userId
            var wsUrl = protocol + host + port + contextPath + "/ws/notifications/" + encodeURIComponent(userId);
                console.log("wsUrl: "+wsUrl);
                alert(wsUrl);
            var socket = new WebSocket(wsUrl);

            socket.onmessage = function(event) {
                var notificationDiv = document.getElementById("notification");
                notificationDiv.innerHTML = "<b>New Notification:</b> " + event.data;
                notificationDiv.style.display = "block";

                // Sound play karein
                var audio = new Audio("../Design/ringtone-193209.mp3");
                audio.play();
            };

            socket.onopen = function() {
                console.log("WebSocket connected");
            };

            socket.onclose = function() {
                console.log("WebSocket disconnected");
            };
        }
</script>

<div id="notification" style="display: none; position: fixed; top: 10px; right: 10px; background: yellow; padding: 10px;"></div>

                                    
                                
<!--ends here--> 
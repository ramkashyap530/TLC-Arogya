/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */





function get_groups(){

       var http = new XMLHttpRequest();
     var url = '../../WACCM0001_SERV';
        var params = 'value=1';
        http.open('POST', url, true);

 
        http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
 http.onreadystatechange = function() {//Call a function when the state changes.
            if(http.readyState === 4 && http.status === 200) {
            document.getElementById("groups").innerHTML=http.responseText;
         
            }
    };
    http.send(params);

}



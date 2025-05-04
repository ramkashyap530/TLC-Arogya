/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


function get_mini_day_book(){

  var hospital_code=document.getElementById("hospital_code").value; 
  var date_from=document.getElementById("date_from").value; 
  var date_to=document.getElementById("date_to").value; 
  var patient_name=document.getElementById("patient_name_id").value; 
  var type=document.getElementById("type").value; 
  

  
//   var usr=document.getElementById("assign").value; 
    var http = new XMLHttpRequest();
     var url = '../../WREP0007_Serv';
        var params = 'value=1&hospital_code='+hospital_code+'&date_from='+date_from+'&date_to='+date_to+'&patient_name='+patient_name+'&type='+type;
        http.open('POST', url, true);

 
        http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
           $("#M_tab").DataTable().destroy();
 http.onreadystatechange = function() {//Call a function when the state changes.
            if(http.readyState === 4 && http.status === 200) {
            document.getElementById("tbody").innerHTML=http.responseText;
                     
           
            }
    };
    http.send(params); 
}


function get_mini_day_book_opd(){

  var hospital_code=document.getElementById("hospital_code").value; 
  var date_from=document.getElementById("date_from").value; 
  var date_to=document.getElementById("date_to").value; 
  var patient_name=document.getElementById("patient_name_id").value; 
  var type=document.getElementById("type").value; 
  

  
//   var usr=document.getElementById("assign").value; 
    var http = new XMLHttpRequest();
     var url = '../../WREP0007_Serv';
        var params = 'value=2&hospital_code='+hospital_code+'&date_from='+date_from+'&date_to='+date_to+'&patient_name='+patient_name+'&type='+type;
        http.open('POST', url, true);

 
        http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
           $("#M_tab").DataTable().destroy();
 http.onreadystatechange = function() {//Call a function when the state changes.
            if(http.readyState === 4 && http.status === 200) {
            document.getElementById("tbody").innerHTML=http.responseText;
                     
           
            }
    };
    http.send(params); 
}

/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


function module(){

       var http = new XMLHttpRequest();
     var url = '../../WMAS0001_SERV';
        var params = 'value=1';
        http.open('POST', url, true);

 
        http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
 http.onreadystatechange = function() {//Call a function when the state changes.
            if(http.readyState === 4 && http.status === 200) {
            document.getElementById("module").innerHTML=http.responseText;
         
            }
    };
    http.send(params);

}




function check(id){


    var chk=document.getElementById("chk_"+id);
    var mdid=document.getElementById("mdid_"+id);
    var module=document.getElementById("smdid_"+id);
  
    if(chk.checked){
        mdid.disabled=false;
         module.disabled=false;
    }else{
       mdid.disabled=true;
         module.disabled=true; 
    }
}



function onchange_1(){


   
   var usr=document.getElementById("mem_sub").value; 
    var http = new XMLHttpRequest();
     var url = '../../WMAS0001_SERV';
        var params = 'value=3&usr='+usr;
        http.open('POST', url, true);

 
        http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
 http.onreadystatechange = function() {//Call a function when the state changes.
            if(http.readyState === 4 && http.status === 200) {
            document.getElementById("assign").innerHTML=http.responseText;
         
            }
    };
    http.send(params);

   
}




//for geeting the sub module

function onchange_module(){

   var usr_1=document.getElementById("mem_sub").value; 
   var usr=document.getElementById("assign").value; 
    var http = new XMLHttpRequest();
     var url = '../../WMAS0001_SERV';
        var params = 'value=6&usr='+usr+'&usr_1='+usr_1+'';
        http.open('POST', url, true);

 
        http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
 http.onreadystatechange = function() {//Call a function when the state changes.
            if(http.readyState === 4 && http.status === 200) {
            document.getElementById("submodnm").innerHTML=http.responseText;
         
            }
    };
    http.send(params); 
}



function enable(id){
    
    
    var chk=document.getElementById("chkk_"+id);
    var mdid=document.getElementById("getmdid_"+id);
    var module=document.getElementById("getsubmid_"+id);
  
    if(chk.checked){
         mdid.disabled=false;
         module.disabled=false;
    }else{
         mdid.disabled=true;
         module.disabled=true;
    }
    
}


function all_hospital_charges(){

   var category=document.getElementById("category").value; 
   
    var http = new XMLHttpRequest();
     var url = '../../WMAS0001_SERV';
        var params = 'value=13&category='+category;
        http.open('POST', url, true);

 
        http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
 http.onreadystatechange = function() {//Call a function when the state changes.
            if(http.readyState === 4 && http.status === 200) {
            document.getElementById("submodnm").innerHTML=http.responseText;
         
            }
    };
    http.send(params); 
}


function get_all_system_user(){

   
   
    var http = new XMLHttpRequest();
     var url = '../../WMAS0001_SERV';
        var params = 'value=16';
        http.open('POST', url, true);

 
        http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
 http.onreadystatechange = function() {//Call a function when the state changes.
            if(http.readyState === 4 && http.status === 200) {
            document.getElementById("tbody").innerHTML=http.responseText;
         
            }
    };
    http.send(params); 
}


function get_hospital_details(){

   
   
    var http = new XMLHttpRequest();
     var url = '../../WMAS0001_SERV';
        var params = 'value=17';
        http.open('POST', url, true);

 
        http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
 http.onreadystatechange = function() {//Call a function when the state changes.
            if(http.readyState === 4 && http.status === 200) {
            document.getElementById("tbody").innerHTML=http.responseText;
         
            }
    };
    http.send(params); 
}

function get_phr_details(){

   
   
    var http = new XMLHttpRequest();
     var url = '../../WMAS0001_SERV';
        var params = 'value=19';
        http.open('POST', url, true);

 
        http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
 http.onreadystatechange = function() {//Call a function when the state changes.
            if(http.readyState === 4 && http.status === 200) {
            document.getElementById("tbody").innerHTML=http.responseText;
         
            }
    };
    http.send(params); 
}



function get_other_charge_details(){
    var http = new XMLHttpRequest();
     var url = '../../WMAS0001_SERV';
        var params = 'value=22';
        http.open('POST', url, true);

 
        http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
 http.onreadystatechange = function() {//Call a function when the state changes.
            if(http.readyState === 4 && http.status === 200) {
            document.getElementById("tbody").innerHTML=http.responseText;
         
            }
    };
    http.send(params); 
}


function get_all_procedure(){

    var http = new XMLHttpRequest();
     var url = '../../WMAS0001_SERV';
        var params = 'value=23';
        http.open('POST', url, true);

        http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
 http.onreadystatechange = function() {//Call a function when the state changes.
            if(http.readyState === 4 && http.status === 200) {
            document.getElementById("tbody").innerHTML=http.responseText;
         
            }
    };
    http.send(params); 
}


function get_total_number_rooms(){
    
    var room_id=document.getElementById("mst_room_id").value;
 
    var total_rooms="";
    
    var http = new XMLHttpRequest();
     var url = '../../WMAS0001_SERV';
        var params = 'value=24&room_id='+room_id;
        http.open('POST', url, true);

        http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
 http.onreadystatechange = function() {//Call a function when the state changes.
            if(http.readyState === 4 && http.status === 200) {
           total_rooms = http.responseText;
            alert(total_rooms);
      
            }
     
            
    };
    
                   
              
          

    http.send(params); 
    
}
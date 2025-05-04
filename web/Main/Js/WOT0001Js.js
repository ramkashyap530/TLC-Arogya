/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


function ot_doctor(){
//
//  var hospital_code=document.getElementById("hospital_code").value; 
//   var usr=document.getElementById("assign").value; 
    var http = new XMLHttpRequest();
     var url = '../../WOT0001_Serv';
        var params = 'value=1';
        http.open('POST', url, true);

 
        http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
 http.onreadystatechange = function() {//Call a function when the state changes.
            if(http.readyState === 4 && http.status === 200) {
            document.getElementById("tbody").innerHTML=http.responseText;
       
            }
    };
    http.send(params); 
}


function ot_staff(){
//
//  var hospital_code=document.getElementById("hospital_code").value; 
//   var usr=document.getElementById("assign").value; 
    var http = new XMLHttpRequest();
     var url = '../../WOT0001_Serv';
        var params = 'value=2';
        http.open('POST', url, true);

 
        http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
 http.onreadystatechange = function() {//Call a function when the state changes.
            if(http.readyState === 4 && http.status === 200) {
            document.getElementById("tbody_staff").innerHTML=http.responseText;
       
            }
    };
    http.send(params); 
}


function assign_OT_doc(id){
    var chk=document.getElementById("chk_"+id); 
    var doc_id=document.getElementById("doc_id_"+id); 
    var doc_name=document.getElementById("doc_name_"+id); 
    var div_id_doc=document.getElementById("doc_t"); 
    


    if(chk.checked){
        doc_id.disabled=false;
        
        doc_name.disabled=false;
      
        
        
     
        div_id_doc.innerHTML +="<span class='badge bg-success'>"+doc_name.value+"</span> <span></span>";
        
        
        
        
    }
    else{
        doc_id.disabled=true;
       
        doc_name.disabled=true;
        
        
        
    }
     
      
  }
  
  
  
  
  
  function assign_OT_staff(id){
    var chk=document.getElementById("chk_staff_"+id); 
    
    var div_id_staff=document.getElementById("staff_t"); 
    
    var id_staff=document.getElementById("staff_id_"+id); 
    var name_staff=document.getElementById("staff_name_"+id); 

    if(chk.checked){
       
         id_staff.disabled=false;
       
        name_staff.disabled=false;
        
        
     
        div_id_staff.innerHTML +="<span class='badge bg-warning'>"+name_staff.value+"</span> <span></span>";
        
        
        
        
    }
    else{
        
        id_staff.disabled=true;
       
        name_staff.disabled=true;
    
    
        
        
    }
     
      
  }
  
  
  
  
function ot_details(){
//
//  var hospital_code=document.getElementById("hospital_code").value; 
//   var usr=document.getElementById("assign").value; 
    var http = new XMLHttpRequest();
     var url = '../../WOT0001_Serv';
        var params = 'value=4';
        http.open('POST', url, true);

 
        http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
 http.onreadystatechange = function() {//Call a function when the state changes.
            if(http.readyState === 4 && http.status === 200) {
            document.getElementById("tbody_ot").innerHTML=http.responseText;
       
            }
    };
    http.send(params); 
}


function TotAmtOT()
{
  var OT_Amount= document.getElementById("OT_Amount").value;
  if(OT_Amount===""){OT_Amount=0;}
  var Amount_paid= document.getElementById("Amount_paid").value;
  if(Amount_paid===""){Amount_paid=0;}
  var Amount_pending=parseFloat(OT_Amount)-parseFloat(Amount_paid);
  document.getElementById("Amount_pending").value=Amount_pending;
}


function getRateOfOt(ot)
{
   var http = new XMLHttpRequest();
     var url = '../../WOT0001_Serv';
        var params = 'value=5&ot='+ot;
        http.open('POST', url, true);

 
        http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
 http.onreadystatechange = function() {//Call a function when the state changes.
            if(http.readyState === 4 && http.status === 200) {
            document.getElementById("OT_Amount").value=http.responseText;
            var OT_Amount=document.getElementById("OT_Amount").value;
            document.getElementById("Amount_pending").value=OT_Amount;
            document.getElementById("Amount_paid").value=0;
            
            }
    };
    http.send(params);
}
  

  
  
    
    
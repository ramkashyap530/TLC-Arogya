/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


function get_ipd_rec(){

  var hospital_code=document.getElementById("hospital_code").value; 
  var date_from=document.getElementById("date_from").value; 
  var date_to=document.getElementById("date_to").value; 
  var patient_name=document.getElementById("patient_name_id").value; 
  var type=document.getElementById("type").value; 
  

  
//   var usr=document.getElementById("assign").value; 
    var http = new XMLHttpRequest();
     var url = '../../WIPD0002_serv';
        var params = 'value=1&hospital_code='+hospital_code+'&date_from='+date_from+'&date_to='+date_to+'&patient_name='+patient_name+'&type='+type;
        http.open('POST', url, true);

 
        http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
           $("#M_tab").DataTable().destroy();
 http.onreadystatechange = function() {//Call a function when the state changes.
            if(http.readyState === 4 && http.status === 200) {
            document.getElementById("tbody").innerHTML=http.responseText;
                 $("#M_tab").DataTable({
              dom: 'Bfrtip',
        buttons: [
            'copyHtml5',
            'excelHtml5',
            'print',
            'pdfHtml5'
        ]
               
            });
            }
    };
    http.send(params); 
}



function all_hospital_charges(){

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



function get_test_his(i){
   var patient_id=document.getElementById("id_"+i).value; 
   var patient_id_Modal=document.getElementById("patient_id_Modal"); 

 patient_id_Modal.value=patient_id;
    var http = new XMLHttpRequest();
     var url = '../../WIPD0002_serv';
        var params ='value=2&patient_id='+patient_id;
        http.open('POST', url, true);

 
        http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
 http.onreadystatechange = function() {//Call a function when the state changes.
            if(http.readyState === 4 && http.status === 200) {
            document.getElementById("test_his").innerHTML=http.responseText;
         get_ot_his(patient_id);
         get_room_charge_his(patient_id);
         get_other_charge_his(patient_id);
       
         
            }
    };
    http.send(params); 
}

function getToTFinal(i)
{
//    var final_bill_amt=0;
//  $(".total_amt").each(function() {
//    final_bill_amt=final_bill_amt+parseFloat($(this).val());
//});

var tot_amt=document.getElementById("tot_amt_"+i).value;
var paid_amt=document.getElementById("paid_amt_"+i).value;
var left_amt=document.getElementById("left_amt_"+i).value;

$("#total_amount").val(tot_amt);
$("#total_paid").val(paid_amt);
$("#total_left").val(left_amt);
}



function get_ot_his(patient_id){
  

    var http = new XMLHttpRequest();
     var url = '../../WIPD0002_serv';
        var params ='value=3&patient_id='+patient_id;
        http.open('POST', url, true);

 
        http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
 http.onreadystatechange = function() {//Call a function when the state changes.
            if(http.readyState === 4 && http.status === 200) {
            document.getElementById("OT_HIs").innerHTML=http.responseText;
         
            }
    };
    http.send(params); 
}


function get_room_his(patient_id){
  

    var http = new XMLHttpRequest();
     var url = '../../WIPD0002_serv';
        var params ='value=3&patient_id='+patient_id;
        http.open('POST', url, true);

 
        http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
 http.onreadystatechange = function() {//Call a function when the state changes.
            if(http.readyState === 4 && http.status === 200) {
            document.getElementById("OT_HIs").innerHTML=http.responseText;
         
            }
    };
    http.send(params); 
}


function patient_payment(i){
   var patient_id=document.getElementById("id_"+i).value; 
   var patient_id_Modal_payment=document.getElementById("patient_id_Modal_payment"); 
   
   var get_total_amount=document.getElementById("tot_amt_"+i).value;
   var get_total_paid_amount=document.getElementById("paid_amt_"+i).value;

 patient_id_Modal_payment.value=patient_id;
 document.getElementById("total_amount_till_now").value=get_total_amount;
 document.getElementById("total_amount_paid_till_now").value=get_total_paid_amount;
    var http = new XMLHttpRequest();
     var url = '../../WIPD0002_serv';
        var params ='value=2&patient_id='+patient_id;
        http.open('POST', url, true);

 
        http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
 http.onreadystatechange = function() {//Call a function when the state changes.
            if(http.readyState === 4 && http.status === 200) {
            document.getElementById("test_his").innerHTML=http.responseText;
           
         
            }
    };
    http.send(params); 
}




//for checking the amount on reciprt 
function check_amount(){
    var total_amount=document.getElementById("total_amount_till_now").value;
    var amt_paid=document.getElementById("total_amount_paid_till_now").value;
    var collect_amt=document.getElementById("amt_collect").value;
    
    var left=0;
    
    left = parseFloat(total_amount)-parseFloat(amt_paid);
    
    if(collect_amt > left){
        alert("Amount Should not be More Than "+left);
        collect_amt=0;
    }
        
   
}










function get_room_charge_his(patient_id){
  

    var http = new XMLHttpRequest();
     var url = '../../WIPD0002_serv';
        var params ='value=5&patient_id='+patient_id;
        http.open('POST', url, true);

 
        http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
 http.onreadystatechange = function() {//Call a function when the state changes.
            if(http.readyState === 4 && http.status === 200) {
            document.getElementById("patietient_room_hi").innerHTML=http.responseText;
         // getToTFinal();
            }
    };
    http.send(params); 
}


//get boucher detils

function get_ipd_rec_voucher(){

  var hospital_code=document.getElementById("hospital_code").value; 
  var date_from=document.getElementById("date_from").value; 
  var date_to=document.getElementById("date_to").value; 
  var patient_name=document.getElementById("patient_name_id").value; 
  var type=document.getElementById("type").value; 
  

  
//   var usr=document.getElementById("assign").value; 
    var http = new XMLHttpRequest();
     var url = '../../WIPD0002_serv';
        var params = 'value=10&hospital_code='+hospital_code+'&date_from='+date_from+'&date_to='+date_to+'&patient_name='+patient_name+'&type='+type;
        http.open('POST', url, true);

 
        http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
           $("#M_tab").DataTable().destroy();
 http.onreadystatechange = function() {//Call a function when the state changes.
            if(http.readyState === 4 && http.status === 200) {
            document.getElementById("tbody").innerHTML=http.responseText;
                 $("#M_tab").DataTable({
              dom: 'Bfrtip',
        buttons: [
            'copyHtml5',
            'excelHtml5',
            'print',
            'pdfHtml5'
        ],
               
            });
            }
    };
    http.send(params); 
}



function get_room(){
    
   
  var category_id=document.getElementById("category_id").value; 
  
//   var usr=document.getElementById("assign").value; 
    var http = new XMLHttpRequest();
     var url = '../../WOP0001_SERV';
        var params = 'value=10&category_id='+category_id;
        http.open('POST', url, true);

 
        http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
 http.onreadystatechange = function() {//Call a function when the state changes.
            if(http.readyState === 4 && http.status === 200) {
                
    
            document.getElementById("room_no_id").innerHTML=http.responseText;
            
        
            }
    };
    http.send(params); 
    
}




function get_beds(){
    
   
  var room_id=document.getElementById("room_no_id").value; 
  
//   var usr=document.getElementById("assign").value; 
    var http = new XMLHttpRequest();
     var url = '../../WOP0001_SERV';
        var params = 'value=11&room_id='+room_id;
        http.open('POST', url, true);

 
        http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
 http.onreadystatechange = function() {//Call a function when the state changes.
            if(http.readyState === 4 && http.status === 200) {
                
    
            document.getElementById("room_bed_id").innerHTML=http.responseText;
          
        
            }
    };
    http.send(params); 
    
}



function get_current_bed(i){
    var patient_id=document.getElementById("id_"+i).value; 
    var shift_patient_id=document.getElementById("shift_patient_id");
    
    var current_bed=document.getElementById("current_bed");
    
   
   shift_patient_id.value=patient_id;
   current_bed.value=document.getElementById("bed_"+i).value;
    var http = new XMLHttpRequest();
     var url = '../../WIPD0002_serv';
        var params = 'value=11&patient_id='+patient_id;
        http.open('POST', url, true);

 
        http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
 http.onreadystatechange = function() {//Call a function when the state changes.
            if(http.readyState === 4 && http.status === 200) {
                
    
            document.getElementById("current_room").innerHTML=http.responseText;
          
        
            }
    };
    http.send(params); 
}



//for other charges 
function get_other_Charge_assign() {
//
//  var hospital_code=document.getElementById("hospital_code").value; 
//   var usr=document.getElementById("assign").value;  
 var patient_id=document.getElementById("patient_id").value; 
 alert(patient_id);
 if(patient_id!==""){
    var http = new XMLHttpRequest();
    var url = '../../WIPD0002_serv';
    var params = 'value=13&patient_id='+patient_id;
    http.open('POST', url, true);


    http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    http.onreadystatechange = function () {//Call a function when the state changes.
        if (http.readyState === 4 && http.status === 200) {
            document.getElementById("tbody").innerHTML = http.responseText;
           
        }
    };
    http.send(params);
 }else{
    document.getElementById("tbody").innerHTML="";
 }
}



function assign_test(id) {
   
    var chk_D=document.getElementById("chk_"+id);
    if(chk_D.checked===true)
    {
       document.getElementById("rate_"+id).disabled = false;
       
       document.getElementById("test_paid_amt_"+id).disabled = false;
       document.getElementById("test_paid_amt_"+id).value=0;
       document.getElementById("test_left_amt_"+id).disabled = false;
       //document.getElementById("test_left_amt_"+id).value=0;
       document.getElementById("test_left_amt_"+id).value=document.getElementById("rate_"+id).value;
      
       
    }else{document.getElementById("test_paid_amt_"+id).disabled = true;document.getElementById("test_paid_amt_"+id).value=0;
   document.getElementById("test_left_amt_"+id).disabled = true;document.getElementById("test_left_amt_"+id).value=0; 
   document.getElementById("rate_"+id).disabled = true;
   }
   
   var div_id = document.getElementById("test_t");
   div_id.innerHTML="";
   var total = 0;
   var checkboxes = document.getElementsByName('test_checkbox');
    for (var checkbox of checkboxes)
    {
        if (checkbox.checked) {
            var test_name = document.getElementById("test_name_" + checkbox.value );
            document.getElementById("test_id_"+checkbox.value).disabled = false;
            div_id.innerHTML += "<span class='badge bg-success'>" + test_name.value + "</span> <span></span>";
            total += parseFloat(document.getElementById("test_rate_" + checkbox.value).value);
        }else{
           document.getElementById("test_id_"+checkbox.value).disabled = true;
          }
    }
    document.getElementById("tot_test_amt").innerHTML=total;
    document.getElementById("Test_Tot_amt").value=total;
    
    
    finalPaidAmt();
}



function autoPaid(data){
   
   var left_in_hand=data;//10
   
  var tta= document.getElementById("Test_Tot_amt").value;
   if(parseFloat(left_in_hand)<=parseFloat(tta)){
   
   var checkboxes = document.getElementsByName('test_checkbox');
    for (var checkbox of checkboxes)
    {
        if (checkbox.checked) {
         
         if(left_in_hand>0){
         var test_left_amt=document.getElementById("rate_"+checkbox.value).value;// 500
         if(parseFloat(left_in_hand)>=parseFloat(test_left_amt))
         {
            document.getElementById("test_paid_amt_"+checkbox.value).value=test_left_amt;
            var x=(parseFloat(left_in_hand)-parseFloat(test_left_amt));
            left_in_hand=x;
           
         }else{
            
           // var x=(parseFloat(test_left_amt)-parseFloat(left_in_hand));
            document.getElementById("test_paid_amt_"+checkbox.value).value=left_in_hand;
            left_in_hand-=left_in_hand;
            
         }
      }else{document.getElementById("test_paid_amt_"+checkbox.value).value=0;}
         
         get_test_paidData(checkbox.value);
         
         }
    }
 }else{
    alert("Please Valid Input ! Not Greater than :"+tta);
    var title=document.getElementById("auto_paid");
      title.classList.add('error');
    setTimeout(function() {
      title.classList.remove('error');
    }, 900);
    //document.getElementById("auto_paid").focus();
 }
 
   //finalPaidAmt();
}


function get_test_paidData(i)
{
   var test_paid_amt=document.getElementById("test_paid_amt_"+i).value;
   if(test_paid_amt===''){test_paid_amt=0;}
   var rate=document.getElementById("rate_"+i).value;
   
   if(parseFloat(test_paid_amt)<=parseFloat(rate))
   {
   var test_left_amt=parseFloat(rate)-parseFloat(test_paid_amt);
   
   document.getElementById("test_left_amt_"+i).value=test_left_amt;
   }else{
      document.getElementById("test_left_amt_"+i).value=rate;
      document.getElementById("test_paid_amt_"+i).value=0;}
   
   finalPaidAmt();
   
}

function testtotamt()
{
   var Test_Tot_amt=0;var Test_paid_amt=0;var Test_pending_amt=0;
   
   Test_Tot_amt=document.getElementById("Test_Tot_amt").value;
   
   Test_paid_amt=document.getElementById("Test_paid_amt").value;
   if(Test_paid_amt===''){Test_paid_amt=0;document.getElementById("Test_paid_amt").value=0;}
   
   Test_pending_amt=parseFloat(Test_Tot_amt)-parseFloat(Test_paid_amt);
   
   document.getElementById("Test_pending_amt").value=Test_pending_amt;
   
}
function finalPaidAmt()
{
   var tpa=0
   document.getElementById("Test_paid_amt").value=0;
   var checkboxes = document.getElementsByName('test_checkbox');
    for (var checkbox of checkboxes)
    {
        if (checkbox.checked) {
            tpa+=parseFloat(document.getElementById("test_paid_amt_"+checkbox.value).value);
         }
    }
   document.getElementById("Test_paid_amt").value=tpa;
}

function get_lab_patient_id() {
    var patient_id = document.getElementById("patient_id").value;
    var mod_input_pat = document.getElementById("patient_id_mod");


    if (patient_id == "Select") {
        alert("Please Select Patient First");

    }

    mod_input_pat.value = patient_id;
    
    testtotamt();
}




function get_other_Charge_assign_pending () {
//
//  var hospital_code=document.getElementById("hospital_code").value; 
//   var usr=document.getElementById("assign").value;  
 var patient_id=document.getElementById("patient_id").value; 

 if(patient_id!==""){
    var http = new XMLHttpRequest();
    var url = '../../WIPD0002_serv';
    var params = 'value=14&patient_id='+patient_id;
    http.open('POST', url, true);


    http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    http.onreadystatechange = function () {//Call a function when the state changes.
        if (http.readyState === 4 && http.status === 200) {
            document.getElementById("tbody").innerHTML = http.responseText;
           
        }
    };
    http.send(params);
 }else{
    document.getElementById("tbody").innerHTML="";
 }
}


function collect_payment(id) {
   
    var chk_D=document.getElementById("chk_"+id);
    if(chk_D.checked===true)
    {
       document.getElementById("rate_"+id).disabled = false;
       
       document.getElementById("test_paid_amt_"+id).disabled = false;
       document.getElementById("test_paid_amt_"+id).value=0;
       document.getElementById("test_left_amt_"+id).disabled = false;
       //document.getElementById("test_left_amt_"+id).value=0;
       document.getElementById("test_left_amt_"+id).value=document.getElementById("rate_"+id).value;
      
       
    }else{document.getElementById("test_paid_amt_"+id).disabled = true;document.getElementById("test_paid_amt_"+id).value=0;
   document.getElementById("test_left_amt_"+id).disabled = true;document.getElementById("test_left_amt_"+id).value=0; 
   document.getElementById("rate_"+id).disabled = true;
   }
   
   var div_id = document.getElementById("test_t");
   div_id.innerHTML="";
   var total = 0;
   var checkboxes = document.getElementsByName('test_checkbox');
    for (var checkbox of checkboxes)
    {
        if (checkbox.checked) {
            var test_name = document.getElementById("test_name_" + checkbox.value );
            document.getElementById("test_id_"+checkbox.value).disabled = false;
            div_id.innerHTML += "<span class='badge bg-success'>" + test_name.value + "</span> <span></span>";
            total += parseFloat(document.getElementById("test_rate_" + checkbox.value).value);
        }else{
           document.getElementById("test_id_"+checkbox.value).disabled = true;
          }
    }
    document.getElementById("tot_test_amt").innerHTML=total;
    document.getElementById("Test_Tot_amt").value=total;
    
    
    finalPaidAmt();
}

//for other charge his 
function get_other_charge_his(patient_id){
  

    var http = new XMLHttpRequest();
     var url = '../../WIPD0002_serv';
        var params ='value=15&patient_id='+patient_id;
        http.open('POST', url, true);

 
        http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
 http.onreadystatechange = function() {//Call a function when the state changes.
            if(http.readyState === 4 && http.status === 200) {
            document.getElementById("OC_HIs").innerHTML=http.responseText;
          
         
            }
    };
    http.send(params); 
}




function Get_selected_type(){
    
   var type=document.getElementById("Pay_type").value;
    var Checque_number=document.getElementById("Checque_number");
     var Card_number=document.getElementById("Card_number");
      var UPI_type=document.getElementById("UPI_type");
      var transcation_id=document.getElementById("transcation_id");
      
 
     
      
      if(type==="Cash"){
          Checque_number.style.display="none";  
          Card_number.style.display="none";  
          UPI_type.style.display="none";  
      }
          if(type==="Cheque"){
          Checque_number.style.display="block";  
          Card_number.style.display="none";  
          UPI_type.style.display="none";  
      }
      
              if(type==="Card"){
          Checque_number.style.display="none";  
          Card_number.style.display="block";  
          UPI_type.style.display="none";  
      }
      
           if(type==="UPI"){
          Checque_number.style.display="none";  
          Card_number.style.display="none";  
          UPI_type.style.display="block"; 
          transcation_id.style.display="block";  
      }
      
      if(type===""){
              Checque_number.style.display="none";  
          Card_number.style.display="none";  
          UPI_type.style.display="none"; 
          transcation_id.style.display="none";  
      }

}



//for Bill Approvrval 

function get_all_bills(){

   var Patient_id=document.getElementById("PATIENT_ID").value; 
   var Bill_no=document.getElementById("BILL_NO").value; 
   
    var http = new XMLHttpRequest();
     var url = '../../WIPD0002_serv';
        var params = 'value=16&Patient_id='+Patient_id+'&Bill_no='+Bill_no;
        http.open('POST', url, true);

 
        http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
 http.onreadystatechange = function() {//Call a function when the state changes.
            if(http.readyState === 4 && http.status === 200) {
            document.getElementById("tbody").innerHTML=http.responseText;
         
            }
    };
    http.send(params); 

}



//for Approving the bIll 

function Approve_bills(i){

   var Patient_id=document.getElementById("patient_id_"+i).value; 
   var Bill_no=document.getElementById("bill_no_"+i).value; 
  
    var http = new XMLHttpRequest();
     var url = '../../WIPD0002_serv';
        var params = 'value=17&Patient_id='+Patient_id+'&Bill_no='+Bill_no;
        http.open('POST', url, true);

 
        http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
 http.onreadystatechange = function() {//Call a function when the state changes.
            if(http.readyState === 4 && http.status === 200) {
            
            window.location.reload();
            
         
            }
    };
    http.send(params); 

}



//for checking the patient with in the room 

function Check_bed_within_room(){

   var Patient_id=document.getElementById("shift_patient_id").value; 
   var Room_category_id=document.getElementById("category_id").value; 
   
   
   alert(Room_category_id);
   
  
    var http = new XMLHttpRequest();
     var url = '../../WIPD0002_serv';
        var params = 'value=17&Patient_id='+Patient_id+'&Room_category_id='+Room_category_id;
        http.open('POST', url, true);

 
        http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
 http.onreadystatechange = function() {//Call a function when the state changes.
            if(http.readyState === 4 && http.status === 200) {
           
            
         
            }
    };
    http.send(params); 

}





    






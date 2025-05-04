/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


function get_test_name() {

    var hospital_code = document.getElementById("hospital_code").value;
//   var usr=document.getElementById("assign").value; 
    var http = new XMLHttpRequest();
    var url = '../../WLAB0001_Serv';
    var params = 'value=2';
    http.open('POST', url, true);


    http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    http.onreadystatechange = function () {//Call a function when the state changes.
        if (http.readyState === 4 && http.status === 200) {
            document.getElementById("tbody").innerHTML = http.responseText;
            get_test_rate();
        }
    };
    http.send(params);
}

function get_test_rate() {

    var hospital_code = document.getElementById("hospital_code").value;
//   var usr=document.getElementById("assign").value; 
    var http = new XMLHttpRequest();
    var url = '../../WLAB0001_Serv';
    var params = 'value=3';
    http.open('POST', url, true);


    http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    http.onreadystatechange = function () {//Call a function when the state changes.
        if (http.readyState === 4 && http.status === 200) {
            document.getElementById("tbody_rate").innerHTML = http.responseText;

        }
    };
    http.send(params);
}


function get_test_assign() {
//
//  var hospital_code=document.getElementById("hospital_code").value; 
//   var usr=document.getElementById("assign").value; 
 var patient_id=document.getElementById("patient_id").value; 
 if(patient_id!==""){
    var http = new XMLHttpRequest();
    var url = '../../WLAB0001_Serv';
    var params = 'value=5&patient_id='+patient_id;
    http.open('POST', url, true);


    http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    http.onreadystatechange = function () {//Call a function when the state changes.
        if (http.readyState === 4 && http.status === 200) {
            document.getElementById("tbody").innerHTML = http.responseText;
            get_test_rate();
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



//  get assigned test to patients 
function get_test_assigned_to_pat() {
//
//  var hospital_code=document.getElementById("hospital_code").value; 
//   var usr=document.getElementById("assign").value; 
    var http = new XMLHttpRequest();
    var url = '../../WLAB0001_Serv';
    var params = 'value=7';
    http.open('POST', url, true);

    get_test_assigned_to_pata_all();
    http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    http.onreadystatechange = function () {//Call a function when the state changes.
        if (http.readyState === 4 && http.status === 200) {
            document.getElementById("tbody").innerHTML = http.responseText;

        }
    };
    http.send(params);
}



function change_test_sts(i) {

    var patient_code = document.getElementById("patient_id_" + i).value;
    alert(patient_code);

    var test_code = document.getElementById("test_id_" + i).value;
    var user = document.getElementById("user").value;
    var http = new XMLHttpRequest();
    var url = '../../WLAB0001_Serv';

    var params = 'value=8&patient_code=' + patient_code + '&test_code=' + test_code + '&user=' + user;
    alert("test");
    http.open('POST', url, true);


    http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    http.onreadystatechange = function () {//Call a function when the state changes.
        if (http.readyState === 4 && http.status === 200) {

            alert("Test Done Success");

            get_test_assigned_to_pat();
            get_test_assigned_to_pata_all();
        }

    };

    http.send(params);
}


function get_test_assigned_to_pata_all() {
//
//  var hospital_code=document.getElementById("hospital_code").value; 
//   var usr=document.getElementById("assign").value; 
    var http = new XMLHttpRequest();
    var url = '../../WLAB0001_Serv';
    var params = 'value=9';
    http.open('POST', url, true);


    http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    http.onreadystatechange = function () {//Call a function when the state changes.
        if (http.readyState === 4 && http.status === 200) {
            document.getElementById("tbody_done").innerHTML = http.responseText;

        }
    };
    http.send(params);
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



//for getting the payemts dues in lab test Assigniing 


function get_test_assigned_to_pat_dues() {
//
//  var hospital_code=document.getElementById("hospital_code").value; 
//   var usr=document.getElementById("assign").value; 
    var http = new XMLHttpRequest();
    var url = '../../WLAB0001_Serv';
    var params = 'value=10';
    http.open('POST', url, true);

    get_test_assigned_to_pata_all();
    http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    http.onreadystatechange = function () {//Call a function when the state changes.
        if (http.readyState === 4 && http.status === 200) {
            document.getElementById("tbody").innerHTML = http.responseText;

        }
    };
    http.send(params);
}




function type() {
    var type = document.getElementById("type").value;
    var opd_patient_name = document.getElementById("opd_patient");
    var opd_patient_mobile = document.getElementById("opd_patient_mobile");

    var inpd_select = document.getElementById("ipd_patient");


    if (type == "OPD") {
        opd_patient_name.hidden = false;
        opd_patient_mobile.hidden = false;
        inpd_select.hidden = true;
    }
    if (type == "IPD") {
        opd_patient_name.hidden = true;
        opd_patient_mobile.hidden = true;
        inpd_select.hidden = false;
    }

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


//for patient wise data 

function Get_patient_wise_payment_details(){
    
    var Patient_id=document.getElementById("patient_id").value;
    
    
    
    
    var http = new XMLHttpRequest();
    var url = '../../WLAB0001_Serv';
    var params = 'value=11&Patient_id='+Patient_id;
    http.open('POST', url, true);

    http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    http.onreadystatechange = function () {//Call a function when the state changes.
        if (http.readyState === 4 && http.status === 200) {
            document.getElementById("tbody").innerHTML = http.responseText;


        }
    };
    http.send(params);
    
}





//for checking Ayushman Patient

function check_Ayushmaan(){
    var Patient_id=document.getElementById("patient_id").value;
    var AYUSH_MAAN=document.getElementById("AYUSH_MAAN");
    
    
    var http = new XMLHttpRequest();
    var url = '../../WLAB0001_Serv';
    var params = 'value=15&Patient_id='+Patient_id;
    http.open('POST', url, true);

    http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    http.onreadystatechange = function () {//Call a function when the state changes.
        if (http.readyState === 4 && http.status === 200) {
            alert(http.response);
            if((http.response)==='AyushMan_Bharat'){
               document.getElementById("AYUSH_MAAN").style.display="block";
           
            }
                  
            
            
            


        }
    };
    http.send(params);

}












function final_bill_settle(){
    
    var gross_total=document.getElementById("bill_gross_amount").value;
    var Bill_Total_bill_paid=document.getElementById("Bill_Total_bill_paid").value;
    var Bill_total_amount_left=document.getElementById("Bill_total_amount_left").value;
   
    
    var Gross_amount=document.getElementById("Gross_amount");
    var total_voc_amount=document.getElementById("total_voc_amount");
    var Discount=document.getElementById("Discount");
    var Net_amount=document.getElementById("Net_amount");
   
     Gross_amount.value=gross_total;
     total_voc_amount.value=Bill_total_amount_left;
     
        
      
}

function Bill_discount(){
    
   
    var Gross_amount=document.getElementById("Gross_amount").value;
    var total_voc_amount=document.getElementById("total_voc_amount").value;
    var Discount=document.getElementById("Discount").value;
   
 var Bill_Total_bill_paid=document.getElementById("Bill_Total_bill_paid").value;  
   
      document.getElementById("Vocher_payment").value=Bill_Total_bill_paid;
    var Net_pay_value=0;
    
    
    Net_pay_value=parseFloat(total_voc_amount) - parseFloat(Discount);
    document.getElementById("Net_amount").value=Net_pay_value;
   
   
   alert(Net_pay_value);

     if(parseFloat(Discount) > parseFloat(Gross_amount)){
         alert("Discount Can not Be Greater Than Gross Amount");
         document.getElementById("Net_amount").value=0;
     
     }
    
}


//for printing 

    function printPageArea(){
    var printContent = document.getElementById("Bill_print_area").innerHTML;
    var originalContent = document.body.innerHTML;
    document.body.innerHTML = printContent;
    window.print();
    document.body.innerHTML = originalContent;
    
    }
    
    
    
    //for total vouvher pay 
    
function Get_total_voc_pay(){

  var Patient_id=document.getElementById("patient_id").value; 
  

  
//   var usr=document.getElementById("assign").value; 
    var http = new XMLHttpRequest();
     var url = '../../WLAB0001_Serv';
        var params = 'value=14&Patient_id='+Patient_id;
        http.open('POST', url, true);

 
        http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
           $("#M_table").DataTable().destroy();
 http.onreadystatechange = function() {//Call a function when the state changes.
            if(http.readyState === 4 && http.status === 200) {
            document.getElementById("voc_tbody").innerHTML=http.responseText;
                    $("#M_table").DataTable({
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
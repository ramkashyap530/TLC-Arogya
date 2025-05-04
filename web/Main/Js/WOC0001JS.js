function get_oc_assign()
{
    var patient_id = document.getElementById("patient_id").value;
    var patient_id_opd = document.getElementById("patient_id_opd").value;
    var category_id=document.getElementById("charge_category_id").value;
    var type=document.getElementById("type").value;
    alert(type);
    if(type=="OPD"){
        var http = new XMLHttpRequest();
    var url = '../../WOC0001_Serv';
    var params = 'value=1&patient_id='+patient_id_opd+'&category_id';
    http.open('POST', url, true);
    }
    else{
    var http = new XMLHttpRequest();
    var url = '../../WOC0001_Serv';
    var params = 'value=1&patient_id='+patient_id+'&category_id='+category_id;
    http.open('POST', url, true);
    }


    http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    http.onreadystatechange = function () {//Call a function when the state changes.
        if (http.readyState === 4 && http.status === 200) {
            document.getElementById("tbody").innerHTML = http.responseText;

        }
    };
    http.send(params);
}


function checkboxFN(i)
{
   var chk=document.getElementById("oc_checkBox_"+i); 
   if(chk.checked){
      document.getElementById("submit_amt_"+i).disabled=false;
      document.getElementById("qty_"+i).disabled=false;
      document.getElementById("submit_amt_"+i).value="0";
  document.getElementById("qty_"+i).value="1";
      
   }else{document.getElementById("submit_amt_"+i).disabled=true;
      document.getElementById("qty_"+i).disabled=true;
   document.getElementById("submit_amt_"+i).value="0";
  document.getElementById("qty_"+i).value="0";
   }
  
}

function get_oc_assign2()
{
    var patient_id = document.getElementById("patient_id").value;
    var http = new XMLHttpRequest();
    var url = '../../WOC0001_Serv';
    var params = 'value=3&patient_id='+patient_id;
    http.open('POST', url, true);


    http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    http.onreadystatechange = function () {//Call a function when the state changes.
        if (http.readyState === 4 && http.status === 200) {
            document.getElementById("tbody").innerHTML = http.responseText;

        }
    };
    http.send(params);
}

function checkboxFN2(i)
{
   var chk=document.getElementById("oc_checkBox_"+i); 
   var add_oc_code=document.getElementById("add_oc_code_"+i).value; 
   if(chk.checked){
      document.getElementById("submit_amt_"+i+"_"+add_oc_code).disabled=false;
      document.getElementById("submit_amt_"+i+"_"+add_oc_code).value="1";
     }else{document.getElementById("submit_amt_"+i+"_"+add_oc_code).disabled=true;
         document.getElementById("submit_amt_"+i+"_"+add_oc_code).value="0";
      }
  
}



function type_change() {
    var type = document.getElementById("type").value;
    var ipd_patient = document.getElementById("ipd_patient");
    var opd_patient = document.getElementById("opd_patient");
    var patient_id_ipd = document.getElementById("patient_id");
    var patient_id_opd = document.getElementById("patient_id_opd");

  
    if (type == "OPD") {
             
        ipd_patient.hidden = true;
        opd_patient.hidden = false;
        patient_id_ipd.disabled=true;
        patient_id_opd.disabled=false;
     }
    if (type == "IPD") {
        ipd_patient.hidden = false;
        opd_patient.hidden = true;
        patient_id_ipd.disabled=false;
        patient_id_opd.disabled=true;
   
    }
    
    

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


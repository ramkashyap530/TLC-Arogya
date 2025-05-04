function get_RoomData()
{
   var patient_id=document.getElementById("patient_id").value; 
   var http = new XMLHttpRequest();
     var url = '../../WIPD0005_serv';
        var params = 'value=1&patient_id='+patient_id;
        http.open('POST', url, true);
        http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
        http.onreadystatechange = function() {//Call a function when the state changes.
        if(http.readyState === 4 && http.status === 200) {
            document.getElementById("tbody").innerHTML=http.responseText;
        }
    };
    http.send(params); 
}

function disabled_enabled(sNo)
{
   var checkbox=document.getElementById("checkbox_room_payment_"+sNo);
   if(checkbox.checked===true)
   {
      document.getElementById("patient_locationRwid_"+sNo).disabled=false;
      document.getElementById("room_bed_id_"+sNo).disabled=false;
      document.getElementById("room_amt_"+sNo).disabled=false;
      document.getElementById("paid_amt_"+sNo).disabled=false;
      document.getElementById("panding_amt_"+sNo).disabled=false;
      document.getElementById("submit_amt_"+sNo).disabled=false;
   }else{
      document.getElementById("patient_locationRwid_"+sNo).disabled=true;
      document.getElementById("room_bed_id_"+sNo).disabled=true;
      document.getElementById("room_amt_"+sNo).disabled=true;
      document.getElementById("paid_amt_"+sNo).disabled=true;
      document.getElementById("panding_amt_"+sNo).disabled=true;
      document.getElementById("submit_amt_"+sNo).disabled=true;
      document.getElementById("submit_amt_"+sNo).value="0";
   }
}

function get_RoomPaymentData()
{
   var patient_id=document.getElementById("patient_id").value; 
   var http = new XMLHttpRequest();
     var url = '../../WIPD0005_serv';
        var params = 'value=3&patient_id='+patient_id;
        http.open('POST', url, true);
        http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
        http.onreadystatechange = function() {//Call a function when the state changes.
        if(http.readyState === 4 && http.status === 200) {
            document.getElementById("tbody_payment").innerHTML=http.responseText;
        }
    };
    http.send(params); 
}
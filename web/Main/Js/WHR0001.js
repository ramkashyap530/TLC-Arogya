/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


function get_staff(){

  var hospital_code=document.getElementById("hospital_code").value; 
//   var usr=document.getElementById("assign").value; 
    var http = new XMLHttpRequest();
     var url = '../../WHR0001_SERV';
        var params = 'value=1&hospital_code='+hospital_code;
        http.open('POST', url, true);

 
        http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
 http.onreadystatechange = function() {//Call a function when the state changes.
            if(http.readyState === 4 && http.status === 200) {
            document.getElementById("tbody").innerHTML=http.responseText;
         
            }
    };
    http.send(params); 
}



function enable_id(id){
    
    
    var chk=document.getElementById("chk_"+id);
    var name=document.getElementById("id_"+id);
    var shift=document.getElementById("shift_"+id);
  
    if(chk.checked){
         name.disabled=false;
         shift.disabled=false;
    }else{
         name.disabled=true;
         shift.disabled=true;
    }
    
}





//for attandenec


function get_att_data(){

  var hospital_code=document.getElementById("hospital_code").value; 
//   var usr=document.getElementById("assign").value; 
    var http = new XMLHttpRequest();
     var url = '../../WHR0001_SERV';
        var params = 'value=3&hospital_code='+hospital_code;
        http.open('POST', url, true);

 
        http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
 http.onreadystatechange = function() {//Call a function when the state changes.
      $("#M_tab").DataTable().destroy();
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




 function valthisform()
{
    var checkboxs=document.getElementsByName("chk");
  
    
    var form=document.getElementById("form1");
    var okay=false;
    for(var i=0,l=checkboxs.length;i<l;i++)
    {
        if(checkboxs[i].checked)
        {
            okay=true;
            break;
            
        }
       
    }
     
    if(okay){
       form.submit();

    }
    else alert("Please check a  At least One Checkbox");
}


function enable(i){
   
    var chk=document.getElementById("chk_"+i);
    var emp_id=document.getElementById("emp_id_"+i);
    var sts=document.getElementById("sts_"+i);


        if(chk.checked){
            emp_id.disabled=false;
            sts.disabled=false;
            
        }else{
             emp_id.disabled=true;
            sts.disabled=true;
        }

}



//for salary total

function getMonStaff(){ 
var emp_name=document.getElementById("staff").value;
 
    var http = new XMLHttpRequest();
        var url = '../../WHR0001_SERV';
        var params = 'value=5&reg_id='+emp_name;
        http.open('POST', url, true);

        //Send the proper header information along with the request
        http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');

        http.onreadystatechange = function() {//Call a function when the state changes.
            if(http.readyState === 4 && http.status === 200) {
            document.getElementById("payment").innerHTML=http.responseText;
            }
            
            
            
        };
        http.send(params);
    
}




//for attendance  view 

function getatt_emp_part(i)
  {
  
      var id=document.getElementById("emp_"+i).value;
      var  name_id=document.getElementById("emp_id");
      name_id.value=id;

      var http = new XMLHttpRequest();
        var url = '../../WHR0001_SERV';
        var params = 'value=6&id='+id;
        http.open('POST', url, true);

        //Send the proper header information along with the request
        http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');

        http.onreadystatechange = function() {//Call a function when the state changes.
            if(http.readyState === 4 && http.status === 200) {
                     $("#M_tab_M").DataTable().destroy();
                
            document.getElementById("m_tbody").innerHTML=http.responseText;
                          $("#M_tab_M").DataTable({
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
  




function show_emp_sal_det(i)
  {
  
      var id=document.getElementById("emp_id_det_"+i).value;
      var  card=document.getElementById("compete_details");
     

      var http = new XMLHttpRequest();
        var url = '../../WHR0001_SERV';
        var params = 'value=7&id='+id;
        http.open('POST', url, true);

        //Send the proper header information along with the request
        http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');

        http.onreadystatechange = function() {//Call a function when the state changes.
            if(http.readyState === 4 && http.status === 200) {
//                     $("#M_tab").DataTable().destroy();
                
            document.getElementById("sal_det").innerHTML=http.responseText;
            document.getElementById("compete_details").hidden=false;
//                          $("#M_tab").DataTable({
//            
//               
//            });
            }
            
            
            
        };
        http.send(params);
  }
  
  
//  for hr leaves 

function get_leaves(){

  
//   var usr=document.getElementById("assign").value; 
    var http = new XMLHttpRequest();
     var url = '../../WHR0001_SERV';
        var params = 'value=8';
        http.open('POST', url, true);

 
        http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
 http.onreadystatechange = function() {//Call a function when the state changes.
            if(http.readyState === 4 && http.status === 200) {
            document.getElementById("tbody").innerHTML=http.responseText;
         
            }
    };
    http.send(params); 
}



function leave_approve(i){

  
   var leave_id=document.getElementById("id_"+i).value; 
   var user_id=document.getElementById("user_id").value; 
    var http = new XMLHttpRequest();
     var url = '../../WHR0001_SERV';
        var params = 'value=9&leave_id='+leave_id+'&user_id='+user_id;
        http.open('POST', url, true);

 
        http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
 http.onreadystatechange = function() {//Call a function when the state changes.
            if(http.readyState === 4 && http.status === 200) {
          alert("Approved ");
        
         get_leaves();
            }
    };
    http.send(params); 
}


function leave_cancel(i){

  
   var leave_id=document.getElementById("id_"+i).value; 
   var user_id=document.getElementById("user_id").value; 
   var Reason_of_cancel=document.getElementById("Reason_of_cancel").value; 
    var http = new XMLHttpRequest();
     var url = '../../WHR0001_SERV';
        var params = 'value=10&leave_id='+leave_id+'&user_id='+user_id+'&Reason_of_cancel='+Reason_of_cancel;
        http.open('POST', url, true);

 
        http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
 http.onreadystatechange = function() {//Call a function when the state changes.
            if(http.readyState === 4 && http.status === 200) {
          alert("Cancled");
        
         get_leaves();
            }
    };
    http.send(params); 
}




//for geting dates b/w two dates 


function getdates(i){

   var stff_id=document.getElementById("id_"+i).value;
   var dep=document.getElementById("dep_"+i).value;
   var emp_id=document.getElementById("emp_id");
   var dep_id=document.getElementById("dep_id");
   emp_id.value=stff_id;
   dep_id.value=dep;
   
}

function live_date(){
    var date_from=document.getElementById("txtDate_from").value; 
   var Date_too=document.getElementById("txtDate_to").value; 
   var satff_id=document.getElementById("emp_id").value; 
   
 

  
    var http = new XMLHttpRequest();
     var url = '../../WHR0001_SERV';
        var params = 'value=11&date_from='+date_from+'&Date_too='+Date_too+'&satff_id='+satff_id;
        http.open('POST', url, true);

 
        http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
 http.onreadystatechange = function() {//Call a function when the state changes.
            if(http.readyState === 4 && http.status === 200) {
          document.getElementById("m_tbody").innerHTML=http.responseText;
            }
    };
    http.send(params); 
}



function advance_pay(i){
   var month=document.getElementById("month_"+i).value; 
   var emp_id=document.getElementById("emp_id_"+i).value; 
   var advance_month=document.getElementById("advance_month"); 
   var month_name_ad=document.getElementById("month_name_ad"); 
   var employyee_id=document.getElementById("employyee_id"); 
   var base_salary=document.getElementById("base_salary_"+i).value; 
   
   employyee_id.value=emp_id;
   advance_month.value=base_salary;
   month_name_ad.value=month;
   
}


function varpay(i){
    
    var chk=document.getElementById("chk_"+i);
    var final_sal=document.getElementById("final_sal"+i);
    var var_pay=document.getElementById("var_pay_"+i);
    var month=document.getElementById("month_"+i);
   var  emp_id=document.getElementById("emp_id_"+i);
   var  base_salary=document.getElementById("base_salary_"+i);
   var  month_name=document.getElementById("month_name_"+i);
   var  month_year=document.getElementById("year_name_"+i);

        if(chk.checked){
            final_sal.disabled=false;
            var_pay.disabled=false;
            month.disabled=false;
            emp_id.disabled=false;
            base_salary.disabled=false;
            month_name.disabled=false;
            month_year.disabled=false;
            
        }else{
             final_sal.disabled=true;
            var_pay.disabled=true;
            month.disabled=true;
            emp_id.disabled=true;
            base_salary.disabled=true;
            month_name.disabled=true;
            month_year.disabled=true;
        }
    
}



  function payment_mode(){
  
     var mode=document.getElementById("mode");
     var Pyem_mode=document.getElementById("Pyem_mode");
     var trans_id=document.getElementById("trans_id");
      var check=document.getElementById("check");
     
     if(mode.value==="Online"){
         Pyem_mode.hidden=false;
         trans_id.hidden=false;
         check.hidden=true;
         
     }else if(mode.value==="Check"){
         check.hidden=false;
          Pyem_mode.hidden=true;
         trans_id.hidden=true;
     }else{
  
         Pyem_mode.hidden=true;
         trans_id.hidden=true;
          check.hidden=true;
     }
}
  


//for salary slip genertae
function Slip_data(){

  var staff=document.getElementById("staff").value; 
//   var usr=document.getElementById("assign").value; 
    var http = new XMLHttpRequest();
     var url = '../../WHR0001_SERV';
        var params = 'value=16&staff='+staff;
        http.open('POST', url, true);

 
        http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
 http.onreadystatechange = function() {//Call a function when the state changes.
 
            if(http.readyState === 4 && http.status === 200) {
            document.getElementById("tbody").innerHTML=http.responseText;
  
     
            }
    };
    http.send(params); 
}
/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
function get_opd(){

  var hospital_code=document.getElementById("hospital_code").value; 
  var opd_date=document.getElementById("opd_date").value; 
  
//   var usr=document.getElementById("assign").value; 
    var http = new XMLHttpRequest();
     var url = '../../WOP0001_SERV';
        var params = 'value=2&hospital_code='+hospital_code+'&opd_date='+opd_date;
        http.open('POST', url, true);

 
        http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
 http.onreadystatechange = function() {//Call a function when the state changes.
            if(http.readyState === 4 && http.status === 200) {
            document.getElementById("tbody").innerHTML=http.responseText;
         
            }
    };
    http.send(params); 
}


function get_opd_rec(){

  var hospital_code=document.getElementById("hospital_code").value; 
  var opd_date=document.getElementById("opd_date").value; 
  var doc_code=document.getElementById("doc_code").value; 
  
//   var usr=document.getElementById("assign").value; 
    var http = new XMLHttpRequest();
     var url = '../../WOP0001_SERV';
        var params = 'value=9&hospital_code='+hospital_code+'&opd_date='+opd_date+'&doc_code='+doc_code;
        http.open('POST', url, true);

 
        http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
              $("#tab").DataTable().destroy();
 http.onreadystatechange = function() {//Call a function when the state changes.
            if(http.readyState === 4 && http.status === 200) {
            document.getElementById("tbody").innerHTML=http.responseText;
                $("#tab").DataTable({
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



function get_opd_doctor(){

  var hospital_code=document.getElementById("hospital_code").value; 
  var opd_date=document.getElementById("opd_date").value; 
  
//   var usr=document.getElementById("assign").value; 
    var http = new XMLHttpRequest();
     var url = '../../WOP0001_SERV';
        var params = 'value=3&hospital_code='+hospital_code+'&opd_date='+opd_date;
        http.open('POST', url, true);

 
        http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
 http.onreadystatechange = function() {//Call a function when the state changes.
            if(http.readyState === 4 && http.status === 200) {
            document.getElementById("tbody").innerHTML=http.responseText;
        
            }
    };
    http.send(params); 
}











function get_opd_patient_next_name(){

  var hospital_code=document.getElementById("hospital_code").value; 
  var opd_date=document.getElementById("opd_date").value; 
  
//   var usr=document.getElementById("assign").value; 
    var http = new XMLHttpRequest();
     var url = '../../WOP0001_SERV';
        var params = 'value=5&hospital_code='+hospital_code+'&opd_date='+opd_date;
        http.open('POST', url, true);

 
        http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
 http.onreadystatechange = function() {//Call a function when the state changes.
            if(http.readyState === 4 && http.status === 200) {
            document.getElementById("page_name").innerHTML=http.responseText;
            document.getElementById("card_name").innerHTML=http.responseText;
        
            }
    };
    http.send(params); 
}


function get_opd_patient_next_opd_id(){

  var hospital_code=document.getElementById("hospital_code").value; 
  var opd_date=document.getElementById("opd_date").value; 
  
//   var usr=document.getElementById("assign").value; 
    var http = new XMLHttpRequest();
     var url = '../../WOP0001_SERV';
        var params = 'value=6&hospital_code='+hospital_code+'&opd_date='+opd_date;
        http.open('POST', url, true);

 
        http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
 http.onreadystatechange = function() {//Call a function when the state changes.
            if(http.readyState === 4 && http.status === 200) {
                
           
            document.getElementById("opd_code").value=http.responseText;
            
        
            }
    };
    http.send(params); 
}






function get_opd_patient_next_age(){

  var hospital_code=document.getElementById("hospital_code").value; 
  var opd_date=document.getElementById("opd_date").value; 
  
//   var usr=document.getElementById("assign").value; 
    var http = new XMLHttpRequest();
     var url = '../../WOP0001_SERV';
        var params = 'value=8&hospital_code='+hospital_code+'&opd_date='+opd_date;
        http.open('POST', url, true);

 
        http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
 http.onreadystatechange = function() {//Call a function when the state changes.
            if(http.readyState === 4 && http.status === 200) {
                
    
            document.getElementById("opd_age").value=http.responseText;
            
        
            }
    };
    http.send(params); 
}








function add_to_prec(){
      
    var Diagonisis=document.getElementById("Diagonisis").value;
    var medicine_pre=document.getElementById("medicine_pre").value;
    var dosage=document.getElementById("medicine_dos").value;
    var Medicine_Additional=document.getElementById("medicine_Add").value;
    
    var Test=document.getElementById("Test").value;

    
    
    var tbody_medi=document.getElementById("tbody_medcine");
    
     document.getElementById("medicine_pre").value="";

    
 
  
    if(medicine_pre===""){
        alert("Plese Enter Medicine");
    }
    
    else{
     
    var http = new XMLHttpRequest();
    

        var http = new XMLHttpRequest();
        var url = '../../WOP0001_SERV';
        var params = 'value=4&Diagonisis='+Diagonisis+'&medicine_pre='+medicine_pre+'&Test='+Test;
        http.open('POST', url, true);
             
             
             
        //Send the proper header information along with the request
        http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');

        http.onreadystatechange = function() {//Call a function when the state changes.
            if(http.readyState === 4 && http.status === 200) {
               
           // document.getElementById("adms_tbody").innerHTML=http.responseText;
         
           $("#preci").append(http.responseText)
            
              perctable(medicine_pre,Diagonisis,dosage,Medicine_Additional);
            //  document.getElementById("prd_sale").appendChild(http.responseText);
           
            }
        };
        http.send(params);
    
}
}




function perctable(medicine,Diagonisis,dosage,Medicine_Additional){

     $("#perctable").append("\n\
 <tr>\n\
<td><input type='hidden' name='opd_medicine' value='"+medicine+"'>\n\
<input type='hidden' name='opd_Diagonisis' value='"+Diagonisis+"'>"+medicine+"\
 </td>\n\
<td><input type='hidden' name='Dosage' value='"+dosage+"'>"+dosage+"</td>\n\
<td><input type='hidden' name='Medicine_Additional' value='"+Medicine_Additional+"'>"+Medicine_Additional+"</td>\n\
<td><button class=\"btn btn-danger  btn-sm\"  \n\
onClick=\"$(this).closest('tr').remove();remove();\"><i class=\"fa fa-minus\"></i></button></td></tr>");
}


//for test advice 


function add_to_test(){
      
    var Diagonisis=document.getElementById("Diagonisis").value;
    var medicine_pre=document.getElementById("medicine_pre").value;
    var dosage=document.getElementById("medicine_dos").value;
    var Medicine_Additional=document.getElementById("medicine_Add").value;
    
    var Test=document.getElementById("Test").value;
    var Test_Additional=document.getElementById("test_additional").value;
    
    
    var tbody_medi=document.getElementById("tbody_medcine");
    
     document.getElementById("medicine_pre").value="";

    
 
  
    if(Test===""){
        alert("Please Enter Test ");
    }
    
    else{
     
    var http = new XMLHttpRequest();
    

        var http = new XMLHttpRequest();
        var url = '../../WOP0001_SERV';
        var params = 'value=4&Diagonisis='+Diagonisis+'&medicine_pre='+medicine_pre+'&Test='+Test;
        http.open('POST', url, true);
             
             
             
        //Send the proper header information along with the request
        http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');

        http.onreadystatechange = function() {//Call a function when the state changes.
            if(http.readyState === 4 && http.status === 200) {
               
           // document.getElementById("adms_tbody").innerHTML=http.responseText;
            
           
              test_table(Test,Test_Additional);
            //  document.getElementById("prd_sale").appendChild(http.responseText);
           
            }
        };
        http.send(params);
    
}
}


function test_table(Test,Test_Additional){
    
    

     $("#Test_Table_1").append("\n\
 <tr>\n\
<td><input type='hidden' name='Test_name' value='"+Test+"'>"+Test+"\n\
 </td>\n\
<td><input type='hidden' name='Test_additional' value='"+Test_Additional+"'>"+Test_Additional+"</td>\n\\n\
<td><button class=\"btn btn-danger  btn-sm\"  \n\
onClick=\"$(this).closest('tr').remove();remove();\"><i class=\"fa fa-minus\"></i></button></td></tr>");
}







function remove(){
     
 
    document.getElementById("preci").deleteRow(0);

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


//for cancling opd

function cancel_opd ( opd_id){
    var opd_id;
    
    var http = new XMLHttpRequest();
     var url = '../../WOP0001_SERV';
        var params = 'value=12&opd_id='+opd_id;
        http.open('POST', url, true);

 
        http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
 http.onreadystatechange = function() {//Call a function when the state changes.
            if(http.readyState === 4 && http.status === 200) {
                
    
           alert("OPD Cancled Succssfully ");
     location.reload();
           
            }
            
    };
    http.send(params); 
    
}


//for procedure 
function get_procedure_id(i){
   
    var opd_id=document.getElementById("id_o_"+i).value;
    var charge=document.getElementById("charge_"+i).value;
    var modal_opd=document.getElementById("modal_opd");
    var modal_charge=document.getElementById("modal_charge");

     modal_opd.value=opd_id;
     modal_charge.value=charge;
     
  
}


//for procedure modal 

function add_to_list() {



    var procedure_id = document.getElementById("procedure_id").value;

                           


    if (procedure_id !== "") {
   

        var name = $("#procedure_id option:selected").val();

        var http = new XMLHttpRequest();
        var http = new XMLHttpRequest();
        var url = '../../WOP0001_SERV';
        var params = 'value=13&procedure_id=' + procedure_id;
        http.open('POST', url, true);

        http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');

        http.onreadystatechange = function () {//Call a function when the state changes.
            if (http.readyState === 4 && http.status === 200) {


                $("#prd_sale").append(http.responseText);
                // $("#prd_sale").appendTo(http.responseText);
               final_bill_amount();
            }
        };
        http.send(params);
    } else {
        alert("Plese Select Procedure");
    }
}

//for procedure final bill Amount 

function final_bill_amount() {

    var final_bill_amt = 0;
    $(".total_amt").each(function () {
        final_bill_amt = final_bill_amt + parseFloat($(this).val());
    });

    $("#final_bil_amt").val(final_bill_amt);

    var extra_chr_amt = $("#extra_chr_amt").val();
    var finalPay_amt = parseFloat(final_bill_amt)-parseFloat(extra_chr_amt) ;

    if (isNaN(finalPay_amt)) {
        $("#finalPay_amt").val = 0;
    } else {
        $("#finalPay_amt").val(parseFloat(finalPay_amt));
    }


}

//for procedure 

function adjust_from_opd(){
    var chk=document.getElementById("adjust");
    var final_amount=document.getElementById("finalPay_amt").value;
    var modal_charge=document.getElementById("modal_charge").value;
    
    var rate_after_adjustmet=0;
    
    if(chk.checked){
        rate_after_adjustmet=parseFloat(final_amount) - parseFloat(modal_charge);
      
    }
    
    if(parseFloat(rate_after_adjustmet) < 0){
        document.getElementById("after_ad").innerHTML="<center><div class='col-md-2'><span class='badge bg-primary'>Return</span><input type='text' class='form-control' name='collect' value='"+Math.abs(rate_after_adjustmet)+"' readonly></div></center>";
    }if(parseFloat(rate_after_adjustmet) > 0){
           document.getElementById("after_ad").innerHTML="<center><div class='col-md-2'><span class='badge bg-success'>Collect</span><input type='text' name='return'  class='form-control' value='"+Math.abs(rate_after_adjustmet)+"' readonly></div></center>";
    }
}



// fre opd click 
function enbale(){
    var chk=document.getElementById("chk");
    var  input=document.getElementById("free");
    
    if(chk.checked){
        alert("Free OPD Enabled !!");
       input.disabled=false;
    }else{
         alert("Free OPD Disabled !!");
        input.disabled=true;
    }
}

function get_precription_id(i){
      
 var opd_id=document.getElementById("id_o_"+i).value;
         
    var http = new XMLHttpRequest();
     var url = '../../WOP0001_SERV';
        var params = 'value=14&opd_id='+opd_id;
        http.open('POST', url, true);

 
        http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
 http.onreadystatechange = function() {//Call a function when the state changes.
            if(http.readyState === 4 && http.status === 200) {
                
                document.getElementById("Medicine_Name").innerHTML=http.responseText;
    
            }
            
    };
    http.send(params); 
    
}


//for follow up pateinet details 
function follow_up_detail(){
    var doc=document.getElementById("selectuserrole_follow").value;
    
    var http = new XMLHttpRequest();
     var url = '../../WOP0001_SERV';
        var params = 'value=16&doc='+doc;
        http.open('POST', url, true);

 
        http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
 http.onreadystatechange = function() {//Call a function when the state changes.
            if(http.readyState === 4 && http.status === 200) {
                
                document.getElementById("tbody_follow_up").innerHTML=http.responseText;
 
 }
 
 };
 http.send(params); 
    
}


function create_follow_up(i){
    
   
    var opd_id=document.getElementById("opd_id_f_"+i).value;
    
     var last_visit=document.getElementById("visit_"+i).value;
     var doc_id=document.getElementById("doc_id_"+i).value;
    
    var http = new XMLHttpRequest();
     var url = '../../WOP0001_SERV';
        var params = 'value=17&opd_id='+opd_id+'&last_visit='+last_visit+'&doc_id='+doc_id;
        http.open('POST', url, true);

 
        http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
 http.onreadystatechange = function() {//Call a function when the state changes.
            if(http.readyState === 4 && http.status === 200) {
                
                alert("Follow Up Created successfully");
                  location.reload();
 }
 
 };
 http.send(params); 
    
}

 
 //for doctor wise OPD
 
 function get_opd_doctor_Wise(){

  var hospital_code=document.getElementById("hospital_code").value; 
  var doc_code=document.getElementById("doc_code").value; 
  
//   var usr=document.getElementById("assign").value; 
    var http = new XMLHttpRequest();
     var url = '../../WOP0001_SERV';
        var params = 'value=3&hospital_code='+hospital_code+'&opd_date='+doc_code;
        http.open('POST', url, true);

 
        http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
 http.onreadystatechange = function() {//Call a function when the state changes.
            if(http.readyState === 4 && http.status === 200) {
            document.getElementById("tbody_doc_wise_opd").innerHTML=http.responseText;
        
            }
    };
    http.send(params); 
}


//for geeting doc fees on opd 
function  get_doc_fees (){
    var doc_code=document.getElementById("doc_id_code").value;
   
    var http = new XMLHttpRequest();
     var url = '../../WOP0001_SERV';
        var params = 'value=19&doc_id_code='+doc_code;
        http.open('POST', url, true);

 
        http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
 http.onreadystatechange = function() {//Call a function when the state changes.
            if(http.readyState === 4 && http.status === 200) {
             document.getElementById("doc_fees").value=http.responseText;
            }
    };
    http.send(params); 
    
}


//opd refund

function Refund_opd(i){

    
    var opd_id=document.getElementById("id_o_"+i).value;
    var name=document.getElementById("id_"+i).value;
    var charge=document.getElementById("charge_"+i).value;
    
    var opd_amount=document.getElementById("OPD_AMOUNT");
    var opd_amount_refunded=document.getElementById("AMOUNT_REFUNDED").value;
    var amount_Left =document.getElementById("FINAL_AMOUNT").value;
    
    
     document.getElementById("Patient_name_on_opd").innerHTML=opd_id;  
     document.getElementById("OPD_REFUND_Patient").value=opd_id;
    
    alert (charge);
    
    opd_amount.value=charge;
    
   
}


function Refund_cal(){
    
    var opd_amount=document.getElementById("OPD_AMOUNT").value;
    var opd_amount_refunded=document.getElementById("AMOUNT_REFUNDED").value;
    var amount_Left =document.getElementById("FINAL_AMOUNT");
    
    
     if(opd_amount_refunded >=opd_amount){
         
         alert("Refund Amount Should Not Greter Then "+opd_amount+"");
         
        
     }else{ 
     
  amount_Left.value= parseFloat(opd_amount)-parseFloat(opd_amount_refunded);
     }
     
}

         //for geeting TPA 
        
    
   function get_TPA_Details(){
       
       var TPA=document.getElementById("TPA_DETAILS");
       var Admission_Fess=document.getElementById("Admission_Fess");
       
       
       if(document.getElementById("TPA_DETAILS").value==""){
            document.getElementById("Admission_Fess").value="0";
        document.getElementById("Admission_Fess").hidden=false;
         
       
       }else{
          
             document.getElementById("Admission_Fess").hidden=true;
              alert("TPA Mode Selected");
       }
         
   } 
    
    
    

 
         
         
         

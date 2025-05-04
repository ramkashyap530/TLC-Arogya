/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


function get_category()
  {
      
      var http = new XMLHttpRequest();
        var url = '../../WINV0001_serv';
        var params = 'value=2';
        http.open('POST', url, true);

        //Send the proper header information along with the request
        http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');

        http.onreadystatechange = function() {//Call a function when the state changes.
            if(http.readyState === 4 && http.status === 200) {
                 $("#tab").DataTable().destroy();
            document.getElementById("tbody").innerHTML=http.responseText;
            $("#tab").DataTable({
        "scrollY":"400px",
        "scrollCollapse": true,
        "paging":true,
         "scrollX": true
              
                });
            }
            
            
            
        };
        http.send(params);
  }
  
  
  function  getInventoryDetails()
{
    
  
          var inv_cat=document.getElementById("inv_cat").value;
          var item_name=document.getElementById("item_name").value;

         var prbar=document.getElementById("progress");
         prbar.value=10;
         prbar.style.display = "block";
        var http = new XMLHttpRequest();
        var url = '../../WINV0001_serv';
        var params = 'value=4&inv_cat='+inv_cat+'&item_name='+item_name;
        http.open('POST', url, true);

        //Send the proper header information along with the request
        http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');

        http.onreadystatechange = function() {//Call a function when the state changes.
            if(http.readyState === 4 && http.status === 200) {
               
           // document.getElementById("adms_tbody").innerHTML=http.responseText;
            
             prbar.value=100;
            
              
              document.getElementById("tableBody").innerHTML=http.responseText;
            
                
            prbar.style.display = "none";
            
            }
        };
        http.send(params);
       
}


function opemValidation(node,i)
{
            if($(node).prop("checked") == true){
                
                $("#ivn_cat_id"+i).prop('disabled', false);
                $("#item_code"+i).prop('disabled', false);
                $("#receiver_name"+i).prop('disabled', false);
                $("#item_qty"+i).prop('disabled', false);
                $("#sender_name"+i).prop('disabled', false);
                $("#remark"+i).prop('disabled', false);
            }
            else if($(node).prop("checked") == false){
                
                $("#ivn_cat_id"+i).prop('disabled', true);
                $("#item_code"+i).prop('disabled', true);
                $("#receiver_name"+i).prop('disabled', true);
                $("#item_qty"+i).prop('disabled', true);
                $("#sender_name"+i).prop('disabled', true);
                $("#remark"+i).prop('disabled', true);
            }
}
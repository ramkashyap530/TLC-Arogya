function get_TPA(){
//
//  var hospital_code=document.getElementById("hospital_code").value; 
//   var usr=document.getElementById("assign").value; 
    var http = new XMLHttpRequest();
     var url = '../../WTPA0001_Serv';
        var params = 'value=2';
        http.open('POST', url, true);

  
        http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
 http.onreadystatechange = function() {//Call a function when the state changes.
            if(http.readyState === 4 && http.status === 200) {
            document.getElementById("tbody").innerHTML=http.responseText;
    
            }
    };
    http.send(params); 
}

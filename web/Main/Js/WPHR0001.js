/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */



function conversion() {
    var pur_unit = document.getElementById("purchase_unit");
    var sal_unit = document.getElementById("sale_untit");
    var con_id = document.getElementById("con_id");


    if (sal_unit.value == pur_unit.value) {
        alert("Purcchase Unit and Sale unit will Not be same !!");
        sal_unit.value == "";
    } else {
        con_id.innerHTML = "<div class='row'><div class='col-md-3' id='perchase_unit_val' name='perchase_unit_val'> 1 " + pur_unit.value + "   </div>\n\
                                  <div class='col-md-1'> = </div> \n\
 <div class='col-md-3'><input type='text' class='form-control'> </div> <div class='col-md-1'>" + sal_unit.value + "</div></div>\n\
<div ><hr></div>\n\
<div class='row'><div class='col-md-3'> 1 " + sal_unit.value + "   </div>\n\
                                  <div class='col-md-1'> = </div>\n\
 <div class='col-md-3'><input type='text' class='form-control' id='per_piece'> </div> <div class='col-md-1'>Unit</div></div>"
                ;
    }

}




function sale_price_cost() {
    var perunit = document.getElementById("per_piece").value;
    var purcahse_cost = document.getElementById("purchase_price").value;
    var Tax = document.getElementById("Tax").value;
    var sale_price = document.getElementById("sale");


    var sal = parseFloat(Tax) / 100 * parseFloat(purcahse_cost);

    var tax_amount = parseFloat(sal) + parseFloat(purcahse_cost);

    var sal_amount = parseFloat(tax_amount) / parseFloat(perunit);

    sale_price.value = parseFloat(sal_amount);


}





//for pharmacy billing only 

function add_to_bill() {



    var prd_cd = document.getElementById("prd_cd").value;
    var qty = document.getElementById("qty").value;


    if (qty !== "") {

        var name = $("#prd_cd option:selected").val();




        var http = new XMLHttpRequest();
        var http = new XMLHttpRequest();
        var url = '../../WPHR0001_SERV';
        var params = 'value=2&prd_cd=' + prd_cd + '&qty=' + qty + '&name=' + name;
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
        alert("Plese Fill Qty");
    }
}



function getCluAfterAdd(prd_cd, qty)
{

    var x = prd_cd.value;

    var tot = parseFloat(qty) * (parseFloat(x));
    document.getElementById("total_" + prd_cd).value = tot;
    // $("#total_"+prd_cd).val(tot);
    final_bill_amount();
}



function multi(prd_cd, node) {

    var qty = document.getElementById("qty" + prd_cd).value;
    var rate = $(node).val();


    var final_tot = 0;

    final_tot = parseFloat(rate) * parseInt(qty);

    document.getElementById("total_" + prd_cd).value = final_tot;

    final_bill_amount();
}





function final_bill_amount() {

    var final_bill_amt = 0;
    $(".total_amt").each(function () {
        final_bill_amt = final_bill_amt + parseFloat($(this).val());
    });

    $("#final_bil_amt").val(final_bill_amt);

    var extra_chr_amt = $("#extra_chr_amt").val();
    var finalPay_amt = parseFloat(extra_chr_amt) + parseFloat(final_bill_amt);

    if (isNaN(finalPay_amt)) {
        $("#finalPay_amt").val = 0;
    } else {
        $("#finalPay_amt").val(parseFloat(finalPay_amt));
    }




}


//ends here 



//for pharmacy stock 

function stock()

{

    var item_name = document.getElementById("item_name").value;

    var item_code = document.getElementById("item_code").value;
//

    var http = new XMLHttpRequest();
    var url = '../../WPHR0001_SERV';
    var params = 'value=3&item_name=' + item_name + '&item_code=' + item_code;
    http.open('POST', url, true);

    //Send the proper header information along with the request
    http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');

    http.onreadystatechange = function () {//Call a function when the state changes.
        if (http.readyState === 4 && http.status === 200) {
            $("#tab").DataTable().destroy();
            document.getElementById("tbody").innerHTML = http.responseText;
            $("#tab").DataTable({
                "scrollY": "400px",
                "scrollCollapse": true,
                "paging": true,
                "scrollX": true,

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


//  for pharmacy Rate

function phr_rate()

{

    var item_name = document.getElementById("item_name").value;

    var item_code = document.getElementById("item_code").value;
//

    var http = new XMLHttpRequest();
    var url = '../../WPHR0001_SERV';
    var params = 'value=4&item_name=' + item_name + '&item_code=' + item_code;
    http.open('POST', url, true);

    //Send the proper header information along with the request
    http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');

    http.onreadystatechange = function () {//Call a function when the state changes.
        if (http.readyState === 4 && http.status === 200) {
            $("#tab").DataTable().destroy();
            document.getElementById("tbody").innerHTML = http.responseText;
            $("#tab").DataTable({
                "scrollY": "400px",
                "scrollCollapse": true,
                "paging": true,
                "scrollX": true,

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



function phr_bill()

{

    var item_name = document.getElementById("item_name").value;

    var item_code = document.getElementById("item_code").value;
//

    var http = new XMLHttpRequest();
    var url = '../../WPHR0001_SERV';
    var params = 'value=6&item_name=' + item_name + '&item_code=' + item_code;
    http.open('POST', url, true);

    //Send the proper header information along with the request
    http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');

    http.onreadystatechange = function () {//Call a function when the state changes.
        if (http.readyState === 4 && http.status === 200) {
            $("#tab").DataTable().destroy();
            document.getElementById("tbody").innerHTML = http.responseText;
            $("#tab").DataTable({
                "scrollY": "400px",
                "scrollCollapse": true,
                "paging": true,
                "scrollX": true,

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



//  for bill type


function bill_type_bi() {
    var bill_type_bill = document.getElementById("bill_type");
    var ipd = document.getElementById("bill_ipd");
    var opd = document.getElementById("bill_opd");


    if (bill_type_bill.value === "IPD") {
        ipd.hidden = false;
        opd.hidden = true;
    }
    if (bill_type_bill.value === "OPD") {
        ipd.hidden = true;
        opd.hidden = false;
    }
    if (bill_type_bill.value === "OTHER") {
        ipd.hidden = true;
        opd.hidden = true;
    }


}




//for suggestiin system in pharchmacy billing 

let suggestions = [
    "Channel",
    "CodingLab",
    "CodingNepal",
    "YouTube",
    "YouTuber",
    "YouTube Channel",
    "Blogger",
    "Bollywood",
    "Vlogger",
    "Vechiles",
    "Facebook",
    "Freelancer",
    "Facebook Page",
    "Designer",
    "Developer",
    "Web Designer",
    "Web Developer",
    "Login Form in HTML & CSS",
    "How to learn HTML & CSS",
    "How to learn JavaScript",
    "How to became Freelancer",
    "How to became Web Designer",
    "How to start Gaming Channel",
    "How to start YouTube Channel",
    "What does HTML stands for?",
    "What does CSS stands for?",
];



//for seraching field

// getting all required elements
const searchWrapper = document.querySelector(".search-input");
const inputBox = searchWrapper.querySelector("input");
const suggBox = searchWrapper.querySelector(".autocom-box");
const icon = searchWrapper.querySelector(".icon");
let linkTag = searchWrapper.querySelector("a");
let webLink;

// if user press any key and release
inputBox.onkeyup = (e)=>{
    let userData = e.target.value; //user enetered data
    let emptyArray = [];
    if(userData){
        icon.onclick = ()=>{
            webLink = `https://www.google.com/search?q=${userData}`;
            linkTag.setAttribute("href", webLink);
            linkTag.click();
        }
        emptyArray = suggestions.filter((data)=>{
            //filtering array value and user characters to lowercase and return only those words which are start with user enetered chars
            return data.toLocaleLowerCase().startsWith(userData.toLocaleLowerCase());
        });
        emptyArray = emptyArray.map((data)=>{
            // passing return data inside li tag
            return data = `<li>${data}</li>`;
        });
        searchWrapper.classList.add("active"); //show autocomplete box
        showSuggestions(emptyArray);
        let allList = suggBox.querySelectorAll("li");
        for (let i = 0; i < allList.length; i++) {
            //adding onclick attribute in all li tag
            allList[i].setAttribute("onclick", "select(this)");
        }
    }else{
        searchWrapper.classList.remove("active"); //hide autocomplete box
    }
}

function select(element){
    let selectData = element.textContent;
    inputBox.value = selectData;
    icon.onclick = ()=>{
        webLink = `https://www.google.com/search?q=${selectData}`;
        linkTag.setAttribute("href", webLink);
        linkTag.click();
    }
    searchWrapper.classList.remove("active");
}

function showSuggestions(list){
    let listData;
    if(!list.length){
        userValue = inputBox.value;
        listData = `<li>${userValue}</li>`;
    }else{
      listData = list.join('');
    }
    suggBox.innerHTML = listData;
}

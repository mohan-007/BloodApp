<?php


$response = array();


if (isset($_POST['namepar']) && isset($_POST['addrpar']) && isset($_POST['distpar']) && isset($_POST['phonepar'])
&& isset($_POST['agepar'])&& isset($_POST['bloodpar'])&& isset($_POST['genderpar'])&& isset($_POST['lastdatebloodpar'])) {
    
    $namepar = $_POST['namepar'];
    $addrpar = $_POST['addrpar'];
    $distpar = $_POST['distpar'];
    $pinno=$_POST['pinno'];
    $phonepar=$_POST['phonepar'];
$agepar=$_POST['agepar'];
$bloodpar=$_POST['bloodpar'];
$genderpar=$_POST['genderpar'];
$lastdatebloodpar=$_POST['lastdatebloodpar'];
$imei=$_POST['imei'];
$date = str_replace('/', '-', $lastdatebloodpar);
$new=date('Y-m-d', strtotime($date));




    require_once __DIR__ . '/db_connect.php';


    $db = new DB_CONNECT();

 

   $result = mysql_query("INSERT INTO regtable(`tabname`, `tabaddr`, `tabdistr`, `Pincode`, `tabph`, `tabage`, `tabbloodgrp`, `tabgender`, `tablast`,`tabimei`) VALUES ('$namepar','$addrpar','$distpar','$pinno','$phonepar','$agepar','$bloodpar','$genderpar','$new','$imei')");


  
    if ($result) {
      
        $response["success"] = 1;
        $response["message"] = "donar successfully created.";

       
        echo json_encode($response);
    } else {
       
       $response["message"] = "An error occurred.";
        
       
        echo json_encode($response);
    }
} else {
  
    $response["success"] = 0;
  $response["message"] = "Required field(s) is missing";

   
    echo json_encode($response);
}
?>

<?php
$response = array();
$response2 = array();

if (isset($_POST['uname']) && isset($_POST['phno'])) {
 $uname = $_POST['uname'];
    $phno = $_POST['phno'];}
 
require_once __DIR__ . '/db_connect.php';
$db = new DB_CONNECT();
$result = mysql_query("SELECT *FROM regtable where tabname='$uname' and tabph='$phno'") or die(mysql_error());
if (mysql_num_rows($result) > 0)
{


 $response["products"] = array();
    
    while ($row = mysql_fetch_array($result)) {
       
        $product = array();
        $product["ID"] = $row["ID"];
        $product["tabname"] = $row["tabname"];
 $product["tabaddr"] = $row["tabaddr"];
 $product["tabage"] = $row["tabage"];
 $product["tabph"] = $row["tabph"];
 $product["tabbloodgrp"] = $row["tabbloodgrp"];
 $product["tablast"] = $row["tablast"];
 $product["tabdistr"] = $row["tabdistr"];
 $product["tabgender"] = $row["tabgender"];
      



     
        array_push($response["products"], $product);
}

   
 $response["success"] = $product["ID"];
    echo json_encode($response);
 echo json_encode($response2);}



 else {
   
    $response["success"] = 0;
    $response["message"] = "No products found";

 
    echo json_encode($response);
  echo json_encode($response2);
}
?>
}

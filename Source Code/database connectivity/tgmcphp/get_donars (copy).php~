<?php


$response = array();
$response2 = array();

require_once __DIR__ . '/db_connect.php';

 $name = 'theni';
    $group = 'A1+ve';

$db = new DB_CONNECT();


$result = mysql_query("SELECT *FROM regtable where tabdistr='$name' and tabbloodgrp='$group'") or die(mysql_error());

if (1> 0) {
  
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
$product["Pincode"]=$row["Pincode"];
      



       
        array_push($response["products"], $product);
    }

    $response["success"] = 1;

    
    echo json_encode($response);
} else {

$response["products"] = array();
$product = array();
        $product["ID"] = "No results found";
        $product["tabname"] = "No results found";
 $product["tabaddr"] = "No results found";
 $product["tabage"] = "No results found";
 $product["tabph"] = "No results found";
 $product["tabbloodgrp"] = "No results found";
 $product["tablast"] = "No results found";
 $product["tabdistr"] = "No results found";
 $product["tabgender"] = "No results found";
$product["Pincode"]="No results found";
      



       
        array_push($response["products"], $product);
    
   
    $response["success"] = 0;
    $response["message"] = "No products found";

  
    echo json_encode($response2);
    echo json_encode($response);
}
?>

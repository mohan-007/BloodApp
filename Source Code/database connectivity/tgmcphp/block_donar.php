<?php


$response = array();

$namepar = $_POST['namepar'];
$passwordpar = $_POST['passwordpar'];
 require_once __DIR__ . '/db_connect.php';


$db = new DB_CONNECT();

$result = mysql_query("UPDATE regtable SET tagblockrate = tagblockrate+1 WHERE tabname='$namepar' and tabph='$passwordpar'");
if ($result) {
       
        $response["success"] = 1;
        $response["message"] = "donar successfully blocked.";

       
        echo json_encode($response);
    } else {

       $response["message"] = " An error occurred.";
        
   
        echo json_encode($response);
    }
?>




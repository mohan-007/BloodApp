<?php
$response = array();
$id=$_POST['pid'];
$lastdate=$_POST['lastdate'];
$phoneno=$_POST['phoneno'];
$curaddress=$_POST['curaddress'];
$date = str_replace('/', '-', $lastdate);
$new=date('Y-m-d', strtotime($date));


require_once __DIR__ . '/db_connect.php';
  $db = new DB_CONNECT();
$result = mysql_query("UPDATE regtable SET tablast = '$new', tabph = '$phoneno', tabaddr = '$curaddress' WHERE ID = $id");
if ($result) {
       
        $response["success"] = 1;
        $response["message"] = "donar successfully updated.";

       
        echo json_encode($response);
    } else {

       $response["message"] = " An error occurred.";
        
   
        echo json_encode($response);
    }
?>


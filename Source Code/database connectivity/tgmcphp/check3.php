<?php
$var='22/3/2013';
$date = str_replace('/', '-', $var);
$new=date('Y-m-d', strtotime($date));
echo DATEDIFF('2008-11-30','2008-2-21');
echo $new;
?>

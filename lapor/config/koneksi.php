<?php
// 	mysql_connect("localhost","micq6558_a","micq6558_db");
// 	mysql_select_db("micq6558_event");
$hostname='localhost';
$username='root';
$password='';
$server = "localhost";
$database = "lapor_db";
date_default_timezone_set('Asia/Jakarta');
$dbh = new PDO("mysql:host=$hostname;dbname=lapor_db",$username,$password);
$con =mysqli_connect($server,$username,$password,$database) or die("Koneksi gagal");

$dbh->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION); // <== add this line
//echo 'Connected to Database<br/>';
?>
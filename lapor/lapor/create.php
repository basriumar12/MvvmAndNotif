<?php
require_once('../config/koneksi.php');

if(
  
   isset($_POST['nama_pelapor']) &&
   isset($_POST['lokasi']) &&
   isset($_POST['deksripsi_lapor']) &&
   isset($_POST['tanggal_lapor']) &&
   isset($_POST['gambar'])   ){
	   
	$nama_pelapor = $_POST['nama_pelapor'];
	$lokasi = $_POST['lokasi'];
	$deksripsi_lapor = $_POST['deksripsi_lapor'];
	$tanggal_lapor = $_POST['tanggal_lapor'];
	$gambar = $_POST['gambar'];
	

		try {
			$sql="INSERT INTO `tb_lapor` ( `nama_pelapor`, `lokasi`, 
			`deksripsi_lapor`, `tanggal_lapor`, 
			`gambar`
			) VALUES ('".$nama_pelapor."', 
			'".$lokasi."', 
			'".$deksripsi_lapor."', 
			'".$tanggal_lapor."', 
			'".$gambar."'
	
			);";
			$ss = $dbh->prepare($sql);
			$ss->execute();
			if($ss){
				
				$json['message'] = "Success Create Data";
							$json['success'] = true;
							echo json_encode($json);
	                
			} else{
				$json['message']='Error';
				$json['success'] = false;
				
				echo json_encode($json);
			}

		} catch(PDOException $e){
			$json["success"] = false;
			$json["message"] = $e->getMessage();
			echo json_encode($json);
		}
	
} else{
	$json["success"] = false;
    $json["message"] = 'Inputan salah';
    echo json_encode($json);
}
?> 
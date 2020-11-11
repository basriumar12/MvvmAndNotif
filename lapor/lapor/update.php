<?php 
 require_once './config/koneksi.php';

 if($_SERVER['REQUEST_METHOD'] == 'POST')
 {
 	
 //parameter post
 	$id_lapor = $_POST['id_lapor'];
 	$nama_pelapor = $_POST['nama_pelapor'];
	$lokasi = $_POST['lokasi'];
	$deksripsi_lapor = $_POST['deksripsi_lapor'];
	$tanggal_lapor = $_POST['tanggal_lapor'];
	$gambar = $_POST['gambar'];

//update
 	/*echo "UPDATE  tb_resep 
 	SET nama_resep = '$nama_resep',
 	detail = '$detail', 
 	gambar = '$gambar'
 	 WHERE id_resep='$id_resep'";*/
 	 
 	$query = "UPDATE  tb_lapor 
 	SET nama_pelapor = '$nama_pelapor',
 	lokasi = '$lokasi',
     deksripsi_lapor = '$deksripsi_lapor',
     tanggal_lapor = '$tanggal_lapor', 
 	gambar = '$gambar'
 	 WHERE id='$id_lapor'";

 	$exeQuery = mysqli_query($con, $query); 

 	echo ($exeQuery) ? json_encode(array('kode' =>1, 'pesan' => 'data berhasil update')) :  json_encode(array('kode' =>2, 'pesan' => 'data gagal diupdate'));
 }
 else
 {
 	 echo json_encode(array('kode' =>101, 'pesan' => 'request tidak valid'));
 }

 ?>

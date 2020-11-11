<?php
require_once('../config/koneksi.php');

		try {
			$sql="select * from tb_lapor";
			$ss = $dbh->prepare($sql);
			$ss->execute();
			$data=$ss->fetchAll(PDO::FETCH_OBJ);
			$size=$ss->rowCount();
			if($size > 0){
				$json['lapor']=$data;
				$json['message'] = "Success Load Data";
				$json['success'] = true;
				echo json_encode($json);
				
			} else{
				$json['message']='Error Load Data';
				$json['success'] = false;
				
				echo json_encode($json);
			}

		} catch(PDOException $e){
			$json["success"] = false;
			$json["message"] = $e->getMessage();
			echo json_encode($json);
		}
	
?> 
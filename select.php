<?php
$res=array();
$s = "localhost"; $u = "root"; $p = ""; $db = "a3";
$con = new mysqli($s, $u, $p, $db);
if ($con->connect_error) {
    die("Connection failed: " . $con->connect_error);
}
$sql = "SELECT * FROM sanpham1";
$result = $con->query($sql); //doc duLieu va tra ve result
if ($result->num_rows>0) {  // so dong lon hon 0
    $res['products'] = array(); 
    while ($row=$result->fetch_assoc()) { // doc tung dong
        $product = array();
        $product['MaSP'] =$row['MaSP'];
        $product['TenSP'] =$row['TenSP'];
        $product['MoTa'] =$row['MoTa'];
        array_push($res['products'],$product); // day du lieu doc duoc vao mang
    }
    $res['success']=1;
    $res['message']= "Select thành công";
    echo json_encode($res);
} else {
    $res['success']=0;
    $res['message']= "Select kh thành công";
    echo json_encode($res);
}
$con->close();
// http://localhost/2024071/insert1.php?MaSP=SP04&TenSP=san pham 5&MoTa= Mo ta san pham 4

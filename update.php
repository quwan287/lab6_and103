<?php
$res=array();
$s = "localhost"; $u = "root"; $p = ""; $db = "a3";
$con = new mysqli($s, $u, $p, $db);
$MaSP = $_POST['MaSP'];
$TenSP = $_POST['TenSP'];
$MoTa = $_POST['MoTa'];
if ($con->connect_error) {
    die("Connection failed: " . $con->connect_error);
}
$sql = "UPDATE sanpham1 SET  TenSP= '$TenSP', MoTa='$MoTa' WHERE MaSP='$MaSP'";
if ($con->query($sql) === TRUE) {
    $res['success']=1;
    $res['message']= "Update thành công";
    echo json_encode($res);
} else {
    $res['success']=0;
    $res['message']= "Update kh thành công";
    echo json_encode($res);
}
$con->close();
// http://localhost/2024071/insert1.php?MaSP=SP04&TenSP=san pham 5&MoTa= Mo ta san pham 4

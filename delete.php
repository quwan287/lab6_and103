<?php
$res=array();
$s = "localhost"; $u = "root"; $p = ""; $db = "a3";
$con = new mysqli($s, $u, $p, $db);
$MaSP = $_POST['MaSP'];
if ($con->connect_error) {
    die("Connection failed: " . $con->connect_error);
}
$sql = "DELETE FROM sanpham1 WHERE MaSP= '$MaSP'";
if ($con->query($sql) === TRUE) {
    $res['success']=1;
    $res['message']= "Xóa thành công";
    echo json_encode($res);
} else {
    $res['success']=0;
    $res['message']= "Xóa kh thành công";
    echo json_encode($res);
}
$con->close();
// http://localhost/2024071/delete.php?MaSP=SP04&TenSP=san pham 5&MoTa= Mo ta san pham 4
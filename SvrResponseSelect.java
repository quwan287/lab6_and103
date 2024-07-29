package fpl.quangnm.myapplication.demo5.select;

import fpl.quangnm.myapplication.demo5.SanPham;

public class SvrResponseSelect {
    private SanPham[] products; // Đảm bảo rằng tên thuộc tính và kiểu dữ liệu khớp với phản hồi từ server
    private String message;

    public SanPham[] getProducts() {
        return products;
    }

    public String getMessage() {
        return message;
    }
}

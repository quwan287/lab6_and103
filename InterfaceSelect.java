package fpl.quangnm.myapplication.demo5.select;

import retrofit2.Call;
import retrofit2.http.GET;

public interface InterfaceSelect {
    @GET("select.php")
    Call<SvrResponseSelect> selectSanPham();
}

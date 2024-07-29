package fpl.quangnm.myapplication.demo5.delete;


import fpl.quangnm.myapplication.demo5.SvrResponseSanPham;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface InterfaceDelete {
    @FormUrlEncoded
    @POST("delete.php")
    Call<SvrResponseSanPham> deleleteSanPham(
            @Field("MaSP") String MaSP
    );
}

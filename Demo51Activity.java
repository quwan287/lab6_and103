package fpl.quangnm.myapplication.demo5;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fpl.quangnm.myapplication.R;
import fpl.quangnm.myapplication.demo5.delete.InterfaceDelete;
import fpl.quangnm.myapplication.demo5.select.InterfaceSelect;
import fpl.quangnm.myapplication.demo5.select.SvrResponseSelect;
import fpl.quangnm.myapplication.demo5.update.InterfaceUpdate;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Demo51Activity extends AppCompatActivity {
    EditText txt1, txt2, txt3;
    TextView tvKQ;
    Button btn1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo51);
        txt1 = findViewById(R.id.demo51_txt1);
        txt2 = findViewById(R.id.demo51_txt2);
        txt3 = findViewById(R.id.demo51_txt3);
        tvKQ = findViewById(R.id.demo51_Tv1);
        btn1 = findViewById(R.id.demo51_btn1);

        btn1.setOnClickListener(view -> {
             //insertData(txt1,txt2,txt3,tvKQ);
             selectData();
             //DeleteData(txt1);
             //updateData(txt1,txt2,txt3,tvKQ);
        });
    }
    public void insertData(EditText txt1, EditText txt2, EditText txt3, TextView tvKQ) {
        // Tạo đối tượng lưu trữ
        SanPham s = new SanPham(txt1.getText().toString(), txt2.getText().toString(), txt3.getText().toString());
        // Tạo đối tượng Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.24.45.122/2024071/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        // Gọi hàm insert
        InterfaceInsertSP insertSanPham = retrofit.create(InterfaceInsertSP.class);
        Call<SvrResponseSanPham> call = insertSanPham.insertSPCall(s.getMaSP(), s.getTenSP(), s.getMoTa());
        call.enqueue(new Callback<SvrResponseSanPham>() {
            @Override
            public void onResponse(Call<SvrResponseSanPham> call, Response<SvrResponseSanPham> response) {
                if (response.body() != null) {
                    SvrResponseSanPham res = response.body();
                    tvKQ.setText(res.getMessage());
                } else {
                    tvKQ.setText("Response body is null");
                }
            }

            @Override
            public void onFailure(Call<SvrResponseSanPham> call, Throwable t) {
                tvKQ.setText(t.getMessage());
            }
        });
    }

    private void updateData(EditText txt1, EditText txt2, EditText txt3, TextView tvKQ){
        //B1. tao doi tuong chua du lieu
        SanPham s=new SanPham(txt1.getText().toString(),
                txt2.getText().toString(),txt3.getText().toString());
        //b2. tao doi tuong retrofit
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("http://10.24.45.122/2024071/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //b3. goi ham insert
        InterfaceUpdate updateSanPham
                =retrofit.create(InterfaceUpdate.class);
        //chuan bi ham
        Call<SvrResponseSanPham> call
                =updateSanPham.updateSanPham(s.getMaSP(),s.getTenSP(),s.getMoTa());
        //thuc thi ham
        call.enqueue(new Callback<SvrResponseSanPham>() {
            //thanh cong
            @Override
            public void onResponse(Call<SvrResponseSanPham> call, Response<SvrResponseSanPham> response) {
                SvrResponseSanPham res=response.body();
                tvKQ.setText(res.getMessage());
            }
            //that bai
            @Override
            public void onFailure(Call<SvrResponseSanPham> call, Throwable t) {
                tvKQ.setText(t.getMessage());
            }
        });

    }
    private void DeleteData(EditText txt1){
        //b2. tao doi tuong retrofit
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("http://10.24.45.122/2024071/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //b3. goi ham insert
        InterfaceDelete deleteSanPham
                =retrofit.create(InterfaceDelete.class);
        //chuan bi ham
        Call<SvrResponseSanPham> call
                =deleteSanPham.deleleteSanPham(txt1.getText().toString());
        //thuc thi ham
        call.enqueue(new Callback<SvrResponseSanPham>() {
            //thanh cong
            @Override
            public void onResponse(Call<SvrResponseSanPham> call, Response<SvrResponseSanPham> response) {
                SvrResponseSanPham res=response.body();
                tvKQ.setText(res.getMessage());
            }
            //that bai
            @Override
            public void onFailure(Call<SvrResponseSanPham> call, Throwable t) {
                tvKQ.setText(t.getMessage());
            }
        });

    }
    String strKQ="";
    List<SanPham> ls;
    private void selectData() {
        strKQ = "";
        // Tạo đối tượng Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.24.45.122/2024071/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        // Gọi hàm select trong interface
        InterfaceSelect select = retrofit.create(InterfaceSelect.class);
        Call<SvrResponseSelect> call = select.selectSanPham();
        call.enqueue(new Callback<SvrResponseSelect>() {
            @Override
            public void onResponse(Call<SvrResponseSelect> call, Response<SvrResponseSelect> response) {
                if (response.body() != null) {
                    SvrResponseSelect res = response.body();
                    ls = new ArrayList<>(Arrays.asList(res.getProducts()));
                    // Đọc list
                    for (SanPham p : ls) {
                        strKQ += "MaSP: " + p.getMaSP() + "; TenSP: " + p.getTenSP() + "; MoTa: " + p.getMoTa() + "\n";
                    }
                    tvKQ.setText(strKQ);
                } else {
                    tvKQ.setText("Response body is null");
                }
            }

            @Override
            public void onFailure(Call<SvrResponseSelect> call, Throwable throwable) {
                tvKQ.setText(throwable.getMessage());
            }
        });
    }
}
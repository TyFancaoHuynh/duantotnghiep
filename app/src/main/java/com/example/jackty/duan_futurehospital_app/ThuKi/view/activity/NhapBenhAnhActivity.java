package com.example.jackty.duan_futurehospital_app.ThuKi.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.jackty.duan_futurehospital_app.R;

public class NhapBenhAnhActivity extends AppCompatActivity {
    Button btnLuu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thuki_nhap_benh_anh_activity_);
        init();
    }

    private void init() {
        btnLuu = (Button) findViewById(R.id.btn_luu);
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(NhapBenhAnhActivity.this, "Đã Lưu", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),thukimain_Activity.class));


            }
        });
    }
}

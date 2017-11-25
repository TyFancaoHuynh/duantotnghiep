package com.example.jackty.duan_futurehospital_app.BacSi.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.jackty.duan_futurehospital_app.R;

public class Bacsi_chitietkhambenh_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // animation for activity
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        setContentView(R.layout.bacsi_chitietkhambenh_activity_);
    }
}

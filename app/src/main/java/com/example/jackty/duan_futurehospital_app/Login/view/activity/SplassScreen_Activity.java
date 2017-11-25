package com.example.jackty.duan_futurehospital_app.Login.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.example.jackty.duan_futurehospital_app.R;

public class SplassScreen_Activity extends AppCompatActivity {
    private static int SPLASS_TIME_OUT = 1500;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splass_screen_activity_);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(),Login_Activity.class));
                finish();
            }
        },SPLASS_TIME_OUT);
    }
}

package com.example.jackty.duan_futurehospital_app.Login.view.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.jackty.duan_futurehospital_app.Login.control.checkconnection;
import com.example.jackty.duan_futurehospital_app.Login.view.fragment.Checkconnection_out_fragment;
import com.example.jackty.duan_futurehospital_app.Login.view.fragment.login_Fragment;
import com.example.jackty.duan_futurehospital_app.R;

public class Login_Activity extends AppCompatActivity {
   private static FragmentManager fragmentManager;
    private static View view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity_);

        //

        fragmentManager = getSupportFragmentManager();

        // kiểm tra kết nối mạng , nếu không có trả về cái fragment checkout

        if(checkconnection.haveNetworkConnection(getApplicationContext())== true)
        {
            // nếu có mạng
            // commit fragment login

                fragmentManager
                        .beginTransaction()
                        .replace(R.id.frameContainer,new login_Fragment(),"login_Fragment")
                        .commit();


        }
        else
        {
            // nếu k có mạng
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.frameContainer,new Checkconnection_out_fragment(),"Checkconnection_out_fragment")
                    .commit();
        }



    }
}

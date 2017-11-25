package com.example.jackty.duan_futurehospital_app.ThuKiCreateQR.presenter;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.jackty.duan_futurehospital_app.ThuKiCreateQR.view.Quanly_TheQR_Activity;
import com.example.jackty.duan_futurehospital_app.ThuKiCreateQR.view.ReturnQR_Activity;
import com.example.jackty.duan_futurehospital_app.ThuKiCreateQR.view.ViewXuliquanlythe;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;

import static android.R.attr.id;
import static com.example.jackty.duan_futurehospital_app.R.id.tvDiaChi;
import static com.example.jackty.duan_futurehospital_app.R.id.tvTenBenhNhan;

/**
 * Created by Fancao Huynh (Jack) on 11/10/2017.
 */

public class PresenterIlogicXulytQLthe implements PresenterXulyQuanLythe {
    private static ViewXuliquanlythe viewXuliquanlythe;


    public PresenterIlogicXulytQLthe(ViewXuliquanlythe viewXuliquanlythe) {
        this.viewXuliquanlythe = viewXuliquanlythe;

    }

    @Override
    public void Timkiembenhnhan() {
        viewXuliquanlythe.TienhanhTimkiem();
    }

    @Override
    public void XulyXoaBenhNhan() {
        viewXuliquanlythe.TienhanhXoa();

    }




}

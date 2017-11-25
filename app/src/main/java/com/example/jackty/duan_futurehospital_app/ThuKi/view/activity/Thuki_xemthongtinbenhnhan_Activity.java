package com.example.jackty.duan_futurehospital_app.ThuKi.view.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.jackty.duan_futurehospital_app.R;
import com.example.jackty.duan_futurehospital_app.ThuKiCreateQR.model.benhnhan;
import com.example.jackty.duan_futurehospital_app.ThuKiCreateQR.view.ReturnQR_Activity;
import com.example.jackty.duan_futurehospital_app.URL_sever.urlsever;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Thuki_xemthongtinbenhnhan_Activity extends AppCompatActivity {
    Bundle db;
    int idBN;
    TextView tvTenBenhNhan,tvNamSinh,tvDiaChi,tvNghe;
    public static ArrayList<benhnhan> benhnhanArray;
    String urlStr;
    Button btnTiepTuc;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = getIntent().getExtras();
        idBN = db.getInt("idbn");

        setContentView(R.layout.thuki_xemthongtinbenhnhan_activity_);

        // gán link
        urlStr = urlsever.getBenhnhantheoid(idBN);

        // init
        init();

        // get
        new LoadinBN(this).execute();


    }

    private void init() {
        benhnhanArray = new ArrayList<>();
        tvTenBenhNhan = (TextView) findViewById(R.id.tv_namebenhnhan);
        tvNamSinh = (TextView) findViewById(R.id.tv_namsinhbenhnhan);
        tvDiaChi = (TextView) findViewById(R.id.tv_diachibenhnhan);
        tvNghe = (TextView) findViewById(R.id.tv_Nghenghiep);
        btnTiepTuc = (Button) findViewById(R.id.btnTieptuc);
        btnTiepTuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(new Intent(getApplicationContext(),NhapBenhAnhActivity.class));
            }
        });
    }

    // void loadingBenhNhanh to the ================================================================>


    public class LoadinBN extends AsyncTask<String,Void,Void>
    {
        private Thuki_xemthongtinbenhnhan_Activity activity;
        private Context context;
        private ProgressDialog dialog;




        public LoadinBN(Thuki_xemthongtinbenhnhan_Activity activity)
        {
            this.activity = activity;
            context=activity;
            dialog = new ProgressDialog(context);
        }

        @Override
        protected void onPreExecute()
        {


        }

        @Override
        protected Void doInBackground(String... strings) {





            final RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(urlStr, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    // ================== nếu như tồn tại kết quả json  ============================================>
                    if (response != null) {

                        int id;
                        String TenBenhNhan,NamSinh,SoCMND,NgheNghiep,DiaChi;


                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(0);
                                id = jsonObject.getInt("id");
                                TenBenhNhan = jsonObject.getString("TenBenhNhan");
                                SoCMND = jsonObject.getString("SoCMND");
                                NamSinh = jsonObject.getString("NamSinh");
                                NgheNghiep = jsonObject.getString("NgheNghiep");
                                DiaChi = jsonObject.getString("DiaChi");



                                  benhnhanArray.add(new benhnhan(id, TenBenhNhan, NamSinh,SoCMND,NgheNghiep, DiaChi));
                                  // setText
                               tvTenBenhNhan.setText(TenBenhNhan);
                                try {
                                    tvNamSinh.setText(Formatdate(NamSinh));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                tvDiaChi.setText(DiaChi);
                               tvNghe.setText(NgheNghiep);





                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }










                    }




                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), "Loi Json" +error, Toast.LENGTH_SHORT).show();
                }
            });
            requestQueue.add(jsonArrayRequest);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }




            return null;

        }

        @Override
        protected void onPostExecute(Void aVoid) {

        }
    }


    // Format date ================================================================>
    public   String Formatdate(String date_s) throws ParseException {

        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dt.parse(date_s);
        SimpleDateFormat dt1 = new SimpleDateFormat("dd-MM-yyyy");
        System.out.println(dt1.format(date));
        return dt1.format(date);

    }
}

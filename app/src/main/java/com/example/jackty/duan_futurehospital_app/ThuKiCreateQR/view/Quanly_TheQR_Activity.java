package com.example.jackty.duan_futurehospital_app.ThuKiCreateQR.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.jackty.duan_futurehospital_app.R;
import com.example.jackty.duan_futurehospital_app.ThuKiCreateQR.model.benhnhan;
import com.example.jackty.duan_futurehospital_app.ThuKiCreateQR.presenter.CustomAdapterQuanLyThe;
import com.example.jackty.duan_futurehospital_app.ThuKiCreateQR.presenter.PresenterIlogicXulytQLthe;
import com.example.jackty.duan_futurehospital_app.URL_sever.urlsever;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Quanly_TheQR_Activity extends AppCompatActivity implements SearchView.OnQueryTextListener,ViewXuliquanlythe, View.OnClickListener {
    SearchView editsearch;
    Button btnFind;
    private int check = 0 ;
  private PresenterIlogicXulytQLthe presenterIlogicXulytQLthe = new PresenterIlogicXulytQLthe(this);
    private String getValue;
    private String urlStr,urlXoa;
    public static ArrayList<benhnhan> benhnhanArrayList;
    private CustomAdapterQuanLyThe customAdapterQuanLyThe;
    private static ListView listfind;
    private int idxoa ;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        setContentView(R.layout.quanly_the_qr_);

        // innit
        init();










    }

    private void init() {
        editsearch = (SearchView) findViewById(R.id.simpleSearchView);
        editsearch.setOnQueryTextListener(this);
        btnFind = (Button) findViewById(R.id.btnFind);
        btnFind.setOnClickListener(this);
        benhnhanArrayList = new ArrayList<>();
        listfind = (ListView) findViewById(R.id.listfind);
        registerForContextMenu(listfind);
        listfind.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                // do your work here
                idxoa = position;

                return false;
            }
        });




    }






    // create conntext menu to listview


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if (v.getId() == R.id.listfind) {
            ListView lv = (ListView) v;

            menu.setHeaderTitle("Tùy Chọn");
            menu.add("Cập nhật thông tin");
            menu.add("Xóa thông tin");

        }
    }


    // menu click

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getTitle() == "Cập nhật thông tin")
        {
           Intent intent = new Intent(getApplicationContext(),UpdateTheActivity.class);
            intent.putExtra("posison",idxoa);
            startActivity(intent);


        }
        else
        if (item.getTitle() == "Xóa thông tin")
        {



           presenterIlogicXulytQLthe.XulyXoaBenhNhan();

        }




                return true;

        }



    @Override
    public boolean onQueryTextSubmit(String s) {

        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {

//        tv.setText(editsearch.getQuery());
        return false;
    }


    // set click gradion button
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radioTimtheoTen:
                if (checked)
                    // Pirates are the best
                check = 1;


                    break;
            case R.id.radioTimtheoCMND:
                if (checked)
                    // Ninjas rule
                check = 2;
                    break;
        }
    }






    // implement MVP

    @Override
    public void TienhanhTimkiem() {

        //
        getValue = String.valueOf(editsearch.getQuery());

        if (check == 0)
        {
            Toast.makeText(this, "Vui lòng chọn loại tìm kiếm", Toast.LENGTH_SHORT).show();

        }
        else

        if(check ==1 )
        {
            urlStr = urlsever.LinkGetBenhNhanTheoTen(getValue);

            //
            new GetBenhnhan().execute();


        }
        else
            if(check ==2)
            {
                urlStr = urlsever.LinkGetBenhNhanTheoCMND(getValue);
                //
                new GetBenhnhan().execute();


            }



    }

    // tiến hành xóa bệnh nhân

    @Override
    public void TienhanhXoa()
    {
        // truyền id để xóa
        urlXoa = urlsever.XoaBenhNhanTheoID(benhnhanArrayList.get(idxoa).getId());
        // show dialog
        new AlertDialog.Builder(this,R.style.AlertDialogStyle)
                .setTitle("Thông báo")
                .setMessage("Bạn chắc chắn muốn xóa?")
                .setNegativeButton("Không", null)
                .setPositiveButton("Có" +
                        "" +
                        "" +
                        "" +
                        "" +
                        "", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        ConfilmXoa();




                    }
                }).create().show();




    }

    private void ConfilmXoa()
    {
        new XoaBenhNhan(this).execute();
        benhnhanArrayList.remove(idxoa);

    }


    
    // onclick

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btnFind)
        {
            presenterIlogicXulytQLthe.Timkiembenhnhan();



        }
        
    }




    // void loadingBenhNhanh to the ================================================================>


    public class GetBenhnhan extends AsyncTask<String,Void,Void>
    {



        public GetBenhnhan()
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
                        benhnhanArrayList.clear();


                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                id = jsonObject.getInt("id");
                                TenBenhNhan = jsonObject.getString("TenBenhNhan");
                                SoCMND = jsonObject.getString("SoCMND");
                                NamSinh = jsonObject.getString("NamSinh");
                                NgheNghiep = jsonObject.getString("NgheNghiep");
                                DiaChi = jsonObject.getString("DiaChi");

                               benhnhanArrayList.add(new benhnhan(id,TenBenhNhan,NamSinh,SoCMND,NgheNghiep,DiaChi));






                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        // trả về kết quả

                        if(benhnhanArrayList.size() > 0)
                        {
                            customAdapterQuanLyThe = new CustomAdapterQuanLyThe(getApplicationContext(),R.layout.quanli_the_qr_custom_adapter,benhnhanArrayList);
                            listfind.setAdapter(customAdapterQuanLyThe);
                            customAdapterQuanLyThe.notifyDataSetChanged();

                            return;
                        }
                        if(benhnhanArrayList.size() == 0)
                        {

                            try {
                                customAdapterQuanLyThe.notifyDataSetChanged();
                            }catch (Exception e) {
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




            return null;
        }


    }


    // void Xoa Benh Nhan  ================================================================>
    public class XoaBenhNhan extends AsyncTask<String,Void,Void> {

        private Quanly_TheQR_Activity activity;
        private Context context;
        private ProgressDialog dialog;

        public XoaBenhNhan(Quanly_TheQR_Activity activity)
        {  this.activity = activity;
            context = activity;
            dialog = new ProgressDialog(context);
        }



        @Override
        protected void onPreExecute()
        {
            dialog.setMessage("Đang xóa...");
            dialog.show();


        }

        @Override
        protected Void doInBackground(String... strings)
        {

            StringRequest stringRequest = new StringRequest(Request.Method.GET,urlXoa, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {


                    // kiểm tra xem có dòng này không, nếu có là thêm thành công


                    String a= "\"affectedRows\":1";
                    if(response.toString().indexOf(a)!=-1)
                    {
//                          Toast.makeText(getApplicationContext(), "Xóa thành công", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Xóa thất bại", Toast.LENGTH_SHORT).show();
                    }



                }
            },new Response.ErrorListener()
            {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(),"Lỗi",Toast.LENGTH_SHORT).show();
                }
            });
            RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());

            requestQueue.add(stringRequest);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            return null;




        }

        @Override
        protected void onPostExecute(Void aVoid)
        {
            if (dialog.isShowing()) {
                dialog.dismiss();
                  Toast.makeText(getApplicationContext(), "Xóa thành công", Toast.LENGTH_SHORT).show();
                customAdapterQuanLyThe.notifyDataSetChanged();

            }



        }
    }




    // onBackPress


    @Override
    public void onBackPressed() {
        {

            // show dialog
            new AlertDialog.Builder(this,R.style.AlertDialogStyle)
                    .setTitle("Thông báo")
                    .setMessage("Bạn chắc chắn muốn thoát?")
                    .setNegativeButton("Không", null)
                    .setPositiveButton("Có" +
                            "" +
                            "" +
                            "" +
                            "" +
                            "", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface arg0, int arg1) {
                            //Khoi tao lai Activity muốn khởi tạo lại khi vào lần nữa

                            Intent intent = new Intent(getApplicationContext(), ThuKi_CreateQR_Activity.class);
                            startActivity(intent);


                        }
                    }).create().show();
        }
    }
}




package com.example.jackty.duan_futurehospital_app.Login.view.fragment;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.jackty.duan_futurehospital_app.BacSi.view.activity.Bacsi_Activity;
import com.example.jackty.duan_futurehospital_app.Login.model.taikhoan;
import com.example.jackty.duan_futurehospital_app.R;
import com.example.jackty.duan_futurehospital_app.ThuKi.view.activity.thukimain_Activity;
import com.example.jackty.duan_futurehospital_app.ThuKiCreateQR.view.ThuKi_CreateQR_Activity;
import com.example.jackty.duan_futurehospital_app.URL_sever.urlsever;
import com.example.jackty.duan_futurehospital_app.YTaTruc.activityYTa.YtaTrucActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class login_Fragment extends Fragment implements View.OnClickListener {
    Button btnLogin;
    EditText tvUser, tvPass;
    private static View view;
    public static ArrayList<taikhoan> taikhoanArrayList;
    public static String edtUser = "";
    public static String edtPass = "";
    String urlStr;


    public login_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //
        view = inflater.inflate(R.layout.login_fragment_, container, false);
        //


        innit();


        // btn click

        btnLogin.setOnClickListener(this);


        // return view
        return view;
    }

    // anh xa
    private void innit() {
        taikhoanArrayList = new ArrayList<>();
        btnLogin = view.findViewById(R.id.sign_in_button);
        tvUser = view.findViewById(R.id.login_email);
        tvPass = view.findViewById(R.id.login_password);

        //


    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.sign_in_button) {

            edtUser = tvUser.getText().toString();
            edtPass = tvPass.getText().toString();

            urlStr = Link(edtUser,edtPass);



            if(edtUser.isEmpty() || edtPass.isEmpty())
           {
               Toast.makeText(getActivity(), "Vui lòng nhập user hoặc pass", Toast.LENGTH_SHORT).show();
           }
           else
            {new LoginLoading().execute();}









        }


    }


    // String Link =========================================================================>

    public String Link(String user, String pass) {
        String url_login = urlsever.local + "login?TaiKhoan=" + user + "&MatKhau=" + pass;
        url_login = url_login.replace(" ", "%20");
        return url_login;

    }



// ================== Loading login ============================================>

    public class LoginLoading extends AsyncTask<String,Void,Void>
    {


        private ProgressDialog dialog;

        public LoginLoading()
        {
            dialog = new ProgressDialog(getContext());
        }

        @Override
        protected void onPreExecute()
        {
            dialog.setMessage("Đang đăng nhập...");
            dialog.show();


        }

        @Override
        protected Void doInBackground(String... strings) {




            final RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(urlStr, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    // ================== nếu như tồn tại kết quả json  ============================================>
                    if (response != null) {

                        int id;
                        int chucvu;
                        String Ten;
                        String NgaySinh;
                        String DiaChi;
                        String Sdt;
                        int Khoa;
                        String TaiKhoan;
                        String MatKhau;
                        taikhoanArrayList.clear();


                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(0);
                                id = jsonObject.getInt("id");
                                chucvu = jsonObject.getInt("ChucVu");
                                Ten = jsonObject.getString("Ten");
                                NgaySinh = jsonObject.getString("NgaySinh");
                                DiaChi = jsonObject.getString("DiaChi");
                                Sdt = jsonObject.getString("Sdt");
                                Khoa = jsonObject.getInt("Khoa");
                                TaiKhoan = jsonObject.getString("TaiKhoan");
                                MatKhau = jsonObject.getString("MatKhau");

                                taikhoanArrayList.add(new taikhoan(id, chucvu,Ten ,NgaySinh, DiaChi, Sdt, Khoa, TaiKhoan, MatKhau));


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        // ==============================================================>
                        int legtharr = taikhoanArrayList.size();
                        int loaiTK;

                        // kiểm tra xem trong mảng có tồn tại taikhoan nao khong
                        // nếu không
                        if (legtharr == 0)
                        {
                            Toast.makeText(getActivity(), "Sai User hoặc Password", Toast.LENGTH_SHORT).show();
                        }
                        // nếu có
                        else
                        {
                            //  ====================kiểm tra loại tài khoản rồi cho login đến activity cần thiết==========================================>
                            loaiTK = taikhoanArrayList.get(0).getChucvu();

                            if(loaiTK == 0)
                            {
                                startActivity(new Intent(getActivity(),thukimain_Activity.class));
                            }
                            else
                                if (loaiTK ==1)
                                {
                                    startActivity(new Intent(getActivity(),Bacsi_Activity.class));
                                }
                            else if(loaiTK ==2)
                                {
                                    startActivity(new Intent(getActivity(),ThuKi_CreateQR_Activity.class));
                                }
                                else if(loaiTK ==3)
                                {
                                    startActivity(new Intent(getActivity(),YtaTrucActivity.class));
                                }

                        }

                    }


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getActivity(), "Loi Json"+error, Toast.LENGTH_SHORT).show();
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
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
        }
    }


}
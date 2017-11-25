package com.example.jackty.duan_futurehospital_app.ThuKiCreateQR.view;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.jackty.duan_futurehospital_app.R;
import com.example.jackty.duan_futurehospital_app.URL_sever.urlsever;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Calendar;

public class Thuki_CreateQR_ADDSEVER_Activity extends AppCompatActivity implements View.OnClickListener {

  private   EditText edtName,edtDate,edtSoCMND , edtJob, edtLocation ;
    private   TextView tvValidatename,tvValidateDate,tvSoCMND,tvValidateJob,tvValidateLocation;
    private Button btnCreate,btnSetdate;

    // date picker
    private DatePicker datePicker;
    private Calendar calendar;
    private int year, month, day;

    //  gettextEDT
   private static String getEdtName,getEdtDate,getEdtCMND,getEdtJob,getEdtLocation ;

    // get name string send

    private final String Namesl = "";

    // url
    private static    String urlStr;

    public Thuki_CreateQR_ADDSEVER_Activity() {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        setContentView(R.layout.create_qr__addsever_activity);

        // ánh xạ
        innit();

        // date picker

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month+1, day);



    }

    // innit ===============================================================>

    private void innit()
    {
        edtName = (EditText) findViewById(R.id.edt_name_createQR_);
        edtDate = (EditText) findViewById(R.id.edt_date_createQR_);
        edtSoCMND = (EditText) findViewById(R.id.edt_CMND_createQR_);
        edtJob = (EditText) findViewById(R.id.edt_nghenghiep_createQR_);
        edtLocation = (EditText) findViewById(R.id.edt_diachi_createQR_);
        //
        tvValidatename = (TextView) findViewById(R.id.tv_name_validate_createQR);
        tvValidateDate = (TextView) findViewById(R.id.tv_date_validate_createQR);
        tvSoCMND = (TextView) findViewById(R.id.tv_CMND_validate_createQR);
        tvValidateJob = (TextView) findViewById(R.id.tv_nghenghiep_validate_createQR);
        tvValidateLocation = (TextView) findViewById(R.id.tv_diachi_validate_createQR);
        btnCreate = (Button) findViewById(R.id.btn_create_QR);
        btnSetdate = (Button) findViewById(R.id.btnsetdate);

        // set onclick
        btnCreate.setOnClickListener(this);
        btnSetdate.setOnClickListener(this);
    }

    // onclick ===============================================================>

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.btn_create_QR:

                // getTexxt

                getEdtName = edtName.getText().toString();
                getEdtDate = edtDate.getText().toString();
                getEdtCMND = edtSoCMND.getText().toString();
                getEdtJob = edtJob.getText().toString();
                getEdtLocation = edtLocation.getText().toString();

                // gán Namesl = getedt

                //gán link trước khi Request , truyền vào các tham số
                urlStr = Link(getEdtName,getEdtDate,getEdtCMND,getEdtJob,getEdtLocation);
                // kiểm tra các trường trống
                Validate();

                // intent sang activity khác để nhả về QR






                break;

            case R.id.btnsetdate:
                setDate(view);
                break;

        }

    }




    private void Validate()
    {


        if(getEdtName.isEmpty())
        {
            tvValidatename.setVisibility(View.VISIBLE);
        }
        else
        {
                tvValidatename.setVisibility(View.GONE);
        }

        if(getEdtCMND.isEmpty())
        {
            tvSoCMND.setVisibility(View.VISIBLE);
        }
        else
        {
            tvSoCMND.setVisibility(View.GONE);
        }


        if(getEdtDate.isEmpty())
        {
            tvValidateDate.setVisibility(View.VISIBLE);
        }
        else
        {
            tvValidateDate.setVisibility(View.GONE);
        }

        if(getEdtJob.isEmpty())
        {
            tvValidateJob.setVisibility(View.VISIBLE);
        }
        else
        {
            tvValidateJob.setVisibility(View.GONE);
        }

        if(getEdtLocation.isEmpty())
        {
            tvValidateLocation.setVisibility(View.VISIBLE);
        }
        else
        {
            tvValidateLocation.setVisibility(View.GONE);
        }

        // bắt sự kiện khi đầy đủ
        if(getEdtName.length() >0 && getEdtCMND.length()>0 && getEdtJob.length() > 0 && getEdtLocation.length()>0)
        {
           new LoadingThemBenhNhan(Thuki_CreateQR_ADDSEVER_Activity.this).execute();
           // Toast.makeText(this, urlStr, Toast.LENGTH_SHORT).show();

        }








    }

    // ===================== void Link ============================================================== >

    public String Link(String name, String date ,String cmnd ,String job , String location) {
        String urladd = urlsever.local +"Thembenhnhan?TenBenhNhan=" + encodeUrl(name) + "&NamSinh=" + encodeUrl(date) + "&SoCMND=" + encodeUrl(cmnd)+ "&NgheNghiep="+ encodeUrl(job)+ "&DiaChi="+ encodeUrl(location);

        return urladd;
    }

    // endcode params để nhận tiếng việt đẩy lên sever

    private String encodeUrl(String params) {
        try {
           params = URLEncoder.encode(params, "UTF-8");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return params;
    }








    // =====================  ONBACK PRESS ( CLICK TRỞ VỀ CỨNG ) ============================================================== >
    @Override
    public void onBackPressed() {

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


    // =====================Dake Picker================================================================>
    public void setDate(View view) {
        showDialog(999);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this,
                    myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    // TODO Auto-generated method stub
                    // arg1 = year
                    // arg2 = month
                    // arg3 = day
                    showDate(arg1, arg2+1, arg3);
                }
            };

    private void showDate(int year, int month, int day) {
        edtDate.setText(new StringBuilder().append(year).append("/")
                .append(month).append("/").append(day));
    }




    // =====================Loading them benhnhanh================================================================>

    public class LoadingThemBenhNhan extends AsyncTask<String,Void,Void> {

        private Thuki_CreateQR_ADDSEVER_Activity activity;
        private Context context;
        private ProgressDialog dialog;

        public LoadingThemBenhNhan(Thuki_CreateQR_ADDSEVER_Activity activity)
        {  this.activity = activity;
            context = activity;
            dialog = new ProgressDialog(context);
        }

        @Override
        protected void onPreExecute()
        {
            dialog.setMessage("Đang thêm...");
            dialog.show();


        }

        @Override
        protected Void doInBackground(String... strings)
        {

            StringRequest stringRequest = new StringRequest(Request.Method.GET, urlStr, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {


                    // kiểm tra xem có dòng này không, nếu có là thêm thành công


                    String a= "\"affectedRows\":1";
                    if(response.toString().indexOf(a)!=-1)
                    {
                     //  Toast.makeText(getApplicationContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
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


            return null;




        }

        @Override
        protected void onPostExecute(Void aVoid)
        {
            if (dialog.isShowing()) {
                dialog.dismiss();
              //  Toast.makeText(getApplicationContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),ReturnQR_Activity.class);
                intent.putExtra("Name" , getEdtName);
                startActivity(intent);
            }



        }
    }
}

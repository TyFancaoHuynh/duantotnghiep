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
import com.example.jackty.duan_futurehospital_app.ThuKiCreateQR.presenter.PresenterLogicUpdateThe;
import com.example.jackty.duan_futurehospital_app.URL_sever.urlsever;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class UpdateTheActivity extends AppCompatActivity implements View.OnClickListener,ViewUpdateThe {
    Bundle bd;
    private int potision;
    private EditText edtName,edtDate,edtSoCMND , edtJob, edtLocation ;
    private TextView tvValidatename,tvValidateDate,tvSoCMND,tvValidateJob,tvValidateLocation;
    private Button btnLuu,btnSetdate;

    private String urlStr;
    //  gettextEDT
    private static String getEdtName,getEdtDate,getEdtCMND,getEdtJob,getEdtLocation ;

    private static int id;


    // date picker
    private DatePicker datePicker;
    private Calendar calendar;
    private int year, month, day;
    private PresenterLogicUpdateThe presenterLogicUpdateThe = new PresenterLogicUpdateThe(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        setContentView(R.layout.quanli_qr_update_);
        // get bundle
        bd = getIntent().getExtras();
        if (bd != null)
        {potision  =  bd.getInt("posison");}

        init();

        // date picker

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month+1, day);


        // setText
        setTexttoUpdate();






    }

    // ánh xạ

    private void init()
    {
        edtName = (EditText) findViewById(R.id.edt_name_update_);
        edtDate = (EditText) findViewById(R.id.edt_date_update_);
        edtSoCMND = (EditText) findViewById(R.id.edt_CMND_update_);
        edtJob = (EditText) findViewById(R.id.edt_nghenghiep_update_);
        edtLocation = (EditText) findViewById(R.id.edt_diachi_update_);
        //
        tvValidatename = (TextView) findViewById(R.id.tv_name_validate_update);
        tvValidateDate = (TextView) findViewById(R.id.tv_date_validate_update);
        tvSoCMND = (TextView) findViewById(R.id.tv_CMND_validate_update);
        tvValidateJob = (TextView) findViewById(R.id.tv_nghenghiep_validate_update);
        tvValidateLocation = (TextView) findViewById(R.id.tv_diachi_validate_update);
        btnLuu = (Button) findViewById(R.id.btn_update_);
        btnSetdate = (Button) findViewById(R.id.btnsetdateupdate);

        // set onclick
        btnLuu.setOnClickListener(this);
        btnSetdate.setOnClickListener(this);
    }

    // void setText

    private void setTexttoUpdate()
    {
        id = Quanly_TheQR_Activity.benhnhanArrayList.get(potision).getId();
        edtName.setText(Quanly_TheQR_Activity.benhnhanArrayList.get(potision).getTenBenhNhan());
        edtLocation.setText(Quanly_TheQR_Activity.benhnhanArrayList.get(potision).getDiaChi());
        edtJob.setText(Quanly_TheQR_Activity.benhnhanArrayList.get(potision).getNgheNghiep());
        edtSoCMND.setText(Quanly_TheQR_Activity.benhnhanArrayList.get(potision).getSoCMND());
        try {
            edtDate.setText(Formatt(Quanly_TheQR_Activity.benhnhanArrayList.get(potision).getNgaySinh()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    // format day

    String Formatt(String date_s) throws ParseException {

        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dt.parse(date_s);
        SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(dt1.format(date));
        return dt1.format(date);

    }

    @Override
    public void onBackPressed() {
        {

            // show dialog
            new AlertDialog.Builder(this,R.style.AlertDialogStyle)
                    .setTitle("Thông báo")
                    .setMessage("Thoát phải lưu dữ liệu trước khi thoát?")
                    .setNegativeButton("Có", null)
                    .setPositiveButton("Không" +
                            "" +
                            "" +
                            "" +
                            "" +
                            "", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface arg0, int arg1) {
                            //Khoi tao lai Activity muốn khởi tạo lại khi vào lần nữa

                            Intent intent = new Intent(getApplicationContext(), Quanly_TheQR_Activity.class);
                            startActivity(intent);


                        }
                    }).create().show();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btn_update_:
                //
                presenterLogicUpdateThe.XuliCapNhat();
                break;
            case  R.id.btnsetdateupdate:
                //
                setDate(view);
                break;
        }
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


    // =====================Tiến hành cập nhật================================================================>
    @Override
    public void Tienhanhcapnhat()
    {
        // getTexxt

        getEdtName = edtName.getText().toString();
        getEdtDate = edtDate.getText().toString();
        getEdtCMND = edtSoCMND.getText().toString();
        getEdtJob = edtJob.getText().toString();
        getEdtLocation = edtLocation.getText().toString();
        // gán link
        urlStr = urlsever.UpdateBN(id,getEdtName,getEdtDate,getEdtCMND,getEdtJob,getEdtLocation);

        // cập nhật
        new LoadingCapNhatBenhNhan(this).execute();



    }






    // =====================Loading Cập Nhật bệnh nhân ============================================================>
    public class LoadingCapNhatBenhNhan extends AsyncTask<String,Void,Void> {

        private UpdateTheActivity activity;
        private Context context;
        private ProgressDialog dialog;

        public LoadingCapNhatBenhNhan(UpdateTheActivity activity)
        {  this.activity = activity;
            context = activity;
            dialog = new ProgressDialog(context);
        }

        @Override
        protected void onPreExecute()
        {
            dialog.setMessage("Đang Cập Nhật...");
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
                        Toast.makeText(getApplicationContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();

                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
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
                Thread.sleep(1500);
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
                //   Toast.makeText(activity, "Cập nhật thành công!", Toast.LENGTH_SHORT).show();

                startActivity(new Intent(getApplicationContext(),Quanly_TheQR_Activity.class));

            }



        }
    }
}

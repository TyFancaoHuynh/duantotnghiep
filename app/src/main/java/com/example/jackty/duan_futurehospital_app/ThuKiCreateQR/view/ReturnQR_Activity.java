package com.example.jackty.duan_futurehospital_app.ThuKiCreateQR.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.jackty.duan_futurehospital_app.R;
import com.example.jackty.duan_futurehospital_app.ThuKiCreateQR.model.benhnhan;
import com.example.jackty.duan_futurehospital_app.URL_sever.urlsever;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ReturnQR_Activity extends AppCompatActivity implements View.OnClickListener {
      private static String TenBN = "";
    private static  String urlStr;
    private static ArrayList<benhnhan> BenhNhanReturnQRArrayList;
    private ImageView imgAnh;
    private static int idBN ;
    private TextView tvTenBenhNhan, tvNgaySinh , tvDiaChi;
    private Button btnSave;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        // get bundle
        Bundle b = getIntent().getExtras();
        TenBN = b.getString("Name");
        // setcontent view
        setContentView(R.layout.createqr_return_qr_);
        // gán link
        urlStr = urlsever.LinkreturnQR(TenBN);


        //

        init();



       new LoadingBenhnhan(ReturnQR_Activity.this).execute();







    }





 // =================  init ====================================================================>

    private void init() {
        BenhNhanReturnQRArrayList = new ArrayList<>();
        linearLayout = (LinearLayout) findViewById(R.id.layoutThetichhop);
        imgAnh=(ImageView)findViewById(R.id.imgQR);
        tvTenBenhNhan = (TextView) findViewById(R.id.tvTenBenhNhan);
        tvNgaySinh = (TextView) findViewById(R.id.tvNamSinh);
        tvDiaChi = (TextView) findViewById(R.id.tvDiaChi);
        btnSave = (Button) findViewById(R.id.btnSaveimgQR);
        btnSave.setOnClickListener(this);

    }


    // void make QR code================================================================>

    private void makeQR(int idBenhNhan)
    {

        MultiFormatWriter multiFormatWriter=new MultiFormatWriter();
        try {

            BitMatrix bitMatrix=multiFormatWriter.encode(String.valueOf(idBenhNhan), BarcodeFormat.QR_CODE,200,200);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap= barcodeEncoder.createBitmap(bitMatrix);
            imgAnh.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }

    }



    // =====================  ONBACK PRESS ( CLICK TRỞ VỀ CỨNG ) ============================================================== >
    @Override
    public void onBackPressed() {

        // show dialog
        new AlertDialog.Builder(this,R.style.AlertDialogStyle)
                .setTitle("Thông báo")
                .setMessage("Bạn phải lưu thẻ trước khi thoát?")
                .setNegativeButton("Có", null)
                .setPositiveButton("Không" +
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


    // setOnclick ================================================================>

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btnSaveimgQR:
                saveIMG();
                break;
        }

    }

    //  void save IMG ================================================================>
    private void saveIMG()
    {
        File file = saveBitMap(this, linearLayout);    //which view you want to pass that view as parameter
        if (file != null) {
            Toast.makeText(this, "Đã lưu ảnh thành công", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(),ThuKi_CreateQR_Activity.class));
        } else {
            Toast.makeText(this, "Lỗi lưu ảnh", Toast.LENGTH_SHORT).show();
        }
    }


  //  void save IMG (convert Layout to bitmap)  ================================================================>


    private File saveBitMap(Context context, View drawView){
        File pictureFileDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),"Handcare");
        if (!pictureFileDir.exists()) {
            boolean isDirectoryCreated = pictureFileDir.mkdirs();
            if(!isDirectoryCreated)
                Log.i("ATG", "Can't create directory to save the image");
            return null;
        }
        String filename = pictureFileDir.getPath() +File.separator+ System.currentTimeMillis()+".jpg";
        File pictureFile = new File(filename);
        Bitmap bitmap =getBitmapFromView(drawView);
        try {
            pictureFile.createNewFile();
            FileOutputStream oStream = new FileOutputStream(pictureFile);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, oStream);
            oStream.flush();
            oStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("TAG", "There was an issue saving the image.");
        }
        scanGallery( context,pictureFile.getAbsolutePath());
        return pictureFile;
    }
    //create bitmap from view and returns it
    private Bitmap getBitmapFromView(View view) {
        //Define a bitmap with the same size as the view
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(),Bitmap.Config.ARGB_8888);
        //Bind a canvas to it
        Canvas canvas = new Canvas(returnedBitmap);
        //Get the view's background
        Drawable bgDrawable =view.getBackground();
        if (bgDrawable!=null) {
            //has background drawable, then draw it on the canvas
            bgDrawable.draw(canvas);
        }   else{
            //does not have background drawable, then draw white background on the canvas
            canvas.drawColor(Color.WHITE);
        }
        // draw the view on the canvas
        view.draw(canvas);
        //return the bitmap
        return returnedBitmap;
    }
    // used for scanning gallery
    private void scanGallery(Context cntx, String path) {
        try {
            MediaScannerConnection.scanFile(cntx, new String[]{path}, null, new MediaScannerConnection.OnScanCompletedListener() {
                public void onScanCompleted(String path, Uri uri) {
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // void loadingBenhNhanh to the ================================================================>


    public class LoadingBenhnhan extends AsyncTask<String,Void,Void>
    {
        private ReturnQR_Activity activity;
        private Context context;
        private ProgressDialog dialog;




        public LoadingBenhnhan(ReturnQR_Activity activity)
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
                        String TenBenhNhan,NamSinh,NgheNghiep,DiaChi;


                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(0);
                                id = jsonObject.getInt("id");
                                TenBenhNhan = jsonObject.getString("TenBenhNhan");
                                NamSinh = jsonObject.getString("NamSinh");
                                NgheNghiep = jsonObject.getString("NgheNghiep");
                                DiaChi = jsonObject.getString("DiaChi");

                                // gán idBN = id;
                                idBN = id;

                              //  BenhNhanReturnQRArrayList.add(new benhnhan(id, TenBenhNhan, NamSinh, NgheNghiep, DiaChi));
                                // setText to Thẻ
                                tvTenBenhNhan.setText(TenBenhNhan);
                                try {
                                    tvNgaySinh.setText(Formatdate(NamSinh));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                tvDiaChi.setText(DiaChi);


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }



                        makeQR(idBN);




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


    // Format date ================================================================>
  public   String Formatdate(String date_s) throws ParseException {

        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dt.parse(date_s);
        SimpleDateFormat dt1 = new SimpleDateFormat("dd-MM-yyyy");
        System.out.println(dt1.format(date));
        return dt1.format(date);

    }




}

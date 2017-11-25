package com.example.jackty.duan_futurehospital_app.ThuKi.view.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jackty.duan_futurehospital_app.Login.view.activity.Login_Activity;
import com.example.jackty.duan_futurehospital_app.Login.view.fragment.login_Fragment;
import com.example.jackty.duan_futurehospital_app.R;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class thukimain_Activity extends AppCompatActivity implements View.OnClickListener,NavigationView.OnNavigationItemSelectedListener, ZXingScannerView.ResultHandler {


    // QR code lib

    private ZXingScannerView zXingScannerView;
    private boolean checkcamstartorstop = false;


    // biến
    private Toolbar toolbar;
    Button btnquet;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thukimain_activity);
        // set toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        // anh xa
        init();

        // call drawer nav
        calldrawernav();



        btnquet.setOnClickListener(this);
    }


    // anh xa =======================================================================>
    private void init()
    {

        btnquet= (Button) findViewById(R.id.btn_quetQR);


    }

    // =====================VOID CALL DRAWER NAV  ============================================================== >
    private void calldrawernav()
    {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        // setText header nav for name user =================================>
        View header=navigationView.getHeaderView(0);
        TextView name = (TextView)header.findViewById(R.id.dr_namebacsi);
        TextView  chucvu = (TextView)header.findViewById(R.id.dr_chucvubacsi);
        name.setText(login_Fragment.taikhoanArrayList.get(0).getTen());
        int LoaiTK = login_Fragment.taikhoanArrayList.get(0).getChucvu();
        if(LoaiTK == 0)
        {
            chucvu.setText("Y tá 1 (Nhập bệnh án)");
        }
        else
        if(LoaiTK == 1)
        {
            chucvu.setText("Bác sĩ");
        }
        else
        if(LoaiTK == 2)
        {
            chucvu.setText("Y tá 2 (Tạo thẻ)");
        }
    }

    // ================== DRAWER NAV ONCLICK =============================================== >
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.drawer_myacc) {
            Toast.makeText(this, "Tài khoản của tôi", Toast.LENGTH_SHORT).show();
        }
        else if (id == R.id.drawe_chagepass) {
            Toast.makeText(this, "Thay đổi mật khẩu", Toast.LENGTH_SHORT).show();
        }
        // đăng xuất
        else if(id == R.id.drawe_logout)
        {
            startActivity(new Intent(this,Login_Activity.class));
        }
        else if(id == R.id.drawer_socialnet)
        {
            Toast.makeText(this, "MẠNG XÃ HỘI", Toast.LENGTH_SHORT).show();
        }

        else if(id == R.id.drawer_sendchat)
        {
            Toast.makeText(this, "CHAT", Toast.LENGTH_SHORT).show();
        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    // =================================================================================== >





    // onclick button =================================================>

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.btn_quetQR)
        {
            QrScanner(view);
        }
    }






    // QR SCANNER =========================================================================================>

    public void QrScanner(View view)
    {
        zXingScannerView = new ZXingScannerView(this);
        setContentView(zXingScannerView);
        zXingScannerView.setResultHandler(this);
        zXingScannerView.startCamera();
        checkcamstartorstop = true;


    }


    @Override
    protected void onPause() {
        super.onPause();
        if(checkcamstartorstop == true) {
            zXingScannerView.stopCamera();
        }
    }

    @Override
    public void handleResult(final Result result)
    {
     //   Toast.makeText(this, "id: "+result, Toast.LENGTH_SHORT).show();
        // show dialog
        new AlertDialog.Builder(this,R.style.AlertDialogStyle)
                .setTitle("Tìm thấy một kết quả")
                .setMessage(result.getText())
                .setNegativeButton("Trở lại", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        zXingScannerView.stopCamera();
                        Intent intent = new Intent(getApplicationContext(), thukimain_Activity.class);
                        startActivity(intent);
                    }
                })
                .setPositiveButton("Xem" +
                        "" +
                        "" +
                        "" +
                        "" +
                        "", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        Intent intent = new Intent(getApplicationContext(), Thuki_xemthongtinbenhnhan_Activity.class);
                        intent.putExtra("idbn",Integer.parseInt(String.valueOf(result)));
                        startActivity(intent);

                    }
                }).create().show();

    }




    // Onback press =========================================================================================>

    @Override
    public void onBackPressed() {
        if(checkcamstartorstop == true)
        {
            zXingScannerView.stopCamera();
            Intent intent = new Intent(getApplicationContext(), thukimain_Activity.class);
            startActivity(intent);

        }

//
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        }




        else

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

                            Intent intent = new Intent(getApplicationContext(), thukimain_Activity.class);
                            startActivity(intent);
                            // kết thúc app
                            Intent startMain = new Intent(Intent.ACTION_MAIN);
                            startMain.addCategory(Intent.CATEGORY_HOME);
                            startActivity(startMain);
                            finish();
                        }
                    }).create().show();









    }



}

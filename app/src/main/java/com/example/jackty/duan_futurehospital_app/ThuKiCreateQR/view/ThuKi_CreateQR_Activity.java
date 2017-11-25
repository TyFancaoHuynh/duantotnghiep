package com.example.jackty.duan_futurehospital_app.ThuKiCreateQR.view;

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
import android.widget.TextView;
import android.widget.Toast;

import com.example.jackty.duan_futurehospital_app.Login.view.activity.Login_Activity;
import com.example.jackty.duan_futurehospital_app.Login.view.fragment.login_Fragment;
import com.example.jackty.duan_futurehospital_app.R;

public class ThuKi_CreateQR_Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {


    // biến
    private Toolbar toolbar;
    Button btnAddnew;
    Button btnManagerment;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //  transition
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

        setContentView(R.layout.createqr_thuki_main);
        // set toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        // anh xa
        init();

        // call drawer nav
        calldrawernav();

    }

    private void init()
    {
        btnAddnew = (Button) findViewById(R.id.create_QR_addnew);
        btnManagerment = (Button) findViewById(R.id.create_QR_managerment);
        btnAddnew.setOnClickListener(this);
        btnManagerment.setOnClickListener(this);
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

    // =====================  ONBACK PRESS ( CLICK TRỞ VỀ CỨNG ) ============================================================== >
    @Override
    public void onBackPressed() {


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }

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

                            Intent intent = new Intent(getApplicationContext(), ThuKi_CreateQR_Activity.class);
                            startActivity(intent);
                            // kết thúc app
                            Intent startMain = new Intent(Intent.ACTION_MAIN);
                            startMain.addCategory(Intent.CATEGORY_HOME);
                            startActivity(startMain);
                            finish();
                        }
                    }).create().show();
    }



    // ========================= onclick =========================================>

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.create_QR_addnew:
                startActivity(new Intent(getApplicationContext(),Thuki_CreateQR_ADDSEVER_Activity.class));
                break;
            case R.id.create_QR_managerment:
                startActivity(new Intent(getApplicationContext(),Quanly_TheQR_Activity.class));
                break;

        }

    }

    // ==================================
}

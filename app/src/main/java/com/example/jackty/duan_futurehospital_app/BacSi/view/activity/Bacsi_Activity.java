package com.example.jackty.duan_futurehospital_app.BacSi.view.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jackty.duan_futurehospital_app.BacSi.view.fragment.bacsi_fragment_thongbao;
import com.example.jackty.duan_futurehospital_app.BacSi.view.fragment.bacsi_fragment_trangchu;
import com.example.jackty.duan_futurehospital_app.Login.view.activity.Login_Activity;
import com.example.jackty.duan_futurehospital_app.Login.view.fragment.login_Fragment;
import com.example.jackty.duan_futurehospital_app.R;

public class Bacsi_Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    // biến
    private  Toolbar toolbar;

    // đếm số thông báo để hiển thị
    public static int noticount;


    // fragment manager

    private static FragmentManager fragmentManager;


    // bottom nav
    private static    BottomNavigationView navigation;
    TextView bage_tv ;






// thanh công cụ ========================================================>

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_quanly:
                    //
                    call_trangchu();
                    return true;
                case R.id.navigation_thongbao:
                    //
                    call_thongbao();

                    return true;
            }
            return false;
        }

    };
    // ========================================================>

    // =====================  ONCREATE  ============================================================== >
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bacsi_activity_);

        // set toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // getsupportfragment

        fragmentManager = getSupportFragmentManager();

        if (savedInstanceState == null) {
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.content, new bacsi_fragment_trangchu(),
                            "bacsi_fragment_trangchu").commit();
        }

        //call drawer nav

        calldrawernav();

        // call bottom nav
        callbottomnav();












    }



    // =====================VOID CALL Bottom NAV  ============================================================== >


    private void callbottomnav()
    {
        // thanh công cụ
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        // bottom nav badge
        BottomNavigationMenuView bottomNavigationMenuView =
                (BottomNavigationMenuView) navigation.getChildAt(0);
        View v = bottomNavigationMenuView.getChildAt(1);
        BottomNavigationItemView itemView = (BottomNavigationItemView) v;
        View badge = LayoutInflater.from(this)
                .inflate(R.layout.bacsi_notification_badge, bottomNavigationMenuView, false);

        itemView.addView(badge);
        bage_tv = (TextView) findViewById(R.id.notificationsbadge);


        // SHOW HIDE VIEW BADGE from Count noti



        badge.setVisibility(View.VISIBLE);


            bage_tv.setText(""+3);


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
     TextView   name = (TextView)header.findViewById(R.id.dr_namebacsi);
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



    // ============ call to fragment ================================>
    private void call_trangchu ()
    {
        fragmentManager
                .beginTransaction()
                .setCustomAnimations(R.anim.left_enter,R.anim.right_out)
                .replace(R.id.content, new bacsi_fragment_trangchu(),
                        "bacsi_fragment_trangchu").commit();

    }

    private void call_thongbao()
    {
        fragmentManager
                .beginTransaction()
                .setCustomAnimations(R.anim.right_enter,R.anim.left_out)
                .replace(R.id.content, new bacsi_fragment_thongbao(),
                        "bacsi_fragment_thongbao").commit();
    }




    // =====================  ONBACK PRESS ( CLICK TRỞ VỀ CỨNG ) ============================================================== >
    @Override
    public void onBackPressed() {
        // Back fragment
        Fragment fragment_trangchu = fragmentManager.findFragmentByTag("bacsi_fragment_trangchu");
        Fragment fragment_thongbao = fragmentManager.findFragmentByTag("bacsi_fragment_thongbao");

        if(fragment_thongbao != null)
        {
            call_trangchu();
        }
        else
            if (fragment_trangchu !=null)
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

                                Intent intent = new Intent(getApplicationContext(), Bacsi_Activity.class);
                                startActivity(intent);
                                // kết thúc app
                                Intent startMain = new Intent(Intent.ACTION_MAIN);
                                startMain.addCategory(Intent.CATEGORY_HOME);
                                startActivity(startMain);
                                finish();
                            }
                        }).create().show();
            }
            else {
                super.onBackPressed();
            }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }

    }
}









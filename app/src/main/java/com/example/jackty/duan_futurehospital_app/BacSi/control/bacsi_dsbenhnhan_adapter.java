package com.example.jackty.duan_futurehospital_app.BacSi.control;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jackty.duan_futurehospital_app.BacSi.model.dsbenhnhan;
import com.example.jackty.duan_futurehospital_app.BacSi.view.fragment.bacsi_fragment_trangchu;
import com.example.jackty.duan_futurehospital_app.ClassInfo.BenhAnKhamNgay;
import com.example.jackty.duan_futurehospital_app.ClassInfo.BenhAnKhoa;
import com.example.jackty.duan_futurehospital_app.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by jackty on 18/10/2017.
 */

public class bacsi_dsbenhnhan_adapter extends RecyclerView.ViewHolder {

    TextView tv_name;
    TextView tv_tenloaibenh;
    ImageView imgv_;
    TextView tv_phongbenh;
    TextView tv_ngaynhapvien;

  public static   View mView;



    public bacsi_dsbenhnhan_adapter(View itemView) {
        super(itemView);
        mView = itemView;
        this.tv_name = (TextView) itemView.findViewById(R.id.item_name_tv);
        this.tv_tenloaibenh = (TextView) itemView.findViewById(R.id.item_tenloaibenh_tv);
        this.imgv_ = (ImageView) itemView.findViewById(R.id.item_trend_flag);
        this.tv_phongbenh = (TextView) itemView.findViewById(R.id.item_phong_tv);
        this.tv_ngaynhapvien = (TextView) itemView.findViewById(R.id.item_ngaynhapvien_tv);
    }
    public void bindData(BenhAnKhamNgay benhAnKhoa) throws ParseException {
        tv_name.setText(benhAnKhoa.getTenBenhNhan());
        tv_tenloaibenh.setText(benhAnKhoa.getTenBenh());
       // trendFlagIv.setImageResource(dsbn.getFlag() > 0 ? R.drawable.up_red : R.drawable.down_green);
        tv_phongbenh.setText("P." +benhAnKhoa.getPhong());
        tv_ngaynhapvien.setText("Ngày: " +Format(benhAnKhoa.getNgayNhapVien()));

        // setonclick

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

       // grossTv.setTextColor(dsbenhnhan.getFlag() > 0 ? redColor : greenColor);
    }
    String Format(String date_s) throws ParseException {

        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dt.parse(date_s);
        SimpleDateFormat dt1 = new SimpleDateFormat("dd-MM-yyyy");
        System.out.println(dt1.format(date));
        return dt1.format(date);

    }
}

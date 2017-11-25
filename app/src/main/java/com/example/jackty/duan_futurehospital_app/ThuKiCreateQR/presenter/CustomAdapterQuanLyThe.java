package com.example.jackty.duan_futurehospital_app.ThuKiCreateQR.presenter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.jackty.duan_futurehospital_app.BacSi.model.listthongbao;
import com.example.jackty.duan_futurehospital_app.R;
import com.example.jackty.duan_futurehospital_app.ThuKiCreateQR.model.benhnhan;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Fancao Huynh (Jack) on 11/11/2017.
 */

public class CustomAdapterQuanLyThe extends ArrayAdapter<benhnhan> {

    public CustomAdapterQuanLyThe(Context context, int resource, List<benhnhan> objects) {
        super(context, resource, objects);
    }

    // getview Adapter
    @NonNull
    @Override
    public View getView(int position,View convertView,ViewGroup parent) {
        View v = convertView;

        if (v == null)
        {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v =vi.inflate(R.layout.quanli_the_qr_custom_adapter,null);

        }

        // set to text view;

        benhnhan list = getItem(position);

        if(list != null)
        {
            // set Text view
            TextView tvName = v.findViewById(R.id.tvName);
            TextView tvcmnd = v.findViewById(R.id.tvSoCMND);
            TextView tvNghe = v.findViewById(R.id.tvNgheNghiep);
            TextView tvdate = v.findViewById(R.id.tvDate);


            //
            tvName.setText("Tên: "+list.getTenBenhNhan());
            tvcmnd.setText("CMND: "+list.getSoCMND());
            tvNghe.setText("Nghề nghiệp: "+list.getNgheNghiep());
            try {
                tvdate.setText("Ngày sinh: " +Formatt(list.getNgaySinh()));
            } catch (ParseException e) {
                e.printStackTrace();
            }


        }






        return v;
    }



     // format day

    String Formatt(String date_s) throws ParseException {

        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dt.parse(date_s);
        SimpleDateFormat dt1 = new SimpleDateFormat("dd-MM-yyyy");
        System.out.println(dt1.format(date));
        return dt1.format(date);

    }
}

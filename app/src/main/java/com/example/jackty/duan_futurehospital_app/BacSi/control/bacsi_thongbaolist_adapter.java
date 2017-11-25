package com.example.jackty.duan_futurehospital_app.BacSi.control;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.jackty.duan_futurehospital_app.BacSi.model.listthongbao;
import com.example.jackty.duan_futurehospital_app.R;

import java.util.List;

/**
 * Created by jackty on 23/10/2017.
 */

public class bacsi_thongbaolist_adapter extends ArrayAdapter<listthongbao>{


    public bacsi_thongbaolist_adapter(Context context, int resource, List<listthongbao> objects) {
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
            v =vi.inflate(R.layout.bacsi_thongbao_customadapter,null);

        }

        // set to text view;

        listthongbao list = getItem(position);

        if(list != null)
        {
            // set Text view
            TextView tv_tieude = v.findViewById(R.id.tv_listbsthongbao_tieude);
            TextView tv_noidung = v.findViewById(R.id.tv_listbsthongbao_noidung);

            //

            tv_tieude.setText(list.getTieude());
            tv_noidung.setText(list.getNoidung());


        }






        return v;
    }
}

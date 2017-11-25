package com.example.jackty.duan_futurehospital_app.BacSi.view.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.jackty.duan_futurehospital_app.BacSi.control.bacsi_dsbenhnhan_adapter;
import com.example.jackty.duan_futurehospital_app.BacSi.model.dsbenhnhan;
import com.example.jackty.duan_futurehospital_app.BacSi.view.activity.Bacsi_chitietkhambenh_Activity;
import com.example.jackty.duan_futurehospital_app.BacSi.view.DownloadJsonInterface;
import com.example.jackty.duan_futurehospital_app.ClassInfo.BenhAnKhamNgay;
import com.example.jackty.duan_futurehospital_app.ClassInfo.BenhAnKhoa;
import com.example.jackty.duan_futurehospital_app.Login.view.fragment.login_Fragment;
import com.example.jackty.duan_futurehospital_app.R;
import com.example.jackty.duan_futurehospital_app.URL_sever.DowloadDuLieuJson;
import com.example.jackty.duan_futurehospital_app.URL_sever.ParserDuLieuJson;
import com.example.jackty.duan_futurehospital_app.URL_sever.urlsever;
import com.stone.vega.library.VegaLayoutManager;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class bacsi_fragment_trangchu extends Fragment implements DownloadJsonInterface {
    private static View view;

    // recyclerview


    ArrayList<BenhAnKhamNgay> benhAnKhoaArrayList;
    ParserDuLieuJson parserDuLieuJson;

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    private int redColor, greenColor;

    public bacsi_fragment_trangchu() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.bacsi_fragment_trangchu, container, false);

        // test rycyclerview
        // 3. recyclerView


        // onclick ryclerview


        redColor = getResources().getColor(R.color.red);
        greenColor = getResources().getColor(R.color.green);

        // add cứng dữ liệu
        prepareDataList();


        return view;
    }

    // add cứng dữ liệu để test recylerview

    private void prepareDataList() {
        if (login_Fragment.taikhoanArrayList == null) {
            Toast.makeText(getContext(), "loi", Toast.LENGTH_SHORT).show();
        } else {
            int khoa = login_Fragment.taikhoanArrayList.get(0).getKhoa();
            String link = urlsever.getChuaRaVienTheoKhoaDetail(khoa);
            DowloadDuLieuJson dowloadDuLieuJson = new DowloadDuLieuJson(this);
            dowloadDuLieuJson.execute(link);


//        for (int i = 0; i < 15; i++) {
//            dataList.add(new dsbenhnhan(1,"Nguyen Van A","1/1/2018","sot xuat huyet","306"));
//        }
        }
    }


    // get adapter to recyclerview
    private RecyclerView.Adapter getAdapter() {
        final LayoutInflater inflater = LayoutInflater.from(getActivity());
        RecyclerView.Adapter adapter = new RecyclerView.Adapter() {
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = inflater.inflate(R.layout.bacsi_recycler_item, parent, false);
                return new bacsi_dsbenhnhan_adapter(view);
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
                bacsi_dsbenhnhan_adapter myHolder = (bacsi_dsbenhnhan_adapter) holder;
                try {
                    myHolder.bindData(benhAnKhoaArrayList.get(position));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                // onclick item recylerview
                myHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Toast.makeText(getContext(), "clicked on" + position + " :" + benhAnKhoaArrayList.get(position).getTenBenhNhan(), Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getContext(), Bacsi_chitietkhambenh_Activity.class));
                    }
                });
            }

            @Override
            public int getItemCount() {
                return benhAnKhoaArrayList.size();
            }
        };
        return adapter;
    }


    @Override
    public void starDownload() {

    }

    @Override
    public void finishDownload(String s) {
        parserDuLieuJson = new ParserDuLieuJson(s);
        benhAnKhoaArrayList = parserDuLieuJson.GetArrayBenhAnTheoKhoaTrongNgay();
        Toast.makeText(getContext(), "" + benhAnKhoaArrayList.size(), Toast.LENGTH_SHORT).show();

        recyclerView = (RecyclerView) view.findViewById(R.id.bacsi_recyclerview_trangchu);
        recyclerView.setLayoutManager(new VegaLayoutManager());

        adapter = getAdapter();

        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}

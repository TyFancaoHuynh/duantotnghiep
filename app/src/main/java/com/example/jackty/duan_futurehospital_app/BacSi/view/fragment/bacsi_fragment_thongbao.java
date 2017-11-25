package com.example.jackty.duan_futurehospital_app.BacSi.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jackty.duan_futurehospital_app.BacSi.control.bacsi_thongbaolist_adapter;
import com.example.jackty.duan_futurehospital_app.BacSi.model.listthongbao;
import com.example.jackty.duan_futurehospital_app.BacSi.view.activity.Bacsi_Activity;
import com.example.jackty.duan_futurehospital_app.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class bacsi_fragment_thongbao extends Fragment implements View.OnClickListener,SwipeRefreshLayout.OnRefreshListener {

    // khai báo

    private static View view;
    ImageButton btn_seen;
    ListView list_thongbao;
    TextView tv_none_notification;
    // SwipeRefreshLayout

    private static SwipeRefreshLayout swipeRefreshLayout;


    //

    public static ArrayList<listthongbao> Arr_listthongbao;
    bacsi_thongbaolist_adapter thongbaoadapter;
     public static  int countnoti;



    public bacsi_fragment_thongbao() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.bacsi_fragment_thongbao, container, false);
        // innit
        innit();

        btn_seen.setOnClickListener(this);
        swipeRefreshLayout.setOnRefreshListener(this);

        //setTVnotication


        
        // set list view 

      //  setdatalistthongbao();
        /**
         * Showing Swipe Refresh animation on activity create
         * As animation won't start on onCreate, post runnable is used
         */
        swipeRefreshLayout.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        swipeRefreshLayout.setRefreshing(true);

                                        setdatalistthongbao();
                                        countnoti = Arr_listthongbao.size();


                                    }
                                }
        );








        list_thongbao.setAdapter(thongbaoadapter);

        thongbaoadapter.notifyDataSetChanged();













        return view;
    }
    // ======================== Set list thong bao  =======================================================>

    private void setdatalistthongbao()
    {
        // showing refresh animation before making http call
        swipeRefreshLayout.setRefreshing(true);

        for (int i = 0 ; i < 5 ; i++)
        {
            Arr_listthongbao.add(new listthongbao("Lịch khám" , "Còn 10p nữa đến giờ khám bệnh nhân Nguyễn Văn A, tại P. 306"));

        }


        // count noti
        countnoti = Arr_listthongbao.size();
        thongbaoadapter.notifyDataSetChanged();

        // stopping swipe refresh
        swipeRefreshLayout.setRefreshing(false);





    }

    // ======================== Anh xa =======================================================>
    private void innit()
    {
        btn_seen = view.findViewById(R.id.btn_seen);
        list_thongbao = view.findViewById(R.id.listview_bacsi_thongbao);
        tv_none_notification = view.findViewById(R.id.tv_nonenoti);
        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout);
        Arr_listthongbao = new ArrayList<>();
        thongbaoadapter = new bacsi_thongbaolist_adapter(getActivity(),R.layout.bacsi_thongbao_customadapter,Arr_listthongbao);
    }

    // ======================== Onclick =======================================================>

    @Override
    public void onClick(View v) {
        int t = v.getId();
        if(t == R.id.btn_seen)
        {
            Arr_listthongbao.clear();
            thongbaoadapter.notifyDataSetChanged();
            countnoti = Arr_listthongbao.size();
            Toast.makeText(getActivity(), ""+countnoti, Toast.LENGTH_SHORT).show();


            // setTextnone  notification

            setTVnotication();


        }

    }

    // ======================== set Tv _ none _ noti =======================================================>

        private void setTVnotication()
        {
            if(countnoti == 0)
            {
                tv_none_notification.setVisibility(View.VISIBLE);
            }
            else
                if(countnoti > 0)
                {
                    tv_none_notification.setVisibility(View.GONE);
                }
        }


    @Override
    public void onRefresh() {
            setdatalistthongbao();
        countnoti = Arr_listthongbao.size();
        setTVnotication();
    }
}

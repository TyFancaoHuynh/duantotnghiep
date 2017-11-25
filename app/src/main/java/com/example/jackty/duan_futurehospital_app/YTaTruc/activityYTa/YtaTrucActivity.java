package com.example.jackty.duan_futurehospital_app.YTaTruc.activityYTa;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jackty.duan_futurehospital_app.R;
import com.example.jackty.duan_futurehospital_app.URL_sever.urlsever;
import com.example.jackty.duan_futurehospital_app.YTaTruc.Service.CanhBaoService;
import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;

public class YtaTrucActivity extends AppCompatActivity {
    private Socket mSocket;
    Emitter.Listener onIdServer;
    TextView tvNhipTim;
    private static String eventWarning = "SERVER_SEND_NHIPTIM";
    int nhiptim;

    {
        try {

            mSocket = IO.socket(urlsever.local);
        } catch (URISyntaxException e) {

        }
        onIdServer = new Emitter.Listener() {
            @Override
            public void call(Object... args) {

            }
        };

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ytatruc_main);
        tvNhipTim = (TextView) findViewById(R.id.txtNhipTim);
        Intent intent=new Intent(getApplicationContext(), CanhBaoService.class);
        startService(intent);
        mSocket.connect();
//        mSocket.on(eventWarning, onIdServer);
        mSocket.on(eventWarning, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                Log.d("Dulieu","1");
                try {

                    Log.d("Dulieu","2");
                    JSONObject jsonObject = new JSONObject(args[0].toString());

                    nhiptim = jsonObject.getInt("nhiptim");



                } catch (JSONException e) {
                    e.printStackTrace();
                }


                Handler requestHandler = new Handler(Looper.getMainLooper());

     /* Handler from UI Thread to process messages */

                final Handler responseHandler = new Handler(Looper.getMainLooper()) {
                    @Override
                    public void handleMessage(Message msg) {
                        Toast.makeText(getApplicationContext(),""+nhiptim,Toast.LENGTH_SHORT).show();
                        tvNhipTim.setText("Nhip tim:" +nhiptim);

                    }
                };
                Runnable myRunnable = new Runnable() {
                    @Override
                    public void run() {
                        try {

                            String text = "hhhhh";
                            Message msg = new Message();
                            responseHandler.sendMessage(msg);
                            System.out.println(text.toString());
                        } catch (Exception err) {
                            err.printStackTrace();
                        }
                    }
                };
                requestHandler.post(myRunnable);
            }

        });
    }

}

package com.example.jackty.duan_futurehospital_app.YTaTruc.Service;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jackty.duan_futurehospital_app.R;
import com.example.jackty.duan_futurehospital_app.URL_sever.urlsever;
import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.net.URISyntaxException;

/**
 * Created by TiepNguyen on 18-11-17.
 */

public class CanhBaoService extends Service {
    private WindowManager mWindowManager;
    private View mFloatingView;
    private Socket mSocket;
    MediaPlayer player;
    int idd=0;
    int nhiptim;
    String ten,phong;
    Boolean t;
    private static String eventWarning = "SERVER_SEND_WARNING";

    Emitter.Listener onIdServer;

    {
        try {

            mSocket = IO.socket(urlsever.local);
        } catch (URISyntaxException e) {

        }
        onIdServer = new Emitter.Listener() {
            @Override
            public void call(Object... args) {
//                int NhipTim = 0;
//                int id=0;
//                String ten,phong;
//                try {
//                    JSONObject jsonObject = new JSONObject(args[0].toString());
//
//                    JSONObject jsonId = jsonObject.getJSONObject("message");
//                    NhipTim = jsonId.getInt("nhiptim");
//                    JSONArray jsonArray = jsonObject.getJSONArray("result");
////                    message=jsonArray.toString();
//                    JSONObject jsonObject1 = jsonArray.getJSONObject(0);
//                    id=jsonObject1.getInt("id");
//
//                    ten = jsonObject1.getString("TenBenhNhan");
//
//                    phong = jsonObject1.getString("Phong");
//
////                    message=jsonObject1.getString("Phong");
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                Log.d("Dulieu", "" + NhipTim);
            }
        };

    }


    public CanhBaoService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        t=false;
        player = MediaPlayer.create(this, R.raw.nhac);
        player.setLooping(true); // Set looping
        player.setVolume(100, 100);
        Log.i("DuLieu", "onCreate() , service started...");

        mSocket.connect();
//        mSocket.on(eventWarning, onIdServer);
        mSocket.on(eventWarning, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                Log.d("Dulieu","1");
                try {

                    Log.d("Dulieu","2");
                    JSONObject jsonObject = new JSONObject(args[0].toString());

                    JSONObject jsonId = jsonObject.getJSONObject("message");
                    nhiptim = jsonId.getInt("nhiptim");
                    JSONArray jsonArray = jsonObject.getJSONArray("result");
//                    message=jsonArray.toString();
                    JSONObject jsonObject1 = jsonArray.getJSONObject(0);
                    idd=jsonObject1.getInt("id");

                    ten = jsonObject1.getString("TenBenhNhan");

                    phong = jsonObject1.getString("Phong");

//                    Toast.makeText(getApplicationContext(), "" +nhiptim+ten+phong, Toast.LENGTH_SHORT).show();
//                    message=jsonObject1.getString("Phong");
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                Handler requestHandler = new Handler(Looper.getMainLooper());

     /* Handler from UI Thread to process messages */

                final Handler responseHandler = new Handler(Looper.getMainLooper()) {
                    @Override
                    public void handleMessage(Message msg) {

            /* Processing handleMessage */

                        Log.d("Dulieu","3");
                        if(t==false) ShowDialog(idd,ten,phong,nhiptim);
                    }
                };
                Runnable myRunnable = new Runnable() {
                    @Override
                    public void run() {
                        try {
                   /* Send an Event to UI Thread through message.
                      Add business logic and prepare message by
                      replacing example code */

                            String text = "hhhhh";
                            Message msg = new Message();

                            msg.obj = text.toString();
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

    public int onStartCommand(Intent intent, int flags, int startId) {
        player = MediaPlayer.create(this, R.raw.nhac);
        return Service.START_STICKY;
    }

    void ShowDialog(int id,String ten,String phong,int nhiptim) {
        t=true;
        player.start();
        final WindowManager.LayoutParams params;
        //Inflate the floating view layout we created
        //chuyen layout ve view vi windowManager chi nhận view không nhận xml
        mFloatingView = LayoutInflater.from(this).inflate(R.layout.layout_thong_bao, null);
        Toast.makeText(this, "" + Build.VERSION.SDK_INT + " - " + Build.VERSION_CODES.LOLLIPOP, Toast.LENGTH_SHORT).show();
        //Note: sdk >= 19 layoutparams.type == TYPE_TOAST or TYPE_SYSTEM_ALERT
        //sdk <19 TYPE_PHONE
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //Add the view to the window.
            //thiết lập thông số cho view window hiển thị
            params = new WindowManager.LayoutParams(
                    WindowManager.LayoutParams.WRAP_CONTENT,
                    WindowManager.LayoutParams.WRAP_CONTENT,
                    WindowManager.LayoutParams.TYPE_TOAST,
                    WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                    PixelFormat.TRANSLUCENT);//làm cho layout của bạn trở nên trong suốt những phần không hiển thị (bên ngoài layout bạn đã tạo)

        } else {
            //Add the view to the window.
            //thiết lập thông số cho view window hiển thị
            params = new WindowManager.LayoutParams(
                    WindowManager.LayoutParams.WRAP_CONTENT,
                    WindowManager.LayoutParams.WRAP_CONTENT,
                    WindowManager.LayoutParams.TYPE_PHONE,
                    WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                    PixelFormat.TRANSLUCENT);//làm cho layout của bạn trở nên trong suốt những phần không hiển thị (bên ngoài layout bạn đã tạo)

        }

        //Specify the view position
        // thiết lập vị trí ban đầu khi chạy
        params.gravity = Gravity.CENTER | Gravity.CENTER;        //Initially view will be added to top-left corner
        params.x = 0;
        params.y = 100;

        //Add the view to the window
        //add màn hình view vào alert window
        mWindowManager = (WindowManager) getSystemService(WINDOW_SERVICE);

        mWindowManager.addView(mFloatingView, params);
        //Ánh xạ màn view thông qua view
        //The root element of the collapsed view layout
        final View collapsedView = mFloatingView.findViewById(R.id.collapse_viewThongBao);
        //sự kiên khi kéo thả
        //Set the close button
        Button closeButton = (Button) mFloatingView.findViewById(R.id.close_btnThongBao);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player.stop();
                mWindowManager.removeView(mFloatingView);
                t=false;
            }
        });
        TextView txtTen = (TextView) mFloatingView.findViewById(R.id.txtTenThongBao);
        txtTen.setText(ten);
        TextView txtPhong = (TextView) mFloatingView.findViewById(R.id.txtPhongThongBao);
        txtPhong.setText(phong);
        TextView txtNhipTim = (TextView) mFloatingView.findViewById(R.id.txtNhipTimThongBao);
        txtNhipTim.setText(nhiptim+" BPM");


//        Toast.makeText(this, "" +idd+ten+phong+nhiptim, Toast.LENGTH_SHORT).show();
        //Drag and move floating view using user's touch action.
        mFloatingView.findViewById(R.id.root_container2).setOnTouchListener(new View.OnTouchListener() {
            private int initialX;
            private int initialY;
            private float initialTouchX;
            private float initialTouchY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    //Sự bắt đầu của cảm ứng.
                    case MotionEvent.ACTION_DOWN:
                        //ghi nhớ vị trí ban đầu
                        //remember the initial position.
                        initialX = params.x;
                        initialY = params.y;
                        //lấy vị trí chạm ban đầu
                        //get the touch location
                        initialTouchX = event.getRawX();
                        Log.d("DuLieu", initialTouchX + " x");
                        initialTouchY = event.getRawY();
                        Log.d("DuLieu", initialTouchY + " y");
                        return true;
                    //Sự kết thúc của cảm ứng.
                    case MotionEvent.ACTION_UP:
                        //lấy số param mà đã kéo thả
                        int Xdiff = (int) (event.getRawX() - initialTouchX);
                        int Ydiff = (int) (event.getRawY() - initialTouchY);

                        Log.d("DuLieu", Xdiff + " Xdiff");
                        Log.d("DuLieu", Ydiff + " Ydiff");


                        //The check for Xdiff <10 && YDiff< 10 because sometime elements moves a little while clicking.
                        //So that is click event.


                        return true;
                    //Các hình cảm ứng đã chuyển giữa xuống và đi lên hành động.
                    case MotionEvent.ACTION_MOVE:
                        //tính tọa độ di chuyển => gán lại cho view
                        //Calculate the X and Y coordinates of the view.
                        params.x = initialX + (int) (event.getRawX() - initialTouchX);
                        params.y = initialY + (int) (event.getRawY() - initialTouchY);


                        //Update the layout with new X & Y coordinate
                        mWindowManager.updateViewLayout(mFloatingView, params);
                        return true;
                }
                return false;
            }
        });
    }


    /**
     * Detect if the floating view is collapsed or expanded.
     *
     * @return true if the floating view is collapsed.
     */


}
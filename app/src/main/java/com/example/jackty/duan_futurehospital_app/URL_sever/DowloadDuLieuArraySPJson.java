package com.example.jackty.duan_futurehospital_app.URL_sever;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


/**
 * Created by TiepNguyen on 06-08-17.
 */

public class DowloadDuLieuArraySPJson extends AsyncTask<ArrayList<String>,Void,ArrayList<String>> {
    //goi interface
//    public AsyncResponse delegate = null;
    StringBuilder dulieu;
    StringBuilder adddulieu;
    urlsever server;

    @Override
    protected void onPreExecute() {
        server= new urlsever();
    }

    @Override
    protected ArrayList<String> doInBackground(ArrayList<String>... params) {
        //result tong mang
        ArrayList<String> result = new ArrayList<String>();
        //result ran param vao result2
        ArrayList<String> result2 = params[0];


        //ket noi mang de tai json ve
        try {
            adddulieu= new StringBuilder();
            for (int i = 0; i < result2.size(); i++) {
                URL url= new URL(result2.get(i).toString());
                //tao giao thuc ket noi
                HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                httpURLConnection.connect();
                //tao luong nhan ket noi ve, thread
                InputStream inputStream= httpURLConnection.getInputStream();
                //gan inputStream vao inputStreamreader
                InputStreamReader reader= new InputStreamReader(inputStream);
                //them buffer cho bao mat
                BufferedReader bufferedReader=new BufferedReader(reader);

                String dong="";
                dulieu=new StringBuilder();
                //dov tu buffer vo , neu k null se thuc hien, khi con du lieu
                while ((dong= bufferedReader.readLine())!=null){
                    dulieu.append(dong);
                }
//                adddulieu.append(dong);
                result.add(dulieu.toString());

                Log.d("UrlDuLieu",dulieu.toString());
                //tat luong di
                bufferedReader.close();
                reader.close();
                inputStream.close();
                httpURLConnection.disconnect();
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    protected void onPostExecute(ArrayList<String> s) {
        super.onPostExecute(s);
        //ep ve string json
        String dulieuJson="";
        for (int i = 0; i <s.size() ; i++) {
            dulieuJson+= s.get(i).toString();
        }
        dulieuJson=server.replaceMultiArrayJson(dulieuJson);
//        delegate.processFinish(dulieuJson);
    }
}
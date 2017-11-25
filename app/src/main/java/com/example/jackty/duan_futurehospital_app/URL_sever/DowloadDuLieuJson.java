package com.example.jackty.duan_futurehospital_app.URL_sever;

import android.os.AsyncTask;
import android.util.Log;

import com.example.jackty.duan_futurehospital_app.BacSi.view.DownloadJsonInterface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * Created by TiepNguyen on 06-08-17.
 */

public class DowloadDuLieuJson extends AsyncTask<String,Void,String> {
    //goi interface
//    public AsyncResponse delegate = null;
    StringBuilder dulieu;
    public DownloadJsonInterface downloadJsonInterface;

    public DowloadDuLieuJson(DownloadJsonInterface downloadJsonInterface) {
        this.downloadJsonInterface = downloadJsonInterface;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
        //ket noi mang de tai json ve
        try {
            URL url= new URL(params[0]);
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
            //tat luong di
            bufferedReader.close();
            reader.close();
            inputStream.close();
            httpURLConnection.disconnect();
            Log.d("Dulieu",dulieu.toString());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return dulieu.toString();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

//        ParserDuLieuJson parserDuLieuJson= new ParserDuLieuJson(s);
//        parserDuLieuJson.LayDiaChi();
//        delegate.processFinish(s);
        downloadJsonInterface.finishDownload(s);

    }
}
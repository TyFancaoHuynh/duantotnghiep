package com.example.jackty.duan_futurehospital_app.URL_sever;

import android.util.Log;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by Fancao Huynh (Jack) on 11/3/2017.
 */

public class urlsever {
//        public static String local = "http://10.0.3.2:3000/";
    public static String local = "http://192.168.0.108:3000/";


    // ================= link getChuaRaVienTheoKhoa================================================>
    public static String getChuaRaVienTheoKhoa(int khoa){
        String getChuaRaVienTheoKhoa="";
        if(khoa==0){
            getChuaRaVienTheoKhoa=local+"getChuaRaVienTheoKhoa?khoa=ngoaian";
        }else if(khoa==1){
            getChuaRaVienTheoKhoa=local+"getChuaRaVienTheoKhoa?khoa=noian"+khoa;
        }else {
            Log.d("Dulieu","L?i khoa");
        }
        //

        return getChuaRaVienTheoKhoa;
    }

    public static String getChuaRaVienTheoKhoaDetail(int khoa){
        String getChuaRaVienTheoKhoa="";
        getChuaRaVienTheoKhoa=local+"getchuaRaVienTheoKhoaDetail?khoa="+khoa;

        return getChuaRaVienTheoKhoa;
    }
    // =================================================================>
//    public static String getChuaRaVienTheoKhoa=local+"getChuaRaVienTheoKhoa?khoa=";
    public String replaceMultiArrayJson(String s){
        String getData= s;
        getData=getData.replace("][",",");
        return getData;
    }

    // ============ Link get benh nhan to return QR =============================================>


    public static String LinkreturnQR(String TenBN) {
        String urladd = urlsever.local +"getBenhNhanToReturnQR?TenBenhNhan="+"%22"+encodeUrl(TenBN)+"%22";
        urladd = urladd.replace(" ", "%20");

        return urladd;
    }



    // ============ Link get benh nhan theo ten (Quan Ly Benh Nhan) =============================================>


    public static String LinkGetBenhNhanTheoTen(String TenBN) {
        String urladd = urlsever.local +"getBenhNhanTheoTen?TenBenhNhan="+"%22"+encodeUrl(TenBN)+"%22";
        urladd = urladd.replace(" ", "%20");

        return urladd;
    }

    // ============ Link get benh nhan theo so CMND (Quan Ly Benh Nhan) =============================================>
    public static String LinkGetBenhNhanTheoCMND(String soCMND) {
        String urladd = urlsever.local +"getBenhNhanTheoCMND?SoCMND="+encodeUrl(soCMND);
        urladd = urladd.replace(" ", "%20");

        return urladd;
    }


    // ============ Xóa bệnh nhân theo id============================================>

    public static String XoaBenhNhanTheoID(int id) {
        String urladd = urlsever.local +"XoaBenhNhanThe?id="+id;
        urladd = urladd.replace(" ", "%20");

        return urladd;
    }

    // ============ Link update  benh nhan  =============================================>
    public static String UpdateBN(int id,String name, String date ,String cmnd ,String job , String location) {
        String urladd = urlsever.local +"UpdateBN?id="+id+"&TenBenhNhan=" + encodeUrl(name) + "&NamSinh=" + encodeUrl(date)+ "&SoCMND=" + encodeUrl(cmnd)+ "&NgheNghiep="+ encodeUrl(job)+ "&DiaChi="+ encodeUrl(location);

        return urladd;
    }

    // ============ Link update  benh nhan  =============================================>
    public static String getBenhnhantheoid(int id) {
        String urlget = urlsever.local +"getBenhNhanTheoId?id="+id;

        return urlget;
    }





    // endcode params d? nh?n ti?ng vi?t d?y lên sever

    private static String encodeUrl(String params) {
        try {
            params = URLEncoder.encode(params, "UTF-8");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return params;
    }

}

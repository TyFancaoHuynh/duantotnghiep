package com.example.jackty.duan_futurehospital_app.URL_sever;

import android.util.Log;

import com.example.jackty.duan_futurehospital_app.ClassInfo.BenhAnKhamNgay;
import com.example.jackty.duan_futurehospital_app.ClassInfo.BenhAnKhoa;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * Created by TiepNguyen on 06-08-17.
 */

public class ParserDuLieuJson {
    private String Dulieu;
//    GetFinishInterface getFinishInterface;

    public ParserDuLieuJson(String dulieu) {
        Dulieu = dulieu;
    }

    //    public ParserDuLieuJson(String dulieu, GetFinishInterface getFinishInterface) {
//        Dulieu = dulieu;
//        this.getFinishInterface = getFinishInterface;
//    }
    //lay san pham tu munti jsonArray
    public ArrayList<BenhAnKhoa> GetArrayBenhAnTheoKhoa() {
        ArrayList<BenhAnKhoa> benhAnKhoaArrayList = new ArrayList<>();
        int Id;
        int idBenhAn;
        int idBenhNhan;
        String tenBenhNhan;
        String ngayNhapVien;
        String trieuChung;
        String tinhTrang;
        int raVien;
        String Phong;

        try {
            JSONArray jsonArray = new JSONArray(Dulieu);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Log.d("dulieuJson", jsonObject.getString("TenBenhNhan"));
                Id = jsonObject.getInt("id");
                idBenhAn = jsonObject.getInt("idBenhAn");
                idBenhNhan = jsonObject.getInt("idBenhNhan");
                tenBenhNhan = jsonObject.getString("TenBenhNhan");
                ngayNhapVien = jsonObject.getString("NgayNhapVien");
                trieuChung = jsonObject.getString("TrieuChung");
                tinhTrang = jsonObject.getString("TinhTrang");
                raVien = jsonObject.getInt("RaVien");
                Phong = jsonObject.getString("Phong");
                benhAnKhoaArrayList.add(new BenhAnKhoa(Id, idBenhAn, idBenhNhan, tenBenhNhan, ngayNhapVien, trieuChung, tinhTrang, raVien, Phong));
            }
//            getFinishInterface.FinishGetData(sanPhamArrayList);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return benhAnKhoaArrayList;
    }
    public ArrayList<BenhAnKhamNgay> GetArrayBenhAnTheoKhoaTrongNgay(){
        ArrayList<BenhAnKhamNgay> benhAnKhoaArrayList= new ArrayList<>();
        int id;
        String TenBenhNhan;
        String NgayNhapVien;
        String Phong;
        String TenBenh;
        int Kham;

        try {
            JSONArray jsonArray= new JSONArray(Dulieu);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject= jsonArray.getJSONObject(i);
                Log.d("dulieuJson",jsonObject.getString("TenBenhNhan"));
                id= jsonObject.getInt("id");
                TenBenhNhan= jsonObject.getString("TenBenhNhan");
                NgayNhapVien=jsonObject.getString("NgayNhapVien");
                Phong=jsonObject.getString("Phong");
                TenBenh=jsonObject.getString("TenBenh");
                Kham= jsonObject.getInt("Kham");
                benhAnKhoaArrayList.add(new BenhAnKhamNgay(id,TenBenhNhan,NgayNhapVien,Phong,TenBenh,Kham));
            }
//            getFinishInterface.FinishGetData(sanPhamArrayList);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return benhAnKhoaArrayList;
    }
//    //lay san pham tu munti jsonArray
//    public void GetJsonDonHang(){
//        int insertId;
//        try {
//            JSONObject jsonObject= new JSONObject(Dulieu);
//            insertId= jsonObject.getInt("insertId");
//            getFinishInterface.FinishGetIdDonhang(insertId);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//    }
}

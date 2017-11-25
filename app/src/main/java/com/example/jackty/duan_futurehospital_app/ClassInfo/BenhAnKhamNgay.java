package com.example.jackty.duan_futurehospital_app.ClassInfo;

/**
 * Created by TiepNguyen on 14-11-17.
 */

public class BenhAnKhamNgay {
    int id;
    String TenBenhNhan;
    String NgayNhapVien;
    String Phong;
    String TenBenh;
    int Kham;

    public BenhAnKhamNgay() {
    }

    public BenhAnKhamNgay(int id, String tenBenhNhan, String ngayNhapVien, String phong, String tenBenh, int kham) {
        this.id = id;
        TenBenhNhan = tenBenhNhan;
        NgayNhapVien = ngayNhapVien;
        Phong = phong;
        TenBenh = tenBenh;
        Kham = kham;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenBenhNhan() {
        return TenBenhNhan;
    }

    public void setTenBenhNhan(String tenBenhNhan) {
        TenBenhNhan = tenBenhNhan;
    }

    public String getNgayNhapVien() {
        return NgayNhapVien;
    }

    public void setNgayNhapVien(String ngayNhapVien) {
        NgayNhapVien = ngayNhapVien;
    }

    public String getPhong() {
        return Phong;
    }

    public void setPhong(String phong) {
        Phong = phong;
    }

    public String getTenBenh() {
        return TenBenh;
    }

    public void setTenBenh(String tenBenh) {
        TenBenh = tenBenh;
    }

    public int getKham() {
        return Kham;
    }

    public void setKham(int kham) {
        Kham = kham;
    }
}

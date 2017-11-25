package com.example.jackty.duan_futurehospital_app.Login.model;

import java.util.Date;

/**
 * Created by Fancao Huynh (Jack) on 11/3/2017.
 */

public class taikhoan {
    int id;
    int chucvu ;
    String Ten;
    String NgaySinh;
    String DiaChi;
    String Sdt;
    int Khoa;
    String TaiKhoan;
    String MatKhau;

    public taikhoan() {
    }

    public taikhoan(int id, int chucvu, String ten, String ngaySinh, String diaChi, String sdt, int khoa, String taiKhoan, String matKhau) {
        this.id = id;
        this.chucvu = chucvu;
        Ten = ten;
        NgaySinh = ngaySinh;
        DiaChi = diaChi;
        Sdt = sdt;
        Khoa = khoa;
        TaiKhoan = taiKhoan;
        MatKhau = matKhau;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getChucvu() {
        return chucvu;
    }

    public void setChucvu(int chucvu) {
        this.chucvu = chucvu;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String ten) {
        Ten = ten;
    }

    public String getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        NgaySinh = ngaySinh;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public String getSdt() {
        return Sdt;
    }

    public void setSdt(String sdt) {
        Sdt = sdt;
    }

    public int getKhoa() {
        return Khoa;
    }

    public void setKhoa(int khoa) {
        Khoa = khoa;
    }

    public String getTaiKhoan() {
        return TaiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        TaiKhoan = taiKhoan;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String matKhau) {
        MatKhau = matKhau;
    }

    @Override
    public String toString() {
        return TaiKhoan+"-"+MatKhau;
    }
}

package com.example.jackty.duan_futurehospital_app.ThuKiCreateQR.model;

/**
 * Created by Fancao Huynh (Jack) on 11/5/2017.
 */

public class benhnhan

{
    int id;
    String TenBenhNhan, NgaySinh,SoCMND, NgheNghiep, DiaChi;

    public benhnhan() {
    }

    public benhnhan(int id, String tenBenhNhan, String ngaySinh, String soCMND, String ngheNghiep, String diaChi) {
        this.id = id;
        TenBenhNhan = tenBenhNhan;
        NgaySinh = ngaySinh;
        SoCMND = soCMND;
        NgheNghiep = ngheNghiep;
        DiaChi = diaChi;
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

    public String getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        NgaySinh = ngaySinh;
    }

    public String getSoCMND() {
        return SoCMND;
    }

    public void setSoCMND(String soCMND) {
        SoCMND = soCMND;
    }

    public String getNgheNghiep() {
        return NgheNghiep;
    }

    public void setNgheNghiep(String ngheNghiep) {
        NgheNghiep = ngheNghiep;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    @Override
    public String toString() {
        return ""+id;
    }
}

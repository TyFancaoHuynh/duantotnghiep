package com.example.jackty.duan_futurehospital_app.BacSi.model;

import java.util.Date;

/**
 * Created by jackty on 18/10/2017.
 */

public class dsbenhnhan
{
    int id;
    String name;
    String ngaynhapvien;
    String tenbenh;
    String phongbenh;

    public dsbenhnhan(int id, String name, String ngaynhapvien, String tenbenh, String phongbenh) {
        this.id = id;
        this.name = name;
        this.ngaynhapvien = ngaynhapvien;
        this.tenbenh = tenbenh;
        this.phongbenh = phongbenh;
    }

    public dsbenhnhan() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNgaynhapvien() {
        return ngaynhapvien;
    }

    public void setNgaynhapvien(String ngaynhapvien) {
        this.ngaynhapvien = ngaynhapvien;
    }

    public String getTenbenh() {
        return tenbenh;
    }

    public void setTenbenh(String tenbenh) {
        this.tenbenh = tenbenh;
    }

    public String getPhongbenh() {
        return phongbenh;
    }

    public void setPhongbenh(String phongbenh) {
        this.phongbenh = phongbenh;
    }
}

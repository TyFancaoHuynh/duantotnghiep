package com.example.jackty.duan_futurehospital_app.ClassInfo;

/**
 * Created by TiepNguyen on 05-11-17.
 */

public class BenhAnKhoa {
    int id;
    int idBenhAn;
    int idBenhNhan;
    String TenBenhNhan;
    String ngayNhapVien;
    String trieuChung;
    String tinhTrang;
    int raVien;
    String Phong;

    public BenhAnKhoa() {
    }

    public BenhAnKhoa(int id, int idBenhAn, int idBenhNhan, String tenBenhNhan, String ngayNhapVien, String trieuChung, String tinhTrang, int raVien, String phong) {
        this.id = id;
        this.idBenhAn = idBenhAn;
        this.idBenhNhan = idBenhNhan;
        TenBenhNhan = tenBenhNhan;
        this.ngayNhapVien = ngayNhapVien;
        this.trieuChung = trieuChung;
        this.tinhTrang = tinhTrang;
        this.raVien = raVien;
        Phong = phong;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdBenhAn() {
        return idBenhAn;
    }

    public void setIdBenhAn(int idBenhAn) {
        this.idBenhAn = idBenhAn;
    }

    public int getIdBenhNhan() {
        return idBenhNhan;
    }

    public void setIdBenhNhan(int idBenhNhan) {
        this.idBenhNhan = idBenhNhan;
    }

    public String getTenBenhNhan() {
        return TenBenhNhan;
    }

    public void setTenBenhNhan(String tenBenhNhan) {
        TenBenhNhan = tenBenhNhan;
    }

    public String getNgayNhapVien() {
        return ngayNhapVien;
    }

    public void setNgayNhapVien(String ngayNhapVien) {
        this.ngayNhapVien = ngayNhapVien;
    }

    public String getTrieuChung() {
        return trieuChung;
    }

    public void setTrieuChung(String trieuChung) {
        this.trieuChung = trieuChung;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public int getRaVien() {
        return raVien;
    }

    public void setRaVien(int raVien) {
        this.raVien = raVien;
    }

    public String getPhong() {
        return Phong;
    }

    public void setPhong(String phong) {
        Phong = phong;
    }
}

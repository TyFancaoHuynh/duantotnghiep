package com.example.jackty.duan_futurehospital_app.BacSi.model;

/**
 * Created by jackty on 23/10/2017.
 */

public class listthongbao {
    String tieude;
    String noidung;

    public listthongbao() {
    }

    public listthongbao(String tieude, String noidung) {
        this.tieude = tieude;
        this.noidung = noidung;
    }

    public String getTieude() {
        return tieude;
    }

    public void setTieude(String tieude) {
        this.tieude = tieude;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    
}

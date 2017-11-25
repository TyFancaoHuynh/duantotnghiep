package com.example.jackty.duan_futurehospital_app.ThuKiCreateQR.presenter;

import com.example.jackty.duan_futurehospital_app.ThuKiCreateQR.view.ViewUpdateThe;

/**
 * Created by Fancao Huynh (Jack) on 11/13/2017.
 */

public class PresenterLogicUpdateThe implements PresenterimlXuliUpdate {
    ViewUpdateThe viewUpdateThe;

    public PresenterLogicUpdateThe(ViewUpdateThe viewUpdateThe)
    {
        this.viewUpdateThe =viewUpdateThe;
    }

    @Override
    public void XuliCapNhat()
    {
        viewUpdateThe.Tienhanhcapnhat();

    }
}

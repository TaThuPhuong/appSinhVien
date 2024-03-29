package com.example.htrsinhvin.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.htrsinhvin.DTO.PH18428_TaiKhoan;
import com.example.htrsinhvin.Database.PH18428_DbHelper;

import java.util.ArrayList;

public class PH18428_TaiKhoanDAO {
    PH18428_DbHelper dtbRegister;

    public PH18428_TaiKhoanDAO(Context context) {

        dtbRegister = new PH18428_DbHelper(context);
    }

    public ArrayList<PH18428_TaiKhoan> getALl() {
        ArrayList<PH18428_TaiKhoan> listTK = new ArrayList<>();
        SQLiteDatabase dtb = dtbRegister.getReadableDatabase();
        Cursor cs = dtb.rawQuery("SELECT * FROM taiKhoan", null);
        cs.moveToFirst();
        while (!cs.isAfterLast()) {
            try {
                String tk = cs.getString(0);
                String mk = cs.getString(1);
                PH18428_TaiKhoan t = new PH18428_TaiKhoan(tk, mk);
                listTK.add(t);
                cs.moveToNext();
            } catch (Exception ex) {
                ex.printStackTrace();

            }
        }
        cs.close();
        return listTK;
    }
    public boolean Them(PH18428_TaiKhoan tk) {
        SQLiteDatabase db = dtbRegister.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tenTaiKhoan", tk.getTenTaiKhoan());
        values.put("matKhau", tk.getMatKhau());
        long r = db.insert("taiKhoan", null, values);
        if (r <= 0) {
            return false;
        }
        return true;
    }
    public boolean doiMk(PH18428_TaiKhoan tk) {
        SQLiteDatabase db = dtbRegister.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("matKhau", tk.getMatKhau());
        int r = db.update("taiKhoan", values, "tenTaiKhoan=?", new String[]{tk.getTenTaiKhoan()});
        if (r <= 0) {
            return false;
        }
        return true;
    }
}

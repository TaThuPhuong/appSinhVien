package com.example.htrsinhvin.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class PH18428_DbHelper extends SQLiteOpenHelper {
    static final String dbName ="QLSV";
    static final int versionDB =1;

    public PH18428_DbHelper(Context context) {
        super(context, dbName, null , versionDB);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlLop = "CREATE TABLE Lop (" +
                "maLop TEXT PRIMARY KEY ," +
                "tenLop TEXT NOT NULL)";
        db.execSQL(sqlLop);


        String sqlSV = "CREATE TABLE Sinhvien (" +
                "maSV TEXT PRIMARY KEY ,"+
                "tenSV TEXT NOT NULL," +
                "gioitinh TEXT NOT NULL," +
                "sdt TEXT NOT NULL," +
                "tenLop TEXT REFERENCES Lop(tenLop))";
        db.execSQL(sqlSV);

        String sql = "CREATE TABLE taiKhoan(tenTaiKhoan TEXT PRIMARY KEY, matKhau TEXT NOT NULL)";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

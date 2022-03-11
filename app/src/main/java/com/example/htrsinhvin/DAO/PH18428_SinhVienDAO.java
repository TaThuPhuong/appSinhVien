package com.example.htrsinhvin.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.htrsinhvin.DTO.PH18428_Sinhvien;
import com.example.htrsinhvin.Database.PH18428_DbHelper;

import java.util.ArrayList;
import java.util.List;

public class PH18428_SinhVienDAO {

    private SQLiteDatabase db;
    private PH18428_DbHelper PH18428DbHelper;

    public PH18428_SinhVienDAO(Context context) {
        PH18428_DbHelper PH18428DbHelper = new PH18428_DbHelper(context);
        db = PH18428DbHelper.getWritableDatabase();
    }


    public long insert(PH18428_Sinhvien PH18428Sinhvien) {

        ContentValues values = new ContentValues();

        values.put(PH18428_Sinhvien.COL_MaSV, PH18428Sinhvien.getMaSV());
        values.put(PH18428_Sinhvien.COL_TENSV, PH18428Sinhvien.getTenSV());
        values.put(PH18428_Sinhvien.COL_GT, PH18428Sinhvien.getGioitinh());
        values.put(PH18428_Sinhvien.COL_LOP, PH18428Sinhvien.getTenLop());
        values.put(PH18428_Sinhvien.COL_SDT, PH18428Sinhvien.getSdt());


        return db.insert(PH18428_Sinhvien.TB_NAMESV, null, values);
    }

    public long update(PH18428_Sinhvien PH18428Sinhvien) {
        ContentValues values = new ContentValues();

        values.put(PH18428_Sinhvien.COL_TENSV, PH18428Sinhvien.getTenSV());
        values.put(PH18428_Sinhvien.COL_GT, PH18428Sinhvien.getGioitinh());
        values.put(PH18428_Sinhvien.COL_LOP, PH18428Sinhvien.getTenLop());
        values.put(PH18428_Sinhvien.COL_SDT, PH18428Sinhvien.getSdt());

        String[] arr = new String[]{PH18428Sinhvien.getMaSV()};

        return db.update(PH18428_Sinhvien.TB_NAMESV, values, "maSV = ?", arr );
    }

    public int delete(PH18428_Sinhvien PH18428Sinhvien) {
        String[] arr = new String[]{PH18428Sinhvien.getMaSV()+""};

        return db.delete(PH18428_Sinhvien.TB_NAMESV,"maSV = ?",arr);
    }



    public List<PH18428_Sinhvien> getSinhvien(String sqlSV, String...selectionArgs) {

        List<PH18428_Sinhvien> list = new ArrayList<PH18428_Sinhvien>();

        //định nghĩa cột
        String[] ds_col = new String[]{"*"};


        //tạo con trỏ
        Cursor cursor = db.query(PH18428_Sinhvien.TB_NAMESV,ds_col,null,null,null,null,null);
        if(cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                // lấy dữ liệu
                PH18428_Sinhvien sv = new PH18428_Sinhvien();

                sv.setMaSV(cursor.getString(0));
                sv.setTenSV(cursor.getString(1));
                sv.setGioitinh(cursor.getString(2));
                sv.setSdt(cursor.getString(3));
                sv.setTenLop(cursor.getString(4));

                //cho đối tượng vào ds
                list.add(sv);


                //chuyển con trỏ sang dòng tiếp
                cursor.moveToNext();
            }
        }
        return list;
    }


    public List<PH18428_Sinhvien> getSinhvienAll() {
        String sqlSV = "SELECT * FROM Sinhvien";

        return getSinhvien(sqlSV);
    }


    //get thao ma
    public List<String> getSinhvientenLop() {
        String sql="SELECT tenLop FROM Lop";
        List<String> list = new ArrayList<String>();

        Cursor c = db.rawQuery(sql, null);

        while (c.moveToNext()){
            String tenLop = c.getString(0);
            list.add(tenLop);
        }
        return list;
    }


    // get theo ten

    public  List<PH18428_Sinhvien> getinhvienTenSV(String tenSV){
        String sqlSV = "select * from Sinhvien where tenSV=?";

        List<PH18428_Sinhvien> list = getSinhvien(sqlSV,tenSV);
        return  list;
    }
}

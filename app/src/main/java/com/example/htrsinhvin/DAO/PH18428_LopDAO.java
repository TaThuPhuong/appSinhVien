package com.example.htrsinhvin.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.htrsinhvin.DTO.PH18428_Lop;
import com.example.htrsinhvin.Database.PH18428_DbHelper;

import java.util.ArrayList;
import java.util.List;

public class PH18428_LopDAO {
    private SQLiteDatabase db;

    public PH18428_LopDAO(Context context) {
        PH18428_DbHelper PH18428DbHelper = new PH18428_DbHelper(context);
        db = PH18428DbHelper.getWritableDatabase();
    }

    public long insert(PH18428_Lop PH18428Lop) {

        ContentValues values = new ContentValues();

        values.put(PH18428_Lop.COL_ID, PH18428Lop.getMalop());
        values.put(PH18428_Lop.COL_TEN, PH18428Lop.getTenLop());

        return db.insert(PH18428_Lop.TB_NAME, null, values);
    }

    public int update(PH18428_Lop PH18428Lop) {
        ContentValues values = new ContentValues();

        values.put(PH18428_Lop.COL_TEN, PH18428Lop.getTenLop());

        String[] arr = new String[]{(PH18428Lop.getMalop())};
        return db.update(PH18428_Lop.TB_NAME, values, "maLop = ?", arr );
    }

    public  int delete(PH18428_Lop PH18428Lop) {
        String[] arr = new String[]{PH18428Lop.getMalop()+""};

        return db.delete(PH18428_Lop.TB_NAME,"maLop = ?",arr);
    }


    public List<PH18428_Lop> getLop(String sqlLop, String...selectionArgs) {

        List<PH18428_Lop> list = new ArrayList<PH18428_Lop>();

        String[] ds_col = new String[]{"*"};
        Cursor cursor = db.query(PH18428_Lop.TB_NAME,ds_col,null,null,null,null,null);

        if(cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                // lấy dữ liệu
                PH18428_Lop PH18428Lop = new PH18428_Lop();
                PH18428Lop.setMalop(cursor.getString(0));
                PH18428Lop.setTenLop(cursor.getString(1));


                //cho đối tượng vào ds
                list.add(PH18428Lop);


                //chuyển con trỏ sang dòng tiếp
                cursor.moveToNext();
            }
        }

        return list;
    }

    //get tat ca san pham

    public List<PH18428_Lop> getLopAll() {
        String sqlLop = "SELECT * FROM Lop";

        return getLop(sqlLop);
    }





    // get san pham theo ten

    public  List<PH18428_Lop> getLopTenLop(String tenLop){
        String sqlLop = "select * from Lop where tenLop=?";

        List<PH18428_Lop> list = getLop(sqlLop,tenLop);
        return  list;
    }
}

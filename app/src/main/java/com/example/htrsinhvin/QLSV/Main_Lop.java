package com.example.htrsinhvin.QLSV;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.htrsinhvin.ADAPTER.PH18428_LopAdapter;
import com.example.htrsinhvin.DAO.PH18428_LopDAO;
import com.example.htrsinhvin.DTO.PH18428_Lop;
import com.example.htrsinhvin.R;

import java.util.List;

public class Main_Lop extends AppCompatActivity {
    List<PH18428_Lop> PH18428Lops;

    ListView lvLop;
    Button btnThem;
    public PH18428_LopAdapter adapter;

    PH18428_LopDAO PH18428LopDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class);

        lvLop = findViewById(R.id.lvClass);
        btnThem = findViewById(R.id.btnAddL);
        PH18428LopDao = new PH18428_LopDAO(this);

        adapter = new PH18428_LopAdapter(PH18428LopDao.getLopAll(), PH18428LopDao);

        lvLop.setAdapter(adapter);

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.showDialogAdd(Main_Lop.this);
            }
        });
    }

}
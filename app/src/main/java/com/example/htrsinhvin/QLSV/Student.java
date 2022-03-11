package com.example.htrsinhvin.QLSV;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.htrsinhvin.ADAPTER.PH18428_SvAdapter;
import com.example.htrsinhvin.DAO.PH18428_SinhVienDAO;
import com.example.htrsinhvin.R;

public class Student extends AppCompatActivity {
    Button btnAddSV;
    ListView lvsv;
    PH18428_SinhVienDAO svDao;
    PH18428_SvAdapter PH18428SvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        svDao = new PH18428_SinhVienDAO(this);
        PH18428SvAdapter = new PH18428_SvAdapter(svDao.getSinhvienAll(),svDao);

        btnAddSV = findViewById(R.id.btnAddSVMain);
        lvsv = findViewById(R.id.lvSV);
        lvsv.setAdapter(PH18428SvAdapter);

        btnAddSV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PH18428SvAdapter.showDiaLogAdd(Student.this);
            }
        });
    }
}
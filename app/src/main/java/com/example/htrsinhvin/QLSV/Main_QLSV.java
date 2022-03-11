package com.example.htrsinhvin.QLSV;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.htrsinhvin.R;

public class Main_QLSV extends AppCompatActivity {

    Button lop,sv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_qlsv);

        lop = findViewById(R.id.btnLop);
        sv = findViewById(R.id.btnSV);

        lop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Main_QLSV.this, Main_Lop.class);
                startActivity(i);
            }
        });

        sv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Main_QLSV.this,Student.class);
                startActivity(i);
            }
        });
    }
}
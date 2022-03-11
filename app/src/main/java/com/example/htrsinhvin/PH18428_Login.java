package com.example.htrsinhvin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.htrsinhvin.DAO.PH18428_TaiKhoanDAO;
import com.example.htrsinhvin.DTO.PH18428_TaiKhoan;

import java.util.ArrayList;

public class PH18428_Login extends AppCompatActivity {
    private Button btReg, btLogin;
    EditText edtTaiKhoan, edtMatKhau;
    CheckBox cbLuuThongTin;

    LinearLayout linearLayout;
    Animation animation;

    PH18428_TaiKhoanDAO tkDao;
    ArrayList<PH18428_TaiKhoan> listTK = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        edtTaiKhoan = findViewById(R.id.edtUserName);
        edtMatKhau = findViewById(R.id.edtPassword);
        btLogin = findViewById(R.id.btnLogin);
        btReg = findViewById(R.id.btnRegister);
        cbLuuThongTin = findViewById(R.id.cbLuuThongTin);

        linearLayout = findViewById(R.id.linearLayoutlogin);

        animation = AnimationUtils.loadAnimation(this, R.anim.login);
        linearLayout.startAnimation(animation);
        layThongTin();

//        ======================================Sang màn đăng kí
        btReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(PH18428_Login.this,Reg.class);
                startActivity(i);
                overridePendingTransition(R.anim.ani_intent, R.anim.ani_intenexit);
            }
        });

//        ========================================Sang màn main
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean xetTk = false;
                tkDao = new PH18428_TaiKhoanDAO(PH18428_Login.this);
                String tenTK = edtTaiKhoan.getText().toString();
                String mk = edtMatKhau.getText().toString();
                listTK = tkDao.getALl();
                for (int i = 0; i < listTK.size(); i++) {
                    PH18428_TaiKhoan tkx = listTK.get(i);
                    if (tkx.getTenTaiKhoan().matches(tenTK) && tkx.getMatKhau().matches(mk)) {
                        xetTk = true;
                        break;
                    }
                }
                if (xetTk == true) {
                    Toast.makeText(PH18428_Login.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                    luuThongTin();
                    Intent i = new Intent(PH18428_Login.this,Main.class);
                    startActivity(i);
                    overridePendingTransition(R.anim.ani_intent, R.anim.ani_intenexit);

                } else {
                    Toast.makeText(PH18428_Login.this, "Tên tài khoản hoặc mật khẩu không chính xác!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void luuThongTin() {
        SharedPreferences sharedPreferences = getSharedPreferences("sinhvien", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String ten = edtTaiKhoan.getText().toString();
        String pass = edtMatKhau.getText().toString();
        boolean check = cbLuuThongTin.isChecked();
        if (!check) {
            editor.clear();
        } else {
            editor.putString("tennguoidung", ten);
            editor.putString("matkhau", pass);
            editor.putBoolean("checkstatus", check);
        }
        editor.commit();

    }

    private void layThongTin() {
        SharedPreferences sharedPreferences = getSharedPreferences("sinhvien", MODE_PRIVATE);

        boolean check = sharedPreferences.getBoolean("checkstatus", false);
        if (check) {
            String tenNguoiDung = sharedPreferences.getString("tennguoidung", "");
            String matKhau = sharedPreferences.getString("matkhau", "");
            edtTaiKhoan.setText(tenNguoiDung);
            edtMatKhau.setText(matKhau);
        } else {
            edtTaiKhoan.setText("");
            edtMatKhau.setText("");
        }
        cbLuuThongTin.setChecked(check);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 999 && resultCode == RESULT_OK) {
            String tk = data.getStringExtra("taikhoan");
            String mk = data.getStringExtra("matkhau");
            edtTaiKhoan.setText(tk);
            edtMatKhau.setText(mk);
        }
    }
}
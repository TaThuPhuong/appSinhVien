package com.example.htrsinhvin.ui.pass;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.htrsinhvin.DAO.PH18428_TaiKhoanDAO;
import com.example.htrsinhvin.DTO.PH18428_TaiKhoan;
import com.example.htrsinhvin.PH18428_Login;
import com.example.htrsinhvin.R;
import com.example.htrsinhvin.databinding.FragmentRepassBinding;

import java.util.ArrayList;


public class PassFragment extends Fragment {
    EditText txtCTk, txtCpass, txtNewPass;
    Button btChangePass, btNhapLai;
    PH18428_TaiKhoanDAO tkDao;
    Animation animation;
    LinearLayout linearLayout;
    ArrayList<PH18428_TaiKhoan> listTk = new ArrayList<>();
    View view;

    public PassFragment() {
    }

    private FragmentRepassBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        PassViewModel slideshowViewModel =
                new ViewModelProvider(this).get(PassViewModel.class);

        binding = FragmentRepassBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        txtCTk = view.findViewById(R.id.edtCUser);
        txtCpass = view.findViewById(R.id.edtCPass);
        txtNewPass = view.findViewById(R.id.edtNewPass);
        btChangePass  =view.findViewById(R.id.btnChange);
        btNhapLai = view.findViewById(R.id.btnRelay);
        linearLayout=view.findViewById(R.id.linearLayoutchange);

        animation = AnimationUtils.loadAnimation(getContext(), R.anim.login);
        linearLayout.setAnimation(animation);
        btChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean xetTk = false, xetMk = true;
                tkDao = new PH18428_TaiKhoanDAO(getContext());
                String tk = txtCTk.getText().toString();
                String mk = txtCpass.getText().toString();
                String mkk = txtNewPass.getText().toString();
                PH18428_TaiKhoan tkmkMoi = new PH18428_TaiKhoan(tk,mkk);
                listTk = tkDao.getALl();

                //Check tk, mk có khớp vs tk trong list k
                for (int i = 0; i < listTk.size(); i++) {
                    PH18428_TaiKhoan tkx = listTk.get(i);
                    if (tkx.getTenTaiKhoan().matches(tk)&&tkx.getMatKhau().matches(mk)) {
                        xetTk = true;
                        break;
                    }
                }

                if(mk.matches(mkk)){
                    xetMk=false;
                }
                else {
                    xetMk=true;
                }

                if (tk.isEmpty()) {
                    Toast.makeText(getContext(), "Tên tài khoản không được để trống!", Toast.LENGTH_SHORT).show();
                } else {
                    if (mk.isEmpty() || mkk.isEmpty()) {
                        Toast.makeText(getContext(), "Mật khẩu không được để trống!", Toast.LENGTH_SHORT).show();
                    } else {
                        if (xetTk == true) {
                            if (xetMk == true) {
                                tkDao.doiMk(tkmkMoi);
                                Toast.makeText(getContext(), "Đổi mật khẩu thành công!", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(view.getContext(), PH18428_Login.class);
                                startActivity(i);
                                Toast.makeText(getContext(), "Mời bạn đăng nhập lại", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getContext(), "Mật khẩu cũ không được trùng với mật khẩu mới!", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getContext(), "Tên tài khoản hoặc mật khẩu không đúng!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

            }
        });

        btNhapLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtCTk.setText("");
                txtCpass.setText("");
                txtNewPass.setText("");
            }
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
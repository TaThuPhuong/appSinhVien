package com.example.htrsinhvin.ADAPTER;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.htrsinhvin.DAO.PH18428_SinhVienDAO;
import com.example.htrsinhvin.DTO.PH18428_Sinhvien;
import com.example.htrsinhvin.QLSV.Student;
import com.example.htrsinhvin.R;

import java.util.List;

public class PH18428_SvAdapter extends BaseAdapter {

    List<PH18428_Sinhvien> PH18428Sinhviens;
    PH18428_SinhVienDAO svDao;
    String spn;
    PH18428_Sinhvien sv;
    Context context;


    public PH18428_SvAdapter(List<PH18428_Sinhvien> PH18428Sinhviens, PH18428_SinhVienDAO svDao) {
        this.PH18428Sinhviens = PH18428Sinhviens;
        this.svDao = svDao;
    }


    @Override
    public int getCount() {
        return PH18428Sinhviens.size();
    }

    @Override
    public Object getItem(int i) {
        PH18428_Sinhvien sv = PH18428Sinhviens.get(i);
        return sv;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View itemView;

        if (convertView == null) {
            itemView = View.inflate(parent.getContext(), R.layout.lv_sv, null);
        } else
            itemView = convertView;

        // lay dong cua list san pham
        final PH18428_Sinhvien s = PH18428Sinhviens.get(position);

        TextView tvTen = itemView.findViewById(R.id.txtTen);
        TextView tvSdt = itemView.findViewById(R.id.txtsdt);
        TextView tvMa = itemView.findViewById(R.id.txtmaSV);
        TextView tvLop = itemView.findViewById(R.id.txttenLop);

        ImageView imgGt = itemView.findViewById(R.id.imageGT);
        ImageView imgCall = itemView.findViewById(R.id.imagecall);
        ImageView imgSms = itemView.findViewById(R.id.imagesms);

        ImageView sua = itemView.findViewById(R.id.imgSua);
        ImageView xoa = itemView.findViewById(R.id.imgXoa);


        //setText
        tvTen.setText(s.getTenSV());
        tvSdt.setText(s.getSdt());
        tvMa.setText(s.getMaSV());
        tvLop.setText(s.getTenLop());

        if (s.getGioitinh().equalsIgnoreCase("Nam")) {
            imgGt.setImageResource(R.drawable.nam);
        } else {
            imgGt.setImageResource(R.drawable.nu);
        }

        xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(parent.getContext());
                builder.setTitle("Xóa Sinh Viên?");
                builder.setIcon(android.R.drawable.ic_delete);
                builder.setMessage("Bạn chắc chắn xóa sinh viên " + s.getMaSV());
                builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int res = svDao.delete(s);

                        if (res > 0) {
                            PH18428Sinhviens.remove(position);

                            notifyDataSetChanged();

                            Toast.makeText(parent.getContext(), "Xóa thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(parent.getContext(), "Xóa lỗi", Toast.LENGTH_SHORT).show();
                        }

                        dialogInterface.dismiss();
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        dialogInterface.dismiss();
                    }
                });
                AlertDialog alertDialog=builder.create();
                alertDialog.show();
            }
        });

        sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogEdit(parent.getContext(),s,position);
            }
        });

        imgCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNo = tvSdt.getText().toString();
                if (!TextUtils.isEmpty(phoneNo)) {
                    String dial = "tel:" + phoneNo;
                    ((Student) context).startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(dial)));

                } else {
                    Toast.makeText(context, "Thực hiện cuộc gọi không thành công", Toast.LENGTH_SHORT).show();
                    Toast.makeText(context, "Vui lòng kiểm tra lại số điện thoại", Toast.LENGTH_SHORT).show();
                }
            }
        });

        imgSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phonesms = tvSdt.getText().toString();
                if (!TextUtils.isEmpty(phonesms)) {
                    String dialsms = "smsto:" + phonesms;
                    ((Student) context).startActivity(new Intent(Intent.ACTION_SENDTO, Uri.parse(dialsms)));
                } else {
                    Toast.makeText(context, "Thực hiện tin nhắn không thành công", Toast.LENGTH_SHORT).show();
                    Toast.makeText(context, "Vui lòng kiểm tra lại số điện thoại", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return itemView;

    }

    //////////////////////////////////=====================dialog
    public void showDiaLogAdd(Context context){
        final Dialog dialog = new Dialog(context, androidx.appcompat.R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.setContentView(R.layout.dialog_add_sv);

        EditText edtmaSV, edttenSV,edtGT,edtsdt;
        Button btnThem, btnXoa;
        Spinner spinner;

        edtmaSV = dialog.findViewById(R.id.AddMa);
        edttenSV = dialog.findViewById(R.id.AddTen);
        edtGT= dialog.findViewById(R.id.AddGt);
        edtsdt= dialog.findViewById(R.id.AddSdt);

        btnThem = dialog.findViewById(R.id.BtnAddSV);
        btnXoa = dialog.findViewById(R.id.btnXoaTrang);
        spinner = dialog.findViewById(R.id.SpinL);
        svDao = new PH18428_SinhVienDAO(dialog.getContext());

        List<String> listLop = svDao.getSinhvientenLop();

        ArrayAdapter arrayAdapter = new ArrayAdapter(context, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, listLop);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                spn = adapterView.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtmaSV.setText("");
                edttenSV.setText("");
                edtGT.setText("");
                edtsdt.setText("");

            }
        });

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PH18428_Sinhvien sv = new PH18428_Sinhvien();
                String maSV = edtmaSV.getText().toString();
                String tenSV = edttenSV.getText().toString();
                String gioitinh = edtGT.getText().toString();
                String tenLop = spn;
                String sdt=edtsdt.getText().toString();

                if(maSV.length() <2 ||tenSV.length() <2 ||sdt.length() != 10 ){
                    Toast.makeText(context, "Các mục không được để trống, số điện thoại có 10 số", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!gioitinh.equalsIgnoreCase("Nam") && !gioitinh.equalsIgnoreCase("Nữ")){
                    Toast.makeText(context, "Giới tính phải là nam hoặc nữ", Toast.LENGTH_SHORT).show();
                    return;
                }

                sv.setMaSV(maSV);
                sv.setTenSV(tenSV);
                sv.setGioitinh(gioitinh);
                sv.setSdt(sdt);
                sv.setTenLop(spn);

                long res = svDao.insert(sv);

                if (res>0){
                    PH18428Sinhviens.clear();
                    PH18428Sinhviens.addAll(svDao.getSinhvienAll());
                    notifyDataSetChanged();
                    Toast.makeText(context, "Thêm mới thành công", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context, "Thêm mới thất bại", Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            }


        });
        dialog.show();
    }

    public void showDialogEdit(Context context, PH18428_Sinhvien sv, int index){
        final Dialog dialog = new Dialog(context, androidx.appcompat.R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.setContentView(R.layout.dialog_update_sv);

        EditText edtmaSV, edttenSV,edtGT,edtsdt;
        Button btnLuu, btnXoa;
        Spinner spinner;

        edtmaSV = dialog.findViewById(R.id.EditMa);
        edttenSV = dialog.findViewById(R.id.EditTen);
        edtGT= dialog.findViewById(R.id.EditGt);
        edtsdt= dialog.findViewById(R.id.EditSdt);

        btnLuu = dialog.findViewById(R.id.BtnUpdateSV);
        btnXoa = dialog.findViewById(R.id.btnXoaTrang);
        spinner = dialog.findViewById(R.id.SpinL);

        edtmaSV.setText(sv.getMaSV());
        edtGT.setText(sv.getGioitinh());
        edttenSV.setText(sv.getTenSV());
        edtsdt.setText(sv.getSdt());

        List<String> listLop = svDao.getSinhvientenLop();

        ArrayAdapter arrayAdapter = new ArrayAdapter(context, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, listLop);
        spinner.setAdapter(arrayAdapter);

        int vitri = -1;
        for (int i = 0; i < listLop.size(); i++) {
            if (listLop.get(i).equalsIgnoreCase(PH18428Sinhviens.get(index).getTenLop())) {
                vitri = i;
                break;
            }
        }
        spinner.setSelection(vitri);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                spn = adapterView.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtmaSV.setText("");
                edtGT.setText("");
                edttenSV.setText("");
                edtsdt.setText("");
                spinner.setSelection(0);
            }
        });

        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String maSV = edtmaSV.getText().toString();
                String tenSV = edttenSV.getText().toString();
                String gioitinh = edtGT.getText().toString();
                String tenLop = spn;
                String sdt=edtsdt.getText().toString();



                if(maSV.length() <2 ||tenSV.length() <2 ||sdt.length() != 10 ){
                    Toast.makeText(context, "Các mục không được để trống, số điện thoại có 10 số", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!gioitinh.equalsIgnoreCase("Nam") && !gioitinh.equalsIgnoreCase("Nữ")){
                    Toast.makeText(context, "Giới tính phải là nam hoặc nữ", Toast.LENGTH_SHORT).show();
                    return;
                }

                sv.setMaSV(maSV);
                sv.setTenSV(tenSV);
                sv.setGioitinh(gioitinh);
                sv.setSdt(sdt);
                sv.setTenLop(spn);

                long res = svDao.update(sv);



                if (res>0){
                    PH18428Sinhviens.clear();
                    PH18428Sinhviens.addAll(svDao.getSinhvienAll());

                    notifyDataSetChanged();
                    Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
                }

                dialog.dismiss();

            }
        });

        dialog.show();
    }
}



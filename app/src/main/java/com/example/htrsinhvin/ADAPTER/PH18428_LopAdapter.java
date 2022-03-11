package com.example.htrsinhvin.ADAPTER;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.htrsinhvin.DAO.PH18428_LopDAO;
import com.example.htrsinhvin.DTO.PH18428_Lop;
import com.example.htrsinhvin.R;

import java.util.List;

public class PH18428_LopAdapter extends BaseAdapter {
    Context context;
    List<PH18428_Lop> PH18428Lops;
    TextView TvmaLop,TvtenLop;
    ImageView imgSua,imgXoa;

    PH18428_LopDAO PH18428LopDAO;

    public PH18428_LopAdapter(List<PH18428_Lop> PH18428Lops, PH18428_LopDAO PH18428LopDAO) {
        this.PH18428Lops = PH18428Lops;
        this.PH18428LopDAO = PH18428LopDAO;
    }

    @Override
    public int getCount() {
        return PH18428Lops.size();
    }

    @Override
    public Object getItem(int i) {
        PH18428_Lop PH18428Lop = PH18428Lops.get(i);
        return PH18428Lop;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View itemview;
        if(convertView == null){
            itemview = View.inflate(parent.getContext(), R.layout.lv_lop,null);
        }else {
            itemview = convertView;
        }

        // lay dong cua list san pham
        final PH18428_Lop PH18428Lop = PH18428Lops.get(position);

        if (PH18428Lop != null){
            //anh xa
            TvmaLop = (TextView)itemview.findViewById(R.id.txtmaLop);
            TvtenLop = (TextView)itemview.findViewById(R.id.txttenLop);

            imgSua = itemview.findViewById(R.id.imgSua);
            imgXoa = itemview.findViewById(R.id.imgXoa);

            // set data len cac view

            TvmaLop.setText(PH18428Lop.getMalop());
            TvtenLop.setText(PH18428Lop.getTenLop());


        }

        imgXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(parent.getContext());
                builder.setTitle("Xóa lớp?");
                builder.setIcon(android.R.drawable.ic_delete);
                builder.setMessage("Bạn chắc chắn xóa lớp "+ PH18428Lop.getTenLop());

                builder.setPositiveButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        dialogInterface.dismiss();
                    }
                });
                builder.setNegativeButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int res = PH18428LopDAO.delete(PH18428Lop);
                        if(res >0){
                            PH18428Lops.remove(position);

                            notifyDataSetChanged();

                            Toast.makeText(parent.getContext(), "Bạn vừa xóa lớp "+ PH18428Lop.getMalop(), Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(parent.getContext(), "Xóa lỗi", Toast.LENGTH_SHORT).show();
                        }

                        dialog.dismiss();
                    }
                });
                AlertDialog alertDialog=builder.create();
                alertDialog.show();
            }
        });

        imgSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogEdit(parent.getContext(), PH18428Lop,position);
            }
        });
        return itemview;
    }






    //=====================Dialog==================================================
    public void showDialogAdd(Context context){
        final Dialog dialog = new Dialog(context, androidx.appcompat.R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.setContentView(R.layout.dialog_add_lop);

        EditText edID = dialog.findViewById(R.id.AddId);
        EditText edTen = dialog.findViewById(R.id.AddTen);
        Button btnAdd = dialog.findViewById(R.id.btnAddClass);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PH18428_Lop PH18428Lop = new PH18428_Lop();
                String id = edID.getText().toString();
                String ten = edTen.getText().toString();
                if(id.length()<2 || ten.length() < 2){
                    Toast.makeText(context, "ID lớp và tên lớp không được để trống", Toast.LENGTH_SHORT).show();
                    return;
                }
                PH18428Lop.setMalop(edID.getText().toString());
                PH18428Lop.setTenLop(edTen.getText().toString());
                long res = PH18428LopDAO.insert(PH18428Lop);
                if(res > 0){
                    PH18428Lops.clear();
                    //load lại từ CSDL
                    PH18428Lops.addAll(PH18428LopDAO.getLopAll());
                    //báo cho Adapter
                    notifyDataSetChanged();
                    Toast.makeText(context, "Thêm mới thành công", Toast.LENGTH_SHORT).show();
                    //tắt hiển thị dialag
                    dialog.dismiss();
                }else {
                    Toast.makeText(context, "Thêm mới thất bại", Toast.LENGTH_SHORT).show();
                } }});

        dialog.show();
    }

    public void showDialogEdit(Context context, PH18428_Lop PH18428Lop, int index){
        final Dialog dialog = new Dialog(context, androidx.appcompat.R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.setContentView(R.layout.dialog_update_lop);

        TextView tvID = dialog.findViewById(R.id.tvID);
        EditText edTen = dialog.findViewById(R.id.EditTen);

        Button btnEditClass = dialog.findViewById(R.id.btnEditClass);
        //gán dữ liệu
        tvID.setText("ID lớp: "+ PH18428Lop.getMalop());
        edTen.setText(PH18428Lop.getTenLop());
        //nút bấm
        btnEditClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ten = edTen.getText().toString();
                if(ten.isEmpty()){
                    Toast.makeText(context, "Không được để trống tên", Toast.LENGTH_SHORT).show();
                    return; }
                PH18428Lop.setTenLop(edTen.getText().toString());
                int res = PH18428LopDAO.update(PH18428Lop);
                if(res > 0){
                    PH18428Lops.set(index, PH18428Lop);
                    notifyDataSetChanged();
                    Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();//ẩn
                }else {
                    Toast.makeText(context, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
        dialog.show();
    }

}

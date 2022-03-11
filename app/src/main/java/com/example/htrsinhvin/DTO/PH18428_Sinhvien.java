package com.example.htrsinhvin.DTO;

public class PH18428_Sinhvien {
    private String maSV, tenSV, gioitinh, tenLop,sdt;

    public static final String TB_NAMESV = "Sinhvien";
    public static final String COL_MaSV = "maSV";
    public static final String COL_LOP = "tenLop";
    public static final String COL_TENSV = "tenSV";
    public static final String COL_GT = "gioitinh" ;
    public static final String COL_SDT = "sdt";

    public PH18428_Sinhvien() {
    }

    public PH18428_Sinhvien(String maSV, String tenSV, String gioitinh, String tenLop, String sdt) {
        this.maSV = maSV;
        this.tenSV = tenSV;
        this.gioitinh = gioitinh;
        this.tenLop = tenLop;
        this.sdt = sdt;
    }

    public String getMaSV() {
        return maSV;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

    public String getTenSV() {
        return tenSV;
    }

    public void setTenSV(String tenSV) {
        this.tenSV = tenSV;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public String getTenLop() {
        return tenLop;
    }

    public void setTenLop(String tenLop) {
        this.tenLop = tenLop;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }
}

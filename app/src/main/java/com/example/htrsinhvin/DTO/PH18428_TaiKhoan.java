package com.example.htrsinhvin.DTO;

public class PH18428_TaiKhoan {
    private String tenTaiKhoan, matKhau;

    public static final String TB_NAME = "taiKhoan";
    public static final String COL_ID = "tenTaiKhoan";
    public static final String COL_PASS = "matKhau";

    public PH18428_TaiKhoan() {
    }

    public PH18428_TaiKhoan(String tenTaiKhoan, String matKhau) {
        this.tenTaiKhoan = tenTaiKhoan;
        this.matKhau = matKhau;
    }

    public String getTenTaiKhoan() {
        return tenTaiKhoan;
    }

    public void setTenTaiKhoan(String tenTaiKhoan) {
        this.tenTaiKhoan = tenTaiKhoan;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }
}

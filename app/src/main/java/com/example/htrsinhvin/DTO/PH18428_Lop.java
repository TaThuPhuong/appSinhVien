package com.example.htrsinhvin.DTO;

public class PH18428_Lop {
    private String malop,tenLop;

    public static final String TB_NAME = "Lop";
    public static final String COL_ID = "maLop";
    public static final String COL_TEN = "tenLop";

    public PH18428_Lop() {
    }

    public PH18428_Lop(String malop, String tenLop) {
        this.malop = malop;
        this.tenLop = tenLop;
    }

    public String getMalop() {
        return malop;
    }

    public void setMalop(String malop) {
        this.malop = malop;
    }

    public String getTenLop() {
        return tenLop;
    }

    public void setTenLop(String tenLop) {
        this.tenLop = tenLop;
    }
}

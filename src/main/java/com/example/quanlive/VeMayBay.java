package com.example.quanlive;



public class VeMayBay {
    private String tenNguoiMua;
    private int soCMT;
    private String noiDi;
    private String noiDen;
    private double quangDuong;
    private String ngayGioBay;
    private String ngayBanVe;
    private String loaiVe;
    private String giaVe = "0";

    public VeMayBay(String tenNguoiMua, int soCMT, String noiDi, String noiDen, double quangDuong, String ngayGioBay, String ngayBanVe, String loaiVe) {
        this.tenNguoiMua = tenNguoiMua;
        this.soCMT = soCMT;
        this.noiDi = noiDi;
        this.noiDen = noiDen;
        this.quangDuong = quangDuong;
        this.ngayGioBay = ngayGioBay;
        this.ngayBanVe = ngayBanVe;
        this.loaiVe = loaiVe;
        tinhTien(loaiVe);
    }


    public VeMayBay() {
    }
    public String toString() {
        return tenNguoiMua + "," + soCMT + "," + noiDi + "," + noiDen + "," + quangDuong + "," + ngayGioBay + "," + ngayBanVe + "," + loaiVe;
    }

    public String getTenNguoiMua() {
        return tenNguoiMua;
    }

    public void setTenNguoiMua(String tenNguoiMua) {
        this.tenNguoiMua = tenNguoiMua;
    }

    public int getSoCMT() {
        return soCMT;
    }

    public void setSoCMT(int soCMT) {
        this.soCMT = soCMT;
    }

    public String getNoiDi() {
        return noiDi;
    }

    public void setNoiDi(String noiDi) {
        this.noiDi = noiDi;
    }

    public String getNoiDen() {
        return noiDen;
    }

    public void setNoiDen(String noiDen) {
        this.noiDen = noiDen;
    }

    public double getQuangDuong() {
        return quangDuong;
    }

    public void setQuangDuong(double quangDuong) {
        this.quangDuong = quangDuong;
    }

    public String getNgayGioBay() {
        return ngayGioBay;
    }

    public void setNgayGioBay(String ngayGioBay) {
        this.ngayGioBay = ngayGioBay;
    }

    public String getNgayBanVe() {
        return ngayBanVe;
    }

    public void setNgayBanVe(String ngayBanVe) {
        this.ngayBanVe = ngayBanVe;
    }

    public String getLoaiVe() {
        return loaiVe;
    }

    public void setLoaiVe(String loaiVe) {this.loaiVe = loaiVe;}

    public String getGiaVe() {return giaVe;}

    public void setGiaVe(String giaVe) {this.giaVe = giaVe;}

    //method này nhận vào loại vé theo kiểu string và sẽ chia trường hợp và tính tiền cho từng loại vé,sau đó sẽ set giá tiền cho attribute
    //giaVe
    public void tinhTien(String select){
        Double temp = Double.parseDouble(giaVe);
        if(select.equals("Phổ thông")) temp = quangDuong*20000.0;
        if(select.equals("Thương gia")) temp =quangDuong*30000.0+1000000.0;
        giaVe = String.format("%.1f", temp);
    }
}

//Nguyen Cong Quyen
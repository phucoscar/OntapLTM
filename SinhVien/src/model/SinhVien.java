package model;

import java.io.Serializable;

public class SinhVien implements Serializable {
    private static final long serialVersionUID = 14L;

    private String maSV;
    private String hoTen;
    private int port;
    private String url;

    public SinhVien() {

    }

    public SinhVien(String maSV, String hoTen, int port, String url) {
        this.maSV = maSV;
        this.hoTen = hoTen;
        this.port = port;
        this.url = url;
    }

    public String getMaSV() {
        return maSV;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

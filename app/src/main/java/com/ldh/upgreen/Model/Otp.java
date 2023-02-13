package com.ldh.upgreen.Model;

import java.util.Date;

public class Otp {
    private String _id="";
    private String gmail ="";
    private String otp = "";
    private Date createdAt = new Date();
    public Otp(){};

    public Otp(String _id, String gmail, String otp, Date createdAt) {
        this._id = _id;
        this.gmail = gmail;
        this.otp = otp;
        this.createdAt = createdAt;
    }
  public Otp(String gmail,String otp){
        this.gmail = gmail;
        this.otp = otp;
  }
    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}

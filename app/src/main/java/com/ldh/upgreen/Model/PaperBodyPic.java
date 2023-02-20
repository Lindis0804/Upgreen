package com.ldh.upgreen.Model;

public class PaperBodyPic {
    private String link,des;

    public PaperBodyPic(String link, String des) {
        this.link = link;
        this.des = des;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}

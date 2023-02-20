package com.ldh.upgreen.Model;

import java.io.Serializable;

public class PaperBody implements Serializable {
    private String h1,h2,p,label,intro;
    private PaperBodyPic pic;

    public PaperBody(String h1, String h2, String p, String label,String intro, PaperBodyPic pic) {
        this.h1 = h1;
        this.h2 = h2;
        this.p = p;
        this.label = label;
        this.pic = pic;
        this.intro = intro;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getH1() {
        return h1;
    }

    public void setH1(String h1) {
        this.h1 = h1;
    }

    public String getH2() {
        return h2;
    }

    public void setH2(String h2) {
        this.h2 = h2;
    }

    public String getP() {
        return p;
    }

    public void setP(String p) {
        this.p = p;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public PaperBodyPic getPic() {
        return pic;
    }

    public void setPic(PaperBodyPic pic) {
        this.pic = pic;
    }
}

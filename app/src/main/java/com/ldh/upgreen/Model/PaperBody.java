package com.ldh.upgreen.Model;

public class PaperBody {
    private String content = "";
    private String pic = "";
    private int type = 0;

    public PaperBody() {
    }

    ;

    public PaperBody(String content, String pic, int type) {
        this.content = content;
        this.pic = pic;
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}

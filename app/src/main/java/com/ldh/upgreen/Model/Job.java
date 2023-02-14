package com.ldh.upgreen.Model;

import java.io.Serializable;

public class Job implements Serializable {
    private String _id = "";
    private String title = "";

    public Job() {
    }

    ;
    public Job(String title){
        this.title = title;
    }
    public Job(String _id, String title) {
        this._id = _id;
        this.title = title;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

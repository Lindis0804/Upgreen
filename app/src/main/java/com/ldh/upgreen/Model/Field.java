package com.ldh.upgreen.Model;

import java.io.Serializable;

public class Field implements Serializable {
    private String _id = "";
    private String title = "";
    private int numOfCare = 0;
    private int numOfProject = 0;

    public Field() {
    }

    ;
    public Field(String title){
        this.title = title;
    }
    public Field(String _id, String title, int numOfCare, int numOfProject) {
        this._id = _id;
        this.title = title;
        this.numOfCare = numOfCare;
        this.numOfProject = numOfProject;
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

    public int getNumOfCare() {
        return numOfCare;
    }

    public void setNumOfCare(int numOfCare) {
        this.numOfCare = numOfCare;
    }

    public int getNumOfProject() {
        return numOfProject;
    }

    public void setNumOfProject(int numOfProject) {
        this.numOfProject = numOfProject;
    }
}

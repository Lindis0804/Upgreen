package com.ldh.upgreen.Model;

import java.io.Serializable;

public class UserJob implements Serializable {
    private String _id="";
    private Job job = new Job();
    private Place workplace = new Place();
    private String description = "";
    public UserJob(){};

    public UserJob(Job job, Place workplace, String description) {
        this.job = job;
        this.workplace = workplace;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserJob(String _id, Job job, Place workplace, String description) {
        this._id = _id;
        this.job = job;
        this.workplace = workplace;
        this.description = description;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Place getWorkplace() {
        return workplace;
    }

    public void setWorkplace(Place workplace) {
        this.workplace = workplace;
    }
}

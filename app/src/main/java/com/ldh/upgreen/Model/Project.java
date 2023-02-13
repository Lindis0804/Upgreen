package com.ldh.upgreen.Model;

import java.util.ArrayList;

public class Project {
    private String _id="";
    private String title="";
    private String pic="";
    private ArrayList<User> likers = new ArrayList<>();
    private ArrayList<Comment> comments = new ArrayList<>();
    private ArrayList<User> viewers = new ArrayList<>();
    private ArrayList<User> sharers = new ArrayList<>();
    private ArrayList<Field> fields = new ArrayList<>();
    private double validationScore = 0.0;
    private String introduction;
    private ArrayList<User> members = new ArrayList<>();
    private ArrayList<User> investors = new ArrayList<>();
    private int numOfRasingFund = 0;
    private int numOfSuccess = 0;
    private String detailPlan = "";
    public Project(){};

    public Project(String _id, String title, String pic, ArrayList<User> likers, ArrayList<Comment> comments, ArrayList<User> viewers, ArrayList<User> sharers, ArrayList<Field> fields, double validationScore, String introduction, ArrayList<User> members, ArrayList<User> investors, int numOfRasingFund, int numOfSuccess, String detailPlan) {
        this._id = _id;
        this.title = title;
        this.pic = pic;
        this.likers = likers;
        this.comments = comments;
        this.viewers = viewers;
        this.sharers = sharers;
        this.fields = fields;
        this.validationScore = validationScore;
        this.introduction = introduction;
        this.members = members;
        this.investors = investors;
        this.numOfRasingFund = numOfRasingFund;
        this.numOfSuccess = numOfSuccess;
        this.detailPlan = detailPlan;
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

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public ArrayList<User> getLikers() {
        return likers;
    }

    public void setLikers(ArrayList<User> likers) {
        this.likers = likers;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public ArrayList<User> getViewers() {
        return viewers;
    }

    public void setViewers(ArrayList<User> viewers) {
        this.viewers = viewers;
    }

    public ArrayList<User> getSharers() {
        return sharers;
    }

    public void setSharers(ArrayList<User> sharers) {
        this.sharers = sharers;
    }

    public ArrayList<Field> getFields() {
        return fields;
    }

    public void setFields(ArrayList<Field> fields) {
        this.fields = fields;
    }

    public double getValidationScore() {
        return validationScore;
    }

    public void setValidationScore(double validationScore) {
        this.validationScore = validationScore;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public ArrayList<User> getMembers() {
        return members;
    }

    public void setMembers(ArrayList<User> members) {
        this.members = members;
    }

    public ArrayList<User> getInvestors() {
        return investors;
    }

    public void setInvestors(ArrayList<User> investors) {
        this.investors = investors;
    }

    public int getNumOfRasingFund() {
        return numOfRasingFund;
    }

    public void setNumOfRasingFund(int numOfRasingFund) {
        this.numOfRasingFund = numOfRasingFund;
    }

    public int getNumOfSuccess() {
        return numOfSuccess;
    }

    public void setNumOfSuccess(int numOfSuccess) {
        this.numOfSuccess = numOfSuccess;
    }

    public String getDetailPlan() {
        return detailPlan;
    }

    public void setDetailPlan(String detailPlan) {
        this.detailPlan = detailPlan;
    }
}

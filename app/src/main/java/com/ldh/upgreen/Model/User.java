package com.ldh.upgreen.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    private String _id = "";
    private String name = "";
    private String username = "";
    private String password = "";
    private String avatar = "";
    private ArrayList<UserJob> jobs = new ArrayList<>();
    private String birthday = "";
    private int role = 0;
    private String phone = "";
    private String gmail = "";
    private String position = "";
    private ArrayList<Field> caredFields = new ArrayList<>();
    private ArrayList<Project> projects = new ArrayList<>();
    private String linkedInLink = "";
    private ArrayList<Spe> spes = new ArrayList<>();
    private ArrayList<User> followers = new ArrayList<>();
    private ArrayList<User> likers = new ArrayList<>();
    private String takingPartInSomeProjects = "";

    public User() {
    }

    ;

    public User(String _id, String name, String username, String password, String avatar, ArrayList<UserJob> jobs, String birthday, int role, String phone, String gmail, String position, ArrayList<Field> caredFields, ArrayList<Project> projects, String linkedInLink, ArrayList<Spe> spes, ArrayList<User> followers, ArrayList<User> likers, String takingPartInSomeProjects) {
        this._id = _id;
        this.username = username;
        this.password = password;
        this.avatar = avatar;
        this.jobs = jobs;
        this.birthday = birthday;
        this.role = role;
        this.phone = phone;
        this.gmail = gmail;
        this.position = position;
        this.caredFields = caredFields;
        this.projects = projects;
        this.linkedInLink = linkedInLink;
        this.spes = spes;
        this.followers = followers;
        this.likers = likers;
        this.takingPartInSomeProjects = takingPartInSomeProjects;
        this.name = name;
    }

    public User(String name, String username, String password, String phone, String gmail) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.gmail = gmail;
        this.role = role;
    }

    public User(int role, ArrayList<UserJob> jobs, String birthday, ArrayList<Field> caredFields, String linkedInLink, String takingPartInSomeProjects) {
        this.jobs = jobs;
        this.birthday = birthday;
        this.caredFields = caredFields;
        this.linkedInLink = linkedInLink;
        this.takingPartInSomeProjects = takingPartInSomeProjects;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public ArrayList<UserJob> getJobs() {
        return jobs;
    }

    public void setJobs(ArrayList<UserJob> jobs) {
        this.jobs = jobs;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public ArrayList<Field> getCaredFields() {
        return caredFields;
    }

    public void setCaredFields(ArrayList<Field> caredFields) {
        this.caredFields = caredFields;
    }

    public ArrayList<Project> getProjects() {
        return projects;
    }

    public void setProjects(ArrayList<Project> projects) {
        this.projects = projects;
    }

    public String getLinkedInLink() {
        return linkedInLink;
    }

    public void setLinkedInLink(String linkedInLink) {
        this.linkedInLink = linkedInLink;
    }

    public ArrayList<Spe> getSpes() {
        return spes;
    }

    public void setSpes(ArrayList<Spe> spes) {
        this.spes = spes;
    }

    public ArrayList<User> getFollowers() {
        return followers;
    }

    public void setFollowers(ArrayList<User> followers) {
        this.followers = followers;
    }

    public ArrayList<User> getLikers() {
        return likers;
    }

    public void setLikers(ArrayList<User> likers) {
        this.likers = likers;
    }

    public String getTakingPartInSomeProjects() {
        return takingPartInSomeProjects;
    }

    public void setTakingPartInSomeProjects(String takingPartInSomeProjects) {
        this.takingPartInSomeProjects = takingPartInSomeProjects;
    }
}

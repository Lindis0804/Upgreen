package com.ldh.upgreen.Model.Response;

import com.ldh.upgreen.Model.Comment;
import com.ldh.upgreen.Model.Field;
import com.ldh.upgreen.Model.Job;
import com.ldh.upgreen.Model.Otp;
import com.ldh.upgreen.Model.Paper;
import com.ldh.upgreen.Model.Place;
import com.ldh.upgreen.Model.Project;
import com.ldh.upgreen.Model.Spe;
import com.ldh.upgreen.Model.User;
import com.ldh.upgreen.Model.UserJob;

import java.util.ArrayList;

public class Data {
    //private Comment comment = new Comment();
    private Field field = new Field();
    private Job job = new Job();
    private Otp otp = new Otp();
    private Paper paper = new Paper();
    private Place place = new Place();
    private Project project = new Project();
    private Spe spe = new Spe();
    private User user = new User();
    private UserJob userJob = new UserJob();
    private ArrayList<Comment> comments = new ArrayList<>();
    private ArrayList<Field> fields = new ArrayList<>();
    private ArrayList<Job> jobs = new ArrayList<>();
    private ArrayList<Paper> papers = new ArrayList<>();
    private ArrayList<Place> places = new ArrayList<>();
    private ArrayList<Project> projects = new ArrayList<>();
    private ArrayList<Spe> spes = new ArrayList<>();
    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<UserJob> userJobs = new ArrayList<>();
    private String token = "";
    public Data() {
    }

    ;

    public Data( Field field, Job job, Otp otp, Paper paper, Place place, Project project, Spe spe, User user, UserJob userJob, ArrayList<Comment> comments, ArrayList<Field> fields, ArrayList<Job> jobs, ArrayList<Paper> papers, ArrayList<Place> places, ArrayList<Project> projects, ArrayList<Spe> spes, ArrayList<User> users, ArrayList<UserJob> userJobs,String token) {
        this.field = field;
        this.job = job;
        this.otp = otp;
        this.paper = paper;
        this.place = place;
        this.project = project;
        this.spe = spe;
        this.user = user;
        this.userJob = userJob;
        this.comments = comments;
        this.fields = fields;
        this.jobs = jobs;
        this.papers = papers;
        this.places = places;
        this.projects = projects;
        this.spes = spes;
        this.users = users;
        this.userJobs = userJobs;
        this.token = token;
    }


//    public Comment getComment() {
//        return comment;
//    }
//
//    public void setComment(Comment comment) {
//        this.comment = comment;
//    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Otp getOtp() {
        return otp;
    }

    public void setOtp(Otp otp) {
        this.otp = otp;
    }

    public Paper getPaper() {
        return paper;
    }

    public void setPaper(Paper paper) {
        this.paper = paper;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Spe getSpe() {
        return spe;
    }

    public void setSpe(Spe spe) {
        this.spe = spe;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserJob getUserJob() {
        return userJob;
    }

    public void setUserJob(UserJob userJob) {
        this.userJob = userJob;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public ArrayList<Field> getFields() {
        return fields;
    }

    public void setFields(ArrayList<Field> fields) {
        this.fields = fields;
    }

    public ArrayList<Job> getJobs() {
        return jobs;
    }

    public void setJobs(ArrayList<Job> jobs) {
        this.jobs = jobs;
    }

    public ArrayList<Paper> getPapers() {
        return papers;
    }

    public void setPapers(ArrayList<Paper> papers) {
        this.papers = papers;
    }

    public ArrayList<Place> getPlaces() {
        return places;
    }

    public void setPlaces(ArrayList<Place> places) {
        this.places = places;
    }

    public ArrayList<Project> getProjects() {
        return projects;
    }

    public void setProjects(ArrayList<Project> projects) {
        this.projects = projects;
    }

    public ArrayList<Spe> getSpes() {
        return spes;
    }

    public void setSpes(ArrayList<Spe> spes) {
        this.spes = spes;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public ArrayList<UserJob> getUserJobs() {
        return userJobs;
    }

    public void setUserJobs(ArrayList<UserJob> userJobs) {
        this.userJobs = userJobs;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

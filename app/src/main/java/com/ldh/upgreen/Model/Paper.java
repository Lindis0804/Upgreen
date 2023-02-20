package com.ldh.upgreen.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Paper implements Serializable {
    private String _id = "";
    private User author = new User();
    private String title = "";
    private ArrayList<PaperBody> body = new ArrayList<>();
    private ArrayList<User> likers = new ArrayList<>();
    private ArrayList<User> sharers = new ArrayList<>();
    private ArrayList<Comment> comments = new ArrayList<>();
    private String pic ;
    private int numOfLiker=0,numOfSharer=0,numOfComment=0,numOfViewer=0;
    private String createdAt = "";
    public Paper(){};

    public Paper(String _id, User author, String title, ArrayList<PaperBody> body, ArrayList<User> likers, ArrayList<User> sharers, ArrayList<Comment> comments, String pic) {
        this._id = _id;
        this.author = author;
        this.title = title;
        this.body = body;
        this.likers = likers;
        this.sharers = sharers;
        this.comments = comments;
        this.pic = pic;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Paper(String _id, User author, String title, ArrayList<PaperBody> body, ArrayList<User> likers, ArrayList<User> sharers, ArrayList<Comment> comments) {
        this._id = _id;
        this.author = author;
        this.title = title;
        this.body = body;
        this.likers = likers;
        this.sharers = sharers;
        this.comments = comments;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<PaperBody> getBody() {
        return body;
    }

    public void setBody(ArrayList<PaperBody> body) {
        this.body = body;
    }

    public ArrayList<User> getLikers() {
        return likers;
    }

    public void setLikers(ArrayList<User> likers) {
        this.likers = likers;
    }

    public ArrayList<User> getSharers() {
        return sharers;
    }

    public void setSharers(ArrayList<User> sharers) {
        this.sharers = sharers;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public int getNumOfLiker() {
        return numOfLiker;
    }

    public void setNumOfLiker(int numOfLiker) {
        this.numOfLiker = numOfLiker;
    }

    public int getNumOfSharer() {
        return numOfSharer;
    }

    public void setNumOfSharer(int numOfSharer) {
        this.numOfSharer = numOfSharer;
    }

    public int getNumOfComment() {
        return numOfComment;
    }

    public void setNumOfComment(int numOfComment) {
        this.numOfComment = numOfComment;
    }

    public int getNumOfViewer() {
        return numOfViewer;
    }

    public void setNumOfViewer(int numOfViewer) {
        this.numOfViewer = numOfViewer;
    }
}

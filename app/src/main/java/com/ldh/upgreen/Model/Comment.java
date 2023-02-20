package com.ldh.upgreen.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Comment implements Serializable {
    private String _id ;
    private User author ;
    private Comment replied ;
    private ArrayList<Comment> replies ;
    private ArrayList<User> likers ;
    private String content;
    public Comment() {
        this._id = "";
        this.author = new User();
        this.replied = new Comment();
        this.replies = new ArrayList<>();
        this.likers = new ArrayList<>();
        this.content = "";
    };
    public Comment(String _id, User author, Comment replied, ArrayList<Comment> replies, ArrayList<User> likers,String content) {
        this._id = _id;
        this.author = author;
        this.replied = replied;
        this.replies = replies;
        this.likers = likers;
        this.content = content;
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

    public Comment getReplied() {
        return replied;
    }

    public void setReplied(Comment replied) {
        this.replied = replied;
    }

    public ArrayList<Comment> getReplies() {
        return replies;
    }

    public void setReplies(ArrayList<Comment> replies) {
        this.replies = replies;
    }

    public ArrayList<User> getLikers() {
        return likers;
    }

    public void setLikers(ArrayList<User> likers) {
        this.likers = likers;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

package com.ldh.upgreen.Model;

import java.util.ArrayList;

public class Comment {
    private String _id ;
    private User author ;
    private Comment replied ;
    private ArrayList<Comment> replies ;
    private ArrayList<User> likers ;
    public Comment() {
        this._id = "";
        this.author = new User();
        this.replied = new Comment();
        this.replies = new ArrayList<>();
        this.likers = new ArrayList<>();
    };
    public Comment(String _id, User author, Comment replied, ArrayList<Comment> replies, ArrayList<User> likers) {
        this._id = _id;
        this.author = author;
        this.replied = replied;
        this.replies = replies;
        this.likers = likers;
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
}

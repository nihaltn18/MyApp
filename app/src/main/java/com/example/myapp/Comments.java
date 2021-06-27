package com.example.myapp;

public class Comments {
    String from;
    String commentContent;

    public Comments(String from, String commentContent) {
        this.from = from;
        this.commentContent = commentContent;
    }

    public Comments() {
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }
}

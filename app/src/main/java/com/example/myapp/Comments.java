package com.example.myapp;

public class Comments {
    boolean Anonymous;
    String from;
    String To;
    String FromId;
    String ToId;
    String commentContent;

    public Comments(boolean anonymous, String from, String to, String fromId, String toId, String commentContent) {
        Anonymous = anonymous;
        this.from = from;
        To = to;
        FromId = fromId;
        ToId = toId;
        this.commentContent = commentContent;
    }

    public Comments() {
    }

    @Override
    public String toString() {
        return "Comments{" +
                "Anonymous=" + Anonymous +
                ", from='" + from + '\'' +
                ", To='" + To + '\'' +
                ", FromId='" + FromId + '\'' +
                ", ToId='" + ToId + '\'' +
                ", commentContent='" + commentContent + '\'' +
                '}';
    }

    public boolean isAnonymous() {
        return Anonymous;
    }

    public void setAnonymous(boolean anonymous) {
        Anonymous = anonymous;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return To;
    }

    public void setTo(String to) {
        To = to;
    }

    public String getFromId() {
        return FromId;
    }

    public void setFromId(String fromId) {
        FromId = fromId;
    }

    public String getToId() {
        return ToId;
    }

    public void setToId(String toId) {
        ToId = toId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }
}

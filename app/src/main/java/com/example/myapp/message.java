package com.example.myapp;

import java.util.ArrayList;

public class message {
    String message;
    String from_id;
    String from_name;
    String to_id;
    boolean Private;
    boolean anonymous;
    String to_name;
    String message_id;

    public String getMessage_id() {
        return message_id;
    }

    public void setMessage_id(String message_id) {
        this.message_id = message_id;
    }

    ArrayList<String> likedList;
    ArrayList<Comments> commentList;
    ArrayList<String> unlikedList;

    public String getFrom_name() {
        return from_name;
    }

    public void setFrom_name(String from_name) {
        this.from_name = from_name;
    }

    public ArrayList<String> getLikedList() {
        return likedList;
    }

    public void setLikedList(ArrayList<String> likedList) {
        this.likedList = likedList;
    }

    public ArrayList<Comments> getCommentList() {
        return commentList;
    }

    public void setCommentList(ArrayList<Comments> commentList) {
        this.commentList = commentList;
    }

    public ArrayList<String> getUnlikedList() {
        return unlikedList;
    }

    public void setUnlikedList(ArrayList<String> unlikedList) {
        this.unlikedList = unlikedList;
    }

    public message(String message) {
        this.message = message;
    }

    public message(String message, String from_id, String to_id, boolean aPrivate, boolean anonymous, String from_name, String to_name) {
        this.message = message;
        this.to_name = to_name;
        this.from_name=from_name;
        this.from_id = from_id;
        this.to_id = to_id;
        Private = aPrivate;
        String messageId;
        this.anonymous = anonymous;
        likedList = new ArrayList<>();
        likedList.add("one");
        commentList = new ArrayList<>();
        unlikedList = new ArrayList<>();
        unlikedList.add("one");
    }

    public String getTo_name() {
        return to_name;
    }

    public void setTo_name(String to_name) {
        this.to_name = to_name;
    }

    public message() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFrom_id() {
        return from_id;
    }

    public void setFrom_id(String from_id) {
        this.from_id = from_id;
    }

    public String getTo_id() {
        return to_id;
    }

    public void setTo_id(String to_id) {
        this.to_id = to_id;
    }

    public boolean isPrivate() {
        return Private;
    }

    public void setPrivate(boolean aPrivate) {
        Private = aPrivate;
    }

    public boolean isAnonymous() {
        return anonymous;
    }

    public void setAnonymous(boolean anonymous) {
        this.anonymous = anonymous;
    }
    public void Add_to_likedList(String id)
    {
        likedList.add(id);
    }
    public void Add_to_unlikedList(String id)
    {
        unlikedList.add(id);
    }
    public void remove_from_likedList(String id)
    {
        likedList.remove(new String(id));
    }
    public void remove_from_unlikedList(String id)
    {
        unlikedList.remove(new String(id));
    }
    public boolean inLikedList(String id)
    {
        if(likedList.contains(id))
            return true;
        return false;
    }
    public boolean inUnlikedList(String id)
    {
        if(unlikedList.contains(id))
            return true;
        return false;
    }
    public int numberOfLikes()
    {
        return likedList.size();
    }
    public int numberOfUnlikes()
    {
        return unlikedList.size();
    }
    public void addComment(boolean anonymous, String from, String to, String fromId, String toId, String commentContent)
    {
        Comments comments = new Comments(anonymous,from,to,fromId,toId,commentContent);
        commentList.add(comments);
    }
    public int numberOfComments()
    {
        return commentList.size();
    }
}

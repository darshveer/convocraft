package com.convocraft.chatroomManager;

import java.io.Serializable;

import com.convocraft.chatroom.Chatroom;

public class User implements Serializable {
    String username;
    transient Chatroom chatroom;
    private String chatroomName;

    public User(String username, Chatroom chatroom) {
        this.username = username;
        this.chatroom = chatroom;
        this.chatroomName = chatroom.getName();
    }

    public String receiveMessage(){
        return chatroom.receiveMessage();
    }
    public void sendMessage(String message){
        chatroom.sendMessage(message);
    }

    public String getChatroomName() {
        return chatroomName;
    }

    public String getUserName() {
        return username;
    }
}


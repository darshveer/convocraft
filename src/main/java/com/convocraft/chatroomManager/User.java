package com.convocraft.chatroomManager;

public class User {
    String username;
    Chatroom chatroom;
    Thread consumThread;
    Thread producerThread;
}

public User(String username, Chatroom chatroom) {
    this.username = username;
    this.chatroom = chatroom;
}

create

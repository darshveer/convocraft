package com.convocraft.chatroomManager;

import com.convocraft.chatroom.Chatroom;

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

public String receiveMessage(){
    return chatroom.receiveMessage();
}
public void sendMessage(String meessage){
    chatroom.sendMessage(meessage);
}
}


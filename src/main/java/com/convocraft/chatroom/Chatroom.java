package com.convocraft.chatroom;

import java.util.HashMap;
import com.convocraft.chatroomManager.ActiveMQConnectionManager;
public class Chatroom {

    ActiveMQConnection topic ;
    HashMap<String,String> userIpMap ;
    CommandProcessor cmdProcessor;
    
    public Chatroom(String chatroomName, String adminName, String adminIp){

        this.userIpMap = new HashMap<>();
        this.cmdProcessor = new CommandProcessor();
        userIpMap.put(adminName, adminIp);

        topic = ActiveMQConnectionManager.createChatroomTopic(chatroomName);
    }

    public Chatroom(String chatroomName, String username, String hostIp, String hostPort){

        this.userIpMap = new HashMap<>();
        this.cmdProcessor = new CommandProcessor();
        userIpMap.put(username, hostIp);

        topic = ActiveMQConnectionManager.joinChatroomTopic(chatroomName, hostIp, hostPort);
    }


private class CommandProcessor {
    public CommandProcessor(){

    }
}
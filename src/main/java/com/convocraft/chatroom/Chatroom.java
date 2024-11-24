package com.convocraft.chatroom;
import java.util.HashMap;
public class Chatroom {

    ActiveMQConnection topic ;
    HashMap<String,String> userIpMap ;
    CommandProcessor cmdProcessor;
    
    public Chatroom(ActiveMQConnection topic, String username, String userIp){
        this.topic = topic;
        this.userIpMap = new HashMap<>();
        this.cmdProcessor = new CommandProcessor();
        userIpMap.put(username, userIp);

    }
}
private class CommandProcessor {
    public CommandProcessor(){

    }
}
package com.convocraft.chatroomManager;

import com.convocraft.chatroom.ActiveMQConnection;

public class ActiveMQConnectionManager{
    
    public static ActiveMQConnection createChatroomTopic(String topicName) {
        ActiveMQConnection newConnection = new ActiveMQConnection(topicName);
    }

    public static ActiveMQConnection joinChatroomTopic(String topicName, String hostIp, String hostPort) {
        ActiveMQConnection newConnection = new ActiveMQConnection(topicName, adminIp, adminPort);

    }
}
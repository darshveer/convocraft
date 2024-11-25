package com.convocraft.chatroom;

import java.util.HashMap;
import javax.jms.*;
import com.convocraft.chatroomManager.ActiveMQConnectionManager;

public class Chatroom {

    ActiveMQConnection connection;
    HashMap<String,String> userIpMap ;
    CommandProcessor cmdProcessor;
    Session session;
    MessageProducer producer;
    MessageConsumer consumer;
    
    public Chatroom(String chatroomName, String adminName, String adminIp){

        this.userIpMap = new HashMap<>();
        this.cmdProcessor = new CommandProcessor();
        userIpMap.put(adminName, adminIp);

        connection = ActiveMQConnectionManager.createChatroomTopic(chatroomName);
        session = connection.getSession();
        producer = session.createProducer(destination);
        consumer = session.createConsumer(destination);
    }

    public Chatroom(String chatroomName, String username, String hostIp, String hostPort){

        this.userIpMap = new HashMap<>();
        this.cmdProcessor = new CommandProcessor();
        userIpMap.put(username, hostIp);

        connection = ActiveMQConnectionManager.joinChatroomTopic(chatroomName, hostIp, hostPort);
        session = connection.getSession();
        producer = session.createProducer(destination);
        consumer = session.createConsumer(destination);
    }

    public void sendMessage(String message){ // send message to topic
        TextMessage textMessage = session.createTextMessage(message);
        producer.send(textMessage);
    }

    public String receiveMessage(){ // receive message from topic
       
        Message receivedMessage = consumer.receive();
        // Print the received message
        if (receivedMessage instanceof TextMessage) {
            return ((TextMessage) receivedMessage).getText();
        }
        return null;
    }

                                                                                                                                                                                      
private class CommandProcessor {
    public CommandProcessor(){
        
    }
}
package com.convocraft.chatroom;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class ChatroomSkele {
    private final String name;
    private final String admin;
    private Connection connection;
    private Session session;
    private MessageProducer producer;
    private MessageConsumer consumer;

    // Constructor for admin creating the chatroom
    public ChatroomSkele(String name, String admin) {
        this.name = name;
        this.admin = admin;
        initializeConnection();
    }

    // Constructor for users joining the chatroom
    public ChatroomSkele(String name) {
        this.name = name;
        this.admin = null; // Admin not required for joining
        initializeConnection();
    }

    private void initializeConnection() {
        try {
            ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://localhost:61616");
            connection = factory.createConnection();
            connection.start();

            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            Destination topic = session.createTopic(name);
            producer = session.createProducer(topic);
            consumer = session.createConsumer(topic);

            System.out.println("Chatroom " + name + " is connected to ActiveMQ.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void broadcastMessage(String sender, String message) {
        try {
            String fullMessage = sender + ": " + message;
            TextMessage textMessage = session.createTextMessage(fullMessage);
            producer.send(textMessage);
            System.out.println("Broadcasted: " + fullMessage);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public void subscribe(String username) {
        try {
            System.out.println(username + " has joined chatroom: " + name);

            consumer.setMessageListener(message -> {
                if (message instanceof TextMessage) {
                    try {
                        String receivedText = ((TextMessage) message).getText();
                        System.out.println(username + " received: " + receivedText);
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            if (connection != null) connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}

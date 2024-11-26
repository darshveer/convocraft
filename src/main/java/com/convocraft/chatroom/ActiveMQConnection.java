package com.convocraft.chatroom;

import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class ActiveMQConnection{

    private ConnectionFactory connectionFactory;
    private Connection connection;
    private Session session;
    private Destination destination;

    public ActiveMQConnection(String topicName){
        try{
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");

        // Create a connection
        Connection connection = connectionFactory.createConnection();
        connection.start(); System.out.println("Connection started successfully");


        // Create a session
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE); System.out.println("Session created successfully");


        // Create a destination (topic for broadcasts)
        Destination destination = session.createTopic(topicName); System.out.println("Destination created successfully");
        }catch (JMSException e) {
            System.out.println("Error connecting to ActiveMQ: " + e.getMessage());
        }
    }
    
    public ActiveMQConnection(String topicName, String hostIp, String hostPort, String username){
        try{
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://"+hostIp+":"+hostPort);

            // Create a connection
            Connection connection = connectionFactory.createConnection();
            connection.start(); System.out.println("Connection started successfully");

            // Create a session
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE); System.out.println("Session created successfully");

            // Create a destination (topic for broadcasts)
            Destination destination = session.createTopic(topicName); System.out.println("Destination created successfully");
        } catch (JMSException e) {
            System.out.println("Error connecting to ActiveMQ: " + e.getMessage());
        }
    }

    public Session getSession(){
        return session;
    }
    
}

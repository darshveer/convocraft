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

    

    public ActiveMQConnection(String topicName){
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");

        // Create a connection
        Connection connection = connectionFactory.createConnection();
        connection.start();

        // Create a session
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // Create a destination (topic for broadcasts)
        Destination destination = session.createTopic(topicName);

        // Create a message producer
        MessageProducer producer = session.createProducer(destination);
    }
    
    public ActiveMQConnection(String topicName, String hostIp, String hostPort, String username){
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://"+hostIp+":"+hostPort+);

        // Create a connection
        Connection connection = connectionFactory.createConnection();
        connection.start();

        // Create a session
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // Create a destination (topic for broadcasts)
        Destination destination = session.createTopic(topicName);
    }
    

    public void joinChatroom() throws JMSException {

        // Create a connection factory
        try {
            InetAddress address = InetAddress.getLocalHost();
            String ipAddress = address.getHostAddress();
            System.out.println("IP Address: " + ipAddress);

            // Create a connection factory using the IP address
        } catch (UnknownHostException e) {
            System.out.println("Unknown host");
        }
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");

        // Create a connection
        Connection connection = connectionFactory.createConnection();
        connection.start();

        // Create a session
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // Create a destination (queue or topic)
        Destination destination = session.createQueue("hello.world.queue");

        // Create a message producer
        MessageProducer producer = session.createProducer(destination);

        // Create a message
        TextMessage message = session.createTextMessage("Hello, World!");

        // Send the message
        producer.send(message);

        // Create a message consumer
        MessageConsumer consumer = session.createConsumer(destination);

        // Receive the message
        Message receivedMessage = consumer.receive();

        // Print the received message
        if (receivedMessage instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) receivedMessage;
            System.out.println("Received message: " + textMessage.getText());
        }

        // Close the connection
        connection.close();
    }
}

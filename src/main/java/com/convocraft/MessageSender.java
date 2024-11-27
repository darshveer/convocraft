package com.convocraft;

import com.convocraft.chatroomManager.*;
import java.util.Scanner;

class MessageSender implements Runnable {
    private User user;
    private Scanner scanner;

    public MessageSender(User user, Scanner scanner) {
        this.user = user;
        this.scanner = scanner;
    }

    @Override
    public void run() {
        System.out.println("Start sending messages:@startuml
        skinparam nodesep 50
        skinparam ranksep 50
        
        class Chatroom {
          - userIpMap: HashMap<String, String>
          - cmdProcessor: CommandProcessor
          - session: Session
          - producer: MessageProducer
          - consumer: MessageConsumer
          + sendMessage(String message)
          + receiveMessage()
        }
        
        class User {
          - username: String
          - chatroom: Chatroom
          + receiveMessage()
          + sendMessage(String message)
        }
        
        class Admin extends User {
          + Admin(String username, Chatroom chatroom)
        }
        
        class MessageSender {
          - user: User
          - scanner: Scanner
          + run()
        }
        
        class MessageReceiver {
          - user: User
          + run()
        }
        
        class ActiveMQConnection {
          - connectionFactory: ConnectionFactory
          - connection: Connection
          - session: Session
          - destination: Destination
          + getSession()
          + getDestination()
        }
        
        class ActiveMQConnectionManager {
          + createChatroomTopic(String topicName)
          + joinChatroomTopic(String topicName, String hostIp, String hostPort)
        }
        
        class App {
          + main(String[] args)
        }
        
        Chatroom --* User
        User --* Chatroom
        Admin --* Chatroom
        MessageSender --* User
        MessageReceiver --* User
        ActiveMQConnection --* ActiveMQConnectionManager
        ActiveMQConnectionManager --* Chatroom
        App --* Chatroom
        App --* User
        
        @enduml");
        while (true) {
            String message = scanner.nextLine();
            user.sendMessage(message);
        }
    }
}
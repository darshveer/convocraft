package com.convocraft;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

import com.convocraft.chatroom.Chatroom;
import com.convocraft.chatroomManager.Admin;
import com.convocraft.chatroomManager.User;

public class App
{
    
    public static void main( String[] args )
    {       
        Chatroom chatroom;
        User user;
        String ipAddress = null;

        try {                                                                           // Get the IP address
            InetAddress address = InetAddress.getLocalHost();
            ipAddress = address.getHostAddress();
            System.out.println("IP Address: " + ipAddress);

            // Create a connection factory using the IP address
        } catch (UnknownHostException e) {
            System.out.println("Unknown host");
        }


        Scanner scanner = new Scanner(System.in);
        System.out.println( "Hello User!\nWould you like to 'CREATE' a new Chatroom or 'JOIN' an existing one?" );
        

        while(true){
            String input = scanner.nextLine();

            if (input.equals("'CREATE'")|| input.equals("CREATE")){                          //Creating a new chatroom and user

                System.out.println("What would you like to name your chatroom?");
                String chatroomName = scanner.nextLine();
                System.out.println("What would you like to name your admin?");
                String adminName = scanner.nextLine();

                //TODO: Make sure chatroom name is unique while extending
                chatroom = new Chatroom(chatroomName, adminName, ipAddress);

                user = new Admin(adminName, chatroom);
                
                // Start threads for sending and receiving messages
                Thread senderThread = new Thread(new MessageSender(user,scanner));
                Thread receiverThread = new Thread(new MessageReceiver(user));

                System.out.println("Your ip address is: " + ipAddress);
                System.out.println("Your port is: 61616" );

                senderThread.start();
                receiverThread.start();
                try {
                    senderThread.join();
                    receiverThread.join();
                } catch (InterruptedException e) {
                    // Handle the exception
                    System.err.println("Thread was interrupted: " + e.getMessage());
                }
                
                break;
            }else if(input.equals("'JOIN'")|| input.equals("JOIN")){            //Joining an existing chatroom and creating user

                System.out.println("What is the name of the chatroom you would like to join?");
                String chatroomName = scanner.nextLine();
                System.out.println("What is your username?");
                String username = scanner.nextLine();
                System.out.println("What is the Ip of the chatroom host?");
                String hostIp = scanner.nextLine();
                System.out.println("What is the port of the chatroom host?");
                String hostPort = scanner.nextLine();

                chatroom = new Chatroom(chatroomName, username, hostIp, hostPort);

                System.out.println("What would you like to name your user?");
                username = scanner.nextLine();
                user = new User(username, chatroom);

                // Start threads for sending and receiving messages
                Thread senderThread = new Thread(new MessageSender(user,scanner));
                Thread receiverThread = new Thread(new MessageReceiver(user));

                senderThread.start();
                receiverThread.start();
                try {
                    senderThread.join();
                    receiverThread.join();
                } catch (InterruptedException e) {
                    // Handle the exception
                    System.err.println("Thread was interrupted: " + e.getMessage());
                }
                
                
                break;
            }else{
                System.out.println("Invalid input, please type 'CREATE' or 'JOIN'.");
            }
        }
    }
}

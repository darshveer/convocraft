package com.convocraft.cmdManager;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

import com.convocraft.MessageReceiver;
import com.convocraft.MessageSender;
import com.convocraft.chatroom.Chatroom;
import com.convocraft.chatroomManager.Admin;

public class newRoom {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("\033[H\033[2J"); // move cursor to top and clear screen
        System.out.flush();

        System.out.print("Enter Chatroom name: ");
        String chatroomName = scanner.nextLine();
        System.out.print("Enter admin name: ");
        String adminName = scanner.nextLine();
        String ipAddress = null;
        try {                                                                           // Get the IP address
            ipAddress = InetAddress.getLocalHost().getHostAddress();

            // Create a connection factory using the IP address
        } catch (UnknownHostException e) {
            System.out.println("Unknown host");
        }

        Chatroom chatroom = new Chatroom(chatroomName, adminName, ipAddress);

        Admin admin = new Admin(ipAddress, chatroom);
        // Start threads for sending and receiving messages
        Thread senderThread = new Thread(new MessageSender(admin,scanner));
        Thread receiverThread = new Thread(new MessageReceiver(admin));

        System.out.println("Your IP address is: " + ipAddress);
        System.out.println("Your port is: 61616" );

        
        receiverThread.start();
        senderThread.start();
        try {
            receiverThread.join();
            senderThread.join();
        } catch (InterruptedException e) {
            // Handle the exception
            System.err.println("Thread was interrupted: " + e.getMessage());
        }
    }
}

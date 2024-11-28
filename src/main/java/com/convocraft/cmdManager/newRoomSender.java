package com.convocraft.cmdManager;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

import com.convocraft.MessageSender;
import com.convocraft.chatroom.Chatroom;
import com.convocraft.chatroomManager.Admin;

public class newRoomSender {
    public static void main(String[] args) throws IOException {
        try (Scanner scanner = new Scanner(System.in)) {
            Admin admin;
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("admin.ser"))) {
                admin = (Admin) ois.readObject();
            } catch (ClassNotFoundException e) {
                throw new IOException("Failed to deserialize admin object", e);
            }

            String ipAddress = null;
            try {                                                                           // Get the IP address
                ipAddress = InetAddress.getLocalHost().getHostAddress();

                // Create a connection factory using the IP address
            } catch (UnknownHostException e) {
                System.out.println("Unknown host");
            }

            Chatroom chatroom = new Chatroom(admin.getChatroomName(), admin.getUserName(), ipAddress);
            admin = new Admin(ipAddress, chatroom);
            Thread senderThread = new Thread(new MessageSender(admin, scanner));
            senderThread.start();

            try {
                senderThread.join();
            } catch (InterruptedException e) {
                // Handle the exception
                System.err.println("Thread was interrupted: " + e.getMessage());
            }
        }
    }
}

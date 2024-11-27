package com.convocraft.cmdManager;

import java.io.IOException;
import java.util.Scanner;

import com.convocraft.MessageReceiver;
import com.convocraft.MessageSender;
import com.convocraft.chatroom.Chatroom;
import com.convocraft.chatroomManager.User;

public class existingRoom {
    public static void main(String[] args) throws IOException {
        System.out.print("\033[H\033[2J"); // move cursor to top and clear screen
        System.out.flush();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter name of Chatroom to join: ");
        String chatroomName = scanner.nextLine();
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Enter IP Address of the chatroom host: ");
        String hostIp = scanner.nextLine();
        System.out.print("Enter port of Chatroom host: ");
        String hostPort = scanner.nextLine();

        Chatroom chatroom = new Chatroom(chatroomName, username, hostIp, hostPort);
        User user = new User(username, chatroom);
        // Start threads for sending and receiving messages
        Thread senderThread = new Thread(new MessageSender(user,scanner));
        Thread receiverThread = new Thread(new MessageReceiver(user));

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

package com.convocraft.cmdManager;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Scanner;

import com.convocraft.MessageSender;
import com.convocraft.chatroom.Chatroom;
import com.convocraft.chatroomManager.User;

public class existingRoomSender {
    public static void main(String[] args) throws IOException {
        try (Scanner scanner = new Scanner(System.in)) {
            User user;
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("user.ser"))) {
                user = (User) ois.readObject();
            } catch (ClassNotFoundException e) {
                throw new IOException("Failed to deserialize user object", e);
            }

            Chatroom chatroom = new Chatroom(user.getChatroomName(), user.getUserName(), user.getChatroomIp(), user.getChatroomPort());
            user = new User(user.getUserName(), chatroom, user.getChatroomIp(), user.getChatroomPort());
            Thread senderThread = new Thread(new MessageSender(user, scanner));
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

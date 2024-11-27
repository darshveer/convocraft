package com.convocraft;
import com.convocraft.chatroomManager.User;

public class MessageReceiver implements Runnable {
    private User user;

    public MessageReceiver(User user) {
        this.user = user;
    }

    @Override
    public void run() {
        while (true) {
            String message = user.receiveMessage();
            if (message != null) {
                // System.out.print("\033[10;0H");
                System.out.println("Received message: " + message); // Print message
                // System.out.print("\033[B");
                // System.out.print("\033[H\033[0E");
            }
        }
    }
}

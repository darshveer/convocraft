package com.convocraft;
import com.convocraft.chatroomManager.User;

class MessageReceiver implements Runnable {
    private User user;

    public MessageReceiver(User user) {
        this.user = user;
    }

    @Override
    public void run() {
        while (true) {
            String message = user.receiveMessage();
            if (message != null) {
                System.out.println("Received message: " + message);
            }
        }
    }
}

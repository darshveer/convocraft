package com.convocraft;

import java.util.Scanner;

import com.convocraft.chatroomManager.User;

class MessageSender implements Runnable {
    private User user;
    private Scanner scanner;

    public MessageSender(User user, Scanner scanner) {
        this.user = user;
        this.scanner = scanner;
    }

    @Override
    public void run() {
        System.out.println("You've joined the Chatroom! Send messages below.");
        while (true) {
            String message = scanner.nextLine();
            user.sendMessage(message);
        }
    }
}
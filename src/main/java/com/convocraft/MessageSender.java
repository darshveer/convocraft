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
        System.out.println("Start sending messages: ");
        while (true) {
            String message = scanner.nextLine();
            user.sendMessage(message);
        }
    }
}
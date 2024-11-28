package com.convocraft;

import java.util.Scanner;

import com.convocraft.chatroomManager.User;

public class MessageSender implements Runnable {
    private User user;
    private Scanner scanner;

    public MessageSender(User user, Scanner scanner) {
        this.user = user;
        this.scanner = scanner;
    }


    @Override
    public void run() {
        System.out.println("Send messages to the Chatroom "+user.getChatroomName()+" below");
        while (true) {
            System.out.print("Send >> ");
            String message = scanner.nextLine();
            //System.out.print("\033[2K\r");
            user.sendMessage(message);
        }
    }
}
package com.convocraft;

import java.util.Scanner;

import com.convocraft.chatroomManager.User;
import com.convocraft.commandProcessor.commandProcessor;

public class MessageSender implements Runnable {
    private User user;
    private Scanner scanner;
    private commandProcessor commandProcessor;

    public MessageSender(User user, Scanner scanner) {
        this.user = user;
        this.scanner = scanner;
        commandProcessor = new commandProcessor(user.getChatroom(), user);
    }


    @Override
    public void run() {
        System.out.println("Send messages to the Chatroom "+user.getChatroomName()+" below");
        while (true) {
            System.out.print("Send >> ");
            String message = scanner.nextLine();
            //System.out.print("\033[2K\r");

            commandProcessor.process(message);

        }
    }
}
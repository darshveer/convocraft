package com.convocraft;

import com.convocraft.chatroomManager.*;

}
class MessageSender implements Runnable {
    private User user;

    public MessageSender(User user) {
        this.user = user;
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter message to send:");
            String message = scanner.nextLine();
            user.sendMessage(message);
        }
    }
}
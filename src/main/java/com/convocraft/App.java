package com.convocraft;

import java.net.InetAddress;
import java.util.Scanner;

import com.convocraft.chatroom.Chatroom;

public class App 
{
    public static void main( String[] args )
    {   
        try {
            InetAddress address = InetAddress.getLocalHost();
            String ipAddress = address.getHostAddress();
            System.out.println("IP Address: " + ipAddress);

            // Create a connection factory using the IP address
        } catch (UnknownHostException e) {
            System.out.println("Unknown host");
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println( "Hello User!\nWould you like to 'CREATE' a new Chatroom or 'JOIN' an existing one?" );

        String input = scanner.nextLine();

        if (input.equals("'CREATE'")| input.equals("CREATE")){                          //Creating a new chatroom

            System.out.println("What would you like to name your chatroom?");
            String chatroomName = scanner.nextLine();
            System.out.println("What would you like to name your admin?");
            String adminName = scanner.nextLine();

            //TODO: Make sure chatroom name is unique

            Chatroom chatroom = new Chatroom(chatroomName, adminName, ipAddress);
        }else if(input.equals(input.equals("'JOIN'")| input.equals("JOIN"))){            //Joining an existing chatroom

            System.out.println("What is the name of the chatroom you would like to join?");
            String chatroomName = scanner.nextLine();
            System.out.println("What is your username?");
            String username = scanner.nextLine();
            System.out.println("What is the Ip of the chatroom host?");
            String hostIp = scanner.nextLine();
            System.out.println("What is the port of the chatroom host?");
            String hostPort = scanner.nextLine();

            Chatroom chatroom = new Chatroom(chatroomName, username, hostIp, hostPort);

    }
}

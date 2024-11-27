package com.convocraft;

import java.util.Scanner;

import com.convocraft.cmdManager.TerminalInteraction;

public class App
{
    
    public static void main( String[] args )
    {       
        Scanner scanner = new Scanner(System.in);        

        System.out.print( "Hello User!\nWould you like to 'CREATE' a new Chatroom or 'JOIN' an existing one: " );
        String input = scanner.nextLine();

        if (input.equals("'CREATE'")|| input.equals("CREATE")){                          //Creating a new chatroom and user
            // TODO: Make sure chatroom name is unique while extending
            TerminalInteraction terminalInteraction = new TerminalInteraction();
            terminalInteraction.createRoom();
        } else if (input.equals("'JOIN'")|| input.equals("JOIN")){            //Joining an existing chatroom and creating user

            TerminalInteraction terminalInteraction = new TerminalInteraction();
            terminalInteraction.joinRoom();
        } else {
            System.out.println("Invalid input, please type 'CREATE' or 'JOIN'.");
        }
    }
}

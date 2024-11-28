package com.convocraft;

import java.util.Scanner;

import com.convocraft.cmdManager.TerminalInteraction2;

public class App
{
    
    public static void main( String[] args )
    {       
        TerminalInteraction2 terminalInteraction = new TerminalInteraction2();
        terminalInteraction.loop();
    }
}

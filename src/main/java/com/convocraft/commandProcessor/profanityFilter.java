package com.convocraft.commandProcessor;

import java.io.File;

public class profanityFilter {
    static 
    {
        // String currentDir = new File(".").getAbsolutePath();
        // System.setProperty("java.library.path", currentDir);
        // System.out.println(System.getProperty("java.library.path"));
        try {
            System.loadLibrary("profanityFilter");
            System.out.println("Profanity filter loaded");
        } catch (UnsatisfiedLinkError e) {
            System.out.println("Error loading library: " + e.getMessage());
        }  
        
    }
    
    public native static String filterProfanityRetainingWhitespace(String message, String fileName);
}

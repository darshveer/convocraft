package main.java.com.convocraft.commandProcessor;

import java.util.HashMap;

public class profanityFilter {
    static 
    {
        System.setProperty("java.library.path", System.getProperty("java.library.path") + ";src\\main\\resources");
        System.loadLibrary("profanityFilter.dll");    }

    public native String filterProfanityRetainingWhitespace(String message, String fileName);
}

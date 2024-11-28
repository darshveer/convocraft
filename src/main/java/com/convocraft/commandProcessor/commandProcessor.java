package com.convocraft.commandProcessor;

import com.convocraft.chatroom.Chatroom;
import com.convocraft.chatroomManager.User;
import com.convocraft.commandProcessor.profanityFilter;
import com.convocraft.chatroomManager.Admin;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class commandProcessor 
{
    public void addProfanity(String profanity, Chatroom chatroom)        // admin only
    {
        List<String> profanityList = chatroom.getProfanities();         // generates a refernce to the list of profanities within scope

        if(profanityList.contains(profanity))
            System.out.println("Profanity is already blocked.");
        else
        {
            chatroom.sendMessage("\'" + profanity + "\' has been added to the list of profanities for this chatroom.");
            profanityList.add(profanity);       // profanity is added to list after announcement so that it is not censored
        }
    }

    public void removeProfanity(String profanity, Chatroom chatroom)    // admin only
    {
        List<String> profanityList = chatroom.getProfanities();         // generates a refernce to the list of profanities
    
       if(!profanityList.contains(profanity))
                System.out.println("Profanity is not blocked.");
        else
        {
            profanityList.remove(profanity);
            chatroom.sendMessage("\'" + profanity + "\' has been removed from the list of profanities for this chatroom.");
        }
        
    }

    public void leaveChatroom(Chatroom chatroom)                        // accessible to all users
    {
        chatroom.closeConnection();
    }

    public void kickUser(Chatroom chatroom, String kickUser)            // admin only
    {
        if(!connectedUsers.containsKey(kickUser))
            System.out.println("User is not connected to the chatroom.");
        else
        {
            chatroom.removeUser(kickUser);
            chatroom.sendMessage("The user " + kickUser + " has been kicked from the chatroom.");
        }
    }

    public void banUser(Chatroom chatroom, String banUser)              // admin only
    {
        HashMap<String, String> bannedUsers = chatroom.getBanned();     // generates a refernce to list of banned users
        HashMap<String, String> connectedUsers = chatroom.getUsers();   // generates a refernce to list of connected users

        if(!connectedUsers.containsKey(banUser))
            System.out.println("User is not connected to the chatroom.");
        else
        {
            String banIP = bannedUsers.get(banUser);
            bannedUsers.put(banUser, banIP);
            chatroom.removeUser(banUser);
            chatroom.sendMessage("The user " + banUser + " has been banned permanently.");
        }
    }

    public void createPoll(Chatroom chatroom, String pollID, String question, String[] options, String answer)
    {

        
    }

    public void replyPoll(Chatroom chatroom, String pollID, String answer)
    {

    }

    public void process(String message, User user)
    {

    }

    public static void main(String[] args)
    {
        profanityFilter pFilter = new profanityFilter();
        System.out.println(pFilter.filterProfanityRetainingWhitespace("fuck piss shit cum"));
    }

}

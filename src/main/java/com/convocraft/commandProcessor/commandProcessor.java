package com.convocraft.commandProcessor;

import com.convocraft.chatroom.Chatroom;
import com.convocraft.chatroomManager.User;
import com.convocraft.commandProcessor.ProfanityFilter;
import com.convocraft.chatroomManager.Admin;
import com.convocraft.chatroom.Poll;

import java.util.HashMap;
import java.util.List;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class commandProcessor 
{

    Chatroom chatroom;
    User user;
    boolean isActive;
    ProfanityFilter filter;

    public commandProcessor(Chatroom chatroom, User user)
    {
        this.user = user;
        this.chatroom = chatroom;
        isActive = true;
        try {
            filter = new ProfanityFilter("badWords.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isActive() {
        return isActive;
    }
    
    // public void addProfanity(String profanity, Chatroom chatroom)        // admin only
    // {
    //     List<String> profanityList = chatroom.getProfanities();         // generates a refernce to the list of profanities within scope

    //     if(profanityList.contains(profanity))
    //         System.out.println("Profanity is already blocked.");
    //     else
    //     {
    //         chatroom.sendMessage("\'" + profanity + "\' has been added to the list of profanities for this chatroom.");
    //         profanityList.add(profanity);       // profanity is added to list after announcement so that it is not censored
    //     }
    // }

    // public void removeProfanity(String profanity, Chatroom chatroom)    // admin only
    // {
    //     List<String> profanityList = chatroom.getProfanities();         // generates a refernce to the list of profanities
    
    //    if(!profanityList.contains(profanity))
    //             System.out.println("Profanity is not blocked.");
    //     else
    //     {
    //         profanityList.remove(profanity);
    //         chatroom.sendMessage("\'" + profanity + "\' has been removed from the list of profanities for this chatroom.");
    //     }
        
    // }

    public void leaveChatroom()                        // accessible to all users
    {
        chatroom.closeConnection();
    }

    // public void kickUser(Chatroom chatroom, String kickUser)            // admin only
    // {
    //     if(!connectedUsers.containsKey(kickUser))
    //         System.out.println("User is not connected to the chatroom.");
    //     else
    //     {
    //         chatroom.removeUser(kickUser);
    //         chatroom.sendMessage("The user " + kickUser + " has been kicked from the chatroom.");
    //     }
    // }

    // public void banUser(Chatroom chatroom, String banUser)              // admin only
    // {
    //     HashMap<String, String> bannedUsers = chatroom.getBanned();     // generates a refernce to list of banned users
    //     HashMap<String, String> connectedUsers = chatroom.getUsers();   // generates a refernce to list of connected users

    //     if(!connectedUsers.containsKey(banUser))
    //         System.out.println("User is not connected to the chatroom.");
    //     else
    //     {
    //         String banIP = bannedUsers.get(banUser);
    //         bannedUsers.put(banUser, banIP);
    //         chatroom.removeUser(banUser);
    //         chatroom.sendMessage("The user " + banUser + " has been banned permanently.");
    //     }
    // }

    public void createPoll(Chatroom chatroom, String pollID, String question, String[] options)
    {
        Poll newPoll = new Poll(pollID, question, Arrays.asList(options));
        chatroom.addPoll(pollID, newPoll);

    }

    public void replyPoll(Chatroom chatroom, String pollID, String answer, String user)
    {
        if(chatroom.getPollMap().containsKey(pollID))
        {
            Poll poll = chatroom.getPollMap().get(pollID);
            poll.addResponse(user, answer);
        }
    }

    public void viewPoll(Chatroom chatroom, String pollID)
    {
        if(chatroom.getPollMap().containsKey(pollID))
        {
            Poll poll = chatroom.getPollMap().get(pollID);
            user.sendMessage("/viewpoll " + poll.toString());
        }
        else
            System.out.println("Poll with ID " + pollID + " does not exist.");
    }


    // public static void main(String[] args)
    // {
    //     profanityFilter pFilter = new profanityFilter();
    //     System.out.println(pFilter.filterProfanityRetainingWhitespace("fuck piss shit cumb"));
    // }

    public void processSend(String message)
    {
        String[] words = message.replaceAll("^\\s+/", "/").split(" ");
        if (words.length > 0) {
            String command = words[0].toLowerCase();
            switch (command) {
                case "/help":
                    System.out.println("/msg [your message] - Send a message to the chatroom\n/leave - Leave the chatroom");
                    break;
                case "/msg":
                    String toSend = "/msg " + user.getUserName() + ": " + filter.filterProfanity(String.join(" ", Arrays.copyOfRange(words, 1, words.length)));
                    chatroom.sendMessage(toSend);
                    break;

                case "/leave":
                    chatroom.sendMessage("/leave "+user.getUserName());
                    leaveChatroom();
                    break;

                case "/kickuser":
                    if (user instanceof Admin){
                        chatroom.sendMessage(message);
                    }else{
                        System.out.println("You are not an admin.");
                    }
                    break;

                case "/banuser":
                    // Handle /banuser command
                    break;

                case "/createpoll":                     // splits the message via format /createpoll question | option1, option2, ...
                if (user instanceof Admin){
                    if (words.length < 3) {
                        System.out.println("Error: Invalid poll format. Please use /createpoll question | option1, option2, ...");
                    } else {
                        String pollID = words[1];
                        if (chatroom.getPollMap().containsKey(pollID)) {
                            System.out.println("Error: Poll ID already in use. Please choose a unique ID.");
                            break;
                        }
                
                        String pollInfo = String.join(" ", Arrays.copyOfRange(words, 2, words.length));
                        String[] pollDetails = pollInfo.split("\\|");
                        if (pollDetails.length < 2) {
                            System.out.println("Error: Invalid poll format. Please use /createpoll question | option1, option2, ...");
                            break;
                        }
                
                        String question = pollDetails[0].trim();
                        if (question.isEmpty()) {
                            System.out.println("Error: Question cannot be empty.");
                            break;
                        }
                
                        String[] options = pollDetails[1].split(",");
                        for (String option : options) {
                            option = option.trim();
                            if (option.isEmpty()) {
                                System.out.println("Error: Options cannot be empty.");
                                break;
                            }
                        }
                
                        createPoll(chatroom, pollID, question, options);
                    }
                }else{
                    System.out.println("Only admins can create polls.");
                }
                    break;

                case "/replypoll":
                    String pollID = words[1];
                    String answer = String.join(" ", Arrays.copyOfRange(words, 2, words.length));
                    user.sendMessage("/replypoll " +user.getUserName()+" "+ pollID + " " + answer);
                    break;

                case "/viewpoll":
                if (user instanceof Admin){
                    pollID = words[1];
                    viewPoll(chatroom, pollID);
                }   
                    break;

                default:
                    // Handle other commands or send message to chatroom
                    break;
            }
        }
    }
    public void processReceive(String message){
        String[] words = message.split(" ");
        if (words.length > 0) {
            String command = words[0].toLowerCase();
            switch (command) {
                case "/msg":
                    System.out.println(words[1] +" "+ String.join(" ", Arrays.copyOfRange(words, 2, words.length)));
                    break;
                case "/leave":
                    System.out.println("User - "+words[1]+" has left the chatroom");
                    break;
                case "/kickuser":
                    if (user.getUserName().equals(words[1])){
                        user.sendMessage("User "+words[1]+" has been kicked from the chatroom");
                        leaveChatroom();
                        isActive=false;
                    }
                    break;
                case "/banuser":
                    // Handle /banuser command
                    break;
                case "/replypoll":
                    if (user instanceof Admin){
                        String username = words[1]; 
                        String pollID = words[2];
                        String answer = String.join(" ", Arrays.copyOfRange(words, 3, words.length));
                        replyPoll(chatroom, pollID, answer, username);
                    }
                case "/viewpoll":
                    System.out.println(Arrays.copyOfRange(words, 1, words.length));
            
                    break;
                default:
                    // Handle other commands or send message to chatroom
                    break;
            }
        }
    }

}

package com.convocraft.commandProcessor;

import java.util.*;
import java.io.*;

public class ProfanityFilter {
    private Set<String> badWords;

    public ProfanityFilter(String filename) throws IOException {
        badWords = loadBadWords(filename);
    }

    private Set<String> loadBadWords(String filename) throws IOException {
        Set<String> badWords = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/" + filename)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                badWords.add(line.trim().toLowerCase());
            }
        }
        return badWords;
    }

    public String filterProfanity(String message) {
        StringBuilder filteredMessage = new StringBuilder();
        String currentWord = "";

        for (char ch : message.toCharArray()) {
            if (Character.isWhitespace(ch)) {
                if (!currentWord.isEmpty()) {
                    String cleanWord = toLower(stripPunctuation(currentWord));
                    if (badWords.contains(cleanWord)) {
                        filteredMessage.append(censorWord(currentWord));
                    } else {
                        filteredMessage.append(currentWord);
                    }
                    currentWord = "";
                }
                filteredMessage.append(ch);
            } else {
                currentWord += ch;
            }
        }

        if (!currentWord.isEmpty()) {
            String cleanWord = toLower(stripPunctuation(currentWord));
            if (badWords.contains(cleanWord)) {
                filteredMessage.append(censorWord(currentWord));
            } else {
                filteredMessage.append(currentWord);
            }
        }

        return filteredMessage.toString();
    }

    private String toLower(String str) {
        return str.toLowerCase();
    }

    private String stripPunctuation(String word) {
        StringBuilder cleanWord = new StringBuilder();
        for (char ch : word.toCharArray()) {
            if (Character.isLetterOrDigit(ch)) {
                cleanWord.append(ch);
            }
        }
        return cleanWord.toString();
    }

    private String censorWord(String word) {
        StringBuilder censoredWord = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            censoredWord.append('*');
        }
        return censoredWord.toString();
    }
}
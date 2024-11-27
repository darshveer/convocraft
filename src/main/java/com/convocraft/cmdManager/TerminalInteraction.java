package com.convocraft.cmdManager;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class TerminalInteraction {

    public static void main(String[] args) {
        
    }

    public void createRoom() {
        Process newTerminalProcess = null;

        try {
            // Get the current working directory
            String currentDir = new File(".").getCanonicalPath();

            // Command to open a new terminal, run the Java file
            String command = String.format(
                "cmd.exe /c start cmd.exe /k \"cd %s && mvn dependency:copy-dependencies && javac -cp target/classes;target/dependency/* src/main/java/com/convocraft/cmdManager/newRoom.java  && java -cp target/classes;target/dependency/*;target/dependency/javax.jms-api-2.0.1.jar com.convocraft.cmdManager.newRoom && exit\"",
                currentDir
            );

            // Use ProcessBuilder to execute the command
            ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", command);

            // Start the process (this opens the new terminal window)
            newTerminalProcess = processBuilder.start();

            System.out.println("New terminal opened to run new Chatroom.");

            // Main terminal input handling
            Scanner scanner = new Scanner(System.in);
            String str;
            do {
                System.out.print("Enter a String [Main Terminal]: ");
                str = scanner.nextLine();
                System.out.println(str);
            } while (!str.equals("exit"));

            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Ensure the new terminal process is destroyed when exiting
            if (newTerminalProcess != null) {
                newTerminalProcess.destroy();
                System.out.println("New terminal process terminated.");
            }
        }
    }

    public void joinRoom() {
        Process newTerminalProcess = null;

        try {
            // Get the current working directory
            String currentDir = new File(".").getCanonicalPath();

            // Command to open a new terminal, run the Java file
            String command = String.format(
                "cmd.exe /c start cmd.exe /k \"cd %s && mvn dependency:copy-dependencies && javac -cp target/classes;target/dependency/* src/main/java/com/convocraft/cmdManager/existingRoom.java  && java -cp target/classes;target/dependency/*;target/dependency/javax.jms-api-2.0.1.jar com.convocraft.cmdManager.existingRoom && exit\"",
                currentDir
            );

            // Use ProcessBuilder to execute the command
            ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", command);

            // Start the process (this opens the new terminal window)
            newTerminalProcess = processBuilder.start();

            System.out.println("New terminal opened for existing Chatroom.");

            // Main terminal input handling
            Scanner scanner = new Scanner(System.in);
            String str;
            do {
                System.out.print("Enter a String [Main Terminal]: ");
                str = scanner.nextLine();
                System.out.println(str);
            } while (!str.equals("exit"));

            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Ensure the new terminal process is destroyed when exiting
            if (newTerminalProcess != null) {
                newTerminalProcess.destroy();
                System.out.println("New terminal process terminated.");
            }
        }
    }
}

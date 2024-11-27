package com.convocraft.cmdManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TerminalInputHandler {

    private static BufferedReader mainTerminalInput;
    private static PrintWriter mainTerminalOutput;

    public static void main(String[] args) {
        // Create an ExecutorService to handle both terminals' inputs and outputs concurrently
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        // Handle main terminal input/output
        executorService.submit(() -> {
            try {
                mainTerminalInput = new BufferedReader(new InputStreamReader(System.in));
                mainTerminalOutput = new PrintWriter(System.out, true);

                String line;
                System.out.println("Main Terminal: Type something!");

                while ((line = mainTerminalInput.readLine()) != null) {
                    // Main terminal output
                    mainTerminalOutput.println("[Main Terminal] You typed: " + line);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        // Handle secondary terminal input/output
        executorService.submit(() -> {
            try {
                // Launch secondary terminal and execute commands
                launchAndRunCommandOnSecondaryTerminal();

            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        // Shutdown executor service after tasks are complete
        executorService.shutdown();
    }

    // Function to launch the secondary terminal and execute commands
    private static void launchAndRunCommandOnSecondaryTerminal() throws IOException {
        // Launch cmd.exe to open the secondary terminal
        ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/k"); // /k keeps cmd open
        Process process = processBuilder.start();

        // Get the output stream to send commands to the secondary terminal
        OutputStream outputStream = process.getOutputStream();
        PrintWriter writer = new PrintWriter(outputStream);

        // Get the input stream to capture the output from the secondary terminal
        InputStream inputStream = process.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        // Send commands to the secondary terminal (e.g., "echo Hello from secondary terminal")
        writer.println("javac TerminalInteraction.java");
        writer.flush();
        writer.println("java TerminalInteraction.java");
        writer.flush();

        // Wait for the process to finish
        try {
            process.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

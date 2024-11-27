package com.convocraft.cmdManager;

import java.util.Scanner;

public class InOu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str;
        do {
            System.out.print("Enter a String [Secondary Terminal]: ");
            str = scanner.nextLine();
            System.out.println(str);
        } while (!str.equals("exit"));

        scanner.close();
    }
}

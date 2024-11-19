package com.convocraft;

public class NativeInterface {
    static {
        System.loadLibrary("nativelib");
    }

    // Declare native method
    public native int add(int a, int b);

    public static void main(String[] args) {
        NativeInterface obj = new NativeInterface();
        System.out.println("Result: " + obj.add(5, 3));
    }
}

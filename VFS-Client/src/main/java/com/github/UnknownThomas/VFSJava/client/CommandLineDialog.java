package com.github.UnknownThomas.VFSJava.client;

import java.util.Scanner;

public class CommandLineDialog {
    public static void main(String[] args) {
        new CommandLineDialog().start();
    }

    private final Scanner input;

    public CommandLineDialog() {
        input = new Scanner(System.in);
    }

    private void start() {
        while (input.hasNextLine()) {
            String command = input.nextLine();
            readConnectCommand();

            System.out.println(command);
        }
        System.out.println("finished");
    }

    private void readConnectCommand() {

    }
}

package com.github.UnknownThomas.VFSJava.server;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ConnectionHandler implements Runnable {
    private final Socket socket;

    private final Scanner input;
    private final PrintWriter output;

    public ConnectionHandler(Socket socket) throws IOException {
        this.socket = socket;

        input = new Scanner(socket.getInputStream());
        output = new PrintWriter(new BufferedOutputStream(socket.getOutputStream()));
    }

    @Override
    public void run() {
        if (isConnectedToVFSClient()) {
            sendAcknowledgment();
            startDialog();
        } else {
            finish();
        }
    }

    private boolean isConnectedToVFSClient() {
        String message = readMessage();
        return message.equals("VFSClient");
    }

    private String readMessage() {
        return null;
    }

    private void sendAcknowledgment() {

    }

    private void startDialog() {
    }

    private void finish() {
        try {
            socket.close();
        } catch (IOException e) {
        }
    }


}

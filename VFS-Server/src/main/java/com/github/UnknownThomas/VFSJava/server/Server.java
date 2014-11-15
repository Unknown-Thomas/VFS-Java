package com.github.UnknownThomas.VFSJava.server;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws Exception {
        new Server().serve();
    }

    private final ServerSocket serverSocket;
    private final ConnectionPool connectionPool;

    public Server() throws Exception {
        serverSocket = new ServerSocket(8888);
        connectionPool = new ConnectionPool();
    }

    public void serve() throws Exception {
        while (!Thread.currentThread().isInterrupted()) {
            Socket socket = serverSocket.accept();
            ConnectionHandler connectionHandler = new ConnectionHandler(socket);
            connectionPool.add(connectionHandler);
        }
    }
}

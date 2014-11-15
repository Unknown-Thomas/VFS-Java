package com.github.UnknownThomas.VFSJava.server;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ConnectionPool {
    private final Executor executor;

    public ConnectionPool() {
        // todo fill from conf.properties
        executor = Executors.newFixedThreadPool(100);
    }

    public void add(ConnectionHandler connectionHandler) {
        executor.execute(connectionHandler);
    }
}

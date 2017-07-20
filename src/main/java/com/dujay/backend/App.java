package com.dujay.backend;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class App {
    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new ServerModule());
        SparkServer server = injector.getInstance(SparkServer.class);
        Runtime.getRuntime().addShutdownHook(new Thread(server::stop));
        server.start();
    }
}

package com.example.demo;

import java.io.IOException;
import java.net.ServerSocket;
import java.sql.DriverManager;
import java.net.*;

import org.apache.catalina.startup.Catalina;

public class test {
    // create the server
    // while the server is running; accept incoming connections
    // create a new thread to handle the clinet's requests
    // start the client handler thread

    public static void main(String args[]) {
        test server = new test();
        server.startServer();
    }

    public void startServer(){
        try(ServerSocket serverSocket=new ServerSocket(8000)){
            System.out.println("Server running on port 8000...")
            while(true){
                Socket client=serverSocket.accept();
                Thread clientHandler=new Thread(new clientHandler(client));
                clientHandler.start();
            }
        }
    }

    public class JDBCConnection {
        public Connection conn;

        public JDBCConnection() {
            try {
                conn = DriverManager.getConnection("jdbc:mysql//localhost:3306/TODO", "root", "abhijit1");
                System.out.println("Connected to the database...");
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}

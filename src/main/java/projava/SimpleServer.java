package main.java.projava;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1600);
        System.out.println("waiting...");
        try (Socket accept = serverSocket.accept();
             InputStream inputStream = accept.getInputStream();){
            System.out.println("""
                connect from %s
                """.formatted(accept.getInetAddress()));
            System.out.println(inputStream.read());
        }
    }
}

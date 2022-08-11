package main.java.projava;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class SimpleClient {
    public static void main(String[] args) throws IOException {
        try(Socket socket = new Socket("localhost", 1600);
            OutputStream outputStream = socket.getOutputStream();){
            outputStream.write(234);
        }
    }
}

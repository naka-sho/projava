package main.java.projava;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

public class ReadFile {
    public static void main(String[] args) throws IOException {
        Path of = Path.of("test.txt");
        try{
            String s = Files.readString(of);
            System.out.println(s);
        }catch (NoSuchFileException e){
            System.out.println("ファイルが見つかりません:" + e.getFile());
        }
    }
}

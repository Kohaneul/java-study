package io.text;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static io.text.TextConst.FILE_NAME;
import static java.nio.charset.StandardCharsets.UTF_8;

public class ReaderWriterMainV4 {

    private static final int BUFFER_SIZE = 8192;
    public static void main(String[] args) throws IOException {
    System.out.println(" == Write File ==");
    String writeString = "가나다\nABC";
    System.out.println(writeString);
    FileWriter fw = new FileWriter(FILE_NAME, UTF_8);
    BufferedWriter bw = new BufferedWriter(fw);
    bw.write(writeString);
    bw.close();

    StringBuilder content = new StringBuilder();
    FileReader fr = new FileReader(FILE_NAME, UTF_8);
    BufferedReader br = new BufferedReader(fr);

    String line;
    while((line = br.readLine())!=null){
        content.append(line).append("\n");
    }
    br.close();
        System.out.println(" == Read File ==");

        System.out.println(content);



    }
}

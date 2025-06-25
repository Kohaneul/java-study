package io.text;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static io.text.TextConst.FILE_NAME;
import static java.nio.charset.StandardCharsets.UTF_8;

public class ReaderWriterMainV3 {
    public static void main(String[] args) throws IOException {
    String writeString = "ABC";
    System.out.println("writeString = " + writeString);
    FileWriter fw = new FileWriter(FILE_NAME);  //인코딩 생략하면 시스템의 기본 인코딩 설정
    fw.write(writeString);
    fw.close();

    FileReader fr = new FileReader(FILE_NAME);
    StringBuilder content = new StringBuilder();
    int ch;
    while((ch=fr.read())!=-1){
        content.append((char)ch);
    }
    fr.close();
    System.out.println("read String = " + content.toString());
    }
}

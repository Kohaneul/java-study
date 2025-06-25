package io.text;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static io.text.TextConst.FILE_NAME;
import static java.nio.charset.StandardCharsets.UTF_8;

public class ReaderWriterMainV2 {
    public static void main(String[] args) throws IOException {
       String writeString = "가나다";
        System.out.println("writeString = " + writeString);
        FileOutputStream fos = new FileOutputStream(FILE_NAME);
        OutputStreamWriter osw = new OutputStreamWriter(fos, UTF_8);
        osw.write(writeString);
        osw.close();
        
        //파일에서 읽기
        FileInputStream fis = new FileInputStream(FILE_NAME);
        InputStreamReader isr = new InputStreamReader(fis, UTF_8);
        
        StringBuilder sb= new StringBuilder();
        int ch;
        while((ch = isr.read())!=-1){
            sb.append((char)ch);
        }

        isr.close();
        System.out.println("ch = " + sb.toString());
    }
}

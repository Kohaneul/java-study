package io.buffered;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import static io.buffered.BufferedConst.*;

public class CreateFileV3 {
    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream("temp/buffered.dat");
        BufferedOutputStream bos = new BufferedOutputStream(fos, BUFFER_SIZE);
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < FILE_SIZE; i++) {
            bos.write(1);
        }
        bos.close();    //연쇄적으로 FileOutputStream까지 close 됨

        long endTime = System.currentTimeMillis();

        System.out.println("File created: "+FILE_NAME);
        System.out.println("file size : "+(FILE_SIZE/1024/1024)+"MB");
        System.out.println("Time taken: "+(endTime-startTime)+"ms");
    }

}

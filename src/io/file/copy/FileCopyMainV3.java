package io.file.copy;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class FileCopyMainV3 {
    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();

        //파일에서 파일만 유용 (파일의 정보를 통해서 처리해야한다면 x)
        Path source = Path.of("temp/copy.dat");
        Path target = Path.of("temp/copy_new.dat");
        Files.copy(source,target, StandardCopyOption.REPLACE_EXISTING); //기존꺼 교체

        long endTime = System.currentTimeMillis();
        System.out.println("Time taken : "+(endTime-startTime)+" ms");


    }
}

package io.file;

import java.io.File;
import java.io.IOException;

public class OldFilePath {
    public static void main(String[] args) throws IOException {
        File file = new File("temp/..");
        System.out.println("path = "+file.getPath());

        //절대 경로(정규경로 포함)
        //ex) /Users/gohaneul/Documents/study/java/java-adv2/temp/..
        // /Users/gohaneul/Documents/study/java/java-adv2
        System.out.println("Absolute path = "+file.getAbsolutePath());

        //정규 경로 : 경로의 계산이 다 끝난 경우
        //ex) /Users/gohaneul/Documents/study/java/java-adv2

        System.out.println("Cononical path = "+file.getCanonicalPath());
        File[] files = file.listFiles();
        for (File f : files) {
            System.out.println((f.isFile()? "F" : "D")+" | "+f.getName() );
        }

    }

}

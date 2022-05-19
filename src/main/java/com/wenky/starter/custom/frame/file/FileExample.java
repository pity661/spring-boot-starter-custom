package com.wenky.starter.custom.frame.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: huwenqi
 * @email: huwenqi@olading.com
 * @create: 2022-04-29 13:40
 */
public class FileExample {
    private static final String fileName = "/Users/huwenqi/Desktop/111/22/33/file2.txt";

    public static void readLine() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            System.out.println("start");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            System.out.println("end");
        }
    }

    public static void writeLine() throws IOException {
        File file = new File(fileName);
        if (Boolean.FALSE == file.exists()) {
            file.getParentFile().mkdirs();
        }
        file.createNewFile();
        Files.write(
                new File(fileName).toPath(), Arrays.asList("aaa", "bbb"), StandardOpenOption.WRITE);
        // StandardOpenOption.APPEND
    }

    public static void main(String[] args) throws IOException {
        writeLine();
        readLine();
    }
}

package com.wenky.starter.custom.frame.file.nio;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: huwenqi
 * @email: huwenqi@olading.com
 * @create: 2022-07-01 09:47
 */
public class Write {
    public static void main(String[] args) throws IOException {
        File file = new File("/Users/huwenqi/Desktop/1/2/3/4/5/test.txt");
        // 判断文件是否存在优先创建
        if (Boolean.FALSE == file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            FileChannel fileChannel = fileOutputStream.getChannel();
            ByteBuffer allocate = ByteBuffer.allocate(1024);
            String context = "wenky";
            allocate.put(context.getBytes(StandardCharsets.UTF_8));
            allocate.flip();
            fileChannel.write(allocate);
        }
    }
}

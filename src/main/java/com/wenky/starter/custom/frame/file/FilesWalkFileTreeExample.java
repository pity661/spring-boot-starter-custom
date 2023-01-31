package com.wenky.starter.custom.frame.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: huwenqi
 * @email: huwenqi@olading.com
 * @create: 2022-07-29 11:55
 */
public class FilesWalkFileTreeExample {
    public static void main(String[] args) throws IOException {
        walkFileTree();
        //        Files.deleteIfExists(Paths.get("/Users/huwenqi/Desktop/111"));
    }

    private static void walkFileTree() throws IOException {
        File file = new File("/Users/huwenqi/Desktop/1/2/3/4/5/test.txt");
        // 判断文件是否存在优先创建
        if (Boolean.FALSE == file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        Path path = Paths.get("/Users/huwenqi/Desktop/1");
        Files.walkFileTree(
                path,
                new SimpleFileVisitor<Path>() {
                    // 访问文件夹
                    @Override
                    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs)
                            throws IOException {
                        System.out.println("正在访问：" + dir);
                        File file = new File(dir + File.separator + "a.txt");
                        if (!file.exists()) {
                            file.createNewFile();
                        }
                        return super.preVisitDirectory(dir, attrs);
                    }

                    // 访问文件夹下的每一个文件
                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
                            throws IOException {
                        System.out.println("正在访问：" + file);
                        return FileVisitResult.CONTINUE;
                    }
                });
    }
}

package com.wenky.starter.custom.frame.sftp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: huwenqi
 * @email: huwenqi@olading.com
 * @create: 2022-05-13 12:11
 */
public class FtpExample {

    public static void main(String[] args) throws IOException {
        String SERVER_CHARSET = "ISO-8859-1";
        String LOCAL_CHARSET = "GBK";

        FTPClient ftpClient = new FTPClient();
        ftpClient.connect("172.19.60.188", 21);
        ftpClient.login("ftp1", "123456");

        // https://blog.csdn.net/qq_40816848/article/details/104761863
        // 主动模式(默认) server使用tcp 21和20两个端口
        // 命令连接(客户端->服务端)：客户端从任意一个大于1024的随机端口 N 连接到ftp服务端的命令端口 tcp21(提交PORT命令)
        // 数据连接(服务端->客户端)：客户端监听自己 N+1 端口，服务端20连接指定端口，建立数据传输通道
        //        ftpClient.enterLocalActiveMode();

        // 被动模式 client 告诉 server开通一个端口来传输数据
        // 大于1024随机端口
        // 命令连接(客户端->服务端)：客户端从任意一个大于1024的随机端口 N 连接到ftp服务端的命令端口 tcp21(提交PASV命令)
        // 数据连接(客户端->服务端)：服务端提交 PORT P命令给客户端，客户端发起从本地端口N+1到服务器的端口P，传送数据
        //        ftpClient.enterLocalPassiveMode();

        // 必须配置，否则文件数据打开乱码 使用二进制传输文件数据
        ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
        if (FTPReply.isPositiveCompletion(ftpClient.sendCommand("OPTS UTF8", "ON"))) {
            LOCAL_CHARSET = "UTF-8";
        }
        ftpClient.setControlEncoding(LOCAL_CHARSET);
        // 文件目录不存在
        // 如果目录不存在需要一级级创建
        //        if (!ftpClient.changeWorkingDirectory("")) {
        //            return;
        //        }
        ftpClient.storeFile(
                new String("胡文琦".getBytes(LOCAL_CHARSET), SERVER_CHARSET),
                new FileInputStream(new File("/Users/huwenqi/Desktop/我是测试文件.txt")));

        // 能删除成功
        // ftpClient.deleteFile(new String("胡文琦".getBytes(LOCAL_CHARSET), SERVER_CHARSET));

        // 删除失败
        ftpClient.deleteFile("胡文琦");
    }
}

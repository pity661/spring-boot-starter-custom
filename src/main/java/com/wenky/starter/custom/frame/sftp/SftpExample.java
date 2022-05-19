package com.wenky.starter.custom.frame.sftp;

import com.jcraft.jsch.*;
import org.apache.commons.vfs2.*;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: huwenqi
 * @email: huwenqi@olading.com
 * @create: 2022-04-29 13:59
 */
public class SftpExample {

    public static void jSch() throws JSchException, SftpException {
        JSch jSch = new JSch();
        jSch.setKnownHosts("/Users/huwenqi/.ssh/known_hosts");
        //    ftp.url=172.19.60.188
        //    ftp.port=21
        //    ftp.userName=ftp1
        //    ftp.password=123456
        //    ftp.node-file-path=/home/ftp1
        Session session = jSch.getSession("ftp1", "172.19.60.188", 21);
        session.setPassword("123456");

        Channel sftp = session.openChannel("sftp");

        sftp.connect(50000);

        ((ChannelSftp) sftp).put("/Users/huwenqi/Desktop/file2.txt", "/home/ftp1/wenky.txt");

        ((ChannelSftp) sftp).exit();
    }

    public static void main(String[] args)
            throws JSchException, SftpException, FileSystemException {
        //        jSch();
        vfsImport();
        //        vfsDelete();
        //        System.out.println(System.getProperty("user.dir"));
    }

    public static void vfsImport() throws FileSystemException {
        FileSystemManager manager = VFS.getManager();
        // "/Users/huwenqi/Desktop/file2.txt"
        String localPath = "/var/folders/dq/csyq57xj2dg2hvzs40jf_9fw0000gn/T/XET20220428.txt";
        // "sftp://ftp1:123456@172.19.60.188/XET20220428.txt"
        String remotePath = "sftp://ftp1:123456@172.19.60.188/XET20220428.txt";
        try (FileObject local = manager.resolveFile(localPath);
                FileObject remote = manager.resolveFile(remotePath)) {
            remote.copyFrom(local, Selectors.SELECT_SELF);
        }
    }

    public static void vfsDelete() throws FileSystemException {
        try (FileObject remote =
                VFS.getManager().resolveFile("sftp://ftp1:123456@172.19.60.188/vfsFile.txt")) {
            remote.delete();
        }
    }
}

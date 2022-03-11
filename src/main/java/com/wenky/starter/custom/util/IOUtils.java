package com.wenky.starter.custom.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: huwenqi
 * @email: huwenqi@olading.com
 * @create: 2022-03-10 11:13
 */
public class IOUtils extends org.apache.commons.io.IOUtils {
    /**
     * 服务器图片预览接口
     *
     * @param inputStream
     * @param response
     */
    public void previewFile(InputStream inputStream, HttpServletResponse response)
            throws IOException {
        copy(inputStream, response.getOutputStream());
    }

    /**
     * 文件二进制数组预览
     *
     * @param bytes
     * @param response
     * @throws IOException
     */
    public void previewFile(byte[] bytes, HttpServletResponse response) throws IOException {
        previewFile(new ByteArrayInputStream(bytes), response);
    }

    /**
     * 文件链接预览
     *
     * @param fileUrl
     * @param response
     * @throws IOException
     */
    public void previewFile(String fileUrl, HttpServletResponse response) throws IOException {
        copy(new URL(fileUrl).openStream(), response.getOutputStream());
    }

    /**
     * 将base64文本转化为流
     *
     * @param content
     * @return
     */
    public InputStream fileBase64ToInputStream(String content) {
        // DatatypeConverter.parseBase64Binary decode base64 string to byte[]
        return new ByteArrayInputStream(DatatypeConverter.parseBase64Binary(content));
    }
}

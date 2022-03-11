package com.wenky.starter.custom.frame.restful;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2021-12-08 18:26
 */
@Component
public class DownloadFileExample {
    @Autowired private RestTemplate restTemplate;

    @Qualifier("restTemplateWithoutEncode")
    @Autowired
    private RestTemplate restTemplateWithoutEncode;

    public void downloadFile() throws RestClientException {

        String downloadUrl =
                "https://64-object-storage-dev.s3.cn-northwest-1.amazonaws.com.cn/%2B62/id_card_front/8e2c8b48-f8b5-42b5-b63f-cdbba9167552/id_card_front%5E_%5E8e2c8b48-f8b5-42b5-b63f-cdbba9167552%5E_%5E20200902055744utc%5E_%5Ea0c666b7648146b7a9b0526d9364a56d%5E_%5E111.jpg?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIATN2LORYALRTEZ5O3%2F20211209%2Fcn-northwest-1%2Fs3%2Faws4_request&X-Amz-Date=20211209T021913Z&X-Amz-Expires=172800&X-Amz-SignedHeaders=host&X-Amz-Signature=a253382c8a3614bd844833f2abec62bdc6c82dc9239fbeee0da5691d2e16a263";

        ResponseExtractor<File> extractor =
                response -> {
                    String targetFileName = "/Users/huwenqi/Desktop/testfile";
                    File targetFile = new File(targetFileName);
                    try (ReadableByteChannel readableByteChannel =
                            Channels.newChannel(response.getBody())) {
                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                        try (WritableByteChannel writableByteChannel =
                                new FileOutputStream(targetFile).getChannel()) {
                            // channel - to - channel
                            while (readableByteChannel.read(byteBuffer) > 0) {
                                byteBuffer.flip();
                                while (byteBuffer.hasRemaining()) {
                                    writableByteChannel.write(byteBuffer);
                                }
                                byteBuffer.clear();
                            }
                        }
                    }
                    return targetFile;
                };

        ResponseExtractor<File> extractor1 =
                response -> {
                    InputStream inputStream = response.getBody();
                    File file = new File("/Users/huwenqi/Desktop/testfile1");
                    FileOutputStream fileOut = new FileOutputStream(file);
                    /** 根据实际运行效果 设置缓冲区大小 */
                    byte[] buffer = new byte[4 * 1024];
                    int ch = 0;
                    while ((ch = inputStream.read(buffer)) != -1) {
                        fileOut.write(buffer, 0, ch);
                    }
                    inputStream.close();
                    fileOut.flush();
                    fileOut.close();
                    return file;
                };
        // 默认的encodingMode 是 EncodingMode.URI_COMPONENT
        // 对于不需要处理的连接需要将EncodingMode设置成NONE
        // 1.
        ((DefaultUriBuilderFactory) restTemplate.getUriTemplateHandler())
                .setEncodingMode(DefaultUriBuilderFactory.EncodingMode.NONE);
        // 下载文件操作
        File file = restTemplate.execute(downloadUrl, HttpMethod.GET, null, extractor1);

        // 2.
        File file1 =
                restTemplateWithoutEncode.execute(downloadUrl, HttpMethod.GET, null, extractor);
        System.out.println(file.exists());
        System.out.println(file1.exists());
    }
}

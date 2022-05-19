package com.wenky.starter.custom.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;
import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import org.apache.commons.io.IOUtils;

/**
 * @program: olading-operate-log-support
 * @description:
 * @author: huwenqi
 * @email: huwenqi@olading.com
 * @create: 2022-04-20 16:23
 */
public class BufferedRequestWrapper extends HttpServletRequestWrapper {

    private byte[] bodyBuffer;
    private Map<String, String> parameterMap = new HashMap<>();

    public BufferedRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        if (Objects.equals("multipart/form-data", request.getContentType())) {
            return;
        }
        this.bodyBuffer = IOUtils.toByteArray(request.getInputStream());
        String body = getRequestBody();
        if (StringUtils.isEmpty(body)) {
            return;
        }
        if (body.contains("=")) {
            parameterMap =
                    Arrays.stream(body.split("&"))
                            .map(single -> single.split("="))
                            .collect(
                                    Collectors.toMap(
                                            array -> array[0],
                                            array -> array.length > 1 ? array[1] : ""));
        }
    }

    public String getRequestBody() {
        return new String(bodyBuffer, StandardCharsets.UTF_8);
    }

    @Override
    public String getParameter(String name) {
        return parameterMap.getOrDefault(name, super.getParameter(name));
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        Map<String, String[]> result =
                parameterMap.entrySet().stream()
                        .collect(
                                Collectors.toMap(
                                        Map.Entry::getKey,
                                        entry -> new String[] {entry.getValue()}));
        result.putAll(super.getParameterMap());
        return result;
    }

    @Override
    public Enumeration<String> getParameterNames() {
        Iterator<String> iterator = getParameterMap().keySet().iterator();
        return new Enumeration<String>() {
            @Override
            public boolean hasMoreElements() {
                return iterator.hasNext();
            }

            @Override
            public String nextElement() {
                return iterator.next();
            }
        };
    }

    @Override
    public String[] getParameterValues(String name) {
        return getParameterMap().get(name);
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(this.getInputStream()));
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        if (this.bodyBuffer == null) {
            return super.getInputStream();
        }
        ByteArrayInputStream in = new ByteArrayInputStream(bodyBuffer);
        return new BufferedServletInputStream(in, super.getInputStream());
    }

    private static final class BufferedServletInputStream extends ServletInputStream {
        private final ServletInputStream inputStream;
        private ByteArrayInputStream byteArrayInputStream;

        public BufferedServletInputStream(
                ByteArrayInputStream byteArrayInputStream, ServletInputStream inputStream) {
            this.byteArrayInputStream = byteArrayInputStream;
            this.inputStream = inputStream;
        }

        @Override
        public int available() {
            return this.byteArrayInputStream.available();
        }

        @Override
        public int read() {
            return this.byteArrayInputStream.read();
        }

        @Override
        public int read(byte[] buf, int off, int len) {
            return this.byteArrayInputStream.read(buf, off, len);
        }

        @Override
        public boolean isFinished() {
            return inputStream.isFinished();
        }

        @Override
        public boolean isReady() {
            return inputStream.isReady();
        }

        @Override
        public void setReadListener(ReadListener listener) {
            inputStream.setReadListener(listener);
        }
    }
}

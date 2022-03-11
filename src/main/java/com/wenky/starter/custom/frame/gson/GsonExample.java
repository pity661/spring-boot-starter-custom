package com.wenky.starter.custom.frame.gson;

import static java.nio.charset.StandardCharsets.UTF_8;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

/**
 * @program: spring-boot-starter-custom
 * @description: load resource json config
 * @author: huwenqi
 * @email: huwenqi@olading.com
 * @create: 2022-03-11 11:18
 */
public class GsonExample {

    public static Map<String, ?> config;

    static {
        // 1
        InputStream inputStream =
                Thread.currentThread()
                        .getContextClassLoader()
                        .getResourceAsStream("json/resource.json");
        // 2
        //      ClassPathResource classPathResource = new ClassPathResource("json/resource.json");
        //      InputStream inputStream = null;
        //      try {
        //          inputStream = classPathResource.getInputStream();
        //      } catch (IOException e) {
        //          e.printStackTrace();
        //      }
        // 3
        //      InputStream inputStream =
        // GsonExample.class.getResourceAsStream("/json/resource.json");

        config =
                new Gson()
                        .fromJson(
                                new JsonReader(new InputStreamReader(inputStream, UTF_8)),
                                Map.class);
    }
}

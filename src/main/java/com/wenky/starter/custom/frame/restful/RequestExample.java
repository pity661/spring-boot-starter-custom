package com.wenky.starter.custom.frame.restful;

import static com.wenky.starter.custom.util.LoggerUtils.info;
import static com.wenky.starter.custom.util.LoggerUtils.logMessage;

import com.wenky.starter.custom.util.GsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2021-11-15 17:01
 */
@Component
public class RequestExample {
    @Autowired private RestTemplate restTemplate;

    public void getMapRequest() {
        // GsonUtils.obj2Map 会把整形变为浮点型要注意一下接口的接收类型
        String url = "http://127.0.0.1:8080/get?phone={phone}&name={name}";
        ResponseEntity response =
                restTemplate.exchange(
                        url,
                        HttpMethod.GET,
                        null,
                        String.class,
                        GsonUtils.obj2Map(GetParam.newInstance()));
        info(response.getBody());
    }

    // primary
    public void getUrlRequest() {
        String url = String.format("http://127.0.0.1:8080/get?phone=%s&name=%s", "10086", "wenky");
        ResponseEntity response = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
        info(response.getBody());
    }

    public void postRequest() {
        // 1、map
        //    Map<String, Object> paramMap = new HashMap<String, Object>() {{
        //      put("phone", 10086);
        //      put("name", "wenky");
        //    }};
        //    HttpEntity<Map<String, Object>> httpEntity = new HttpEntity<>(paramMap);

        // 2、custom object
        //    HttpEntity<PostParam> httpEntity = new HttpEntity<>(PostParam.newInstance());

        // 3、string 必须设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> httpEntity =
                new HttpEntity<>(GsonUtils.toString(PostParam.newInstance()), headers);
        ResponseEntity response =
                restTemplate.exchange(
                        "http://127.0.0.1:8080/post", HttpMethod.POST, httpEntity, String.class);
        info(response.getBody());
    }

    public void httpStatus(RequestURIEnum requestURI) {
        try {
            ResponseEntity response =
                    restTemplate.exchange(
                            "http://127.0.0.1:8080/" + requestURI.getRelativeUrl(),
                            HttpMethod.GET,
                            null,
                            String.class);
            info(response.getBody());
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            // 400 40X client error
            // 500 501 502 503 504 server error
            HttpStatus httpStatus = e.getStatusCode();
            // error http code
            Integer errorHttpCode = httpStatus.value();
            // response headers
            HttpHeaders headers = e.getResponseHeaders();
            String headersStringValue = headers.toString();
            // response body string value
            String responseBodyStringValue = e.getResponseBodyAsString();
            info(
                    logMessage(
                            e.getMessage(),
                            errorHttpCode,
                            headersStringValue,
                            responseBodyStringValue));
        } catch (ResourceAccessException e) {
            // Read timed out
            info(e.getMessage());
        } catch (Exception e) {
            info(e.getMessage());
        }
    }
}

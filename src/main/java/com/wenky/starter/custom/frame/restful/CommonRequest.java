package com.wenky.starter.custom.frame.restful;

import static com.wenky.starter.custom.util.LoggerUtils.info;
import static com.wenky.starter.custom.util.LoggerUtils.logMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
public class CommonRequest {
  @Autowired private RestTemplate restTemplate;

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
      info(logMessage(e.getMessage(), errorHttpCode, headersStringValue, responseBodyStringValue));
    } catch (ResourceAccessException e) {
      // Read timed out
      info(e.getMessage());
    } catch (Exception e) {
      info(e.getMessage());
    }
  }
}

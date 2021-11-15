package com.wenky.starter.custom.frame.restful;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CommonRequestTest {

  @Autowired private CommonRequest commonRequest;

  @Test
  public void test() {
    commonRequest.httpStatus(RequestURIEnum.CREATED);
    // 302 响应200
    //        commonRequest.httpStatus(RequestURIEnum.FOUND);
    // 400 HttpClientErrorException
    //            commonRequest.httpStatus(RequestURIEnum.BAD_REQUEST);
    // 401 HttpClientErrorException
    //        commonRequest.httpStatus(RequestURIEnum.UNAUTHORIZED);
    // 402 HttpClientErrorException
    //    commonRequest.httpStatus(RequestURIEnum.PAYMENT_REQUIRED);
    // 404 HttpClientErrorException
    //            commonRequest.httpStatus(RequestURIEnum.NOT_FOUND);
    // 500 HttpServerErrorException
    //    commonRequest.httpStatus(RequestURIEnum.INTERNAL_SERVER_ERROR);
  }
}

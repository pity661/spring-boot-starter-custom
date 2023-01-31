package com.wenky.starter.custom.frame.restful;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CommonRequestTest {

    @Autowired private RequestExample requestExample;

    @Test
    public void test() {
        //      commonRequest.httpStatus(RequestURIEnum.OK);
        // 201
        //    commonRequest.httpStatus(RequestURIEnum.CREATED);
        // 302 响应200
        requestExample.httpStatus(RequestURIEnum.FOUND);
        // 400 HttpClientErrorException
        //            commonRequest.httpStatus(RequestURIEnum.BAD_REQUEST);
        // 401 HttpClientErrorException
        //            commonRequest.httpStatus(RequestURIEnum.UNAUTHORIZED);
        // 402 HttpClientErrorException
        //    commonRequest.httpStatus(RequestURIEnum.PAYMENT_REQUIRED);
        // 404 HttpClientErrorException
        //            commonRequest.httpStatus(RequestURIEnum.NOT_FOUND);
        // 500 HttpServerErrorException
        //    commonRequest.httpStatus(RequestURIEnum.INTERNAL_SERVER_ERROR);
    }

    @Test
    public void postTest() {
        requestExample.postRequest();
    }

    @Test
    public void getUrlTest() {
        requestExample.getUrlRequest();
    }

    @Test
    public void getMapTest() {
        requestExample.getMapRequest();
    }

    @Test
    public void formPostTest() {
        requestExample.formPostRequest();
    }
}

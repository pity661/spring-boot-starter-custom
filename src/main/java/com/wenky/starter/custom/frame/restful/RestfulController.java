package com.wenky.starter.custom.frame.restful;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2021-11-15 17:13
 */
@Controller
public class RestfulController {
  @Autowired private HttpServletRequest request;
  @Autowired private HttpServletResponse response;

  @RequestMapping("/200")
  public ResponseEntity<String> OK() {
    return ResponseEntity.ok("message");
  }

  @RequestMapping("/201")
  public ResponseEntity<String> CREATED() {
    return new ResponseEntity("message", HttpStatus.CREATED);
  }

  @RequestMapping("/302")
  public void FOUND() throws IOException {
    // http://127.0.0.1:8080
    String basePath =
        request.getScheme()
            + "://"
            + request.getServerName()
            + ":"
            + request.getServerPort()
            + request.getContextPath();
    response.sendRedirect(basePath + "/200");
  }

  @RequestMapping("/400")
  public ResponseEntity<String> BAD_REQUEST() {
    return new ResponseEntity("message", HttpStatus.BAD_REQUEST);
  }

  @RequestMapping("/401")
  public ResponseEntity<String> UNAUTHORIZED() {
    return new ResponseEntity("message", HttpStatus.UNAUTHORIZED);
  }

  @RequestMapping("/402")
  public ResponseEntity<String> PAYMENT_REQUIRED() {
    return new ResponseEntity("message", HttpStatus.PAYMENT_REQUIRED);
  }

  @RequestMapping("/404")
  public ResponseEntity<String> NOT_FOUND() {
    return new ResponseEntity("message", HttpStatus.NOT_FOUND);
  }

  @RequestMapping("/500")
  public ResponseEntity<String> INTERNAL_SERVER_ERROR() {
    return new ResponseEntity("message", HttpStatus.INTERNAL_SERVER_ERROR);
  }
}

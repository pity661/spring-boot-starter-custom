package com.wenky.starter.custom.frame.restful;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping("/302")
    public void FOUND(HttpServletRequest request, HttpServletResponse response) throws IOException {
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

    @RequestMapping("/{value}")
    public ResponseEntity<String> CREATED(@PathVariable Integer value) {
        return new ResponseEntity("message", HttpStatus.valueOf(value));
    }

    @PostMapping("/post")
    public ResponseEntity post(@RequestBody PostParam param) {
        return ResponseEntity.ok(param);
    }

    @PostMapping("/form/post")
    public ResponseEntity formPost(PostParam param) {
        return ResponseEntity.ok(param);
    }

    @GetMapping("/get")
    public ResponseEntity get(GetParam param) {
        return ResponseEntity.ok(param);
    }
}

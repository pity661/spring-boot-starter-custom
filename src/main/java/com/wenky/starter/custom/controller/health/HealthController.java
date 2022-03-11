package com.wenky.starter.custom.controller.health;

import com.wenky.starter.custom.util.LoggerUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2020-12-04 18:54
 */
// 不需要@Controller注解也能正常工作
@Controller
// 1.从wenky.health.path读取 2.从health.path读取 3.默认值/health
@RequestMapping("${wenky.health.path:${health.path:/health}}")
public class HealthController {
    private HealthProperties healthProperties;

    public HealthController(HealthProperties healthProperties) {
        LoggerUtils.construct();
        this.healthProperties = healthProperties;
    }

    @RequestMapping
    public ResponseEntity<String> health(HttpServletRequest request) {
        if (healthProperties.getOnline()) {
            return ResponseEntity.ok("ok");
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping("/offline")
    public ResponseEntity<String> offline() {
        healthProperties.setOnline(false);
        return ResponseEntity.ok("service is offline");
    }

    @RequestMapping("/online")
    public ResponseEntity<String> online() {
        healthProperties.setOnline(true);
        return ResponseEntity.ok("service is online");
    }
}

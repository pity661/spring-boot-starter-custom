package com.wenky.starter.custom.controller.switch1;

import com.wenky.starter.custom.util.LoggerUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2021-01-15 11:17
 */
// 不需要@Controller注解也能正常工作
@Controller
@RequestMapping("/switch")
public class SwitchController {
  private SwitchProperties switchProperties;

  public SwitchController(SwitchProperties switchProperties) {
    LoggerUtils.construct();
    this.switchProperties = switchProperties;
  }

  @GetMapping("/change/{switchName}")
  public ResponseEntity<String> switchChange(@PathVariable String switchName) {
    Boolean result = switchProperties.switchChange(switchName);
    if (result == null) {
      String body =
          String.format(
              "Switch not find:[%s]. Switch list:%s",
              switchName, switchProperties.getAllSwitchNames());
      LoggerUtils.info(body);
      return ResponseEntity.ok(body);
    }
    String body = String.format("切换后开关状态,[%s:%s].", switchName, result);
    LoggerUtils.info(body);
    return ResponseEntity.ok(body);
  }
}

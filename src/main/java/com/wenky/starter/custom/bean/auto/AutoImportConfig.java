package com.wenky.starter.custom.bean.auto;

import com.wenky.starter.custom.holder.SpringContextHolder;
import com.wenky.starter.custom.util.LoggerUtils;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2020-12-04 16:29
 */
@AutoConfigureAfter(SpringContextHolder.class)
public class AutoImportConfig {
  AutoImportConfig() {
    LoggerUtils.construct();
  }
}

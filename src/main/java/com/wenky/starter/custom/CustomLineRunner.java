package com.wenky.starter.custom;

import com.wenky.starter.custom.holder.SpringContextHolder;
import java.util.Map;
import java.util.Set;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: huwenqi
 * @email: huwenqi@olading.com
 * @create: 2022-03-11 11:24
 */
@Component
public class CustomLineRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        RequestMappingHandlerMapping requestHandler =
                SpringContextHolder.getBean(RequestMappingHandlerMapping.class);
        Map<RequestMappingInfo, HandlerMethod> map = requestHandler.getHandlerMethods();
        RequestMappingInfo requestMappingInfo = map.keySet().iterator().next();
        // methods
        Set<RequestMethod> methods = requestMappingInfo.getMethodsCondition().getMethods();
        // patterns
        Set<String> patterns = requestMappingInfo.getPatternsCondition().getPatterns();

        requestMappingInfo.toString();
        System.out.println(map.size());
    }
}

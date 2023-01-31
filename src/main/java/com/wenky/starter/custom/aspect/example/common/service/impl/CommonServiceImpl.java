package com.wenky.starter.custom.aspect.example.common.service.impl;

import com.wenky.starter.custom.aspect.example.annotation.bean.AnnotationBean;
import com.wenky.starter.custom.aspect.example.common.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2021-07-08 15:28
 */
@Order(0)
@Service
public class CommonServiceImpl implements CommonService {

    @Autowired private AnnotationBean annotationBean;

    @Override
    public void handle() {
        System.out.println("handle");
    }
}

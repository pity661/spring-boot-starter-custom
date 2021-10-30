package com.wenky.starter.custom.aspect.example.common;

import static org.junit.jupiter.api.Assertions.*;

import com.wenky.starter.custom.aspect.example.common.bean.CommonBean;
import com.wenky.starter.custom.aspect.example.common.service.CommonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CommonAspectTest {
  // 默认cglib代理
  @Autowired private CommonService commonService;
  // 使用jdk代理模式生成的bean是实现commonService接口生成的proxy类，不是CommonServiceImpl类型，注入失败
  // 但是cglib代理模式是通过继承原对象生成的，可以注入
  //  @Autowired private CommonServiceImpl commonServiceImpl;

  // 只能通过cglib代理
  @Autowired private CommonBean commonBean;

  @Test
  public void serviceHandleTest() {
    // cglib代理
    // targetServiceImplPointcut
    // targetServicePointcut
    // thisServiceImplPointcut
    // thisServicePointcut
    // handle

    // jdk代理，接口实现方式可以使用jdk代理
    // targetServiceImplPointcut
    // targetServicePointcut
    // thisServicePointcut
    // handle
    commonService.handle();

    // commonServiceImpl.handle();
  }

  @Test
  public void beanHandleTest() {
    // 只能通过cglib代理
    // targetBeanPointcut
    // thisBeanPointcut
    // handle
    commonBean.handle();
  }
}

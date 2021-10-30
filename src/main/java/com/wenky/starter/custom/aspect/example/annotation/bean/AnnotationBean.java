package com.wenky.starter.custom.aspect.example.annotation.bean;

import com.wenky.starter.custom.aspect.example.annotation.Target;
import com.wenky.starter.custom.aspect.example.annotation.TargetA;
import com.wenky.starter.custom.aspect.example.annotation.TargetB;
import org.springframework.stereotype.Component;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2021-07-07 15:45
 */
@Component
public class AnnotationBean {
  public void handle() {
    System.out.println("handle");
  }

  @Target
  public void annotationHandle() {
    System.out.println("annotationHandle");
  }

  @TargetA
  @TargetB
  public void doubleAnnotationHandle() {
    System.out.println("doubleAnnotationHandle");
  }
}

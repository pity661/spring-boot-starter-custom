package com.wenky.starter.custom.aspect.example.annotation;

import com.wenky.starter.custom.aspect.example.annotation.bean.AnnotationBean;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AnnotationAspectTest {
    @Autowired private AnnotationBean annotationBean;

    @Test
    public void handleTest() {
        // executeClass
        // handle
        annotationBean.handle();
        // executeClass
        // executeMethod
        // annotationHandle
        annotationBean.annotationHandle();
    }

    @Test
    public void doubleAnnotationHandleTest() {
        // executeClass
        // executeDouble
        // executeDouble1
        // doubleAnnotationHandle
        annotationBean.doubleAnnotationHandle();
    }
}

package com.wenky.starter.custom.aspect.example.execution;

import com.wenky.starter.custom.aspect.example.execution.bean.ExecutionBean;
import com.wenky.starter.custom.aspect.example.execution.service.ExecutionService;
import com.wenky.starter.custom.aspect.example.execution.service.impl.ExecutionServiceAnnotationImpl;
import com.wenky.starter.custom.aspect.example.execution.service.impl.ExecutionServiceImpl;
import com.wenky.starter.custom.aspect.example.execution.service.impl.ExecutionServiceSerializableImpl;
import javax.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ExecutionAspectTest {
  @Autowired private ExecutionService executionService;
  @Autowired private ExecutionServiceImpl executionServiceImpl;
  @Autowired private ExecutionServiceSerializableImpl executionServiceSerializableImpl;

  @Resource(name = "executionServiceAnnotationImpl")
  private ExecutionServiceAnnotationImpl executionServiceAnnotationImpl;

  @Autowired private ExecutionBean executionBean;

  @Test
  public void handleTest() {
    // execute
    // executePlus
    // handle
    executionService.handle();
  }

  @Test
  public void handleThrowsTest() {
    // 实现接口的子类中单独加的方法
    // executePlus
    // executeThrows
    // handleThrows
    executionServiceImpl.handleThrows();
  }

  @Test
  public void handlePlusTest() {
    // 实现接口的子类中单独加的方法
    // executePlus
    // handlePlus
    executionServiceImpl.handlePlus();
  }

  @Test
  public void handleAnnotationTest() {
    // 实现接口的子类中单独加的方法
    // executeAnnotation
    // executePlus
    // handleAnnotation
    executionServiceImpl.handleAnnotation();
  }

  @Test
  public void handleSerializableTest() {
    // execute
    // executePlus
    // executeSerializable
    // handle
    executionServiceSerializableImpl.handle();
  }

  @Test
  public void handleAnnotationTest1() {
    // execute
    // executeAnnotation
    // executePlus
    // handle
    executionServiceAnnotationImpl.handle();
  }

  @Test
  public void executeClassAnnotationTest() {
    // executeClassAnnotation
    // handle
    executionBean.handle();
  }

  @Test
  public void executeParamAnnotationTest() {
    // executeClassAnnotation
    // executeParamAnnotation
    // handle, a:[a], b:[b]
    executionBean.handle("a", "b");
  }

  @Test
  public void executeParamWithAnnotationTest() {
    // executeClassAnnotation
    // executeParamWithAnnotation
    // handle
    ExecutionBean.Param param = new ExecutionBean.Param();
    executionBean.handle(param);

    // executeClassAnnotation
    // handle
    ExecutionBean.Param1 param1 = new ExecutionBean.Param1();
    executionBean.handle(param1);
  }
}

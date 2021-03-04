package com.wenky.starter.custom.aspect.controller.verify;

import com.wenky.starter.custom.aspect.controller.ControllerAspect;
import com.wenky.starter.custom.controller.support.VerifyException;
import java.lang.reflect.Parameter;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.core.annotation.Order;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2021-03-03 15:35
 */
@Aspect
@AutoConfigureAfter(ControllerAspect.class)
@Order(Integer.MIN_VALUE - 1)
public class ControllerParamVerifyAspect {

  @Pointcut(
      "bean(*Controller) && args(com.wenky.starter.custom.aspect.controller.verify.VerifyInterface)")
  public void execute() {}

  // 接口请求参数校验切面
  @Around("execute()")
  public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
    Parameter[] parameters =
        ((MethodSignature) joinPoint.getSignature()).getMethod().getParameters();
    // 打印参数注解信息
    //    LoggerUtils.info(
    //        Stream.of(parameters[0])
    //            .map(Parameter::getAnnotations)
    //            .flatMap(Stream::of)
    //            .map(Annotation::toString)
    //            .collect(Collectors.joining(",")));
    if (parameters[0].isAnnotationPresent(NotVerify.class)) {
      // 不做参数校验直接返回
      return joinPoint.proceed();
    }
    // 参数检验处理
    if (((VerifyInterface) (joinPoint.getArgs()[0])).verify()) {
      return joinPoint.proceed();
    }
    throw new VerifyException("param verify fail!");
  }
}

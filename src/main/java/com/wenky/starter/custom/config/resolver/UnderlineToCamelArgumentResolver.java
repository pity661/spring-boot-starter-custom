package com.wenky.starter.custom.config.resolver;

import com.wenky.starter.custom.config.annotation.LineToCamel;
import com.wenky.starter.custom.util.LoggerUtils;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2021-03-03 10:12
 */
public class UnderlineToCamelArgumentResolver implements HandlerMethodArgumentResolver {

    private static Pattern pattern = Pattern.compile("_(\\w)");

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(LineToCamel.class);
    }

    @Override
    public Object resolveArgument(
            MethodParameter parameter,
            ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory)
            throws Exception {
        return handleParameterNames(parameter, webRequest);
    }

    private Object handleParameterNames(MethodParameter parameter, NativeWebRequest webRequest)
            throws IllegalAccessException, InstantiationException {
        Object obj = parameter.getParameterType().newInstance();
        BeanWrapper wrapper = PropertyAccessorFactory.forBeanPropertyAccess(obj);
        Iterator<String> paramNames = webRequest.getParameterNames();
        while (paramNames.hasNext()) {
            String paramName = paramNames.next();
            Object o = webRequest.getParameter(paramName);
            try {
                wrapper.setPropertyValue(underLineToCamel(paramName), o);
            } catch (BeansException e) {
                LoggerUtils.exception(e);
            }
        }
        return obj;
    }

    private static String underLineToCamel(String source) {
        Matcher matcher = pattern.matcher(source);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(underLineToCamel("aaa_bbb_ccc"));
    }
}

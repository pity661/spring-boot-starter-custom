package com.wenky.starter.custom.config.filter;

import com.wenky.starter.custom.util.BufferedRequestWrapper;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: huwenqi
 * @email: huwenqi@olading.com
 * @create: 2022-04-21 17:47
 */
@Component
public class RequestFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        chain.doFilter(new BufferedRequestWrapper((HttpServletRequest) request), response);
    }
}

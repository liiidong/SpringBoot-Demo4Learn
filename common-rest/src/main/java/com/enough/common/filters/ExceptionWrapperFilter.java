package com.supermap.gaf.rest.filters;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.bouncycastle.cert.ocsp.Req;

import com.supermap.gaf.rest.utils.UrlUtil;

public class ExceptionWrapperFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
       // 是否已经放有异常栈, 避免循环
    }

    @Override
    public void destroy() {

    }
}

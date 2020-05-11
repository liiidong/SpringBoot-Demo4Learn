package com.supermap.gaf.rest.filters;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.MediaType;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.StringBuilderWriter;
import org.slf4j.Logger;

import com.supermap.gaf.commontypes.AccessRecord;
import com.supermap.gaf.rest.utils.HttpUtil;
import com.supermap.gaf.shiro.SecurityUtilsExt;
import com.supermap.gaf.shiro.commontypes.ShiroUser;
import com.supermap.gaf.utils.LogUtil;

public class AccessLogFilter implements Filter{
    
    private static final String NOT_LOGIN_USER = "anyone";
    
    private static final Logger accesslogger = LogUtil.getAccessLogger();
    
    private static final Logger logger = LogUtil.getLocLogger(AccessLogFilter.class);
 
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }
 
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        try {
            /**
             * 判断请求
             *  get请求不用多余处理，获取请求参数后放行。
             *  非get请求，判断context-type为application-json的情况，记录请求体
             */
            AccessRecord accessInfo = new AccessRecord();
            accessInfo.setAccessTime(new Date());
            accessInfo.setHttpMethod(httpServletRequest.getMethod());
            accessInfo.setRemoteAddress(HttpUtil.getRequestIp(httpServletRequest));
            accessInfo.setUserName(getCurrentUserName());
            accessInfo.setRequestUrl(httpServletRequest.getRequestURI());
            accessInfo.setRequestParams(httpServletRequest.getQueryString());
            Map<String,String> headers = HttpUtil.getRequestHeaders(httpServletRequest);
            accessInfo.setRequestHeaders(
                    headers.entrySet().stream()
                    .map(item -> item.getKey() + ":" + item.getValue())
                    .collect(Collectors.joining(", ", "[", "]"))
                    );
            
            ServletRequest requestWrapper = null;    
            if(!HttpMethod.GET.equals(httpServletRequest.getMethod())){
                String contentType = httpServletRequest.getHeader("Content-Type") ; 
                if(contentType != null && contentType.contains(MediaType.APPLICATION_JSON)) { 
                    requestWrapper = new PnRequestWrapper(httpServletRequest);    
                    StringBuilder sw = new StringBuilder ();
                    try (InputStream ins = requestWrapper.getInputStream();
                            Writer writer = new StringBuilderWriter(sw);){
                        IOUtils.copy(ins, writer, StandardCharsets.UTF_8);
                        writer.flush();
                    } catch (IOException e) {
                        logger.debug(e.getMessage(), e);
                    }
                    accessInfo.setRequestContent(sw.toString());
                }    
            }
            if(requestWrapper == null) { 
                chain.doFilter(request, response); 
            }else{
                chain.doFilter(requestWrapper, response);
            }
            accessInfo.setRequestDuration(new Date().getTime() - accessInfo.getAccessTime().getTime());
            accessInfo.setResponseCode(((HttpServletResponse)response).getStatus());
            accesslogger.info(accessInfo.toLogString());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
    
    @Override
    public void destroy() {
    }
    
    public String getCurrentUserName() {
        try {
            ShiroUser user = SecurityUtilsExt.getUser();
            if(user != null) {
                return user.getUsername(); 
            }    
        }catch(Exception ex) {
            accesslogger.debug(ex.getMessage(), ex);
        }
        return NOT_LOGIN_USER;
        
    }
    
    public static class PnRequestWrapper extends HttpServletRequestWrapper {
        protected final byte[] body; // 报文  
        public PnRequestWrapper(HttpServletRequest request) throws IOException {  
            super(request);
            body = InputStreamTOByte(request);  
        }  
          
        @Override  
        public BufferedReader getReader() throws IOException {  
            return new BufferedReader(new InputStreamReader(getInputStream()));  
        }  
          
        @Override  
        public ServletInputStream getInputStream() throws IOException {  
            final ByteArrayInputStream bais = new ByteArrayInputStream(body);  
            return new ServletInputStream() {  
                @Override  
                public int read() throws IOException {  
                    return bais.read();  
                }  
            };  
        }
        // 将InputStream转换成byte数组  
        public static byte[] InputStreamTOByte(HttpServletRequest request) throws IOException {
            try (ByteArrayOutputStream outStream = new ByteArrayOutputStream();
                    InputStream in = request.getInputStream();){
                IOUtils.copy(in, outStream);
                return outStream.toByteArray();
            }catch (Exception e) {
                logger.debug(e.getMessage(), e);
            }
            return null;
        }  
    }
    
}

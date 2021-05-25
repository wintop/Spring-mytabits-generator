package net.hyjuki.smgen.config;

import net.hyjuki.smgen.base.utils.DataContants;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class GlobalCorsConfig implements Filter {
    private static String ACCESS_CONTROL_ALLOW_ORIGIN = "Access-Control-Allow-Origin";
    private static String ACCESS_CONTROL_ALLOW_METHODS = "Access-Control-Allow-Methods";
    private static String ACCESS_CONTROL_MAX_AGE = "Access-Control-Max-Age";
    private static String ACCESS_CONTROL_ALLOW_HEADERS = "Access-Control-Allow-Headers";
    private static String ACCESS_CONTROL_REQUEST_HEADERS= "Access-Control-Request-Headers";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        System.out.println("===h_token===" + httpRequest.getHeader(DataContants.AUTHORIZATION));
        httpResponse.addHeader(ACCESS_CONTROL_ALLOW_ORIGIN, httpRequest.getHeader("Origin"));
        httpResponse.addHeader(ACCESS_CONTROL_ALLOW_METHODS, httpRequest.getMethod());
        httpResponse.addHeader("Access-Control-Allow-Credentials", "true");
        httpResponse.addHeader(ACCESS_CONTROL_MAX_AGE, "3600");
        httpResponse.addHeader(ACCESS_CONTROL_ALLOW_HEADERS,
                httpRequest.getHeader(ACCESS_CONTROL_REQUEST_HEADERS));
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
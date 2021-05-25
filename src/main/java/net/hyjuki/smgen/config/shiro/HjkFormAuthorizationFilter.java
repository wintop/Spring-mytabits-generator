package net.hyjuki.smgen.config.shiro;

import com.alibaba.fastjson.JSON;
import net.hyjuki.smgen.base.common.HjkResult;
import net.hyjuki.smgen.base.common.MessageData;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class HjkFormAuthorizationFilter extends FormAuthenticationFilter {
    private static final Logger logger = LoggerFactory.getLogger(HjkFormAuthorizationFilter.class);

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object o) {
        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
        System.out.println("====HjkFormAuthorizationFilter--isAccessAllowed--url--"
                + ((HttpServletRequest)request).getRequestURL() + "--METHOD: "
                + httpServletRequest.getMethod());
        if ("OPTIONS".equals(httpServletRequest.getMethod())) {
            return true;
        }
        return super.isAccessAllowed(request, response, o);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response)
            throws Exception {
        HttpServletResponse res = (HttpServletResponse)response;
        res.setHeader("Access-Control-Allow-Origin", "*");
        System.out.println("===-header.origin====" + ((HttpServletRequest)request).getHeader("origin"));
//        res.addHeader("Access-Control-Allow-Origin", ((HttpServletRequest)request).getHeader("origin"));
        res.addHeader("Access-Control-Allow-Credentials", "true");
        res.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
        res.setStatus(HttpServletResponse.SC_OK);
        res.setCharacterEncoding("UTF-8");
        PrintWriter writer = res.getWriter();

        writer.write(JSON.toJSONString(HjkResult.fail(MessageData.ERROR_NO_USER_LOGIN)));
        writer.close();
        return false;
    }
}

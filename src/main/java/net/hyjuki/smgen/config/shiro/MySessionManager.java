package net.hyjuki.smgen.config.shiro;

import net.hyjuki.smgen.base.utils.DataContants;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;

public class MySessionManager extends DefaultWebSessionManager {
    private static final String REFERENCED_SESSION_ID_SOURCE = "Stateless request";

    public MySessionManager() {
        super();
    }

    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        //从前端ajax headers中获取这个参数用来判断授权
        String id = WebUtils.toHttp(request).getHeader(DataContants.AUTHORIZATION);
        System.out.println("==pathInfo==" + WebUtils.toHttp(request).getRequestURL());
        System.out.println("--SessionManager--sessionId--" +DataContants. AUTHORIZATION + "--" + id);

        if (StringUtils.hasLength(id)) {
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, REFERENCED_SESSION_ID_SOURCE);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, id);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
            return id;
        } else {
            //从前端的cookie中取值
            Serializable sessionId = super.getSessionId(request, response);
            System.out.println("--SessionManager--no " + DataContants.AUTHORIZATION + " --default: " + sessionId);
            return sessionId;
        }
    }
}

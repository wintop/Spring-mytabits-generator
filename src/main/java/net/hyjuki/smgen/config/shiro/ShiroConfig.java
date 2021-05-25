package net.hyjuki.smgen.config.shiro;

import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private int port;
    @Value("${spring.redis.timeout}")
    private int timeout;
    @Value("${spring.redis.password}")
    private String password;

    @Value("${server.servlet.session.timeout}")
    private long sessionTimeout;

    /**
     * 权限规则配置 // @Qualifier("securityManager") SecurityManager securityManager
     **/
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean() {
        System.out.println("--1--ShiroConfig--------shiroFilterFactoryBean--");
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager());

        Map<String, Filter> filters = shiroFilterFactoryBean.getFilters();
        filters.put("authc", new HjkFormAuthorizationFilter());

        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();

        filterChainDefinitionMap.put("/user/logout", "anon");
        filterChainDefinitionMap.put("/user/login","anon");
        filterChainDefinitionMap.put("/page/**","anon");

        filterChainDefinitionMap.put("/**", "authc");

        shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    /**
     * shiro安全管理器（权限验证核心配置）
     **/
    @Bean
    public DefaultWebSecurityManager securityManager() {
        System.out.println("--2--ShiroConfig--------securityManager--");
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myShiroRealm());
        // 自定义session管理 使用redis
        securityManager.setSessionManager(sessionManager());
        // 自定义缓存实现 使用redis
        securityManager.setCacheManager(shiroCacheManager());

        return securityManager;
    }

    /**
     * 会话管理 自定义sessionManager
     **/
    @Bean
    public SessionManager sessionManager() {
        System.out.println("---4---ShiroConfig--------sessionManager-----------");
        MySessionManager sessionManager = new MySessionManager();
        // 取消登陆跳转URL后面的jsessionid参数
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        sessionManager.setSessionDAO(sessionDAO());
        // sessionManager.setGlobalSessionTimeout(10800000L);// -1不过期，单位ms
        sessionManager.setGlobalSessionTimeout(sessionTimeout*1000);// -1不过期，单位ms
        return sessionManager;
    }

    /**
     * 使用的是shiro-redis开源插件 缓存依赖
     **/
    @Bean
    public RedisManager redisManager() {
        System.out.println("---6---ShiroConfig--------redisManager--");
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(host+":"+port);
        redisManager.setTimeout(timeout);
//        redisManager.setPassword(password);
        return redisManager;
    }

    /**
     * 使用的是shiro-redis开源插件 session持久化
     **/
    @Bean
    public RedisSessionDAO sessionDAO() {
        System.out.println("---5---ShiroConfig--------sessionDAO--------------------");
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        return redisSessionDAO;
    }

    /**
     * 缓存管理
     **/
    @Bean
    public CacheManager shiroCacheManager() {
        System.out.println("---7---ShiroConfig--------shiroCacheManager--");
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        return redisCacheManager;
    }

    /**
     * 权限管理
     **/
    @Bean
    public HyShiroRealm myShiroRealm() {
        System.out.println("--3--ShiroConfig--------myShiroRealm--");
        return new HyShiroRealm();
    }

}

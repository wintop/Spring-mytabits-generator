package net.hyjuki.smgen.config.shiro;

import net.hyjuki.smgen.model.UserAccount;
import net.hyjuki.smgen.service.UserAccountService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 权限验证
 */
public class HyShiroRealm extends AuthorizingRealm {
    private Logger logger= LoggerFactory.getLogger(HyShiroRealm.class);

    // 人员登录帐户的Service
    @Autowired
    private UserAccountService accountService;

    /**
     * 获取权限信息
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
//        OrgUser user = (OrgUser) principalCollection.getPrimaryPrincipal();
//        System.out.println("========================" + user);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//        info.addStringPermissions();
        return info;
    }

    /**
     * 获取认证信息
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        UserAccount currentUser = accountService.login(token.getUsername());
        if(null == currentUser){
            throw new AuthenticationException("账户不存在");
        }

        if(!currentUser.getPassword().equals(new String(token.getPassword()))){
            throw new IncorrectCredentialsException("账户密码不正确");
        }

        if(currentUser.getStatus() == 2){
            throw new LockedAccountException("账户已冻结");
        }

        logger.info("======已授权" + currentUser + "====");
        return new SimpleAuthenticationInfo(currentUser, currentUser.getPassword(), currentUser.getUserName());
    }
}

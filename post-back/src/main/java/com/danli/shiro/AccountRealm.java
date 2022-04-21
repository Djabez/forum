package com.danli.shiro;

import cn.hutool.core.bean.BeanUtil;
import com.danli.entity.User;
import com.danli.service.UserService;
import com.danli.util.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Login authentication and authorization
 *
 * @author Mingyu.jin
 * @date  2022/3/29
 */
@Slf4j
@Component
public class AccountRealm extends AuthorizingRealm {
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    UserService userService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }


    /**
     * to grant authorization
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        log.info("use doGetAuthorizationInfo method");
//        String username = JwtUtil.getUsername(principalCollection.toString());
        log.info(principals.toString());
//        log.info(":" + username);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        AccountProfile accountProfile = (AccountProfile)principals.getPrimaryPrincipal();
        String[] roles = accountProfile.getRole().split(",");
        log.info("roles");
        for(String role : roles){
            info.addRole(role);
            if(role.equals("role_root")){
                info.addStringPermission("user:create");
                info.addStringPermission("user:update");
                info.addStringPermission("user:read");
                info.addStringPermission("user:delete");
            }
            else if( role.equals("role_admin")){
                info.addStringPermission("user:read");
                info.addStringPermission("user:create");
                info.addStringPermission("user:update");
            }
            else if( role.equals("role_user")){
                info.addStringPermission("user:read");
                info.addStringPermission("user:create");
                info.addStringPermission("user:update");
                info.addStringPermission("user:delete");
            }
            else if(role.equals("role_guest")){
                info.addStringPermission("user:read");
            }
            else if (role.equals("")){
                info.addStringPermission("user:read");


            }
        }


        return info;

    }


    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        JwtToken jwt = (JwtToken) token;
        log.info("jwt----------------->{}", jwt);
        String userId = (String) jwtUtils.getClaimByToken((String) jwt.getPrincipal()).get("userId");
        String username = (String) jwtUtils.getClaimByToken((String) jwt.getPrincipal()).get("username");
        User user = userService.getById(Long.parseLong(userId));
        if (user == null) {
            throw new UnknownAccountException("account do not exist！");
        }
        if (user.getStatus() == -1) {
            throw new LockedAccountException("account！");
        }
        if(!user.getUsername().equals(username)){
            throw new UnknownAccountException("userId and username inconsistent");
        }
        AccountProfile profile = new AccountProfile();
        //know principals
        BeanUtil.copyProperties(user, profile);
        log.info("profile----------------->{}", profile.toString());
        return new SimpleAuthenticationInfo(profile, jwt.getCredentials(), getName());
    }
}


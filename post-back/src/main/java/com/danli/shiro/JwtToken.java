package com.danli.shiro;
//hhhhhhhhhhhhhhhhhhhhhh
import org.apache.shiro.authc.AuthenticationToken;

/**
 * Jwt
 *
 * @author Mingyu.Jin
 * @date  2022/3/29
 */
public class JwtToken implements AuthenticationToken {
    private String token;

    public JwtToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}


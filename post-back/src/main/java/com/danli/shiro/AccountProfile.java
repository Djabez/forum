package com.danli.shiro;

import lombok.Data;

import java.io.Serializable;

/**
 * account info
 *
 * @author Mingyu.Jin
 * @date  2022/3/28
 */
@Data
public class AccountProfile implements Serializable {
    private Long id;
    private String username;
    private String avatar;
    private String role;
}


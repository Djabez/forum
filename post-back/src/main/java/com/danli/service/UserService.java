package com.danli.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.danli.common.lang.vo.UserInfo;
import com.danli.entity.User;

import java.util.List;
/**

 *Query all users (only partial information)

 *

 *@ return user (only partial information) list

 */

public interface UserService extends IService<User> {

    List<UserInfo> getUserInfoList();

}

package com.danli.controller;


import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.danli.common.lang.Result;
import com.danli.common.lang.vo.UserInfo;
import com.danli.entity.User;
import com.danli.service.UserService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * User front-end controller
 *
 * @author Yicong Wang
 * @date  2022
 */


@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    /**
     * Paging query user
     */
    @RequiresRoles("role_root")
    @RequiresAuthentication
    @RequiresPermissions("user:read")
    @GetMapping("/list")
    public Result userList(@RequestParam(defaultValue = "1") Integer currentPage, @RequestParam(defaultValue = "10") Integer pageSize) {
        List<UserInfo> list = userService.getUserInfoList();
        int size = list.size();
        Page page = new Page(currentPage,pageSize);
        if (pageSize > size) {
            pageSize = size;
        }
        // Find the maximum number of pages to prevent currentPage from crossing the boundary
        int maxPage = size % pageSize == 0 ? size / pageSize : size / pageSize + 1;
        if (currentPage > maxPage) {
            currentPage = maxPage;
        }
        // The index of the first data on the current page
        int curIdx = currentPage > 1 ? (currentPage - 1) * pageSize : 0;
        List pageList = new ArrayList();
        // Put the current page's data into a pageList
        for (int i = 0; i < pageSize && curIdx + i < size; i++) {
            pageList.add(list.get(curIdx + i));
        }
        page.setTotal(list.size()).setRecords(pageList);
        return Result.succ(page);
    }

    /**
     * Create user
     */
    @RequiresRoles("role_root")
    @RequiresPermissions("user:create")
    @RequiresAuthentication
    @PostMapping("/create")
    public Result createUser(@Validated @RequestBody User user){
        if(user==null){
            return Result.fail("cannot be empty");
        }
        else{
            if(user.getRole().contains("role_root")){
                return Result.fail("Do not configure the root user");
            }
            user.setUpdateTime(LocalDateTime.now());
            user.setCreateTime(LocalDateTime.now());
            user.setPassword(SecureUtil.md5(user.getPassword()));

            userService.saveOrUpdate(user);
        }
        return Result.succ(null);
    }

    @PostMapping("/create1")
    public Result createUser1(@Validated @RequestBody User user){
        if(user==null){
            return Result.fail("cannot be empty");
        }
        else{
            if(user.getRole().contains("role_root")){
                return Result.fail("Do not configure the root user");
            }

            user.setUpdateTime(LocalDateTime.now());
            user.setCreateTime(LocalDateTime.now());
            user.setPassword(SecureUtil.md5(user.getPassword()));
            user.setRole("role_user");
            userService.saveOrUpdate(user);
        }
        return Result.succ(null);
    }
    /**
     *Modifying User Information
     */
    @RequiresRoles("role_root")
    @RequiresPermissions("user:update")
    @RequiresAuthentication
    @PostMapping("/update")
    public Result updateUser(@Validated @RequestBody User user){
        if(user==null){
            return Result.fail("cannot be empty");
        }
        else{
            user.setUpdateTime(LocalDateTime.now());
            User subUser = userService.getById(user.getId());
            if(subUser.getRole().equals("role_root")){
                return Result.fail("Do not modify this user");
            }
            //Password not changed
            if(user.getPassword().equals("")){
                user.setPassword(subUser.getPassword());
            }
            else{
                //The password stored in the data is md5 encrypted
                user.setPassword(SecureUtil.md5(user.getPassword()));
            }
            userService.saveOrUpdate(user);
        }
        return Result.succ(null);
    }

    /**
     * delete user
     */
    @RequiresRoles("role_root")
    @RequiresPermissions("user:delete")
    @RequiresAuthentication
    @GetMapping("/delete/{id}")
    public Result delete(@PathVariable(name = "id") Long id) {
        User user = userService.getById(id);
        if(user.getRole().equals("role_root")){
            return Result.fail("Do not delete the user");
        }
        if (userService.removeById(id)) {
            return Result.succ(null);
        } else {
            return Result.fail("Delete failed");
        }

    }
    /*
    * change password
    * */
    @PostMapping("/changepassword")
    public Result changeUserPassword(@Validated @RequestParam String email,
                                     @Validated @RequestParam String oldpassword,
                                     @Validated @RequestParam String oldpassword2,
                                     @Validated @RequestParam String password) {
        if (!oldpassword.equals(oldpassword2)) {
            return Result.fail("Sorry, the two passwords do not match");
        }
        List<User> userList = userService.list();
        for (User eachUser : userList) {
            if (eachUser.getEmail().equals(email)) {
                // if the old password(after md5) is correct,
                if (eachUser.getPassword().equals(SecureUtil.md5(oldpassword))) {
                    // then set the new password(after md50
                    eachUser.setPassword(SecureUtil.md5(password));
                    userService.saveOrUpdate(eachUser);
                    return Result.succ(null);
                } else {
                    return Result.fail("Sorry, old password error!");
                }
            }
        }
        return Result.fail("Sorry, can't find this email!");
    }
    /**
     * Example Modify the user status
     */
    @RequiresRoles("role_root")
    @RequiresPermissions("user:update")
    @RequestMapping("/publish/{id}")
    public Result publish(@PathVariable(name = "id")Long id){
        User user = userService.getById(id);
        if(user.getRole().equals("role_root")){
            return Result.fail("Disable Disable the user");
        }
        if (user.getStatus()==0)
        {
            user.setStatus(1);
        }
        else {
            user.setStatus(0);
        }
        userService.saveOrUpdate(user);
        return Result.succ(null);
    }
}

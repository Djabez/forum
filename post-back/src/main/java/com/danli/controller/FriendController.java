package com.danli.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.danli.annotation.VisitLogger;
import com.danli.common.lang.Result;
import com.danli.config.RedisKeyConfig;
import com.danli.entity.Friend;
import com.danli.service.FriendService;
import com.danli.service.RedisService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Friendchain front controller
 *
 * @author Yicong Wang
 * @date  2022
 */
@RestController

public class FriendController {


    @Autowired
    FriendService friendService;
    @Autowired
    RedisService redisService;


    /**
     * Query all public friend links
     */
    @RequestMapping("/friend/all")
    public Result getFriendList(){
        if (redisService.hasHashKey(RedisKeyConfig.FRIEND_INFO_CACHE, RedisKeyConfig.All)) {
            return Result.succ(redisService.getValueByHashKey(RedisKeyConfig.FRIEND_INFO_CACHE, RedisKeyConfig.All));
        }
        List<Friend> list = friendService.lambdaQuery().eq(Friend::getIsPublished, 1).list();
        redisService.saveKVToHash(RedisKeyConfig.FRIEND_INFO_CACHE, RedisKeyConfig.All,list);
        return Result.succ(list);
    }


    /**
     * Paging query all friend chains
     */
    @RequiresAuthentication
    @RequiresPermissions("user:read")
    @GetMapping("/friendList")
    public Result friendList(@RequestParam(defaultValue = "1") Integer currentPage,@RequestParam(defaultValue = "10") Integer pageSize) {

        Page page = new Page(currentPage, pageSize);
        IPage pageData = friendService.page(page, new QueryWrapper<Friend>().orderByDesc("create_time"));
        return Result.succ(pageData);
    }


    /**
     * Increase the number of friendlink visits by one
     */
    @VisitLogger(behavior = "Click friendlink")
    @RequestMapping("/friend/onclick")
    public Result addView(@RequestParam(name = "")String nickname ){
        if(nickname.equals("")){
            return Result.fail("Access error");
        }
        Friend friend = friendService.getOne(new QueryWrapper<Friend>().eq("nickname",nickname));
        friend.setViews(friend.getViews()+1);
//        Friend temp = new Friend();
//        BeanUtil.copyProperties(friend, temp);
        friendService.saveOrUpdate(friend);
        return Result.succ(null);
    }


    /**
     * Modify the status of the friend chain
     */

    @RequiresAuthentication
    @RequiresPermissions("user:update")
    @RequestMapping("friend/publish/{id}")
    public Result publish(@PathVariable(name = "id")Long id){
        Friend friend = friendService.getById(id);
        friend.setIsPublished(!friend.getIsPublished());
//        Friend temp = new Friend();
//        BeanUtil.copyProperties(friend, temp);
        friendService.saveOrUpdate(friend);
        redisService.deleteCacheByKey(RedisKeyConfig.FRIEND_INFO_CACHE);
        return Result.succ(null);

    }


    /**
     * Modify friendlink
     */
//    @RequiresPermissions("user:update")
//    @RequiresAuthentication
//    @PostMapping("/friend/update")
//    public Result updateFriend(@Validated @RequestBody Friend friend){
//        if(friend==null){
//            return Result.fail("Cannot be empty");
//
//        }
//        else{
//            if (friend.getId()==null){
//                friend.setCreateTime(LocalDateTime.now());
//            }
//
//            friendService.saveOrUpdate(friend);
//            redisService.deleteCacheByKey(RedisKeyConfig.FRIEND_INFO_CACHE);
//        }
//        return Result.succ(null);
//    }
    @RequiresRoles("role_root")
    @RequiresPermissions("user:update")
    @RequiresAuthentication
    @PostMapping("/friend/update")
    public Result updateFriend(@Validated @RequestBody Friend friend){
        Friend temp=null;

        if(friend==null){
            return Result.fail("Cannot be empty");

        }
        else{
            if (friend.getId()==null){
                friend.setCreateTime(LocalDateTime.now());
            }

            friendService.saveOrUpdate(friend);
            redisService.deleteCacheByKey(RedisKeyConfig.FRIEND_INFO_CACHE);
        }
        return Result.succ(null);
    }

    /**
     * Add friends chain
     */
    @RequiresPermissions("user:create")
    @RequiresAuthentication
    @PostMapping("/friend/create")
    public Result createFriend(@Validated @RequestBody Friend friend){
        if(friend==null){
            return Result.fail("Cannot be empty");
        }
        else{
            if (friend.getId()==null){
                friend.setCreateTime(LocalDateTime.now());
            }
            friendService.saveOrUpdate(friend);
            redisService.deleteCacheByKey(RedisKeyConfig.FRIEND_INFO_CACHE);
        }
        return Result.succ(null);
    }


    /**
     * Delete frientlink
     */
    @RequiresRoles("role_root")
    @RequiresPermissions("user:delete")
    @RequiresAuthentication
    @GetMapping("/friend/delete/{id}")
    public Result delete(@PathVariable(name = "id") Long id) {

        if (friendService.removeById(id)) {
            redisService.deleteCacheByKey(RedisKeyConfig.FRIEND_INFO_CACHE);
            return Result.succ(null);
        } else {
            return Result.fail("Delete failed");
        }


    }
}

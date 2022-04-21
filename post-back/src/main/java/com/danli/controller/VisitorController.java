package com.danli.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.danli.common.lang.Result;
import com.danli.common.lang.vo.VisitorNum;
import com.danli.config.RedisKeyConfig;
import com.danli.entity.Visitor;
import com.danli.service.RedisService;
import com.danli.service.VisitorService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Visitor front-end controller
 *
 * @author Yicong Wang
 * @date  2021/4/8
 */
@RestController
public class VisitorController {
    @Autowired
    VisitorService visitorService;
    @Autowired
    RedisService redisService;

    /**
     * Total UV and PV are obtained
     */
    @GetMapping("/visitornum")
    public Result getPvAndUv() {
        if (redisService.hasKey(RedisKeyConfig.PV_UV)) {
            return Result.succ(redisService.getValueByHashKey(RedisKeyConfig.PV_UV, RedisKeyConfig.All));
        }
        int uv = visitorService.list().size();
        int pv = visitorService.getPv();
        VisitorNum visitorNum = new VisitorNum(uv,pv);

        redisService.saveKVToHash(RedisKeyConfig.PV_UV, RedisKeyConfig.All, visitorNum);
        return Result.succ(visitorNum);
    }


    /**
     * Query all visitors
     */
    @RequiresPermissions("user:read")
    @RequiresAuthentication
    @RequestMapping("/visitor")
    public Result getAllVisiorList(){
        List<Visitor> list = visitorService.lambdaQuery().list();

        return Result.succ(list);
    }


    /**
     * Paging query all visitors
     */
    @RequiresAuthentication
    @RequiresPermissions("user:read")
    @GetMapping("/visitorList")
    public Result getVisitorList(@RequestParam(defaultValue = "1") Integer currentPage,@RequestParam(defaultValue = "10") Integer pageSize) {

        Page page = new Page(currentPage, pageSize);
        IPage pageData = visitorService.page(page, new QueryWrapper<Visitor>().orderByDesc("create_time"));
        return Result.succ(pageData);
    }


    /**
     * Query all visitors based on access time pages
     */
    @RequiresAuthentication
    @RequiresPermissions("user:read")
    @GetMapping("/visitor/part")
    public Result getVisitorListByTime(@RequestParam(defaultValue = "") String time,@RequestParam(defaultValue = "1") Integer currentPage,@RequestParam(defaultValue = "10") Integer pageSize) {
        String[] endStartTime = time.split(",");
        if(endStartTime.length!=2){
            return Result.fail("Time setting error");
        }
        Page page = new Page(currentPage, pageSize);
        IPage pageData = visitorService.page(page, new QueryWrapper<Visitor>().le("last_time",endStartTime[1]).ge("last_time",endStartTime[0]).orderByDesc("create_time"));
        return Result.succ(pageData);
    }


    /**
     * Modify a visitor's information
     */
    @RequiresAuthentication
    @PostMapping("/visitor/update")
    public Result updateVisitLog(@Validated @RequestBody Visitor visitor){
        if(visitor ==null){
            return Result.fail("cannot be empty");
        }
        else{
            if(visitor.getId()==null){
                visitor.setLastTime(LocalDateTime.now());
                visitor.setCreateTime(LocalDateTime.now());
            }
            visitorService.saveOrUpdate(visitor);
        }
        return Result.succ(null);
    }


    /**
     * Delete visitor
     */
    @RequiresRoles("role_root")
    @RequiresAuthentication
    @RequiresPermissions("user:delete")
    @GetMapping("/visitor/delete/{id}")
    public Result delete(@PathVariable(name = "id") Long id) {

        if (visitorService.removeById(id)) {
            return Result.succ(null);
        } else {
            return Result.fail("Delete failed");
        }
    }


}

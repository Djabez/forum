package com.danli.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.danli.common.lang.Result;
import com.danli.entity.VisitLog;
import com.danli.service.VisitLogService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Visitor log front end controller
 *
 * @author Yicong Wang
 * @date  2022
 */
@RestController
public class VisitLogController {
    @Autowired
    VisitLogService visitLogService;

    /**
     *Query all visitor browsing logs
     */
    @RequiresAuthentication
    @RequiresPermissions("user:read")
    @RequestMapping("/visitLog/all")
    public Result getFriendList(){
        List<VisitLog> list = visitLogService.lambdaQuery().list();

        return Result.succ(list);
    }

    /**
     * Paging query all visitors
     */
    @RequiresAuthentication
    @RequiresPermissions("user:read")
    @GetMapping("/visitLogList")
    public Result getVisitorList(@RequestParam(defaultValue = "1") Integer currentPage,@RequestParam(defaultValue = "10") Integer pageSize) {

        Page page = new Page(currentPage, pageSize);
        IPage pageData = visitLogService.page(page, new QueryWrapper<VisitLog>().orderByDesc("create_time"));
        return Result.succ(pageData);
    }


    /**
     * Query all visitor logs in pages based on the UUID access time range
     */
    @RequiresAuthentication
    @RequiresPermissions("user:read")
    @GetMapping("/visitLog/part")
    public Result getVisitorList(@RequestParam(defaultValue = "") String uuid,@RequestParam(defaultValue = "") String time,@RequestParam(defaultValue = "1") Integer currentPage,@RequestParam(defaultValue = "10") Integer pageSize) {
        String[] endStartTime = time.split(",");
        if(time.equals("")&&uuid.equals("")){
            Page page = new Page(currentPage, pageSize);
            IPage pageData = visitLogService.page(page, new QueryWrapper<VisitLog>().orderByDesc("create_time"));
            return Result.succ(pageData);
        }
        if(time.equals("")){
            Page page = new Page(currentPage, pageSize);
            IPage pageData = visitLogService.page(page, new QueryWrapper<VisitLog>().eq("uuid",uuid ).orderByDesc("create_time"));
            return Result.succ(pageData);
        }
        else if(uuid.equals("")){
            Page page = new Page(currentPage, pageSize);
            IPage pageData = visitLogService.page(page, new QueryWrapper<VisitLog>().le("create_time",endStartTime[1]).ge("create_time",endStartTime[0]).orderByDesc("create_time"));
            return Result.succ(pageData);
        }
        else{
            Page page = new Page(currentPage, pageSize);
            IPage pageData = visitLogService.page(page, new QueryWrapper<VisitLog>().eq("uuid",uuid ).le("create_time",endStartTime[1]).ge("create_time",endStartTime[0]).orderByDesc("create_time"));
            return Result.succ(pageData);
        }

    }


    /**
     * Modify visitor access logs
     */
    @RequiresAuthentication
    @PostMapping("/visitLog/update")
    public Result updateVisitLog(@Validated @RequestBody VisitLog visitLog){
        if(visitLog ==null){
            return Result.fail("cannot be empty");

        }
        else{
            if(visitLog.getId()==null){
                visitLog.setCreateTime(LocalDateTime.now());
            }
            visitLogService.saveOrUpdate(visitLog);
        }
        return Result.succ(null);
    }


    /**
     * Delete a browse log
     */
    @RequiresRoles("role_root")
    @RequiresPermissions("user:delete")
    @RequiresAuthentication
    @GetMapping("/visitLog/delete/{id}")
    public Result delete(@PathVariable(name = "id") Long id) {

        if (visitLogService.removeById(id)) {
            return Result.succ(null);
        } else {
            return Result.fail("Delete failed");
        }
    }
}

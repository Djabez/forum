package com.danli.controller;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.danli.annotation.AccessLimit;
import com.danli.annotation.VisitLogger;
import com.danli.common.lang.Result;
import com.danli.common.lang.vo.PageComment;
import com.danli.config.RedisKeyConfig;
import com.danli.entity.Comment;
import com.danli.service.*;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.danli.entity.User;
import com.danli.controller.UserController;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 *
 * @author Yicong Wang
 * @date  2022
 */
@RestController
public class    CommentController {
    @Autowired
    CommentService commentService;
    @Autowired
    MailService mailService;
    Logger logger = LoggerFactory.getLogger(CommentController.class);
    @Autowired
    UserService userService;
    /**
     * Paging query all comments
     */
    @RequiresAuthentication
    @RequiresPermissions("user:read")
    @GetMapping("/commentList")
    public Result getCommentListByPage(@RequestParam(defaultValue = "1") Integer currentPage,@RequestParam(defaultValue = "10") Integer pageSize ) {


        Page page = new Page(currentPage, pageSize);
        IPage pageData = commentService.page(page, new QueryWrapper<Comment>().orderByDesc("create_time"));

        //Assert.notNull(blog, "The blog has been deleted！");
        return Result.succ(pageData);

    }


    /**
     * Paging queries root comments under a blog
     */
    @RequiresAuthentication
    @RequiresPermissions("user:read")
    @GetMapping("/comment/detail")
    public Result getCommentListByPageId(@RequestParam(defaultValue = "1") Long blogId, @RequestParam(defaultValue = "1") Integer currentPage,@RequestParam(defaultValue = "10") Integer pageSize ) {

        Page page = new Page(currentPage, pageSize);
        IPage pageData = commentService.page(page, new QueryWrapper<Comment>().eq("blog_id",blogId).orderByDesc("create_time"));

        //Assert.notNull(blog, "The blog has been deleted！");
        return Result.succ(pageData);

    }



    /**
     * Get all the comments under a blog
     */
    @GetMapping("/comment/{blogId}")
    public Result getCommentByBlogId(@PathVariable(name = "blogId") Long blogId) {

        //Entity model collection objects are transformed into VO object collections
        List<PageComment> pageComments = commentService.getPageCommentListByDesc(blogId, (long) -1);

        for (PageComment pageComment : pageComments) {

            List<PageComment> reply = commentService.getPageCommentList(blogId, pageComment.getId());
            pageComment.setReplyComments(reply);
        }
        //Assert.notNull(blog, "The blog has since deleted！");
        return Result.succ(pageComments);

    }

    /**
     * Modify the status of a comment
     */
    @RequiresAuthentication
    @RequiresPermissions("user:update")
    @RequestMapping("comment/publish/{id}")
    public Result publish(@PathVariable(name = "id")String id){
        Comment comment = commentService.getById(id);
        comment.setIsPublished(!comment.getIsPublished());
//        Friend temp = new Friend();
//        BeanUtil.copyProperties(friend, temp);
        commentService.saveOrUpdate(comment);
        return Result.succ(null);

    }

    /**
     * Modify comments
     */
    @RequiresAuthentication
    @RequiresPermissions("user:update")
    @RequestMapping("comment/update")
    public Result updateById(@Validated @RequestBody Comment comment){
        if(comment==null){
            return Result.fail("Cannot be empty");
        }
//        Friend temp = new Friend();
//        BeanUtil.copyProperties(friend, temp);
        commentService.saveOrUpdate(comment);
        return Result.succ(null);

    }

    /**
     * Delete the comment
     */
    @RequiresRoles("role_root")
    @RequiresPermissions("user:delete")
    @RequiresAuthentication
    @RequestMapping("comment/delete/{id}")
    public Result delete(@PathVariable(name = "id")String id){

        if (commentService.removeById(id)) {
            return Result.succ(null);
        } else {
            return Result.fail("Delete failed");
        }

    }

    /**
     * Submit comments
     *
     */

    @AccessLimit(seconds = 30, maxCount = 1, msg = "Only one comment can be submitted within 30 seconds")
    @VisitLogger(behavior = "Submit comments")
    @PostMapping("/comment/add")
    public Result edit(@Validated @RequestBody Comment comment, HttpServletRequest request) {

        if (comment.getContent().contains("<script>") || comment.getEmail().contains("<script>") ||
                comment.getNickname().contains("<script>") || comment.getWebsite().contains("<script>")) {
            return Result.fail("Illegal input");
        }
        System.out.println(comment.toString());
        Comment temp = new Comment();
        temp.setCreateTime(LocalDateTime.now());
        temp.setIp(request.getHeader("x-forwarded-for"));

        BeanUtil.copyProperties(comment, temp, "id", "ip", "createTime");
        commentService.saveOrUpdate(temp);




        return Result.succ(null);
    }


/*
*   comment test !!
*    if not correct, please use the old version.
*
* */
//    @AccessLimit(seconds = 30, maxCount = 1, msg = "Only one comment can be submitted within 30 seconds")
//    @VisitLogger(behavior = "Submit comments")
//    @RequiresAuthentication
//    @PostMapping("/comment/add")
//    public Result edit(@Validated @RequestBody Comment comment, HttpServletRequest request,
//                       @Validated @RequestBody User user) {
//
//        if (comment.getContent().contains("<script>") || comment.getEmail().contains("<script>") || comment.getNickname().contains("<script>") || comment.getWebsite().contains("<script>")) {
//            return Result.fail("Illegal input");
//        }
//        System.out.println(comment.toString());
//
//        Comment temp = new Comment();
//        temp.setCreateTime(LocalDateTime.now());
//
//        temp.setNickname(user.getNickname());
//        temp.setAvatar(user.getAvatar());
//        BeanUtil.copyProperties(comment, temp,  "ip", "createTime");
//        commentService.saveOrUpdate(temp);
//
//        return Result.succ(null);
//
//    }
}

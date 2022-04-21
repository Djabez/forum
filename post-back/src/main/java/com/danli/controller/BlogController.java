package com.danli.controller;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.danli.annotation.VisitLogger;
import com.danli.common.lang.Result;
import com.danli.common.lang.vo.BlogInfo;
import com.danli.config.RedisKeyConfig;
import com.danli.entity.Blog;
import com.danli.service.BlogService;
import com.danli.service.RedisService;
import com.danli.service.UserService;
import com.danli.util.ShiroUtil;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.danli.entity.User;
import com.danli.service.UserService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Blog front-end controller
 *
 * @author Yicong Wang
 * @date 2022
 */
@RestController
public class BlogController {
    @Autowired
    BlogService blogService;
    @Autowired
    RedisService redisService;
    Logger logger = LoggerFactory.getLogger(BlogController.class);
    @Autowired
    UserService userService;
    /**
     * Click top to create time sorted pages to query public blogs
     */
    @VisitLogger(behavior = "Access the page",content = "Home page")
    @GetMapping("/blogs")
    public Result getBlogsByPage(@RequestParam(defaultValue = "1") Integer currentPage) {
        //There is a cache return directly
        if(redisService.hasHashKey(RedisKeyConfig.BLOG_INFO_CACHE,currentPage)){
           return   Result.succ(redisService.getValueByHashKey(RedisKeyConfig.BLOG_INFO_CACHE,currentPage));
        }
        Page page = new Page(currentPage, 5);
        IPage pageData = blogService.page(page, new QueryWrapper<Blog>().eq("status", 1).orderByDesc("create_time"));
        redisService.saveKVToHash(RedisKeyConfig.BLOG_INFO_CACHE, currentPage,pageData);
        return Result.succ(pageData);
    }

    /**
     * Query the list of public blog profiles by sorting them by creation time
     */
    @VisitLogger(behavior = "Check the classification")
    @GetMapping("/blogsByType")
    public Result blogsByType(@RequestParam(defaultValue = "1") Integer currentPage, @RequestParam String typeName) {
        if (redisService.hasHashKey(RedisKeyConfig.CATEGORY_BLOG_CACHE, typeName+currentPage)) {
            return Result.succ(redisService.getValueByHashKey(RedisKeyConfig.CATEGORY_BLOG_CACHE, typeName+currentPage));
        }

        List<BlogInfo> list = blogService.getBlogInfoListByCategoryName(typeName);
        int pageSize = 10;
        Page page = new Page();
        int size = list.size();
        if (pageSize > size) {
            pageSize = size;
        }
        // Find the maximum number of pages to prevent currentPage from crossing the boundary
        int maxPage = size % pageSize == 0 ? size / pageSize : size / pageSize + 1;
        if (currentPage > maxPage) {
            currentPage = maxPage;
        }
        //The index of the first data on the current page
        int curIdx = currentPage > 1 ? (currentPage - 1) * pageSize : 0;
        List pageList = new ArrayList();
        // Put the current page's data into a pageList
        for (int i = 0; i < pageSize && curIdx + i < size; i++) {
            pageList.add(list.get(curIdx + i));
        }
        page.setCurrent(currentPage).setSize(pageSize).setTotal(list.size()).setRecords(pageList);

        redisService.saveKVToHash(RedisKeyConfig.CATEGORY_BLOG_CACHE, typeName+currentPage, page);
        return Result.succ(page);
    }

    /**
     * Query all blogs in sorted pages by creation time
     */
    @RequiresPermissions("user:read")
    @GetMapping("/blogList")
    public Result blogList(@RequestParam(defaultValue = "1") Integer currentPage,@RequestParam(defaultValue = "10") Integer pageSize) {

        Page page = new Page(currentPage, pageSize);
        IPage pageData = blogService.page(page, new QueryWrapper<Blog>().orderByDesc("create_time"));

        return Result.succ(pageData);
    }

    /**
     * Query all blogs
     */
    @RequiresPermissions("user:read")
    @GetMapping("/blog/all")
    public Result blogAll() {
        List<Blog> list = blogService.lambdaQuery().list();
        return Result.succ(list);
    }
    /**
     * Search friendlink blogs
     */

    @VisitLogger(behavior = "Access the page",content = "friendlink")
    @GetMapping("/friends")
    public Result friends() {
        if (redisService.hasHashKey(RedisKeyConfig.BLOG_INFO_CACHE,RedisKeyConfig.FRIEND_BLOG_CACHE)) {
            return Result.succ(redisService.getValueByHashKey(RedisKeyConfig.BLOG_INFO_CACHE,RedisKeyConfig.FRIEND_BLOG_CACHE));
        }
        List<Blog> list = blogService.lambdaQuery().eq(Blog::getTitle, "friendlink").list();
        redisService.saveKVToHash(RedisKeyConfig.BLOG_INFO_CACHE, RedisKeyConfig.FRIEND_BLOG_CACHE, list.get(0));
        return Result.succ(list.get(0));
    }
    /**
     * Inquire about my blog
     */
    @VisitLogger(behavior = "Access the page",content = "About me")
    @GetMapping("/about")
    public Result aboutMe() {
        if (redisService.hasHashKey(RedisKeyConfig.BLOG_INFO_CACHE,RedisKeyConfig.ABOUT_INFO_CACHE)) {
            return Result.succ(redisService.getValueByHashKey(RedisKeyConfig.BLOG_INFO_CACHE,RedisKeyConfig.ABOUT_INFO_CACHE));
        }
        List<Blog> list = blogService.lambdaQuery().eq(Blog::getTitle, "About me！！").list();
        redisService.saveKVToHash(RedisKeyConfig.BLOG_INFO_CACHE,RedisKeyConfig.ABOUT_INFO_CACHE, list.get(0));
        return Result.succ(list.get(0));
    }

    /**
     * Query all blogs in sorted pages by creation time
     */
    @VisitLogger(behavior = "Access the page",content = "The archive")
    @GetMapping("/blog/archives")
    public Result getBlogsArchives(@RequestParam(defaultValue = "1") Integer currentPage) {
        if(redisService.hasHashKey(RedisKeyConfig.ARCHIVE_INFO_CACHE,currentPage)){
            return   Result.succ(redisService.getValueByHashKey(RedisKeyConfig.ARCHIVE_INFO_CACHE,currentPage));
        }
        Integer pageSize = 15;
        Page page = new Page(currentPage, pageSize);
        IPage pageData = blogService.page(page, new QueryWrapper<Blog>().eq("status", 1).orderByDesc("create_time"));
        //caching
        redisService.saveKVToHash(RedisKeyConfig.ARCHIVE_INFO_CACHE, currentPage,pageData);
        return Result.succ(pageData);
    }

    /**
     * Search public blogs by content
     */
    @VisitLogger(behavior = "Search the blog")
    @GetMapping("/search")
    public Result search(@RequestParam String queryString) {
        List<Blog> list = blogService.list(new QueryWrapper<Blog>().like("content", queryString).eq("status", 1).orderByDesc("create_time"));
        return Result.succ(list);
    }

    /**
     * Query details of a public blog
     */
    @VisitLogger(behavior = "View the blog")
    @GetMapping("/blog/{id}")
    public Result detail(@PathVariable(name = "id") Integer id) {


        Blog blog = blogService.getById(id);
        Assert.notNull(blog, "The blog has been deleted！");
        if (blog.getStatus()!=1){
            return Result.fail("You do not have access to this blog");
        }


        if (redisService.getMapByHash(RedisKeyConfig.BLOG_VIEWS_MAP).containsKey(id)) {
            redisService.incrementByHashKey(RedisKeyConfig.BLOG_VIEWS_MAP, id, 1);
        } else {
            redisService.saveKVToHash(RedisKeyConfig.BLOG_VIEWS_MAP, id, 1);
        }

        return Result.succ(blog);

    }


    /**
     * Query a blog for details
     */
    @RequiresPermissions("user:read")
    @GetMapping("/blog/detail/{id}")
    public Result getDetail(@PathVariable(name = "id") Long id) {


        Blog blog = blogService.getById(id);
        Assert.notNull(blog, "The blog has been deleted！");

        return Result.succ(blog);

    }
    /**
     * Delete a Blog
     */
//    @RequiresRoles("role_root")
//    @RequiresPermissions("user:delete")
//    @RequiresAuthentication
//    @GetMapping("/blog/delete/{id}")
//    public Result delete(@PathVariable(name = "id") Long id) {
//
//        if (blogService.removeById(id)) {
//
//            redisService.deleteCacheByKey(RedisKeyConfig.BLOG_INFO_CACHE);
//            redisService.deleteCacheByKey(RedisKeyConfig.ARCHIVE_INFO_CACHE);
//            redisService.deleteCacheByKey(RedisKeyConfig.CATEGORY_BLOG_CACHE);
//            return Result.succ(null);
//        } else {
//            return Result.fail("Delete failed");
//        }
//
//
//    }
   // @RequiresRoles("role_root")
    @RequiresPermissions("user:delete")
    @RequiresAuthentication
    @GetMapping("/blog/delete/{id}")
    public Result delete(@PathVariable(name = "id") Long id) {
        Blog temp = blogService.getById(id);

        if (blogService.removeById(id)) {


            if (ShiroUtil.getProfile().getRole().equals("role_root")){
               // Assert.isTrue(true, "You can edit your posts");
                redisService.deleteCacheByKey(RedisKeyConfig.BLOG_INFO_CACHE);
                redisService.deleteCacheByKey(RedisKeyConfig.ARCHIVE_INFO_CACHE);
                redisService.deleteCacheByKey(RedisKeyConfig.CATEGORY_BLOG_CACHE);
                return Result.succ(null);
            }
            else {
               // Assert.isTrue(temp.getUserId() == ShiroUtil.getProfile().getId(), "You can only delete your posts");
                if (temp.getUserId() == ShiroUtil.getProfile().getId()){
                    redisService.deleteCacheByKey(RedisKeyConfig.BLOG_INFO_CACHE);
                    redisService.deleteCacheByKey(RedisKeyConfig.ARCHIVE_INFO_CACHE);
                    redisService.deleteCacheByKey(RedisKeyConfig.CATEGORY_BLOG_CACHE);
                    return Result.succ(null);
                }
                else {
                    blogService.saveOrUpdate(temp);
                    return Result.fail("You can only delete your posts");
                }
            }
        }
        else {
            return Result.fail("Delete failed");
        }


    }



    /**
     * Modify blog
     */
    @RequiresPermissions("user:update")
    @RequiresAuthentication
    @PostMapping("/blog/update")
    public Result update(@Validated @RequestBody Blog blog) {
        //System.out.println(blog.toString());
        Blog temp = null;

        if (blog.getId() != null) {
            temp = blogService.getById(blog.getId());


            if (ShiroUtil.getProfile().getRole().equals("role_root")){
                Assert.isTrue(true, "You can edit your posts");
            }
            else {
                Assert.isTrue(temp.getUserId() == ShiroUtil.getProfile().getId(), "You can only edit your posts");
            }
              //  Assert.isTrue(temp.getUserId() == ShiroUtil.getProfile().getId()&&user1.getRole()!="role_root", "You can only edit your posts");
                //Assert.isTrue(temp.getUserId() == ShiroUtil.getProfile().getId(), "You can only edit your posts");

        } else {
            temp = new Blog();
            temp.setUserId(ShiroUtil.getProfile().getId());
            temp.setCreateTime(LocalDateTime.now());
            temp.setStatus(0);
        }
        temp.setUpdateTime(LocalDateTime.now());
        BeanUtil.copyProperties(blog, temp, "id", "userId", "createTime", "updateTime");
        blogService.saveOrUpdate(temp);
        redisService.deleteCacheByKey(RedisKeyConfig.BLOG_INFO_CACHE);
        redisService.deleteCacheByKey(RedisKeyConfig.ARCHIVE_INFO_CACHE);
        redisService.deleteCacheByKey(RedisKeyConfig.CATEGORY_BLOG_CACHE);
        return Result.succ(null);
    }

    /**
     * Create blog
     */
//    @RequiresPermissions("user:create")
//    @RequiresAuthentication
//    @PostMapping("/blog/create")
//    public Result create(@Validated @RequestBody Blog blog) {
//        //System.out.println(blog.toString());
//        Blog temp = null;
//        if (blog.getId() != null) {
//            temp = blogService.getById(blog.getId());
//        } else {
//            temp = new Blog();
//            temp.setUserId(ShiroUtil.getProfile().getId());
//            temp.setCreateTime(LocalDateTime.now());
//        }
//        temp.setUpdateTime(LocalDateTime.now());
//        BeanUtil.copyProperties(blog, temp, "id", "userId", "createTime", "updateTime");
//        blogService.saveOrUpdate(temp);
//        redisService.deleteCacheByKey(RedisKeyConfig.BLOG_INFO_CACHE);
//        redisService.deleteCacheByKey(RedisKeyConfig.ARCHIVE_INFO_CACHE);
//        redisService.deleteCacheByKey(RedisKeyConfig.CATEGORY_BLOG_CACHE);
//        return Result.succ(null);
//    }
    @RequiresPermissions("user:create")
    @RequiresAuthentication
    @PostMapping("/blog/create")
    public Result create(@Validated @RequestBody Blog blog) {
        //System.out.println(blog.toString());
        Blog temp = null;
        if (blog.getId() != null) {
            temp = blogService.getById(blog.getId());
        } else {
            temp = new Blog();
            temp.setUserId(ShiroUtil.getProfile().getId());
            temp.setCreateTime(LocalDateTime.now());

        }
        temp.setUpdateTime(LocalDateTime.now());
        BeanUtil.copyProperties(blog, temp, "id", "userId", "createTime", "updateTime");
        blogService.saveOrUpdate(temp);
        redisService.deleteCacheByKey(RedisKeyConfig.BLOG_INFO_CACHE);
        redisService.deleteCacheByKey(RedisKeyConfig.ARCHIVE_INFO_CACHE);
        redisService.deleteCacheByKey(RedisKeyConfig.CATEGORY_BLOG_CACHE);
        return Result.succ(null);
    }

    /**
     * Modify blog Status
     */
    @RequiresPermissions("user:update")
    @RequestMapping("blog/publish/{id}")
    public Result publish(@PathVariable(name = "id")String id){
        Blog blog = blogService.getById(id);
        if (blog.getStatus()==0)
        {
            blog.setStatus(1 );
        }
        else {
            blog.setStatus(0);
        }
        blogService.saveOrUpdate(blog);
        redisService.deleteCacheByKey(RedisKeyConfig.BLOG_INFO_CACHE);
        redisService.deleteCacheByKey(RedisKeyConfig.ARCHIVE_INFO_CACHE);
        redisService.deleteCacheByKey(RedisKeyConfig.CATEGORY_BLOG_CACHE);
        return Result.succ(null);

    }



//    /**
//     * Increase blog views by one
//     */
//    @RequestMapping("/blog/view/{id}")
//    public Result addView(@PathVariable(name = "id")String id){
//        Blog blog = blogService.getById(id);
//        blog.setViews(blog.getViews()+1);
//        blogService.saveOrUpdate(blog);
//        return Result.succ(null);
//    }
}


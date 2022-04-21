package com.danli.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.danli.common.lang.vo.BlogInfo;
import com.danli.entity.Blog;

import java.util.List;

/**
 * service
 *
 * @author Mingyu.jin
 * @date  2022
 */
public interface BlogService extends IService<Blog> {

    /**
     * find list
     *
     * @param categoryName
     * @return blog list
     */
    List<BlogInfo> getBlogInfoListByCategoryName(String categoryName);
}

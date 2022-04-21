package com.danli.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danli.common.lang.vo.BlogInfo;
import com.danli.entity.Blog;
import com.danli.mapper.BlogMapper;
import com.danli.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
        * service
        *
        * @author Mingyu.jin
        * @date  2022
        */
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {
    @Autowired
    BlogMapper blogMapper;


    @Override
    public List<BlogInfo> getBlogInfoListByCategoryName(String categoryName) {
        List<BlogInfo> blogInfos = blogMapper.getBlogByTypeName(categoryName);
        return blogInfos;
    }

}

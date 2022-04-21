package com.danli.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danli.common.lang.vo.PageComment;
import com.danli.entity.Comment;
import com.danli.mapper.CommentMapper;
import com.danli.service.CommentService;
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
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {
    @Autowired
    CommentMapper commentMapper;


    /**
     *
     *
     * @param blogId
     * @param parentCommentId
     * @return
     */
    @Override
    public List<PageComment> getPageCommentListByDesc(Long blogId, Long parentCommentId) {
        return commentMapper.getPageCommentListByPageAndParentCommentIdByDesc(blogId, parentCommentId);
    }

    /**
     * use blog id and parent id search
     *
     * @param blogId
     * @param parentCommentId
     * @return
     */
    @Override
    public List<PageComment> getPageCommentList(Long blogId, Long parentCommentId) {
        return commentMapper.getPageCommentListByPageAndParentCommentId(blogId, parentCommentId);
    }


}

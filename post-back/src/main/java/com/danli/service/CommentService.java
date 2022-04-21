package com.danli.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.danli.common.lang.vo.PageComment;
import com.danli.entity.Comment;

import java.util.List;

/**
 * service class
 *
 * @author jmy
 * @date  2022/4/8
 */
public interface CommentService extends IService<Comment> {


    public List<PageComment> getPageCommentListByDesc(Long blogId, Long parentCommentId);

    /**
     *
     * Find all child comments by blog ID and parent comment ID
     * @param blogId
     * @param parentCommentId
     * @return
     */
    public List<PageComment> getPageCommentList(Long blogId, Long parentCommentId);
}

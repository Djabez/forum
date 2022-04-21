package com.danli.common.lang.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * The purpose of this class is to transmit to the comment view object in the front end
 *
 * @author yicong wang
 * @date 2021/3/25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class PageComment {
    private Long id;
    //Nickname
    private String nickname;
    //Comment on the content
    private String content;
    //Profile picture (Picture path)
    private String avatar;
    //Personal website
    private String website;
    //Time for comments
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    //Blogger reply
    private Integer isAdminComment;
    //Parent comment nickname
    private String parentCommentNickname;
    //Comment in response to this comment
    private List<PageComment> replyComments = new ArrayList<>();
}

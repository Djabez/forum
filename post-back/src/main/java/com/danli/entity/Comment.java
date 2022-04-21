package com.danli.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 *
 *
 * @author Mingyu
 * @date 2022.04.05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * nickname
     */
    private String nickname;

    /**
     * email
     */
    private String email;

    /**
     * comment content
     */
    private String content;

    /**
     * avatar
     */
    private String avatar;

    /**
     * comment time
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * ip address
     */
    private String ip;

    /**
     * admin reply
     */
    private Integer isAdminComment;
    /**
     * publish or screate
     */
    private Boolean isPublished;

    /**
     * blog id
     */
    private Long blogId;


    /**
     *  parent comment nickname
     */
    private String parentCommentNickname;

    /**
     *
     */
    private Long parentCommentId;
    /**
     * personal website
     */
    private String website;
    /**
     * if input a qq eamil,  it will automatic get the name
     */
    private String qq;


}

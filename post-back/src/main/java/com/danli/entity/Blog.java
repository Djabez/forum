package com.danli.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
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
@TableName(value = "", resultMap = "")
public class Blog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * title
     */
    @NotBlank(message = "title can not be empty")
    private String title;

    /**
     * picture
     */
    private String firstPicture;

    /**
     * description
     */
    @NotBlank(message = "description")
    private String description;

    /**
     * content
     */
    @NotBlank(message = "content can not be empty")
    private String content;

    /**
     * set up time
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     *  update time
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     *  view number
     */
    private Integer views;

    /**
     * word
     */
    private Integer words;

    /**
     *  type id
     */
    private Long typeId;

    /**
     * author id
     */

    private Long userId;

    /**
     * blog status
     */
    private Integer status;



}

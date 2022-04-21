package com.danli.common.lang.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.danli.entity.Type;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * View object passed to the front-end blog
 *
 * @author Yicong Wang
 * @date  2022/3/22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)

public class BlogInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * The article title
     */
    @NotBlank(message = "The title cannot be empty")
    private String title;

    /**
     * Article header diagram, used for random article display
     */
    private String firstPicture;

    /**
     * description
     */
    @NotBlank(message = "The digest cannot be empty")
    private String description;

    /**
     * The article body
     */
    @NotBlank(message = "The content cannot be empty")
    private String content;

    /**
     * Creation time
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * Update time
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * viewed
     */
    private Integer views;

    /**
     * The article word count
     */
    private Integer words;

    /**
     * Article Category ID
     */
    private Long typeId;

    /**
     * Article author ID
     */
    private Long userId;
    /**
     * The article states
     */
    private Integer status;
    /**
     * Article type
     */
    private Type type;


}

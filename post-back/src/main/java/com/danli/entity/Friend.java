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
public class Friend implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * nickname
     */
    private String nickname;

    /**
     * description
     */
    private String description;

    /**
     * website
     */
    private String website;

    /**
     * avatar
     */
    private String avatar;

    /**
     * publish or screate
     */
    private Boolean isPublished;

    /**
     * click times
     */
    private Integer views;

    /**
     * set up time
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;


}

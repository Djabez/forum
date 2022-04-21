package com.danli.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
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
public class User implements Serializable {

    private static final long serialVersionUID = 1L;


    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * username
     */
    @NotBlank(message = "username can not be empty ")
    private String username;

    /**
     * password
     */
    @NotBlank(message = "password can not be empty")
    private String password;

    /**
     * nickname
     */
    @NotBlank(message = "nickname can not be empty ")
    private String nickname;

    /**
     * avatar
     */
    private String avatar;

    /**
     * email
     */
    @NotBlank(message = "email can not be empty")
    @Email(message = "format of email is worng")
    private String email;

    private Integer status;

    /**
     * set up time
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * update time
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    /**
     * role authentication
     */
    private String role;


}

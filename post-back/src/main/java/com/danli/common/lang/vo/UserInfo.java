package com.danli.common.lang.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;


/**
 * The User part of the information passed to the front-end
 *
 * @author Yicong Wang
 * @date  2022
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserInfo {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * user name
     */
    @NotBlank(message = "The user name cannot be empty")
    private String username;


    /**
     * nickname
     */
    @NotBlank(message = "Nickname cannot be empty")
    private String nickname;

    /**
     * Head address
     */
    private String avatar;

    /**
     * email
     */
    @NotBlank(message = "Email cannot be empty")
    @Email(message = "The mailbox format is incorrect")
    private String email;

    private Integer status;

    /**
     * Creation time
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * Update time
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    /**
     * Role Access Rights
     */
    private String role;
}

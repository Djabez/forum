package com.danli.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
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
@AllArgsConstructor
@NoArgsConstructor
public class VisitLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     *
     */
    private String uuid;

    /**
     * quest interface
     */
    private String uri;

    /**
     * quest method
     */
    private String method;

    /**
     * quest param
     */
    private String param;

    /**
     * behavior
     */
    private String behavior;

    /**
     * content of quest
     */
    private String content;
    /**
     *remark
     */
    private String remark;

    /**
     * ip
     */
    private String ip;

    /**
     * ip source
     */
    private String ipSource;

    /**
     *  os system
     */
    private String os;

    /**
     * browser times
     */
    private String browser;

    /**
     * access time
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * times
     */
    private Integer times;
    /**
     * user-agent
     */
    private String userAgent;


}

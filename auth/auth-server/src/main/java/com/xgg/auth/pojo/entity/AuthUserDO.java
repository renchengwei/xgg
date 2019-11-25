package com.xgg.auth.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * TODO 请描述类的具体实现功能和作用
 * @author renchengwei
 * @date 2019-11-24
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("auth_user")
public class AuthUserDO implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("password")
    private String password;

    @TableField("user_name")
    private String userName;

    @TableField("mobile")
    private String mobile;

    @TableField("email")
    private String email;

    @TableField("password_salt")
    private String passwordSalt;

    @TableField("is_locked")
    private Boolean locked;

    @TableField("gmt_create")
    private LocalDateTime gmtCreate;

    @TableField("gmt_modified")
    private LocalDateTime gmtModified;

    @TableField("is_deleted")
    @TableLogic
    private Boolean deleted;


}

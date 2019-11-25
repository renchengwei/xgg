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
@TableName("auth_role_authority")
public class AuthRoleAuthorityDO implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("role_id")
    private Long roleId;

    @TableField("authority_id")
    private Long authorityId;

    @TableField("gmt_modified")
    private LocalDateTime gmtModified;

    @TableField("is_deleted")
    @TableLogic
    private Boolean deleted;


}

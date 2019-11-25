package com.xgg.auth.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * TODO 请描述类的具体实现功能和作用
 * @author renchengwei
 * @date 2019-11-24
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("persistent_logins")
public class PersistentLoginsDO implements Serializable {

    private static final long serialVersionUID=1L;

    @TableField("username")
    private String username;

    @TableId(value = "series", type = IdType.AUTO)
    private String series;

    @TableField("token")
    private String token;

    @TableField("last_used")
    private LocalDateTime lastUsed;


}

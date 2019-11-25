package com.xgg.auth.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import java.sql.Blob;
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
@TableName("oauth_refresh_token")
public class OauthRefreshTokenDO implements Serializable {

    private static final long serialVersionUID=1L;

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("token_id")
    private String tokenId;

    @TableField("token")
    private Blob token;

    @TableField("authentication")
    private Blob authentication;


}

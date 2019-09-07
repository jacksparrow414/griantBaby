package com.graint.baby.code.modules.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author dhb
 * @since 2019-09-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_user")
public class SysUserEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 名字
     */
    @TableField("user_name")
    private String userName;

    /**
     * 机构ID
     */
    @TableField("age")
    private String age;

    /**
     * 登录邮箱
     */
    @TableField("email")
    private String email;

    /**
     * 登录密码
     */
    @TableField("password")
    private String password;

    @TableField("test")
    private Integer test;

    /**
     * 盐
     */
    @TableField("salt")
    private String salt;


}

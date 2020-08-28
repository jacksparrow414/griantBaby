package com.graint.baby.code.modules.menu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 菜单管理.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_menu")
public class SysMenuEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "menu_id", type = IdType.ID_WORKER)
    private Long menuId;

    /**
     * 父菜单ID，一级菜单为0.
     */
    @TableField("parent_id")
    private Long parentId;

    /**
     * 菜单名称.
     */
    @TableField("name")
    private String name;

    /**
     * 菜单URL.
     */
    @TableField("url")
    private String url;

    /**
     * 授权(多个用逗号分隔，如：user:list,user:create).
     */
    @TableField("perms")
    private String perms;

    /**
     * 类型   0：目录   1：菜单   2：按钮.
     */
    @TableField("type")
    private Integer type;

    /**
     * 菜单图标.
     */
    @TableField("icon")
    private String icon;

    /**
     * 排序.
     */
    @TableField("order_num")
    private Integer orderNum;


}

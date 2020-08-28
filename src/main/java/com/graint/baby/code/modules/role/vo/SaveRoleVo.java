package com.graint.baby.code.modules.role.vo;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 保存角色VO.
 */
@Getter
@Setter
public class SaveRoleVo {

    /**
     * 操作类型.
     * 0：新增
     * 1：修改
     */
    @NotNull
    @Min(value = 0)
    @Max(value = 1)
    private Integer operationType;

    private Long roleId;

    private String remake;

    @NotBlank(message = "请输入角色名称")
    private String roleName;
    
}

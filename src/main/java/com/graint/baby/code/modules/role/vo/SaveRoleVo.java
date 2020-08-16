package com.graint.baby.code.modules.role.vo;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Author jacksparrow414
 * @Date 2019-09-13
 * @Description: 保存角色VO
 */
public class SaveRoleVo {

    /**
     * 操作类型
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

    public Integer getOperationType() {
        return operationType;
    }

    public void setOperationType(Integer operationType) {
        this.operationType = operationType;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRemake() {
        return remake;
    }

    public void setRemake(String remake) {
        this.remake = remake;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}

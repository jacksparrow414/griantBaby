package com.graint.baby.code.modules.role.controller;

import com.graint.baby.code.common.SysConstants;
import com.graint.baby.code.modules.role.service.SysRoleService;
import com.graint.baby.code.modules.role.vo.SaveRoleVo;
import com.graint.baby.code.utils.CustomData;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

/**
 * 角色 前端控制器.
 */
@RestController
@RequestMapping("/role")
public class SysRoleController {
    
    @Autowired
    private SysRoleService sysRoleService;
    
    /**
     * 保存角色.
     *
     * @param saveRoleVo 角色VO
     * @return com.graint.baby.code.utils.CustomData
     */
    @PostMapping(value = "saveRole", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public CustomData saveRole(@RequestBody @Valid final SaveRoleVo saveRoleVo) {
        String result = sysRoleService.saveRole(saveRoleVo);
        return Optional.ofNullable(result).filter(u -> StringUtils.isBlank(result)).map(u -> CustomData.ok()).orElse(CustomData.error(result));
    }
    
    /**
     * 移除角色.
     *
     * @param id 角色id
     * @return com.graint.baby.code.utils.CustomData
     */
    @GetMapping(value = "removeRole")
    public CustomData removeRole(@RequestParam final Long id) {
        boolean result = this.sysRoleService.removeById(id);
        if (result) {
            return CustomData.ok(SysConstants.DELETE_SUCCESS);
        }
        return CustomData.error(SysConstants.DELETE_FAILURE);
    }
    
}

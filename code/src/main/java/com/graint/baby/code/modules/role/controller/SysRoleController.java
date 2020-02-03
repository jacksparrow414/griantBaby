package com.graint.baby.code.modules.role.controller;


import com.graint.baby.code.common.SysConstants;
import com.graint.baby.code.modules.role.service.SysRoleService;
import com.graint.baby.code.modules.role.vo.SaveRoleVo;
import com.graint.baby.code.utils.CustomData;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

/**
 * <p>
 * 角色 前端控制器
 * </p>
 *
 * @author dhb
 * @since 2019-09-13
 */
@RestController
@RequestMapping("/role")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    @PostMapping(value = "saveRole",consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public CustomData saveRole(@RequestBody @Valid SaveRoleVo saveRoleVo){
        String result = sysRoleService.saveRole(saveRoleVo);
        return Optional.ofNullable(result).filter(u->StringUtils.isBlank(result))
                .map(u->CustomData.ok()).orElse(CustomData.error(result));
    }

    @GetMapping(value = "removeRole")
    public CustomData removeRole(@RequestParam Long id){
       boolean result = this.sysRoleService.removeById(id);
       if (result){
           return CustomData.ok(SysConstants.DELETE_SUCCESS);
       }
       return CustomData.error(SysConstants.DELETE_FAILURE);
    }

}
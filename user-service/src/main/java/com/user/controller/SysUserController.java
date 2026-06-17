package com.user.controller;

import cn.hutool.crypto.SecureUtil;
import com.common.result.ResponseResult;
import com.common.vo.SysUserVO;
import com.user.entity.SysUser;
import com.user.service.SysUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 系统用户控制器
 * <p>提供 SYS_USER 表的 CRUD 接口。</p>
 * <p>对外不暴露 password 字段，通过 VO 转换过滤敏感信息。</p>
 */
@RestController
@RequestMapping("/user")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 查询所有用户
     */
    @GetMapping("/list")
    public ResponseResult list() {
        List<SysUserVO> list = sysUserService.list().stream()
                .map(this::toVO)
                .collect(Collectors.toList());
        return ResponseResult.ok("list", list);
    }

    /**
     * 根据ID查询用户
     */
    @GetMapping("/{id}")
    public ResponseResult getById(@PathVariable Long id) {
        SysUser user = sysUserService.getById(id);
        if (user == null) {
            return ResponseResult.error("404", "用户不存在");
        }
        return ResponseResult.ok("user", toVO(user));
    }



//    @PostMapping("/login")
//    public ResponseResult login(@RequestBody LoginDTO loginDTO) {
//        // 验证用户名密码
//        SysUser user = userService.authenticate(loginDTO);
//
//        // 生成token
//        String token = JwtUtils.generateToken(user.getUsername(), user.getId());
//
//        return ResponseResult.ok(data -> {
//            data.put("token", token);
//            data.put("user", user);
//        });
//    }



    /**
     * 新增用户
     */
    @PostMapping
    public ResponseResult save(@RequestBody SysUser sysUser) {
        return ResponseResult.ok("result", sysUserService.save(sysUser));
    }

    /**
     * 修改用户
     */
    @PutMapping
    public ResponseResult update(@RequestBody SysUser sysUser) {
        return ResponseResult.ok("result", sysUserService.updateById(sysUser));
    }

    /**
     * 删除用户
     */
    @DeleteMapping("/{id}")
    public ResponseResult delete(@PathVariable Long id) {
        return ResponseResult.ok("result", sysUserService.removeById(id));
    }

    /**
     * 实体转 VO（不暴露 password 字段）
     */
    private SysUserVO toVO(SysUser user) {
        SysUserVO vo = new SysUserVO();
        vo.setId(user.getId());
        vo.setUserName(user.getUserName());
        return vo;
    }
}

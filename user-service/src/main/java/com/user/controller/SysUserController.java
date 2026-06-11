package com.user.controller;

import com.user.entity.SysUser;
import com.user.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 查询所有用户
     */
    @GetMapping("/list")
    public List<SysUser> list() {
        return sysUserService.list();
    }

    /**
     * 根据ID查询用户
     */
    @GetMapping("/{id}")
    public SysUser getById(@PathVariable Long id) {
        return sysUserService.getById(id);
    }

    /**
     * 新增用户
     */
    @PostMapping
    public boolean save(@RequestBody SysUser sysUser) {
        return sysUserService.save(sysUser);
    }

    /**
     * 修改用户
     */
    @PutMapping
    public boolean update(@RequestBody SysUser sysUser) {
        return sysUserService.updateById(sysUser);
    }

    /**
     * 删除用户
     */
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id) {
        return sysUserService.removeById(id);
    }
}

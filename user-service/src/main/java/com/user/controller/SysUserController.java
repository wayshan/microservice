package com.user.controller;

import com.common.vo.SysUserVO;
import com.user.entity.SysUser;
import com.user.service.SysUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 查询所有用户
     */
    @GetMapping("/list")
    public List<SysUserVO> list() {
        return sysUserService.list().stream()
                .map(this::toVO)
                .collect(Collectors.toList());
    }

    /**
     * 根据ID查询用户
     */
    @GetMapping("/{id}")
    public SysUserVO getById(@PathVariable Long id) {
        SysUser user = sysUserService.getById(id);
        return user != null ? toVO(user) : null;
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

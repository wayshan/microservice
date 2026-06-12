package com.order.controller;

import com.order.entity.SysOrder;
import com.order.service.SysOrderService;
import com.common.vo.SysOrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sys-order")
public class SysOrderController {

    @Autowired
    private SysOrderService sysOrderService;

    /**
     * 查询所有订单（包含用户名）
     */
    @GetMapping("/list")
    public List<SysOrderVO> list() {
        return sysOrderService.listWithUserName();
    }

    /**
     * 根据ID查询订单
     */
    @GetMapping("/{id}")
    public SysOrder getById(@PathVariable Long id) {
        return sysOrderService.getById(id);
    }

    /**
     * 新增订单
     */
    @PostMapping
    public boolean save(@RequestBody SysOrder sysOrder) {
        return sysOrderService.save(sysOrder);
    }

    /**
     * 修改订单
     */
    @PutMapping
    public boolean update(@RequestBody SysOrder sysOrder) {
        return sysOrderService.updateById(sysOrder);
    }

    /**
     * 删除订单
     */
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id) {
        return sysOrderService.removeById(id);
    }
}

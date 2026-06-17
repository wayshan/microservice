package com.order.controller;

import com.common.result.ResponseResult;
import com.order.entity.SysOrder;
import com.order.service.SysOrderService;
import com.common.vo.SysOrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 系统订单控制器
 * <p>提供 SYS_ORDER 表的 CRUD 接口，查询时通过 Feign 调用 user-service 获取用户名。</p>
 * <p>访问路径示例：{@code http://gateway:8080/order-service/sys-order/list}</p>
 */
@RestController
@RequestMapping("/sys-order")
public class SysOrderController {

    @Autowired
    private SysOrderService sysOrderService;

    /**
     * 查询所有订单（包含用户名）
     */
    @GetMapping("/list")
    public ResponseResult list() {
        List<SysOrderVO> list = sysOrderService.listWithUserName();
        return ResponseResult.ok("list", list);
    }

    /**
     * 根据ID查询订单
     */
    @GetMapping("/{id}")
    public ResponseResult getById(@PathVariable Long id) {
        SysOrder order = sysOrderService.getById(id);
        if (order == null) {
            return ResponseResult.error("404", "订单不存在");
        }
        return ResponseResult.ok("order", order);
    }

    /**
     * 新增订单
     */
    @PostMapping
    public ResponseResult save(@RequestBody SysOrder sysOrder) {
        return ResponseResult.ok("result", sysOrderService.save(sysOrder));
    }

    /**
     * 修改订单
     */
    @PutMapping
    public ResponseResult update(@RequestBody SysOrder sysOrder) {
        return ResponseResult.ok("result", sysOrderService.updateById(sysOrder));
    }

    /**
     * 删除订单
     */
    @DeleteMapping("/{id}")
    public ResponseResult delete(@PathVariable Long id) {
        return ResponseResult.ok("result", sysOrderService.removeById(id));
    }
}

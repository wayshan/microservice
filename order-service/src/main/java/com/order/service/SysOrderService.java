package com.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.order.entity.SysOrder;
import com.common.vo.SysOrderVO;

import java.util.List;

public interface SysOrderService extends IService<SysOrder> {

    /**
     * 查询订单列表，包含用户名
     */
    List<SysOrderVO> listWithUserName();
}

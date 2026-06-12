package com.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.order.entity.SysOrder;
import com.common.vo.SysOrderVO;

import java.util.List;

/**
 * 系统订单服务接口
 * <p>继承 MyBatis-Plus IService，提供 SYS_ORDER 表的业务层操作。</p>
 */
public interface SysOrderService extends IService<SysOrder> {

    /**
     * 查询订单列表，包含用户名
     */
    List<SysOrderVO> listWithUserName();
}

package com.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.order.entity.SysOrder;
import com.common.feign.UserFeignClient;
import com.common.vo.SysOrderVO;
import com.common.vo.SysUserVO;
import com.order.mapper.SysOrderMapper;
import com.order.service.SysOrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 系统订单服务实现
 * <p>继承 MyBatis-Plus ServiceImpl，提供 SYS_ORDER 表的业务逻辑。</p>
 * <p>查询订单列表时，通过 Feign 调用 user-service 获取用户名信息。</p>
 */
@Service
public class SysOrderServiceImpl extends ServiceImpl<SysOrderMapper, SysOrder> implements SysOrderService {

    /** Feign 客户端，用于调用 user-service 获取用户信息 */
    @Autowired
    private UserFeignClient userFeignClient;

    /**
     * 查询订单列表，并关联获取用户名。
     * <p>流程：先查询所有订单 → 遍历通过 Feign 获取对应用户名 → 组装为 VO 返回。</p>
     *
     * @return 包含用户名的订单列表
     */
    @Override
    public List<SysOrderVO> listWithUserName() {
        List<SysOrder> orders = list();
        return orders.stream().map(order -> {
            SysOrderVO vo = new SysOrderVO();
            BeanUtils.copyProperties(order, vo);
            SysUserVO user = userFeignClient.getById(order.getUserId());
            if (user != null) {
                vo.setUserName(user.getUserName());
            }
            return vo;
        }).collect(Collectors.toList());
    }
}

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

@Service
public class SysOrderServiceImpl extends ServiceImpl<SysOrderMapper, SysOrder> implements SysOrderService {

    @Autowired
    private UserFeignClient userFeignClient;

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

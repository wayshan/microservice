package com.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.order.entity.SysOrder;
import com.order.vo.SysOrderVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysOrderMapper extends BaseMapper<SysOrder> {

    /**
     * 查询订单列表，关联用户表获取用户名
     */
    List<SysOrderVO> selectOrderListWithUserName();
}

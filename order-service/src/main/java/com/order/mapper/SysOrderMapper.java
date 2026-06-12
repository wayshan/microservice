package com.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.order.entity.SysOrder;
import com.common.vo.SysOrderVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 系统订单 Mapper 接口
 * <p>继承 MyBatis-Plus BaseMapper，提供 SYS_ORDER 表的基础 CRUD 操作。
 * 自定义 SQL 查询在对应的 XML 文件中定义。</p>
 */
@Mapper
public interface SysOrderMapper extends BaseMapper<SysOrder> {

    /**
     * 查询订单列表，关联用户表获取用户名
     */
    List<SysOrderVO> selectOrderListWithUserName();
}

package com.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.user.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统用户 Mapper 接口
 * <p>继承 MyBatis-Plus BaseMapper，提供 SYS_USER 表的基础 CRUD 操作。</p>
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
}

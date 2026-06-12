package com.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.user.entity.SysUser;
import com.user.mapper.SysUserMapper;
import com.user.service.SysUserService;
import org.springframework.stereotype.Service;

/**
 * 系统用户服务实现
 * <p>继承 MyBatis-Plus ServiceImpl，提供 SYS_USER 表的业务逻辑。
 * 当前无自定义业务方法，直接使用父类提供的 CRUD 方法。</p>
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
}

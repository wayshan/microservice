package com.common.feign;

import com.common.vo.SysUserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.cloud.openfeign.FallbackFactory;

@Slf4j
@AutoConfiguration
public class UserFeignFallbackFactory implements FallbackFactory<UserFeignClient> {

    @Override
    public UserFeignClient create(Throwable cause) {
        log.error("调用 user-service 失败: {}", cause.getMessage());
        return new UserFeignClient() {
            @Override
            public SysUserVO getById(Long id) {
                SysUserVO fallback = new SysUserVO();
                fallback.setId(id);
                fallback.setUserName("未知用户");
                fallback.setFallback(true);
                return fallback;
            }
        };
    }
}

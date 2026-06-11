package com.order.feign;

import com.order.vo.SysUserVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service", fallbackFactory = UserFeignFallbackFactory.class)
public interface UserFeignClient {

    @GetMapping("/user/{id}")
    SysUserVO getById(@PathVariable("id") Long id);
}

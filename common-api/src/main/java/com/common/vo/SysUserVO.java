package com.common.vo;

import lombok.Data;

@Data
public class SysUserVO {

    private Long id;
    private String userName;
    private String password;

    /**
     * 标记是否为降级数据
     */
    private boolean fallback;
}

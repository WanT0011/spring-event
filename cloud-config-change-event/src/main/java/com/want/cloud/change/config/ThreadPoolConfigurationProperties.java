package com.want.cloud.change.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author WangZhiJian
 * @since 2021/2/4
 */
@Data
@ConfigurationProperties(value = ThreadPoolConfigurationProperties.THREAD_POOL_CONFIG_PREFIX)
public class ThreadPoolConfigurationProperties {
    public static final String THREAD_POOL_CONFIG_PREFIX = "com.want.thread-pool";

    /**
     * 最大连接数
     */
    private Integer maxConnection = 5;
    /**
     * 最小链接数
     */
    private Integer minConnection = 2;

}

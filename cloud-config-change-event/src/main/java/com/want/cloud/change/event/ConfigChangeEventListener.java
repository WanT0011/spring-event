package com.want.cloud.change.event;

import com.want.cloud.change.config.ThreadPoolConfigurationProperties;
import com.want.cloud.change.thread.pool.DynamicThreadPool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.context.environment.EnvironmentChangeEvent;
import org.springframework.context.ApplicationListener;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * @author WangZhiJian
 * @since 2021/2/4
 */
@Slf4j
public class ConfigChangeEventListener implements ApplicationListener<EnvironmentChangeEvent> {


    @Resource
    private DynamicThreadPool dynamicThreadPool;
    @Resource
    private ThreadPoolConfigurationProperties threadPoolConfigurationProperties;
    /**
     * Handle an application event.
     *
     * @param event the event to respond to
     */
    @Override
    public void onApplicationEvent(EnvironmentChangeEvent event) {
        log.info("event : {}",event);
        Boolean aBoolean = Optional.ofNullable(event.getKeys())
                .map(keys -> keys.stream().anyMatch(key -> key.startsWith(ThreadPoolConfigurationProperties.THREAD_POOL_CONFIG_PREFIX)))
                .orElse(false);
        if(aBoolean){
            dynamicThreadPool.getExecutorService().setMaximumPoolSize(threadPoolConfigurationProperties.getMaxConnection());
            dynamicThreadPool.getExecutorService().setCorePoolSize(threadPoolConfigurationProperties.getMinConnection());
        }
    }
}

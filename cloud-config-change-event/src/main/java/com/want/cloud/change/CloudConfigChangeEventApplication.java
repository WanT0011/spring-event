package com.want.cloud.change;

import com.want.cloud.change.config.ThreadPoolConfigurationProperties;
import com.want.cloud.change.event.ConfigChangeEventListener;
import com.want.cloud.change.thread.pool.DynamicThreadPool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author WangZhiJian
 * @since 2021/2/4
 */
@Slf4j
@EnableScheduling
@Import({DynamicThreadPool.class, ConfigChangeEventListener.class})
@EnableConfigurationProperties(ThreadPoolConfigurationProperties.class)
@SpringBootApplication
public class CloudConfigChangeEventApplication {
    public static void main(String[] args) {
        SpringApplication.run(CloudConfigChangeEventApplication.class,args);
    }

    @Resource
    private DynamicThreadPool dynamicThreadPool;


    @Scheduled(fixedDelay = 1000)
    public void scheduled(){
        ThreadPoolExecutor service = dynamicThreadPool.getExecutorService();
        log.info("core size is {}, max size is {}",service.getCorePoolSize(),service.getMaximumPoolSize());
    }
}

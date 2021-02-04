package com.want.cloud.change.thread.pool;

import com.want.cloud.change.config.ThreadPoolConfigurationProperties;
import lombok.Data;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.Resource;
import java.util.concurrent.*;

/**
 * 动态线程池
 *
 * @author WangZhiJian
 * @since 2021/2/4
 */
@Data
public class DynamicThreadPool implements InitializingBean {

    @Resource
    private ThreadPoolConfigurationProperties threadPoolConfigurationProperties;

    private ThreadPoolExecutor executorService;

    /**
     * Invoked by the containing {@code BeanFactory} after it has set all bean properties
     * and satisfied {@link BeanFactoryAware}, {@code ApplicationContextAware} etc.
     * <p>This method allows the bean instance to perform validation of its overall
     * configuration and final initialization when all bean properties have been set.
     *
     * @throws Exception in the event of misconfiguration (such as failure to set an
     *                   essential property) or if initialization fails for any other reason
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        executorService = new ThreadPoolExecutor(threadPoolConfigurationProperties.getMinConnection()
                ,threadPoolConfigurationProperties.getMaxConnection(),5, TimeUnit.MILLISECONDS,new ArrayBlockingQueue<>(300)
                ,r -> { Thread thread = new Thread(r, "Dynamic-"); thread.setDaemon(true);return thread;},new ThreadPoolExecutor.AbortPolicy());
    }
}

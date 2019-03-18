package net.qicp.aaron.config;

import net.qicp.aaron.component.TaskThreadPoolProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author Aaron
 * @Description 配置线程池
 * @Version 1.0
 * @Date 10:40 2019/3/18
 */
@Configuration
@EnableAsync
public class TaskExecutePoolConfiguration  {

    @Autowired
    private TaskThreadPoolProperties configProperties;

    @Bean
    public Executor myTaskAsyncPool() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(configProperties.getCorePoolSize());
        executor.setMaxPoolSize(configProperties.getMaxPoolSize());
        executor.setQueueCapacity(configProperties.getQueueCapacity());
        executor.setKeepAliveSeconds(configProperties.getKeepAliveSeconds());
        executor.setThreadNamePrefix(configProperties.getThreadNamePrefix());

        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是由调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 等待任务在关机时完成--表明等待所有线程执行完
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.initialize();
        return executor;
    }

}

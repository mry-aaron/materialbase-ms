package net.qicp.aaron.component;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author Aaron
 * @Description 配置线程属性类
 * @Version 1.0
 * @Date 10:30 2019/3/18
 */
@ConfigurationProperties(prefix = "task.thread.pool")
public class TaskThreadPoolProperties {

    private int corePoolSize;           // 核心线程数
    private int maxPoolSize;            // 最大线程数
    private int keepAliveSeconds;       // 线程池维护线程所允许的空闲时间，默认为60s
    private int queueCapacity;          // 队列最大长度
    private String threadNamePrefix;    // 线程名称前缀

    public int getCorePoolSize() {
        return corePoolSize;
    }
    public void setCorePoolSize(int corePoolSize) {
        this.corePoolSize = corePoolSize;
    }
    public int getMaxPoolSize() {
        return maxPoolSize;
    }
    public void setMaxPoolSize(int maxPoolSize) {
        this.maxPoolSize = maxPoolSize;
    }
    public int getKeepAliveSeconds() {
        return keepAliveSeconds;
    }
    public void setKeepAliveSeconds(int keepAliveSeconds) {
        this.keepAliveSeconds = keepAliveSeconds;
    }
    public int getQueueCapacity() {
        return queueCapacity;
    }
    public void setQueueCapacity(int queueCapacity) {
        this.queueCapacity = queueCapacity;
    }
    public String getThreadNamePrefix() {
        return threadNamePrefix;
    }
    public void setThreadNamePrefix(String threadNamePrefix) {
        this.threadNamePrefix = threadNamePrefix;
    }
}

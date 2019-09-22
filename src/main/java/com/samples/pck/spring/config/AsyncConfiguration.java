package com.samples.pck.spring.config;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

@Component
public class AsyncConfiguration {

    @Bean("asyncThreadPool")
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(7);
        executor.setMaxPoolSize(42);
        executor.setQueueCapacity(11);
        executor.setThreadNamePrefix("asyncThreadPool ---");
        executor.initialize();
        return executor;
    }
}

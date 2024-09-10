package com.poc.config;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class AsyncConfig  implements AsyncConfigurer {
	/*
	 * 
	 * Core Pool Size: Minimum number of threads to keep alive.
Max Pool Size: Maximum number of threads to allow.
Queue Capacity: Capacity of the queue to hold tasks before they are executed.
Thread Name Prefix: Custom prefix for naming threads.
	 * */

	@Override
	public Executor getAsyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(2);
		executor.setMaxPoolSize(2);
		executor.setQueueCapacity(20);
		executor.setThreadNamePrefix("POCManagement-");
		executor.initialize();
		return executor;
	}

	@Override
	public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
		return new AsyncExceptionHandler();
	}
}

class AsyncExceptionHandler implements AsyncUncaughtExceptionHandler {
	private static final Logger logger = LoggerFactory.getLogger(AsyncExceptionHandler.class);

	@Override
	public void handleUncaughtException(Throwable throwable, Method method, Object... obj) {
		logger.error("Exception Cause - " + throwable.getMessage());
		logger.error("Method name - " + method.getName());
		for (Object param : obj) {
			logger.error("Parameter value - " + param);
		}
	}
}
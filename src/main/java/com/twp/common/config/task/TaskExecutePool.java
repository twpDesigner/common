//package com.twp.common.config.task;
//import com.efivestar.eep.jobs.task.ITaskHandler;
//import com.efivestar.eep.jobs.task.TaskTuple;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.annotation.Async;
//import org.springframework.scheduling.annotation.EnableAsync;
//import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.concurrent.Executor;
//import java.util.concurrent.ThreadPoolExecutor;
//
///**
// * Created by tanweiping on 17/8/16.
// */
//@Configuration
//@EnableAsync
//public class TaskExecutePool {
//
//    @Bean
//    public Executor myTaskAsyncPool() {
//        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//        executor.setCorePoolSize(10);
//        executor.setMaxPoolSize(50);
//        executor.setQueueCapacity(100);
//        executor.setKeepAliveSeconds(300);
//        executor.setThreadNamePrefix("MyExecutor-taskJob");
//
//        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
//        // CALLER_RUNS：不在新线程中执行任务，而是由调用者所在的线程来执行
//        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
//        executor.initialize();
//        return executor;
//    }
//
//    @Async("myTaskAsyncPool")  //myTaskAsynPool即配置线程池的方法名，此处如果不写自定义线程池的方法名，会使用默认的线程池
//    @Transactional
//    public void doNotifyTask(ITaskHandler handler,TaskTuple taskTuple) throws InterruptedException{
//            handler.run(taskTuple);
//    }
//}

package com.felipeporge.sample.executors;

import com.felipeporge.sample.domain.executors.TaskExecutor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * This class represents the background executor to process tasks.
 *
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 23/11/2016
 */
public class BackgroundExecutor implements TaskExecutor {

    private static final int CORE_POOL_SIZE = 3;
    private static final int MAX_POOL_SIZE = 5;
    private static final int KEEP_ALIVE_TIME = 120;
    private static final TimeUnit TIME_UNIT = TimeUnit.SECONDS;
    private static final BlockingQueue<Runnable> WORK_QUEUE = new LinkedBlockingQueue<>();

    private static BackgroundExecutor sInstance = null;

    private ThreadPoolExecutor threadPoolexecutor;

    /**
     * Private constructor method.
     */
    private BackgroundExecutor() {}

    /**
     * Gets the current background thread instance.
     * @return {@link BackgroundExecutor} instance.
     */
    public static BackgroundExecutor getInstance(){
        int corePoolSize = CORE_POOL_SIZE;
        int maxPoolSize = MAX_POOL_SIZE;
        int keepAliveTime = KEEP_ALIVE_TIME;
        TimeUnit timeUnit = TIME_UNIT;
        BlockingQueue<Runnable> workQueue = WORK_QUEUE;

        if(sInstance == null) {
            sInstance = new BackgroundExecutor();
            sInstance.threadPoolexecutor = new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveTime, timeUnit, workQueue);
        }
        return sInstance;
    }

    @Override
    public void run(Runnable runnable) {
        if(runnable == null){
            throw  new IllegalArgumentException("Trying to run a null task.");
        }

        threadPoolexecutor.submit(runnable);
    }
}

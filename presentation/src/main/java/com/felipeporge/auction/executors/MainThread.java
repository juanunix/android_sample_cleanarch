package com.felipeporge.auction.executors;

import android.os.Handler;
import android.os.Looper;

import com.felipeporge.auction.domain.executors.PostExecutorThread;

/**
 * This class represents the main Android thread.
 *
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 23/11/2016
 */
public class MainThread implements PostExecutorThread {

    private static MainThread sInstance = null;

    private Handler mHandler;

    /**
     * Constructor method.
     */
    private MainThread(){
        mHandler = new Handler(Looper.getMainLooper());
    }

    /**
     * Gets the current instance of the main thread singleton.
     * @return {@link MainThread} instance.
     */
    public static MainThread getInstance(){
        if(sInstance == null){
            sInstance = new MainThread();
        }
        return sInstance;
    }

    @Override
    public void post(Runnable runnable) {
        mHandler.post(runnable);
    }
}

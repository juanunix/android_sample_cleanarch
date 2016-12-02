package com.felipeporge.sample.di.modules;

import com.felipeporge.sample.BaseApplication;
import com.felipeporge.sample.domain.executors.PostExecutorThread;
import com.felipeporge.sample.domain.executors.TaskExecutor;
import com.felipeporge.sample.executors.BackgroundExecutor;
import com.felipeporge.sample.executors.MainThread;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * This class represents the module with the application providers.
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 23/11/16
 */
@Module
public class AppModule {

    private BaseApplication mApplication;

    /**
     * Constructor method.
     * @param application - {@link BaseApplication} instance.
     */
    public AppModule(BaseApplication application){
        this.mApplication = application;
    }

    @Provides
    @Singleton
    BaseApplication providesApplication(){
        return mApplication;
    }

    @Provides
    @Singleton
    TaskExecutor providesExecutorScheduler(){
        return BackgroundExecutor.getInstance();
    }

    @Provides
    @Singleton
    PostExecutorThread providesPostExecutorScheduler(){
        return  MainThread.getInstance();
    }
}

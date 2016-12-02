package com.felipeporge.sample;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.felipeporge.sample.di.components.AppComponent;
import com.felipeporge.sample.di.components.DaggerAppComponent;
import com.felipeporge.sample.di.interfaces.HasComponent;
import com.felipeporge.sample.di.modules.AppModule;
import com.felipeporge.sample.di.modules.entities.ItemModule;

/**
 * This class represents an Android application.
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 22/11/16
 */
public class BaseApplication extends Application implements HasComponent<AppComponent> {

    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeInjector();
    }

    /**
     * Initializes dependency injections.
     * @return - App component instance.
     */
    private void initializeInjector(){
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .itemModule(new ItemModule())
                .build();
    }

    @Override
    public AppComponent getComponent() {
        return mAppComponent;
    }

    /**
     * Gets app component.
     * @param context - The context.
     * @return - App component instance.
     */
    public static AppComponent getComponent(@NonNull Context context) {
        return from(context).getComponent();
    }

    /**
     * Gets the application.
     * @param context - The context.
     * @return - App instance.
     */
    public static BaseApplication from(@NonNull Context context) {
        return (BaseApplication) context.getApplicationContext();
    }
}

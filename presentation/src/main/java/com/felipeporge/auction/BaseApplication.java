package com.felipeporge.auction;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.felipeporge.auction.data.database.DatabaseHelper;
import com.felipeporge.auction.data.database.daos.ItemDao;
import com.felipeporge.auction.di.components.AppComponent;
import com.felipeporge.auction.di.components.DaggerAppComponent;
import com.felipeporge.auction.di.interfaces.HasComponent;
import com.felipeporge.auction.di.modules.AppModule;
import com.felipeporge.auction.di.modules.PrefsModule;
import com.felipeporge.auction.di.modules.entities.BidModule;
import com.felipeporge.auction.di.modules.entities.ItemModule;
import com.felipeporge.auction.di.modules.entities.UserModule;

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

        try {
            new ItemDao(DatabaseHelper.getDatabase(this)).getAllUserWonItems(2);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Initializes dependency injections.
     * @return - App component instance.
     */
    private void initializeInjector(){
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .prefsModule(new PrefsModule())
                .userModule(new UserModule())
                .itemModule(new ItemModule())
                .bidModule(new BidModule())
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

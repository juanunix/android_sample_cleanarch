package com.felipeporge.sample.di.components;

import com.felipeporge.sample.di.interfaces.DaggerComponent;
import com.felipeporge.sample.di.modules.AppModule;
import com.felipeporge.sample.di.modules.entities.ItemModule;
import com.felipeporge.sample.di.modules.views.ItemDetailsModule;
import com.felipeporge.sample.di.modules.views.LoginModule;
import com.felipeporge.sample.di.modules.views.MainModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * This class represents the app component.
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 23/11/16
 */
@Singleton
@Component(
        modules = {
            AppModule.class, ItemModule.class
        }
)
public interface AppComponent extends DaggerComponent {

    LoginViewComponent plus(LoginModule loginModule);

    MainViewComponent plus(MainModule mainModule);

    ItemDetailsViewComponent plus(ItemDetailsModule itemDetailsModule);
}

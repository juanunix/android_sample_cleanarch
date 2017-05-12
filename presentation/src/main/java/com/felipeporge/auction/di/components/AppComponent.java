package com.felipeporge.auction.di.components;

import com.felipeporge.auction.di.interfaces.DaggerComponent;
import com.felipeporge.auction.di.modules.AppModule;
import com.felipeporge.auction.di.modules.PrefsModule;
import com.felipeporge.auction.di.modules.entities.BidModule;
import com.felipeporge.auction.di.modules.entities.ItemModule;
import com.felipeporge.auction.di.modules.entities.UserModule;
import com.felipeporge.auction.di.modules.views.AddEditItemModule;
import com.felipeporge.auction.di.modules.views.ItemDetailsModule;
import com.felipeporge.auction.di.modules.views.LoginModule;
import com.felipeporge.auction.di.modules.views.MainModule;
import com.felipeporge.auction.di.modules.views.SignUpModule;

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
            AppModule.class, PrefsModule.class,
            UserModule.class, ItemModule.class, BidModule.class
        }
)
public interface AppComponent extends DaggerComponent {

    SignUpViewComponent plus(SignUpModule presenterModule);

    LoginViewComponent plus(LoginModule loginModule);

    MainViewComponent plus(MainModule mainModule);

    AddEditItemViewComponent plus(AddEditItemModule addEditItemModule);

    ItemDetailsViewComponent plus(ItemDetailsModule itemDetailsModule);
}

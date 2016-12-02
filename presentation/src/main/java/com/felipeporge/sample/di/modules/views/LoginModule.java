package com.felipeporge.sample.di.modules.views;

import com.felipeporge.sample.di.interfaces.ActivityScope;
import com.felipeporge.sample.presentation.presenters.LoginViewPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * This class represents the login module.
 *
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 23/11/2016
 */
@Module
public class LoginModule {

    /* PRESENTER PROVIDERS - BEGIN */

    @Provides
    @ActivityScope
    LoginViewPresenter providesSignUpPresenter(){

        return new LoginViewPresenter();
    }

    /* PRESENTER PROVIDERS - END */
}

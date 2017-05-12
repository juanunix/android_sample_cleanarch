package com.felipeporge.auction.di.components;

import com.felipeporge.auction.di.interfaces.ActivityScope;
import com.felipeporge.auction.di.interfaces.DaggerComponent;
import com.felipeporge.auction.di.modules.views.LoginModule;
import com.felipeporge.auction.presentation.views.activities.LoginActivity;

import dagger.Subcomponent;

/**
 * This class represents the login view module.
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 9/23/16
 */
@ActivityScope
@Subcomponent(modules = {LoginModule.class})
public interface LoginViewComponent extends DaggerComponent {

    void inject(LoginActivity loginActivity);
}

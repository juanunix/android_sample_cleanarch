package com.felipeporge.sample.di.components;

import com.felipeporge.sample.di.interfaces.ActivityScope;
import com.felipeporge.sample.di.interfaces.DaggerComponent;
import com.felipeporge.sample.di.modules.views.LoginModule;
import com.felipeporge.sample.presentation.views.activities.LoginActivity;

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

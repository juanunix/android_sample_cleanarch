package com.felipeporge.auction.di.components;

import com.felipeporge.auction.di.interfaces.ActivityScope;
import com.felipeporge.auction.di.interfaces.DaggerComponent;
import com.felipeporge.auction.di.modules.views.SignUpModule;
import com.felipeporge.auction.presentation.views.activities.SignUpActivity;

import dagger.Subcomponent;

/**
 * This class represents the signup view module.
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 9/23/16
 */
@ActivityScope
@Subcomponent(modules = {SignUpModule.class})
public interface SignUpViewComponent extends DaggerComponent {

    void inject(SignUpActivity signUpActivity);
}

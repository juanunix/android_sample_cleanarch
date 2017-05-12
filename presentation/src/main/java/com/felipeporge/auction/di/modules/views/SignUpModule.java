package com.felipeporge.auction.di.modules.views;

import com.felipeporge.auction.di.interfaces.ActivityScope;
import com.felipeporge.auction.domain.interactors.users.CreateUserUseCase;
import com.felipeporge.auction.domain.interactors.users.GetUserByEmailUseCase;
import com.felipeporge.auction.presentation.mapper.UserModelMapper;
import com.felipeporge.auction.presentation.presenters.SignUpViewPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * This class represents the sign up module.
 *
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 23/11/2016
 */
@Module
public class SignUpModule {

    /* PRESENTER PROVIDERS - BEGIN */

    @Provides
    @ActivityScope
    SignUpViewPresenter providesSignUpPresenter(UserModelMapper userModelMapper, CreateUserUseCase createUserUseCase,
                                                GetUserByEmailUseCase getUserByEmailUseCase){
        return new SignUpViewPresenter(userModelMapper, createUserUseCase, getUserByEmailUseCase);
    }

    /* PRESENTER PROVIDERS - END */
}

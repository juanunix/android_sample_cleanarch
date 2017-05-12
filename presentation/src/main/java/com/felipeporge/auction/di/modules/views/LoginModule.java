package com.felipeporge.auction.di.modules.views;

import com.felipeporge.auction.di.interfaces.ActivityScope;
import com.felipeporge.auction.domain.interactors.prefs.GetShowedWalkthroughUseCase;
import com.felipeporge.auction.domain.interactors.prefs.PutShowedWalkthroughUseCase;
import com.felipeporge.auction.domain.interactors.users.GetUserByCredentialsUseCase;
import com.felipeporge.auction.domain.interactors.users.GetUserByEmailUseCase;
import com.felipeporge.auction.domain.interactors.users.UpdateUserUseCase;
import com.felipeporge.auction.presentation.mapper.UserModelMapper;
import com.felipeporge.auction.presentation.presenters.LoginViewPresenter;

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
    LoginViewPresenter providesSignUpPresenter(UserModelMapper userModelMapper,
                                               GetUserByCredentialsUseCase getUserByCredentials,
                                               GetUserByEmailUseCase getUserByEmailUseCase,
                                               UpdateUserUseCase updateUserUseCase,
                                               GetShowedWalkthroughUseCase getShowedWalkthroughUseCase,
                                               PutShowedWalkthroughUseCase putShowedWalkthroughUseCase){

        return new LoginViewPresenter(userModelMapper, getUserByCredentials, getUserByEmailUseCase,
                updateUserUseCase, getShowedWalkthroughUseCase, putShowedWalkthroughUseCase);
    }

    /* PRESENTER PROVIDERS - END */
}

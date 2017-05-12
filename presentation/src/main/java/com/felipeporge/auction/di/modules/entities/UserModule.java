package com.felipeporge.auction.di.modules.entities;

import com.felipeporge.auction.BaseApplication;
import com.felipeporge.auction.data.database.DatabaseHelper;
import com.felipeporge.auction.data.database.daos.UserDao;
import com.felipeporge.auction.data.mappers.UserMapper;
import com.felipeporge.auction.data.repositories.UserRepositoryImpl;
import com.felipeporge.auction.domain.executors.PostExecutorThread;
import com.felipeporge.auction.domain.executors.TaskExecutor;
import com.felipeporge.auction.domain.interactors.users.CreateUserUseCase;
import com.felipeporge.auction.domain.interactors.users.GetUserByCredentialsUseCase;
import com.felipeporge.auction.domain.interactors.users.GetUserByEmailUseCase;
import com.felipeporge.auction.domain.interactors.users.UpdateUserUseCase;
import com.felipeporge.auction.domain.repositories.UserRepository;
import com.felipeporge.auction.presentation.mapper.UserModelMapper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * This class represents the module that provides all user management dependencies.
 *
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 23/11/2016
 */
@Module
public class UserModule {

    /* Data Layer - BEGIN */

    @Provides
    @Singleton
    UserDao providesUserDao(BaseApplication app){
        return new UserDao(DatabaseHelper.getDatabase(app));
    }

    @Provides
    @Singleton
    UserMapper providesUserMapper(){
        return new UserMapper();
    }

    @Provides
    @Singleton
    UserRepository providesUserRepo(UserMapper mapper, UserDao dao){
        return new UserRepositoryImpl(mapper, dao);
    }

    /* Data Layer - END */


    /* Domain Layer - BEGIN */

    @Provides
    @Singleton
    CreateUserUseCase providesCreateUserUseCase(TaskExecutor executor, PostExecutorThread postExecutor, UserRepository repo){
        return new CreateUserUseCase(executor, postExecutor, repo);
    }

    @Provides
    @Singleton
    GetUserByEmailUseCase providesGetUserByEmailUseCase(TaskExecutor executor, PostExecutorThread postExecutor, UserRepository repo){
        return new GetUserByEmailUseCase(executor, postExecutor, repo);
    }


    @Provides
    @Singleton
    GetUserByCredentialsUseCase providesGetUserByCredentialsUc(TaskExecutor executor, PostExecutorThread postExecutor, UserRepository repo){
        return new GetUserByCredentialsUseCase(executor, postExecutor, repo);
    }

    @Provides
    @Singleton
    UpdateUserUseCase providesUpdateUserUc(TaskExecutor executor, PostExecutorThread postExecutor, UserRepository repo){
        return new UpdateUserUseCase(executor, postExecutor, repo);
    }

    /* Domain Layer - END */

    /* Presentation Layer - BEGIN */

    @Provides
    @Singleton
    UserModelMapper providesUserModelMapper(){
        return new UserModelMapper();
    }

    /* Presentation Layer - END */

}

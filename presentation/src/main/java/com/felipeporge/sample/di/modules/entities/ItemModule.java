package com.felipeporge.sample.di.modules.entities;

import com.felipeporge.sample.BaseApplication;
import com.felipeporge.sample.data.database.DatabaseHelper;
import com.felipeporge.sample.data.database.daos.ItemDao;
import com.felipeporge.sample.data.mappers.ItemMapper;
import com.felipeporge.sample.data.repositories.ItemRepositoryImpl;
import com.felipeporge.sample.domain.executors.PostExecutorThread;
import com.felipeporge.sample.domain.executors.TaskExecutor;
import com.felipeporge.sample.domain.interactors.items.GetAllItemsUseCase;
import com.felipeporge.sample.domain.repositories.ItemRepository;
import com.felipeporge.sample.presentation.mapper.ItemModelMapper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * This class represents the module that provides all item management dependencies.
 *
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 23/11/2016
 */
@Module
public class ItemModule {

    /* Data Layer - BEGIN */

    @Provides
    @Singleton
    ItemDao providesItemDao(BaseApplication app){
        return new ItemDao(DatabaseHelper.getDatabase(app));
    }

    @Provides
    @Singleton
    ItemMapper providesItemMapper(){
        return new ItemMapper();
    }

    @Provides
    @Singleton
    ItemRepository providesItemRepo(ItemMapper mapper, ItemDao dao){
        return new ItemRepositoryImpl(mapper, dao);
    }

    /* Data Layer - END */


    /* Domain Layer - BEGIN */

    @Provides
    @Singleton
    GetAllItemsUseCase providesGetAllItemsUseCase(TaskExecutor executor, PostExecutorThread postExecutor, ItemRepository repo){
        return new GetAllItemsUseCase(executor, postExecutor, repo);
    }

    /* Domain Layer - END */

    /* Presentation Layer - BEGIN */

    @Provides
    @Singleton
    ItemModelMapper providesItemModelMapper(){
        return new ItemModelMapper();
    }

    /* Presentation Layer - END */

}

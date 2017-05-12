package com.felipeporge.auction.di.modules.entities;

import com.felipeporge.auction.BaseApplication;
import com.felipeporge.auction.data.database.DatabaseHelper;
import com.felipeporge.auction.data.database.daos.ItemDao;
import com.felipeporge.auction.data.mappers.BidMapper;
import com.felipeporge.auction.data.mappers.ItemMapper;
import com.felipeporge.auction.data.repositories.ItemRepositoryImpl;
import com.felipeporge.auction.domain.executors.PostExecutorThread;
import com.felipeporge.auction.domain.executors.TaskExecutor;
import com.felipeporge.auction.domain.interactors.items.CreateItemUseCase;
import com.felipeporge.auction.domain.interactors.items.GetAllAuctionItemsUseCase;
import com.felipeporge.auction.domain.interactors.items.GetBiddingItemsUseCase;
import com.felipeporge.auction.domain.interactors.items.GetStoreItemsUseCase;
import com.felipeporge.auction.domain.interactors.items.GetWonItemsUseCase;
import com.felipeporge.auction.domain.interactors.items.UpdateItemUseCase;
import com.felipeporge.auction.domain.repositories.ItemRepository;
import com.felipeporge.auction.presentation.mapper.BidModelMapper;
import com.felipeporge.auction.presentation.mapper.ItemModelMapper;

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
    ItemMapper providesItemMapper(BidMapper bidMapper){
        return new ItemMapper(bidMapper);
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
    CreateItemUseCase providesCreateItemUseCase(TaskExecutor executor, PostExecutorThread postExecutor, ItemRepository repo){
        return new CreateItemUseCase(executor, postExecutor, repo);
    }

    @Provides
    @Singleton
    GetAllAuctionItemsUseCase providesGetAllAuctionItemsUseCase(TaskExecutor executor, PostExecutorThread postExecutor, ItemRepository repo){
        return new GetAllAuctionItemsUseCase(executor, postExecutor, repo);
    }


    @Provides
    @Singleton
    GetStoreItemsUseCase providesGetStoreItemsUseCase(TaskExecutor executor, PostExecutorThread postExecutor, ItemRepository repo){
        return new GetStoreItemsUseCase(executor, postExecutor, repo);
    }

    @Provides
    @Singleton
    GetBiddingItemsUseCase providesGetBiddingItemsUseCase(TaskExecutor executor, PostExecutorThread postExecutor, ItemRepository repo){
        return new GetBiddingItemsUseCase(executor, postExecutor, repo);
    }

    @Provides
    @Singleton
    GetWonItemsUseCase providesGetWonItemsUseCase(TaskExecutor executor, PostExecutorThread postExecutor, ItemRepository repo){
        return new GetWonItemsUseCase(executor, postExecutor, repo);
    }

    @Provides
    @Singleton
    UpdateItemUseCase providesUpdateItemUseCase(TaskExecutor executor, PostExecutorThread postExecutor, ItemRepository repo){
        return new UpdateItemUseCase(executor, postExecutor, repo);
    }

    /* Domain Layer - END */

    /* Presentation Layer - BEGIN */

    @Provides
    @Singleton
    ItemModelMapper providesItemModelMapper(BidModelMapper bidModelMapper){
        return new ItemModelMapper(bidModelMapper);
    }

    /* Presentation Layer - END */

}

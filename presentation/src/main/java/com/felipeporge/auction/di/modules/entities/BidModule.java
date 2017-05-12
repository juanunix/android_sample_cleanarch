package com.felipeporge.auction.di.modules.entities;

import com.felipeporge.auction.BaseApplication;
import com.felipeporge.auction.data.database.DatabaseHelper;
import com.felipeporge.auction.data.database.daos.BidDao;
import com.felipeporge.auction.data.mappers.BidMapper;
import com.felipeporge.auction.data.repositories.BidRepositoryImpl;
import com.felipeporge.auction.domain.executors.PostExecutorThread;
import com.felipeporge.auction.domain.executors.TaskExecutor;
import com.felipeporge.auction.domain.interactors.bids.PlaceBidUseCase;
import com.felipeporge.auction.domain.repositories.BidRepository;
import com.felipeporge.auction.presentation.mapper.BidModelMapper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * This class represents the module that provides all bid management dependencies.
 *
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 23/11/2016
 */
@Module
public class BidModule {

    /* Data Layer - BEGIN */

    @Provides
    @Singleton
    BidDao providesBidDao(BaseApplication app){
        return new BidDao(DatabaseHelper.getDatabase(app));
    }

    @Provides
    @Singleton
    BidMapper providesBidMapper(){
        return new BidMapper();
    }

    @Provides
    @Singleton
    BidRepository providesBidRepo(BidMapper mapper, BidDao dao){
        return new BidRepositoryImpl(mapper, dao);
    }

    /* Data Layer - END */


    /* Domain Layer - BEGIN */

    @Provides
    @Singleton
    PlaceBidUseCase providesPlaceBidUseCase(TaskExecutor executor, PostExecutorThread postExecutor, BidRepository repo){
        return new PlaceBidUseCase(executor, postExecutor, repo);
    }

    /* Domain Layer - END */

    /* Presentation Layer - BEGIN */

    @Provides
    @Singleton
    BidModelMapper providesBidModelMapper(){
        return new BidModelMapper();
    }

    /* Presentation Layer - END */

}

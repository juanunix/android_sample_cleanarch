package com.felipeporge.auction.domain.interactors.items;

import com.felipeporge.auction.domain.entities.AuctionItem;
import com.felipeporge.auction.domain.executors.PostExecutorThread;
import com.felipeporge.auction.domain.executors.TaskExecutor;
import com.felipeporge.auction.domain.interactors.base.UseCase;
import com.felipeporge.auction.domain.repositories.ItemRepository;
import com.felipeporge.auction.domain.repositories.base.RepositoryCallback;

import java.util.ArrayList;

/**
 * This class represents the use case to get all user won items.
 *
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 23/11/2016
 */
public class GetWonItemsUseCase extends UseCase<ItemRepository, Long, ArrayList<AuctionItem>> {

    /**
     * Constructor method.
     * @param executor Executor thread.
     * @param postExecutor Post executor thread.
     * @param repository Repository.
     */
    public GetWonItemsUseCase(TaskExecutor executor, PostExecutorThread postExecutor, ItemRepository repository) {
        super(executor, postExecutor, repository);
    }

    @Override
    public void run() {
        long userId = getParams();

        getRepository().getWonItems(userId, new RepositoryCallback<ArrayList<AuctionItem>>() {
            @Override
            public void onRepoSuccess(ArrayList<AuctionItem> auctionItems) {
                GetWonItemsUseCase.this.notifySuccess(auctionItems);
            }

            @Override
            public void onRepoFailure(Exception exception) {
                GetWonItemsUseCase.this.notifyFailure(exception);
            }
        });
    }
}
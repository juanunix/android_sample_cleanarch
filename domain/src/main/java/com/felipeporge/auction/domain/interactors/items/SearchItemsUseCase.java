package com.felipeporge.auction.domain.interactors.items;

import com.felipeporge.auction.domain.entities.AuctionItem;
import com.felipeporge.auction.domain.executors.PostExecutorThread;
import com.felipeporge.auction.domain.executors.TaskExecutor;
import com.felipeporge.auction.domain.interactors.base.UseCase;
import com.felipeporge.auction.domain.repositories.ItemRepository;
import com.felipeporge.auction.domain.repositories.base.RepositoryCallback;

import java.util.ArrayList;

/**
 * This class represents the use case to search an item using a keyword.
 *
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 23/11/2016
 */
public class SearchItemsUseCase extends UseCase<ItemRepository, String, ArrayList<AuctionItem>> {

    /**
     * Constructor method.
     * @param executor Executor thread.
     * @param postExecutor Post executor thread.
     * @param repository Repository.
     */
    public SearchItemsUseCase(TaskExecutor executor, PostExecutorThread postExecutor, ItemRepository repository) {
        super(executor, postExecutor, repository);
    }

    @Override
    public void run() {
        String keyword = getParams();
        getRepository().searchForItems(keyword, new RepositoryCallback<ArrayList<AuctionItem>>() {
            @Override
            public void onRepoSuccess(ArrayList<AuctionItem> auctionItems) {
                SearchItemsUseCase.this.notifySuccess(auctionItems);
            }

            @Override
            public void onRepoFailure(Exception exception) {
                SearchItemsUseCase.this.notifyFailure(exception);
            }
        });
    }
}
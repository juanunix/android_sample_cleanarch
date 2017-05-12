package com.felipeporge.auction.domain.interactors.items;

import com.felipeporge.auction.domain.entities.AuctionItem;
import com.felipeporge.auction.domain.executors.PostExecutorThread;
import com.felipeporge.auction.domain.executors.TaskExecutor;
import com.felipeporge.auction.domain.interactors.base.UseCase;
import com.felipeporge.auction.domain.repositories.ItemRepository;
import com.felipeporge.auction.domain.repositories.base.RepositoryCallback;

import java.security.InvalidParameterException;

/**
 * This class represents the use case to update an item.
 *
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 23/11/2016
 */
public class UpdateItemUseCase extends UseCase<ItemRepository, AuctionItem, AuctionItem> {

    /**
     * Constructor method.
     * @param executor Executor thread.
     * @param postExecutor Post executor thread.
     * @param repository Repository.
     */
    public UpdateItemUseCase(TaskExecutor executor, PostExecutorThread postExecutor, ItemRepository repository) {
        super(executor, postExecutor, repository);
    }

    @Override
    public void run() {
        AuctionItem item = getParams();
        if(item == null){
            notifyFailure(new InvalidParameterException());
        }else {
            getRepository().updateItem(item, new RepositoryCallback<AuctionItem>() {
                @Override
                public void onRepoSuccess(AuctionItem auctionItem) {
                    UpdateItemUseCase.this.notifySuccess(auctionItem);
                }

                @Override
                public void onRepoFailure(Exception exception) {
                    UpdateItemUseCase.this.notifyFailure(exception);
                }
            });
        }
    }
}

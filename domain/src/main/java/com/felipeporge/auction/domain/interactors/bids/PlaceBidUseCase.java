package com.felipeporge.auction.domain.interactors.bids;

import com.felipeporge.auction.domain.entities.AuctionBid;
import com.felipeporge.auction.domain.executors.PostExecutorThread;
import com.felipeporge.auction.domain.executors.TaskExecutor;
import com.felipeporge.auction.domain.interactors.base.UseCase;
import com.felipeporge.auction.domain.repositories.BidRepository;
import com.felipeporge.auction.domain.repositories.base.RepositoryCallback;

import java.security.InvalidParameterException;

/**
 * This class represents the use case to place a new bid.
 *
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 23/11/2016
 */
public class PlaceBidUseCase extends UseCase<BidRepository, AuctionBid, AuctionBid> {

    /**
     * Constructor method.
     * @param executor Executor thread.
     * @param postExecutor Post executor thread.
     * @param repository Repository.
     */
    public PlaceBidUseCase(TaskExecutor executor, PostExecutorThread postExecutor, BidRepository repository) {
        super(executor, postExecutor, repository);
    }

    @Override
    public void run() {
        AuctionBid bid = getParams();
        if(bid == null){
            PlaceBidUseCase.this.notifyFailure(new InvalidParameterException());
        }else {
            getRepository().createBid(bid, new RepositoryCallback<AuctionBid>() {
                @Override
                public void onRepoSuccess(AuctionBid auctionBid) {
                    PlaceBidUseCase.this.notifySuccess(auctionBid);
                }

                @Override
                public void onRepoFailure(Exception exception) {
                    PlaceBidUseCase.this.notifyFailure(exception);
                }
            });
        }
    }
}

package com.felipeporge.auction.domain.repositories;

import com.felipeporge.auction.domain.entities.AuctionBid;
import com.felipeporge.auction.domain.repositories.base.RepositoryCallback;

/**
 * This class represents the placed bid repository.
 *
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 23/11/2016
 */
public interface BidRepository {

    /**
     * Creates a new bid.
     * @param bid Bid to create.
     * @param callback Callback.
     */
    void createBid(AuctionBid bid, RepositoryCallback<AuctionBid> callback);
}

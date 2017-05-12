package com.felipeporge.auction.domain.repositories;

import com.felipeporge.auction.domain.entities.AuctionItem;
import com.felipeporge.auction.domain.repositories.base.RepositoryCallback;

import java.util.ArrayList;

/**
 * This class represents the auction item repository.
 *
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 23/11/2016
 */
public interface ItemRepository {

    /**
     * Creates an auction item.
     * @param item Auction item to create.
     * @param callback Callback.
     */
    void createItem(AuctionItem item, RepositoryCallback<AuctionItem> callback);

    /**
     * Updates an auction item.
     * @param item Auction item to update.
     * @param callback Callback.
     */
    void updateItem(AuctionItem item, RepositoryCallback<AuctionItem> callback);

    /**
     * Gets user bidding items.
     * @param userId User id.
     * @param callback Callback.
     */
    void getBiddingItems(long userId, RepositoryCallback<ArrayList<AuctionItem>> callback);

    /**
     * Gets store items.
     * @param userId User id.
     * @param callback Callback.
     */
    void getStoreItems(long userId, RepositoryCallback<ArrayList<AuctionItem>> callback);

    /**
     * Gets won items.
     * @param userId User id.
     * @param callback Callback.
     */
    void getWonItems(long userId, RepositoryCallback<ArrayList<AuctionItem>> callback);

    /**
     * Gets all visible items in auction.
     * @param userId Logged user id.
     * @param callback Callback.
     */
    void getAllItemsInAuction(long userId, RepositoryCallback<ArrayList<AuctionItem>> callback);

    /**
     * Searchs fo items.
     * @param keyword Search keyword.
     * @param callback Callback.
     */
    void searchForItems(String keyword, RepositoryCallback<ArrayList<AuctionItem>> callback);
}

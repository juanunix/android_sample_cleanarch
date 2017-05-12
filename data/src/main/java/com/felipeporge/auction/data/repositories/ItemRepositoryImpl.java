package com.felipeporge.auction.data.repositories;

import com.felipeporge.auction.data.Bot;
import com.felipeporge.auction.data.database.daos.ItemDao;
import com.felipeporge.auction.data.entities.Item;
import com.felipeporge.auction.data.mappers.ItemMapper;
import com.felipeporge.auction.domain.entities.AuctionItem;
import com.felipeporge.auction.domain.repositories.ItemRepository;
import com.felipeporge.auction.domain.repositories.base.RepositoryCallback;

import java.util.ArrayList;

/**
 * This class represents represents the implementation of an item repository.
 *
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 24/11/2016
 */
public class ItemRepositoryImpl implements ItemRepository {

    private ItemMapper mMapper;
    private ItemDao mItemDao;

    /**
     * Constructor method.
     * @param mapper Data mapper.
     * @param dao Item dao.
     */
    public ItemRepositoryImpl(ItemMapper mapper, ItemDao dao){
        mMapper = mapper;
        mItemDao = dao;
    }

    @Override
    public void createItem(AuctionItem item, RepositoryCallback<AuctionItem> callback) {
        try {
            Item createdItem = mItemDao.create(mMapper.transform(item));
            Bot.placeNewBid(mItemDao.getDatabase());
            callback.onRepoSuccess(mMapper.parseBack(createdItem));
        }catch (Exception e){
            e.printStackTrace();
            callback.onRepoFailure(e);
        }
    }

    @Override
    public void updateItem(AuctionItem item, RepositoryCallback<AuctionItem> callback) {
        try {
            Item updatedItem = mItemDao.update(mMapper.transform(item));
            Bot.placeNewBid(mItemDao.getDatabase());
            callback.onRepoSuccess(mMapper.parseBack(updatedItem));
        }catch (Exception e){
            e.printStackTrace();
            callback.onRepoFailure(e);
        }
    }

    @Override
    public void getBiddingItems(long userId, RepositoryCallback<ArrayList<AuctionItem>> callback) {
        try {
            ArrayList<Item> items = mItemDao.getAllUserBiddingItems(userId);
            Bot.placeNewBid(mItemDao.getDatabase());
            callback.onRepoSuccess(mMapper.parseBack(items));
        }catch (Exception e){
            e.printStackTrace();
            callback.onRepoFailure(e);
        }
    }

    @Override
    public void getStoreItems(long userId, RepositoryCallback<ArrayList<AuctionItem>> callback) {
        try {
            ArrayList<Item> items = mItemDao.getAllUserItems(userId);
            Bot.placeNewBid(mItemDao.getDatabase());
            callback.onRepoSuccess(mMapper.parseBack(items));
        }catch (Exception e){
            e.printStackTrace();
            callback.onRepoFailure(e);
        }
    }

    @Override
    public void getWonItems(long userId, RepositoryCallback<ArrayList<AuctionItem>> callback) {
        try {
            ArrayList<Item> items = mItemDao.getAllUserWonItems(userId);
            Bot.placeNewBid(mItemDao.getDatabase());
            callback.onRepoSuccess(mMapper.parseBack(items));
        }catch (Exception e){
            e.printStackTrace();
            callback.onRepoFailure(e);
        }
    }

    @Override
    public void getAllItemsInAuction(long userId, RepositoryCallback<ArrayList<AuctionItem>> callback) {
        try {
            ArrayList<Item> items = mItemDao.getAllItemsInAuction(userId);
            Bot.placeNewBid(mItemDao.getDatabase());
            callback.onRepoSuccess(mMapper.parseBack(items));
        }catch (Exception e){
            e.printStackTrace();
            callback.onRepoFailure(e);
        }
    }

    @Override
    public void searchForItems(String keyword, RepositoryCallback<ArrayList<AuctionItem>> callback) {
        try {
            ArrayList<Item> items = mItemDao.searchItems(keyword);
            Bot.placeNewBid(mItemDao.getDatabase());
            callback.onRepoSuccess(mMapper.parseBack(items));
        }catch (Exception e){
            e.printStackTrace();
            callback.onRepoFailure(e);
        }
    }
}

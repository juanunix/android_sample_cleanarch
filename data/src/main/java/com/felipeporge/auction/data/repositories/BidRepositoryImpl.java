package com.felipeporge.auction.data.repositories;

import com.felipeporge.auction.data.database.daos.BidDao;
import com.felipeporge.auction.data.entities.Bid;
import com.felipeporge.auction.data.mappers.BidMapper;
import com.felipeporge.auction.domain.entities.AuctionBid;
import com.felipeporge.auction.domain.repositories.BidRepository;
import com.felipeporge.auction.domain.repositories.base.RepositoryCallback;

/**
 * This class represents a bid repository implementation.
 *
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 24/11/2016
 */
public class BidRepositoryImpl implements BidRepository {

    private BidMapper mMapper;
    private BidDao mBidDao;

    /**
     * Constructor method.
     * @param mapper Data mapper.
     * @param dao Item dao.
     */
    public BidRepositoryImpl(BidMapper mapper, BidDao dao){
        mMapper = mapper;
        mBidDao = dao;
    }

    @Override
    public void createBid(AuctionBid bid, RepositoryCallback<AuctionBid> callback) {
        try {
            Bid createdBid = mBidDao.create(mMapper.transform(bid));
            callback.onRepoSuccess(mMapper.parseBack(createdBid));
        }catch (Exception e){
            e.printStackTrace();
            callback.onRepoFailure(e);
        }
    }
}

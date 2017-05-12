package com.felipeporge.auction.presentation.mapper;

import com.felipeporge.auction.domain.entities.AuctionItem;
import com.felipeporge.auction.domain.mapper.DataMapper;
import com.felipeporge.auction.presentation.model.AuctionItemModel;

/**
 * This class represents the item model mapper.
 *
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 24/11/2016
 */
public class ItemModelMapper extends DataMapper<AuctionItemModel, AuctionItem> {

    private BidModelMapper mBidMapper;

    /**
     * Constructor method.
     * @param bidMapper Bid model mapper instance.
     */
    public ItemModelMapper(BidModelMapper bidMapper){
        mBidMapper = bidMapper;
    }

    @Override
    public AuctionItem transform(AuctionItemModel object) {
        if(object == null)
            return null;

        AuctionItem item = new AuctionItem();
        item.setId(object.getId());
        item.setTitle(object.getTitle());
        item.setDescription(object.getDescription());
        item.setUserId(object.getUserId());
        item.setStartingBid(object.getStartingBid());
        item.setFinishAt(object.getEndDateMillis());
        item.setBidCount(object.getBidCount());
        item.setImg(object.getImg());
        item.setCurrentBid(mBidMapper.transform(object.getCurrentBid()));
        return item;
    }

    @Override
    public AuctionItemModel parseBack(AuctionItem object) {
        if(object == null)
            return null;

        AuctionItemModel item = new AuctionItemModel();
        item.setId(object.getId());
        item.setTitle(object.getTitle());
        item.setDescription(object.getDescription());
        item.setUserId(object.getUserId());
        item.setStartingBid(object.getStartingBid());
        item.setEndDateMillis(object.getFinishAt());
        item.setBidCount(object.getBidCount());
        item.setImg(object.getImg());
        item.setCurrentBid(mBidMapper.parseBack(object.getCurrentBid()));
        return item;
    }
}

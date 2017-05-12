package com.felipeporge.auction.data.mappers;

import com.felipeporge.auction.data.entities.Item;
import com.felipeporge.auction.domain.entities.AuctionItem;
import com.felipeporge.auction.domain.mapper.DataMapper;

/**
 * This class represents an item data mapper.
 *
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 24/11/2016
 */
public class ItemMapper extends DataMapper<AuctionItem, Item> {

    private BidMapper mBidMapper;

    /**
     * Constructor method.
     * @param bidMapper Bid mapper instance.
     */
    public ItemMapper(BidMapper bidMapper){
        mBidMapper = bidMapper;
    }

    @Override
    public Item transform(AuctionItem object) {
        if(object == null)
            return null;

        Item result = new Item();
        result.setId(object.getId());
        result.setUserId(object.getUserId());
        result.setBidCount(object.getBidCount());
        result.setTitle(object.getTitle());
        result.setDescription(object.getDescription());
        result.setImg(object.getImg());
        result.setFinishAt(object.getFinishAt());
        result.setStartingBid(object.getStartingBid());
        result.setCurrentBid(mBidMapper.transform(object.getCurrentBid()));
        return result;
    }

    @Override
    public AuctionItem parseBack(Item object) {
        if(object == null)
            return null;

        AuctionItem result = new AuctionItem();
        result.setId(object.getId());
        result.setUserId(object.getUserId());
        result.setBidCount(object.getBidCount());
        result.setTitle(object.getTitle());
        result.setDescription(object.getDescription());
        result.setImg(object.getImg());
        result.setFinishAt(object.getFinishAt());
        result.setStartingBid(object.getStartingBid());
        result.setCurrentBid(mBidMapper.parseBack(object.getCurrentBid()));

        return result;
    }
}

package com.felipeporge.auction.data.mappers;

import com.felipeporge.auction.data.entities.Bid;
import com.felipeporge.auction.domain.entities.AuctionBid;
import com.felipeporge.auction.domain.mapper.DataMapper;

/**
 * This class represents a bid data mapper.
 *
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 24/11/2016
 */
public class BidMapper extends DataMapper<AuctionBid, Bid> {

    @Override
    public Bid transform(AuctionBid object) {
        if(object == null)
            return null;

        Bid result = new Bid();
        result.setId(object.getId());
        result.setUserId(object.getUserId());
        result.setItemId(object.getItemId());
        result.setValue(object.getValue());
        result.setCreatedAt(object.getCreatedAt());
        return result;
    }

    @Override
    public AuctionBid parseBack(Bid object) {
        if(object == null)
            return null;

        AuctionBid result = new AuctionBid();
        result.setId(object.getId());
        result.setUserId(object.getUserId());
        result.setItemId(object.getItemId());
        result.setValue(object.getValue());
        result.setCreatedAt(object.getCreatedAt());
        return result;
    }
}

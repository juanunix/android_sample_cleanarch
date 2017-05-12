package com.felipeporge.auction.presentation.mapper;

import com.felipeporge.auction.domain.entities.AuctionBid;
import com.felipeporge.auction.domain.mapper.DataMapper;
import com.felipeporge.auction.presentation.model.AuctionBidModel;

/**
 * This class represents the bid model mapper.
 *
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 24/11/2016
 */
public class BidModelMapper extends DataMapper<AuctionBidModel, AuctionBid> {

    @Override
    public AuctionBidModel parseBack(AuctionBid object) {
        if(object == null)
            return null;

        AuctionBidModel result = new AuctionBidModel();
        result.setId(object.getId());
        result.setUserId(object.getUserId());
        result.setItemId(object.getItemId());
        result.setValue(object.getValue());
        result.setCreatedAt(object.getCreatedAt());
        return result;
    }

    @Override
    public AuctionBid transform(AuctionBidModel object) {
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

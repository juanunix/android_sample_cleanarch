package com.felipeporge.auction.presentation.mapper;

import com.felipeporge.auction.domain.entities.AuctionBid;
import com.felipeporge.auction.presentation.model.AuctionBidModel;

import junit.framework.Assert;

import org.junit.Test;

/**
 * This class represents the unit tests for {@link BidModelMapper}.
 *
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 24/11/2016
 */
public class BidModelMapperTest {

    @Test
    public void transform_parseBack() throws Exception {
        AuctionBidModel mockedBid = new AuctionBidModel();
        mockedBid.setId(1);
        mockedBid.setUserId(1);
        mockedBid.setItemId(1);
        mockedBid.setCreatedAt(12345678);
        mockedBid.setValue(50.12f);

        BidModelMapper modelMapper = new BidModelMapper();
        AuctionBid transfMockedBid = modelMapper.transform(mockedBid);

        Assert.assertEquals(mockedBid, modelMapper.parseBack(transfMockedBid));
    }

}
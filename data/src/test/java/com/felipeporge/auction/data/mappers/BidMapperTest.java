package com.felipeporge.auction.data.mappers;

import com.felipeporge.auction.data.entities.Bid;
import com.felipeporge.auction.domain.entities.AuctionBid;

import junit.framework.Assert;

import org.junit.Test;

/**
 * This class represents the unit tests for {@link BidMapper}.
 *
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 25/11/2016
 */
public class BidMapperTest {

    @Test
    public void transform_parseBack() throws Exception {
        Bid mockedBid = new Bid();
        mockedBid.setId(1);
        mockedBid.setUserId(1);
        mockedBid.setItemId(1);
        mockedBid.setCreatedAt(12345678);
        mockedBid.setValue(50.12f);

        BidMapper modelMapper = new BidMapper();
        AuctionBid parsedBackBid = modelMapper.parseBack(mockedBid);

        Assert.assertEquals(mockedBid, modelMapper.transform(parsedBackBid));
    }

}
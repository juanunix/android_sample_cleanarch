package com.felipeporge.auction.data.mappers;

import com.felipeporge.auction.data.entities.Bid;
import com.felipeporge.auction.data.entities.Item;
import com.felipeporge.auction.domain.entities.AuctionItem;

import org.junit.Assert;
import org.junit.Test;

/**
 * This class represents...
 *
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 25/11/2016
 */
public class ItemMapperTest {

    @Test
    public void transform_parseBack() throws Exception {
        Bid mockedBid = new Bid();
        mockedBid.setId(1);
        mockedBid.setUserId(1);
        mockedBid.setItemId(1);
        mockedBid.setCreatedAt(12345678);
        mockedBid.setValue(50.12f);

        Item mockedItem = new Item();
        mockedItem.setId(1);
        mockedItem.setUserId(1);
        mockedItem.setCurrentBid(mockedBid);
        mockedItem.setBidCount(1);
        mockedItem.setFinishAt(123456789);
        mockedItem.setTitle("Mocked item");
        mockedItem.setDescription("This is a test item.");
        mockedItem.setImg("http://www.img.com/img.png");
        mockedItem.setStartingBid(45.25f);

        ItemMapper modelMapper = new ItemMapper(new BidMapper());
        AuctionItem transfMockedItem = modelMapper.parseBack(mockedItem);

        Assert.assertEquals(mockedItem, modelMapper.transform(transfMockedItem));
    }

}
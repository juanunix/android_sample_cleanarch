package com.felipeporge.sample.presentation.mapper;

import com.felipeporge.sample.domain.entities.AuctionItem;
import com.felipeporge.sample.presentation.model.AuctionBidModel;
import com.felipeporge.sample.presentation.model.SampleItemModel;

import org.junit.Assert;
import org.junit.Test;

/**
 * This class represents the unit tests for {@link UserModelMapper}.
 *
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 24/11/2016
 */
public class ItemModelMapperTest {

    @Test
    public void transform_parseBack() throws Exception {
        AuctionBidModel mockedBid = new AuctionBidModel();
        mockedBid.setId(1);
        mockedBid.setUserId(1);
        mockedBid.setItemId(1);
        mockedBid.setCreatedAt(12345678);
        mockedBid.setValue(50.12f);

        SampleItemModel mockedItem = new SampleItemModel();
        mockedItem.setId(1);
        mockedItem.setUserId(1);
        mockedItem.setCurrentBid(mockedBid);
        mockedItem.setBidCount(1);
        mockedItem.setEndDateMillis(123456789);
        mockedItem.setTitle("Mocked item");
        mockedItem.setDescription("This is a test item.");
        mockedItem.setImgUrl("http://www.img.com/img.png");
        mockedItem.setStartingBid(45.25f);

        ItemModelMapper modelMapper = new ItemModelMapper(new BidModelMapper());
        AuctionItem transfMockedItem = modelMapper.transform(mockedItem);

        Assert.assertEquals(mockedItem, modelMapper.parseBack(transfMockedItem));
    }

}
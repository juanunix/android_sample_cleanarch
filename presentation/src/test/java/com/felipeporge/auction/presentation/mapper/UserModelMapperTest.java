package com.felipeporge.auction.presentation.mapper;

import com.felipeporge.auction.domain.entities.AuctionUser;
import com.felipeporge.auction.presentation.model.UserModel;

import junit.framework.Assert;

import org.junit.Test;

/**
 * This class represents the unit tests for {@link UserModelMapper}.
 *
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 24/11/2016
 */
public class UserModelMapperTest {

    @Test
    public void transform_parseBack() throws Exception {
        UserModel mockedUser = new UserModel();
        mockedUser.setId(100);
        mockedUser.setBirthday("15/10/1990");
        mockedUser.setEmail("felipefpx@gmail.com");
        mockedUser.setName("Felipe Porge Xavier");
        mockedUser.setPassword("12345678");
        mockedUser.setDocCode("12345");

        UserModelMapper mapper = new UserModelMapper();
        AuctionUser transfMockedUser = mapper.transform(mockedUser);

        Assert.assertEquals(mockedUser, mapper.parseBack(transfMockedUser));
    }

}
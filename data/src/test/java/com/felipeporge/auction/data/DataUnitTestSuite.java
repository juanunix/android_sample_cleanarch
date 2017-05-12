package com.felipeporge.auction.data;

import com.felipeporge.auction.data.mappers.BidMapperTest;
import com.felipeporge.auction.data.mappers.ItemMapperTest;
import com.felipeporge.auction.data.mappers.UserMapperTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;


/**
 * This class represents the unit tests suit.
 *
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 25/11/2016
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({
        BidMapperTest.class,
        ItemMapperTest.class,
        UserMapperTest.class
})
public class DataUnitTestSuite {}

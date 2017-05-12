package com.felipeporge.auction.presentation;

import com.felipeporge.auction.presentation.helpers.InfoValidatorHelperTest;
import com.felipeporge.auction.presentation.helpers.TimeHelperTest;
import com.felipeporge.auction.presentation.mapper.BidModelMapperTest;
import com.felipeporge.auction.presentation.mapper.ItemModelMapperTest;
import com.felipeporge.auction.presentation.mapper.UserModelMapperTest;

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
        InfoValidatorHelperTest.class,
        TimeHelperTest.class,
        BidModelMapperTest.class,
        ItemModelMapperTest.class,
        UserModelMapperTest.class
})
public class PresentationUnitTestSuite {}

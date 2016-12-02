package com.felipeporge.sample.data;

import com.felipeporge.sample.data.mappers.ItemMapperTest;

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
        ItemMapperTest.class
})
public class DataUnitTestSuite {}

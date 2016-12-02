package com.felipeporge.sample.presentation;

import com.felipeporge.sample.presentation.helpers.InfoValidatorHelperTest;
import com.felipeporge.sample.presentation.helpers.TimeHelperTest;
import com.felipeporge.sample.presentation.mapper.ItemModelMapperTest;

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
        ItemModelMapperTest.class
})
public class PresentationUnitTestSuite {}

package com.felipeporge.auction.presentation.helpers;

import junit.framework.Assert;

import org.junit.Test;

/**
 * This class tests represents the unit tests for {@link TimeHelper}.
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 22/11/16
 */
public class TimeHelperTest {

    @Test
    public void formatMillis_valid() throws Exception {

        Assert.assertEquals(TimeHelper.formatMillis(500), "< 1m");
        Assert.assertEquals(TimeHelper.formatMillis(800), "< 1m");
        Assert.assertEquals(TimeHelper.formatMillis(1000), "< 1m");
        Assert.assertEquals(TimeHelper.formatMillis(1400), "< 1m");
        Assert.assertEquals(TimeHelper.formatMillis(1800), "< 1m");
        Assert.assertEquals(TimeHelper.formatMillis(2 * TimeHelper.SECOND_MS), "< 1m");

        Assert.assertEquals(TimeHelper.formatMillis(TimeHelper.MINUTE_MS + 500), "1m");
        Assert.assertEquals(TimeHelper.formatMillis(2 * TimeHelper.MINUTE_MS + 800), "2m");
        Assert.assertEquals(TimeHelper.formatMillis(5 * TimeHelper.MINUTE_MS + 1000), "5m");
        Assert.assertEquals(TimeHelper.formatMillis(12 * TimeHelper.MINUTE_MS + 1400), "12m");
        Assert.assertEquals(TimeHelper.formatMillis(30 * TimeHelper.MINUTE_MS + 1800), "30m");

        Assert.assertEquals(TimeHelper.formatMillis(TimeHelper.HOUR_MS + 500), "1h0m");
        Assert.assertEquals(TimeHelper.formatMillis(TimeHelper.HOUR_MS + (25 * TimeHelper.MINUTE_MS) + 800), "1h25m");
        Assert.assertEquals(TimeHelper.formatMillis(TimeHelper.HOUR_MS + (35 * TimeHelper.MINUTE_MS) + 1000), "1h35m");
        Assert.assertEquals(TimeHelper.formatMillis(TimeHelper.HOUR_MS + (60 * TimeHelper.MINUTE_MS) + 1400), "2h0m");
        Assert.assertEquals(TimeHelper.formatMillis(TimeHelper.HOUR_MS + (120 * TimeHelper.MINUTE_MS) + 1800), "3h0m");
        Assert.assertEquals(TimeHelper.formatMillis((12 * TimeHelper.HOUR_MS) + (120 * TimeHelper.MINUTE_MS) + 1800), "14h0m");
        Assert.assertEquals(TimeHelper.formatMillis((16 * TimeHelper.HOUR_MS) + (120 * TimeHelper.MINUTE_MS) + 1800), "18h0m");
        Assert.assertEquals(TimeHelper.formatMillis((22 * TimeHelper.HOUR_MS) + (180 * TimeHelper.MINUTE_MS) + 1800), "1d1h");

        Assert.assertEquals(TimeHelper.formatMillis(TimeHelper.DAY_MS + TimeHelper.HOUR_MS + 500), "1d1h");
        Assert.assertEquals(TimeHelper.formatMillis(TimeHelper.DAY_MS + TimeHelper.HOUR_MS + (25 * TimeHelper.MINUTE_MS) + 800), "1d1h");
        Assert.assertEquals(TimeHelper.formatMillis(TimeHelper.DAY_MS + TimeHelper.HOUR_MS + (35 * TimeHelper.MINUTE_MS) + 1000), "1d1h");
        Assert.assertEquals(TimeHelper.formatMillis(TimeHelper.DAY_MS + TimeHelper.HOUR_MS + (60 * TimeHelper.MINUTE_MS) + 1400), "1d2h");
        Assert.assertEquals(TimeHelper.formatMillis(TimeHelper.DAY_MS + TimeHelper.HOUR_MS + (120 * TimeHelper.MINUTE_MS) + 1800), "1d3h");

        Assert.assertEquals(TimeHelper.formatMillis((2 * TimeHelper.DAY_MS) + TimeHelper.HOUR_MS + 500), "2d1h");
        Assert.assertEquals(TimeHelper.formatMillis((5 * TimeHelper.DAY_MS) + (10 * TimeHelper.HOUR_MS) + (25 * TimeHelper.MINUTE_MS) + 800), "5d10h");
        Assert.assertEquals(TimeHelper.formatMillis((8 * TimeHelper.DAY_MS) + (20 * TimeHelper.HOUR_MS) + (35 * TimeHelper.MINUTE_MS) + 1000), "8d20h");
        Assert.assertEquals(TimeHelper.formatMillis((30 * TimeHelper.DAY_MS) + (18 * TimeHelper.HOUR_MS) + (60 * TimeHelper.MINUTE_MS) + 1400), "30d19h");
        Assert.assertEquals(TimeHelper.formatMillis((40 * TimeHelper.DAY_MS) + (12 * TimeHelper.HOUR_MS) + (120 * TimeHelper.MINUTE_MS) + 1800), "40d14h");
    }

}
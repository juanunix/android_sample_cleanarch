package com.felipeporge.auction.data.config;

/**
 * This class stores some important constants.
 *
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 24/11/2016
 */
public abstract class Constants {

    /**
     * Time constants.
     */
    public abstract class Time{
        public final static long SECOND_MS = 1000;
        public final static long MINUTE_MS = 60 * SECOND_MS;
        public final static long HOUR_MS = 60 * MINUTE_MS;
        public final static long DAY_MS = 24 * HOUR_MS;
    }
}

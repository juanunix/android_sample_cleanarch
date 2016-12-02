package com.felipeporge.sample.presentation.helpers;

import org.apache.commons.lang3.time.DurationFormatUtils;

/**
 * This class allows to format time.
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 22/11/16
 */
public class TimeHelper {

    public final static long SECOND_MS = 1000;
    public final static long MINUTE_MS = 60 * SECOND_MS;
    public final static long HOUR_MS = 60 * MINUTE_MS;
    public final static long DAY_MS = 24 * HOUR_MS;

    /**
     * Formats a time in millis.
     * @param millis Time in millis.
     * @return Formatted string.
     */
    public static String formatMillis(long millis){
        int minutes = (int) Math.floor((millis/1000)/60.0f);
        int hours = (int) Math.floor(minutes/60.0f);
        int days = (int) Math.floor(hours/24.0f);

        if(days > 0) {
            return DurationFormatUtils.formatDuration(millis, "d'd'H'h'");
        }else if(hours > 0){
            return DurationFormatUtils.formatDuration(millis, "H'h'm'm'");
        }else if(minutes > 0){
            return DurationFormatUtils.formatDuration(millis, "m'm'");
        }else{
            return "< 1m";
        }
    }
}

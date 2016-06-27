package com.joshi.pankaj.clipped;

import java.util.Date;

/**
 * Created by Pankaj on 25-06-2016.
 */
public class Utils {
    public static String getFormattedTimeStamp(long milliseconds){
        Date  currentDate = new Date(System.currentTimeMillis());
        Date date = new Date(milliseconds);
        return date.toString();
    }
}

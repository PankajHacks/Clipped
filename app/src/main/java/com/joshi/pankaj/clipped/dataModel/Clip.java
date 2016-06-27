package com.joshi.pankaj.clipped.dataModel;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Pankaj on 25-06-2016.
 */
public class Clip {
    public String text;
    public long timeStamp;

    public Clip(){

    }

    public Clip(String text, long timeStamp) {
        this.text = text;
        this.timeStamp = timeStamp;
    }


}

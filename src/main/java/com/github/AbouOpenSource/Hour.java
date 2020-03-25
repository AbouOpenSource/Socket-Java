package com.github.AbouOpenSource;
import java.io.*;

public class Hour {

    private int h,m,s;

    public Hour() {
        h = 0; m = 0; s = 0;
    }

    public Hour(int h, int m, int s) {
        this.h = h; this.m = m; this.s = s;
    }


    public String toString() {
        String msg = h+":"+m+":"+s;
        return msg;
    }

    public int getH() {
        return h;
    }

    public int getM() {
        return m;
    }

    public int getS() {
        return s;
    }

    public Hour clone() {
        return new Hour(h,m,s);
    }

    public boolean closeTo(Hour hour, int gapSec) {
        /* to fill :
 	   must return true if current hour is equal to hour, +/- gapSec.
	   for example, if this = 12:34:56 and hour = 12:35:0, return true if gapSec <= 5,
	   else returns false.
        */
        return gapInSeconds(hour)<=gapSec;
    }

    public int gapInSeconds(Hour hour) {
         /* to fill :
	    return the gap in  seconds between this and hour
	    for example, if this = 10:0:0 and hour = 11:01:01, return 3661
         */

         int ThisSecond = (this.getH()* 3600) +
                 (this.getM() * 60) + this.getS();
         int two = (hour.getH()* 3600) +
                 (hour.getM() * 60) + hour.getS();
        return Math.abs(ThisSecond-two);
    }

}
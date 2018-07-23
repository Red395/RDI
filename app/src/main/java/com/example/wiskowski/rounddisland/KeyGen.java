package com.example.wiskowski.rounddisland;

import java.util.Calendar;

public class KeyGen {
    // should use android Calendar var = Calendar.getInstance().getTime() to get the current time
    // Date = int year, int month, int day, int hours, int day
    public KeyGen(Calendar time) {
        int curDayW = time.get(Calendar.DAY_OF_WEEK);
        int curDayY = time.get(Calendar.DAY_OF_YEAR);
        int curHour = time.get(Calendar.HOUR);
    }
}

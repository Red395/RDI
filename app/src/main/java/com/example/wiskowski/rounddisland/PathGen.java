package com.example.wiskowski.rounddisland;

import android.util.Log;

import java.util.Calendar;

public class PathGen {
    // should use android Calendar var = Calendar.getInstance() to get the current time
    // Date = int year, int month, int day, int hours, int day
    private String[] filesLocal;
    private String[] locations;

    public PathGen(String[] files) {
        filesLocal = files.clone();
        Calendar time = Calendar.getInstance();

        int curDayY = time.get(Calendar.DAY_OF_YEAR);
        int curDayW = time.get(Calendar.DAY_OF_WEEK);
        int curYear = time.get(Calendar.YEAR);

        curDayW -= 1; // sets the range of days to 0 - 6
        curDayY -= curDayW; // sets the day to the most recent Sunday

        String curYS = Integer.toString(curYear);
        curYear = curYS.charAt(curYS.length() - 1) - '0';

        pickLocations(curDayY, curYear);
    }

    public String[] getLocations() {
        return(locations);
    }

    private void pickLocations(int curDayY, int curYear) {
        String pos1 = filesLocal[curDayY % (filesLocal.length)];
        filesLocal = rotateArray((curDayY + curYear) % (filesLocal.length), filesLocal);
        filesLocal = trimArray(filesLocal, pos1);
        String pos2 = filesLocal[curDayY % (filesLocal.length)];
        filesLocal = rotateArray((curDayY + curYear) % (filesLocal.length), filesLocal);
        filesLocal = trimArray(filesLocal, pos2);
        String pos3 = filesLocal[curDayY % (filesLocal.length)];

        if (pos3.equals(pos2) || pos3.equals(pos1))
            pos3 = filesLocal[(curDayY % (filesLocal.length)) + 1];

        locations = new String[3];
        locations[0] = pos1;
        locations[1] = pos2;
        locations[2] = pos3;
    }

    private String[] rotateArray(int distance, String[] array) {
        String[] tempArray = new String[array.length];

        for (int i = 0; i < array.length; i++) {
            tempArray[i] = array[(i + distance) % array.length];
        }

        return tempArray;
    }

    private String[] trimArray(String[] array, String pos) {
        String[] tempArray = new String[array.length - 1];
        int numSkipped = 0;

        Log.d("test", array[0]);

        for (int i = 0; i < array.length - numSkipped; i++) {
            if (!(array[i + numSkipped].equals(pos)))
                tempArray[i] = array[i + numSkipped];
            else {
                numSkipped++;
            }
        }

        return tempArray;
    }
}

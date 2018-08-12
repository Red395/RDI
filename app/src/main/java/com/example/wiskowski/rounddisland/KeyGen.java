package com.example.wiskowski.rounddisland;

import java.util.concurrent.ThreadLocalRandom;

public class KeyGen {
    private String key;

    public KeyGen() {
        key = newKey();
    }

    public String newKey() {
        String startCode = "!!!!!!";
        String endCode = "";

        for (int i = 0; i < startCode.length(); i++) {
            endCode += shiftBit(startCode.charAt(i));
        }

        return endCode;
    }

    public String getKey() {
        return key;
    }

    private char shiftBit(char bit) {
        int[] charRanges = {49, 65};
        int index = ThreadLocalRandom.current().nextInt(2);

        int range;
        if (index == 0) {range = 9;}
        else {range = 25;}

        char newBit = (char) (((int) bit) + charRanges[index] + ThreadLocalRandom.current().nextInt(range));
        return newBit;
    }
}

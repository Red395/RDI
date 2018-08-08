package com.example.wiskowski.rounddisland;

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
        char newBit = (char) (((int) bit) + (int)(Math.random() * 93) + 33);
        return newBit;
    }
}

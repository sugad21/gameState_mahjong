package com.example.sugad21.gamestate_mahjong;

public class mTiles {

    private int value;
    private String suit;
    private int cardType;

    public mTiles(int inValue, String inSuit) {
        value = inValue;
        suit = inSuit;
    }

    //make each tile hold a value between 0 & 9
    public String getSuit() {
        return this.suit;
    }

    public int getValue() {
        return this.value;
    }

}

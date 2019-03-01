package com.example.sugad21.gamestate_mahjong;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class mPlayer {

    private int position;
    private ArrayList<mTiles> hand;
    private String handType;
    private int score;



    public mPlayer(int inPosition) {

        position = inPosition;
        hand = new ArrayList<mTiles>(14);
        handType = "";
        score = 0;
    }

    public int getPosition() {
        return this.position;
    }

    public ArrayList<mTiles> getHand() {
        return this.hand;
    }

    public String getHandType() {
        return this.handType;
    }

    public int getScore() {
        return this.score;
    }

    public void incrementScore() {
        score++;
    }

    public void setScore(int newScore) {
        score = newScore;
    }

    public void setHand(ArrayList<mTiles> currentHand) {
        hand = currentHand;
    }

    public void setHandType(String newHands) {
        handType = newHands;
    }

    public void setPosition(int newPosition) {
        position = newPosition;
    }
    public void addTiletoHand(mTiles in){
        hand = new ArrayList<mTiles>();
        hand.add(in);
    }

    public void removeTile(mTiles out){
        hand = new ArrayList<mTiles>();
        hand.remove(out);
    }
}

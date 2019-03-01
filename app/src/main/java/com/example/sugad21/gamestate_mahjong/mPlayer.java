package com.example.sugad21.gamestate_mahjong;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class mPlayer {

    private int position;
    private ArrayList<mTiles> hand;
    private String handType;
    private int score;
    private ArrayList<mTiles> discardHand;



    public mPlayer(int inPosition, ArrayList<mTiles> ownTiles) {

        position = inPosition;
        hand = ownTiles;
        handType = "";
        score = 0;
        discardHand = new ArrayList<mTiles>();

    }

    public int getPosition() {
        return this.position;
    }

    public ArrayList<mTiles> getHand() {

        return this.hand;
    }


    public ArrayList<mTiles> getDiscardHand(){
        return this.discardHand;
    }

    public void setDiscardHand(ArrayList<mTiles> newDHand){
        discardHand = newDHand;
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
        hand.add(in);
    }

    public void removeTile(mTiles out){
        hand = new ArrayList<mTiles>();
        hand.remove(out);
    }
}

package com.example.sugad21.gamestate_mahjong;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class mPlayer {

    private int position;
    private ArrayList<mTiles> handCount;
    private String handType;
    private int score;

    public mPlayer(int inPosition,int inHandCount,String inHandtype, int inScore){
        position = inPosition;
        handCount = new ArrayList<mTiles>(14);
        handType = inHandtype;
        score = inScore;
    }

    public int getPosition(){
        return this.position;
    }

    public ArrayList<mTiles> getHandCount(){
        return this.handCount;
    }

    public String getHandType(){
        return this.handType;
    }

    public int getScore(){
        return this.score;
    }

    public void incrementScore(){
        score++;
    }

    public void setScore(int newScore){
        score = newScore;
    }

    public void setHandCount(ArrayList<mTiles> currentCount){
        handCount = currentCount;
    }

    public void setHandType(String newHands){
        handType = newHands;
    }

    public void setPosition(int newPosition){
        position = newPosition;
    }
}

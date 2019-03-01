package com.example.sugad21.gamestate_mahjong;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class mPlayer {

    private int position;
    private ArrayList<mTiles> hand;
    private String handType;
    private int score;
    private ArrayList<mTiles> discardHand;



    public mPlayer(int inPosition) {

        position = inPosition;
        hand = new ArrayList<mTiles>(14);
        handType = "";
        score = 0;
        discardHand = new ArrayList<mTiles>(0);

    }

    public int getPosition() {
        return this.position;
    }

    public ArrayList<mTiles> getHand() {

        return this.hand;
    }

    public String totalHand(){
        String newTot = "";
        for(int i = 0; i< getHand().size(); i++){
        if(getHand().get(i).getValue() == 1){
            switch(getHand().get(i).getSuit()){
                case "Bamboo":
                    newTot += "b1";
                case "Character":
                    newTot +="c2";
                case "Dots":
                    newTot += "d3";
            }
        }
        else if(getHand().get(i).getValue() == 2){
            switch(getHand().get(i).getSuit()){
                case "Bamboo":
                    newTot += "b2";
                case "Character":
                    newTot += "c2";
                case "Dots":
                    newTot += "d2";
            }
        }
        else if(getHand().get(i).getValue() == 3){
            switch(getHand().get(i).getSuit()){
                case "Bamboo":
                    newTot += "b3";
                case "Character":
                    newTot += "c3";
                case "Dots":
                    newTot += "d3";
            }
        }
        else if(getHand().get(i).getValue() == 4){
            switch(getHand().get(i).getSuit()){
                case "Bamboo":
                    newTot += "b4";
                case "Character":
                    newTot += "c4";
                case "Dots":
                    newTot += "d4";
            }
        }
        else if(getHand().get(i).getValue() == 5){
            switch(getHand().get(i).getSuit()){
                case "Bamboo":
                    newTot += "b5";
                case "Character":
                    newTot += "c5";
                case "Dots":
                    newTot += "d5";
            }
        }
        else if(getHand().get(i).getValue() == 6){
            switch(getHand().get(i).getSuit()){
                case "Bamboo":
                    newTot += "b6";
                case "Character":
                    newTot += "c6";
                case "Dots":
                    newTot += "d6";
            }
        }
        else if(getHand().get(i).getValue() == 7){
            switch(getHand().get(i).getSuit()){
                case "Bamboo":
                    newTot += "b7";
                case "Character":
                    newTot += "c7";
                case "Dots":
                    newTot += "d7";
            }
        }
        else if(getHand().get(i).getValue() == 8){
            switch(getHand().get(i).getSuit()){
                case "Bamboo":
                    newTot += "b8";
                case "Character":
                    newTot += "c8";
                case "Dots":
                    newTot += "d8";
            }
        }
        else if(getHand().get(i).getValue() == 9){
            switch(getHand().get(i).getSuit()){
                case "Bamboo":
                    newTot += "b9";
                case "Character":
                    newTot += "c9";
                case "Dots":
                    newTot += "d9";
            }
        }
    }
    return newTot;
    }

    public ArrayList<mTiles> getDiscardHand(){
        return this.discardHand;
    }

    public void setDiscardHand(ArrayList<mTiles> newDHand){
        this.hand = newDHand;
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

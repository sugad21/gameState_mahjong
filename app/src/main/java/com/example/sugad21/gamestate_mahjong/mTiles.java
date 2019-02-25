package com.example.sugad21.gamestate_mahjong;

public class mTiles {

private int value;
private String suit;
private int cardType;

public mTiles(int inValue,String inSuit){
    value = inValue;
    suit = inSuit;
}

//make each tile hold a value between 0 & 9
    public String getSuit(){
        return this.suit;
    }

    public int getValue(){
        return this.value;
    }

    public String getFaceValue(){

        if(this.getValue() == 1){
            switch(this.getSuit()){
                case "Bamboo":
                    return "b1";
                case "Character":
                    return "c2";
                case "Dots":
                    return "d3";
            }
        }
        else if(this.getValue() == 2){
            switch(this.getSuit()){
                case "Bamboo":
                    return "b2";
                case "Character":
                    return "c2";
                case "Dots":
                    return "d2";
            }
        }
        else if(this.getValue() == 3){
            switch(this.getSuit()){
                case "Bamboo":
                    return "b3";
                case "Character":
                    return "c3";
                case "Dots":
                    return "d3";
            }
        }
        else if(this.getValue() == 4){
            switch(this.getSuit()){
                case "Bamboo":
                    return "b4";
                case "Character":
                    return "c4";
                case "Dots":
                    return "d4";
            }
        }
        else if(this.getValue() == 5){
            switch(this.getSuit()){
                case "Bamboo":
                    return "b5";
                case "Character":
                    return "c5";
                case "Dots":
                    return "d5";
            }
        }
        else if(this.getValue() == 6){
            switch(this.getSuit()){
                case "Bamboo":
                    return "b6";
                case "Character":
                    return "c6";
                case "Dots":
                    return "d6";
            }
        }
        else if(this.getValue() == 7){
            switch(this.getSuit()){
                case "Bamboo":
                    return "b7";
                case "Character":
                    return "c7";
                case "Dots":
                    return "d7";
            }
        }
        else if(this.getValue() == 8){
            switch(this.getSuit()){
                case "Bamboo":
                    return "b8";
                case "Character":
                    return "c8";
                case "Dots":
                    return "d8";
            }
        }
        else if(this.getValue() == 9){
            switch(this.getSuit()){
                case "Bamboo":
                    return "b9";
                case "Character":
                    return "c9";
                case "Dots":
                    return "d9";
            }
        }
        return "Not Valid";
    }
}

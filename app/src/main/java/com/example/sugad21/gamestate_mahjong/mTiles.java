package com.example.sugad21.gamestate_mahjong;

public class mTiles {

    private int value;
    private String suit;

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


    public String totalHand(){

            if(getValue() == 1){
                switch(getSuit()){
                    case "Bamboo":
                        return "b1";
                    case "Character":
                        return  "c2";
                    case "Dots":
                        return "d3";
                }
            }
            else if(getValue() == 2){
                switch(getSuit()){
                    case "Bamboo":
                        return "b2";
                    case "Character":
                        return "c2";
                    case "Dots":
                        return "d2";
                }
            }
            else if(getValue() == 3){
                switch(getSuit()){
                    case "Bamboo":
                        return "b3";
                    case "Character":
                        return"c3";
                    case "Dots":
                        return "d3";
                }
            }
            else if(getValue() == 4){
                switch(getSuit()){
                    case "Bamboo":
                        return"b4";
                    case "Character":
                        return "c4";
                    case "Dots":
                        return"d4";
                }
            }
            else if(getValue() == 5){
                switch(getSuit()){
                    case "Bamboo":
                        return "b5";
                    case "Character":
                        return "c5";
                    case "Dots":
                        return "d5";
                }
            }
            else if(getValue() == 6){
                switch(getSuit()){
                    case "Bamboo":
                        return "b6";
                    case "Character":
                        return "c6";
                    case "Dots":
                        return "d6";
                }
            }
            else if(getValue() == 7){
                switch(getSuit()){
                    case "Bamboo":
                        return "b7";
                    case "Character":
                        return "c7";
                    case "Dots":
                        return "d7";
                }
            }
            else if(getValue() == 8){
                switch(getSuit()){
                    case "Bamboo":
                        return"b8";
                    case "Character":
                        return "c8";
                    case "Dots":
                        return"d8";
                }
            }
            else if(getValue() == 9){
                switch(getSuit()){
                    case "Bamboo":
                        return "b9";
                    case "Character":
                        return "c9";
                    case "Dots":
                        return "d9";
                }
            }
            return "not valid";
        }

    }



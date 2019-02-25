package com.example.sugad21.gamestate_mahjong;

import java.util.ArrayList;
import java.util.Collections;

public class mWall {

    private ArrayList<mTiles> wall;

    public mWall(){
        String newSuit = "";
        wall = new ArrayList<mTiles>();
        for(int j = 1; j < 4; j++){
            if(j == 1){
                newSuit = "Bamboo";
            }
            else if(j == 2){
                newSuit = "Character";
            }
            else if(j == 3){
                newSuit = "Dots";
            }
        }
        for(int i = 1 ; i < 10; i++){
            mTiles newTile = new mTiles(i,newSuit);
            wall.add(newTile);
        }
        Collections.shuffle(wall);
    }

    public int getWallSize(){
        return wall.size();
    }

    public ArrayList<mTiles> getWall() {
        return wall;
    }

    public mTiles removeTile(int index){
        return wall.remove(index);
    }
}

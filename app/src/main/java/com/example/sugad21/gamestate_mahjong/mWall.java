package com.example.sugad21.gamestate_mahjong;

import java.util.ArrayList;
import java.util.Collections;

public class mWall {

    private ArrayList<mTiles> wall;
    private int sWall;

    public mWall(){
        String newSuit = "";

    }

    public int getWallSize(){
        return wall.size();
    }

    public ArrayList<mTiles> getWall() {
        return wall;
    }

    public void setWallsize(int in){
        this.sWall = in;
    }

    public mTiles removeTile(int index){
        return wall.remove(index);
    }
}

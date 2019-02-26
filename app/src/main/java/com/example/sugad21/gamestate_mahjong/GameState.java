package com.example.sugad21.gamestate_mahjong;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public class GameState {

    private ArrayList<mPlayer> gamePlayers;
    private ArrayList<mTiles> wall;
    private ArrayList<mTiles> playerTiles;
    private ArrayList<mTiles> discardTiles;
    private int turnCount;
    private int lastTurn;

    public GameState() {
        //initialize values for each instance variable
        gamePlayers = new ArrayList<mPlayer>();
        wall = new ArrayList<mTiles>();
        playerTiles = new ArrayList<mTiles>();
        discardTiles = new ArrayList<mTiles>();
        turnCount = 0;
        lastTurn = 0;
        //before play, wall is shuffled with new tiles
        mWall wallInstance = new mWall();
        Collections.shuffle(wallInstance.getWall());

        //players are added to list of players
        for (int i = 0; i < 5; i++) {
            mPlayer pInfo = new mPlayer();
            gamePlayers.add(pInfo);
        }
        Random rand = new Random();
        int wallDie1 = rand.nextInt(5);
        int wallDie2 = rand.nextInt(5);

        int wSize = wallInstance.getWallSize() - (wallDie1 + wallDie2);
        int j = 0;
        for (int i = 0; i <= wSize; i++) {
            gamePlayers.get(0).addTile(wallInstance.removeTile(0));
            if (j < gamePlayers.size() - 1) {
                j++;
            } else {
                j = 0;
            }
        }

    }

    public GameState(GameState in){
        gamePlayers = new ArrayList<mPlayer>();
        for(int i = 0; i < 5; i++){
            gamePlayers.add(in.)
        }
    }



}

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
    private mTiles recentDiscard;
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
        recentDiscard = null;
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

    /*
    Copy Constructor to save current state of game as a copy, so original
    isn't used for instance variables
     */
    public GameState(GameState in){
        gamePlayers = new ArrayList<mPlayer>();
        for(int i = 0; i < 5; i++){
             gamePlayers.add(in.getGamePlayers().get(i));
        }

        wall = new ArrayList<mTiles>();
        for(int j = 0; j <= in.getWall().size();j++){
            wall.add(in.getWall().get(j));
        }

        playerTiles = new ArrayList<mTiles>();
        for(int k = 0; k <= in.getPlayerTiles().size(); k++){
            playerTiles.add(in.getPlayerTiles().get(k));
        }

        discardTiles = new ArrayList<mTiles>();
        for(int l = 0; l <= in.getDiscardTiles().size(); l++){
            discardTiles.add(in.getDiscardTiles().get(l));
        }

        turnCount = in.getTurnCount();
        lastTurn = in.getLastTurn();
        recentDiscard = in.getRecentDiscard();
    }

    public void initTiles ()
    {
        wall = new ArrayList<mTiles> ();
        for(int i = 0; i < 9; i++)
        {
            //Bamboo suit 4 of one value made at a time 1-9 (mTiles[0-31])
            wall.set(4*i, new mTiles(i+1, "Bamboo"));
            wall.set(4*i+1, new mTiles(i+1, "Bamboo"));
            wall.set(4*i+2, new mTiles(i+1, "Bamboo"));
            wall.set(4*i+3, new mTiles(i+1, "Bamboo"));
            //Characters suit 4 of one value made at a time 1-9 (mTiles[36-71])
            wall.set(4*i+36, new mTiles(i+1, "Characters"));
            wall.set(4*i+37, new mTiles(i+1, "Characters"));
            wall.set(4*i+38, new mTiles(i+1, "Characters"));
            wall.set(4*i+39, new mTiles(i+1, "Characters"));
            //Dots suit 4 of one value made at a time 1-9 (mTiles[72-107])
            wall.set(4*i+72, new mTiles(i+1, "Dots"));
            wall.set(4*i+73, new mTiles(i+1, "Dots"));
            wall.set(4*i+74, new mTiles(i+1, "Dots"));
            wall.set(4*i+75, new mTiles(i+1, "Dots"));
            //initialized the first 108 tiles, 4 of each tile of each suit
        }

        //Values of wind : 0 - west, 1 - south, 2 - east, 3 - north
        //Winds suit 4 of one wind made on each loop (mTiles[108-123])
        for(int j = 0; j < 4; j ++)
        {
            wall.set(4*j+108, new mTiles(j+1, "Winds"));
            wall.set(4*j+109, new mTiles(j+1, "Winds"));
            wall.set(4*j+110, new mTiles(j+1, "Winds"));
            wall.set(4*j+111, new mTiles(j+1, "Winds"));

            if(j != 3)
            {
                //value of dragon : 0 - red dragon, 1 - green dragon, 2 - white dragon
                //Dragons suit 4 of one dragon made on each loop (mTiles[124-135])
                wall.set(4*j+124, new mTiles(j+1, "Dragon"));
                wall.set(4*j+125, new mTiles(j+1, "Dragon"));
                wall.set(4*j+126, new mTiles(j+1, "Dragon"));
                wall.set(4*j+127, new mTiles(j+1, "Dragon"));
            }

            //Flower and season tiles one of each value and suit made (mTiles[136-143])
            wall.set(j+136, new mTiles(j+1, "Flower"));
            wall.set(j+140, new mTiles(j+1, "Season"));
        }
    }

    public mTiles getRecentDiscard(){
        return recentDiscard;
    }
    public ArrayList<mPlayer> getGamePlayers(){
        return gamePlayers;
    }

    public ArrayList<mTiles> getWall(){
        return wall;
    }

    public ArrayList<mTiles> getPlayerTiles(){
        return playerTiles;
    }

    public ArrayList<mTiles> getDiscardTiles(){
        return discardTiles;
    }

    public int getTurnCount(){
        return turnCount;
    }

    public int getLastTurn(){
        return lastTurn;
    }

    public void setGamePlayers(ArrayList<mPlayer> inPlayers){
        this.gamePlayers = inPlayers;
    }

    public void setWall(ArrayList<mTiles> inWall){
        this.wall = inWall;
    }

    public void setPlayerTiles(ArrayList<mTiles> inPTiles){
        this.playerTiles = inPTiles;
    }

    public void setDiscardTiles(ArrayList<mTiles> inDTiles){
        this.discardTiles = inDTiles;
    }

    public void setTurnCount(int inCount){
        this.turnCount = inCount;
    }

    public void setLastTurn(int inLast){
        this.lastTurn = inLast;
    }

    public void setRecentDiscard(mTiles inMTile){
        this.recentDiscard = inMTile;
    }

    public boolean gameOver(){
        /*
        Returns T/F if a player has mahjong and ends game
         */
    }

    public boolean drawFromWall(){
        /*
        if the image of the draw tile is selected, then a tile from the wall array will
        be accessed and this new tile will be added into the array of X player and removed from
        the wall
         */
    }

    public boolean discardTile(){
        /*
        If user selects on tile of own collection during turn, then the tile is
        set to the most recently discarded card and is available for other players
        to take in order of clockwise.
         */
    }

    public void nextPlayer(){
        /*
        Once current player is done, this method is called in order to move to next player
         */
    }

    public void handCheck(){
        /*
        Checks hand of currentTurnPlayer and if there is a mahjong, then gameOver method is
        called. But if player picked up a discarded tile and completes a set, that is not mahjong.
        Toast appears for 5 seconds showing the current set completed.(One can only get mahjong if
        one draw from wall or discarded tile.
         */
    }

    public boolean pungSet(){
        /*
        returns true if there is a pung in a players hand
         */
    }

    public boolean kongSet(){
        /*
        returns true if there is a kong in a players hand
         */
    }

    public boolean quintSet(){
        /*
        returns true if there is a quint
         */
    }

    public boolean sextetSet(){
        /*
        returns true if sextet
         */
    }

    public boolean pairSet{
        /*
        returns true if pair in hand
         */
    }

    public boolean winningHands(){
        /*
        This method will check the current players hand once picked up a discarded tile
        or wall tile to see all the different combinations of winning hands. So
         */
    }

    public String toString()
    {
        String toReturn = "";

        //Should print format all gameState info into a single string to be returned

        return toReturn;
    }





}

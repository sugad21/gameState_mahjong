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





}

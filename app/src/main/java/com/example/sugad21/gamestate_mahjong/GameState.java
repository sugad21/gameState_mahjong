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
    private int turn;
    private int lastTurn;

    public GameState() {
        //initialize values for each instance variable
        gamePlayers = new ArrayList<mPlayer>();
        wall = new ArrayList<mTiles>();
        playerTiles = new ArrayList<mTiles>();
        discardTiles = new ArrayList<mTiles>();
        turn = 0;
        lastTurn = 0;
        recentDiscard = null;


        mPlayer EastPlayer = new mPlayer(0);
        mPlayer NorthPlayer = new mPlayer(1);
        mPlayer WestPlayer = new mPlayer(2);
        mPlayer SouthPlayer = new mPlayer(3);

        gamePlayers.add(EastPlayer);
        gamePlayers.add(NorthPlayer);
        gamePlayers.add(WestPlayer);
        gamePlayers.add(SouthPlayer);

        initTiles();
        initHand();
    }

    /*
    Copy Constructor to save current state of game as a copy, so original
    isn't used for instance variables
     */
    public GameState(GameState in) {

        gamePlayers = new ArrayList<mPlayer>();
        for (int i = 0; i < 5; i++) {
            gamePlayers.add(in.getGamePlayers().get(i));
        }

        wall = new ArrayList<mTiles>();
        for (int j = 0; j <= in.getWall().size(); j++) {
            wall.add(in.getWall().get(j));
        }

        playerTiles = new ArrayList<mTiles>();
        for (int k = 0; k <= in.getPlayerTiles().size(); k++) {
            playerTiles.add(in.getPlayerTiles().get(k));
        }

        discardTiles = new ArrayList<mTiles>();
        for (int l = 0; l <= in.getDiscardTiles().size(); l++) {
            discardTiles.add(in.getDiscardTiles().get(l));
        }

        turn = in.getTurn();
        lastTurn = in.getLastTurn();
        recentDiscard = in.getRecentDiscard();
    }

    public void initTiles() {
        wall = new ArrayList<mTiles>();
        for (int i = 0; i < 9; i++) {
            //Bamboo suit 4 of one value made at a time 1-9 (mTiles[0-31])
            wall.add(4 * i, new mTiles(i + 1, "Bamboo"));
            wall.add(4 * i + 1, new mTiles(i + 1, "Bamboo"));
            wall.add(4 * i + 2, new mTiles(i + 1, "Bamboo"));
            wall.add(4 * i + 3, new mTiles(i + 1, "Bamboo"));
            //Characters suit 4 of one value made at a time 1-9 (mTiles[36-71])
            wall.add(4 * i + 36, new mTiles(i + 1, "Characters"));
            wall.add(4 * i + 37, new mTiles(i + 1, "Characters"));
            wall.add(4 * i + 38, new mTiles(i + 1, "Characters"));
            wall.add(4 * i + 39, new mTiles(i + 1, "Characters"));
            //Dots suit 4 of one value made at a time 1-9 (mTiles[72-107])
            wall.add(4 * i + 72, new mTiles(i + 1, "Dots"));
            wall.add(4 * i + 73, new mTiles(i + 1, "Dots"));
            wall.add(4 * i + 74, new mTiles(i + 1, "Dots"));
            wall.add(4 * i + 75, new mTiles(i + 1, "Dots"));
            //initialized the first 108 tiles, 4 of each tile of each suit
        }

        //Values of wind : 0 - west, 1 - south, 2 - east, 3 - north
        //Winds suit 4 of one wind made on each loop (mTiles[108-123])
        for (int j = 0; j < 4; j++) {
            wall.add(4 * j + 108, new mTiles(j + 1, "Winds"));
            wall.add(4 * j + 109, new mTiles(j + 1, "Winds"));
            wall.add(4 * j + 110, new mTiles(j + 1, "Winds"));
            wall.add(4 * j + 111, new mTiles(j + 1, "Winds"));

            if (j != 3) {
                //value of dragon : 0 - red dragon, 1 - green dragon, 2 - white dragon
                //Dragons suit 4 of one dragon made on each loop (mTiles[124-135])
                wall.add(4 * j + 124, new mTiles(j + 1, "Dragon"));
                wall.add(4 * j + 125, new mTiles(j + 1, "Dragon"));
                wall.add(4 * j + 126, new mTiles(j + 1, "Dragon"));
                wall.add(4 * j + 127, new mTiles(j + 1, "Dragon"));
            }

            //Flower and season tiles one of each value and suit made (mTiles[136-143])
            wall.add(j + 136, new mTiles(j + 1, "Flower"));
            wall.add(j + 140, new mTiles(j + 1, "Season"));
        }
        Collections.shuffle(wall);
    }

    public void initHand() {
        playerTiles = new ArrayList<mTiles>();

        if (getGamePlayers().get(0).getPosition() == 0) {
            for (int i = 1; 4 * i < 54; i++) {
                for (int j = 1; j <= i; i++) {
                    playerTiles.add(wall.get(i));
                    getWall().remove(wall.get(i));
                    getGamePlayers().get(j).setPosition(j);
                }
            }

        }
    }

    public mTiles getRecentDiscard() {
        return recentDiscard;
    }

    public ArrayList<mPlayer> getGamePlayers() {
        return gamePlayers;
    }

    public ArrayList<mTiles> getWall() {
        return wall;
    }

    public ArrayList<mTiles> getPlayerTiles() {

        return playerTiles;
    }

    public ArrayList<mTiles> getDiscardTiles() {
        return discardTiles;
    }

    public int getTurn() {
        return turn;
    }

    public int getLastTurn() {
        return lastTurn;
    }

    public void setGamePlayers(ArrayList<mPlayer> inPlayers) {
        this.gamePlayers = inPlayers;
    }

    public void setWall(ArrayList<mTiles> inWall) {
        this.wall = inWall;
    }

    public void setPlayerTiles(ArrayList<mTiles> inPTiles) {
        this.playerTiles = inPTiles;
    }

    public void setDiscardTiles(mTiles inDTiles) {
        getDiscardTiles().add(inDTiles);
    }

    public void setTurn(int inTurn) {
        this.turn = inTurn;
    }

    public void setLastTurn(int inLast) {
        this.lastTurn = inLast;
    }

    public void setRecentDiscard(mTiles inMTile) {
        this.recentDiscard = inMTile;
    }

    public boolean drawFromWall(mTiles drawnTile, int position) {
        /*
        if the image of the draw tile is selected, then a tile from the wall array will
        be accessed and this new tile will be added into the array of X player and removed from
        the wall
         */
        mPlayer newPlayer = this.gamePlayers.get(position);
        if (!(currentTurn(newPlayer))) {
            return false;
        }
        newPlayer.addTiletoHand(drawnTile);
        getWall().remove(drawnTile);
        setPlayerTiles(getPlayerTiles());
        setWall(getWall());
        return true;
    }

    public boolean discardTile(mTiles discardTile, int position) {
        /*
        If user selects on tile of own collection during turn, then the tile is
        set to the most recently discarded card and is available for other players
        to take in order of clockwise.
         */
        mPlayer newPlayer = this.gamePlayers.get(position);
        if (!(currentTurn(newPlayer))) {
            return false;
        }
            setRecentDiscard(discardTile);
            setDiscardTiles(discardTile);
            newPlayer.removeTile(discardTile);
            newPlayer.setHand(newPlayer.getHand());

            return  true;
    }

    /*
    if a user just discarded a card, then anyone can pick up the card just discarded
    and choose to discard it or keep it
     */
    public boolean drawDiscardTile(mTiles drawDTile, int position){
        mPlayer newPlayer = this.gamePlayers.get(position);
        if(discardTile(recentDiscard,getLastTurn())){
            for(int i = 0; i <= gamePlayers.size();i++){
                if(newPlayer.getPosition() != gamePlayers.get(i).getPosition()){
                    playerTiles.add(drawDTile);
                    getDiscardTiles().remove(drawDTile);
                    setRecentDiscard(null);
                    setPlayerTiles(getPlayerTiles());
                    return true;
                }
            }
        }
        return false;
    }

    public boolean currentTurn(mPlayer pTurn) {
        /*
        Once current player is done, this method is called in order to move to next player
         */
       if(getTurn() == pTurn.getPosition()){

           switch(pTurn.getPosition()){
               case 0: setTurn(1);
               case 1: setTurn(2);
               case 2: setTurn(3);
               case 3: setTurn(0);
           }
           return true;
       }
       return false;
    }


    public String toString() {
        String PlayerInfo = "Player Information: \n";
        String GameInfo = "General Game Information: \n";

        for(int i = 0; i <= this.gamePlayers.size(); i++){
            PlayerInfo = PlayerInfo + "Position: " + this.gamePlayers.get(i).getPosition()+
                    "Players Hand: "+ this.gamePlayers.get(i).getHand()+
                    "Players Discarded Cards" + this.gamePlayers.get(i).getDiscardHand()+
                    "Score"+ this.gamePlayers.get(i).getScore();
        }
        GameInfo = GameInfo + "# of Tiles in Wall: " + getWall().size() +
                "Most Recent Discarded Tile: " + getRecentDiscard();
        //Should print format all gameState info into a single string to be returned

        return GameInfo + PlayerInfo;
    }


    /*
    All Methods below will be implemented during Alpha Release and will check during hands of
    each player and check winning conditions
     */
    public void handCheck(ArrayList<mTiles> pHand) {
        /*
        Checks hand of currentTurnPlayer and if there is a mahjong, then gameOver method is
        called. But if player picked up a discarded tile and completes a set, that is not mahjong.
        Toast appears for 5 seconds showing the current set completed.(One can only get mahjong if
        one draw from wall or discarded tile.
         */
    }

    public boolean gameOver() {
        /*
        Returns T/F if a player has mahjong and ends game
         */
        return true;
    }

    public boolean pungSet(ArrayList<mTiles> pHand) {
        /*
        returns true if there is a pung in a players hand
         */
        return true;
    }

    public boolean kongSet(ArrayList<mTiles> pHand) {
        /*
        returns true if there is a kong in a players hand
         */
        return true;
    }

    public boolean quintSet(ArrayList<mTiles> pHand) {
        /*
        returns true if there is a quint
         */
        return true;
    }

    public boolean sextetSet(ArrayList<mTiles> pHand) {
        /*
        returns true if sextet
         */
        return true;
    }

    public boolean pairSet(ArrayList<mTiles> pHand) {
        /*
        returns true if pair in hand
         */
        return true;
    }

    public boolean winningHands(ArrayList<mTiles> pHand) {
        /*
        This method will check the current players hand once picked up a discarded tile
        or wall tile to see all the different combinations of winning hands. So
         */
        return true;
    }



}

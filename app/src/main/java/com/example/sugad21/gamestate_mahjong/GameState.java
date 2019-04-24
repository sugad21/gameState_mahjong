package com.example.sugad21.gamestate_mahjong;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
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
        recentDiscard = getRecentDiscard();

        initTiles();
        mPlayer EastPlayer = new mPlayer(0, playerTiles);
        mPlayer NorthPlayer = new mPlayer(1, playerTiles);
        mPlayer WestPlayer = new mPlayer(2, playerTiles);
        mPlayer SouthPlayer = new mPlayer(3, playerTiles);

        gamePlayers.add(EastPlayer);
        gamePlayers.add(NorthPlayer);
        gamePlayers.add(WestPlayer);
        gamePlayers.add(SouthPlayer);

        EastPlayer.setHand(initHand0());
        NorthPlayer.setHand(initHand1());
        WestPlayer.setHand(initHand2());
        SouthPlayer.setHand(initHand3());


    }

    /*
    Copy Constructor to save current state of game as a copy, so original
    isn't used for instance variables
     */
    /*
    External Citation:
    Problem: Adding player of collection to currrent game players
    Source: Used example of Pig State we did in lab
    Solution: use the add method already implemented with arrayLists
     */
    public GameState(GameState in) {

        gamePlayers = new ArrayList<mPlayer>();
        for (int i = 0; i < in.gamePlayers.size(); i++) {
            gamePlayers.add(in.gamePlayers.get(i));
        }


        wall = new ArrayList<mTiles>();
        for (int m = 0; m < in.getWall().size(); m++) {
            wall.add(in.wall.get(m));
        }

        playerTiles = new ArrayList<mTiles>();


        discardTiles = new ArrayList<mTiles>();
        for (int l = 0; l < in.getDiscardTiles().size(); l++) {
            discardTiles.add(in.discardTiles.get(l));
        }

        in.initTiles();
        mPlayer EastPlayer = new mPlayer(0, in.playerTiles);
        mPlayer NorthPlayer = new mPlayer(1, in.playerTiles);
        mPlayer WestPlayer = new mPlayer(2, in.playerTiles);
        mPlayer SouthPlayer = new mPlayer(3, in.playerTiles);

        in.gamePlayers.set(0, EastPlayer);
        in.gamePlayers.set(1, NorthPlayer);
        in.gamePlayers.set(2, WestPlayer);
        in.gamePlayers.set(3, SouthPlayer);

        EastPlayer.setHand(in.initHand0());
        NorthPlayer.setHand(in.initHand1());
        WestPlayer.setHand(in.initHand2());
        SouthPlayer.setHand(in.initHand3());


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
        }
        for (int i = 0; i < 9; i++) {
            wall.add(4 * i + 36, new mTiles(i + 1, "Characters"));
            wall.add(4 * i + 37, new mTiles(i + 1, "Characters"));
            wall.add(4 * i + 38, new mTiles(i + 1, "Characters"));
            wall.add(4 * i + 39, new mTiles(i + 1, "Characters"));
        }
        for (int i = 0; i < 9; i++) {
            //Dots suit 4 of one value made at a time 1-9 (mTiles[72-107])
            wall.add(4 * i + 72, new mTiles(i + 1, "Dots"));
            wall.add(4 * i + 73, new mTiles(i + 1, "Dots"));
            wall.add(4 * i + 74, new mTiles(i + 1, "Dots"));
            wall.add(4 * i + 75, new mTiles(i + 1, "Dots"));
        }
        //initialized the first 108 tiles, 4 of each tile of each suit


        //Values of wind : 0 - west, 1 - south, 2 - east, 3 - north
        //Winds suit 4 of one wind made on each loop (mTiles[108-123])
        for (int j = 0; j < 4; j++) {
            wall.add(4 * j + 108, new mTiles(j + 1, "Winds"));
            wall.add(4 * j + 109, new mTiles(j + 1, "Winds"));
            wall.add(4 * j + 110, new mTiles(j + 1, "Winds"));
            wall.add(4 * j + 111, new mTiles(j + 1, "Winds"));
        }
        for (int j = 0; j < 4; j++) {
            if (j != 3) {
                //value of dragon : 0 - red dragon, 1 - green dragon, 2 - white dragon
                //Dragons suit 4 of one dragon made on each loop (mTiles[124-135])
                wall.add(4 * j + 124, new mTiles(j + 1, "Dragon"));
                wall.add(4 * j + 125, new mTiles(j + 1, "Dragon"));
                wall.add(4 * j + 126, new mTiles(j + 1, "Dragon"));
                wall.add(4 * j + 127, new mTiles(j + 1, "Dragon"));
            }
        }
        /*
        for (int j = 0; j < 4; j++) {
            //Flower and season tiles one of each value and suit made (mTiles[136-143])
            wall.add(j + 136, new mTiles(j + 1, "Flower"));
            wall.add(j + 140, new mTiles(j + 1, "Season"));
        }
        */

        /*
        External Citation:
        Problem: Shuffling and randomizing the Wall
        Source: https://www.geeksforgeeks.org/collections-shuffle-java-examples/
        Solution: use Collection.shuffle(object);
         */
        Collections.shuffle(wall);
    }

    public ArrayList<mTiles> initHand0() {

        for (int i = 0; i < 14; i++) {
            gamePlayers.get(0).addTiletoHand(getWall().get(i));
            getWall().remove(getWall().get(i));
            setWall(getWall());
            gamePlayers.get(0).setHand(gamePlayers.get(0).getHand());
        }
        return gamePlayers.get(0).getHand();
    }

    public ArrayList<mTiles> initHand1() {
        for (int j = 14; j < 27; j++) {
            gamePlayers.get(1).addTiletoHand(getWall().get(j));
            getWall().remove(getWall().get(j));
            setWall(getWall());
            gamePlayers.get(1).setHand(gamePlayers.get(1).getHand());

        }
        return gamePlayers.get(1).getHand();
    }


    public ArrayList<mTiles> initHand2() {
        for (int k = 27; k < 40; k++) {
            gamePlayers.get(2).addTiletoHand(getWall().get(k));
            getWall().remove(getWall().get(k));
            setWall(getWall());
            gamePlayers.get(2).setHand(gamePlayers.get(2).getHand());

        }
        return gamePlayers.get(2).getHand();
    }

    public ArrayList<mTiles> initHand3() {
        for (int l = 40; l < 53; l++) {
            gamePlayers.get(3).addTiletoHand(getWall().get(l));
            getWall().remove(getWall().get(l));
            setWall(getWall());
            gamePlayers.get(3).setHand(gamePlayers.get(3).getHand());

        }
        setWall(getWall());
        return gamePlayers.get(3).getHand();
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
        nextTurn(newPlayer);
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
        setDiscardTiles(discardTile);
        newPlayer.setDiscardHand(getDiscardTiles());
        newPlayer.removeTile(discardTile);
        newPlayer.setHand(newPlayer.getHand());

        return true;
    }

    /*
    if a user just discarded a card, then anyone can pick up the card just discarded
    and choose to discard it or keep it
     */
    public boolean drawDiscardTile(mTiles drawDTile, int position) {
        mPlayer newPlayer = this.gamePlayers.get(position);
        if (discardTile(recentDiscard, getLastTurn())) {
            for (int i = 0; i <= gamePlayers.size(); i++) {
                if (newPlayer.getPosition() != gamePlayers.get(i).getPosition()) {
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

    public boolean currentTurn(mPlayer cTurn) {
        if (getTurn() == cTurn.getPosition()) {
            return true;
        }
        return false;
    }

    public boolean nextTurn(mPlayer pTurn) {
        /*
        Once current player is done, this method is called in order to move to next player
         */
        if (getTurn() == pTurn.getPosition()) {

            switch (pTurn.getPosition()) {
                case 0:
                    setTurn(1);
                    break;
                case 1:
                    setTurn(2);
                    break;
                case 2:
                    setTurn(3);
                    break;
                case 3:
                    setTurn(0);
                    break;
            }
            return true;
        }
        return false;
    }

    public String toString() {
        String PlayerInfo = "Player Information: \n";
        String GameInfo = "General Game Information: \n";
        String p = "";
        String Hand = "";


        for (int i = 0; i < this.gamePlayers.size(); i++) {
            PlayerInfo = PlayerInfo + "Position: " + this.gamePlayers.get(i).getPosition() + "\n" +
                    "Players Hand: " + this.gamePlayers.get(i).getHand().toString() + "\n" +
                    "Score: " + this.gamePlayers.get(i).getScore() + "\n";
            for (int j = 0; j < this.gamePlayers.get(3).getDiscardHand().size(); j++) {
                p = this.gamePlayers.get(0).getDiscardHand().get(j).totalHand() +
                        this.gamePlayers.get(1).getDiscardHand().get(j).totalHand() +
                        this.gamePlayers.get(2).getDiscardHand().get(j).totalHand() +
                        this.gamePlayers.get(3).getDiscardHand().get(j).totalHand();
            }

        }



        GameInfo = GameInfo + "# of Tiles in Wall: " + getWall().size() + "\n" + "Most Recent Discarded Tile: " + this.getGamePlayers().get(0).getDiscardHand() + "\n";

        //Should print format all gameState info into a single string to be returned

        return GameInfo + PlayerInfo + "Discarded Tiles: " + p;
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

    public boolean mahjongCheck(ArrayList<mTiles> pHand)
    {
        ArrayList<mTiles> bamboos = new ArrayList<mTiles> ();
        ArrayList<mTiles> dots = new ArrayList<mTiles> ();
        ArrayList<mTiles> characters = new ArrayList<mTiles> ();

        for(int i = 0; i < pHand.size(); i++)
        {
            if(pHand.get(i).getSuit() == "Bamboo")
            {
                bamboos.add(pHand.get(i));
            }
            else if(pHand.get(i).getSuit() == "Dots")
            {
                dots.add(pHand.get(i));
            }
            else if(pHand.get(i).getSuit() == "Characters")
            {
                characters.add(pHand.get(i));
            }
        }

        if(checkSuit(bamboos))
        {
            if(checkSuit(dots))
            {
                if (checkSuit(characters))
                {
                    return true;
                }
                else
                {}
            }
        }
        return false;
    }

    public boolean checkSuit(ArrayList<mTiles> pHand)
    {
        ArrayList<mTiles> suitList = pHand;
        int prevSize = 0;
        for(int i = 0; i < suitList.size(); i++)
        {
            for(int j = i; j < suitList.size(); j++)
            {
                if(suitList.get(i).getValue() > suitList.get(j).getValue())
                {
                    Collections.swap(suitList, i, j);
                }
            }
        }

        ArrayList<Integer> temp = new ArrayList<> ();

        temp = findLargestSet(suitList);
        /*
        for(int i = temp.size()-1; i >= 0; i--)
        {
            suitList.remove(temp.get(i).intValue());

        }
        /*


        while(findLargestSet(suitList).size() != 0 && prevSize != suitList.size() )
        {
            prevSize = suitList.size();
            ArrayList<Integer> temp = new ArrayList<> ();
            temp = findLargestSet(suitList);
            for(int x = temp.size() - 1; x >= 0; x--)
            {
                suitList.remove(temp.get(x));
            }
        }
        */

        prevSize = 1000;
        ArrayList<Integer> temp1 = new ArrayList<> ();
        temp1 = findLowestMatching(suitList);
        /*
        while(findLowestMatching(suitList).size() != 0 && prevSize != suitList.size())
        {
            prevSize = suitList.size();
            ArrayList<Integer> temp1 = new ArrayList<> ();
            temp1 = findLowestMatching(suitList);
            for(int x = temp1.size() - 1; x >= 0; x--)
            {
                suitList.remove(temp1.get(x).intValue());
            }
        }
        */

        if(temp1.size()+ temp.size() == suitList.size())
            return true;
        else
            return false;
    }

    public ArrayList<Integer> findLargestSet(ArrayList<mTiles> suitList)
    {


        //Create var to hold index of current high val of set
        int prevValInd = 0;
        //Create arraylist to hold all indexes of tiles part of set
        ArrayList<Integer> indexes = new ArrayList<Integer>();

        if(suitList.size() == 0) return indexes;

        //Create var to hold the current highest val of set
        int prevVal = suitList.get(0).getValue();

        //Iterate through all of the tiles
        for(int i = 0; i < suitList.size() - 1; i++)
        {
            prevVal = suitList.get(i).getValue();
            prevValInd = i;
            //If i th card val is equal to prevVal+1 then add the index
            //where prevVal was found to the arraylist
            if(suitList.get(i+1).getValue() == prevVal + 1 && suitList.get(i).getValue() == prevVal)
            {
                indexes.add(prevValInd);
            }
            else if(indexes.size() <= 2)
            {
                indexes.clear();
            }
            else if(prevVal == suitList.get(i-1).getValue()+1)
            {
                indexes.add(i);

            }
        }
        if(prevVal + 1 == suitList.get(suitList.size()-1).getValue())
        {
            indexes.add(suitList.size()-1);
        }

        //largest set must be at least 3 tiles long
       return indexes;
    }

    public ArrayList<Integer> findLowestMatching(ArrayList<mTiles> suitList) {
        ArrayList<Integer> indexes = new ArrayList<Integer>();

        int heldVal, occurences = 0;

        for (int i = 0; i < suitList.size() - 1; i++) {
            heldVal = suitList.get(i).getValue();

            if (heldVal == suitList.get(i + 1).getValue()) {
                indexes.add(i);
                break;
            }
        }

        if(indexes.size() == 0) return indexes;


        for (int i = indexes.get(0)+1; i < suitList.size() &&
                suitList.get(indexes.get(0)).getValue() == suitList.get(i).getValue(); i++)
        {

            if(suitList.get(indexes.get(0)).getValue() == suitList.get(i).getValue());
            {
                indexes.add(i);
            }
        }

        if(indexes.size() == suitList.size()) return indexes;
        //This next if statement is to check if by removing these tiles it would ruin a set
        //if a tile can be removed without ruining the matching tiles


        return indexes;
    }

    public ArrayList<Integer> findLowestSet(ArrayList<mTiles> suitList)
    {
        ArrayList<Integer> indexes = new ArrayList<>();

        for(int i = 0; i < suitList.size(); i++)
        {
            for(int j = i; j < suitList.size(); j++)
            {
                if(suitList.get(i).getValue() > suitList.get(j).getValue())
                {
                    Collections.swap(suitList, i, j);
                }
            }
        }

        int prevVal;

        for(int i = 0; i < suitList.size(); i++)
        {
            prevVal = suitList.get(i).getValue();

            for(int j = i + 1; j < suitList.size(); j++)
            {

                if(suitList.get(j).getValue() == prevVal+1)
                {
                    prevVal = suitList.get(j).getValue();
                    if(indexes.size() == 0) indexes.add(i);
                    indexes.add(j);
                }
            }

            if(indexes.size() > 2) return indexes;
            else indexes.clear();
        }

        return indexes;
    }

    public boolean mahjonggCheck(ArrayList<mTiles> pHand)
    {

        ArrayList<mTiles> bamboos = new ArrayList<mTiles> ();
        ArrayList<mTiles> dots = new ArrayList<mTiles> ();
        ArrayList<mTiles> characters = new ArrayList<mTiles> ();

        for(int i = 0; i < pHand.size(); i++)
        {
            if(pHand.get(i).getSuit() == "Bamboo")
            {
                bamboos.add(pHand.get(i));
            }
            else if(pHand.get(i).getSuit() == "Dots")
            {
                dots.add(pHand.get(i));
            }
            else if(pHand.get(i).getSuit() == "Characters")
            {
                characters.add(pHand.get(i));
            }
        }

        if(checkSuit2(bamboos))
        {
            if(checkSuit2(dots))
            {
                if(checkSuit2(characters))
                {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkSuit2 (ArrayList<mTiles> suitList)
    {

        for(int i = 0; i < suitList.size(); i++)
        {
            for(int j = i; j < suitList.size(); j++)
            {
                if(suitList.get(i).getValue() > suitList.get(j).getValue())
                {
                    Collections.swap(suitList, i, j);
                }
            }
        }

        int prevSize = -1;
        ArrayList<Integer> temp = findLowestSet(suitList);
        while(suitList.size() != prevSize)
        {
            prevSize = suitList.size();
            for(int i = temp.size() - 1; i >= 0; i--)
            {
                suitList.remove(temp.get(i).intValue());
            }
            temp = findLowestSet(suitList);
        }

        prevSize = -1;
        if(suitList.size() == 0) return true;

        temp = findLowestMatching(suitList);
        while(suitList.size() != prevSize)
        {
            prevSize = suitList.size();
            for(int i = temp.size() - 1; i >= 0; i--)
            {
                suitList.remove(temp.get(i).intValue());
            }
            temp = findLowestMatching(suitList);
        }

        if(suitList.size() == 0) return true;
        return false;
    }

    public ArrayList<mTiles> findPreferredTiles(ArrayList<mTiles> pHand)
    {
        ArrayList<mTiles> viableTiles = new ArrayList<> ();

        for(int i = 0; i < pHand.size(); i++)
        {
            mTiles curr = new mTiles(pHand.get(i).getValue(), pHand.get(i).getSuit());
            if(!viableTiles.contains(curr))
            {
                viableTiles.add(curr);
            }

            if(curr.getValue() > 1)
            {
                if(!viableTiles.contains(
                        new mTiles(pHand.get(i).getValue()-1,pHand.get(i).getSuit())))
                {
                    viableTiles.add(new mTiles(pHand.get(i).getValue()-1,pHand.get(i).getSuit()));
                }
            }
            if(curr.getValue() < 9)
            {
                if(!viableTiles.contains(
                        new mTiles(pHand.get(i).getValue()+1,pHand.get(i).getSuit())))
                {
                    viableTiles.add(new mTiles(pHand.get(i).getValue()+1,pHand.get(i).getSuit()));
                }
            }
        }

        return viableTiles;
    }

    public mTiles tileToDiscard(ArrayList<mTiles> pHand)
    {
        int prevSize = -1;

        ArrayList<mTiles> bamboos = new ArrayList<mTiles> ();
        ArrayList<mTiles> dots = new ArrayList<mTiles> ();
        ArrayList<mTiles> characters = new ArrayList<mTiles> ();

        for(int i = 0; i < pHand.size(); i++)
        {
            if(pHand.get(i).getSuit() == "Bamboo")
            {
                bamboos.add(pHand.get(i));
            }
            else if(pHand.get(i).getSuit() == "Dots")
            {
                dots.add(pHand.get(i));
            }
            else if(pHand.get(i).getSuit() == "Characters")
            {
                characters.add(pHand.get(i));
            }
        }

        if(!checkSuit2(bamboos))
        {
            if(findBadTile(bamboos) != null) return findBadTile(bamboos);
        }
        if(!checkSuit2(dots))
        {
            if(findBadTile(dots) != null) return findBadTile(dots);
        }
        if(!checkSuit2(characters))
        {
            if(findBadTile(characters) != null) return findBadTile(characters);
        }
        return null;
    }

    public mTiles findBadTile(ArrayList<mTiles> suitList)
    {
        for(int i = 0; i < suitList.size(); i++)
        {
            for(int j = i; j < suitList.size(); j++)
            {
                if(suitList.get(i).getValue() > suitList.get(j).getValue())
                {
                    Collections.swap(suitList, i, j);
                }
            }
        }

        int prevSize = -1;
        ArrayList<Integer> temp = findLowestSet(suitList);
        while(suitList.size() != prevSize)
        {
            prevSize = suitList.size();
            for(int i = temp.size() - 1; i >= 0; i--)
            {
                suitList.remove(temp.get(i).intValue());
            }
            temp = findLowestSet(suitList);
        }

        prevSize = -1;
        if(suitList.size() == 0) return null;

        temp = findLowestMatching(suitList);
        while(suitList.size() != prevSize)
        {
            prevSize = suitList.size();
            for(int i = temp.size() - 1; i >= 0; i--)
            {
                suitList.remove(temp.get(i).intValue());
            }
            temp = findLowestMatching(suitList);
        }

        if(suitList.size() == 0) return null;
        return suitList.get(0);
    }
}




package edu.up.cs301.pig;

import edu.up.cs301.game.infoMsg.GameState;

public class PigGameState extends GameState {
    private int playerTurnID;
    private int playerZeroScore;
    private int playerOneScore;
    private int runningTotal;
    private int currDieVal;


    public PigGameState(){
        playerTurnID = 0;
        playerZeroScore = 0;
        playerOneScore = 0;
        runningTotal = 0;
        currDieVal = 1;
    }
    public PigGameState(PigGameState copy){
        playerTurnID = copy.playerTurnID;
        playerZeroScore = copy.playerZeroScore;
        playerOneScore = copy.playerOneScore;
        runningTotal = copy.runningTotal;
        currDieVal = copy.currDieVal;
    }

    public int getPlayerTurnID(){ return playerTurnID; }

    public int getPlayerZeroScore() { return playerZeroScore; }

    public int getPlayerOneScore() { return playerOneScore; }

    public int getRunningTotal() { return runningTotal; }

    public int getCurrDieVal() { return currDieVal; }

    public void setPlayerTurnID(int newID){ playerTurnID = newID; }

    public void setPlayerZeroScore(int playerZeroScore) { this.playerZeroScore = playerZeroScore; }

    public void setPlayerOneScore(int playerOneScore) { this.playerOneScore = playerOneScore; }

    public void setRunningTotal(int runningTotal) { this.runningTotal = runningTotal; }

    public void setCurrDieVal(int currDieVal) { this.currDieVal = currDieVal; }
}

package edu.up.cs301.pig;

import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.LocalGame;
import edu.up.cs301.game.actionMsg.GameAction;
import edu.up.cs301.game.infoMsg.GameState;
import java.util.Random;

import android.util.Log;

// dummy comment, to see if commit and push work from srvegdahl account

/**
 * class PigLocalGame controls the play of the game
 *
 * @author Andrew M. Nuxoll, modified by Steven R. Vegdahl
 * @version February 2016
 */
public class PigLocalGame extends LocalGame {
    private PigGameState stateOfGame;
    private Random ran = new Random();
    /**
     * This ctor creates a new game state
     */
    public PigLocalGame() {
        stateOfGame = new PigGameState();
    }

    /**
     * can the player with the given id take an action right now?
     */
    @Override
    protected boolean canMove(int playerIdx) {
        if(playerIdx == stateOfGame.getPlayerTurnID()){
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * This method is called when a new action arrives from a player
     *
     * @return true if the action was taken or false if the action was invalid/illegal.
     */
    @Override
    protected boolean makeMove(GameAction action) {
        int genNum;
        genNum = ran.nextInt(6) + 1;
        if(action instanceof PigHoldAction){
            if(stateOfGame.getPlayerTurnID() == 0){
                stateOfGame.setPlayerZeroScore(stateOfGame.getPlayerZeroScore() + stateOfGame.getRunningTotal());
                stateOfGame.setRunningTotal(0);
                if(players.length > 1) {
                    stateOfGame.setPlayerTurnID(1);
                }
                stateOfGame.setCurrDieVal(genNum);
                return true;
            }
            else{
                stateOfGame.setPlayerOneScore(stateOfGame.getPlayerOneScore() + stateOfGame.getRunningTotal());
                stateOfGame.setRunningTotal(0);
                if(players.length > 1) {
                    stateOfGame.setPlayerTurnID(0);
                }
                stateOfGame.setCurrDieVal(genNum);
                return true;
            }
        }
        else if(action instanceof PigRollAction){
            if(genNum != 1){
                stateOfGame.setRunningTotal(stateOfGame.getRunningTotal() + genNum);
                stateOfGame.setCurrDieVal(genNum);
                return true;
            }
            else{
                stateOfGame.setRunningTotal(0);
                if(stateOfGame.getPlayerTurnID() == 1) {
                    if(players.length > 1) {
                        stateOfGame.setPlayerTurnID(0);
                    }
                    return true;
                }
                else{
                    if(players.length > 1) {
                        stateOfGame.setPlayerTurnID(1);
                    }
                    return true;
                }
            }
        }
        else{
            return false;
        }

    }//makeMove

    /**
     * send the updated state to a given player
     */
    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {
        PigGameState copied = new PigGameState(stateOfGame);
        p.sendInfo(copied);
    }//sendUpdatedSate

    /**
     * Check if the game is over
     *
     * @return
     * 		a message that tells who has won the game, or null if the
     * 		game is not over
     */
    @Override
    protected String checkIfGameOver() {
        if(stateOfGame.getPlayerOneScore() >= 50){
            return "Congratulation Player One on winning with " + stateOfGame.getPlayerOneScore() + " points!";
        }
        else if(stateOfGame.getPlayerZeroScore() >= 50){
            return "Congratulation Player Zero on winning with " + stateOfGame.getPlayerZeroScore() + " points!";
        }
        return null;
    }

}// class PigLocalGame

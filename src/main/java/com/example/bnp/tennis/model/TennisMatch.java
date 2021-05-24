package com.example.bnp.tennis.model;

public class TennisMatch {

    private Player playerOne;
    private Player playerTwo;

    public TennisMatch(String playerOneName, String playerTwoName){
        playerOne = new Player(playerOneName);
        playerTwo = new Player(playerTwoName);
    }

    public Player getPlayerOne() {
        return playerOne;
    }

    public void setPlayerOne(Player playerOne) {
        this.playerOne = playerOne;
    }

    public Player getPlayerTwo() {
        return playerTwo;
    }

    public void setPlayerTwo(Player playerTwo) {
        this.playerTwo = playerTwo;
    }
}

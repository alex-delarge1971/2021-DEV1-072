package com.example.bnp.tennis.logic;

import com.example.bnp.tennis.model.Player;
import com.example.bnp.tennis.model.TennisMatch;
import org.springframework.stereotype.Service;

@Service
public class TennisMatchService {

    public TennisMatch createTennisMatch(String playerOneName, String playerTwoName){
        System.out.println(playerOneName);
        return new TennisMatch(playerOneName, playerTwoName);
    }

    public boolean isMatchFinished(TennisMatch tennisMatch){

        return (!draw(tennisMatch)) && (hasAdvantage(tennisMatch)) && (Math.abs(tennisMatch.getPlayerOne().getPoint() - tennisMatch.getPlayerTwo().getPoint())>= 2);
    }

    public String printWinner(TennisMatch tennisMatch){
        if(tennisMatch.getPlayerOne().getPoint() - tennisMatch.getPlayerTwo().getPoint() > 0){
            return tennisMatch.getPlayerOne().getName() + " wins";
        }else{
            return tennisMatch.getPlayerTwo().getName() + " wins";
        }
    }


    public void player1Scores(TennisMatch tennisMatch){
        tennisMatch.getPlayerOne().scorePoint();
    }

    public void player2Scores(TennisMatch tennisMatch){
        tennisMatch.getPlayerTwo().scorePoint();
    }



    public String printScores(TennisMatch tennisMatch)
    {

        if(hasAdvantage(tennisMatch)){
            if(draw(tennisMatch)){
                return "Deuce";
            }
            return "Advantage " + getWinningPlayer(tennisMatch).getName();
        }else{
            if(draw(tennisMatch)){
                return tennisMatch.getPlayerOne().printPoint() + " all";
            }else{
                StringBuffer scorePrint = new StringBuffer();
                scorePrint.append(tennisMatch.getPlayerOne().getName());
                scorePrint.append(": ");
                scorePrint.append(tennisMatch.getPlayerOne().printPoint());
                scorePrint.append(" to ");
                scorePrint.append(tennisMatch.getPlayerTwo().getName());
                scorePrint.append(": ");
                scorePrint.append(tennisMatch.getPlayerTwo().printPoint());

                return scorePrint.toString();
            }
        }
    }

    private boolean hasAdvantage(TennisMatch tennisMatch){
        return tennisMatch.getPlayerOne().getPoint() > 3 || tennisMatch.getPlayerTwo().getPoint() > 3;
    }

    private boolean draw(TennisMatch tennisMatch){
        return tennisMatch.getPlayerOne().getPoint() == tennisMatch.getPlayerTwo().getPoint();
    }

    private Player getWinningPlayer(TennisMatch tennisMatch){
        if(tennisMatch.getPlayerOne().getPoint() > tennisMatch.getPlayerTwo().getPoint()){
            return tennisMatch.getPlayerOne();
        }else{
            return tennisMatch.getPlayerTwo();
        }
    }

}

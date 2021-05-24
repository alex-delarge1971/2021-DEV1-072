package com.example.bnp.tennis.controller;

import com.example.bnp.tennis.constant.Constants;
import com.example.bnp.tennis.logic.TennisMatchService;
import com.example.bnp.tennis.model.StartMatchRequest;
import com.example.bnp.tennis.model.TennisMatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/tennis")
public class TennisController {

    @Autowired
    private TennisMatchService tennisMatchService;

    @PostMapping("startmatch")
    public String startMatch(StartMatchRequest request, HttpSession session){

        TennisMatch tennisMatch = (TennisMatch) session.getAttribute(Constants.MATCH_SESSION_KEY);

        if(tennisMatch != null){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Match already started"
            );
        }

        tennisMatch = tennisMatchService.createTennisMatch(request.getPlayerOneName(), request.getPlayerTwoName());

        session.setAttribute(Constants.MATCH_SESSION_KEY, tennisMatch);

        return tennisMatchService.printScores(tennisMatch);
    }

    @PostMapping("playerOneScores")
    public String playerOneScores(HttpSession session){
        TennisMatch tennisMatch = (TennisMatch) session.getAttribute(Constants.MATCH_SESSION_KEY);

        if(tennisMatch == null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "No match is being played"
            );
        }

        tennisMatchService.player1Scores(tennisMatch);

        if(tennisMatchService.isMatchFinished(tennisMatch)){
            session.setAttribute(Constants.MATCH_SESSION_KEY, null);
            return tennisMatchService.printWinner(tennisMatch);
        }

        return tennisMatchService.printScores(tennisMatch);
    }

    @PostMapping("playerTwoScores")
    public String playerTwoScores(HttpSession session){
        TennisMatch tennisMatch = (TennisMatch) session.getAttribute(Constants.MATCH_SESSION_KEY);

        if(tennisMatch == null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "No match is being played"
            );
        }

        tennisMatchService.player2Scores(tennisMatch);

        if(tennisMatchService.isMatchFinished(tennisMatch)){
            session.setAttribute(Constants.MATCH_SESSION_KEY, null);
            return tennisMatchService.printWinner(tennisMatch);
        }

        return tennisMatchService.printScores(tennisMatch);
    }
}

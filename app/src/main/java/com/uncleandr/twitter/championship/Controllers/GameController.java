package com.uncleandr.twitter.championship.Controllers;

import com.uncleandr.twitter.championship.DAO.Game;
import com.uncleandr.twitter.championship.DAO.Gamer;
import com.uncleandr.twitter.championship.DAO.Match;
import com.uncleandr.twitter.championship.DAO.MatchStatus;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.TreeMap;

/**
 * Created by zexir on 05.05.2015.
 */
public class GameController
{
    public Match GetNextMatch( ArrayList< Match > previousMatches, Collection< Gamer > gamers )
    {
        if (gamers == null || gamers.size() < 2) {
            //throw new Exception
        }
        Integer maxMatchesOfGamer = gamers.size()-1;

        Gamer firstGamer = null;
        Gamer secondGamer = null;
        Integer minWeight = Integer.MAX_VALUE;
        TreeMap< Gamer, Integer > candidatesForPlay = new TreeMap<>();
        for ( Gamer gamer : gamers )
        {
            Integer weight = 0;
            Integer playedMatches = 0;
            if (previousMatches != null)
                for ( Match match : previousMatches ) {
                    if (match.getGamer1().equals( gamer ) || match.getGamer2().equals( gamer ))
                    {
                        playedMatches++;
                        weight+=previousMatches.indexOf( match );
                    }
                }

            if (playedMatches<maxMatchesOfGamer)
            {
                candidatesForPlay.put( gamer, weight );
                if (weight<minWeight){
                    minWeight = weight;
                    firstGamer = gamer;
                }
            }
        }
        if (candidatesForPlay.size()<=1)
            return null;

        candidatesForPlay.remove( firstGamer );
        while(secondGamer==null)
        {
            if (candidatesForPlay.isEmpty()) {
                //throw new exception
            }
            minWeight = Integer.MAX_VALUE;
            for ( Gamer gamer : candidatesForPlay.keySet() )
            {
                if (candidatesForPlay.get( gamer )<minWeight){
                    minWeight = candidatesForPlay.get( gamer );
                    secondGamer = gamer;
                }
            }

            if (previousMatches != null)
                for ( Match match : previousMatches ) {
                    if (match.getGamer1().equals( firstGamer ) && match.getGamer2().equals( secondGamer ) || match.getGamer2().equals( firstGamer ) && match.getGamer1().equals( secondGamer ))
                    {
                        candidatesForPlay.remove( secondGamer );
                        secondGamer = null;
                        break;
                    }
                }
        }
        Match match = new Match();
        match.setGamer1( firstGamer );
        match.setGamer2( secondGamer );
        return match;
    }


    public ArrayList< Result > getWinners( Game game )
    {
        Collection< Gamer > gamersCollection = game.getGamers();
        Collection< Match > matchesCollection = game.getMatches();

        TreeMap< Gamer, Result > results = new TreeMap<>();
        for ( Gamer gamer : gamersCollection ) results.put( gamer, new Result( gamer ) );

        for ( Match match : matchesCollection )
        {
            Result firstResult = results.get( match.getGamer1() );
            Result secondResult = results.get( match.getGamer2() );
            int score1 = match.getScore1();
            int score2 = match.getScore2();
            firstResult.goals = secondResult.missed = score1;
            firstResult.missed = secondResult.goals = score2;
            if ( score1 > score2 )
            {
                firstResult.wins++;
                secondResult.loses++;
            }
            else
            {
                firstResult.loses++;
                secondResult.wins++;
            }
        }
        ArrayList< Result > resultsList = new ArrayList<>( results.values() );
        Collections.sort( resultsList );
        for ( Result result : resultsList )
        {
            result.place = resultsList.indexOf( result );
        }
        return resultsList;
    }
}

class Result implements Comparable< Result >
{
    public Gamer gamer;
    public int place;
    public int wins;
    public int loses;
    public int goals;
    public int missed;

    public Result( Gamer gamer )
    {
        this.gamer = gamer;
    }

    @Override
    public int compareTo( Result another )
    {
        return this.wins - another.wins;
    }
}
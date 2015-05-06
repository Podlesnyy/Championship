package com.uncleandr.twitter.championship.Controllers;

import com.uncleandr.twitter.championship.DAO.Match;
import com.uncleandr.twitter.championship.DAO.MatchStatus;

/**
 * Created by zexir on 05.05.2015.
 */
public class MatchController
{
    public void StartMatch( Match match )
    {
        if ( match.getGamer1() == null || match.getGamer2() == null && match.getStatus() != MatchStatus.Ready )
        {
            throw new IllegalArgumentException();
        }
        match.setStatus( MatchStatus.InProgress );
    }

    public void FinishMatch( Match match, int score1, int score2, boolean isFinished )
    {
        if ( match.getStatus() != MatchStatus.InProgress )
        {
            throw new IllegalArgumentException();
        }
        if ( isFinished )
        {
            match.setStatus( MatchStatus.Finished );
        }
        if ( score1 != match.getScore1() )
        {
            match.setScore1( score1 );
        }
        if ( score2 != match.getScore2() )
        {
            match.setScore2( score2 );
        }
        if ( isFinished )
        {
            match.setStatus( MatchStatus.Finished );
        }
    }
}

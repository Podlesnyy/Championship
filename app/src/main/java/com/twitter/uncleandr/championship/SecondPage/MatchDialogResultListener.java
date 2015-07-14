package com.twitter.uncleandr.championship.SecondPage;

import android.content.DialogInterface;
import android.util.Pair;
import android.widget.ArrayAdapter;

import com.j256.ormlite.dao.ForeignCollection;
import com.twitter.uncleandr.championship.DAO.Gamer;
import com.twitter.uncleandr.championship.DAO.Match;
import com.twitter.uncleandr.championship.DB.DatabaseManager;

import java.sql.SQLException;

/**
 * Created by zexir on 18.05.2015.
 */
public class MatchDialogResultListener implements DialogInterface.OnClickListener
{
    MatchDialogFragment dialogFragment;
    private ArrayAdapter< Match > adapter;
    private ForeignCollection< Match > matches;
    private Match match;
    private Boolean addToAdapter;

    public MatchDialogResultListener( ArrayAdapter< Match > adapter, ForeignCollection< Match > matches, Match match, Boolean addToAdapter )
    {
        this.adapter = adapter;
        this.matches = matches;
        this.match = match;
        this.addToAdapter = addToAdapter;
    }

    @Override
    public void onClick( DialogInterface dialog, int which )
    {
        Pair< Gamer, Gamer > gamers = dialogFragment.getGamers();
        if ( gamers.first == null || gamers.second == null )
        {
            return;
        }
        match.setGamer1( gamers.first );
        match.setGamer2( gamers.second );
        if ( addToAdapter )
        {
            try
            {
                DatabaseManager.getInstance().getHelper().getMatchDao().create( match );
            } catch ( SQLException e )
            {
                e.printStackTrace();
            }
            matches.add( match );
            adapter.add( match );
        }
        else
        {
            adapter.notifyDataSetChanged();
            try
            {
                DatabaseManager.getInstance().getHelper().getMatchDao().update( match );
            } catch ( SQLException e )
            {
                e.printStackTrace();
            }
        }
    }
}

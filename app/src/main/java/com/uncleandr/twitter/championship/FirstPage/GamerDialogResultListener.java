package com.uncleandr.twitter.championship.FirstPage;

import android.content.DialogInterface;
import android.widget.ArrayAdapter;

import com.j256.ormlite.dao.ForeignCollection;
import com.uncleandr.twitter.championship.DAO.Gamer;
import com.uncleandr.twitter.championship.DB.DatabaseManager;

import java.sql.SQLException;

/**
 * Created by Heisenberg on 28.04.2015.
 */
public class GamerDialogResultListener implements DialogInterface.OnClickListener
{
    GamerDialogFragment dialogFragment;
    private ArrayAdapter< Gamer > adapter;
    private ForeignCollection< Gamer > gamers;
    private Gamer gamer;
    private Boolean addToAdapter;

    public GamerDialogResultListener( ArrayAdapter< Gamer > adapter, ForeignCollection< Gamer > gamers, Gamer gamer, Boolean addToAdapter )
    {
        this.adapter = adapter;
        this.gamers = gamers;
        this.gamer = gamer;
        this.addToAdapter = addToAdapter;
    }

    @Override
    public void onClick( DialogInterface dialog, int which )
    {
        String name = dialogFragment.getGamerName();
        if ( name.isEmpty() )
        {
            return;
        }

        gamer.setName( name );
        if ( addToAdapter )
        {
            try
            {
                DatabaseManager.getInstance().getHelper().getGamerDao().create( gamer );
            }
            catch ( SQLException e )
            {
                e.printStackTrace();
            }
            gamers.add( gamer );
            adapter.add( gamer );
        }
        else
        {
            adapter.notifyDataSetChanged();
            try
            {
                DatabaseManager.getInstance().getHelper().getGamerDao().update( gamer );
            }
            catch ( SQLException e )
            {
                e.printStackTrace();
            }
        }
    }
}

package com.twitter.uncleandr.championship.GameActivity.GameFragment;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.twitter.uncleandr.championship.DAO.Game;
import com.twitter.uncleandr.championship.R;

public class PagerGameFragment extends android.support.v4.app.Fragment
{

    private Game game;

    public PagerGameFragment()
    {
    }

    public static PagerGameFragment newInstance( Game game )
    {
        PagerGameFragment fragment = new PagerGameFragment();
        fragment.game = game;
        return fragment;
    }

    @Override
    public void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setHasOptionsMenu( true );
        setRetainInstance( true );
    }

    @Override
    public void onActivityCreated( @Nullable Bundle savedInstanceState )
    {
        super.onActivityCreated( savedInstanceState );
    }

    @Override
    public boolean onContextItemSelected( MenuItem item )
    {
/*        AdapterView.AdapterContextMenuInfo info = ( AdapterView.AdapterContextMenuInfo ) item.getMenuInfo();
        if ( info.targetView.getId() != R.id.listViewGamers )
        {
            return false;
        }
        Gamer gamer = allGamers.get( info.position );
        adapter.remove( gamer );
        try
        {
            DatabaseManager.getInstance().getHelper().getGamerDao().delete( gamer );
        } catch ( SQLException e )
        {
            e.printStackTrace();
        }
        getGamers().remove( gamer );*/
        return true;
    }


    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState )
    {
        View view = inflater.inflate( R.layout.activity_game_fragment_game, container, false );
        EditText edit = ( EditText ) view.findViewById( R.id.editText );
        edit.setText( game.getName() );
        edit.setSelection( edit.getText().length() );
        return view;
    }

    @Override
    public void onCreateOptionsMenu( Menu menu, MenuInflater inflater )
    {
        //inflater.inflate( R.menu.menu_gamers, menu );
    }

    @Override
    public void onAttach( Activity activity )
    {
        super.onAttach( activity );
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
    }
}

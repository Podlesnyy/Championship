package com.twitter.uncleandr.championship.GameActivity.GameFragment;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.j256.ormlite.dao.ForeignCollection;
import com.twitter.uncleandr.championship.DAO.Game;
import com.twitter.uncleandr.championship.DAO.Gamer;
import com.twitter.uncleandr.championship.DB.DatabaseManager;
import com.twitter.uncleandr.championship.R;

import java.sql.SQLException;
import java.util.ArrayList;

public class PagerGameFragment extends android.support.v4.app.Fragment
{
    private ArrayAdapter< Gamer > adapter;
    private ArrayList< Gamer > allGamers;
    private Game game;

    public PagerGameFragment()
    {
        // Required empty public constructor
    }

    public static PagerGameFragment newInstance( Game game )
    {
        PagerGameFragment fragment = new PagerGameFragment();
        fragment.game = game;
        return fragment;
    }

    private ForeignCollection< Gamer > getGamers()
    {
        return game.getGamers();
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

        /*allGamers = new ArrayList<>( getGamers() );
        adapter = new ArrayAdapter<>( getActivity(), R.layout.gamers_list_text_view, allGamers );
        ListView listview = ( ListView ) getActivity().findViewById( R.id.listViewGamers );
        listview.setAdapter( adapter );
        listview.setOnItemClickListener( new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick( AdapterView< ? > parent, View view, int position, long id )
            {
                Gamer gamer = ( Gamer ) parent.getItemAtPosition( position );
                CreateDialog( game, gamer, false );
            }
        } );
        registerForContextMenu( listview );*/
    }

    @Override
    public void onCreateContextMenu( ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo )
    {
        if ( v.getId() == R.id.listViewGamers )
        {
            AdapterView.AdapterContextMenuInfo info = ( AdapterView.AdapterContextMenuInfo ) menuInfo;
            menu.setHeaderTitle( allGamers.get( info.position ).getName() );
            menu.add( Menu.NONE, 0, 0, R.string.remove );
        }
    }

    @Override
    public boolean onContextItemSelected( MenuItem item )
    {
        AdapterView.AdapterContextMenuInfo info = ( AdapterView.AdapterContextMenuInfo ) item.getMenuInfo();
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
        getGamers().remove( gamer );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item )
    {
        int id = item.getItemId();
        /*if ( id == R.id.action_add_gamer )
        {
            Gamer gamer = new Gamer();
            CreateDialog( game, gamer, true );
            return true;
        }*/
        return super.onOptionsItemSelected( item );
    }

    private void CreateDialog( Game game, Gamer gamer, Boolean addToAdapter )
    {
       /* FragmentManager fm = getActivity().getSupportFragmentManager();
        GamerDialogResultListener adder = new GamerDialogResultListener( adapter, game, gamer, addToAdapter );
        GamerDialogFragment dialog = GamerDialogFragment.newInstance( gamer.getName(), adder );
        adder.dialogFragment = dialog;
        dialog.show( fm, "GamerProps" );*/
    }

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState )
    {
        return inflater.inflate( R.layout.activity_game_fragment_game, container, false );
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

package com.uncleandr.twitter.championship.FirstPage;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.j256.ormlite.dao.ForeignCollection;
import com.uncleandr.twitter.championship.DAO.Gamer;
import com.uncleandr.twitter.championship.DB.DatabaseManager;

import java.sql.SQLException;
import java.util.ArrayList;

import comuncleandr.twitter.championship.R;

public class PagerGamersFragment extends android.support.v4.app.Fragment
{
    private ForeignCollection< Gamer > gamers;
    private ArrayAdapter< Gamer > adapter;
    private ArrayList< Gamer > alGamers;

    public PagerGamersFragment()
    {
        // Required empty public constructor
    }

    public static PagerGamersFragment newInstance( ForeignCollection< Gamer > gamers )
    {
        PagerGamersFragment fragment = new PagerGamersFragment();
        fragment.gamers = gamers;
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

        alGamers = new ArrayList<>( gamers );
        adapter = new ArrayAdapter<>( getActivity(), R.layout.gamers_list_text_view, alGamers );
        ListView listview = ( ListView ) getActivity().findViewById( R.id.listViewGamers );
        listview.setAdapter( adapter );
        listview.setOnItemClickListener( new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick( AdapterView< ? > parent, View view, int position, long id )
            {
                Gamer gamer = ( Gamer ) parent.getItemAtPosition( position );
                CreateDialog( gamer, false );
            }
        } );
        registerForContextMenu( listview );
    }

    @Override
    public void onCreateContextMenu( ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo )
    {
        if ( v.getId() == R.id.listViewGamers )
        {
            AdapterView.AdapterContextMenuInfo info = ( AdapterView.AdapterContextMenuInfo ) menuInfo;
            menu.setHeaderTitle( alGamers.get( info.position ).getName() );
            menu.add( Menu.NONE, 0, 0, R.string.Remove );
        }
    }

    @Override
    public boolean onContextItemSelected( MenuItem item )
    {
        AdapterView.AdapterContextMenuInfo info = ( AdapterView.AdapterContextMenuInfo ) item.getMenuInfo();
        Gamer gamer = alGamers.get( info.position );
        adapter.remove( gamer );
        try
        {
            DatabaseManager.getInstance().getHelper().getGamerDao().delete( gamer );
        }
        catch ( SQLException e )
        {
            e.printStackTrace();
        }
        gamers.remove( gamer );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item )
    {
        int id = item.getItemId();
        if ( id == R.id.action_add_gamer )
        {
            Gamer gamer = new Gamer();
            CreateDialog( gamer, true );
            return true;
        }
        return super.onOptionsItemSelected( item );
    }

    private void CreateDialog( Gamer gamer, Boolean addToAdapter )
    {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        GamerDialogResultListener adder = new GamerDialogResultListener( adapter, gamers, gamer, addToAdapter );
        GamerDialogFragment dialog = GamerDialogFragment.newInstance( gamer.getName(), adder );
        adder.dialogFragment = dialog;
        dialog.show( fm, "GamerProps" );
    }

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState )
    {
        return inflater.inflate( R.layout.fragment_pager_gamers, container, false );
    }

    @Override
    public void onCreateOptionsMenu( Menu menu, MenuInflater inflater )
    {
        inflater.inflate( R.menu.menu_gamers, menu );
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

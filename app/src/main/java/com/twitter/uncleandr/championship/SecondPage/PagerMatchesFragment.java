package com.twitter.uncleandr.championship.SecondPage;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import com.twitter.uncleandr.championship.DAO.Game;
import com.twitter.uncleandr.championship.DAO.Match;
import com.twitter.uncleandr.championship.DB.DatabaseManager;
import com.twitter.uncleandr.championship.R;

import java.sql.SQLException;
import java.util.ArrayList;

public class PagerMatchesFragment extends Fragment
{
    private ArrayAdapter< Match > adapter;
    private ArrayList< Match > allMatches;
    private Game game;

    public PagerMatchesFragment()
    {
        // Required empty public constructor
    }

    public static PagerMatchesFragment newInstance( Game game )
    {
        PagerMatchesFragment fragment = new PagerMatchesFragment();
        fragment.game = game;
        return fragment;
    }

    private ForeignCollection< Match > getMatches()
    {
        return game.getMatches();
    }

    @Override
    public void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setHasOptionsMenu( true );
        setRetainInstance( true );
    }

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState )
    {
        return inflater.inflate( R.layout.fragment_pager_matches, container, false );
    }

    @Override
    public void onActivityCreated( Bundle savedInstanceState )
    {
        super.onActivityCreated( savedInstanceState );
        allMatches = new ArrayList<>( getMatches() );
        adapter = new ArrayAdapter<>( getActivity(), R.layout.matches_list_text_view, allMatches );
        ListView listview = ( ListView ) getActivity().findViewById( R.id.listViewMatches );
        listview.setAdapter( adapter );
        listview.setOnItemClickListener( new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick( AdapterView< ? > parent, View view, int position, long id )
            {
                Match match = ( Match ) parent.getItemAtPosition( position );
                CreateDialog( match, false );
            }
        } );
        registerForContextMenu( listview );
    }

    private void CreateDialog( Match match, Boolean addToAdapter )
    {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        MatchDialogResultListener adder = new MatchDialogResultListener( adapter, getMatches(), match, addToAdapter );
        MatchDialogFragment dialog = MatchDialogFragment.newInstance( game, match.getGamer1(), match.getGamer2(), adder );
        adder.dialogFragment = dialog;
        dialog.show( fm, "MatchPrors" );
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

    @Override
    public void onCreateOptionsMenu( Menu menu, MenuInflater inflater )
    {
        // inflater.inflate( R.menu.menu_matches, menu );
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item )
    {
        int id = item.getItemId();
       /* if ( id == R.id.action_add_match )
        {
            Match match = new Match();
            CreateDialog( match, true );
            return true;
        }*/
        return super.onOptionsItemSelected( item );
    }

    @Override
    public void onCreateContextMenu( ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo )
    {
        if ( v.getId() == R.id.listViewMatches )
        {
            AdapterView.AdapterContextMenuInfo info = ( AdapterView.AdapterContextMenuInfo ) menuInfo;
            menu.setHeaderTitle( allMatches.get( info.position ).toString() );
            menu.add( Menu.NONE, 0, 0, R.string.remove );
        }
    }

    @Override
    public boolean onContextItemSelected( MenuItem item )
    {
        AdapterView.AdapterContextMenuInfo info = ( AdapterView.AdapterContextMenuInfo ) item.getMenuInfo();
        if ( info.targetView.getId() != R.id.listViewMatches )
        {
            return false;
        }
        Match match = allMatches.get( info.position );
        adapter.remove( match );
        try
        {
            DatabaseManager.getInstance().getHelper().getMatchDao().delete( match );
        } catch ( SQLException e )
        {
            e.printStackTrace();
        }
        getMatches().remove( match );
        return true;
    }
}

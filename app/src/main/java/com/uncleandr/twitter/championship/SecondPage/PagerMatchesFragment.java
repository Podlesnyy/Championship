package com.uncleandr.twitter.championship.SecondPage;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.j256.ormlite.dao.ForeignCollection;
import com.uncleandr.twitter.championship.DAO.Gamer;
import com.uncleandr.twitter.championship.DAO.Match;

import java.util.ArrayList;

import comuncleandr.twitter.championship.R;

public class PagerMatchesFragment extends Fragment implements AbsListView.OnItemClickListener
{

    private OnFragmentInteractionListener mListener;
    private ForeignCollection< Match > matches;
    private ArrayAdapter< Match > adapter;
    private ArrayList< Match > allMatches;

    public PagerMatchesFragment()
    {
    }

    public static PagerMatchesFragment newInstance( ForeignCollection< Match > matches )
    {
        PagerMatchesFragment fragment = new PagerMatchesFragment();
        fragment.matches = matches;
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
    public View onCreateView( LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState )
    {
        return inflater.inflate( R.layout.fragment_pager_matches, container, false );
    }

    @Override
    public void onActivityCreated( Bundle savedInstanceState )
    {
        super.onActivityCreated( savedInstanceState );
        allMatches = new ArrayList<>( matches );
        adapter = new ArrayAdapter<>( getActivity(), R.layout.matches_list_text_view, allMatches );
        ListView listview = ( ListView ) getActivity().findViewById( R.id.listViewMatches );
        listview.setAdapter( adapter );
        registerForContextMenu( listview );
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
        mListener = null;
    }

    @Override
    public void onCreateOptionsMenu( Menu menu, MenuInflater inflater )
    {
        inflater.inflate( R.menu.menu_matches, menu );
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item )
    {
        int id = item.getItemId();
        if ( id == R.id.action_add_match )
        {
            Match match = new Match();
            matches.add( match );
            adapter.add( match );
            return true;
        }
        return super.onOptionsItemSelected( item );
    }


    @Override
    public void onItemClick( AdapterView< ? > parent, View view, int position, long id )
    {
        if ( null != mListener )
        {
        }
    }

    public interface OnFragmentInteractionListener
    {
        // TODO: Update argument type and name
        void onFragmentInteraction( String id );
    }

}

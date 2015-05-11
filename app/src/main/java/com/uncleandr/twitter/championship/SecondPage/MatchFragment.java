package com.uncleandr.twitter.championship.SecondPage;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.j256.ormlite.dao.ForeignCollection;
import com.uncleandr.twitter.championship.DAO.Match;

import java.util.ArrayList;

import comuncleandr.twitter.championship.R;

public class MatchFragment extends android.support.v4.app.Fragment
{
    private ForeignCollection< Match > matches;
    private ArrayAdapter< Match > adapter;
    private ArrayList< Match > allMatches;

    public MatchFragment()
    {
    }

    public static MatchFragment newInstance( ForeignCollection< Match > matches )
    {
        MatchFragment fragment = new MatchFragment();
        fragment.matches = matches;
        return fragment;
    }

    @Override
    public void onActivityCreated( @Nullable Bundle savedInstanceState )
    {
        super.onActivityCreated( savedInstanceState );

        allMatches = new ArrayList<>( matches );
        adapter = new ArrayAdapter<>( getActivity(), R.layout.gamers_list_text_view, allMatches );
        ListView listview = ( ListView ) getActivity().findViewById( R.id.listViewGamers );
        listview.setAdapter( adapter );
        registerForContextMenu( listview );
    }

    @Override
    public void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setHasOptionsMenu( true );
        getActivity().setTitle( R.string.gamers );
    }

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState )
    {
        return inflater.inflate( R.layout.fragment_match, container, false );
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
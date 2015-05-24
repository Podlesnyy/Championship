package com.uncleandr.twitter.championship;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.uncleandr.twitter.championship.DAO.Game;
import com.uncleandr.twitter.championship.FirstPage.PagerGamersFragment;
import com.uncleandr.twitter.championship.SecondPage.PagerMatchesFragment;

import comuncleandr.twitter.championship.R;

/**
 * Created by Heisenberg on 19.04.2015.
 */
class PagerAdapter extends FragmentPagerAdapter
{
    PagerGamersFragment firstPage;
    PagerMatchesFragment secondPage;
    private Context context;

    public PagerAdapter( FragmentManager fm, Game game, Context context )
    {
        super( fm );
        this.context = context;
        firstPage = PagerGamersFragment.newInstance( game );
        secondPage = PagerMatchesFragment.newInstance( game );
    }


    @Override
    public Object instantiateItem( ViewGroup container, int position )
    {

        return super.instantiateItem( container, position );
    }

    @Override
    public Fragment getItem( int i )
    {
        if ( i == 0 )
        {
            return firstPage;
        }

        return secondPage;
    }

    @Override
    public int getCount()
    {
        return 2;
    }

    @Override
    public CharSequence getPageTitle( int position )
    {
        switch ( position )
        {
            case 0:
                return context.getString( R.string.gamers );
            case 1:
                return context.getString( R.string.matches );
        }
        return "INEEDNAME";
    }
}

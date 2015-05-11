package com.uncleandr.twitter.championship;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.uncleandr.twitter.championship.DAO.Game;
import com.uncleandr.twitter.championship.FirstPage.PagerGamersFragment;
import com.uncleandr.twitter.championship.SecondPage.MatchFragment;

/**
 * Created by Heisenberg on 19.04.2015.
 */
class PagerAdapter extends FragmentPagerAdapter
{
    PagerGamersFragment firstPage;
    MatchFragment secondPage;

    public PagerAdapter( FragmentManager fm, Game game )
    {
        super( fm );
        firstPage = PagerGamersFragment.newInstance( game.getGamers() );
        secondPage = new MatchFragment().newInstance( game.getMatches() );
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
        return "OBJECT " + ( position + 1 );
    }
}

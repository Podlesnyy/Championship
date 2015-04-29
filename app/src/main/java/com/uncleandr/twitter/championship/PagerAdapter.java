package com.uncleandr.twitter.championship;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;

import BO.Gamer;
import FirstPage.PagerGamersFragment;

/**
 * Created by Heisenberg on 19.04.2015.
 */
class PagerAdapter extends FragmentPagerAdapter
{
    PagerGamersFragment firstPage;
    SecondFragment secondPage;

    public PagerAdapter( FragmentManager fm, ArrayList< Gamer > gamers )
    {
        super( fm );
        firstPage = PagerGamersFragment.newInstance( gamers );
        secondPage = new SecondFragment();
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

        return new SecondFragment();
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
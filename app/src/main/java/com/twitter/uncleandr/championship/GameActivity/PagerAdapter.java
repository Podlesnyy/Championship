package com.twitter.uncleandr.championship.GameActivity;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.twitter.uncleandr.championship.DAO.Game;
import com.twitter.uncleandr.championship.GameActivity.GameFragment.PagerGameFragment;
import com.twitter.uncleandr.championship.R;

import java.util.ArrayList;


/**
 * Created by Heisenberg on 19.04.2015.
 */
class PagerAdapter extends FragmentPagerAdapter
{
    ArrayList< Fragment > fragments = new ArrayList<>();
    private Context context;

    public PagerAdapter( FragmentManager fm, Game game, Context context )
    {
        super( fm );
        this.context = context;
        fragments.add( PagerGameFragment.newInstance( game ) );
        //fragments.add( PagerGamersFragment.newInstance( game ) );
        //fragments.add( PagerMatchesFragment.newInstance( game ) );
    }


    @Override
    public Object instantiateItem( ViewGroup container, int position )
    {
        return super.instantiateItem( container, position );
    }

    @Override
    public Fragment getItem( int i )
    {
        return fragments.get( i );
    }

    @Override
    public int getCount()
    {
        return fragments.size();
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

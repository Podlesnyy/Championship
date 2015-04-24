package comuncleandr.twitter.championship;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

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
        secondPage = new SecondFragment( );
    }

    @Override
    public Fragment getItem( int i )
    {
        if ( i == 0 )
        {
            return firstPage;
        }

        return new SecondFragment( );
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

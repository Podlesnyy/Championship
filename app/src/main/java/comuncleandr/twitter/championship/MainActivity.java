package comuncleandr.twitter.championship;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity
{
    ArrayList< Gamer > gamers;
    PagerAdapter mAdapter;
    ViewPager mViewPager;

    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        gamers = new ArrayList<>( );
        AddGamer( "Andrey" );
        AddGamer( "Kate" );
        AddGamer( "Vika" );
        AddGamer( "Artemio" );


        mAdapter = new PagerAdapter( getSupportFragmentManager( ), gamers );
        mViewPager = ( ViewPager ) findViewById( R.id.view );
        mViewPager.setAdapter( mAdapter );
    }

    private void AddGamer( String name )
    {
        Gamer gamer = new Gamer( );
        gamer.setName( name );
        gamers.add( gamer );
    }


    @Override
    public boolean onCreateOptionsMenu( Menu menu )
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater( ).inflate( R.menu.menu_main, menu );

        return true;
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item )
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId( );

        //noinspection SimplifiableIfStatement
        if ( id == R.id.action_settings )
        {
            return true;
        }

        return super.onOptionsItemSelected( item );
    }
}



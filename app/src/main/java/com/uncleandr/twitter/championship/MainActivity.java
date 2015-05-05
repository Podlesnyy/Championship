package com.uncleandr.twitter.championship;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.uncleandr.twitter.championship.BO.Game;
import com.uncleandr.twitter.championship.DB.DatabaseHelper;
import com.uncleandr.twitter.championship.DB.DatabaseManager;

import java.sql.SQLException;
import java.util.List;

import comuncleandr.twitter.championship.R;


public class MainActivity extends ActionBarActivity
{
    PagerAdapter mAdapter;
    ViewPager mViewPager;
    private Game game;

    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        DatabaseManager.getInstance().init( this );

        try
        {
            InitGame();
            setContentView( R.layout.activity_main );

            mAdapter = new PagerAdapter( getSupportFragmentManager(), game );
            mViewPager = ( ViewPager ) findViewById( R.id.view );
            mViewPager.setAdapter( mAdapter );

        } catch ( SQLException e )
        {

        }

    }

    private void InitGame() throws SQLException
    {
        DatabaseHelper helper = DatabaseManager.getInstance().getHelper();
        List< Game > games = helper.getGameDao().queryForAll();
        if ( games.size() == 0 )
        {
            Game game = new Game();
            helper.getGameDao().create( game );
            games.add( game );
        }

        game = games.get( 0 );
    }

    @Override
    public void onResume()
    {
        super.onResume();

        if ( game == null )
        {
            try
            {
                InitGame();
            } catch ( SQLException e )
            {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        //Gson gson = new Gson();
        //String json = gson.toJson( gamers );
        //WriteGamersFile( json );
    }

    @Override
    public boolean onCreateOptionsMenu( Menu menu )
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate( R.menu.menu_main, menu );

        return true;
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item )
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if ( id == R.id.action_settings )
        {
            return true;
        }

        return super.onOptionsItemSelected( item );
    }
}



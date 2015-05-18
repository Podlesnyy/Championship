package com.uncleandr.twitter.championship;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.uncleandr.twitter.championship.DAO.Game;
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
        DatabaseManager.init( this );
        try
        {
            InitGame();
            setContentView( R.layout.activity_main );
            mAdapter = new PagerAdapter( getSupportFragmentManager(), game );
            mViewPager = ( ViewPager ) findViewById( R.id.view );
            mViewPager.setAdapter( mAdapter );
            SetPageChangeListener();
        }
        catch ( SQLException ignored )
        {
        }
    }

    private void SetPageChangeListener()
    {
        mViewPager.setOnPageChangeListener( new ViewPager.OnPageChangeListener()
        {
            @Override
            public void onPageScrolled( int position, float positionOffset, int positionOffsetPixels )
            {
            }
            @Override
            public void onPageSelected( int position )
            {
                switch ( position )
                {
                    case 0:
                        setTitle( R.string.gamers );
                    case 1:
                        setTitle( R.string.matches );
                }
            }
            @Override
            public void onPageScrollStateChanged( int state )
            {
            }
        } );
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
            }
            catch ( SQLException e )
            {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onPause()
    {
        super.onPause();
    }

    @Override
    public boolean onCreateOptionsMenu( Menu menu )
    {
        getMenuInflater().inflate( R.menu.menu_main, menu );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item )
    {
        int id = item.getItemId();
        return id == R.id.action_settings || super.onOptionsItemSelected( item );
    }
}
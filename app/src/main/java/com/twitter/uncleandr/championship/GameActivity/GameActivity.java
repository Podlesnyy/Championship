package com.twitter.uncleandr.championship.GameActivity;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.twitter.uncleandr.championship.DAO.Game;
import com.twitter.uncleandr.championship.R;


public class GameActivity extends AppCompatActivity
{
    static final String param1 = "game";
    PagerAdapter mAdapter;
    ViewPager mViewPager;
    private Game game;
    private TabLayout mSlidingTabLayout;

    public static void Create( AppCompatActivity activity, Game game )
    {
        Intent intent = new Intent( activity, GameActivity.class );
        intent.putExtra( param1, game.getId() );
        ActivityCompat.startActivity( activity, intent, null );
    }

    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_game );

        mAdapter = new PagerAdapter( getSupportFragmentManager(), game, getApplicationContext() );
        mViewPager = ( ViewPager ) findViewById( R.id.view );
        mViewPager.setAdapter( mAdapter );

        mSlidingTabLayout = ( TabLayout ) findViewById( R.id.sliding_tabs );
        mSlidingTabLayout.setupWithViewPager( mViewPager );
    }

    @Override
    public boolean onCreateOptionsMenu( Menu menu )
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate( R.menu.menu_main, menu );

        return true;
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item )
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected( item );
    }
}



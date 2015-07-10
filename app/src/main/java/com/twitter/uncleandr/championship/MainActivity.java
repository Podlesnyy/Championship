package com.twitter.uncleandr.championship;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;


public class MainActivity extends AppCompatActivity
{
    public static final String AVATAR_URL = "https://pbs.twimg.com/profile_images/412967939772796928/VwPG3rwa.jpeg";

    private DrawerLayout drawerLayout;
    private View content;

    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        initFab();
        initToolbar();
        setupDrawerLayout();

        content = findViewById( R.id.content );

        final ImageView avatar = ( ImageView ) findViewById( R.id.avatar );
        Picasso.with( this ).load( AVATAR_URL ).transform( new CircleTransform() ).into( avatar );
    }


    private void initFab()
    {
        findViewById( R.id.fab ).setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick( View v )
            {
                Snackbar.make( content, "FAB Clicked", Snackbar.LENGTH_SHORT ).show();
            }
        } );
    }

    private void initToolbar()
    {
        final Toolbar toolbar = ( Toolbar ) findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );
        final ActionBar actionBar = getSupportActionBar();

        if ( actionBar != null )
        {
            actionBar.setHomeAsUpIndicator( R.drawable.ic_menu_black_24dp );
            actionBar.setDisplayHomeAsUpEnabled( true );
        }
    }

    private void setupDrawerLayout()
    {
        drawerLayout = ( DrawerLayout ) findViewById( R.id.drawer_layout );

        NavigationView view = ( NavigationView ) findViewById( R.id.navigation_view );
        view.setNavigationItemSelectedListener( new NavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected( MenuItem menuItem )
            {
                Snackbar.make( content, menuItem.getTitle() + " pressed", Snackbar.LENGTH_LONG ).show();
                menuItem.setChecked( true );
                drawerLayout.closeDrawers();
                return true;
            }
        } );
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item )
    {
        switch ( item.getItemId() )
        {
            case android.R.id.home:
                drawerLayout.openDrawer( GravityCompat.START );
                return true;
        }

        return super.onOptionsItemSelected( item );
    }
}

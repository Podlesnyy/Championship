package com.twitter.uncleandr.championship;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.twitter.uncleandr.championship.DAO.Game;
import com.twitter.uncleandr.championship.DB.DatabaseHelper;
import com.twitter.uncleandr.championship.DB.DatabaseManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.OnItemClickListener
{
    public static final String AVATAR_URL = "https://pbs.twimg.com/profile_images/412967939772796928/VwPG3rwa.jpeg";

    private static List< ViewModel > items = new ArrayList<>();

    static
    {
        for ( int i = 1; i <= 10; i++ )
        {
            items.add( new ViewModel( "Item " + i, "http://lorempixel.com/500/500/animals/" + i ) );
        }
    }

    private DrawerLayout drawerLayout;
    private View content;
    private boolean clicked;
    private List< Game > games;
    private DatabaseHelper dbHelper;
    private RecyclerViewAdapter adapter;

    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        DatabaseManager.init( this );
        dbHelper = DatabaseManager.getInstance().getHelper();

        initRecyclerView();
        initFab();
        initToolbar();
        setupDrawerLayout();

        content = findViewById( R.id.content );

        final ImageView avatar = ( ImageView ) findViewById( R.id.avatar );
        Picasso.with( this ).load( AVATAR_URL ).transform( new CircleTransform() ).into( avatar );
    }

    private void initRecyclerView()
    {
        RecyclerView recyclerView = ( RecyclerView ) findViewById( R.id.recycler );
        recyclerView.setLayoutManager( new GridLayoutManager( this, 1 ) );
        try
        {
            games = DatabaseManager.getInstance().getHelper().getGameDao().queryForAll();
        } catch ( SQLException e )
        {
            e.printStackTrace();
        }
        adapter = new RecyclerViewAdapter( games );
        adapter.setOnItemClickListener( this );
        recyclerView.setAdapter( adapter );

        RecyclerView.ItemDecoration itemDecoration =
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST);
        recyclerView.addItemDecoration(itemDecoration);
    }

    @Override
    public boolean onPrepareOptionsMenu( Menu menu )
    {
        if ( clicked )
        {
            menu.add( Menu.NONE, 0, Menu.NONE, "View RSS" ).setIcon( R.drawable.abc_btn_radio_material )
                    .setShowAsAction( MenuItem.SHOW_AS_ACTION_ALWAYS );
        }
        return super.onPrepareOptionsMenu( menu );
    }

    private void initFab()
    {
        findViewById( R.id.fab ).setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick( View v )
            {
                Game game = new Game();
                try
                {
                    dbHelper.getGameDao().create( game );
                    games.add( game );
                    adapter.notifyItemInserted( games.size() - 1 );
                } catch ( SQLException e )
                {
                    e.printStackTrace();
                }
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

    @Override
    public void onItemClick( View view, ViewModel viewModel )
    {
        clicked = true;
        invalidateOptionsMenu();
        DetailActivity.navigate( this, view.findViewById( R.id.image ), viewModel );
    }
}

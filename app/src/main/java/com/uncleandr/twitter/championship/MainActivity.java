package com.uncleandr.twitter.championship;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

import BO.Gamer;
import comuncleandr.twitter.championship.R;


public class MainActivity extends ActionBarActivity
{
    ArrayList< Gamer > gamers;
    PagerAdapter mAdapter;
    ViewPager mViewPager;

    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );

        InitGamers();

        setContentView( R.layout.activity_main );

        mAdapter = new PagerAdapter( getSupportFragmentManager(), gamers );
        mViewPager = ( ViewPager ) findViewById( R.id.view );
        mViewPager.setAdapter( mAdapter );
    }

    private void InitGamers()
    {
        try
        {
            String fileContent = ReadGamersFile();
            Gson gson = new Gson();
            Type collectionType = new TypeToken< ArrayList< Gamer > >()
            {
            }.getType();
            gamers = gson.fromJson( fileContent, collectionType );
        } catch ( IOException e )
        {
            gamers = new ArrayList<>();
        }
    }

    @Override
    public void onResume()
    {
        super.onResume();

        if ( gamers == null )
        {
            InitGamers();
        }
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        Gson gson = new Gson();
        String json = gson.toJson( gamers );
        WriteGamersFile( json );
    }

    private void WriteGamersFile( String json )
    {
        try
        {
            OutputStreamWriter outputStreamWriter =
                    new OutputStreamWriter( openFileOutput( getString( R.string.json_file_name ), Context.MODE_PRIVATE ) );
            outputStreamWriter.write( json );
            outputStreamWriter.close();
        } catch ( IOException e )
        {
        }
    }

    String ReadGamersFile() throws IOException
    {
        InputStream inputStream = openFileInput( getString( R.string.json_file_name ) );
        BufferedReader r = new BufferedReader( new InputStreamReader( inputStream ) );
        StringBuilder total = new StringBuilder();
        String line;

        while ( ( line = r.readLine() ) != null )
        {
            total.append( line );
        }
        return total.toString();
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



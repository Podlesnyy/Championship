package com.twitter.uncleandr.championship.DB;

/**
 * Created by Heisenberg on 03.05.2015.
 */

import android.content.Context;

public class DatabaseManager
{
    static private DatabaseManager instance;
    private final DatabaseHelper helper;

    private DatabaseManager( Context ctx )
    {
        helper = new DatabaseHelper( ctx );
    }

    static public void init( Context ctx )
    {
        if ( null == instance )
        {
            instance = new DatabaseManager( ctx );
        }
    }

    static public DatabaseManager getInstance()
    {
        return instance;
    }

    public DatabaseHelper getHelper()
    {
        return helper;
    }
}
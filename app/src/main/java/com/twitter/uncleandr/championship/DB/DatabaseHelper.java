package com.twitter.uncleandr.championship.DB;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.twitter.uncleandr.championship.DAO.Game;
import com.twitter.uncleandr.championship.DAO.Gamer;
import com.twitter.uncleandr.championship.DAO.Match;


/**
 * Created by Heisenberg on 03.05.2015.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper
{
    // name of the database file for your application -- change to something appropriate for your app
    private static final String DATABASE_NAME = "champ.db";

    // any time you make changes to your database objects, you may have to increase the database version
    private static final int DATABASE_VERSION = 4;

    // the DAO object we use to access the SimpleData table
    //pressure
    private Dao< Game, Integer > gameDao = null;
    private Dao< Gamer, Integer > gamerDao = null;
    private Dao< Match, Integer > matchDao = null;

    public DatabaseHelper( Context context )
    {
        super( context, DATABASE_NAME, null, DATABASE_VERSION );
    }

    @Override
    public void onCreate( SQLiteDatabase database, ConnectionSource connectionSource )
    {
        try
        {
            TableUtils.createTable( connectionSource, Game.class );
            TableUtils.createTable( connectionSource, Gamer.class );
            TableUtils.createTable( connectionSource, Match.class );
        } catch ( SQLException e )
        {
            Log.e( DatabaseHelper.class.getName(), "Can't create database", e );
            throw new RuntimeException( e );
        } catch ( java.sql.SQLException e )
        {
            e.printStackTrace();
        }

    }

    @Override
    public void onUpgrade( SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion )
    {
        try
        {
            TableUtils.dropTable( connectionSource, Game.class, true );
            TableUtils.dropTable( connectionSource, Gamer.class, true );
            TableUtils.dropTable( connectionSource, Match.class, true );
            // Drop other tables here
            onCreate( db, connectionSource );
        } catch ( java.sql.SQLException e )
        {
            e.printStackTrace();
        }

    }

    public Dao< Game, Integer > getGameDao()
    {
        if ( null == gameDao )
        {
            try
            {
                gameDao = getDao( Game.class );
            } catch ( java.sql.SQLException e )
            {
                e.printStackTrace();
            }
        }
        return gameDao;
    }

    public Dao< Gamer, Integer > getGamerDao()
    {
        if ( null == gamerDao )
        {
            try
            {
                gamerDao = getDao( Gamer.class );
            } catch ( java.sql.SQLException e )
            {
                e.printStackTrace();
            }
        }
        return gamerDao;
    }

    public Dao< Match, Integer > getMatchDao()
    {
        if ( null == matchDao )
        {
            try
            {
                matchDao = getDao( Match.class );
            } catch ( java.sql.SQLException e )
            {
                e.printStackTrace();
            }
        }
        return matchDao;
    }
}
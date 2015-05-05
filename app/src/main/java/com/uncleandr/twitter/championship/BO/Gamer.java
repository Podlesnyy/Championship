package com.uncleandr.twitter.championship.BO;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Heisenberg on 24.04.2015.
 */
@DatabaseTable( tableName = "gamer" )
public class Gamer
{
    @DatabaseField( generatedId = true )
    private int id;


    @DatabaseField( foreign = true )
    private Game game;

    @DatabaseField
    private String name;

    @Override
    public String toString()
    {
        return name;
    }

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public int getId()
    {
        return id;
    }

    public void setId( int id )
    {
        this.id = id;
    }

    public Game getGame()
    {
        return game;
    }

    public void setGame( Game game )
    {
        this.game = game;
    }
}
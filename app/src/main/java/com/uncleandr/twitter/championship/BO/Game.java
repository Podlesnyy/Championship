package com.uncleandr.twitter.championship.BO;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Heisenberg on 29.04.2015.
 */
@DatabaseTable( tableName = "game" )
public class Game
{
    @DatabaseField( generatedId = true )
    private int id;

    @ForeignCollectionField
    private ForeignCollection< Gamer > gamers;

    @ForeignCollectionField
    private ForeignCollection< Gamer > matches;

    public ForeignCollection< Gamer > getGamers()
    {
        return gamers;
    }

    public void setGamers( ForeignCollection< Gamer > gamers )
    {
        this.gamers = gamers;
    }

    public ForeignCollection< Gamer > getMatches()
    {
        return matches;
    }

    public void setMatches( ForeignCollection< Gamer > matches )
    {
        this.matches = matches;
    }

    public int getId()
    {
        return id;
    }

    public void setId( int id )
    {
        this.id = id;
    }
}

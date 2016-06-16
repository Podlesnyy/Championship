package com.twitter.uncleandr.championship.DAO;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

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
    private ForeignCollection< Match > matches;
    @DatabaseField
    private String title;

    @DatabaseField
    private Date createDate;

    public ForeignCollection< Gamer > getGamers()
    {
        return gamers;
    }

    public void setGamers( ForeignCollection< Gamer > gamers )
    {
        this.gamers = gamers;
    }

    public ForeignCollection< Match > getMatches()
    {
        return matches;
    }

    public void setMatches( ForeignCollection< Match > matches )
    {
        this.matches = matches;
    }

    @Override
    public String toString()
    {
        return title;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle( String title )
    {
        this.title = title;
    }

    public int getId()
    {
        return id;
    }

    public void setId( int id )
    {
        this.id = id;
    }

    public Boolean getIsActive()
    {
        return true;
    }

    public Date getCreateDate()
    {
        return createDate;
    }

    public void setCreateDate( Date createDate )
    {
        this.createDate = createDate;
    }
}

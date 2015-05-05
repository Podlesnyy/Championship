package com.uncleandr.twitter.championship.BO;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Heisenberg on 03.05.2015.
 */
@DatabaseTable( tableName = "match" )
public class Match
{
    @DatabaseField( generatedId = true )
    private int id;

    @DatabaseField( foreign = true )
    private Gamer gamer1;

    @DatabaseField( foreign = true )
    private Gamer gamer2;

    @DatabaseField
    private int score1;

    @DatabaseField
    private int score2;

    @DatabaseField
    private Boolean finished;

    @DatabaseField( foreign = true )
    private Game game;

    public int getId()
    {
        return id;
    }

    public void setId( int id )
    {
        this.id = id;
    }

    public Gamer getGamer1()
    {
        return gamer1;
    }

    public void setGamer1( Gamer gamer1 )
    {
        this.gamer1 = gamer1;
    }

    public Gamer getGamer2()
    {
        return gamer2;
    }

    public void setGamer2( Gamer gamer2 )
    {
        this.gamer2 = gamer2;
    }

    public int getScore1()
    {
        return score1;
    }

    public void setScore1( int score1 )
    {
        this.score1 = score1;
    }

    public int getScore2()
    {
        return score2;
    }

    public void setScore2( int score2 )
    {
        this.score2 = score2;
    }

    public Boolean getFinished()
    {
        return finished;
    }

    public void setFinished( Boolean finished )
    {
        this.finished = finished;
    }
}
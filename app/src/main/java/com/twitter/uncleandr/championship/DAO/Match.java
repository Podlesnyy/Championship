package com.twitter.uncleandr.championship.DAO;

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
    private MatchStatus status;

    @DatabaseField( foreign = true )
    private Game game;

    @DatabaseField
    private String name;

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
        updateName();
    }

    public Gamer getGamer2()
    {
        return gamer2;
    }

    public void setGamer2( Gamer gamer2 )
    {
        this.gamer2 = gamer2;
        updateName();
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

    public MatchStatus getStatus()
    {
        return status;
    }

    public void setStatus( MatchStatus status )
    {
        this.status = status;
    }

    private void updateName()
    {
        name = String.format( "%1$s versus %2$s", gamer1 == null ? "Gamer 1" : gamer1.toString(), gamer2 == null ? "Gamer 2" : gamer2.toString() );
    }

    @Override
    public String toString()
    {
        return name;
    }
}
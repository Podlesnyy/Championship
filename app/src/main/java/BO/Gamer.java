package BO;

import java.util.UUID;

/**
 * Created by Heisenberg on 24.04.2015.
 */
public class Gamer
{
    private String name;
    private String uuid;

    public void CreateUUID()
    {
        uuid = UUID.randomUUID().toString();
    }

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return name;
    }

    public String getUuid()
    {
        return uuid;
    }
}
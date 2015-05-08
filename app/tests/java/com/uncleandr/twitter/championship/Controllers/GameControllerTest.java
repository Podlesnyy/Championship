package com.uncleandr.twitter.championship.Controllers;

import com.j256.ormlite.dao.BaseForeignCollection;
import com.j256.ormlite.dao.EagerForeignCollection;
import com.j256.ormlite.dao.ForeignCollection;
import com.uncleandr.twitter.championship.DAO.Game;
import com.uncleandr.twitter.championship.DAO.Gamer;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by zexir on 09.05.2015.
 */
public class GameControllerTest extends TestCase
{
    private GameController controller;

    @Before
    public void setUp() throws Exception
    {
        controller = new GameController();
    }

    @After
    public void tearDown() throws Exception
    {
        controller = null;
    }

    @Test
    public void testGetWinners() throws Exception
    {
    }
}
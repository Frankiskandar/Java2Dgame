/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.frank.PlatformerGame.entities.creatures;

import dev.frank.PlatformerGame.Game;
import dev.frank.PlatformerGame.Handler;
import java.awt.Graphics;
import java.util.ArrayList;
import junit.framework.TestCase;
import org.junit.Test;

/**
 *
 * @author Frank
 */
public class PlayerTest extends TestCase {
    
    public PlayerTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    @Test
    public void PlayerXSpawnTest() {
        System.out.println("PlayerXSpawnTest");
        Game game = new Game(1000, 500);
        Handler handler = new Handler(game);
        Player player = new Player(handler, 100,100);
        float expected = player.getX();
        assertEquals(100, expected);
    }

    
}

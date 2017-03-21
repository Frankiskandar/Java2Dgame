/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.frank.PlatformerGame.test;

import dev.frank.PlatformerGame.Game;
import dev.frank.PlatformerGame.Handler;
import dev.frank.PlatformerGame.entities.creatures.Creature;
import dev.frank.PlatformerGame.entities.creatures.Player;
import static junit.framework.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Frank
 */
public class TestClass {
            
    Game game = new Game("Platformer Game", 1000, 500);
    Handler handler = new Handler(game);
    Player player = new Player(handler, 100, 200);

     /**
     * Test of getWidth method, of class Game.
     */
    @Test
    public void testGetWidth() {
        System.out.println("Game Window Width Test");
        Game instance = new Game("Platformer Game", 1000, 500);
        int expResult = 1000;
        int result = instance.getWidth();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
                                
    /**
     * Test of getHeight method, of class Game.
     */
    @Test
    public void testGetHeight() {
        System.out.println("Game Window Height Test");
        Game instance = new Game("Platformer Game", 1000, 500);
        int expResult = 500;
        int result = instance.getHeight();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    @Test
    public void PlayerXSpawnTest() {
        System.out.println("Player X Spawn Test");
        float expected = player.getX();
        assertEquals(100.0, expected);
    }
    
    @Test
    public void PlayerYSpawnTest() {
        System.out.println("Player X Spawn Test");
        float expected = player.getY();
        assertEquals(200.0, expected);
    }
    
        /**
     * Test of getHealth method, of class Creature.
     */
    @Test
    public void testGetHealth() {
        System.out.println("Player's default health Test");
        int expResult = 3;
        int result = player.getHealth();
        assertEquals(expResult, result);

    }

    /**
     * Test of setHealth method, of class Creature.
     */
    @Test
    public void testSetHealth() {
        System.out.println("Player's setHealth Test");        
        int health = 50;
        player.setHealth(health);
        int result = player.getHealth();
        assertEquals(50, result);
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.frank.PlatformerGame.entities.creatures;

import dev.frank.PlatformerGame.Game;
import dev.frank.PlatformerGame.Handler;
import junit.framework.TestCase;

/**
 *
 * @author Frank
 */
public class CreatureTest extends TestCase {
    
    public CreatureTest(String testName) {
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

    /**
     * Test of getHealth method, of class Creature.
     */
    public void testGetHealth() {
        System.out.println("getHealth");
        Game game = new Game(1000, 500);
        Handler handler = new Handler(game);
        Creature instance = new Player(handler,100,100);;
        int expResult = 3;
        int result = instance.getHealth();
        assertEquals(expResult, result);

    }

    /**
     * Test of setHealth method, of class Creature.
     */
    public void testSetHealth() {
        System.out.println("setHealth");        
        Game game = new Game(1000, 500);
        Handler handler = new Handler(game);
        Creature instance = new Player(handler,100,100);
        int health = 50;
        instance.setHealth(health);
    }

 
}

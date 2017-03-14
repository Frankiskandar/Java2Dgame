/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.frank.PlatformerGame;

import dev.frank.PlatformerGame.gfx.GameCamera;
import dev.frank.PlatformerGame.input.KeyManager;
import dev.frank.PlatformerGame.input.MouseManager;
import junit.framework.TestCase;

/**
 *
 * @author Frank
 */
public class GameTest extends TestCase {
    
    public GameTest(String testName) {
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
     * Test of getWidth method, of class Game.
     */
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
    public void testGetHeight() {
        System.out.println("Game Window Height Test");
        Game instance = new Game("Platformer Game", 1000, 500);
        int expResult = 500;
        int result = instance.getHeight();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
}



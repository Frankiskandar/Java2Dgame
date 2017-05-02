/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.frank.PlatformerGame;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 *
 * @author Frank
 */
public class launcher {
    
     /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Game game = new Game(1000, 500);
        game.start();
        
        //run Junit Test
        Result result = JUnitCore.runClasses(dev.frank.PlatformerGame.test.TestClass.class);
		
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
    }
    
    
}

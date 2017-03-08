/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.frank.PlatformerGame;

import dev.frank.PlatformerGame.display.Display;

/**
 *
 * @author Frank
 */
public class launcher {
    
     /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       Game game = new Game("Tile Game", 1000, 500);
       game.start(); 
    }
    
    
}

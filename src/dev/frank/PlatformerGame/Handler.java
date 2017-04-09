/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.frank.PlatformerGame;

import dev.frank.PlatformerGame.gfx.GameCamera;
import dev.frank.PlatformerGame.input.KeyManager;
import dev.frank.PlatformerGame.input.MouseManager;
import dev.frank.PlatformerGame.worlds.World;

/**
 *
 * @author Frank
 */
public class Handler {
    
    //handler class allows us to pass 1 object (handler) and allows us access other object such as camera and key listener
    // will be useful to access world
    
    private Game game;
    private World world;
    
    public Handler (Game game) {
        this.game = game;
    }
    
    public GameCamera getGameCamera() {
        return game.getGameCamera();
    }
    
    public KeyManager getKeyManager() {
        return game.getKeyManager();
    }
    
    public MouseManager getMouseManager() {
        return game.getMouseManager();
    }
    
    public int getWidth() {
        return game.getWidth();
    }
    
    public int getHeight() {
        return game.getHeight();
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }
    
}

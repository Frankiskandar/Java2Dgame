/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.frank.PlatformerGame;

import dev.frank.PlatformerGame.gfx.Camera;
import dev.frank.PlatformerGame.input.KeyboardInputManager;
import dev.frank.PlatformerGame.input.MouseInputManager;
import dev.frank.PlatformerGame.maps.Map;

/**
 *
 * @author Frank
 */
public class Handler {
    
    //handler class allows us to pass 1 object (handler) and allows us access other object such as camera and keyboard listener
    // will be useful to access map
    
    private Game game;
    private Map map;
    
    public Handler (Game game) {
        this.game = game;
    }
    
    public Camera getGameCamera() {
        return game.getGameCamera();
    }
    
    public KeyboardInputManager getKeyboardInputManager() {
        return game.getKeyboardInputManager();
    }
    
    public MouseInputManager getMouseInputManager() {
        return game.getMouseInputManager();
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

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }
    
}

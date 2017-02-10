/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.frank.tilegame.state;

import dev.frank.tilegame.Game;
import java.awt.Graphics;

/**
 *
 * @author Frank
 */
public abstract class State {
    
    //game state manager
    private static State currentState = null;
    
    public static void setState(State state) {
        currentState = state;
    }
    
    public static State getState() {
        return currentState;
    }
    
    //CLASS
    
    protected Game game;
    
    public State(Game game){
        this.game = game;
    }
    //every single state has tick() and render()
    public abstract void tick();
    
    public abstract void render(Graphics g);
    
}

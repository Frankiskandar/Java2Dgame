/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.frank.PlatformerGame.state;

import dev.frank.PlatformerGame.Game;
import dev.frank.PlatformerGame.Handler;
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
    
    //protected Game game;
    protected Handler handler;
    
//    public State(Game game){
//        this.game = game;
//    }
    
    public State(Handler handler) {
        this.handler = handler;
    }
    
    //every single state has tick() and render()
    public abstract void tick();
    
    public abstract void render(Graphics g);
    
}

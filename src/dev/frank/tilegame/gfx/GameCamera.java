/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.frank.tilegame.gfx;

import dev.frank.tilegame.Game;
import dev.frank.tilegame.entities.Entity;

/**
 *
 * @author Frank
 */
public class GameCamera { //to follow our player
    
    private Game game;
    //position
    private float xOffset, yOffset;
    
    public GameCamera(Game game, float xOffset, float yOffset) {
        this.game = game;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }
    
    //set x and y offset so it center the entitiy
    public void centerOnEntity(Entity e) {
        // entity x posisition - game screen width
        // div by 2 so the player is centered not at the edge of the screen
        xOffset = e.getX() - game.getWidth() / 2 + e.getWidth() /2;
        yOffset = e.getY() - game.getHeight() / 2 + e.getHeight() /2;
    }
    
    // take these 2 number and add them to yx offset
    public void move(float xAmt, float yAmt) {
        xOffset += xAmt;
        yOffset += yAmt;
    }

    public float getxOffset() {
        return xOffset;
    }

    public void setxOffset(float xOffset) {
        this.xOffset = xOffset;
    }

    public float getyOffset() {
        return yOffset;
    }

    public void setyOffset(float yOffset) {
        this.yOffset = yOffset;
    }
    
    
    
}

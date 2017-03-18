/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.frank.PlatformerGame.gfx;

import dev.frank.PlatformerGame.Game;
import dev.frank.PlatformerGame.Handler;
import dev.frank.PlatformerGame.entities.Entity;
import dev.frank.PlatformerGame.tiles.Tile;

/**
 *
 * @author Frank
 */
public class GameCamera { // camera class to follow our player
    
    private Handler handler;
    //position
    private float xOffset, yOffset;
    
    public GameCamera(Handler handler, float xOffset, float yOffset) {
        this.handler = handler;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }
    
    public void checkBlankSpace() { // to check if we see undrawn outside map
        if (xOffset < 0) {
            xOffset = 0;
        }else if (xOffset > handler.getWorld().getWidth() * Tile.TILEWIDTH - handler.getWidth())
        {
            xOffset = handler.getWorld().getWidth() * Tile.TILEWIDTH - handler.getWidth();
        }
        
        if (yOffset < 0) {
            yOffset = 0;
        } else if (yOffset > handler.getWorld().getHeight() * Tile.TILEHEIGHT - handler.getHeight()) 
        {
            yOffset = handler.getWorld().getHeight() * Tile.TILEHEIGHT - handler.getHeight();
        }
    }
    
    //set x and y offset so it center the entitiy
    public void centerOnEntity(Entity e) {
        // entity x posisition - game screen width
        // div by 2 so the player is centered not at the edge of the screen
        xOffset = e.getX() - handler.getWidth() / 2 + e.getWidth() /2;
        yOffset = e.getY() - handler.getHeight() / 2 + e.getHeight() /2;
        checkBlankSpace();
    }
    
    // take these 2 number and add them to yx offset
    public void move(float xAmt, float yAmt) {
        xOffset += xAmt;
        yOffset += yAmt;
        // check blank space after we move the camera
        checkBlankSpace();
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

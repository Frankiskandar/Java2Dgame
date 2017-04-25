/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.frank.PlatformerGame.gfx;

import dev.frank.PlatformerGame.Handler;
import dev.frank.PlatformerGame.entities.Entity;
import dev.frank.PlatformerGame.entities.creatures.Player;
import dev.frank.PlatformerGame.tiles.Tile;

/**
 *
 * @author Frank
 */
public class Camera { // camera class to follow our player
    
    private Handler handler;
    //position
    private float xOffset, yOffset;
    
    public Camera(Handler handler, float xOffset, float yOffset) {
        this.handler = handler;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }
    
    public void checkBlankSpace() { // to check if we see undrawn outside map
        if (xOffset < 0) {
            xOffset = 0;
        } else if (xOffset > handler.getMap().getWidth() * Tile.TILEWIDTH - handler.getWidth()) {
            xOffset = handler.getMap().getWidth() * Tile.TILEWIDTH - handler.getWidth();
        }
        
        if (yOffset < 0) {
            yOffset = 0;
        } else if (yOffset > handler.getMap().getHeight() * Tile.TILEHEIGHT - handler.getHeight()) {
            yOffset = handler.getMap().getHeight() * Tile.TILEHEIGHT - handler.getHeight();
        }
    }
    
    //set x and y offset so it center the player
    public void centerOnPlayer(Player player) {
        // entity x posisition - game screen width
        // div by 2 so the player is centered not at the edge of the screen
        xOffset = player.getX() - handler.getWidth() / 2 + player.getWidth() /2;
        yOffset = player.getY() - handler.getHeight() / 2 + player.getHeight() /2;
        checkBlankSpace();
    }
    
    // take these 2 number and add them to yx offset
    public void move(float xAmount, float yAmount) {
        xOffset += xAmount;
        yOffset += yAmount;
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

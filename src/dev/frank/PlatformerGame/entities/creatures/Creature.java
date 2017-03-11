/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.frank.PlatformerGame.entities.creatures;

import dev.frank.PlatformerGame.Game;
import dev.frank.PlatformerGame.Handler;
import dev.frank.PlatformerGame.entities.Entity;
import dev.frank.PlatformerGame.tiles.Tile;

/**
 *
 * @author Frank
 */
public abstract class Creature extends Entity {

    public static final int DEFAULT_HEALTH = 10;
    public static final float DEFAULT_SPEED = 3.0f;
    public static final int DEFAULT_CREATURE_WIDTH = 64,
                            DEFAULT_CREATURE_HEIGTH = 64;
    final int FIREBALL_DAMAGE = 5;
    final int LEVEL1_DEAD_Y_COORDINATE = 1400;
    //protected int health;
    protected float speed;
    protected float xMove, yMove;
    public int health;
    protected float runSpeed;
    boolean hitWall = false;
    
    
    public Creature(Handler handler,float x, float y, int width, int heigth) {
        super(handler, x, y, width, heigth);
        health = DEFAULT_HEALTH;
        speed = DEFAULT_SPEED;
        xMove = 0;
        yMove = 0;
    }
    
    public void move() {
//        x += xMove;
//        y += yMove;
        // ga usah checkentity
     //   if (!checkEntityCollisions(xMove,0f))
            moveX();
     //   if(!checkEntityCollisions(0f, yMove))
            moveY();
//        moveX();
//        moveY();
    }
    
    //to move separately
    //going left and right
    public void moveX() {
       // x += xMove;
        //to help determine what corners to check for collision detection
        if(xMove > 0) { //moving right
            int tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILEWIDTH;
            //if there is not solid tile/ collision, move right
            if (!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT) &&
                !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT))
            {
                x += xMove;
                hitWall = false;
            } else { // if there is a collision
                //reset x position of player
                x = tx * Tile.TILEWIDTH - bounds.x - bounds.width - 1; // -1 so it doesnt lock the plaer
                hitWall = true;
            }
        } else if (xMove < 0 ) { //moving left //moving lower in the x axis
            //left side of bounding rectangle
            int tx = (int) (x + xMove + bounds.x) / Tile.TILEWIDTH;
            
            if (!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT) &&
                !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT))
            {
                x += xMove;
                hitWall = false;
            } else {
                x = tx * Tile.TILEWIDTH + Tile.TILEWIDTH - bounds.x; //might not need this
                hitWall = true;
            }
        }
        
    }
    //going up and down
    public void moveY() {
        //y += yMove;
        
        if (yMove < 0) { //moving up
            int ty = (int) (y + yMove + bounds.y) / Tile.TILEHEIGHT;
            
            if(!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty) && 
               !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)) //right side
            {
                y += yMove;
            }else { // if there is collision
                y = ty * Tile.TILEHEIGHT + Tile.TILEHEIGHT - bounds.y;
            }
            
            
        } else if (yMove > 0) { //going down
            int ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILEHEIGHT;
            
            if(!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty) && 
               !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)) //right side
            {
                y += yMove;
            } else { // if there is collision //might not need this
                y = ty * Tile.TILEHEIGHT - bounds.y - bounds.height - 1;
            }
            
        }
    }
    
    //if its solid return true
    //if its not solid return false
    protected boolean collisionWithTile(int x, int y) {
        return handler.getWorld().getTile(x, y).isSolid();
    }
    
 //getters and setters
    public float getxMove() {
        return xMove;
    }

    public void setxMove(float xMove) {
        this.xMove = xMove;
    }

    public float getyMove() {
        return yMove;
    }

    public void setyMove(float yMove) {
        this.yMove = yMove;
    }
    

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    } 
    
}
